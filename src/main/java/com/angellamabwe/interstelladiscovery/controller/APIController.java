package com.angellamabwe.interstelladiscovery.controller;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.angellamabwe.interstelladiscovery.entities.Planet;
import com.angellamabwe.interstelladiscovery.entities.Route;
import com.angellamabwe.interstelladiscovery.services.excel.ExcelService;
import com.angellamabwe.interstelladiscovery.services.spp.Graph;
import com.angellamabwe.interstelladiscovery.services.spp.Node;
import com.angellamabwe.interstelladiscovery.services.spp.Pathfinding;

@RestController
@RequestMapping("/spp")
public class APIController {

	@Autowired
	private ExcelService excelService;

	@PostMapping("/planets")
	public String savePlanets() {
		try {
			excelService.ReadDataFromExcel("src/main/resources/discoveryAssignment.xlsx");
		} catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
			e.printStackTrace();
		}

		return "Data saved";

	}

	@GetMapping("/planets")
	public Iterable<Planet> getPlanets() {
		return excelService.retrieveNodes();
	}

	@GetMapping("/routes")
	public Iterable<Route> getRoutes() {
		return excelService.retrieveRoutes();
	}

//	Add our Planets node to the graph
//	This method will take into account the Traffic Delay
	@GetMapping("/graph")
	public List<Node> findShortestPathAfterDelay(@RequestBody String destPlanet) {
		Graph graph = new Graph();
		Node sourceNode = new Node("A");
		List<Node> shortestPath = null;
//		use Routes to create each Nodes & add all connected destinations
		Iterable<Route> routes = excelService.retrieveRoutes();
		for (Route route : routes) {
			Node node = new Node(route.getPlanetOrigin());
			Node destination = new Node(route.getPlanetDest());
			double distance = route.getDistanceAfterDelay();
			node.addDestination(destination, distance);
			graph.addNode(node);
		}
		graph = Pathfinding.calculateShortestPathFromSource(graph, sourceNode);
		Set<Node> resultNodesSet = graph.getNodes();
		for (Node node : resultNodesSet) {
			if (node.getName().equals(destPlanet)) {
				List<Node> sPath = node.getShortestPath();
				shortestPath = sPath;
//				DEBUG
//				System.out.println("The shortest path from Earth to: " + node.getName() + " is");
//				System.out.println((node.getShortestPath()).toString());
//				System.out.println("with a total distance of: " + node.getDistance());
			}
		}
		return shortestPath;
	}

//	This method DOES NOT take into account the Traffic Delay
	@GetMapping("/graphNoDelay")
	public List<Node> findShortestPath(@RequestBody String destPlanet) {
		Graph graph = new Graph();
		Node sourceNode = new Node("A");
		List<Node> shortestPath = null;
//		use Routes to create each Nodes & add all connected destinations
		Iterable<Route> routes = excelService.retrieveRoutes();
		for (Route route : routes) {
			Node node = new Node(route.getPlanetOrigin());
			Node destination = new Node(route.getPlanetDest());
			double distance = route.getDistance();
			node.addDestination(destination, distance);
			graph.addNode(node);
		}
		graph = Pathfinding.calculateShortestPathFromSource(graph, sourceNode);
		Set<Node> resultNodesSet = graph.getNodes();
		for (Node node : resultNodesSet) {
			if (node.getName().equals(destPlanet)) {
				List<Node> sPath = node.getShortestPath();
				shortestPath = sPath;
			}
		}
		return shortestPath;
	}

}
