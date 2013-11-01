package com.onurersen.baykus35.list.tariff;
/**
 * 
 * @author onurersen
 *
 */
public class TariffItem {

	public int Id;
	public String IconFile;
	public String Number;
	public String Route;
	public String Description;

	public TariffItem(int id, String iconFile, String number, String route,
			String description) {
		Id = id;
		IconFile = iconFile;
		Number = number;
		Route = route;
		Description = description;
	}

}
