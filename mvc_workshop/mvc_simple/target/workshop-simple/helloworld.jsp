<%@ page import="cr.prodigious.Capability" %>
<%@ page import="java.text.MessageFormat" %>
<html>
<body>

<h2>Hello World!</h2>


<p>
    <%= request.getAttribute("message")%>

    <ul>
    <%
        final java.util.List<Capability> capabilityList =
            (java.util.List<Capability>)request.getAttribute("capabilityList");

        if (null != capabilityList) {

            for (Capability capability : capabilityList) {

               out.println(MessageFormat.format
                       ("<li>{0} / <br/> {1} / <br/> {2} / <br/> {3} </li>",
                               capability.getName(),
                               capability.getTitle(),
                               capability.getGroup(),
                               capability.getTechnology()));
            }
        }
    %>
</ul>
</p>


</body>
</html>
