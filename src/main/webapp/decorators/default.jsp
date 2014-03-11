<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<!DOCTYPE html>
<%@ include file="/common/taglibs.jsp"%>
<html lang="en">
<head>
    <meta http-equiv="Cache-Control" content="no-store"/>
    <meta http-equiv="Pragma" content="no-cache"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="<c:url value="/images/favicon.ico"/>"/>
    <title><decorator:title/></title>
    <t:assets/>
    <decorator:head/>
    <sb:head/>
</head>
<body<decorator:getProperty property="body.id" writeEntireProperty="true"/><decorator:getProperty property="body.class" writeEntireProperty="true"/>>
    <c:set var="currentMenu" scope="request"><decorator:getProperty property="meta.menu"/></c:set>
    <div class="navbar navbar-fixed-top">
        <div class="navbar-inner">
            <div class="container-fluid">
                <%-- For smartphones and smaller screens --%>
                <button type="button" class="btn btn-mini btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <%@ include file="/common/menu.jsp" %>
            </div>
        </div>
    </div>

    <div class="container-fluid">
        <%@ include file="/common/messages.jsp" %>
        <div class="row-fluid">
            <decorator:body/>
        </div>
    </div>
    
    <div id="footer" class="navbar navbar-fixed-bottom">
        <span class="left"><fmt:message key="webapp.version"/>
            <c:if test="${pageContext.request.remoteUser != null}">
            | <fmt:message key="user.status"/> ${pageContext.request.remoteUser}
            </c:if>
        </span>
        <span class="right">
            &copy; <fmt:message key="copyright.year"/>
        </span>
    </div>
<%= (request.getAttribute("scripts") != null) ?  request.getAttribute("scripts") : "" %>
</body>
</html>
