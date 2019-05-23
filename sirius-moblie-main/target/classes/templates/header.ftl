<div id="Container">
    <ul class="nav nav-pills" style="display: flex; justify-content: flex-end">
        <li><a><span class="glyphicon glyphicon-user"> 登录</span></a></li>
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
            <li id="searchBox" style="justify-content: flex-end;color: black">        <#--输入框颜色可能被父模块更改-->
                <input  v-model.trim="searchSth" placeholder="输入你想知道的内容">
                <button v-on:click="startSearch"><span class="glyphicon glyphicon-search"></span></button>
            </li>
        </ul>
    </div>

    <hr style="margin-top: 10px;margin-bottom: 1px;">
</div>



