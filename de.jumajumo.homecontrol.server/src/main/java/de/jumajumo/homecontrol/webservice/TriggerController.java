package de.jumajumo.homecontrol.webservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.jumajumo.homecontrol.configuration.server.trigger.Trigger;
import de.jumajumo.homecontrol.service.TriggerService;
import de.jumajumo.homecontrol.type.ActionChainResult;

@RestController
@RequestMapping("/trigger")
public class TriggerController
{
	@Autowired
	private TriggerService triggerService;

	@RequestMapping(method = RequestMethod.GET, value = "{triggerpath}/activate", headers = "Accept=application/json")
	public List<ActionChainResult> activateTrigger(
			@PathVariable("triggerpath") final String path)
	{
		final Trigger trigger = triggerService.findTriggerByPath(path);

		return triggerService.executeTrigger(trigger);
	}
}
