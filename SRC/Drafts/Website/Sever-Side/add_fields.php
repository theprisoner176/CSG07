<?php
	$walk_id, $walk_title, $walk_short, $walk_long, $walk_hours, $walk_distance;
	/**
	 * checks to see if it's set, no point in doing it if it's not
	 */
	if (isset($_POST["id"])){
			$walk_id = mysql_escape_string($_POST['id']);

	if (isset($_POST["title"])){
			$walk_title = mysql_escape_string($_POST['title']);
	}
	if (isset($_POST["sh_de"])){
			$walk_short = mysql_escape_string($_POST['sh_de']);
	}
	if (isset($_POST["l_de"])){
			$walk_long = mysql_escape_string($_POST['l_de']);
	}
	if (isset($_POST["hours"])){
			$walk_hours = mysql_escape_string($_POST['hours']);
	}
	if (isset($_POST["distance"])){
			$walk_distance = mysql_escape_string($_POST['distance']);
	}

	$con=mysqli_connect("db.dcs.aber.ac.uk","csadmgp07","c54admgp07","csgp07_13_14") or die("Couldn't connect");
	/**
	 * Adds the information as new records to the Walks Table
	 */
	$query = "INSERT INTO Walks (id, title, shortdescription, longdescription, hours, distance) VALUES 
	('$walk_id','$walk_title','$walk_short', '$walk_long', '$walk_hours', '$walk_distance')";

	if (mysqli_query($query)){
		echo "<script> alert('Sent to the Database'); </script>";
		header('Location: field_form.php');
	}
	mysqli_close($con);		






?>
