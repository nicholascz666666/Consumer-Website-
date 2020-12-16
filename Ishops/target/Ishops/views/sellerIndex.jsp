<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>Seller</title>
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
            <dt>Seller Center</dt>
            <dd><a href="#">My Product</a></dd>
        </dl>
    </aside>
    <div class="user_rt_cont">
        <div class="top_title">
            <strong>Product List</strong>
            <a href="showAddProduct" title="Add new product" class="fr">Add new product</a>
        </div>
        <table class="order_table">
            <tr>
                <th>Item id</th>
                <th>Image</th>
                <th>Product name</th>
                <th>Price</th>
                <th>Instock</th>
                <th>Unit</th>
                <th>Operation</th>
            </tr>
            <c:forEach items="${myProducts}" var="item">
            <tr>
                <td class="center">${item.id}</td>
                <td class="center"><a href="product.html" target="_blank"><img src="upload/goods009.jpg" width="50" height="50"/></a></td>
                <td><a h href="product.html" target="_blank">${item.name}</a></td>
                <td class="center"><strong >￥${item.price}</strong></td>
                <td class="center">Instock:${item.instock}</td>
                <td class="center">${item.discription}</td>
                <td class="center">
                    <a href="showUpdateItem" title="edit"><img src="images/icon_edit.gif"/></a>
                    <a href="deleteItem?itemId=${item.id}" title="delete"><img src="images/icon_trash.gif"/></a>
                </td>
            </tr>
            </c:forEach>
        </table>

    </div>
</section>
<!--footer-->
<footer>
    <!--help-->


</footer>
</body>
</html>

