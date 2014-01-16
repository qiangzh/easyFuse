<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<head>
	<title><fmt:message key="role.list.title" /></title>
	<meta name="menu" content="AdminMenu" />
</head>

<div class="span10">
	<h2><fmt:message key="role.list.heading" /></h2>
	
	<div id="actions" class="btn-group">
		<a class="btn btn-primary" href="<c:url value='/role/addRole?from=list'/>"> 
			<i class="icon-plus icon-white"></i>
			<fmt:message key="button.add" />
		</a>
	</div>
	
	<display:table id="role" name="roles" requestURI="" defaultsort="1" pagesize="25" 
		class="table table-condensed table-striped table-hover table-bordered" size="resultSize" export="false">		
        <display:column title="序号">${role_rowNum}</display:column>   
		<display:column property="roleCode" titleKey="role.code" sortable="true" escapeXml="true" url="/role/editRole?from=list" paramId="id" paramProperty="id" />
		<display:column property="roleName" titleKey="role.name" sortable="true" autolink="true" media="html" />
		
		<display:setProperty name="paging.banner.item_name">&nbsp;</display:setProperty>
		<display:setProperty name="paging.banner.items_name">&nbsp;</display:setProperty>		
	</display:table>
</div>
