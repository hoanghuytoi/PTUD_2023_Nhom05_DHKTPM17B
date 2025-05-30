package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import ConnectionDB.ConnectDB;
import Entity.NhanVien;
import Entity.PhongBan;

public class NhanVien_Dao {
	public NhanVien_Dao() {
    }

    public ArrayList<NhanVien> layDanhSachNhanVien() {
        PhongBan_Dao phongBan_DAO = new PhongBan_Dao();
        Statement stm = null;
        ArrayList<NhanVien> dsNhanVien = new ArrayList<NhanVien>();
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String query = "SELECT * FROM NhanVien";
            stm = con.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                String maNhanVien = rs.getString("maNhanVien");
                String hoTen = rs.getString("hoTen");
                Date ngaySinh = rs.getDate("ngaySinh");
                String maCCCD = rs.getString("maCCCD");
                String soDienThoai = rs.getString("soDienThoai");
                String email = rs.getString("email");
                String matKhau = rs.getString("matKhau");
                String chucVu = rs.getString("chucVu");
                Date ngayVaoLam = rs.getDate("ngayVaoLam");
                double luongCoBan = rs.getBigDecimal("luongCoBan").doubleValue();
                boolean gioiTinh = rs.getBoolean("gioiTinh");
                String anhDaiDien = rs.getString("anhDaiDien");
                String diaChi = rs.getString("diaChi");
                String maPhongBan = rs.getString("maPhongBan");
                PhongBan phongBan = phongBan_DAO.layMotPhongBanTheoMa(maPhongBan);
                dsNhanVien.add(new NhanVien(maNhanVien, hoTen, ngaySinh, maCCCD, soDienThoai, email, matKhau, chucVu, ngayVaoLam, luongCoBan, gioiTinh, anhDaiDien, diaChi, phongBan));
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
        return dsNhanVien;
    }
    
