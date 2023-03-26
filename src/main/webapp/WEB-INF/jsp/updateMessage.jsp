<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/jsp/struct/header.jsp"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<title>Nouveau Message</title>
</head>
<body>

<div id="myApp">
    <div class="container">
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <ul class="navbar-nav">
                <a class="navbar-brand nav-left" id="title"><b>Forum</b></a>
                <sec:authorize access="isAuthenticated()">
                    <a class="navbar-brand nav-right" href="/">Accueil</a>
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
        <h1>Saisir un nouveau message </h1>
        <form:form method="POST" modelAttribute="message" >
            <div class="form-group">
                <label for="subject"> <strong> Sujet: </strong> </label>
                <form:input  path="subject" type="text" class="form-control" />
                <form:errors path="subject" cssClass="alert alert-warning" element="div" />
            </div>

            <div class="form-group">
                <label for="content"> Contenu : </label>
                <form:textarea path="content" type="text" class="form-control" size="4"/>
                <form:errors path="content" cssClass="alert alert-warning" element="div" />
            </div>
            <button type="submit" class="btn btn-info">Confirmer</button>            </form:form>
        </div>
</div>


<%@ include file="/WEB-INF/jsp/struct/footer.jsp"%>
