<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/jsp/struct/header.jsp"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<title>Page administrateur</title>
</head>
<body>

<div class="myApp">
    <div class="container">
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <ul class="navbar-nav">
                <a class="navbar-brand nav-left">Page administrateur</a>
                <sec:authorize access="isAuthenticated()">
                    <a class="navbar-brand nav-right" href="/">Accueil</a>
                    <a class="navbar-brand nav-right" href="/forum">forum</a>
                    <a class="navbar-brand nav-right" href="/myTeam">Mon équipe de recherche</a>
                    <a class="navbar-brand nav-right" href="/logout">Déconnexion</a>
                </sec:authorize>

                <sec:authorize access="!isAuthenticated()">
                    <a class="navbar-brand nav-right" href="/login">Connexion</a>
                </sec:authorize>
            </ul>
        </nav>
    </div>


    <div class="container" id="seminaireManager">
        <a>séminaires de l'équipe</a>
        <div class="container">
            <ul style="list-style: none;">
                <c:forEach items="${seminar}" var="seminar">
                    <hr>
                    <li><h3><a href="seminarDetails/${seminar.idSeminar}"><c:out value="${seminar.title}"/></a></h3></li>
                    <li><b>Auteur : </b><c:out value="${seminar.author.name}" /> <c:out value="${seminar.author.firstName}"/></li>
                    <li>
                        <a href="seminarUpdate/${seminar.idSeminar}">Modifier</a>
                        <a href="admin/seminarDelete/${seminar.idSeminar}">Supprimer</a>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
</div>
</body>



<%@ include file="/WEB-INF/jsp/struct/footer.jsp"%>
