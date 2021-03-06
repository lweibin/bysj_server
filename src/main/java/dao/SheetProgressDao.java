package dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import common.CriteriaBuilderPersonal;
import constant.ComConstant;
import constant.SheetConstant;
import entity.SheetProgress;

@Repository
public class SheetProgressDao extends BaseDao<SheetProgress> {

	public SheetProgressDao() {
		super(SheetProgress.class);
	}

	public List<SheetProgress> findBySheetId(int id) {
		CriteriaBuilderPersonal builder = super.getCriteriaBuilderPersonal();
		builder.and(SheetConstant.SHEET_ID, id);
		builder.addOrder(ComConstant.CREATE_DATE, CriteriaBuilderPersonal.ASC);
		List<SheetProgress> SheetProgressList = super.execute(builder);
		return SheetProgressList;
	}

}
