<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>Product Detail </title>
    <meta name="keywords"  content="DeathGhost" />
    <meta name="description" content="DeathGhost" />
    <meta name="author" content="DeathGhost,deathghost@deathghost.cn">
    <link rel="icon" href="images/icon/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="css/style.css" /><script src="js/html5.js"></script>
    <script src="js/jquery.js"></script>
    <script src="js/jquery.jqzoom.js"></script>
    <script src="js/base.js"></script>
    <script>
        $(document).ready(function(){
            $("nav .indexAsideNav").hide();
            $("nav .category").mouseover(function(){
                $(".asideNav").slideDown();
            });
            $("nav .asideNav").mouseleave(function(){
                $(".asideNav").slideUp();
            });
            //detail tab
            $(".product_detail_btm .item_tab a").click(function(){
                var liindex = $(".product_detail_btm .item_tab a").index(this);
                $(this).addClass("curr_li").parent().siblings().find("a").removeClass("curr_li");
                $(".cont_wrap").eq(liindex).fadeIn(150).siblings(".cont_wrap").hide();
            });
            //radio
            $(".horizontal_attr label").click(function(){
                $(this).addClass("isTrue").siblings().removeClass("isTrue");
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


<aside class="wrap insideLink">
    <a href="showIndex">Home</a>
</aside>
<section class="wrap product_detail">
    <!--img:left-->
    <div class="gallery">
        <div>
            <div id="preview" class="spec-preview"> <span class="jqzoom"><img jqimg="upload/goods.jpg" src="upload/goodssmall.jpg" /></span> </div>
            <div class="spec-scroll"> <a class="prev">&lt;</a> <a class="next">&gt;</a>
                <div class="items">
                    <ul>
                        <li><img bimg="upload/goods.jpg" src="upload/goodssmall.jpg" onmousemove="preview(this);"></li>
                        <li><img bimg="upload/goods004.jpg" src="upload/goods004small.jpg" onmousemove="preview(this);"></li>
                        <li><img bimg="upload/goods.jpg" src="upload/goodssmall.jpg" onmousemove="preview(this);"></li>
                        <li><img bimg="upload/goods.jpg" src="upload/goodssmall.jpg" onmousemove="preview(this);"></li>
                        <li><img bimg="upload/goods.jpg" src="upload/goodssmall.jpg" onmousemove="preview(this);"></li>
                        <li><img bimg="upload/goods.jpg" src="upload/goodssmall.jpg" onmousemove="preview(this);"></li>
                        <li><img bimg="upload/goods.jpg" src="upload/goodssmall.jpg" onmousemove="preview(this);"></li>
                        <li><img bimg="upload/goods.jpg" src="upload/goodssmall.jpg" onmousemove="preview(this);"></li>
                        <li><img bimg="upload/goods.jpg" src="upload/goodssmall.jpg" onmousemove="preview(this);"></li>
                        <li><img bimg="upload/goods.jpg" src="upload/goodssmall.jpg" onmousemove="preview(this);"></li>
                        <li><img bimg="upload/goods.jpg" src="upload/goodssmall.jpg" onmousemove="preview(this);"></li>
                        <li><img bimg="upload/goods.jpg" src="upload/goodssmall.jpg" onmousemove="preview(this);"></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <!--text:right-->
    <div class="rt_infor">
        <!--lt_infor-->
        <div class="goods_infor">
            <h2>${item.name}</h2>
            <ul>
                <li>
                    <dl class="horizontal">
                        <dt>Price：</dt>
                        <dd><strong>￥${item.price}</strong>RMB</dd>
                    </dl>
                </li>
                <li>
                    <dl class="horizontal horizontal_attr">
                        <dt>Count：</dt>
                        <dd>
                            <span>Instock：${item.instock}</span>
                        </dd>
                    </dl>
                </li>
                <li class="last_li">
                    <input type="button" value="Buy Now" class="buy_btn" onClick="javascript:location.href='showCreateOrder'"/>
                </li>
            </ul>
        </div>
        <!--rt_infor-->
        <div class="shop_infor">
            <dl class="business_card">
                <dt>Seller Info</dt>
                <dd>Username：${seller.username}</dd>
                <dd>Firstname：${seller.firstname}</dd>
                <dd>Lastname：${seller.lastname}</dd>
                <dd>Deal Count：${seller.number_deals}</dd>
            </dl>
        </div>
    </div>
</section>
<!--detail-->
<section class="wrap product_detail_btm">
    <article>
        <ul class="item_tab">
            <li><a class="curr_li">Product Detail</a></li>
        </ul>
        <div class="cont_wrap active">
            <font color="#008b8b">${product.discription}</font>
            The product has participated in the commonweal baby program, and the seller promises to donate 0.02 yuan for each transaction. A total of 24560 donations have been made.

            Introduction to the use of the donation: Based on the importance of game education in children's growth, one foundation has set up the "one paradise project" to help provide slide, climbing frame, seesaw, swing, table tennis table, etc., to build classes for children in post disaster and poverty-stricken areas
        </div>
    </article>
    <aside>
        <dl class="aside_pro_list">
            <dt>
                <strong>Hot</strong>
                <a>More</a>
            </dt>
            <dd>
                <a href="#" class="goods_img"><img src="upload/goods002.jpg"/></a>
                <div class="rt_infor">
                    <h3><a href="#">Fashion women's spring 2020 T-shirt</a></h3>
                    <p><del >1298.00</del></p>
                    <p><strong>980.00</strong></p>
                </div>
            </dd>
            <dd>
                <a href="#" class="goods_img"><img src="upload/goods002.jpg"/></a>
                <div class="rt_infor">
                    <h3><a href="#">Fashion women's spring 2020 T-shirt</a></h3>
                    <p><del >1298.00</del></p>
                    <p><strong >980.00</strong></p>
                </div>
            </dd>
            <dd>
                <a href="#" class="goods_img"><img src="upload/goods002.jpg"/></a>
                <div class="rt_infor">
                    <h3><a href="#">Fashion women's spring 2020 T-shirt</a></h3>
                    <p><del >1298.00</del></p>
                    <p><strong >980.00</strong></p>
                </div>
            </dd>
            <dd>
                <a href="#" class="goods_img"><img src="upload/goods002.jpg"/></a>
                <div class="rt_infor">
                    <h3><a href="#">Fashion women's spring 2020 T-shirt</a></h3>
                    <p><del >1298.00</del></p>
                    <p><strong>980.00</strong></p>
                </div>
            </dd>
        </dl>
    </aside>
</section>
<footer>

</footer>
</body>
</html>

