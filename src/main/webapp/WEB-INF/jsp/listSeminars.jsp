<%@ include file="/WEB-INF/jsp/struct/header.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div id="myApp">
    <div class="container">
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <ul class="navbar-nav">
                <a class="navbar-brand nav-left">Ajouter un séminaire</a>
                <sec:authorize access="isAuthenticated()">
                    <a class="navbar-brand nav-right" href="/">Accueil</a>
                    <a class="navbar-brand nav-right" href="/forum">forum</a>
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

    <div>
        <form method="get" action="recherche.jsp">
              <input type="text" name="q">
              <input type="submit" value="Rechercher">
        </form>
    </div>

    <h1> Liste des séminaires prévus  </h1>
    <table class="table">
                   <th>Titre </th>
                   <th>Auteur </th>
                   <th>Résumé </th>

                    <c:forEach items="${seminars}" var="seminar">
                            <tr>
                                <td> <c:out value="${seminar.title}" /> </td>
                                <td> <c:out value="${seminar.author.name}" /> </td>
                                <td> <c:out value="${seminar.description}" /> </td>
                                <td><a href="${seminar.idSeminar}"
                                class="btn btn-outline-primary" type="submit">Afficher plus </a></td>
                            </tr>
                </c:forEach>
            </table>
</div>

<%@ include file="/WEB-INF/jsp/struct/footer.jsp"%>