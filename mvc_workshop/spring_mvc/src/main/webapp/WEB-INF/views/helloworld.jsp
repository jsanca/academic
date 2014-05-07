<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Spring4 MVC -HelloWorld</title>
</head>
<body>

<h2>Hello World!</h2>


<p>
    Hello : <c:out value="${name}"/>
<ul>

    <c:forEach items="${capabilityList}" var="capability">
        <li>
            <c:out value="${capability.name}"/> /
            <c:out value="${capability.title}"/> /
            <c:out value="${capability.group}"/> /
            <c:out value="${capability.technology}"/>
        </li>
    </c:forEach>

</ul>
</p>


</body>
</html>