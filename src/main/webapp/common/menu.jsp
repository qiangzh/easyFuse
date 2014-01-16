<%@ include file="/common/taglibs.jsp"%>

<menu:useMenuDisplayer name="Velocity" config="navbarMenu.vm" permissions="rolesAdapter">
<div class="nav-collapse collapse">
<ul class="nav">
    <c:if test="${empty pageContext.request.remoteUser}"></c:if>
    <menu:displayMenu name="Home"/>
    <menu:displayMenu name="StaffMenu"/>    
    <menu:displayMenu name="DecorateMenu"/>
    <menu:displayMenu name="FundMenu"/>
    <menu:displayMenu name="MaterialMenu"/>
    <menu:displayMenu name="ReportMenu"/>
    <menu:displayMenu name="MarketMenu"/>
    <menu:displayMenu name="SystemMenu"/>
    <menu:displayMenu name="Logout"/>
    <c:forEach var="menu" items="${repository.topMenus}">
    	<menu:displayMenu name="${menu.name}"/>
    </c:forEach>   
</ul>
</div>
</menu:useMenuDisplayer>