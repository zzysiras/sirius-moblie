<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <script type="text/javascript" src="../labweb/jquery-3.3.1.min.js"></script>
    <!--script src="https://code.jquery.com/jquery.js"></script-->
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/labcss.css">
    <script src="../labweb/jquery-validation-1.14.0/lib/jquery.js"></script>
    <script src="../labweb/jquery-validation-1.14.0/dist/jquery.validate.min.js"></script>
    <script src="../labweb/jquery-validation-1.14.0/dist/localization/messages_zh.js"></script>
    <script src="../labweb/vue.js" type="text/javascript"></script>
    <script src="../libs/vue.min.js" type="text/javascript"></script>
    <script src="../libs/vuelidate.min.js" type="text/javascript"></script>
    <script src="../libs/validators.min.js" type="text/javascript"></script>
    <script src="../libs/jquery.blockUI.js" type="text/javascript"></script>
    <script src="../libs/bootstrap.min.js" type="text/javascript"></script>
    <script src="../js/labWeb.js" type="text/javascript"></script>
    <title>登录</title>
</head>

<style>
    html, body {
        margin: 0;
        padding: 0;
        -webkit-box-sizing: border-box;
        -moz-box-sizing: border-box;
        box-sizing: border-box;
    }
    #container {
        border: 1px solid rgba(0, 0, 0, 0);
        height: calc(100% - 2px);
    }
    .login-box {
        z-index: 1000;
        border: 1px solid #ddd;
        -webkit-border-radius: 3px;
        -moz-border-radius: 3px;
        border-radius: 3px;
        background-color: #fff;
    }
    .login-box .login-logo {
        position: relative;
        margin-top: 20px;
        margin-bottom: 15px;
        color: #3c8dbc;
    }
    .login-box .login-box-msg {
        padding: 0;
    }
    .login-page{
        background-image: url("../img/bg003.jpg");    <#--一定要注意这里的路径-->
        background-size: cover;
        width: 100%;
        height: 100%;
        background-repeat: no-repeat;
    }
</style>

<body>
<div id="Container">




    <div id="header">
        <div class="logomask"></div>
        <div id="content">四川大学 | 数字媒体实验室</div>
        <div class="logomask"></div>
    </div>


    <hr style="margin-top: 1px;margin-bottom: 1px;;">

    <div class="mainNavigate">
        <ul class="nav nav-pills" style="display: flex;justify-content: flex-start ;font-size: 20px;">

            <li><a href="../labWeb" class="texPattern">首页</a></li>
            <li><a href="../introducation" class="texPattern">实验室简介</a></li>
            <li><a href="../main" class="texPattern">师资队伍</a></li>
            <li><a href="../main" class="texPattern">科研论文</a></li>
            <li><a href="../main" class="texPattern">科研过程</a></li>
            <li><a href="../main" class="texPattern">科研会议</a></li>
            <li><a href="../main" class="texPattern">最新公告</a></li>
            <li id="searchBox" style="justify-content: flex-end;color: black">        <#--输入框颜色可能被父模块更改-->
                <input  v-model.trim="searchSth" placeholder="输入你想知道的内容">
                <button v-on:click="startSearch"><span class="glyphicon glyphicon-search"></span></button>
            </li>
        </ul>
    </div>

    <hr style="margin-top: 10px;margin-bottom: 1px;">
</div>

<div class="login-box"  id="app" style="width: 450px;height: 340px;margin: 50px auto;">
    <div class="login-logo" style="text-align: center;margin-top: 30px">
        <sup><small><kbd style="background-color: #3c8dbc;font-size: 20px;">数字媒体实验室</kbd></small></sup>
        <div style="font-size: 20px;text-align: center;font-weight: bold">用户登录</div>
    </div>
    <hr style="margin-top: 0; margin-bottom: 5px;">
    <!-- /.login-logo -->
    <form class="login-box-body" id="loginInfo" name="login" action="/login/form" method="post">


        <div class="form-group has-feedback" style="width: 70%;margin: 20px auto 0 auto">
            <input type="text" id="username" name="username" required minlength="4" maxlength="12" class="form-control" v-model='username' placeholder="账号"/>
            <span class="glyphicon glyphicon-user form-control-feedback"></span>
            <label for="username" style="padding:0;text-align: left; color: red">{{username}}</label>
        </div>
        <div class="form-group has-feedback" style="width: 70%;margin: 0 auto 20px auto">
            <input type="password" id="password" name="password" required minlength="5" class="form-control" v-model='password' placeholder="密码"/>
            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
            <label for="password" style="padding:0;text-align: left;color: red">{{ password }}</label>
        </div>

        <hr style="margin-bottom: 5px">

        <p style="padding:0 auto;text-align: center;">请输入正确的实验室账号密码</p>

        <div style="display: flex;justify-content: space-evenly">
            <input type="submit" class="btn btn-primary btn-block btn-flat" value="登录"
                   style="width: 40%;margin: auto">
            <input type="submit" class="btn btn-primary btn-block btn-flat" value="忘记密码"
                   style="width: 40%;margin: auto">
        </div>


    </form>
    <!-- /.login-box-body -->

</div>


<footer id="labFooter" style="position: absolute"></footer>                <!--这里不能用JQ脚本引入元素容易引起异步加载异常!!!!-->

</body>

<script type="text/javascript">
    $().ready(function () {

        var validator = $("#loginInfo").validate({
            errorPlacement:function (error, element) {                   <!--一定要换行这样写？？？？-->
                $( element )
                        .closest( "div" )
                        .find( "label[for='" + element.attr("id") + "']" )
                        .append(error);
            },
            errorElement: "span",
            messages: {
                username: {
                    required:"(用户名不能为空！)",
                    minlength: " (用户名不能少于五个字符！)",
                    maxlength: "(多了也不行啊！)"
                },
                password: {
                    required:"(密码不能为空！)",
                    minlength: "(密码也不能少于五个字符)"
                }
            }
        });
    });


    var login = new Vue({

        el:'#app',

        data:{
            username:'',
            password:'',
        }

    });
</script>

</html>