package dtoMapper;

import dto.MantainerDTO;
import entity.Mantainer;

public class MantainerDTOMapper {

	public static MantainerDTO toDTO(MantainerDTO dto, Mantainer entity) {
		dto.setId(entity.getId());
		dto.setAddress(entity.getAddress());
		dto.setEvaNum(entity.getEvaNum());
		dto.setHeadPortrait(entity.getHeadPortrait());
		dto.setMantainTypeList(entity.getMantainTypeList());
		dto.setName(entity.getName());
		dto.setPhone(entity.getPhone());
		dto.setPsw(entity.getPsw());
		dto.setSignature(entity.getSignature());
		dto.setCredit(entity.getCredit());
		return dto;
	}

	public static Mantainer toEntity(Mantainer entity, MantainerDTO dto) {
		entity.setAddress(dto.getAddress());
		entity.setCredit(dto.getCredit());
		entity.setEvaNum(dto.getEvaNum());
		entity.setHeadPortrait(dto.getHeadPortrait());
		entity.setId(dto.getId());
		entity.setMantainTypeList(dto.getMantainTypeList());
		entity.setName(dto.getName());
		entity.setPhone(dto.getPhone());
		entity.setPsw(dto.getPsw());
		entity.setSignature(dto.getSignature());
		return entity;
	}

}