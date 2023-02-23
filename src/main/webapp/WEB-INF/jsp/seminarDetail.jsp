<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/jsp/struct/header.jsp"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<title>Détail de Séminaire</title>
</head>
<body>

<div id="myApp">
    <div class="container">
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <ul class="navbar-nav">
                <a class="navbar-brand nav-left">Détail de Séminaire</a>
                <sec:authorize access="isAuthenticated()">
                    <a class="navbar-brand nav-right" href="/logout">Déconnexion</a>
                    <sec:authorize access="hasAuthority('ADMIN')">
                        <a class="navbar-brand nav-right" href="/admin">Gérer mon équipe</a>
                    </sec:authorize>
                    <a class="navbar-brand nav-right" href="/forum">Forum</a>
                    <a class="navbar-brand nav-right" href="/">Accueil</a>
                </sec:authorize>

                <sec:authorize access="!isAuthenticated()">
                    <a class="navbar-brand nav-right" href="/login">Connexion</a>
                </sec:authorize>
            </ul>
        </nav>
    </div>
    <div class="card border-primary mb-3 mx-auto p-4" style="max-width: 30rem;">
        <div class="card-body text-dark mx-auto p-4">
            <h3 >Informations sur le seminaire</h3>
            <ul>
                <li> <b>Titre :</b> ${seminar.title}  </li>
                <li> <b>Date :</b> ${seminar.date}  </li>
                <li> <b>Location :</b> ${seminar.location}  </li>
                <li> <b>Auteur(s) :</b>
                <ul>
                    <c:forEach items="${seminar.authors}" var="author">
                        <li>
                            <a class="firstname"><c:out value="${author.firstName}"/></a>
                            <a class="lastname"><c:out value="${author.name}" /></a>
                        </li>
                    </c:forEach>
                </ul>
                </li>
                <li><b>Equipe : </b>${seminar.team.name} </li>
                <li><b>Description detaillee :</b> ${seminar.description} </li>

            </ul>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/jsp/struct/footer.jsp"%>