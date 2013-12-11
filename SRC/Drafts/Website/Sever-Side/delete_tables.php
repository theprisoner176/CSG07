<?php
$con=mysql_connect("db.dcs.aber.ac.uk","csadmgp07","c54admgp07","csgp07_13_14");
if(! $con ){
  die('Could not connect: ' . mysql_error());
}

echo 'Connected successfully<br />';
$sql = "DROP TABLE IF EXISTS csgp07_13_14 . Location";
mysql_query($sql, $con) or die(mysql_error());
echo "Table deleted successfully\n";
mysql_close($con);
?>