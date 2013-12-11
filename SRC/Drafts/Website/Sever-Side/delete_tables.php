<?php
$con=mysqli_connect("db.dcs.aber.ac.uk","csadmgp07","c54admgp07","csgp07_13_14");
if(! $con ){
  die('Could not connect: ' . mysql_error());
}

echo 'Connected successfully<br />';
$sql = "DROP TABLE Location";
mysql_select_db( 'csgp07_13_14' );
$retval = mysql_query( $sql, $con );
if(! $retval ){
  die('Could not delete table: ' . mysql_error());
}
echo "Table deleted successfully\n";
mysql_close($con);
?>