<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>Order</title>
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

<section class="wrap" style="margin-top:20px;overflow:hidden;">
    <table class="order_table">
        <caption>
            <strong>Order</strong>
        </caption>
        <tr>
            <td class="center"><a href="product.html"><img src="upload/goods.jpg" style="width:50px;height:50px;"/></a></td>
            <td><a href="#">${item.name}</a></td>
            <td>
                <p>${item.discription}</p>
            </td>
            <td class="center"><span>${item.price}</span></td>
            <td class="center"><span>1</span></td>
            <td class="center"><strong >sum：${item.price}</strong></td>
        </tr>

    </table>
    <ul class="order_choice">
        <li>
            <dl>
                <dt>Receive Address</dt>
                <dd>
                    <label class="radio istrue">Default Address</label>
                </dd>

            </dl>
        </li>

    </ul>
    <div class="order_btm_btn">
        <a href="createOrder" class="link_btn_02 add_btn"/>Submit Order</a>
    </div>
</section>
<!--footer-->
<footer>

</footer>
</body>
</html>

