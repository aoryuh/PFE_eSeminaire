<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/jsp/struct/header.jsp"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<title>Recherche</title>
</head>
<body>

<div id="myApp">
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand nav-left" id="title"><b>Accueil</b></a>
        <sec:authorize access="isAuthenticated()">
            <a class="navbar-brand nav-right" href="/forum">Forum</a>
            <a class="navbar-brand nav-right" href="/myTeam">Mon équipe de recherche</a>
            <sec:authorize access="hasAnyAuthority('ADMIN', 'RESPO')">
                <a class="navbar-brand nav-right" href="/admin">Gérer mon équipe</a>
            </sec:authorize>
            <a class="navbar-brand nav-right" href="/archive">Archives</a>
            <a class="navbar-brand nav-right" href="/logout">Déconnexion</a>
        </sec:authorize>

        <sec:authorize access="!isAuthenticated()">
            <a class="navbar-brand nav-right" href="/login">Connexion</a>
        </sec:authorize>
    </nav>
    </div>


    <div class="container">
        <div>
            <form method="get" action="/search">
                <label for="query">Recherche :</label>
                <input type="text" name="query" id="query">
                <button type="submit">Rechercher</button>
            </form>
        </div>

        <a>Aucun  séminaire ne correspond au caractéristiques saisies...</a>
    </div>
</div>

<%@ include file="/WEB-INF/jsp/struct/footer.jsp"%>