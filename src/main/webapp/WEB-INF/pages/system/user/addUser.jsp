<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp" %>

<head>
    <title><fmt:message key="signup.title"/></title>
	<meta name="menu" content="AdminMenu" />
	<sx:head locale="zh" parseContent="true" />
</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span2">			    
				<c:choose> 
					<c:when test="${empty user.id}">
			    <h2><fmt:message key="signup.heading"/></h2>
					</c:when>
					<c:otherwise>
				    	<h2>编辑用户信息</h2>
				    </c:otherwise>
			   	</c:choose>
			    <fmt:message key="signup.message"/>
			</div>
			
			<div class="span10">
			    <s:form name="userForm" action="/user/saveUser" method="post" validate="true" autocomplete="off" cssClass="well form-horizontal">
			    	<s:hidden key="user.id"/>	
			    	<s:hidden key="user.enabled" value="true"/>
			    	<s:hidden key="backUrl"/>
			    	    
			        <div class="row">
			            <div class="span6">	
			        		<s:textfield key="user.username" required="true" autofocus="true" cssClass="form-control"/>
			        	</div>
			        </div>	 
			               	
			        <div class="row">
			            <div class="span6">
			                <s:password key="user.password" showPassword="true" required="true" cssClass="form-control"/>
			            </div>
			            <div class="span6">
			                <s:password key="user.confirmPassword" required="true" showPassword="true" cssClass="form-control"/>
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
					<div class="row">
						<div class="span8">
							<s:optiontransferselect label="设置用户角色" name="leftRoles"
								list="leftRoleList"
								listKey="id"
								listTitle="roleCode"
								listValue="roleName"
								doubleName="rightRoles"	
								doubleList="rightRoleList"
								doubleListKey="id"
								doubleListTitle="roleCode"	
								doubleListValue="roleName"
								allowUpDownOnLeft="false" allowUpDownOnRight="false"/>
						</div>
					</div>		
					<div id="actions" class="form-group form-actions">
			            <s:submit type="button" cssclass="btn btn-mini btn-primary" key="button.register" theme="simple">
			                <i class="icon-ok icon-white"></i>
			                <fmt:message key="button.register"/>
			            </s:submit>
			            <a href="./listUsers" class="btn btn-mini btn-default"><i class="icon-remove"></i><fmt:message key="button.cancel"/></a>
			        </div>
			    </s:form>
			</div>
		</div>
	</div>
</body>
