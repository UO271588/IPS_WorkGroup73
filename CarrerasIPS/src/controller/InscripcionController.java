package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JOptionPane;

import model.inscription.InscriptionModel;
import ui.InscripcionFrame;
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
							imprimirFormulario();
							view.getBtnPagar().setEnabled(true);
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
		System.out.println("----------------------------------------------------");
		System.out.println("JUSTIFICANTE DE INSCRIPCION: ");
		System.out.println("Nombre atleta: " + model.getJustificante().getNombre());
		System.out.println("Competición: "+ model.getJustificante().getnombreCompeticion());
		System.out.println("Categoria: "+ model.getJustificante().getCategoria());
		System.out.println("Fecha inscripcion: " + TimeUtil.dateToIsoString(model.getJustificante().getFecha_inscripcion()));
		System.out.println("Cantidad: "+ model.getJustificante().getCantidad());
		System.out.println("Estado: " + model.getJustificante().getEstado());
		System.out.println("----------------------------------------------------");
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