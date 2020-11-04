package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

import collections.AVLTree;
import collections.Node;

public class Manager {
	
	public static final String populationPath = "data/population_by_country_2020.csv";
	public static final String namePath = "data/names.csv";
	public static final String lastNamePath = "data/last_names.csv";
	public static final String agesPath = "data/ages.csv";
	public static final String allowedPath = "data/allowedCountries.txt";
	
	private AVLTree<String, Record> tree;
	
	private ArrayList<String> names;
	private ArrayList<String> lastNames;
	private ArrayList<String> ages;
	private ArrayList<String> population;
	private Record[] generatedRecords;
	
	
	public Manager() {
		tree = new AVLTree<String,Record>();
		names = new ArrayList<>();
		lastNames = new ArrayList<>();
		ages = new ArrayList<>();
		population = new ArrayList<>();
	}
	
	public boolean loadData() throws IOException {
	
		BufferedReader fNameReader = new BufferedReader(new FileReader(namePath));
		BufferedReader lNameReader = new BufferedReader(new FileReader(lastNamePath));
		BufferedReader ageReader = new BufferedReader(new FileReader(agesPath));
		BufferedReader populationReader = new BufferedReader(new FileReader(populationPath));
		BufferedReader allowedReader = new BufferedReader(new FileReader(allowedPath));
		
		//Name Load
		String temp;
		names = new ArrayList<>();
		
		while ((temp = fNameReader.readLine()) != null) {
			names.add(temp);
		}
		
		//Last Name Load
		lastNames = new ArrayList<>();
		while ((temp = lNameReader.readLine()) != null) {
			lastNames.add(temp);
		}
		
		//Age Load
		ages = new ArrayList<>();
		while ((temp = ageReader.readLine()) != null) {
			ages.add(temp);
		}
		
		//Population Load
		allowedReader.mark(410);
		population = new ArrayList<>();
		String aux; 
		
		while ((temp = populationReader.readLine()) != null) {
			boolean exit = false;
			String countryName = temp.split(",")[0];
			allowedReader.reset();
			
			while ((aux = allowedReader.readLine()) != null && !exit) {
				if (countryName.equalsIgnoreCase(aux)) {
					population.add(temp);
					exit = true;
				}
			}
		}
		
		fNameReader.close();
		lNameReader.close();
		ageReader.close();
		populationReader.close();
		allowedReader.close();
		
		//All data bases need at least one line
		if (names.size() != 0 && lastNames.size() != 0 && ages.size() != 0 && 
				population.size() != 0) {
			return true;
		}
		else {
			return false;
		}
	}

	public Record generateRecord() throws IOException {

		//Records Generation
		Random r = new Random();
		boolean exit = false;

		//Code
		long numeric = r.nextLong(); //Gets a random long number
		int letterAscii = 65 + r.nextInt(26); //Gets a random capital letter
		char letter = (char) letterAscii; 
		String code = letter + "" + numeric; //Union between letter and number

		//Name
		int index = r.nextInt(names.size() - 1); //Gets a random index in the name database
		if (index == 0) { //0 cannot be used, so it gets changed to 1
			index = 1;
		}
		String name = names.get(index).split(",")[0]; //Gets the name from the row

		//Last Name
		index = r.nextInt(lastNames.size() - 1); //Same as name
		if (index == 0 || index == 162254) {
			index = 1;
		}
		String lastName = lastNames.get(index).split(",")[0];

		//Gender
		index = r.nextInt(2); // 50/50 chance for being a male or female
		String gender;
		if (index == 0) {
			gender = Record.FEMALE;
		}
		else {
			gender = Record.MALE;
		}

		//Age (Needed for birth date calculation and height calculation)
		int per = r.nextInt(101);
		String percentage = Integer.toString(per) + "%"; //Necessary for a good comparison
		int age = 0;

		exit = false;
		for (int i = 0; i < ages.size() && !exit; i++) {
			if (percentage.compareTo(ages.get(i).split(",")[2]) < 0) {
				
				int minNumber = Integer.parseInt(ages.get(i).split(",")[0]);
				int maxNumber = Integer.parseInt(ages.get(i).split(",")[1]);
				int difference = maxNumber - minNumber;
				age = minNumber + r.nextInt(difference);
				exit = true;
			}
		}
		System.out.println("AGE: " + age);

		//Height
		double baseAge = 1.00; 
		if (age < 9) {
			baseAge = 0;
		}
		else if (age < 14) {
			baseAge = 0.50;
		}

		double prevHeight = baseAge + r.nextDouble();
		String heightProcess = Double.toString(prevHeight);
		if (heightProcess.length() > 4) {
			heightProcess = Double.toString(prevHeight).substring(0,4);
		}
		double height = Double.parseDouble(heightProcess);

		//BirthDate
		LocalDate baseDate = LocalDate.now();
		baseDate = baseDate.minusYears(age);
		baseDate = baseDate.minusDays(r.nextInt(342));
		String birthDate = baseDate.toString();

		//Nationality
		
		//Gets the max percentage (
		String maxPerString = population.get(0).split(",")[10]; 
		double maxPer = Double.parseDouble(maxPerString.substring(0, maxPerString.length() - 1));

		double chosenPer = maxPer * r.nextDouble();
		
		String nationPer = Double.toString(chosenPer);
		if (nationPer.length() > 4) {
			nationPer = Double.toString(chosenPer).substring(0,4) + "%";
		}
		String perCondition = "";
		
		exit = false;
		for (int i = population.size() - 1; i >= 0 && !exit; i--) {
			if (nationPer.compareTo(population.get(i).split(",")[10]) <= 0) {
				perCondition = population.get(i).split(",")[10];
				exit = true;
			}
		}

		
		ArrayList<String> candidateCountries = new ArrayList<>();
		for (int i = 0; i < population.size(); i++) {
			if (perCondition.compareTo(population.get(i).split(",")[10]) == 0) {
				candidateCountries.add(population.get(i).split(",")[0]);
			}
		}
		
		String nationality;
		if (candidateCountries.size() == 1) {
			nationality = candidateCountries.get(0);
		}
		
		else {
			int finalDecision = r.nextInt(candidateCountries.size() - 1);
			
			nationality = candidateCountries.get(finalDecision);
		}
	
		Record record = new Record (code, name, lastName, gender, birthDate, height, 
				nationality);
		
		return record;
	}

	public void setRecords(Record[] records) {
		generatedRecords = records;
	}

	public ArrayList<String> searchByName(String name) {
		ArrayList<String> names= new ArrayList<String>();
		Node<String,Record> node = tree.getRoot();
		if(node!=null) {
			return searchByName(names,node,name);
		}
		return null;
	}
	
	private ArrayList<String> searchByName(ArrayList<String> names,Node<String,Record> current,String name) {
		String toCompare = current.getValue().getName().substring(0,name.length());
		if(current.getValue().getName().contains(name)) {
			if(toCompare.contains(name)) {
				names.add(current.getValue().getName());
			}
		}
		
		searchByName();
		return names;
		
	}
}