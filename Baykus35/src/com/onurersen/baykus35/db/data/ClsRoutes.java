package com.onurersen.baykus35.db.data;
/**
 * 
 * @author onurersen
 *
 */
public class ClsRoutes {

	private int RouteId;

	private int RouteNumber;
	
	private String RouteName;

	private String RouteDescription;

	private String FirstStopName;

	private String LastStopName;

	/**
	 * @return the routeId
	 */
	public int getRouteId() {
		return RouteId;
	}

	/**
	 * @param routeId the routeId to set
	 */
	public void setRouteId(int routeId) {
		RouteId = routeId;
	}

	/**
	 * @return the routeNumber
	 */
	public int getRouteNumber() {
		return RouteNumber;
	}

	/**
	 * @param routeNumber the routeNumber to set
	 */
	public void setRouteNumber(int routeNumber) {
		RouteNumber = routeNumber;
	}

	/**
	 * @return the routeName
	 */
	public String getRouteName() {
		return RouteName;
	}

	/**
	 * @param routeName the routeName to set
	 */
	public void setRouteName(String routeName) {
		RouteName = routeName;
	}

	/**
	 * @return the routeDescription
	 */
	public String getRouteDescription() {
		return RouteDescription;
	}

	/**
	 * @param routeDescription the routeDescription to set
	 */
	public void setRouteDescription(String routeDescription) {
		RouteDescription = routeDescription;
	}

	/**
	 * @return the firstStopName
	 */
	public String getFirstStopName() {
		return FirstStopName;
	}

	/**
	 * @param firstStopName the firstStopName to set
	 */
	public void setFirstStopName(String firstStopName) {
		FirstStopName = firstStopName;
	}

	/**
	 * @return the lastStopName
	 */
	public String getLastStopName() {
		return LastStopName;
	}

	/**
	 * @param lastStopName the lastStopName to set
	 */
	public void setLastStopName(String lastStopName) {
		LastStopName = lastStopName;
	}
	
	
	
}
