package UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JTable;

import Custom_UI.ScrollBarCustom;
import Dao.NhanVien_Dao;
import Dao.PhongBan_Dao;
import Entity.NhanVien;
import Entity.PhongBan;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import java.awt.Dimension;

public class NhanVien_GUI extends JPanel {
	/**
	 * Hoàng Huy Tới
	 */
	private static final long serialVersionUID = 1L;
	private JPanel btnAnhNhanVien;
	private JButton btnCapNhat;
	private JButton btnHuy;
	private JButton btnLuu;
	private JButton btnThem;
	private JButton btnThemNhieu;
	private JButton btnXoa;
	private JComboBox<String> cboChucVu;
	private JComboBox<String> cboPhongBan;
	private JDateChooser dcsNgaySinh;
	private JDateChooser dcsNgayVaoLam;
	private JPanel panelNhanVien;

	private JLabel lblAnhDaiDien;
	private JLabel lblChucVu;
	private JLabel lblDiaChi;
	private JLabel lblEmail;
	private JLabel lblErrDiaChi;
	private JLabel lblErrEmail;
	private JLabel lblErrHoVaTen;
	private JLabel lblErrLuongCoBan;
	private JLabel lblErrNgaySinh;
	private JLabel lblErrNgayVaoLam;
	private JLabel lblErrSoCCCD;
	private JLabel lblErrSoDienThoai;
	private JLabel lblGioiTinh;
	private JLabel lblHinhAnh;
	private JLabel lblHoVaTen;
	private JLabel lblLuongCoBan;
	private JLabel lblMaNhanVien;
	private JLabel lblNgaySinh;
	private JLabel lblNgayVaoLam;
	private JLabel lblPhongBan;
	private JLabel lblSoCCCD;
	private JLabel lblSoDienThoai;
	private JRadioButton rdoNam;
	private JRadioButton rdoNu;
	private JScrollPane srcNhanVien;
	private JTable tblNhanVien;
	private JTextField txtDiaChi;
	private JTextField txtEmail;
	private JTextField txtHoVaTen;
	private JTextField txtLuongCoBan;
	private JTextField txtMaNhanVien;
	private JTextField txtSoCCCD;
	private JTextField txtSoDienThoai;

	private NhanVien_Dao daoNhanVien = new NhanVien_Dao();
	private NhanVien nhanvienEntity;
	private DefaultTableModel model;
	private DateFormat df = new SimpleDateFormat("yyy-MM-dd");
	private DecimalFormat dcf = new DecimalFormat("###.00");
	private boolean isThem = false;
	private String stErrKhongDeTrong;
    private String stThongbao;
    private String stBanXacNhanXoa;
    private String stXoaThanhCong;
    private String stXoaThatBai;
    private String stThemThanhCong;
    private String stThemThatBai;
    private String stCapNhatThanhCong;
    private String stCapNhatThatBai;
    private String stErrHoTen;
    private String stErrSoCCCD;
    private String stErrEmail;
    private String stErrSdt;
    private String stErrNgaySinh;
    private String stErrNgayVaoLam;
    private String stErrNhanVienKhongDuTuoi;
    private String stErrTienKhongHopLe;
    private String stSoTienLonHonKhong;
    private String stTren;

	private JSeparator jSeparator1_2;
	private JSeparator jSeparator1_3;
	private JSeparator jSeparator1_4;
	private JSeparator jSeparator1_5;
	private JSeparator jSeparator1_6;
	private JLabel lblTieuDe;


	public NhanVien_GUI(String fileName) throws Exception {
		setBackground(new Color(255, 255, 255));
		initComponents();
		caiDatNgonNgu(fileName);
		excute();
		//taiDuLieuLenBang();
		this.txtMaNhanVien.setEditable(false);

	}

