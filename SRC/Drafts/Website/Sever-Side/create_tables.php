<?php
 $con=mysqli_connect("db.dcs.aber.ac.uk","csadmgp07","c54admgp07","csgp07_13_14");
 // Check connection
 if (mysqli_connect_errno())
   {
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
   }

 // Create table
 $sql="CREATE TABLE Location(
 id INT NOT NULL AUTO_INCREMENT, 
 PRIMARY KEY(id),
 walkID INT,
 latitude FLOAT,
 longitude FLOAT,
 timestamp, FLOAT
 )";

 // Execute query
 if (mysqli_query($con,$sql))
  {
  echo "Table persons created successfully";
  }
else
  {
  echo "Error creating table: " . mysqli_error($con);
  }
 ?>