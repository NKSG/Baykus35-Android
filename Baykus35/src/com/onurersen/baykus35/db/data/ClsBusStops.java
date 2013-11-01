package com.onurersen.baykus35.db.data;

/**
 * 
 * @author onurersen
 * 
 */
public class ClsBusStops {

	private int StopId;

	private String StopName;

	private int RouteId;

	private Double Latitude;

	private Double Longitude;

	/**
	 * @return the stopId
	 */
	public int getStopId() {
		return StopId;
	}

	/**
	 * @param stopId
	 *            the stopId to set
	 */
	public void setStopId(int stopId) {
		StopId = stopId;
	}

	/**
	 * @return the stopName
	 */
	public String getStopName() {
		return StopName;
	}

	/**
	 * @param stopName
	 *            the stopName to set
	 */
	public void setStopName(String stopName) {
		StopName = stopName;
	}

	/**
	 * @return the routeId
	 */
	public int getRouteId() {
		return RouteId;
	}

	/**
	 * @param routeId
	 *            the routeId to set
	 */
	public void setRouteId(int routeId) {
		RouteId = routeId;
	}

	/**
	 * @return the latitude
	 */
	public Double getLatitude() {
		return Latitude;
	}

	/**
	 * @param latitude
	 *            the latitude to set
	 */
	public void setLatitude(Double latitude) {
		Latitude = latitude;
	}

	/**
	 * @return the longitude
	 */
	public Double getLongitude() {
		return Longitude;
	}

	/**
	 * @param longitude
	 *            the longitude to set
	 */
	public void setLongitude(Double longitude) {
		Longitude = longitude;
	}

}
