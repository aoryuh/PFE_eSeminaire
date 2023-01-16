<%@ include file="/WEB-INF/jsp/header.jsp"%>


<c:url var="addForm" value="admin/formationCreate" />
<c:url var="addCompo" value="admin/createComposante" />
<c:url var="userGestion" value="admin/gestionUtilisateur" />

<div>
    <%@ include file="/WEB-INF/jsp/nav.jsp"%>
</div>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="container">

    <ul>
        <li>
            <table class="table">

                <h1> Liste des composantes :  </h1>




                <c:forEach items="${composante}" var="composante">
                    <tr>
                        <td><h3> <c:out value="${composante.intitule}"/></h3></td>
                    </tr>
                </c:forEach>

            </table>
        </li>
    </ul>

    <div class="top">
        <button class="ajouter-formation-button"><a href="${addForm}">Ajouter une formation</a></button>
        <button class="ajouter-composante-button"><a href="${addCompo}">Ajouter une composante</a></button>

    </div>

    <div class="bottom">
        <button class="gerer-utilisateur-button"><a href="${userGestion}">Gérer les utilisateurs</a></button>
        <button class="exporter-données-button"><a href=#>Exporter les données</a></button>
    </div>
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp"%>
