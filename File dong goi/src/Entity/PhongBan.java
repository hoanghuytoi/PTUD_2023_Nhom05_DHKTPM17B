package Entity;

public class PhongBan {
	private String maPhongBan;
    private String tenPhongBan;
    private int soLuongNhanVien;

    public PhongBan(String maPhongBan, String tenPhongBan, int soLuongNhanVien) {
        try {
            setMaPhongBan(maPhongBan);
            setTenPhongBan(tenPhongBan);
            setSoLuongNhanVien(soLuongNhanVien);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    public String getMaPhongBan() {
        return maPhongBan;
    }

    private void setMaPhongBan(String maPhongBan) throws Exception {
        if (maPhongBan.equals("")) {
            throw new Exception("Mã phòng ban không được rỗng");
        } else if (!maPhongBan.matches("^PPPB[1-9][0-9]{5}$")) {
            throw new Exception("Mã phòng ban phải theo định dạng PPPBxxxxxx với x là các kí tự số, x đầu tiền từ [1-9][0-9]");
        } else {
            this.maPhongBan = maPhongBan;
        }
    }

    public String getTenPhongBan() {
        return tenPhongBan;
    }

    public void setTenPhongBan(String tenPhongBan) throws Exception {
        if (tenPhongBan.equals("")) {
            throw new Exception("Tên phòng ban không được để trống!");

        } else if (!tenPhongBan.toLowerCase()
                .matches("^([a-vxyỳọáầảấờễàạằệếýộậốũứĩõúữịỗìềểẩớặòùồợãụủíỹắẫựỉỏừỷởóéửỵẳẹèẽổẵẻỡơôưăêâđ]+)((\\s{1}[a-vxyỳọáầảấờễàạằệếýộậốũứĩõúữịỗìềểẩớặòùồợãụủíỹắẫựỉỏừỷởóéửỵẳẹèẽổẵẻỡơôưăêâđ]+){0,})$")) {
            throw new Exception ("Tên phòng ban chỉ chứa kí tự chữ");
        } else{
            this.tenPhongBan = tenPhongBan;
        }
    }

    public int getSoLuongNhanVien() {
        return soLuongNhanVien;
    }

    public void setSoLuongNhanVien(int soLuongNhanVien) throws Exception {
        if (soLuongNhanVien < 0) {
            throw new Exception("Số lượng nhân viên không được < 0");
        } else {
            this.soLuongNhanVien = soLuongNhanVien;
        }
    }

    @Override
    public String toString() {
        return "PhongBan{" + "maPhongBan=" + maPhongBan + ", tenPhongBan=" + tenPhongBan + ", soLuongNhanVien=" + soLuongNhanVien + '}';
    }
    
    
}
