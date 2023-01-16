<%@ include file="/WEB-INF/jsp/header.jsp"%>
<div>
    <%@ include file="/WEB-INF/jsp/nav.jsp"%>
</div>


<c:url var="view" value="/formationDetails" />
<c:url var="add" value="/correspondant/addAdress" />
<c:url var="edit" value="/correspondant/editAdress" />
<c:url var="remove" value="/deleteAdress" />



<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>



<div class="container">
    <table class="table">
        <h1> Liste des formations de la composante : </h1>
        <thead>
        <tr>
            <th scope="col">Intitulé</th>
            <th scope="col">CodeFormation</th>
            <th scope="col">EtatEdition</th>
            <th scope="col">Profil</th>
        </tr>
        <thead>
        <tbody>

        <c:forEach items="${composante.formations}" var="formation">
            <tr>
                <td><c:out value="${formation.intitule}" /></td>
                <td><c:out value="${formation.code}" /></td>
                <td><c:out value="${formation.etatEdition}" /></td>
                <td> <a href="${view}?id=${formation.id}"> <i class="fas fa-eye"></i></a></td>

            </tr>

        </c:forEach>
        </tbody>
    </table>

    <table class="table">
        <h1> Liste des adresses de la composante :  </h1>
        <thead>
        <tr>
            <th scope="col">Ligne</th>
            <th scope="col"></th>
            <th scope="col"></th>

        </tr>
        </thead>
        <tbody>
        <c:forEach items="${composante.adresses}" var="adresse">
            <tr>
                <td><c:out value="${adresse.ligne}" /></td>
                <td><a href="${edit}?id=${adresse.id}">modifier</a></td>
                <td><a href="${remove}/${adresse.id}">Supprimer</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <button class="ajouter-adresse-button"><a href="/correspondant/addAdress/${composante.id}">Ajouter une adresse</a></button>

</div>

<%@ include file="/WEB-INF/jsp/footer.jsp"%>
