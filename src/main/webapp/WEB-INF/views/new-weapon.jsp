<%--
  Created by IntelliJ IDEA.
  User: Flavien
  Date: 26/02/2018
  Time: 14:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New weapon</title>
</head>
<body>
<div>

    <h1><b>Add a new weapon to the Arsenal</b></h1>

    <p><b>Fill the weapons characteristics bellow :</b></p><br/>
    <form action="rep/addWeapon">
        Name:       <input type="text" name="name"><br>
        Type:       <input type="text" name="type"><br>
        Group:      <input type="text" name="group"><br>
        Range:      <input type="text" name="range"><br>
        Mode:       <input type="text" name="mode"><br>
        Damage:     <input type="text" name="damage"><br>
        Penetration:<input type="text" name="penetration"><br>
        Autonomy:   <input type="text" name="autonomy"><br>
        Reload:     <input type="text" name="reload"><br>
        Attributes: <input type="text" name="attributes"><br>
        Weight:     <input type="text" name="weight"><br>
        <input type="text" name="owner" style="display: none" value="${user}"><br>

        <input type="submit" value="Add">
    </form>

</div>

</body>
</html>
