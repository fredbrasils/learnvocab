<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<s:message code="box.title.list" var="title"/>
<tags:pageTemplate title="${title}">

    <jsp:attribute name="extraScripts">
        <c:url value="/resources/js" var="jsPath" />        
        <script src="${jsPath }/box.js" type="text/javascript"></script>
    </jsp:attribute>
        
    <jsp:body>
    
        <h2><s:message code="box.title.list"/></h2> 
        
        <section style="padding: 1em 0;">
            <input id="boxName" type="text" name="name" value="${box.name}" placeholder="<s:message code="box.msg.name"/>" cssClass="form-control"/>
            <input id="boxDaysNumber" type="text" name="daysNumber" value="${box.daysNumber}" placeholder="<s:message code="box.msg.daysNumber"/>" cssClass="form-control"/>
            <a href="#" data-register><s:message code="add"/> </a>
        </section>
        
        <table id="boxTable" class="table table-bordered table-striped table-hover">
            <tr>
                <th><s:message code="box.name"/></th>
                <th><s:message code="box.daysNumber"/></th>
                <th><s:message code="box.order"/></th>
                <th><s:message code="date.register"/></th>   
                <security:authorize access="isAuthenticated()">
                    <th colspan="2"><s:message code="action"/></th>                    
                </security:authorize>
            </tr>
            <c:forEach items="${boxes }" var="box">
                <tr data-tr-show>
                    <td>${box.name }</td>
                    <td>${box.daysNumber }</td>
                    <td>${box.order }</td>
                    <td><fmt:formatDate pattern="dd/MM/yyyy" value="${box.dateRegister.time}" /></td>                                
                    <security:authorize access="isAuthenticated()">                        
                        <td><a href="#" data-delete="${box.id}"><s:message code="delete"/> </a></td>                        
                    </security:authorize>    
                </tr>                
            </c:forEach>
            <c:if test="${empty boxes }">
                <tr data-tr-show>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>                                
                    <security:authorize access="isAuthenticated()">
                        <td><a href="#" data-delete=""><s:message code="delete"/> </a></td>                        
                    </security:authorize>    
                </tr>                
            </c:if>
        </table>
        <security:csrfMetaTags/>    
        
    </jsp:body>
       
</tags:pageTemplate>
