<%@ include file="/WEB-INF/jsp/header.jsp"%>
<div><%@ include file="/WEB-INF/jsp/nav.jsp"%></div>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<div class="container">
    <h1> Edition de l'adresse</h1>

    <form:form method="post" modelAttribute="adresse">



        <div class="form-group">
            <label for="ligne"> Tapez l'adresse : </label>
            <form:input path="ligne" class="form-control" />
            <form:errors path="ligne" cssClass="alert alert-warning" element="div" />
        </div>

        <div class="form-group">
            <label for="nom"> Tapez le nom : </label>
            <form:input path="nom" class="form-control" />
            <form:errors path="nom" cssClass="alert alert-warning" element="div" />
        </div>


        <div class="form-group">
            <label for="ligne2"> Tapez la ligne 2 : </label>
            <form:input path="ligne2" class="form-control" />
            <form:errors path="ligne2" cssClass="alert alert-warning" element="div" />
        </div>


        <div class="form-group">
            <label for="ligne3"> Tapez la ligne 3 : </label>
            <form:input path="ligne3" class="form-control" />
            <form:errors path="ligne3" cssClass="alert alert-warning" element="div" />
        </div>

        <div class="form-group">
            <label for="codePostal"> Tapez le code postal : </label>
            <form:input path="codePostal" class="form-control" />
            <form:errors path="codePostal" cssClass="alert alert-warning" element="div" />
        </div>


        <div class="form-group">
            <label for="codePays"> Tapez le code pays : </label>
            <form:input path="codePays" class="form-control" />
            <form:errors path="codePays" cssClass="alert alert-warning" element="div" />
        </div>

        <div class="form-group">
            <label for="numerVoie"> Tapez le numero de voie : </label>
            <form:input path="numerVoie" class="form-control" />
            <form:errors path="numerVoie" cssClass="alert alert-warning" element="div" />
        </div>

        <div class="form-group">
            <label for="libelleVoie"> Tapez le libelleVoie : </label>
            <form:input path="libelleVoie" class="form-control" />
            <form:errors path="libelleVoie" cssClass="alert alert-warning" element="div" />
        </div>


        <div class="form-group">
            <label for="accessibiliteBatimentaire"> Tapez l'accessibiliteBatimentaire : </label>
            <form:input path="accessibiliteBatimentaire" class="form-control" />
            <form:errors path="accessibiliteBatimentaire" cssClass="alert alert-warning" element="div" />
        </div>

        <div class="form-group">
            <label for="conformiteReglementaire"> Tapez le conformiteReglementaire : </label>
            Oui<form:radiobutton path="conformiteReglementaire" value="1"/>
            Non<form:radiobutton path="conformiteReglementaire" value="0"/>

            <form:errors path="conformiteReglementaire" cssClass="alert alert-warning" element="div" />
        </div>

        <div>
            <td><input type="submit" value="Update" /></td>
        </div>


    </form:form>
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp"%>

