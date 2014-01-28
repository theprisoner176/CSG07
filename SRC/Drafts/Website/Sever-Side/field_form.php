<?php
session_save_path('../../web/sessions');
session_start();
if(!isSet($_SESSION['gpadmin'])){
		header("Location: logon.php");	
	}
?>

<!doctype html>
<html lang="en">
<head>
	<title>Add info to Database </title>
</head>
<body>
	<h1> Walks</h1>
	<form action="add_fields.php" method="post">
		<p> ID: </p><input type="textbox" name="id"/>
		<p> Title </p><input type="textbox" name="title"/>
		<p> Short Desc </p><input type="textbox" name="sh_de"/>
		<p> Long Desc </p><input type="textbox" name="l_de"/>
		<p> Hours </p><input type="textbox" name="hours"/>
		<p> Distance </p><input type="textbox" name="distance"/>
		<p><input type="submit"/></p>
	</form>
	<h1> Location </h1>
	<form action="add_fields.php" method="post">
		<p> ID: </p><input type="textbox" name="L-id"/>
		<p> WalkID </p><input type="textbox" name="walkid"/>
		<p> Latt </p><input type="textbox" name="latt"/>
		<p> Longitude </p><input type="textbox" name="long"/>
		<p> Time Stamp </p><input type="textbox" name="time"/>
		<p><input type="submit"/></p>
	</form>
	<h1> Place Desc </h1>
	<form action="add_fields.php" method="post">
		<p> ID: </p><input type="textbox" name="p-id"/>
		<p> LocID </p><input type="textbox" name="locid"/>
		<p> desc </p><input type="textbox" name="desc"/>
		<p><input type="submit"/></p>
	</form>
	<h1> Photo </h1>
	<form action="add_fields.php" method="post">
		<p> ID: </p><input type="textbox" name="p-id"/>
		<p> placeID </p><input type="textbox" name="placeid"/>
		<p> photoname </p><input type="textbox" name="photo"/>
		<p><input type="submit"/></p>
	</form>
	
	<a href = "list_table.php">View Tables</a><br>
</body>
</html>
