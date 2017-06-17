<%@ page pageEncoding="UTF-8" %>
<%@ include file="/views/jspf/directive/page.jspf" %>
<%@ include file="/views/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Periodicals" scope="page"/>
<%@ include file="/views/jspf/head.jspf" %>

<body>
<table id="main-container">

    <%@ include file="/views/jspf/header.jspf" %>
    <%@include file="/views/jspf/categories.jspf" %>

    <div class="periodical_info">
        <tr>
            <td class="content">

                <form id="make_order" action="controller">
                    <input type="hidden" name="command" value="makeOrder"/>
                    <input value="Make an order" type="submit"/>

                    <table id="list_menu_table">
                        <thead>
                        <tr>
                            <td>N</td>
                            <td>Name</td>
                            <td>Price</td>
                            <td>Order</td>
                        </tr>
                        </thead>

                        <c:set var="k" value="0"/>
                        <c:forEach var="item" items="${periodicals}">
                            <c:set var="k" value="${k+1}"/>
                            <tr>
                                <td><c:out value="${k}"/></td>
                                <td>${item.name}</td>
                                <td>${item.price}</td>
                                <td><input type="checkbox" name="itemId" value="${item.id}"/></td>
                            </tr>
                        </c:forEach>
                    </table>

                </form>

            </td>
        </tr>
    </div>

    <%@ include file="/views/jspf/footer.jspf" %>

</table>
</body>
