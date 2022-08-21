package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import util.JDBCUtil;

public class ProductDAO {
	Connection conn;
	PreparedStatement pstmt;
	final String sql_selectAll="SELECT B.* FROM (SELECT ROWNUM AS RNUM,A.* FROM (SELECT * FROM PRODUCT ORDER BY PID DESC)A WHERE ROWNUM <=?)B WHERE RNUM>=?";
	final String sql_count="SELECT COUNT(*) FROM PRODUCT";
	final String sql_insert="INSERT INTO PRODUCT VALUES((SELECT NVL(MAX(PID),0)+1 FROM PRODUCT),'테스트','10000','몰?루')";
	public ArrayList<ProductVO> selectAll(ProductVO pvo){
		ArrayList<ProductVO> datas=new ArrayList<ProductVO>();
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(sql_selectAll);
			pstmt.setInt(1, pvo.getEndNum());
			pstmt.setInt(2, pvo.getStartNum());
			ResultSet rs=pstmt.executeQuery();
			System.out.println("여까지 들어옴");
			while(rs.next()) {
				ProductVO vo=new ProductVO();
				vo.setPid(rs.getInt("PID"));
				vo.setPname(rs.getString("PNAME"));
				vo.setPprice(rs.getString("PPRICE"));
				vo.setPcategory(rs.getString("PCATEGORY"));
				datas.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return datas;
	}
	
	public int count() {
		conn=JDBCUtil.connect();
		int count=0;
		try {
			pstmt=conn.prepareStatement(sql_count);
			ResultSet rs=pstmt.executeQuery();
			int index=0;
			if(rs.next()) {
				count=rs.getInt(++index);
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return count;
	}
	
	public boolean insert() { 
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(sql_insert);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return true;
	}
	public static void main(String[] args) {
		ProductDAO dao=new ProductDAO();
		for(int i=0;i<50;i++) {
		dao.insert();
		}
	}
}
