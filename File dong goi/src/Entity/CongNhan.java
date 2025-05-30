package Entity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CongNhan {
	private String maCongNhan;
    private String hoTen;
    private Date ngaySinh;
    private String maCCCD;
    private String soDienThoai;
    private String email;
    private Date ngayVaoLam;
    private boolean gioiTinh;
    private String anhDaiDien;
    private String diaChi;
    private ToNhom toNhom;
    
	public CongNhan(String maCongNhan, String hoTen, Date ngaySinh, String maCCCD, String soDienThoai, String email,
			Date ngayVaoLam, boolean gioiTinh, String anhDaiDien, String diaChi, ToNhom toNhom) {
		try {
            setMaCongNhan(maCongNhan);
            setHoTen(hoTen);
            setNgaySinh(ngaySinh);
            setMaCCCD(maCCCD);
            setSoDienThoai(soDienThoai);
            setEmail(email);
            setNgayVaoLam(ngayVaoLam);
            setGioiTinh(gioiTinh);
            setAnhDaiDien(anhDaiDien);
            setDiaChi(diaChi);
            setToNhom(toNhom);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
	}

	public String getMaCongNhan() {
		return maCongNhan;
	}

	public void setMaCongNhan(String maCongNhan) throws Exception{
		if (maCongNhan.equals("")) {
            throw new Exception("Mã công nhân không được bỏ trống!");
        } else if (!maCongNhan.matches("^PPCN[1-9][0-9]{5}$")) {
            throw new Exception("Mã công nhân phải theo định dạng PPCNxxxxxx với x là các kí tự số, x đầu tiền từ [1-9], x sau từ [0-9]");
        } else {
            this.maCongNhan = maCongNhan;
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

	public void setMaCCCD(String maCCCD) throws Exception{
		if (maCCCD.equals("")) {
            throw new Exception("Mã căn cước công dân không được trống!");
        } else if (!maCCCD.matches("^[0-9]{12}$")) {
            throw new Exception("Mã căn cước công dân chỉ chứa chữ số gồm 12 kí tự!");
        } else {
            this.maCCCD = maCCCD;
        }
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) throws Exception{
		if(soDienThoai.equals("")) {
			throw new Exception("Số điện thoại không được để trống!");
		}else if (!soDienThoai.matches("^\\+84[1-9][0-9]{8}$")) {
            throw new Exception("Số điện thoại theo mẫu +84+... sdt!");
        } else {
            this.soDienThoai = soDienThoai;
        }
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) throws Exception {
        if (email.equals("")) {
            throw new Exception("Địa chỉ email không được để trống!");
        } else if (!email.matches("^^[a-z]\\w*@gmail.com$$")) {
            throw new Exception("Địa chỉ email không hợp lệ, phải đúng định dạng gmail hiện nay, phải có @, tên miền!");
        } else {
            this.email = email;
        }
    }

	public Date getNgayVaoLam() {
		return ngayVaoLam;
	}

	public void setNgayVaoLam(Date ngayVaoLam) throws Exception {
		if (ngayVaoLam.after(new Date())) {
            throw new Exception("Ngày vào làm phải trước ngày hiện tại!");
        }
        this.ngayVaoLam = ngayVaoLam;
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

	public void setAnhDaiDien(String anhDaiDien) throws Exception{
		if (anhDaiDien.equals("")) {
            throw new Exception("Ảnh sản phẩm không được để trống!");
        } else if (!anhDaiDien.matches("^.{1,}\\.(png|PNG|jpg|JPG|raw|RAW|JPEG|jpeg)$")) {
            throw new Exception("Chỉ chấp nhận các ảnh có định dạng: png, jpg, raw, jpeg!");
        } else {
            this.anhDaiDien = anhDaiDien;
        }
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi)throws Exception {
		if (diaChi.equals("")) {
            throw new Exception("Địa chỉ không được để trống!");
        }else {
        	this.diaChi = diaChi;
        }
	}

	public ToNhom getToNhom() {
		return toNhom;
	}

	public void setToNhom(ToNhom toNhom) {
		this.toNhom = toNhom;
	}
    
	@Override
    public String toString() {
        return "CongNhan{" + "maCongNhan=" + maCongNhan + ", hoTen=" + hoTen + ", ngaySinh=" + ngaySinh + ", maCCCD=" + maCCCD + ", soDienThoai=" + soDienThoai + ", email=" + email +  ", ngayVaoLam=" + ngayVaoLam + ", gioiTinh=" + gioiTinh + ", anhDaiDien=" + anhDaiDien + ", diaChi=" + diaChi + ", toNhom=" + toNhom + '}';
    }
	
}
