<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>MySpring | Login</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <script src="../libs/jquery.min.js"></script>
    <script type="text/javascript" src="../js/jquery-3.3.1.min.js"></script>
<#--<link rel="stylesheet" href="${cp}/statics/plugins/iCheck/square/blue.css">-->
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/labcss.css">
    <script src="../labweb/jquery-validation-1.14.0/lib/jquery.js"></script>
    <script src="../labweb/jquery-validation-1.14.0/dist/jquery.validate.min.js"></script>
    <script src="../labweb/jquery-validation-1.14.0/dist/localization/messages_zh.js"></script>
<#--<link rel="stylesheet" href="${cp}/statics/css/main.css">-->
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style>
        [v-cloak] {   <#-- CSS 规则如 [v-cloak] { display: none } 一起用时，这个指令可以隐藏未编译的带有{{ }}或者{{{ }}}标签直到实例准备完毕-->
            display: none;
        }
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
            background-repeat: repeat-y;
        }
    </style>
</head>

<body class="login-page" style="background-color: #000;">   <#--class="hold-transition login-page"-->>
<#--div id="anitOut">这是框架高大上的背景图</div-->

<div class="login-box"  id="app" style="width: 450px;height: 340px;margin: 10px auto;">
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
<!-- /.login-box -->


</body>



<script src="../labweb/vue.js" type="text/javascript"></script>
<script src="../libs/vue.min.js" type="text/javascript"></script>
<script src="../libs/vuelidate.min.js" type="text/javascript"></script>
<script src="../libs/validators.min.js" type="text/javascript"></script>
<script src="../libs/jquery.blockUI.js" type="text/javascript"></script>
<script src="../libs/bootstrap.min.js" type="text/javascript"></script>
<script type="text/javascript">
    <#--$.validator.setDefaults({                   <万恶之源！！！！！！！！！！！！！！！！！！！！！！>
        submitHandler: function () {
            alert("提交成功！！！！！");
            location.href ="../labWeb";
        }
    });-->

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
                    minlength: " (用户名不能少于四个字符！)",
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