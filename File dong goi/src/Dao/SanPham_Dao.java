package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import ConnectionDB.ConnectDB;
import Entity.HopDong;
import Entity.SanPham;

public class SanPham_Dao {
	public SanPham_Dao() {
    }

    public ArrayList<SanPham> layDanhSachSanPham() {
        ArrayList<SanPham> dsSanPham = new ArrayList<SanPham>();
        Statement stm = null;
        HopDong_Dao hopDong_DAO = new HopDong_Dao();
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String truyVan = "SELECT * FROM SanPham";
            stm = con.createStatement();
            ResultSet rs = stm.executeQuery(truyVan);
            while (rs.next()) {
                String maSanPham = rs.getString("maSanPham");
                String maHopDong = rs.getString("maHopDong");
                String tenSanPham = rs.getString("tenSanPham");
                int soLuongSanPham = rs.getInt("soLuongSanPham");
                double donGia = rs.getBigDecimal("donGia").doubleValue();
                String chatLieu = rs.getString("chatLieu");
                int kichThuoc = rs.getInt("kichThuoc");
                String anhSanPham = rs.getString("anhSanPham");
                int soLuongCongDoan = rs.getInt("soLuongCongDoan");
                HopDong hopDong = hopDong_DAO.layRaMotHopDongTheoMaHopDong(maHopDong);
                dsSanPham.add(new SanPham(maSanPham, tenSanPham,soLuongSanPham, donGia, chatLieu, kichThuoc, anhSanPham, soLuongCongDoan, hopDong));
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
        return dsSanPham;
    }
    
    public ArrayList<SanPham> layDanhSachSanPhamTheoMaHopDong(String maHopDongTemp) {
        PreparedStatement stm = null;
        ArrayList<SanPham> dsSanPham = new ArrayList<SanPham>();
        HopDong_Dao hopDong_DAO = new HopDong_Dao();
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String truyVan = "select * from SanPham where maHopDong = ?";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, maHopDongTemp);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
            	String maSanPham = rs.getString("maSanPham");
                String maHopDong = rs.getString("maHopDong");
                String tenSanPham = rs.getString("tenSanPham");
                int soLuongSanPham = rs.getInt("soLuongSanPham");
                double donGia = rs.getBigDecimal("donGia").doubleValue();
                String chatLieu = rs.getString("chatLieu");
                int kichThuoc = rs.getInt("kichThuoc");
                String anhSanPham = rs.getString("anhSanPham");
                int soLuongCongDoan = rs.getInt("soLuongCongDoan");
                HopDong hopDong = hopDong_DAO.layRaMotHopDongTheoMaHopDong(maHopDong);
                dsSanPham.add(new SanPham(maSanPham, tenSanPham,soLuongSanPham, donGia, chatLieu, kichThuoc, anhSanPham, soLuongCongDoan, hopDong));
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
        return dsSanPham;
    }

