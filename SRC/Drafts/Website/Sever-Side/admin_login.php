<?php 
session_save_path('../web/sessions');
session_start();
	if(!isSet($_SESSION['gpadmin'])){
		header("Location: logon.php");	
	}
?>
<!DOCTYPE html> 


<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	
	
	<title>Table Control</title>	
	</head>
	
	<body> 
		<?php
			$tableId = 0;
			while($tableId < 4){
				if($tableId == 0){
					echo "<p>Location";
				}
					
				elseif($tableId == 1){
					echo "<p>List of Walks";
				}
				
				elseif($tableId == 2){
					echo "<p>Place Description";
				}
				
				elseif($tableId == 3){
					echo "<p>Photos";
				}
				
				$tableId++;
				echo <<<EOT
					<form action = "c_tables.php" method = "post" name = "tableForm"> 
						<input type="checkbox" id = "$tableId" name = "tableList[]" value="$tableId" />
					</p>
EOT;
			}
			echo <<<EOT
			<p>
				<input type = "submit" value = "Create Tables"/>
			</form>
			</p>
EOT;
		?>
		<a href = "list_table.php">View Tables</a><br>
		<a href = "logon.php">Logout Page</a>
	</body>
</html>
