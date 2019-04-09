package com.meetingroom.model;

import java.io.Serializable;
import java.sql.Timestamp;

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
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	private long id;
	
	@Column(name = "meeting_id")
	private String meetingId;
	
	@Column(name = "host_id", nullable = false)
	private long hostId;
	
	@Column(name = "host_email", nullable = false)
	private String hostEmail;
	
//	Email address of the attendee as the attendee may not be part of the system
	@Column(name = "attendee")
	private String attendee;
	
	@Column(name = "meeting_start_date_time")
	private Timestamp meetingStartDate;
	
	@Column(name = "meeting_end_date_time")
	private Timestamp meetingEndDate;
	
	@Column(name = "meeting_status")
	private String meetingStatus;
	
	@Column(name = "meeting_location")
	private String meetingLocation;
	
	@Column(name = "meeting_room_id")
	private long meetingRoomId;

	/**
	 * @return the id
	 */
	public String getMeetingId() {
		return meetingId;
	}

	/**
	 * @param id the id to set
	 */
	public void setMeetingId(String meetingId) {
		this.meetingId = meetingId;
	}

	/**
	 * @return the host_id
	 */
	public long getHostId() {
		return hostId;
	}

	/**
	 * @param host_id the host_id to set
	 */
	public void setHostId(long host_id) {
		this.hostId = host_id;
	}
	
	/**
	 * @return the hostName
	 */
	public String getHostEmail() {
		return hostEmail;
	}

	/**
	 * @param hostName the hostName to set
	 */
	public void setHostEmail(String hostEmail) {
		this.hostEmail = hostEmail;
	}

	/**
	 * @return the attendee
	 */
	public String getAttendee() {
		return attendee;
	}

	public Timestamp getMeetingStartDate() {
		return meetingStartDate;
	}

	public void setMeetingStartDate(Timestamp meetingStartDate) {
		this.meetingStartDate = meetingStartDate;
	}

	public Timestamp getMeetingEndDate() {
		return meetingEndDate;
	}

	public void setMeetingEndDate(Timestamp meetingEndDate) {
		this.meetingEndDate = meetingEndDate;
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
	 * @param id
	 * @param host_id
	 * @param attendee
	 * @param meetingDate
	 * @param meetingStatus
	 * @param meetingLocation
	 * @param meetingRoomId
	 */
	public ScheduleMeetingRoom(String meetingId, long host_id, String hostEmail, String attendee, Timestamp meetingStartDate, Timestamp meetingEndDate, String meetingStatus,
			String meetingLocation, long meetingRoomId) {
		super();
		this.meetingId = meetingId;
		this.hostId = host_id;
		this.hostEmail = hostEmail;
		this.attendee = attendee;
		this.meetingStartDate = meetingStartDate;
		this.meetingEndDate = meetingEndDate;
		this.meetingStatus = meetingStatus;
		this.meetingLocation = meetingLocation;
		this.meetingRoomId = meetingRoomId;
	}
	

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
		return "ScheduleMeetingRoom [id=" + id + ", host_id=" + hostId + ", attendee=" + attendee + ", meetingStatus="
				+ meetingStatus + ", meetingLocation=" + meetingLocation + ", meetingRoomId=" + meetingRoomId + "]";
	}
	
}
