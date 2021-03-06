<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<head>
	<title><fmt:message key="userList.title" /></title>
	<meta name="menu" content="AdminMenu" />
</head>
<body>
	<div class="container-fluid">
		<h2><fmt:message key="userList.heading" /></h2>
		<div class="row-fluid">
			<div id="actions" class="btn-group">
				<a class="btn btn-mini btn-primary" href="<c:url value='/user/addUser?from=list'/>"> 
					<i class="icon-plus icon-white"></i>
					<fmt:message key="button.add" />
				</a>
			</div>
		</div>
		<div class="row-fluid">		
			<display:table id="user" name="users" requestURI="" pagesize="20" 
				class="table table-condensed table-striped table-hover table-bordered" size="resultSize" export="false">		
		        <display:column title="序号">${user_rowNum}</display:column>   
				<display:column property="username" titleKey="user.username" url="/user/editUser?from=list" paramId="id" paramProperty="id" />
				<display:column property="email" titleKey="user.email"/>
				<display:column sortProperty="enabled" titleKey="user.enabled">
					<input type="checkbox" disabled="disabled" <c:if test="${user.enabled}">checked="checked"</c:if> />
				</display:column>
		
				<display:setProperty name="paging.banner.item_name"><fmt:message key="userList.user" /></display:setProperty>
				<display:setProperty name="paging.banner.items_name"><fmt:message key="userList.users" /></display:setProperty>		
			</display:table>
		</div>
	</div>
</body>
