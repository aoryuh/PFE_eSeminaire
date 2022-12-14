<%@ include file="/WEB-INF/jsp/struct/header.jsp"%>
<c:url var="app" value="/app.js" />

<div id="myApp">
  <div class="container">
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
      <a class="navbar-brand" href="#/">Liste des personnes</a>
      <a class="navbar-brand" href="#/mon-profil">Mon profil</a>
    </nav>
    <component :is="currentView" />
  </div>
</div>

<script src="${app}" type="module"></script>


<%@ include file="/WEB-INF/jsp/struct/footer.jsp"%>