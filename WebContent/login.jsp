<%@ include file="/views/jspf/directive/page.jspf" %>
<%@ include file="/views/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Login" />
<%@ include file="/views/jspf/head.jspf" %>

<body class="main">

<div class="content">
    <p class="title"><span class="text"></span></p>
    <p class="title">Periodicals</p>
    <%--<p class="text">Добро пожаловать в библиотеку периодических изданий.</p>--%>
    <%--<p class="text">Проект находится в разработке, поэтому дизайн и функционал будет постоянно дорабатываться.</p>--%>
    <%--<p class="text">По всем вопросам обращайтесь по адресу <a href="mailto:support@testperiodical.com">support@testperiodical.com</a>--%>
    <%--</p>--%>
</div>

<div id="loginbox">

    <%@include file="/views/jspf/header.jspf"%>

    <form class="login_form" action="controller" method="post">
        <input type="hidden" name="command" value="login">
        <p>
            <label for="login">Login or email</label>
            <input type="text" id="login" name="login" placeholder="login or email" required>
        </p>
        <p>
            <label for="password">Password</label>
            <input type="password" id="password" name='password' placeholder="Password" required>
        </p>

        <p>
            <input type="submit" value="Sign In">
        </p>
        <div class="form-group">
            <div class="col-md-12 control">
                <div style="border-top: 1px solid#876354; padding-top:15px; font-size:85%">
                    Don't have an account!
                    <a href="views/registration/signup.html"> Sign Up Here </a>
                </div>
            </div>
        </div>
    </form>
</div>

<%@include file="/views/jspf/footer.jspf"%>
</body>
</html>