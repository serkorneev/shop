<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <c:set var="count" value="1" scope="page" />
        <c:forEach var="item" items="${items}">
            <tr>
                <th scope="row">${count}</th>
                <td>${item.name}</td>
                <td>$${item.price}</td>
                <td>
                    <a href="buy?name=${item.name}" class="btn btn-sm btn-info">
                        <i class="glyphicon glyphicon-shopping-cart"></i>
                    </a>
                </td>
            </tr>
            <c:set var="count" value="${count + 1}" scope="page"/>
        </c:forEach>
        </tbody>
    </table>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="http://getbootstrap.com/dist/js/bootstrap.min.js"></script>
</body>
</html>
