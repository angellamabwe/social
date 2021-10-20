package com.angellamabwe.interstelladiscovery.services.spp;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

@Component
public class Graph {

	private Set<Node> nodes = new HashSet<>(); // HashSet so that the nodes will be uniQue

	public void addNode(Node nodeA) {
		nodes.add(nodeA);
	}

	public Set<Node> getNodes() {
		return nodes;
	}

	public void setNodes(Set<Node> nodes) {
		this.nodes = nodes;
	}
}
