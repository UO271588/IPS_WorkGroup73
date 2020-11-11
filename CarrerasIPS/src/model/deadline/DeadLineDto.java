package model.deadline;

import java.util.Date;

import util.TimeUtil;

public class DeadLineDto {

	public String idDeadLine;	
	public String idCompetition;
	public String initialDate;
	public String finalDate;
	public int fee;
	
	
	
	public String getIdDeadLine() {
		return idDeadLine;
	}
	public void setIdDeadLine(String idDeadLine) {
		this.idDeadLine = idDeadLine;
	}
	public Date getInitialDateAsDate() {
		return TimeUtil.isoStringToDate(initialDate);
	}
	public Date getFinalDateAsDate() {
		return TimeUtil.isoStringToDate(finalDate);
	}
	
	public void setInitialDateAsDate(Date iniDate) {
		this.initialDate = TimeUtil.dateToIsoString(iniDate);
	}
	public void setFinalDateAsDate(Date finalDate) {
		this.finalDate = TimeUtil.dateToIsoString(finalDate);
	}
	public String getIdCompetition() {
		return idCompetition;
	}
	public void setIdCompetition(String idCompetition) {
		this.idCompetition = idCompetition;
	}
	public String getInitialDate() {
		return initialDate;
	}
	public void setInitialDate(String initialDate) {
		this.initialDate = initialDate;
	}
	public String getFinalDate() {
		return finalDate;
	}
	public void setFinalDate(String finalDate) {
		this.finalDate = finalDate;
	}
	public int getFee() {
		return fee;
	}
	public void setFee(int fee) {
		this.fee = fee;
	}
	
}
