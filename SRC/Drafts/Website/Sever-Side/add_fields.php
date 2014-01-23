<?php
session_save_path('../web/sessions');
session_start();
if(!isSet($_SESSION['gpadmin'])){
		header("Location: logon.php");	
	}

	$walk_id; 
	$walk_title; 
	$walk_short; 
	$walk_long; 
	$walk_hours;
	$walk_distance;
	/**
	 * checks to see if it's set, no point in doing it if it's not
	 */
	 $con=mysqli_connect("db.dcs.aber.ac.uk","csadmgp07","c54admgp07","csgp07_13_14") or die("Couldn't connect");
	if (isset($_POST["id"])){
			$walk_id = mysqli_real_escape_string($con,$_POST['id']);
	}
	if (isset($_POST["title"])){
			$walk_title = mysqli_real_escape_string($con,$_POST['title']);
	}
	if (isset($_POST["sh_de"])){
			$walk_short = mysqli_real_escape_string($con,$_POST['sh_de']);
	}
	if (isset($_POST["l_de"])){
			$walk_long = mysqli_real_escape_string($con,$_POST['l_de']);
	}
	if (isset($_POST["hours"])){
			$walk_hours = mysqli_real_escape_string($con,$_POST['hours']);
	}
	if (isset($_POST["distance"])){
			$walk_distance = mysqli_real_escape_string($con,$_POST['distance']);
	}

	
	/**
	 * Adds the information as new records to the Walks Table
	 */
	$query = "INSERT INTO List_of_Walks (id, title, shortDesc, longDesc, hours, distance) VALUES 
	('$walk_id','$walk_title','$walk_short', '$walk_long', '$walk_hours', '$walk_distance')";

	if (mysqli_query($con,$query)){
		echo "Sent to the Database";
	}
	else{
		echo "Could not add data" . mysqli_error($con);
	}
		echo'<p><a href = "list_table.php">View Tables</a><br>
		<a href = "field_form.php">Add Data to Fields</a><br>
		<a href = "logon.php">Logout Page</a></p>';
		mysqli_close($con);	
?>