<%@ include file="/views/jspf/directive/taglib.jspf" %>
<%@ page pageEncoding="UTF-8" %>
<html>

<c:set var="title" value="Periodicals"/>
<%@ include file="/views/jspf/head.jspf" %>

<link href="css/reset.css" rel="stylesheet" type="text/css">
<link href="css/style_periodicals.css" rel="stylesheet" type="text/css">

<%@ include file="/views/jspf/header.jspf" %>

<body class="container">

<div class="search_form">
    <form name="search_form" method="POST">
        <input type="text" name="search_string" value="${search_string}" size="100"/>
        <input class="search_button" type="submit" value="Поиск" name="search_button"/>
    </form>
</div>

<div class="periodical_list">

    <%@include file="/views/jspf/categories.jspf" %>

    <div class="periodicals_info">

        <c:set var="index" value="0"/>
        <c:forEach var="item" items="${periodicals}">
            <div id="item">
                <div id="periodical_title">
                    <p> ${item.name}</p>
                </div>
                <div id="periodical_image">
                    <img src="<%request.getContextPath();%>/showImage?index=${index}" alt="Обложка" height="210px"
                         width="100%"/>
                </div>
                <div id="p_details">
                    <br><strong>Стоимость: </strong>${item.price}<strong>грн.</strong>
                    <p><a href="#">Просмотреть</a></p>
                </div>
            </div>
            <c:set var="index" value="${index+1}"></c:set>
        </c:forEach>

    </div>

</div>

</body>
</html>