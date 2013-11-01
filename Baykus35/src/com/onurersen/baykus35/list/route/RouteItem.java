package com.onurersen.baykus35.list.route;
/**
 * 
 * @author onurersen
 *
 */
public class RouteItem {

	public int Id;
	public String IconFile;
	public String Number;
	public String Route;
	public String Description;

	public RouteItem(int id, String iconFile, String number, String route,
			String description) {
		Id = id;
		IconFile = iconFile;
		Number = number;
		Route = route;
		Description = description;
	}

}