	public void caiDatNgonNgu(String fileName) throws FileNotFoundException, IOException {
        FileInputStream fis = new FileInputStream(fileName);
        Properties prop = new Properties();
        prop.load(fis);
        btnThemNhieu.setText(prop.getProperty("btnThemNhieu"));
        btnThem.setText(prop.getProperty("btnThem"));
        btnXoa.setText(prop.getProperty("btnXoa"));
        btnCapNhat.setText(prop.getProperty("btnCapNhat"));
        btnLuu.setText(prop.getProperty("btnLuu"));
        btnHuy.setText(prop.getProperty("btnHuy"));

        panelNhanVien.setBorder(new TitledBorder(prop.getProperty("nhanVien_ThongTinNV")));
        srcNhanVien.setBorder(new TitledBorder(prop.getProperty("nhanVien_TableNV")));
        
        lblTieuDe.setText(prop.getProperty("nhanVien_TieuDe"));
        lblMaNhanVien.setText(prop.getProperty("maNhanVien"));
        lblHoVaTen.setText(prop.getProperty("hoTen"));
        lblSoCCCD.setText(prop.getProperty("soCCCD"));
        lblEmail.setText(prop.getProperty("email"));
        lblSoDienThoai.setText(prop.getProperty("sdt"));
        lblDiaChi.setText(prop.getProperty("diaChi"));
        lblNgaySinh.setText(prop.getProperty("ngaySinh"));
        lblGioiTinh.setText(prop.getProperty("gioiTinh"));
        lblPhongBan.setText(prop.getProperty("phongBan"));
        lblChucVu.setText(prop.getProperty("chucVu"));
        lblNgayVaoLam.setText(prop.getProperty("ngayVaoLam"));
        lblLuongCoBan.setText(prop.getProperty("luongCoBan"));
        lblAnhDaiDien.setText(prop.getProperty("anhDaiDien"));
        rdoNam.setText(prop.getProperty("nam"));
        rdoNu.setText(prop.getProperty("nu"));

        doiNgonNguTable(tblNhanVien, 0, prop.getProperty("pcd_stt"));
        doiNgonNguTable(tblNhanVien, 1, lblMaNhanVien.getText());
        doiNgonNguTable(tblNhanVien, 2, lblHoVaTen.getText());
        doiNgonNguTable(tblNhanVien, 3, lblSoCCCD.getText());
        doiNgonNguTable(tblNhanVien, 4, lblGioiTinh.getText());
        doiNgonNguTable(tblNhanVien, 5, lblNgaySinh.getText());
        doiNgonNguTable(tblNhanVien, 6, lblSoDienThoai.getText());
        doiNgonNguTable(tblNhanVien, 7, lblDiaChi.getText());
        doiNgonNguTable(tblNhanVien, 8, lblAnhDaiDien.getText());
        doiNgonNguTable(tblNhanVien, 9, lblEmail.getText());
        doiNgonNguTable(tblNhanVien, 10, lblPhongBan.getText());
        doiNgonNguTable(tblNhanVien, 11, lblChucVu.getText());
        doiNgonNguTable(tblNhanVien, 12, lblNgayVaoLam.getText());
        doiNgonNguTable(tblNhanVien, 13, lblLuongCoBan.getText());

        stThongbao = prop.getProperty("thongBao");
        stBanXacNhanXoa = prop.getProperty("banXacNhanXoa");
        stXoaThanhCong = prop.getProperty("xoaThanhCong");
        stXoaThatBai = prop.getProperty("xoaThatBai");
        stThemThanhCong = prop.getProperty("themThanhCong");
        stThemThatBai = prop.getProperty("themThatBai");
        stCapNhatThanhCong = prop.getProperty("capNhatThanhCong");
        stCapNhatThatBai = prop.getProperty("capNhatThatBai");
        stErrKhongDeTrong = prop.getProperty("khongDeTrong");
        stErrHoTen = prop.getProperty("hoTenKhongHopLe");
        stErrSoCCCD = prop.getProperty("soCCCDKhongHopLe");
        stErrEmail = prop.getProperty("emailKhongHopLe");
        stErrSdt = prop.getProperty("sdtKhongHopLe");
        stErrNgaySinh = prop.getProperty("ngaySinhKhongHopLe");
        stErrNgayVaoLam = prop.getProperty("ngayVaoLamKhongHopLe");
        stErrNhanVienKhongDuTuoi = prop.getProperty("nhanVienChuaDuTuoi");
        stErrTienKhongHopLe = prop.getProperty("soTienKhongHople");
        stSoTienLonHonKhong = prop.getProperty("soTienLonHonKhong");
    }

    public void doiNgonNguTable(JTable table, int col_index, String col_name) {
        table.getColumnModel().getColumn(col_index).setHeaderValue(col_name);
    }
    
	public void excute() throws Exception {
		model = (DefaultTableModel) tblNhanVien.getModel();
		ButtonGroup btnGroup = new ButtonGroup();
		btnGroup.add(rdoNam);
		btnGroup.add(rdoNu);
		btnLuu.setEnabled(false);
		btnHuy.setEnabled(false);
		ConnectionDB.ConnectDB.getInstance().connect();
		setInit();
	}

