package userManagementService;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertNotEquals;

import com.mongodb.*;

import spark.Spark;

public class appControllerTest {
	public static UserService service;
	public static UserResource resource;
	public static MongoClientOptions mongoClientOptions;
	public static MongoClient mongoClient;
	private static final String TEST = "test";

	
	/**
     * Using a test DB named "test" for unit testing
     */
	
	@BeforeClass
	public static void BeforeClass(){
		mongoClientOptions = MongoClientOptions.builder().connectionsPerHost(20).build();
        mongoClient = new MongoClient(new ServerAddress("localhost", 8080), mongoClientOptions);
	}
	@Test
    public void testMongo_ConnectionSetup() throws Exception {
        
        mongoClient.setWriteConcern(WriteConcern.SAFE);
        DB db = mongoClient.getDB("test");
		service = new UserService(db);
        resource = new UserResource(service);
        appController app = new appController();
        Thread.sleep(2000);
        assertNotEquals("Test Connection Setup",db,null);
        
    }

    /**
     * Close the connection and stop the server
     * @throws Exception
     */
    @AfterClass
    public static void tearDown() throws Exception {
    	mongoClient.close();
        Spark.stop();
    }

}
