<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/WEB-INF/jsp/struct/header.jsp"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<title>Détails du séminaire</title>
</head>
<body>

<div id="myApp">
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
                <a class="navbar-brand nav-left" id="title"><b>Détail de Séminaire</b></a>
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

    <div>
        <h2> ${seminar.title}</h2>
        <p> Présenté par l'équipe de recherche <b> ${seminar.team.name} </b></p>
        <ul style="list-style: none">

            <li> <b> Date : </b> <fmt:formatDate value="${seminar.date}" pattern="dd/MM/yyyy"/>  </li>
            <li> <b> Heure : </b> <fmt:formatDate value="${seminar.date}" pattern="HH:mm" /> </li>
            <li> <b> Lieu : </b> ${seminar.location}</li>
            <c:if test="${seminar.authors.size() == 1}">
            <li> <b> Auteur(s) :</b>
                <c:forEach items="${seminar.authors}" var="author">
                  <a class="firstname"><c:out value="${author.firstName}"/></a> <a class="lastname"><c:out value="${author.name}" /> </a>

                </c:forEach>
        </li>
        </c:if>
        <c:if test="${seminar.authors.size() > 1}">
            <li> <b>Auteur(s) : </b>
                <ul>
                    <c:forEach items="${seminar.authors}" var="author">
                        <li>
                            <a class="firstname"><c:out value="${author.firstName}"/></a>
                            <a class="lastname"><c:out value="${author.name}" /></a>
                        </li>
                    </c:forEach>
                </ul>
            </li>
        </c:if>

        <li><b>Equipe : </b>${seminar.team.name} </li>
        <li><b> Description : </b>
            <c:out value="${seminar.description}" escapeXml="false"/></li>
        <c:if test = "${seminar.optionalContentLinks.get(0).length() > 0}">
            <li> <b> Liens utiles :</b>
                <ul>
                    <c:forEach items="${seminar.optionalContentLinks}" var="link">
                        <li>
                            <a href="https://${link}" ><c:out value="${link}"/></a>
                        </li>
                    </c:forEach>
                </ul>
            </li>
        </c:if>
        </ul>

    </div>
</div>
</body>

<%@ include file="/WEB-INF/jsp/struct/footer.jsp"%>