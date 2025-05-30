package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import Entity.ChamCongNhanVien;
import Entity.NhanVien;

public class ChamCongNhanVien_Dao {
	public ChamCongNhanVien_Dao() {
    }

    public ArrayList<ChamCongNhanVien> danhSachChamCongNhanVien() {
        Statement stm = null;
        NhanVien_Dao nhanVien_Dao = new NhanVien_Dao();
        ArrayList<ChamCongNhanVien> dsChamCong = new ArrayList<ChamCongNhanVien>();
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String truyVan = "select * from BangChamCongNhanVien order by ngayChamCong desc";
            stm = con.createStatement();
            ResultSet rs = stm.executeQuery(truyVan);
            while (rs.next()) {
                String maNhanVien = rs.getString("maNhanVien");
                String caLam = rs.getString("caLam");
                Date ngayChamCong = rs.getDate("ngayChamCong");
                String trangThaiDiLam = rs.getString("trangThaiDiLam");
                String gioDiLam = rs.getString("gioDiLam");
                int gioTangCa = rs.getInt("gioTangCa");
                String maNguoiCham = rs.getString("maNguoiCham");
                NhanVien nhanVien = nhanVien_Dao.layMotNhanVienTheoMaNhanVien(maNhanVien);
                NhanVien nguoiChamCong = nhanVien_Dao.layMotNhanVienTheoMaNhanVien(maNguoiCham);
                dsChamCong.add(new ChamCongNhanVien(nhanVien, ngayChamCong, caLam, trangThaiDiLam, gioDiLam, gioTangCa, nguoiChamCong));
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
        return dsChamCong;
    }
    
    public boolean themMotChamCongNhanVien(ChamCongNhanVien chamCongNhanVien) {
        PreparedStatement stm = null;
        int soDongThemDuoc = 0;
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String truyVan = "INSERT INTO BangChamCongNhanVien(maNhanVien, caLam, ngayChamCong, trangThaiDiLam"
                    + " , gioDiLam, gioTangCa, maNguoiCham)"
                    + " VALUES(?, ?, ?, ?, ?, ?,?)";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, chamCongNhanVien.getNhanVien().getMaNhanVien());
            stm.setString(2, chamCongNhanVien.getCaLam());
            stm.setDate(3, new java.sql.Date(chamCongNhanVien.getNgayChamCong().getTime()));
            stm.setString(4, chamCongNhanVien.getTrangThaiDiLam());
            stm.setString(5, chamCongNhanVien.getGioDiLam());
            stm.setInt(6, chamCongNhanVien.getGioTangCa());
            stm.setString(7, chamCongNhanVien.getNguoiChamCong().getMaNhanVien());

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

    public boolean suaMotChamCongNhanVien(ChamCongNhanVien chamCongNhanVien) {
        PreparedStatement stm = null;
        int soDongSuaDuoc = 0;
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();

            String truyVan = "UPDATE BangChamCongNhanVien set trangThaiDiLam =?, gioDiLam = ?,gioTangCa = ? , maNguoiCham = ?\n"
                    + " where maNhanVien =? and caLam=? and ngayChamCong=?";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, chamCongNhanVien.getTrangThaiDiLam());
            stm.setString(2, chamCongNhanVien.getGioDiLam());
            stm.setInt(3, chamCongNhanVien.getGioTangCa());
            stm.setString(4, chamCongNhanVien.getNguoiChamCong().getMaNhanVien());
            stm.setString(5, chamCongNhanVien.getNhanVien().getMaNhanVien());
            stm.setString(6, chamCongNhanVien.getCaLam());
            stm.setDate(7, new java.sql.Date(chamCongNhanVien.getNgayChamCong().getTime()));
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

    public ArrayList<ChamCongNhanVien> layDanhSachChamCongTheoNgay(String ngayChamCong) {
        PreparedStatement stm = null;
        NhanVien_Dao nhanVien_Dao = new NhanVien_Dao();
        ArrayList<ChamCongNhanVien> dsChamCong = new ArrayList<ChamCongNhanVien>();
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String truyVan = "select * from BangChamCongNhanVien where ngayChamCong=?";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, ngayChamCong);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String maNhanVien = rs.getString("maNhanVien");
                String caLam = rs.getString("caLam");
                Date ngayCham = rs.getDate("ngayChamCong");
                String trangThaiDiLam = rs.getString("trangThaiDiLam");
                String gioDiLam = rs.getString("gioDiLam");
                int gioTangCa = rs.getInt("gioTangCa");
                String maNguoiCham = rs.getString("maNguoiCham");
                NhanVien nhanVien = nhanVien_Dao.layMotNhanVienTheoMaNhanVien(maNhanVien);
                NhanVien nguoiChamCong = nhanVien_Dao.layMotNhanVienTheoMaNhanVien(maNguoiCham);
                dsChamCong.add(new ChamCongNhanVien(nhanVien, ngayCham, caLam, trangThaiDiLam, gioDiLam,gioTangCa, nguoiChamCong));
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
        return dsChamCong;
    }
    
    public ArrayList<String[]> layDanhSachChamCongTheoMaNhanVienVaThang(String maNhanVien, String thang, String nam) {
        PreparedStatement stm = null;
        ArrayList<String[]> arrayList = new ArrayList<>();
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String truyVan = "select caLam, trangThaiDiLam, gioTangCa\n"
                    + "from BangChamCongNhanVien  where maNhanVien = ? \n"
                    + "group by maNhanVien, trangThaiDiLam, caLam, gioTangCa, ngayChamCong\n"
                    + "having Month(ngayChamCong) = ? and year(ngayChamCong) = ?";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, maNhanVien);
            stm.setString(2, thang);
            stm.setString(3, nam);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String caLam = rs.getString("caLam");
                String trangThaiDiLam = rs.getString("trangThaiDiLam");
                int gioTangCa = rs.getInt("gioTangCa");
                String[] value = {caLam, trangThaiDiLam, String.valueOf(gioTangCa)};
                arrayList.add(value);
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
        return arrayList;
    }


}

