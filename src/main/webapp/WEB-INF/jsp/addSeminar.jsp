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

    <form:form method="POST" modelAttribute="seminar" >



        <div class="form-group">
            <label for="title"> Titre : </label>
            <form:input path="title" class="form-control" />
            <form:errors path="title" cssClass="alert alert-warning" element="div" />
        </div>

        <div class="form-group">
            <label for="content"> Résumé : </label>
            <form:input path="content" class="form-control" rows="4"/>
            <form:errors path="content" cssClass="alert alert-warning" element="div" />
        </div>

        <div class="form-group">
            <label for="date"> Date : </label>
            <form:input path="date" type="datetime-local" class="form-control" />
            <form:errors path="date" cssClass="alert alert-warning" element="div" />
        </div>

        <div class="form-group">
            <label for="location"> Lieu : </label>
            <form:input path="location" class="form-control" rows="4"/>
            <form:errors path="location" cssClass="alert alert-warning" element="div" />
        </div>

        <div class="form-group">
            <label for="author">Auteur :</label>
            <form:select path="author" multiple="false" class="form-control">
                <form:option value="" label="--- Select ---" />
                <form:options items="${ListUsersOfTeam}" />
            </form:select>
            <form:errors path="author" cssClass="alert alert-warning"
                         element="div" />
        </div>

        <div class="form-group">
            <label for="optionalContentLinks"> Lien supplémentaires : </label>
            <form:input path="optionalContentLinks" class="form-control" rows="4"/>
            <form:errors path="optionalContentLinks" cssClass="alert alert-warning" element="div" />
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-info">Valider</button>
        </div>
    </form:form>
</div>





<%@ include file="/WEB-INF/jsp/struct/footer.jsp"%>
