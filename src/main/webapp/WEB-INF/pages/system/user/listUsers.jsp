<%@ include file="/common/taglibs.jsp"%>

<head>
	<title><fmt:message key="userList.title" /></title>
	<meta name="menu" content="AdminMenu" />
</head>

<div class="col-sm-10">
	<h2>
		<fmt:message key="userList.heading" />
	</h2>

	<form method="get" action="${ctx}/user/queryUsers" id="searchForm" class="form-inline">
		<div id="search" class="text-right">
			<span class="col-sm-9">
				<input type="text" size="20" name="q" id="query" value="${param.q}"
				placeholder="<fmt:message key="search.enterTerms"/>"
				class="form-control input-sm" />
			</span>
			<button id="button.search" class="btn btn-default btn-sm" type="submit">
				<i class="icon-search"></i>
				<fmt:message key="button.search" />
			</button>
		</div>
	</form>

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
