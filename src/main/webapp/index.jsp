<%@ page import="com.griddynamics.devschool.shop.User" %>
<%
    if (request.getSession().getAttribute("user") instanceof User) {
        response.setStatus(response.SC_MOVED_TEMPORARILY);
        response.setHeader("Location", "list.jsp");
    }
%>
<html>
<head>
    <title>Welcome</title>
    <link href="http://getbootstrap.com/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <%@ include file="header.jsp" %>
    <div class="jumbotron text-center">
        <h1>Welcome!</h1>
        <span>
            <a class="btn btn-lg btn-primary" href="list.jsp" role="button">View products</a>
            <a class="btn btn-lg btn-info" href="registration.jsp" role="button">Registration</a>
        </span>
    </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="http://getbootstrap.com/dist/js/bootstrap.min.js"></script>
</body>
</html>
