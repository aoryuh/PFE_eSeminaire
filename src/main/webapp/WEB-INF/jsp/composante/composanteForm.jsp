<%@ include file="/WEB-INF/jsp/header.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="container">
    <h1>Ajout d'une composante</h1>


<form:form method="POST" modelAttribute="composante" >


        <div class="form-group">
            <label for="intitule"> Intitulé</label>
            <form:input class="form-control" path="intitule"/>
            <form:errors path="intitule" cssClass="alert alert-warning" element="div" />
        </div>





        <div class="form-group">
            <button type="submit" class="btn btn-info">Submit</button>
        </div>
</form:form>
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp"%>
