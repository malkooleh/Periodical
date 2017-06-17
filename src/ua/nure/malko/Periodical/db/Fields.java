package ua.nure.malko.Periodical.db;

/**
 * Holder for fields names of DB tables and beans.
 * 
 * @author D.Kolesnikov
 * 
 */
public final class Fields {
	
	// entities
	public static final String ENTITY_ID = "id";
	
	public static final String USER_LOGIN = "login";
	public static final String USER_PASSWORD = "password";
	public static final String USER_FIRST_NAME = "first_name";
	public static final String USER_LAST_NAME = "last_name";
	public static final String USER_ROLE_ID = "role_id";
	public static final String USER_ACCOUNT = "account";
	
	public static final String SUBSCRIPTION_BILL = "bill";
	public static final String SUBSCRIPTION_USER_ID = "user_id";
	public static final String SUBSCRIPTION_STATUS_ID= "status_id";

	public static final String CATEGORY_NAME = "name";

	// beans
	public static final String USER_SUBSCRIPTION_BEAN_SUBSCRIPTION_ID = "id";	
	public static final String USER_SUBSCRIPTION_BEAN_USER_FIRST_NAME = "first_name";	
	public static final String USER_SUBSCRIPTION_BEAN_USER_LAST_NAME = "last_name";	
	public static final String USER_SUBSCRIPTION_BEAN_SUBSCRIPTION_BILL = "bill";	
	public static final String USER_SUBSCRIPTION_BEAN_STATUS_NAME = "name";

	public static final String PERIODICAL_NAME = "name";
	public static final String PERIODICAL_PRICE = "price";
	public static final String PERIODICAL_CATEGORY_ID = "category_id";
	
}