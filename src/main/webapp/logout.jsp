<%
  request.getSession().removeAttribute("user");
  response.setStatus(response.SC_MOVED_TEMPORARILY);
  response.setHeader("Location", "index.jsp");
%>