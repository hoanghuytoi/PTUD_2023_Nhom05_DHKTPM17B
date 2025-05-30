package Entity;

import java.util.Date;

public class BangLuongCongNhan {
	private String maBangLuong;
    private CongNhan congNhan;
    private int soLuongSanPhamLam;
    private int soNgayDiLam;
    private int soNgayNghi;
    private int soPhepNghi;
    private Date ngayTinh;
    private double tongLuong;
    private String donViTien;
    private String luongTheoThang;
    
    
    
    public BangLuongCongNhan() {
		super();
		// TODO Auto-generated constructor stub
	}


	public BangLuongCongNhan(String maBangLuong, CongNhan congNhan, int soLuongSanPhamLam, int soNgayDiLam, int soNgayNghi, int soPhepNghi, Date ngayTinh, double tongLuong, String donViTien, String luongTheoThang) {
        try {
            setMaBangLuong(maBangLuong);
            setCongNhan(congNhan);
            setSoLuongSanPhamLam(soLuongSanPhamLam);
            setSoNgayDiLam(soNgayDiLam);
            setSoNgayNghi(soNgayNghi);
            setSoPhepNghi(soPhepNghi);
            setNgayTinh(ngayTinh);
            setTongLuong(tongLuong);
            setDonViTien(donViTien);
            setLuongTheoThang(luongTheoThang);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    

    public String getMaBangLuong() {
        return maBangLuong;
    }

    private void setMaBangLuong(String maBangLuong) throws Exception {
        if (maBangLuong.equals("")) {
            throw new Exception("Mã bảng lương không được rỗng!");
        } else if (!maBangLuong.matches("^PPLC[1-9][0-9]{5}$")) {
            throw new Exception("Mã bảng lương phải theo dạng PPLCxxxxxx với x là các kí tự số x đầu tiền từ [1-9], x sau từ [0-9]");
        } else {
            this.maBangLuong = maBangLuong;
        }
    }

    public CongNhan getCongNhan() {
        return congNhan;
    }

    public void setCongNhan(CongNhan congNhan) {
        this.congNhan = congNhan;
    }

    public int getSoLuongSanPhamLam() {
        return soLuongSanPhamLam;
    }

    public void setSoLuongSanPhamLam(int soLuongSanPhamLam) throws Exception {
        if (soLuongSanPhamLam < 0){
            throw new Exception ("Số lượng làm ra không được < 0!");
        } else {
            this.soLuongSanPhamLam = soLuongSanPhamLam;
        }
    }

    public int getSoNgayDiLam() {
        return soNgayDiLam;
    }

    public void setSoNgayDiLam(int soNgayDiLam) throws Exception {
       if (soNgayDiLam < 0){
           throw new Exception("Số ngày đi làm không được < 0!");
       } else {
           this.soNgayDiLam = soNgayDiLam;
       }
    }

    public int getSoNgayNghi() {
        return soNgayNghi;
    }

    public void setSoNgayNghi(int soNgayNghi) throws Exception {
        if (soNgayNghi < 0){
            throw new Exception("Số ngày nghỉ không được < 0!");
        } else {
            this.soNgayNghi = soNgayNghi;
        }
    }

    public int getSoPhepNghi() {
        return soPhepNghi;
    }

    public void setSoPhepNghi(int soPhepNghi) throws Exception {
       if (soPhepNghi > soNgayNghi){
           throw new Exception("Số phép nghỉ không được nhiều hơn số ngày nghỉ!");
       } else if(soPhepNghi < 0){
           throw new Exception("Số phép nghỉ không được < 0");
       } else {
           this.soPhepNghi = soPhepNghi;
       }
    }

    public Date getNgayTinh() {
        return ngayTinh;
    }

    public void setNgayTinh(Date ngayTinh) throws Exception {
        if (ngayTinh.after(new Date())){
            throw new Exception("Ngày tính không được sau ngày hiện tại!");
        } else {
            this.ngayTinh = ngayTinh;
        }
    }

    public double getTongLuong() {
        return tongLuong;
    }

    public void setTongLuong(double tongLuong) throws Exception {
        if (tongLuong < 0){
            throw new Exception("Tổng lương không được < 0!");
        } else {
            this.tongLuong = tongLuong;
        }
    }

    public String getDonViTien() {
        return donViTien;
    }

    public void setDonViTien(String donViTien) throws Exception {
        if (!donViTien.equalsIgnoreCase("VND") && !donViTien.equalsIgnoreCase("USD")){
            throw new Exception("Chỉ chấp nhận tiền VND hoặc USD");
        } else {
            this.donViTien = donViTien;
        }
    }

    public String getLuongTheoThang() {
        return luongTheoThang;
    }

    public void setLuongTheoThang(String luongTheoThang) {
        this.luongTheoThang = luongTheoThang;
    }

    @Override
    public String toString() {
        return "BangLuongCongNhan{" + "maBangLuong=" + maBangLuong + ", congNhan=" + congNhan + ", soLuongSanPhamLam=" + soLuongSanPhamLam + ", soNgayDiLam=" + soNgayDiLam + ", soNgayNghi=" + soNgayNghi + ", soPhepNghi=" + soPhepNghi + ", ngayTinh=" + ngayTinh + ", tongLuong=" + tongLuong + ", donViTien=" + donViTien + ", luongTheoThang=" + luongTheoThang + '}';
    }
}
