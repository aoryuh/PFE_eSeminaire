<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/jsp/struct/header.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>



<div id="myApp">
    <div class="container">
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <ul class="navbar-nav">
                <a class="navbar-brand nav-left">Forum</a>
                <sec:authorize access="isAuthenticated()">
                    <a class="navbar-brand nav-right" href="/">Accueil</a>
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

        <h1>Forum</h1>

        <c:forEach items="${messages}" var="message" >
            <div class="message">
                <h3> Sujet : ${message.subject}</h3>
                <p>${message.content}</p>
                <p>Publié par ${message.user.name} ${message.user.firstName} le ${message.date}</p>
                <a href="forum/messageUpdate/${message.id}">Modifier</a>
                <a href="forum/messageDelete/${message.id}">Supprimer</a>
            </div>
        </c:forEach>

        <div>
                <h3>Ecrire un nouveau Message</h3>

                   <form action="/addMessage" method="POST">
                       <label for="subject"> <strong> Sujet: </strong> </label>
                       <input type="text" id="subject" name="subject" required>
                       <br>

                       <label for="content"> <strong> Contenu: </strong> </label>
                       <textarea id="content" name="content" required></textarea>
                       <br>

                       <input type="submit" value="Submit">
                   </form>
            </div>

</div>



<%@ include file="/WEB-INF/jsp/struct/footer.jsp"%>
