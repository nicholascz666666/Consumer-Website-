<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>List</title>
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
            //冒泡
            $(".favorite_list li a").click(function(){
                alert("链接");
                window.location.href='product.html';
            });
            $(".favorite_list li .shop_collect_goods").click(function(){
                alert("收藏产品");
                event.stopPropagation();
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
                    <li><a href="user.html">Center</a></li>
                </ul>
            </c:if>
        </div>
    </div>
    <!--logoArea-->

    <!--nav-->
    <nav>
        <ul class="wrap navList">

        </ul>
    </nav>
</header>


<section class="wrap list_class_page">
    <div class="lt_area">
        <section class="shop_goods_li">
            <h2>Products</h2>

            <ul class="favorite_list">
                <c:forEach items="${products}" var="item">
                <li>
                    <a href="showProductDetail?id=${item.id}">
                        <img src="upload/goods005.jpg"/>
                        <h3>${item.name}</h3>
                        <p class="price"><span>￥${item.price}</span></p>
                    </a>
                </li>
                </c:forEach>
            </ul>

        </section>

    </div>
    <aside class="rtWrap">
        <dl class="rtLiTwoCol">
            <dt>Hot</dt>
            <dd>
                <a href="#">
                    <img src="upload/goods002.jpg"/>
                    <p>0.00</p>
                </a>
            </dd>
            <dd>
                <a href="#">
                    <img src="upload/goods001.jpg"/>
                    <p>0.00</p>
                </a>
            </dd>
            <dd>
                <a href="#">
                    <img src="upload/goods003.jpg"/>
                    <p>0.00</p>
                </a>
            </dd>
            <dd>
                <a href="#">
                    <img src="upload/goods004.jpg"/>
                    <p>0.00</p>
                </a>
            </dd>
            <dd>
                <a href="#">
                    <img src="upload/goods008.jpg"/>
                    <p>0.00</p>
                </a>
            </dd>
            <dd>
                <a href="#">
                    <img src="upload/goods007.jpg"/>
                    <p>0.00</p>
                </a>
            </dd>
            <dd>
                <a href="#">
                    <img src="upload/goods006.jpg"/>
                    <p>0.00</p>
                </a>
            </dd>
            <dd>
                <a href="#">
                    <img src="upload/goods005.jpg"/>
                    <p>0.00</p>
                </a>
            </dd>
            <dd>
                <a href="#">
                    <img src="upload/goods008.jpg"/>
                    <p>0.00</p>
                </a>
            </dd>
            <dd>
                <a href="#">
                    <img src="upload/goods007.jpg"/>
                    <p>0.00</p>
                </a>
            </dd>
            <dd>
                <a href="#">
                    <img src="upload/goods006.jpg"/>
                    <p>0.00</p>
                </a>
            </dd>
            <dd>
                <a href="#">
                    <img src="upload/goods005.jpg"/>
                    <p>0.00</p>
                </a>
            </dd>
        </dl>
    </aside>
</section>
<!--footer-->
<footer>

</footer>
</body>
</html>
