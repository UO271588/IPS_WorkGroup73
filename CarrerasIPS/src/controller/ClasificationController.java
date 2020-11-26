package controller;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.category.CategoryDto;
import model.clasification.ClasificationDto;
import model.participant.ParticipantModel;
import ui.ClasificationsFrame;

public class ClasificationController {

	private List<String> categorias;
	private ClasificationsFrame view;
	private List<ClasificationDto> clasificaciones;

	public ClasificationController(List<ClasificationDto> clasificaciones, ClasificationsFrame view) {
		this.view = view;
		this.clasificaciones = clasificaciones;
		new DefaultComboBoxModel<String>(new String[] { "HOMBRE", "MUJER", "ABSOLUTA" });
		 categorias = new ArrayList<>();
		for(ClasificationDto dto : clasificaciones) {
			if(categorias.contains(dto.categoryname)){
				continue;
			}
			else {
				
				categorias.add(dto.categoryname);
			}
		}
	}

	public void loadClasifications() {
		view.getCbCategory().setModel(new DefaultComboBoxModel<String>((String[]) categorias.toArray(new String[1])));
		
		
	}

	int posicion = 0;
	public void loadRows(String category, JPanel paneParticipantes) {
		for(ClasificationDto clasificacion : clasificaciones) {
			if(!clasificacion.categoryname.equals(category)) {
				continue;
			}
			System.out.println("Bien");
			posicion++;
			JPanel panelClasificacion = new JPanel();
			panelClasificacion.setLayout(new GridLayout(1, 4, 0, 0));
			JTextField txtNombre = new JTextField();
			JTextField txtSexo = new JTextField();
			JTextField txtPosicion = new JTextField();
			JTextField txtTiempo = new JTextField();

			// Creacion textField poscion
			txtPosicion.setText(posicion + "");
			panelClasificacion.add(txtPosicion);
			txtPosicion.setColumns(10);
			txtPosicion.setHorizontalAlignment(JTextField.CENTER);

			// Creacion textField sexo
			txtSexo.setText(clasificacion.sexo);
			panelClasificacion.add(txtSexo);
			txtSexo.setColumns(10);
			txtSexo.setHorizontalAlignment(JTextField.CENTER);

			// Creacion textField nombre participante
			txtNombre.setText(ParticipantModel.getBasicData(clasificacion.dni).name);
			panelClasificacion.add(txtNombre);
			txtNombre.setColumns(10);
			txtNombre.setHorizontalAlignment(JTextField.CENTER);

			// Creacion textField tiempo
			String[] tiempoInicial = clasificacion.tiempoInicio.split(":");
			String[] tiempoFinal = clasificacion.tiempoFinal.split(":");
			int segundosIniciales = Integer.parseInt(tiempoInicial[0]) * 3600
					+ Integer.parseInt(tiempoInicial[1]) * 60 + Integer.parseInt(tiempoInicial[2]);
			int segundosFinales = Integer.parseInt(tiempoFinal[0]) * 3600 + Integer.parseInt(tiempoFinal[1]) * 60
					+ Integer.parseInt(tiempoFinal[2]);
			if (segundosIniciales != 0 && segundosFinales != 0) {
				int segundosTotales = segundosFinales - segundosIniciales;

				int horas = segundosTotales / 3600;
				int minutos = (segundosTotales - 3600 * horas) / 60;
				int segundos = (segundosTotales - 3600 * horas) - (minutos * 60);

				String tiempo = horas + ":" + minutos + ":" + segundos;
				txtTiempo.setText(tiempo);
			} else {
				txtTiempo.setText("---");
			}
			panelClasificacion.add(txtTiempo);
			txtTiempo.setColumns(10);
			txtTiempo.setHorizontalAlignment(JTextField.CENTER);
			
			paneParticipantes.add(panelClasificacion);
			paneParticipantes.repaint();
			paneParticipantes.revalidate();
		}
		
	}

}
