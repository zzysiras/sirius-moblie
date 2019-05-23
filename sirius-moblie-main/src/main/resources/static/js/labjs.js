$.ajaxSetup({
    cache: false,
    headers: {                    //卧槽这里是headers!!!!!!!!!!!!!!!!!!!!!!!!
        "token" : localStorage.getItem("token")
    },
    error : function(xhr, textStatus, errorThrown) {
        var msg = xhr.responseText;
        var response = JSON.parse(msg);
        var code = response.code;
        var message = response.message;
        if (code == 400) {
            layer.msg(message);
        } else if (code == 401) {
            console.log("something wrong");
            localStorage.removeItem("token");
            location.href = '/rolelogin.html';
        } else if (code == 403) {
            console.log("未授权:" + message);
            layer.msg('未授权');
        } else if (code == 500) {
            layer.msg('系统错误：' + message);
        }
    }
});


function buttonDel(data, permission, pers){

    var btn = $("<button class='layui-btn layui-btn-xs' title='删除' onclick='del(\"" + data +"\")'><i class='layui-icon'>&#xe640;</i></button>");
    return btn.prop("outerHTML");
}

function buttonEdit(href, permission, pers){

    var btn = $("<button class='layui-btn layui-btn-xs' title='编辑' onclick='window.location=\"" + href +"\"'><i class='layui-icon'>&#xe642;</i></button>");
    return btn.prop("outerHTML");
}

function buttonDownload(data, perminssion, pers) {

    var btn = $("<button class='layui-btn layui-btn-xs' title='下载' onclick='download(\"" + data + "\")'><i class='layui-icon'>&#xe601;</i></button>");
    return btn.prop("outerHTML");

}


function deleteCurrentTab(){
    var lay_id = $(parent.document).find("ul.layui-tab-title").children("li.layui-this").attr("lay-id");
    parent.active.tabDelete(lay_id);
}

function checkPermission() {
    var pers = [];
    $.ajax({
        type : 'get',
        url : '/permissions/owns',
        contentType : "application/json; charset=utf-8",
        async : false,
        success : function(data) {
            pers = data;
            $("[permission]").each(function() {
                var per = $(this).attr("permission");
                if ($.inArray(per, data) < 0) {
                    $(this).hide();
                }
            });
        }
    });

    return pers;
}

/*initMenu();
function initMenu(){
    $.ajax({
        url:"/permissions/current",
        type:"get",
        async:false,
        success:function(data){
            console.log(token);
        }
    })
}*/

//form序列化为json
$.fn.serializeObject = function()
{
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};

//获取url后的参数值
function getUrlParam(key) {
    var href = window.location.href;
    var url = href.split("?");
    if(url.length <= 1){
        return "";
    }
    var params = url[1].split("&");

    for(var i=0; i<params.length; i++){
        var param = params[i].split("=");
        if(key == param[0]){
            return param[1];
        }
    }
}