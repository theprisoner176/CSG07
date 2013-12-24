<?php
session_save_path('../web/sessions');
session_start();
	if(!isSet($_SESSION['gpadmin'])){
		header("Location: logon.php");	
	}
	$con=mysqli_connect("db.dcs.aber.ac.uk","csadmgp07","c54admgp07","csgp07_13_14");

	  $res = mysqli_query($con,"SHOW TABLES");
	  $field;
	  $fieldNo = 0;
	  while($cRow = mysqli_fetch_array($res)){
	    echo "<p>" . $cRow[0] . "</p>";
		$field = mysqli_query($con,"SELECT * FROM $cRow[0]");

		echo "<table border='1'>";//list every field in the table
		while($row = mysqli_fetch_array($field)){
			echo "<td>" . $row[$fieldNo] . "</td>";
				$fieldNo++;
		}
		echo "</tr>";
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
