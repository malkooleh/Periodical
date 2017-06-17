<%@ page pageEncoding="UTF-8" %>
<%@ include file="/views/jspf/directive/page.jspf" %>
<%@ include file="/views/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="User Subscription" scope="page"/>
<%@ include file="/views/jspf/head.jspf" %>

<body>

<%@ include file="/views/jspf/header.jspf" %>

<div>

    <h4>Order</h4>
    <c:out value="${userOrder.id} ${userOrder.bill} ${userOrder.statusId}"/>

</div>
</body>
</html>
