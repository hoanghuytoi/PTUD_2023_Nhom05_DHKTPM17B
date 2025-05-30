package Dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import ConnectionDB.ConnectDB;
import Entity.CongDoan;
import Entity.SanPham;

public class CongDoan_Dao {
	public CongDoan_Dao() {
    }

    public ArrayList<CongDoan> layDanhSachCongDoanTheoMaSP(String maSanPham) {
        PreparedStatement stm = null;
        ArrayList<CongDoan> dsCongDoan = new ArrayList<CongDoan>();
        SanPham_Dao sanPham_DAO = new SanPham_Dao();
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String query = "SELECT * FROM CongDoan WHERE maSanPham = ? order by thuTuCD ";
            stm = con.prepareStatement(query);
            stm.setString(1, maSanPham);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String maCongDoan = rs.getString("maCongDoan");
                int thuTuCongDoan = rs.getInt("thuTuCD");
                String tenCongDoan = rs.getString("tenCongDoan");
                int soLuongCan = rs.getInt("soLuongCan");
                String tinhTrang = rs.getString("tinhTrang");
                Date thoiHan = rs.getDate("thoiHan");
                String maSanPhamOb = rs.getString("maSanPham");
                double donGia = rs.getBigDecimal("donGia").doubleValue();
                SanPham sanPham = sanPham_DAO.layMotSanPhamTheoMa(maSanPhamOb);
                dsCongDoan.add(new CongDoan(maCongDoan, thuTuCongDoan, tenCongDoan, soLuongCan, tinhTrang, thoiHan, sanPham, donGia));
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
        return dsCongDoan;
    }

    public CongDoan layMotCongDoanTheoMaCongDoan(String maCongDoan) {
        PreparedStatement stm = null;
        CongDoan congDoan = null;
        SanPham_Dao sanPham_DAO = new SanPham_Dao();
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String query = "select * from CongDoan where maCongDoan = ?";
            stm = con.prepareStatement(query);
            stm.setString(1, maCongDoan);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String maCongDoanOb = rs.getString("maCongDoan");
                int thuTuCongDoan = rs.getInt("thuTuCD");
                String tenCongDoan = rs.getString("tenCongDoan");
                int soLuongCan = rs.getInt("soLuongCan");
                String tinhTrang = rs.getString("tinhTrang");
                Date thoiHan = rs.getDate("thoiHan");
                String maSanPhamOb = rs.getString("maSanPham");
                double donGia = rs.getBigDecimal("donGia").doubleValue();
                SanPham sanPham = sanPham_DAO.layMotSanPhamTheoMa(maSanPhamOb);
                congDoan = new CongDoan(maCongDoanOb, thuTuCongDoan, tenCongDoan, soLuongCan, tinhTrang, thoiHan, sanPham, donGia);
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
        return congDoan;
    }
    
