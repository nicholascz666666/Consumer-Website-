<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>Message</title>
    <meta name="keywords"  content="DeathGhost" />
    <meta name="description" content="DeathGhost" />
    <meta name="author" content="DeathGhost,deathghost@deathghost.cn">
    <link rel="icon" href="images/icon/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="css/style.css" /><script src="js/html5.js"></script>
    <script src="js/jquery.js"></script>
    <script>
        $(document).ready(function(){
            $("nav .indexAsideNav").hide();
            $("nav .category").mouseover(function(){
                $(".asideNav").slideDown();
            });
            $("nav .asideNav").mouseleave(function(){
                $(".asideNav").slideUp();
            });
        });
    </script>
</head>
<body>
<!--header-->
<header>
    <!--topNavBg-->
    <div class="topNavBg">
        <div class="wrap">
            <!--topLeftNav-->
            <c:if test="${empty customer}">
                <ul class="topLtNav">
                    <li><a href="showLogin" class="obviousText">Please Login</a></li>
                    <li><a href="register.html">Register</a></li>
                </ul>
            </c:if>
            <c:if test="${not empty customer}">
                <ul class="topRtNav">
                    <li><a href="user.html">Center</a></li>
                </ul>
            </c:if>
        </div>
    </div>
    <!--logoArea-->
    <div class="wrap logoSearch">
        <!--logo-->
        <div class="logo">
            <h1><img src="images/logo.png"/></h1>
        </div>
        <!--search-->

    </div>
    <!--nav-->
    <nav>
        <ul class="wrap navList">

        </ul>
    </nav>

</header>


<section class="wrap order_back_infor">
    <div style="width:210px;margin:0 auto;overflow:hidden;text-align:left;">
        <h2>Order submit success！</h2>
    </div>
    <div class="order_btm_btn">
        <a href="showIndex" class="link_btn_01 buy_btn"/>Go Home</a>
    </div>
</section>
<!--footer-->
<footer>
    <!--help-->

</body>
</html>

