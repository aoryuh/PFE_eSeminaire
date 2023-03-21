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
    <div class="container">
        <h1>Forum</h1>
        <c:forEach items="${messages}" var="message" >
            <div class="message">
                <h3> Sujet : ${message.subject}</h3>
                <p>${message.content}</p>
                <p>Publié par <a class="firstname"><c:out value="${message.user.firstName}"/></a><a class="lastname"> <c:out value="${message.user.name}"/></a> le ${message.date}</p>
                <sec:authorize access="hasAuthority('USER')">
                <a href="forum/updateMessage/${message.id}">Modifier</a>
                <a href="forum/deleteMessage/${message.id}">Supprimer</a>
                </sec:authorize>
            </div>
        </c:forEach>
        <hr>
      <div class="container">
              <h1>Saisir un nouveau message </h1>
              <form method="POST" modelAttribute="message" >
                      <label for="subject"> <strong> Sujet: </strong> </label>
                      <input type="text" id="subject" name="subject" required>
                      <br>
                      <label for="content"> <strong> Contenu: </strong> </label>
                      <textarea id="content" name="content" required></textarea>
                      <br>
                      <input type="submit" value="Enregister">
                  </form>
              </div>
    </div>
</div>
<%@ include file="/WEB-INF/jsp/struct/footer.jsp"%>
