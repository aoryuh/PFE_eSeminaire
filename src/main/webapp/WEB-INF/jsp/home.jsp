<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/jsp/struct/header.jsp"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<title>Accueil</title>
</head>
<body>

<div id="myApp">
    <div class="container">
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <ul class="navbar-nav">
                <a class="navbar-brand nav-left">Accueil</a>
                <sec:authorize access="isAuthenticated()">
                    <a class="navbar-brand nav-right" href="/forum">Forum</a>
                    <a class="navbar-brand nav-right" href="/myTeam">Mon équipe de recherche</a>
                    <sec:authorize access="hasAnyAuthority('ADMIN', 'RESPO')">
                        <a class="navbar-brand nav-right" href="/admin">Gérer mon équipe</a>
                    </sec:authorize>
                    <a class="navbar-brand nav-right" href="/logout">Déconnexion</a>
                </sec:authorize>

                <sec:authorize access="!isAuthenticated()">
                    <a class="navbar-brand nav-right" href="/login">Connexion</a>
                </sec:authorize>
            </ul>
        </nav>
    </div>


    <div class="container">
        <h1> Liste des séminaires prévus  </h1>
        <table class="table">
            <th>Titre </th>
            <th>Date </th>
            <th>Localisation </th>
            <th>Auteur </th>
            <th>Equipe </th>

            <c:forEach items="${seminars}" var="seminar">
                <tr>
                    <td> <c:out value="${seminar.title}" /> </td>
                    <td> <c:out value="${seminar.date}" /> </td>
                    <td> <c:out value="${seminar.location}" /> </td>
                    <td> <c:forEach items="${seminar.authors}" var="author">
                         <a class="firstname"><c:out value="${author.firstName}"/></a> <a class="lastname"><c:out value="${author.name}" /></a><br>
                    </c:forEach> </td>
                    <td> <c:out value="${seminar.team.name}" /> </td>


                    <td><a href="${seminar.idSeminar}"
                           class="btn btn-outline-primary" type="submit">Afficher </a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

<%@ include file="/WEB-INF/jsp/struct/footer.jsp"%>