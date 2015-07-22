<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
    <title>Exercise</title>
    <link rel="stylesheet" type="text/css"
          href="<c:url value="/resources/style.css" />">
</head>
<body>
<h1>Register</h1>

<form method="POST">
    Name: <input type="text" name="name"/><br/>
    Description: <input type="text" name="description"/><br/>
    <input type="submit" value="Register"/>
</form>
</body>
</html>
