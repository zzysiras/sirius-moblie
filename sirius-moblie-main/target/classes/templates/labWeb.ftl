<!DOCTYPE html>
<html lang="en">
<head>
    <base href="<%=basePath%>">
    <meta charset="UTF-8">
    <title> 四川大学 | 数字媒体实验室 </title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">   <!--移动端适应-->
    <!-- BootStrap和JQ -->
    <script type="text/javascript" src="../labweb/jquery-3.3.1.min.js"></script>
    <!--script src="https://code.jquery.com/jquery.js"></script-->
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <style>
        html, body {           <#--全局设置-->
            margin: 0;
            padding: 0;
            -webkit-box-sizing: border-box;
            -moz-box-sizing: border-box;
            box-sizing: border-box;
        }

        li {
            list-style-type: none;
        }

        #Container {
            border:1px solid rgba(0,0,0,0);    <#--主页面容器-->
            height: calc(100%);
            width: calc(70%);
            margin: 0 auto;
        }


        <#--面包屑列表登录退出联系方式-->
        <#--.top-nav > li + li :{
            display:flex;
            justify-content:flex-end;
            color：#CCCCCC;
            content: "|";
            padding:0 5px;
            float: right;
        }
        <#--flexlayout-->


        #header {
            background-image: url("../img/nav-img2.png");            <#--底片--><#--实验室logo-->
            width: 100%;
            height: 150px;
            z-index: 0;
            font-size: 40px;
            color:#000000;
            text-align: center;
        }

        #content {
            z-index: 1;
            width: 100%;
            height: 60px;
            background-color: hsla(0,0%,100%,.5);        <#--色调饱和度亮度透明度-->
            display: flex;
            display: -webkit-flex;
            justify-content: center;            <#--通过displayflex设置适应性文字居中-->
            margin:0 auto;
        }

        #content:after {
            z-index: -1;
            background-image: url("../img/nav-img2.png");
            background-position: center top;
            background-size: cover;
            background-attachment: fixed;
            background-color: rgba(255,255,255,0.4);         <#--伪元素毛玻璃效果还有点问题-->
            filter: blur(5px);
            -webkit-filter: blur(5px);
            -moz-filter: blur(5px);
            -ms-filter: blur(5px);
            -o-filter: blur(5px);
        }

        <#--#content::after {                                    <#--废弃伪元素
            background-image: url("../img/nav-img2.png");
            background-position: center top;
            background-size: cover;
            background-attachment: fixed;

            filter: blur(20px);
        }-->


        .logomask{                <#--群众演员-->
            width: 100%;
            height: 50px;
        }


        .mainNavigate {            <#--主导航栏-->

            background-color:#0064b6;
            border-radius: 0 0 4px 4px;
            background-size: cover;
            z-index: 2;

        }

        .showUI {

            width:100%;
            height: 600px;
            border: 1px solid red;

        }

        .banner{

            width: 916px;
            height: 550px;
            margin: 25px auto 25px 25px;
            border: 1px solid purple;
            position: relative;
            overflow: hidden;
            float: left;

        }


        .ban-image{                   <#--底片relative 相对组件absolute脱离同级DOM流！-->
            position:absolute;
            width: 916px;
            height: 550px;
            padding-left: 0;
            border: 1px solid blue;
        }

        .banner ul{
            position: relative;
            width: 3664px;           <#--滚动-->
            height: 550px;
            right: 0;
            margin-left: -40px;
        }

        .banner ul li{

            height: 550px;
            float: left;

        }


        .btn-left,.btn-right{
            position:absolute;
            top:50%;
            width: 50px;
            margin-top: -30px;
            background-color: rgba(0,0,0,0.5);
            opacity: 0;          <#--鼠标不进入区域默认隐藏-->
            font-size: 20px;
            text-align: center;
            line-height: 60px;
            color: #fff;
            cursor: pointer;        <#--光标样式-->
        }

        .btn-left{
            left: 0;
        }

        .btn-right{
            right: 0;
        }

        .tab {
            position:absolute;
            bottom: 10px;
            left: 50%;
            transform: translateX(-50%);
        }

        .tab ul li{

            float: left;
            width: 20px;
            height: 20px;
            margin-right: 8px;
            color: black;
            border-radius: 50%;
            text-align: center;
            cursor: pointer;
        }

        .tab ul li.on{
            background-color: #8c8c8c;
            opacity: 0.8;
        }

        .rightCarousel {
            width: 300px;
            height: 550px;
            margin-top: 25px;
            margin-bottom: 25px;
            margin-right: 25px;
            margin-left: 20px;
            float: right;
        }

        .carousel{
            width: 300px;
            height: 550px;
            margin-top: 0;
            margin-bottom: 0;
            margin-right: 40px;
            margin-left: 20px;
            float: right;
        }


        .clickBtn {              <#--按钮样式-->

        }
        .bottomContainer{
            width:100%;
            height: 600px;
            border: 1px solid #0064b6;

        }

        .sideTxt {           <#--左边文本区域-->

            position: absolute;
            height: 550px;
            width: 920px;
            margin: 20px 20px auto 20px;
        }

        .sidePattern {         <#--右侧边展示图-->
            position: relative;
            height: 80px;
            width: 300px;
            margin: 20px 24px auto 20px;
            background-color: #e8e8e8;
            font-size: 15px;
            color: #0064b6;
            float: right;
            padding: 15px;

        }

        .sideTxt ul{
            width: 920px;
            height: 100px;

        }

        .sideTxt ul li{
            width: 920px;
            height: 100px;
            padding: 0;
            margin: 30px 0 30px -38px;
            float: left;
        }

        .itemPic{
            width: 120px;
            height: 100px;
            background-color: white;
            font-size: 20px;
            font-weight: bold;
            float: left;
            padding: 10px;
            text-align: center;                  <#--padding和text-align搞定-->
            color: #0064b6;
            border: 4px solid #0064b6;
            margin-right: 10px;

        }

        .pagination {
            position: relative;
            left: 20%;
            top:90%;
            float: bottom;
            width: 60%;
            height: 30px;
            background-color: #8c8c8c;
            text-align: center;

        }



        #searchBox {         <#--搜索框-->
            position: absolute;
            margin-left: 1000px;
            margin-top: 6px;

        }

        .texPattern {       <#--字体-->
            color: white;
        }

        .texPattern:hover{
            color: #0064b6;
        }

        #labFooter{
            position: absolute;
            width: 100%;
            height: 100px;
            background-color: #0064b6;
        }

    </style>
