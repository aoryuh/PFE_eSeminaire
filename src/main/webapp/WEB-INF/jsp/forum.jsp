<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/WEB-INF/jsp/struct/header.jsp"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<title>Forum</title>
</head>
<body>

<div id="myApp">
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
                <a class="navbar-brand nav-left" id="title"><b>Forum</b></a>
                <sec:authorize access="isAuthenticated()">
                    <a class="navbar-brand nav-right" href="/">Accueil</a>
                    <a class="navbar-brand nav-right" href="/myTeam">Mon équipe de recherche</a>
                    <sec:authorize access="hasAnyAuthority('ADMIN')">
                        <a class="navbar-brand nav-right" href="/admin">Gérer mon équipe</a>
                    </sec:authorize>
                    <a class="navbar-brand nav-right" href="/archive">Archives</a>
                    <a class="navbar-brand nav-right" href="/logout">Déconnexion</a>
                </sec:authorize>

                <sec:authorize access="!isAuthenticated()">
                    <a class="navbar-brand nav-right" href="/login">Connexion</a>
                </sec:authorize>
        </nav>
    <div class="container">
        <h1>Forum</h1>
        <div class="DivToScroll messages">
        <c:forEach items="${messages}" var="message" >
            <div class="message">
                <h5> Sujet : ${message.subject}</h5>
                <p>${message.content}</p>
                <p class="author">Publié par <a class="firstname"><c:out value="${message.user.firstName}"/></a><a class="lastname"> <c:out value="${message.user.name}"/></a> le <fmt:formatDate value="${message.date}" pattern="dd/MM/yyyy"/> à <fmt:formatDate value="${message.date}" pattern="HH:mm" /></p>
                <sec:authorize access="hasAnyAuthority('RESPO', 'ADMIN')">
                    <a href="forum/updateMessage/${message.id}">Modifier</a>
                    <a href="forum/deleteMessage/${message.id}">Supprimer</a>
                </sec:authorize>
            </div>
        </c:forEach>
        </div>
        <hr>
        <div class="newElement msg">
            <h3>Nouveau message</h3>
            <form method="POST" modelAttribute="message" >
                <label for="subject"> <strong> Sujet: </strong> </label>
                <input type="text" id="subject" class="form-control" name="subject" required>
                <br>
                <label for="content"> <strong> Contenu: </strong> </label>
                <textarea id="content" class="form-control" name="content" required></textarea>
                <br>
                <input type="submit" value="Enregister">
            </form>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/jsp/struct/footer.jsp"%>
