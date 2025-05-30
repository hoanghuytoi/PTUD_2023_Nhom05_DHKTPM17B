package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import ConnectionDB.ConnectDB;
import Entity.CongNhan;
import Entity.ToNhom;

public class CongNhan_Dao {
	public CongNhan_Dao() {
    }

    public ArrayList<CongNhan> layDanhSachCongNhan() {
        ArrayList<CongNhan> dsCongNhan = new ArrayList<CongNhan>();
        Statement stm = null;
        ToNhom_Dao toNhom_DAO = new ToNhom_Dao();
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String query = "SELECT * FROM CongNhan";
            stm = con.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                String maCongNhan = rs.getString("maCongNhan");
                String hoTen = rs.getString("hoTen");
                Date ngaySinh = rs.getDate("ngaySinh");
                String maCCCD = rs.getString("maCCCD");
                String soDienThoai = rs.getString("soDienThoai");
                String email = rs.getString("email");
                Boolean gioiTinh = rs.getBoolean("gioiTinh");
                String anhDaiDien = rs.getString("anhDaiDien");
                String diaChi = rs.getString("diaChi");
                Date ngayVaoLam = rs.getDate("ngayVaoLam");
                String maToNhom = rs.getString("toNhom");
                ToNhom toNhom = toNhom_DAO.layMotToNhomTheoMa(maToNhom);
                dsCongNhan.add(new CongNhan(maCongNhan, hoTen, ngaySinh, maCCCD, soDienThoai, email, ngayVaoLam, gioiTinh, anhDaiDien, diaChi, toNhom));
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
        return dsCongNhan;
    }

