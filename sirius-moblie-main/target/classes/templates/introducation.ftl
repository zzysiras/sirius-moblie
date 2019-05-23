<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <script type="text/javascript" src="../labweb/jquery-3.3.1.min.js"></script>
    <!--script src="https://code.jquery.com/jquery.js"></script-->
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/labcss.css">
    <title>实验室简介</title>
    <style type="text/css">
        .changeColor{
            background-color: #0064b6;
            color: white;
        }
    </style>
</head>
<body>


<div id="Container">
    <div class="header-1">
        <ul class="nav nav-pills" style="display: flex; justify-content: flex-end">
            <li><a href="../labLogin"><span class="glyphicon glyphicon-user"> 登录</span></a></li>
            <li><a href="#"><span class="glyphicon glyphicon-log-in"> 退出</span></a></li>
            <li><a href="../logout"><span class="glyphicon glyphicon-envelope"> 联系我们</span></a></li>
        </ul>

    <hr style="margin-top: 1px;margin-bottom: 1px;">




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
            <li><a href="../login" class="texPattern">最新公告</a></li>
            <li id="searchBox" style="justify-content: flex-end;color: black">        <#--输入框颜色可能被父模块更改-->
                <input  v-model.trim="searchSth" placeholder="输入你想知道的内容">
                <button v-on:click="startSearch"><span class="glyphicon glyphicon-search"></span></button>
            </li>
        </ul>
    </div>

    <hr style="margin-top: 10px;margin-bottom: 1px;"></div>
<div class="bottomContainer">
    <div class="sideTxt">
        <p style="font-size: 18px;font-weight: bold;color: #0064b6">学术交流</p>
        <hr style="margin-top: 3px;margin-bottom: 3px;background-color: #8c8c8c;height: 1px" />
        <ul>
            <li>
                <div class="itemPic" id="item1">
                    <p style="font-size: 30px">27日</p>
                    <p>Oct</p>
                </div>
                <p style="color: #0064b6;font-size: 20px">Deep Learning: Theory and Applications (深度学习：理论与应用)</p>
                <p style="color: #0C0C0C;font-size: 10px">此次研讨会是北京大学与新加坡国立大学联合举办的第一届联合研讨会，受到北京大学与新加坡国立大学校级合作计划的资助。研讨会旨在为来自数学、统计、人工智能、生物医学工程、神经科学等相关领域的学者提供交流平台，促进两校在深度学习理论及应用方面</p>
                <p style="color: #0064b6;font-size: 10px">查看详情>></p>
            </li>
            <li>
                <div class="itemPic" id="item2">
                    <p style="font-size: 30px">27日</p>
                    <p>Oct</p>
                </div>
                <p style="color: #0064b6;font-size: 20px">Deep Learning: Theory and Applications (深度学习：理论与应用)</p>
                <p style="color: #0C0C0C;font-size: 10px">此次研讨会是北京大学与新加坡国立大学联合举办的第一届联合研讨会，受到北京大学与新加坡国立大学校级合作计划的资助。研讨会旨在为来自数学、统计、人工智能、生物医学工程、神经科学等相关领域的学者提供交流平台，促进两校在深度学习理论及应用方面</p>
                <p style="color: #0064b6;font-size: 10px">查看详情>></p>
            </li>
            <li>
                <div class="itemPic" id="item3">
                    <p style="font-size: 30px">27日</p>
                    <p>Oct</p>
                </div>
                <p style="color: #0064b6;font-size: 20px">Deep Learning: Theory and Applications (深度学习：理论与应用)</p>
                <p style="color: #0C0C0C;font-size: 10px">此次研讨会是北京大学与新加坡国立大学联合举办的第一届联合研讨会，受到北京大学与新加坡国立大学校级合作计划的资助。研讨会旨在为来自数学、统计、人工智能、生物医学工程、神经科学等相关领域的学者提供交流平台，促进两校在深度学习理论及应用方面</p>
                <p style="color: #0064b6;font-size: 10px">查看详情>></p>
            </li>
        </ul>
    </div>
    <div class="sidePattern">
        <p><strong>学术交流</strong></p>
        <p><strong>ACADEMIC EXCHANGE</strong></p>
    </div>
    <div class="pagination">分页功能还没做！</div>
  </div>
</div>

<hr style="margin-bottom: 30px"/>

<footer id="labFooter" style="position: relative"></footer>
</body>
<script>


    $("#item1").mouseenter(function () {                  <#--代码冗余大问题 怎么解决同类事件不同绑定-->
        $("#item1").addClass("changeColor");
    });

    $("#item1").mouseleave(function () {
        $("#item1").removeClass("changeColor");
    });

    $("#item2").mouseenter(function () {
        $("#item2").addClass("changeColor");
    });

    $("#item2").mouseleave(function () {
        $("#item2").removeClass("changeColor");
    });

    $("#item3").mouseenter(function () {
        $("#item3").addClass("changeColor");
    });

    $("#item3").mouseleave(function () {
        $("#item3").removeClass("changeColor");
    });
</script>
</html>