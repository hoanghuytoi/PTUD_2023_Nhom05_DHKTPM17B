package UI;

import java.awt.Color;
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

import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JTable;

import Custom_UI.ScrollBarCustom;
import Dao.CongNhan_Dao;
import Dao.ToNhom_Dao;
import Entity.CongNhan;
import Entity.ToNhom;

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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import java.awt.Dimension;

public class CongNhan_GUI extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel btnAnhCongNhan;
	private JButton btnCapNhat;
	private JButton btnHuy;
	private JButton btnLuu;
	private JButton btnThem;
	private JButton btnThemNhieu;
	private JButton btnXoa;
	private JComboBox<String> cboToNhom;
	private JDateChooser dcsNgaySinh;
	private JDateChooser dcsNgayVaoLam;
	private JPanel panelCongNhan;

	private JLabel lblAnhDaiDien;
	private JLabel lblDiaChi;
	private JLabel lblEmail;
	private JLabel lblErrDiaChi;
	private JLabel lblErrEmail;
	private JLabel lblErrHoVaTen;
	private JLabel lblErrNgaySinh;
	private JLabel lblErrNgayVaoLam;
	private JLabel lblErrSoCCCD;
	private JLabel lblErrSoDienThoai;
	private JLabel lblGioiTinh;
	private JLabel lblHinhAnh;
	private JLabel lblHoVaTen;
	private JLabel lblMaCongNhan;
	private JLabel lblNgaySinh;
	private JLabel lblNgayVaoLam;
	private JLabel lblToNhom;
	private JLabel lblSoCCCD;
	private JLabel lblSoDienThoai;
	private JRadioButton rdoNam;
	private JRadioButton rdoNu;
	private JScrollPane srcCongNhan;
	private JTable tblCongNhan;
	private JTextField txtDiaChi;
	private JTextField txtEmail;
	private JTextField txtHoVaTen;
	private JTextField txtMaCongNhan;
	private JTextField txtSoCCCD;
	private JTextField txtSoDienThoai;

	private CongNhan_Dao daoCongNhan = new CongNhan_Dao();
	private CongNhan congNhanEntity;
	private DefaultTableModel model;
	private DateFormat df = new SimpleDateFormat("yyy-MM-dd");
	private boolean isThem = false;
	private String stErrKhongDeTrong;
    private String stThongbao;
    private String stBanXacNhanXoa;
    private String stXoaThanhCong;
    private String stThemThanhCong;
    private String stThemThatBai;
    private String stTren;
    private String stKhongTimThayFile;
    private String stKhongDocDuocFile;
    private String stCapNhatThanhCong;
    private String stCapNhatThatBai;
    private String stErrHoTen;
    private String stErrSoCCCD;
    private String stErrEmail;
    private String stErrSdt;
    private String stErrNgaySinh;
    private String stErrNgayVaoLam;
    private String stErrCongNhanKhongDuTuoi;
    private String stCongNhan;

	private JSeparator jSeparator1_2;
	private JSeparator jSeparator1_3;
	private JSeparator jSeparator1_4;
	private JSeparator jSeparator1_5;
	private JLabel lblTieuDe;
	private ToNhom_Dao toNhom_Dao;


	public CongNhan_GUI(String fileName) throws Exception {
		initComponents();
		caiDatNgonNgu(fileName);
		ConnectionDB.ConnectDB.getInstance().connect();
        daoCongNhan = new CongNhan_Dao();
        toNhom_Dao = new ToNhom_Dao();
		excute();
		//taiDuLieuLenBang();
		this.txtMaCongNhan.setEditable(false);
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

        lblTieuDe.setText(prop.getProperty("TieuDe_congNhan"));
        lblMaCongNhan.setText(prop.getProperty("maCongNhan"));
        lblHoVaTen.setText(prop.getProperty("hoTen"));
        lblSoCCCD.setText(prop.getProperty("soCCCD"));
        lblEmail.setText(prop.getProperty("email"));
        lblSoDienThoai.setText(prop.getProperty("sdt"));
        lblDiaChi.setText(prop.getProperty("diaChi"));
        lblNgaySinh.setText(prop.getProperty("ngaySinh"));
        lblGioiTinh.setText(prop.getProperty("gioiTinh"));
        lblToNhom.setText(prop.getProperty("toNhom"));
        lblNgayVaoLam.setText(prop.getProperty("ngayVaoLam"));
        lblAnhDaiDien.setText(prop.getProperty("anhDaiDien"));
        rdoNam.setText(prop.getProperty("nam"));
        rdoNu.setText(prop.getProperty("nu"));
        stCongNhan = prop.getProperty("congNhan");

        panelCongNhan.setBorder(new TitledBorder(prop.getProperty("congNhan_ThongTinCN")));
        srcCongNhan.setBorder(new TitledBorder(prop.getProperty("congNhan_TableCN")));
        
        ChangeName(tblCongNhan, 0, prop.getProperty("pcd_stt"));
        ChangeName(tblCongNhan, 1, lblMaCongNhan.getText());
        ChangeName(tblCongNhan, 2, lblHoVaTen.getText());
        ChangeName(tblCongNhan, 3, lblSoCCCD.getText());
        ChangeName(tblCongNhan, 4, lblGioiTinh.getText());
        ChangeName(tblCongNhan, 5, lblNgaySinh.getText());
        ChangeName(tblCongNhan, 6, lblSoDienThoai.getText());
        ChangeName(tblCongNhan, 7, lblDiaChi.getText());
        ChangeName(tblCongNhan, 8, lblAnhDaiDien.getText());
        ChangeName(tblCongNhan, 9, lblEmail.getText());
        ChangeName(tblCongNhan, 10, lblToNhom.getText());
        ChangeName(tblCongNhan, 11, lblNgayVaoLam.getText());

        stThongbao = prop.getProperty("thongBao");
        stBanXacNhanXoa = prop.getProperty("banXacNhanXoa");
        stXoaThanhCong = prop.getProperty("xoaThanhCong");
        prop.getProperty("xoaThatBai");
        stThemThanhCong = prop.getProperty("themThanhCong");
        stThemThatBai = prop.getProperty("themThatBai");
        stTren = prop.getProperty("tren");
        stKhongDocDuocFile = prop.getProperty("khongDocDuocFile");
        stKhongTimThayFile = prop.getProperty("khongTimThayFile");
        stCapNhatThanhCong = prop.getProperty("capNhatThanhCong");
        stCapNhatThatBai = prop.getProperty("capNhatThatBai");
        stErrKhongDeTrong = prop.getProperty("khongDeTrong");
        stErrHoTen = prop.getProperty("hoTenKhongHopLe");
        stErrSoCCCD = prop.getProperty("soCCCDKhongHopLe");
        stErrEmail = prop.getProperty("emailKhongHopLe");
        stErrSdt = prop.getProperty("sdtKhongHopLe");
        stErrNgaySinh = prop.getProperty("ngaySinhKhongHopLe");
        stErrNgayVaoLam = prop.getProperty("ngayVaoLamKhongHopLe");
        stErrCongNhanKhongDuTuoi = prop.getProperty("congNhanChuaDuTuoi");
    }

    public void ChangeName(JTable table, int col_index, String col_name) {
        table.getColumnModel().getColumn(col_index).setHeaderValue(col_name);
    }

	public void excute() throws Exception {
		model = (DefaultTableModel) tblCongNhan.getModel();
		tblCongNhan.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
		tblCongNhan.getTableHeader().setOpaque(false);
		((DefaultTableCellRenderer) tblCongNhan.getTableHeader().getDefaultRenderer())
		.setHorizontalAlignment(JLabel.CENTER);
		tblCongNhan.setRowHeight(25);
		ButtonGroup btnGroup = new ButtonGroup();
		btnGroup.add(rdoNam);
		btnGroup.add(rdoNu);
		btnLuu.setEnabled(false);
		btnHuy.setEnabled(false);
		ConnectionDB.ConnectDB.getInstance().connect();
		setInit();
	}

	private void initComponents() {
		setBackground(new Color(255, 255, 255));

		panelCongNhan = new JPanel();
		panelCongNhan.setBounds(16, 53, 1240, 384);
		
		lblHinhAnh = new JLabel();
		lblHinhAnh.setIcon(new ImageIcon(CongNhan_GUI.class.getResource("/image/anhCongNhan/congNhan1.png")));
		lblHinhAnh.setBounds(32, 100, 128, 128);
		
		btnAnhCongNhan = new JPanel();
		btnAnhCongNhan.setBounds(40, 260, 120, 40);
		btnAnhCongNhan.setBackground(Color.PINK);
		
		lblAnhDaiDien = new JLabel();
		lblAnhDaiDien.setFont(new Font("Times New Roman", Font.BOLD, 16));
		
		lblErrHoVaTen = new JLabel();
		lblErrHoVaTen.setBounds(930, 70, 290, 29);
		
		txtHoVaTen = new JTextField();
		txtHoVaTen.setBackground(Color.WHITE);
		txtHoVaTen.setBounds(930, 30, 290, 40);
		lblHoVaTen = new JLabel();
		lblHoVaTen.setBounds(780, 51, 140, 29);
		
		txtEmail = new JTextField();
		txtEmail.setBackground(Color.WHITE);
		txtEmail.setBounds(930, 100, 290, 40);
		lblErrEmail = new JLabel();
		lblErrEmail.setBounds(930, 140, 290, 29);
		lblEmail = new JLabel();
		lblEmail.setBounds(780, 119, 140, 29);
		
		txtSoCCCD = new JTextField();
		txtSoCCCD.setBackground(Color.WHITE);
		txtSoCCCD.setBounds(420, 81, 280, 45);
		lblErrSoCCCD = new JLabel();
		lblErrSoCCCD.setBounds(420, 130, 290, 18);
		lblSoCCCD = new JLabel();
		lblSoCCCD.setBounds(270, 111, 140, 29);
		
		txtMaCongNhan = new JTextField();
		txtMaCongNhan.setBackground(Color.WHITE);
		txtMaCongNhan.setBounds(420, 30, 280, 40);
		lblMaCongNhan = new JLabel();
		lblMaCongNhan.setBounds(270, 51, 140, 29);
		
		txtSoDienThoai = new JTextField();
		txtSoDienThoai.setBackground(Color.WHITE);
		txtSoDienThoai.setBounds(420, 150, 280, 38);
		
		lblErrNgaySinh = new JLabel();
		lblErrNgaySinh.setBounds(420, 271, 300, 18);
		
		lblErrDiaChi = new JLabel();
		lblErrDiaChi.setBounds(930, 200, 280, 28);
		txtDiaChi = new JTextField();
		txtDiaChi.setBackground(Color.WHITE);
		txtDiaChi.setBounds(930, 160, 290, 40);
		
		lblGioiTinh = new JLabel();
		lblGioiTinh.setBounds(780, 249, 140, 29);
		
		lblDiaChi = new JLabel();
		lblDiaChi.setBounds(780, 182, 140, 29);
		
		rdoNam = new JRadioButton();
		rdoNam.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		rdoNam.setBackground(Color.WHITE);
		rdoNam.setBounds(930, 248, 71, 30);
		
		rdoNu = new JRadioButton();
		rdoNu.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		rdoNu.setBackground(Color.WHITE);
		rdoNu.setBounds(1021, 248, 64, 30);
		
		dcsNgaySinh = new JDateChooser();
		dcsNgaySinh.setBackground(Color.WHITE);
		dcsNgaySinh.setBounds(420, 231, 280, 40);
		
		cboToNhom = new JComboBox<>();
		cboToNhom.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		cboToNhom.setBackground(new Color(255, 255, 255));
		cboToNhom.setBounds(420, 322, 280, 40);
		
		lblSoDienThoai = new JLabel();
		lblSoDienThoai.setBounds(270, 171, 140, 29);
		lblNgaySinh = new JLabel();
		lblNgaySinh.setBounds(270, 249, 140, 29);
		lblErrSoDienThoai = new JLabel();
		lblErrSoDienThoai.setBounds(420, 190, 300, 29);
		lblToNhom = new JLabel();
		lblToNhom.setBounds(270, 333, 130, 29);
		dcsNgayVaoLam = new JDateChooser();
		dcsNgayVaoLam.setBackground(Color.WHITE);
		dcsNgayVaoLam.setBounds(930, 308, 280, 40);
		lblNgayVaoLam = new JLabel();
		lblNgayVaoLam.setBounds(780, 322, 140, 40);
		lblErrNgayVaoLam = new JLabel();
		lblErrNgayVaoLam.setBounds(930, 349, 300, 28);
		srcCongNhan = new JScrollPane();
		srcCongNhan.setBounds(16, 508, 1240, 184);
		tblCongNhan = new JTable();

		setPreferredSize(new Dimension(1290, 750));
		setLayout(null);
		
		lblTieuDe = new JLabel("QUẢN LÝ CÔNG NHÂN");
		lblTieuDe.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblTieuDe.setBounds(473, 11, 272, 42);
		add(lblTieuDe);

		LineBorder blackLineBorder = new LineBorder(Color.BLACK, 2);
		TitledBorder titledBorder = BorderFactory.createTitledBorder(blackLineBorder, "Thông tin công nhân");
		panelCongNhan.setBorder(titledBorder);
		panelCongNhan.setBackground(new Color(255, 255, 255));
		panelCongNhan.setPreferredSize(new Dimension(1250, 500));
		panelCongNhan.setLayout(null);

		lblHinhAnh.setBackground(new Color(153, 0, 0));
		lblHinhAnh.setForeground(new Color(255, 0, 51));
		panelCongNhan.add(lblHinhAnh);

		lblAnhDaiDien.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnhDaiDien.setText("Ảnh đại diện");
		lblAnhDaiDien.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				lblAnhDaiDienMouseClicked(evt);
			}
		});

		javax.swing.GroupLayout btnAnhDaiDienLayout = new javax.swing.GroupLayout(btnAnhCongNhan);
		btnAnhCongNhan.setLayout(btnAnhDaiDienLayout);
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
		panelCongNhan.add(btnAnhCongNhan);

		lblErrHoVaTen.setFont(new Font("Segoe UI", 0, 13));
		lblErrHoVaTen.setForeground(new Color(204, 0, 0));
		lblErrHoVaTen.setText("đây là dòng thông báo lỗi");
		panelCongNhan.add(lblErrHoVaTen);

		txtHoVaTen.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtHoVaTen.setBorder(null);
		panelCongNhan.add(txtHoVaTen);

		lblHoVaTen.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblHoVaTen.setText("Họ và tên:");
		panelCongNhan.add(lblHoVaTen);

		txtEmail.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtEmail.setBorder(null);
		panelCongNhan.add(txtEmail);

		lblErrEmail.setFont(new Font("Segoe UI", 0, 13));
		lblErrEmail.setForeground(new Color(204, 0, 0));
		lblErrEmail.setText("đây là dòng thông báo lỗi");
		panelCongNhan.add(lblErrEmail);

		lblEmail.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblEmail.setText("Email:");
		panelCongNhan.add(lblEmail);

		txtSoCCCD.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtSoCCCD.setBorder(null);
		panelCongNhan.add(txtSoCCCD);

		lblErrSoCCCD.setFont(new java.awt.Font("Segoe UI", 0, 13));
		lblErrSoCCCD.setForeground(new java.awt.Color(204, 0, 0));
		lblErrSoCCCD.setText("đây là dòng thông báo lỗi");
		panelCongNhan.add(lblErrSoCCCD);

		lblSoCCCD.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblSoCCCD.setText("Số CCCD:");
		panelCongNhan.add(lblSoCCCD);

		txtMaCongNhan.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtMaCongNhan.setBorder(null);
		panelCongNhan.add(txtMaCongNhan);

		lblMaCongNhan.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblMaCongNhan.setText("Mã công nhân:");
		panelCongNhan.add(lblMaCongNhan);

		txtSoDienThoai.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtSoDienThoai.setBorder(null);
		panelCongNhan.add(txtSoDienThoai);

		lblErrNgaySinh.setFont(new java.awt.Font("Segoe UI", 0, 13));
		lblErrNgaySinh.setForeground(new java.awt.Color(204, 0, 0));
		lblErrNgaySinh.setText("đây là dòng thông báo lỗi");
		panelCongNhan.add(lblErrNgaySinh);

		lblErrDiaChi.setFont(new java.awt.Font("Segoe UI", 0, 13));
		lblErrDiaChi.setForeground(new java.awt.Color(204, 0, 0));
		lblErrDiaChi.setText("đây là dòng thông báo lỗi");
		panelCongNhan.add(lblErrDiaChi);

		txtDiaChi.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtDiaChi.setBorder(null);
		panelCongNhan.add(txtDiaChi);

		lblGioiTinh.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblGioiTinh.setText("Giới tính");
		panelCongNhan.add(lblGioiTinh);

		lblDiaChi.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblDiaChi.setText("Địa chỉ:");
		panelCongNhan.add(lblDiaChi);

		rdoNam.setSelected(true);
		rdoNam.setText("Nam");
		rdoNam.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		rdoNam.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				rdoNamActionPerformed(evt);
			}
		});
		panelCongNhan.add(rdoNam);

		rdoNu.setText("Nữ");
		rdoNu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		rdoNu.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				rdoNuActionPerformed(evt);
			}
		});
		panelCongNhan.add(rdoNu);

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
		panelCongNhan.add(dcsNgaySinh);

		cboToNhom.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tổ 1"}));
		cboToNhom.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		panelCongNhan.add(cboToNhom);

		lblSoDienThoai.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblSoDienThoai.setText("Số điện thoại:");
		panelCongNhan.add(lblSoDienThoai);

		lblNgaySinh.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNgaySinh.setText("Ngày sinh:");
		panelCongNhan.add(lblNgaySinh);

		lblErrSoDienThoai.setFont(new java.awt.Font("Segoe UI", 0, 13));
		lblErrSoDienThoai.setForeground(new java.awt.Color(204, 0, 0));
		lblErrSoDienThoai.setText("đây là dòng thông báo lỗi");
		panelCongNhan.add(lblErrSoDienThoai);

		lblToNhom.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblToNhom.setText("Tổ nhóm:");
		panelCongNhan.add(lblToNhom);

		dcsNgayVaoLam.setDateFormatString("yyyy-MM-dd");
		panelCongNhan.add(dcsNgayVaoLam);

		lblNgayVaoLam.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNgayVaoLam.setText("Ngày vào làm:");
		panelCongNhan.add(lblNgayVaoLam);

		lblErrNgayVaoLam.setFont(new java.awt.Font("Segoe UI", 0, 13));
		lblErrNgayVaoLam.setForeground(new java.awt.Color(204, 0, 0));
		lblErrNgayVaoLam.setText("đây là dòng thông báo lỗi");
		panelCongNhan.add(lblErrNgayVaoLam);

		add(panelCongNhan);

		JSeparator jSeparator1 = new JSeparator();
		jSeparator1.setForeground(Color.BLACK);
		jSeparator1.setBounds(420, 70, 280, 10);
		panelCongNhan.add(jSeparator1);

		JSeparator jSeparator1_1 = new JSeparator();
		jSeparator1_1.setForeground(Color.BLACK);
		jSeparator1_1.setBounds(420, 127, 280, 2);
		panelCongNhan.add(jSeparator1_1);

		jSeparator1_2 = new JSeparator();
		jSeparator1_2.setForeground(Color.BLACK);
		jSeparator1_2.setBounds(420, 188, 280, 10);
		panelCongNhan.add(jSeparator1_2);

		jSeparator1_3 = new JSeparator();
		jSeparator1_3.setForeground(Color.BLACK);
		jSeparator1_3.setBounds(930, 70, 290, 10);
		panelCongNhan.add(jSeparator1_3);

		jSeparator1_4 = new JSeparator();
		jSeparator1_4.setForeground(Color.BLACK);
		jSeparator1_4.setBounds(930, 140, 290, 10);
		panelCongNhan.add(jSeparator1_4);

		jSeparator1_5 = new JSeparator();
		jSeparator1_5.setForeground(Color.BLACK);
		jSeparator1_5.setBounds(930, 200, 290, 10);
		panelCongNhan.add(jSeparator1_5);

		btnThemNhieu = new JButton();
		btnThemNhieu.setBounds(48, 452, 160, 40);
		add(btnThemNhieu);
		btnThemNhieu.setBackground(new Color(255, 215, 0));
		btnThemNhieu.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnThemNhieu.setIcon(new ImageIcon(NhanVien_GUI.class.getResource("/image/icon/them.png")));
		btnThemNhieu.setText("Thêm nhiều");
		btnThemNhieu.setBorder(null);
		btnThemNhieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemNhieuMouseClicked(evt);
            }
        });

		
		btnThem = new JButton();
		btnThem.setBounds(257, 452, 160, 40);
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
		btnXoa.setBounds(473, 452, 160, 40);
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
		btnCapNhat.setBounds(687, 452, 160, 40);
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
		btnLuu.setBounds(890, 452, 160, 40);
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
		btnHuy.setBounds(1086, 452, 160, 40);
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
		TitledBorder titledBorder1 = BorderFactory.createTitledBorder(blackLineBorder1, "Danh sách công nhân");
		srcCongNhan.setBorder(titledBorder1);

		srcCongNhan.setBackground(Color.WHITE);
		tblCongNhan.setModel(new DefaultTableModel(
				new Object [][] {
	                {null, null, null, null, null, null, null, null, null, null, null, null},
	                {null, null, null, null, null, null, null, null, null, null, null, null},
	                {null, null, null, null, null, null, null, null, null, null, null, null},
	                {null, null, null, null, null, null, null, null, null, null, null, null}
	            },
	            new String [] {
	                "STT", "Mã công nhân", "Họ và tên", "Số CCCD", "Giới tính", "Ngày sinh", "Số điện thoại", "Địa chỉ", "Ảnh đại diện", "Email", "Tổ/Nhóm", "Ngày vào làm"
	            }
		));
		tblCongNhan.getTableHeader().setBackground(new Color(128, 200, 255));
		tblCongNhan.setSelectionBackground(new Color(255, 215, 0));
		tblCongNhan.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				tblCongNhanMouseClicked(evt);
			}
		});
		srcCongNhan.setViewportView(tblCongNhan);
		add(srcCongNhan);
		ScrollBarCustom scrollBar = new ScrollBarCustom();
		scrollBar.setForeground(Color.RED);
		srcCongNhan.setVerticalScrollBar(scrollBar);
		
	}

	private void tblCongNhanMouseClicked(java.awt.event.MouseEvent evt) {
		lblErrHoVaTen.setText("");
		lblErrSoCCCD.setText("");
		lblErrSoDienThoai.setText("");
		lblErrEmail.setText("");
		lblErrNgaySinh.setText("");
		lblErrNgayVaoLam.setText("");
		lblErrDiaChi.setText("");
		try {
			setHidden(btnLuu, btnHuy);
			setShow(btnThem, btnXoa, btnCapNhat, btnThemNhieu);
			hienThiDuLieuLenTxt(tblCongNhan.getSelectedRow());
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
	}

	public void hienThiDuLieuLenTxt(int dong) throws ParseException {

		txtMaCongNhan.setText(tblCongNhan.getValueAt(dong, 1).toString());
		txtHoVaTen.setText(tblCongNhan.getValueAt(dong, 2).toString());
		txtSoCCCD.setText(tblCongNhan.getValueAt(dong, 3).toString());
		if (rdoNam.getText() == tblCongNhan.getValueAt(dong, 4).toString()) {
			rdoNam.setSelected(true);
		} else {
			rdoNu.setSelected(true);
		}
		dcsNgaySinh.setDate(df.parse(tblCongNhan.getValueAt(dong, 5).toString()));
		txtSoDienThoai.setText(tblCongNhan.getValueAt(dong, 6).toString());
		txtDiaChi.setText(tblCongNhan.getValueAt(dong, 7).toString());
		lblHinhAnh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/anhCongNhan/" + tblCongNhan.getValueAt(dong, 8))));
		txtEmail.setText(tblCongNhan.getValueAt(dong, 9).toString());
		cboToNhom.setSelectedItem(tblCongNhan.getValueAt(dong, 10).toString());
		dcsNgayVaoLam.setDate(df.parse(tblCongNhan.getValueAt(dong, 11).toString()));
	}

	private void lblAnhDaiDienMouseClicked(java.awt.event.MouseEvent evt) {
		JFileChooser fileChooser = new JFileChooser("d:");
		fileChooser.setCurrentDirectory(new File(".\\src\\image\\anhCongNhan"));
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

	public void taiDuLieuLenBang() throws ParseException {
		while (tblCongNhan.getRowCount() != 0) {
			model.removeRow(0);
		}
		cboToNhom.removeAllItems();
		ToNhom_Dao daoToNhom = new ToNhom_Dao();
		ArrayList<ToNhom> toNhom = daoToNhom.layDanhSachToNhom();
		if (toNhom.size() > 0) {
			for (ToNhom tn : toNhom) {
				cboToNhom.addItem(tn.getTenToNhom());
			}
			ArrayList<CongNhan> danhSachCongNhan = daoCongNhan.layDanhSachCongNhan();
			for (CongNhan cn : danhSachCongNhan) { 
				String data[] = {(model.getRowCount() + 1) + "", cn.getMaCongNhan(), cn.getHoTen(), cn.getMaCCCD(), cn.isGioiTinh() ? "Nam" : "Nữ", cn.getNgaySinh().toString(),
						cn.getSoDienThoai(), cn.getDiaChi(), cn.getAnhDaiDien(), cn.getEmail(), cn.getToNhom().getTenToNhom(),
						cn.getNgayVaoLam().toString()};
				model.addRow(data);
			}
			if (tblCongNhan.getRowCount() != 0) {
				tblCongNhan.setRowSelectionInterval(0, 0);
				hienThiDuLieuLenTxt(0);
			}
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
		//this.txtLuongThoaThuan.setEditable(kq);
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
	private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {
		setHidden(btnThem, btnXoa, btnCapNhat, btnThemNhieu);
		setShow(btnLuu, btnHuy);
		String maCongNhan = daoCongNhan.layRaMaCongNhanDeThem();
		this.txtMaCongNhan.setText(maCongNhan);

		this.txtHoVaTen.setText("");
		this.txtSoCCCD.setText("");
		this.txtEmail.setText("");
		this.txtSoDienThoai.setText("");
		this.txtDiaChi.setText("");
		this.dcsNgaySinh.setDate(new Date());
		this.rdoNam.setSelected(true);
		this.cboToNhom.setSelectedIndex(0);
		this.dcsNgayVaoLam.setDate(new Date());
		lblHinhAnh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/anhCongNhan/congNhan1.png")));
		setEnableForInput(true);
		isThem = true;
		setEditTextDateChooser();
	}

	private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {
		int rowSelected = tblCongNhan.getSelectedRow();
		if (rowSelected != -1) {
			if (JOptionPane.showConfirmDialog(null, stBanXacNhanXoa, stThongbao, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				if (daoCongNhan.xoaCongNhanTheoMa(tblCongNhan.getValueAt(tblCongNhan.getSelectedRow(), 1).toString())) {
					JOptionPane.showMessageDialog(null, stXoaThanhCong, stThongbao, JOptionPane.INFORMATION_MESSAGE);
					try {
						taiDuLieuLenBang();
					} catch (ParseException ex) {
						JOptionPane.showMessageDialog(null, "Lỗi!!!", stThongbao, JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		}
	}

	private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {
		setHidden(btnThem, btnXoa, btnCapNhat);
		setShow(btnLuu, btnHuy);
		setEnableForInput(true);
		setEditTextDateChooser();
		isThem = false;
	}
	
	public boolean validateForm() {
        boolean flag = true;
        String hoTen = txtHoVaTen.getText().trim();
        String soCCCD = txtSoCCCD.getText().trim();
        String email = txtEmail.getText().trim();
        String soDienThoai = txtSoDienThoai.getText().trim();
        String diaChi = txtDiaChi.getText().trim();
        Date ngaySinh = dcsNgaySinh.getDate();
        Date ngayVaoLam = dcsNgayVaoLam.getDate();

        if (hoTen.equals("")) {
            lblErrHoVaTen.setText(stErrKhongDeTrong);
            flag = false;
        } else if (!hoTen.matches("^([A-ZĐÂÁƯ]{1}[a-zvxyỳọáầảấờễàạằệếýộậốũứĩõúữịỗìềểẩớặòùồợãụủíỹắẫựỉỏừỷởóéửỵẳẹèẽổẵẻỡơôưăêâđ]+)"
                + "((\\s{1}[A-ZĐÂÁƯ][{1}a-vxyỳọáầảấờễàạằệếýộậốũứĩõúữịỗìềểẩớặòùồợãụủíỹắẫựỉỏừỷởóéửỵẳẹèẽổẵẻỡơôưăêâđ]+){1,})$")) {
        	lblErrHoVaTen.setText(stErrHoTen);
            flag = false;
        } else {
        	lblErrHoVaTen.setText("");
        }
        if (soCCCD.equals("")) {
            lblErrSoCCCD.setText(stErrKhongDeTrong);
            flag = false;
        } else if (!soCCCD.matches("^[0-9]{12}$")) {
        	lblErrSoCCCD.setText(stErrSoCCCD);
            flag = false;
        } else {
        	lblErrSoCCCD.setText("");
        }
        if (email.equals("")) {
            lblErrEmail.setText(stErrKhongDeTrong);
            flag = false;
        } else if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            lblErrEmail.setText(stErrEmail);
            flag = false;
        } else {
            lblErrEmail.setText("");
        }
        if (soDienThoai.equals("")) {
            lblErrSoDienThoai.setText(stErrKhongDeTrong);
            flag = false;
        } else if (!soDienThoai.matches("^\\+84[1-9][0-9]{8}$")) {
            lblErrSoDienThoai.setText(stErrSdt);
            flag = false;
        } else {
            lblErrSoDienThoai.setText("");
        }
        if (diaChi.equals("")) {
            lblErrDiaChi.setText(stErrKhongDeTrong);
            flag = false;
        } else {
            lblErrDiaChi.setText("");
        }
        if (!ngaySinh.before(new Date())) {
            lblErrNgaySinh.setText(stErrNgaySinh);
            flag = false;
        } else if (calculateAgeWithJava7(ngaySinh, new Date()) < 18) {
            lblErrNgaySinh.setText(stErrCongNhanKhongDuTuoi);
            flag = false;
        } else {
            lblErrNgaySinh.setText("");
        }
        if (ngayVaoLam.after(new Date())) {
            lblErrNgayVaoLam.setText(stErrNgayVaoLam);
            flag = false;
        } else {
            lblErrNgayVaoLam.setText("");
        }

        return flag;
    }

    public int calculateAgeWithJava7(Date birthDate, Date currentDate) {
        // validate inputs ...                                                                               
        DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        int d1 = Integer.parseInt(formatter.format(birthDate));
        int d2 = Integer.parseInt(formatter.format(currentDate));
        int age = (d2 - d1) / 10000;
        return age;
    }
	
	private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {
		try {
			ToNhom_Dao dao = new ToNhom_Dao();
			ToNhom tn = dao.layMotToNhomTheoTen(cboToNhom.getSelectedItem().toString());
			System.out.println(tn.getMaToNhom());
			
			if (dcsNgaySinh.getDate() != null && dcsNgayVaoLam.getDate() != null) {
				congNhanEntity = new CongNhan(txtMaCongNhan.getText(), txtHoVaTen.getText(), dcsNgaySinh.getDate(),
						txtSoCCCD.getText(), txtSoDienThoai.getText(), txtEmail.getText(),
						dcsNgayVaoLam.getDate(), rdoNam.isSelected(), lblHinhAnh.getIcon().toString().split("anhCongNhan/")[1], txtDiaChi.getText(), tn);
				congNhanEntity.toString();
				if (isThem) {
					boolean coHopLe = validateForm();
	                if (!coHopLe) {
	                    return;
	                }
					if (this.daoCongNhan.themMotCongNhan(congNhanEntity)) {
						taiDuLieuLenBang();
	                    JOptionPane.showMessageDialog(null, stThemThanhCong, stThongbao, JOptionPane.INFORMATION_MESSAGE);
						setHidden(btnLuu, btnHuy);
						setShow(btnThem, btnThemNhieu, btnXoa, btnCapNhat);
						setEnableForInput(false);
						isThem = false;
					} else {
	                    JOptionPane.showMessageDialog(null, stThemThatBai, stThongbao, JOptionPane.INFORMATION_MESSAGE);
						setHidden(btnLuu, btnHuy);
						setShow(btnThem, btnThemNhieu, btnXoa, btnCapNhat);
						setEnableForInput(false);
					}
				} 
				else {
					boolean coHopLe = validateForm();
	                if (!coHopLe) {
	                    return;
	                }
					if (this.daoCongNhan.capNhatMotCongNhan(congNhanEntity)) {
						taiDuLieuLenBang();
	                    JOptionPane.showMessageDialog(null, stCapNhatThanhCong, stThongbao, JOptionPane.INFORMATION_MESSAGE);
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
			} else {
				JOptionPane.showMessageDialog(null, "ERROR: NgayVaoLam cannot be null");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR");
		}
	}

	private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {
		setHidden(btnLuu, btnHuy);
		setShow(btnXoa, btnCapNhat, btnThemNhieu, btnThem);
		tblCongNhan.setRowSelectionInterval(0, 0);
		setEnableForInput(false);
		try {
			hienThiDuLieuLenTxt(0);
		} catch (ParseException ex) {
			Logger.getLogger(CongNhan_GUI.class.getName()).log(Level.SEVERE, null, ex);
		}
		isThem = false;
		setInit();
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
					    try {
					        String hoTen = "", maCCCD = "", soDienThoai = "", email = "", anhDaiDien = "", diaChi = "";
					        String maToNhom = "";
					        Date ngaySinh = new Date(), ngayVaoLam = new Date();
					        Boolean gioiTinh = false;
					        while (cellItera.hasNext()) {
					            Cell cell = cellItera.next();
					            if (row.getRowNum() == 0) {
					                continue;
					            } else {
					                if (cell.getColumnIndex() == 0) {
					                    hoTen = cell.getStringCellValue();
					                } else if (cell.getColumnIndex() == 1) {
					                    String chuoiNgaySinh = cell.getStringCellValue();
					                    try {
					                        ngaySinh = new SimpleDateFormat("yyyy-MM-dd").parse(chuoiNgaySinh);
					                        System.out.println("Ngay Sinh" + ngaySinh);
					                    } catch (ParseException ex) {
					                        System.out.println(ex.getMessage());
					                    }
					                } else if (cell.getColumnIndex() == 2) {
					                    maCCCD = cell.getStringCellValue();
					                } else if (cell.getColumnIndex() == 3) {
					                    soDienThoai = cell.getStringCellValue();
					                } else if (cell.getColumnIndex() == 4) {
					                    email = cell.getStringCellValue();
					                } else if (cell.getColumnIndex() == 5) {
					                    String gioiTinhStr = cell.getStringCellValue();
					                    if (gioiTinhStr.equalsIgnoreCase("Nữ") || gioiTinhStr.equalsIgnoreCase("Nu")) {
					                        gioiTinh = false;
					                    } else {
					                        gioiTinh = true;
					                    }
					                } else if (cell.getColumnIndex() == 6) {
					                    anhDaiDien = cell.getStringCellValue();
					                } else if (cell.getColumnIndex() == 7) {
					                    diaChi = cell.getStringCellValue();
					                } else if (cell.getColumnIndex() == 8) {
					                    String chuoiNgayVaoLam = cell.getStringCellValue();
					                    try {
					                        ngayVaoLam = new SimpleDateFormat("yyyy-MM-dd").parse(chuoiNgayVaoLam);
					                    } catch (ParseException ex) {
					                        System.out.println(ex.getMessage());
					                    }

					                } else if (cell.getColumnIndex() == 9) {
					                    maToNhom = cell.getStringCellValue();
					                }
					            }

					        }

					        String maCongNhan = daoCongNhan.layRaMaCongNhanDeThem();
					        ToNhom toNhom = toNhom_Dao.layMotToNhomTheoMa(maToNhom);
					        boolean coThemDuoc
					                = daoCongNhan.themMotCongNhan(new CongNhan(maCongNhan, hoTen, ngaySinh, maCCCD,
					                        soDienThoai, email, ngayVaoLam, gioiTinh, anhDaiDien, diaChi, toNhom));
					        if (coThemDuoc) {
					            count++;
					        }
					    } catch (Exception e) {
					        System.out.println(e.getMessage());
					    }
					}
				}
                in.close();
                JOptionPane.showMessageDialog(null, stThemThanhCong + " " + count + " " + stTren + (--total) + stCongNhan);
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, stKhongTimThayFile, stThongbao, JOptionPane.ERROR_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, stKhongDocDuocFile, stThongbao, JOptionPane.ERROR_MESSAGE);
            }
            if (count != 0) {
                try {
                    taiDuLieuLenBang();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
	
	private void rdoNamActionPerformed(java.awt.event.ActionEvent evt) {
	}
	
	private void rdoNuActionPerformed(java.awt.event.ActionEvent evt) {
	}
	
	private void dcsNgaySinhPropertyChange(java.beans.PropertyChangeEvent evt) {
	}
	
	private void dcsNgaySinhMouseExited(java.awt.event.MouseEvent evt) {
	}
}
