<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>Center</title>
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
            <c:if test="${empty customer}">
                <ul class="topLtNav">
                    <li><a href="showLogin" class="obviousText">Please Enter</a></li>
                    <li><a href="register.html">Register</a></li>
                </ul>
            </c:if>
            <c:if test="${not empty customer}">
                <ul class="topRtNav">
                    <li><a href="showUserCenter">Center</a></li>
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

    </nav>

</header>


<section class="wrap user_center_wrap">
    <aside class="user_aside_nav">
        <dl>
            <dt>User Center</dt>
            <dd><a href="#">My Orders</a></dd>
            <dd><a href="showAllAddress">My Addresses</a></dd>
        </dl>

    </aside>
    <div class="user_rt_cont">
        <div class="top_title">
            <strong>My Order</strong>
        </div>

        <ul class="order_li">
            <c:forEach items="${orders}" var="order">
            <li>
                <table class="order_table">
                    <caption>
                        <strong>Order id：${order.id}</strong>
                    </caption>
                    <tr>
                        <td class="center"><a href="#"><img src="upload/goods009.jpg" style="width:50px;height:50px;"/></a></td>
                        <td><a href="product.html">Product name：${order.item.name}</a></td>
                        <td class="center"><span >Product price：${order.item.price}</span></td>
                        <td class="center"><b>1</b></td>
                        <td class="center"><strong >Product total：${order.sum}</strong></td>
                    </tr>
                </table>
            </li>
            </c:forEach>
        </ul>

    </div>
</section>
<!--footer-->
<footer>
    <!--help-->


</footer>
</body>
</html>

