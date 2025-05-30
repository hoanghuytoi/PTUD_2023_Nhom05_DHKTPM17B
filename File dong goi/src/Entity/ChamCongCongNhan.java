package Entity;

import java.util.Date;

public class ChamCongCongNhan {

    private PhanCongCongNhan phanCong;
    private String caLam;
    private Date ngayChamCong;
    private int soLuongLam;
    private String trangThaiDiLam;
    private String gioDiLam;

    public ChamCongCongNhan(PhanCongCongNhan phanCong, String caLam, Date ngayChamCong, int soLuongLam, String trangThaiDiLam, String gioDiLam) {
        try {
            setPhanCong(phanCong);
            setCaLam(caLam);
            setNgayChamCong(ngayChamCong);
            setSoLuongLam(soLuongLam);
            setTrangThaiDiLam(trangThaiDiLam);
            setGioDiLam(gioDiLam);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Date getNgayChamCong() {
        return ngayChamCong;
    }

    public void setNgayChamCong(Date ngayChamCong) throws Exception {
        if (ngayChamCong.after(new Date())) {
            throw new Exception("Ngày chấm công công nhân phải trước hoặc bằng ngày hiện tại");
        } else {
            this.ngayChamCong = ngayChamCong;
        }
    }

    public int getSoLuongLam() {
        return soLuongLam;
    }

    public void setSoLuongLam(int soLuongLam) throws Exception {
        if (soLuongLam < 0) {
            throw new Exception("Số lượng làm không được < 0");
        } else {
            this.soLuongLam = soLuongLam;
        }
    }

    public PhanCongCongNhan getPhanCong() {
        return phanCong;
    }

    public void setPhanCong(PhanCongCongNhan phanCong) {
        this.phanCong = phanCong;
    }

    private void setTrangThaiDiLam(String trangThaiDiLam) throws Exception {
        this.trangThaiDiLam = trangThaiDiLam;
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

    public String getCaLam() {
        return caLam;
    }

    public void setCaLam(String caLam) throws Exception {
        if (caLam.equalsIgnoreCase("")){
            throw new Exception("Ca làm không được trống!");
        } else {
            this.caLam = caLam;
        }
    }

    @Override
    public String toString() {
        return "ChamCongCongNhan{" + "phanCong=" + phanCong + ", caLam=" + caLam + ", ngayChamCong=" + ngayChamCong + ", soLuongLam=" + soLuongLam + ", trangThaiDiLam=" + trangThaiDiLam + ", gioDiLam=" + gioDiLam + '}';
    }

}
