package XuatFile;

import java.math.BigDecimal;
import java.util.Date;

public class PhieuLuongNVForm {
    private Date ngayTinhLuong;
    private String luongThang;
    private String maLuong;
    private String maNV;
    private String hoVaTen;
    private String SDT;
    private int soNgayDiLam;
    private int soNgayNghi;
    private int soPhepNghi;
    private String chucVu;
    private BigDecimal luongCoBan;
    private BigDecimal luongTangCa;
    private BigDecimal phuCap;
    private BigDecimal tongLuong;

    public PhieuLuongNVForm() {
        super();
    }

    public PhieuLuongNVForm(Date ngayTinhLuong, String luongThang, String maLuong, String maNV, String hoVaTen,
                            String sDT, int soNgayDiLam, int soNgayNghi, int soPhepNghi, String chucVu,
                            BigDecimal luongCoBan, BigDecimal luongTangCa, BigDecimal phuCap, BigDecimal tongLuong) {
        super();
        this.ngayTinhLuong = ngayTinhLuong;
        this.luongThang = luongThang;
        this.maLuong = maLuong;
        this.maNV = maNV;
        this.hoVaTen = hoVaTen;
        this.SDT = sDT;
        this.soNgayDiLam = soNgayDiLam;
        this.soNgayNghi = soNgayNghi;
        this.soPhepNghi = soPhepNghi;
        this.chucVu = chucVu;
        this.luongCoBan = luongCoBan;
        this.luongTangCa = luongTangCa;
        this.phuCap = phuCap;
        this.tongLuong = tongLuong;
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

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getHoVaTen() {
        return hoVaTen;
    }

    public void setHoVaTen(String hoVaTen) {
        this.hoVaTen = hoVaTen;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String sDT) {
        SDT = sDT;
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

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public BigDecimal getLuongCoBan() {
        return luongCoBan;
    }

    public void setLuongCoBan(BigDecimal luongCoBan) {
        this.luongCoBan = luongCoBan;
    }

    public BigDecimal getLuongTangCa() {
        return luongTangCa;
    }

    public void setLuongTangCa(BigDecimal luongTangCa) {
        this.luongTangCa = luongTangCa;
    }

    public BigDecimal getPhuCap() {
        return phuCap;
    }

    public void setPhuCap(BigDecimal phuCap) {
        this.phuCap = phuCap;
    }

    public BigDecimal getTongLuong() {
        return tongLuong;
    }

    public void setTongLuong(BigDecimal tongLuong) {
        this.tongLuong = tongLuong;
    }


    @Override
    public String toString() {
        return "PhieuLuongNVForm [ngayTinhLuong=" + ngayTinhLuong + ", luongThang=" + luongThang + ", maBangLuong="
                + maLuong + ", maNV=" + maNV + ", hoVaTen=" + hoVaTen + ", SDT=" + SDT + ", soNgayDiLam="
                + soNgayDiLam + ", soNgayNghi=" + soNgayNghi + ", soPhepNghi=" + soPhepNghi + ", chucVu=" + chucVu
                + ", luongCoBan=" + luongCoBan + ", luongTangCa=" + luongTangCa + ", phuCap=" + phuCap + ", tongLuong="
                + tongLuong + "]";
    }
}
