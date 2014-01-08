<%@ include file="/common/taglibs.jsp"%>

<head>
	<title><fmt:message key="userList.title" /></title>
	<meta name="menu" content="AdminMenu" />
</head>

<div class="span10">
	<h2>
		<fmt:message key="userList.heading" />
	</h2>
	
	<div id="actions" class="btn-group">
		<a class="btn btn-primary" href="<c:url value='/user/addUser?from=list'/>"> 
			<i class="icon-plus icon-white"></i>
			<fmt:message key="button.add" />
		</a>
	</div>

	<display:table name="users" cellspacing="0" cellpadding="0" 
	    requestURI="" defaultsort="1" id="users" pagesize="25"
		class="table table-condensed table-striped table-hover" export="true">
		<display:column property="username" titleKey="user.username" sortable="true" escapeXml="true" url="/user/editUser?from=list" paramId="id" paramProperty="id" />
		<display:column property="email" titleKey="user.email" sortable="true" autolink="true" media="html" />
		<display:column sortProperty="enabled" titleKey="user.enabled" sortable="true" media="html">
		
		<display:column property="email" titleKey="user.email" media="csv xml excel pdf" />
			<input type="checkbox" disabled="disabled" <c:if test="${users.enabled}">checked="checked"</c:if> />
		</display:column>
		<display:column property="enabled" titleKey="user.enabled" media="csv xml excel pdf" />

		<display:setProperty name="paging.banner.item_name"><fmt:message key="userList.user" /></display:setProperty>
		<display:setProperty name="paging.banner.items_name"><fmt:message key="userList.users" /></display:setProperty>
		<display:setProperty name="export.excel.filename" value="User List.xls" />
		<display:setProperty name="export.csv.filename" value="User List.csv" />
		<display:setProperty name="export.pdf.filename" value="User List.pdf" />		
	</display:table>
</div>
