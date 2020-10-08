package util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class TimeUtil {

	/**
	 * Recibe strings con la fecha dividida en año mes y dia y devuelve true si es una fecha valida
	 * @param ano
	 * @param mes
	 * @param dia
	 * @return
	 */
	public static boolean chechDate(String ano, String mes, String dia) {
		//		Date date = new Date();
		String date = ano.trim() + "-" + mes.trim() + "-" + dia.trim();
		return checkDate(date);
	}

	
	private static boolean checkDate(String date) {
		
		 try {
			 System.out.println(date);
	            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	            df.setLenient(false);
	            df.parse(date);
	            return true;
	        } catch (ParseException e) {
	            return false;
	        }
	}
	
	/**
	 * Transforma una fecha en formato java en una valida para hacer query
	 * @param date
	 * @return
	 */
	public static String DateToSQL(Date date) {
		String Sdate = date.getYear()+ "-" + date.getMonth() + "-" + date.getDate();
		return Sdate;
	}
	


}
