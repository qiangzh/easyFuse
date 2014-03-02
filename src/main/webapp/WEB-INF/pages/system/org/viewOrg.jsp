<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp" %>

<head>
    <title><fmt:message key="signup.title"/></title>
</head>

<body class="signup">	
	<div>
	    <s:form name="userForm" action="/org/saveOrg" method="post" validate="true" autocomplete="off" cssClass="well form-horizontal">
	    	<s:hidden key="org.id"/>
	    		    	
	        <div class="row">
	            <div class="span6">
	        		<s:textfield key="org.code"  disabled="true" cssClass="form-control"/>
	            </div>
	            <div class="span6">
	        		<s:textfield key="org.name" disabled="true" cssClass="form-control"/>
	            </div>
	        </div>
	
	        <div id="actions" class="form-group form-actions">
				<a class="btn btn-mini btn-primary" onclick="addTreeNode('${org.id}');"><i class="icon-plus icon-white"></i>设立下级</a>
				<a class="btn btn-mini btn-primary" onclick="editTreeNode('${org.id}');">修改</a>	
				<c:if test="${'-1' ne org.parentID}">
					<a class="btn btn-mini btn-primary" onclick="deleteTreeNode('${org.id}');">删除</a>
				</c:if>
			</div>
	    </s:form>
	</div>
</body>
