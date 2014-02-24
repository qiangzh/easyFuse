<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp" %>

<head>
    <title><fmt:message key="signup.title"/></title>
</head>

<body class="signup">	
	<div>
	    <s:form name="roleForm" action="/role/saveRolePermission" method="post" validate="true" autocomplete="off" cssClass="well form-horizontal">
	    	<s:hidden key="roleFuncTreeVO.id"/>	
	    	<s:hidden key="roleFuncTreeVO.roleID"/>
	    	<s:hidden key="roleFuncTreeVO.funcTreeID"/>
	    		    	
	        <div class="row">
	            <div class="span6">
	            	<s:radio name="roleFuncTreeVO.hasPermission" listKey="label" listValue="value" required="true" autofocus="true" list="chkRolelist"></s:radio>	            
				</div>
	        </div>	
	        <div id="actions" class="form-group form-actions">
	            <s:submit type="button" cssClass="btn btn-primary" key="button.register" theme="simple">
	                <i class="icon-ok icon-white"></i>
	                <fmt:message key="button.register"/>
	            </s:submit>
	        </div>
	    </s:form>
	</div>
</body>
