<%@ include file="/views/jspf/directive/page.jspf" %>
<%@ include file="/views/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Subscriptions"/>
<%@ include file="/views/jspf/head.jspf" %>

<body>

<%@ include file="/views/jspf/header.jspf" %>

<tr>
    <td class="content">
        <c:choose>
            <c:when test="${fn:length(userSubscriptionBeanList) == 0}">No such subscriptions</c:when>

            <c:otherwise>
                <table id="list_subscriptions_table">
                    <thead>
                    <tr>
                        <td>N</td>
                        <td>Client</td>
                        <td>Bill</td>
                        <td>Status</td>
                    </tr>
                    </thead>

                    <c:forEach var="bean" items="${userSubscriptionBeanList}">

                        <tr>
                            <td>${bean.id}</td>
                            <td>${bean.userFirstName} ${bean.userLastName}</td>
                            <td>${bean.subscriptionBill}</td>
                            <td>${bean.statusName}</td>
                        </tr>

                    </c:forEach>
                </table>
            </c:otherwise>
        </c:choose>
    </td>
</tr>

<%@include file="/views/jspf/footer.jspf" %>
</body>
</html>