package de.jumajumo.homecontrol.configuration.server.trigger;

import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import de.jumajumo.homecontrol.configuration.AbstractConfigurationObject;

/**
 * The Class Trigger carries information on how to execute a server trigger.
 * 
 * Triggers are used in order to start action chains on the server.
 * 
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
@XmlTransient
@XmlSeeAlso({ TriggerByClientSensor.class, TriggerByRestCall.class,
		TriggerByScheduling.class, TriggerByFirebaseListener.class })
public abstract class Trigger extends AbstractConfigurationObject
{
	private int blockIntervall;

	public int getBlockIntervall()
	{
		return blockIntervall;
	}

	public void setBlockIntervall(int blockIntervall)
	{
		this.blockIntervall = blockIntervall;
	}
}
