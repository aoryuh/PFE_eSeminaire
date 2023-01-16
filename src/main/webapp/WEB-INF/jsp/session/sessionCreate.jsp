

<%@ include file="/WEB-INF/jsp/header.jsp"%>
<div>
    <%@ include file="/WEB-INF/jsp/nav.jsp"%>
</div>


<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div class="container">
    <h1>Ajout d'une Session</h1>

    <form:form method="POST" modelAttribute="session" >

        <form:errors path="*" cssClass="alert alert-danger" element="div" />

        <div class="form-group">
            <label for="debut">Date de début :</label>
            <form:input type="date" class="form-control" path="debut"/>
            <form:errors path="debut" cssClass="alert alert-warning" element="div" />
        </div>
        <div class="form-group">
            <label for="debut">Date de fin :</label>
            <form:input type="date" class="form-control" path="fin"/>
            <form:errors path="fin" cssClass="alert alert-warning" element="div" />
        </div>

        <div class="form-group">
            <label for="etatRecrutement">Etat de recrutement :</label>
            Ouvert <form:radiobutton path="etatRecrutement" value="1"/>
            Fermé <form:radiobutton path="etatRecrutement" value="2"/>
            <form:errors path="etatRecrutement" cssClass="alert alert-warning" element="div" />
        </div>

        <div class="form-group">
            <label for="sessionOuverte">Session ouverte  :</label>
            oui <form:radiobutton path="sessionOuverte" value="1"/>
            non <form:radiobutton path="sessionOuverte" value="0"/>
            <form:errors path="sessionOuverte" cssClass="alert alert-warning" element="div" />
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-info">Submit</button>
        </div>
    </form:form>
</div>

