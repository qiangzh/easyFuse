<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<head>
<title><fmt:message key="orgList.title" /></title>
<meta name="menu" content="AdminMenu" />
<sx:head locale="zh" parseContent="true" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script language="javaScript" type="text/javascript">
	// dojo tree 加载页面后一次性展开树所有节点
	dojo.addOnLoad(function() {
		expandObj(dojo.widget.byId('orgTree'));//为下面标签的ID
	});

	function expandObj(obj) {
		if (obj) {
			if (obj.children) {
				for (var i = 0; i < obj.children.length; i++) {
					var childObj = obj.children[i];
					if (childObj) {
						try {
							childObj.expand();
						} catch (e) {
						}
						if (childObj.children) {
							expandObj(childObj);
						}
					}
				}
			}
		}
	}
	
	//点击树的节点时候触发的事件
	dojo.event.topic.subscribe("treeSelected", function treeNodeSelected(node) {
		dojo.io.bind({
			url : "<s:url value='/org/listSubOrgs'/>",
			content:{'treeNode.id':node.node.widgetId},
			load : function(type, data, evt) {
				document.getElementById('mainFrame').innerHTML=data; 
			},
			mimeType : "text/Html"
		});
	});
	

	
	//点击树的节点时候触发的事件
	dojo.event.topic.subscribe("treeExpanded", function treeNodeExpanded(node) {
		if(node.node.widgetId=='0'){
			var treeSelector = dojo.widget.manager.getWidgetById('treeSelector');
			treeSelector.sdoSelect(node.node);
		}
	});
	
	// mainFrame 添加跳转操作
	function addTreeNode(treeNodeID) {
		dojo.io.bind({
			url : "<s:url value='/org/addOrg'/>",
			content:{'treeNode.id':treeNodeID},
			load : function(type, data, evt) {
				document.getElementById('mainFrame').innerHTML=data; 
			},
			mimeType : "text/Html"
		});
	}
</script>
<div class="col-sm-10">
	<h2>组织机构</h2>
	
	<form method="get" action="${ctx}/org/queryOrgs" id="searchForm" class="form-inline">
		<div id="search" class="text-right">
			<span class="col-sm-9"> <input type="text" size="20" name="q"
				id="query" value="${param.q}"
				placeholder="<fmt:message key="search.enterTerms"/>"
				class="form-control input-sm" />
			</span>
			<button id="button.search" class="btn btn-default btn-sm"
				type="submit">
				<i class="icon-search"></i>
				<fmt:message key="button.search" />
			</button>
		</div>
	</form>

	<div class="row">
		<div class="col-sm-3" id="leftFrame">
			<sx:tree id="orgTree" rootNode="treeRootNode"
				childCollectionProperty="children" nodeIdProperty="id"
				nodeTitleProperty="name" value="id"
				treeSelectedTopic="treeSelected" expandedNotifyTopics="treeExpanded" >
			</sx:tree>
		</div>
		<div class="col-sm-7" id="mainFrame">
			<h2>Left Frame</h2>
		</div>
	</div>
</div>
