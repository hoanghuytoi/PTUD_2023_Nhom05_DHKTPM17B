package Dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import Entity.BangLuongNhanVien;
import Entity.NhanVien;

public class BangLuongNhanVien_Dao {
	public BangLuongNhanVien_Dao() {
    }

    public ArrayList<BangLuongNhanVien> danhSachBangLuong() {
        Statement stm = null;
        ArrayList<BangLuongNhanVien> dsBangLuong = new ArrayList<BangLuongNhanVien>();
        NhanVien_Dao nhanVien_DAO = new NhanVien_Dao();
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String truyVan = "select * from BangLuongNhanVien";
            stm = con.createStatement();
            ResultSet rs = stm.executeQuery(truyVan);
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
                dsBangLuong.add(new BangLuongNhanVien(maBangLuong, nhanVien, soNgayDiLam, soNgayNghi, soPhepNghi, ngayTinh, luongTheoThang,luongTangCa,phuCap, thucLanh, donViTien));
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
        return dsBangLuong;
    }
    
    public BangLuongNhanVien layDanhSachBangLuongTheoMaBangLuong(String maBangLuong) {
        PreparedStatement stm = null;
        BangLuongNhanVien dsBangLuong = null;
        NhanVien_Dao nhanVien_DAO = new NhanVien_Dao();
        try {
        	ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String truyVan = "SELECT * FROM BangLuongNhanVien WHERE maBangLuong = ?";
            stm = con.prepareCall(truyVan);
            stm.setString(1, maBangLuong);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String maBangLuongtmp = rs.getString("maBangLuong");
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
                dsBangLuong = new BangLuongNhanVien(maBangLuongtmp, nhanVien, soNgayDiLam, soNgayNghi, soPhepNghi, ngayTinh, luongTheoThang,luongTangCa,phuCap, thucLanh, donViTien);
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
        return dsBangLuong;
    }
    
    public ArrayList<BangLuongNhanVien> danhSachBangLuongTheoThangNam(String thang, String nam) {
        PreparedStatement stm = null;
        ArrayList<BangLuongNhanVien> dsBangLuong = new ArrayList<>();
        NhanVien_Dao nhanVien_DAO = new NhanVien_Dao();

        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String truyVan = "SELECT * FROM BangLuongNhanVien WHERE MONTH(ngayTinh) = ? AND YEAR(ngayTinh) = ?";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, thang);
            stm.setString(2, nam);

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
                dsBangLuong.add(new BangLuongNhanVien(maBangLuong, nhanVien, soNgayDiLam, soNgayNghi, soPhepNghi, ngayTinh, luongTheoThang,luongTangCa,phuCap, thucLanh, donViTien));
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
    
    public ArrayList<BangLuongNhanVien> layDanhSachBangLuongTheoMaNhanVien(String maNhanVien) {
        PreparedStatement stm = null;
        ArrayList<BangLuongNhanVien> dsBangLuong = new ArrayList<BangLuongNhanVien>();
        NhanVien_Dao nhanVien_DAO = new NhanVien_Dao();
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String truyVan = "select * from BangLuongNhanVien where maNhanVien = ?";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, maNhanVien);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
            	String maBangLuong = rs.getString("maBangLuong");
                String maNhanVienOB = rs.getString("maNhanVien");
                int soNgayDiLam = rs.getInt("soNgayDiLam");
                int soNgayNghi = rs.getInt("soNgayNghi");
                int soPhepNghi = rs.getInt("soPhepNghi");
                Date ngayTinh = rs.getDate("ngayTinh");
                String luongTheoThang = rs.getString("luongTheoThang");
                double luongTangCa = rs.getBigDecimal("luongTangCa").doubleValue();
                double phuCap = rs.getBigDecimal("phuCap").doubleValue();
                double thucLanh = rs.getBigDecimal("thucLanh").doubleValue();
                String donViTien = rs.getString("donViTien");
                NhanVien nhanVien = nhanVien_DAO.layMotNhanVienTheoMaNhanVien(maNhanVienOB);
                dsBangLuong.add(new BangLuongNhanVien(maBangLuong, nhanVien, soNgayDiLam, soNgayNghi, soPhepNghi, ngayTinh, luongTheoThang,luongTangCa,phuCap, thucLanh, donViTien));
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
        return dsBangLuong;
    }
    
