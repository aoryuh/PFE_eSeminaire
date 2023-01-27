<%@ include file="/WEB-INF/jsp/struct/header.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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

<div class="p-3"></div>
<div class="card border-primary mb-3 mx-auto p-4" style="max-width: 30rem;">
	<div class="card-body text-dark mx-auto p-4">
		<h3 >Informations sur le seminaire</h3>
		<ul>
            <li> Titre : ${seminar.title}  </li>
            <li> Date : ${seminar.date}  </li>
            <li> Location : ${seminar.location}  </li>
            <li> Auteur : ${seminar.author.name} </li>
            <li> Equipe : ${seminar.team.name} </li>
            <li> Description detaillee : ${seminar.description} </li>

		</ul>
	</div>
</div>

</div>
<%@ include file="/WEB-INF/jsp/struct/footer.jsp"%>