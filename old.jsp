
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <title>Old Person</title>
</head>
<body>

    <jsp:useBean id="person"
                 type="lab.mvc.model.Person"
                 scope="request" />

    <h1>Old Person</h1>

    <p>First Name:
        <jsp:getProperty name="person" property="firstName" />
    </p>

    <p>Last Name:
        <jsp:getProperty name="person" property="lastName" />
    </p>

    <p>Birth Day:
        <jsp:getProperty name="person" property="birthDay" />
    </p>

    <p>Sex:
        <jsp:getProperty name="person" property="sex" />
    </p>

    <h2>You were born before 1990!</h2>

</body>
</html>
