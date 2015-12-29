package de.jumajumo.homecontrol.webservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.jumajumo.homecontrol.service.ImageCollectionService;
import de.jumajumo.homecontrol.service.ImageGroup;

@RestController
@RequestMapping("/image")
public class ImageCollectionController
{

	@Autowired
	private ImageCollectionService imageCollectionService;

	@RequestMapping(method = RequestMethod.GET, value = "refreshcollection", headers = "Accept=application/json")
	public void refreshImageCollection()
	{
		this.imageCollectionService.refreshCollection();
	}

	@RequestMapping(method = RequestMethod.GET, value = "collection", headers = "Accept=application/json")
	public List<ImageGroup> loadImageCollection()
	{
		return this.imageCollectionService.getImageCollection();
	}

}
