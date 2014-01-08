<?php
	$walk_title = $_POST["title"];//to do
	
	if(!isSet($_POST["walk_data"])){
		echo "No Data Received";	
	}
	else{
		$walk_conn=mysqli_connect("db.dcs.aber.ac.uk","csadmgp07","c54admgp07","csgp07_13_14");
	
		if (mysqli_connect_errno()) {
		  echo "Cannot Connect: " . mysqli_connect_error();
		}
		else{
			echo "Connected";	
		}
			  
		$json = $_REQUEST['json'];
		echo "JSON: \n";
		var_dump($json);
		echo "\n\n";

		$data = json_decode($json,true);
		var_dump($data);


		//insert all table Variables
		
		$shortDescription=$data['shortDesc'];
		$longDescription=$data['longDesc'];
		$photoName=$data['photoName'];
		$titleName=$data['titleName'];
		$coordinates=$data['coordinates'];
		

		//Get Table Name and field names
		$sql = "INSERT INTO createListOfWalksTable(shortDesc, longDes,) values($sortDescription,$longDescription,0)";
		$sql = "INSERT INTO createPhotoTable(photoName) values($photoName,0)";
		
		
		mysql_query($sql);

	 mysqli_close($walk_conn);
	}
?>