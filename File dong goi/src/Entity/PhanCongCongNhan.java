package Entity;

import java.util.Date;

public class PhanCongCongNhan {
	private String maPhanCong;
    private CongNhan congNhan;
    private CongDoan congDoan;
    private NhanVien nguoiPhanCong;
    private Date ngayPhanCong;
    private ToNhom toNhom;
    private int soLuongCanLam;
    
    public PhanCongCongNhan(String maPhanCong, CongNhan congNhan, CongDoan congDoan, NhanVien nguoiPhanCong, Date ngayPhanCong, int soLuongCanLam, ToNhom toNhom) {
        try {
            setMaPhanCong(maPhanCong);
            setCongNhan(congNhan);
            setCongDoan(congDoan);
            setNguoiPhanCong(nguoiPhanCong);
            setNgayPhanCong(ngayPhanCong);
            setToNhom(toNhom);
            setSoLuongCanLam(soLuongCanLam);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

	public String getMaPhanCong() {
		return maPhanCong;
	}

	public void setMaPhanCong(String maPhanCong)throws Exception {
		if (maPhanCong.equals("")) {
            throw new Exception("Mã phân công không được để trống!");
        } else if (!maPhanCong.matches("^PPPC[1-9][0-9]{5}$")) {
            throw new Exception("Mã phân công công phải theo dạng PPPCxxxxxx với x là các kí tự số x đầu tiền từ [1-9], x sau từ [0-9]");
        } else {
            this.maPhanCong = maPhanCong;
        }
	}

	public CongNhan getCongNhan() {
		return congNhan;
	}

	public void setCongNhan(CongNhan congNhan) {
		this.congNhan = congNhan;
	}

	public CongDoan getCongDoan() {
		return congDoan;
	}

	public void setCongDoan(CongDoan congDoan) {
		this.congDoan = congDoan;
	}

	public NhanVien getNguoiPhanCong() {
		return nguoiPhanCong;
	}

	public void setNguoiPhanCong(NhanVien nguoiPhanCong) {
		this.nguoiPhanCong = nguoiPhanCong;
	}

	public Date getNgayPhanCong() {
		return ngayPhanCong;
	}

	public void setNgayPhanCong(Date ngayPhanCong)throws Exception {
		if (ngayPhanCong.after(new Date())) {
            throw new Exception("Ngày phân công phải là ngày hiện tại hoặc trước ngày hiện tại!");
        } else {
            this.ngayPhanCong = ngayPhanCong;
        }
	}

	public ToNhom getToNhom() {
		return toNhom;
	}

	public void setToNhom(ToNhom toNhom) {
		this.toNhom = toNhom;
	}

	public int getSoLuongCanLam() {
		return soLuongCanLam;
	}

	public void setSoLuongCanLam(int soLuongCanLam) {
		this.soLuongCanLam = soLuongCanLam;
	}
    
	@Override
    public String toString() {
        return "PhanCongCongNhan{" + "maPhanCong=" + maPhanCong + ", congNhan=" + congNhan + ", congDoan=" + congDoan + ", nguoiPhanCong=" + nguoiPhanCong + ", ngayPhanCong=" + ngayPhanCong + ", soLuongCanLam=" + soLuongCanLam + ", toNhom=" + toNhom + '}';
    }
    
}
