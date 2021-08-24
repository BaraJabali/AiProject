package application;

import java.util.ArrayList;
import java.util.Comparator;

public class Node implements Comparable<Node> {
	private int id;
	private double pathCost;// h + Cost
	private double h;
	private double traveltime;
	private double besttraveltime;
	Node parent;
	public ArrayList<Node> Child = new ArrayList<Node>();

	public Node() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Node(int id, double pathCost, double h, double traveltime, double besttraveltime) {
		super();
		this.id = id;
		this.pathCost = pathCost;
		this.h = h;
		this.traveltime = traveltime;
		this.besttraveltime = besttraveltime;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPathCost() {
		return pathCost;
	}

	public void setPathCost(double pathCost) {
		this.pathCost = pathCost;
	}

	public double getH() {
		return h;
	}

	public void setH(double h) {
		this.h = h;

	}

	@Override
	public String toString() {
		return "Node [id=" + id + ", pathCost=" + pathCost + ", h=" + h + ", parent=" + parent + ", Child=" + Child
				+ "]";
	}

	@Override
	public int compareTo(Node s1) {
		if (s1.getId() > this.getId())
			return 1;
		else if ((s1.getId() < this.getId()))
			return -1;
		return 0;
	}

	public double getTraveltime() {
		return traveltime;
	}

	public void setTraveltime(double traveltime) {
		this.traveltime = traveltime;
	}

	public double getBesttraveltime() {
		return besttraveltime;
	}

	public void setBesttraveltime(double besttraveltime) {
		this.besttraveltime = besttraveltime;
	}

	public void AddChild(Node n) {
		Child.add(n);
	}

	public double returnTotalCost() {
		return h + pathCost;
	}

}

class NodeComparator implements Comparator<Node> {

	// Overriding compare()method of Comparator
	// for descending order of cgpa
	public int compare(Node s1, Node s2) {
		if (s1.returnTotalCost() > s2.returnTotalCost())
			return 1;
		else if (s1.returnTotalCost() < s2.returnTotalCost())
			return -1;
		return 0;
	}

}

class NodeComparator2 implements Comparator<Node> {

	// Overriding compare()method of Comparator
	// for descending order of cgpa
	public int compare(Node s1, Node s2) {
		if (s1.getTraveltime() > s2.getTraveltime())
			return 1;
		else if (s1.getTraveltime() < s2.getTraveltime())
			return -1;
		return 0;
	}
}

class NodeComparator3 implements Comparator<Node> {

	// Overriding compare()method of Comparator
	// for descending order of cgpa
	public int compare(Node s1, Node s2) {
		if (s1.getPathCost() > s2.getPathCost())
			return 1;
		else if (s1.getPathCost() < s2.getPathCost())
			return -1;
		return 0;
	}

}