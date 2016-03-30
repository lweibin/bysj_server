package dtoMapper;

import java.text.ParseException;

import common.TimeAssistant;
import dto.SheetCreateDTO;
import dto.SheetDTO;
import dto.SheetEvalDTO;
import entity.Sheet;

public class SheetDTOMapper {

	static public Sheet toNewSheet(SheetCreateDTO dto) throws ParseException {
		Sheet sheet = new Sheet();
		sheet.setAddress(dto.getAddress());
		if (dto.getExpectiveTime() != null) {
			sheet.setExpectiveTime(TimeAssistant.toBackendFormat(dto
					.getExpectiveTime()));
		}
		sheet.setMtnId(dto.getMtnerId());
		sheet.setPhone(dto.getPhone());
		sheet.setCustomerId(dto.getCustomerId());
		sheet.setType(dto.getType());
		return sheet;
	}

	static public SheetEvalDTO toSheetsEvalDTO(Sheet entity)
			throws ParseException {
		SheetEvalDTO dto = new SheetEvalDTO();
		dto.setId(entity.getId());
		dto.setEvaluation(entity.getEvaluation());
		dto.setType(entity.getType());
		dto.setAchive(entity.getAchive());
		dto.setAttitude(entity.getAttitude());
		// dto.setEva(entity.isEnableEva());
		if (entity.getEvaTime() != null) {
			dto.setEvaTime(TimeAssistant.toFontFormat(entity.getEvaTime()));
		}
		dto.setSpeed(entity.getSpeed());
		dto.setSheetImgList(entity.getSheetImgList());
		return dto;
	}

	static public SheetDTO toSimpleInfo(Sheet entity) throws ParseException {
		SheetDTO sheetDTO = new SheetDTO();
		sheetDTO.setId(entity.getId());
		sheetDTO.setAddress(entity.getAddress());
		sheetDTO.setState(entity.getState());
		sheetDTO.setType(entity.getType());
		sheetDTO.setCreateDate(TimeAssistant.toFontFormat(entity
				.getCreateDate()));
		sheetDTO.setExpectiveTime(entity.getExpectiveTime());
		sheetDTO.setEvaTime(TimeAssistant.toFontFormat(entity.getEvaTime()));
		sheetDTO.setEndTime(TimeAssistant.toFontFormat(entity.getEndTime()));
		return sheetDTO;
	}

	static public SheetDTO toDetailInfo(Sheet entity) throws ParseException {
		SheetDTO sheetDTO = new SheetDTO();
		sheetDTO.setId(entity.getId());
		sheetDTO.setAddress(entity.getAddress());
		sheetDTO.setState(entity.getState());
		sheetDTO.setType(entity.getType());
		sheetDTO.setCreateDate(TimeAssistant.toFontFormat(entity
				.getCreateDate()));
		if (entity.getEvaTime() != null) {
			sheetDTO.setEvaTime(TimeAssistant.toFontFormat(entity.getEvaTime()));
		}
		if (entity.getEndTime() != null) {
			sheetDTO.setEndTime(TimeAssistant.toFontFormat(entity.getEndTime()));
		}
		sheetDTO.setSheetImgList(entity.getSheetImgList());
		return sheetDTO;
	}

	static public void toEvalSheet(Sheet sheet, SheetDTO dto) {
		sheet.setAchive(dto.getAchive());
		sheet.setAttitude(dto.getAttitude());
		sheet.setEvaluation(dto.getEvaluation());
		sheet.setSpeed(dto.getSpeed());
	}

}
