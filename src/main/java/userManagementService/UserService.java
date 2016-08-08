package userManagementService;

import com.google.gson.Gson;
import com.mongodb.*;

import com.mongodb.util.JSON;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Projections;
import com.mongodb.diagnostics.logging.Logger;

import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.omg.CORBA.portable.InputStream;
import org.slf4j.LoggerFactory;

import spark.Request;
import spark.Response;
import spark.utils.IOUtils;

import org.bson.Document;
import org.bson.conversions.Bson;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.Object;
import java.nio.charset.StandardCharsets;

import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.xml.bind.ValidationException;

/**
 * Class which implements all the services
 * <p>
 * Created by vinoth on 8/6/16.
 */

public class UserService {
	
	private final DB db;
    private final DBCollection collection;
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserService(DB db) {
        this.db = db;
        this.collection = db.getCollection("userInfo");
    }

    /**
     * Method which returns all the users in the datastore.
     */
    public List<Document> findAllUser() {
    	this.logger.info("[GET] - Fetching information of all users in the datastore");
        List<Document> todos = new ArrayList<>();
        DBCursor dbObjects = collection.find();
        while (dbObjects.hasNext()) {
            DBObject dbObject = dbObjects.next();
            todos.add(new Document((BasicDBObject) dbObject));
        }
        if(todos.size()==0){
        	this.logger.info("Database empty!");
        	return null;
        }      	
        else
        {
        	this.logger.info("Returning all user information");
        	return todos;
        }
    }
    /**
     * Method to create a new user 
     * @param body
     *        String containing the new user information 
     *        
     * Notes: The 'id' for each user has to be unique. User 'id' is generated in accordance with the UUID standard.
     */
    public void createNewUser(final String body){  
    	this.logger.info("[POST]- Creating a new User in the datastore");
    	 UUID newUserID = UUID.randomUUID();
    	 String ID = newUserID.toString();
    	 DBObject dbObject = (DBObject)JSON.parse(body);
    	 dbObject.put("id", ID);	 
         collection.insert(dbObject);    
         this.logger.info("New User Created with ID: "+ID);
    }
    
    /**
     * Method to update the information for an existing user
     * @param 
     *        String containing the new information for the user 
     * @param body
     *        String containing the new information for the user
     *        
     * @return:
     * 		Success - On successful updation for a valid user
     * 		Error - When specified user is not found in the datastore
     * Notes: The 'id' for each user has to be unique. User 'id' is generated in accordance with the UUID standard.
     */

    public String update(String userId, String body){
    	this.logger.info("[POST] - Updating information for user ID: "+userId);
    	DBObject currentUserObject = findUser(userId);
    	DBObject dbObject = (DBObject) JSON.parse(body);
    	if(currentUserObject!=null){
    		collection.update(new BasicDBObject().append("id", userId), dbObject);
    		this.logger.info("Update successful for user ID:"+userId);
    		return "success";
    	}
    	else{
    		this.logger.info("User ID "+userId+" not found in the datastore");
    		return "error";
    	}          
    }
    /**
     * Method to find a specific user 
     * @param ids
     *        String containing the unique user id
     * @return 
     * 		  Object containing all the information for the specified user  
     */
    public DBObject findUser(String ids) {	
    	this.logger.info("[GET] - Finding information for user ID:"+ids);
    	DBObject query = new BasicDBObject("id", ids);
		BasicDBObject fields = new BasicDBObject();
		DBObject d1 = collection.findOne(query, fields);
		if(d1==null){
			this.logger.info("User Id "+ids+" not found in the datastore");
		}
		return d1;   	
    }
   
}