    public String layRaMaCongDoanDeThem() {
        Statement stm = null;
        String maCongDoan = "";
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String truyVan = "select top 1 * from CongDoan order by len(maCongDoan), maCongDoan desc ";
            stm = con.createStatement();
            ResultSet rs = stm.executeQuery(truyVan);
            while (rs.next()) {
                maCongDoan = rs.getString("maCongDoan");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (maCongDoan == null || maCongDoan.equals("")) {
            return "PPCD100001";
        }
        String chuoiCanLay = maCongDoan.split("PPCD")[1];

        try {
            chuoiCanLay = "PPCD" + (Integer.parseInt(chuoiCanLay) + 1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return chuoiCanLay;
    }
    
    public boolean themMotCongDoan(CongDoan congDoan) {
        PreparedStatement stm = null;
        int soDongThemDuoc = 0;

        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String truyVan = "INSERT INTO CongDoan(maCongDoan, thuTuCD, tenCongDoan, soLuongCan, tinhTrang, "
                    + " thoiHan, maSanPham, donGia)"
                    + " VALUES(?, ?, ?, ?, ? ,? ,?, ?)";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, congDoan.getMaCongDoan());
            stm.setInt(2, congDoan.getThuTuCongDoan());
            stm.setString(3, congDoan.getTenCongDoan());
            stm.setInt(4, congDoan.getSoLuongCan());
            stm.setString(5, congDoan.getTinhTrang());
            stm.setDate(6, new java.sql.Date(congDoan.getThoiHan().getTime()));
            stm.setString(7, congDoan.getSanPham().getMaSanPham());
            stm.setBigDecimal(8, new BigDecimal(congDoan.getDonGia()));
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

    public boolean suaMotCongDoan(CongDoan congDoan) {
        PreparedStatement stm = null;
        int soDongDuocSua = 0;
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String query = "update CongDoan "
                    + " set tenCongDoan = ?, thuTuCD = ?, soLuongCan = ?, tinhTrang = ?"
                    + " , thoiHan = ?, maSanPham = ?, donGia = ?"
                    + " where maCongDoan = ? ";
            stm = con.prepareStatement(query);
            stm.setString(1, congDoan.getTenCongDoan());
            stm.setInt(2, congDoan.getThuTuCongDoan());
            stm.setInt(3, congDoan.getSoLuongCan());
            stm.setString(4, congDoan.getTinhTrang());
            stm.setDate(5, new java.sql.Date(congDoan.getThoiHan().getTime()));
            stm.setString(6, congDoan.getSanPham().getMaSanPham());
            stm.setBigDecimal(7, new BigDecimal(congDoan.getDonGia()));
            stm.setString(8, congDoan.getMaCongDoan());
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

    public boolean xoaMotCongDoanTheoMa(String maCongDoan) {
        PreparedStatement stm = null;
        int soDongXoaDuoc = 0;
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String query = " DELETE FROM CongDoan"
                    + " WHERE maCongDoan = ?";
            stm = con.prepareStatement(query);
            stm.setString(1, maCongDoan);
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
    
    public int laySoLuongLamDuocTheoMaCongDoan(String maCongDoan) {
        PreparedStatement stm = null;
        int soLuongLamDuoc = 0;
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String truyVan = "select soLuongCan, sum(soLuongLam) as tongSoLuongLam from CongDoan CD join PhanCongCongNhan PCCN on CD.maCongDoan = PCCN.maCongDoan"
                    + " join BangChamCongCongNhan CCCN on CCCN.maPhanCong = PCCN.maPhanCong where CD.maCongDoan = ? group by CD.maCongDoan, CD.soLuongCan";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, maCongDoan);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                soLuongLamDuoc = rs.getInt("tongSoLuongLam");
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
        return soLuongLamDuoc;
    }
    
    public boolean updateThoiHan(String maCongDoan, String tienDo) {
        PreparedStatement stm = null;
        int soLuongUpdate = 0;
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String truyVan = "update CongDoan set tinhTrang = ? where maCongDoan = ?";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, tienDo);
            stm.setString(2, maCongDoan);
            soLuongUpdate = stm.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                stm.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return soLuongUpdate != 0;
    }
    
    public float layMucDoHoanThanhCuaMotCongDoan(String maCongDoan) {

        PreparedStatement stm = null;
        float mucDoHoanThanh = 0;
        int soLuongCan = 0, soLuongLam = 0;
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String truyVan = "select soLuongCan, sum(soLuongLam) as tongSoLuongLam from CongDoan CD join PhanCongCongNhan PCCN on CD.maCongDoan = PCCN.maCongDoan"
                    + " join BangChamCongCongNhan CCCN on CCCN.maPhanCong = PCCN.maPhanCong where CD.maCongDoan = ? group by CD.maCongDoan, CD.soLuongCan";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, maCongDoan);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                soLuongCan = rs.getInt("soLuongCan");
                soLuongLam = rs.getInt("tongSoLuongLam");
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
        mucDoHoanThanh = (soLuongLam / (float) soLuongCan) * 100;
        if (mucDoHoanThanh > 100) {
            mucDoHoanThanh = 100;
        }
        if (Double.isNaN(mucDoHoanThanh)) {
            updateThoiHan(maCongDoan, "0%");
            return 0;
        }
        updateThoiHan(maCongDoan, String.format("%.2f", mucDoHoanThanh) + "%");
        return mucDoHoanThanh;
    }
    public void updateTinhTrangHoanThanhCuaCacCongDoan(){
        Statement stm = null;
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String truyVan = "SELECT * FROM CongDoan";
            stm = con.createStatement();
            ResultSet rs = stm.executeQuery(truyVan);
            while (rs.next()){
                String maCongDoan = rs.getString("maCongDoan");
                layMucDoHoanThanhCuaMotCongDoan(maCongDoan);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally{
            try {
                stm.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public ArrayList<CongDoan> layRaThuTuLamLonNhatCuaMotSanPham(String maSanPham){
        ArrayList<CongDoan> dsCongDoan = new ArrayList<CongDoan>();
        PreparedStatement stm = null;
        SanPham_Dao sanPham_DAO = new SanPham_Dao();
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String truyVan = "select top 1 WITH TIES * from CongDoan where maSanPham = ? order by thuTuCD desc";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, maSanPham);
            
            ResultSet rs = stm.executeQuery();
            while (rs.next()){
                String maCongDoanOb = rs.getString("maCongDoan");
                int thuTuCongDoan = rs.getInt("thuTuCD");
                String tenCongDoan = rs.getString("tenCongDoan");
                int soLuongCan = rs.getInt("soLuongCan");
                String tinhTrang = rs.getString("tinhTrang");
                Date thoiHan = rs.getDate("thoiHan");
                String maSanPhamOb = rs.getString("maSanPham");
                double donGia = rs.getBigDecimal("donGia").doubleValue();
                SanPham sanPham = sanPham_DAO.layMotSanPhamTheoMa(maSanPhamOb);
                dsCongDoan.add(new CongDoan(maCongDoanOb, thuTuCongDoan, tenCongDoan, soLuongCan, tinhTrang, thoiHan, sanPham, donGia));
            }
        }  catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                stm.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return dsCongDoan;
    }
    public ArrayList<CongDoan> layDanhSachCongDoanTheoThuTuSanPham(String maSanPham, int thuTu){
        ArrayList<CongDoan> dsCongDoan = new ArrayList<CongDoan>();
        PreparedStatement stm = null;
        SanPham_Dao sanPham_DAO = new SanPham_Dao();
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String truyVan = "select * from CongDoan where maSanPham = ? and thuTuCD = ?";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, maSanPham);
            stm.setInt(2, thuTu);
            ResultSet rs = stm.executeQuery();
            while (rs.next()){
                String maCongDoanOb = rs.getString("maCongDoan");
                int thuTuCongDoan = rs.getInt("thuTuCD");
                String tenCongDoan = rs.getString("tenCongDoan");
                int soLuongCan = rs.getInt("soLuongCan");
                String tinhTrang = rs.getString("tinhTrang");
                Date thoiHan = rs.getDate("thoiHan");
                String maSanPhamOb = rs.getString("maSanPham");
                double donGia = rs.getBigDecimal("donGia").doubleValue();
                SanPham sanPham = sanPham_DAO.layMotSanPhamTheoMa(maSanPhamOb);
                dsCongDoan.add(new CongDoan(maCongDoanOb, thuTuCongDoan, tenCongDoan, soLuongCan, tinhTrang, thoiHan, sanPham, donGia));
            }
        }  catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                stm.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return dsCongDoan;
    }
    
    public int laySoLuongCongDoanTheoMaSP(String maSP) {
        PreparedStatement stm = null;
        ResultSet rs = null;
        int soLuong = 0;

        try {
        	ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String truyVan = "SELECT COUNT(*) AS soLuongCongDoan FROM CongDoan WHERE maSanPham = ?";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, maSP);
            rs = stm.executeQuery();

            if (rs.next()) {
                soLuong = rs.getInt("soLuongCongDoan");
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
    
    public ArrayList<CongDoan> layDanhSachCongDoanDuocPhanCongTheoMaSpMaTN(String maToNhom, String maSanPham) {
        PreparedStatement stm = null;
        ArrayList<CongDoan> dsCongDoan = new ArrayList<CongDoan>();
        String tinhTrangHoanThanh = "100,00%";
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String truyVan = "SELECT CD.maCongDoan FROM ToNhom TN JOIN PhanCongCongNhan PCCN ON TN.maToNhom = PCCN.maToNhom"
                    + " JOIN CongDoan CD ON PCCN.maCongDoan = CD.maCongDoan"
                    + " JOIN SanPham SP ON SP.maSanPham = CD.maSanPham WHERE TN.maToNhom = ? and SP.maSanPham = ? and CD.tinhTrang != ?"
                    + " group by CD.maCongDoan";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, maToNhom);
            stm.setString(2, maSanPham);
            stm.setString(3, tinhTrangHoanThanh);
            ResultSet rs = stm.executeQuery();
            while (rs.next()){
                String maCongDoan = rs.getString("maCongDoan");
                dsCongDoan.add(layMotCongDoanTheoMaCongDoan(maCongDoan));
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
        return dsCongDoan;
    }
}
