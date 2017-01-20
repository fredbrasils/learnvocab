<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<s:message code="idiom.title.list" var="title"/>
<tags:pageTemplate title="${title}">

    <jsp:attribute name="extraScripts">
        <c:url value="/resources/js" var="jsPath" />        
        <script src="${jsPath }/idiom.js" type="text/javascript"></script>
    </jsp:attribute>
        
    <jsp:body>
    
        <div class="w3-main" style="margin:80px 50px 0 350px;">
            
            <h2><s:message code="idiom.title.list"/></h2> 

            <section style="padding: 1em 0;">
                <input id="idiomName" type="text" name="name" value="${idiom.name}" placeholder="<s:message code="idiom.msg.register"/>" cssClass="form-control"/>
                <a href="#" data-register><s:message code="add"/> </a>
            </section>

            <table id="idiomTable" class="table table-bordered table-striped table-hover">
                <tr>
                    <th><s:message code="idiom.name"/></th>
                    <th><s:message code="date.register"/></th>   
                    <security:authorize access="isAuthenticated()">
                        <th colspan="2"><s:message code="action"/></th>                    
                    </security:authorize>
                </tr>
                <c:forEach items="${idioms }" var="idiom">
                    <tr data-tr-show>
                        <td>${idiom.name }</td>
                        <td><fmt:formatDate pattern="dd/MM/yyyy" value="${idiom.dateRegister.time}" /></td>                                
                        <security:authorize access="isAuthenticated()">
                            <td><a href="#" data-edit> <s:message code="edit"/> </a></td>                                
                            <td><a href="#" data-delete="${idiom.id}"><s:message code="delete"/> </a></td>                        
                        </security:authorize>    
                    </tr>
                    <tr data-tr-edit style="display: none">
                        <td colspan="2"><input type="text" value="${idiom.name }" cssClass="form-control"/></td>
                        <td><a href="#" data-update="${idiom.id}"><s:message code="add"/> </a></td>                        
                        <td><a href="#" data-cancel><s:message code="cancel"/> </a></td>                        
                    </tr>
                </c:forEach>
                <c:if test="${empty idioms }">
                    <tr data-tr-show>
                        <td></td>
                        <td></td>
                        <security:authorize access="isAuthenticated()">
                            <td><a href="#" data-edit> <s:message code="edit"/> </a></td>                                
                            <td><a href="#" data-delete=""><s:message code="delete"/> </a></td>                        
                        </security:authorize>    
                    </tr>
                    <tr data-tr-edit style="display: none">
                        <td colspan="2"><input type="text" value="" cssClass="form-control"/></td>
                        <td><a href="#" data-update=""><s:message code="add"/> </a></td>                        
                        <td><a href="#" data-cancel><s:message code="cancel"/> </a></td>                        
                    </tr>
                </c:if>
            </table>
            <security:csrfMetaTags/>
            
        </div>
    </jsp:body>
       
</tags:pageTemplate>
