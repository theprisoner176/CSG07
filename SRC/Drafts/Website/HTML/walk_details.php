<!DOCTYPE html>
<html lang="en">
<head>
	<title>List of walks</title>
	<link rel="stylesheet" type="text/css" href="../CSS/style.css" media="screen" />
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <meta charset="utf-8">  
	  <style>
     html, body , #map-canvas {
        height: 85%;
        margin: 0px;
        padding: 0px
      }
    </style>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBMaDwxzucsIfiT1sYyFKWIvDljXOWSeM0&sensor=false"></script>
    <link rel="stylesheet" type="text/css" href="shadowbox-3.0.3/shadowbox.css">
	<script type="text/javascript" src="shadowbox-3.0.3/shadowbox.js"></script>
	<script type="text/javascript">
		Shadowbox.init();
	</script>
   
</head>
<body>
	<a href="Images/myimage.jpg" rel="shadowbox"><img src="Images/myimage.jpg" height="87" width="156" /></a>
	<header>
		<h1>Aber Tour</h1>
	</header>
	<nav>
		<ul>
			<li><a href="#">Home</a></li>
			<li><a href="list_walks.php">List Walks</a></li>
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
							
							
							$query = "SELECT latitude, longitude FROM Location";
							$database->prepare_query($query);
							$database->send_query($database->get_query());
							$lat = array();
							$long = array();
							while ($value = mysqli_fetch_array($database->get_result())){
										$lat[] = 52.416667;
										$long[] = -4.066667;
							}
							$database->close_connection();										
						?>
						 <script type="text/javascript">
						var map;
						var lat = <?php echo json_encode($lat); ?>;
						var lng = <?php echo json_encode($long); ?>;
						function initialize() {
						var mapOptions = {
							zoom: 8,
							center: new google.maps.LatLng(52.416667, -4.066667)
						};
						var map = new google.maps.Map(document.getElementById('map-canvas'),
								mapOptions);
						
						var contentString = '<div id="content">'+'<h1 id="firstHeading" class="firstHeading"></h1>'+
						'<div id="bodyContent">'+'<p>short desc </p>'+'</div>'+'</div>';
	
						var infowindow = new google.maps.InfoWindow({
							content: contentString
						});
						for (i = 0; i < lat.length; i++){
							var myLatLng = new google.maps.LatLng(lat[i], lng[i]);
						}
						var marker = new google.maps.Marker({
							position: myLatLng,
							map: map,
							title: 'Aberystwyth'
						});
  
						google.maps.event.addListener(marker, 'click', function() {
								infowindow.open(map,marker);
						});

						var myLatLng = new google.maps.LatLng(52.7077, -2.7541);
						var marker = new google.maps.Marker({
								position: myLatLng,
								map: map,
								title: 'Shrewsbury'
						});
  
						var flightPlanCoordinates = [
							new google.maps.LatLng(52.416667, -4.066667),
							new google.maps.LatLng(52.7077, -2.7541),   
						];
  
						var flightPath = new google.maps.Polyline({
							path: flightPlanCoordinates,
							geodesic: true,
							strokeColor: '#FF0000',
							strokeOpacity: 1.0,
							strokeWeight: 2
						});

						flightPath.setMap(map);
						}
						google.maps.event.addDomListener(window, 'load', initialize);
    </script>
</body>
</html>
