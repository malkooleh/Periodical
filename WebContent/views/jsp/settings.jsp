<%@ include file="/views/jspf/directive/taglib.jspf" %>
<%@ page pageEncoding="UTF-8" %>
<html>

<c:set var="title" value="Settings"/>
<%@ include file="/views/jspf/head.jspf" %>

<body>

<%@ include file="/views/jspf/header.jspf" %>

<table>

    <tr>
        <td class="content">
                <%-- CONTENT --%>

            <form id="settings_form" action="controller" method="post">
                <input type="hidden" name="command" value="updateSettings"/>

                <div>
                    <p>Language</p>
                    <select name="locale">
                        <option value="ru">Russian</option>
                        <option value="en">English</option>
                    </select>
                </div>

                <input type="submit" value="Update"><br/>
            </form>

                <%-- CONTENT --%>
        </td>
    </tr>
</table>
<%@ include file="/views/jspf/footer.jspf" %>
</body>
</html>