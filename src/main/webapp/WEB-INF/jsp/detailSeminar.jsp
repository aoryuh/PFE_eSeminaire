<%@ include file="/WEB-INF/jsp/struct/header.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="p-3"></div>

<div class="card border-primary mb-3 mx-auto p-4" style="max-width: 30rem;">
	<div class="card-body text-dark mx-auto p-4">
		<h3 >Informations sur le seminaire</h3>
		<ul>
            <li> Titre : ${seminar.title}  </li>
            <li> Date : ${seminar.date}  </li>
            <li> Location : ${seminar.location}  </li>
            <li> Auteur : ${seminar.author.name} </li>
            <li> Equipe : ${seminar.team.name} </li>
            <li> Description detaillee : ${seminar.description} </li>

		</ul>
	</div>
</div>

<%@ include file="/WEB-INF/jsp/struct/footer.jsp"%>