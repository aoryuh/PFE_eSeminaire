<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/WEB-INF/jsp/struct/header.jsp"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<title>Mon équipe</title>
</head>
<body>

<div id="myApp">
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
                <a class="navbar-brand nav-left" id="title"><b>Mon équipe</b></a>
                <sec:authorize access="isAuthenticated()">
                    <a class="navbar-brand nav-right" href="/">Accueil</a>
                    <a class="navbar-brand nav-right" href="/forum">Forum</a>
                    <sec:authorize access="hasAnyAuthority('ADMIN', 'RESPO')">
                        <a class="navbar-brand nav-right" href="/admin">Gérer mon équipe</a>
                    </sec:authorize>
                    <a class="navbar-brand nav-right" href="/archive">Archive</a>
                    <a class="navbar-brand nav-right" href="/logout">Déconnexion</a>
                </sec:authorize>

                <sec:authorize access="!isAuthenticated()">
                    <a class="navbar-brand nav-right" href="/login">Connexion</a>
                </sec:authorize>
        </nav>
    <div class="aParent" >
        <div id="seminaireManager" class="adminSplit">
            <h2> Liste des séminaires de l'équipe </h2>
            <ul class="adminList">
                <c:forEach items="${seminar}" var="seminar">
                    <hr>
                    <li><h4><c:out value="${seminar.title}"/></h4></li>
                    <li><b> Date : </b> <fmt:formatDate value="${seminar.date}" pattern="dd/MM/yyyy"/> </li>
                    <li><b> Heure : </b> <fmt:formatDate value="${seminar.date}" pattern="HH:mm" /></li>
                    <li><b> Auteur(s) : </b>
                        <ul style="list-style: none;">
                            <c:forEach items="${seminar.authors}" var="author">
                                <li><a class="firstname"><c:out value="${author.firstName}"/></a> <a class="lastname"><c:out value="${author.name}" /></a> </li>
                            </c:forEach>
                        </ul>
                    </li>
                    <li>
                        <a class="btn" href="../seminarDetails/${seminar.idSeminar}">Voir plus </a>

                    </li>
                </c:forEach>
            </ul>
        </div>
        <div id="membersManager" class="adminSplit">
            <h2>Membres de l'équipe </h2>
            <ul style="list-style: none;">
                <c:forEach items="${users}" var="user">
                    <hr>
                    <li><b><a class="firstname"><c:out value="${user.firstName}"/></a><a class="lastname"> <c:out value="${user.name}"/></a></b></li>
                    <li><a class="btn" href="myTeam/userDetails/${user.idUser}"> Voir plus </a></li>
                </c:forEach>
            </ul>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/jsp/struct/footer.jsp"%>