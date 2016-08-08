package userManagementService;

public class UserTestData {
	public static final String VALID_USER_DATA = "{'id':'9eb28bb6-eb5f-4425-a912-ddf2fddb3761'," +
            "'firstName':'Vinoth'," +
            "'lastName':'Kumar'," +
            "'email':'vinothkumar@gmail.com'," +
            "'address':{" +
            "'street':'8216 5th avenue'," +
            "'city':'New York'," +
            "'zip':'11209'," +
            "'state':'NY'," +
            "'country':'US'}," +
            "'dateCreated':'2016-08-07T07:02:40.896Z'," +
            "'company':{" +
            "'name':'NYU'," +
            "'website':'http://www.nyu.edu'}," +
            "'profilePic':'http://bitly/8867'}";
	
	public static final String VALID_USER_DATA_MODIFIED = 
			"{'id':'4f57985d-8d4e-4b3a-9e94-a390b3d153f5'," +
		            "'firstName':'Vinoth'," +
		            "'lastName':'Kumar'," +
		            "'email':'vinothkumar@gmail.com'," +
		            "'address':{" +
		            "'street':'8216 5th avenue'," +
		            "'city':'New York'," +
		            "'zip':'11209'," +
		            "'state':'NY'," +
		            "'country':'US'}," +
		            "'dateCreated':'2016-08-07T07:02:40.896Z'," +
		            "'company':{" +
		            "'name':'NYU'," +
		            "'website':'http://www.nyu.edu'}," +
		            "'profilePic':'http://bitly/8867'}";
	
    public static final String TEST_ID = "4f57985d-8d4e-4b3a-9e94-a390b3d153f5";


    public static final String INVALID_USER_DATA = "{'firstName':'Vinoth'," +
            "'lastName':'Kumar'," +
            "'email':'vinothkumar@gmail.com'," +
            "'dateCreated':'2016-08-07T05:06:40.896Z'}";

    public static final String NO_USERS_FOUND ='"'+"Database Empty"+'"';

    public static final String USER_CREATED = '"'+"User Creation Successful"+'"';

    public static final String USER_UPDATED = '"'+"User Update Successful"+'"';

}
