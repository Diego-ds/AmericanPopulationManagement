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

public class Manager {
	
	public static final String populationPath = "data/population_by_country_2020.csv";
	public static final String namePath = "data/names.csv";
	public static final String lastNamePath = "data/last_names.csv";
	public static final String agesPath = "data/ages.csv";
	public static final String allowedPath = "data/allowedCountries.txt";
	
	private AVLTree<String, Record> tree;
	
	public Manager() {
		tree = new AVLTree<String,Record>();
	}
	
	public Record[] generateRecords(int quantity) throws IOException {
		
		Record[] records = new Record[quantity];
		
		BufferedReader fNameReader = new BufferedReader(new FileReader(namePath));
		BufferedReader lNameReader = new BufferedReader(new FileReader(lastNamePath));
		BufferedReader ageReader = new BufferedReader(new FileReader(agesPath));
		BufferedReader populationReader = new BufferedReader(new FileReader(populationPath));
		BufferedReader allowedReader = new BufferedReader(new FileReader(allowedPath));
		
		//Name Storage
		String temp;
		ArrayList<String> names = new ArrayList<>();
		
		while ((temp = fNameReader.readLine()) != null) {
			names.add(temp);
		}
		
		//Last Name Storage
		ArrayList<String> lastNames = new ArrayList<>();
		while ((temp = lNameReader.readLine()) != null) {
			lastNames.add(temp);
		}
		
		//Age Storage
		ArrayList<String> ages = new ArrayList<>();
		while ((temp = ageReader.readLine()) != null) {
			ages.add(temp);
		}
		
		//Population Storage
		allowedReader.mark(410);
		ArrayList<String> population = new ArrayList<>();
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
		
		//Records Generation
		Random r = new Random();
		for (int i = 0; i < quantity; i++) {
			
			//Code
			long numeric = r.nextLong();
			int letterAscii = 65 + r.nextInt(26);
			char letter = (char) letterAscii;
			String code = letter + "" + numeric;
			
			//Name
			int index = r.nextInt(6783);
			if (index == 0) {
				index = 1;
			}
			String name = names.get(index).split(",")[0];
			
			//Last Name
			index = r.nextInt(162255);
			if (index == 0 || index == 162254) {
				index = 1;
			}
			String lastName = lastNames.get(index).split(",")[0];
			
			//Gender
			index = r.nextInt(2);
			String gender;
			if (index == 0) {
				gender = Record.FEMALE;
			}
			else {
				gender = Record.MALE;
			}
			
			//Age
			int per = r.nextInt(101);
			String percentage = Integer.toString(per);
			String age = "0";
			
			boolean exit = false;
			for (int j = 0; j < ages.size() && !exit; i++) {
				if (percentage.compareTo(ages.get(j).split(",")[2]) < 0) {
					int minNumber = Integer.parseInt(ages.get(j).split(",")[0]);
					int maxNumber = Integer.parseInt(ages.get(j).split(",")[1]);
					
					
					age = Integer.toString(minNumber + r.nextInt(maxNumber + 1));
					exit = true;
				}
			}
			
			//Height
			double baseAge = 1; 
			if (Integer.parseInt(age) < 9) {
				baseAge = 0;
			}
			else if (Integer.parseInt(age) < 14) {
				baseAge = 0.50;
			}
			
			double prevHeight = baseAge + r.nextDouble();
			DecimalFormat df = new DecimalFormat("#.##");
			df.setRoundingMode(RoundingMode.DOWN);
			String height = df.format(prevHeight);
			
			//BirthDate
			LocalDate baseDate = LocalDate.now();
			baseDate.minusYears(Integer.parseInt(age));
			baseDate.minusDays(r.nextInt(342));
			String birthDate = baseDate.toString();
			
			//Nationality
		}
	}
}