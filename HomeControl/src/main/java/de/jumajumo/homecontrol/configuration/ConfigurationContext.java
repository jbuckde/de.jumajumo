package de.jumajumo.homecontrol.configuration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import de.jumajumo.homecontrol.configuration.client.Device;
import de.jumajumo.homecontrol.configuration.server.Action;
import de.jumajumo.homecontrol.configuration.server.ActionChain;
import de.jumajumo.homecontrol.configuration.server.Condition;
import de.jumajumo.homecontrol.configuration.server.trigger.Trigger;

@XmlRootElement(name = "configuration")
@XmlAccessorType(XmlAccessType.FIELD)
public class ConfigurationContext implements Serializable
{
	private static final long serialVersionUID = -2904843840064791996L;

	@XmlElementWrapper(name = "actionChains")
	@XmlElement(name = "actionChain")
	private List<ActionChain> actionChains;

	@XmlElementWrapper(name = "devices")
	@XmlElement(name = "device")
	private List<Device> devices;

	@XmlElementWrapper(name = "triggers")
	@XmlElement(name = "trigger")
	private List<Trigger> triggers;

	@XmlElementWrapper(name = "conditions")
	@XmlElement(name = "condition")
	private List<Condition> conditions;

	@XmlElementWrapper(name = "actions")
	@XmlElement(name = "action")
	private List<Action> actions;

	public List<ActionChain> getActionChains()
	{
		if (null == actionChains)
		{
			actionChains = new ArrayList<ActionChain>();
		}

		return actionChains;
	}

	public void setActionChains(List<ActionChain> actionChains)
	{
		this.actionChains = actionChains;
	}

	public List<Device> getDevices()
	{
		if (null == devices)
		{
			devices = new ArrayList<Device>();
		}

		return devices;
	}

	public void setDevices(List<Device> devices)
	{
		this.devices = devices;
	}

	public List<Trigger> getTriggers()
	{
		if (null == triggers)
		{
			triggers = new ArrayList<Trigger>();
		}

		return triggers;
	}

	public void setTriggers(List<Trigger> triggers)
	{
		this.triggers = triggers;
	}

	public List<Condition> getConditions()
	{
		if (null == conditions)
		{
			conditions = new ArrayList<Condition>();
		}

		return conditions;
	}

	public void setConditions(List<Condition> conditions)
	{
		this.conditions = conditions;
	}

	public List<Action> getActions()
	{
		if (null == actions)
		{
			actions = new ArrayList<Action>();
		}

		return actions;
	}

	public void setActions(List<Action> actions)
	{
		this.actions = actions;
	}

	// public void loadConfiguration()
	// {
	// // create device
	// final Device garageDevice = new Device();
	// garageDevice.setUuid(UUID.randomUUID());
	// garageDevice.setName("GarageDevice");
	// garageDevice.setProtocol("http");
	// garageDevice.setHostName("192.168.1.81");
	//
	// // open door actor
	// final Actor garageDeviceOpenDoorActor = new Actor();
	// garageDeviceOpenDoorActor.setUuid(UUID.randomUUID());
	// garageDeviceOpenDoorActor.setName("GaragDeviceOpenDoorActor");
	// garageDeviceOpenDoorActor.setRequestInfo(new RequestInfo(
	// RequestInfo.EnumRequestType.REQUEST_TYPE_HHTP,
	// "/cgi-bin/tasterRelais1.sh"));
	//
	// // add the actor to the device definition
	// garageDevice.getActors().add(garageDeviceOpenDoorActor);
	//
	// // create a action chain for opening the garage door
	// final ActionChain openGarageDoorActionChain = new ActionChain();
	// openGarageDoorActionChain.setUuid(UUID.randomUUID());
	// openGarageDoorActionChain.setName("OpenGarageDoor");
	//
	// final ActionRef doorOpenActionRef = new ActionRef();
	// doorOpenActionRef.setUuid(UUID.randomUUID());
	// doorOpenActionRef.setName("OpenGarageDoorAction");
	//
	// openGarageDoorActionChain.getActionRefs().add(doorOpenActionRef);
	//
	// // define the action to open the door
	// final Action doorOpenAction = new Action();
	// doorOpenAction.setUuid(UUID.randomUUID());
	// doorOpenAction.setName("GarageDoorOpenAction");
	// doorOpenAction.getActorUuids().add(garageDeviceOpenDoorActor.getUuid());
	//
	// doorOpenActionRef.setActionUuid(doorOpenAction.getUuid());
	//
	// // define the condition
	// final Condition condition = new Condition();
	// condition.setUuid(UUID.randomUUID());
	// condition.setBeanName("conditionIsFalse");
	// condition.setName("false condition");
	//
	// final ConditionRef conditionRef = new ConditionRef();
	// conditionRef.setUuid(UUID.randomUUID());
	// conditionRef.setConditionUuid(condition.getUuid());
	// conditionRef.setName("false condition");
	//
	// doorOpenActionRef.getConditionRefs().add(conditionRef);
	//
	// // define a trigger in order to start the action chain
	// final Trigger requestGarageDoorOpenTrigger = new TriggerByRestCall(
	// new RequestInfo(EnumRequestType.REQUEST_TYPE_HHTP,
	// "opengaragedoor"));
	// requestGarageDoorOpenTrigger.setUuid(UUID.randomUUID());
	// requestGarageDoorOpenTrigger
	// .setName("trigger for a request to open the garage door");
	//
	// // add the trigger to the action chain
	// final TriggerRef requestGarageDoorOpenTriggerRef = new TriggerRef();
	// requestGarageDoorOpenTriggerRef.setUuid(UUID.randomUUID());
	// requestGarageDoorOpenTriggerRef.setName("trigger for user request");
	// requestGarageDoorOpenTriggerRef
	// .setTriggerUuid(requestGarageDoorOpenTrigger.getUuid());
	//
	// openGarageDoorActionChain.getTriggerRefs().add(
	// requestGarageDoorOpenTriggerRef);
	//
	// // ad to this configuration object
	// this.getDevices().add(garageDevice);
	// this.getActionChains().add(openGarageDoorActionChain);
	// this.getActions().add(doorOpenAction);
	// this.getConditions().add(condition);
	// this.getTriggers().add(requestGarageDoorOpenTrigger);
	// }
}
