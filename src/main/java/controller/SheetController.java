package controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import service.SheetService;
import common.BeanAssistant;

@Controller
@RequestMapping("/sheet")
public class SheetController {
	SheetService sheetService = BeanAssistant.getBean(SheetService.class);

	// not test
	@RequestMapping("/createNewSheet")
	public @ResponseBody String createNewSheet(String dtoStr,
			@RequestParam("imgfile") List<MultipartFile> imgfileList)
			throws JsonParseException, JsonMappingException, IOException,
			ParseException {
		return this.sheetService.createNewSheet(dtoStr, imgfileList);
	}

	// not test
	@RequestMapping("/getSheetsEvalsByMtnId/{mtnId}")
	public @ResponseBody String getSheets(@PathVariable("mtnId") int mtnId)
			throws Exception {
		return this.sheetService.getSheetsEvalsByMtnId(mtnId);
	}

	@RequestMapping("/getSheetEvalBySheetId/{sheetId}")
	public @ResponseBody String getSheetEvalBySheetId(
			@PathVariable("sheetId") int sheetId) throws Exception {
		return this.sheetService.getSheetEvalBySheetId(sheetId);
	}

	// not test
	@RequestMapping("/changeStateToSuccessFail")
	public @ResponseBody String changeStateToSuccessFail(int id, int state)
			throws Exception {
		return this.sheetService.changeStateToSuccessFail(id, state);
	}

	// not test
	@RequestMapping("/getSheetProgress/{id}")
	public @ResponseBody String getSheetProgress(@PathVariable int id)
			throws Exception {
		return this.sheetService.getSheetProgress(id);
	}

	// not test
	@RequestMapping("/chargeback")
	public @ResponseBody String chargeback(String chargebackDtoStr,
			@RequestParam("imgfile") List<MultipartFile> imgfileList)
			throws Exception {
		return this.sheetService.chargeback(chargebackDtoStr, imgfileList);
	}

	@RequestMapping("/getSheetSimpleInfo")
	public @ResponseBody String getSheetSimpleInfo(String customerId,
			String states) throws IOException, ParseException {
		return this.sheetService.getSheetSimpleInfo(customerId, states);
	}

	@RequestMapping("getSheetSimpleInfoByMtnId")
	public @ResponseBody String getSheetSimpleInfoByMtnId(String mtnId,
			String states) throws Exception {
		return this.sheetService.getSheetSimpleInfoByMtnId(mtnId, states);
	}

	@RequestMapping("getSheetDetailInfo/{id}")
	public @ResponseBody String getSheetDetailInfo(
			@PathVariable("id") int sheetId) throws JsonProcessingException,
			ParseException {
		return this.sheetService.getSheetDetailInfo(sheetId);
	}

	@RequestMapping("getChargebackSimpleInfoByCustomerIdState")
	public @ResponseBody String getChargebackSimpleInfoByCustomerIdState(
			int id, String states) throws Exception {
		return this.sheetService.getChargebackSimpleInfoByCustomerIdState(id,
				states);
	}

	@RequestMapping("getChargebackDetailInfoByChargebackId/{id}")
	public @ResponseBody String getChargebackDetailInfoByChargebackId(
			@PathVariable("id") int id) throws Exception {
		return this.sheetService.getChargebackDetailInfoByChargebackId(id);
	}

	@RequestMapping("evaluate")
	public @ResponseBody String evaluate(String dtoStr,
			@RequestParam("imgFile") List<MultipartFile> imgFiles)
			throws Exception {
		return this.sheetService.evaluate(dtoStr, imgFiles);
	}

}
