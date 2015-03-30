package de.jumajumo.homecontrol.type;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlEnum;

/**
 * The Class RequestInfo carries information for the server on how to request a
 * device function.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class RequestInfo
{
	// Define the possible request types
	@XmlEnum
	public enum EnumRequestType
	{
		REQUEST_TYPE_HHTP;

		public String value()
		{
			return name();
		}

		public static EnumRequestType fromValue(String v)
		{
			return valueOf(v);
		}
	}

	private EnumRequestType requestType;
	private String path;

	public RequestInfo()
	{
		super();
	}

	public RequestInfo(final EnumRequestType requestType, final String path)
	{
		super();

		this.setRequestType(requestType);
		this.setPath(path);
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getPath() == null) ? 0 : getPath().hashCode());
		result = prime
				* result
				+ ((getRequestType() == null) ? 0 : getRequestType().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RequestInfo other = (RequestInfo) obj;
		if (getPath() == null)
		{
			if (other.getPath() != null)
				return false;
		} else if (!getPath().equals(other.getPath()))
			return false;
		if (getRequestType() != other.getRequestType())
			return false;
		return true;
	}

	public EnumRequestType getRequestType()
	{
		return requestType;
	}

	public String getPath()
	{
		return path;
	}

	public void setRequestType(EnumRequestType requestType)
	{
		this.requestType = requestType;
	}

	public void setPath(String path)
	{
		this.path = path;
	}

}
