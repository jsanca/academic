<html>
<body>

    <h2>Hello World!</h2>

    <p>
        preconditions: <%= request.getAttribute("precondition")%>
    </p>


    <p>
        <%= request.getAttribute("message")%>
    </p>


    <p>
        postconditions: <%= request.getAttribute("postcondition")%>
    </p>
</body>
</html>
