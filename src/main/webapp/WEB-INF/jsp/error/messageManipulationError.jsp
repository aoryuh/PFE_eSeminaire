<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/jsp/struct/header.jsp"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<title>Erreur : manipulations non autorisée</title>
</head>
<body>

<div id="myApp">
    <div class="container">
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <a class="navbar-brand nav-left" id="title"><b>Forum</b></a>
            <sec:authorize access="isAuthenticated()">
                <a class="navbar-brand nav-right" href="/">Accueil</a>
                <a class="navbar-brand nav-right" href="/myTeam">Mon équipe de recherche</a>
                <sec:authorize access="hasAnyAuthority('ADMIN')">
                    <a class="navbar-brand nav-right" href="/admin">Gérer mon équipe</a>
                </sec:authorize>
                <a class="navbar-brand nav-right" href="/archive">Tous les séminaires</a>
                <a class="navbar-brand nav-right" href="/logout">Déconnexion</a>
            </sec:authorize>

            <sec:authorize access="!isAuthenticated()">
                <a class="navbar-brand nav-right" href="/login">Connexion</a>
            </sec:authorize>
        </nav>
    </div>

    <div class="container">
        <h1>Erreur : modification/suppression de message non autorisée</h1>
        <p>Vous tentez de modifier/supprimer un message qui ne vous appartient pas.</p>
        <p>Veuillez retourner à la page précédente et sélectionner un message qui vous appartient pour effectuer des manipulations.</p>
    </div>

 </div>


 <%@ include file="/WEB-INF/jsp/struct/footer.jsp"%>