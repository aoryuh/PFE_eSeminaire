<%@ include file="/WEB-INF/jsp/header.jsp"%>


<c:url var="view" value="/sessionDetails" />
<c:url var="addSession" value="sessions/add" />


<div>
    <%@ include file="/WEB-INF/jsp/nav.jsp"%>
</div>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="container">
    <h1>Sessions de la formation <c:out value="${formation.intitule}"/> </h1>
    <ul>
        <table class="table">
            <tr>
                <th>Numéro de session : </th>
                <th>Début :</th>
                <th>Fin :</th>
                <th>Session ouverte ? (0 oui/1 non)</th>
                <th></th>
                <th></th>
            </tr>
            <tr>
                <c:forEach items="${formation.action.sessions}" var="session">
            <tr>
                <td><c:out value="${session.numero}" /></td>

                <td><c:out value="${session.debut}" /></td>
                <td><c:out value="${session.fin}" /></td>
                <td><c:out value="${session.sessionOuverte}" /></td>
                <td> <a href="${form}?id=${session.numero}"> modifier la session</a></td>
                <td> <a href="${form}?id=${session.numero}"> supprimer la session</a></td>


            </tr>
            </c:forEach>
        </table>


    </ul>
    <button class="ajouter-session-button"><a href="${addSession}">Ajouter une session</a></button>

</div>

<%@ include file="/WEB-INF/jsp/footer.jsp"%>
