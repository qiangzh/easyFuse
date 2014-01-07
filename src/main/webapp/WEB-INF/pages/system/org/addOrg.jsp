<%@ include file="/common/taglibs.jsp" %>

<head>
    <title><fmt:message key="signup.title"/></title>
</head>

<body class="signup">	
	<div class="col-sm-7">
	    <s:form name="userForm" action="/org/saveOrg" method="post" validate="true" autocomplete="off" cssClass="well">
	    	<s:hidden key="org.id"/>	
	    	<s:hidden key="treeNode.id"/>
	    	
	        <div class="row">
	            <div class="col-sm-6">
	        		<s:textfield key="org.name" required="true" autofocus="true" cssClass="form-control"/>
	            </div>
	            <div class="col-sm-6">
	        		<s:textfield key="org.code" required="true" autofocus="true" cssClass="form-control"/>
	            </div>
	        </div>
	
	        <div id="actions" class="form-group form-actions">
	            <s:submit type="button" cssClass="btn btn-primary" key="button.register" theme="simple">
	                <i class="icon-ok icon-white"></i>
	                <fmt:message key="button.register"/>
	            </s:submit>
	            <a href="./listOrgs" class="btn btn-default"><i class="icon-remove"></i><fmt:message key="button.cancel"/></a>
	        </div>
	    </s:form>
	</div>
</body>
