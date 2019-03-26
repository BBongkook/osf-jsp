package com.osf.test.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.osf.test.dao.CommonCodeDAO;
import com.osf.test.db.DBCon;
import com.osf.test.vo.CommonCodeVO;

public class CommonCodeDAOImpl implements CommonCodeDAO {
	private static final String SELECT_LIST = "select * from common_code where cc_group=?";
	@Override
	public List<CommonCodeVO> selectCommonCodeList(String ccGroup) {
		try {
			PreparedStatement ps = DBCon.openCon().prepareStatement(SELECT_LIST);
			ps.setString(1, ccGroup);
			List<CommonCodeVO> cclist = new ArrayList<>();
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				CommonCodeVO ccvo = new CommonCodeVO();
				ccvo.setCcNum(rs.getInt("cc_num"));
				ccvo.setCcGroup(rs.getString("cc_name"));
				ccvo.setCcCode(rs.getString("cc_code"));
				ccvo.setCcName(rs.getString("cc_name"));
				cclist.add(ccvo);
			} return cclist;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static void main(String[] args) {
		CommonCodeDAO ccdao = new CommonCodeDAOImpl();
		System.out.println(ccdao.selectCommonCodeList("trans"));
	}
}
