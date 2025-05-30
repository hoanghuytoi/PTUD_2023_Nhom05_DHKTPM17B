package UI;

import Entity.ChamCongCongNhan;
import Entity.CongDoan;
import Entity.CongNhan;
import Entity.PhanCongCongNhan;
import Entity.SanPham;
import Entity.ToNhom;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import Dao.ChamCongCongNhan_Dao;
import Dao.CongDoan_Dao;
import Dao.CongNhan_Dao;
import Dao.PhanCongCongNhan_Dao;
import Dao.SanPham_Dao;
import Dao.ToNhom_Dao;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;


public class ChamCongCongNhan_GUI extends JPanel implements ActionListener, MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnCapNhat;
    private JButton btnChamCong;
    private JButton btnHuy;
    private JButton btnLayDanhSach;
    private JButton btnLuu;
    private JButton btnXoa;
    private JComboBox<String> cmbCaLam;
    private JComboBox<String> cmbGioDiLam;
    private JComboBox<String> cmbHienThi;
    private JComboBox<String> cmbMaCongDoan;
    private JComboBox<String> cmbMaSanPham;
    private JComboBox<String> cmbPhutDiLam;
    private JComboBox<String> cmbTenCongDoan;
    private JComboBox<String> cmbTenSanPham;
    private JComboBox<String> cmbTenToNhom;
    private JComboBox<String> cmbTrangThai;
    private JDateChooser dtcNgayChamCong;
    private JPanel pnlChamCongCN;
    private JSeparator jSeparator1;
    private JLabel lblCaLam;
    private JLabel lblErrSoLuongSP;
    private JLabel lblGioDiLam;
    private JLabel lblHienThi;
    private JLabel lblHoTen;
    private JLabel lblHoVaTenHienThi;
    private JLabel lblMaCongDoan;
    private JLabel lblMaCongNhan;
    private JLabel lblMaCongNhanHienThi;
    private JLabel lblMaSanPham;
    private JLabel lblNgayChamCong;
    private JLabel lblPhut;
    private JLabel lblSoLuongSanPham;
    private JLabel lblTenCongDoan;
    private JLabel lblTenCongDoanOutput;
    private JLabel lblTenCongDoanRight;
    private JLabel lblTenSanPham;
    private JLabel lblTenSanPhamOutput;
    private JLabel lblTenSanPhamRight;
    private JLabel lblToNhom;
    private JLabel lblTrangThai;
    private JScrollPane scrChamCong;
    private JScrollPane scrTableCongNhan;
    private JTable tblChamCong;
    private JTable tblCongNhan;
    private JTextField txtSoLuongLam;
    
    private DefaultTableModel modelTableCongNhan;
    private DefaultTableModel modelTableChamCong;
    private SanPham_Dao sanPham_DAO;
    private CongDoan_Dao congDoan_DAO;
    private ToNhom_Dao toNhom_DAO;
    private PhanCongCongNhan_Dao phanCong_DAO;
    private String maCongDoanFlag;
    private Date ngayChamCongFlag;
    private ChamCongCongNhan_Dao chamCongCN_DAO;
    private ItemListener itemMaCongDoan, itemTenCongDoan, itemMaSanPham, itemTenSanPham;

    private String stErrKhongDeTrong;
    private String stErrSoLuong;
    private String stThongbao;
    private String stBanXacNhanXoa;
    private String stXoaThanhCong;
    private String stXoaThatBai;
    private String stThemThanhCong;
    private String stThemThatBai;
    private String stCapNhatThanhCong;
    private String stCapNhatThatBai;
    private String stErrBeHonTongSanPhamCongDoan;
    private String stErrChamCong;
    private JSeparator jSeparator1_3;
    private JSeparator jSeparator1_4;
	private JCheckBox checkBoxCoMat;
	private JCheckBox checkBoxNghiCoPhep;
	private JButton btnTichTatCa;
	private JButton btnChamCongTatCa;

    public ChamCongCongNhan_GUI(String fileName) throws IOException {
    	initComponents();
        excute();
        lblErrSoLuongSP.setText("");
        try {
            ConnectionDB.ConnectDB.getInstance().connect();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        maCongDoanFlag = "";
        ngayChamCongFlag = new Date();
        btnChamCong.setEnabled(false);
        btnCapNhat.setEnabled(false);
        btnXoa.setEnabled(false);
        btnHuy.setEnabled(false);
        btnLuu.setEnabled(false);
        // add su kien cho cac button, table
        btnLayDanhSach.addActionListener(this);
        btnChamCong.addActionListener(this);
        btnChamCongTatCa.addActionListener(this);
        btnCapNhat.addActionListener(this);
        btnLuu.addActionListener(this);
        btnXoa.addActionListener(this);
        btnHuy.addActionListener(this);
        tblCongNhan.addMouseListener(this);
        tblChamCong.addMouseListener(this);
        cmbHienThi.addItemListener(this::hienThiChamCongTheoYeuCau);

        // chinh lai txt hien thi
        lblMaCongNhanHienThi.setText("");
        lblHoVaTenHienThi.setText("");
        lblTenSanPhamOutput.setText("");
        lblTenCongDoanOutput.setText("");

        sanPham_DAO = new SanPham_Dao();
        congDoan_DAO = new CongDoan_Dao();
        phanCong_DAO = new PhanCongCongNhan_Dao();
        chamCongCN_DAO = new ChamCongCongNhan_Dao();
        toNhom_DAO = new ToNhom_Dao();
        modelTableChamCong = (DefaultTableModel) tblChamCong.getModel();
        modelTableCongNhan = (DefaultTableModel) tblCongNhan.getModel();
        dtcNgayChamCong.setDate(new Date());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dtcNgayChamCong.getDate());
        if (calendar.get(Calendar.DAY_OF_WEEK) == 1) {
            setComBoBoxToSunDay();
        } else {
            setComBoBoxToReset();
        }

        taiDuLieuChamCongLenBang();
        itemMaSanPham = new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    if (cmbMaSanPham.getItemCount() != 0 && cmbTenSanPham.getItemCount() != 0) {
                        cmbTenSanPham.removeItemListener(itemTenSanPham);
                        cmbTenSanPham.setSelectedIndex(cmbMaSanPham.getSelectedIndex());
                        cmbTenSanPham.addItemListener(itemTenSanPham);
                        taiDuLieuLenCmbCongDoan();
                        reloadTableCongNhan();
                    }
                }
            }
        };
        itemTenSanPham = new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    if (cmbMaSanPham.getItemCount() != 0 && cmbTenSanPham.getItemCount() != 0) {
                        cmbMaSanPham.removeItemListener(itemMaSanPham);
                        cmbMaSanPham.setSelectedIndex(cmbTenSanPham.getSelectedIndex());
                        cmbMaSanPham.addItemListener(itemMaSanPham);
                        taiDuLieuLenCmbCongDoan();
                        reloadTableCongNhan();
                    }
                }
            }
        };
        itemMaCongDoan = new ItemListener() {
            public void itemStateChanged(ItemEvent itemEvent) {
                if (itemEvent.getStateChange() == ItemEvent.SELECTED) {

                    if (cmbTenCongDoan.getItemCount() != 0) {

                        cmbTenCongDoan.removeItemListener(itemTenCongDoan);
                        cmbTenCongDoan.setSelectedIndex(cmbMaCongDoan.getSelectedIndex());
                        cmbTenCongDoan.addItemListener(itemTenCongDoan);
                        reloadTableCongNhan();
                    }

                }
            }
        };
        itemTenCongDoan = new ItemListener() {
            public void itemStateChanged(ItemEvent itemEvent) {
                if (itemEvent.getStateChange() == ItemEvent.SELECTED) {

                    if (cmbMaCongDoan.getItemCount() != 0) {
                        cmbMaCongDoan.removeItemListener(itemMaCongDoan);
                        cmbMaCongDoan.setSelectedIndex(cmbTenCongDoan.getSelectedIndex());
                        cmbMaCongDoan.addItemListener(itemMaCongDoan);
                        reloadTableCongNhan();
                    }

                }
            }
        };
        cmbMaCongDoan.addItemListener(itemMaCongDoan);
        cmbTenCongDoan.addItemListener(itemTenCongDoan);
        taiDuLieuLenCmbToNhom();
        cmbTenCongDoan.addItemListener(this::comboBoxitemStateChangedTenCongDoan);
        cmbTenToNhom.addItemListener(this::comboBoxItemStateChangedToNhom);
        cmbMaSanPham.addItemListener(itemMaSanPham);
        cmbTenSanPham.addItemListener(itemTenSanPham);
        cmbCaLam.addItemListener(this::toggleThoiGianLam);
        cmbTrangThai.addItemListener(this::toggleCmbTrangThai);

        setCmbGio("Sáng");
        caiDatNgonNgu(fileName);
        setEditTextDateChooser();
    }

    public void reloadTableCongNhan() {
        while (tblCongNhan.getRowCount() > 0) {
                modelTableCongNhan.removeRow(0);
        }
    }

    public void setEditTextDateChooser() {
        JTextFieldDateEditor ngaySinh = (JTextFieldDateEditor) dtcNgayChamCong.getDateEditor();
        ngaySinh.setEnabled(false);
    }

    public void caiDatNgonNgu(String fileName) throws FileNotFoundException, IOException {
        FileInputStream fis = new FileInputStream(fileName);
        Properties prop = new Properties();
        prop.load(fis);
        btnCapNhat.setText(prop.getProperty("btnCapNhat"));
        btnLuu.setText(prop.getProperty("btnLuu"));
        btnHuy.setText(prop.getProperty("btnHuy"));
        btnXoa.setText(prop.getProperty("btnXoa"));
		btnChamCongTatCa.setText(prop.getProperty("chamCong_btnChamCongTatCa"));
        btnChamCong.setText(prop.getProperty("chamCong_btnChamCong"));
        btnLayDanhSach.setText(prop.getProperty("cccc_layDanhSach"));
		btnTichTatCa.setText(prop.getProperty("btnTichTatCa"));

        lblToNhom.setText(prop.getProperty("cccc_toNhom"));
        lblNgayChamCong.setText(prop.getProperty("cccc_ngayCham"));
        lblMaSanPham.setText(prop.getProperty("cccc_maSanPham"));
        lblTenSanPham.setText(prop.getProperty("cccc_tenSanPham"));
        lblMaCongDoan.setText(prop.getProperty("cccc_maCongDoan"));
        lblTenCongDoan.setText(prop.getProperty("cccc_tenCongDoan"));
        lblMaCongNhan.setText(prop.getProperty("cccc_maCongNhan"));
        lblHoTen.setText(prop.getProperty("cccc_hoVaTen"));
        lblTrangThai.setText(prop.getProperty("cccc_trangThai"));
        lblCaLam.setText(prop.getProperty("cccc_caLam"));
        lblGioDiLam.setText(prop.getProperty("cccc_gioLam"));
        lblPhut.setText(prop.getProperty("cccc_phut"));
        lblTenSanPhamRight.setText(prop.getProperty("cccc_sanPham"));
        lblTenCongDoanRight.setText(prop.getProperty("cccc_congDoanLam"));
        lblSoLuongSanPham.setText(prop.getProperty("cccc_soLuongLam"));
        lblHienThi.setText(prop.getProperty("chamCong_hienThi"));

        scrTableCongNhan.setBorder(new TitledBorder(prop.getProperty("cccc_tieuDe1")));
        scrChamCong.setBorder(new TitledBorder(prop.getProperty("cccc_tieuDe2")));

        cmbHienThi.removeAllItems();
        cmbHienThi.addItem(prop.getProperty("cmbHienThiAll"));
        cmbHienThi.addItem(prop.getProperty("cmbHienThiLoc"));

        ChangeName(tblCongNhan, 0, prop.getProperty("cccc_stt"));
        ChangeName(tblCongNhan, 1, lblMaCongNhan.getText());
        ChangeName(tblCongNhan, 2, lblHoTen.getText());
        ChangeName(tblCongNhan, 3, prop.getProperty("chamCong_coMat"));
        ChangeName(tblCongNhan, 4, prop.getProperty("chamCong_nghiCoPhep"));
        ChangeName(tblCongNhan, 5, prop.getProperty("cccc_soLuongSPCNLam"));

        ChangeName(tblChamCong, 0, prop.getProperty("cccc_stt"));
        ChangeName(tblChamCong, 1, prop.getProperty("cccc_maPhanCong"));
        ChangeName(tblChamCong, 2, lblMaCongNhan.getText());
        ChangeName(tblChamCong, 3, lblHoTen.getText());
        ChangeName(tblChamCong, 4, lblToNhom.getText());
        ChangeName(tblChamCong, 5, lblNgayChamCong.getText());
        ChangeName(tblChamCong, 6, lblCaLam.getText());
        ChangeName(tblChamCong, 7, lblTrangThai.getText());
        ChangeName(tblChamCong, 8, lblGioDiLam.getText());
        ChangeName(tblChamCong, 9, lblMaSanPham.getText());
        ChangeName(tblChamCong, 10, lblTenSanPham.getText());
        ChangeName(tblChamCong, 11, lblMaCongDoan.getText());
        ChangeName(tblChamCong, 12, lblTenCongDoan.getText());
        ChangeName(tblChamCong, 13, lblSoLuongSanPham.getText());

        stErrBeHonTongSanPhamCongDoan = prop.getProperty("lblErrSoLuongLam");
        stThongbao = prop.getProperty("thongBao");
        stBanXacNhanXoa = prop.getProperty("banXacNhanXoa");
        stXoaThanhCong = prop.getProperty("xoaThanhCong");
        stXoaThatBai = prop.getProperty("xoaThatBai");
        stThemThanhCong = prop.getProperty("cccc_themThanhCong");
        stThemThatBai = prop.getProperty("cccc_themThatBai");
        stCapNhatThanhCong = prop.getProperty("capNhatThanhCong");
        stCapNhatThatBai = prop.getProperty("capNhatThatBai");
        stErrSoLuong = prop.getProperty("sp_lblErrSoLuong");
        stErrKhongDeTrong = prop.getProperty("KhongDeTrong");
        stErrChamCong = prop.getProperty("chamCong_errNgayChamCong");
    }

    public void ChangeName(JTable table, int col_index, String col_name) {
        table.getColumnModel().getColumn(col_index).setHeaderValue(col_name);
    }

    public void hienThiChamCongTheoYeuCau(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            if (cmbHienThi.getSelectedIndex() == 0) {
                taiDuLieuChamCongLenBang();
            } else {
                hienThiChamCongTheoNgay();
            }
        }
    }

    public void hienThiChamCongTheoNgay() {
        Date ngayChon = dtcNgayChamCong.getDate();
        while (tblChamCong.getRowCount() != 0) {
            modelTableChamCong.removeRow(0);
        }
        ArrayList<ChamCongCongNhan> dsChamCong = chamCongCN_DAO.layDanhSachChamCongTheoNgay(ngayChon);
        for (ChamCongCongNhan cccn : dsChamCong) {
            String data[] = {(modelTableChamCong.getRowCount() + 1) + "", cccn.getPhanCong().getMaPhanCong(), cccn.getPhanCong().getCongNhan().getMaCongNhan(),
                cccn.getPhanCong().getCongNhan().getHoTen(), cccn.getPhanCong().getCongNhan().getToNhom().getTenToNhom(),
                cccn.getNgayChamCong().toString(), cccn.getCaLam(), cccn.getTrangThaiDiLam(), cccn.getGioDiLam(), cccn.getPhanCong().getCongDoan().getSanPham().getMaSanPham(),
                cccn.getPhanCong().getCongDoan().getSanPham().getTenSanPham(),
                cccn.getPhanCong().getCongDoan().getMaCongDoan(), cccn.getPhanCong().getCongDoan().getTenCongDoan(), cccn.getSoLuongLam() + ""};
            modelTableChamCong.addRow(data);
        }

    }

    public void comboBoxItemStateChangedToNhom(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            taiDuLieuLenCmbSanPham();
            reloadTableCongNhan();
        }
    }

    public void taiDuLieuLenCmbSanPham() {
        cmbMaSanPham.removeItemListener(itemMaSanPham);
        cmbTenSanPham.removeItemListener(itemTenSanPham);
        String tenToNhom = cmbTenToNhom.getSelectedItem().toString();
        ToNhom toNhom = toNhom_DAO.layMotToNhomTheoTen(tenToNhom);
        if (cmbMaSanPham.getItemCount() != 0 || cmbTenSanPham.getItemCount() != 0) {
            cmbMaSanPham.removeAllItems();
            cmbTenSanPham.removeAllItems();
        }
        ArrayList<SanPham> dsSanPham = sanPham_DAO.layDanhSachSanPhamDuocPhanCongChoTo(toNhom.getMaToNhom());
        for (SanPham sp : dsSanPham) {
            cmbMaSanPham.addItem(sp.getMaSanPham());
            cmbTenSanPham.addItem(sp.getTenSanPham());
        }

        if (dsSanPham.size() == 0) {
            cmbMaCongDoan.removeAllItems();
            cmbTenCongDoan.removeAllItems();
            setEnAbleForCmb(false);
        } else {
            setEnAbleForCmb(true);
        }
        if (cmbMaSanPham.getItemCount() != 0) {
            taiDuLieuLenCmbCongDoan();
        }
        cmbMaSanPham.addItemListener(itemMaSanPham);
        cmbTenSanPham.addItemListener(itemTenSanPham);
    }

    public void setEnAbleForCmb(boolean kq) {
        cmbMaCongDoan.setEnabled(kq);
        cmbTenCongDoan.setEnabled(kq);
        cmbTenSanPham.setEnabled(kq);
        cmbMaSanPham.setEnabled(kq);
        cmbMaCongDoan.setEnabled(kq);
        cmbTenCongDoan.setEnabled(kq);
        btnLayDanhSach.setEnabled(kq);
    }

    public void comboBoxitemStateChangedTenCongDoan(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            if (cmbMaCongDoan.getItemCount() != 0) {
                cmbMaCongDoan.setSelectedIndex(cmbTenCongDoan.getSelectedIndex());
            }
        }
    }

    public void toggleThoiGianLam(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            if (e.getItem().toString().equalsIgnoreCase("Sáng") || e.getItem().toString().equalsIgnoreCase("Sáng chủ nhật")) {
                setCmbGio("Sáng");
            } else if (e.getItem().toString().equalsIgnoreCase("Chiều") || e.getItem().toString().equalsIgnoreCase("Chiều chủ nhật")) {
                setCmbGio("Chiều");
            } else {
                setCmbGio("Đêm");
            }
        }
    }

    public void taiDuLieuLenCmbToNhom() {
        cmbTenToNhom.removeItemListener(this::comboBoxItemStateChangedToNhom);
        cmbTenToNhom.removeAllItems();
		CongNhan_Dao congNhan_dao = new CongNhan_Dao();
        ArrayList<ToNhom> dsToNhom = toNhom_DAO.layDanhSachToNhom();
        for (ToNhom toNhom : dsToNhom) {
	        int soLuongCongNhan = congNhan_dao.laySoLuongCongNhanTheoToNhom(toNhom.getMaToNhom());
            if (soLuongCongNhan > 0) {
                cmbTenToNhom.addItem(toNhom.getTenToNhom());
            }
        }
        if (cmbTenToNhom.getItemCount() != 0) {
            ToNhom toNhom = toNhom_DAO.layMotToNhomTheoTen(cmbTenToNhom.getSelectedItem().toString());
            if (toNhom != null) {
                taiDuLieuLenCmbSanPham();
            }
        }
        cmbTenToNhom.addItemListener(this::comboBoxItemStateChangedToNhom);
    }

    public void setCmbGio(String ca) {
        if (ca.equalsIgnoreCase("Sáng") || ca.equalsIgnoreCase("Sáng chủ nhật")) {
            cmbGioDiLam.removeAllItems();
            for (int i = 6; i <= 14; i++) {
                cmbGioDiLam.addItem(i + "");
            }
        } else if (ca.equalsIgnoreCase("Chiều") || ca.equalsIgnoreCase("Chiều chủ nhật")) {
            cmbGioDiLam.removeAllItems();
            for (int i = 14; i <= 22; i++) {
                cmbGioDiLam.addItem(i + "");
            }
        } else {
            cmbGioDiLam.removeAllItems();
            cmbGioDiLam.addItem("22");
            cmbGioDiLam.addItem("23");
            cmbGioDiLam.addItem("24");
            cmbGioDiLam.addItem("1");
            cmbGioDiLam.addItem("2");
            cmbGioDiLam.addItem("3");
            cmbGioDiLam.addItem("4");
            cmbGioDiLam.addItem("5");
            cmbGioDiLam.addItem("6");
        }
    }

    public void toggleCmbTrangThai(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            String trangThai = e.getItem().toString();
            if (trangThai.equalsIgnoreCase("Nghỉ có phép") || trangThai.equalsIgnoreCase("Nghỉ không phép")) {
                cmbGioDiLam.setEnabled(false);
                cmbPhutDiLam.setEnabled(false);
                txtSoLuongLam.setText("0");
                txtSoLuongLam.setEditable(false);
                cmbGioDiLam.addItem("0");
                cmbGioDiLam.setSelectedItem("0");
                cmbPhutDiLam.setSelectedIndex(0);
            } else {
                cmbGioDiLam.removeItem("0");
                if (tblChamCong.getSelectedRow() != -1 && btnCapNhat.isEnabled()) {
                    cmbGioDiLam.setEnabled(false);
                    cmbPhutDiLam.setEnabled(false);
                    txtSoLuongLam.setEditable(false);
                } else if (!btnCapNhat.isEnabled() || tblCongNhan.getSelectedRow() != -1) {
                    cmbGioDiLam.setEnabled(true);
                    cmbPhutDiLam.setEnabled(true);
                    txtSoLuongLam.setEditable(true);
                }
            }

        }
    }

    public void excute() {
        // custom table
        tblCongNhan.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tblCongNhan.getTableHeader().setOpaque(false);
        ((DefaultTableCellRenderer) tblCongNhan.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER);
        tblCongNhan.setRowHeight(25);

        tblChamCong.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tblChamCong.getTableHeader().setOpaque(false);
        ((DefaultTableCellRenderer) tblChamCong.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER);
        tblChamCong.setRowHeight(25);
        this.txtSoLuongLam.setBackground(new Color(255, 255, 255));
    }

    public void taiDuLieuLenCmbCongDoan() {
        cmbMaCongDoan.removeItemListener(itemMaCongDoan);
        cmbTenCongDoan.removeItemListener(itemTenCongDoan);
        cmbMaCongDoan.removeAllItems();
        cmbTenCongDoan.removeAllItems();
        String maToNhom = toNhom_DAO.layMotToNhomTheoTen(cmbTenToNhom.getSelectedItem().toString()).getMaToNhom();
        String maSanPham = cmbMaSanPham.getSelectedItem().toString();
        ArrayList<CongDoan> dsCongDoan = congDoan_DAO.layDanhSachCongDoanDuocPhanCongTheoMaSpMaTN(maToNhom, maSanPham);
        for (CongDoan cd : dsCongDoan) {
            cmbMaCongDoan.addItem(cd.getMaCongDoan());
            cmbTenCongDoan.addItem(cd.getTenCongDoan());
        }
        if (cmbMaCongDoan.getItemCount() == 0 || cmbTenCongDoan.getItemCount() == 0) {
            btnLayDanhSach.setEnabled(false);
            cmbMaCongDoan.setEnabled(false);
            cmbTenCongDoan.setEnabled(false);
        } else {
            btnLayDanhSach.setEnabled(true);
            cmbMaCongDoan.setEnabled(true);
            cmbTenCongDoan.setEnabled(true);
        }
        cmbMaCongDoan.addItemListener(itemMaCongDoan);
        cmbTenCongDoan.addItemListener(itemTenCongDoan);
    }

    
    @SuppressWarnings("serial")
	private void initComponents() {
        pnlChamCongCN = new JPanel();
        pnlChamCongCN.setBounds(0, 0, 1290, 450);
        
        dtcNgayChamCong = new JDateChooser();
        dtcNgayChamCong.setBounds(440, 20, 130, 40);
        
        scrTableCongNhan = new JScrollPane();
        scrTableCongNhan.setBounds(20, 190, 810, 180);
        
        tblCongNhan = new JTable();
        lblNgayChamCong = new JLabel();
        lblNgayChamCong.setBounds(290, 20, 130, 40);
        
        lblSoLuongSanPham = new JLabel();
        lblSoLuongSanPham.setBounds(880, 324, 150, 30);
        
        lblMaCongNhanHienThi = new JLabel();
        lblMaCongNhanHienThi.setBounds(1040, 9, 180, 30);
        
        lblHoVaTenHienThi = new javax.swing.JLabel();
        lblHoVaTenHienThi.setBounds(1040, 50, 180, 30);
        
        lblMaCongNhan = new javax.swing.JLabel();
        lblMaCongNhan.setBounds(880, 9, 140, 40);
        
        lblHoTen = new javax.swing.JLabel();
        lblHoTen.setBounds(880, 68, 140, 21);
        
        lblTenCongDoanRight = new javax.swing.JLabel();
        lblTenCongDoanRight.setBounds(880, 273, 150, 30);
        
        lblTenSanPhamRight = new javax.swing.JLabel();
        lblTenSanPhamRight.setBounds(880, 221, 120, 30);
        
        lblTenSanPhamOutput = new javax.swing.JLabel();
        lblTenSanPhamOutput.setBounds(1040, 208, 180, 30);
        
        lblTenCongDoanOutput = new javax.swing.JLabel();
        lblTenCongDoanOutput.setBounds(1040, 259, 180, 30);
        
        txtSoLuongLam = new javax.swing.JTextField();
        txtSoLuongLam.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        txtSoLuongLam.setBounds(1040, 314, 180, 30);
        
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator1.setForeground(new Color(0, 0, 0));
        jSeparator1.setBounds(1040, 344, 180, 10);
        
        lblErrSoLuongSP = new javax.swing.JLabel();
        lblErrSoLuongSP.setBounds(1040, 349, 240, 21);
        
        cmbMaCongDoan = new javax.swing.JComboBox<>();
        cmbMaCongDoan.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        cmbMaCongDoan.setBounds(140, 140, 120, 40);
        
        lblTrangThai = new javax.swing.JLabel();
        lblTrangThai.setBounds(880, 118, 140, 30);
        
        lblMaCongDoan = new javax.swing.JLabel();
        lblMaCongDoan.setBounds(20, 140, 120, 40);
        
        cmbCaLam = new javax.swing.JComboBox<>();
        cmbCaLam.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        cmbCaLam.setBounds(680, 20, 150, 40);
        
        cmbGioDiLam = new javax.swing.JComboBox<>();
        cmbGioDiLam.setBounds(1040, 173, 60, 30);
        lblGioDiLam = new javax.swing.JLabel();
        lblGioDiLam.setBounds(880, 172, 140, 30);
        
        lblPhut = new javax.swing.JLabel();
        lblPhut.setBounds(1110, 173, 40, 30);
        
        cmbPhutDiLam = new javax.swing.JComboBox<>();
        cmbPhutDiLam.setBounds(1160, 174, 60, 29);
        
        cmbTrangThai = new javax.swing.JComboBox<>();
        cmbTrangThai.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        cmbTrangThai.setBounds(1040, 109, 180, 30);
        
        lblCaLam = new javax.swing.JLabel();
        lblCaLam.setBounds(590, 20, 90, 40);
        
        lblMaSanPham = new javax.swing.JLabel();
        lblMaSanPham.setBounds(20, 80, 110, 30);
        
        btnLayDanhSach = new javax.swing.JButton();
        btnLayDanhSach.setBounds(620, 140, 210, 40);
        
        lblTenSanPham = new javax.swing.JLabel();
        lblTenSanPham.setBounds(290, 80, 120, 30);
        
        cmbTenCongDoan = new javax.swing.JComboBox<>();
        cmbTenCongDoan.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        cmbTenCongDoan.setBounds(440, 140, 160, 40);
        lblTenCongDoan = new javax.swing.JLabel();
        lblTenCongDoan.setBounds(290, 140, 120, 40);
        
        lblHienThi = new javax.swing.JLabel();
        lblHienThi.setBounds(20, 409, 60, 21);
        cmbHienThi = new javax.swing.JComboBox<>();
        cmbHienThi.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        cmbHienThi.setBounds(90, 398, 95, 40);
        
        cmbTenToNhom = new javax.swing.JComboBox<>();
        cmbTenToNhom.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        cmbTenToNhom.setBounds(140, 20, 120, 40);
        
        lblToNhom = new javax.swing.JLabel();
        lblToNhom.setBounds(20, 20, 90, 40);
        
        btnChamCong = new javax.swing.JButton();
        btnChamCong.setBounds(383, 399, 140, 40);
        
        btnXoa = new javax.swing.JButton();
        btnXoa.setBounds(855, 399, 120, 40);
        
        btnCapNhat = new javax.swing.JButton();
        btnCapNhat.setBackground(new Color(255, 215, 0));
        btnCapNhat.setBounds(702, 399, 130, 40);
        
        btnLuu = new javax.swing.JButton();
        btnLuu.setBounds(998, 399, 120, 40);
        
        btnHuy = new javax.swing.JButton();
        btnHuy.setBackground(new Color(255, 0, 255));
        btnHuy.setBounds(1140, 397, 140, 40);
        
        cmbMaSanPham = new javax.swing.JComboBox<>();
        cmbMaSanPham.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        cmbMaSanPham.setBounds(140, 80, 120, 40);
        
        cmbTenSanPham = new javax.swing.JComboBox<>();
        cmbTenSanPham.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        cmbTenSanPham.setBounds(440, 80, 390, 40);
        
        scrChamCong = new javax.swing.JScrollPane();
        scrChamCong.setBounds(21, 471, 1242, 250);
        tblChamCong = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(1290, 750));

        pnlChamCongCN.setBackground(new java.awt.Color(255, 255, 255));
        pnlChamCongCN.setPreferredSize(new java.awt.Dimension(1250, 450));

        dtcNgayChamCong.setDateFormatString("yyyy-MM-dd");
        dtcNgayChamCong.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dtcNgayChamCongPropertyChange(evt);
            }
        });
        pnlChamCongCN.setLayout(null);
        pnlChamCongCN.add(dtcNgayChamCong);

        scrTableCongNhan.setBackground(new java.awt.Color(255, 255, 255));
        scrTableCongNhan.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách chấm công", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 16))); // NOI18N

        tblCongNhan.setFont(new java.awt.Font("Segoe UI", 0, 13));
        tblCongNhan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            	{null, null, null, null, null, null},
            	{null, null, null, null, null, null},
            	{null, null, null, null, null, null},
            	{null, null, null, null, null, null},
            	{null, null, null, null, null, null},
            	{null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã công nhân", "Họ và tên", "Có mặt", "Nghỉ có phép", "Số lượng SP làm được"
            }
        		) {
		    @Override
		    public Class<?> getColumnClass(int columnIndex) {
		        return columnIndex == 3 || columnIndex == 4 ? Boolean.class : columnIndex == 5 ? Integer.class : Object.class;
		    }

		    @Override
		    public Object getValueAt(int row, int column) {
		        if (column == 5) {
		            return super.getValueAt(row, column);
		        }
		        return super.getValueAt(row, column);
		    }

		    @Override
		    public boolean isCellEditable(int row, int column) {
		        return column == 3 || column == 4;
		    }
		});
        
        checkBoxCoMat = new JCheckBox();
        tblCongNhan.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(checkBoxCoMat));

		checkBoxNghiCoPhep = new JCheckBox();
		tblCongNhan.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(checkBoxNghiCoPhep));

		// Thêm ItemListener cho checkbox "Có mặt"
		ItemListener coMatListener = new ItemListener() {
		    @Override
		    public void itemStateChanged(ItemEvent e) {
		        int selectedRow = tblCongNhan.getSelectedRow();
		        if (selectedRow != -1 && e.getStateChange() == ItemEvent.SELECTED) {
		            // Nếu ô "Có mặt" được tích, hủy tích ô "Nghỉ có phép"
		            modelTableCongNhan.setValueAt(true, selectedRow, 3);  // Chọn "Có mặt"
		            modelTableCongNhan.setValueAt(false, selectedRow, 4); // Hủy chọn "Nghỉ có phép"
		        }
		    }
		};

		// Thêm ItemListener cho checkbox "Nghỉ có phép"
		ItemListener nghiCoPhepListener = new ItemListener() {
		    @Override
		    public void itemStateChanged(ItemEvent e) {
		        int selectedRow = tblCongNhan.getSelectedRow();
		        if (selectedRow != -1 && e.getStateChange() == ItemEvent.SELECTED) {
		            // Nếu ô "Nghỉ có phép" được tích, hủy tích ô "Có mặt"
		        	modelTableCongNhan.setValueAt(true, selectedRow, 4);  // Chọn "Nghỉ có phép"
		        	modelTableCongNhan.setValueAt(false, selectedRow, 3); // Hủy chọn "Có mặt"
		        }
		    }
		};

		checkBoxCoMat.addItemListener(coMatListener);
		checkBoxNghiCoPhep.addItemListener(nghiCoPhepListener);
        
        tblCongNhan.getTableHeader().setBackground(new Color(128, 200, 255));
		tblCongNhan.setSelectionBackground(new Color(255, 215, 0));
        
		tblCongNhan.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent evt) {
		        tblCongNhanMouseClicked(evt);
		        int column = tblCongNhan.columnAtPoint(evt.getPoint());
		        int row = tblCongNhan.rowAtPoint(evt.getPoint());

		        if (column == 5 && row != -1) {
		            Boolean isCoMat = (Boolean) modelTableCongNhan.getValueAt(row, 3);
		            Boolean isNghiCoPhep = (Boolean) modelTableCongNhan.getValueAt(row, 4);

		            if (isCoMat != null && isCoMat) {
		                if (isNghiCoPhep == null || !isNghiCoPhep) {
		                    String newOvertime = JOptionPane.showInputDialog("Nhập số lượng sản phẩm công nhân làm: ");
		                    if (newOvertime != null && !newOvertime.isEmpty()) {
		                        try {
		                            int quantity = Integer.parseInt(newOvertime);
		                            modelTableCongNhan.setValueAt(quantity, row, 5);
		                        } catch (NumberFormatException e) {
		                            JOptionPane.showMessageDialog(null, "Vui lòng nhập một số nguyên cho số lượng!!!", "Lỗi", JOptionPane.ERROR_MESSAGE);
		                        }
		                    }
		                } else {
		                    JOptionPane.showMessageDialog(null, "Công nhân đang nghỉ có phép! Không thể nhập số lượng!!!", "Lỗi", JOptionPane.ERROR_MESSAGE);
		                }
		            } else {
		                JOptionPane.showMessageDialog(null, "Công nhân không có mặt! Không thể nhập số lượng!!!", "Lỗi", JOptionPane.ERROR_MESSAGE);
		            }
		        }
		    }
		});
		
        scrTableCongNhan.setViewportView(tblCongNhan);
        pnlChamCongCN.add(scrTableCongNhan);

        lblNgayChamCong.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        lblNgayChamCong.setText("Ngày chấm công:");
        pnlChamCongCN.add(lblNgayChamCong);

        lblSoLuongSanPham.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        lblSoLuongSanPham.setHorizontalAlignment(SwingConstants.LEFT);
        lblSoLuongSanPham.setText("Số lượng sản phẩm:");
        pnlChamCongCN.add(lblSoLuongSanPham);

        lblMaCongNhanHienThi.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lblMaCongNhanHienThi.setText("NV001");
        pnlChamCongCN.add(lblMaCongNhanHienThi);

        lblHoVaTenHienThi.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lblHoVaTenHienThi.setText("Nguyễn Văn A");
        pnlChamCongCN.add(lblHoVaTenHienThi);

        lblMaCongNhan.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        lblMaCongNhan.setHorizontalAlignment(SwingConstants.LEFT);
        lblMaCongNhan.setText("Mã công nhân:");
        pnlChamCongCN.add(lblMaCongNhan);

        lblHoTen.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        lblHoTen.setHorizontalAlignment(SwingConstants.LEFT);
        lblHoTen.setText("Họ và tên:");
        pnlChamCongCN.add(lblHoTen);

        lblTenCongDoanRight.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        lblTenCongDoanRight.setHorizontalAlignment(SwingConstants.LEFT);
        lblTenCongDoanRight.setText("Công đoạn làm:");
        pnlChamCongCN.add(lblTenCongDoanRight);

        lblTenSanPhamRight.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        lblTenSanPhamRight.setHorizontalAlignment(SwingConstants.LEFT);
        lblTenSanPhamRight.setText("Sản phẩm:");
        pnlChamCongCN.add(lblTenSanPhamRight);

        lblTenSanPhamOutput.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lblTenSanPhamOutput.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTenSanPhamOutput.setText("Giày loại 1");
        pnlChamCongCN.add(lblTenSanPhamOutput);

        lblTenCongDoanOutput.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lblTenCongDoanOutput.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTenCongDoanOutput.setText("Sơn màu");
        pnlChamCongCN.add(lblTenCongDoanOutput);
        txtSoLuongLam.setBorder(null);
        pnlChamCongCN.add(txtSoLuongLam);
        pnlChamCongCN.add(jSeparator1);

        lblErrSoLuongSP.setFont(new java.awt.Font("Segoe UI", 0, 11));
        lblErrSoLuongSP.setForeground(new java.awt.Color(204, 0, 0));
        lblErrSoLuongSP.setText("đây là dòng thông báo lỗi");
        pnlChamCongCN.add(lblErrSoLuongSP);
        
        JSeparator jSeparator1_1 = new JSeparator();
        jSeparator1_1.setForeground(Color.BLACK);
        jSeparator1_1.setBounds(1040, 293, 180, 10);
        pnlChamCongCN.add(jSeparator1_1);
        
        JSeparator jSeparator1_2 = new JSeparator();
        jSeparator1_2.setForeground(Color.BLACK);
        jSeparator1_2.setBounds(1040, 241, 180, 10);
        pnlChamCongCN.add(jSeparator1_2);
        
        jSeparator1_3 = new JSeparator();
        jSeparator1_3.setForeground(Color.BLACK);
        jSeparator1_3.setBounds(1040, 80, 180, 10);
        pnlChamCongCN.add(jSeparator1_3);
        
        jSeparator1_4 = new JSeparator();
        jSeparator1_4.setForeground(Color.BLACK);
        jSeparator1_4.setBounds(1040, 39, 180, 10);
        pnlChamCongCN.add(jSeparator1_4);

        cmbMaCongDoan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {}));
        cmbMaCongDoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbMaCongDoanActionPerformed(evt);
            }
        });
        pnlChamCongCN.add(cmbMaCongDoan);

        lblTrangThai.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        lblTrangThai.setHorizontalAlignment(SwingConstants.LEFT);
        lblTrangThai.setText("Trạng thái:");
        pnlChamCongCN.add(lblTrangThai);

        lblMaCongDoan.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        lblMaCongDoan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblMaCongDoan.setText("Mã công đoạn:");
        pnlChamCongCN.add(lblMaCongDoan);

        cmbCaLam.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {}));
        cmbCaLam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCaLamActionPerformed(evt);
            }
        });
        pnlChamCongCN.add(cmbCaLam);

        cmbGioDiLam.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        cmbGioDiLam.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {}));
        pnlChamCongCN.add(cmbGioDiLam);

        lblGioDiLam.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        lblGioDiLam.setHorizontalAlignment(SwingConstants.LEFT);
        lblGioDiLam.setText("Giờ đi làm:");
        pnlChamCongCN.add(lblGioDiLam);

        lblPhut.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lblPhut.setHorizontalAlignment(SwingConstants.LEFT);
        lblPhut.setText("Phút");
        pnlChamCongCN.add(lblPhut);

        cmbPhutDiLam.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        cmbPhutDiLam.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00", "15", "30", "45" }));
        pnlChamCongCN.add(cmbPhutDiLam);

        cmbTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đi làm", "Nghỉ có phép", "Nghỉ không phép" }));
        pnlChamCongCN.add(cmbTrangThai);

        lblCaLam.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        lblCaLam.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblCaLam.setText("Ca làm:");
        lblCaLam.setToolTipText("");
        pnlChamCongCN.add(lblCaLam);

        lblMaSanPham.setFont(new Font("Times New Roman", Font.PLAIN, 18)); 
        lblMaSanPham.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblMaSanPham.setText("Mã sản phẩm:");
        pnlChamCongCN.add(lblMaSanPham);

        btnLayDanhSach.setBackground(new java.awt.Color(46, 204, 113));
        btnLayDanhSach.setFont(new Font("Times New Roman", Font.BOLD, 18)); 
        btnLayDanhSach.setIcon(new ImageIcon(ChamCongCongNhan_GUI.class.getResource("/image/icon/them.png"))); 
        btnLayDanhSach.setText("Lấy danh sách");
        btnLayDanhSach.setBorder(null);
        btnLayDanhSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLayDanhSachActionPerformed(evt);
            }
        });
        pnlChamCongCN.add(btnLayDanhSach);

        lblTenSanPham.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        lblTenSanPham.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTenSanPham.setText("Tên sản phẩm:");
        pnlChamCongCN.add(lblTenSanPham);

        cmbTenCongDoan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {}));
        pnlChamCongCN.add(cmbTenCongDoan);

        lblTenCongDoan.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        lblTenCongDoan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTenCongDoan.setText("Công đoạn:");
        pnlChamCongCN.add(lblTenCongDoan);

        lblHienThi.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        lblHienThi.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblHienThi.setText("Hiển thị");
        pnlChamCongCN.add(lblHienThi);

        cmbHienThi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Theo ngày chấm công" }));
        cmbHienThi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbHienThiActionPerformed(evt);
            }
        });
        pnlChamCongCN.add(cmbHienThi);

        cmbTenToNhom.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {}));
        cmbTenToNhom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTenToNhomActionPerformed(evt);
            }
        });
        pnlChamCongCN.add(cmbTenToNhom);

        lblToNhom.setFont(new Font("Times New Roman", Font.PLAIN, 18)); 
        lblToNhom.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblToNhom.setText("Tổ/nhóm:");
        pnlChamCongCN.add(lblToNhom);

        btnChamCong.setBackground(new java.awt.Color(46, 204, 113));
        btnChamCong.setFont(new Font("Times New Roman", Font.BOLD, 18)); 
        btnChamCong.setIcon(new ImageIcon(ChamCongCongNhan_GUI.class.getResource("/image/icon/them.png"))); 
        btnChamCong.setText("Chấm công");
        btnChamCong.setBorder(null);
        btnChamCong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChamCongActionPerformed(evt);
            }
        });
        pnlChamCongCN.add(btnChamCong);

        btnXoa.setBackground(new Color(255, 215, 0));
        btnXoa.setFont(new Font("Times New Roman", Font.BOLD, 18)); 
        btnXoa.setIcon(new ImageIcon(ChamCongCongNhan_GUI.class.getResource("/image/icon/xoa.png"))); 
        btnXoa.setText("Xóa");
        btnXoa.setBorder(null);
        btnXoa.setMaximumSize(new java.awt.Dimension(67, 31));
        btnXoa.setMinimumSize(new java.awt.Dimension(67, 31));
        btnXoa.setPreferredSize(new java.awt.Dimension(67, 31));
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        pnlChamCongCN.add(btnXoa);

        btnCapNhat.setFont(new Font("Times New Roman", Font.BOLD, 18));
        btnCapNhat.setIcon(new ImageIcon(ChamCongCongNhan_GUI.class.getResource("/image/icon/capNhat.png")));
        btnCapNhat.setText("Cập nhật");
        btnCapNhat.setBorder(null);
        btnCapNhat.setMaximumSize(new java.awt.Dimension(67, 31));
        btnCapNhat.setMinimumSize(new java.awt.Dimension(67, 31));
        btnCapNhat.setPreferredSize(new java.awt.Dimension(67, 31));
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });
        pnlChamCongCN.add(btnCapNhat);

        btnLuu.setBackground(new Color(255, 0, 255));
        btnLuu.setFont(new Font("Times New Roman", Font.BOLD, 18));
        btnLuu.setIcon(new ImageIcon(ChamCongCongNhan_GUI.class.getResource("/image/icon/luu.png")));
        btnLuu.setText("Lưu");
        btnLuu.setBorder(null);
        btnLuu.setMaximumSize(new java.awt.Dimension(67, 31));
        btnLuu.setMinimumSize(new java.awt.Dimension(67, 31));
        btnLuu.setPreferredSize(new java.awt.Dimension(67, 31));
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });
        pnlChamCongCN.add(btnLuu);

        btnHuy.setFont(new Font("Times New Roman", Font.BOLD, 18));
        btnHuy.setIcon(new ImageIcon(ChamCongCongNhan_GUI.class.getResource("/image/icon/huy.png")));
        btnHuy.setText("Hủy");
        btnHuy.setBorder(null);
        btnHuy.setMaximumSize(new java.awt.Dimension(67, 31));
        btnHuy.setMinimumSize(new java.awt.Dimension(67, 31));
        btnHuy.setPreferredSize(new java.awt.Dimension(67, 31));
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });
        pnlChamCongCN.add(btnHuy);

        btnTichTatCa = new JButton();
        btnTichTatCa.setIcon(new ImageIcon(ChamCongCongNhan_GUI.class.getResource("/image/icon/them.png")));
        btnTichTatCa.setText("Có mặt tất cả");
        btnTichTatCa.setFont(new Font("Times New Roman", Font.BOLD, 18));
        btnTichTatCa.setBorder(null);
        btnTichTatCa.setBackground(new Color(46, 204, 113));
        btnTichTatCa.setBounds(540, 399, 140, 40);
        pnlChamCongCN.add(btnTichTatCa);
        btnTichTatCa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int rowCount = tblCongNhan.getRowCount();
				for (int i = 0; i < rowCount; i++) {
					tblCongNhan.getModel().setValueAt(true, i, 3);
				}
			}
		});
        
        btnChamCongTatCa = new JButton();
        btnChamCongTatCa.setIcon(new ImageIcon(ChamCongCongNhan_GUI.class.getResource("/image/icon/them.png")));
        btnChamCongTatCa.setText("Chấm công tất cả");
        btnChamCongTatCa.setFont(new Font("Times New Roman", Font.BOLD, 18));
        btnChamCongTatCa.setBorder(null);
        btnChamCongTatCa.setBackground(new Color(46, 204, 113));
        btnChamCongTatCa.setBounds(195, 399, 178, 40);
        btnChamCongTatCa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	btnChamCongTatCaActionPerformed(evt);
            }
        });
        pnlChamCongCN.add(btnChamCongTatCa);
        
        cmbMaSanPham.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cd001" }));
        cmbMaSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbMaSanPhamActionPerformed(evt);
            }
        });
        pnlChamCongCN.add(cmbMaSanPham);

        cmbTenSanPham.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "" }));
        cmbTenSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTenSanPhamActionPerformed(evt);
            }
        });
        setLayout(null);
        pnlChamCongCN.add(cmbTenSanPham);

        add(pnlChamCongCN);

        scrChamCong.setBackground(new java.awt.Color(255, 255, 255));
        scrChamCong.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Bảng chấm công", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 16))); // NOI18N

        tblChamCong.setFont(new java.awt.Font("Segoe UI", 0, 13));
        tblChamCong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã Phân công", "Mã công nhân", "Họ và tên", "Tổ/Nhóm", "Ngày chấm công", "Ca làm", "Trạng thái", "Giờ đi làm", "Mã sản phẩm", "Sản phẩm", "Mã công đoạn", "Công đoạn", "Số lượng làm"
            }
        ));
        tblChamCong.getTableHeader().setBackground(new Color(128, 200, 255));
        tblChamCong.setSelectionBackground(new Color(255, 215, 0));
        tblChamCong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblChamCongMouseClicked(evt);
            }
        });
        scrChamCong.setViewportView(tblChamCong);

        add(scrChamCong);
    }

    private void btnChamCongTatCaActionPerformed(ActionEvent evt) {
        setEditTextDateChooser();
	}

    private void tblCongNhanMouseClicked(MouseEvent evt) {
	}
	
    private void cmbHienThiActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void btnChamCongActionPerformed(java.awt.event.ActionEvent evt) {
        setEditTextDateChooser();
    }

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {
        setEditTextDateChooser();
    }

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void cmbTenSanPhamActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void cmbTenToNhomActionPerformed(java.awt.event.ActionEvent evt) {
        btnChamCong.setEnabled(false);
    }

    private void cmbMaSanPhamActionPerformed(java.awt.event.ActionEvent evt) {
        btnChamCong.setEnabled(false);
    }

    private void cmbMaCongDoanActionPerformed(java.awt.event.ActionEvent evt) {
        btnChamCong.setEnabled(false);
    }

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {
        setEditTextDateChooser();
    }

    private void tblChamCongMouseClicked(java.awt.event.MouseEvent evt) {
        setEditTextDateChooser();
    }

    private void cmbCaLamActionPerformed(java.awt.event.ActionEvent evt) {
        btnChamCong.setEnabled(false);
        while (tblCongNhan.getRowCount() != 0) {
            modelTableCongNhan.removeRow(0);
        }
    }

    public void setComBoBoxToSunDay() {
        cmbCaLam.removeAllItems();
        cmbCaLam.addItem("Sáng chủ nhật");
        cmbCaLam.addItem("Chiều chủ nhật");
    }

    public void setComBoBoxToReset() {
        cmbCaLam.removeAllItems();
        cmbCaLam.addItem("Sáng");
        cmbCaLam.addItem("Chiều");
        cmbCaLam.addItem("Đêm");
    }
    
    private void btnLayDanhSachActionPerformed(java.awt.event.ActionEvent evt) {
        if (tblCongNhan.getRowCount() == 0) {
            btnChamCong.setEnabled(false);
        } else {
            btnChamCong.setEnabled(true);
        }
        lblTenSanPhamOutput.setText(cmbTenSanPham.getSelectedItem().toString());
        lblTenCongDoanOutput.setText(cmbTenCongDoan.getSelectedItem().toString());
        setEditTextDateChooser();
    }

    private void dtcNgayChamCongPropertyChange(java.beans.PropertyChangeEvent evt) {
        if ("date".equals(evt.getPropertyName())) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dtcNgayChamCong.getDate());
            if (calendar.get(Calendar.DAY_OF_WEEK) == 1) {
                setComBoBoxToSunDay();
            } else {
                setComBoBoxToReset();
            }
        }
        Date ngayChon = dtcNgayChamCong.getDate();
        if (ngayChon.after(new Date())) {
            JOptionPane.showMessageDialog(null, stErrChamCong, stThongbao, JOptionPane.INFORMATION_MESSAGE);
            dtcNgayChamCong.setDate(new Date());
        }
        btnChamCong.setEnabled(false);
        while (tblCongNhan.getRowCount() != 0) {
            modelTableCongNhan.removeRow(0);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(btnLayDanhSach)) {
            cmbCaLam.setEnabled(true);
            taiDuLieuLenBangCongNhan();
            cmbTrangThai.setEnabled(true);
            cmbGioDiLam.setEnabled(true);
            cmbPhutDiLam.setEnabled(true);
            txtSoLuongLam.setEditable(true);
            btnCapNhat.setEnabled(false);
            btnHuy.setEnabled(false);
            btnXoa.setEnabled(false);
            btnLuu.setEnabled(false);
            cmbTrangThai.setSelectedIndex(0);
            if (modelTableCongNhan.getRowCount() == 0) {
    			JOptionPane.showMessageDialog(null, "Đã chấm công rồi không có dữ liệu chấm công!!!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                lblMaCongNhanHienThi.setText("");
                lblHoVaTenHienThi.setText("");
                lblTenCongDoanOutput.setText("");
                lblTenCongDoanOutput.setText("");
                txtSoLuongLam.setText("");
                txtSoLuongLam.setEditable(false);
                cmbTrangThai.setEnabled(false);
                cmbGioDiLam.setEnabled(false);
                cmbPhutDiLam.setEnabled(false);
                txtSoLuongLam.setEditable(false);
                btnChamCong.setEnabled(false);
            }
            maCongDoanFlag = cmbMaCongDoan.getSelectedItem().toString();
            cmbCaLam.getSelectedItem().toString();
            ngayChamCongFlag = dtcNgayChamCong.getDate();
            lblTenCongDoanOutput.setText(cmbTenCongDoan.getSelectedItem().toString());
        } 
        else if (o.equals(btnChamCong)) {
            boolean isValid = validateForm();
            if (!isValid) {
                return;
            }
            if (lblMaCongNhanHienThi.getText().equals("")) {
                return;
            }
            String maPhanCong = phanCong_DAO.layRaMaPhanCongTheoMaCongDoanMaCongNhan(maCongDoanFlag, lblMaCongNhanHienThi.getText().trim());
            PhanCongCongNhan phanCong = phanCong_DAO.layMotPhanCongCongNhanTheoMaPhanCong(maPhanCong);
            String gioDiLam = cmbGioDiLam.getSelectedItem().toString() + "h" + cmbPhutDiLam.getSelectedItem().toString();
            if (cmbTrangThai.getSelectedIndex() >= 1) {
                gioDiLam = "";
            }
            int soLuongLam = Integer.parseInt(txtSoLuongLam.getText().trim());
            boolean chamDuoc = chamCongCN_DAO
                    .themMotChamCongCongNhan(new ChamCongCongNhan(phanCong, cmbCaLam.getSelectedItem().toString(), ngayChamCongFlag, soLuongLam, cmbTrangThai.getSelectedItem().toString(), gioDiLam));
            if (chamDuoc) {
                JOptionPane.showMessageDialog(null, stThemThanhCong, stThongbao, JOptionPane.INFORMATION_MESSAGE);
                taiDuLieuChamCongLenBang();
                taiDuLieuLenBangCongNhan();
                btnChamCong.setEnabled(false);
                cmbTrangThai.setEnabled(false);

                cmbGioDiLam.setEnabled(false);
                cmbPhutDiLam.setEnabled(false);
                txtSoLuongLam.setEditable(false);
                if (tblChamCong.getRowCount() != 0) {
                    if (tblCongNhan.getSelectedRow() != -1) {
                        tblCongNhan.removeRowSelectionInterval(tblCongNhan.getSelectedRow(), tblCongNhan.getSelectedRow());
                    }
                    tblChamCong.setRowSelectionInterval(0, 0);
                }
                congDoan_DAO.updateTinhTrangHoanThanhCuaCacCongDoan();
            } else {
                JOptionPane.showMessageDialog(null, stThemThatBai, stThongbao, JOptionPane.INFORMATION_MESSAGE);
            }
        }
        else if (o.equals(btnChamCongTatCa)) {
            boolean isValid = validateForm();
            if (!isValid) {
                return;
            }
            if (lblMaCongNhanHienThi.getText().equals("")) {
                return;
            }
            // Kiểm tra xem đã chọn đúng công đoạn và có công nhân nào trong bảng không
            if (maCongDoanFlag == null || modelTableCongNhan.getRowCount() == 0) {
                return;
            }

            for (int i = 0; i < modelTableCongNhan.getRowCount(); i++) {
                String maCongNhan = modelTableCongNhan.getValueAt(i, 1).toString();
                String maPhanCong = phanCong_DAO.layRaMaPhanCongTheoMaCongDoanMaCongNhan(maCongDoanFlag, maCongNhan);
                PhanCongCongNhan phanCong = phanCong_DAO.layMotPhanCongCongNhanTheoMaPhanCong(maPhanCong);

                Boolean isCoMat = (Boolean) modelTableCongNhan.getValueAt(i, 3);
                Boolean isNghiCoPhep = (Boolean) modelTableCongNhan.getValueAt(i, 4);

                boolean coMat = isCoMat != null && isCoMat.booleanValue();
                boolean nghiCoPhep = isNghiCoPhep != null && isNghiCoPhep.booleanValue();

                String tinhTrang = "";
                if (coMat) {
                    tinhTrang = "Đi làm";
                }else if (nghiCoPhep) {
                    tinhTrang = "Nghỉ có phép";
                } else {
                    tinhTrang = "Nghỉ không phép";
                }
                
                String gioDiLam = "";
                int soLuongLam = 0;
                if (coMat) {
                    gioDiLam = cmbGioDiLam.getSelectedItem().toString() + "h" + cmbPhutDiLam.getSelectedItem().toString();
                    soLuongLam = Integer.parseInt(modelTableCongNhan.getValueAt(i, 5).toString());
                }
                try {
                	if (chamCongCN_DAO.themMotChamCongCongNhan(new ChamCongCongNhan(phanCong, cmbCaLam.getSelectedItem().toString(), ngayChamCongFlag, soLuongLam, tinhTrang, gioDiLam))) {
                        JOptionPane.showMessageDialog(null, stThemThanhCong, stThongbao, JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, stThemThatBai, stThongbao, JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                
            }

            taiDuLieuChamCongLenBang();
            taiDuLieuLenBangCongNhan();
            btnChamCong.setEnabled(false);
            cmbTrangThai.setEnabled(false);
            cmbGioDiLam.setEnabled(false);
            cmbPhutDiLam.setEnabled(false);
            txtSoLuongLam.setEditable(false);
            if (tblChamCong.getRowCount() != 0) {
                if (tblCongNhan.getSelectedRow() != -1) {
                    tblCongNhan.removeRowSelectionInterval(tblCongNhan.getSelectedRow(), tblCongNhan.getSelectedRow());
                }
                tblChamCong.setRowSelectionInterval(0, 0);
            }
            congDoan_DAO.updateTinhTrangHoanThanhCuaCacCongDoan();
        }
        else if (o.equals(btnCapNhat)) {
            cmbCaLam.setEnabled(false);
            cmbTrangThai.setEnabled(true);
            if (cmbTrangThai.getSelectedIndex() >= 1) {
                cmbGioDiLam.setEnabled(false);
                cmbPhutDiLam.setEnabled(false);
                txtSoLuongLam.setEditable(false);
            } else {
                cmbGioDiLam.setEnabled(true);
                cmbPhutDiLam.setEnabled(true);
                txtSoLuongLam.setEditable(true);
            }
            btnCapNhat.setEnabled(false);
            btnXoa.setEnabled(false);
            btnLuu.setEnabled(true);
            btnHuy.setEnabled(true);
        } 
        else if (o.equals(btnHuy)) {
            cmbTrangThai.setEnabled(false);
            cmbGioDiLam.setEnabled(false);
            cmbPhutDiLam.setEnabled(false);
            txtSoLuongLam.setEditable(false);
            btnCapNhat.setEnabled(true);
            btnXoa.setEnabled(true);
            btnLuu.setEnabled(false);
            btnHuy.setEnabled(false);
            lblErrSoLuongSP.setText("");
            cmbCaLam.setEnabled(true);
        } 
        else if (o.equals(btnLuu)) {
            boolean isHople = validateForm();
            if (!isHople) {
                return;
            }
            String maPhanCong = tblChamCong.getValueAt(tblChamCong.getSelectedRow(), 1).toString();
            PhanCongCongNhan phanCong = phanCong_DAO.layMotPhanCongCongNhanTheoMaPhanCong(maPhanCong);
            String caLam = tblChamCong.getValueAt(tblChamCong.getSelectedRow(), 6).toString();
            String ngayChamCongStr = tblChamCong.getValueAt(tblChamCong.getSelectedRow(), 5).toString();
            Date ngayChamCong = new Date();
            try {
                ngayChamCong = new SimpleDateFormat("yyyy-MM-dd").parse(ngayChamCongStr);
            } catch (ParseException ex) {
                System.out.println("đổi chuỗi ngày sang object date ngày lỗi tại view cham cong nhan vien");
            }
            int soLuongLam = Integer.parseInt(txtSoLuongLam.getText().trim());
            String trangThaiDiLam = cmbTrangThai.getSelectedItem().toString();
            String gioDiLam = cmbGioDiLam.getSelectedItem().toString() + "h" + cmbPhutDiLam.getSelectedItem().toString();
            if (cmbTrangThai.getSelectedIndex() >= 1) {
                gioDiLam = "";
            }
            ChamCongCongNhan cccn = new ChamCongCongNhan(phanCong, caLam, ngayChamCong, soLuongLam, trangThaiDiLam, gioDiLam);
            boolean isCapNhat = chamCongCN_DAO.suaMotChamCongCongNhan(cccn);
            if (isCapNhat) {
                taiDuLieuChamCongLenBang();
                if (tblChamCong.getRowCount() != 0) {
                    if (tblCongNhan.getSelectedRow() != -1) {
                        tblCongNhan.removeRowSelectionInterval(tblCongNhan.getSelectedRow(), tblCongNhan.getSelectedRow());
                    }
                    tblChamCong.setRowSelectionInterval(0, 0);
                }
                JOptionPane.showMessageDialog(null, stCapNhatThanhCong, stThongbao, JOptionPane.INFORMATION_MESSAGE);
                congDoan_DAO.updateTinhTrangHoanThanhCuaCacCongDoan();

                cmbTrangThai.setEnabled(false);
                cmbGioDiLam.setEnabled(false);
                cmbPhutDiLam.setEnabled(false);
                txtSoLuongLam.setEditable(false);
                btnCapNhat.setEnabled(true);
                btnXoa.setEnabled(true);
                btnLuu.setEnabled(false);
                btnHuy.setEnabled(false);
                cmbCaLam.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(null, stCapNhatThatBai, stThongbao, JOptionPane.INFORMATION_MESSAGE);
            }
        }
        else if (o.equals(btnXoa)) {
            int rowChamCongSelect = tblChamCong.getSelectedRow();
            if (rowChamCongSelect != -1) {
                int isAccept = JOptionPane.showConfirmDialog(null, stBanXacNhanXoa, stThongbao, JOptionPane.INFORMATION_MESSAGE);
                if (isAccept == 0) {
                    String maPhanCong = tblChamCong.getValueAt(rowChamCongSelect, 1).toString();
                    String caLam = tblChamCong.getValueAt(rowChamCongSelect, 6).toString();
                    String ngayChamCongStr = tblChamCong.getValueAt(tblChamCong.getSelectedRow(), 5).toString();
                    Date ngayChamCong = new Date();
                    try {
                        ngayChamCong = new SimpleDateFormat("yyyy-MM-dd").parse(ngayChamCongStr);
                    } catch (ParseException ex) {
                        System.out.println("đổi chuỗi ngày sang object date ngày lỗi tại view cham cong nhan vien");
                    }
                    boolean coXoaDuoc = chamCongCN_DAO.xoaChamCongCongNhanTheoMa(maPhanCong, caLam, ngayChamCong);
                    if (coXoaDuoc) {
                        JOptionPane.showMessageDialog(null, stXoaThanhCong, stThongbao, JOptionPane.INFORMATION_MESSAGE);
                        taiDuLieuChamCongLenBang();
                        congDoan_DAO.updateTinhTrangHoanThanhCuaCacCongDoan();
                        if (tblChamCong.getRowCount() != 0) {
                            if (tblCongNhan.getSelectedRow() != -1) {
                                tblCongNhan.removeRowSelectionInterval(tblCongNhan.getSelectedRow(), tblCongNhan.getSelectedRow());
                            }
                            tblChamCong.setRowSelectionInterval(0, 0);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, stXoaThatBai, stThongbao, JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        }
    }

    public boolean validateForm() {
        if (txtSoLuongLam.getText().trim().equals("")) {
            lblErrSoLuongSP.setText(stErrKhongDeTrong);
            JOptionPane.showMessageDialog(null, "Vui lòng nhập số lượng sản phẩm!!!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        int soLuong = -1;
        try {
            soLuong = Integer.parseInt(txtSoLuongLam.getText().trim());
        } catch (Exception e) {
            lblErrSoLuongSP.setText(stErrSoLuong);
            return false;
        }
        if (soLuong < 0) {
            lblErrSoLuongSP.setText(stErrSoLuong);
            return false;
        }
        CongDoan congDoanDangCham = congDoan_DAO.layMotCongDoanTheoMaCongDoan(maCongDoanFlag);

        if (congDoanDangCham != null) {
            if (congDoanDangCham.getThuTuCongDoan() != 1) {
                int thuTuTruoc = congDoanDangCham.getThuTuCongDoan() - 1;
                String maSanPham = congDoanDangCham.getSanPham().getMaSanPham();
                ArrayList<CongDoan> dsCongDoan = congDoan_DAO.layDanhSachCongDoanTheoThuTuSanPham(maSanPham, thuTuTruoc);
                for (CongDoan cd : dsCongDoan) {
                    if (soLuong > congDoan_DAO.laySoLuongLamDuocTheoMaCongDoan(cd.getMaCongDoan())) {
                        lblErrSoLuongSP.setText("Phải <= số lượng làm công đoạn trước");
                        return false;
                    }
                }
            }
        }
        ArrayList<PhanCongCongNhan> phanCongDS = phanCong_DAO.layDanhSachPhanCongTheoMaCongDoan(maCongDoanFlag);
        if (phanCongDS.size() > 0) {
            int soLuongLamCuaPhanCong = phanCongDS.get(0).getSoLuongCanLam();
            if (soLuong > soLuongLamCuaPhanCong){
                lblErrSoLuongSP.setText(stErrBeHonTongSanPhamCongDoan);
                return false;
            }
        }
        lblErrSoLuongSP.setText("");
        return true;
    }

    public void taiDuLieuLenBangCongNhan() {
        while (tblCongNhan.getRowCount() != 0) {
            modelTableCongNhan.removeRow(0);
        }
        if (cmbMaCongDoan.getItemCount() == 0 || cmbCaLam.getItemCount() == 0) {
            return;
        }
        String maCongDoan = cmbMaCongDoan.getSelectedItem().toString();
        String maToNhom = toNhom_DAO.layMotToNhomTheoTen(cmbTenToNhom.getSelectedItem().toString()).getMaToNhom();
        SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
        String ngayChamCong = sm.format(dtcNgayChamCong.getDate());
        ArrayList<CongNhan> dsCongNhan = phanCong_DAO.layRaDanhSachCongNhanTheoCongDoanVaToNhom(maCongDoan, maToNhom, ngayChamCong, cmbCaLam.getSelectedItem().toString());// vu them
        for (CongNhan congNhan : dsCongNhan) {
            String data[] = {(modelTableCongNhan.getRowCount() + 1) + "", congNhan.getMaCongNhan(), congNhan.getHoTen()};
            modelTableCongNhan.addRow(data);
        }
        if (modelTableCongNhan.getRowCount() != 0) {
            tblCongNhan.setRowSelectionInterval(0, 0);
            btnChamCong.setEnabled(true);
            int rowSelectTblChamCong = tblChamCong.getSelectedRow();
            if (rowSelectTblChamCong != -1) {
                tblChamCong.removeRowSelectionInterval(rowSelectTblChamCong, rowSelectTblChamCong);
            }
            hienThiThongTinCongNhanLenTxt(0);
        } else {
            btnChamCong.setEnabled(false);
        }

    }

    public void hienThiThongTinCongNhanLenTxt(int row) {
        lblMaCongNhanHienThi.setText(tblCongNhan.getValueAt(row, 1).toString());
        lblHoVaTenHienThi.setText(tblCongNhan.getValueAt(row, 2).toString());
    }

    public void taiDuLieuChamCongLenBang() {
        while (tblChamCong.getRowCount() != 0) {
            modelTableChamCong.removeRow(0);
        }
        ArrayList<ChamCongCongNhan> dsChamCong = chamCongCN_DAO.layDanhSachChamCong();
        for (ChamCongCongNhan cccn : dsChamCong) {
            String data[] = {(modelTableChamCong.getRowCount() + 1) + "", cccn.getPhanCong().getMaPhanCong(), cccn.getPhanCong().getCongNhan().getMaCongNhan(),
                cccn.getPhanCong().getCongNhan().getHoTen(), cccn.getPhanCong().getCongNhan().getToNhom().getTenToNhom(),
                cccn.getNgayChamCong().toString(), cccn.getCaLam(), cccn.getTrangThaiDiLam(), cccn.getGioDiLam(), cccn.getPhanCong().getCongDoan().getSanPham().getMaSanPham(),
                cccn.getPhanCong().getCongDoan().getSanPham().getTenSanPham(),
                cccn.getPhanCong().getCongDoan().getMaCongDoan(), cccn.getPhanCong().getCongDoan().getTenCongDoan(), cccn.getSoLuongLam() + ""};

            modelTableChamCong.addRow(data);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object o = e.getSource();
        if (o.equals(tblCongNhan)) {
            cmbCaLam.setEnabled(true);
            int rowSelectOfChamCong = tblChamCong.getSelectedRow();
            if (rowSelectOfChamCong != -1) {
                tblChamCong.removeRowSelectionInterval(rowSelectOfChamCong, rowSelectOfChamCong);

            }
            int row = tblCongNhan.getSelectedRow();
            if (row != -1) {
                hienThiThongTinCongNhanLenTxt(row);
                txtSoLuongLam.setText("0");
                txtSoLuongLam.requestFocus();
                btnCapNhat.setEnabled(false);
                btnHuy.setEnabled(false);
                btnLuu.setEnabled(false);
                btnXoa.setEnabled(false);
                btnChamCong.setEnabled(true);
                cmbTrangThai.setEnabled(true);
                cmbGioDiLam.setEnabled(true);
                cmbPhutDiLam.setEnabled(true);
                txtSoLuongLam.setEditable(true);
            }
        } else if (o.equals(tblChamCong)) {
            int rowSelectCongNhan = tblCongNhan.getSelectedRow();
            lblErrSoLuongSP.setText("");
            if (rowSelectCongNhan != -1) {
                tblCongNhan.removeRowSelectionInterval(rowSelectCongNhan, rowSelectCongNhan);
            }
            int rowSelectChamCong = tblChamCong.getSelectedRow();
            if (rowSelectChamCong != -1) {
                btnChamCong.setEnabled(false);
                btnCapNhat.setEnabled(true);
                btnHuy.setEnabled(false);
                btnLuu.setEnabled(false);
                btnXoa.setEnabled(true);
                lblErrSoLuongSP.setText("");
                if (btnCapNhat.isEnabled()) {
                    cmbTrangThai.setEnabled(false);
                    cmbGioDiLam.setEnabled(false);
                    cmbPhutDiLam.setEnabled(false);
                    txtSoLuongLam.setEditable(false);
                } else {
                    cmbTrangThai.setEnabled(true);
                    cmbGioDiLam.setEnabled(true);
                    cmbPhutDiLam.setEnabled(true);
                    txtSoLuongLam.setEditable(true);
                }
                taiDuLieuTuBangChamCongLenTxt();
            }
        }
    }

    public void taiDuLieuTuBangChamCongLenTxt() {
        lblMaCongNhanHienThi.setText(tblChamCong.getValueAt(tblChamCong.getSelectedRow(), 2).toString());
        lblHoVaTenHienThi.setText(tblChamCong.getValueAt(tblChamCong.getSelectedRow(), 3).toString());
        cmbTrangThai.setSelectedItem(tblChamCong.getValueAt(tblChamCong.getSelectedRow(), 7).toString());
        setCmbGio(tblChamCong.getValueAt(tblChamCong.getSelectedRow(), 6).toString());
        //cmbCaLam.setSelectedItem(tblChamCong.getValueAt(tblChamCong.getSelectedRow(), 6).toString());
        String times[] = tblChamCong.getValueAt(tblChamCong.getSelectedRow(), 8).toString().split("h");
        if (times.length > 1) {
            cmbGioDiLam.setSelectedItem(times[0]);
            cmbPhutDiLam.setSelectedItem(times[1]);
        }
        lblTenSanPhamOutput.setText(tblChamCong.getValueAt(tblChamCong.getSelectedRow(), 10).toString());
        lblTenCongDoanOutput.setText(tblChamCong.getValueAt(tblChamCong.getSelectedRow(), 12).toString());
        txtSoLuongLam.setText(tblChamCong.getValueAt(tblChamCong.getSelectedRow(), 13).toString());
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
