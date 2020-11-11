package model.category;

import util.TimeUtil;
import util.database.Database;

public class CategoryModel {

	public static void addCategory(CategoryDto category) {
		Database db = new Database();
		String sql = "INSERT INTO CATEGORY (IDCATEGORY, IDCOMPETITION, SEX,NAME,INITIAL_AGE,FINAL_AGE)"
				+ "	VALUES (?,?,?,?,?,?);";
		db.executeUpdate(sql,category.idCategory, category.idCompetition, category.sex, category.name
				, category.inital_Age, category.final_Age);

		
	}

}