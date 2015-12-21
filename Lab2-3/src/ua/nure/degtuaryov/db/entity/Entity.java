package ua.nure.degtuaryov.db.entity;

import java.io.Serializable;

/**
 * Entity class, parent for all entities from data base
 * 
 * @author Degtuaryow
 *
 */
public class Entity implements Serializable {

	private static final long serialVersionUID = 3252624542575432L;

	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
