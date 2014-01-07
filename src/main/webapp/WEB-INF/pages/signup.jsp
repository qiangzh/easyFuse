<%@ include file="/common/taglibs.jsp" %>

<head>
    <title><fmt:message key="signup.title"/></title>
</head>

<body class="signup">

	<div class="col-sm-2">
	    <h2><fmt:message key="signup.heading"/></h2>
	    <fmt:message key="signup.message"/>
	</div>
	
	<div class="col-sm-7">
	    <s:form name="userForm" action="saveSignup" method="post" validate="true" autocomplete="off" cssClass="well">
	    	<s:hidden key="user.enabled" value="true"/>
	    	
	        <s:textfield key="user.username" required="true" autofocus="true" cssClass="form-control"/>
	
	        <div class="row">
	            <div class="col-sm-6">
	                <s:password key="user.password" showPassword="true" required="true" cssClass="form-control"/>
	            </div>
	            <div class="col-sm-6">
	                <s:password key="user.confirmPassword" required="true" showPassword="true" cssClass="form-control"/>
	            </div>
	        </div>
	
	        <div class="row">
	            <div class="col-sm-6">
	                <s:textfield key="user.email" required="true" cssClass="form-control"/>
	            </div>
	            <div class="col-sm-6">
	                <s:textfield key="user.phoneNumber" cssClass="form-control"/>
	            </div>
	        </div>
	
	        <div id="actions" class="form-group form-actions">
	            <s:submit type="button" cssClass="btn btn-primary" key="button.register" theme="simple">
	                <i class="icon-ok icon-white"></i>
	                <fmt:message key="button.register"/>
	            </s:submit>
	            <a href="./" class="btn btn-default"><i class="icon-remove"></i><fmt:message key="button.cancel"/></a>
	        </div>
	    </s:form>
	</div>
</body>
