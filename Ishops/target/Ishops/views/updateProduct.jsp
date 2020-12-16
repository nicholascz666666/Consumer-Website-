<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>Update</title>
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
            <dd><a href="showSellerIndex">Center</a></dd>
        </dl>

    </aside>
    <div class="user_rt_cont">
        <div class="top_title">
            <strong>Update Product</strong>
        </div>
        <form method="post" action="updateItem">
        <table class="order_table">
            <tr>
                <td width="80" align="right">Product Id：</td>
                <td>
                    <input type="text" class="textbox textbox_295" name="id" placeholder="please enter product id"/>
                    <mark class="tips_errors">*</mark>
                </td>
            </tr>
            <tr>
                <td width="80" align="right">Product Name：</td>
                <td>
                    <input type="text" class="textbox textbox_295" name="name" placeholder="please enter prodcut name"/>
                    <mark class="tips_errors">*</mark>
                </td>
            </tr>

            <tr>
                <td width="80" align="right">Instock count：</td>
                <td>
                    <input type="text" class="textbox" name="instock" placeholder="Total Instock"/>
                </td>
            </tr>
            <tr>
                <td width="80" align="right">Sell Price：</td>
                <td>
                    <input type="text" class="textbox" name="price" placeholder="0.00"/>
                    <mark class="tips_errors">*</mark>
                </td>
            </tr>
            <tr>
                <td width="80" align="right">Product Introduce：</td>
                <td>
                    <input type="text" class="textbox" name="discription" placeholder="Product Introduce"/>
                    <mark class="tips_errors">*</mark>
                </td>
            </tr>

            <tr>
                <td width="80" align="right"></td>
                <td>
                    <input type="submit" value="Save" class="group_btn"/>
                </td>
            </tr>
        </table>
        </form>
    </div>
</section>
<!--footer-->
<footer>


</footer>
</body>
</html>

