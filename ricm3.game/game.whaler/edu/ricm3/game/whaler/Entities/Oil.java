package edu.ricm3.game.whaler.Entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import edu.ricm3.game.whaler.Direction;
import edu.ricm3.game.whaler.Location;
import edu.ricm3.game.whaler.Model;
import edu.ricm3.game.whaler.Options;
import edu.ricm3.game.whaler.Game_exception.Game_exception;

public final class Oil extends Mobile_Entity {

	boolean is_burning;
	long m_lastSpread;

	int m_idx;
	BufferedImage[] m_spriteFire;

	/**
	 * @param pos
	 * @param sprite
	 * @param underSprite
	 * @param model
	 * @throws Game_exception
	 */

	public Oil(Location pos, BufferedImage sprite, BufferedImage underSprite, Model model) throws Game_exception {
		super(pos, false, sprite, underSprite, model, Direction.SOUTH, Options.OIL_LIFE);
		// We use a default direction,because it has no importance

		this.is_burning = false;
		m_lastSpread = 0;

		m_spriteFire = new BufferedImage[32];

		for (int i = 0; i < 32; i++) {
			m_spriteFire[i] = m_model.get_fire_sprite().getSubimage(0, 32 * i, 32, 32);
		}
	}

	@Override
	public void destroy() throws Game_exception {
		m_model.map().tile(m_pos).remove(this);
		m_model.m_garbage.add(this);
	}

	@Override
	public void step(long now) throws Game_exception {
		if (m_life <= 0) {
			this.destroy();
		}

		if (is_burning) {

			long elapsed = now - m_lastStep;

			if (elapsed > Options.BURNING_OIL_SPD_BURNING) {
				// change of the fire spite and reduces the life of the oil

				m_idx = (m_idx + 1) % m_spriteFire.length;

				m_life--;

				m_lastStep = now;
			}

			elapsed = now - m_lastSpread;

			if (elapsed > Options.BURNING_OIL_SPD_SPREAD) { // spreading of the fire

				Location adja = new Location(this.m_pos);
				adja.up();
				Entity result = m_model.map().tile(adja).contain(EntityType.OIL); // to the top
				if (result != null) {
					Oil will_burn = (Oil) result;
					will_burn.is_burning = true;
				}

				adja = new Location(this.m_pos);
				adja.down();
				result = m_model.map().tile(adja).contain(EntityType.OIL); // to the bottom
				if (result != null) {
					Oil will_burn = (Oil) result;
					will_burn.is_burning = true;
				}

				adja = new Location(this.m_pos);
				adja.left();
				result = m_model.map().tile(adja).contain(EntityType.OIL); // to the left
				if (result != null) {
					Oil will_burn = (Oil) result;
					will_burn.is_burning = true;
				}

				adja = new Location(this.m_pos);
				adja.right();
				result = m_model.map().tile(adja).contain(EntityType.OIL); // to the right
				if (result != null) {
					Oil will_burn = (Oil) result;
					will_burn.is_burning = true;
				}

				m_lastSpread = now; // we reset the timer

			}

		} else { // if the oil doestn't burn
			m_lastSpread = now; // we keep the timers readied
			m_lastStep = now;
		}
	}

	@Override
	public void paint(Graphics g, Location map_ref) {

		g.drawImage(m_sprite, (m_pos.x - map_ref.x) * 32, (m_pos.y - map_ref.y) * 32, 32, 32, null);
		if (is_burning) {
			g.drawImage(m_spriteFire[m_idx], (m_pos.x - map_ref.x) * 32, (m_pos.y - map_ref.y) * 32, 32, 32, null);
		}
	}

	@Override
	public void paint_under(Graphics g, Location map_ref) {
		// nothing
	}

	@Override
	public void pop() {
		// TODO
	}

	@Override
	public void wizz() {
		this.is_burning = true;

	}

	@Override
	public void hit() {
		// TODO comment le feu va blesser les joueurs ?
	}

	@Override
	public void pick() {
		this.pop();
	}

}
