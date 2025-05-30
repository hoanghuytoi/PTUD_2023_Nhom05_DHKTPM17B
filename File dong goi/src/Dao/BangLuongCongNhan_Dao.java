package Dao;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import ConnectionDB.ConnectDB;
import Entity.BangLuongCongNhan;
import Entity.CongNhan;

public class BangLuongCongNhan_Dao {
	public BangLuongCongNhan_Dao() {
	}

	public ArrayList<BangLuongCongNhan> layDanhSachBangLuongCongNhan() {
		Statement stm = null;
		CongNhan_Dao congNhan_DAO = new CongNhan_Dao();
		ArrayList<BangLuongCongNhan> dsBangLuongCongNhan = new ArrayList<BangLuongCongNhan>();
		try {
			ConnectionDB.ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String truyVan = "SELECT * FROM BangLuongCongNhan";
			stm = con.createStatement();
			ResultSet rs = stm.executeQuery(truyVan);
			while (rs.next()) {
				String maBangLuong = rs.getString("maBangLuong");
				String maCongNhan = rs.getString("maCongNhan");
				Date ngayTinh = rs.getDate("ngayTinh");
				int soLuongSanPhamLam = rs.getInt("soLuongSanPhamLam");
				int soNgayDiLam = rs.getInt("soNgayDiLam");
				int soNgayNghi = rs.getInt("soNgayNghi");
				int soPhepNghi = rs.getInt("soPhepNghi");
				double tongLuong = rs.getBigDecimal("tongLuong").doubleValue();
				String donViTien = rs.getString("donViTien");
				String luongTheoThang = rs.getString("luongTheoThang");
				CongNhan congNhan = congNhan_DAO.layMotCongNhanTheoMa(maCongNhan);
				dsBangLuongCongNhan.add(new BangLuongCongNhan(maBangLuong, congNhan,
						soLuongSanPhamLam, soNgayDiLam, soNgayNghi, soPhepNghi, ngayTinh, tongLuong, donViTien, luongTheoThang));
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
		return dsBangLuongCongNhan;
	}

	public BangLuongCongNhan layDanhSachBangLuongCNTheoMaBangLuong(String maBangLuong) {
		PreparedStatement stm = null;
		BangLuongCongNhan dsBangLuong = null;
		CongNhan_Dao congNhan_DAO = new CongNhan_Dao();
		try {
			ConnectionDB.ConnectDB.getInstance();
			Connection con = ConnectionDB.ConnectDB.getConnection();
			String truyVan = "SELECT * FROM BangLuongCongNhan WHERE maBangLuong = ?";
			stm = con.prepareCall(truyVan);
			stm.setString(1, maBangLuong);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				String maBangLuongtmp = rs.getString("maBangLuong");
				String maCongNhan = rs.getString("maCongNhan");
				Date ngayTinh = rs.getDate("ngayTinh");
				int soLuongSanPhamLam = rs.getInt("soLuongSanPhamLam");
				int soNgayDiLam = rs.getInt("soNgayDiLam");
				int soNgayNghi = rs.getInt("soNgayNghi");
				int soPhepNghi = rs.getInt("soPhepNghi");
				double tongLuong = rs.getBigDecimal("tongLuong").doubleValue();
				String donViTien = rs.getString("donViTien");
				String luongTheoThang = rs.getString("luongTheoThang");
				CongNhan congNhan = congNhan_DAO.layMotCongNhanTheoMa(maCongNhan);
				dsBangLuong = new BangLuongCongNhan(maBangLuongtmp, congNhan,soLuongSanPhamLam, soNgayDiLam, soNgayNghi, soPhepNghi, ngayTinh, tongLuong, donViTien, luongTheoThang);
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

	public ArrayList<BangLuongCongNhan> layDanhSachBangLuongTheoMaCongNhan(String maCongNhan) {
		ArrayList<BangLuongCongNhan> dsBangLuong = new ArrayList<BangLuongCongNhan>();
		PreparedStatement stm = null;
		CongNhan_Dao congNhan_DAO = new CongNhan_Dao();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String truyVan = "SELECT * FROM BangLuongCongNhan where maCongNhan = ?";
			stm = con.prepareStatement(truyVan);
			stm.setString(1, maCongNhan);
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
			String truyVan = "delete from BangLuongCongNhan where luongTheoThang= ? ";
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

	public boolean themMotBangLuong(BangLuongCongNhan bangLuongCongNhan) {
		PreparedStatement stm = null;
		int soDongThemDuoc = 0;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String truyVan = "INSERT INTO BangLuongCongNhan(maBangLuong, maCongNhan, ngayTinh"
					+ ", soLuongSanPhamLam, soNgayDiLam, soNgayNghi, soPhepNghi, tongLuong, donViTien, luongTheoThang)"
					+ " VALUES (?, ?, ?, ?, ?, ? ,? ,? ,?, ?)";
			stm = con.prepareStatement(truyVan);
			stm.setString(1, bangLuongCongNhan.getMaBangLuong());
			stm.setString(2, bangLuongCongNhan.getCongNhan().getMaCongNhan());
			stm.setDate(3, new java.sql.Date(bangLuongCongNhan.getNgayTinh().getTime()));
			stm.setInt(4, bangLuongCongNhan.getSoLuongSanPhamLam());
			stm.setInt(5, bangLuongCongNhan.getSoNgayDiLam());
			stm.setInt(6, bangLuongCongNhan.getSoNgayNghi());
			stm.setInt(7, bangLuongCongNhan.getSoPhepNghi());
			stm.setBigDecimal(8, new BigDecimal(bangLuongCongNhan.getTongLuong()));
			stm.setString(9, bangLuongCongNhan.getDonViTien());
			stm.setString(10, bangLuongCongNhan.getLuongTheoThang());
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

	public double layRaTongTienTheoMaCongNhan(String maCongNhan, int thang, int nam) {
		PreparedStatement stm = null;
		double tongTien = 0;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String truyVan = "select CN.maCongNhan, sum(BCCCN.soLuongLam*CD.donGia) as tongTienLuong from CongNhan CN join PhanCongCongNhan PCCN on CN.maCongNhan = PCCN.maCongNhan"
					+ " JOIN BangChamCongCongNhan BCCCN ON BCCCN.maPhanCong = PCCN.maPhanCong"
					+ " JOIN CongDoan CD on CD.maCongDoan = PCCN.maCongDoan"
					+ " where CN.maCongNhan = ? and  MONTH(ngayChamCong) = ? and year(ngayChamCong) = ?"
					+ " GROUP BY CN.maCongNhan";
			stm = con.prepareStatement(truyVan);
			stm.setString(1, maCongNhan);
			stm.setInt(2, thang);
			stm.setInt(3, nam);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				tongTien = rs.getDouble("tongTienLuong");
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
		return tongTien;
	}

	public int laySoNgayDiLam(String maCongNhan, int thang, int nam) {
		PreparedStatement stm = null;
		int soNgay = 0;
		String diLam = "Đi làm";
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String truyVan = "select CN.maCongNhan, count(trangThaiDiLam) as soNgayDiLam from CongNhan CN join PhanCongCongNhan PCCN on CN.maCongNhan = PCCN.maCongNhan"
					+ " join BangChamCongCongNhan CCCN on PCCN.maPhanCong = CCCN.maPhanCong"
					+ " JOIN CongDoan CD on CD.maCongDoan = PCCN.maCongDoan "
					+ " where CN.maCongNhan = ? and trangThaiDiLam = ?"
					+ " and  MONTH(ngayChamCong) = ? and year(ngayChamCong) = ?"
					+ " GROUP BY CN.maCongNhan";
			stm = con.prepareStatement(truyVan);
			stm.setString(1, maCongNhan);
			stm.setString(2, diLam);
			stm.setInt(3, thang);
			stm.setInt(4, nam);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				soNgay = rs.getInt("soNgayDiLam");
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
		return soNgay;
	}

	public int layRaSoNgayNghiCoPhep(String maCongNhan, int thang, int nam) {
		PreparedStatement stm = null;
		int soNgay = 0;
		String nghiCoPhep = "Nghỉ có phép";

		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String truyVan = "select CN.maCongNhan, count(trangThaiDiLam) as soNgayPhep from CongNhan CN join PhanCongCongNhan PCCN on CN.maCongNhan = PCCN.maCongNhan"
					+ " join BangChamCongCongNhan CCCN on PCCN.maPhanCong = CCCN.maPhanCong"
					+ " JOIN CongDoan CD on CD.maCongDoan = PCCN.maCongDoan "
					+ " where CN.maCongNhan = ? and trangThaiDiLam = ? and  MONTH(ngayChamCong) = ? and year(ngayChamCong) = ?"
					+ " GROUP BY CN.maCongNhan";
			stm = con.prepareStatement(truyVan);
			stm.setString(1, maCongNhan);
			stm.setString(2, nghiCoPhep);
			stm.setInt(3, thang);
			stm.setInt(4, nam);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				soNgay = rs.getInt("soNgayPhep");
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
		return soNgay;
	}

	public int layRaSoNgayNghiKhongPhep(String maCongNhan, int thang, int nam) {
		PreparedStatement stm = null;
		int soNgay = 0;
		String nghiKoCoPhep = "Nghỉ không phép";

		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String truyVan = "select CN.maCongNhan, count(trangThaiDiLam) as soNgayKhongPhep from CongNhan CN join PhanCongCongNhan PCCN on CN.maCongNhan = PCCN.maCongNhan"
					+ " join BangChamCongCongNhan CCCN on PCCN.maPhanCong = CCCN.maPhanCong"
					+ " JOIN CongDoan CD on CD.maCongDoan = PCCN.maCongDoan "
					+ " where CN.maCongNhan = ? and trangThaiDiLam = ? and  MONTH(ngayChamCong) = ? and year(ngayChamCong) = ?"
					+ " GROUP BY CN.maCongNhan";
			stm = con.prepareStatement(truyVan);
			stm.setString(1, maCongNhan);
			stm.setString(2, nghiKoCoPhep);
			stm.setInt(3, thang);
			stm.setInt(4, nam);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				soNgay = rs.getInt("soNgayKhongPhep");
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
		return soNgay;
	}

	public int layRaTongSoLuongSPLam(String maCongNhan, int thang, int nam) {
		PreparedStatement stm = null;
		int tongSoLuong = 0;

		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String truyVan = "select CN.maCongNhan, sum(soLuongLam) as tongSoLuongLam from CongNhan CN join PhanCongCongNhan PCCN on CN.maCongNhan = PCCN.maCongNhan"
					+ " join BangChamCongCongNhan CCCN on PCCN.maPhanCong = CCCN.maPhanCong"
					+ " JOIN CongDoan CD on CD.maCongDoan = PCCN.maCongDoan "
					+ " where CN.maCongNhan = ? and  MONTH(ngayChamCong) = ? and year(ngayChamCong) = ?"
					+ " GROUP BY CN.maCongNhan";
			stm = con.prepareStatement(truyVan);
			stm.setString(1, maCongNhan);
			stm.setInt(2, thang);
			stm.setInt(3, nam);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				tongSoLuong = rs.getInt("tongSoLuongLam");
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
		return tongSoLuong;
	}

	public boolean tinhLuongCongNhan(int thang, int nam) {
	    xoaDiNhungThangDaTinh(thang, nam);
	    
		CongNhan_Dao congNhan_DAO = new CongNhan_Dao();
		ArrayList<CongNhan> dsCongNhanDiLamTrongThang = congNhan_DAO.layDanhSachCongNhanLamTrongThang(thang, nam);
		boolean flag = false;
		for (CongNhan congNhan : dsCongNhanDiLamTrongThang) {
			double tongLuong = layRaTongTienTheoMaCongNhan(congNhan.getMaCongNhan(), thang, nam);
			int soNgayDiLam = laySoNgayDiLam(congNhan.getMaCongNhan(), thang, nam);
			int soNgayNghi = layRaSoNgayNghiCoPhep(congNhan.getMaCongNhan(), thang, nam);
			int soPhepNghi = layRaSoNgayNghiKhongPhep(congNhan.getMaCongNhan(), thang, nam);
			int soLuongSanPhamLam = layRaTongSoLuongSPLam(congNhan.getMaCongNhan(), thang, nam);

			flag = themMotBangLuong(new BangLuongCongNhan(layRaMotMaBangLuongDeThem(), congNhan, soLuongSanPhamLam,
					soNgayDiLam, soNgayNghi, soPhepNghi, new Date(), tongLuong, "VND", thang + "-" + nam));
		}
		ArrayList<CongNhan> dsCongNhanKhongDuocChamCong = congNhan_DAO.layDanhSachCongNhanKhongDiLamTrongThang(thang, nam);
		for (CongNhan congNhan : dsCongNhanKhongDuocChamCong) {
			flag = themMotBangLuong(new BangLuongCongNhan(layRaMotMaBangLuongDeThem(), congNhan, 0, 0, 0, 0, new Date(), 0, "VND", thang + "-" + nam));
		}
		return flag;
	}

	public String layRaMotMaBangLuongDeThem() {
		Statement stm = null;
		String maBangLuong = "";
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String truyVan = "select top 1 * from BangLuongCongNhan order by LEN(maBangLuong), maBangLuong desc";
			stm = con.createStatement();
			ResultSet rs = stm.executeQuery(truyVan);
			while (rs.next()) {
				maBangLuong = rs.getString("maBangLuong");

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
		if (maBangLuong == null || maBangLuong.equals("")) {
			return "PPLC100001";
		}
		String chuoiCanLay = maBangLuong.split("PPLC")[1];

		try {
			chuoiCanLay = "PPLC" + (Integer.parseInt(chuoiCanLay) + 1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return chuoiCanLay;
	}

	public boolean xoaDiNhungThangDaTinh(int thang, int nam) {
		PreparedStatement stm = null;
		int soLuongDongXoaDuoc = 0;
		String theoThangNam = thang + "-" + nam;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String truyVan = "delete from BangLuongCongNhan"
					+ " where luongTheoThang = ?";
			stm = con.prepareStatement(truyVan);
			stm.setString(1, theoThangNam);
			soLuongDongXoaDuoc = stm.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				stm.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return soLuongDongXoaDuoc != 0;
	}

	public ArrayList<BangLuongCongNhan> layDanhSachBangLuongTheoThangNam(int thang, int nam) {
		PreparedStatement stm = null;
		ArrayList<BangLuongCongNhan> dsBangLuong = new ArrayList<BangLuongCongNhan>();
		CongNhan_Dao congNhan_DAO = new CongNhan_Dao();
		String ngaySoSanh = thang + "-" + nam;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String truyVan = "select * from BangLuongCongNhan where luongTheoThang = ?";
			stm = con.prepareStatement(truyVan);
			stm.setString(1, ngaySoSanh);

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
				stm.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return dsBangLuong;
	}

	public ArrayList<BangLuongCongNhan> danhSachBangLuongTheoThangNam(String thang, String nam) {
		PreparedStatement stm = null;
		ArrayList<BangLuongCongNhan> dsBangLuong = new ArrayList<>();
		CongNhan_Dao congNhan_DAO = new CongNhan_Dao();

		try {
			ConnectionDB.ConnectDB.getInstance();
			Connection con = ConnectionDB.ConnectDB.getConnection();
			String truyVan = "SELECT * FROM BangLuongCongNhan WHERE MONTH(ngayTinh) = ? AND YEAR(ngayTinh) = ?";
			stm = con.prepareStatement(truyVan);
			stm.setString(1, thang);
			stm.setString(2, nam);

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

	public static void main(String[] args) {
		try {
			System.setOut(new PrintStream(System.out, true, "UTF8"));
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

		try {
			ConnectDB.getInstance().connect();
			System.out.println("Yes");
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
