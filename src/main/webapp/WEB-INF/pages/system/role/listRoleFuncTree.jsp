<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<head>
	<title><fmt:message key="funcTree.list.title" /></title>
	<meta name="menu" content="AdminMenu" />
	<sx:head locale="zh" parseContent="true" />
	<script language="javaScript" type="text/javascript">
		// dojo tree 加载页面后一次性展开树所有节点
		dojo.addOnLoad(function() {
			expandObj(dojo.widget.byId('funcTree'));//为下面标签的ID
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
			var roleID = document.getElementById("roleFuncTreeVO.roleID").value;
			dojo.io.bind({
				url : "<s:url value='/role/setRolePermission'/>",
				content : {'roleFuncTreeVO.funcTreeID' : node.node.widgetId,'roleFuncTreeVO.roleID' : roleID},
				load : function(type, data, evt) {
					document.getElementById('mainFrame').innerHTML = data;
				},
				mimeType : "text/Html",
			    preventCache:true
			});
		}
	
		//点击树的节点时候触发的事件
		dojo.event.topic.subscribe("treeSelected", function treeNodeSelected(node) {
			var roleID = document.getElementById("roleFuncTreeVO.roleID").value;
			dojo.io.bind({
				url : "<s:url value='/role/setRolePermission'/>",
				content : {'roleFuncTreeVO.funcTreeID' : node.node.widgetId,'roleFuncTreeVO.roleID' : roleID},
				load : function(type, data, evt) {
					document.getElementById('mainFrame').innerHTML = data;
				},
				mimeType : "text/Html",
			    preventCache:true
			});
		});
	
		//点击树的节点时候触发的事件
		dojo.event.topic.subscribe("treeExpanded", function treeNodeExpanded(node) {
			if (node.node.widgetId == '${roleFuncTreeVO.funcTreeID}') {
				var tree = dojo.widget.byId('funcTree');
				var selector = tree.selector; 
				selector.doSelect(node.node);
				treeNodeSelected1(node);
			}
		});
	</script>
</head>
<body>
	<div class="container-fluid">
		<h2><fmt:message key="funcTree.list.heading" /></h2>
		<s:hidden id="roleFuncTreeVO.roleID" key="roleFuncTreeVO.roleID" />
		
		<div class="row-fluid">
			<div id="actions">
				<a href="./listRoles" class="btn btn-mini btn-default"><i
					class="icon-remove"></i> <fmt:message key="button.cancel" /></a>
			</div>
		</div>
		
		<div class="row-fluid">
			<div class="span2" id="leftFrame">
				<sx:tree id="funcTree" rootNode="treeRootNode" 
					childCollectionProperty="children" nodeIdProperty="id"
					nodeTitleProperty="name" value="id"
					treeSelectedTopic="treeSelected"
					expandedNotifyTopics="treeExpanded">
				</sx:tree>
			</div>
			<div class="span10" id="mainFrame"></div>
		</div>
	</div>
</body>
