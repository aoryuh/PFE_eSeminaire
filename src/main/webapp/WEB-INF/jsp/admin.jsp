<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/jsp/struct/header.jsp"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<title>Page administrateur</title>
</head>
<body>

<div class="myApp">
    <div class="container">
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <ul class="navbar-nav">
                <a class="navbar-brand nav-left"><b>Page administrateur</b></a>
                <sec:authorize access="isAuthenticated()">
                    <a class="navbar-brand nav-right" href="/">Accueil</a>
                    <a class="navbar-brand nav-right" href="/forum">forum</a>
                    <a class="navbar-brand nav-right" href="/myTeam">Mon équipe de recherche</a>
                    <a class="navbar-brand nav-right" href="/logout">Déconnexion</a>
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
                    <li><h3><c:out value="${seminar.title}"/></h3></li>
                    <li><b>Auteur(s) : </b>
                        <ul style="list-style: none;">
                            <c:forEach items="${seminar.authors}" var="author">
                                <li><a class="firstname"><c:out value="${author.firstName}"/></a> <a class="lastname"><c:out value="${author.name}" /></a> </li>
                            </c:forEach>
                        </ul>
                    </li>
                    <li>
                        <div id="deleteModify${seminar.idSeminar}">
                            <a class="deleteLink" id="${seminar.idSeminar}" onclick="askDelete(this.id)">Supprimer</a>
                            <a href="admin/seminarUpdate/${seminar.idSeminar}">Modifier</a>
                        </div>
                        <div id="deleteConfirm${seminar.idSeminar}" style="display: none">
                            Voulez-vous vraiment supprimer ce séminaire ?
                            <a href="admin/seminarDelete/${seminar.idSeminar}">Supprimer</a>
                            <a class="deleteLink" id="${seminar.idSeminar}" onclick="cancelDelete(this.id)">Annuler</a>
                        </div>
                    </li>
                </c:forEach>
            </ul>
            <hr>

            <div  class="adminSplit">
                <form method="post" enctype="multipart/form-data">
                    <div>
                        <h3>Importez un séminaire :</h3>
                        <br>
                        <input class="btn btn-secondary" type="file" name="file" id="file">
                    </div>
                    <br>
                    <div>
                        <input class="btn btn-secondary" type="submit" value="Submit" />
                    </div>
                </form>
            </div>
        </div>
        <sec:authorize access="hasAuthority('ADMIN')">

            <div id="membersManager" class="adminSplit">
                <h3><a>Membres de l'équipe</a></h3>
                <ul style="list-style: none;">
                    <c:forEach items="${users}" var="user">
                        <hr>
                        <li><a class="firstname"><c:out value="${user.firstName}"/></a> <a class="lastname"><c:out value="${user.name}"/></a></li>
                        <li>
                            <div id="deleteModify${user.idUser}">
                                <a href="userDetail/${user.idUser}">Voir</a>
                                <a class="deleteLink " id="${user.idUser}" onclick="askDelete(this.id)">Supprimer</a>
                            </div>
                            <div id="deleteConfirm${user.idUser}" style="display: none">
                                Voulez-vous vraiment retirer cet utilisateur de l'équipe ?
                                <a href="admin/userDelete/${user.idUser}">Supprimer</a>
                                <a class="deleteLink" id="${user.idUser}" onclick="cancelDelete(this.id)">Annuler</a>

                            </div>
                        </li>
                    </c:forEach>
                    <hr>
                    <li><a class="firstname"><c:out value="${loggedUser.firstName}"/></a> <a class="lastname"><c:out value="${loggedUser.name}"/></a></li>
                    <li>
                        <a href="userDetail/${loggedUser.idUser}">Voir</a>

                </ul>
                <div  class="adminSplit">
                    <a class="btn btn-secondary" onclick="window.location.href='http://localhost:8080/admin/addUser';">ajouter un utilisateur</a>

                </div>

            </div>
        </sec:authorize>

    </div>


</div>
</body>



<%@ include file="/WEB-INF/jsp/struct/footer.jsp"%>
<script>function cancelDelete(id) {
    console.log("cancel delete"+id);
    document.getElementById("deleteModify"+id).style.display = "block";
    document.getElementById("deleteConfirm"+id).style.display = "none";
}

function askDelete(id){
    console.log("deleteSem"+id);
    document.getElementById("deleteModify"+id).style.display = "none";
    document.getElementById("deleteConfirm"+id).style.display = "block";
}
</script>