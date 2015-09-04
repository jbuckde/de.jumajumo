package de.jumajumo.homecontrol.configuration.server.trigger;

import javax.xml.bind.annotation.XmlElement;

import de.jumajumo.homecontrol.service.scheduling.ESchedulingPointInTime;

public class TriggerByScheduling extends Trigger
{
	@XmlElement
	private ESchedulingPointInTime runAt;

	public ESchedulingPointInTime getRunAt()
	{
		return runAt;
	}

	public void setRunAt(ESchedulingPointInTime runAt)
	{
		this.runAt = runAt;
	}
}
