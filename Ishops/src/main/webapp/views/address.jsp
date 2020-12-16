<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>My Address</title>
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
                    <li><a href="showLogin" class="obviousText">Please Login</a></li>
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
        <ul class="wrap navList">

        </ul>
    </nav>
</header>


<section class="wrap user_center_wrap">
    <aside class="user_aside_nav">
        <dl>
            <dt>Seller Center</dt>
            <dd><a href="showOrderList">My Orders</a></dd>
            <dd><a href="#">My Address</a></dd>
        </dl>

    </aside>
    <div class="user_rt_cont">
        <div class="top_title">
            <strong>Address list</strong>
        </div>
        <form action="addAddress" method="post">
        <table class="order_table">
            <tr>
                <td width="100"  align="right">Country：</td>
                <td><input type="text"name="country" placeholder="" class="textbox"/></td>
            </tr>
            <tr>
                <td width="100" align="right">State：</td>
                <td><input type="text" placeholder="" name="state" class="textbox"/></td>
            </tr>
            <tr>
                <td width="100"  align="right">City：</td>
                <td><input type="text"name="city" placeholder="" class="textbox"/></td>
            </tr>
            <tr>
                <td width="100"  align="right">Short Address：</td>
                <td><input type="text" name="address1"placeholder="" class="textbox"/></td>
            </tr>
            <tr>
                <td width="100" align="right">Detail Address：</td>
                <td><input type="text" name="address2" placeholder="" class="textbox"/></td>
            </tr>
            <tr>
                <td width="100"  align="right">Zipcode：</td>
                <td><input type="text"name="zipcode"  class="textbox textbox_295"/></td>
            </tr>
            <tr>
                <td width="100" align="right"></td>
                <td><input type="submit" value="Save" class="group_btn"/></td>
            </tr>
        </table>
        </form>
        <table class="order_table address_tbl">
            <tr>
                <th width="140">Name</th>
                <th width="140">Zipcode</th>
                <th>Address</th>
            </tr>
            <c:forEach items="${addresses}" var="address">
            <tr>
                <td>${customer.username}</td>
                <td>${address.zipcode}</td>
                <td></td>
                <td>
                    <address>
                        ${address.country}  ${address.state}  ${address.city}  ${address.address2}
                    </address>
                </td>

            </tr>
            </c:forEach>
        </table>

    </div>
</section>
<!--footer-->
<footer>

</footer>
</body>
</html>
