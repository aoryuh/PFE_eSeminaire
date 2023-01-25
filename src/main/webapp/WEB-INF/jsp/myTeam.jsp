<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/jsp/struct/header.jsp"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<div id="myApp">
    <div class="container">
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <ul class="navbar-nav">
                <a class="navbar-brand nav-left">Mon équipe</a>
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
    <div class="aParent" >
        <div id="seminaireManager" class="adminSplit">
            <h3><a>séminaires de l'équipe</a></h3>
            <ul style="list-style: none;">
                <c:forEach items="${seminar}" var="seminar">
                    <hr>
                    <li><h3><a href="seminarDetails/${seminar.idSeminar}"><c:out value="${seminar.title}"/></a></h3></li>
                    <li><b>Auteur : </b><c:out value="${seminar.author.name}" /> <c:out value="${seminar.author.firstName}"/></li>
                </c:forEach>
            </ul>
        </div>
        <div id="membersManager" class="adminSplit">
            <h3><a>Membres de l'équipe</a></h3>
            <ul style="list-style: none;">
                <c:forEach items="${users}" var="user">
                    <hr>
                    <li><b><c:out value="${user.name}"/></b> <c:out value="${user.firstName}"/></li>
                    <li><a href="userDetail/${user.idUser}">voir le profil</a></li>
                </c:forEach>
            </ul>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/jsp/struct/footer.jsp"%>