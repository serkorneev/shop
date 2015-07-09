<%@ page import="com.griddynamics.devschool.shop.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Registration</title>
  <link href="http://getbootstrap.com/dist/css/bootstrap.min.css" rel="stylesheet">
  <link href="css/registration.css" rel="stylesheet">
</head>
<body>
<%
  if (request.getParameter("username") != null && request.getParameter("email") != null) {
    User user = new User();
    user.setEmail(request.getParameter("email"));
    user.setName(request.getParameter("username"));
    request.getSession().setAttribute("user", user);
  }

  if (request.getSession().getAttribute("user") instanceof User) {
    response.setStatus(response.SC_MOVED_TEMPORARILY);
    response.setHeader("Location", "list.jsp");
  }
%>
<div class="container">
  <%@ include file="header.jsp" %>
  <div class="jumbotron text-center">
    <form class="form-signin">
      <h2 class="form-signin-heading">Registration</h2>
      <label class="sr-only">Username</label>
      <input type="text" name="username" class="form-control" placeholder="Enter your name" required="" autofocus="">
      <label class="sr-only">Email address</label>
      <input type="email" name="email" class="form-control" placeholder="Enter Email address" required="">
      <button class="btn btn-lg btn-primary btn-block" type="submit">Register</button>
    </form>
  </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="http://getbootstrap.com/dist/js/bootstrap.min.js"></script>
</body>
</html>
