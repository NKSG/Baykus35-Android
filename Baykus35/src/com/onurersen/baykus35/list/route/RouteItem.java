package com.onurersen.baykus35.list.route;

/**
 * 
 * @author onurersen
 * 
 */
public class RouteItem {

	private int index;
	private int RouteId;
	private String Number;
	private String Route;
	private String Description;
	private String FirstStopName;
	private String LastStopName;

	public RouteItem(int index, int RouteId, String Number, String Route, String Description,String FirstStopName,String LastStopName) {
		this.index = index;
		this.RouteId = RouteId;
		this.Number = Number;
		this.Route = Route;
		this.Description = Description;
		this.FirstStopName = FirstStopName;
		this.LastStopName = LastStopName;
	}

	/**
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * @param index the index to set
	 */
	public void setIndex(int index) {
		this.index = index;
	}

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
	 * @return the number
	 */
	public String getNumber() {
		return Number;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(String number) {
		Number = number;
	}

	/**
	 * @return the route
	 */
	public String getRoute() {
		return Route;
	}

	/**
	 * @param route the route to set
	 */
	public void setRoute(String route) {
		Route = route;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return Description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		Description = description;
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
