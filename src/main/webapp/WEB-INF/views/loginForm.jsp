<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Login</title>
  
  <c:url value="/resources/css/login" var="cssPath" />  
  <link rel="stylesheet" href="${cssPath }/reset.css">  
  <link rel="stylesheet prefetch" href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500,700,900|RobotoDraft:400,100,300,500,700,900">
  <link rel="stylesheet prefetch" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
  <link rel="stylesheet" href="${cssPath}/style.css">
  
</head>

<body>
  
<!-- Mixins-->
<!-- Pen Title-->
<div class="pen-title">
  <h1>Login Form</h1>
</div>

<div class="container">
  <div class="card"></div>
  
  <!-- Login -->  
  <div class="card">
    <h1 class="title">Login</h1>    
    
    <c:if test="${not empty error}">
            <div>${error}</div>
    </c:if>
    
    <c:if test="${not empty msg}">
            <div>${msg}</div>
    </c:if>
            
    <form:form servletRelativeAction="/login" method="POST">
        <div class="input-container">
          <input type="text" id="Username" required="required" name="username"/>
          <label for="Username">E-mail</label>
          <div class="bar"></div>
        </div>
        
      <div class="input-container">
        <input type="password" id="Password" required="required" name="password"/>
        <label for="Password">Senha</label>
        <div class="bar"></div>
      </div>
      <div class="button-container">        
        <button type="submit" class="btn btn-primary"><span>Logar</span></button>
      </div>
      <div class="footer"><a href="#">Forgot your password?</a></div>
    </form:form>
    
  </div>
  
  <!-- Register -->
  <div class="card alt">
    <div class="toggle"></div>
    <h1 class="title">Register
      <div class="close"></div>
    </h1>
    <form>
      <div class="input-container">
        <input type="text" id="Username" required="required"/>
        <label for="Username">Username</label>
        <div class="bar"></div>
      </div>
      <div class="input-container">
        <input type="password" id="Password" required="required"/>
        <label for="Password">Password</label>
        <div class="bar"></div>
      </div>
      <div class="input-container">
        <input type="password" id="Repeat Password" required="required"/>
        <label for="Repeat Password">Repeat Password</label>
        <div class="bar"></div>
      </div>
      <div class="button-container">
        <button><span>Next</span></button>
      </div>
    </form>
  </div>
</div>

<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<c:url value="/resources/js" var="jsPath" />
<script src="${jsPath }/login.js" type="text/javascript"></script>

</body>
</html>
