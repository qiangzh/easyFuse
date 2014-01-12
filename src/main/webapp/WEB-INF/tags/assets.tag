<%@ tag body-content="empty" %>
<%@ attribute name="group" required="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="base" value="${pageContext.request.contextPath}"/>
<c:if test="${empty group}"><c:set var="group" value="main"/></c:if>
<c:if test="${not empty param.debug}">
    <c:set var="debugAssets" value="${param.debug}" scope="session"/>
</c:if>
<c:set var="debugAssets" value="true" scope="session"/>
<c:choose>
    <c:when test="${sessionScope.debugAssets}">
	    <link rel="stylesheet" type="text/css" media="all" href="<c:url value='/styles/lib/bootstrap-2.2.1.css'/>" />    
	    <link rel="stylesheet" type="text/css" media="all" href="<c:url value='/styles/lib/bootstrap-responsive-2.2.1.css'/>" />
	    <link rel="stylesheet" type="text/css" media="all" href="<c:url value='/styles/style.css'/>" /> 
	    	    
	    <script type="text/javascript" src="<c:url value='/scripts/lib/jquery-1.8.2.min.js'/>"></script>
	    <script type="text/javascript" src="<c:url value='/scripts/lib/bootstrap-2.2.1.min.js'/>"></script>
	    <script type="text/javascript" src="<c:url value='/scripts/lib/plugins/jquery.cookie.js'/>"></script>
	    <script type="text/javascript" src="<c:url value='/scripts/script.js'/>"></script>
	</c:when>
    <c:otherwise>
        <link rel="stylesheet" type="text/css" href="${base}/assets/scripts/${group}.css"/>
        <script type="text/javascript" src="${base}/assets/scripts/${group}.js"></script>
    </c:otherwise> 
</c:choose>