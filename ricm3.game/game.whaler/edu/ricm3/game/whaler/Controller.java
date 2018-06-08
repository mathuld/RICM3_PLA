/*
 * Educational software for a basic game development
 * Copyright (C) 2018  Pr. Olivier Gruber
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.ricm3.game.whaler;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.ImageIcon;
import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;

import edu.ricm3.game.GameController;
import edu.ricm3.game.whaler.Model;
import edu.ricm3.game.whaler.Model.Screen;

/**
 * This class is to illustrate the most simple game controller. It does not
 * much, but it shows how to obtain the key strokes, mouse buttons, and mouse
 * moves.
 * 
 * With ' ', you see what you should never do, SLEEP. With '+' and '-', you can
 * add or remove some simulated overheads.
 * 
 * @author Pr. Olivier Gruber
 */

public class Controller extends GameController implements ActionListener {
	Panel cont;
	Panel p;
	Panel main;
	Model m_model;
	JButton play;
	Music m_player;
	JButton automata;
	JButton annuler;
	JButton retour;
	JButton preference;
	JLabel infoLabel;
	JComboBox<?> b[];
	JButton valider;

	public Controller(Model m) {
		m_model = m;
	}

	/**
	 * Simulation step. Warning: the model has already executed its step.
	 * 
	 * @param now
	 *            is the current time in milliseconds.
	 */
	@Override
	public void step(long now) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// if (Options.ECHO_KEYBOARD)
		// System.out.println("KeyTyped: " + e);
		/*
		 * if (e.getKeyChar() == 'a letter') { try { something } catch
		 * (InterruptedException ex) { } }
		 */

		if (e.getKeyChar() == 'u' || e.getKeyChar() == 'U') {
			m_model.swap();
		}

		if (e.getKeyChar() == 'i' || e.getKeyChar() == 'I') {
			m_model.m_whales[0].pop();
		}

