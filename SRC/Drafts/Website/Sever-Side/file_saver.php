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

		
		$shortDescription=$_POST['shortDesc'];
		$longDescription=$_POST['longDesc'];
		$photoName=$_POST['photoName'];
		$coordinates=$_POST['coordinates'];
		

		//insert post data into tables 
		$sql = "INSERT INTO createListOfWalksTable values($sortDescription,$longDescription,0)";
		$sql = "INSERT INTO createPhotoTable values($photoName,0)";
		
		
		mysql_query($sql);

	 mysqli_close($walk_conn);
	}
?>