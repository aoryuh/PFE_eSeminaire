<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="v-on" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="v-bind" uri="http://www.springframework.org/tags" %>

<c:url var="bootstrap_css"
	   value="/webjars/bootstrap/5.2.0/css/bootstrap.min.css" />
<c:url var="bootstrap_js"
	   value="/webjars/bootstrap/5.2.0/js/bootstrap.min.js" />
<c:url var="jquery_js" value="/webjars/jquery/3.5.1/jquery.min.js" />
<c:url var="css" value="/style.css" />
<c:url var="vue_js" value="/webjars/vue/3.2.19/dist/vue.global.js" />
<c:url var="axios_js" value="/webjars/axios/0.22.0/dist/axios.min.js" />

<html>
	<head>
	<meta charset="UTF-8">
	<title>CV MANAGER</title>
	<link rel="stylesheet" href="${css}">
	<link rel="stylesheet" href="${bootstrap_css}">
	<script src="${jquery_js}"></script>
	<script src="${bootstrap_js}"></script>
	<script src="${vue_js}"></script>
	<script src="${axios_js}"></script>
</head>
<body>
<h1 style="text-align: center">CV MANAGER</h1>
