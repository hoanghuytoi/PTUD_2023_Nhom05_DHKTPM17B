package UI;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Custom_UI.ScrollBarCustom;
import Dao.CongDoan_Dao;
import Dao.SanPham_Dao;
import Entity.CongDoan;
import Entity.SanPham;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

public class CongDoanSanPham_GUI extends JPanel implements ActionListener, MouseListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JScrollPane scrTableSanPham;
	private JTable tblDanhSachSanPham;
	private JScrollPane scrTableCongDoan;
	private JTable tblCongDoan;

	private JTextField txtMaCD;
	private JTextField txtTenCD;
	private JTextField txtThuTu;
	private JTextField txtSoLuongCan;
	private JPanel panelCDSP;
	private JLabel lblTieuDe;
	private JButton btnThem;
	private JButton btnXoa;
	private JButton btnCapNhat;
	private JButton btnLuu;
	private JButton btnHuy;
	private JTextField txtDonGia;
	private JTextField txtHienThiMaSP;
	private JLabel lblMaSanPham;
	private JLabel lblErrDonGia;
	private JLabel lblDonGia;
	private JLabel lblErrThoiHan;
	private JLabel lblThoiHan;
	private JLabel lblErrSoLuongCan;
	private JDateChooser dcsThoiHan;
	private JLabel lblThuTu;
	private JLabel lblErrThuTu;
	private JLabel lblSoLuongCan;
	private JLabel lblErrTenCD;
	private JLabel lblTenCD;
	private JLabel lblMaCD;
	private DefaultTableModel modelTableSanPham;
	private DefaultTableModel modelTableCongDoan;

	private SanPham_Dao sanPham_DAO;
	private CongDoan_Dao congDoan_DAO;
	private NumberFormat nf;
	private DecimalFormat df;
	private Object oFlag;

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
	private String stChuaKiTuChu;
	private String stSoLuongPhaiLonHonHoacBang;
	private String stSoNguyen;
	private String stThuTuLam;
	private String stSauNgayHienTai;
	private String stThuTuLamPhaiLonHon;
	private String stThuTuCongDoanHienTai;
	private String stTren;
	private String stCongDoan;
	private String stKhongTimThayFile;
	private String stKhongDocDuocFile;
	private SanPham_Dao daoSanPham;
	private JButton btnThemNhieu;

	public CongDoanSanPham_GUI(String fileName)throws IOException {
		initComponents();
		caiDatNgonNgu(fileName);

		nf = new DecimalFormat("#,###.00");
		df = new DecimalFormat("#");
		df.setMaximumFractionDigits(2);

		try {
			ConnectionDB.ConnectDB.getInstance().connect();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		tblCongDoan.addMouseListener(this);
		tblDanhSachSanPham.addMouseListener(this);

		modelTableSanPham = (DefaultTableModel) tblDanhSachSanPham.getModel();
		modelTableCongDoan = (DefaultTableModel) tblCongDoan.getModel();
		sanPham_DAO = new SanPham_Dao();
		congDoan_DAO = new CongDoan_Dao();

		btnThemNhieu.addActionListener(this);
		btnThem.addActionListener(this);
		btnHuy.addActionListener(this);
		btnCapNhat.addActionListener(this);
		btnLuu.addActionListener(this);
		btnXoa.addActionListener(this);
		btnLuu.setEnabled(false);
		btnHuy.setEnabled(false);

		moKhoaTextField(false);
		excute();
	}

	public void caiDatNgonNgu(String fileName) throws FileNotFoundException, IOException {
		FileInputStream fis = new FileInputStream(fileName);
		Properties prop = new Properties();
		prop.load(fis);
		lblTieuDe.setText(prop.getProperty("pcd_TieuDe"));
		lblMaSanPham.setText(prop.getProperty("pcd_maSanPham"));
		lblMaCD.setText(prop.getProperty("pcd_maCongDoan"));
		lblTenCD.setText(prop.getProperty("pcd_tenCongDoan"));
		lblSoLuongCan.setText(prop.getProperty("pcd_soLuongCan"));
		lblDonGia.setText(prop.getProperty("pcd_luongSanPham"));
		lblThoiHan.setText(prop.getProperty("pcd_thoiHan"));
		lblThuTu.setText(prop.getProperty("pcd_thuTuLam"));

		doiNgonNguTable(tblDanhSachSanPham, 0, prop.getProperty("sp_stt"));
		doiNgonNguTable(tblDanhSachSanPham, 1, lblMaSanPham.getText());
		doiNgonNguTable(tblDanhSachSanPham, 2, prop.getProperty("sp_tenSP"));
		doiNgonNguTable(tblDanhSachSanPham, 2, prop.getProperty("sp_donGia"));

		doiNgonNguTable(tblCongDoan, 0, lblThuTu.getText());
		doiNgonNguTable(tblCongDoan, 1, lblMaCD.getText());
		doiNgonNguTable(tblCongDoan, 2, lblTenCD.getText());
		doiNgonNguTable(tblCongDoan, 3, lblSoLuongCan.getText());
		doiNgonNguTable(tblCongDoan, 4, prop.getProperty("pcd_soLuongDaLam"));
		doiNgonNguTable(tblCongDoan, 5, lblDonGia.getText());
		doiNgonNguTable(tblCongDoan, 6, lblThoiHan.getText());
		doiNgonNguTable(tblCongDoan, 7, prop.getProperty("pcd_mucDoHoanThanh"));
		scrTableSanPham.setBorder(new TitledBorder(prop.getProperty("pcd_tieuDeSanPham")));
		scrTableCongDoan.setBorder(new TitledBorder(prop.getProperty("pcd_tieuDeCongDoan")));
		panelCDSP.setBorder(new TitledBorder(prop.getProperty("pcd_tieuDeThongTinCD")));
		btnThem.setText(prop.getProperty("btnThem"));
		btnThemNhieu.setText(prop.getProperty("btnThemNhieu"));
		btnXoa.setText(prop.getProperty("btnXoa"));
		btnCapNhat.setText(prop.getProperty("btnCapNhat"));
		btnLuu.setText(prop.getProperty("btnLuu"));
		btnHuy.setText(prop.getProperty("btnHuy"));

		stThongbao = prop.getProperty("thongBao");
		stBanXacNhanXoa = prop.getProperty("banXacNhanXoa");
		stXoaThanhCong = prop.getProperty("xoaThanhCong");
		stXoaThatBai = prop.getProperty("xoaThatBai");
		stThemThanhCong = prop.getProperty("themThanhCong");
		stThemThatBai = prop.getProperty("themThatBai");
		stCapNhatThanhCong = prop.getProperty("capNhatThanhCong");
		stCapNhatThatBai = prop.getProperty("capNhatThatBai");
		stChuaKiTuChu = prop.getProperty("pcd_ErrChiChua1KyTu");
		stErrSoLuong = prop.getProperty("sp_lblErrSoLuong");
		stErrKhongDeTrong = prop.getProperty("khongDeTrong");
		stSoLuongPhaiLonHonHoacBang = prop.getProperty("pcd_ErrSoLuongPhaiLonHon");
		stSoNguyen = prop.getProperty("pcd_ErrPhaiLaSoNguyen");
		stThuTuLam = prop.getProperty("pcd_ErrThuTuLam");
		stSauNgayHienTai = prop.getProperty("pcd_ErrPhaiBangHoacSauNgayHienTai");
		stThuTuLamPhaiLonHon = prop.getProperty("pcd_thuThuTuLamPhaiBeHon");
		stThuTuCongDoanHienTai = prop.getProperty("pcd_thuThuCongDoanHienTai");
		stTren = prop.getProperty("tren");
        stCongDoan = prop.getProperty("pcd_congDoan");
        stKhongDocDuocFile = prop.getProperty("khongDocDuocFile");
        stKhongTimThayFile = prop.getProperty("khongTimThayFile");
	}

	public void doiNgonNguTable(JTable table, int col_index, String col_name) {
		table.getColumnModel().getColumn(col_index).setHeaderValue(col_name);
	}

	private void initComponents() {
		setSize(1290, 750);
		setBackground(new Color(255, 255, 255));
		setLayout(null);

		lblTieuDe = new JLabel("QUẢN LÝ CÔNG ĐOẠN SẢN PHẨM");
		lblTieuDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblTieuDe.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblTieuDe.setBounds(10, 21, 490, 39);
		add(lblTieuDe);

		tblDanhSachSanPham = new JTable();
		tblDanhSachSanPham.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		tblDanhSachSanPham.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 15));
		tblDanhSachSanPham.setRowHeight(22);
		tblDanhSachSanPham.getTableHeader().setBackground(new Color(128, 200, 255));
		tblDanhSachSanPham.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null},
					{null, null, null, null},
					{null, null, null, null},
					{null, null, null, null},
					{null, null, null, null},
					{null, null, null, null},
					{null, null, null, null},
					{null, null, null, null},
					{null, null, null, null},
					{null, null, null, null},
				},
				new String[] {
						"STT", "M\u00E3 s\u1EA3n ph\u1EA9m", "T\u00EAn s\u1EA3n ph\u1EA9m", "S\u1ED1 l\u01B0\u1EE3ng"
				}
				));
		tblDanhSachSanPham.getColumnModel().getColumn(0).setPreferredWidth(40);
		tblDanhSachSanPham.getColumnModel().getColumn(2).setPreferredWidth(95);
		tblDanhSachSanPham.getColumnModel().getColumn(3).setPreferredWidth(57);
		tblDanhSachSanPham.setRowHeight(30);
		tblDanhSachSanPham.getTableHeader().setOpaque(false);
		((DefaultTableCellRenderer) tblDanhSachSanPham.getTableHeader().getDefaultRenderer())
		.setHorizontalAlignment(JLabel.CENTER);
		tblDanhSachSanPham.setSelectionBackground(new java.awt.Color(232, 57, 95));

		tblDanhSachSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				tblDanhSachSanPhamMouseClicked(evt);
			}
		});


		scrTableSanPham = new JScrollPane(tblDanhSachSanPham);
		scrTableSanPham.setBackground(new java.awt.Color(255, 255, 255));
		LineBorder blackLineBorder1 = new LineBorder(Color.BLACK, 2);
		TitledBorder titledBorder1 = BorderFactory.createTitledBorder(blackLineBorder1, "Danh sách sản phẩm");
		scrTableSanPham.setBorder(titledBorder1);
		scrTableSanPham.setBounds(48, 103, 625, 340);
		add(scrTableSanPham);

		ScrollBarCustom scrollBar = new ScrollBarCustom();
		scrollBar.setForeground(Color.RED);
		scrTableSanPham.setVerticalScrollBar(scrollBar);

		panelCDSP = new JPanel();
		LineBorder blackLineBorder = new LineBorder(Color.BLACK, 2);
		TitledBorder titledBorder = BorderFactory.createTitledBorder(blackLineBorder, "Thông tin công đoạn sản phẩm");
		panelCDSP.setBorder(titledBorder);
		panelCDSP.setBackground(Color.WHITE);
		panelCDSP.setBounds(772, 11, 467, 454);
		add(panelCDSP);
		panelCDSP.setLayout(null);

		lblMaSanPham = new JLabel();
		lblMaSanPham.setText("Mã sản phẩm:");
		lblMaSanPham.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblMaSanPham.setBounds(24, 23, 100, 28);
		panelCDSP.add(lblMaSanPham);

		txtHienThiMaSP = new JTextField();
		txtHienThiMaSP.setBackground(new Color(255, 255, 255));
		txtHienThiMaSP.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtHienThiMaSP.setBorder(null);
		txtHienThiMaSP.setBounds(134, 22, 251, 30);
		panelCDSP.add(txtHienThiMaSP);

		lblMaCD = new JLabel();
		lblMaCD.setBounds(24, 86, 100, 20);
		lblMaCD.setText("Mã công đoạn:");
		lblMaCD.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		panelCDSP.add(lblMaCD);

		txtMaCD = new JTextField();
		txtMaCD.setBackground(new Color(255, 255, 255));
		txtMaCD.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtMaCD.setBorder(null);
		txtMaCD.setBounds(134, 63, 251, 36);
		panelCDSP.add(txtMaCD);

		JSeparator jSeparator = new JSeparator();
		jSeparator.setForeground(Color.BLACK);
		jSeparator.setBounds(134, 102, 251, 10);
		panelCDSP.add(jSeparator);

		lblTenCD = new JLabel();
		lblTenCD.setText("Tên công đoạn:");
		lblTenCD.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblTenCD.setBounds(24, 139, 102, 30);
		panelCDSP.add(lblTenCD);

		txtTenCD = new JTextField();
		txtTenCD.setBackground(new Color(255, 255, 255));
		txtTenCD.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtTenCD.setBorder(null);
		txtTenCD.setBounds(134, 118, 251, 36);
		panelCDSP.add(txtTenCD);

		lblErrTenCD = new JLabel();
		lblErrTenCD.setText("lblErrTenCD");
		lblErrTenCD.setForeground(new Color(204, 0, 0));
		lblErrTenCD.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblErrTenCD.setBounds(134, 173, 200, 17);
		panelCDSP.add(lblErrTenCD);

		JSeparator jSeparator1 = new JSeparator();
		jSeparator1.setForeground(Color.BLACK);
		jSeparator1.setBounds(134, 159, 251, 10);
		panelCDSP.add(jSeparator1);

		lblSoLuongCan = new JLabel();
		lblSoLuongCan.setText("Số lượng cần:");
		lblSoLuongCan.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblSoLuongCan.setBounds(24, 267, 100, 30);
		panelCDSP.add(lblSoLuongCan);

		txtThuTu = new JTextField();
		txtThuTu.setBackground(new Color(255, 255, 255));
		txtThuTu.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtThuTu.setBorder(null);
		txtThuTu.setBounds(134, 187, 251, 36);
		panelCDSP.add(txtThuTu);

		JSeparator jSeparator2 = new JSeparator();
		jSeparator2.setForeground(Color.BLACK);
		jSeparator2.setBounds(134, 224, 251, 10);
		panelCDSP.add(jSeparator2);

		lblErrThuTu = new JLabel();
		lblErrThuTu.setText("thông báo lỗi");
		lblErrThuTu.setForeground(new Color(204, 0, 0));
		lblErrThuTu.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblErrThuTu.setBounds(134, 226, 200, 24);
		panelCDSP.add(lblErrThuTu);

		lblThuTu = new JLabel();
		lblThuTu.setText("Thứ tự:");
		lblThuTu.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblThuTu.setBounds(24, 204, 100, 30);
		panelCDSP.add(lblThuTu);

		txtSoLuongCan = new JTextField();
		txtSoLuongCan.setBackground(new Color(255, 255, 255));
		txtSoLuongCan.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtSoLuongCan.setBorder(null);
		txtSoLuongCan.setBounds(134, 251, 251, 30);
		panelCDSP.add(txtSoLuongCan);

		JSeparator jSeparator2_1 = new JSeparator();
		jSeparator2_1.setForeground(Color.BLACK);
		jSeparator2_1.setBounds(134, 286, 251, 10);
		panelCDSP.add(jSeparator2_1);

		dcsThoiHan = new JDateChooser();
		dcsThoiHan.setDateFormatString("yyyy-MM-dd");
		dcsThoiHan.setBounds(134, 315, 251, 30);
		panelCDSP.add(dcsThoiHan);

		lblErrSoLuongCan = new JLabel();
		lblErrSoLuongCan.setText("thông báo lỗi");
		lblErrSoLuongCan.setForeground(new Color(204, 0, 0));
		lblErrSoLuongCan.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblErrSoLuongCan.setBounds(134, 286, 200, 30);
		panelCDSP.add(lblErrSoLuongCan);

		lblThoiHan = new JLabel();
		lblThoiHan.setText("Thời hạn:");
		lblThoiHan.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblThoiHan.setBounds(24, 327, 100, 30);
		panelCDSP.add(lblThoiHan);

		lblErrThoiHan = new JLabel();
		lblErrThoiHan.setText("thông báo lỗi");
		lblErrThoiHan.setForeground(new Color(204, 0, 0));
		lblErrThoiHan.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblErrThoiHan.setBounds(134, 346, 200, 20);
		panelCDSP.add(lblErrThoiHan);

		lblDonGia = new JLabel();
		lblDonGia.setText("Đơn giá CĐ:");
		lblDonGia.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblDonGia.setBounds(24, 386, 100, 30);
		panelCDSP.add(lblDonGia);

		txtDonGia = new JTextField();
		txtDonGia.setBackground(new Color(255, 255, 255));
		txtDonGia.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtDonGia.setBorder(null);
		txtDonGia.setBounds(134, 369, 251, 36);
		panelCDSP.add(txtDonGia);

		JSeparator jSeparator2_1_1 = new JSeparator();
		jSeparator2_1_1.setForeground(Color.BLACK);
		jSeparator2_1_1.setBounds(134, 406, 251, 10);
		panelCDSP.add(jSeparator2_1_1);

		lblErrDonGia = new JLabel();
		lblErrDonGia.setText("thông báo lỗi");
		lblErrDonGia.setForeground(new Color(204, 0, 0));
		lblErrDonGia.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblErrDonGia.setBounds(134, 407, 200, 30);
		panelCDSP.add(lblErrDonGia);

		JSeparator jSeparator_1 = new JSeparator();
		jSeparator_1.setForeground(Color.BLACK);
		jSeparator_1.setBounds(134, 54, 251, 10);
		panelCDSP.add(jSeparator_1);

		btnThemNhieu = new JButton();
		btnThemNhieu.setIcon(new ImageIcon(CongDoanSanPham_GUI.class.getResource("/image/icon/them.png")));
		btnThemNhieu.setText("Thêm nhiều");
		btnThemNhieu.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnThemNhieu.setBorder(null);
		btnThemNhieu.setBackground(new Color(255, 215, 0));
		btnThemNhieu.setBounds(48, 476, 140, 40);
		btnThemNhieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemNhieuMouseClicked(evt);
            }
        });
		add(btnThemNhieu);
		
		btnThem = new JButton();
		btnThem.setBounds(226, 476, 140, 40);
		add(btnThem);
		btnThem.setBackground(new Color(255, 215, 0));
		btnThem.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnThem.setIcon(new ImageIcon(NhanVien_GUI.class.getResource("/image/icon/them.png")));
		btnThem.setText("Thêm");
		btnThem.setBorder(null);
		btnThem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnThemActionPerformed(evt);
			}
		});

		btnXoa = new JButton();
		btnXoa.setBounds(405, 476, 140, 40);
		add(btnXoa);
		btnXoa.setBackground(new Color(255, 215, 0));
		btnXoa.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnXoa.setIcon(new ImageIcon(NhanVien_GUI.class.getResource("/image/icon/xoa.png")));
		btnXoa.setText("Xóa");
		btnXoa.setBorder(null);
		btnXoa.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnXoaActionPerformed(evt);
			}
		});

		btnCapNhat = new JButton();
		btnCapNhat.setBounds(589, 476, 140, 40);
		add(btnCapNhat);
		btnCapNhat.setBackground(new Color(255, 215, 0));
		btnCapNhat.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnCapNhat.setIcon(new ImageIcon(NhanVien_GUI.class.getResource("/image/icon/capNhat.png")));
		btnCapNhat.setText("Cập nhật");
		btnCapNhat.setBorder(null);
		btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnCapNhatActionPerformed(evt);
			}
		});

		btnLuu = new JButton();
		btnLuu.setBounds(800, 476, 140, 40);
		add(btnLuu);
		btnLuu.setBackground(new Color(255, 0, 255));
		btnLuu.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnLuu.setIcon(new ImageIcon(NhanVien_GUI.class.getResource("/image/icon/luu.png")));
		btnLuu.setText("Lưu");
		btnLuu.setBorder(null);
		btnLuu.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnLuuActionPerformed(evt);
			}
		});

		btnHuy = new JButton();
		btnHuy.setBounds(1020, 476, 140, 40);
		add(btnHuy);
		btnHuy.setBackground(new Color(255, 0, 255));
		btnHuy.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnHuy.setIcon(new ImageIcon(NhanVien_GUI.class.getResource("/image/icon/huy.png")));
		btnHuy.setText("Hủy");
		btnHuy.setBorder(null);
		btnHuy.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnHuyActionPerformed(evt);
			}
		});


		tblCongDoan = new JTable();
		tblCongDoan.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		tblCongDoan.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 18));
		tblCongDoan.getTableHeader().setBackground(new Color(128, 200, 255));

		tblCongDoan.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null, null},
				},
				new String[] {
						"Th\u1EE9 t\u1EF1 l\u00E0m", "M\u00E3 c\u00F4ng \u0111o\u1EA1n", "T\u00EAn  c\u00F4ng \u0111o\u1EA1n", "S\u1ED1 l\u01B0\u1EE3ng c\u1EA7n l\u00E0m", "S\u1ED1 l\u01B0\u01A1ng \u0111\u00E3 l\u00E0m", "\u0110\u01A1n gi\u00E1 C\u0110", "Th\u1EDDi h\u1EA1n", "M\u1EE9c \u0111\u1ED9 ho\u00E0n th\u00E0nh (%)"
				}
				));
		tblCongDoan.getColumnModel().getColumn(0).setPreferredWidth(63);
		tblCongDoan.getColumnModel().getColumn(7).setPreferredWidth(130);
		tblCongDoan.setRowHeight(24);
		tblCongDoan.setSelectionBackground(new Color(255, 215, 0));
		tblCongDoan.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				tblCongDoanMouseClicked(evt);
			}
		});

		scrTableCongDoan = new JScrollPane(tblCongDoan);
		scrTableCongDoan.setBackground(new java.awt.Color(255, 255, 255));
		LineBorder blackLineBorder2 = new LineBorder(Color.BLACK, 2);
		TitledBorder titledBorder2 = BorderFactory.createTitledBorder(blackLineBorder2, "Danh sách công đoạn sản phẩm");
		scrTableCongDoan.setBorder(titledBorder2);
		scrTableCongDoan.setBounds(37, 527, 1203, 192);
		add(scrTableCongDoan);

		ScrollBarCustom scrollBar1 = new ScrollBarCustom();
		scrollBar1.setForeground(Color.RED);
		scrTableCongDoan.setVerticalScrollBar(scrollBar1);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(CongDoanSanPham_GUI.class.getResource("/image/icon/muitenNgang.png")));
		lblNewLabel.setBounds(696, 248, 55, 50);
		add(lblNewLabel);

	}

	public void setEditTextDateChooser() {
		JTextFieldDateEditor thoiHan = (JTextFieldDateEditor) dcsThoiHan.getDateEditor();
		thoiHan.setEnabled(false);
	}

	public void moKhoaTextField(boolean b) {
		if (b) {
			txtTenCD.requestFocus();
		}
		txtTenCD.setEditable(b);
		txtSoLuongCan.setEditable(b);
		txtDonGia.setEditable(b);
		dcsThoiHan.setEnabled(b);
	}

	public void xoaTrangTextField() {
		txtTenCD.setText("");
		txtThuTu.setText("");
		txtSoLuongCan.setText("");
		txtDonGia.setText("");
		dcsThoiHan.setDate(new Date());
		txtMaCD.setText("");
	}

	public void excute() {
		taiDuLieuLenBangSanPham();
		lblErrTenCD.setText("");
		lblErrSoLuongCan.setText("");
		lblErrDonGia.setText("");
		lblErrThoiHan.setText("");
		lblErrThuTu.setText("");
	}

	public void taiDuLieuLenBangSanPham() {
		while (tblDanhSachSanPham.getRowCount() != 0) {
			modelTableSanPham.removeRow(0);
		}
		daoSanPham = new SanPham_Dao();
		ArrayList<SanPham> listSanPham = daoSanPham.layDanhSachSanPham();
		for (SanPham sanPham : listSanPham) {
			String data[] = {(modelTableSanPham.getRowCount() + 1) + "", sanPham.getMaSanPham(), sanPham.getTenSanPham(),sanPham.getSoLuongSanPham() + "",};
			modelTableSanPham.addRow(data);
		}
		if (tblDanhSachSanPham.getRowCount() != 0) {
			tblDanhSachSanPham.setRowSelectionInterval(0, 0);
			hienThiSanPhamLenTxt(0);
			taiDuLieuLenBangCongDoan();
		}
		if (tblCongDoan.getRowCount() == 0) {
			btnCapNhat.setEnabled(false);
			btnXoa.setEnabled(false);
		}
	}
	public void hienThiSanPhamLenTxt(int row) {
		txtHienThiMaSP.setText(tblDanhSachSanPham.getValueAt(row, 1).toString());
	}

	public void taiDuLieuLenBangCongDoan() {
		while (tblCongDoan.getRowCount() != 0) {
			modelTableCongDoan.removeRow(0);
		}

		String maSanPham = tblDanhSachSanPham.getValueAt(tblDanhSachSanPham.getSelectedRow(), 1).toString();
		ArrayList<CongDoan> dsCongDoan = congDoan_DAO.layDanhSachCongDoanTheoMaSP(maSanPham);
		for (CongDoan congDoan : dsCongDoan) {
			String maCongDoan = congDoan.getMaCongDoan();

			String data[] = {congDoan.getThuTuCongDoan() + "", congDoan.getMaCongDoan(), congDoan.getTenCongDoan(),
					congDoan.getSoLuongCan() + "", congDoan_DAO.laySoLuongLamDuocTheoMaCongDoan(maCongDoan) + "", nf.format(congDoan.getDonGia()), congDoan.getThoiHan().toString(),
					String.format("%.2f", congDoan_DAO.layMucDoHoanThanhCuaMotCongDoan(maCongDoan)) + "%"};

			modelTableCongDoan.addRow(data);
		}
		if (tblCongDoan.getRowCount() != 0) {
			tblCongDoan.setRowSelectionInterval(0, 0);
			hienThiCongDoanLenTxt(0);
		} else {
			xoaTrangTextField();
		}

	}

	public void hienThiCongDoanLenTxt(int row) {
		if (row != -1) {
			txtThuTu.setText(tblCongDoan.getValueAt(row, 0).toString());
			double luongTrenSanPham = 0;
			try {
				luongTrenSanPham = nf.parse(tblCongDoan.getValueAt(row, 5).toString()).doubleValue();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			txtMaCD.setText(tblCongDoan.getValueAt(row, 1).toString());
			txtTenCD.setText(tblCongDoan.getValueAt(row, 2).toString());
			txtSoLuongCan.setText(tblCongDoan.getValueAt(row, 3).toString());
			txtDonGia.setText(df.format(luongTrenSanPham).replaceAll(",", "."));
			try {
				dcsThoiHan.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(tblCongDoan.getValueAt(row, 6).toString()));
			} catch (ParseException ex) {
				Logger.getLogger(CongDoanSanPham_GUI.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	private void tblCongDoanMouseClicked(MouseEvent evt) {
		// TODO Auto-generated method stub

	}

	private void btnHuyActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub

	}

	private void btnLuuActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub

	}

	private void btnCapNhatActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub

	}

	private void btnXoaActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub

	}

	private void btnThemActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub

	}

	private void tblDanhSachSanPhamMouseClicked(java.awt.event.MouseEvent evt) {
		txtTenCD.setEditable(false);
		txtSoLuongCan.setEditable(false);
		txtDonGia.setEditable(false);
		dcsThoiHan.setEnabled(false);
		btnLuu.setEnabled(false);
		btnHuy.setEnabled(false);
		btnThem.setEnabled(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource();
		if (o.equals(tblCongDoan)) {
			int rowSelected = tblCongDoan.getSelectedRow();
			if (rowSelected != -1) {
				hienThiCongDoanLenTxt(rowSelected);
				moKhoaTextField(false);
				btnCapNhat.setEnabled(true);
				btnXoa.setEnabled(true);
				btnThem.setEnabled(true);
				btnHuy.setEnabled(false);
				btnLuu.setEnabled(false);
				lblErrTenCD.setText("");
				lblErrSoLuongCan.setText("");
				lblErrDonGia.setText("");
				lblErrThoiHan.setText("");
				lblErrThuTu.setText("");
			}
		} else if (o.equals(tblDanhSachSanPham)) {
			int rowSelected = tblDanhSachSanPham.getSelectedRow();
			if (rowSelected != -1) {
				taiDuLieuLenBangCongDoan();
				hienThiSanPhamLenTxt(rowSelected);
				if (tblCongDoan.getRowCount() != 0) {
					tblCongDoan.setRowSelectionInterval(0, 0);
					hienThiCongDoanLenTxt(0);
				} else {
					xoaTrangTextField();
				}
			}
			if (tblCongDoan.getRowCount() == 0) {
				btnCapNhat.setEnabled(false);
				btnXoa.setEnabled(false);
			} else {
				btnCapNhat.setEnabled(true);
				btnXoa.setEnabled(true);
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public boolean kiemTraDuLieuHopLe() {
		//Ten CD
		boolean flag = true;
		int soLuongCan = 0;
		double donGia = 0;
		int thuTuLam = 0;
		if (txtTenCD.getText().trim().equals("")) {
			lblErrTenCD.setText(stErrKhongDeTrong);
			flag = false;
		} else if (!txtTenCD.getText().matches("^[A-Za-zÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ]{2,}(\\s[A-Za-zÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ]{1,})*$")) {
			lblErrTenCD.setText(stChuaKiTuChu);
			flag = false;
		} else {
			lblErrTenCD.setText("");
		}
		// so luong can
		try {
			soLuongCan = Integer.parseInt(txtSoLuongCan.getText());
		} catch (Exception e) {
			lblErrSoLuongCan.setText(stErrSoLuong);
			flag = false;
		}

		if (soLuongCan > 0) {
			lblErrSoLuongCan.setText("");
		}
		if (soLuongCan <= 0) {
			lblErrSoLuongCan.setText(stErrSoLuong);
			flag = false;
		} else {
			lblErrSoLuongCan.setText("");
		}

		SanPham sanPham = sanPham_DAO.layMotSanPhamTheoMa(txtHienThiMaSP.getText());
		if (soLuongCan < sanPham.getSoLuongSanPham()) {
			lblErrSoLuongCan.setText(stSoLuongPhaiLonHonHoacBang + sanPham.getSoLuongSanPham());
			flag = false;
		}else if (soLuongCan > sanPham.getSoLuongSanPham()+ sanPham.getSoLuongSanPham()*0.5) {
			lblErrSoLuongCan.setText(stSoLuongPhaiLonHonHoacBang + sanPham.getSoLuongSanPham());
			flag = false;
		} else {
			lblErrSoLuongCan.setText("");
		}
		// Thu tu lam
		try {
			thuTuLam = Integer.parseInt(txtThuTu.getText());
		} catch (Exception e) {
			lblErrThuTu.setText(stSoNguyen);
			flag = false;
		}

		String maSanPham = txtHienThiMaSP.getText().trim();
		ArrayList<CongDoan> dsCongDoan = congDoan_DAO.layRaThuTuLamLonNhatCuaMotSanPham(maSanPham);
		int thuTuMax = 0;
		if (dsCongDoan.size() > 0) {
			thuTuMax = dsCongDoan.get(0).getThuTuCongDoan();
		}
		if (thuTuLam <= 0) {
			lblErrThuTu.setText(stThuTuLam);
			flag = false;
		} else if (thuTuLam > thuTuMax + 1 && oFlag.equals(btnThem)) {
			lblErrThuTu.setText(stThuTuLamPhaiLonHon + " " + (thuTuMax + 1));
			flag = false;
		} else if (oFlag.equals(btnCapNhat) && thuTuLam > thuTuMax + 1 && dsCongDoan.size() > 1) {
			lblErrThuTu.setText(stThuTuLamPhaiLonHon + " " + (thuTuMax + 1));
			flag = false;
		} else if (oFlag.equals(btnCapNhat) && thuTuLam > thuTuMax && dsCongDoan.size() == 1) {
			lblErrThuTu.setText(stThuTuLamPhaiLonHon + " " + (thuTuMax));
			flag = false;
		} else {
			lblErrThuTu.setText("");
		}

		if (oFlag.equals(btnCapNhat)) {
			CongDoan congDoanDangCapNhat = congDoan_DAO.layMotCongDoanTheoMaCongDoan(txtMaCD.getText().trim());

			ArrayList<CongDoan> dsCongDoanCungThuTu = congDoan_DAO.layDanhSachCongDoanTheoThuTuSanPham(congDoanDangCapNhat.getSanPham().getMaSanPham(), congDoanDangCapNhat.getThuTuCongDoan());
			if (dsCongDoanCungThuTu.size() == 1 && dsCongDoanCungThuTu.get(0).getThuTuCongDoan() != thuTuLam
					&& dsCongDoanCungThuTu.get(0).getThuTuCongDoan() != thuTuMax) {
				lblErrThuTu.setText(stThuTuCongDoanHienTai);
				flag = false;
			}
		}

		// ThoiHan
		if (dcsThoiHan.getDate().before(new Date())) {
			lblErrThoiHan.setText(stSauNgayHienTai);
			flag = false;
		} else {
			lblErrThoiHan.setText("");
		}
		// don gia
		try {
			donGia = Double.parseDouble(txtDonGia.getText());
		} catch (Exception e) {
			lblErrDonGia.setText(stErrSoLuong);
			flag = false;
		}
		if (donGia > 0) {
			lblErrDonGia.setText("");
		}
		if (donGia <= 0) {
			lblErrDonGia.setText(stErrSoLuong);
			flag = false;
		}
		// kiểm tra rỗng
		if (txtTenCD.getText().equals("")) {
			lblErrTenCD.setText(stErrKhongDeTrong);
			flag = false;
		}
		if (txtDonGia.getText().equals("")) {
			lblErrDonGia.setText(stErrKhongDeTrong);
			flag = false;
		}
		if (txtSoLuongCan.getText().equals("")) {
			lblErrSoLuongCan.setText(stErrKhongDeTrong);
			flag = false;
		}
		return flag;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnThem)) {
			txtMaCD.setText(congDoan_DAO.layRaMaCongDoanDeThem());
			moKhoaTextField(true);
			xoaTrangTextField();
			txtMaCD.setText(congDoan_DAO.layRaMaCongDoanDeThem());
			oFlag = e.getSource();
			// mở | khóa các btn
			btnCapNhat.setEnabled(false);
			btnXoa.setEnabled(false);
			btnThem.setEnabled(false);
			btnHuy.setEnabled(true);
			btnLuu.setEnabled(true);
		}

		else if (o.equals(btnCapNhat)) {
			moKhoaTextField(true);
			oFlag = e.getSource();
			// mở | khóa các btn
			btnCapNhat.setEnabled(false);
			btnXoa.setEnabled(false);
			btnThem.setEnabled(false);
			btnHuy.setEnabled(true);
			btnLuu.setEnabled(true);
		} 
		else if (o.equals(btnHuy)) {
			moKhoaTextField(false);
			if (tblCongDoan.getRowCount() != 0) {
				hienThiCongDoanLenTxt(0);
				tblCongDoan.setRowSelectionInterval(0, 0);
			} else {
				xoaTrangTextField();
			}
			// mở | khóa các btn
			btnCapNhat.setEnabled(true);
			btnXoa.setEnabled(true);
			btnThem.setEnabled(true);
			btnHuy.setEnabled(false);
			btnLuu.setEnabled(false);

			lblErrTenCD.setText("");
			lblErrSoLuongCan.setText("");
			lblErrDonGia.setText("");
			lblErrThoiHan.setText("");
			lblErrThuTu.setText("");

			if (tblCongDoan.getRowCount() == 0) {
				btnCapNhat.setEnabled(false);
				btnXoa.setEnabled(false);
			}
		}

		else if (o.equals(btnXoa)) {
			int rowSelected = tblCongDoan.getSelectedRow();
			if (rowSelected != -1) {
				int coXacNhanXoa = JOptionPane.showConfirmDialog(null, stBanXacNhanXoa, stThongbao, JOptionPane.ERROR_MESSAGE);
				if (coXacNhanXoa == 0) {
					boolean coXoaDuoc = congDoan_DAO.xoaMotCongDoanTheoMa(tblCongDoan.getValueAt(tblCongDoan.getSelectedRow(), 1).toString());
					if (coXoaDuoc) {
						JOptionPane.showMessageDialog(null, stXoaThanhCong, stThongbao, JOptionPane.INFORMATION_MESSAGE);
						taiDuLieuLenBangCongDoan();
					} else {
						JOptionPane.showMessageDialog(null, stXoaThatBai, stThongbao, JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		} 
		else if (o.equals(btnLuu)) {
			if (oFlag.equals(btnThem)) {
				boolean kiemTra = kiemTraDuLieuHopLe();
				if (!kiemTra) {
					return;
				}
				lblErrTenCD.setText("");
				lblErrSoLuongCan.setText("");
				lblErrDonGia.setText("");
				lblErrThoiHan.setText("");
				int soLuongCan = 0;
				double donGia = 0;
				int thuTuLam = 0;
				String maCongDoan = txtMaCD.getText();
				String tenCongDoan = txtTenCD.getText();
				try {
					soLuongCan = Integer.parseInt(txtSoLuongCan.getText());
					donGia = Double.parseDouble(txtDonGia.getText());
					thuTuLam = Integer.parseInt(txtThuTu.getText());
				} catch (Exception e2) {
					System.out.println(e2.getMessage());
				}
				SanPham sanPham = sanPham_DAO.layMotSanPhamTheoMa(txtHienThiMaSP.getText());
				boolean coThemDuoc = congDoan_DAO.themMotCongDoan(new CongDoan(maCongDoan, thuTuLam, tenCongDoan, soLuongCan, "0%", dcsThoiHan.getDate(), sanPham, donGia));
				if (coThemDuoc) {
					moKhoaTextField(false);
					taiDuLieuLenBangCongDoan();
					btnCapNhat.setEnabled(true);
					btnXoa.setEnabled(true);
					btnThem.setEnabled(true);
					btnHuy.setEnabled(false);
					btnLuu.setEnabled(false);
					oFlag = null;
					JOptionPane.showMessageDialog(null, stThemThanhCong, stThongbao, JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, stThemThatBai, stThongbao, JOptionPane.INFORMATION_MESSAGE);
				}
			} 
			else if (oFlag.equals(btnCapNhat)) {
				boolean kiemTra = kiemTraDuLieuHopLe();
				if (!kiemTra) {
					return;
				}
				lblErrTenCD.setText("");
				lblErrSoLuongCan.setText("");
				lblErrDonGia.setText("");
				lblErrThoiHan.setText("");
				int soLuongCan = 0;
				double donGia = 0;
				int thuTuLam = 0;
				String maCongDoan = txtMaCD.getText();
				String tenCongDoan = txtTenCD.getText();
				try {
					soLuongCan = Integer.parseInt(txtSoLuongCan.getText());
					donGia = Double.parseDouble(txtDonGia.getText());
					thuTuLam = Integer.parseInt(txtThuTu.getText());
				} catch (Exception e2) {
					System.out.println(e2.getMessage());
				}
				SanPham sanPham = sanPham_DAO.layMotSanPhamTheoMa(txtHienThiMaSP.getText());
				boolean coSuaDuoc = congDoan_DAO.suaMotCongDoan(new CongDoan(maCongDoan, thuTuLam, tenCongDoan, soLuongCan, "0%", dcsThoiHan.getDate(), sanPham, donGia));
				if (coSuaDuoc) {
					moKhoaTextField(false);
					taiDuLieuLenBangCongDoan();
					btnCapNhat.setEnabled(true);
					btnXoa.setEnabled(true);
					btnThem.setEnabled(true);
					btnHuy.setEnabled(false);
					btnLuu.setEnabled(false);
					oFlag = null;
					JOptionPane.showMessageDialog(null, stCapNhatThanhCong, stThongbao, JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, stCapNhatThatBai, stThongbao, JOptionPane.INFORMATION_MESSAGE);
				}
			}

		}
	}

	private void btnThemNhieuMouseClicked(java.awt.event.MouseEvent evt) {
		File file = new File("./excelData");
		JFileChooser fileChooser = new JFileChooser(file.getAbsolutePath());
		fileChooser.setCurrentDirectory(new File("../excelData"));
		fileChooser.removeChoosableFileFilter(fileChooser.getFileFilter());
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel File (.xlsx)", "xlsx");
		fileChooser.setFileFilter(filter);
		int count = 0, total = 0;
		int respone = fileChooser.showSaveDialog(null);
		if (respone == JFileChooser.APPROVE_OPTION) {
			File inputFile = fileChooser.getSelectedFile();

			try (FileInputStream in = new FileInputStream(inputFile)) {
				try (XSSFWorkbook importedFile = new XSSFWorkbook(in)) {
					XSSFSheet sheet1 = importedFile.getSheetAt(0);
					Iterator<Row> rowIterator = sheet1.iterator();
					while (rowIterator.hasNext()) {
						total++;
						Row row = rowIterator.next();
						Iterator<Cell> cellItera = row.cellIterator();
						// khai báo biến 
						int thuTuLam = 0, soLuongCan = 0;
						String tenCongDoan = "";
						Date thoiHan = new Date();
						String maSanPham = "";
						String tinhTrang = "0%";
						double tienLuong = 0f;
						try {
							while (cellItera.hasNext()) {
								Cell cell = cellItera.next();
								if (row.getRowNum() == 0) {
									continue;
								} else {
									if (cell.getColumnIndex() == 0) {
										thuTuLam = (int) cell.getNumericCellValue();
									} else if (cell.getColumnIndex() == 1) {
										tenCongDoan = cell.getStringCellValue();
									} else if (cell.getColumnIndex() == 2) {
										soLuongCan = (int) cell.getNumericCellValue();
									} else if (cell.getColumnIndex() == 3) {
										try {
											String chuoiThoiHan = cell.getStringCellValue();
											thoiHan = new SimpleDateFormat("yyyy-MM-dd").parse(chuoiThoiHan);
										} catch (Exception e) {
											System.out.println(e.getMessage());
										}
									} else if (cell.getColumnIndex() == 4) {
										maSanPham = cell.getStringCellValue();
									} else if (cell.getColumnIndex() == 5) {
										tienLuong = cell.getNumericCellValue();
									}
								}
							}

							SanPham sanPham = sanPham_DAO.layMotSanPhamTheoMa(maSanPham);
							String maCongDoan = congDoan_DAO.layRaMaCongDoanDeThem();
							boolean coThemDuoc = congDoan_DAO.themMotCongDoan(new CongDoan(maCongDoan, thuTuLam, tenCongDoan, soLuongCan, tinhTrang, thoiHan, sanPham, tienLuong));

							if (coThemDuoc) {
								count++;
							}
						} catch (Exception e) {
							System.out.println(e.getMessage());
						}
					}
				}
				in.close();
				JOptionPane.showMessageDialog(null, stThemThanhCong + " " + count + " " + stTren + " " + (--total) + " " + stCongDoan);
			} catch (FileNotFoundException ex) {
				JOptionPane.showMessageDialog(null, stKhongTimThayFile, stThongbao, JOptionPane.ERROR_MESSAGE);
			} catch (IOException ex) {
				JOptionPane.showMessageDialog(null, stKhongDocDuocFile, stThongbao, JOptionPane.ERROR_MESSAGE);
			}
			if (count != 0) {
				try {
					taiDuLieuLenBangCongDoan();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		}
	}
}
