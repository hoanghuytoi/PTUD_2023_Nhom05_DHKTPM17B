package XuatFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Entity.BangLuongCongNhan;
import Entity.BangLuongNhanVien;
import Entity.HopDong;

import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class xuatIreport {

	public void inHopDong(ArrayList<HopDong> dsHD) throws JRException {
	    try {
	        InputStream arq = getClass().getResourceAsStream("/fileReport/InHopDong.jrxml");
	        JasperReport report = JasperCompileManager.compileReport(arq);

	        Map<String, Object> parameters = new HashMap<String, Object>();
	        ArrayList<HopDongForm> list = new ArrayList<>();

	        for (HopDong hd : dsHD) {
	            HopDongForm hopDongForm = new HopDongForm();
	            hopDongForm.setTime(hd.getNgayKyKetHD());
	            hopDongForm.setMaHopDong(hd.getMaHopDong());
	            hopDongForm.setTenHopDong(hd.getTenHopDong());
	            hopDongForm.setTenKhachHang(hd.getTenKhachHang());
	            hopDongForm.setNgayBatDau(new SimpleDateFormat("dd-MM-yyyy").format(hd.getNgayKyKetHD()));
	            hopDongForm.setNgayKetThuc(new SimpleDateFormat("dd-MM-yyyy").format(hd.getNgayKetThucHD()));
	            hopDongForm.setTriGiaHD(new BigDecimal(hd.getTriGiaHD()));
	            hopDongForm.setTienDatCoc(new BigDecimal(hd.getTienDatCoc()));
	            hopDongForm.setYeuCau(hd.getYeuCau());
	            hopDongForm.setNguoiKyKet(hd.getNguoiKyKet().getHoTen());

	            list.add(hopDongForm);
	        }

	        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);
	        JasperPrint print = JasperFillManager.fillReport(report, parameters, dataSource);
	        JasperViewer.viewReport(print, false);

	    } catch (JRException e) {
	        e.printStackTrace();
	    }
	}

	public void xuatChiTietLuong(ArrayList<BangLuongNhanVien> dsBangLuong) throws JRException {
		try {
	        InputStream arq = getClass().getResourceAsStream("/fileReport/ChiTietLuongNV.jrxml");
	        JasperReport report = JasperCompileManager.compileReport(arq);

	        Map<String, Object> parameters = new HashMap<String, Object>();
	        ArrayList<PhieuLuongNVForm> list = new ArrayList<>();

	        for (BangLuongNhanVien lnv : dsBangLuong) {
	        	PhieuLuongNVForm phieuLuongNVForm = new PhieuLuongNVForm();
	        	phieuLuongNVForm.setNgayTinhLuong(lnv.getNgayTinh());
	        	phieuLuongNVForm.setLuongThang(lnv.getLuongTheoThang());
	        	phieuLuongNVForm.setMaLuong(lnv.getMaBangLuong());
	        	phieuLuongNVForm.setMaNV(lnv.getNhanVien().getMaNhanVien());
	        	phieuLuongNVForm.setHoVaTen(lnv.getNhanVien().getHoTen());
	        	phieuLuongNVForm.setSDT(lnv.getNhanVien().getSoDienThoai());
	        	phieuLuongNVForm.setChucVu(lnv.getNhanVien().getChucVu());
	        	phieuLuongNVForm.setSoNgayDiLam(lnv.getSoNgayDiLam());
	        	phieuLuongNVForm.setSoNgayNghi(lnv.getSoNgayNghi());
	        	phieuLuongNVForm.setSoPhepNghi(lnv.getSoPhepNghi());
	        	phieuLuongNVForm.setLuongCoBan(new BigDecimal(lnv.getNhanVien().getLuongCoBan()));
	        	phieuLuongNVForm.setLuongTangCa(new BigDecimal(lnv.getLuongTangCa()));
	        	phieuLuongNVForm.setPhuCap(new BigDecimal(lnv.getPhuCap()));
	        	phieuLuongNVForm.setTongLuong(new BigDecimal(lnv.getThucLanh()));

	            list.add(phieuLuongNVForm);
	        }

	        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);
	        JasperPrint print = JasperFillManager.fillReport(report, parameters, dataSource);
	        JasperViewer.viewReport(print, false);

	    } catch (JRException e) {
	        e.printStackTrace();
	    }
	}
	
	public void xuatChiTietLuongCN(ArrayList<BangLuongCongNhan> dsBangLuong) throws JRException {
		try {
	        InputStream arq = getClass().getResourceAsStream("/fileReport/ChiTietLuongCN.jrxml");
	        JasperReport report = JasperCompileManager.compileReport(arq);

	        Map<String, Object> parameters = new HashMap<String, Object>();
	        ArrayList<PhieuLuongCNForm> list = new ArrayList<>();

	        for (BangLuongCongNhan lnv : dsBangLuong) {
	        	PhieuLuongCNForm phieuLuongCNForm = new PhieuLuongCNForm();
	        	phieuLuongCNForm.setNgayTinhLuong(lnv.getNgayTinh());
	        	phieuLuongCNForm.setLuongThang(lnv.getLuongTheoThang());
	        	phieuLuongCNForm.setMaLuong(lnv.getMaBangLuong());
	        	phieuLuongCNForm.setMaCN(lnv.getCongNhan().getMaCongNhan());
	        	phieuLuongCNForm.setHoVaTen(lnv.getCongNhan().getHoTen());
	        	phieuLuongCNForm.setSDT(lnv.getCongNhan().getSoDienThoai());
	        	phieuLuongCNForm.setCCCD(lnv.getCongNhan().getMaCCCD());
	        	phieuLuongCNForm.setSoNgayDiLam(lnv.getSoNgayDiLam());
	        	phieuLuongCNForm.setSoNgayNghi(lnv.getSoNgayNghi());
	        	phieuLuongCNForm.setSoPhepNghi(lnv.getSoPhepNghi());
	        	phieuLuongCNForm.setSoLuongSPLam(lnv.getSoLuongSanPhamLam());
	        	phieuLuongCNForm.setTongLuong(new BigDecimal(lnv.getTongLuong()));

	            list.add(phieuLuongCNForm);
	        }

	        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);
	        JasperPrint print = JasperFillManager.fillReport(report, parameters, dataSource);
	        JasperViewer.viewReport(print, false);

	    } catch (JRException e) {
	        e.printStackTrace();
	    }
	}
}
