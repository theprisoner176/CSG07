<?php
	require_once "../Website/HTML/database_layer.php";
	class DatabaseSet extends PHPUnit_Framework_TestCase { 
		
		
		/**
		 * Tests a query to make sure that it equals the value which is stored by the instance of the database.
		 * @depends set_up
		 */ 
		public function test_query(){
			$query = "SELECT * FROM Description";
			$result = $this->connection->prepare_query($query);
			$this->assertEquals($query, $this->connection->get_query());
		}
		/**
		 * Tests to make sure a connection is valid 
		 * @depends set_up
		 */
		public function test_connection(){
			$this->assertTrue($this->connection, $this->connection->get_connection());		
		}
		/**
		 * Sets up the connection for the database by creating an instance of the connection
		 */
		public function set_up(){
				$this->connection = new Database();
				$this->connection->connect();
		}
		/**
		 * Removes the instance
		 */
		public function tear_down(){
				unset($this->connection);
		}
	}
?>