    public String layRaMaCongNhanDeThem() {
        Statement stm = null;
        String  maCongNhan = "";
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String truyVan = "select top 1 * from CongNhan order by LEN(maCongNhan), maCongNhan desc";
            stm = con.createStatement();
            ResultSet rs = stm.executeQuery(truyVan);
            while (rs.next()) {
            	maCongNhan = rs.getString("maCongNhan");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (maCongNhan.equals("") || maCongNhan == null){
            return "PPCN100001";
            
        }

        String chuoiCanLay = maCongNhan.split("PPCN")[1];

        try {
            chuoiCanLay = "PPCN" + (Integer.parseInt(chuoiCanLay) + 1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return chuoiCanLay;
    }
    
    public CongNhan layMotCongNhanTheoMa(String maCongNhan) {
        CongNhan congNhan = null;
        PreparedStatement stm = null;
        ToNhom_Dao toNhom_DAO = new ToNhom_Dao();
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String query = "SELECT * FROM CongNhan where maCongNhan = ?";
            stm = con.prepareStatement(query);
            stm.setString(1, maCongNhan);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String maCongNhanOb = rs.getString("maCongNhan");
                String hoTen = rs.getString("hoTen");
                Date ngaySinh = rs.getDate("ngaySinh");
                String maCCCD = rs.getString("maCCCD");
                String soDienThoai = rs.getString("soDienThoai");
                String email = rs.getString("email");
                Boolean gioiTinh = rs.getBoolean("gioiTinh");
                String anhDaiDien = rs.getString("anhDaiDien");
                String diaChi = rs.getString("diaChi");
                Date ngayVaoLam = rs.getDate("ngayVaoLam");
                String maToNhom = rs.getString("toNhom");
                ToNhom toNhom = toNhom_DAO.layMotToNhomTheoMa(maToNhom);
                congNhan = new CongNhan(maCongNhanOb, hoTen, ngaySinh, maCCCD, soDienThoai, email, ngayVaoLam, gioiTinh, anhDaiDien, diaChi, toNhom);
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
        return congNhan;
    }

    public boolean themMotCongNhan(CongNhan congNhan) {
        PreparedStatement stm = null;
        int soLuongThemDuoc = 0;

        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String query = "INSERT INTO CongNhan(maCongNhan, hoTen, ngaySinh, maCCCD, soDienThoai, email, gioiTinh, anhDaiDien, diaChi, ngayVaoLam, toNhom) "
                    + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            stm = con.prepareStatement(query);
            stm.setString(1, congNhan.getMaCongNhan());
            stm.setString(2, congNhan.getHoTen());
            stm.setDate(3, new java.sql.Date(congNhan.getNgaySinh().getTime()));
            stm.setString(4, congNhan.getMaCCCD());
            stm.setString(5, congNhan.getSoDienThoai());
            stm.setString(6, congNhan.getEmail());
            stm.setBoolean(7, congNhan.isGioiTinh());
            stm.setString(8, congNhan.getAnhDaiDien());
            stm.setString(9, congNhan.getDiaChi());
            stm.setDate(10, new java.sql.Date(congNhan.getNgayVaoLam().getTime()));
            stm.setString(11, congNhan.getToNhom().getMaToNhom());
            soLuongThemDuoc = stm.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                stm.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return soLuongThemDuoc != 0;
    }
    
    public boolean capNhatMotCongNhan(CongNhan congNhan) {
        PreparedStatement stm = null;
        int soLuongDuocCapNhat = 0;
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String query = "UPDATE CongNhan"
                    + " SET hoTen = ?, ngaySinh = ?, maCCCD = ?,"
                    + " soDienThoai = ?, email = ?,"
                    + " gioiTinh = ?, anhDaiDien = ?, diaChi = ?,"
                    + " ngayVaoLam = ?, toNhom = ?"
                    + " WHERE maCongNhan = ? ";
            stm = con.prepareStatement(query);
            stm.setString(1, congNhan.getHoTen());
            stm.setDate(2, new java.sql.Date(congNhan.getNgaySinh().getTime()));
            stm.setString(3, congNhan.getMaCCCD());
            stm.setString(4, congNhan.getSoDienThoai());
            stm.setString(5, congNhan.getEmail());
            stm.setBoolean(6, congNhan.isGioiTinh());
            stm.setString(7, congNhan.getAnhDaiDien());
            stm.setString(8, congNhan.getDiaChi());
            stm.setDate(9, new java.sql.Date(congNhan.getNgayVaoLam().getTime()));
            stm.setString(10, congNhan.getToNhom().getMaToNhom());
            stm.setString(11, congNhan.getMaCongNhan());
            soLuongDuocCapNhat = stm.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                stm.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return soLuongDuocCapNhat != 0;
    }

    public boolean xoaCongNhanTheoMa(String maCongNhan) {
        PreparedStatement stm = null;
        int soLuongXoaDuoc = 0;
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String query = "delete from CongNhan where maCongNhan = ?";
            stm = con.prepareStatement(query);
            stm.setString(1, maCongNhan);
            soLuongXoaDuoc = stm.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                stm.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return soLuongXoaDuoc != 0;
    }
    
    public int laySoLuongCongNhanTheoToNhom(String maToNhom) {
        PreparedStatement stm = null;
        ResultSet rs = null;
        int soLuong = 0;

        try {
        	ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String truyVan = "SELECT COUNT(*) AS soLuongCongNhan FROM CongNhan WHERE toNhom = ?";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, maToNhom);
            rs = stm.executeQuery();

            if (rs.next()) {
                soLuong = rs.getInt("soLuongCongNhan");
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                stm.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        return soLuong;
    }
    
    public ArrayList<CongNhan> layDanhSachCongNhanTheoMaTo(String maTo) {
        ArrayList<CongNhan> dsCongNhan = new ArrayList<CongNhan>();
        PreparedStatement stm = null;
        ToNhom_Dao toNhom_DAO = new ToNhom_Dao();
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String truyVan = "select * from CongNhan where toNhom = ?";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, maTo);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String maCongNhan = rs.getString("maCongNhan");
                String hoTen = rs.getString("hoTen");
                Date ngaySinh = rs.getDate("ngaySinh");
                String maCCCD = rs.getString("maCCCD");
                String soDienThoai = rs.getString("soDienThoai");
                String email = rs.getString("email");
                Boolean gioiTinh = rs.getBoolean("gioiTinh");
                String anhDaiDien = rs.getString("anhDaiDien");
                String diaChi = rs.getString("diaChi");
                Date ngayVaoLam = rs.getDate("ngayVaoLam");
                String maToNhom = rs.getString("toNhom");
                ToNhom toNhom = toNhom_DAO.layMotToNhomTheoMa(maToNhom);
                dsCongNhan.add(new CongNhan(maCongNhan, hoTen, ngaySinh, maCCCD, soDienThoai, email, ngayVaoLam, gioiTinh, anhDaiDien, diaChi, toNhom));
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
        return dsCongNhan;
    }
    
    public ArrayList<CongNhan> layDanhSachCongNhanLamTrongThang(int thang, int nam) {
        ArrayList<CongNhan> dsCongNhan = new ArrayList<CongNhan>();
        PreparedStatement stm = null;
        
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String truyVan = "select CN.maCongNhan from BangChamCongCongNhan CCCN JOIN PhanCongCongNhan PCCC on CCCN.maPhanCong = PCCC.maPhanCong"
                    + " join CongNhan CN on CN.maCongNhan = PCCC.maCongNhan"
                    + " where MONTH(ngayChamCong) = ? and year(ngayChamCong) = ? group by CN.maCongNhan";
            stm = con.prepareStatement(truyVan);
            stm.setInt(1, thang);
            stm.setInt(2, nam);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String maCongNhan = rs.getString("maCongNhan");
                CongNhan congNhan = layMotCongNhanTheoMa(maCongNhan);
                dsCongNhan.add(congNhan);
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
        return dsCongNhan;
    }
    
    public ArrayList<CongNhan> layDanhSachCongNhanKhongDiLamTrongThang(int thang, int nam) {
        PreparedStatement stm = null;
        ArrayList<CongNhan> dsCongNhan = new ArrayList<CongNhan>();
        ToNhom_Dao toNhom_DAO = new ToNhom_Dao();
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String truyVan = "select * from CongNhan CN"
                    + " where CN.maCongNhan not in (select distinct maCongNhan from PhanCongCongNhan PCCN "
                    + " join BangChamCongCongNhan CCCN on PCCN.maPhanCong = CCCN.maPhanCong"
                    + " where month(ngayChamCong) = ? and year(ngayChamCong) = ?)";
            stm = con.prepareCall(truyVan);
            stm.setInt(1, thang);
            stm.setInt(2, nam);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String maCongNhan = rs.getString("maCongNhan");
                String hoTen = rs.getString("hoTen");
                Date ngaySinh = rs.getDate("ngaySinh");
                String maCCCD = rs.getString("maCCCD");
                String soDienThoai = rs.getString("soDienThoai");
                String email = rs.getString("email");
                Boolean gioiTinh = rs.getBoolean("gioiTinh");
                String anhDaiDien = rs.getString("anhDaiDien");
                String diaChi = rs.getString("diaChi");
                Date ngayVaoLam = rs.getDate("ngayVaoLam");
                String maToNhom = rs.getString("toNhom");
                ToNhom toNhom = toNhom_DAO.layMotToNhomTheoMa(maToNhom);
                dsCongNhan.add(new CongNhan(maCongNhan, hoTen, ngaySinh, maCCCD, soDienThoai, email, ngayVaoLam, gioiTinh, anhDaiDien, diaChi, toNhom));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                stm.close();;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return dsCongNhan;
    }
}
