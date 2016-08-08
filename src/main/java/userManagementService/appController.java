package userManagementService;
import com.mongodb.*;
import com.mongodb.diagnostics.logging.Logger;

import static spark.Spark.setIpAddress;
import static spark.Spark.setPort;
import static spark.SparkBase.setIpAddress;
import static spark.SparkBase.setPort;
import static spark.SparkBase.staticFileLocation;

import org.slf4j.LoggerFactory;

/**
 * Driver Class for the User Management Service
 * <p>
 * Created by vinoth on 8/6/16.
 */

public class appController {
	
	   private static final String IP_ADDRESS = System.getenv("EGEN_CHALLENGE") != null ? System.getenv("EGEN_CHALLENGE") : "localhost";
	   private static final int PORT = System.getenv("EGEN_CHALLENGE") != null ? Integer.parseInt(System.getenv("EGEN_CHALLENGE")) : 8080;
	   
	   public appController(){
	   }
	    public static void main(String[] args) throws Exception {
	        setIpAddress(IP_ADDRESS);
	        setPort(PORT);
	        staticFileLocation("/public");        
	        new UserResource(new UserService(mongo()));       
	    }
	    /**
	     * Method to setup the database connection with MongoDB
	     * Database Name: test
	     * Collection Name: userInfo
	     */
	    private static DB mongo() throws Exception {
	        String host = System.getenv("EGEN_CHALLENGE_MONGODB_DB_HOST");
	        if (host == null) {
	            MongoClient mongoClient = new MongoClient("localhost");
	            return mongoClient.getDB("test");
	        }
	        int port = Integer.parseInt(System.getenv("EGEN_CHALLENGE_MONGODB_DB_PORT"));
	        String dbname = System.getenv("test");
	        /*Configure the access credentials to the database */
	        //String username = System.getenv("EGEN_CHALLENGE_MONGODB_USERNAME");
	        //String password = System.getenv("EGEN_CHALLENGE_MONGODB_PASSWORD");
	        MongoClientOptions mongoClientOptions = MongoClientOptions.builder().connectionsPerHost(20).build();
	        MongoClient mongoClient = new MongoClient(new ServerAddress(host, port), mongoClientOptions);
	        mongoClient.setWriteConcern(WriteConcern.SAFE);
	        DB db = mongoClient.getDB(dbname);
	        return db;
	    }

}
