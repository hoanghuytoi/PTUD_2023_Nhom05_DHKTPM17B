package Entity;

import java.util.Date;

public class ChamCongNhanVien {
    private NhanVien nhanVien;
    private Date ngayChamCong;
    private String caLam;
    private String trangThaiDiLam;
    private String gioDiLam;
    private int gioTangCa;
    private NhanVien nguoiChamCong;

	public ChamCongNhanVien(NhanVien nhanVien, Date ngayChamCong, String caLam, String trangThaiDiLam, String gioDiLam,
			int gioTangCa, NhanVien nguoiChamCong) {
		super();
		this.nhanVien = nhanVien;
		this.ngayChamCong = ngayChamCong;
		this.caLam = caLam;
		this.trangThaiDiLam = trangThaiDiLam;
		this.gioDiLam = gioDiLam;
		this.gioTangCa = gioTangCa;
		this.nguoiChamCong = nguoiChamCong;
	}

	public int getGioTangCa() {
		return gioTangCa;
	}

	public void setGioTangCa(int gioTangCa) {
		this.gioTangCa = gioTangCa;
	}

	public Date getNgayChamCong() {
        return ngayChamCong;
    }

    /* Lý do ngày chấm công là ngày hiện tại hoặc ngày trước vì hệ thống có thể gặp trục trặc ngày đó không
        thể chấm được thì ngày sau vẫn có thể chấm bù cho ngày đó
     */
    public void setNgayChamCong(Date ngayChamCong) throws Exception {
        if (ngayChamCong.after(new Date())) {
            throw new Exception("Ngày chấm công phải bằng hoặc trước ngày hiện tại");
        } else {
            this.ngayChamCong = ngayChamCong;
        }
    }

    public String getCaLam() {
        return caLam;
    }

    public void setCaLam(String caLam) {
        this.caLam = caLam;
    }

    public String getTrangThaiDiLam() {
        return trangThaiDiLam;
    }

    public void setGioDiLam(String gioDiLam) {
        this.gioDiLam = gioDiLam;
    }

    public String getGioDiLam() {
        return gioDiLam;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public NhanVien getNguoiChamCong() {
        return nguoiChamCong;
    }

    public void setNguoiChamCong(NhanVien nguoiChamCong) {
        this.nguoiChamCong = nguoiChamCong;
    }

	@Override
	public String toString() {
		return "ChamCongNhanVien [nhanVien=" + nhanVien + ", ngayChamCong=" + ngayChamCong + ", caLam=" + caLam
				+ ", trangThaiDiLam=" + trangThaiDiLam + ", gioDiLam=" + gioDiLam + ", gioTangCa=" + gioTangCa
				+ ", nguoiChamCong=" + nguoiChamCong + "]";
	}

    

}
