<!DOCTYPE html>
<html lang="en">
<head>
	<title>List of walks</title>
	<link rel="stylesheet" type="text/css" href="../CSS/style.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="../CSS/print.css" media="print" /> 
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <meta charset="utf-8">  
	  <style>
      html, body, #map-canvas {
        height: 100%;
        margin: 0px;
        padding: 0px
      }
    </style>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBMaDwxzucsIfiT1sYyFKWIvDljXOWSeM0&sensor=false"></script>
    <script>
var map;
function initialize() {
  var mapOptions = {
    zoom: 8,
    center: new google.maps.LatLng(52.416667, -4.066667)
  };
  var map = new google.maps.Map(document.getElementById('map-canvas'),
      mapOptions);


  var myLatLng = new google.maps.LatLng(52.416667, -4.066667);
  var marker = new google.maps.Marker({
      position: myLatLng,
      map: map,
      title: 'Aberystwyth'
  });

  var myLatLng = new google.maps.LatLng(52.7077, -2.7541);
  var marker = new google.maps.Marker({
      position: myLatLng,
      map: map,
      title: 'Shrewsbury'
  });

}
google.maps.event.addDomListener(window, 'load', initialize);


    </script>
</head>
<body>
	<header>
		<h1>Aber Tour</h1>
	</header>
	<nav>
		<ul>
			<li class="selected"><a href="#">Home</a></li>
			<li><a href="#">About</a></li>
			<li></li>
			<li><a href="#">Contact</a></li>
			<li><a href="#">Support</a></li>
			<li><a href="#">Log in</a></li>
			<li></li>
		</ul>
	</nav>
	<section id="intro"></section>
		<div id="map-canvas"></div>
						<?php
						
							include "database_layer.php";
							///creates a new instance of the database walk option
							$database = new DatabaseWalk();
							$database->connect();
							$walk = $database->santise_item($_GET['walk']);
							$walk_id = $database->santise_item($_GET['walk_id']);
							echo "<h2>" .  $walk. "</h2>";	
							$query = "SELECT * from List_of_Walks WHERE title='$walk' AND id='$walk_id'";
							$database->prepare_query($query);
							//sends the query
							$database->send_query($database->get_query());
							//outputs the results
							// An option to make it OO with having a class called Walk, and in here creating a new Walk...to which would be outputted in the same way
							while ($walk = mysqli_fetch_array($database->get_result())){
								echo "this is the short desc " . $walk['shortDesc'];
								echo "<br/>";
								echo "This is the long walk for the walk " .  $walk['longDesc'];
								echo "<br/>";
								echo "this is the time taken for the walk ". $walk['hours'];
								echo "<br/>";
								echo "This is the distance for the walk " . $walk['distance'];
							}
							$database->close_connection();							
							
						?>
</body>
</html>
