package controller;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.dnd.DropTargetListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import business.race.RaceDto;
import dbAccess.CompetitionsAccess;
import model.clasification.ClasificationAccess;
import model.clasification.ClasificationDto;
import model.inscription.InscriptionDto;
import model.inscription.InscriptionModel;
import model.time.TimeModel;
import ui.ClasificationsFrame;
import ui.InsciptionsListFrame;
import ui.RegisterFrame;
import ui.TimeReceptionFrame;
import util.TimeUtil;

public class InscriptionListController {



	InsciptionsListFrame vista;

	public InscriptionListController(InsciptionsListFrame vent) {
		this.vista = vent;
		loadRacesRows();
	}

	public void loadRacesRows() {

		setNombreColumnasRaces();
		List<RaceDto> list = new CompetitionsAccess().findAllRaces();
		vista.getLblParticipantes().setText("Lista de carreras");
		vista.getPnlViewportCenter().repaint();
		vista.getPnlViewportCenter().revalidate();
		createRacesList(list);

	}


	public void loadParticipantRows(RaceDto carrera) {
		InscriptionModel model = new InscriptionModel();

		setNombreColumnasParticipantes();
		List<InscriptionDto> list = model.getInscriptionList(carrera.id);
		vista.getLblParticipantes().setText("Participantes  carrera: " + carrera.nombre);
		vista.getPnlViewportCenter().repaint();
		vista.getPnlViewportCenter().revalidate();
		createParticipantList(list);

	}