    public String layRaMaNhanVienDeThem() {
        Statement stm = null;
        String  maNhanVien = "";
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String truyVan = "select top 1 * from NhanVien order by LEN(maNhanVien), maNhanVien desc";
            stm = con.createStatement();
            ResultSet rs = stm.executeQuery(truyVan);
            while (rs.next()) {
            	maNhanVien = rs.getString("maNhanVien");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (maNhanVien.equals("") || maNhanVien == null){
            return "PPNV100001";
            
        }

        String chuoiCanLay = maNhanVien.split("PPNV")[1];

        try {
            chuoiCanLay = "PPNV" + (Integer.parseInt(chuoiCanLay) + 1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return chuoiCanLay;
    }
    
    public ArrayList<NhanVien> layDanhSachNhanVienTheoMaPhongBan(String maPhongBan) {
        PreparedStatement stm = null;
        ArrayList<NhanVien> dsNhanVien = new ArrayList<NhanVien>();
        PhongBan_Dao phongBan_DAO = new PhongBan_Dao();
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String query = "SELECT * FROM NhanVien where maPhongBan = ?";
            stm = con.prepareStatement(query);
            stm.setString(1, maPhongBan);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String maNhanVien = rs.getString("maNhanVien");
                String hoTen = rs.getString("hoTen");
                Date ngaySinh = rs.getDate("ngaySinh");
                String maCCCD = rs.getString("maCCCD");
                String soDienThoai = rs.getString("soDienThoai");
                String email = rs.getString("email");
                String matKhau = rs.getString("matKhau");
                String chucVu = rs.getString("chucVu");
                Date ngayVaoLam = rs.getDate("ngayVaoLam");
                double luongCoBan = rs.getBigDecimal("luongCoBan").doubleValue();
                boolean gioiTinh = rs.getBoolean("gioiTinh");
                String anhDaiDien = rs.getString("anhDaiDien");
                String diaChi = rs.getString("diaChi");
                String maPhongBanOB = rs.getString("maPhongBan");
                PhongBan phongBan = phongBan_DAO.layMotPhongBanTheoMa(maPhongBanOB);
                dsNhanVien.add(new NhanVien(maNhanVien, hoTen, ngaySinh, maCCCD, soDienThoai, email, matKhau, chucVu, ngayVaoLam, luongCoBan, gioiTinh, anhDaiDien, diaChi, phongBan));

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
        return dsNhanVien;
    }

    public NhanVien layMotNhanVienTheoMaNhanVien(String maNhanVien) {
        PreparedStatement stm = null;
        NhanVien nhanVien = null;
        PhongBan_Dao phongBan_DAO = new PhongBan_Dao();
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String query = "SELECT * FROM NhanVien where maNhanVien = ?";
            stm = con.prepareStatement(query);
            stm.setString(1, maNhanVien);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String maNhanVienOB = rs.getString("maNhanVien");
                String hoTen = rs.getString("hoTen");
                Date ngaySinh = rs.getDate("ngaySinh");
                String maCCCD = rs.getString("maCCCD");
                String soDienThoai = rs.getString("soDienThoai");
                String email = rs.getString("email");
                String matKhau = rs.getString("matKhau");
                String chucVu = rs.getString("chucVu");
                Date ngayVaoLam = rs.getDate("ngayVaoLam");
                double luongCoBan = rs.getBigDecimal("luongCoBan").doubleValue();
                boolean gioiTinh = rs.getBoolean("gioiTinh");
                String anhDaiDien = rs.getString("anhDaiDien");
                String diaChi = rs.getString("diaChi");
                String maPhongBanOB = rs.getString("maPhongBan");
                PhongBan phongBan = phongBan_DAO.layMotPhongBanTheoMa(maPhongBanOB);
                nhanVien = new NhanVien(maNhanVienOB, hoTen, ngaySinh, maCCCD, soDienThoai, email, matKhau, chucVu, ngayVaoLam, luongCoBan, gioiTinh, anhDaiDien, diaChi, phongBan);
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
        return nhanVien;
    }
    public NhanVien layMotNhanVienTheoTen(String ten) {
        PreparedStatement stm = null;
        NhanVien nhanVien = null;
        PhongBan_Dao phongBan_DAO = new PhongBan_Dao();
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String truyVan = "SELECT * FROM NhanVien where hoTen = ?";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, ten);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
            	String maNhanVienOB = rs.getString("maNhanVien");
                String hoTen = rs.getString("hoTen");
                Date ngaySinh = rs.getDate("ngaySinh");
                String maCCCD = rs.getString("maCCCD");
                String soDienThoai = rs.getString("soDienThoai");
                String email = rs.getString("email");
                String matKhau = rs.getString("matKhau");
                String chucVu = rs.getString("chucVu");
                Date ngayVaoLam = rs.getDate("ngayVaoLam");
                double luongCoBan = rs.getBigDecimal("luongCoBan").doubleValue();
                boolean gioiTinh = rs.getBoolean("gioiTinh");
                String anhDaiDien = rs.getString("anhDaiDien");
                String diaChi = rs.getString("diaChi");
                String maPhongBanOB = rs.getString("maPhongBan");
                PhongBan phongBan = phongBan_DAO.layMotPhongBanTheoMa(maPhongBanOB);
                nhanVien = new NhanVien(maNhanVienOB, hoTen, ngaySinh, maCCCD, soDienThoai, email, matKhau, chucVu, ngayVaoLam, luongCoBan, gioiTinh, anhDaiDien, diaChi, phongBan);
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
        return nhanVien;
    }

    public boolean themMotNhanVien(NhanVien nhanVien) {
        PreparedStatement stm = null;
        int soDongThemDuoc = 0;
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String query = "INSERT INTO NhanVien(maNhanVien, hoTen, ngaySinh"
                    + " ,maCCCD, soDienThoai, email, matKhau, chucVu"
                    + " , ngayVaoLam, luongCoBan, gioiTinh, anhDaiDien, diaChi, maPhongBan)"
                    + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            stm = con.prepareStatement(query);
            stm.setString(1, nhanVien.getMaNhanVien());
            stm.setString(2, nhanVien.getHoTen());
            stm.setDate(3, new java.sql.Date(nhanVien.getNgaySinh().getTime()));
            stm.setString(4, nhanVien.getMaCCCD());
            stm.setString(5, nhanVien.getSoDienThoai());
            stm.setString(6, nhanVien.getEmail());
            stm.setString(7, nhanVien.getMatKhau());
            stm.setString(8, nhanVien.getChucVu());
            stm.setDate(9, new java.sql.Date(nhanVien.getNgayVaoLam().getTime()));
            stm.setDouble(10,nhanVien.getLuongCoBan());
            stm.setBoolean(11, nhanVien.isGioiTinh());
            stm.setString(12, nhanVien.getAnhDaiDien());
            stm.setString(13, nhanVien.getDiaChi());
            stm.setString(14, nhanVien.getPhongBan().getMaPhongBan());
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
    public boolean suaThongTinMotNhanVien(NhanVien nhanVien) {
        PreparedStatement stm = null;
        int soDongDuocSua = 0;
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String query = "UPDATE NhanVien"
                    + " SET hoten = ?, ngaySinh = ?, maCCCD = ?"
                    + " , soDienThoai = ?, email = ?, matKhau = ?"
                    + " , chucVu = ?, ngayVaoLam = ?, luongCoBan = ?"
                    + " , gioiTinh = ?, anhDaiDien = ?"
                    + " , diaChi = ?, maPhongBan = ?"
                    + " where maNhanVien = ?";
            stm = con.prepareStatement(query);
            stm.setString(1, nhanVien.getHoTen());
            stm.setDate(2, new java.sql.Date(nhanVien.getNgaySinh().getTime()));
            stm.setString(3, nhanVien.getMaCCCD());
            stm.setString(4, nhanVien.getSoDienThoai());
            stm.setString(5, nhanVien.getEmail());
            stm.setString(6, nhanVien.getMatKhau());
            stm.setString(7, nhanVien.getChucVu());
            stm.setDate(8, new java.sql.Date(nhanVien.getNgayVaoLam().getTime()));
            stm.setDouble(9, nhanVien.getLuongCoBan());
            stm.setBoolean(10, nhanVien.isGioiTinh());
            stm.setString(11, nhanVien.getAnhDaiDien());
            stm.setString(12, nhanVien.getDiaChi());
            stm.setString(13, nhanVien.getPhongBan().getMaPhongBan());
            stm.setString(14, nhanVien.getMaNhanVien());
            soDongDuocSua = stm.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                stm.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return soDongDuocSua != 0;
    }
    public boolean xoaMotNhanVienTheoMa(String maNhanVien) {
        PreparedStatement stm = null;
        int soDongXoaDuoc = 0;
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String query = "DELETE FROM NhanVien where maNhanVien = ?";
            stm = con.prepareStatement(query);
            stm.setString(1, maNhanVien);
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
    
    public int laySoLuongNhanVienTheoPhongBan(String maPhongBan) {
        PreparedStatement stm = null;
        ResultSet rs = null;
        int soLuong = 0;

        try {
        	ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String truyVan = "SELECT COUNT(*) AS soLuongNhanVien FROM NhanVien WHERE maPhongBan = ?";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, maPhongBan);
            rs = stm.executeQuery();

            if (rs.next()) {
                soLuong = rs.getInt("soLuongNhanVien");
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
    
    public NhanVien layMotNhanVienTheoEmail(String em) {
        PreparedStatement stm = null;
        NhanVien nhanVien = null;
        PhongBan_Dao phongBan_DAO = new PhongBan_Dao();
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String truyVan = "SELECT * FROM NhanVien where email = ?";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, em);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
            	String maNhanVienOB = rs.getString("maNhanVien");
                String hoTen = rs.getString("hoTen");
                Date ngaySinh = rs.getDate("ngaySinh");
                String maCCCD = rs.getString("maCCCD");
                String soDienThoai = rs.getString("soDienThoai");
                String email = rs.getString("email");
                String matKhau = rs.getString("matKhau");
                String chucVu = rs.getString("chucVu");
                Date ngayVaoLam = rs.getDate("ngayVaoLam");
                double luongCoBan = rs.getBigDecimal("luongCoBan").doubleValue();
                boolean gioiTinh = rs.getBoolean("gioiTinh");
                String anhDaiDien = rs.getString("anhDaiDien");
                String diaChi = rs.getString("diaChi");
                String maPhongBanOB = rs.getString("maPhongBan");
                PhongBan phongBan = phongBan_DAO.layMotPhongBanTheoMa(maPhongBanOB);
                nhanVien = new NhanVien(maNhanVienOB, hoTen, ngaySinh, maCCCD, soDienThoai, email, matKhau, chucVu, ngayVaoLam, luongCoBan, gioiTinh, anhDaiDien, diaChi, phongBan);
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
        return nhanVien;
    }
}
