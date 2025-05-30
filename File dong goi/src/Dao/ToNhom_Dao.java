package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import ConnectionDB.ConnectDB;
import Entity.ToNhom;

public class ToNhom_Dao {
	public ToNhom_Dao() {
    }
	
	public ArrayList<ToNhom> layDanhSachToNhom(){
		ArrayList<ToNhom> dsToNhom = new ArrayList<ToNhom>();
		Statement stm = null;
		try {
			ConnectionDB.ConnectDB.getInstance();
			Connection con = ConnectionDB.ConnectDB.getConnection();
			String query = "select *from ToNhom";
			stm = con.createStatement();
			ResultSet rs = stm.executeQuery(query);
			while(rs.next()) {
				String maToNhom = rs.getString("maToNhom");
				String tenToNhom = rs.getString("tenToNhom");
				int soLuongCongNhan = rs.getInt("soLuongCongNhan");
				dsToNhom.add(new ToNhom(maToNhom, tenToNhom, soLuongCongNhan));
 			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				stm.close();
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return dsToNhom;
	}
	
	public ToNhom layMotToNhomTheoMa(String maToNhom){
        PreparedStatement stm = null;
        ToNhom toNhom = null;
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String query = "SELECT * FROM ToNhom WHERE maToNhom = ?";
            stm = con.prepareStatement(query);
            stm.setString(1, maToNhom);
            ResultSet rs = stm.executeQuery();
           
            while (rs.next()){
                String maToNhomOb = rs.getString("maToNhom");
                System.out.println("Mã tổ nhóm: " + maToNhomOb);
                String tenToNhom = rs.getString("tenToNhom");
                int soLuongCongNhan = rs.getInt("soLuongCongNhan");
                toNhom = new ToNhom(maToNhomOb, tenToNhom, soLuongCongNhan);
            }
//            if (toNhom == null){
//                throw new Exception("Không tìm thấy tổ nhóm này!");
//            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
			try {
				stm.close();
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
        return toNhom;
    }
	
	public ToNhom layMotToNhomTheoTen(String ten){
        PreparedStatement stm = null;
        ToNhom toNhom = null;
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String truyVan = "SELECT * FROM ToNhom WHERE tenToNhom = ?";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, ten);
            ResultSet rs = stm.executeQuery();
           
            while (rs.next()){
                String maToNhomOb = rs.getString("maToNhom");
                String tenToNhom = rs.getString("tenToNhom");
                int soLuongCongNhan = rs.getInt("soLuongCongNhan");
                toNhom = new ToNhom(maToNhomOb, tenToNhom, soLuongCongNhan);
            }
            if (toNhom == null){
                throw new Exception("Không tìm thấy tổ nhóm này!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return toNhom;
    }

	public int layRaSoLuongToNhom(){
		ArrayList<ToNhom> dsToNhom = new ArrayList<ToNhom>();
        Statement stm = null;
        int n = 0;
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String chuoiTruyVan = "select * from ToNhom";
            stm = con.createStatement();
            ResultSet rs = stm.executeQuery(chuoiTruyVan);
            while (rs.next()) {
                String maTo = rs.getString("maToNhom");
                String tenToNhom = rs.getString("tenToNhom");
                int soLuongCongNhan = rs.getInt("soLuongCongNhan");
                dsToNhom.add(new ToNhom(maTo, tenToNhom, soLuongCongNhan));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                stm.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        n = dsToNhom.size();
        return n;
    }
	
	public boolean themToNhom(ToNhom toNhom) {
        PreparedStatement stm = null;
        int soLuongThemDuoc = 0;
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String query = "INSERT INTO ToNhom(maToNhom, tenToNhom) VALUES (?,?)";
            stm = con.prepareStatement(query);
            stm.setString(1, toNhom.getMaToNhom());
            stm.setString(2, toNhom.getTenToNhom());
            soLuongThemDuoc = stm.executeUpdate();
            if (soLuongThemDuoc == 0) {
                return false;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            try {
                stm.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return true;
    }
	
	public String layRaMaToNhomDeThem() {
        Statement stm = null;
        String  maToNhom = "";
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String truyVan = "select top 1 * from ToNhom order by LEN(maToNhom), maToNhom desc";
            stm = con.createStatement();
            ResultSet rs = stm.executeQuery(truyVan);
            while (rs.next()) {
            	maToNhom = rs.getString("maToNhom");
               
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (maToNhom.equals("") || maToNhom == null){
            return "PPTN100001";
            
        }

        String chuoiCanLay = maToNhom.split("PPTN")[1];

        try {
            chuoiCanLay = "PPTN" + (Integer.parseInt(chuoiCanLay) + 1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return chuoiCanLay;
    }
	
	
	public boolean xoaMotToNhomTheoMa(String maToNhom) {
        PreparedStatement stm = null;
        int soLuongXoa = 0;
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String query = "DELETE FROM ToNhom" + " where maToNhom = ?";
            stm = con.prepareStatement(query);
            stm.setString(1, maToNhom);
            soLuongXoa = stm.executeUpdate();
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            
        } finally{
            try {
                stm.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return soLuongXoa != 0;
    }
	public boolean suaToNhom(ToNhom toNhom) {
        PreparedStatement stm = null;
        int soLuongSua = 0;
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String query = "UPDATE ToNhom"
                    + " set tenToNhom = ?"
                    + " where maToNhom = ?";
            stm = con.prepareStatement(query);
            stm.setString(1, toNhom.getTenToNhom());
            stm.setString(2, toNhom.getMaToNhom());
            soLuongSua = stm.executeUpdate();
            if (soLuongSua == 0) {
                return false;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        } finally{
            try {
                stm.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return true;
    }
}
