<%@ include file="/WEB-INF/jsp/struct/header.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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

<div >
		<h1 > Informations sur le séminaire </h1>
	        <h3> Titre : ${seminar.title} </h3>
	        <ul>
	            <li> Date et heure : ${seminar.date}  </li>
	            <li> Localisation : ${seminar.location} </li>
	            <li> Auteur : ${seminar.author.name} </li>
	            <li> Equipe : ${seminar.team.name} </li>
	        </ul>
            <p> <strong>Description detaillee</strong>: <br>${seminar.description}</br></p>

</div>

</div>
<%@ include file="/WEB-INF/jsp/struct/footer.jsp"%>