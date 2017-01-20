<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<s:message code="user.title.register" var="title"/>
<tags:pageTemplate title="${title}">

    <jsp:attribute name="extraScripts">
        <c:url value="/resources/js" var="jsPath" />        
        <script src="${jsPath }/user.js" type="text/javascript"></script>
    </jsp:attribute>
        
    <jsp:body>
        
        <form:form id="userForm" modelAttribute="user">
            <div class="form-group">
                <label><s:message code="user.name" /></label>
                <input type="text" name="name" value="${user.name }" cssClass="form-control"/>            
            </div>            

            <div class="form-group">            
                <label><s:message code="user.email" /></label>
                <input type="text" name="email" value="${user.email }" cssClass="form-control"/>            
            </div>            

            <div class="form-group">            
                <label><s:message code="user.password" /></label>
                <input type="password" name="password" value="${user.password }" cssClass="form-control"/>
            </div>            

            <div class="form-group">            
                <label><s:message code="role" /></label>
                <form:select path="roles" multiple="true">                    
                    <form:options items="${roles}" />
                </form:select>
            </div>            

            <input type="hidden" name="id" value="${user.id }"/>            

            <button type="submit" class="btn btn-primary">Cadastrar</button>
        </form:form>    
        <security:csrfMetaTags/>    
        
    </jsp:body>
       
</tags:pageTemplate>
