package de.jumajumo.homecontrol.webservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.jumajumo.homecontrol.service.websocketregistry.WebSocketInfo;
import de.jumajumo.homecontrol.service.websocketregistry.WebSocketInfoSerializationWrapper;
import de.jumajumo.homecontrol.service.websocketregistry.WebSocketRegistryService;

@RestController
@RequestMapping("/admin")
public class AdminController
{
	@Autowired
	private WebSocketRegistryService webSocketRegistryService;

	@RequestMapping(value = "websockets", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<WebSocketInfoSerializationWrapper> getRegisteredWebSockets()
	{
		final Map<String, WebSocketInfo> registeredSessions = this.webSocketRegistryService
				.getRegisteredSessions();

		final List<WebSocketInfoSerializationWrapper> resultList = new ArrayList<WebSocketInfoSerializationWrapper>();
		for (final WebSocketInfo info : registeredSessions.values())
		{
			resultList.add(new WebSocketInfoSerializationWrapper(info));
		}

		return resultList;
	}
}