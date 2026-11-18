<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <title>JSP Scriptlet</title>
</head>
<body>
    <h1>JSP Scriptlet</h1>

    <%
        Date date = new Date();
        String host = request.getRemoteHost();
    %>

    <p>Current time: <%= date %></p>
    <p>Remote host: <%= host %></p>

</body>
</html>