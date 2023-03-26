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
                <a class="navbar-brand nav-left" id="title"><b>Accueil</b></a>
                <sec:authorize access="isAuthenticated()">
                    <a class="navbar-brand nav-right" href="/forum">Forum</a>
                    <a class="navbar-brand nav-right" href="/myTeam">Mon équipe de recherche</a>
                    <sec:authorize access="hasAnyAuthority('ADMIN', 'RESPO')">
                        <a class="navbar-brand nav-right" href="/admin">Gérer mon équipe</a>
                    </sec:authorize>
                    <a class="navbar-brand nav-right" href="/archive">Séminaires passés</a>
                    <a class="navbar-brand nav-right" href="/logout">Déconnexion</a>
                </sec:authorize>

                <sec:authorize access="!isAuthenticated()">
                    <a class="navbar-brand nav-right" href="/login">Connexion</a>
                </sec:authorize>
            </ul>
        </nav>
    </div>




    <div class="container">
        <div>
            <form method="get" action="/search">
                <label for="query">Recherche :</label>
                <input type="text" name="query" id="query">
                <button type="submit" class="btn btn-info">Rechercher</button>
            </form>
        </div>
        <div></div>
        <form method="get" action="/sort">
            <label for="select">trier par :</label>
            <select name="select" id="select">
                <option selected value="title">nom</option>
                <option value="date">date</option>
            </select>
            <button type="submit" class="btn btn-info">Valider</button>
        </form>
    </div>

    <div class="sort"></div>

    <h1> Liste des séminaires prévus  </h1>
    <table class="table">
        <th>Titre </th>
        <th>Auteurs </th>
        <th>Résumé</th>
        <th></th>


        <c:forEach items="${seminars}" var="seminar">
            <tr>
                <td class="semListTd"> <c:out value="${seminar.title}" /> </td>
                <td class="semListTd"> <c:forEach items="${seminar.authors}" var="author">
                    <a class="firstname"><c:out value="${author.firstName}"/></a> <a class="lastname"><c:out value="${author.name}" /></a><br>
                </c:forEach> </td>
                <td class="semListTd desc" >
                    <c:if test = "${seminar.description.length() > 2001}">
                        <c:out value="${seminar.description.substring(0,2000)}" />...
                    </c:if>
                    <c:if test = "${seminar.description.length() <= 2001}">
                        <c:out value="${seminar.description}" />
                    </c:if>
                </td>
                <td class="semListTd"><a href="seminarDetails/${seminar.idSeminar}"
                                         class="btn btn-outline-primary" type="submit">Afficher plus</a></td>
            </tr>
        </c:forEach>
    </table>
</div>
</div>
</body>

<%@ include file="/WEB-INF/jsp/struct/footer.jsp"%>