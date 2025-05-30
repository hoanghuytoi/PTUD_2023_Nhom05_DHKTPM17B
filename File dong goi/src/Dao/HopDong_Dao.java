package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import ConnectionDB.ConnectDB;
import Entity.HopDong;
import Entity.NhanVien;

public class HopDong_Dao {
	public HopDong_Dao() {
    }

    public ArrayList<HopDong> layDanhSachHopDong() {
        Statement stm = null;
        NhanVien_Dao nhanVien_Dao = new NhanVien_Dao();
        ArrayList<HopDong> dsHopDong = new ArrayList<HopDong>();
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String query = "select * from HopDong";
            stm = con.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                String maHopDong = rs.getString("maHopDong");
                String tenHopDong = rs.getString("tenHopDong");
                String tenKhachHang = rs.getString("tenKhachHang");
                double tienDatCoc = rs.getBigDecimal("tienDatCoc").doubleValue();
                double triGiaHD = rs.getBigDecimal("triGiaHD").doubleValue();
                Date ngayKyKet = rs.getDate("ngayKyKetHD");
                Date ngayKetThuc = rs.getDate("ngayKetThucHD");
                String yeuCau = rs.getString("yeuCau");
                String maNguoiKyKet = rs.getString("maNguoiKyKet");
                NhanVien maNhanVien = nhanVien_Dao.layMotNhanVienTheoMaNhanVien(maNguoiKyKet);
                dsHopDong.add(new HopDong(maHopDong, tenHopDong, tenKhachHang, tienDatCoc, triGiaHD, ngayKyKet, ngayKetThuc, yeuCau, maNhanVien));
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
        return dsHopDong;
    }
    
