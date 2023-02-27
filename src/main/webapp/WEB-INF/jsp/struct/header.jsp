<!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:url var="bootstrap_css"
	   value="/webjars/bootstrap/5.2.0/css/bootstrap.min.css" />
<c:url var="bootstrap_js"
	   value="/webjars/bootstrap/5.2.0/js/bootstrap.min.js" />


<html>
<head>
	<meta charset="UTF-8">
	<title>eSeminaire</title>
	<link rel="stylesheet" href="${bootstrap_css}">
</head>
<style>

	body{
		margin: 0;
	}

	.dele

	.container
	{
		padding: 8px;
	}
	.navbar-nav{
		display: flex;
		border-bottom: 2px solid black;
		width: 100%;


	}
	.navbar-brand{
		float: left;
		padding-bottom:16px;
		text-align: center;
		font-size: 17px;
		width: max-content;
		margin: fill;
		flex-grow: 1;
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
	.semListTd{
		padding: 5px 20px;
		border: 2px;
	}
	.message{
		padding: 10px;
		border-bottom: 1px solid #ddd;
	}
	.message:nth-child(even){
		background-color: #eee
	}
	#myApp {
		width: 100%;
	}
</style>
<body>
<%@ page contentType="text/html; charset=UTF-8" %>