    public boolean xoaBangLuongInsert(String thang, String nam) {
        System.out.println("Xoa");
        PreparedStatement stm = null;
        int soDongxoa=0;
            try {
                ConnectionDB.ConnectDB.getInstance();
                Connection con = ConnectionDB.ConnectDB.getConnection();
                String truyVan = "delete from BangLuongNhanVien where luongTheoThang= ? ";
                stm = con.prepareStatement(truyVan);
                stm.setString(1, thang+"-"+nam);
                soDongxoa=stm.executeUpdate();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                try {
                    stm.close();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        return soDongxoa != 0;
    }

    public boolean themMotBangLuong(BangLuongNhanVien bangLuongNhanVien, String thang, String nam) {
        PreparedStatement stm = null;
        int soDongThemDuoc = 0;
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            xoaBangLuongInsert(thang, nam);
            String truyVan = "INSERT INTO BangLuongNhanVien(maBangLuong, maNhanVien"
                    + " , soNgayDiLam, soNgayNghi, soPhepNghi, ngayTinh, luongTheoThang"
                    + " luongTangCa, phuCap, thucLanh, donViTien)"
                    + " values (? , ? , ? , ? , ? , ?, ?, ? , ?, ?, ?)";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, bangLuongNhanVien.getMaBangLuong());
            stm.setString(2, bangLuongNhanVien.getNhanVien().getMaNhanVien());
            stm.setInt(3, bangLuongNhanVien.getSoNgayDiLam());
            stm.setInt(4, bangLuongNhanVien.getSoNgayNghi());
            stm.setInt(5, bangLuongNhanVien.getSoPhepNghi());
            stm.setDate(6, new java.sql.Date(bangLuongNhanVien.getNgayTinh().getTime()));
            stm.setBigDecimal(7, new BigDecimal(bangLuongNhanVien.getLuongTangCa()));
            stm.setBigDecimal(8, new BigDecimal(bangLuongNhanVien.getPhuCap()));
            stm.setBigDecimal(9, new BigDecimal(bangLuongNhanVien.getThucLanh()));
            stm.setString(10, bangLuongNhanVien.getDonViTien());
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
    
    public int laySoNgayDilamCuaNhanVien(String maNhanVien, int thang, int nam) {
        PreparedStatement stm = null;
        int soDongSuaDuoc = 0;
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String truyVan = "select DISTINCT ngayChamCong from BangChamCongNhanVien where maNhanVien = ? \n"
                    + "and DAY(ngayChamCong) >=1 and DAY(ngayChamCong) <=31 and \n"
                    + "month(ngayChamCong) = ? and YEAR(ngayChamCong) = ? and trangThaiDiLam not like N'%Nghỉ%'";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, maNhanVien);
            stm.setInt(2, thang);
            stm.setInt(3, nam);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                soDongSuaDuoc++;
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
        return soDongSuaDuoc;
    }

    public int laySoNgayNghiCoPhepCuaNhanVien(String maNhanVien, int thang, int nam) {
        PreparedStatement stm = null;
        int soNgayNghiCoPhep = 0;
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String truyVan = "select DISTINCT ngayChamCong from BangChamCongNhanVien where maNhanVien = ? \n"
                    + "and DAY(ngayChamCong) >=1 and DAY(ngayChamCong) <=31 and \n"
                    + "month(ngayChamCong) =? and YEAR(ngayChamCong) =? and trangThaiDiLam  like N'%Nghỉ Có%'";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, maNhanVien);
            stm.setInt(2, thang);
            stm.setInt(3, nam);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                soNgayNghiCoPhep++;
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
        return soNgayNghiCoPhep;
    }
    
    public boolean themMotBangLuongString(String maLuong, String maNhanVien, int soNgayLam, int soNgayNghi, int soPhepNghi, Date ngayTinh, 
            String luongTheoThang, double luongTangCa, double phuCap, double thucLanh, String donViTien) {
        PreparedStatement stm = null;
        int soDongThemDuoc = 0;
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String truyVan = "INSERT INTO BangLuongNhanVien values ( ? , ? , ? , ? , ?, ? , ? , ? , ? , ? , ?)";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, maLuong);
            stm.setString(2, maNhanVien);
            stm.setInt(3, soNgayLam);
            stm.setInt(4, soNgayNghi);
            stm.setInt(5, soPhepNghi);
            stm.setDate(6, new java.sql.Date(ngayTinh.getTime()));
            stm.setString(7, luongTheoThang);
            stm.setDouble(8, luongTangCa);
            stm.setDouble(9, phuCap);
            stm.setDouble(10, thucLanh);
            stm.setString(11, donViTien);
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
}
