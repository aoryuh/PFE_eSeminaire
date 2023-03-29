<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/jsp/struct/header.jsp"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<title>Page administrateur</title>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand nav-left" id="title"><b>Page administrateur</b></a>
    <sec:authorize access="isAuthenticated()">
        <a class="navbar-brand nav-right" href="/">Accueil</a>
        <a class="navbar-brand nav-right" href="/forum">forum</a>
        <a class="navbar-brand nav-right" href="/myTeam">Mon équipe de recherche</a>
        <a class="navbar-brand nav-right" href="/archive">Archives</a>
        <a class="navbar-brand nav-right" href="/logout">Déconnexion</a>
    </sec:authorize>
    <sec:authorize access="!isAuthenticated()">
        <a class="navbar-brand nav-right" href="/login">Connexion</a>
    </sec:authorize>
</nav>

<h2>ERREUR DE FORMAT DU FICHIER D'IMPORT DE SEMINAIRE :</h2>
<h3>${seminar.errorDescription}</h3>


</div>
</body>



<%@ include file="/WEB-INF/jsp/struct/footer.jsp"%>
