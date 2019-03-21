package com.osf.test.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.osf.test.dao.PBoardDAO;
import com.osf.test.db.DBCon;

public class PBoardDAOImpl implements PBoardDAO {
	private static final String INSERT_PBOARD = "insert into photo_board(pb_num, pb_title, pb_content, pb_credat, pb_cretim, pb_file_path, pb_real_path)"
											+" values(seq_pb_num.nextval,?,?,to_char(sysdate,'YYYYMMDD'),to_char(sysdate,'HH24MISS'),?,?)";
	private static final String SELECT_PBOARD_LIST = "select * from photo_board";
	private static final String SELECT_PBOARD = "select * from photo_board where pb_num=?";
												
	@Override
	public int insertPBoard(Map<String, String> pBoard) {
			try {
				PreparedStatement ps = DBCon.openCon().prepareStatement(INSERT_PBOARD);
				ps.setString(1, pBoard.get("pb_title"));
				ps.setString(2, pBoard.get("pb_content"));
				ps.setString(3, pBoard.get("pb_file_path"));
				ps.setString(4, pBoard.get("pb_real_path"));
				return ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				DBCon.close();
			}
		return 0;
	}
	public static void main(String[] args) {
//		PBoardDAO pdao = new PBoardDAOImpl();
//		System.out.println(pdao.selectPBoardList());
	}
	@Override
	public List<Map<String, String>> selectPBoardList() {
		try {
			PreparedStatement ps = DBCon.openCon().prepareStatement(SELECT_PBOARD_LIST);
			List<Map<String,String>> pBoardList = new ArrayList<>();
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Map<String,String> pbm = new HashMap<>();
				pbm.put("pb_num", rs.getString("pb_num"));
				pbm.put("pb_title", rs.getString("pb_title"));
				pbm.put("pb_content", rs.getString("pb_content"));
				pbm.put("pb_credat", rs.getString("pb_credat"));
				pbm.put("pb_cretim", rs.getString("pb_cretim"));
				pbm.put("pb_file_path", rs.getString("pb_file_path"));
				pbm.put("pb_real_path", rs.getString("pb_real_path"));
				pBoardList.add(pbm);
			}
			return pBoardList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public Map<String, String> selectPBoard(int pbNum) {
		try {
			PreparedStatement ps = DBCon.openCon().prepareStatement(SELECT_PBOARD);
			ps.setInt(1, pbNum);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				Map<String,String> pbm = new HashMap<>();
				pbm.put("pb_num", rs.getString("pb_num"));
				pbm.put("pb_title", rs.getString("pb_title"));
				pbm.put("pb_content", rs.getString("pb_content"));
				pbm.put("pb_credat", rs.getString("pb_credat"));
				pbm.put("pb_cretim", rs.getString("pb_cretim"));
				pbm.put("pb_file_path", rs.getString("pb_file_path"));
				pbm.put("pb_real_path", rs.getString("pb_real_path"));
				return pbm;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
