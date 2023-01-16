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

<html>
	<head>
	<meta charset="UTF-8">
	<title>eSeminaire</title>
	<link rel="stylesheet" href="${css}">
	<link rel="stylesheet" href="${bootstrap_css}">
</head>
<body>
