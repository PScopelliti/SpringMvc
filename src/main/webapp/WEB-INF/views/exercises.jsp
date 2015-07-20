<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
Hello Exercise.
<body>
<c:forEach items="${exerciseList}" var="exercise">
    <li id="spittle_<c:out value="exercise.id"/>">
        <div class="spittleMessage">
            <c:out value="${exercise.name}"/>
        </div>
        <div class="spittleLocation">
            <c:out value="${exercise.description}"/>
        </div>
    </li>
</c:forEach>
</body>
</html>