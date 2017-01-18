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

import de.jumajumo.homecontrol.service.ECameraInformation;
import de.jumajumo.homecontrol.service.ImageCollectionService;
import de.jumajumo.homecontrol.service.ImageGroup;

@RestController
@RequestMapping("/image")
public class ImageCollectionController
{

	@Autowired
	private ImageCollectionService imageCollectionService;

	@RequestMapping(method = RequestMethod.GET, value = "{camera}/refreshcollection", headers = "Accept=application/json")
	public void refreshImageCollection(
			@PathVariable("camera") String cameraName)
			throws SocketException, IOException
	{
		this.imageCollectionService
				.collectGarbage(ECameraInformation.fromCameraName(cameraName));
		this.imageCollectionService.refreshCollection(
				ECameraInformation.fromCameraName(cameraName));
	}

	@RequestMapping(method = RequestMethod.GET, value = "{camera}/collection", headers = "Accept=application/json")
	public List<ImageGroup> loadImageCollection(
			@PathVariable("camera") String cameraName)
	{
		return this.imageCollectionService.getImageCollection(
				ECameraInformation.fromCameraName(cameraName));
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "{camera}/group/{shotat}", headers = "Accept=application/json")
	public void deleteImageGroup(@PathVariable("camera") String cameraName,
			@PathVariable("shotat") long shotAt)
			throws SocketException, IOException
	{
		this.imageCollectionService.deleteImageGroup(
				ECameraInformation.fromCameraName(cameraName), shotAt);
	}

	@RequestMapping(value = "{camera}/image/{fileName}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<byte[]> getFile(
			@PathVariable("camera") String cameraName,
			@PathVariable("fileName") String fileName) throws IOException
	{

		byte[] output = this.imageCollectionService.loadImage(
				ECameraInformation.fromCameraName(cameraName),
				fileName + ".jpg");

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(MediaType.valueOf("image/jpeg"));
		// responseHeaders.setContentLength(output.length);
		// responseHeaders.set("Content-disposition",
		// "attachment; filename=filename.jpg");

		return new ResponseEntity<byte[]>(output, responseHeaders,
				HttpStatus.OK);

	}

}
