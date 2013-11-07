package com.onurersen.baykus35.list.route;

/**
 * 
 * @author onurersen
 * 
 */
public class RouteItem {

	public int index;
	public int RouteId;
	public String Number;
	public String Route;
	public String Description;

	public RouteItem(int index, int RouteId, String Number, String Route, String Description) {
		this.index = index;
		this.RouteId = RouteId;
		this.Number = Number;
		this.Route = Route;
		this.Description = Description;
	}

}