    public SanPham layMotSanPhamTheoMa(String maSanPham) {
        PreparedStatement stm = null;
        SanPham sanPham = null;
        HopDong_Dao hopDong_DAO = new HopDong_Dao();
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String truyVan = "select * from SanPham where maSanPham = ?";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, maSanPham);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String maSanPhamOB = rs.getString("maSanPham");
                String maHopDong = rs.getString("maHopDong");
                String tenSanPham = rs.getString("tenSanPham");
                int soLuongSanPham = rs.getInt("soLuongSanPham");
                double donGia = rs.getBigDecimal("donGia").doubleValue();
                String chatLieu = rs.getString("chatLieu");
                int kichThuoc = rs.getInt("kichThuoc");
                String anhSanPham = rs.getString("anhSanPham");
                int soLuongCongDoan = rs.getInt("soLuongCongDoan");
                HopDong hopDong = hopDong_DAO.layRaMotHopDongTheoMaHopDong(maHopDong);
                sanPham = new SanPham(maSanPhamOB, tenSanPham, soLuongSanPham, donGia, chatLieu, kichThuoc, anhSanPham, soLuongCongDoan, hopDong);
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
        return sanPham;
    }
    
    public boolean themMotSanPham(SanPham sanPham) {
        PreparedStatement stm = null;
        int soDongThemDuoc = 0;
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String truyVan = "INSERT INTO SanPham(maSanPham, tenSanPham, soLuongSanPham"
                    + "	, donGia, chatLieu, kichThuoc, anhSanPham,maHopDong)"
                    + "  VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, sanPham.getMaSanPham());
            stm.setString(2, sanPham.getTenSanPham());
            stm.setInt(3, sanPham.getSoLuongSanPham());
            stm.setDouble(4, sanPham.getDonGia());
            stm.setString(5, sanPham.getChatLieu());
            stm.setInt(6, sanPham.getKichThuoc());
            stm.setString(7, sanPham.getAnhSanPham());
            stm.setString(8, sanPham.getHopDong().getMaHopDong());
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
    
    public String layMaSanPhamDeThem() {
        Statement stm = null;
        String maSanPham = "";
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String truyVan = "select top 1 * from SanPham order by len(maSanPham), maSanPham desc ";
            stm = con.createStatement();
            ResultSet rs = stm.executeQuery(truyVan);
            while (rs.next()) {
                maSanPham = rs.getString("maSanPham");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (maSanPham == null || maSanPham.equals("")) {
            return "PPSP100001";
        }
        String chuoiCanLay = maSanPham.split("PPSP")[1];

        try {
            chuoiCanLay = "PPSP" + (Integer.parseInt(chuoiCanLay) + 1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return chuoiCanLay;
    }

    public boolean suaMotSanPham(SanPham sanPham) {
        PreparedStatement stm = null;
        int soLuongSuaDuoc = 0;
        System.out.println(sanPham.toString());
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String truyVan = "UPDATE SanPham"
                    + " set tenSanPham = ?, soLuongSanPham = ?,"
                    + " donGia = ?, chatLieu = ?, kichThuoc = ?,"
                    + " anhSanPham = ? ,maHopDong = ?"
                    + " where maSanPham = ?";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, sanPham.getTenSanPham());
            stm.setInt(2, sanPham.getSoLuongSanPham());
            stm.setDouble(3, sanPham.getDonGia());
            stm.setString(4, sanPham.getChatLieu());
            stm.setInt(5, sanPham.getKichThuoc());
            stm.setString(6, sanPham.getAnhSanPham());
            stm.setString(7, sanPham.getHopDong().getMaHopDong());
            stm.setString(8, sanPham.getMaSanPham());

            soLuongSuaDuoc = stm.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                stm.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return soLuongSuaDuoc != 0;
    }

    public boolean xoaMotSanPhamTheoMa(String maSanPham) {
        PreparedStatement stm = null;
        int soLuongXoaDuoc = 0;
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String truyVan = "DELETE FROM SanPham WHERE maSanPham = ?";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, maSanPham);
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
    
    public ArrayList<SanPham> layDanhSachSanPhamDuocPhanCongChoTo(String maToNhom) {
        PreparedStatement stm = null;
        ArrayList<SanPham> dsSanPham = new ArrayList<SanPham>();

        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String truyVan = "SELECT SP.maSanPham FROM ToNhom TN JOIN PhanCongCongNhan PCCN ON TN.maToNhom = PCCN.maToNhom"
                    + " JOIN CongDoan CD ON PCCN.maCongDoan = CD.maCongDoan"
                    + " JOIN SanPham SP ON SP.maSanPham = CD.maSanPham WHERE TN.maToNhom = ? and "
                    + " SP.maSanPham in "
                    + " (select SP2.maSanPham from SanPham SP2 JOIN CongDoan CD2 ON SP2.maSanPham = CD2.maSanPham"
                    + " join PhanCongCongNhan PCCC2 ON CD2.maCongDoan = PCCC2.maCongDoan"
                    + " where tinhTrang != '100,00%')"
                    + " group by SP.maSanPham";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, maToNhom);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String maSanPham = rs.getString("maSanPham");

                dsSanPham.add(layMotSanPhamTheoMa(maSanPham));
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
        return dsSanPham;
    }
}
