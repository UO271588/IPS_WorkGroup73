package model.category;

public class CategoryDto {
	public String idCategory;
	public String idCompetition;
	public String sex;
	public String name;
	public int inital_Age;
	public int final_Age;
	
	
	
	
	
	public void setSexMale() {
		sex = "HOMBRE";
	}
	public void setSexFemale() {
		sex = "MUJER";
	}
	public String getIdCategory() {
		return idCategory;
	}
	public void setIdCategory(String idCategory) {
		this.idCategory = idCategory;
	}
	public String getIdCompetition() {
		return idCompetition;
	}
	public void setIdCompetition(String idCompetition) {
		this.idCompetition = idCompetition;
	}
	public String getSex() {
		return sex;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getInital_Age() {
		return inital_Age;
	}
	public void setInital_Age(int inital_Age) {
		this.inital_Age = inital_Age;
	}
	public int getFinal_Age() {
		return final_Age;
	}
	public void setFinal_Age(int final_Age) {
		this.final_Age = final_Age;
	}

}
