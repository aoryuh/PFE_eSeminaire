<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ include file="/WEB-INF/jsp/struct/header.jsp"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<title>Accueil</title>
</head>
<body>

<div id="myApp">
    <div class="container">
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <sec:authorize access="!isAuthenticated()">
                <a class="navbar-brand" href="/login">Connexion</a>
            </sec:authorize>
            <sec:authorize access="isAuthenticated()">
                <a class="navbar-brand" href="/logout">Déconnexion</a>
                <a class="navbar-brand" href="">Mon équipe de recherche</a>
                <a class="navbar-brand" href="">Forum</a>
            </sec:authorize>
            <sec:authorize access="hasAuthority('ADMIN')">
                <a class="navbar-brand" href="">Gérer mon équipe</a>
            </sec:authorize>
        </nav>
    </div>
</div>



<%@ include file="/WEB-INF/jsp/struct/footer.jsp"%>