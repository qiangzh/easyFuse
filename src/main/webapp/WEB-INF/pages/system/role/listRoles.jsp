<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<head>
	<title><fmt:message key="role.list.title" /></title>
	<meta name="menu" content="AdminMenu" />
	<script language="javaScript" type="text/javascript">	
		// 查看已分配用户
		function viewUser(roleID) {
			var url = '<s:url value='/role/listUserOfRole'/>'+'?role.id='+roleID;
			document.roleForm.action =url;
			document.roleForm.submit();
		}
		// 权限设置
		function listRoleFuncTree(roleID) {
			var url = '<s:url value='/role/listRoleFuncTree'/>'+'?roleFuncTreeVO.roleID='+roleID;
			document.roleForm.action =url;
			document.roleForm.submit();
		}
		// 修改角色
		function editRole(roleID) {
			var url = '<s:url value='/role/editRole'/>'+'?role.id='+roleID;
			document.roleForm.action =url;
			document.roleForm.submit();
		}
		// 删除角色
		function deleteRole(roleID) {
			var url = '<s:url value='/role/deleteRole'/>'+'?role.id='+roleID;
			document.roleForm.action =url;
			document.roleForm.submit();
		}
	</script>
</head>
<body>
	<div class="container-fluid">
		<h2><fmt:message key="role.list.heading" /></h2>
		<div class="row-fluie">
			<div id="actions" class="btn-group">
				<a class="btn btn-mini btn-primary" href="<c:url value='/role/addRole?from=list'/>"> 
					<i class="icon-plus icon-white"></i>
					<fmt:message key="button.add" />
				</a>
			</div>
		</div>		
		<div class="row-fluie">
			<display:table id="role" name="roles" requestURI="" pagesize="20" 
				class="table table-condensed table-striped table-hover table-bordered" size="resultSize" export="false">		
		        <display:column title="序号">${role_rowNum}</display:column>   
				<display:column property="roleCode" titleKey="role.code" />
				<display:column property="roleName" titleKey="role.name" />		
				<display:column title="" media="html">
					<a onclick="viewUser('${role.id}');">查看已分配用户</a>
		        </display:column>		        		
				<display:column title="">
					<a onclick="listRoleFuncTree('${role.id}');">权限设置</a>
		        </display:column>
		        <display:column title="" >
		        	<a onclick="editRole('${role.id}');">修改</a>
		        </display:column>	
		        <display:column title="">
		        	<a onclick="deleteRole('${role.id}');">删除</a>
		        </display:column>		
				<display:setProperty name="paging.banner.item_name">&nbsp;</display:setProperty>
				<display:setProperty name="paging.banner.items_name">&nbsp;</display:setProperty>		
			</display:table>
		</div>
		<s:form name="roleForm" action="/org/editRole" method="post"></s:form>
	</div>
</body>
