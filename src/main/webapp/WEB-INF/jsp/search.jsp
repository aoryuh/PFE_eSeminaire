<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/WEB-INF/jsp/struct/header.jsp"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<title>Accueil</title>
</head>
<body>

<div id="myApp">
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <a class="navbar-brand nav-left" id="title"><b>Accueil</b></a>
            <sec:authorize access="isAuthenticated()">
                <a class="navbar-brand nav-right" href="/forum">Forum</a>
                <a class="navbar-brand nav-right" href="/myTeam">Mon équipe </a>
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

    <div class="container">
        <div>
            <form method="get" action="/search">
                <label for="query">Recherche :</label>
                <input type="text" name="query" id="query">
                <button type="submit" class="btnn btn-info">Rechercher</button>
            </form>
        </div>
        <div></div>
        <form method="get" action="/sort">
            <label for="select">trier par :</label>
            <select name="select" id="select">
                <option selected value="title"> nom </option>
                <option value="date"> date </option>
            </select>
            <button type="submit" class="btnn btn-info">Valider</button>
        </form>
    </div>

    <div class="sort"></div>

    <h2> Liste des séminaires prévus  </h2>
    <c:forEach items="${seminars}" var="seminar">
        <hr>
        <h4 class="semListTd"> <c:out value="${seminar.title}" /> (${seminar.team.name})</h4>
        <p class="semListTd"> Présenté par  <c:forEach items="${seminar.authors}" var="author">
            <b><a class="firstname"><c:out value="${author.firstName}"/></a> <a class="lastname"><c:out value="${author.name}" /></a></b><br>
        </c:forEach> </p>
        <p class="semListTd dateLieu"> <b> Date : </b> <fmt:formatDate value="${seminar.date}" pattern="dd/MM/yyyy"/> <b> Heure : </b><fmt:formatDate value="${seminar.date}" pattern="HH:mm" /> <b> Lieu : </b> ${seminar.location}</p>
        <p class="semListTd desc" >
            <c:if test = "${seminar.description.length() > 1501}">
                <c:out value="${seminar.description.substring(0,1500)}" escapeXml="false" />...
            </c:if>
            <c:if test = "${seminar.description.length() <= 1501}">
                <c:out value="${seminar.description}" escapeXml="false"/>
            </c:if>
        </p>
        <p class="semListTd"><a href="seminarDetails/${seminar.idSeminar}"
                                class="btn btn-outline-primary" type="submit">Afficher plus</a></p>
    </c:forEach>
</div>
</div>
</body>

<%@ include file="/WEB-INF/jsp/struct/footer.jsp"%>