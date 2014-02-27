<%@ include file="/common/taglibs.jsp" %>

<head>
    <title><fmt:message key="role.add.title"/></title>
	<meta name="menu" content="AdminMenu" />
</head>

<body class="signup">

	<div class="span2">
	    <h2><fmt:message key="role.add.heading"/></h2>
	    <fmt:message key="role.add.message"/>
	</div>
	
	<div class="span7">
	    <s:form name="roleForm" action="/role/saveRole" method="post" validate="true" autocomplete="off" cssClass="well form-horizontal">
	    	<s:hidden key="role.id"/>	
	        <div class="row">
	            <div class="span6">
	                <s:textfield name="role.roleCode" key="role.code" required="true" cssClass="form-control"/>
	            </div>
	            <div class="span6">
	                <s:textfield name="role.roleName" key="role.name" cssClass="form-control"/>
	            </div>
	        </div>
	
	        <div id="actions" class="form-group form-actions">
	            <s:submit type="button" cssclass="btn btn-mini btn-primary" key="button.register" theme="simple">
	                <i class="icon-ok icon-white"></i>
	                <fmt:message key="button.register"/>
	            </s:submit>
	            <a href="./listRoles" class="btn btn-mini btn-default"><i class="icon-remove"></i><fmt:message key="button.cancel"/></a>
	        </div>
	    </s:form>
	</div>
</body>