</head>
<body>
<div id="Container">
    <ul class="nav nav-pills" style="display: flex; justify-content: flex-end">
        <li><a href="../labLogin"><span class="glyphicon glyphicon-user"> 登录</span></a></li>
        <li><a href="../logout"><span class="glyphicon glyphicon-log-in"> 退出</span></a></li>
        <li><a href="#"><span class="glyphicon glyphicon-envelope"> 联系我们</span></a></li>
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
            <li id="searchBox" style="justify-content: flex-end;color: black;margi">        <#--输入框颜色可能被父模块更改-->
                <input  v-model.trim="searchSth" placeholder="输入你想知道的内容">
                <button v-on:click="startSearch"><span class="glyphicon glyphicon-search"></span></button>
            </li>
        </ul>
    </div>

    <hr style="margin-top: 10px;margin-bottom: 1px;">

    <div class="showUI">
        <div class="banner">
            <ul>
                <li><img class="on" src="../img/lab1.png" width="916px" height="550px" alt="" /></li>
                <li><img src="../img/lab2.png" width="916px" height="550px" alt="" /></li>
                <li><img src="../img/lab3.png" width="916px" height="550px" alt="" /><li>
            </ul>
            <div class="btn-left">&lt;</div>
            <div class="btn-right">&gt;</div>
            <!--div class="tab">
                <ul>
                    <li class="on">1</li>
                    <li>2</li>
                    <li>3</li>
                    <li>4</li>
                </ul>
            </div-->
        </div>
        <div class="rightCarousel">
            <ul class="carousel">
                <img src="../img/top1.png" width="300px" height="22px">
                <a href="img/bg001.jpg">
                    <img src="../img/lab1.png" width="300px" height="170px" />
                </a>
                <a href="img/bg002.jpg">
                    <img src="../img/lab2.png" width="300px" height="170px" />
                </a>
                <a href="img/bg003.jpg">
                    <img src="../img/lab3.png" width="300px" height="170px" />
                </a>
                <img src="../img/bottom1.png" width="300px" height="22px">
            </ul>
        </div>
    </div>

    <hr style="margin-top: 10px;margin-bottom: 10px;"/>

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

