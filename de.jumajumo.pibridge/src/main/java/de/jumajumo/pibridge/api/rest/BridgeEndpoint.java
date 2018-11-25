package de.jumajumo.pibridge.api.rest;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.jumajumo.pibridge.broker.ChannelInfo;
import de.jumajumo.pibridge.broker.DataBrokerImpl;

@RestController
@RequestMapping("/bridge")
public class BridgeEndpoint
{

	@GetMapping(path = "/all")
	public Map<String, ChannelInfo> getAllChannelInfos()
	{
		return DataBrokerImpl.getInstance().getAll();
	}

	@GetMapping(path = "/thing/{thingid}/channel/{channelid}")
	public ChannelInfo getChannelInfo(
			@PathVariable("thingid") final String thingId,
			@PathVariable("channelid") final String channelId)
	{
		String channelIdentifier = thingId + ":" + channelId;

		return DataBrokerImpl.getInstance().getChannelInfo(channelIdentifier);
	}

	@PostMapping(path = "/thing/{thingid}/channel/{channelid}")
	public void postChannelInfo(@PathVariable("thingid") final String thingId,
			@PathVariable("channelid") final String channelId,
			@RequestBody final ChannelInfo channelInfo)
	{
		String channelIdentifier = thingId + ":" + channelId;

		DataBrokerImpl.getInstance().putChannelInfo(channelIdentifier,
				channelInfo);
	}
}
