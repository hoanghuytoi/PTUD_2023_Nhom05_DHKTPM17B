package Entity;

public class ToNhom {
	private String maToNhom;
    private String tenToNhom;
    private int soLuongCongNhan;
    
	public ToNhom(String maToNhom, String tenToNhom, int soLuongCongNhan) {
		try {
            setMaToNhom(maToNhom);
            setTenToNhom(tenToNhom);
            setSoLuongCongNhan(soLuongCongNhan);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
	}
	
	public String getMaToNhom() {
		return maToNhom;
	}
	
	public void setMaToNhom(String maToNhom) throws Exception {
        if (maToNhom.equals("")) {
            throw new Exception("Mã tổ nhóm không được bỏ trống!");
        } else if (!maToNhom.matches("^PPTN[1-9][0-9]{5}$")) {
            throw new Exception("Mã tổ nhóm phải theo định dạng PPTNxxxxx với x là các kí tự số, x đầu tiền từ [1-9], x sau từ [0-9]!");
        } else {
            this.maToNhom = maToNhom;
        }
    }
	
	public String getTenToNhom() {
		return tenToNhom;
	}
	
	public void setTenToNhom(String tenToNhom) throws Exception{
		if (tenToNhom.equals("")) {
            throw new Exception("Tên tổ nhóm không được bỏ trống!");
        } else if (!tenToNhom.toLowerCase().matches("^tổ [1-9][0-9]*$")) {
            return;
        } else {
            this.tenToNhom = tenToNhom;
        }
	}
	
	public int getSoLuongCongNhan() {
		return soLuongCongNhan;
	}
	
	public void setSoLuongCongNhan(int soLuongCongNhan)throws Exception {
		if (soLuongCongNhan < 0) {
            throw new Exception("Số lượng công nhân không được < 0!");
        } else {
            this.soLuongCongNhan = soLuongCongNhan;
        }
	}
	@Override
    public String toString() {
        return "ToNhom{" + "maToNhom=" + maToNhom + ", tenToNhom=" + tenToNhom + ", soLuongCongNhan=" + soLuongCongNhan + '}';
    }
}