		if (e.getKeyChar() == 'o' || e.getKeyChar() == 'O') {
			m_model.m_whales[0].m_capture = 20;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Object s = e.getSource();
		if (s == play) {
			ImageIcon img = new ImageIcon("game.whaler/sprites/play_clicked.png");
			play.setIcon(img);
		}
		if (s == retour) {
			ImageIcon img = new ImageIcon("game.whaler/sprites/retour_click.png");
			retour.setIcon(img);
		}
		if (s == automata) {
			ImageIcon img = new ImageIcon("game.whaler/sprites/option_click.png");
			automata.setIcon(img);
		}
		if (s == annuler) {
			ImageIcon img = new ImageIcon("game.whaler/sprites/annuler_click.png");
			annuler.setIcon(img);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		Object s = e.getSource();
		if (s == play) {
			ImageIcon img = new ImageIcon("game.whaler/sprites/play_unclicked.png");
			play.setIcon(img);
		}
		if (s == retour) {
			ImageIcon img = new ImageIcon("game.whaler/sprites/retour.png");
			retour.setIcon(img);
		}
		if (s == annuler) {
			ImageIcon img = new ImageIcon("game.whaler/sprites/annuler.png");
			annuler.setIcon(img);
		}
		if (s == automata) {
			ImageIcon img = new ImageIcon("game.whaler/sprites/option.png");
			automata.setIcon(img);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		Object s = e.getSource();
		if (s == play) {
			ImageIcon img = new ImageIcon("game.whaler/sprites/play.png");
			play.setIcon(img);
		}
		if (s == retour) {
			ImageIcon img = new ImageIcon("game.whaler/sprites/retour_hover.png");
			retour.setIcon(img);
		}
		if (s == annuler) {
			ImageIcon img = new ImageIcon("game.whaler/sprites/annuler_hover.png");
			annuler.setIcon(img);
		}
		if (s == automata) {
			ImageIcon img = new ImageIcon("game.whaler/sprites/option_hover.png");
			automata.setIcon(img);
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		Object s = e.getSource();
		if (s == play) {
			ImageIcon img = new ImageIcon("game.whaler/sprites/play_unclicked.png");
			play.setIcon(img);
		}
		if (s == retour) {
			ImageIcon img = new ImageIcon("game.whaler/sprites/retour.png");
			retour.setIcon(img);
		}
		if (s == annuler) {
			ImageIcon img = new ImageIcon("game.whaler/sprites/annuler.png");
			annuler.setIcon(img);
		}
		if (s == automata) {
			ImageIcon img = new ImageIcon("game.whaler/sprites/option.png");
			automata.setIcon(img);
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}

	public void notifyVisible() {
		cont = new Panel();
		p = new Panel();
		main = new Panel();
		main.setLayout(new GridLayout(9, 1, 0, 15));
		main.setBackground(Color.WHITE);

		main.setVisible(false);
		cont.setBackground(Color.WHITE);

		ImageIcon image = new ImageIcon("game.whaler/sprites/play_unclicked.png");
		play = new JButton(image);
		play.addActionListener(this);
		play.addMouseListener(this);
		play.setContentAreaFilled(false);
		play.setBorderPainted(false);
		play.setFocusPainted(false);
		cont.add(play);

		ImageIcon image1 = new ImageIcon("game.whaler/sprites/option.png");
		automata = new JButton(image1);
		automata.addActionListener(this);
		automata.addMouseListener(this);
		automata.setContentAreaFilled(false);
		automata.setBorderPainted(false);
		automata.setFocusPainted(false);
		cont.add(automata);

		ImageIcon image2 = new ImageIcon("game.whaler/sprites/retour.png");
		retour = new JButton(image2);
		retour.addActionListener(this);
		retour.addMouseListener(this);
		retour.setContentAreaFilled(false);
		retour.setBorderPainted(false);
		retour.setFocusPainted(false);
		retour.setVisible(false);
		cont.add(retour);

		ImageIcon image3 = new ImageIcon("game.whaler/sprites/annuler.png");
		annuler = new JButton(image3);
		annuler.addActionListener(this);
		annuler.addMouseListener(this);
		annuler.setContentAreaFilled(false);
		annuler.setBorderPainted(false);
		annuler.setFocusPainted(false);
		annuler.setVisible(false);
		cont.add(annuler);

		preference = new JButton("Préférences");
		preference.addActionListener(this);
		preference.addMouseListener(this);
		cont.add(preference);

		valider = new JButton("Valider");
		valider.addActionListener(this);
		valider.addMouseListener(this);
		valider.setVisible(false);
		cont.add(valider);
		infoLabel = new JLabel("Sélectionnez un item");
		infoLabel.setVisible(false);

		String[] items = { "Baleine", "Baleinier", "Destroyer", "Joueur", "Pétrole", "Projectile" };
		b = new JComboBox[6];
		int i = 0;
		while (i < 6) {
			b[i] = new JComboBox<Object>(items);
			main.add(b[i]);
			b[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Object o = ((JComboBox<?>) e.getSource()).getSelectedItem();
					String string = (String) o;
					infoLabel.setText(string);
				}
			});
			i++;
		}

		cont.add(infoLabel);
		m_game.addSouth(cont);
		m_game.addEast(main);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object s = e.getSource();
		if (s == retour) {
			m_model.setScreen(Screen.HOME);
			automata.setVisible(true);
			play.setVisible(true);
			preference.setVisible(true);
			retour.setVisible(false);
			main.setVisible(false);
			annuler.setVisible(false);
			infoLabel.setVisible(false);
			valider.setVisible(false);
		}

		if (s == annuler) {
			infoLabel.setText("Sélectionnez un item");
			for (int i = 0; i < 6; i++)
				b[i].setSelectedIndex(0);
		}

		if (s == play) {
			m_model.setScreen(Screen.GAME);
			cont.setVisible(false);
		}

		if (s == valider) {
			File file = new File("game.whaler/sprites/choix_automates.txt");
			BufferedWriter out = null;
			try {
				out = new BufferedWriter(new FileWriter(file));
				for (int i = 0; i < 6; i++)
					out.write(b[i].getSelectedItem().toString() + ";");
			} catch (IOException ae) {
				ae.printStackTrace();
			} finally {
				try {
					out.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}

		if (s == automata) {
			m_model.setScreen(Screen.AUTOMATA);
			automata.setVisible(false);
			play.setVisible(false);
			preference.setVisible(false);
			retour.setVisible(true);
			main.setVisible(true);
			annuler.setVisible(true);
			infoLabel.setVisible(true);
			valider.setVisible(true);
		}

		if (s == preference) {
			m_model.setScreen(Screen.PREFERENCES);
			automata.setVisible(false);
			play.setVisible(false);
			preference.setVisible(false);
			retour.setVisible(true);
			annuler.setVisible(true);

			if (s == annuler) {
				infoLabel.setText("Sélectionnez un item");
				/*
				 * Ici on met le champ m_automate de toutes les entités à NULL ou on introduit
				 * un last automate On garde en mémoire l'ancien automate assigné quand on en
				 * assigne un nouveau Et en cliquant sur annuler, on remet l'ancien automate
				 */
			}

		}
	}
}
