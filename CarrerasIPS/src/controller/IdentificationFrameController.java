package controller;

import javax.swing.JOptionPane;

import model.participant.ParticipantDtoPojo;
import model.participant.ParticipantModel;
import ui.IdentificationFrame;

public class IdentificationFrameController {

	
	private IdentificationFrame view;

	public IdentificationFrameController(IdentificationFrame identificationFrame) {
		this.view = identificationFrame;
	}

	public void identificate(String mail) {
		if(!ParticipantModel.checkMail(mail)) {
			
			ParticipantDtoPojo dto =  ParticipantModel.getByEmail(mail);
			view.getNextFrame().initialize(dto, view);
			view.setVisible(false);
		}
		else {
			JOptionPane.showMessageDialog(null, "El mail introducido no corresponde con ningun usuario");
			view.getTextField().setText("");
			view.getTextField().requestFocus();
		}
	}

}