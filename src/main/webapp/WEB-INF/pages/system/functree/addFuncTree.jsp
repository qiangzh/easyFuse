<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp" %>

<head>
    <title><fmt:message key="signup.title"/></title>
</head>

<body class="signup">	
	<div>
	    <s:form name="userForm" action="/functree/saveFuncTree" method="post" validate="true" autocomplete="off" cssClass="well form-horizontal">
	    	<s:hidden key="funcTree.id"/>	
	    	<s:hidden key="treeNode.id"/>
	    	<s:hidden key="funcTree.sort"/>
	    	
	        <div class="row">
	            <div class="span6">
	        		<s:textfield key="funcTree.code" required="true" autofocus="true" cssClass="form-control"/>
	            </div>
	            <div class="span6">
	        		<s:textfield key="funcTree.name" required="true" autofocus="true" cssClass="form-control"/>
	            </div>
	        </div>
	        <s:radio name="funcTree.type" required="true" autofocus="true" list="#{'1':'功能菜单','0':'功能点'}"></s:radio>
	        <div class="row">
	            <div class="span12">
	        		<s:textfield key="funcTree.url" required="true" autofocus="true" cssClass="form-control"/>
	            </div>
	        </div>
	
	        <div id="actions" class="form-group form-actions">
	            <s:submit type="button" cssclass="btn btn-mini btn-primary" key="button.register" theme="simple">
	                <i class="icon-ok icon-white"></i>
	                <fmt:message key="button.register"/>
	            </s:submit>
	            <a href="./listFuncTrees" class="btn btn-mini btn-default"><i class="icon-remove"></i><fmt:message key="button.cancel"/></a>
	        </div>
	    </s:form>
	</div>
</body>
