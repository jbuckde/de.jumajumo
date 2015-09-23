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
		REQUEST_TYPE_HTTP, REQUEST_TYPE_WEBSOCKET;

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
	private String query;

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
		result = prime * result + ((path == null) ? 0 : path.hashCode());
		result = prime * result + ((query == null) ? 0 : query.hashCode());
		result = prime * result
				+ ((requestType == null) ? 0 : requestType.hashCode());
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
		if (path == null)
		{
			if (other.path != null)
				return false;
		} else if (!path.equals(other.path))
			return false;
		if (query == null)
		{
			if (other.query != null)
				return false;
		} else if (!query.equals(other.query))
			return false;
		if (requestType != other.requestType)
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

	public String getQuery()
	{
		return query;
	}

	public void setRequestType(EnumRequestType requestType)
	{
		this.requestType = requestType;
	}

	public void setPath(String path)
	{
		this.path = path;
	}

	public void setQuery(String query)
	{
		this.query = query;
	}

}
