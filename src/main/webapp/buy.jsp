<%@ page import="com.griddynamics.devschool.shop.User" %>
<%@ page import="com.griddynamics.devschool.shop.Store" %>
<%@ page import="com.griddynamics.devschool.shop.exception.AccessDeniedException" %>
<%@ page import="com.griddynamics.devschool.shop.exception.NotFoundException" %>
<%
    Store store = new Store((User) request.getSession().getAttribute("user"));
    response.setStatus(response.SC_MOVED_TEMPORARILY);
    try {
        store.buy(request.getParameter("name"));
        response.setHeader("Location", "list.jsp");
    } catch (AccessDeniedException e) {
        response.setHeader("Location", "registration.jsp");
    } catch (NotFoundException e) {
        response.setHeader("Location", "list.jsp");
    }
%>
