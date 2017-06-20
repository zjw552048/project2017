<%@ page pageEncoding="utf-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<!-- 引入标签库 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>

<!-- 项目路径 -->
<c:set var="basePath" value="${pageContext.request.scheme }://${pageContext.request.serverName }:${pageContext.request.serverPort }${pageContext.request.contextPath }"/>
<!-- 静态资源路径 -->
<c:set var="staticPath" value="${basePath }/static"/>