package com.meetingroom.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "meeting_room")
public class MeetingRoom implements Serializable {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5477595125206172242L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", unique= true)
	private long id;
	
	@Column(name = "name", unique=true, nullable = false)
	private String name; 
	
	@Column(name = "location")
	private String location;
	
	@Column(name = "capacity")
	private int capacity;

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the capacity
	 */
	public int getCapacity() {
		return capacity;
	}

	/**
	 * @param capacity the capacity to set
	 */
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public MeetingRoom(String name, String location, int capacity) {
		super();
		this.name = name;
		this.location = location;
		this.capacity = capacity;
	}

	/**
	 * 
	 */
	public MeetingRoom() {
		super();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MeetingRoom [id=" + id + ", name=" + name + ", location=" + location + ", capacity=" + capacity + "]";
	}
	
	
	
	
}
