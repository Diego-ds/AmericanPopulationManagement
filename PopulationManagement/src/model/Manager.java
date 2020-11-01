package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import collections.AVLTree;

public class Manager {
	
	public static final String populationPath = "data/population_by_country_2020.csv";
	public static final String namePath = "data/names.csv";
	public static final String lastNamePath = "data/last_names.csv";
	public static final String agesPath = "data/ages.csv";
	
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
		
		//Information take
		String temp;
		ArrayList<String> names = new ArrayList<>();
		
		while ((temp = fNameReader.readLine()) != null) {
			names.add(temp);
		}
		
		ArrayList<String> lastNames = new ArrayList<>();
		while ((temp = lNameReader.readLine()) != null) {
			lastNames.add(temp);
		}
		
		ArrayList<String> ages = new ArrayList<>();
		while ((temp = ageReader.readLine()) != null) {
			ages.add(temp);
		}
		
		ArrayList<String> population = new ArrayList<>();
		while ((temp = ageReader.readLine()) != null) {
			ages.add(temp);
		}
	}
}
