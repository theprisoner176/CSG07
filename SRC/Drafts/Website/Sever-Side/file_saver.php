<?php
        
       if(!isSet($_POST)){
               echo "No Data Received";        
        }
        else{
                $walk_conn=mysqli_connect("db.dcs.aber.ac.uk","csadmgp07","c54admgp07","csgp07_13_14") or die("Cannot Connect");
        

                          
                $file = fopen('data_file.txt','a+');	
				$json = implode($_POST);
				fwrite($file, $json);
                $json_array = json_decode($json,true);
                //fwrite($file, print_r($json_array['title']));
        			foreach($json_array as $result => $val) {
 						fwrite($file, $result);
 						foreach($val as $arry){
 							fwrite($file, $arry->long_desc);
 						}
              			//fwrite($file, $json_array[0]['walk'][0]['long_desc']);              			
      			
        			
        		}
        			
        		//}
				//else echo "\$json['results'] is not an array";
                //fwrite($file, "JSON =" . $post_data . "\n");
               // $shortDescription=$_POST['shortDesc'];
               // $longDescription=$_POST['longDesc'];
               // $photoName=$_POST['photoName'];
               // $latitude=$_POST['latitude'];
               // $longitude=$_POST['longitude'];
                


				fclose($file);

                //insert post data into tables 
                $sql = "INSERT INTO List_of_Walks values($shortDescription,$longDescription,0)"; 
                mysqli_query($walk_conn,$sql);            
                $sql = "INSERT INTO Photo values($photoName,0)";
                mysqli_query($walk_conn,$sql);
                $sql = "INSERT INTO Location values($latitude,$longitude,0)";
                mysqli_query($walk_conn,$sql);
                
                //mysql_query($sql);

         mysqli_close($walk_conn);
       }
?>