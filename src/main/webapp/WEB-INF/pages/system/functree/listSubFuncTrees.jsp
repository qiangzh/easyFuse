<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<head>
	<title><fmt:message key="funcTree.list.title" /></title>
</head>
<div>
	<c:if test="${'-1' ne funcTree.id}">
	<div class="panel panel-info">
		<div class="panel-body">
		    <s:form name="userForm" action="/functree/saveFuncTree" method="post" validate="true" autocomplete="off" cssClass="well form-horizontal">
		    	<div class="row">
		            <div class="span5">
		        		<s:textfield key="funcTree.code" disabled="true" cssClass="form-control"/>
		            </div>
		            <div class="span5">
		        		<s:textfield key="funcTree.name" disabled="true" cssClass="form-control"/>
		            </div>
		            <div class="span2 btn-group">
			            <a class="btn btn-mini btn-primary" onclick="editTreeNode('${funcTree.parentID}','${funcTree.id}');">修改</a>
			       </div>
		        </div>	
		    </s:form>
		</div>
	</div>
	</c:if>
	    
	<div id="actions" class="btn-group">
		<a class="btn btn-mini btn-primary" onclick="addTreeNode('${treeNode.id}');"> 
			<i class="icon-plus icon-white"></i>
			<fmt:message key="button.add" />
		</a>
	</div>
		
	<display:table id="funcTree" name="funcTrees" requestURI="" defaultsort="1" pagesize="10" 
		class="table table-condensed table-striped table-hover table-bordered" export="false">	
        <display:column title="序号">${funcTree_rowNum}</display:column> 
		<display:column property="code" titleKey="funcTree.code" sortable="false" escapeXml="true"/>
		<display:column property="name" titleKey="funcTree.name" sortable="false" autolink="true" media="html" />
		<display:column title="" media="html">
			<a onclick="editTreeNode('${treeNode.id}','${funcTree.id}');">修改</a>
        </display:column>
		<display:setProperty name="paging.banner.item_name">功能</display:setProperty>
		<display:setProperty name="paging.banner.items_name">功能</display:setProperty>
	</display:table>
</div>
