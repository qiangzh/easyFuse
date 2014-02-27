<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html>
<%@ include file="/common/taglibs.jsp"%>
<html lang="en">
<head>
	<meta http-equiv="Cache-Control" content="no-store" />
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="icon" href="<c:url value="/images/favicon.ico"/>" />
	<t:assets />
	<title><fmt:message key="login.title" /></title>
</head>

<body id="login">
	<div class="container">
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span6 offset3">
					<form method="post" id="loginForm" action="<c:url value='/j_security_check'/>" class="form-signin" autocomplete="off">
						<h2 class="form-signin-heading">帐号登录</h2>	
						
						<c:if test="${param.error != null}">
				        <div class="row">
				        	<div class="offset1">
								<div class="alert alert-error fade in">
									<fmt:message key="errors.password.mismatch" />
								</div>
							</div>
						</div>
						</c:if>
						
				        <div class="row">
				            <div class="span3 offset1">
				            	<label for="j_username" class="form-control">用户名</label>
				            </div>	
				            <div class="span8">			          
				            	<input type="text" name="j_username" id="j_username" class="form-control"
				            		placeholder="<fmt:message key="label.username"/>" required tabindex="1">
				        	</div>
				        </div>	
				        <div class="row">
				            <div class="span3 offset1">
								<label for="j_username" class="form-control">密码</label>
				            </div>				            
				            <div class="span8">	
								<input type="password" name="j_password" id="j_password" class="form-control"
									placeholder="<fmt:message key="label.password"/>" required tabindex="2">
				            </div>
				        </div>	
				        
						<c:if test="${appConfig['rememberMeEnabled']}">
				        <div class="row">
				            <div class="offset1">
				            	<label class="checkbox" for="rememberMe"> 
					            	<input type="checkbox" class="checkbox" name="_spring_security_remember_me" id="rememberMe" tabindex="3" />
									<fmt:message key="login.rememberMe" />
								</label>
				            </div>
				        </div>	
						</c:if>
						
				        <div class="row-fluid">
				            <div class="offset5 span2">
								<button type="submit" class="btn btn-block btn-lg" name="login" tabindex="4">
									<fmt:message key='button.login' />
								</button>
							</div>
				        </div>	
					</form>
				</div>
			</div>
		</div>
	</div>
</body>