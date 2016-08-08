package userManagementService;

import com.google.gson.Gson;
import com.mongodb.diagnostics.logging.Logger;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import org.bson.Document;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;

/**
 * Class to create the endpoints for the User Management Service
 * <p>
 * Created by vinoth on 8/6/16.
 */

public class UserResource {

	    private final UserService userService;
	    private final org.slf4j.Logger logger = LoggerFactory.getLogger(UserResource.class);

	    /**
	     * Constructor
	     *
	     * @param userService
	     *        userService Object which does all the back end operations and connects to the MongoDB
	     */
	    public UserResource(UserService userService) {
	        this.userService = userService;
	        setupEndpoints();
	    }
	    

	    /**
	     * The setUpEndPoints method is defines all the REST points for the User Management Service
	     */
	    private void setupEndpoints() {
	    	this.logger.info("Server Started on Port 8080");
	    	this.logger.info("Setting all configured endpoints");
	        
	    	/**
		     * findAllUser - Endpoint which returns a list of all the users in the datastore
		     */
	        get("/findAllUser", "application/json", (request, response)
	                -> {userService.findAllUser();
	                List<Document> allUserInfo = userService.findAllUser();
	                if(allUserInfo==null){
	                	return "Database Empty";
	                }
	                return allUserInfo ;
	                }, new JsonTransformer());
	        
	        /**
		     * createUser - Endpoint which is used to create a new user in the datastore
		     */
	        post("/createUser", "application/json", (request, response) -> {
	  			userService.createNewUser(request.body());
	            response.status(200);
	            return "User Creation Successful";
	        }, new JsonTransformer());
	        
	        /**
		     * updateUser - Endpoint which is used to update informaton for an existing user in the datastore.
		     * User 'id' is appended to the URL
		     */
	        post("/updateUser/:id", "application/json", (request, response) -> {
	        	String output = userService.update(request.params(":id"), request.body());
	        	if(output=="success"){
	        		response.status(200);
	        		return "User Update Successful";
	        	}
	        	else{
	        		response.status(404);
	        		return "User Not Found";
	        	}
	        }, new JsonTransformer());
	        
	        /**
		     * findUser - Endpoint which returns the information for a particular user
		     * User 'id' is appended to the URL
		     */
	        get("/findUser/:id", "application/json", (request, response)

	                -> userService.findUser(request.params(":id")), new JsonTransformer());
	        	this.logger.info("All services ready to be deployed");
	        
	        
	    }

}
