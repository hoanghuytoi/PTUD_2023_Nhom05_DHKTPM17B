package XuatFile;

import java.math.BigDecimal;
import java.util.Date;

public class PhieuLuongCNForm {
	private Date ngayTinhLuong;
    private String luongThang;
    private String maLuong;
    private String maCN;
    private String hoVaTen;
    private String SDT;
    private String CCCD;
    private int soNgayDiLam;
    private int soNgayNghi;
    private int soPhepNghi;
    private int soLuongSPLam;
	private BigDecimal tongLuong;

    @Override
	public String toString() {
		return "PhieuLuongCNForm [ngayTinhLuong=" + ngayTinhLuong + ", luongThang=" + luongThang + ", maLuong="
				+ maLuong + ", maCN=" + maCN + ", hoVaTen=" + hoVaTen + ", SDT=" + SDT + ", CCCD=" + CCCD
				+ ", soNgayDiLam=" + soNgayDiLam + ", soNgayNghi=" + soNgayNghi + ", soPhepNghi=" + soPhepNghi
				+ ", soLuongSPLam=" + soLuongSPLam + ", tongLuong=" + tongLuong + "]";
	}
	public PhieuLuongCNForm(Date ngayTinhLuong, String luongThang, String maLuong, String maCN, String hoVaTen,
			String sDT, String cCCD, int soNgayDiLam, int soNgayNghi, int soPhepNghi, int soLuongSPLam,
			BigDecimal tongLuong) {
		super();
		this.ngayTinhLuong = ngayTinhLuong;
		this.luongThang = luongThang;
		this.maLuong = maLuong;
		this.maCN = maCN;
		this.hoVaTen = hoVaTen;
		SDT = sDT;
		CCCD = cCCD;
		this.soNgayDiLam = soNgayDiLam;
		this.soNgayNghi = soNgayNghi;
		this.soPhepNghi = soPhepNghi;
		this.soLuongSPLam = soLuongSPLam;
		this.tongLuong = tongLuong;
	}
	public PhieuLuongCNForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getSoLuongSPLam() {
		return soLuongSPLam;
	}
	public void setSoLuongSPLam(int soLuongSPLam) {
		this.soLuongSPLam = soLuongSPLam;
	}
    
	public String getSDT() {
		return SDT;
	}
	public void setSDT(String sDT) {
		SDT = sDT;
	}
	public Date getNgayTinhLuong() {
		return ngayTinhLuong;
	}
	public void setNgayTinhLuong(Date ngayTinhLuong) {
		this.ngayTinhLuong = ngayTinhLuong;
	}
	public String getLuongThang() {
		return luongThang;
	}
	public void setLuongThang(String luongThang) {
		this.luongThang = luongThang;
	}
	public String getMaLuong() {
		return maLuong;
	}
	public void setMaLuong(String maLuong) {
		this.maLuong = maLuong;
	}
	public String getMaCN() {
		return maCN;
	}
	public void setMaCN(String maCN) {
		this.maCN = maCN;
	}
	public String getHoVaTen() {
		return hoVaTen;
	}
	public void setHoVaTen(String hoVaTen) {
		this.hoVaTen = hoVaTen;
	}
	public String getCCCD() {
		return CCCD;
	}
	public void setCCCD(String cCCD) {
		CCCD = cCCD;
	}
	public int getSoNgayDiLam() {
		return soNgayDiLam;
	}
	public void setSoNgayDiLam(int soNgayDiLam) {
		this.soNgayDiLam = soNgayDiLam;
	}
	public int getSoNgayNghi() {
		return soNgayNghi;
	}
	public void setSoNgayNghi(int soNgayNghi) {
		this.soNgayNghi = soNgayNghi;
	}
	public int getSoPhepNghi() {
		return soPhepNghi;
	}
	public void setSoPhepNghi(int soPhepNghi) {
		this.soPhepNghi = soPhepNghi;
	}
	public BigDecimal getTongLuong() {
		return tongLuong;
	}
	public void setTongLuong(BigDecimal tongLuong) {
		this.tongLuong = tongLuong;
	}
    
    
}
