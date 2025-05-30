
package Entity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Acer
 */
public class NhanVien {
    
    private String maNhanVien;
    private String hoTen;
    private Date ngaySinh;
    private String maCCCD;
    private String soDienThoai;
    private String email;
    private String matKhau;
    private String chucVu;
    private Date ngayVaoLam;
    private double luongCoBan;
    private boolean gioiTinh;
    private String anhDaiDien;
    private String diaChi;
    private PhongBan phongBan;
    
    public NhanVien(String maNhanVien) {
        this.maNhanVien=maNhanVien;

	}
    
    public NhanVien() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NhanVien(String maNhanVien, String hoTen, Date ngaySinh, String maCCCD, String soDienThoai, String email, String matKhau, String chucVu, Date ngayVaoLam, double luongCoBan, boolean gioiTinh, String anhDaiDien, String diaChi, PhongBan phongBan) {
        try {
            setMaNhanVien(maNhanVien);
            setHoTen(hoTen);
            setNgaySinh(ngaySinh);
            setMaCCCD(maCCCD);
            setSoDienThoai(soDienThoai);
            setEmail(email);
            setMatKhau(matKhau);
            setChucVu(chucVu);
            setNgayVaoLam(ngayVaoLam);
            setLuongCoBan(luongCoBan);
            setGioiTinh(gioiTinh);
            setAnhDaiDien(anhDaiDien);
            setDiaChi(diaChi);
            setPhongBan(phongBan);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    public String getMaNhanVien() {
        return maNhanVien;
    }
    
    private void setMaNhanVien(String maNhanVien) throws Exception {
        if (maNhanVien.equals("")) {
            throw new Exception("Mã nhân viên không được để trống!");
        } else if (!maNhanVien.matches("^PPNV[1-9][0-9]{5}$")) {
            throw new Exception("Mã nhân viên phải theo định dạng PPNVxxxxxx với x là các kí tự số, x đầu tiền từ [1-9][0-9]");
        } else {
            this.maNhanVien = maNhanVien;
        }
    }
    
    public String getHoTen() {
        return hoTen;
    }
    
    public void setHoTen(String hoTen) throws Exception {
        if (hoTen.equals("")) {
            throw new Exception("Họ tên không được để trống!");
        } else if (!hoTen.matches("^([A-ZĐÂÁƯ][a-zỳọáầảấờễàạằệếýộậốũứĩõúữịỗìềểẩớặòùồợãụủíỹắẫựỉỏừỷởóéửỵẳẹèẽổẵẻỡơôưăêâđ]+)((\\s[A-ZĐÂÁƯ][a-zỳọáầảấờễàạằệếýộậốũứĩõúữịỗìềểẩớặòùồợãụủíỹắẫựỉỏừỷởóéửỵẳẹèẽổẵẻỡơôưăêâđ]+){1,})$")) {
            throw new Exception(hoTen);
        } else {
            this.hoTen = hoTen;
        }
    }
    
    public int calculateAgeWithJava7(Date birthDate, Date currentDate) {                                                                              
        DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        int d1 = Integer.parseInt(formatter.format(birthDate));
        int d2 = Integer.parseInt(formatter.format(currentDate));
        int age = (d2 - d1) / 10000;
        return age;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }
    
    public void setNgaySinh(Date ngaySinh) throws Exception {
        if (ngaySinh.after(new Date())) {
            throw new Exception("Ngày sinh phải trước ngày hiện tại");
        } else if (calculateAgeWithJava7(ngaySinh, new Date()) < 18) {
            throw new Exception("Phải từ 18 tuổi trở lên");
        } else {
            this.ngaySinh = ngaySinh;
        }
    }
    
    public String getMaCCCD() {
        return maCCCD;
    }
    
    public void setMaCCCD(String maCCCD) throws Exception {
        if (maCCCD.equals("")) {
            throw new Exception("Mã căn cước công dân không được trống!");
        } else if (!maCCCD.matches("^[0-9]{12}$")) {
            throw new Exception("Mã căn cước công dân chí chứa chữ số, gồm 12 kí tự");
        } else {
            this.maCCCD = maCCCD;
        }
    }
    
    public String getSoDienThoai() {
        return soDienThoai;
    }
    
    public void setSoDienThoai(String soDienThoai) throws Exception {
        if (soDienThoai.equals("")) {
            throw new Exception("Số điện thoại không được rỗng");
        } else if (!soDienThoai.matches("^\\+84[1-9][0-9]{8}$")) {
            throw new Exception("Số điện thoại theo mau +84 + sdt ");
        } else {
            this.soDienThoai = soDienThoai;
        }
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) throws Exception {
        if (email.equals("")) {
            throw new Exception("Địa chỉ email không được để trống");
        } else if (!email.matches("^^[a-z]\\w*@gmail.com$$")) {
            throw new Exception("Địa chỉ email không hợp lệ, phải đúng định dạng gmail hiện nay, phải có @, tên miền");
        } else {
            this.email = email;
        }
    }
    
    public String getMatKhau() {
        return matKhau;
    }
    
    public void setMatKhau(String matKhau) throws Exception {
        if (matKhau.equals("")) {
            throw new Exception("Mật khẩu không được để trống!");
        } else if (matKhau.length() < 6) {
            throw new Exception("Mật khẩu phải từ 6 kí tự trở lên");
        } else {
            this.matKhau = matKhau;
        }
    }
    
    public String getChucVu() {
        return chucVu;
    }
    
    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }
    
    public Date getNgayVaoLam() {
        return ngayVaoLam;
    }
    
    public void setNgayVaoLam(Date ngayVaoLam) throws Exception {
        this.ngayVaoLam = ngayVaoLam;
    }
    
    public double getLuongCoBan() {
        return luongCoBan;
    }
    
    public void setLuongCoBan(double luongCoBan) throws Exception {
        if (luongCoBan < 0) {
            throw new Exception("Lương cơ bản phải >= 0");
        } else {
            this.luongCoBan = luongCoBan;
        }
    }
    
    public boolean isGioiTinh() {
        return gioiTinh;
    }
    
    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }
    
    public String getAnhDaiDien() {
        return anhDaiDien;
    }
    
    public void setAnhDaiDien(String anhDaiDien) throws Exception {
        if (anhDaiDien.equals("")) {
            throw new Exception("Ảnh sản phẩm không được để trống!");
        } else if (!anhDaiDien.matches("^.{1,}\\.(png|PNG|jpg|JPG|raw|RAW|JPEG|jpeg)$")) {
            throw new Exception("Chỉ chấp nhận các ảnh có định dạng png, jpg, raw, jpeg");
        } else {
            this.anhDaiDien = anhDaiDien;
        }
    }
    
    public String getDiaChi() {
        return diaChi;
    }
    
    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
    
    public PhongBan getPhongBan() {
        return phongBan;
    }
    
    public void setPhongBan(PhongBan phongBan) {
        this.phongBan = phongBan;
    }
    
    @Override
    public String toString() {
        return "NhanVien{" + "maNhanVien=" + maNhanVien + ", hoTen=" + hoTen + ", ngaySinh=" + ngaySinh + ", maCCCD=" + maCCCD + ", soDienThoai=" + soDienThoai + ", email=" + email + ", matKhau=" + matKhau + ", chucVu=" + chucVu + ", ngayVaoLam=" + ngayVaoLam + ", luongThoaThuan=" + luongCoBan + ", gioiTinh=" + gioiTinh + ", anhDaiDien=" + anhDaiDien + ", diaChi=" + diaChi + ", phongBan=" + phongBan + '}';
    }
}
