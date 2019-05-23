//author Wallace
var t = {
    width: 1.5,
    height: 1.5,
    depth: 10,
    segments: 12,
    slices: 6,
    xRange: 0.8,
    yRange: 0.1,
    zRange: 1,
    ambient: "#525252",
    diffuse: "#FFFFFF",
    speed: 0.0002
};
var G = {
    count: 2,
    xyScalar: 1,
    zOffset: 100,
    ambient: "#002c4a",
    diffuse: "#005584",
    speed: 0.001,
    gravity: 1200,
    dampening: 0.95,
    minLimit: 10,
    maxLimit: null,
    minDistance: 20,
    maxDistance: 400,
    autopilot: false,
    draw: false,
    bounds: CAV.Vector3.create(),
    step: CAV.Vector3.create(Math.randomInRange(0.2, 1), Math.randomInRange(0.2, 1), Math.randomInRange(0.2, 1))
};
var canvas = "canvas";
var svg = "svg";
var configRenderer = {
    renderer: canvas
};
var timeDelta, timeStart = Date.now();
var L = CAV.Vector3.create();
var k = CAV.Vector3.create();
var $container = document.getElementById("container");
var $anitOut = document.getElementById("anitOut");
var $canvas, scene, mesh, plane, material;
var canvasRenderer;

function create() {
    createRenderer();
    createScene();
    setupScene();
    setupLight();
    resize($container.scrollWidth, $container.scrollHeight);
    handleResize();
    update()
}
function createRenderer() {
    canvasRenderer = new CAV.CanvasRenderer();
    createRendererElement(configRenderer.renderer)
}
function createRendererElement(renderer) {
    if ($canvas) {
        $anitOut.removeChild($canvas.element)
    }
    switch (renderer) {
    case canvas:
        $canvas = canvasRenderer;
        break
    }
    $canvas.setSize($container.scrollWidth, $container.offsetHeight);
    $anitOut.appendChild($canvas.element)
}
function createScene() {
    scene = new CAV.Scene()
}
function setupScene() {
    scene.remove(mesh);
    $canvas.clear();
    plane = new CAV.Plane(t.width * $canvas.width, t.height * $canvas.height, t.segments, t.slices);
    material = new CAV.Material(t.ambient,t.diffuse);
    mesh = new CAV.Mesh(plane, material);
    scene.add(mesh);
    var index, vertice;
    for (index = plane.vertices.length - 1; index >= 0; index--) {
        vertice = plane.vertices[index];
        vertice.anchor = CAV.Vector3.clone(vertice.position);
        vertice.step = CAV.Vector3.create(
            Math.randomInRange(0.2, 1), Math.randomInRange(0.2, 1), Math.randomInRange(0.2, 1));
        vertice.time = Math.randomInRange(0, Math.PIM2)
    }
}
function setupLight() {
    var index, light;
    for (index = scene.lights.length - 1; index >= 0; index--) {
        light = scene.lights[index];
        scene.remove(light)
    }
    $canvas.clear();
    for (index = 0; index < G.count; index++) {
        light = new CAV.Light(G.ambient, G.diffuse);
        light.ambientHex = light.ambient.format();
        light.diffuseHex = light.diffuse.format();
        scene.add(light);
        light.mass = Math.randomInRange(0.5, 1);
        light.velocity = CAV.Vector3.create();
        light.acceleration = CAV.Vector3.create();
        light.force = CAV.Vector3.create()
    }
}
function resize(width, height) {
    $canvas.setSize(width, height);
    CAV.Vector3.set(L, $canvas.halfWidth, $canvas.halfHeight);
    setupScene()
}
function update() {
    timeDelta = Date.now() - timeStart;
    updateScene();
    renderScene();
    requestAnimationFrame(update)
}
function updateScene() {
    var Q, P, O, R, light, V, U, S = t.depth / 2;
    CAV.Vector3.copy(G.bounds, L);
    CAV.Vector3.multiplyScalar(G.bounds, G.xyScalar);
    CAV.Vector3.setZ(k, G.zOffset);
    for (R = scene.lights.length - 1; R >= 0; R--) {
        light = scene.lights[R];
        CAV.Vector3.setZ(light.position, G.zOffset);
        var N = Math.clamp(CAV.Vector3.distanceSquared(light.position, k), G.minDistance, G.maxDistance);
        var W = G.gravity * light.mass / N;
        CAV.Vector3.subtractVectors(light.force, k, light.position);
        CAV.Vector3.normalise(light.force);
        CAV.Vector3.multiplyScalar(light.force, W);
        CAV.Vector3.set(light.acceleration);
        CAV.Vector3.add(light.acceleration, light.force);
        CAV.Vector3.add(light.velocity, light.acceleration);
        CAV.Vector3.multiplyScalar(light.velocity, G.dampening);
        CAV.Vector3.limit(light.velocity, G.minLimit, G.maxLimit);
        CAV.Vector3.add(light.position, light.velocity)
    }
    for (V = plane.vertices.length - 1; V >= 0; V--) {
        U = plane.vertices[V];
        Q = Math.sin(U.time + U.step[0] * timeDelta * t.speed);
        P = Math.cos(U.time + U.step[1] * timeDelta * t.speed);
        O = Math.sin(U.time + U.step[2] * timeDelta * t.speed);
        CAV.Vector3.set(U.position, t.xRange * plane.segmentWidth * Q, t.yRange * plane.sliceHeight * P, t.zRange * S * O - S);
        CAV.Vector3.add(U.position, U.anchor)
    }
    plane.dirty = true
}
function renderScene() {
    $canvas.render(scene)
}
function setColors(color) {
    var index, light, colors = color;
    var set1stColor = function(color) {
        for (index = 0,
        l = scene.lights.length; index < l; index++) {
            light = scene.lights[index];
            light.ambient.set(color);
            light.ambientHex = light.ambient.format()
        }
    };
    var set2ndColor = function(color) {
        for (index = 0,
        l = scene.lights.length; index < l; index++) {
            light = scene.lights[index];
            light.diffuse.set(color);
            light.diffuseHex = light.diffuse.format()
        }
    };
    return {
        set: function() {
            set1stColor(colors[0]);
            set2ndColor(colors[1])
        }
    }
}
function handleResize() {
    window.addEventListener("resize", onResize);
    window.addEventListener("load", onResize);
    window.addEventListener("change", onResize);

    // window.addEventListener("DOMNodeInserted", onResize);
    // window.addEventListener("DOMNodeRemoved", onResize);
}
function A(N) {
    CAV.Vector3.set(k, N.x, $canvas.height - N.y);
    CAV.Vector3.subtract(k, L)
}
function onResize(N) {
    console.log('resize');
    resize($container.scrollWidth, $container.scrollHeight);
    renderScene()
}

create();
//start
var colorTable = [["#002c4a", "#005584"], ["#35ac03", "#3f4303"], ["#ac0908", "#cd5726"], ["18bbff", "#00486b"]];
function changeColor() {
    setColors(colorTable[2]).set();
}
