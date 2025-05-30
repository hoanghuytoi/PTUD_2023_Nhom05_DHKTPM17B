package Entity;

import java.util.Date;

public class BangLuongNhanVien {
	private String maBangLuong;
    private NhanVien nhanVien;
    private int soNgayDiLam;
    private int soNgayNghi;
    private int soPhepNghi;
    private Date ngayTinh;
    private String luongTheoThang;
    private double luongTangCa;
    private double phuCap;
    private double thucLanh;
    private String donViTien;
    
	@Override
	public String toString() {
		return "BangLuongNhanVien [maBangLuong=" + maBangLuong + ", nhanVien=" + nhanVien + ", soNgayDiLam="
				+ soNgayDiLam + ", soNgayNghi=" + soNgayNghi + ", soPhepNghi=" + soPhepNghi + ", ngayTinh=" + ngayTinh
				+ ", luongTheoThang=" + luongTheoThang + ", luongTangCa=" + luongTangCa + ", phuCap=" + phuCap + ", thucLanh="
				+ thucLanh + ", donViTien=" + donViTien + "]";
	}
	public BangLuongNhanVien(String maBangLuong, NhanVien nhanVien, int soNgayDiLam, int soNgayNghi, int soPhepNghi,
			Date ngayTinh, String luongTheoThang, double luongTangCa, double phuCap, double thucLanh, String donViTien) {
		super();
		this.maBangLuong = maBangLuong;
		this.nhanVien = nhanVien;
		this.soNgayDiLam = soNgayDiLam;
		this.soNgayNghi = soNgayNghi;
		this.soPhepNghi = soPhepNghi;
		this.ngayTinh = ngayTinh;
		this.luongTheoThang = luongTheoThang;
		this.luongTangCa = luongTangCa;
		this.phuCap = phuCap;
		this.thucLanh = thucLanh;
		this.donViTien = donViTien;
	}
	
	public BangLuongNhanVien() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getMaBangLuong() {
		return maBangLuong;
	}
	
	public void setMaBangLuong(String maBangLuong) throws Exception {
		if (maBangLuong.equals("")) {
            throw new Exception("Mã bảng lương nhân viên không được để trống!");
        } else if (!maBangLuong.matches("^PPLN[1-9][0-9]{5}$")) {
            throw new Exception("Mã bảng lương phải theo dạng PPLNxxxxxx với x là các kí tự số x đầu tiền từ [1-9] x sau từ [0-9]");
        } else {
            this.maBangLuong = maBangLuong;
        }
	}
	
	public NhanVien getNhanVien() {
		return nhanVien;
	}
	
	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}
	
	public int getSoNgayDiLam() {
		return soNgayDiLam;
	}
	
	public void setSoNgayDiLam(int soNgayDiLam) throws Exception {
		if (soNgayDiLam < 0) {
            throw new Exception("Số ngày đi làm không được < 0");
        } else {
            this.soNgayDiLam = soNgayDiLam;
        }
	}
	
	public int getSoNgayNghi() {
		return soNgayNghi;
	}
	
	public void setSoNgayNghi(int soNgayNghi) throws Exception {
		if (soNgayNghi < 0) {
            throw new Exception("Số ngày nghỉ không được < 0");
        } else {
            this.soNgayNghi = soNgayNghi;
        }
	}
	
	public int getSoPhepNghi() {
		return soPhepNghi;
	}
	
	public void setSoPhepNghi(int soPhepNghi) throws Exception {
		if (soPhepNghi > soNgayNghi) {
            throw new Exception("Số phép nghỉ không được nhiều hơn số ngày nghỉ");
        } else if (soPhepNghi < 0) {
            throw new Exception("Số phép nghỉ không được < 0");
        } else {
            this.soPhepNghi = soPhepNghi;
        }
	}
	
	public Date getNgayTinh() {
		return ngayTinh;
	}
	
	public void setNgayTinh(Date ngayTinh) throws Exception {
		if (ngayTinh.after(new Date())) {
            throw new Exception("Ngày tính không được sau ngày hiện tại");
        } else {
            this.ngayTinh = ngayTinh;
        }	
	}
	
	public String getLuongTheoThang() {
		return luongTheoThang;
	}
	public void setLuongTheoThang(String luongTheoThang) {
		this.luongTheoThang = luongTheoThang;
	}
	public double getLuongTangCa() {
		return luongTangCa;
	}
	
	public void setLuongTangCa(double luongTangCa) {
		this.luongTangCa = luongTangCa;
	}
	
	public double getPhuCap() {
		return phuCap;
	}
	
	public void setPhuCap(double phuCap) {
		this.phuCap = phuCap;
	}
	
	public double getThucLanh() {
		return thucLanh;
	}
	
	public void setThucLanh(double thucLanh) throws Exception {
		if (thucLanh < 0) {
            throw new Exception("Tổng tiền lương của nhân viên không được < 0");
        } else {
            this.thucLanh = thucLanh;
        }	}
	
	public String getDonViTien() {
		return donViTien;
	}
	
	public void setDonViTien(String donViTien) throws Exception {
		if (!donViTien.equalsIgnoreCase("VND") && !donViTien.equalsIgnoreCase("USD")) {
            throw new Exception("Chỉ chấp nhận tiền VND hoặc USD");
        } else {
            this.donViTien = donViTien;
        }
	}
	
}
