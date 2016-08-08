package userManagementService;

import static org.junit.Assert.*;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mongodb.diagnostics.logging.Logger;

import junit.framework.Assert;
import spark.Spark;
import spark.utils.IOUtils;

/**
 * Test for User Resource
 * <p>
 * Created by vinoth on 8/7/16.
 */
public class UserResourceTest {
	//private static final org.slf4j.Logger logger = LoggerFactory.getLogger(UserResourceTest.class);
	
	@BeforeClass
    public static void setUp() throws Exception {
        //logger.info("Starting Spark server!!");
        //logger.info("Connecting to MongoDB!!");
        new appController();
        //logger.info("Server started successfully on port 8080");
    }
	
   
    @Test
    public void testSetUpEndpoints() {
    	
    	String receivedResponse;
        /*The test case below is to check the user count when the database is empty*/      
    	receivedResponse = this.makeRequest("/findAllUser", null, "GET");
        assertEquals("Database Empty! No Users expected", UserTestData.NO_USERS_FOUND, receivedResponse);

        /* The test case below is to create a valid user*/
        receivedResponse = this.makeRequest("/createUser", UserTestData.VALID_USER_DATA, "POST");
        assertEquals("Creating a valid user", UserTestData.USER_CREATED, receivedResponse);
    	
        /*The test case below is to check if the user created above is inserted into the database and check the 
         * functionality for 'findAllUser' */      
    	receivedResponse = this.makeRequest("/findAllUser", null, "GET");
        assertNotEquals("User Information retrieved", UserTestData.NO_USERS_FOUND, receivedResponse);
        
        /* The test case below is to update the user we just created
         * 
         * NOTE: The test user id "TEST_ID" has to be modified in the UserTestData.java class and also in the lines below
         * with the corresponding user id that is created by executing the previous test case. After assigning the correct
         * user id to TEST_ID, uncomment the test case below and comment the other test cases to avoid ambiguity
         * */
    	/*
        String updateUserTestURL = "/updateUser/"+UserTestData.TEST_ID;
        receivedResponse = this.makeRequest(updateUserTestURL, UserTestData.VALID_USER_DATA_MODIFIED, "POST");
        assertEquals("Updating an existing user", UserTestData.USER_UPDATED, receivedResponse);*/
        
        /* The test case below is to check when trying to update an invalid user. Expected response is 404 - User not found*/
        receivedResponse = this.makeRequest("/updateUser/"+"4f57985d-8d4e-4b3a-9e94-a390b3d153f6", null, "POST");
        assertTrue("Updating an Invalid User", receivedResponse.contains("User Not Found"));

    }

    /**
     * Method to create HTTP connection and send request to the service endpints
     * @param URL
     *         URL for the Endpoint
     * @param payLoad
     *         Body of the request in JSON
     * @param method
     *         HTTP Method - Eg: GET,POST,PUT etc
     * @return response content in JSON string format
     */
    private String makeRequest(final String URL, final String payLoad, final String requestType) {
        HttpURLConnection connection = null;
        try {
        	/* Creating a HTTP Connection*/
            final URL url = new URL("http://localhost:8080" + URL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(requestType);
            connection.setRequestProperty("Content-Type", "application/json");
            /* Make the request to the specified endpoint*/
            if (payLoad != null && !payLoad.isEmpty()) {
                connection.setRequestProperty("Content-Length", Integer.toString(payLoad.getBytes().length));
                connection.setRequestProperty("Content-Language", "en-US");
                connection.setUseCaches(false);
                connection.setDoOutput(true);
                final DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
                wr.writeBytes(payLoad);
                wr.close();
            }

            /* Parse the received response*/
            InputStream inputStream = null;
            if (connection.getResponseCode() == 200) {
                inputStream = connection.getInputStream();
            } else {
                inputStream = connection.getErrorStream();
            }
            final String body = IOUtils.toString(inputStream);
            return body;
        } catch (final Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
    @AfterClass
    public static void tearDown() throws Exception {
        //logger.info("Shutting down server on port 8080");
        //Spark.stop();
    }

}
