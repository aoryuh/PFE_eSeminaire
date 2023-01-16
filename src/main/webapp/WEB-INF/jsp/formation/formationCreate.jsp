




<%@ include file="/WEB-INF/jsp/header.jsp"%>
<div>
    <%@ include file="/WEB-INF/jsp/nav.jsp"%>
</div>


<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>



<div class="container">
    <h1>Ajout d'une formation</h1>

    <form:form method="POST" modelAttribute="formation" >
        <div class="form-group">
            <label for="composante">Choisissez une composante :</label>
            <form:select path="composante" multiple="false" class="form-control">
                <form:option value="" label="--- Select ---" />
                <form:options items="${ListComposantes}" />
            </form:select>
            <form:errors path="composante" cssClass="alert alert-warning"
                         element="div" />
        </div>

        <div class="form-group">
            <label for="CERTIFINFO">CERTIFINFO :</label>
            <form:input class="form-control" path="CERTIFINFO"/>
            <form:errors path="CERTIFINFO" cssClass="alert alert-warning" element="div" />
        </div>

        <div class="form-group">
            <label for="code">code de la formation  :</label>
            <form:input class="form-control" path="code"/>
            <form:errors path="code" cssClass="alert alert-warning" element="div" />
        </div>

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

