package de.jumajumo.meetingpoint.persistence;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Location
{
	@Column(name = "ADDRESS", length = 1024)
	private String address;

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}
}
