package util;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
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
		@SuppressWarnings("deprecation")
		String Sdate = date.getYear()+ "-" + date.getMonth() + "-" + date.getDate();
		return Sdate;
	}
	
	/** 
	 * Convierte fecha repesentada como un string iso a fecha java (para conversion de entradas de tipo fecha)
	 */
	public static Date isoStringToDate(String isoDateString) {
		try {
		return new SimpleDateFormat("yyyy-MM-dd").parse(isoDateString);
		} catch (ParseException e) {
			throw new ApplicationException("Formato ISO incorrecto para fecha: "+isoDateString);
		}
	}
	/** 
	 * Convierte fecha java a un string formato iso (para display o uso en sql) 
	 */
	public static String dateToIsoString(Date javaDate) {
		Format formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(javaDate);
	}
	

	/**
	 * Recibe una fecha y comprueba si alguien nacido en esa fecha es mayor de edad
	 * @param birthday
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static boolean isAdult(Date birthday) {
		
		Date date = new Date();
		date.setYear(date.getYear() - 18);
//		System.out.println(birthday);
//		System.out.println(date);
		return !date.before(birthday);
	}
	


}
