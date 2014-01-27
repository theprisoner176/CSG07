<?php

echo "<a href=./datafile.txt>go here</a>";

$file = fopen('datafile.txt','ab');

$post_data = implode('|',$_POST);

fwrite($file, "JSON =" . $post_data . "\n");

fclose($file);
?>