<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<head>
	<title><fmt:message key="funcTree.list.title" /></title>
</head>
<div class="span15">	
	<div id="actions" class="btn-group">
		<a class="btn btn-mini btn-primary" onclick="addTreeNode('${treeNode.id}');"> 
			<i class="icon-plus icon-white"></i>
			<fmt:message key="button.add" />
		</a>
	</div>
	
	<display:table id="funcTree" name="funcTrees" requestURI="" defaultsort="1" pagesize="10" 
		class="table table-condensed table-striped table-hover table-bordered" export="false">	
        <display:column title="序号">${funcTree_rowNum}</display:column> 
		<display:column property="code" titleKey="funcTree.name" sortable="true" escapeXml="true"/>
		<display:column property="name" titleKey="funcTree.code" sortable="true" autolink="true" media="html" />
		<display:column title="" media="html">
			<a onclick="editTreeNode('${treeNode.id}','${funcTree.id}');">修改</a>
        </display:column>
		<display:setProperty name="paging.banner.item_name">功能</display:setProperty>
		<display:setProperty name="paging.banner.items_name">功能</display:setProperty>
	</display:table>
</div>
