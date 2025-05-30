package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import Entity.BangLuongCongNhan;
import Entity.CongNhan;

public class ThongKeCongNhan_Dao {
	
	public ThongKeCongNhan_Dao() {
    }

    public ArrayList<BangLuongCongNhan> layTop5CongNhanLuongCaoNhat() {
        PreparedStatement stm = null;
        ArrayList<BangLuongCongNhan> dsBangLuong = new ArrayList<>();
        CongNhan_Dao congNhan_DAO = new CongNhan_Dao();

        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String truyVan = "SELECT TOP 5 * FROM BangLuongCongNhan ORDER BY tongLuong DESC";
            stm = con.prepareStatement(truyVan);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String maBangLuong = rs.getString("maBangLuong");
                String maCongNhanOb = rs.getString("maCongNhan");
                Date ngayTinh = rs.getDate("ngayTinh");
                int soLuongSanPhamLam = rs.getInt("soLuongSanPhamLam");
                int soNgayDiLam = rs.getInt("soNgayDiLam");
                int soNgayNghi = rs.getInt("soNgayNghi");
                int soPhepNghi = rs.getInt("soPhepNghi");
                double tongLuong = rs.getBigDecimal("tongLuong").doubleValue();
                String donViTien = rs.getString("donViTien");
                String luongTheoThang = rs.getString("luongTheoThang");
                CongNhan congNhan = congNhan_DAO.layMotCongNhanTheoMa(maCongNhanOb);
                dsBangLuong.add(new BangLuongCongNhan(maBangLuong, congNhan,
                        soLuongSanPhamLam, soNgayDiLam, soNgayNghi, soPhepNghi, ngayTinh, tongLuong, donViTien, luongTheoThang));
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

        return dsBangLuong;
    }

    public ArrayList<BangLuongCongNhan> layTop5CongNhanLuongThapNhat() {
    	PreparedStatement stm = null;
        ArrayList<BangLuongCongNhan> dsBangLuong = new ArrayList<>();
        CongNhan_Dao congNhan_DAO = new CongNhan_Dao();

        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String truyVan = "SELECT TOP 5 * FROM BangLuongCongNhan ORDER BY tongLuong ASC";
            stm = con.prepareStatement(truyVan);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String maBangLuong = rs.getString("maBangLuong");
                String maCongNhanOb = rs.getString("maCongNhan");
                Date ngayTinh = rs.getDate("ngayTinh");
                int soLuongSanPhamLam = rs.getInt("soLuongSanPhamLam");
                int soNgayDiLam = rs.getInt("soNgayDiLam");
                int soNgayNghi = rs.getInt("soNgayNghi");
                int soPhepNghi = rs.getInt("soPhepNghi");
                double tongLuong = rs.getBigDecimal("tongLuong").doubleValue();
                String donViTien = rs.getString("donViTien");
                String luongTheoThang = rs.getString("luongTheoThang");
                CongNhan congNhan = congNhan_DAO.layMotCongNhanTheoMa(maCongNhanOb);
                dsBangLuong.add(new BangLuongCongNhan(maBangLuong, congNhan,
                        soLuongSanPhamLam, soNgayDiLam, soNgayNghi, soPhepNghi, ngayTinh, tongLuong, donViTien, luongTheoThang));
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

        return dsBangLuong;
    }
}
