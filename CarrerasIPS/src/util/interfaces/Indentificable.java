package util.interfaces;

import javax.swing.JFrame;

import model.participant.ParticipantDto;
import model.participant.ParticipantDtoPojo;

public interface Indentificable {

	
	/**
	 * Este metodo se encarga de realmente iniciar toda la frame que se introduce,
	 * en el constructor por tanto no se ha de crear (casi) nada y moverlo todo al initialize que se llama desde el frame que lo crea
	 * @param part
	 */

	void initialize(ParticipantDtoPojo part, JFrame parent);
}
