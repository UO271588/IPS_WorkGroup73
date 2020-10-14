package controller;

import java.util.Date;

import javax.swing.JOptionPane;

import model.participant.ParticipantDto;
import model.participant.ParticipantModel;
import ui.RegisterFrame;
import util.TimeUtil;

public class registerController {

	RegisterFrame vista;

	public registerController(RegisterFrame vent) {
		this.vista = vent;
		// TODO Auto-generated constructor stub
	}

	public void aceptarAction(RegisterFrame registerFrame) {
		boolean correct = true;
		boolean sexMale = false;
		ParticipantDto part = null;

		//comprobar que los campos de texto esta completos como deben
		String aviso = "";	//se almacenara la informacion incorrecta
		if(registerFrame.getTextNombre().getText().trim().length() == 0) {
			aviso += "El nombre es un campo obligatorio\n";
			correct = false;
		}
		if(registerFrame.getTextApellidos().getText().trim().length() == 0) {
			aviso += "El apellido es un campo obligatorio\n";
			correct = false;
		}
		if(registerFrame.getTextMail().getText().trim().length() == 0) {
			aviso += "El Mail es un campo obligatorio\n";
			correct = false;
		}
		else if(!checkMail()) {
			aviso += "Un usuario con el E-Mail introducido ya esta registrado como usuario\n";
			registerFrame.getTextMail().setText("");
			correct = false;
		}
		if(registerFrame.getTextDNI().getText().trim().length() == 0) {
			aviso += "El DNI es un campo obligatorio\n";
			correct = false;
		}
		else if(!checkDniValido()) {
			aviso += "El DNI no es valido o un usuario con el DNI introducido ya esta registrado como usuario\n";
			correct = false;
		}
		if(!checkAnoValido()) {
			aviso += "No ha seleccionado una fecha valida\n";
			correct = false;
		}
		if(registerFrame.rdbtnH.isSelected()) {
			sexMale = true;

		}
		else if(registerFrame.rdbtnM.isSelected()) {
			sexMale = false;
		}
		else {
			aviso += "Debe de seleccionar el sexo\n";
			correct = false;
		}
		int ano = 0, mes = 0, dia = 0;
		//deprecated

		Date birthday = null;
		if(correct) {	//solo entra si la fecha tiene al menos el formato correcto
			ano = Integer.parseInt(registerFrame.getTextAno().getText());
			mes = Integer.parseInt(registerFrame.getTextMes().getText());
			dia = Integer.parseInt(registerFrame.getTextDia().getText());
			
			//conversiones de fechas para java.Date
		
			birthday = new Date();
			birthday.setYear(ano - 1900);	//el 0 es 1900 para Date
			birthday.setMonth(mes -1 );		//mes 12 es enero por alguna razon
			birthday.setDate(dia);
			//comprueba que la fecha introducida corresponder a una persona mayor de 18 años
			if(!TimeUtil.isAdult(birthday)) {
				correct = false;
				aviso += "La edad minima para inscribirse son 18 a�os\n";
			}
		}

		if(correct) {	//si esta correcto tenemos que a�adir la entrada a la base de datos
			
			part = new ParticipantDto(registerFrame.getTextNombre().getText(), registerFrame.getTextApellidos().getText(),
					registerFrame.getTextDNI().getText(), registerFrame.getTextMail().getText(), birthday, sexMale);
			ParticipantModel.addParticipant(part);
			JOptionPane.showMessageDialog(null, "Participante introducido con exito");
			vista.reset();
		}
		else {
			JOptionPane.showMessageDialog(null, "Hay campos incorrectos, reviselos y vuelva a pulsar el boton aceptar\n" + aviso);
		}
		
	}


	protected boolean checkAnoValido() {
		if(vista.getTextAno().getText().length() != 4) {
			return false;
		}
		return TimeUtil.chechDate(vista.getTextAno().getText(), vista.getTextMes().getText(), vista.getTextDia().getText());
	}

	/**
	 * Checkea si el dni introducido es correcto, ha de tener todos los caracteres numericos excepto el ultimo
	 * @return
	 */
	protected boolean checkDniValido() {
		
		String dni = vista.getTextDNI().getText();
		String numeros = dni.substring(0, dni.length() - 1);
		try {
			Integer.parseInt(numeros);
		}
		catch(NumberFormatException e) {
			//el formato no es el correcto
			return false;
		}
		//TODO Añadir 
		return !ParticipantModel.checkDni(dni);
	}

	protected boolean checkMail() {
		return ParticipantModel.checkMail(vista.getTextMail().getText());
	}



}