	/**
	 * Resetea la lista y a�ade las carreras disponibles (se muestran todas), ademas 
	 * cambia el efecto del boton volver para que cierre la ventana
	 * @param list
	 */
	private void createRacesList(List<RaceDto> list) {
		int rows = 1;
		Collections.sort(list);
		JPanel panel = vista.getPnlViewportCenter();
		panel.removeAll();


		//		System.out.println("crear la lista de Carreras, listeners: " + vista.getBtnVolver().getActionListeners().length);
		//si hay un action listener hay que eliminarlo para que solo haya una acion asociada al boton
		resetBtn(vista.getBtnVolver());
		vista.getBtnVolver().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				exit();

			}
		});


		for(RaceDto dto : list) {

			panel.setLayout(new GridLayout(rows++, 0, 0, 0));
			if(rows <10) {	//para que las filas no sean demasiado grandes y siga quedando bien
				panel.setLayout(new GridLayout(10, 0, 0, 0));
			}
			panel.add(createRacesRow(dto));
		}


	}


	private JPanel createRacesRow(RaceDto race) {
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(6, 0, 0, 0));
		JLabel lbl = new JLabel();
		panel.setLayout(new GridLayout(0, 8, 0, 0));
		lbl.setText(race.nombre);
		lbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl.setHorizontalAlignment(SwingConstants.CENTER);
		lbl.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.add(lbl);
		vista.getLblDni().setMinimumSize(lbl.getSize());

		lbl = new JLabel();
		lbl.setText("" +race.distancia);
		lbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl.setHorizontalAlignment(SwingConstants.CENTER);
		lbl.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.add(lbl);
		lbl = new JLabel();
		lbl.setText(race.tipo);
		lbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl.setHorizontalAlignment(SwingConstants.CENTER);
		lbl.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.add(lbl);
		lbl = new JLabel();
		lbl.setText(TimeUtil.dateToIsoString(race.fechaCarrera));
		lbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl.setHorizontalAlignment(SwingConstants.CENTER);
		lbl.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.add(lbl);
		panel.add(generateVerBtn(race));
		panel.add(generateClasBtn(race));
		panel.add(generateBtnVerTiempos(race));
		panel.add(generateGenerarDorsalesBoton(race));
		return panel;
	}

	private JButton generateBtnVerTiempos(RaceDto race) {
		JButton btn = new JButton();
		btn.setText("Cargar");
		btn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btn.setHorizontalAlignment(SwingConstants.CENTER);
		btn.setBorder(new LineBorder(new Color(0, 0, 0)));
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verTiempos(race);
			}
		});

		return btn;
	}

	protected void verTiempos(RaceDto race) {
		
		createTimeReception(race);
	}

	protected void createTimeReception(RaceDto race) {
		TimeModel model = new TimeModel(race.id);
		TimeReceptionFrame frame = new TimeReceptionFrame(race.id,this);
		TimeController tc = new TimeController(frame,model,race.id);
		frame.setVisible(true);
		
		
	}
	private Component generateClasBtn(RaceDto race) {
		JButton btn = new JButton();
		btn.setText("Mas");
		if(!ClasificationAccess.isThereAnyClasification(race.id)) {
			btn.setEnabled(false);
			btn.setText("Vacio");
		}
		
		btn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btn.setHorizontalAlignment(SwingConstants.CENTER);
		btn.setBorder(new LineBorder(new Color(0, 0, 0)));
		btn.addActionListener(new ActionListener() {

			String id = race.id;
			public void actionPerformed(ActionEvent e) {
				createClasificationFrame(id);
			}

		});
		return btn;


	}

	protected void createClasificationFrame(String id) {
		try {
			List<ClasificationDto> clasificaciones =  new ClasificationAccess().findAllByRace(id);
			new ClasificationsFrame(clasificaciones).setVisible(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private JButton generateVerBtn(RaceDto carrera) {
		JButton btn = new JButton();
		btn.setText("Ver");
		btn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btn.setHorizontalAlignment(SwingConstants.CENTER);
		btn.setBorder(new LineBorder(new Color(0, 0, 0)));
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadParticipantRows(carrera);
			}
		});

		vista.getBtnVolver().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadParticipantRows(carrera);
			}
		});
		return btn;
	}
	
	private JButton generateGenerarDorsalesBoton(RaceDto carrera) {
		JButton btn = new JButton();
		btn.setText("Generar");
		btn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btn.setHorizontalAlignment(SwingConstants.CENTER);
		btn.setBorder(new LineBorder(new Color(0, 0, 0)));
		if(carrera.fechaLimite.before(new Date()) && carrera.fechaCarrera.after(new Date())) {
			btn.setEnabled(true);
		}else {
			btn.setEnabled(false);
		}
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InscriptionModel im = new InscriptionModel();
				int numeroDeDorsales = im.updateDorsales(carrera.id);
				mostrarDorsalesGenerados(numeroDeDorsales,carrera.nombre);
				
			}
		});
		return btn;
	}
	
	private void mostrarDorsalesGenerados(int numeroDorsales,String nombre) {
		int input = JOptionPane.showConfirmDialog(vista, "Carrera"+nombre +"\nSe han generado: "+numeroDorsales+" dorsales");
	}
	

	/**
	 * Resetea la lista y a�ade los participante a la carrera en la que se pulso el boton ver, ademas 
	 * cambia el efecto del boton volver para que vuelva a la pesta�a de seleccion de carreras
	 * @param list
	 */
	private void createParticipantList(List<InscriptionDto> list) {
		int rows = 1;
		Collections.sort(list);
		JPanel panel = vista.getPnlViewportCenter();
		panel.removeAll();
		//si hay un action listener hay que eliminarlo para que solo haya una acion asociada al boton

		resetBtn(vista.getBtnVolver());
		vista.getBtnVolver().addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				//				System.out.println("crear la lista de carreras, listeners: " + vista.getBtnVolver().getActionListeners().length);
				loadRacesRows();				
			}
		});
		for(InscriptionDto dto : list) {

			panel.setLayout(new GridLayout(rows++, 0, 0, 0));
			if(rows <10) {	//para que las filas no sean demasiado grandes y siga quedando bien
				panel.setLayout(new GridLayout(10, 0, 0, 0));
			}
			panel.add(createRow(dto));
		}




	}


	/**
	 * Metodo que crea una de las filas de la tabla
	 * @return
	 */
	private JPanel createRow(InscriptionDto inscription) {
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(6, 0, 0, 0));
		JLabel lbl = new JLabel();
		panel.setLayout(new GridLayout(0, 5, 0, 0));
		lbl.setText(inscription.dni);
		lbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl.setHorizontalAlignment(SwingConstants.CENTER);
		lbl.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.add(lbl);
		lbl = new JLabel();
		lbl.setText(inscription.name);
		lbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl.setHorizontalAlignment(SwingConstants.CENTER);
		lbl.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.add(lbl);
		lbl = new JLabel();
		lbl.setText(inscription.Category);
		lbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl.setHorizontalAlignment(SwingConstants.CENTER);
		lbl.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.add(lbl);
		lbl = new JLabel();
		lbl.setText(inscription.getInscriptionDate().toString());
		lbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl.setHorizontalAlignment(SwingConstants.CENTER);
		lbl.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.add(lbl);
		lbl = new JLabel();
		lbl.setText(inscription.getInscriptionState().toString());
		lbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl.setHorizontalAlignment(SwingConstants.CENTER);
		lbl.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.add(lbl);
		return panel;
	}
	private void exit() {
		vista.close();

	}

	private void resetBtn(JButton btn) {
		for(ActionListener al  :btn.getActionListeners()) {
			btn.removeActionListener(al);
		}
	}


	/**
	 * Cambio los nombres de la cabecera para que se correspondan con los datos de las columnas
	 */
	private void setNombreColumnasParticipantes() {
		vista.getpnlViewportNorth().remove(vista.getLblClasifications());
		vista.getpnlViewportNorth().remove(vista.getLblVerTiempos());
		vista.getpnlViewportNorth().remove(vista.getLblExtra());
		vista.getpnlViewportNorth().setLayout(new GridLayout(0, 5, 0, 0));
		vista.getLblDni().setText("DNI ");
		vista.getLblNombre().setText("Nombre");
		vista.getLblCategoria().setText("Categoria");
		vista.getLblFecha().setText("Fecha Insc.");
		vista.getLblEstado().setText("Estado");
		

	}

	private void setNombreColumnasRaces() {
		vista.getLblDni().setText("Nombre ");
		vista.getpnlViewportNorth().setLayout(new GridLayout(0, 8, 0, 0));
		vista.getLblNombre().setText("Distancia");
		vista.getLblCategoria().setText("Tipo");
		vista.getLblFecha().setText("Fecha carrera");
		vista.getLblEstado().setText("Inscritos");
		vista.getpnlViewportNorth().add(vista.getLblClasifications());
		vista.getpnlViewportNorth().add(vista.getLblVerTiempos());
		vista.getpnlViewportNorth().add(vista.getLblExtra());
	}
}
