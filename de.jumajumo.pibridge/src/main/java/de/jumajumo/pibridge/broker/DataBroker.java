package de.jumajumo.pibridge.broker;

import java.util.HashMap;
import java.util.Map;

public interface DataBroker
{
	static Map<String, ChannelInfo> channelInfoStore = new HashMap<>();

	public default Map<String, ChannelInfo> getAll()
	{
		return channelInfoStore;
	}

	public default ChannelInfo getChannelInfo(final String channelId)
	{
		if (!channelInfoStore.containsKey(channelId))
		{
			throw new IllegalArgumentException("channel has no value");
		}

		return channelInfoStore.get(channelId);
	}

	public default void putChannelInfo(final String channelId,
			final ChannelInfo channelInfo)
	{
		if (channelInfoStore.containsKey(channelId))
		{
			channelInfoStore.remove(channelId);
		}

		channelInfoStore.put(channelId, channelInfo);
	}
}
