package de.jumajumo.homecontrol.configuration.server.trigger;

import java.util.UUID;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import de.jumajumo.homecontrol.type.RequestInfo;

@XmlRootElement(name = "trigger")
public class TriggerXmlAdapter extends XmlAdapter<AdaptedTrigger, Trigger>
{
	@Override
	public Trigger unmarshal(AdaptedTrigger v) throws Exception
	{
		final RequestInfo requestInfo = new RequestInfo();
		final Trigger trigger = new TriggerByRestCall(requestInfo);

		trigger.setUuid(UUID.fromString(v.getMap().get("UUID")));

		return trigger;
	}

	@Override
	public AdaptedTrigger marshal(Trigger v) throws Exception
	{
		final AdaptedTrigger aTrigger = new AdaptedTrigger();
		aTrigger.getMap().put("UUID", v.getUuid().toString());

		return aTrigger;
	}
}
