package application;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.Stack;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class SampleController implements Initializable {
	// the radio button for the algorithms
	@FXML
	private RadioButton AstarRB, DepthAlgorithm, Optimal2Algorithm;

	@FXML
	private RadioButton Optimal1Algorithm;

	@FXML
	private RadioButton UniformAlgorithm;

	@FXML
	private RadioButton BreadthAlgorithm;

	@FXML
	private RadioButton RamallahRB, AlBirehRB, NablusRB, SalfeitRB, QalqiliaRB, TulkarmRB, HaifaRB, AkaRB, SafadRB,
			TabareaRB, AlnasraRB;

	@FXML
	private RadioButton JeninRB, TubasRB, JerichoRB, BethlehemRB, JerusalemRB, HebronRB, YafaRB, GazaRB, RafahRB;

	@FXML
	private Button SetStartBTN, SetEndBTN, ResetBTN, ListenEngBTN;
	@FXML
	private TextArea textaria;

	@FXML
	private Button savestart;

	@FXML
	private Button ListenArbBTN;

	@FXML
	private Button cancelstart;

	@FXML
	private Button savegoal;

	@FXML
	private Button cancelgoal;

	@FXML
	private Button ExitBTN;

	@FXML
	private Circle found;

	RadioButton StartSelect;

	@FXML
	private AnchorPane anchor;

	ArrayList<String> CitiesName = new ArrayList<String>();// cities name
	ArrayList<Node> CitiesNode = new ArrayList<Node>();// array of cities nodes
	ArrayList<String> inputData = new ArrayList<String>();
	ArrayList<Integer> PathOfSol = new ArrayList<Integer>();
	ArrayList<Integer> GoalList = new ArrayList<Integer>();
	ToggleGroup gg = new ToggleGroup();
	int startId = 0;
	int index1;
	int index2;
	double Alpha;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		DrawLine();
		Disable();
		found.setFill(Color.CORNSILK);
		textaria.setEditable(false);
		textaria.setDisable(true);
		AstarRB.setToggleGroup(gg);
		DepthAlgorithm.setToggleGroup(gg);
		Optimal2Algorithm.setToggleGroup(gg);
		Optimal1Algorithm.setToggleGroup(gg);
		UniformAlgorithm.setToggleGroup(gg);
		BreadthAlgorithm.setToggleGroup(gg);
		String filename = "input.txt";
		File inputfile = new File(filename);
		if (!inputfile.canExecute()) {
			System.out.println("File not exist.....!");
			System.exit(0);
		}
		// Create a Scanner for the file
		Scanner input = null;
		try {
			input = new Scanner(inputfile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		// Read data from a file
		for (int i = 1; i < 21; i++) {
			for (int j = 1; j < 21; j++) {
				String temp = i + "-" + j + "-" + input.next();
				inputData.add(temp);
			}
		}
		// Close the file
		input.close();
		Node Aka = new Node(1, 0, 0, 0, 0);
		Node AlBireh = new Node(2, 0, 0, 0, 0);
		Node Alnasra = new Node(3, 0, 0, 0, 0);
		Node Bethlehem = new Node(4, 0, 0, 0, 0);
		Node Gaza = new Node(5, 0, 0, 0, 0);
		Node Haifa = new Node(6, 0, 0, 0, 0);
		Node Hebron = new Node(7, 0, 0, 0, 0);
		Node Jenin = new Node(8, 0, 0, 0, 0);
		Node Jericho = new Node(9, 0, 0, 0, 0);
		Node Jerusalem = new Node(10, 0, 0, 0, 0);
		Node Nablus = new Node(11, 0, 0, 0, 0);
		Node Qalqilia = new Node(12, 0, 0, 0, 0);
		Node Rafah = new Node(13, 0, 0, 0, 0);
		Node Ramallah = new Node(14, 0, 0, 0, 0);
		Node Safed = new Node(15, 0, 0, 0, 0);
		Node Salfeit = new Node(16, 0, 0, 0, 0);
		Node Tabarea = new Node(17, 0, 0, 0, 0);
		Node Tubas = new Node(18, 0, 0, 0, 0);
		Node Tulkarm = new Node(19, 0, 0, 0, 0);
		Node Yafa = new Node(20, 0, 0, 0, 0);

		CitiesName.add("Aka");
		CitiesName.add("AlBireh");
		CitiesName.add("Alnasra");
		CitiesName.add("Bethlehem");
		CitiesName.add("Gaza");
		CitiesName.add("Haifa");
		CitiesName.add("Hebron");
		CitiesName.add("Jenin");
		CitiesName.add("Jericho");
		CitiesName.add("Jerusalem");
		CitiesName.add("Nablus");
		CitiesName.add("Qalqilia");
		CitiesName.add("Rafah");
		CitiesName.add("Ramallah");
		CitiesName.add("Safed");
		CitiesName.add("Salfeit");
		CitiesName.add("Tabarea");
		CitiesName.add("Tubas");
		CitiesName.add("Tulkarm");
		CitiesName.add("Yafa");

		CitiesNode.add(Aka);
		CitiesNode.add(AlBireh);
		CitiesNode.add(Alnasra);
		CitiesNode.add(Bethlehem);
		CitiesNode.add(Gaza);
		CitiesNode.add(Haifa);
		CitiesNode.add(Hebron);
		CitiesNode.add(Jenin);
		CitiesNode.add(Jericho);
		CitiesNode.add(Jerusalem);
		CitiesNode.add(Nablus);
		CitiesNode.add(Qalqilia);
		CitiesNode.add(Rafah);
		CitiesNode.add(Ramallah);
		CitiesNode.add(Safed);
		CitiesNode.add(Salfeit);
		CitiesNode.add(Tabarea);
		CitiesNode.add(Tubas);
		CitiesNode.add(Tulkarm);
		CitiesNode.add(Yafa);

		for (int i = 0; i < 400; i = i + 20) {
			for (int j = i; j < i + 20; j++) {
				String[] split = inputData.get(j).split("-");
				if (!(split[3].compareTo("*") == 0 || split[3].compareTo("0") == 0))
					CitiesNode.get(Integer.parseInt(split[0]) - 1)
							.AddChild(new Node(Integer.parseInt(split[1]), Double.parseDouble(split[3]),
									Double.parseDouble(split[2]), Double.parseDouble(split[4]),
									Double.parseDouble(split[5])));
			}

		}
	}

	@FXML
	public void AlgoInitialize(ActionEvent event) {
		startId = 0;
		GoalList.clear();
		PathOfSol.clear();
		textaria.setDisable(true);
		RamallahRB.setSelected(false);
		AlBirehRB.setSelected(false);
		NablusRB.setSelected(false);
		SalfeitRB.setSelected(false);
		QalqiliaRB.setSelected(false);
		TulkarmRB.setSelected(false);
		HaifaRB.setSelected(false);
		AkaRB.setSelected(false);
		SafadRB.setSelected(false);
		TabareaRB.setSelected(false);
		AlnasraRB.setSelected(false);
		JeninRB.setSelected(false);
		TubasRB.setSelected(false);
		JerichoRB.setSelected(false);
		BethlehemRB.setSelected(false);
		JerusalemRB.setSelected(false);
		HebronRB.setSelected(false);
		YafaRB.setSelected(false);
		GazaRB.setSelected(false);
		RafahRB.setSelected(false);
		textaria.setText(null);
		found.setFill(Color.CORNSILK);
		Disable();
		for (int i = index2 - 1; i >= index1; i--) {
			anchor.getChildren().remove(i);
		}
		index1 = anchor.getChildren().size();
		index2 = anchor.getChildren().size();
	}

	public void Optimal2Algo() {
		Node solve = null;
		double Timecost = 0;
		double Finalcost = 0;
		ArrayList<String> str = new ArrayList<String>();
		str.add(CitiesName.get(startId - 1));
		// a loop to get to all goals
		while (!GoalList.isEmpty()) {
			Node CurrentNode = null;
			PriorityQueue<Node> queue = new PriorityQueue<Node>(new NodeComparator2());
			PriorityQueue<Integer> visited = new PriorityQueue<Integer>();
			queue.add(CitiesNode.get(startId - 1));
			CitiesNode.get(startId - 1).parent = null;
			outer: while (queue.size() > 0) {
				CurrentNode = queue.poll();
				// if this node we visit before
				if (visited.contains(CurrentNode.getId())) {
					continue outer;
				}
				visited.add(CurrentNode.getId());
				for (int t = 0; t < GoalList.size(); t++) {
					// if we reach the goal
					if (CurrentNode.getId() == GoalList.get(t)) {
						break outer;
					}
				}
				// here we get the children of the node and set its parent to the current node
				// and set the time for each node of them with the wanted alpha
				for (int i1 = 0; i1 < CurrentNode.Child.size(); i1++) {
					int ChildId = CurrentNode.Child.get(i1).getId();
					Node temp = new Node();// the add to queue node
					temp.setId(CitiesNode.get(ChildId - 1).getId());
					temp.Child = CitiesNode.get(ChildId - 1).Child;
					double Tr = CurrentNode.Child.get(i1).getTraveltime() * Alpha;
					DecimalFormat df = new DecimalFormat("#.00");
					Tr = Double.valueOf(df.format(Tr));
					temp.setTraveltime(Tr + CurrentNode.getTraveltime());
					temp.setPathCost(CurrentNode.Child.get(i1).getPathCost() + CurrentNode.getPathCost());
					temp.parent = CurrentNode;
					if (CurrentNode.parent != null) {
						if (temp.getId() != CurrentNode.parent.getId()) {
							queue.add(temp);
						}
					} else {
						queue.add(temp);
					}
				}
			}

			Timecost += CurrentNode.getTraveltime();
			Finalcost += CurrentNode.getPathCost();
			solve = CurrentNode;
			// here we remove the goal that we found with the lowest travel time
			for (int t = 0; t < GoalList.size(); t++) {
				if (GoalList.get(t) == solve.getId()) {
					GoalList.remove(t);
					break;
				}
			}

			startId = solve.getId();
			ArrayList<String> ss = new ArrayList<String>();
			while (solve.parent != null) {
				ss.add(CitiesName.get(solve.getId() - 1));
				solve = solve.parent;
			}
			for (int y = ss.size() - 1; y >= 0; y--) {
				str.add(ss.get(y));
			}
		}
		for (int y = str.size() - 1; y >= 0; y--) {
			for (int j = 0; j < CitiesName.size(); j++) {
				if (CitiesName.get(j).compareTo(str.get(y)) == 0) {
					PathOfSol.add(j + 1);
					break;
				}
			}
		}
		String s = "";
		for (int i = 0; i < str.size() - 1; i++) {
			s = s + str.get(i) + " --------> ";
			if ((i + 1) % 3 == 0)
				s = s + "\n";
		}
		DecimalFormat df = new DecimalFormat("#.00");
		Finalcost = Double.valueOf(df.format(Finalcost));
		Timecost = Double.valueOf(df.format(Timecost));
		s = s + str.get(str.size() - 1);
		s = s + "\n\nTotal Cost is: " + Finalcost + " km And The Travel Time is: " + Timecost + "h";

		textaria.setText(s);
		textaria.setDisable(false);
		found.setFill(Color.GREEN);
		startId = 0;
		GoalList.clear();
		DrawLine();
	}

	public void Optimal1Algo() {
		Node solve = null;
		double Finalcost = 0;
		ArrayList<String> str = new ArrayList<String>();
		str.add(CitiesName.get(startId - 1));
		// a loop to get to all goals
		while (!GoalList.isEmpty()) {
			Node CurrentNode = null;
			PriorityQueue<Node> queue = new PriorityQueue<Node>(new NodeComparator3());
			PriorityQueue<Integer> visited = new PriorityQueue<Integer>();
			queue.add(CitiesNode.get(startId - 1));
			CitiesNode.get(startId - 1).parent = null;
			outer: while (queue.size() > 0) {
				CurrentNode = queue.poll();
				// if this node we visit before
				if (visited.contains(CurrentNode.getId())) {
					continue outer;
				}
				visited.add(CurrentNode.getId());
				for (int t = 0; t < GoalList.size(); t++) {
					// if we reach the goal
					if (CurrentNode.getId() == GoalList.get(t)) {
						break outer;
					}
				}
				// here we get the children of the node and set its parent to the current node
				// and set the time for each node of them with the wanted alpha
				for (int i1 = 0; i1 < CurrentNode.Child.size(); i1++) {
					int ChildId = CurrentNode.Child.get(i1).getId();
					Node temp = new Node();// the add to queue node
					temp.setId(CitiesNode.get(ChildId - 1).getId());
					temp.Child = CitiesNode.get(ChildId - 1).Child;
					temp.setPathCost(CurrentNode.Child.get(i1).getPathCost() + CurrentNode.getPathCost());
					temp.parent = CurrentNode;
					if (CurrentNode.parent != null) {
						if (temp.getId() != CurrentNode.parent.getId()) {
							queue.add(temp);
						}
					} else {
						queue.add(temp);
					}
				}
			}

			Finalcost += CurrentNode.getPathCost();
			solve = CurrentNode;
			// here we remove the goal that we found with the lowest travel time
			for (int t = 0; t < GoalList.size(); t++) {
				if (GoalList.get(t) == solve.getId()) {
					GoalList.remove(t);
					break;
				}
			}

			startId = solve.getId();
			ArrayList<String> ss = new ArrayList<String>();
			while (solve.parent != null) {
				ss.add(CitiesName.get(solve.getId() - 1));
				solve = solve.parent;
			}
			for (int y = ss.size() - 1; y >= 0; y--) {
				str.add(ss.get(y));
			}
		}
		for (int y = str.size() - 1; y >= 0; y--) {
			for (int j = 0; j < CitiesName.size(); j++) {
				if (CitiesName.get(j).compareTo(str.get(y)) == 0) {
					PathOfSol.add(j + 1);
					break;
				}
			}
		}
		String s = "";
		for (int i = 0; i < str.size() - 1; i++) {
			s = s + str.get(i) + " --------> ";
			if ((i + 1) % 3 == 0)
				s = s + "\n";
		}
		DecimalFormat df = new DecimalFormat("#.00");
		Finalcost = Double.valueOf(df.format(Finalcost));
		s = s + str.get(str.size() - 1);
		s = s + "\n\nTotal Cost is: " + Finalcost + " km ";

		textaria.setText(s);
		textaria.setDisable(false);
		found.setFill(Color.GREEN);
	}

	public void DepthAlgo() {
		double Finalcost = 0;
		Node solve = null;
		boolean flag = false;
		Stack<Node> stack = new Stack<Node>();
		stack.push(CitiesNode.get(startId - 1));
		CitiesNode.get(startId - 1).parent = null;
		Node CurrentNode = null;
		// while(stack.size() > 0) {
		// set the limit for the number of visited to 30
		outer : for (int FF = 0; FF < 50; FF++) {
			// check if this node is the goal node
			CurrentNode = stack.pop();
			for (int i = 0; i < GoalList.size(); i++) {
				if (CurrentNode.getId() == GoalList.get(i)) {
					flag = true;
					break outer;
				}
			}
			// here the list is for the child nodes for the parent
			ArrayList<Node> CurrentNodeChild = new ArrayList<Node>();
			for (int i = 0; i < CurrentNode.Child.size(); i++) {
				// here we fill the child array list with the parent children
				int ChildId = CurrentNode.Child.get(i).getId();
				Node temp = new Node();//
				temp.setId(CitiesNode.get(ChildId - 1).getId());
				temp.Child = CitiesNode.get(ChildId - 1).Child;
				temp.setPathCost(CurrentNode.Child.get(i).getPathCost() + CurrentNode.getPathCost());
				// set the parent for all nodes is the current node
				temp.parent = CurrentNode;
				// check if the node has parent if it has we check that we don't add the parent
				// id as child
				if (CurrentNode.parent != null) {
					if (temp.getId() != CurrentNode.parent.getId()) {
						CurrentNodeChild.add(temp);
					}
				} else {
					CurrentNodeChild.add(temp);
				}
			}
			// sort by id
			Collections.sort(CurrentNodeChild);
			for (int t = 0; t < CurrentNodeChild.size(); t++) {
				stack.push(CurrentNodeChild.get(t));
			}
			
		}
		if (flag == false) {
			String s = "There is no way in the from the start to the goal";
			textaria.setText(s);
			textaria.setDisable(false);
			found.setFill(Color.RED);
		} else if (flag == true) {
			solve = CurrentNode;
			Finalcost = CurrentNode.getPathCost();
			while (solve.parent != null) {
				PathOfSol.add(solve.getId());
				solve = solve.parent;
			}
			PathOfSol.add(solve.getId());
			String s = CitiesName.get(PathOfSol.get(PathOfSol.size() - 1) - 1);

			for (int i = PathOfSol.size() - 2; i >= 0; i--) {
				s = s + " ------> " + CitiesName.get(PathOfSol.get(i) - 1);
				if ((i) % 3 == 0)
					s = s + "\n";
			}
			DecimalFormat df = new DecimalFormat("#.00");
			Finalcost = Double.valueOf(df.format(Finalcost));
			s = s + "\n\nTotal Cost is: " + Finalcost + " km";
			textaria.setText(s);
			textaria.setDisable(false);
			found.setFill(Color.GREEN);
		}
	}

	public void Astar() {
		double Finalcost = 0;
		Node solve = null;

		for (int j = 0; j < GoalList.size(); j++) {
			int idgoal = GoalList.get(j);
			int Loction = idgoal - 1;
			Loction = Loction * 20;

			// this loop to get the heuristic value to the nodes
			for (int i = 0; i < CitiesNode.size(); i++) {
				String[] Temp = inputData.get(Loction).split("-");
				double h = Double.parseDouble(Temp[2]);
				CitiesNode.get(i).setH(h);
				Loction++;

			}
			PriorityQueue<Node> queue = new PriorityQueue<Node>(new NodeComparator());
			// to make sure we don't add node before
			PriorityQueue<Integer> visited = new PriorityQueue<Integer>();
			queue.add(CitiesNode.get(startId - 1));
			CitiesNode.get(startId - 1).parent = null;
			Node CurrentNode = null;

			while (queue.size() > 0) {
				CurrentNode = queue.poll();
				// if we didnt visit this node before we add it to the visited node
				if (visited.contains(CurrentNode.getId())) {
					continue;
				}

				visited.add(CurrentNode.getId());
				if (CurrentNode.getId() == idgoal) {
					break;
				}

				for (int i = 0; i < CurrentNode.Child.size(); i++) {
					// here we get all the node children
					int ChildId = CurrentNode.Child.get(i).getId();
					Node temp = new Node();// the add to queue node
					temp.setId(CitiesNode.get(ChildId - 1).getId());
					temp.Child = CitiesNode.get(ChildId - 1).Child;
					temp.setH(CitiesNode.get(ChildId - 1).getH());
					temp.setPathCost(CurrentNode.Child.get(i).getPathCost() + CurrentNode.getPathCost());
					temp.parent = CurrentNode;
					if (CurrentNode.parent != null) {
						if (temp.getId() != CurrentNode.parent.getId()) {
							queue.add(temp);
						}
					} else {
						queue.add(temp);
					}
				}
			}

			double totalcost = CurrentNode.getPathCost();
			if (j == 0) {
				Finalcost = totalcost;
				solve = CurrentNode;
			} else if (totalcost < Finalcost) {
				Finalcost = totalcost;
				solve = CurrentNode;
			}
		}

		while (solve.parent != null) {
			PathOfSol.add(solve.getId());
			solve = solve.parent;
		}
		PathOfSol.add(solve.getId());
		String s = CitiesName.get(PathOfSol.get(PathOfSol.size() - 1) - 1);

		for (int i = PathOfSol.size() - 2; i >= 0; i--) {
			s = s + " ------> " + CitiesName.get(PathOfSol.get(i) - 1);
			if ((i) % 3 == 0)
				s = s + "\n";
		}
		DecimalFormat df = new DecimalFormat("#.00");
		Finalcost = Double.valueOf(df.format(Finalcost));
		s = s + "\n\nTotal Cost is: " + Finalcost + " km";
		textaria.setText(s);
		textaria.setDisable(false);
	}

	public void uniformAlgo() {
		double Finalcost = 0;
		Node solve = null;
		for (int j = 0; j < GoalList.size(); j++) {
			int idgoal = GoalList.get(j);
			PriorityQueue<Node> queue = new PriorityQueue<Node>(new NodeComparator3());
			// to make sure we don't add node before
			PriorityQueue<Integer> visited = new PriorityQueue<Integer>();
			queue.add(CitiesNode.get(startId - 1));
			CitiesNode.get(startId - 1).parent = null;
			Node CurrentNode = null;
			while (queue.size() > 0) {
				CurrentNode = queue.poll();
				// if we didnt visit this node before we add it to the visited node
				if (visited.contains(CurrentNode.getId())) {
					continue;
				}

				visited.add(CurrentNode.getId());
				if (CurrentNode.getId() == idgoal) {
					break;
				}

				for (int i = 0; i < CurrentNode.Child.size(); i++) {
					// here we get all the node children
					int ChildId = CurrentNode.Child.get(i).getId();
					Node temp = new Node();// the add to queue node
					temp.setId(CitiesNode.get(ChildId - 1).getId());
					temp.Child = CitiesNode.get(ChildId - 1).Child;
					temp.setPathCost(CurrentNode.Child.get(i).getPathCost() + CurrentNode.getPathCost());
					temp.parent = CurrentNode;
					if (CurrentNode.parent != null) {
						if (temp.getId() != CurrentNode.parent.getId()) {
							queue.add(temp);
						}
					} else {
						queue.add(temp);
					}
				}
			}

			double totalcost = CurrentNode.getPathCost();
			if (j == 0) {
				Finalcost = totalcost;
				solve = CurrentNode;
			} else if (totalcost < Finalcost) {
				Finalcost = totalcost;
				solve = CurrentNode;
			}
		}

		while (solve.parent != null) {
			PathOfSol.add(solve.getId());
			solve = solve.parent;
		}
		PathOfSol.add(solve.getId());
		String s = CitiesName.get(PathOfSol.get(PathOfSol.size() - 1) - 1);

		for (int i = PathOfSol.size() - 2; i >= 0; i--) {
			s = s + " ------> " + CitiesName.get(PathOfSol.get(i) - 1);
			if ((i) % 3 == 0)
				s = s + "\n";
		}
		DecimalFormat df = new DecimalFormat("#.00");
		Finalcost = Double.valueOf(df.format(Finalcost));
		s = s + "\n\nTotal Cost is: " + Finalcost + " km";
		textaria.setText(s);
		textaria.setDisable(false);
	}

	public void BreathAlgo() {
		double Finalcost = 0;
		Node solve = null;
		Queue<Node> queue = new LinkedList<Node>();
		// to make sure we don't add node before
		Queue<Integer> visited = new LinkedList<Integer>();
		queue.add(CitiesNode.get(startId - 1));
		CitiesNode.get(startId - 1).parent = null;
		Node CurrentNode = null;
		outer: while (queue.size() > 0) {
			CurrentNode = queue.poll();
			// if we didnt visit this node before we add it to the visited node
			if (visited.contains(CurrentNode.getId())) {
				continue outer;
			}
			visited.add(CurrentNode.getId());
			for (int i = 0; i < GoalList.size(); i++) {
				if (CurrentNode.getId() == GoalList.get(i)) {
					break outer;
				}
			}

			for (int i = 0; i < CurrentNode.Child.size(); i++) {
				// here we get all the node children
				int ChildId = CurrentNode.Child.get(i).getId();
				Node temp = new Node();// the add to queue node
				temp.setId(CitiesNode.get(ChildId - 1).getId());
				temp.Child = CitiesNode.get(ChildId - 1).Child;
				temp.setPathCost(CurrentNode.Child.get(i).getPathCost() + CurrentNode.getPathCost());
				temp.parent = CurrentNode;
				if (CurrentNode.parent != null) {
					if (temp.getId() != CurrentNode.parent.getId()) {
						queue.add(temp);
					}
				} else {
					queue.add(temp);
				}
			}
		}
		solve = CurrentNode;
		Finalcost = CurrentNode.getPathCost();
		while (solve.parent != null) {
			PathOfSol.add(solve.getId());
			solve = solve.parent;
		}
		PathOfSol.add(solve.getId());
		String s = CitiesName.get(PathOfSol.get(PathOfSol.size() - 1) - 1);

		for (int i = PathOfSol.size() - 2; i >= 0; i--) {
			s = s + " ------> " + CitiesName.get(PathOfSol.get(i) - 1);
			if ((i) % 3 == 0)
				s = s + "\n";
		}
		DecimalFormat df = new DecimalFormat("#.00");
		Finalcost = Double.valueOf(df.format(Finalcost));
		s = s + "\n\nTotal Cost is: " + Finalcost + " km";
		textaria.setText(s);
		textaria.setDisable(false);
	}

	@FXML
	public void TalkEnglish(ActionEvent event) throws FileNotFoundException, JavaLayerException {
		if (PathOfSol.isEmpty()) {
			talk("No.mp3");
		} else {
			talk("Path.mp3");
			String temp = CitiesName.get(PathOfSol.get(PathOfSol.size() - 1) - 1) + ".mp3";
			System.out.println(temp);
			talk(temp);
			for (int i = PathOfSol.size() - 2; i >= 0; i--) {
				talk("To.mp3");
				temp = CitiesName.get(PathOfSol.get(i) - 1) + ".mp3";
				talk(temp);
			}
			talk("Finish.mp3");
		}
	}

	@FXML
	public void TalkArbic(ActionEvent event) throws FileNotFoundException, JavaLayerException {
		if (PathOfSol.isEmpty()) {
			talk("NoA.mp3");
		} else {
			talk("PathA.mp3");
			String temp = CitiesName.get(PathOfSol.get(PathOfSol.size() - 1) - 1) + "A.mp3";
			talk(temp);
			for (int i = PathOfSol.size() - 2; i >= 0; i--) {
				talk("ToA.mp3");
				temp = CitiesName.get(PathOfSol.get(i) - 1) + "A.mp3";
				talk(temp);
			}
			talk("FinishA.mp3");
		}
	}

	public void talk(String voice) throws FileNotFoundException, JavaLayerException {
		File file = new File(voice);
		FileInputStream fi = new FileInputStream(file);
		BufferedInputStream bi = new BufferedInputStream(fi);
		Player p = new Player(bi);
		p.play();
		p.close();
	}

	@FXML
	public void Run(ActionEvent event) {
		if (startId == 0 || GoalList.isEmpty()) {
			Reslut("Please Choose The Start City and\nThe End City before The Run");
			Reset();
		} else {
			PathOfSol.clear();
			if (AstarRB.isSelected()) {
				Astar();
				startId = 0;
				GoalList.clear();
				DrawLine();
				found.setFill(Color.GREEN);
			} else if (DepthAlgorithm.isSelected()) {
				DepthAlgo();
				startId = 0;
				GoalList.clear();
				DrawLine();

			} else if (Optimal2Algorithm.isSelected()) {
				Alpha = 0;
				getAlpha();
			}

			else if (Optimal1Algorithm.isSelected()) {
				Optimal1Algo();
				startId = 0;
				GoalList.clear();
				DrawLine();
			} else if (UniformAlgorithm.isSelected()) {
				uniformAlgo();
				startId = 0;
				GoalList.clear();
				DrawLine();
				found.setFill(Color.GREEN);
			} else if (BreadthAlgorithm.isSelected()) {
				BreathAlgo();
				startId = 0;
				GoalList.clear();
				DrawLine();
				found.setFill(Color.GREEN);
			}
		}
	}

	ToggleGroup g1 = new ToggleGroup();

	@FXML
	public void setStart(ActionEvent event) throws JavaLayerException, FileNotFoundException {
		found.setFill(Color.CORNSILK);
		textaria.setText(null);
		textaria.setDisable(true);
		Enable();
		RamallahRB.setToggleGroup(g1);
		AlBirehRB.setToggleGroup(g1);
		NablusRB.setToggleGroup(g1);
		SalfeitRB.setToggleGroup(g1);
		QalqiliaRB.setToggleGroup(g1);
		TulkarmRB.setToggleGroup(g1);
		HaifaRB.setToggleGroup(g1);
		AkaRB.setToggleGroup(g1);
		SafadRB.setToggleGroup(g1);
		TabareaRB.setToggleGroup(g1);
		AlnasraRB.setToggleGroup(g1);
		JeninRB.setToggleGroup(g1);
		TubasRB.setToggleGroup(g1);
		JerichoRB.setToggleGroup(g1);
		BethlehemRB.setToggleGroup(g1);
		JerusalemRB.setToggleGroup(g1);
		HebronRB.setToggleGroup(g1);
		YafaRB.setToggleGroup(g1);
		GazaRB.setToggleGroup(g1);
		RafahRB.setToggleGroup(g1);
		for (int i = index2 - 1; i >= index1; i--) {
			anchor.getChildren().remove(i);
		}
		index1 = anchor.getChildren().size();
		index2 = anchor.getChildren().size();
	}

	@FXML
	public void Startsave(ActionEvent event) {
		if (RamallahRB.isSelected()) {
			startId = 14;
			RamallahRB.setSelected(false);
			StartSelect = RamallahRB;
		} else if (NablusRB.isSelected()) {
			startId = 11;
			NablusRB.setSelected(false);
			StartSelect = NablusRB;

		} else if (JeninRB.isSelected()) {
			startId = 8;
			JeninRB.setSelected(false);
			StartSelect = JeninRB;

		} else if (QalqiliaRB.isSelected()) {
			startId = 12;
			QalqiliaRB.setSelected(false);
			StartSelect = QalqiliaRB;

		} else if (JerusalemRB.isSelected()) {
			startId = 10;
			JerusalemRB.setSelected(false);
			StartSelect = JerusalemRB;

		} else if (BethlehemRB.isSelected()) {
			startId = 4;
			BethlehemRB.setSelected(false);
			StartSelect = BethlehemRB;

		} else if (HebronRB.isSelected()) {
			startId = 7;
			HebronRB.setSelected(false);
			StartSelect = HebronRB;

		} else if (JerichoRB.isSelected()) {
			startId = 9;
			JerichoRB.setSelected(false);
			StartSelect = JerichoRB;

		} else if (AlBirehRB.isSelected()) {
			startId = 2;
			AlBirehRB.setSelected(false);
			StartSelect = AlBirehRB;

		} else if (TulkarmRB.isSelected()) {
			startId = 19;
			TulkarmRB.setSelected(false);
			StartSelect = TulkarmRB;

		} else if (TubasRB.isSelected()) {
			startId = 18;
			TubasRB.setSelected(false);
			StartSelect = TubasRB;

		} else if (SalfeitRB.isSelected()) {
			startId = 16;
			SalfeitRB.setSelected(false);
			StartSelect = SalfeitRB;

		} else if (YafaRB.isSelected()) {
			startId = 20;
			YafaRB.setSelected(false);
			StartSelect = YafaRB;

		} else if (AkaRB.isSelected()) {
			startId = 1;
			AkaRB.setSelected(false);
			StartSelect = AkaRB;

		} else if (HaifaRB.isSelected()) {
			startId = 6;
			HaifaRB.setSelected(false);
			StartSelect = HaifaRB;

		} else if (AlnasraRB.isSelected()) {
			startId = 3;
			AlnasraRB.setSelected(false);
			StartSelect = AlnasraRB;

		} else if (TabareaRB.isSelected()) {
			startId = 17;
			TabareaRB.setSelected(false);
			StartSelect = TabareaRB;

		} else if (SafadRB.isSelected()) {
			startId = 15;
			SafadRB.setSelected(false);
			StartSelect = SafadRB;

		} else if (GazaRB.isSelected()) {
			startId = 5;
			GazaRB.setSelected(false);
			StartSelect = GazaRB;

		} else if (RafahRB.isSelected()) {
			startId = 13;
			RafahRB.setSelected(false);
			StartSelect = RafahRB;
		}
		Disable();
	}

	@FXML
	public void setgoal(ActionEvent event) {
		Enable();
		if (startId != 0)
			StartSelect.setDisable(true);
		RamallahRB.setToggleGroup(null);
		AlBirehRB.setToggleGroup(null);
		NablusRB.setToggleGroup(null);
		SalfeitRB.setToggleGroup(null);
		QalqiliaRB.setToggleGroup(null);
		TulkarmRB.setToggleGroup(null);
		HaifaRB.setToggleGroup(null);
		AkaRB.setToggleGroup(null);
		SafadRB.setToggleGroup(null);
		TabareaRB.setToggleGroup(null);
		AlnasraRB.setToggleGroup(null);
		JeninRB.setToggleGroup(null);
		TubasRB.setToggleGroup(null);
		JerichoRB.setToggleGroup(null);
		BethlehemRB.setToggleGroup(null);
		JerusalemRB.setToggleGroup(null);
		HebronRB.setToggleGroup(null);
		YafaRB.setToggleGroup(null);
		GazaRB.setToggleGroup(null);
		RafahRB.setToggleGroup(null);
	}

	@FXML
	public void ResetButton(ActionEvent event) {
		Reset();
	}

	public void Reset() {
		if (gg.getSelectedToggle() != null)
			gg.getSelectedToggle().setSelected(false);
		startId = 0;
		GoalList.clear();
		PathOfSol.clear();
		RamallahRB.setSelected(false);
		AlBirehRB.setSelected(false);
		NablusRB.setSelected(false);
		SalfeitRB.setSelected(false);
		QalqiliaRB.setSelected(false);
		TulkarmRB.setSelected(false);
		HaifaRB.setSelected(false);
		AkaRB.setSelected(false);
		SafadRB.setSelected(false);
		TabareaRB.setSelected(false);
		AlnasraRB.setSelected(false);
		JeninRB.setSelected(false);
		TubasRB.setSelected(false);
		JerichoRB.setSelected(false);
		BethlehemRB.setSelected(false);
		JerusalemRB.setSelected(false);
		HebronRB.setSelected(false);
		YafaRB.setSelected(false);
		GazaRB.setSelected(false);
		RafahRB.setSelected(false);
		textaria.setText(null);
		found.setFill(Color.CORNSILK);
		textaria.setDisable(true);
		Disable();
		for (int i = index2 - 1; i >= index1; i--) {
			anchor.getChildren().remove(i);
		}
		index1 = 0;
		index2 = 0;
	}

	@FXML
	public void Startcanel(ActionEvent event) {
		startId = 0;
		if (g1.getSelectedToggle() != null)
			g1.getSelectedToggle().setSelected(false);
		Disable();
	}

	@FXML
	public void Goalsave(ActionEvent event) {
		if (RamallahRB.isSelected()) {
			GoalList.add(14);
			RamallahRB.setSelected(false);
		}
		if (NablusRB.isSelected()) {
			GoalList.add(11);
			NablusRB.setSelected(false);
		}
		if (JeninRB.isSelected()) {
			GoalList.add(8);
			JeninRB.setSelected(false);

		}
		if (QalqiliaRB.isSelected()) {
			GoalList.add(12);
			QalqiliaRB.setSelected(false);

		}
		if (JerusalemRB.isSelected()) {
			GoalList.add(10);
			JerusalemRB.setSelected(false);
		}
		if (BethlehemRB.isSelected()) {
			GoalList.add(4);
			BethlehemRB.setSelected(false);

		}
		if (HebronRB.isSelected()) {
			GoalList.add(7);
			HebronRB.setSelected(false);

		}
		if (JerichoRB.isSelected()) {
			GoalList.add(9);
			JerichoRB.setSelected(false);

		}
		if (AlBirehRB.isSelected()) {
			GoalList.add(2);
			AlBirehRB.setSelected(false);

		}
		if (TulkarmRB.isSelected()) {
			GoalList.add(19);
			TulkarmRB.setSelected(false);

		}
		if (TubasRB.isSelected()) {
			GoalList.add(18);
			TubasRB.setSelected(false);

		}
		if (SalfeitRB.isSelected()) {
			GoalList.add(16);
			SalfeitRB.setSelected(false);

		}
		if (YafaRB.isSelected()) {
			GoalList.add(20);
			YafaRB.setSelected(false);

		}
		if (AkaRB.isSelected()) {
			GoalList.add(1);
			AkaRB.setSelected(false);

		}
		if (HaifaRB.isSelected()) {
			GoalList.add(6);
			HaifaRB.setSelected(false);

		}
		if (AlnasraRB.isSelected()) {
			GoalList.add(3);
			AlnasraRB.setSelected(false);

		}
		if (TabareaRB.isSelected()) {
			GoalList.add(17);
			TabareaRB.setSelected(false);

		}
		if (SafadRB.isSelected()) {
			GoalList.add(15);
			SafadRB.setSelected(false);

		}
		if (GazaRB.isSelected()) {
			GoalList.add(5);
			GazaRB.setSelected(false);

		}
		if (RafahRB.isSelected()) {
			GoalList.add(13);
			RafahRB.setSelected(false);

		}
		Disable();
	}

	@FXML
	public void Goalcanel(ActionEvent event) {
		GoalList.clear();
		RamallahRB.setSelected(false);
		AlBirehRB.setSelected(false);
		NablusRB.setSelected(false);
		SalfeitRB.setSelected(false);
		QalqiliaRB.setSelected(false);
		TulkarmRB.setSelected(false);
		HaifaRB.setSelected(false);
		AkaRB.setSelected(false);
		SafadRB.setSelected(false);
		TabareaRB.setSelected(false);
		AlnasraRB.setSelected(false);
		JeninRB.setSelected(false);
		TubasRB.setSelected(false);
		JerichoRB.setSelected(false);
		BethlehemRB.setSelected(false);
		JerusalemRB.setSelected(false);
		HebronRB.setSelected(false);
		YafaRB.setSelected(false);
		GazaRB.setSelected(false);
		RafahRB.setSelected(false);
		Disable();
	}

	public void Disable() {
		RamallahRB.setDisable(true);
		AlBirehRB.setDisable(true);
		NablusRB.setDisable(true);
		SalfeitRB.setDisable(true);
		QalqiliaRB.setDisable(true);
		TulkarmRB.setDisable(true);
		HaifaRB.setDisable(true);
		AkaRB.setDisable(true);
		SafadRB.setDisable(true);
		TabareaRB.setDisable(true);
		AlnasraRB.setDisable(true);
		JeninRB.setDisable(true);
		TubasRB.setDisable(true);
		JerichoRB.setDisable(true);
		BethlehemRB.setDisable(true);
		JerusalemRB.setDisable(true);
		HebronRB.setDisable(true);
		YafaRB.setDisable(true);
		GazaRB.setDisable(true);
		RafahRB.setDisable(true);
	}

	public void Enable() {
		RamallahRB.setDisable(false);
		AlBirehRB.setDisable(false);
		NablusRB.setDisable(false);
		SalfeitRB.setDisable(false);
		QalqiliaRB.setDisable(false);
		TulkarmRB.setDisable(false);
		HaifaRB.setDisable(false);
		AkaRB.setDisable(false);
		SafadRB.setDisable(false);
		TabareaRB.setDisable(false);
		AlnasraRB.setDisable(false);
		JeninRB.setDisable(false);
		TubasRB.setDisable(false);
		JerichoRB.setDisable(false);
		BethlehemRB.setDisable(false);
		JerusalemRB.setDisable(false);
		HebronRB.setDisable(false);
		YafaRB.setDisable(false);
		GazaRB.setDisable(false);
		RafahRB.setDisable(false);
	}

	@FXML
	public void Exit(ActionEvent event) {
		Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
		stage.close();
	}

	public void Reslut(String str) {
		Stage st = new Stage();
		Label textArea = new Label(str);
		textArea.setTranslateX(10);
		textArea.setTranslateY(10);
		textArea.setTextFill(Color.BLACK);
		textArea.setFont(Font.font("Cambria", FontWeight.EXTRA_BOLD, 16));
		Button exit = new Button("Exit");
		exit.setTranslateX(260);
		exit.setTranslateY(70);
		AnchorPane p = new AnchorPane();
		p.getChildren().addAll(textArea, exit);
		exit.setOnAction(e -> st.close());
		Scene ss = new Scene(p, 300, 100);
		st.setScene(ss);
		st.setTitle("Warning Window");
		st.show();
	}

	public String GetXY(int id) {
		String XY = "";
		if (id == 14) {
			XY = XY + RamallahRB.getLayoutX();
			XY = XY + "," + RamallahRB.getLayoutY();
		} else if (id == 11) {
			XY = XY + NablusRB.getLayoutX();
			XY = XY + "," + NablusRB.getLayoutY();
		} else if (id == 8) {
			XY = XY + JeninRB.getLayoutX();
			XY = XY + "," + JeninRB.getLayoutY();
		} else if (id == 12) {
			XY = XY + QalqiliaRB.getLayoutX();
			XY = XY + "," + QalqiliaRB.getLayoutY();
		} else if (id == 10) {
			XY = XY + JerusalemRB.getLayoutX();
			XY = XY + "," + JerusalemRB.getLayoutY();
		} else if (id == 4) {
			XY = XY + BethlehemRB.getLayoutX();
			XY = XY + "," + BethlehemRB.getLayoutY();
		} else if (id == 7) {
			XY = XY + HebronRB.getLayoutX();
			XY = XY + "," + HebronRB.getLayoutY();
		} else if (id == 9) {
			XY = XY + JerichoRB.getLayoutX();
			XY = XY + "," + JerichoRB.getLayoutY();
		} else if (id == 2) {
			XY = XY + AlBirehRB.getLayoutX();
			XY = XY + "," + AlBirehRB.getLayoutY();
		} else if (id == 10) {
			XY = XY + TulkarmRB.getLayoutX();
			XY = XY + "," + TulkarmRB.getLayoutY();
		} else if (id == 18) {
			XY = XY + TubasRB.getLayoutX();
			XY = XY + "," + TubasRB.getLayoutY();
		} else if (id == 16) {
			XY = XY + SalfeitRB.getLayoutX();
			XY = XY + "," + SalfeitRB.getLayoutY();
		} else if (id == 20) {
			XY = XY + YafaRB.getLayoutX();
			XY = XY + "," + YafaRB.getLayoutY();
		} else if (id == 1) {
			XY = XY + AkaRB.getLayoutX();
			XY = XY + "," + AkaRB.getLayoutY();
		} else if (id == 6) {
			XY = XY + HaifaRB.getLayoutX();
			XY = XY + "," + HaifaRB.getLayoutY();
		} else if (id == 3) {
			XY = XY + AlnasraRB.getLayoutX();
			XY = XY + "," + AlnasraRB.getLayoutY();
		} else if (id == 17) {
			XY = XY + TabareaRB.getLayoutX();
			XY = XY + "," + TabareaRB.getLayoutY();
		} else if (id == 15) {
			XY = XY + SafadRB.getLayoutX();
			XY = XY + "," + SafadRB.getLayoutY();
		} else if (id == 5) {
			XY = XY + GazaRB.getLayoutX();
			XY = XY + "," + GazaRB.getLayoutY();
		} else if (id == 13) {
			XY = XY + RafahRB.getLayoutX();
			XY = XY + "," + RafahRB.getLayoutY();
		}
		return XY;
	}

	public void DrawLine() {
		index1 = anchor.getChildren().size();
		for (int i = 0; i < PathOfSol.size() - 1; i++) {
			String s1 = GetXY(PathOfSol.get(i));
			String s2 = GetXY(PathOfSol.get(i + 1));
			String[] splitXY1 = s1.split(",");
			String[] splitXY2 = s2.split(",");
			int v = PathOfSol.size() - i;
			Label l = new Label(String.valueOf(v));
			l.setTextFill(Color.DEEPPINK);
			l.setFont(Font.font("Cambria", FontWeight.EXTRA_BOLD, 18));
			l.setTranslateX(Double.parseDouble(splitXY1[0]) - 5);
			l.setTranslateY(Double.parseDouble(splitXY1[1]));
			if (i == (PathOfSol.size() - 2)) {
				v = 1;
				Label l2 = new Label(String.valueOf(v));
				l2.setTextFill(Color.DEEPPINK);
				l2.setFont(Font.font("Cambria", FontWeight.EXTRA_BOLD, 18));
				l2.setTranslateX(Double.parseDouble(splitXY2[0]) - 5);
				l2.setTranslateY(Double.parseDouble(splitXY2[1]));
				anchor.getChildren().add(l2);
			}
			Line line = new Line(Double.parseDouble(splitXY1[0]) + 10, Double.parseDouble(splitXY1[1]) + 10,
					Double.parseDouble(splitXY2[0]) + 10, Double.parseDouble(splitXY2[1]) + 10);
			line.setStroke(Color.GREEN);
			line.setStrokeWidth(4);
			anchor.getChildren().addAll(l, line);
		}
		index2 = anchor.getChildren().size();

	}

	public void getAlpha() {
		Stage st = new Stage();
		Label textArea = new Label("How to Select Alpha?");
		RadioButton radom = new RadioButton("Randomly");
		RadioButton Hour = new RadioButton("Enter an Hour");
		ToggleGroup A = new ToggleGroup();
		radom.setToggleGroup(A);
		Hour.setToggleGroup(A);
		VBox vv = new VBox(10);
		vv.getChildren().addAll(Hour, radom);
		vv.setTranslateX(20);
		vv.setTranslateY(50);
		textArea.setTranslateX(20);
		textArea.setTranslateY(10);
		textArea.setTextFill(Color.BLACK);
		textArea.setFont(Font.font("Cambria", FontWeight.EXTRA_BOLD, 16));
		Button exit = new Button("Exit");
		exit.setTranslateX(20);
		exit.setTranslateY(120);
		Button next = new Button("Next");
		next.setTranslateX(165);
		next.setTranslateY(120);
		AnchorPane p = new AnchorPane();
		next.setOnAction(e -> {
			if (radom.isSelected()) {
				Alpha = Math.random() + 0.5;
				DecimalFormat df = new DecimalFormat("#.00");
				Alpha = Double.valueOf(df.format(Alpha));
				if (Alpha < 0.8) {
					Alpha = 0.8;
				}
				st.close();
				Optimal2Algo();
			} else if (Hour.isSelected()) {
				p.getChildren().clear();
				Label ll = new Label("Select The Hour:");
				ll.setTextFill(Color.BLACK);
				ll.setFont(Font.font("Cambria", FontWeight.EXTRA_BOLD, 16));
				ll.setTranslateX(20);
				ll.setTranslateY(10);
				ComboBox<String> combo = new ComboBox<String>();
				combo.getItems().addAll("7:00 AM - 10:00 AM", "10:00 AM - 2:00 PM", "2:00 PM - 5:00 PM",
						"5:00 PM - 10:00 PM", "10:00 PM - 7:00 AM");
				combo.setTranslateX(20);
				combo.setTranslateY(50);
				combo.setPromptText("Select One");
				Button save = new Button("Save");
				save.setTranslateX(165);
				save.setTranslateY(120);
				p.getChildren().addAll(ll, combo, save, exit);
				save.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						// TODO Auto-generated method stub
						if (combo.getSelectionModel().isEmpty()) {
							Reslut("Select One!!!!!!!!!!!!!!!!");
						} else {
							String Ho = combo.getSelectionModel().getSelectedItem();
							if (Ho.compareTo("7:00 AM - 10:00 AM") == 0) {
								Alpha = 1.5;
								st.close();
								Optimal2Algo();
							} else if (Ho.compareTo("10:00 AM - 2:00 PM") == 0) {
								Alpha = 1.2;
								st.close();
								Optimal2Algo();
							} else if (Ho.compareTo("2:00 PM - 5:00 PM") == 0) {
								Alpha = 1.4;
								st.close();
								Optimal2Algo();
							} else if (Ho.compareTo("5:00 PM - 10:00 PM") == 0) {
								Alpha = 1;
								st.close();
								Optimal2Algo();
							} else if (Ho.compareTo("10:00 PM - 7:00 AM") == 0) {
								Alpha = 0.8;
								st.close();
								Optimal2Algo();
							}
						}
					}
				}

				);
			} else {
				Reslut("Select One!!!!!!!!!!!!!!!!");

			}
		});
		p.getChildren().addAll(vv, textArea, next, exit);
		exit.setOnAction(e -> st.close());
		Scene ss = new Scene(p, 225, 150);
		st.setScene(ss);
		st.setTitle("Alpha");
		st.show();
	}
}