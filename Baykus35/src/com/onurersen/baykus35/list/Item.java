package com.onurersen.baykus35.list;
/**
 * 
 * @author onurersen
 *
 */
public class Item {

	public int Id;
	public String IconFile;
	public String Number;
	public String Route;
	public String Description;

	public Item(int id, String iconFile, String number, String route,
			String description) {
		Id = id;
		IconFile = iconFile;
		Number = number;
		Route = route;
		Description = description;
	}

}
