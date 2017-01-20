<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="title" required="true" %>
<%@ attribute name="extraScripts" fragment="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1"/>
    
    <c:url value="/resources/css" var="cssPath" />
    <c:url value="/resources/js" var="jsPath" />
    <link rel="stylesheet" href="${cssPath }/bootstrap.min.css">
    <link rel="stylesheet" href="${cssPath }/bootstrap-theme.min.css">    
    <link rel="stylesheet" href="${cssPath }/message.css">    
    <link rel="stylesheet" href="${cssPath }/w3.css">  
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Open+Sans">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css">
    <script src="${jsPath }/jquery-3.1.1.min.js" type="text/javascript"></script>
    <script src="${jsPath }/learnvocab.js" type="text/javascript"></script>

<!--    <style type="text/css">
            body {
                    padding: 54px 0px;
            }
    </style>-->
    
    <script type="text/javascript">
        
        var contextPath = "${pageContext.request.contextPath}";
        
    </script>
    
    <title>${title} -  Learn Vocab</title>
    
</head>
<body class="w3-light-grey">

    <%@ include file="/WEB-INF/views/templates/header.jsp" %>

    <jsp:doBody />
    <jsp:invoke fragment="extraScripts"></jsp:invoke>
    <%@ include file="/WEB-INF/views/templates/footer.jsp" %>

</body>
</html>