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
	  
	  mysqli_close($walk_conn);
	}
?>