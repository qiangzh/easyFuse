<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="home.title"/></title>
    <meta name="menu" content="Home"/>
</head>
<body>
	<div class="container-fluid">
		<h2><fmt:message key="home.heading"/></h2>
		<div class="row-fluid">	
			<p><fmt:message key="home.message"/></p>
			<ul class="glassList">
				<li>
					<a href="<c:url value='/user/editUserProfile'/>"><fmt:message key="menu.user"/></a>
				</li>
			</ul>
		</div>
	</div>
</body>