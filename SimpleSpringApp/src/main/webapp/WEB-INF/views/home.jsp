<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello from Cloud Foundry!  
</h1>

<P>  The time on the server is ${serverTime}. </P>

<p>  The instance id per Cloud Foundry is ${instance_id }. </p>

<p>  You have been routed to instance index number ${instance_index }. </p>

<p>  Available URI's ${application_uris }. </p>

<h1>Aidan's Holiday Wishlist</h1>

${xmasList.description }


<h1>Instance Image</h1> 
<img alt="${instance_index }" src="/resources/images/${instance_index }a.jpg"/>
</body>
</html>
