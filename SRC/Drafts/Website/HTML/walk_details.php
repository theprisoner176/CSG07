<!DOCTYPE html>
<html lang="en">
<head>
        <title>List of walks</title>
       <link rel="stylesheet" href="../CSS/style.css" media="screen" />
        <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <meta charset="utf-8">  
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBMaDwxzucsIfiT1sYyFKWIvDljXOWSeM0&sensor=false"></script>
    <!-- Lightbox to display images, http://shadowbox-js.com/, used un the Non-commercial Licence agreement -->
    <link rel="stylesheet" type="text/css" href="shadowbox-3.0.3/shadowbox.css">
        <script type="text/javascript" src="shadowbox-3.0.3/shadowbox.js"></script>
        <script type="text/javascript">
                Shadowbox.init();
        </script>
   
</head>
<body>
        <!--<a href="Images/myimage.jpg" rel="shadowbox"><img src="Images/myimage.jpg" height="87" width="156" /></a>-->
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
			<div id="container">
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
                                                                echo "<br/>";
                                                        }
                                                        $query = "SELECT photoName FROM Photo";
                                                        $database->prepare_query($query);
                                                        $database->send_query($database->get_query());                                                      
                                                        $imageid = array();
                                                        //$imageid[] = $database($_GET['photoName']);
                                                        //echo $database->get_result();
                                                        while ($value = mysqli_fetch_array($database->get_result())) {
														$imageid[] = $value['photoName'];
														
                                                        }
                                                        	
                                                        for ($i = 0; $i < count($imageid); $i++){
                                                        		echo "<div id='imagedisplay'>";	
																echo "<div id='thumb'>";
																echo "<a href='http://users.aber.ac.uk/mda/csgp07/images/".$imageid[$i].".jpg'rel='shadowbox'><img src='http://users.aber.ac.uk/mda/csgp07/images/".$imageid[$i].".jpg' height='87' width='156' ></img></a>";
																echo "</div>";
																echo "</div>";
														}
													
                                                        
                                                        
                                                        $query = "SELECT l.latitude, l.longitude, lw.title FROM Location l INNER JOIN List_of_Walks lw ON (lw.id = l.walkID) WHERE l.walkID='$walk_id'";
                                                        $database->prepare_query($query);
                                                        $database->send_query($database->get_query());
                                                        $lat = array();
                                                        $long = array();
                                                        $title = array ();

                                                        while ($value = mysqli_fetch_array($database->get_result())){
                                                                                $lat[] = $value['latitude'];
                                                                                $long[] = $value['longitude'];
                                                                                $title[] = $value['title'];
                                                        }
                                                      
                                                        $database->close_connection();                                                                                
                                                ?>
                                 <script type="text/javascript">
                                                var map;
                                                //sets up the array of lat values from the php array
                                                var lat = <?php echo json_encode($lat); ?>;
                                                //sets up the array of long values from the php long array
                                                var lng = <?php echo json_encode($long); ?>;
                                                var title = <?php echo json_encode($title); ?>;
                                                function initialize() {
                                                var mapOptions = {
                                                        zoom: 8,
                                                        center: new google.maps.LatLng(52.416667, -4.066667)
                                                };
                                                //assigns the instance of map to the map canvas id element we use.
                                                var map = new google.maps.Map(document.getElementById('map-canvas'),
                                                                mapOptions);
                                                //this creates a new popup window when we click on a marker.
                                                var infowindow = new google.maps.InfoWindow();
                                                //An array to store the Lat and Long values
                                                var latLngValues = new Array();
                                                //var marker = new Array();
                                                for (i = 0; i < lat.length; i++){
                                                        //populates the array with the correct google maps co-ordinates.
                                                        latLngValues[i] = new google.maps.LatLng(lng[i], lat[i]);
                                                }
                                                // Reference http://stackoverflow.com/questions/11467070/how-to-set-a-popup-on-markers-with-google-maps-api for moving it 
                                                // inside the function to allow to. Based on the code above, changed to map with our application- Renamed methods to make it 
                                                // more readable, but code is based on the link above.
                                                for (j = 0;j < latLngValues.length; j++){        
                                                        //sets a new marker
                                                        var POI = new google.maps.Marker({
                                                                //sets the position to be the co-ords from the loop above
                                                                position: latLngValues[j],
                                                                map: map
                                                        });
                                                        //adds a listener to each of the marker items
                                                        popupInfoWindow(map, infowindow, "<h4>"+title[j]+"</h4> <br/> <p> Stufdsfdsfdsfsff</p>", POI);
                                                
                                                }  
                                                function popupInfoWindow(map, infowindow, insideMarkerText, POI) {
                                                                google.maps.event.addListener(POI, 'click', function() {
                                                                //sets a standard message TODO: GRAB SOME PHP
                                                                infowindow.setContent(insideMarkerText);
                                                                //opens the info window on the marker on the map
                                                                infowindow.open(map,POI);
                                                        });
                                                        }
                                                //TODO        
                                                
                                                var flightPath = new google.maps.Polyline({
                                                        path: latLngValues,
                                                        geodesic: true,
                                                        strokeColor: '#FF0000',
                                                        strokeOpacity: 1.0,
                                                        strokeWeight: 2
                                                });

                                                flightPath.setMap(map);
                                                }
                                                google.maps.event.addDomListener(window, 'load', initialize);
                                </script>
					</div>
</body>
</html>
