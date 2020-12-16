<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/12/14
  Time: 0:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>Login</title>
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
            <ul class="topLtNav">
                <li><a href="showLogin" class="obviousText">Please Login</a></li>
                <li><a href="showRegister">Register</a></li>
            </ul>

        </div>
    </div>
    <!--logoArea-->

    <!--nav-->
    <nav>

    </nav>

</header>


<section class="wrap user_form">
    <div class="lt_img">
        <img src="images/form_bg.jpg"/>
    </div>
    <div class="rt_form">
        <h2>Seller Login</h2>
        <ul>
            <form action="sellerLogin" method="post">
            <li class="user_icon">
                <input type="text" name="username" class="textbox" placeholder="username"/>
            </li>
            <li class="user_pwd">
                <input type="password" name="password"class="textbox" placeholder="password"/>
            </li>
            <li class="link_li">
                <a href="showSellerRegister" title="register new" class="fl">Register New</a>
            </li>
            <li class="link_li">
                <input type="submit" value="Login Now" class="sbmt_btn"/>
            </li>
        </ul>
    </div>
</section>
<!--footer-->
<footer>
    <!--help-->

</footer>
</body>
</html>

