<%@ include file="/WEB-INF/jsp/header.jsp"%>
<div>
    <%@ include file="/WEB-INF/jsp/nav.jsp"%>
</div>

<c:url var="edit" value="formationDetails/edit" />
<c:url var="sessions" value="formationDetails/sessions" />



<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="formationCard">
    <div class="card">
        <p class="title"><c:out value="Date de création" /></p>
        <p> <c:out value="${formation.dateCrea}" /> </p>
        <p class="title"><c:out value="Date de modification" /></p>
        <p> <c:out value="${formation.dateMaj}" /> </p>
        <p class="title"><c:out value="Intitulé" /></p>
        <p><c:out value="${formation.intitule}" /></p>
        <p class="title"><c:out value="Code formation" /></p>
        <p><c:out value="${formation.code}" /></p>
        <p class="title"><c:out value="Etat d'edition" /></p>
        <p> <c:out value="${formation.etatEdition}" /> </p>
        <p class="title"><c:out value="Objectif" /></p>
        <p><c:out value="${formation.objectif}" /></p>
        <p class="title"><c:out value="Type de Parcours (code)" /></p>
        <p><c:out value="${formation.typeParcours}" /></p>
        <p></p>
        <p class="title"><c:out value="Niveau obligatoire" /></p>
        <p><c:out value="${formation.action.niveauObligatoire}" /></p>
        <p class="title"><c:out value="Code \"Modalité d'enseignement\"" /></p>
        <p><c:out value="${formation.action.modaliteEnseignement}" />
    </p><p class="title"><c:out value="Code \"rythme de formation\"" /></p>
        <p><c:out value="${formation.action.rythmeFormation}" /></p>
        </p><p class="title"><c:out value="Conditions spécifiques" /></p>
        <p><c:out value="${formation.action.conditionsSpecifiques}" />
        <p class="title"><c:out value="Modalités d'entrée/sortie" /></p>
        <p><c:out value="${formation.action.modaliteEntreeSortie}" />
        </p><p class="title"><c:out value="url" /></p>
        <p><c:out value="${formation.action.url}" /></p>
        <p class="title"><c:out value="restauration" /></p>
        <p><c:out value="${formation.action.restauration}" /></p>
        <p class="title"><c:out value="hebergement" /></p>
        <p><c:out value="${formation.action.hebergement}" />
        </p><p class="title"><c:out value="transport" /></p>
        <p><c:out value="${formation.action.transport}" /></p>
        <p class="title"><c:out value="Acces handicapes" /></p>
        <p><c:out value="${formation.action.accesHandicapes}" /></p>
        <p class="title"><c:out value="Langue de la formation" /></p>
        <p><c:out value="${formation.action.langueFormation}" />
        </p><p class="title"><c:out value="Modalités de recrutement" /></p>
        <p><c:out value="${formation.action.modaliteRecrutement}" /></p>
        <p class="title"><c:out value="Modalites pédagogique" /></p>
        <p><c:out value="${formation.action.modalitePedagogique}" /></p>
        <p class="title"><c:out value="code \"Périmètre de recrutement\"" /></p>
        <p><c:out value="${formation.action.perimetreRecrutement}" />
        </p><p class="title"><c:out value="Infos sur le périmètre de recrutement" /></p>
        <p><c:out value="${formation.action.infoPerimetreRecrutement}" /></p>
        <p class="title"><c:out value="Nombre d'heure en centre de formation" /></p>
        <p><c:out value="${formation.action.nombreHeureCentre}" /></p>
        <p class="title"><c:out value="Nombre d'heure en entreprise" /></p>
        <p><c:out value="${formation.action.nombreHeureEntreprise}" />
        </p><p class="title"><c:out value="Modalités d'accès pour les handicaps" /></p>
        <p><c:out value="${formation.action.modalitesHandicap}" /></p>
        <p class="title"><c:out value="Code \"Modalités d'admission\"" /></p>
        <p><c:out value="${formation.action.modaliteAdmission}" /></p>
        <p class="title"><c:out value="Informations d'admission" /></p>
        <p><c:out value="${formation.action.infosAdmission}" /></p>
        <p class="title"><c:out value="Frais ANPEC" /></p>
        <p><c:out value="${formation.action.fraisANPEC}" /></p>
        <p class="title"><c:out value="Frais ANPEC compris dans le coût d'inscription" /></p>
        <p><c:out value="${formation.action.fraisInclusANPEC}" /></p>
        <p class="title"><c:out value="Détail des frais ANPEC" /></p>
        <p><c:out value="${formation.action.detailsFraisANPEC}" /></p>
        <p class="title"><c:out value="Code \"Modèle économique\"" /></p>
        <p><c:out value="${formation.action.modeleEconomique}" /></p>
        <p class="title"><c:out value="Autres services" /></p>
        <p><c:out value="${formation.action.autresServices}" /></p>
        <p class="title"><c:out value="taux TVA" /></p>
        <p><c:out value="${formation.action.tauxTVA}" /></p>
        <p class="title"><c:out value="frais hors taxes" /></p>
        <p><c:out value="${formation.action.fraisHT}" /></p>
        <p class="title"><c:out value="Frais toutes taxes comprises" /></p>
        <p><c:out value="${formation.action.fraisTTC}" /></p>
        <p class="title"><c:out value="pré-requis" /></p>
        <p><c:out value="${formation.action.preRequis}" /></p>
        <p class="title"><c:out value="adresse" /></p>
        <p><c:out value="${formation.adresse.ligne}" /></p>
    </div>

    <p>
        <a class="btn btn-info" href="${edit}?id=${formation.id}">Modifier la Formation</a>
        <a class="btn btn-info" href="${sessions}?id=${formation.id}">Gérer les sessions</a>

    </p>
</div>


    <%@ include file="/WEB-INF/jsp/footer.jsp"%>
