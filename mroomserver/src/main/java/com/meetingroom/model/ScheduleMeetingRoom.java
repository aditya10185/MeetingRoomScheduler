package com.meetingroom.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "schedule_meeting_room")
public class ScheduleMeetingRoom implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1806125220009623467L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", unique= true)
	private long id;
	
	@Column(name = "host_id", nullable = false)
	private long host_id;
	
//	Email address of the attendee as the attendee may not be part of the system
	@Column(name = "attendee")
	private String attendee;
	
	@Column(name = "meeting_date_time")
	private Date meetingDate;
	
	@Column(name = "meeting_status")
	private String meetingStatus;
	
	@Column(name = "meeting_location")
	private String meetingLocation;
	
	@Column(name = "meeting_room_id")
	private long meetingRoomId;

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
	 * @return the host_id
	 */
	public long getHost_id() {
		return host_id;
	}

	/**
	 * @param host_id the host_id to set
	 */
	public void setHost_id(long host_id) {
		this.host_id = host_id;
	}

	/**
	 * @return the attendee
	 */
	public String getAttendee() {
		return attendee;
	}

	/**
	 * @param attendee the attendee to set
	 */
	public void setAttendee(String attendee) {
		this.attendee = attendee;
	}

	/**
	 * @return the meetingStatus
	 */
	public String getMeetingStatus() {
		return meetingStatus;
	}

	/**
	 * @param meetingStatus the meetingStatus to set
	 */
	public void setMeetingStatus(String meetingStatus) {
		this.meetingStatus = meetingStatus;
	}

	/**
	 * @return the meetingLocation
	 */
	public String getMeetingLocation() {
		return meetingLocation;
	}

	/**
	 * @param meetingLocation the meetingLocation to set
	 */
	public void setMeetingLocation(String meetingLocation) {
		this.meetingLocation = meetingLocation;
	}

	/**
	 * @return the meetingRoomId
	 */
	public long getMeetingRoomId() {
		return meetingRoomId;
	}

	/**
	 * @param meetingRoomId the meetingRoomId to set
	 */
	public void setMeetingRoomId(long meetingRoomId) {
		this.meetingRoomId = meetingRoomId;
	}
	
	

	/**
	 * @return the meetingDate
	 */
	public Date getMeetingDate() {
		return meetingDate;
	}

	/**
	 * @param meetingDate the meetingDate to set
	 */
	public void setMeetingDate(Date meetingDate) {
		this.meetingDate = meetingDate;
	}

	/**
	 * @param id
	 * @param host_id
	 * @param attendee
	 * @param meetingDate
	 * @param meetingStatus
	 * @param meetingLocation
	 * @param meetingRoomId
	 */
	public ScheduleMeetingRoom(long host_id, String attendee, Date meetingDate, String meetingStatus,
			String meetingLocation, long meetingRoomId) {
		super();
		this.host_id = host_id;
		this.attendee = attendee;
		this.meetingDate = meetingDate;
		this.meetingStatus = meetingStatus;
		this.meetingLocation = meetingLocation;
		this.meetingRoomId = meetingRoomId;
	}

	/**
	 * 
	 */
	public ScheduleMeetingRoom() {
		super();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ScheduleMeetingRoom [id=" + id + ", host_id=" + host_id + ", attendee=" + attendee + ", meetingStatus="
				+ meetingStatus + ", meetingLocation=" + meetingLocation + ", meetingRoomId=" + meetingRoomId + "]";
	}
	
}