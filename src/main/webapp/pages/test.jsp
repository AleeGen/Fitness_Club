<%@ page import="java.util.Enumeration" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="prop.text"/>
<html>
<head>
    <base href="${pageContext.servletContext.getInitParameter("absolutPath")}">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Адаптивная вёрстка сайта</title>
    <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Open+Sans:400,400italic,600,600italic,700,700italic|Playfair+Display:400,700&subset=latin,cyrillic">
    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.css">
    <link rel="stylesheet" href="css/newStyle.css" type="text/css"/>
<%--    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.2.2/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/prefixfree/1.0.7/prefixfree.min.js"></script>--%>
</head>
<body>
<header>
    <nav class="container">
        <a class="logo" href="">
            <span>Fitness Club</span>
        </a>
        <div class="nav-toggle"><span></span></div>
        <form action="" method="get" id="searchform">
            <input type="text" placeholder="Искать на сайте...">
            <button type="submit"><i class="fa fa-search"></i></button>
        </form>

        <ul id="menu">
            <li><a href="">Блог</a></li>
            <li><a href="">Портфолио</a></li>
            <li><a href="">Об авторе</a></li>
            <li>
            <form action="${pageContext.request.contextPath}/controller" method="get">
                <input type="hidden" name="command" value="view_index">
                <input type="submit" name="submit" value="sub"/>
            </form>
            </li>
        </ul>
    </nav>
</header>

<div class="container">
    <div class="posts-list">
        <article id="post-1" class="post">
            <div class="post-image"><a href=""><img src="https://html5book.ru/wp-content/uploads/2016/05/rasskaz_slovar_rodnoy_prirodi.jpg"></a></div>
            <div class="post-content">
                <div class="category"><a href="">Дизайн</a></div>
                <h2 class="post-title">Весна</h2>
                <p>Очень богат русский язык словами, относящимися к временам года и к природным явлениям, с ними связанным.</p>
                <div class="post-footer">
                    <a class="more-link" href="">Продолжить чтение</a>
                    <div class="post-social">
                        <a href="" target="_blank"><i class="fa fa-facebook"></i></a>
                        <a href="" target="_blank"><i class="fa fa-twitter"></i></a>
                        <a href="" target="_blank"><i class="fa fa-pinterest"></i></a>
                    </div>
                </div>
            </div>
        </article>
        <article id="post-2" class="post">
            ...
        </article>
    </div>
    <aside>
        <div class="widget">
            <h3 class="widget-title">Категории</h3>
            <ul class="widget-category-list">
                <li><a href="">Дизайн</a> (2)</li>
                <li><a href="">Вёрстка</a> (5)</li>
                <li><a href="">Видео</a> (1)</li>
            </ul>
        </div>
        <div class="widget">
            <h3 class="widget-title">Последние записи</h3>
            <ul class="widget-posts-list">
                <li>
                    <div class="post-image-small">
                        <a href=""><img src="https://html5book.ru/wp-content/uploads/2016/05/rasskaz_slovar_rodnoy_prirodi.jpg"></a>
                    </div>
                    <h4 class="widget-post-title">Весна</h4>
                </li>
                <li>
                    <div class="post-image-small">
                        <a href=""><img src="https://html5book.ru/wp-content/uploads/2016/05/rasskaz_Russia.jpg"></a>
                    </div>
                    <h4 class="widget-post-title">Лето</h4>
                </li>
                <li>
                    <div class="post-image-small">
                        <a href=""><img src="https://html5book.ru/wp-content/uploads/2016/05/rasskaz_rodnaya_priroda_osen.jpg"></a>
                    </div>
                    <h4 class="widget-post-title">Осень</h4>
                </li>
            </ul>
        </div>
        <div class="widget">
            <h3 class="widget-title">Подписка на рассылку</h3>
            <form action="" method="post" id="subscribe">
                <input type="email" name="email" placeholder="Ваш email" required>
                <button type="submit"><i class="fa fa-paper-plane-o"></i></button>
            </form>
        </div>
    </aside>
</div>
<footer>
    <div class="container">
        <div class="footer-col"><span>Мой блог © 2016</span></div>
        <div class="footer-col">
            <div class="social-bar-wrap">
                <a title="Facebook" href="" target="_blank"><i class="fa fa-facebook"></i></a>
                <a title="Twitter" href="" target="_blank"><i class="fa fa-twitter"></i></a>
                <a title="Pinterest" href="" target="_blank"><i class="fa fa-pinterest"></i></a>
                <a title="Instagram" href="" target="_blank"><i class="fa fa-instagram"></i></a>
            </div>
        </div>
        <div class="footer-col">
            <a href="mailto:admin@yoursite.ru">Написать письмо</a>
        </div>
    </div>
</footer>

</body>
</html>
