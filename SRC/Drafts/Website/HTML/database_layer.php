<?php 
/**
 * A Database connection class. This reduces all common code with handling stuff with the database.
 */
	class DatabaseWalk {
		
		 private $connection;
		 private $query;
		 private $result;
		 
		
		/*
		 * Connects to the database, this sets the handle to the database connection
		 */
		public function connect(){
			$this->connection= mysqli_connect("db.dcs.aber.ac.uk", "csadmgp07","c54admgp07","csgp07_13_14") or die("Couldn't connect");
		}
		
		/**
		 * Prepares a query for the database, but it santises it first
		 * @param is the SQL query sent to the server
		 */
		public function prepare_query($query){
			$this->query = strip_tags(mysqli_escape_string($this->get_connection(), $query));
		}
		
		/**
		 * Same as prepare_query but the query is santised and save for interaction with the database
		 * @param is the sanitised query
		 */
		public function send_query($query){
			$this->result = mysqli_query($this->get_connection(), $query) or die (mysqli_error($this->get_connection()));
		}	
		
		/**
		 * returns the connection from the database
		 */
		public function get_connection(){
				return $this->connection;
		}
		
		/** 
		 * Returns the sanitised query
		 */
		public function get_query (){
				return $this->query;
		}
		
		/**
		 * Returns the result from the Actual query with the database
		 */
		public function get_result(){
				return $this->result;
		}
		
		/**
		 * Closes a connection to a database
		 */
		 public function close_connection(){
			 mysqli_close($this->get_connection());
		 }
		
}
?>
