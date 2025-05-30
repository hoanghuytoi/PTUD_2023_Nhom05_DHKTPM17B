package UI;

import java.awt.Color;
import java.awt.Font;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import Custom_UI.ScrollBarCustom;
import Dao.CongDoan_Dao;
import Dao.CongNhan_Dao;
import Dao.NhanVien_Dao;
import Dao.PhanCongCongNhan_Dao;
import Dao.SanPham_Dao;
import Dao.ToNhom_Dao;
import Entity.CongDoan;
import Entity.CongNhan;
import Entity.NhanVien;
import Entity.PhanCongCongNhan;
import Entity.SanPham;
import Entity.ToNhom;

import javax.swing.JComboBox;
import javax.swing.JButton;

public class PhanCongCongNhan_GUI extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JScrollPane scrSanPham;
	private JTable tblSanPham;
	private JScrollPane scrDanhSachPhanCong;
	private JTable tblPhanCong;

	private JTextField txtMaPhanCong;
	private JTextField txtTenCD;
	private JTextField txtSoLuongCanLam;
	private JLabel lblTieuDe;
	private JPanel panelCDCongNhan;
	private JLabel lblMaPhanCong;
	private JLabel lblTenCD;
	private JLabel lblSoLuongCanLam;
	private JLabel lblMaCD;
	private JLabel lblToNhom;
	private JComboBox<String> cmbToNhom;
	private JLabel lblNgayPhanCong;
	private JDateChooser dcsNgayPhanCong;
	private JButton btnPhanCong;
	private JButton btnXoa;
	private JButton btnCapNhat;
	private JButton btnLuu;
	private JButton btnHuy;

	private DefaultTableModel modelSanPham;
	private DefaultTableModel modelPhanCong;
	private SanPham_Dao daoSanPham;
	private CongDoan_Dao daoCongDoan;
	private CongNhan_Dao daoCongNhan;
	private PhanCongCongNhan_Dao daoPhanCong;
	private ToNhom_Dao daoToNhom;
	private NhanVien_Dao daoNhanVien;
	private boolean checkPhanCong = false;
	private JComboBox<String> cmbMaCongDoan;

	private String stThongbao;
    private String stBanXacNhanXoa;
    private String stXoaThanhCong;
    private String stXoaThatBai;
    private String stThemThanhCong;
    private String stThemThatBai;
    private String stCapNhatThanhCong;
    private String stCapNhatThatBai;
    private String stErr1;
    private String stErr2;
    private String stErr3;
    private String stErrPhanCong;
    private JTextField txtSoLuongCan;
	private JLabel lblSoLuongCan;

	public PhanCongCongNhan_GUI(String fileName) throws IOException {
		initComponents();
        caiDatNgonNgu(fileName);
		excute();
        taiDuLieuLenBangSanPham();
        txtSoLuongCan.setEditable(false);
        txtSoLuongCanLam.setEditable(false);
        dcsNgayPhanCong.setEnabled(false);
        btnPhanCong.setEnabled(false);
	}

	public void caiDatNgonNgu(String fileName) throws FileNotFoundException, IOException {
        FileInputStream fis = new FileInputStream(fileName);
        Properties prop = new Properties();
        prop.load(fis);
        scrSanPham.setBorder(new TitledBorder(prop.getProperty("pc_tieudeSanPham")));
        scrDanhSachPhanCong.setBorder(new TitledBorder(prop.getProperty("pc_tieuDeDanhSachPhanCong")));
        panelCDCongNhan.setBorder(new TitledBorder(prop.getProperty("pc_tieuDeThongTinPhanCong")));

        ChangeName(tblSanPham, 0, prop.getProperty("pc_stt"));
        ChangeName(tblSanPham, 1, prop.getProperty("pc_maSanPham"));
        ChangeName(tblSanPham, 2, prop.getProperty("pc_tenSanPham"));
        ChangeName(tblSanPham, 3, prop.getProperty("pc_soLuong"));
        ChangeName(tblSanPham, 4, prop.getProperty("pc_donGia"));

        
        ChangeName(tblSanPham, 0, prop.getProperty("pc_stt"));
        ChangeName(tblPhanCong, 1, prop.getProperty("pc_maPhanCong"));
        ChangeName(tblPhanCong, 2, prop.getProperty("pc_maSanPham"));
        ChangeName(tblPhanCong, 3, prop.getProperty("pc_tenSanPham"));
        ChangeName(tblPhanCong, 4, prop.getProperty("pc_MaCongDoan"));
        ChangeName(tblPhanCong, 5, prop.getProperty("pc_TenCongDoan"));
        ChangeName(tblPhanCong, 6, prop.getProperty("pc_toNhom"));
        ChangeName(tblPhanCong, 7, prop.getProperty("pc_tenCongNhan"));
        ChangeName(tblPhanCong, 8, prop.getProperty("pc_soLuongCanLam"));
        ChangeName(tblPhanCong, 9, prop.getProperty("pc_ngayPhanCong"));

        lblTieuDe.setText(prop.getProperty("pc_TieuDe"));
        lblNgayPhanCong.setText(prop.getProperty("pc_ngayPhanCong"));
        lblMaPhanCong.setText(prop.getProperty("pc_maPhanCong"));
        lblMaCD.setText(prop.getProperty("pc_MaCongDoan"));
        lblTenCD.setText(prop.getProperty("pc_TenCongDoan"));
        lblSoLuongCan.setText(prop.getProperty("pc_soLuongCan"));
        lblSoLuongCanLam.setText(prop.getProperty("pc_soLuongCanLam"));
        lblToNhom.setText(prop.getProperty("pc_toNhom"));

        btnPhanCong.setText(prop.getProperty("pc_btnPhanCong"));
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
        prop.getProperty("khongDeTrong");
        prop.getProperty("pc_errKytuso");
        stErrPhanCong = prop.getProperty("pc_errNgayPhanCong");
        stErr1 = prop.getProperty("pc_err1");
        stErr2 = prop.getProperty("pc_err2");
        stErr3 = prop.getProperty("pc_err3");
    }

    public void ChangeName(JTable table, int col_index, String col_name) {
        table.getColumnModel().getColumn(col_index).setHeaderValue(col_name);
    }
    
	public void excute() {
        modelPhanCong = (DefaultTableModel) tblPhanCong.getModel();
        modelSanPham = (DefaultTableModel) tblSanPham.getModel();
        
        setShow(btnPhanCong);
        setHidden(btnLuu, btnHuy, btnXoa, btnCapNhat);
        while (modelPhanCong.getRowCount() != 0) {
            modelPhanCong.removeRow(0);
        }
        ToNhom_Dao daoToNhom = new ToNhom_Dao();
        ArrayList<ToNhom> toNhomList = daoToNhom.layDanhSachToNhom();
		CongNhan_Dao congNhan_dao = new CongNhan_Dao();
        cmbToNhom.removeAllItems();
        for (ToNhom toNhom : toNhomList) {
	        int soLuongCongNhan = congNhan_dao.laySoLuongCongNhanTheoToNhom(toNhom.getMaToNhom());
            if (soLuongCongNhan> 0) {
                cmbToNhom.addItem(toNhom.getTenToNhom());
            }
        }
        dcsNgayPhanCong.setDate(new Date());
        txtMaPhanCong.setText("");
        cmbMaCongDoan.removeAllItems();
        txtTenCD.setText("");
        txtSoLuongCanLam.setText("");
    }
	
	private void initComponents() {
		setBackground(Color.WHITE);

		setSize(1290, 750);
		setLayout(null);

		lblTieuDe = new JLabel("PHÂN CÔNG CÔNG NHÂN");
		lblTieuDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblTieuDe.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblTieuDe.setBounds(344, 22, 490, 50);
		add(lblTieuDe);

		tblSanPham = new JTable();
		tblSanPham.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		tblSanPham.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 15));
		tblSanPham.setRowHeight(25);

		tblSanPham.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null, null},
					{null, null, null, null, null},
					{null, null, null, null, null},
					{null, null, null, null, null},
					{null, null, null, null, null},
					{null, null, null, null, null},
					{null, null, null, null, null},
					{null, null, null, null, null},
					{null, null, null, null, null},
					{null, null, null, null, null}
				},
				new String[] {
						"STT", "M\u00E3 s\u1EA3n ph\u1EA9m", "T\u00EAn s\u1EA3n ph\u1EA9m", "S\u1ED1 l\u01B0\u1EE3ng", "\u0110\u01A1n gi\u00E1"
				}
				));

		tblSanPham.getTableHeader().setBackground(new Color(128, 200, 255));
		tblSanPham.setSelectionBackground(new Color(255, 215, 0));
		tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				tblSanPhamMouseClicked(evt);
			}
		});
		scrSanPham = new JScrollPane(tblSanPham);
		LineBorder blackLineBorder = new LineBorder(Color.BLACK, 2);
		TitledBorder titledBorder = BorderFactory.createTitledBorder(blackLineBorder, "Danh sách sản phẩm");
		scrSanPham.setBorder(titledBorder);
		scrSanPham.setBackground(Color.WHITE);
		scrSanPham.setBounds(42, 140, 708, 227);
		scrSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                scrDanhSachSanPhamMouseClicked(evt);
            }
        });
		add(scrSanPham);

		ScrollBarCustom scrollBar = new ScrollBarCustom();
		scrollBar.setForeground(Color.RED);
		scrSanPham.setVerticalScrollBar(scrollBar);

		panelCDCongNhan = new JPanel();
		LineBorder blackLineBorder1 = new LineBorder(Color.BLACK, 2);
		TitledBorder titledBorder1 = BorderFactory.createTitledBorder(blackLineBorder1, "Thông tin phân công công nhân");
		panelCDCongNhan.setBorder(titledBorder1);
		panelCDCongNhan.setBackground(Color.WHITE);
		panelCDCongNhan.setBounds(854, 39, 387, 400);
		add(panelCDCongNhan);
		panelCDCongNhan.setLayout(null);

		lblMaPhanCong = new JLabel();
		lblMaPhanCong.setBounds(10, 23, 114, 30);
		lblMaPhanCong.setText("Mã phân công:");
		lblMaPhanCong.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		panelCDCongNhan.add(lblMaPhanCong);

		txtMaPhanCong = new JTextField();
		txtMaPhanCong.setBackground(new Color(255, 255, 255));
		txtMaPhanCong.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtMaPhanCong.setBorder(null);
		txtMaPhanCong.setBounds(134, 17, 224, 30);
		panelCDCongNhan.add(txtMaPhanCong);

		JSeparator jSeparator = new JSeparator();
		jSeparator.setForeground(Color.BLACK);
		jSeparator.setBounds(134, 50, 224, 10);
		panelCDCongNhan.add(jSeparator);

		lblTenCD = new JLabel();
		lblTenCD.setText("Tên công đoạn:");
		lblTenCD.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblTenCD.setBounds(10, 147, 114, 36);
		panelCDCongNhan.add(lblTenCD);

		txtTenCD = new JTextField();
		txtTenCD.setBackground(new Color(255, 255, 255));
		txtTenCD.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtTenCD.setBorder(null);
		txtTenCD.setBounds(134, 136, 224, 34);
		panelCDCongNhan.add(txtTenCD);

		lblSoLuongCanLam = new JLabel();
		lblSoLuongCanLam.setText("Số lượng nv làm:");
		lblSoLuongCanLam.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblSoLuongCanLam.setBounds(10, 271, 114, 30);
		panelCDCongNhan.add(lblSoLuongCanLam);

		JSeparator jSeparator2 = new JSeparator();
		jSeparator2.setForeground(Color.BLACK);
		jSeparator2.setBounds(134, 176, 224, 10);
		panelCDCongNhan.add(jSeparator2);

		txtSoLuongCanLam = new JTextField();
		txtSoLuongCanLam.setBackground(new Color(255, 255, 255));
		txtSoLuongCanLam.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtSoLuongCanLam.setBorder(null);
		txtSoLuongCanLam.setBounds(134, 254, 224, 30);
		panelCDCongNhan.add(txtSoLuongCanLam);

		JSeparator jSeparator2_1 = new JSeparator();
		jSeparator2_1.setForeground(Color.BLACK);
		jSeparator2_1.setBounds(134, 291, 224, 10);
		panelCDCongNhan.add(jSeparator2_1);

		lblMaCD = new JLabel();
		lblMaCD.setText("Mã công đoạn:");
		lblMaCD.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblMaCD.setBounds(10, 92, 114, 33);
		panelCDCongNhan.add(lblMaCD);

		lblToNhom = new JLabel();
		lblToNhom.setText("Tổ nhóm:");
		lblToNhom.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblToNhom.setBounds(10, 337, 114, 36);
		panelCDCongNhan.add(lblToNhom);

		cmbToNhom = new JComboBox<String>();
		cmbToNhom.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Tổ 1", "Tổ 2", "Tổ 3", "Tổ 4", "Tổ 5", "Tổ 6", "Tổ 7", "Tổ 8", "Tổ 9", "Tổ 10"}));
        cmbToNhom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbToNhomActionPerformed(evt);
            }
        });
		cmbToNhom.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		cmbToNhom.setBackground(Color.WHITE);
		cmbToNhom.setBounds(134, 326, 224, 36);
		panelCDCongNhan.add(cmbToNhom);
		
		cmbMaCongDoan = new JComboBox<String>();
		cmbMaCongDoan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"PPCD100001"}));
        cmbMaCongDoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbMaCongDoanActionPerformed(evt);
            }
        });
		cmbMaCongDoan.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		cmbMaCongDoan.setEnabled(false);
		cmbMaCongDoan.setBackground(Color.WHITE);
		cmbMaCongDoan.setBounds(134, 89, 224, 36);
		panelCDCongNhan.add(cmbMaCongDoan);
		
		lblSoLuongCan = new JLabel();
		lblSoLuongCan.setText("Số lượng cần:");
		lblSoLuongCan.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblSoLuongCan.setBounds(10, 216, 114, 30);
		panelCDCongNhan.add(lblSoLuongCan);
		
		txtSoLuongCan = new JTextField();
		txtSoLuongCan.setText("");
		txtSoLuongCan.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtSoLuongCan.setEditable(false);
		txtSoLuongCan.setBorder(null);
		txtSoLuongCan.setBackground(Color.WHITE);
		txtSoLuongCan.setBounds(134, 205, 224, 30);
		panelCDCongNhan.add(txtSoLuongCan);
		
		JSeparator jSeparator2_1_1 = new JSeparator();
		jSeparator2_1_1.setForeground(Color.BLACK);
		jSeparator2_1_1.setBounds(134, 236, 224, 10);
		panelCDCongNhan.add(jSeparator2_1_1);

		tblPhanCong = new JTable();
		tblPhanCong.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		tblPhanCong.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 15));
		tblPhanCong.setRowHeight(25);

		tblPhanCong.setModel(new DefaultTableModel(
				new Object[][]{
					{null, null, null, null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null, null, null, null}
				},
				new String[]{
						"STT","Mã phân công", "Mã sản phẩm", "Tên sản phẩm", "Mã công đoạn", "Tên  công đoạn", "Tổ Nhóm", "Tên công nhân", "Số lượng cần làm", "Ngày phân công"
				}
				));

		tblPhanCong.getTableHeader().setBackground(new Color(128, 200, 255));
		tblPhanCong.setSelectionBackground(new Color(255, 215, 0));
		tblPhanCong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPhanCongMouseClicked(evt);
            }
        });

		scrDanhSachPhanCong = new JScrollPane(tblPhanCong);
		LineBorder blackLineBorder2 = new LineBorder(Color.BLACK, 2);
		TitledBorder titledBorder2 = BorderFactory.createTitledBorder(blackLineBorder2, "Danh sách phân công công nhân");
		scrDanhSachPhanCong.setBorder(titledBorder2);
		scrDanhSachPhanCong.setBackground(Color.WHITE);
		scrDanhSachPhanCong.setBounds(42, 525, 1195, 202);
		add(scrDanhSachPhanCong);

		ScrollBarCustom scrollBar1 = new ScrollBarCustom();
		scrollBar1.setForeground(Color.RED);
		scrDanhSachPhanCong.setVerticalScrollBar(scrollBar1);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(CongDoanSanPham_GUI.class.getResource("/image/icon/muitenNgang.png")));
		lblNewLabel.setBounds(783, 243, 50, 41);
		add(lblNewLabel);

		lblNgayPhanCong = new JLabel();
		lblNgayPhanCong.setText("Ngày chấm công:");
		lblNgayPhanCong.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNgayPhanCong.setBounds(52, 389, 115, 29);
		add(lblNgayPhanCong);

		dcsNgayPhanCong = new JDateChooser();
		dcsNgayPhanCong.setDateFormatString("yyyy-MM-dd");
		dcsNgayPhanCong.setBounds(198, 377, 177, 41);
		add(dcsNgayPhanCong);

		btnPhanCong = new JButton();
		btnPhanCong.setIcon(new ImageIcon(PhanCongCongNhan_GUI.class.getResource("/image/icon/phancong.png")));
		btnPhanCong.setText("Phân công");
		btnPhanCong.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnPhanCong.setBorder(null);
		btnPhanCong.setBackground(new Color(255, 215, 0));
		btnPhanCong.setBounds(117, 456, 170, 40);
		btnPhanCong.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnPhanCongActionPerformed(evt);
			}
		});
		add(btnPhanCong);

		btnXoa = new JButton();
		btnXoa.setIcon(new ImageIcon(PhanCongCongNhan_GUI.class.getResource("/image/icon/xoa.png")));
		btnXoa.setText("Xóa");
		btnXoa.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnXoa.setBorder(null);
		btnXoa.setBackground(new Color(255, 215, 0));
		btnXoa.setBounds(340, 456, 170, 40);
		btnXoa.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnXoaActionPerformed(evt);
			}
		});
		add(btnXoa);

		btnCapNhat = new JButton();
		btnCapNhat.setIcon(new ImageIcon(PhanCongCongNhan_GUI.class.getResource("/image/icon/capNhat.png")));
		btnCapNhat.setText("Cập nhật");
		btnCapNhat.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnCapNhat.setBorder(null);
		btnCapNhat.setBackground(new Color(255, 215, 0));
		btnCapNhat.setBounds(561, 456, 170, 40);
		btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnCapNhatActionPerformed(evt);
			}
		});
		add(btnCapNhat);

		btnLuu = new JButton();
		btnLuu.setIcon(new ImageIcon(PhanCongCongNhan_GUI.class.getResource("/image/icon/luu.png")));
		btnLuu.setText("Lưu");
		btnLuu.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnLuu.setBorder(null);
		btnLuu.setBackground(new Color(255, 215, 0));
		btnLuu.setBounds(777, 456, 170, 40);
		btnLuu.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnLuuActionPerformed(evt);
			}
		});
		add(btnLuu);

		btnHuy = new JButton();
		btnHuy.setIcon(new ImageIcon(PhanCongCongNhan_GUI.class.getResource("/image/icon/huy.png")));
		btnHuy.setText("Hủy");
		btnHuy.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnHuy.setBorder(null);
		btnHuy.setBackground(new Color(255, 215, 0));
		btnHuy.setBounds(986, 456, 170, 40);
		btnHuy.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnHuyActionPerformed(evt);
			}
		});
		add(btnHuy);

	}

	public void setHidden(JButton... btn) {
        for (JButton jButton : btn) {
            jButton.setEnabled(false);
        }
    }

    public void setShow(JButton... btn) {
        for (JButton jButton : btn) {
            jButton.setEnabled(true);
        }
    }
    
    public void taiDuLieuLenBangSanPham() {
        while (modelSanPham.getRowCount() != 0) {
            modelSanPham.removeRow(0);
        }
        daoSanPham = new SanPham_Dao();
        ArrayList<SanPham> listSanPham = daoSanPham.layDanhSachSanPham();
        daoCongDoan = new CongDoan_Dao();
        if (listSanPham != null) {
            listSanPham.forEach(e -> {
                ArrayList<CongDoan> cd = daoCongDoan.layDanhSachCongDoanTheoMaSP(e.getMaSanPham());
                if (cd.size() > 0) {
                    modelSanPham.addRow(new Object[]{modelSanPham.getRowCount() + 1, e.getMaSanPham(), e.getTenSanPham(), e.getSoLuongSanPham(),e.getDonGia()});
                }
            });
        }
        setTblClick(false);
    }
    
    public void setTblClick(boolean kq) {
    	dcsNgayPhanCong.setEnabled(false);
        cmbMaCongDoan.setEnabled(kq);
        cmbToNhom.setEnabled(kq);
        txtSoLuongCanLam.setEditable(kq);
    }
    
    public void taiDuLieuLenBangPhanCong() {
        while (modelPhanCong.getRowCount() != 0) {
            modelPhanCong.removeRow(0);
        }
        daoCongDoan = new CongDoan_Dao();
        daoPhanCong = new PhanCongCongNhan_Dao();
        daoSanPham = new SanPham_Dao();
        ArrayList<PhanCongCongNhan> listPhanCong = daoPhanCong.layDanhSachPhanCongCongNhan();
        SanPham sp = daoSanPham.layMotSanPhamTheoMa(tblSanPham.getValueAt(tblSanPham.getSelectedRow(), 1).toString());
        ArrayList<CongDoan> listCongDoan = daoCongDoan.layDanhSachCongDoanTheoMaSP(sp.getMaSanPham());
        cmbMaCongDoan.removeAllItems();
        boolean check = false;
        for (CongDoan e : listCongDoan) {
            if (!e.getTinhTrang().contains("100")) {
                cmbMaCongDoan.addItem(e.getMaCongDoan());
                check = true;
            }
        }
        btnPhanCong.setEnabled(check);
        if (listPhanCong != null) {
            for (PhanCongCongNhan e : listPhanCong) {
                if (e.getCongDoan().getSanPham().getMaSanPham().equals(sp.getMaSanPham())) {
                    modelPhanCong.addRow(new Object[]{
                        modelPhanCong.getRowCount() + 1,
                        e.getMaPhanCong(),
                        e.getCongDoan().getSanPham().getMaSanPham(),
                        e.getCongDoan().getSanPham().getTenSanPham(),
                        e.getCongDoan().getMaCongDoan(),
                        e.getCongDoan().getTenCongDoan(),
                        e.getToNhom().getTenToNhom(),
                        e.getCongNhan().getHoTen(),
                        e.getSoLuongCanLam(),
                        e.getNgayPhanCong()
                    });
                }
            }
        }
    }

    
    private void scrDanhSachSanPhamMouseClicked(java.awt.event.MouseEvent evt) {
        setHidden(btnCapNhat, btnHuy, btnLuu, btnCapNhat);
        setShow(btnPhanCong);
        setTblClick(false);
    }

    public boolean checkSoLuongCanLam() {
        boolean flag = true;
        if (dcsNgayPhanCong.getDate().after(new Date())) {
            JOptionPane.showMessageDialog(this, stErrPhanCong, stThongbao, JOptionPane.ERROR_MESSAGE);
            flag = false;
        }
         //Loại bỏ kiểm tra số lượng cần làm không được để trống
//         if (txtSoLuongCan.getText().trim().equals("")) {
//             lblErrSoLuongCan.setText(stErrKhongDeTrong);
//             flag = false;
//         } else 
//        if (!txtSoLuongCan.getText().matches("^[1-9][0-9]*$")) {
//            lblErrSoLuongCan.setText(stErrChuSo);
//            flag = false;
//        } else {
//            lblErrSoLuongCan.setText("");
//        }
        return flag;
    }


    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {
        setShow(btnPhanCong);
        setHidden(btnCapNhat, btnXoa, btnHuy, btnLuu);
        setTblClick(false);
        taiDuLieuLenBangPhanCong();

        // Lấy giá trị soLuongCan từ CongDoan và hiển thị lên txtSoLuongCanLam
        int selectedSanPhamIndex = tblSanPham.getSelectedRow();
        if (selectedSanPhamIndex != -1 && selectedSanPhamIndex < modelSanPham.getRowCount()) {
            String maSanPham = tblSanPham.getValueAt(selectedSanPhamIndex, 1).toString();
            ArrayList<CongDoan> listCongDoan = daoCongDoan.layDanhSachCongDoanTheoMaSP(maSanPham);

            // Giả sử bạn muốn lấy giá trị từ công đoạn đầu tiên của sản phẩm
            if (!listCongDoan.isEmpty()) {
                CongDoan selectedCongDoan = listCongDoan.get(0);
                txtSoLuongCan.setText(String.valueOf(selectedCongDoan.getSoLuongCan()));
            }
        }
    }

    private void cmbMaCongDoanActionPerformed(java.awt.event.ActionEvent evt) {
        daoCongDoan = new CongDoan_Dao();
        try {
            CongDoan cd = daoCongDoan.layMotCongDoanTheoMaCongDoan(cmbMaCongDoan.getSelectedItem().toString());
            if (cd != null) {
                txtTenCD.setText(cd.getTenCongDoan());
            }
        } catch (Exception e) {
        }
    }

    private void tblPhanCongMouseClicked(java.awt.event.MouseEvent evt) {
        ToNhom_Dao daoToNhom = new ToNhom_Dao();
        ArrayList<ToNhom> toNhomList = daoToNhom.layDanhSachToNhom();
        CongNhan_Dao congNhan_dao = new CongNhan_Dao();
        cmbToNhom.removeAllItems();
        
        for (ToNhom toNhom : toNhomList) {
            int soLuongCongNhan = congNhan_dao.laySoLuongCongNhanTheoToNhom(toNhom.getMaToNhom());
            if (soLuongCongNhan > 0) {
                cmbToNhom.addItem(toNhom.getTenToNhom());
            }
        }
        
        SanPham sp = daoSanPham.layMotSanPhamTheoMa(tblPhanCong.getValueAt(tblPhanCong.getSelectedRow(), 2).toString());
        ArrayList<CongDoan> listCongDoan = daoCongDoan.layDanhSachCongDoanTheoMaSP(sp.getMaSanPham());
        cmbMaCongDoan.removeAllItems();
        
        for (CongDoan congDoan : listCongDoan) {
            cmbMaCongDoan.addItem(congDoan.getMaCongDoan());
        }
        
        txtMaPhanCong.setText(tblPhanCong.getValueAt(tblPhanCong.getSelectedRow(), 1).toString());
        cmbMaCongDoan.setSelectedItem(tblPhanCong.getValueAt(tblPhanCong.getSelectedRow(), 4).toString());
        txtTenCD.setText(tblPhanCong.getValueAt(tblPhanCong.getSelectedRow(), 5).toString());

        // Lấy giá trị soLuongCan từ CongDoan và hiển thị lên txtSoLuongCanLam
        int selectedIndex = tblPhanCong.getSelectedRow();
        if (selectedIndex != -1 && selectedIndex < listCongDoan.size()) {
            CongDoan selectedCongDoan = listCongDoan.get(selectedIndex);
            txtSoLuongCan.setText(String.valueOf(selectedCongDoan.getSoLuongCan()));
        }
        txtSoLuongCanLam.setText(tblPhanCong.getValueAt(tblPhanCong.getSelectedRow(), 8).toString());
        cmbToNhom.setSelectedItem(tblPhanCong.getValueAt(tblPhanCong.getSelectedRow(), 6).toString());
        setHidden(btnLuu, btnHuy, btnPhanCong);
        setShow(btnCapNhat, btnXoa);
        setTblClick(false);
    }


    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {
        if (JOptionPane.showConfirmDialog(null, stBanXacNhanXoa, stThongbao, JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            daoPhanCong = new PhanCongCongNhan_Dao();
            daoToNhom = new ToNhom_Dao();
            ToNhom toNhom = daoToNhom.layMotToNhomTheoTen(tblPhanCong.getValueAt(tblPhanCong.getSelectedRow(), 6).toString());
            String maCongDoan = tblPhanCong.getValueAt(tblPhanCong.getSelectedRow(), 4).toString();
            if (daoPhanCong.xoaMotPhanCongTheoMaToNhomVaMaCongDoan(toNhom.getMaToNhom(), maCongDoan)) {
                taiDuLieuLenBangPhanCong();
                if (modelPhanCong.getRowCount() != 0) {
                    tblPhanCong.setRowSelectionInterval(0, 0);
                }
                JOptionPane.showMessageDialog(null, stXoaThanhCong);
            }else {
                JOptionPane.showMessageDialog(null, stXoaThatBai);
            }
        }
        setHidden(btnPhanCong, btnXoa, btnCapNhat, btnLuu, btnPhanCong);
    }

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {
        setTblClick(true);
        setShow(btnHuy, btnLuu);
        setHidden(btnCapNhat, btnXoa, btnPhanCong);
        dcsNgayPhanCong.setEnabled(false);
        cmbMaCongDoan.setEnabled(false);
        cmbToNhom.setEnabled(false);
        checkPhanCong = false;
    }
    
    private void btnPhanCongActionPerformed(java.awt.event.ActionEvent evt) {
        setHidden(btnCapNhat, btnPhanCong, btnXoa);
        setShow(btnLuu, btnHuy);
        checkPhanCong = true;
        daoPhanCong = new PhanCongCongNhan_Dao();
        dcsNgayPhanCong.setEnabled(false);
        setTblClick(true);
        daoPhanCong = new PhanCongCongNhan_Dao();
        String maPhanCong = "";
        if (daoPhanCong.layDanhSachPhanCongCongNhan().size() < 1) {
            maPhanCong = "PPPC100001";
        } else {
            int maSo = Integer.parseInt(daoPhanCong.layDanhSachPhanCongCongNhan().get(daoPhanCong.layDanhSachPhanCongCongNhan().size() - 1).getMaPhanCong().split("C")[1]) + 1;
            maPhanCong = "PPPC" + maSo;
        }
        txtSoLuongCan.setEditable(false);
        txtSoLuongCanLam.setEditable(false);
        txtMaPhanCong.setText(maPhanCong);
        txtSoLuongCanLam.setText("");
    }
    
    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {
        daoCongNhan = new CongNhan_Dao();
        daoToNhom = new ToNhom_Dao();
        daoNhanVien = new NhanVien_Dao();
        daoToNhom = new ToNhom_Dao();
        daoSanPham = new SanPham_Dao();
        daoCongDoan = new CongDoan_Dao();
        
        NhanVien nhanVienChamCong = daoNhanVien.layMotNhanVienTheoMaNhanVien("PPNV100001");
        ToNhom toNhom = daoToNhom.layMotToNhomTheoTen(cmbToNhom.getSelectedItem().toString());

        if (checkSoLuongCanLam()) {
            if (checkPhanCong) {
                ArrayList<CongNhan> congNhanList = daoCongNhan.layDanhSachCongNhanTheoMaTo(toNhom.getMaToNhom());
                String maCongDoan = cmbMaCongDoan.getSelectedItem().toString();
                CongDoan congDoan = daoCongDoan.layMotCongDoanTheoMaCongDoan(maCongDoan);
                Date ngayPhanCong = dcsNgayPhanCong.getDate();
                boolean check = false;
                
                if (daoPhanCong.checkDuocPhanCong(maCongDoan, toNhom.getMaToNhom())) {
                    for (CongNhan congNhanTheoToNhom : congNhanList) {
                        String maPhanCong = "";
                        if (daoPhanCong.layDanhSachPhanCongCongNhan().size() < 1) {
                            maPhanCong = "PPPC100001";
                        } else {
                            int maSo = Integer.parseInt(daoPhanCong.layDanhSachPhanCongCongNhan().get(daoPhanCong.layDanhSachPhanCongCongNhan().size() - 1).getMaPhanCong().split("C")[1]) + 1;
                            maPhanCong = "PPPC" + maSo;
                        }

                        // Chia đều soLuongCan cho số lượng công nhân
                        int soLuongCongNhan = congNhanList.size();
                        int soLuongCanLam = congDoan.getSoLuongCan();
                        int soLuongCanLamChoMotCongNhan = soLuongCanLam / soLuongCongNhan;

                        PhanCongCongNhan phanCong = new PhanCongCongNhan(maPhanCong, congNhanTheoToNhom, congDoan, nhanVienChamCong, ngayPhanCong, soLuongCanLamChoMotCongNhan, toNhom);
                        
                        if (daoPhanCong.themMotPhanCongNhan(phanCong)) {
                            check = true;
                        }
                    }

                    if (check) {
                        taiDuLieuLenBangPhanCong();
                        JOptionPane.showMessageDialog(this, stThemThanhCong, stThongbao, JOptionPane.INFORMATION_MESSAGE);
                        setShow(btnPhanCong);
                        setHidden(btnLuu, btnHuy, btnXoa, btnCapNhat);
                        checkPhanCong = false;
                        dcsNgayPhanCong.setEnabled(false);
                        cmbMaCongDoan.setEnabled(false);
                        cmbToNhom.setEnabled(false);
                        txtSoLuongCanLam.setEditable(false);
                    } else {
                        JOptionPane.showMessageDialog(this, stThemThatBai, stThongbao, JOptionPane.INFORMATION_MESSAGE);
                        setShow(btnPhanCong);
                        setHidden(btnLuu, btnHuy, btnXoa, btnCapNhat);
                        checkPhanCong = false;
                        dcsNgayPhanCong.setEnabled(false);
                        cmbMaCongDoan.setEnabled(false);
                        cmbToNhom.setEnabled(false);
                        txtSoLuongCanLam.setEditable(false);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, stErr1 + " " + congDoan.getMaCongDoan() + " " + stErr2 + " " + toNhom.getTenToNhom() + " " + stErr3);
                }
            } else {
                if (daoPhanCong.suaMotPhanCongNhanTheoMaCongDoan(toNhom.getMaToNhom(), Integer.parseInt(txtSoLuongCanLam.getText()), cmbMaCongDoan.getSelectedItem().toString())) {
                    taiDuLieuLenBangPhanCong();
                    JOptionPane.showMessageDialog(this, stCapNhatThanhCong, stThongbao, JOptionPane.ERROR_MESSAGE);
                    setHidden(btnPhanCong, btnLuu, btnHuy, btnCapNhat, btnXoa);
                } else {
                    JOptionPane.showMessageDialog(this, stCapNhatThatBai, stThongbao, JOptionPane.ERROR_MESSAGE);
                    setHidden(btnPhanCong, btnLuu, btnHuy, btnCapNhat, btnXoa);
                }
            }
        }
    }

    
    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {
        setShow(btnPhanCong);
        setHidden(btnHuy, btnLuu, btnCapNhat, btnXoa, btnPhanCong);
        setTblClick(false);
        dcsNgayPhanCong.setEnabled(false);
        cmbMaCongDoan.setEnabled(false);
        cmbToNhom.setEnabled(false);
        txtSoLuongCanLam.setEditable(false);
        txtSoLuongCanLam.setText("");

    }

    private void cmbToNhomActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }
}
