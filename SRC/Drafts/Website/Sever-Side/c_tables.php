<?php
session_save_path('../../web/sessions');
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
 
 function joinTables($con){
 
 //Link photo placeID to Location ID --- This is not being run
 $sql = "SELECT Photo.id, Location.id ".
 "FROM Photo, Location ".
	"WHERE Photo.placeId = Location.id";
	
 //Link List_of_Walks ID to Location walkID---Changed---Check
 $sql = "SELECT * FROM Location LEFT JOIN List_of_Walks ON Location.walkID=List_of_Walks.id";

	if (mysqli_query($con,$sql)){
		echo "<p>Linked List_of_Walks ID to Location walkID</p>";
	}
	else {
		echo "<br>Error linking Link List_of_Walks ID to Location walkID: " . mysqli_error($con);
	}
 //Link Place_description locationID to Location ID
	$sql = "SELECT Place_description.id, Location.id ".
 "FROM Place_description, Location ".
	"WHERE Place_description.locationId = Location.id";
	if (mysqli_query($con,$sql)){
		echo "<p>Linked Place_description locationID to Location ID</p>";
	}
	else {
		echo "<br>Error linking Place_description locationID to Location ID: " . mysqli_error($con);
	}
//Link photo placeID to Place_description ID
	$sql = "SELECT Photo.id, Place_description.id ".
 "FROM Photo, Place_description ".
	"WHERE Photo.placeId = Place_description.id";
	
	if (mysqli_query($con,$sql)){
		echo "<p>Linked photo placeID to Place_description ID</p>";
	}
	else {
		echo "<br>Error linking photo placeID to Place_description ID: " . mysqli_error($con);
	}
 }
 
 if(isSet($_POST["table_create"])){

	createLocationTable($con);
	createListOfWalksTable($con);
	createPlaceDescriptionTable($con);
	createPhotoTable($con);
	joinTables($con);
	
}
else{
	echo "<p> No data given</p>";	
}
	mysqli_close($con);
		echo '<p><a href = "list_table.php">Continue </a></p>';
		echo '<p><a href = "admin_login.php">Back </a></p>';
 ?>