	private void initComponents() {
		panelNhanVien = new JPanel();
		panelNhanVien.setBounds(26, 50, 1231, 422);
		
		lblHinhAnh = new JLabel();
		lblHinhAnh.setBounds(40, 100, 128, 128);
		
		btnAnhNhanVien = new JPanel();
		btnAnhNhanVien.setBounds(40, 260, 120, 40);
		btnAnhNhanVien.setBackground(Color.PINK);	
		lblAnhDaiDien = new JLabel();
		lblAnhDaiDien.setFont(new Font("Times New Roman", Font.BOLD, 16));
		
		lblErrHoVaTen = new JLabel();
		lblErrHoVaTen.setBounds(930, 70, 290, 29);		
		txtHoVaTen = new JTextField();
		txtHoVaTen.setBackground(Color.WHITE);
		txtHoVaTen.setBounds(930, 30, 290, 40);	
		lblHoVaTen = new JLabel();
		lblHoVaTen.setBounds(780, 40, 140, 40);
		
		txtEmail = new JTextField();
		txtEmail.setBackground(Color.WHITE);
		txtEmail.setBounds(930, 100, 290, 40);
		lblErrEmail = new JLabel();
		lblErrEmail.setBounds(930, 140, 290, 29);
		lblEmail = new JLabel();
		lblEmail.setBounds(780, 100, 140, 40);
		
		txtSoCCCD = new JTextField();
		txtSoCCCD.setBackground(Color.WHITE);
		txtSoCCCD.setBounds(420, 81, 280, 38);
		lblErrSoCCCD = new JLabel();
		lblErrSoCCCD.setBounds(420, 130, 290, 18);
		lblSoCCCD = new JLabel();
		lblSoCCCD.setBounds(270, 100, 140, 40);
		
		txtMaNhanVien = new JTextField();
		txtMaNhanVien.setBackground(Color.WHITE);
		txtMaNhanVien.setBounds(420, 30, 280, 40);
		lblMaNhanVien = new JLabel();
		lblMaNhanVien.setBounds(270, 40, 140, 40);
		
		txtSoDienThoai = new JTextField();
		txtSoDienThoai.setBackground(Color.WHITE);
		txtSoDienThoai.setBounds(420, 150, 280, 38);
		
		lblErrNgaySinh = new JLabel();
		lblErrNgaySinh.setBounds(420, 260, 300, 18);
		
		lblChucVu = new JLabel();
		lblChucVu.setBounds(780, 290, 130, 40);
		
		lblErrDiaChi = new JLabel();
		lblErrDiaChi.setBounds(930, 200, 280, 28);
		txtDiaChi = new JTextField();
		txtDiaChi.setBackground(Color.WHITE);
		txtDiaChi.setBounds(930, 160, 290, 40);
		
		lblGioiTinh = new JLabel();
		lblGioiTinh.setBounds(780, 220, 140, 40);
		
		lblErrLuongCoBan = new JLabel();
		lblErrLuongCoBan.setBounds(930, 390, 280, 29);
		txtLuongCoBan = new JTextField();
		txtLuongCoBan.setBackground(Color.WHITE);
		txtLuongCoBan.setBounds(930, 350, 290, 40);
		lblLuongCoBan = new JLabel();
		lblLuongCoBan.setBounds(780, 361, 140, 40);
		
		lblDiaChi = new JLabel();
		lblDiaChi.setBounds(780, 160, 140, 40);
		
		rdoNam = new JRadioButton();
		rdoNam.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		rdoNam.setBackground(Color.WHITE);
		rdoNam.setBounds(930, 230, 83, 30);
		rdoNu = new JRadioButton();
		rdoNu.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		rdoNu.setBackground(Color.WHITE);
		rdoNu.setBounds(1020, 230, 83, 30);
		
		dcsNgaySinh = new JDateChooser();
		dcsNgaySinh.setBackground(Color.WHITE);
		dcsNgaySinh.setBounds(420, 220, 280, 40);
		
		cboPhongBan = new JComboBox<>();
		cboPhongBan.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		cboPhongBan.setBackground(Color.WHITE);
		cboPhongBan.setBounds(420, 290, 280, 40);
		
		cboChucVu = new JComboBox<>();
		cboChucVu.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		cboChucVu.setBackground(Color.WHITE);
		cboChucVu.setBounds(930, 290, 290, 40);
		
		lblSoDienThoai = new JLabel();
		lblSoDienThoai.setBounds(270, 160, 140, 40);
		
		lblNgaySinh = new JLabel();
		lblNgaySinh.setBounds(270, 220, 140, 40);
		
		lblErrSoDienThoai = new JLabel();
		lblErrSoDienThoai.setBounds(420, 190, 300, 18);
		
		lblPhongBan = new JLabel();
		lblPhongBan.setBounds(270, 290, 130, 40);
		
		dcsNgayVaoLam = new JDateChooser();
		dcsNgayVaoLam.setBackground(Color.WHITE);
		dcsNgayVaoLam.setBounds(420, 350, 280, 40);
		lblNgayVaoLam = new JLabel();
		lblNgayVaoLam.setBounds(270, 350, 140, 40);
		lblErrNgayVaoLam = new JLabel();
		lblErrNgayVaoLam.setBounds(420, 390, 320, 18);
		
		srcNhanVien = new JScrollPane();
		srcNhanVien.setBounds(10, 548, 1257, 167);
		tblNhanVien = new JTable();

		setPreferredSize(new Dimension(1290, 750));
		lblTieuDe = new JLabel("QUẢN LÝ NHÂN VIÊN");
		lblTieuDe.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblTieuDe.setBounds(543, 11, 296, 40);
		add(lblTieuDe);
		
		LineBorder blackLineBorder = new LineBorder(Color.BLACK, 2);
		TitledBorder titledBorder = BorderFactory.createTitledBorder(blackLineBorder, "Thông tin nhân viên");
		panelNhanVien.setBorder(titledBorder);
		panelNhanVien.setBackground(new Color(255, 255, 255));
		panelNhanVien.setPreferredSize(new Dimension(1250, 500));
		panelNhanVien.setLayout(null);

		lblHinhAnh.setBackground(new Color(153, 0, 0));
		lblHinhAnh.setForeground(new Color(255, 0, 51));
		lblHinhAnh.setIcon(new ImageIcon(NhanVien_GUI.class.getResource("/image/anhNhanVien/man.png")));
		panelNhanVien.add(lblHinhAnh);

		lblAnhDaiDien.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnhDaiDien.setText("Ảnh đại diện");
		lblAnhDaiDien.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				lblAnhDaiDienMouseClicked(evt);
			}
		});

		javax.swing.GroupLayout btnAnhDaiDienLayout = new javax.swing.GroupLayout(btnAnhNhanVien);
		btnAnhNhanVien.setLayout(btnAnhDaiDienLayout);
		btnAnhDaiDienLayout.setHorizontalGroup(
				btnAnhDaiDienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnAnhDaiDienLayout.createSequentialGroup()
						.addGap(0, 0, Short.MAX_VALUE)
						.addComponent(lblAnhDaiDien, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
				);
		btnAnhDaiDienLayout.setVerticalGroup(
				btnAnhDaiDienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnAnhDaiDienLayout.createSequentialGroup()
						.addGap(0, 0, Short.MAX_VALUE)
						.addComponent(lblAnhDaiDien, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
				);
		panelNhanVien.add(btnAnhNhanVien);

		lblErrHoVaTen.setFont(new Font("Segoe UI", 0, 13));
		lblErrHoVaTen.setForeground(new Color(204, 0, 0));
		lblErrHoVaTen.setText("đây là dòng thông báo lỗi");
		panelNhanVien.add(lblErrHoVaTen);

		txtHoVaTen.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtHoVaTen.setBorder(null);
		panelNhanVien.add(txtHoVaTen);

		lblHoVaTen.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblHoVaTen.setText("Họ và tên:");
		panelNhanVien.add(lblHoVaTen);

		txtEmail.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtEmail.setBorder(null);
		panelNhanVien.add(txtEmail);

		lblErrEmail.setFont(new Font("Segoe UI", 0, 13));
		lblErrEmail.setForeground(new Color(204, 0, 0));
		lblErrEmail.setText("đây là dòng thông báo lỗi");
		panelNhanVien.add(lblErrEmail);

		lblEmail.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblEmail.setText("Email:");
		panelNhanVien.add(lblEmail);

		txtSoCCCD.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtSoCCCD.setBorder(null);
		panelNhanVien.add(txtSoCCCD);

		lblErrSoCCCD.setFont(new java.awt.Font("Segoe UI", 0, 13));
		lblErrSoCCCD.setForeground(new java.awt.Color(204, 0, 0));
		lblErrSoCCCD.setText("đây là dòng thông báo lỗi");
		panelNhanVien.add(lblErrSoCCCD);

		lblSoCCCD.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblSoCCCD.setText("Số CCCD:");
		panelNhanVien.add(lblSoCCCD);

		txtMaNhanVien.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtMaNhanVien.setBorder(null);
		panelNhanVien.add(txtMaNhanVien);

		lblMaNhanVien.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblMaNhanVien.setText("Mã nhân viên:");
		panelNhanVien.add(lblMaNhanVien);

		txtSoDienThoai.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtSoDienThoai.setBorder(null);
		panelNhanVien.add(txtSoDienThoai);

		lblErrNgaySinh.setFont(new java.awt.Font("Segoe UI", 0, 13));
		lblErrNgaySinh.setForeground(new java.awt.Color(204, 0, 0));
		lblErrNgaySinh.setText("đây là dòng thông báo lỗi");
		panelNhanVien.add(lblErrNgaySinh);

		lblChucVu.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblChucVu.setText("Chức vụ");
		panelNhanVien.add(lblChucVu);

		lblErrDiaChi.setFont(new java.awt.Font("Segoe UI", 0, 13));
		lblErrDiaChi.setForeground(new java.awt.Color(204, 0, 0));
		lblErrDiaChi.setText("đây là dòng thông báo lỗi");
		panelNhanVien.add(lblErrDiaChi);

		txtDiaChi.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtDiaChi.setBorder(null);
		panelNhanVien.add(txtDiaChi);

		lblGioiTinh.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblGioiTinh.setText("Giới tính");
		panelNhanVien.add(lblGioiTinh);

		lblErrLuongCoBan.setFont(new java.awt.Font("Segoe UI", 0, 13));
		lblErrLuongCoBan.setForeground(new java.awt.Color(204, 0, 0));
		lblErrLuongCoBan.setText("đây là dòng thông báo lỗi");
		panelNhanVien.add(lblErrLuongCoBan);

		txtLuongCoBan.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtLuongCoBan.setBorder(null);
		panelNhanVien.add(txtLuongCoBan);

		lblLuongCoBan.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblLuongCoBan.setText("Lương cơ bản:");
		panelNhanVien.add(lblLuongCoBan);

		lblDiaChi.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblDiaChi.setText("Địa chỉ:");
		panelNhanVien.add(lblDiaChi);

		rdoNam.setSelected(true);
		rdoNam.setText("Nam");
		rdoNam.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		rdoNam.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				rdoNamActionPerformed(evt);
			}
		});
		panelNhanVien.add(rdoNam);

		rdoNu.setText("Nữ");
		rdoNu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		rdoNu.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				rdoNuActionPerformed(evt);
			}
		});
		panelNhanVien.add(rdoNu);

		dcsNgaySinh.setDateFormatString("yyyy-MM-dd");
		dcsNgaySinh.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseExited(java.awt.event.MouseEvent evt) {
				dcsNgaySinhMouseExited(evt);
			}
		});
		dcsNgaySinh.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
			public void propertyChange(java.beans.PropertyChangeEvent evt) {
				dcsNgaySinhPropertyChange(evt);
			}
		});
		panelNhanVien.add(dcsNgaySinh);

		cboPhongBan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Phòng tài chính", "Phòng kinh doanh", "Phòng nhân sự" }));
		cboPhongBan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		panelNhanVien.add(cboPhongBan);

		cboChucVu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Quản lý", "Nhân viên", "Nhân viên kế toán"}));
		cboChucVu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		cboChucVu.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cboChucVuActionPerformed(evt);
			}
		});
		setLayout(null);
		panelNhanVien.add(cboChucVu);

		lblSoDienThoai.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblSoDienThoai.setText("Số điện thoại:");
		panelNhanVien.add(lblSoDienThoai);

		lblNgaySinh.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNgaySinh.setText("Ngày sinh:");
		panelNhanVien.add(lblNgaySinh);

		lblErrSoDienThoai.setFont(new java.awt.Font("Segoe UI", 0, 13));
		lblErrSoDienThoai.setForeground(new java.awt.Color(204, 0, 0));
		lblErrSoDienThoai.setText("đây là dòng thông báo lỗi");
		panelNhanVien.add(lblErrSoDienThoai);

		lblPhongBan.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblPhongBan.setText("Phòng ban:");
		panelNhanVien.add(lblPhongBan);

		dcsNgayVaoLam.setDateFormatString("yyyy-MM-dd");
		panelNhanVien.add(dcsNgayVaoLam);

		lblNgayVaoLam.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNgayVaoLam.setText("Ngày vào làm:");
		panelNhanVien.add(lblNgayVaoLam);

		lblErrNgayVaoLam.setFont(new java.awt.Font("Segoe UI", 0, 13));
		lblErrNgayVaoLam.setForeground(new java.awt.Color(204, 0, 0));
		lblErrNgayVaoLam.setText("đây là dòng thông báo lỗi");
		panelNhanVien.add(lblErrNgayVaoLam);

		add(panelNhanVien);

		JSeparator jSeparator1 = new JSeparator();
		jSeparator1.setForeground(Color.BLACK);
		jSeparator1.setBounds(420, 70, 280, 10);
		panelNhanVien.add(jSeparator1);

		JSeparator jSeparator1_1 = new JSeparator();
		jSeparator1_1.setForeground(Color.BLACK);
		jSeparator1_1.setBounds(420, 120, 280, 10);
		panelNhanVien.add(jSeparator1_1);

		jSeparator1_2 = new JSeparator();
		jSeparator1_2.setForeground(Color.BLACK);
		jSeparator1_2.setBounds(420, 188, 280, 10);
		panelNhanVien.add(jSeparator1_2);

		jSeparator1_3 = new JSeparator();
		jSeparator1_3.setForeground(Color.BLACK);
		jSeparator1_3.setBounds(930, 70, 290, 10);
		panelNhanVien.add(jSeparator1_3);

		jSeparator1_4 = new JSeparator();
		jSeparator1_4.setForeground(Color.BLACK);
		jSeparator1_4.setBounds(930, 140, 290, 10);
		panelNhanVien.add(jSeparator1_4);

		jSeparator1_5 = new JSeparator();
		jSeparator1_5.setForeground(Color.BLACK);
		jSeparator1_5.setBounds(930, 200, 290, 10);
		panelNhanVien.add(jSeparator1_5);

		jSeparator1_6 = new JSeparator();
		jSeparator1_6.setForeground(Color.BLACK);
		jSeparator1_6.setBounds(930, 390, 290, 10);
		panelNhanVien.add(jSeparator1_6);

		btnThemNhieu = new JButton();
		btnThemNhieu.setBounds(51, 483, 170, 40);
		add(btnThemNhieu);
		btnThemNhieu.setBackground(new Color(255, 215, 0));
		btnThemNhieu.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnThemNhieu.setIcon(new ImageIcon(NhanVien_GUI.class.getResource("/image/icon/them.png")));
		btnThemNhieu.setText("Thêm nhiều");
		btnThemNhieu.setBorder(null);
		btnThem = new JButton();
		btnThem.setBounds(260, 483, 170, 40);
		add(btnThem);
		btnThemNhieu.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnThemNhieuActionPerformed(evt);
			}
		});

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
		btnXoa.setBounds(474, 483, 160, 40);
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
		btnCapNhat.setBounds(666, 483, 160, 40);
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
		btnLuu.setBounds(861, 483, 160, 40);
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
		btnHuy.setBounds(1053, 482, 170, 40);
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

		LineBorder blackLineBorder1 = new LineBorder(Color.BLACK, 2);
		TitledBorder titledBorder1 = BorderFactory.createTitledBorder(blackLineBorder1, "Danh sách nhân viên");
		srcNhanVien.setBorder(titledBorder1);

		srcNhanVien.setBackground(Color.WHITE);
		tblNhanVien.getTableHeader().setBackground(new Color(128, 200, 255));

		tblNhanVien.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				"STT", "Mã nhân viên", "H\u1ECD v\u00E0 t\u00EAn", "S\u1ED1 CCCD", "Gi\u1EDBi t\u00EDnh", "Ng\u00E0y sinh", "S\u1ED1 \u0111i\u1EC7n tho\u1EA1i", "\u0110\u1ECBa ch\u1EC9", "\u1EA2nh \u0111\u1EA1i di\u1EC7n", "Email", "Ph\u00F2ng Ban", "Ch\u1EE9c v\u1EE5", "Ng\u00E0y v\u00E0o l\u00E0m", "L\u01B0\u01A1ng c\u01A1 b\u1EA3n"
			}
		));
		tblNhanVien.getColumnModel().getColumn(0).setPreferredWidth(35);
		tblNhanVien.getColumnModel().getColumn(1).setPreferredWidth(80);
		tblNhanVien.getColumnModel().getColumn(13).setPreferredWidth(101);
		tblNhanVien.setSelectionBackground(new Color(255, 215, 0));
		tblNhanVien.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 15));
		tblNhanVien.getTableHeader().setOpaque(false);
		((DefaultTableCellRenderer) tblNhanVien.getTableHeader().getDefaultRenderer())
		.setHorizontalAlignment(JLabel.CENTER);
		tblNhanVien.setRowHeight(25);
		
		tblNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				tblNhanVienMouseClicked(evt);
			}
		});
		srcNhanVien.setViewportView(tblNhanVien);
		add(srcNhanVien);
		ScrollBarCustom scrollBar = new ScrollBarCustom();
		scrollBar.setForeground(Color.RED);
		srcNhanVien.setVerticalScrollBar(scrollBar);
	}

	private void tblNhanVienMouseClicked(java.awt.event.MouseEvent evt) {
		lblErrHoVaTen.setText("");
		lblErrSoCCCD.setText("");
		lblErrSoDienThoai.setText("");
		lblErrEmail.setText("");
		lblErrNgaySinh.setText("");
		lblErrNgayVaoLam.setText("");
		lblErrLuongCoBan.setText("");
		lblErrDiaChi.setText("");
		try {
			setHidden(btnLuu, btnHuy);
			setShow(btnThem, btnXoa, btnCapNhat, btnThemNhieu);
			hienThiDuLieuLenTxt(tblNhanVien.getSelectedRow());
			setEnableForInput(false);
		} catch (ParseException ex) {
			JOptionPane.showMessageDialog(null, "ERRO");

		}
		setEditTextDateChooser();
	}

	public void setInit() {
		lblErrHoVaTen.setText("");
		lblErrSoCCCD.setText("");
		lblErrEmail.setText("");
		lblErrSoDienThoai.setText("");
		lblErrDiaChi.setText("");
		lblErrNgaySinh.setText("");
		lblErrNgayVaoLam.setText("");
		lblErrLuongCoBan.setText("");
	}

	public void taiDuLieuLenBang() throws ParseException {
		while (tblNhanVien.getRowCount() != 0) {
			model.removeRow(0);
		}
		cboPhongBan.removeAllItems();
		PhongBan_Dao daoPhongBan = new PhongBan_Dao();
		ArrayList<PhongBan> phongBan = daoPhongBan.layDanhSachPhongBan();
		if (phongBan.size() > 0) {
			for (PhongBan pb : phongBan) {
				cboPhongBan.addItem(pb.getTenPhongBan());
			}
			ArrayList<NhanVien> danhSachNhanVien = daoNhanVien.layDanhSachNhanVien();
			for (NhanVien nv : danhSachNhanVien) {
				String data[] = {(model.getRowCount() + 1) + "", nv.getMaNhanVien(), nv.getHoTen(), nv.getMaCCCD(), nv.isGioiTinh() ? "Nam" : "Nữ", nv.getNgaySinh().toString(),
						nv.getSoDienThoai(), nv.getDiaChi(), nv.getAnhDaiDien(), nv.getEmail(), nv.getPhongBan().getTenPhongBan(),
						nv.getChucVu(), nv.getNgayVaoLam().toString(), dcf.format(nv.getLuongCoBan()).replaceAll("\\,", ".")};
				model.addRow(data);
			}
			if (tblNhanVien.getRowCount() != 0) {
				tblNhanVien.setRowSelectionInterval(0, 0);
				hienThiDuLieuLenTxt(0);
			}
		}
	}
	public void hienThiDuLieuLenTxt(int dong) throws ParseException {

		txtMaNhanVien.setText(tblNhanVien.getValueAt(dong, 1).toString());
		txtHoVaTen.setText(tblNhanVien.getValueAt(dong, 2).toString());
		txtSoCCCD.setText(tblNhanVien.getValueAt(dong, 3).toString());
		if (rdoNam.getText() == tblNhanVien.getValueAt(dong, 4).toString()) {
			rdoNam.setSelected(true);
		} else {
			rdoNu.setSelected(true);
		}
		dcsNgaySinh.setDate(df.parse(tblNhanVien.getValueAt(dong, 5).toString()));
		txtSoDienThoai.setText(tblNhanVien.getValueAt(dong, 6).toString());
		txtDiaChi.setText(tblNhanVien.getValueAt(dong, 7).toString());
		txtEmail.setText(tblNhanVien.getValueAt(dong, 9).toString());
		cboPhongBan.setSelectedItem(tblNhanVien.getValueAt(dong, 10).toString());
		cboChucVu.setSelectedItem(tblNhanVien.getValueAt(dong, 11).toString());
		dcsNgayVaoLam.setDate(df.parse(tblNhanVien.getValueAt(dong, 12).toString()));
		txtLuongCoBan.setText(tblNhanVien.getValueAt(dong, 13).toString().replaceAll("\\,", "."));
		lblHinhAnh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/anhNhanVien/" + tblNhanVien.getValueAt(dong, 8))));
	}

	private void lblAnhDaiDienMouseClicked(java.awt.event.MouseEvent evt) {
		JFileChooser fileChooser = new JFileChooser("d:");
		fileChooser.setCurrentDirectory(new File(".\\src\\image\\anhNhanVien"));
		int respone = fileChooser.showSaveDialog(null);
		if (respone == JFileChooser.APPROVE_OPTION) {
			File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
			System.out.println(file);
			String path = file.toString().split("src")[1].replace('\\', '/');
			System.out.println("path file split: " + file.toString().split("src")[1]);
			this.lblHinhAnh.setIcon(new ImageIcon(this.getClass().getResource(path)));
			System.out.println(this.lblHinhAnh.getIcon().toString());
		}
	}

	public void setHidden(JButton... btnHidden) {
		for (JButton jButton : btnHidden) {
			jButton.setEnabled(false);
		}
	}

	public void setEnableForInput(boolean kq) {
		this.txtHoVaTen.setEditable(kq);
		this.txtSoCCCD.setEditable(kq);
		this.txtEmail.setEditable(kq);
		this.txtSoDienThoai.setEditable(kq);
		this.txtDiaChi.setEditable(kq);
		//this.dcsNgaySinh.setEnabled(kq);
		//this.rdoNam.setEnabled(kq);
		//this.rdoNu.setEnabled(kq);
		//this.cboPhongBan.setEnabled(kq);
		//this.cboChucVu.setEnabled(kq);
		//this.dcsNgayVaoLam.setEnabled(kq);
		this.txtLuongCoBan.setEditable(kq);
		//this.lblAnhDaiDien.setEnabled(kq);
	}

	public void setShow(JButton... btnHidden) {
		for (JButton jButton : btnHidden) {
			jButton.setEnabled(true);
		}
	}

	public void setEditTextDateChooser() {
		JTextFieldDateEditor ngaySinh = (JTextFieldDateEditor) dcsNgaySinh.getDateEditor();
		JTextFieldDateEditor ngayVaoLam = (JTextFieldDateEditor) dcsNgayVaoLam.getDateEditor();
		ngaySinh.setEnabled(false);
		ngayVaoLam.setEnabled(false);
	}
	
	public boolean kiemTraDieuKienNhap() throws ParseException {
        boolean flag = true;
        // Ho và ten
        if (this.txtHoVaTen.getText().equals("")) {
            this.lblErrHoVaTen.setText(stErrKhongDeTrong);
            flag = false;
        } else if (!this.txtHoVaTen.getText().matches("^([A-ZĐÂÁƯ][a-zỳọáầảấờễàạằệếýộậốũứĩõúữịỗìềểẩớặòùồợãụủíỹắẫựỉỏừỷởóéửỵẳẹèẽổ"
                + "ẵẻỡơôưăêâđ]+)((\\s[A-ZĐÂÁƯ][a-zỳọáầảấờễàạằệếýộậốũứĩõúữịỗìềểẩớặòùồợãụủíỹắẫựỉỏừỷởóéửỵẳẹèẽổẵẻỡơôưăêâđ]+){1,})$")) {
            this.lblErrHoVaTen.setText(stErrHoTen);
            flag = false;
        } else {
            this.lblErrHoVaTen.setText("");
        }
        
        // CCCD
        if (txtSoCCCD.getText().equals("")) {
            lblErrSoCCCD.setText(stErrKhongDeTrong);
            flag = false;
        } else if (!txtSoCCCD.getText().matches("^[0-9]{12}$")) {
            lblErrSoCCCD.setText(stErrSoCCCD);
            flag = false;
        } else {
            lblErrSoCCCD.setText("");
        }
        
        // Email
        if (txtEmail.getText().equals("")) {
            lblErrEmail.setText(stErrKhongDeTrong);
        } else if (!txtEmail.getText().matches("^[a-z]\\w*@gmail.com$")) {
            lblErrEmail.setText(stErrEmail);
            flag = false;
        } else {
            lblErrEmail.setText("");
        }
        
        // Dia chi
        if (txtDiaChi.getText().equals("")) {
            lblErrDiaChi.setText(stErrKhongDeTrong);
            flag = false;
        } else {
            lblErrDiaChi.setText("");
        }
        
        // SDT
        if (txtSoDienThoai.getText().equals("")) {
            lblErrSoDienThoai.setText(stErrKhongDeTrong);
            flag = false;
        } else if (!txtSoDienThoai.getText().matches("^\\+84[1-9][0-9]{8}$")) {
            lblErrSoDienThoai.setText(stErrSdt);
            flag = false;
        } else {
            lblErrSoDienThoai.setText("");
        }
        
        // Ngay sinh
        if (new Date().before(dcsNgaySinh.getDate())) {
            lblErrNgaySinh.setText(stErrNgaySinh);
            flag = false;
        } else {
            lblErrNgaySinh.setText("");
        }
        // ngay vao lam
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        long khoangCach2Ngay = sdf.parse(sdf.format(dcsNgayVaoLam.getDate())).getTime() - sdf.parse(sdf.format(dcsNgaySinh.getDate())).getTime();
        long getYearDiff = TimeUnit.MILLISECONDS.toDays(khoangCach2Ngay) / 365;
        if (new Date().before(dcsNgayVaoLam.getDate())) {
            lblErrNgayVaoLam.setText(stErrNgayVaoLam);
            flag = false;
        } else if (getYearDiff < 18) {
            lblErrNgayVaoLam.setText(stErrNhanVienKhongDuTuoi);
            flag = false;
        } else {
            lblErrNgayVaoLam.setText("");
        }
        
        // luong co ban
        try {
            if (txtLuongCoBan.getText().trim().equals("")) {
                this.lblErrLuongCoBan.setText(stErrKhongDeTrong);
                flag = false;
            } else if (Double.parseDouble(txtLuongCoBan.getText()) <= 0) {
                this.lblErrLuongCoBan.setText(stSoTienLonHonKhong);
                flag = false;
            } else {
                this.lblErrLuongCoBan.setText("");
            }
        } catch (Exception e) {
            this.lblErrLuongCoBan.setText(stErrTienKhongHopLe);
            flag = false;
        }
        return flag;
    }
	
	private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {
		int rowSelected = tblNhanVien.getSelectedRow();
        if (rowSelected != -1) {
            if (JOptionPane.showConfirmDialog(null, stBanXacNhanXoa, stThongbao, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                if (daoNhanVien.xoaMotNhanVienTheoMa(tblNhanVien.getValueAt(tblNhanVien.getSelectedRow(), 1).toString())) {
                    JOptionPane.showMessageDialog(null, stXoaThanhCong, stThongbao, JOptionPane.INFORMATION_MESSAGE);
                    try {
                        taiDuLieuLenBang();
                    } catch (ParseException ex) {
                        JOptionPane.showMessageDialog(null, "Error", stThongbao, JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
					JOptionPane.showMessageDialog(null, stXoaThatBai, stThongbao, JOptionPane.INFORMATION_MESSAGE);
				}
            }
        }
	}
	
	private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {
		setHidden(btnThem, btnXoa, btnCapNhat, btnThemNhieu);
		setShow(btnLuu, btnHuy);
		int maNhaNVien = Integer.parseInt(tblNhanVien.getValueAt(tblNhanVien.getRowCount() - 1, 1).toString().split("V")[1]);
        this.txtMaNhanVien.setText("PPNV" + (maNhaNVien + 1));

		this.txtHoVaTen.setText("");
		this.txtSoCCCD.setText("");
		this.txtEmail.setText("");
		this.txtSoDienThoai.setText("");
		this.txtDiaChi.setText("");
		this.dcsNgaySinh.setDate(new Date());
		this.rdoNam.setSelected(true);
		this.cboPhongBan.setSelectedIndex(0);
		this.cboChucVu.setSelectedIndex(0);
		this.dcsNgayVaoLam.setDate(new Date());
		this.txtLuongCoBan.setText("");
		lblHinhAnh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/anhNhanVien/man.png")));
		setEnableForInput(true);
		isThem = true;
		setEditTextDateChooser();
	}

	private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {
		setHidden(btnThem, btnXoa, btnCapNhat);
		setShow(btnLuu, btnHuy);
		setEnableForInput(true);
		txtLuongCoBan.setText(txtLuongCoBan.getText().replaceAll(",", ""));
		setEditTextDateChooser();
		isThem = false;
	}
	
	private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            if (kiemTraDieuKienNhap()) {
                PhongBan_Dao dao = new PhongBan_Dao();
                PhongBan pb = dao.layMotPhongBanTheoTen(cboPhongBan.getSelectedItem().toString());
                System.out.println(pb.getMaPhongBan());
                String tienLuong = txtLuongCoBan.getText();
                if (txtLuongCoBan.getText().contains(",")) {
                    tienLuong = txtLuongCoBan.getText().replaceAll(",", "");
                }
                nhanvienEntity = new NhanVien(txtMaNhanVien.getText(), txtHoVaTen.getText(), dcsNgaySinh.getDate(),
                        txtSoCCCD.getText(), txtSoDienThoai.getText(), txtEmail.getText(), "111111", cboChucVu.getSelectedItem().toString(),
                        dcsNgayVaoLam.getDate(), Double.parseDouble(tienLuong), rdoNam.isSelected(), lblHinhAnh.getIcon().toString().split("anhNhanVien/")[1], txtDiaChi.getText(), pb);
                nhanvienEntity.toString();
                if (isThem) {
                    if (this.daoNhanVien.themMotNhanVien(nhanvienEntity)) {
                        taiDuLieuLenBang();
                        JOptionPane.showMessageDialog(this, stThemThanhCong);
                        setHidden(btnLuu, btnHuy);
                        setShow(btnThem, btnThemNhieu, btnXoa, btnCapNhat);
                        setEnableForInput(false);
                        isThem = false;
                    } else {
                        JOptionPane.showMessageDialog(null, stThemThatBai);
                        setHidden(btnLuu, btnHuy);
                        setShow(btnThem, btnThemNhieu, btnXoa, btnCapNhat);
                        setEnableForInput(false);
                    }
                } else {
                    if (this.daoNhanVien.suaThongTinMotNhanVien(nhanvienEntity)) {
                        taiDuLieuLenBang();
                        JOptionPane.showMessageDialog(this, stCapNhatThanhCong, stThongbao, JOptionPane.INFORMATION_MESSAGE);
                        setHidden(btnLuu, btnHuy);
                        setShow(btnThem, btnThemNhieu, btnCapNhat, btnXoa);
                        setEnableForInput(false);

                    } else {
                        JOptionPane.showMessageDialog(null, stCapNhatThatBai, stThongbao, JOptionPane.INFORMATION_MESSAGE);
                        setHidden(btnLuu, btnHuy);
                        setShow(btnThem, btnThemNhieu, btnXoa, btnCapNhat);
                        setEnableForInput(false);
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO");
        }
    }

	private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {
		setHidden(btnLuu, btnHuy);
		setShow(btnXoa, btnCapNhat, btnThemNhieu, btnThem);
		tblNhanVien.setRowSelectionInterval(0, 0);
		setEnableForInput(false);
		try {
			hienThiDuLieuLenTxt(0);
		} catch (ParseException ex) {
			Logger.getLogger(NhanVien_GUI.class.getName()).log(Level.SEVERE, null, ex);
		}
		isThem = false;
		setInit();
	}

	private void btnThemNhieuActionPerformed(java.awt.event.ActionEvent evt) {
        File file = new File("./excelData");
        JFileChooser openFileChooser = new JFileChooser(file.getAbsolutePath());
        openFileChooser.setDialogTitle("Open file");
        openFileChooser.removeChoosableFileFilter(openFileChooser.getFileFilter());
        FileFilter filter = new FileNameExtensionFilter("Excel FIle(.xlsx)", "xlsx");
        openFileChooser.setFileFilter(filter);
        int count = 0;
        if (openFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File inpuFile = openFileChooser.getSelectedFile();
            try {
                FileInputStream in = new FileInputStream(inpuFile);
                try {
                    try (XSSFWorkbook importFile = new XSSFWorkbook(in)) {
						XSSFSheet sheeth = importFile.getSheetAt(0);
						Row row;
						try {
						    for (int i = 1; i <= sheeth.getLastRowNum(); i++) {
						        try {
						            row = sheeth.getRow(i);
						            String hoTen = row.getCell(0).getStringCellValue();
						            String soCCCD = row.getCell(1).getStringCellValue();
						            String email = row.getCell(2).getStringCellValue();
						            String sdt = row.getCell(3).getStringCellValue();
						            String diaChi = row.getCell(4).getStringCellValue();
						            String ngaySinh = row.getCell(5).getStringCellValue();
						            String gioiTinh = row.getCell(6).getStringCellValue();
						            String phongBan = row.getCell(7).getStringCellValue();
						            String chucVu = row.getCell(8).getStringCellValue();
						            String ngayVaoLam = row.getCell(9).getStringCellValue();
						            String luongThoaThuan = row.getCell(10).getStringCellValue();
						            String maNhanVien = "PPNV100001";
						            daoNhanVien = new NhanVien_Dao();
						            if (daoNhanVien.layDanhSachNhanVien().size() > 0) {
						                maNhanVien = "PPNV" + (Integer.parseInt(daoNhanVien.layDanhSachNhanVien().get(daoNhanVien.layDanhSachNhanVien().size() - 1).getMaNhanVien().split("V")[1]) + 1);
						            }
						            Date ngaySinh1 = new SimpleDateFormat("yyyy-MM-dd").parse(ngaySinh);
						            Date ngayVaoLam1 = new SimpleDateFormat("yyyy-MM-dd").parse(ngayVaoLam);
						            PhongBan_Dao phongBanDao = new PhongBan_Dao();
						            PhongBan phongBan1 = phongBanDao.layMotPhongBanTheoTen(phongBan);
						            NhanVien nhanVienThem = new NhanVien(maNhanVien, hoTen, ngaySinh1, soCCCD, sdt, email,
						                    "111111", chucVu, ngayVaoLam1, Double.parseDouble(luongThoaThuan), "Nam".equals(gioiTinh) ? true : false, "man.png", diaChi, phongBan1);
						            if (nhanVienThem != null) {
						                if (daoNhanVien.themMotNhanVien(nhanVienThem)) {
						                    count++;
						                }
						            }
						        } catch (Exception e) {
						            System.out.println("erro");
						        }
						    }
						} catch (Exception e) {
						    System.out.println("Lỗi get data ");
						} finally {
						    JOptionPane.showMessageDialog(null, stThemThanhCong + " " + count + " " + stTren + " " + sheeth.getLastRowNum());
						    taiDuLieuLenBang();
						}
					} catch (HeadlessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                } catch (IOException ex) {
                    Logger.getLogger(NhanVien_GUI.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(NhanVien_GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(NhanVien_GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
	
	private void rdoNamActionPerformed(java.awt.event.ActionEvent evt) {
	}
	
	private void rdoNuActionPerformed(java.awt.event.ActionEvent evt) {
	}
	
	private void cboChucVuActionPerformed(java.awt.event.ActionEvent evt) {
	}
	
	private void dcsNgaySinhPropertyChange(java.beans.PropertyChangeEvent evt) {
	}
	
	private void dcsNgaySinhMouseExited(java.awt.event.MouseEvent evt) {
	}
	
}
