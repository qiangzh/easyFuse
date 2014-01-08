<%@ include file="/common/taglibs.jsp" %>

<head>
    <title><fmt:message key="userProfile.title"/></title>
    <meta name="menu" content="UserMenu"/>
</head>

<div class="span2">
    <h2><fmt:message key="userProfile.heading"/></h2>
    <p><fmt:message key="userProfile.message"/></p>
</div>
<div class="span7">
    <s:form name="userForm" action="/user/saveUserProfile" method="post" validate="true" cssClass="well form-horizontal" autocomplete="off">
        <s:hidden key="user.id"/>
        <s:hidden key="user.username"/>
        <s:hidden name="user.enabled"/>
        <s:hidden name="user.accountExpired"/>
        <s:hidden name="user.accountLocked"/>
        <s:hidden name="user.credentialsExpired"/>
        <input type="hidden" name="from" value="${param.from}"/>
        
        <div class="row">
	        <div class="span6">
	        	<s:textfield key="user.username" disabled="true" autofocus="true" cssClass="form-control"/>
	        </div>
        </div>  
        
        <div class="row">
            <div class="span6">
                <s:password key="user.password" showPassword="true" required="true" onchange="passwordChanged(this)" cssClass="form-control"/>
            </div>
            <div class="span6">
                <s:password key="user.confirmPassword" required="true" cssClass="form-control" showPassword="true" onchange="passwordChanged(this)"/>
            </div>
        </div>     

        <div class="row">
            <div class="span6">
                <s:textfield key="user.email" required="true" cssClass="form-control"/>
            </div>
            <div class="span6">
                <s:textfield key="user.phoneNumber" cssClass="form-control"/>
            </div>
        </div>
        <div id="actions" class="form-group form-actions">
            <s:submit type="button" cssClass="btn btn-primary" method="saveUserProfile" key="button.save" theme="simple">
                <fmt:message key="button.save"/>
            </s:submit>
            <s:submit type="button" cssClass="btn btn-default" method="cancel" key="button.cancel" theme="simple">
                <fmt:message key="button.cancel"/>
            </s:submit>
        </div>
        </s:form>
    </div>

    <c:set var="scripts" scope="request">
    <script type="text/javascript">
        function passwordChanged(passwordField) {
            if (passwordField.name == "user.password") {
                var origPassword = "<s:property value="user.password"/>";
            } else if (passwordField.name == "user.confirmPassword") {
                var origPassword = "<s:property value="user.confirmPassword"/>";
            }

            if (passwordField.value != origPassword) {
                createFormElement("input", "hidden", "encryptPass", "encryptPass",
                        "true", passwordField.form);
            }
        }
    </script>
    </c:set>
