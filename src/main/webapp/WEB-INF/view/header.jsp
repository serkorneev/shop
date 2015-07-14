<%@ page import="com.griddynamics.devschool.shop.entity.User" %>
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="/">My Best Shop</a>
    </div>

    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li><a href="list">Products </a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <% if (request.getSession().getAttribute("user") instanceof User) { %>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
            <%= ((User) request.getSession().getAttribute("user")).getName() %> <span class="caret"></span>
          </a>
          <ul class="dropdown-menu">
            <li><a href="logout">Logout</a></li>
          </ul>
        </li>
        <% } else { %>
        <li><a href="registration">Registration</a></li>
        <% } %>
      </ul>
    </div>
  </div>
</nav>
