<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<head>
	<title><fmt:message key="orgList.title" /></title>
	<script language="javaScript" type="text/javascript">
	</script>
</head>
<div class="span15">	
	<div id="actions" class="btn-group">
		<a class="btn btn-primary" onclick="addTreeNode('${treeNode.id}');"> 
			<i class="icon-plus icon-white"></i>
			<fmt:message key="button.add" />
		</a>
	</div>

	<display:table name="orgs" cellspacing="0" cellpadding="0" 
	    requestURI="" defaultsort="1" id="org" pagesize="25"
		class="table table-condensed table-striped table-hover" export="true">
		<display:column property="code" titleKey="org.name" sortable="true" escapeXml="true" />
		<display:column property="name" titleKey="org.code" sortable="true" autolink="true" media="html" />
		<display:column title="" media="html">
			<a onclick="editTreeNode('${treeNode.id}','${org.id}');">修改</a>
        </display:column>
	</display:table>
</div>
