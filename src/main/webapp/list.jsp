<%@ page import="com.griddynamics.devschool.shop.User" %>
<%@ page import="com.griddynamics.devschool.shop.Store" %>
<%@ page import="com.griddynamics.devschool.shop.Item" %>
<html>
<head>
    <title>Products</title>
    <link href="http://getbootstrap.com/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <%@ include file="header.jsp" %>
    <table class="table table-bordered table-hover">
        <thead>
        <tr>
            <th>#</th>
            <th>Item Name</th>
            <th>Price</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <%
            Store store = new Store((User) request.getSession().getAttribute("user"));
            int i = 1;
            for (Item item: store.getItems()) {
        %>
        <tr>
            <th scope="row"><%= i++ %></th>
            <td><%= item.getName() %></td>
            <td>$<%= item.getPrice() %></td>
            <td>
                <a href="buy.jsp?name=<%= item.getName() %>" class="btn btn-sm btn-info">
                    <i class="glyphicon glyphicon-shopping-cart"></i>
                </a>
            </td>
        </tr>
        <% } %>
        </tbody>
    </table>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="http://getbootstrap.com/dist/js/bootstrap.min.js"></script>
</body>
</html>
