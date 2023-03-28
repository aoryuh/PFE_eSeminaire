<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/jsp/struct/header.jsp"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<title>Forum</title>
</head>
<body>

<div id="myApp">
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
                <a class="navbar-brand nav-left" id="title"><b>Ajouter Utilisateur</b></a>
                <sec:authorize access="isAuthenticated()">
                    <a class="navbar-brand nav-right title" href="/">Accueil</a>
                    <a class="navbar-brand nav-right" href="/forum">Forum</a>
                    <a class="navbar-brand nav-right" href="/myTeam">Mon équipe </a>
                    <a class="navbar-brand nav-right" href="/archive">Archive</a>
                    <sec:authorize access="hasAnyAuthority('ADMIN', 'RESPO')">
                        <a class="navbar-brand nav-right" href="/admin">Gérer mon équipe</a>
                    </sec:authorize>
                    <a class="navbar-brand nav-right" href="/logout">Déconnexion</a>
                </sec:authorize>

                <sec:authorize access="!isAuthenticated()">
                    <a class="navbar-brand nav-right" href="/login">Connexion</a>
                </sec:authorize>
        </nav>

    <form:form method="POST" modelAttribute="user" >

        <div class="form-group">
            <label for="name"> Nom : </label>
            <form:input path="name" class="form-control" />
            <form:errors path="name" cssClass="alert alert-warning" element="div" />
        </div>

        <div class="form-group">
            <label for="firstName"> Prénom : </label>
            <form:input path="firstName" class="form-control" />
            <form:errors path="firstName" cssClass="alert alert-warning" element="div" />
        </div>


        <div class="form-group">
            <label for="mail"> mail : </label>
            <form:input path="mail" class="form-control" />
            <form:errors path="mail" cssClass="alert alert-warning" element="div" />
        </div>


        <div class="form-group">
            <label for="password"> Mot de passe : </label>
            <form:input path="password" type="password" class="form-control" />
            <form:errors path="password" cssClass="alert alert-warning" element="div" />
        </div>

        <div class="form-group">
            <a style="display: block"id="save"><button type="button" onclick="save()">Sauvegarder</button></a>
            <a style="display: none;" id="confirm">Etes vous sur de vouloir créer cet utilisateur ? <button type="submit" class="btn btn-info">Confirmer</button> <button type="button" onclick="cancel()">annuler</button></a>
        </div>
    </form:form>
</div>

<%@ include file="/WEB-INF/jsp/struct/footer.jsp"%>

<script>
    function save(){
        console.log("save");
        document.getElementById("save").style.display="none";
        document.getElementById("confirm").style.display="block";
    }
    function cancel(){
        console.log("save");
        document.getElementById("save").style.display="block";
        document.getElementById("confirm").style.display="none";
    }
</script>
