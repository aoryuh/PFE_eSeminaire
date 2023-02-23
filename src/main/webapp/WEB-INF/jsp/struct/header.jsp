<!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:url var="bootstrap_css"
	   value="/webjars/bootstrap/5.2.0/css/bootstrap.min.css" />
<c:url var="bootstrap_js"
	   value="/webjars/bootstrap/5.2.0/js/bootstrap.min.js" />
<c:url var="jquery_js" value="/webjars/jquery/3.5.1/jquery.min.js" />


<html>
<head>
	<meta charset="UTF-8">
	<title>eSeminaire</title>
	<link rel="stylesheet" href="${bootstrap_css}">
</head>
<style>


	.navbar-brand{
		padding: 20px;
		border: black 10px;
	}
	.adminSplit{
		float:left;
		width:45%;
		margin: 2%;
	}
	.firstname{
		text-transform: capitalize;
	}
	.lastname{
			 text-transform: uppercase;
		 }
</style>
<body>
<%@ page contentType="text/html; charset=UTF-8" %>

