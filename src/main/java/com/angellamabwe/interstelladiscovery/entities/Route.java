package com.angellamabwe.interstelladiscovery.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Route {
	@Id
	@GeneratedValue
	private int routeId;

	private String planetOrigin;

	private String planetDest;

	private double distance;

	private double distanceAfterDelay;

	public int getRouteId() {
		return routeId;
	}

	public void setRouteId(int routeId) {
		this.routeId = routeId;
	}

	public String getPlanetOrigin() {
		return planetOrigin;
	}

	public void setPlanetOrigin(String planetOrigin) {
		this.planetOrigin = planetOrigin;
	}

	public String getPlanetDest() {
		return planetDest;
	}

	public void setPlanetDest(String planetDest) {
		this.planetDest = planetDest;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public double getDistanceAfterDelay() {
		return distanceAfterDelay;
	}

	public void setDistanceAfterDelay(double distanceAfterDelay) {
		this.distanceAfterDelay = distanceAfterDelay;
	}

}
