<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Registration</title>
  <link href="http://getbootstrap.com/dist/css/bootstrap.min.css" rel="stylesheet">
  <link href="<c:url value="/resources/css/registration.css"/>" rel="stylesheet">
</head>
<body>
<div class="container">
  <%@ include file="header.jsp" %>
  <div class="jumbotron text-center">
    <form:form class="form-signin" method="post" modelAttribute="userForm">
      <h2 class="form-signin-heading">Registration</h2>

      <spring:bind path="name">
        <label class="sr-only">Username</label>
        <form:input path="name" type="text" name="name" class="form-control" placeholder="Enter your name" required="true" autofocus="true" />
      </spring:bind>

      <spring:bind path="email">
        <label class="sr-only">Email address</label>
        <form:input path="email" type="email" name="email" class="form-control" placeholder="Enter Email address" required="true" />
      </spring:bind>

      <button class="btn btn-lg btn-primary btn-block" type="submit">Register</button>
    </form:form>
  </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="http://getbootstrap.com/dist/js/bootstrap.min.js"></script>
</body>
</html>
