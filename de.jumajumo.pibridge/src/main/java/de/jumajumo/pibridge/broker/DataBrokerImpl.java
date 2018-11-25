package de.jumajumo.pibridge.broker;

public class DataBrokerImpl implements DataBroker
{
	private static DataBrokerImpl myInstance;

	private DataBrokerImpl()
	{

	}

	public static DataBroker getInstance()
	{
		if (null == myInstance)
		{
			myInstance = new DataBrokerImpl();
		}

		return myInstance;
	}
}
