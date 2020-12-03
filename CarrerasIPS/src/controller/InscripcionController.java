package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import model.inscription.InscriptionModel;
import ui.InscripcionFrame;
import ui.ReceiptFrame;
import util.TimeUtil;

public class InscripcionController {

	InscriptionModel model;
	InscripcionFrame view;
	Date date = new Date();
	public InscripcionController(InscriptionModel model, InscripcionFrame view) {
		this.model = model;
		this.view = view;
		initController();
	}
	
	@SuppressWarnings("deprecation")
	public void initView() {
		view.setFechaHoy(date.toLocaleString());
	}
	
	public void initController() {
		initView();
		view.getBtnInscribirse().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(view.comprobarCampos()==true) {
					if(model.existeEmail(view.getTextFieldEmail().getText())) {
						if(InscriptionModel.existeYaInscripcion(InscriptionModel.getDni(getEmailFromView()),
								getNombreCompeticionView())==false) {
							if(model.categoria(view.getTextFieldEmail().getText(), view.getTextFieldNombreCompeticion().getText()).isBlank()) {
								JOptionPane.showMessageDialog(null, "No hay ninguna categoria para la edad del participante");
							}
							else {
								imprimirFormulario();
								view.getBtnPagar().setEnabled(true);
							}
						}
						else {
							JOptionPane.showMessageDialog(null, "Ya estas insctito en esta competicion");
						}
					}
			}
		}
	});
		
		
		
		
	}

	private void imprimirFormulario() {
		
		model.justificante(view.getTextFieldEmail().getText(), view.getTextFieldNombreCompeticion().getText());
		
		ReceiptFrame rf = new ReceiptFrame(model.getJustificante().getNombre(), model.getJustificante().getnombreCompeticion(),
				model.getJustificante().getCategoria(),TimeUtil.dateToIsoString(model.getJustificante().getFecha_inscripcion()),
				model.getJustificante().getCantidad(), model.getJustificante().getEstado(),model.getJustificante().getDorsal());
		rf.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		rf.setVisible(true);
//		System.out.println("----------------------------------------------------");
//		System.out.println("JUSTIFICANTE DE INSCRIPCION: ");
//		System.out.println("Nombre atleta: " + );
//		System.out.println("Competición: "+ );
//		System.out.println("Categoria: "+ );
//		System.out.println("Fecha inscripcion: " + );
//		System.out.println("Cantidad: "+ );
//		System.out.println("Estado: " + );
//		System.out.println("----------------------------------------------------");
	}
	
	public String getEmailFromView() {
		String email = view.getTextFieldEmail().getText();
		return email;
		
	}
	
	public String getNombreCompeticionView() {
		String nombreCompeticion = view.getTextFieldNombreCompeticion().getText();
		return nombreCompeticion;
	}
	
	
}