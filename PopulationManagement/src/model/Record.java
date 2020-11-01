package model;

public class Record {
	public static final String MALE = "Male";
	public static final String FEMALE = "Female";
	private String code;
	private String name;
	private String lastname;
	private String gender;
	private String birthDate;
	private double height;
	private String nacionality;
	//photo
	
	//Constructor
	public Record(String code, String name, String lastname, String gender, String birthDate, double height,
			String nacionality) {
		this.code = code;
		this.name = name;
		this.lastname = lastname;
		this.gender = gender;
		this.birthDate = birthDate;
		this.height = height;
		this.nacionality = nacionality;
	}
	//Setters and Getters
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public String getNacionality() {
		return nacionality;
	}
	public void setNacionality(String nacionality) {
		this.nacionality = nacionality;
	}
	
}
