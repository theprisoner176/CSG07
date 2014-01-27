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
      
      
      var contentString1 = '<div id="content">'+
      '<div id="siteNotice">'+
      '</div>'+
      '<h1 id="firstHeading" class="firstHeading">Shrewsbury</h1>'+
      '<div id="bodyContent">'+
      '<p><b>Uluru</b>, also referred to as <b>Ayers Rock</b>, is a large ' +
      'sandstone rock formation in the southern part of the '+
      'Northern Territory, central Australia. It lies 335&#160;km (208&#160;mi) '+
      'south west of the nearest large town, Alice Springs; 450&#160;km '+
      '(280&#160;mi) by road. Kata Tjuta and Uluru are the two major '+
      'features of the Uluru - Kata Tjuta National Park. Uluru is '+
      'sacred to the Pitjantjatjara and Yankunytjatjara, the '+
      'Aboriginal people of the area. It has many springs, waterholes, '+
      'rock caves and ancient paintings. Uluru is listed as a World '+
      'Heritage Site.</p>'+
      '<p>Attribution: Uluru, <a href="http://en.wikipedia.org/w/index.php?title=Uluru&oldid=297882194">'+
      'http://en.wikipedia.org/w/index.php?title=Uluru</a> '+
      '(last visited June 22, 2009).</p>'+
      '</div>'+
      '</div>';
      

var contentString = '<div id="content">'+
      '<div id="siteNotice">'+
      '</div>'+
      '<h1 id="firstHeading" class="firstHeading">Aberystwyth</h1>'+
      '<div id="bodyContent">'+
      '<p><b>Uluru</b>, also referred to as <b>Ayers Rock</b>, is a large ' +
      'sandstone rock formation in the southern part of the '+
      'Northern Territory, central Australia. It lies 335&#160;km (208&#160;mi) '+
      'south west of the nearest large town, Alice Springs; 450&#160;km '+
      '(280&#160;mi) by road. Kata Tjuta and Uluru are the two major '+
      'features of the Uluru - Kata Tjuta National Park. Uluru is '+
      'sacred to the Pitjantjatjara and Yankunytjatjara, the '+
      'Aboriginal people of the area. It has many springs, waterholes, '+
      'rock caves and ancient paintings. Uluru is listed as a World '+
      'Heritage Site.</p>'+
      '<p>Attribution: Uluru, <a href="http://en.wikipedia.org/w/index.php?title=Uluru&oldid=297882194">'+
      'http://en.wikipedia.org/w/index.php?title=Uluru</a> '+
      '(last visited June 22, 2009).</p>'+
      '</div>'+
      '</div>';

  var infowindow1 = new google.maps.InfoWindow({
      content: contentString1
  });

  var infowindow = new google.maps.InfoWindow({
      content: contentString
  });

  var myLatLng = new google.maps.LatLng(52.416667, -4.066667);
  var marker = new google.maps.Marker({
      position: myLatLng,
      map: map,
      title: 'Aberystwyth'
  });
  
    google.maps.event.addListener(marker, 'click', function() {
    infowindow.open(map,marker);
  });

  var myLatLng = new google.maps.LatLng(52.7077, -2.7541);
  var marker1 = new google.maps.Marker({
      position: myLatLng,
      map: map,
      title: 'Shrewsbury'
  });
  
    google.maps.event.addListener(marker1, 'click', function() {
    infowindow1.open(map,marker1);
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
