<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<head>
    <title><fmt:message key="role.add.title"/></title>
	<meta name="menu" content="AdminMenu" />
</head>

<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span2">
				<c:choose> 
					<c:when test="${empty role.id}">
				    	<h2><fmt:message key="role.add.heading"/></h2>
					</c:when>
					<c:otherwise>
				    	<h2>编辑角色</h2>
				    </c:otherwise>
			   	</c:choose>
			    <fmt:message key="role.add.message"/>
			</div>
			
			<div class="span10">
			    <s:form name="roleForm" action="/role/saveRole" method="post" validate="true" autocomplete="off" cssClass="well form-horizontal">
			    	<s:hidden key="role.id"/>	
			        <div class="row">
			            <div class="span6">
			                <s:textfield name="role.roleCode" key="role.code" cssClass="form-control" required="true" maxLength="10"/>
			            </div>
			            <div class="span6">
			                <s:textfield name="role.roleName" key="role.name" cssClass="form-control" required="true" maxLength="32" />
			            </div>
			        </div>
			
			        <div id="actions" class="form-group form-actions">
			            <s:submit type="button" cssclass="btn btn-mini btn-primary" key="button.register" theme="simple">
			                <i class="icon-ok icon-white"></i>确定
			            </s:submit>
			            <a href="./listRoles" class="btn btn-mini btn-default"><i class="icon-remove"></i><fmt:message key="button.cancel"/></a>
			        </div>
			    </s:form>
			</div>
		</div>
	</div>
</body>
