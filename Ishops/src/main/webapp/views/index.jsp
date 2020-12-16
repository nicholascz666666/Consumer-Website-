<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>Home</title>
    <meta name="keywords"  content="DeathGhost" />
    <meta name="description" content="DeathGhost" />
    <meta name="author" content="DeathGhost,deathghost@deathghost.cn">
    <link rel="icon" href="images/icon/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="css/style.css" /><script src="js/html5.js"></script>
    <script src="js/jquery.js"></script>
    <script src="js/swiper.min.js"></script>
    <script>
        $(document).ready(function(){
            var mySwiper = new Swiper('#slide',{
                autoplay:5000,
                visibilityFullFit : true,
                loop:true,
                pagination : '.pagination',
            });
        })
    </script>
</head>
<body>
<!--advertisment<div class="wrap"><img src="upload/banner.jpg"/></div>-->
<!--header-->
<header>
    <!--topNavBg-->
    <div class="topNavBg">
        <div class="wrap">
            <!--topLeftNav-->
                <c:if test="${empty customer}">
                    <ul class="topLtNav">
                        <li><a href="showLogin" class="obviousText">please login</a></li>
                        <li><a href="showRegister">register</a></li>
                    </ul>
                </c:if>
                <c:if test="${not empty customer}">
                    <ul class="topRtNav">
                        <li><a href="showUserCenter">User Center</a></li>
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
        <div class="search">
            <ul class="switchNav">
                <li class="active" id="chanpin">Product</li>
            </ul>
            <div class="searchBox">
                <form method="post" action="searchItems">
                    <div class="inputWrap">
                        <input type="text" name="keywords" placeholder="Please enter keywords"/>
                    </div>
                    <div class="btnWrap">
                        <input type="submit" value="Search"/>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <!--nav-->
    <nav>
        <ul class="wrap navList">


            <li>
                <a href="index.html" class="active">Home</a>
            </li>

        </ul>
    </nav>

</header>


<!--advertisment area-->
<section class="wrap">
    <!--ctCont-->
    <div class="IdxmainArea">
        <!--slide-->
        <div id="slide">
            <div class="swiper-wrapper">
                <div class="swiper-slide">
                    <a href="#">
                        <img src="upload/slide01.jpg"/>
                    </a>
                </div>
                <div class="swiper-slide">
                    <a href="#">
                        <img src="upload/slide02.jpg"/>
                    </a>
                </div>
                <div class="swiper-slide">
                    <a href="#">
                        <img src="upload/slide03.jpg"/>
                    </a>
                </div>
            </div>
            <div class="pagination"></div>
        </div>
        <!--singleAd-->
        <div class="singleAd">
            <a href="#">
                <img src="upload/sigleAd.jpg"/>
            </a>
        </div>
        <!--bestShop-->

    </div>
    <!--asdCont-->
    <div class="IdxAsideRt">
        <!--login-->

    </div>
</section>
<!--productList-->
<section class="wrap idxproLi">
    <h2>
        <strong>
            <a href="#">Product Show</a>
        </strong>
        <span class="classLi">
   <a href="#">Summer</a>
   <a href="#">Dress</a>
   <a href="#">Shirt</a>
   <a href="#">Cols</a>
   <a href="#">Bag</a>
   <a href="#">T-shirt</a>
  </span>
    </h2>
    <div class="ltArea">
        <!--ad:category pic-->
        <a href="product_list.html"><img src="upload/bestCategoryPic01.jpg"/></a>
    </div>
    <div class="ctLi">
        <ul>
            <li>
                <a href="#">
                    <img src="upload/goods001.jpg"/>
                    <h3>2020 newest</h3>
                    <p><span>1000.00</span></p>
                </a>
            </li>
            <li>
                <a href="#">
                    <img src="upload/goods003.jpg"/>
                    <h3>2020 newest</h3>
                    <p><span>545.00</span></p>
                </a>
            </li>
            <li>
                <a href="#">
                    <img src="upload/goods004.jpg"/>
                    <h3>2020 newest</h3>
                    <p><span>1000.00</span></p>
                </a>
            </li>
            <li>
                <a href="#">
                    <img src="upload/goods003.jpg"/>
                    <h3>2020 newest</h3>
                    <p><span>1000.00</span></p>
                </a>
            </li>
            <li>
                <a href="#">
                    <img src="upload/goods001.jpg"/>
                    <h3>2020 newest</h3>
                    <p><span>980.00</span></p>
                </a>
            </li>
            <li>
                <a href="#">
                    <img src="upload/goods002.jpg"/>
                    <h3>2020 newest</h3>
                    <p><span>642.00</span></p>
                </a>
            </li>
            <li>
                <a href="#">
                    <img src="upload/goods004.jpg"/>
                    <h3>2020 newest</h3>
                    <p><span>793.00</span></p>
                </a>
            </li>
            <li>
                <a href="#">
                    <img src="upload/goods001.jpg"/>
                    <h3>2020 newest</h3>
                    <p><span>755.00</span></p>
                </a>
            </li>
            <li>
                <a href="#">
                    <img src="upload/goods002.jpg"/>
                    <h3>2020 newest</h3>
                    <p><span>360.00</span></p>
                </a>
            </li>
            <li>
                <a href="#">
                    <img src="upload/goods003.jpg"/>
                    <h3>2020 newest</h3>
                    <p><span>1255.00</span></p>
                </a>
            </li>
        </ul>
        <!--bestBrand-->
        <div class="idxBrandLi">
            <a href="shop.html"><img src="upload/brandLogo01.jpg"/></a>
            <a href="shop.html"><img src="upload/brandLogo02.jpg"/></a>
            <a href="shop.html"><img src="upload/brandLogo03.jpg"/></a>
            <a href="shop.html"><img src="upload/brandLogo04.jpg"/></a>
        </div>
    </div>
</section>


<!--section:two->articleList-->

    <div class="wrap btmInfor">
        <p>© 2020 </p>
    </div>
</footer>
</body>
</html>
