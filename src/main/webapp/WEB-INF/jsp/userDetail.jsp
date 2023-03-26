<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/jsp/struct/header.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<title>Forum</title>
</head>
<body>

<div id="myApp">
    <div class="container">
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <ul class="navbar-nav">
                <a class="navbar-brand nav-left" id="title"></a>
                <sec:authorize access="isAuthenticated()">
                    <a class="navbar-brand nav-right" href="/"><b>Détail de séminaire</b></a>
                    <a class="navbar-brand nav-right" href="/forum">forum</a>
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
    <div >
        <ul style="list-style: none;">
            <li><h3><c:out value="${user.firstName}" /> <c:out value="${user.name}"/></h3></li>
            <li><b>Contact : </b><c:out value="${user.mail}" /></li>
            <li><b>Equipe de recherche : </b><c:out value="${user.team.name}" /></li>
            <li> </li>
            <li><b>Séminaires organisés par <c:out value="${user.firstName}" /> <c:out value="${user.name}"/> : </b><br></li>
            <ul>
                <c:forEach items="${seminars}" var="seminar">
                    <li><c:out value="${seminar.title}"/></li>
                    </li>
                </c:forEach>
            </ul>
        </ul>

        </ul>
    </div>

</div>





<%@ include file="/WEB-INF/jsp/struct/footer.jsp"%>
