<%@ page import="com.griddynamics.devschool.shop.User" %>
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="index.jsp">My Best Shop</a>
    </div>

    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li class="<%= request.getServletPath().equals("/list.jsp") ? "active" : "" %>"><a href="list.jsp">Products </a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <% if (request.getSession().getAttribute("user") instanceof User) { %>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
            <%= ((User) request.getSession().getAttribute("user")).getName() %> <span class="caret"></span>
          </a>
          <ul class="dropdown-menu">
            <li><a href="logout.jsp">Logout</a></li>
          </ul>
        </li>
        <% } else { %>
        <li class="<%= request.getServletPath().equals("/registration.jsp") ? "active" : "" %>"><a href="registration.jsp">Registration</a></li>
        <% } %>
      </ul>
    </div>
  </div>
</nav>
