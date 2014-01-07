<%@ include file="/common/taglibs.jsp"%>

<head>
	<title><fmt:message key="orgList.title" /></title>
</head>

<script language="javaScript" type="text/javascript">
</script>

<div class="col-sm-10">	
	<div id="actions" class="btn-group">
		<a class="btn btn-primary" onclick="addTreeNode('${treeNode.id}');"> 
			<i class="icon-plus icon-white"></i>
			<fmt:message key="button.add" />
		</a>
	</div>

	<display:table name="orgs" cellspacing="0" cellpadding="0" 
	    requestURI="" defaultsort="1" id="orgs" pagesize="25"
		class="table table-condensed table-striped table-hover" export="true">
		<display:column property="code" titleKey="org.name" sortable="true" escapeXml="true" url="/org/editOrg?from=list" paramId="id" paramProperty="id" />
		<display:column property="name" titleKey="org.code" sortable="true" autolink="true" media="html" />
	</display:table>
</div>
