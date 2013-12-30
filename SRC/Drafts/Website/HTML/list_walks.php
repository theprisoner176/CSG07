<!doctype html>
<html lang="en">
<head>
	<title>List of walks</title>
	<link rel="stylesheet" type="text/css" href="../CSS/style.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="../CSS/print.css" media="print" />
	
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
	<div id="content">
				<article class="stream">
					<header>
						<h2>List Of Walks</h2>
						<p> Below is the list of walks currently available, please click on them to view the walk.</p>
					</header>
						<br />
					<section class="messages">
					
					
					
<script src="http://widgets.twimg.com/j/2/widget.js"></script>
<script>
new TWTR.Widget({
  version: 2,
  type: 'search',
  search: 'html5 or css3',
  interval: 6000,
  title: 'HTML5 / CSS3',
  subject: 'Awesomeness',
  width: 670,
  height: 2200,
  theme: {
    shell: {
      background: '#82d9fd',
      color: '#ffffff'
    },
    tweets: {
      background: '#ffffff',
      color: '#444444',
      links: '#1bbf5f'
    }
  },
  features: {
    scrollbar: false,
    loop: true,
    live: true,
    hashtags: true,
    timestamp: true,
    avatars: true,
    behavior: 'default'
  }
}).render().start();
</script>

					</section>
				</article>
				
		</div>
		
		<aside>
			<section>	
				<?php
				include "database_layer.php";
				///creates a new instance of the database walk option
				$database = new DatabaseWalk();
				//connects to the database
				$database->connect();
				//some Query
				$query ="SELECT * from List_of_Walks";
				//checks against SQL injection
				$database->prepare_query($query);
				//sends the query
				$database->send_query($database->get_query());
				//outputs the results
				// An option to make it OO with having a class called Walk, and in here creating a new Walk...to which would be outputted in the same way
				while ($walk = mysqli_fetch_array($database->get_result())){
						echo "<li>" . $walk["title"] . "</li>";
				}
				$database->close_connection();
				?>
			</section>
			
		</aside>

	<footer>
		<br />
		<p>&nbsp;</p>
	</footer>

</body>
</html>
