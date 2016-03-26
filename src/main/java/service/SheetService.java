package service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import common.ResponseInfo;
import constant.ComConstant;
import constant.SheetConstant;
import dao.ChargebackDao;
import dao.SheetDao;
import dao.SheetProgressDao;
import dto.SheetCreateDTO;
import dto.SheetDTO;
import dto.SheetEvalDTO;
import dtoMapper.SheetDTOMapper;
import entity.Chargeback;
import entity.Sheet;
import entity.SheetProgress;
import entity.SheetStateFollow;

@Service
public class SheetService extends BaseService {

	@Autowired
	SheetDao sheetDao;
	@Autowired
	SheetProgressDao sheetProgressDao;
	@Autowired
	ChargebackDao chargebackDao;

	public String createNewSheet(String dtoStr) throws JsonParseException,
			JsonMappingException, IOException {
		SheetCreateDTO sheetCreateDTO = super.getMapper().readValue(dtoStr,
				SheetCreateDTO.class);
		Sheet sheet = SheetDTOMapper.toNewSheet(sheetCreateDTO);
		ResponseInfo response = new ResponseInfo();
		try {
			this.sheetDao.persist(sheet);
		} catch (Exception e) {
			response.setMsg(ComConstant.SYS_ERRO);
		}
		return super.getMapper().writeValueAsString(response);
	}

	public String getSheetsEval(String id) throws Exception {
		List<Sheet> sheetList = this.sheetDao.findByMtnId(id);
		SheetEvalDTO dto = null;
		List<SheetEvalDTO> sheetEvalDTOList = new ArrayList<SheetEvalDTO>();
		for (Sheet sheet : sheetList) {
			dto = SheetDTOMapper.toSheetsEvalDTO(sheet);
			sheetEvalDTOList.add(dto);
		}
		return super.getMapper().writeValueAsString(sheetEvalDTOList);
	}

	public String changeState(int id, int state) throws Exception {
		ResponseInfo response = new ResponseInfo();
		Sheet sheet = this.sheetDao.findById(id);
		sheet.setState(state);
		this.sheetDao.persist(sheet);
		return super.getMapper().writeValueAsString(response);
	}

	public String getSheetProgress(String id) throws Exception {
		List<SheetProgress> sheetProgressList = this.sheetProgressDao
				.findBySheetId(id);
		return super.getMapper().writeValueAsString(sheetProgressList);
	}

	public String chargeback(String chargebackDtoStr) throws Exception {
		Chargeback chargeback = super.getMapper().readValue(chargebackDtoStr,
				Chargeback.class);
		ResponseInfo info = new ResponseInfo();
		try {
			Sheet sheet = this.sheetDao.findById(chargeback.getSheetId());
			if (chargeback.getState() == SheetConstant.CHARGEBACK_SUCCESS) {
				sheet.setState(SheetConstant.CHARGEBACK_SUCCESS);
				addToSheetStateFollow(sheet, SheetConstant.CHARGEBACK_SUCCESS);
			} else if (chargeback.getState() == SheetConstant.CHARGEBACK_REQUEST) {
				sheet.setState(SheetConstant.CHARGEBACK_REQUEST);
				addToSheetStateFollow(sheet, SheetConstant.CHARGEBACK_REQUEST);
			}
			this.sheetDao.persist(sheet);
			this.chargebackDao.persist(chargeback);
		} catch (Exception e) {
			info.setMsg(ComConstant.SYS_ERRO);
			info.setStatus(false);
		}
		return super.getMapper().writeValueAsString(info);
	}

	private void addToSheetStateFollow(Sheet sheet, int state) {
		List<SheetStateFollow> sheetStateList = sheet.getSheetStateList();
		SheetStateFollow newState = new SheetStateFollow();
		newState.setSheetId(sheet);
		newState.setState(state);
		sheetStateList.add(newState);
	}

	public String getSheetSimpleInfo(String customerId, String state)
			throws IOException {
		List<String> stateList = super.getMapper().readValue(state,
				new TypeReference<List<String>>() {
				});
		List<Sheet> sheetList = this.sheetDao.findByCustomerIdState(
				Integer.parseInt(customerId), stateList);
		List<SheetDTO> sheetDTOList = new ArrayList<SheetDTO>();
		for (Sheet sheet : sheetList) {
			sheetDTOList.add(SheetDTOMapper.toSimpleInfo(sheet));
		}
		return super.buildRespJson(true, super.EMPTY, super.getMapper()
				.writeValueAsString(sheetDTOList));
	}

	public String getSheetDetailInfo(int sheetId)
			throws JsonProcessingException {
		Sheet sheet = this.sheetDao.findById(sheetId);
		SheetDTO dto = SheetDTOMapper.toDetailInfo(sheet);
		return super.buildRespJson(true, EMPTY, super.getMapper()
				.writeValueAsString(dto));
	}

}
