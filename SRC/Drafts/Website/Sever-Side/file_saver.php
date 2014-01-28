<?php        
   if(!isSet($_POST)){
		   echo "No Data Received";        
	}
	else{
	$file;
	$json_array;
	$route;
	$sql;
	$walk_conn;
	$post = $_POST;
	//function init($walk_conn, $file, $post){
		$walk_conn=mysqli_connect("db.dcs.aber.ac.uk","csadmgp07","c54admgp07","csgp07_13_14") or die("Cannot Connect");
				   
		$file = fopen('datafile.txt','ab');	
		$json = implode($post);
		$json_array = json_decode($json,true);
//	}
	
	//function readWalk($walk_conn, $json_array, $route, $file){
		//getting the json values
		$title = $json_array['title'];
		$short_desc = $json_array['short_desc'];
		$long_desc = $json_array['long_desc'];
		$hours = $json_array['hours'];
		$distance = $json_array['distance'];
		$route = $json_array['route'];
		
		
		//write($file, $title . "\n" . $short_desc . "\n" . $long_desc . "\n" . $hours . "\n" . $distance . "\n" . $route);
			
		//add data to table
		$sql = "INSERT INTO List_of_Walks
		(title, shortDesc, longDesc, hours, distance)
		VALUES($title, $short_desc,$long_desc,$hours,$distance)"; 
		mysqli_query($walk_conn,$sql);
	//}
	
	//function readLocation($route, $file){
		foreach($route as $loc){
			//loop through every location
			$longitude = $loc['longitude'];
			$latitude = $loc['latitude'];
			$time = $loc['time'];
			
			$sql = "INSERT INTO Location values
			(latitude, longitude, timestamp)
			($latitude,$longitude, $time)";
			mysqli_query($walk_conn,$sql);
			
			
			if(isSet($loc['description'])){
				//If description, location is point of interest
				$description = $loc['description'];
				$sql = "INSERT INTO Photo(description)
						values($description)";
				if(isSet($loc['images'])){
				$c = 0;
				//If there are images decode and rename them
					foreach($loc['images']as $image){
						$image = implode($image);
						$image = str_replace("\\", "", $image, $count);
						$image =  base64_decode($image);
						file_put_contents($c . ".jpg",$image);
						$c++;
						$sql = "INSERT INTO Photo(photoName)
						values($photoName)";
						mysqli_query($walk_conn,$sql);
					}
				}
			}
		fwrite($file, "\n" . $loc . " " .  $longitude);
		}
	//}
	
	/*function readJson($post, $walk_conn, $json_array, $route, $file){
		init($walk_conn, $file, $post);
		readWalk($walk_conn, $json_array, $route, $file);
		readLocation($route, $file);
		fclose($file);
		mysqli_close($walk_conn);
	}*/
	
	//readJson($_POST, $walk_conn, $json_array, $route, $file);
   }
?>