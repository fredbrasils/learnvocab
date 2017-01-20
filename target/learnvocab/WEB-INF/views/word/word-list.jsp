<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<s:message code="word.title.list" var="title"/>
<tags:pageTemplate title="${title}">

    <jsp:attribute name="extraScripts">
        <c:url value="/resources/js" var="jsPath" />        
        <script src="${jsPath }/word.js" type="text/javascript"></script>
    </jsp:attribute>
        
    <jsp:body>
    
        <h2><s:message code="word.title.list"/></h2> 
        
        <section style="padding: 1em 0;">
            <form id="wordForm">
                <input id="wordName" type="text" name="word" value="${word.word}" placeholder="<s:message code="word.msg.register"/>" cssClass="form-control"/>
                <form:select path="word.idiom.id">
                    <s:message code="select" var="select"/>
                    <form:option value="" label="${select}"/>
                    <form:options items="${idioms}" itemLabel="name" itemValue="id"/>
                 </form:select>
                <form:select path="word.priority">
                    <s:message code="select" var="select"/>
                    <form:option value="" label="${select}"/>
                    <form:options items="${priorities}" itemLabel="description"/>
                 </form:select>
                <a href="#" data-register><s:message code="add"/> </a>
            </form>
        </section>
        
        <table id="wordTable" class="table table-bordered table-striped table-hover">
            <tr>
                <th><s:message code="word.word"/></th>
                <th><s:message code="idiom.name"/></th>   
                <th><s:message code="priority"/></th>   
                <th><s:message code="date.register"/></th>   
                <security:authorize access="isAuthenticated()">
                    <th colspan="2"><s:message code="action"/></th>                    
                </security:authorize>
            </tr>
            <c:forEach items="${words }" var="word">
                <tr data-tr-show>
                    <td>${word.word }</td>
                    <td>${word.idiom.name }</td>
                    <td>${word.priority }</td>
                    <td><fmt:formatDate pattern="dd/MM/yyyy" value="${word.dateRegister.time}" /></td>                                
                    <security:authorize access="isAuthenticated()">
                        <td><a href="#" data-edit> <s:message code="edit"/> </a></td>                                
                        <td><a href="#" data-delete="${word.id}"><s:message code="delete"/> </a></td>                        
                    </security:authorize>    
                </tr>
                <tr data-tr-edit style="display: none">
                    <td><input type="text" value="${word.word}" data-obj="${word.word}" name="word" cssClass="form-control"/></td>
                    <td>
                        <form:select path="word.idiom" data-obj="${word.idiom.id }">
                            <s:message code="select" var="select"/>
                            <form:option value="" label="${select}"/>
                            <form:options items="${idioms}" itemLabel="name" itemValue="id"/>
                         </form:select>
                    </td>
                    <td>
                        <form:select path="word.priority" data-obj="${word.priority }">
                            <s:message code="select" var="select"/>
                            <form:option value="" label="${select}"/>
                            <form:options items="${priorities}" itemLabel="description"/>
                         </form:select>
                    </td>
                    <td><a href="#" data-update="${word.id}"><s:message code="add"/> </a></td>                        
                    <td colspan="2"><a href="#" data-cancel><s:message code="cancel"/> </a></td>                        
                </tr>
            </c:forEach>
                
            <c:if test="${empty words }">
                <tr data-tr-show>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>                                
                    <security:authorize access="isAuthenticated()">
                        <td><a href="#" data-edit> <s:message code="edit"/> </a></td>                                
                        <td><a href="#" data-delete=""><s:message code="delete"/> </a></td>                        
                    </security:authorize>    
                </tr>
                <tr data-tr-edit style="display: none">
                    <td colspan="2"><input type="text" value="" cssClass="form-control"/></td>
                    <td>
                        <form:select path="word.idiom">
                            <s:message code="select" var="select"/>
                            <form:option value="" label="${select}"/>
                            <form:options items="${idioms}" itemLabel="name" itemValue="id"/>
                         </form:select>
                    </td>
                    <td>
                        <form:select path="word.priority">
                            <s:message code="select" var="select"/>
                            <form:option value="" label="${select}"/>
                            <form:options items="${priorities}" itemLabel="description"/>
                         </form:select>
                    </td>
                    <td><a href="#" data-update=""><s:message code="add"/> </a></td>                        
                    <td><a href="#" data-cancel><s:message code="cancel"/> </a></td>                        
                </tr>
            </c:if>
                
        </table>
        <security:csrfMetaTags/>    
        
    </jsp:body>
       
</tags:pageTemplate>
