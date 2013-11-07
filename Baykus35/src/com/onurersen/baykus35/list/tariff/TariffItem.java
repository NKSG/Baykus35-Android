package com.onurersen.baykus35.list.tariff;

/**
 * 
 * @author onurersen
 * 
 */
public class TariffItem {

	public int index;
	public int TariffId;
	public int RouteId;
	public String Time1;
	public String Time2;

	public TariffItem(int index, int TariffId, int RouteId, String Time1, String Time2) {
		this.index = index;
		this.TariffId = TariffId;
		this.RouteId = RouteId;
		this.Time1 = Time1;
		this.Time2 = Time2;
	}

}
