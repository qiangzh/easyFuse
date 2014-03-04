<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp" %>

<head>
    <title><fmt:message key="signup.title"/></title>
</head>

<body class="signup">	
	<div>
	    <s:form name="userForm" action="/org/saveOrg" method="post" validate="true" autocomplete="off" cssClass="well form-horizontal">
	    	<s:hidden key="org.id"/>	
	    	<s:hidden key="org.parentID"/>
	    	<s:hidden key="org.status"/>
	    	
	        <div class="row">
	            <div class="span6">
	        		<s:textfield key="org.code" cssClass="form-control" required="true" maxLength="10"/>
	            </div>
	            <div class="span6">
	        		<s:textfield key="org.name" cssClass="form-control" required="true" maxLength="32"/>
	            </div>
	        </div>
	
	        <div id="actions" class="form-group form-actions">
	            <s:submit type="button" cssclass="btn btn-mini btn-primary" theme="simple">
	                <i class="icon-ok icon-white"></i>确定
	            </s:submit>
	            <a href="./listOrgs" class="btn btn-mini btn-default"><i class="icon-remove"></i><fmt:message key="button.cancel"/></a>
	        </div>
	    </s:form>
	</div>
</body>
