<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
    <%--<script type='text/javascript' src="<c:url value="/resources/js/app.js" />"></script> --%>

</head>

<body>

<div>

    <h1>Welcome <b><c:out value="${user}"/></b></h1>

    <a href="rep/weapons">See all weapons</a><br/>

    <a href="rep/validateWeapons">Validate weapons</a><br/>

    <a href="newWeapon">Add a new Weapon</a><br/>

    <a href="<c:url value="/" />">Back</a>

</div>

</body>

</html>



