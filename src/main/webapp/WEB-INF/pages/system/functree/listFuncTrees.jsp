<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<head>
	<title><fmt:message key="funcTree.list.title" /></title>
	<meta name="menu" content="AdminMenu" />
	<sx:head locale="zh" parseContent="true" />
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
		
		function treeNodeSelected1(node) {
			dojo.io.bind({
				url : "<s:url value='/functree/listSubFuncTrees'/>",
				content : {'treeNode.id' : node.node.widgetId},
				load : function(type, data, evt) {
					document.getElementById('mainFrame').innerHTML = data;
				},
				mimeType : "text/Html",
			    preventCache:true
			});
		}
	
		//点击树的节点时候触发的事件
		dojo.event.topic.subscribe("treeSelected", function treeNodeSelected(node) {
			dojo.io.bind({
				url : "<s:url value='/functree/listSubFuncTrees'/>",
				content : {'treeNode.id' : node.node.widgetId},
				load : function(type, data, evt) {
					document.getElementById('mainFrame').innerHTML = data;
				},
				mimeType : "text/Html",
			    preventCache:true
			});
		});
	
		//点击树的节点时候触发的事件
		dojo.event.topic.subscribe("treeExpanded", function treeNodeExpanded(node) {
			if (node.node.widgetId == '${treeNode.id}') {
				var tree = dojo.widget.byId('orgTree');
				var selector = tree.selector; 
				selector.doSelect(node.node);
				treeNodeSelected1(node);
			}
		});
	
		// mainFrame 添加跳转操作
		function addTreeNode(treeNodeID) {
			dojo.io.bind({
				url : "<s:url value='/functree/addFuncTree'/>",
				content : {'treeNode.id' : treeNodeID},
				load : function(type, data, evt) {
					document.getElementById('mainFrame').innerHTML = data;
				},
				mimeType : "text/Html",
			    preventCache:true
			});
		}
	
		// mainFrame 编辑跳转操作
		function editTreeNode(treeNodeID, childNodeID) {
			dojo.io.bind({
				url : "<s:url value='/functree/editFuncTree'/>",
				content : {'treeNode.id' : treeNodeID,'id' : childNodeID},
				load : function(type, data, evt) {
					document.getElementById('mainFrame').innerHTML = data;
				},
				mimeType : "text/Html",
			    preventCache:true
			});
		}
	</script>
</head>
<body>
	<div class="container-fluid">
		<h2><fmt:message key="funcTree.list.heading" /></h2>
		<div class="row-fluid">
			<div class="span2" id="leftFrame">
				<sx:tree id="orgTree" rootNode="treeRootNode"
					childCollectionProperty="children" nodeIdProperty="id"
					nodeTitleProperty="name" value="id"
					treeSelectedTopic="treeSelected"
					expandedNotifyTopics="treeExpanded">
				</sx:tree>
			</div>
			<div class="span7" id="mainFrame"></div>
		</div>
	</div>
</body>
