package com.angellamabwe.interstelladiscovery.services.spp;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class Node {

	private String name;

	private LinkedList<Node> shortestPath = new LinkedList<>();

//    private Integer distance = Integer.MAX_VALUE; 
	private Double distance = Double.MAX_VALUE; // set to infinite default

//   used 4 associating immediate neighbors with edge length
	private Map<Node, Double> adjacentNodes = new HashMap<>();

	public Node() {
	}

	public Node(String name) {
		this.name = name;
	}

	public void addDestination(Node destination, double distance) {
		adjacentNodes.put(destination, distance);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<Node, Double> getAdjacentNodes() {
		return adjacentNodes;
	}

	public void setAdjacentNodes(Map<Node, Double> adjacentNodes) {
		this.adjacentNodes = adjacentNodes;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public List<Node> getShortestPath() {
		return shortestPath;
	}

	public void setShortestPath(LinkedList<Node> shortestPath) {
		this.shortestPath = shortestPath;
	}
}