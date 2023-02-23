<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/jsp/struct/header.jsp"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<title>Forum</title>
</head>
<body>

<div id="myApp">
    <div class="container">
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <ul class="navbar-nav">
                <a class="navbar-brand nav-left">Ajouter un séminaire</a>
                <sec:authorize access="isAuthenticated()">
                    <a class="navbar-brand nav-right" href="/">Accueil</a>
                    <a class="navbar-brand nav-right" href="/forum">forum</a>
                    <a class="navbar-brand nav-right" href="/myTeam">Mon équipe de recherche</a>
                    <sec:authorize access="hasAuthority('ADMIN')">
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

    <form:form method="POST" modelAttribute="updateSeminar" >
        <div class="form-group">
            <form:input  path="idSeminar" type="hidden" class="form-control" />
            <form:errors path="idSeminar" cssClass="alert alert-warning" element="div" />
        </div>

        <div class="form-group">
            <label for="date"> Date : </label>
            <form:input path="date" type="datetime-local" class="form-control" />
            <form:errors path="date" cssClass="alert alert-warning" element="div" />
        </div>

        <div class="form-group">
            <label for="location"> Lieu : </label>
            <form:input path="location" class="form-control" rows="1"/>
            <form:errors path="location" cssClass="alert alert-warning" element="div" />
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-info" onclick="confirm('Voulez-vous vraiment modifier ce séminaire ?')">Valider</button>
        </div>
    </form:form>
</div>





<%@ include file="/WEB-INF/jsp/struct/footer.jsp"%>