    public ArrayList<HopDong> layDanhSachHopDongChuaHetHan() {
        Statement stm = null;
        NhanVien_Dao nhanVien_Dao = new NhanVien_Dao();
        ArrayList<HopDong> dsHopDong = new ArrayList<>();

        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String query = "SELECT * FROM HopDong WHERE ngayKetThucHD >= GETDATE()";
            stm = con.createStatement();
            ResultSet rs = stm.executeQuery(query);

            while (rs.next()) {
                String maHopDong = rs.getString("maHopDong");
                String tenHopDong = rs.getString("tenHopDong");
                String tenKhachHang = rs.getString("tenKhachHang");
                double tienDatCoc = rs.getBigDecimal("tienDatCoc").doubleValue();
                double triGiaHD = rs.getBigDecimal("triGiaHD").doubleValue();
                Date ngayKyKet = rs.getDate("ngayKyKetHD");
                Date ngayKetThuc = rs.getDate("ngayKetThucHD");
                String yeuCau = rs.getString("yeuCau");
                String maNguoiKyKet = rs.getString("maNguoiKyKet");
                NhanVien maNhanVien = nhanVien_Dao.layMotNhanVienTheoMaNhanVien(maNguoiKyKet);

                dsHopDong.add(new HopDong(maHopDong, tenHopDong, tenKhachHang, tienDatCoc, triGiaHD, ngayKyKet, ngayKetThuc, yeuCau, maNhanVien));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return dsHopDong;
    }

    public HopDong layRaMotHopDongTheoMaHopDong(String maHopDong) {
        PreparedStatement stm = null;
        HopDong hopDong = null;
        NhanVien_Dao nhanVien_Dao = new NhanVien_Dao();
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String query = "select * from HopDong where maHopDong = ?";
            stm = con.prepareCall(query);
            stm.setString(1, maHopDong);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String maHopDongTmp = rs.getString("maHopDong");
                String tenHopDong = rs.getString("tenHopDong");
                String tenKhachHang = rs.getString("tenKhachHang");
                double tienDatCoc = rs.getBigDecimal("tienDatCoc").doubleValue();
                double triGiaHD = rs.getBigDecimal("triGiaHD").doubleValue();
                Date ngayKyKet = rs.getDate("ngayKyKetHD");
                Date ngayKetThuc = rs.getDate("ngayKetThucHD");
                String yeuCau = rs.getString("yeuCau");
                String maNguoiKyKet = rs.getString("maNguoiKyKet");
                NhanVien maNhanVien = nhanVien_Dao.layMotNhanVienTheoMaNhanVien(maNguoiKyKet);
                hopDong = new HopDong(maHopDongTmp, tenHopDong, tenKhachHang, tienDatCoc, triGiaHD, ngayKyKet, ngayKetThuc, yeuCau, maNhanVien);
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
        return hopDong;
    }
    
    public boolean themMotHopDong(HopDong hopDong) {
        PreparedStatement stm = null;
        int soDongThemDuoc = 0;
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String query = "insert into HopDong(maHopDong, tenHopDong, tenKhachHang,tienDatCoc, triGiaHD ,ngayKyKetHD ,ngayKetThucHD, yeuCau, maNguoiKyKet)"
                    + " values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            stm = con.prepareStatement(query);
            stm.setString(1, hopDong.getMaHopDong());
            stm.setString(2, hopDong.getTenHopDong());
            stm.setString(3, hopDong.getTenKhachHang());
            stm.setDouble(4, hopDong.getTienDatCoc());
            stm.setDouble(5, hopDong.getTriGiaHD());
            stm.setDate(6, new java.sql.Date(hopDong.getNgayKyKetHD().getTime()));
            stm.setDate(7, new java.sql.Date(hopDong.getNgayKetThucHD().getTime()));
            stm.setString(8, hopDong.getYeuCau());
            stm.setString(9,hopDong.getNguoiKyKet().getMaNhanVien());
            soDongThemDuoc = stm.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                stm.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return soDongThemDuoc != 0;
    }
    
    public String layRaMaHopDongDeThem() {
        Statement stm = null;
        String  maHopDong = "";
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String truyVan = "select top 1 * from HopDong order by LEN(maHopDong), maHopDong desc";
            stm = con.createStatement();
            ResultSet rs = stm.executeQuery(truyVan);
            while (rs.next()) {
            	maHopDong = rs.getString("maHopDong");
               
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (maHopDong.equals("") || maHopDong == null){
            return "PPHD100001";
            
        }

        String chuoiCanLay = maHopDong.split("PPHD")[1];

        try {
            chuoiCanLay = "PPHD" + (Integer.parseInt(chuoiCanLay) + 1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return chuoiCanLay;
    }

    public boolean suaMotHopDong(HopDong hopDong) {
        PreparedStatement stm = null;
        int soDongSuaDuoc = 0;
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String query = "update HopDong"
                    + " set tenHopDong = ?, tenKhachHang = ?,tienDatCoc = ?, triGiaHD = ? ,ngayKyKetHD = ?"
                    + " , ngayKetThucHD = ?, yeuCau = ? , maNguoiKyKet = ?"
                    + " where maHopDong = ?";
            stm = con.prepareStatement(query);
            stm.setString(1, hopDong.getTenHopDong());
            stm.setString(2, hopDong.getTenKhachHang());
            stm.setDouble(3, hopDong.getTienDatCoc());
            stm.setDouble(4, hopDong.getTriGiaHD());
            stm.setDate(5, new java.sql.Date(hopDong.getNgayKyKetHD().getTime()));
            stm.setDate(6, new java.sql.Date(hopDong.getNgayKetThucHD().getTime()));
            stm.setString(7, hopDong.getYeuCau());
            stm.setString(8,hopDong.getNguoiKyKet().getMaNhanVien());
            stm.setString(9, hopDong.getMaHopDong());
            soDongSuaDuoc = stm.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                stm.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return soDongSuaDuoc != 0;
    }
    
    public boolean xoaMotHopDong(String maHopDong) {
        PreparedStatement stm = null;
        int soDongXoaDuoc = 0;
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String query = "delete HopDong"
                    + " where maHopDong = ?";
            stm = con.prepareStatement(query);
            stm.setString(1, maHopDong);
            soDongXoaDuoc = stm.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                stm.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return soDongXoaDuoc != 0;
    }
}
