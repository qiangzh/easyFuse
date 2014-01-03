<%@ include file="/common/taglibs.jsp" %>

<head>
    <title><fmt:message key="activeUsers.title"/></title>
    <meta name="menu" content="AdminMenu"/>
</head>
<body id="activeUsers">

<div class="col-sm-10">
    <h2><fmt:message key="activeUsers.heading"/></h2>
    <p><fmt:message key="activeUsers.message"/></p>
    
    <display:table name="applicationScope.userNames" id="user" cellspacing="0" cellpadding="0"
                   defaultsort="1" class="table table-condensed table-striped table-hover" pagesize="50" requestURI="">
        <display:column property="username" escapeXml="true" style="width: 30%" titleKey="user.username"
                        sortable="true"/>
        <display:column property="email" titleKey="user.email" sortable="true" autolink="true" media="html" />		
        <display:setProperty name="paging.banner.item_name" value="user"/>
        <display:setProperty name="paging.banner.items_name" value="users"/>
    </display:table>
</div>
</body>