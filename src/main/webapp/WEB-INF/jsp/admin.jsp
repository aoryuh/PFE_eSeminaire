<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/jsp/struct/header.jsp"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<title>Page administrateur</title>
</head>
<body>

<div id="myApp">
    <div class="container">
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <ul class="navbar-nav">
                <a class="navbar-brand nav-left">Page administrateur</a>
                <sec:authorize access="isAuthenticated()">
                    <a class="navbar-brand nav-right" href="/logout">Déconnexion</a>
                    <a class="navbar-brand nav-right" href="/myTeam">Mon équipe de recherche</a>
                    <a class="navbar-brand nav-right" href="/forum">forum</a>
                    <a class="navbar-brand nav-right" href="/">Accueil</a>
                </sec:authorize>

                <sec:authorize access="!isAuthenticated()">
                    <a class="navbar-brand nav-right" href="/login">Connexion</a>
                </sec:authorize>
            </ul>
        </nav>
    </div>
    <div class="container" id="adminPage">

        <div class="container" id="userManager">
            <a>membres de l'équipe</a>




        </div>
        <div class="container" id="seminaireManager">
            <a>séminaires de l'équipe</a>
            <div class="container">
                <h1>Formations  </h1>
                <ul>
                    <c:forEach items="${seminar}" var="seminar">
                        <hr>
                        <li><h3><c:out value="${seminar.title}"/></h3></li>
                        <li>
                            <table class="table">
                                <tr>
                                    <th>contenu :</th>
                                    <th>auteur :</th>
                                    <th></th>
                                    <th></th>
                                </tr>
                                <tr>
                                    <td><c:out value="${seminar.content}" /></td>
                                    <td><c:out value="${seminar.author.name}" /> <c:out value="${seminar.author.firstName}" /></td>
                                    <td> <a href="seminarDetail?id=${seminar.idSeminar}"> <i class="fas fa-eye"></i></a></td>
                                    <td><a href="admin/delete/${seminar.idSeminar}">Supprimer</a></td>
                                </tr>
                            </table>
                        </li>
                    </c:forEach>

                </ul>
            </div>




        </div>



    </div>

</div>



<%@ include file="/WEB-INF/jsp/struct/footer.jsp"%>
