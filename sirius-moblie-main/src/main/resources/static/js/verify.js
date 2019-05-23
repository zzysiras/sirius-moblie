function verify(){
    var str = "";
    layui.use(['layer','element'] ,function()
    {
        var $ = layui.jquery, layer = layui.layer;
        var element = layui.element;
        var active={
            tabAdd:function(url,id,name){
                element.tabAdd('content',{
                    title:name,
                    content:'<iframe data-frameid="'+id+'" scrolling="auto" frameborder="0" src="'+url+'" style="width:100%;"></iframe>',
                    id:id
                });
                rightMenu();
                iframeWH();
            },
            tabChange:function(id){
                element.tabChange('content',id);
            },
            tabDelete:function(id){
                element.tabDelete('content',id);
            },
            tabDeleteAll:function(ids){
                $.each(ids,function(index,item){
                    element.tabDelete('content',item);
                });
            }
        };

        $.ajax({
            type: "GET",
            headers: {
                "token":localStorage.getItem("token")
            },
            url: "user/email",
            success: function(res) {
                console.log(res);
                this.str = res;
                parent.layer.prompt({
                    formType: 0,
                    value: '',
                    anim: 0,
                    closeBtn: false,
                    offset: ['330px', '850px'],
                    btnAlign: 'c',
                    shade: 0.6 ,
                    title: ['<div>访问此内容需要身份验证！<br>请在下方输入您邮箱收到收到的验证码！<br>如未收到 请检查注册信息后关闭重试！</div>', 'margin:auto; padding: 20px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;'],
                    area: ['400px','450px'] //自定义文本域宽高

                }, function(value, index, elem){
                    alert(value); //得到value
                    $.ajax({
                        type: "POST",
                        headers: {
                            "token":localStorage.getItem("token")
                        },
                        url: "/user/email/" + value,
                        success (data){
                            if (data == true){
                                console.log(data);
                                layer.close(index);
                                //window.location = "pages/dashboard.html";
                                active.tabAdd("pages/System/systemlogs.html", 14 ,"系统日志");
                                active.tabChange(14);
                            } else{
                                console.log(data);
                                alert("输入错误！");
                            }
                        },
                        error (data){
                            console.log("验证写错了");
                        }
                    });

                });
            },
            error:function () {
                alert("请求邮箱验证失败！");
            }
        });


        function rightMenu(){
            //右击弹出
            $(".layui-tab-title li").on('contextmenu',function(e){
                var menu=$(".rightmenu");
                menu.find("li").attr('data-id',$(this).attr("lay-id"));
                l = e.clientX;
                t = e.clientY;
                menu.css({ left:l, top:t}).show();
                return false;
            });
            //左键点击隐藏
            $("body,.layui-tab-title li").click(function(){
                $(".rightmenu").hide();
            });
        }

        function iframeWH(){
            var H = $(window).height()-80;
            $("iframe").css("height",H+"px");
        }
        $(window).resize(function(){
            iframeWH();
        });

        /*layer.open({

            type: 1,
            title: false //不显示标题栏
        ,closeBtn: false
        ,area: '300px;'
        ,shade: 0.8
        ,id: 'LAY_layuipro' //设定一个id，防止重复弹出
        ,btn: ['确认输入', '放弃挣扎']
        ,btnAlign: 'c'
        ,moveType: 1 //拖拽模式，0或者1
        ,content: '<div style="padding: 50px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;">你知道吗？亲！<br>layer ≠ layui<br><br>layer只是作为Layui的一个弹层模块，由于其用户基数较大，所以常常会有人以为layui是layerui<br><br>layer虽然已被 Layui 收编为内置的弹层模块，但仍然会作为一个独立组件全力维护、升级。<br><br><form><input type="text" name="验证码" id="name"></form></div>'
        ,
            success: function(){
            var o1=document.getElementById('name').value;
            console.log(o1);
        }
        });*/

})
}