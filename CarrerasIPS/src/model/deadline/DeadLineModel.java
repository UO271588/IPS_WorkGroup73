package model.deadline;

import model.category.CategoryDto;
import util.database.Database;

public class DeadLineModel {
	
	public static void addDeadLine(DeadLineDto deadLine) {
		Database db = new Database();
		String sql = "INSERT INTO INSCRIPTION_DEADLINE (IDDEADLINE, IDCOMPETITION, INITIALDATE,FINALDATE,FEE)"
				+ "	VALUES (?,?,?,?,?);";
		db.executeUpdate(sql,deadLine.idDeadLine, deadLine.idCompetition, deadLine.initialDate, deadLine.finalDate
				, deadLine.fee);

		
	}

}
