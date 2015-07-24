<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
    <title>Exercise Details</title>
</head>
<body>
<h1>Register</h1>

<c:out value="${exercise.id}"/><br/>
<c:out value="${exercise.name}"/><br/>
<c:out value="${exercise.description}"/><br/>

</body>
</html>
