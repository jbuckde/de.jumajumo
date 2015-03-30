package de.jumajumo.meetingpoint.persistence;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import de.jumajumo.core.persistence.AbstractEntityObject;
import de.jumajumo.core.persistence.EntityObject;
import de.jumajumo.core.persistence.UserProfile;

@Entity
@Table(name = "MEETING_POINT")
@NamedQueries({ @NamedQuery(name = "Meeting.findAllRelevant", query = "" //
		+ "select m " //
		+ "from MeetingPoint m  " //
		+ "where " //
		+ "		(" //
		+ "			(m.initiator.uuid = :userProfileUUID) or" //
		+ "			exists (select p from m.participants p where p.userProfile.uuid = :userProfileUUID)" //
		+ "		) and" //
		+ "		m.date >= :validDate") })
public class MeetingPoint extends AbstractEntityObject implements EntityObject
{
	@Column(name = "NAME", length = 255)
	private String name;

	@Column(name = "MEETING_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	@Embedded
	private Location location;

	@OneToOne(cascade = CascadeType.REFRESH, orphanRemoval = false)
	@JoinColumn(name = "INITIATOR_ID")
	private UserProfile initiator;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = false)
	@JoinTable(name = "MEETINGPOINT_PARTICIPANTS", joinColumns = @JoinColumn(name = "MEETING_ID"), inverseJoinColumns = @JoinColumn(name = "PARTICIPANT_ID"))
	private List<MeetingPointParticipant> participants;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = false)
	@JoinTable(name = "MEETINGPOINT_FEEDS", joinColumns = @JoinColumn(name = "MEETING_ID"), inverseJoinColumns = @JoinColumn(name = "FEED_ID"))
	@OrderBy(value = "postedAt DESC")
	private List<MeetingPointFeed> feeds;

	/**
	 * Instantiates a new meeting.
	 */
	public MeetingPoint()
	{
		super();

		this.location = new Location();
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((feeds == null) ? 0 : feeds.hashCode());
		result = prime * result
				+ ((initiator == null) ? 0 : initiator.hashCode());
		result = prime * result
				+ ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((participants == null) ? 0 : participants.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		MeetingPoint other = (MeetingPoint) obj;
		if (date == null)
		{
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (feeds == null)
		{
			if (other.feeds != null)
				return false;
		} else if (!feeds.equals(other.feeds))
			return false;
		if (initiator == null)
		{
			if (other.initiator != null)
				return false;
		} else if (!initiator.equals(other.initiator))
			return false;
		if (location == null)
		{
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (name == null)
		{
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (participants == null)
		{
			if (other.participants != null)
				return false;
		} else if (!participants.equals(other.participants))
			return false;
		return true;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Date getDate()
	{
		return date;
	}

	public void setDate(Date date)
	{
		this.date = date;
	}

	public Location getLocation()
	{
		return location;
	}

	public void setLocation(Location location)
	{
		this.location = location;
	}

	public UserProfile getInitiator()
	{
		return initiator;
	}

	public void setInitiator(UserProfile initiator)
	{
		this.initiator = initiator;
	}

	public List<MeetingPointParticipant> getParticipants()
	{
		if (null == participants)
		{
			this.participants = new ArrayList<MeetingPointParticipant>();
		}

		return participants;
	}

	public void setParticipants(List<MeetingPointParticipant> participants)
	{
		this.participants = participants;
	}

	public List<MeetingPointFeed> getFeeds()
	{
		return feeds;
	}

	public void setFeeds(List<MeetingPointFeed> feeds)
	{
		this.feeds = feeds;
	}
}
