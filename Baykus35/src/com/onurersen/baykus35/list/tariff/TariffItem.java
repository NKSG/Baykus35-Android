package com.onurersen.baykus35.list.tariff;

/**
 * 
 * @author onurersen
 * 
 */
public class TariffItem {

	private int index;
	private int TariffId;
	private int RouteId;
	private String Time1;
	private String Time2;
	private String Route;

	public TariffItem(int index, int TariffId, int RouteId, String Time1, String Time2, String Route) {
		this.index = index;
		this.TariffId = TariffId;
		this.RouteId = RouteId;
		this.Time1 = Time1;
		this.Time2 = Time2;
		this.Route = Route;
	}

	/**
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * @param index
	 *            the index to set
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	/**
	 * @return the tariffId
	 */
	public int getTariffId() {
		return TariffId;
	}

	/**
	 * @param tariffId
	 *            the tariffId to set
	 */
	public void setTariffId(int tariffId) {
		TariffId = tariffId;
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
	 * @return the time1
	 */
	public String getTime1() {
		return Time1;
	}

	/**
	 * @param time1
	 *            the time1 to set
	 */
	public void setTime1(String time1) {
		Time1 = time1;
	}

	/**
	 * @return the time2
	 */
	public String getTime2() {
		return Time2;
	}

	/**
	 * @param time2
	 *            the time2 to set
	 */
	public void setTime2(String time2) {
		Time2 = time2;
	}

	/**
	 * @return the route
	 */
	public String getRoute() {
		return Route;
	}

	/**
	 * @param route
	 *            the route to set
	 */
	public void setRoute(String route) {
		Route = route;
	}

}
