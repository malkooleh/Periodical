package ua.nure.malko.Periodical;

public final class Path {
	
	// pages
	public static final String PAGE_LOGIN = "/login.jsp";
	public static final String PAGE_ERROR_PAGE = "/views/jsp/error_page.jsp";
	public static final String PAGE_LIST_PERIODICALS = "/views/jsp/list_periodicals.jsp";
	public static final String PAGE_LIST_SUBSCRIPTIONS = "/views/jsp/list_subscriptions.jsp";
	public static final String PAGE_SETTINGS = "/views/jsp/settings.jsp";
	public static final String PAGE_USER_SUBSCRIPTION = "/views/jsp/user_subscription.jsp";

	// commands
	public static final String COMMAND_LIST_SUBSCRIPTION = "/controller?command=listSubscriptions";
	public static final String COMMAND_LIST_PERIODICAL = "/controller?command=listPeriodicals";

}