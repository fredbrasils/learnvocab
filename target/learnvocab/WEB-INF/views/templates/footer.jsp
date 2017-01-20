<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<footer class="w3-bottom" style="z-index:4">
    <!--
    <security:authorize access="isAnonymous()">
        <h1>Fim da Página</h1>
    </security:authorize>

    <security:authorize access="isAuthenticated()">            
        <h1>Fim da Página -> </h1>
        <security:authentication property="principal" var="usuario" />
        Usuário: ${usuario.username}
    </security:authorize>
    -->
    
    <ul class="w3-navbar w3-theme-d2 w3-left-align w3-large">
        <li><a href="#" class="w3-padding-large w3-theme-d4"></i>Footer</a></li>
    </ul>
        
</footer>
