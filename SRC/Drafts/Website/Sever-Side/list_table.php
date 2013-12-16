<?php
session_save_path('../web/sessions');
session_start();
	if(!isSet($_SESSION['gpadmin'])){
		header("Location: logon.php");	
	}
	$con=mysqli_connect("db.dcs.aber.ac.uk","csadmgp07","c54admgp07","csgp07_13_14");

	  $res = mysqli_query($con,"SHOW TABLES");
	  $tableId = 0;
	  while($cRow = mysqli_fetch_array($res)){
	  if($cRow[0] == 'Location'){
	  		$tableId = 1;
	  }
	   if($cRow[0] == 'List_of_Walks'){
	  		$tableId = 2;
	  }
	   if($cRow[0] == 'Place_description'){
	  		$tableId = 3;
	  }
	   if($cRow[0] == 'Photo'){
	  		$tableId = 4;
	  }
	    echo "<p>" . $cRow[0] . "</p>";
	    echo <<<EOT
	    	
			<form action = "d_tables.php" method = "post" name = "tableForm"> 
				<input type="checkbox" id = "$tableId" name = "tableList[]" value="$tableId" />
				
EOT;

	}
			echo <<<EOT
			<p>
				<input type = "submit" value = "Delete Tables"/>
			</form>
			</p>
			<a href = "admin_login.php">Create Tables</a><br>
			<a href = "logon.php">Logout Page</a>
EOT;
	  
mysqli_close($con);
?>
