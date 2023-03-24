<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/WEB-INF/jsp/struct/header.jsp"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<title>Détail de Séminaire</title>
</head>
<body>

<div id="myApp">
    <div class="container">
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <ul class="navbar-nav">
                <a class="navbar-brand nav-left" id="title"><b>Détail de Séminaire</b></a>
                <sec:authorize access="isAuthenticated()">
                    <a class="navbar-brand nav-right" href="/">Accueil</a>
                    <a class="navbar-brand nav-right" href="/forum">Forum</a>
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
    <div class="card border-primary mb-3 mx-auto p-4" style="max-width: 30rem;">
        <h1 >Informations sur le seminaire</h1>
        <h3> ${seminar.title}</h3>
        <ul>

            <li> <b>Date :</b> le <fmt:formatDate value="${seminar.date}" pattern="dd/MM/yyyy"/> à <fmt:formatDate value="${seminar.date}" pattern="HH:mm" /> </li>
            <li> <b>Lieu du séminaire :</b> ${seminar.location}  </li>
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
            <li> <b>Liens utiles :</b>
                <ul>
                    <c:forEach items="${seminar.optionalContentLinks}" var="link">
                        <li>
                            <c:out value="${link}"/>
                        </li>
                    </c:forEach>
                </ul>
            </li>
        </ul>



        <h6> Description : </h6>
        <p>${seminar.description}</p>


    </div>
</div>
</body>

<%@ include file="/WEB-INF/jsp/struct/footer.jsp"%>