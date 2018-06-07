package edu.ricm3.game.whaler.Entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import edu.ricm3.game.whaler.Location;
import edu.ricm3.game.whaler.Model;
import edu.ricm3.game.whaler.Game_exception.Map_exception;

public abstract class Entity {

	Location m_pos;
	boolean m_solid;
	BufferedImage m_sprite;
	Model m_model;

	/**
	 * @param pos
	 * @param solid
	 * @param sprite
	 * @param model
	 */
	protected Entity(Location pos, boolean solid, BufferedImage sprite, Model model) throws Map_exception {
		this.m_pos = pos;
		this.m_solid = solid;
		this.m_sprite = sprite;
		this.m_model = model;

		// Adding the Entity to the Map (for rendering and fast access to locations)
		m_model.map().tile(m_pos.x, m_pos.y).addForeground(this);
	}

	public int getx() {
		return m_pos.x;
	}

	public int gety() {
		return m_pos.y;
	}

	/*
	 * Indicate whether the Entity is solid (Boats, Stone, ...) or not (Oil,
	 * Projectile, ...)
	 */
	public boolean isSolid() {
		return m_solid;
	}

	/**
	 * @param now
	 */
	public abstract void step(long now);

	/**
	 * @param g
	 * @param map_ref
	 */
	public abstract void paint(Graphics g, Location map_ref);
	
	/**
	 * @param g
	 * @param map_ref
	 */
	public abstract void paint_under(Graphics g, Location map_ref);

}
