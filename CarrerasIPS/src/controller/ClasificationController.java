package controller;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

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
		categorias.add(0, "HOMBRE");
		categorias.add(0, "MUJER");
		categorias.add(0, "ABSOLUTA");
		view.getCbCategory().setModel(new DefaultComboBoxModel<String>((String[]) categorias.toArray(new String[1])));
		
		
	}

	public void loadRows(String category, JPanel paneParticipantes) {
		int posicion = 0;
		for(ClasificationDto clasificacion : clasificaciones) {
			if(!clasificacion.categoryname.equals(category)) {
				continue;
			}
			System.out.println("Bien");
			posicion++;
			JPanel panelClasificacion = new JPanel();
			panelClasificacion.setLayout(new GridLayout(1, 5, 1, 2));
			panelClasificacion.setBackground(Color.white);
			JLabel txtDorsal = new JLabel();
			JLabel txtCategory = new JLabel();
			JLabel txtNombre = new JLabel();
			JLabel txtSexo = new JLabel();
			JLabel txtPosicion = new JLabel();
			JLabel txtTiempo = new JLabel();
			JPanel pnlNum = new JPanel();
			pnlNum.setBackground(Color.white);
			txtDorsal.setBorder(new LineBorder(new Color(0, 0, 0)));
			txtCategory.setBorder(new LineBorder(new Color(0, 0, 0)));
			txtNombre.setBorder(new LineBorder(new Color(0, 0, 0)));
			txtSexo.setBorder(new LineBorder(new Color(0, 0, 0)));
			txtPosicion.setBorder(new LineBorder(new Color(0, 0, 0)));
			txtTiempo.setBorder(new LineBorder(new Color(0, 0, 0)));
			
			pnlNum.setLayout(new GridLayout(1, 2, 1, 0));
			
			
			panelClasificacion.add(pnlNum);
			// Creacion textField poscion
			txtPosicion.setText(posicion + "");
			txtPosicion.setHorizontalAlignment(JTextField.CENTER);
			pnlNum.add(txtPosicion);

			// Creacion textField Dorsal
			txtDorsal.setText(clasificacion.dorsal);
			txtDorsal.setHorizontalAlignment(JTextField.CENTER);
			pnlNum.add(txtDorsal);
			
			// Creacion textField sexo
			txtSexo.setText(clasificacion.sexo);
			panelClasificacion.add(txtSexo);
			txtSexo.setHorizontalAlignment(JTextField.CENTER);

			// Creacion textField nombre participante
			txtNombre.setText(ParticipantModel.getBasicData(clasificacion.dni).name);
			panelClasificacion.add(txtNombre);
			txtNombre.setHorizontalAlignment(JTextField.CENTER);
			
			// Creacion textField Dorsal
			txtCategory.setText(clasificacion.categoryname);
			panelClasificacion.add(txtCategory);
			txtCategory.setHorizontalAlignment(JTextField.CENTER);

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
				if(clasificacion.tiempoInicio.equals("00:00:00") ) {
					txtTiempo.setText("dns");
				}
				else {
					txtTiempo.setText("dnf");
					
				}
			}
			panelClasificacion.add(txtTiempo);
			txtTiempo.setHorizontalAlignment(JTextField.CENTER);
			
			paneParticipantes.add(panelClasificacion);
			paneParticipantes.repaint();
			paneParticipantes.revalidate();
		}
		
	}

	public void loadAll(JPanel paneParticipantes) {
		paneParticipantes.removeAll();
		paneParticipantes.add(createLableFor("ABSOLUTA"));
		view.crearPanelesCarrera(clasificaciones, "ABSOLUTA");
		paneParticipantes.add(createLableFor("MUJER"));
		view.crearPanelesCarrera(clasificaciones, "MUJER");
		paneParticipantes.add(createLableFor("HOMBRE"));
		view.crearPanelesCarrera(clasificaciones, "HOMBRE");
		loadAllCategories(paneParticipantes);
		paneParticipantes.setLayout(new GridLayout(Math.max(10, paneParticipantes.getComponentCount()), 0));
		
	}
	
	private void loadAllCategories(JPanel paneParticipantes) {
		int excep = 0;
		for(String categoria : categorias) {
			if(excep++ < 3) {
				continue;
			}
			paneParticipantes.add(createLableFor(categoria));
			loadRows(categoria, paneParticipantes);
		}
		
	}

	protected Component createLableFor(String string) {
		JLabel label = new JLabel(string);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.BOLD, 14));
		label.setBackground(Color.LIGHT_GRAY);
		return label;
	}

}