<hr style="margin-top: 20px;margin-bottom: 20px"/>

<footer id="labFooter"></footer>

</body>

<style type="text/css">
    .changeColor{
        background-color: #0064b6;
        color: white;
    }
</style>
<script>
    $(function () {

        $(".banner").mouseenter(function () {
            $(".btn-left,.btn-right").css({opacity:0.8});
        });

        $(".banner").mouseleave(function () {
            $(".btn-left,.btn-right").css({opacity:0});
        });

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


        var imgNum = 0;
        var pageNum = 0;
        var step = $(".banner ul li").width();
        var count = $(".banner ul li").length;

        function leftBtn() {

            imgNum--;
            if(imgNum==-1){
                $(".banner ul").animate({left:-((count-2)*step)},1000,);       <#--有待更好的方案-->
                imgNum = count - 2 ;
                return;
            }
            $(".banner ul").animate({left:-imgNum*step},1000);           <#--正值正向负值反向-->

        }

        function rightBtn() {

            imgNum++;
            if(imgNum==count-1){
                $(".banner ul").animate({left:0},1000);
                imgNum = 0;
                return;
            }
            $(".banner ul").animate({left:-imgNum*step},1000);

        }

        $(".btn-left").click(function () {
            leftBtn();
            console.log(imgNum);
        });

        $(".btn-right").click(function () {
            rightBtn();
            console.log(imgNum);
        });



    });
</script>
<#--script type="text/javascript">

    var rightBtn = document.querySelector('.btn-right');
    var Img = document.querySelectorAll('.ban-image li');


    function changeNumColor(num) {
        var pages = document.querySelectorAll('.page li');
        for (let i=0 ; i<pages.length; i++){
            pages[i].style.opacity = 0 ;
        }

        pages[num].style.opacity = 0.8;
        pages[num].style.backgroundColor = '#8c8c8c';

    }

    function getStyle(ele) {
        if(ele.currentStyle){
            return ele.currentStyle;
        }else{
            return getComputedStyle(ele,null);
        }
    }

    var pageNum = 0;
    changeNumColor(pageNum);

    rightBtn.onclick = function () {

        animate(Img[pageNum],{opacity:0});
        pageNum ++;
        if(pageNum == 4){
            pageNum = 0
        }
        changeNumColor(pageNum);
        animate(Img[pageNum],{opacity:1});
    };

    <#-------------------------------------华丽丽的分界线----------------------------------------->
<#--
    var leftBtn = document.querySelector('.btn-left');
    leftBtn.onclick = function () {

        animate(Img[pageNum],{opacity:0});
        pageNum --;
        if(pageNum == -1){
            pageNum = 3
        }
        changeNumColor(pageNum);
        animate(Img[pageNum],{opacity:1});
    }



</script-->
<script src="../libs/vue.min.js"></script>
<script>
    <#--var search = new Vue({
        el:'#searchBox',
        data:{
            searchSth: ,
        }
        method: {
            startSearch:function (searchSth) {
                
            }
        }
    })
    -->
</script>
</html>