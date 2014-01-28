<?php
        
       if(!isSet($_POST)){
               echo "No Data Received";        
        }
        else{
                $walk_conn=mysqli_connect("db.dcs.aber.ac.uk","csadmgp07","c54admgp07","csgp07_13_14") or die("Cannot Connect");
        

                          
                $file = fopen('datafile.txt','ab');	
				$json = implode($_POST);
                $json_array = json_decode($json,true);
           			
			
				$title = $json_array['title'];
				$short_desc = $json_array['short_desc'];
				$long_desc = $json_array['long_desc'];
				$hours = $json_array['hours'];
				$distance = $json_array['distance'];
				$route = $json_array['route'];
				fwrite($file, $title . "\n" . $short_desc . "\n" . $long_desc . "\n" . $hours . "\n" . $distance . "\n" . $route);
					
				$longitude;
				$latitude;
				$time;
				$description;
				$image;
					//if(var->desc == null
					foreach($route as $loc){
					//loop through every location
						$longitude = $loc['longitude'];
						$latitude = $loc['latitude'];
						$time = $loc['time'];
						if(isSet($loc['description'])){
							//If description, location is point of interest
							$description = $loc['description'];
							if(isSet($loc['images'])){
							//If there are images decode and rename them
								foreach($loc['images']as $image){
									$image = string base64_decode ($image);
									file_put_contents('newImage.JPG',$image);
								}
							}
						}
						fwrite($file, "\n" . $loc . " " .  $longitude);
					}
      
				fclose($file);

                //insert post data into tables 
                $sql = "INSERT INTO List_of_Walks values($shortDescription,$longDescription,0)"; 
                mysqli_query($walk_conn,$sql);            
                $sql = "INSERT INTO Photo values($photoName,0)";
                mysqli_query($walk_conn,$sql);
                $sql = "INSERT INTO Location values($latitude,$longitude,0)";
                mysqli_query($walk_conn,$sql);
                

         mysqli_close($walk_conn);
       }
?>