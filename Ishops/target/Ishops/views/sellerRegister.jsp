<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/12/14
  Time: 1:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>Register</title>
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
                <li><a href="login.html" class="obviousText">Please Login</a></li>
                <li><a href="register.html">Register</a></li>
            </ul>
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

    </nav>

</header>


<section class="wrap user_form">
    <div class="lt_img">
        <img src="images/form_bg.jpg"/>
    </div>
    <div class="rt_form">
        <h2>Seller Register</h2>
        <ul>
            <form action="sellerRegister" method="post">
            <li class="user_icon">
                <input type="text" name="username" class="textbox" placeholder="username"/>
            </li>
            <li class="user_icon">
                <input type="text" name="keywords" class="textbox" placeholder="password"/>
            </li>
            <li class="user_icon">
                <input type="text" name="fristName"class="textbox" placeholder="firstname"/>
            </li>
            <li class="user_icon">
                <input type="text" name="lastName"class="textbox" placeholder="lastname"/>
            </li>
            <li class="link_li">
                <a href="showSellerLogin" title="Login" class="fr">Having account, login now？</a>
            </li>
            <li class="link_li">
                <input type="submit" value="Register now" class="sbmt_btn"/>
            </li>
        </ul>
    </div>
</section>
<!--footer-->
<footer>

</footer>
</body>
</html>

