<?php
session_save_path('../web/sessions');
session_start();
	if(!isSet($_SESSION['gpadmin'])){
		header("Location: logon.php");	
	}
	$con=mysqli_connect("db.dcs.aber.ac.uk","csadmgp07","c54admgp07","csgp07_13_14");

	  $res = mysqli_query($con,"SHOW TABLES");
	  while($cRow = mysqli_fetch_array($res)){
	    echo "<p>" . $cRow[0] . "</p>";

	}
	mysqli_close($con);
			echo <<<EOT
			<p>
			<form action = "d_tables.php" method = "post" name = "tableForm"> 
				<input type = "hidden" name = "table_delete">
				<input type = "submit" value = "Delete Tables" name = "table_delete"/>
			</form>
			</p>
			<a href = "field_form.php">Add Data to Fields</a><br>
			<a href = "admin_login.php">Create Tables</a><br>
			<a href = "logon.php">Logout Page</a>
EOT;
	  

?>
