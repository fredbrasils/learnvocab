<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<s:message code="user.title.list" var="title"/>
<tags:pageTemplate title="${title}">

    <jsp:attribute name="extraScripts">
        <c:url value="/resources/js" var="jsPath" />        
        <script src="${jsPath }/user.js" type="text/javascript"></script>
    </jsp:attribute>
        
    <jsp:body>
    
        <h2><s:message code="user.title.list"/></h2>         
        <h3><a href="${s:mvcUrl('UC#formUser').build()}">Cadastrar Usuário</a></h3>
        
        <table id="userTable" class="table table-bordered table-striped table-hover">
            <tr>
                <th><s:message code="user.name"/></th>
                <th><s:message code="user.email"/></th>   
                <th><s:message code="role"/></th>   
                <th><s:message code="date.register"/></th>   
                <security:authorize access="isAuthenticated()">
                    <th colspan="2"><s:message code="action"/></th>                    
                </security:authorize>
            </tr>
            <c:forEach items="${users }" var="user">
                <tr data-tr-show>
                    <td>${user.name }</td>
                    <td>${user.email }</td>
                    <td>${user.rolesName }</td>
                    <td><fmt:formatDate pattern="dd/MM/yyyy" value="${user.dateRegister.time}" /></td>                                
                    <security:authorize access="isAuthenticated()">
                        <td><a href="#" data-edit="${user.id}"> <s:message code="edit"/> </a></td>                                
                        <td><a href="#" data-delete="${user.id}"><s:message code="delete"/> </a></td>                        
                    </security:authorize>    
                </tr>                
            </c:forEach>
                
        </table>
        <security:csrfMetaTags/>    
        
    </jsp:body>
       
</tags:pageTemplate>
