<%@ include file="/views/jspf/directive/page.jspf" %>
<%@ include file="/views/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Settings" scope="page" />
<%@ include file="/views/jspf/head.jspf" %>

<body>
	<table id="main-container">

		<%@ include file="/views/jspf/header.jspf" %>

		<tr>
			<td class="content">
				<%-- CONTENT --%>

				<form id="settings_form" action="controller" method="post">
					<input type="hidden" name="command" value="updateSettings" />

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

		<%@ include file="/views/jspf/footer.jspf" %>
		
	</table>
</body>
</html>