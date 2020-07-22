package com.java.member.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import com.java.database.ConnectionProvider;
import com.java.database.JdbcUtil;

public class MemberDao {	//Data Access Object
	
	//singleton pattern : 단 한개의 객체만을 가지고 구현(설계)
	private static MemberDao instance = new MemberDao();
	public static MemberDao getInstance() {
		
		return instance;
	}
	
	public int insert(MemberDto memberDto) {
		//check값이 0이면 데이터가 안넘어 온 것이다. 
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int value = 0;

		try {
			conn= ConnectionProvider.getConnection();
			String sql = "insert into member values(member_num_seq.nextval,?,?,?,?,?,?,?,?,?,?,?,?, sysdate)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberDto.getId());
			pstmt.setString(2, memberDto.getPassword());
			pstmt.setString(3, memberDto.getName());
			pstmt.setString(4, memberDto.getJumin1());
			pstmt.setString(5, memberDto.getJumin2());
			pstmt.setString(6, memberDto.getEmail());
			pstmt.setString(7, memberDto.getZipcode());
			pstmt.setString(8, memberDto.getAddress());
			pstmt.setString(9, memberDto.getJob());
			pstmt.setString(10, memberDto.getMailing());
			pstmt.setString(11, memberDto.getInterest());
			pstmt.setString(12, memberDto.getMemberLevel());
			
			value = pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
			
		}
	
		return value;
	}
	
	
	public int idCheck(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int value = 0;
		
		try {
			String sql = "select id from member where id=?";
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs= pstmt.executeQuery();
			
			if(rs.next())value = 1;
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
			
		}
		return value;
	}
	
	
	public ArrayList<ZipcodeDto> zipcodeReader(String checkDong){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<ZipcodeDto> arrayList = null;
		
		try {
			String sql = "select * from zipcode where dong = ?";
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,checkDong);
			rs = pstmt.executeQuery();
			
			arrayList = new ArrayList<ZipcodeDto>();
			while(rs.next()){//커서위치 
				ZipcodeDto address = new ZipcodeDto();
				address.setZipcode(rs.getString("zipcode"));
				address.setSido(rs.getString("sido"));
				address.setGugun(rs.getString("gugun"));
				address.setDong(rs.getString("dong"));
				address.setRi(rs.getString("ri"));
				address.setBunji(rs.getString("Bunji"));
				
				arrayList.add(address);
				
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
			
		}
		
		
		return arrayList;
	}
	
	public String loginCheck(String id,String password) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String value =null;
		
		try {
			String sql = "select member_level from member where id=? and password=?";
			conn=ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,id);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			
			if(rs.next())value = rs.getString("member_level");
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return value;
	}

	public MemberDto updateId(String id) {
		MemberDto memberDto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "select * from member where id = ?";
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				memberDto = new MemberDto();
				memberDto.setNum(rs.getInt("num"));
				memberDto.setId(rs.getString("id"));
				memberDto.setPassword(rs.getString("password"));
				memberDto.setName(rs.getString("name"));
				memberDto.setJumin1(rs.getString("jumin1"));
				memberDto.setJumin2(rs.getString("jumin2"));
				
				memberDto.setEmail(rs.getString("email"));
				memberDto.setZipcode(rs.getString("zipcode"));
				memberDto.setAddress(rs.getString("address"));
				memberDto.setJob(rs.getString("job"));
				memberDto.setMailing(rs.getString("mailing"));
				memberDto.setInterest(rs.getString("interest"));
				memberDto.setMemberLevel(rs.getString("member_level"));
				
				/*Timestamp ts = rs.getTimestamp("register_date");
				long time = ts.getTime();
				Date date = new Date(time);
				memberDto.setRegisterDate(date);*/
				
				memberDto.setRegisterDate(new Date(rs.getTimestamp("register_date").getTime()));
				
			}
			
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
			
		}
		
		
		
		return memberDto;
	}

	public int update(MemberDto memberDto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int value = 0;
		
		try {
			String sql = "update member set password =?, email=?, zipcode=?, address=?, job=?, mailing=?, interest=? where num=?";
			
			conn= ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberDto.getPassword());
			pstmt.setString(2, memberDto.getEmail());
			pstmt.setString(3, memberDto.getZipcode());
			pstmt.setString(4, memberDto.getAddress());
			pstmt.setString(5, memberDto.getJob());
			pstmt.setString(6, memberDto.getMailing());
			pstmt.setString(7, memberDto.getInterest());
			pstmt.setInt(8, memberDto.getNum());
			
			value = pstmt.executeUpdate();
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return value;
	}

	public int delete(String id, String password) {
		int value = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			String sql ="delete from member where id=? and password=?";
			
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			
			value = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		return value;
	}
	

}
