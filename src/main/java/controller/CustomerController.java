package controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import service.CustomerService;

import com.fasterxml.jackson.core.JsonProcessingException;

import common.BeanAssistant;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	private CustomerService customerService = BeanAssistant
			.getBean(CustomerService.class);

	@RequestMapping("/getInfo/{id}")
	public @ResponseBody String getInfo(@PathVariable("id") int id)
			throws JsonProcessingException {
		return this.customerService.findById(id);
	}

	// @RequestMapping("modifyPersonalInfo")
	// public @ResponseBody String modifyPersonalInfo(String customerDtoStr,
	// @RequestParam("headPortrait") MultipartFile img) throws Exception{
	// return this.customerService.modifyPersonalInfo(customerDtoStr, img);
	// }

	@RequestMapping("modifyPersonalInfo")
	public @ResponseBody String modifyPersonalInfo(String dtoStr)
			throws Exception {
		return this.customerService.modifyPersonalInfo(dtoStr);
	}

	@RequestMapping("modifyHeadPortrait")
	public @ResponseBody String modifyHeadPortrait(@RequestParam("id") int id,
			@RequestParam("headPortrait") MultipartFile img) throws IOException {
		return this.customerService.modifyHeadPortrait(id, img);
	}

	@RequestMapping("/collectMtn")
	public @ResponseBody String collectMtn(
			@RequestParam("customerId") int customerId,
			@RequestParam("mantainerId") int mtnId)
			throws JsonProcessingException {
		return this.customerService.collectMtn(customerId, mtnId);
	}

	@RequestMapping("findMtnByLngLat")
	public @ResponseBody String findMtnByLngLat(double longitude,
			double latitude, double distance) throws JsonProcessingException {
		return this.customerService.findMtnByLngLat(longitude, latitude,
				distance);
	}

	@RequestMapping("findMtnByCustomerId")
	public @ResponseBody String findMtnByCustomerId(int customerId,
			double longitude, double latitude) throws JsonProcessingException {
		return this.customerService.findMtnByCustomerId(customerId, longitude, latitude);
	}

}
