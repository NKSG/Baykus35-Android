package com.onuersen.baykus35.busstop;

/**
 * 
 * @author onurersen
 * 
 */
public class BusStopItem {

	public int index;
	public int StopId;
	public String StopName;
	public int RouteId;
	public float Latitude;
	public float Longitude;

	public BusStopItem(int index, int StopId, String StopName, int RouteId, float Latitude, float Longitude) {
		this.index = index;
		this.StopId = StopId;
		this.StopName = StopName;
		this.RouteId = RouteId;
		this.Latitude = Latitude;
		this.Longitude = Longitude;
	}

}
