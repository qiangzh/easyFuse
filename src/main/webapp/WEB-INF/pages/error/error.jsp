<%@ page language="java" isErrorPage="true" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>

<html>
<head>
    <title><fmt:message key="errorPage.title"/></title>
</head>
<body id="error">
    <div class="container">
        <h1><fmt:message key="errorPage.heading"/></h1>
        <%@ include file="/common/messages.jsp" %>

        <p><fmt:message key="errorPage.message"/></p>
                    请点击此处<s:a href="javascript:window.history.back(-1);">返回上页</s:a>
    </div>
</body>
</html>
