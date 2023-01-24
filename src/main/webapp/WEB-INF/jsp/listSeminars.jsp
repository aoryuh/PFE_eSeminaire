<%@ include file="/WEB-INF/jsp/struct/header.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="container">
    <h1> Liste des séminaires prévus  </h1>
    <table class="table">
                   <th>Titre </th>
                   <th>Date </th>
                   <th>Localisation </th>
                   <th>Auteur </th>
                   <th>Equipe </th>

                    <c:forEach items="${seminars}" var="seminar">
                            <tr>
                                <td> <c:out value="${seminar.title}" /> </td>
                                <td> <c:out value="${seminar.date}" /> </td>
                                <td> <c:out value="${seminar.location}" /> </td>
                                <td> <c:out value="${seminar.author.name}" /> </td>
                                <td> <c:out value="${seminar.team.name}" /> </td>


                                <td><a href="${seminar.idSeminar}"
                                class="btn btn-outline-primary" type="submit">Afficher </a></td>
                            </tr>
                </c:forEach>
            </table>
</div>

<%@ include file="/WEB-INF/jsp/struct/footer.jsp"%>