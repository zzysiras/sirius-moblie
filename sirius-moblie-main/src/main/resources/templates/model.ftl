<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <script type="text/javascript" src="../labweb/jquery-3.3.1.min.js"></script>
    <!--script src="https://code.jquery.com/jquery.js"></script-->
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/labcss.css">
    <title>页面标题</title>
</head>
<body>
<div class="header-1"></div>
<div id="Container">

</div>
<div class="footer-1"></div>
</body>
<script>
    $(function () {
        $(".header-1").load("header");
        $(".footer-1").load("footer");
    });
</script>
</html>