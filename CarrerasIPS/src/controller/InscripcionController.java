package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import model.inscription.InscriptionModel;
import ui.InscripcionFrame;

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

		//view.getBtnInscribirse().addActionListener(e -> imprimirFormulario());

		view.getBtnInscribirse().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(view.comprobarCampos()==true) {
					if(model.existeEmail(view.getTextFieldEmail().getText())) {
						if(model.existeYaIniscripcion(getEmailFromView(), getNombreCompeticionView())==false) {
							imprimirFormulario();
				}
			}
			}
			}
		});
		
		
		
		
	}

	private void imprimirFormulario() {
		model.justificante(view.getTextFieldEmail().getText(), view.getTextFieldNombreCompeticion().getText());
		System.out.println("JUSTIFICANTE DE INSCRIPCION: ");
		System.out.println("Nombre atleta: " + model.getJustificante().getNombre());
		System.out.println("Competición: "+ model.getJustificante().getnombreCompeticion());
		System.out.println("Categoria: "+ model.getJustificante().getCategoria());
		System.out.println("Fecha inscripcion: " + model.dateToIsoString(model.getJustificante().getFecha_inscripcion()));
		System.out.println("Cantidad: "+ model.getJustificante().getCantidad());
		System.out.println("Estado: " + model.getJustificante().getEstado());
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
