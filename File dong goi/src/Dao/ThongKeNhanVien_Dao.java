package Dao;

import Entity.BangLuongNhanVien;
import Entity.NhanVien;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

public class ThongKeNhanVien_Dao {

    public ThongKeNhanVien_Dao() {
    }

    public ArrayList<BangLuongNhanVien> layTop5NhanVienLuongCaoNhat() {
        PreparedStatement stm = null;
        ArrayList<BangLuongNhanVien> dsBangLuong = new ArrayList<>();
        NhanVien_Dao nhanVien_DAO = new NhanVien_Dao();

        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String truyVan = "SELECT TOP 5 * FROM BangLuongNhanVien ORDER BY thucLanh DESC";
            stm = con.prepareStatement(truyVan);

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                String maBangLuong = rs.getString("maBangLuong");
                String maNhanVien = rs.getString("maNhanVien");
                int soNgayDiLam = rs.getInt("soNgayDiLam");
                int soNgayNghi = rs.getInt("soNgayNghi");
                int soPhepNghi = rs.getInt("soPhepNghi");
                Date ngayTinh = rs.getDate("ngayTinh");
                String luongTheoThang = rs.getString("luongTheoThang");
                double luongTangCa = rs.getBigDecimal("luongTangCa").doubleValue();
                double phuCap = rs.getBigDecimal("phuCap").doubleValue();
                double thucLanh = rs.getBigDecimal("thucLanh").doubleValue();
                String donViTien = rs.getString("donViTien");
                NhanVien nhanVien = nhanVien_DAO.layMotNhanVienTheoMaNhanVien(maNhanVien);
                dsBangLuong.add(new BangLuongNhanVien(maBangLuong, nhanVien, soNgayDiLam, soNgayNghi, soPhepNghi, ngayTinh, luongTheoThang, luongTangCa, phuCap, thucLanh, donViTien));
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

    public ArrayList<BangLuongNhanVien> layTop5NhanVienLuongThapNhat() {
        PreparedStatement stm = null;
        ArrayList<BangLuongNhanVien> dsBangLuong = new ArrayList<>();
        NhanVien_Dao nhanVien_DAO = new NhanVien_Dao();

        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String truyVan = "SELECT TOP 5 * FROM BangLuongNhanVien ORDER BY thucLanh ASC";
            stm = con.prepareStatement(truyVan);

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                String maBangLuong = rs.getString("maBangLuong");
                String maNhanVien = rs.getString("maNhanVien");
                int soNgayDiLam = rs.getInt("soNgayDiLam");
                int soNgayNghi = rs.getInt("soNgayNghi");
                int soPhepNghi = rs.getInt("soPhepNghi");
                Date ngayTinh = rs.getDate("ngayTinh");
                String luongTheoThang = rs.getString("luongTheoThang");
                double luongTangCa = rs.getBigDecimal("luongTangCa").doubleValue();
                double phuCap = rs.getBigDecimal("phuCap").doubleValue();
                double thucLanh = rs.getBigDecimal("thucLanh").doubleValue();
                String donViTien = rs.getString("donViTien");
                NhanVien nhanVien = nhanVien_DAO.layMotNhanVienTheoMaNhanVien(maNhanVien);
                dsBangLuong.add(new BangLuongNhanVien(maBangLuong, nhanVien, soNgayDiLam, soNgayNghi, soPhepNghi, ngayTinh, luongTheoThang, luongTangCa, phuCap, thucLanh, donViTien));
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

