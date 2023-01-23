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

    <div class="aParent" >
        <div id="seminaireManager" class="adminSplit">
            <h3><a>séminaires de l'équipe</a></h3>
            <ul style="list-style: none;">
                <c:forEach items="${seminar}" var="seminar">
                    <hr>
                    <li><h3><c:out value="${seminar.title}"/></h3></li>
                    <li><b>Auteur : </b><c:out value="${seminar.author.name}" /> <c:out value="${seminar.author.firstName}"/></li>
                    <li>
                        <a href="admin/seminarUpdate/${seminar.idSeminar}">Modifier</a>
                        <a href="admin/seminarDelete/${seminar.idSeminar}">Supprimer</a>
                    </li>
                </c:forEach>
            </ul>
        </div>
        <div id="membersManager" class="adminSplit">
            <h3><a>Membres de l'équipe</a></h3>
            <ul style="list-style: none;">
                <c:forEach items="${users}" var="user">
                    <hr>
                    <li><b><c:out value="${user.name}"/></b> <c:out value="${user.firstName}"/></li>
                    <li>
                        <a href="admin/userUpdate/${user.idUser}">Modifier</a>
                        <a href="admin/userDelete/${user.idUser}">Supprimer</a>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
</div>
</body>



<%@ include file="/WEB-INF/jsp/struct/footer.jsp"%>
