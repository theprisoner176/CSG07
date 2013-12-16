<?php
session_save_path('../web/sessions');
session_start();
	if(!isSet($_SESSION['gpadmin'])){
		header("Location: logon.php");	
	}
 $con=mysqli_connect("db.dcs.aber.ac.uk","csadmgp07","c54admgp07","csgp07_13_14");

if (mysqli_connect_errno()){
   	   echo "Failed to connect to MySQL: " . mysqli_connect_error();
}

 function createLocationTable($con){ 
 
	
		$sql="CREATE TABLE Location(
			id INT NOT NULL AUTO_INCREMENT,
			PRIMARY KEY(id),
			walkID INT,
			latitude FLOAT,
			longitude FLOAT,
			timestamp FLOAT
		)";
		if (mysqli_query($con,$sql)){
			echo "<p>Table Locations created successfully</p>";
		}
		else {
			echo "<br>Error creating Locations table: " . mysqli_error($con);
		}

 }

function createListOfWalksTable($con){

		$sql="CREATE TABLE List_of_Walks(
			id INT NOT NULL AUTO_INCREMENT,
			PRIMARY KEY(id),
			title VARCHAR (30) NOT NULL,
			shortDesc VARCHAR (100) NOT NULL,
			longDesc VARCHAR (500),
			hours FLOAT,
			distance FLOAT
		)";
		if (mysqli_query($con,$sql)){
			echo "<p>Table List Of Walks created successfully</p>";
		}
		else {
			echo "<br>Error creating List Of Walks table: " . mysqli_error($con);
		}

 }
 
 function createPlaceDescriptionTable($con){

		$sql="CREATE TABLE Place_description(
			id INT NOT NULL AUTO_INCREMENT,
			PRIMARY KEY(id),
			locationId INT NOT NULL,
			description VARCHAR (100) NOT NULL
		)";
		if (mysqli_query($con,$sql)){
			echo "<p>Table Place Description created successfully</p>";
		}
		else {
			echo "<br>Error creating Place Description table: " . mysqli_error($con);
		}
	  
 }
 
 function createPhotoTable($con){

		$sql="CREATE TABLE Photo(
			id INT NOT NULL AUTO_INCREMENT,
			PRIMARY KEY(id),
			placeId INT NOT NULL,
			photoName VARCHAR (100) NOT NULL
		)";
		if (mysqli_query($con,$sql)){
			echo "<p>Table Photos created successfully</p>";
		}
		else {
			echo "<br>Error creating Photo table: " . mysqli_error($con);
		}
	  

 }
 if(isSet($_POST["tableList"])){
	foreach($_POST["tableList"] as $tableID){
		if($tableID == 1){
			createLocationTable($con);
		}
		if($tableID == 2){
			createListOfWalksTable($con);
		}
		if($tableID == 3){
			createPlaceDescriptionTable($con);
		}
		if($tableID == 4){
			createPhotoTable($con);	
		}
	}
}
else{
	echo "<p> No data given</p>";	
}
	mysqli_close($con);
		echo '<p><a href = "list_table.php">Continue </a></p>';
		echo '<p><a href = "admin_login.php">Back </a></p>';
 ?>
