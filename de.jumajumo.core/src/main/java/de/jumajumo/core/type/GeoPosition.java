package de.jumajumo.core.type;

public class GeoPosition
{

	private double latitude;
	private double longitude;

	// conversion from string representation
	public static GeoPosition fromString(final String value)
	{
		final String[] split = value.split(",");

		if (split.length != 2)
		{
			throw new IllegalArgumentException(value
					+ " is not a valid string representation for GeoPosition");
		}

		final GeoPosition position = new GeoPosition();

		position.setLatitude(Double.valueOf(split[0]));
		position.setLongitude(Double.valueOf(split[1]));

		return position;
	}

	@Override
	public String toString()
	{
		return String.valueOf(this.getLatitude()) + ","
				+ String.valueOf(this.getLongitude());
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(latitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(longitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		GeoPosition other = (GeoPosition) obj;
		if (Double.doubleToLongBits(latitude) != Double
				.doubleToLongBits(other.latitude))
			return false;
		if (Double.doubleToLongBits(longitude) != Double
				.doubleToLongBits(other.longitude))
			return false;
		return true;
	}

	public double getLatitude()
	{
		return latitude;
	}

	public void setLatitude(double latitude)
	{
		this.latitude = latitude;
	}

	public double getLongitude()
	{
		return longitude;
	}

	public void setLongitude(double longitude)
	{
		this.longitude = longitude;
	}
}
