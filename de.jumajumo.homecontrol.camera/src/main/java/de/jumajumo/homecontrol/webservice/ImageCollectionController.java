package de.jumajumo.homecontrol.webservice;

import java.io.IOException;
import java.net.SocketException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import de.jumajumo.homecontrol.service.ImageCollectionService;
import de.jumajumo.homecontrol.service.ImageGroup;
import de.jumajumo.homecontrol.service.ImageService;

@RestController
@RequestMapping("/image")
public class ImageCollectionController
{

	@Autowired
	private ImageCollectionService imageCollectionService;

	@Autowired
	private ImageService imageService;

	@RequestMapping(method = RequestMethod.GET, value = "refreshcollection", headers = "Accept=application/json")
	public void refreshImageCollection() throws SocketException, IOException
	{
		this.imageCollectionService.collectGarbage();
		this.imageCollectionService.refreshCollection();
	}

	@RequestMapping(method = RequestMethod.GET, value = "collection", headers = "Accept=application/json")
	public List<ImageGroup> loadImageCollection()
	{
		return this.imageCollectionService.getImageCollection();
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "group/{shotat}", headers = "Accept=application/json")
	public void deleteImageGroup(@PathVariable("shotat") long shotAt)
			throws SocketException, IOException
	{
		this.imageCollectionService.deleteImageGroup(shotAt);
	}

	@RequestMapping(value = "/image/{fileName}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<byte[]> getFile(
			@PathVariable("fileName") String fileName) throws IOException
	{

		byte[] output = this.imageService.loadImage(fileName + ".jpg");

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(MediaType.valueOf("image/jpeg"));
		// responseHeaders.setContentLength(output.length);
		// responseHeaders.set("Content-disposition",
		// "attachment; filename=filename.jpg");

		return new ResponseEntity<byte[]>(output, responseHeaders,
				HttpStatus.OK);

	}

}
