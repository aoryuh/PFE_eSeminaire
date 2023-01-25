<!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:url var="bootstrap_css"
	   value="/webjars/bootstrap/5.2.0/css/bootstrap.min.css" />
<c:url var="bootstrap_js"
	   value="/webjars/bootstrap/5.2.0/js/bootstrap.min.js" />
<c:url var="jquery_js" value="/webjars/jquery/3.5.1/jquery.min.js" />


<html>
<head>
	<title>eSeminaire</title>
	<link rel="stylesheet" href="${bootstrap_css}">
</head>
<style>


	.navbar-brand{
		padding: 20px;
	}

	.navbar{
		width: 100%;
		border: black 5px solid;
	}

	.adminSplit{
		float:left;
		width:45%;
		margin: 2%;
	}

	body{
		margin: 0;
	}
</style>
<body>


