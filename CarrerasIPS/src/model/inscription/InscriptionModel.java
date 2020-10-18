package model.inscription;

import java.sql.Connection;
import java.util.List;

import util.database.Database;

public class InscriptionModel {
	
	
	public List<InscriptionDto> getInscriptionList(String id){
		
		String sql = "select name,participante.dni, idcompetition, inscriptionDate,category, inscriptionstate from inscripcion, participante where participante.dni = inscripcion.dni and idcompetition = ?";
		Database db = new Database();
		
		
		return db.executeQueryPojo(InscriptionDto.class, sql, id);
		
	}

}
