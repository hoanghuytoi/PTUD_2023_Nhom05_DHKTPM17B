package UI;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JSeparator;
import javax.swing.JTable;

import Custom_UI.ScrollBarCustom;
import Dao.NhanVien_Dao;
import Dao.PhongBan_Dao;
import Entity.NhanVien;
import Entity.PhongBan;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.awt.Dimension;

public class PhongBan_GUI extends JPanel implements MouseListener, ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnCapNhat;
	private JButton btnHuy;
	private JButton btnLuu;
	private JButton btnThem;
	private JButton btnXoa;
	private JLabel lblAnhPB;
	private JSeparator jSeparator1;
	private JSeparator jSeparator3;
	private JSeparator jSeparator2;
	private JLabel lbErrTenPhongBan;
	private JLabel lblMaPhongBan;
	private JLabel lblSoLuongNhanVien;
	private JLabel lblTenPhongBan;
	private JPanel pnlPhongBan;
	private JScrollPane scrPhongBan;
	private JTable tblPhongBan;
	private JTextField txtMaPhongBan;
	private JTextField txtSoLuongNhanVien;
	private JTextField txtTenPhongBan;

	private PhongBan_Dao phongBan_DAO;
	private DefaultTableModel defaultTablePhongBan;
	private Object oFlag;
	private String stErrKhongDeTrong;
    private String stThongbao;
    private String stBanXacNhanXoa;
    private String stXoaThanhCong;
    private String stXoaThatBai;
    private String stThemThanhCong;
    private String stThemThatBai;
    private String stCapNhatThanhCong;
    private String stCapNhatThatBai;
    private String stErrTenPhongBan;
	private JComponent panelButon;
	private JLabel lblTieuDe;
	private TitledBorder titledBorder;
    

	public PhongBan_GUI(NhanVien nhanVienDangNhap, String fileName) throws IOException {
		setBackground(Color.WHITE);
		initComponents();
		caiDatNgonNgu(fileName);
		try {
			ConnectionDB.ConnectDB.getInstance().connect();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		tblPhongBan.addMouseListener(this);
		phongBan_DAO = new PhongBan_Dao();
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnCapNhat.addActionListener(this);
		btnLuu.addActionListener(this);
		btnLuu.setEnabled(false);
		btnHuy.addActionListener(this);
		btnHuy.setEnabled(false);

		execute();
	}

	public void caiDatNgonNgu(String fileName) throws FileNotFoundException, IOException {
        FileInputStream fis = new FileInputStream(fileName);
        Properties prop = new Properties();
        prop.load(fis);
        lblTieuDe.setText(prop.getProperty("PhongBan_TieuDe"));
        lblMaPhongBan.setText(prop.getProperty("PhongBan_MaPhongBan"));
        lblTenPhongBan.setText(prop.getProperty("PhongBan_TenPhongBan"));
        lblSoLuongNhanVien.setText(prop.getProperty("PhongBan_SoLuongNhanVien"));
		pnlPhongBan.setBorder(new TitledBorder(prop.getProperty("PhongBan_ThongTinPB")));
		scrPhongBan.setBorder(new TitledBorder(prop.getProperty("PhongBan_TablePB")));


        doiNgonNguTable(tblPhongBan, 0, prop.getProperty("PhongBan_STT"));
        doiNgonNguTable(tblPhongBan, 1, lblMaPhongBan.getText());
        doiNgonNguTable(tblPhongBan, 2, lblTenPhongBan.getText());
        doiNgonNguTable(tblPhongBan, 3, lblSoLuongNhanVien.getText());
        
        btnThem.setText(prop.getProperty("btnThem"));
        btnXoa.setText(prop.getProperty("btnXoa"));
        btnCapNhat.setText(prop.getProperty("btnCapNhat"));
        btnLuu.setText(prop.getProperty("btnLuu"));
        btnHuy.setText(prop.getProperty("btnHuy"));
        
        stErrTenPhongBan=prop.getProperty("lblErrTenPhongBan");
        stThongbao = prop.getProperty("thongBao");
        stBanXacNhanXoa = prop.getProperty("banXacNhanXoa");
        stXoaThanhCong = prop.getProperty("xoaThanhCong");
        stXoaThatBai = prop.getProperty("xoaThatBai");
        stThemThanhCong = prop.getProperty("themThanhCong");
        stThemThatBai = prop.getProperty("themThatBai");
        stCapNhatThanhCong = prop.getProperty("capNhatThanhCong");
        stCapNhatThatBai = prop.getProperty("capNhatThatBai");
        stErrKhongDeTrong = prop.getProperty("khongDeTrong");
    }
	
	public void doiNgonNguTable(JTable table, int col_index, String col_name) {
        table.getColumnModel().getColumn(col_index).setHeaderValue(col_name);
    }
	
	private void initComponents() {
		lblTieuDe = new JLabel("QUẢN LÝ PHÒNG BAN");
		lblTieuDe.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblTieuDe.setBounds(546, 11, 431, 55);
		add(lblTieuDe);

		pnlPhongBan = new JPanel();
		pnlPhongBan.setBounds(80, 77, 1115, 237);
		LineBorder blackLineBorder = new LineBorder(Color.BLACK, 2);
		titledBorder = BorderFactory.createTitledBorder(blackLineBorder, "Thông tin phòng ban");
		pnlPhongBan.setBorder(titledBorder);

		txtMaPhongBan = new JTextField();
		txtMaPhongBan.setBounds(690, 30, 290, 30);
		jSeparator1 = new JSeparator();
		jSeparator1.setForeground(new Color(0, 0, 0));
		jSeparator1.setBounds(690, 60, 290, 10);
		lblTenPhongBan = new JLabel();
		lblTenPhongBan.setBounds(500, 85, 190, 50);
		txtTenPhongBan = new JTextField();
		txtTenPhongBan.setBounds(690, 90, 290, 30);
		lblAnhPB = new JLabel();
		lblAnhPB.setBounds(24, 20, 422, 192);
		lbErrTenPhongBan = new JLabel();
		lbErrTenPhongBan.setBounds(690, 127, 290, 24);
		lblMaPhongBan = new JLabel();
		lblMaPhongBan.setBounds(500, 25, 190, 50);
		lblSoLuongNhanVien = new JLabel();
		lblSoLuongNhanVien.setBounds(500, 135, 190, 60);
		txtSoLuongNhanVien = new JTextField();
		txtSoLuongNhanVien.setBackground(Color.WHITE);
		txtSoLuongNhanVien.setBounds(690, 150, 290, 30);
		jSeparator2 = new JSeparator();
		jSeparator2.setForeground(Color.BLACK);
		jSeparator2.setBounds(690, 125, 290, 10);
		pnlPhongBan.add(jSeparator2);
		jSeparator3 = new JSeparator();
		jSeparator3.setForeground(new Color(0, 0, 0));
		jSeparator3.setBounds(690, 180, 290, 10);
		scrPhongBan = new JScrollPane();
		scrPhongBan.setBounds(80, 452, 1115, 248);
		tblPhongBan = new JTable();

		setPreferredSize(new Dimension(1290, 750));

		pnlPhongBan.setBackground(new Color(255, 255, 255));
		pnlPhongBan.setPreferredSize(new java.awt.Dimension(1250, 300));

		txtMaPhongBan.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtMaPhongBan.setBorder(null);
		txtMaPhongBan.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				txtMaPhongBanActionPerformed(evt);
			}
		});
		pnlPhongBan.setLayout(null);
		pnlPhongBan.add(txtMaPhongBan);
		pnlPhongBan.add(jSeparator1);

		lblTenPhongBan.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblTenPhongBan.setText("Tên phòng ban:");
		pnlPhongBan.add(lblTenPhongBan);

		txtTenPhongBan.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtTenPhongBan.setBorder(null);
		txtTenPhongBan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				txtTenPhongBanActionPerformed(evt);
			}
		});
		pnlPhongBan.add(txtTenPhongBan);

		lblAnhPB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/phongBan.png")));
		lblAnhPB.setText("jLabel1");
		pnlPhongBan.add(lblAnhPB);

		lbErrTenPhongBan.setFont(new Font("Segoe UI", 0, 13));
		lbErrTenPhongBan.setForeground(new Color(255, 0, 0));
		lbErrTenPhongBan.setText("đây là dòng thông báo lỗi");
		pnlPhongBan.add(lbErrTenPhongBan);

		lblMaPhongBan.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblMaPhongBan.setText("Mã phòng ban:");
		pnlPhongBan.add(lblMaPhongBan);

		lblSoLuongNhanVien.setFont(new Font("Times New Roman", Font.PLAIN, 18)); 
		lblSoLuongNhanVien.setText("Số lượng nhân viên:");
		pnlPhongBan.add(lblSoLuongNhanVien);

		txtSoLuongNhanVien.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtSoLuongNhanVien.setBorder(null);
		txtSoLuongNhanVien.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				txtSoLuongNhanVienActionPerformed(evt);
			}
		});
		pnlPhongBan.add(txtSoLuongNhanVien);
		pnlPhongBan.add(jSeparator3);
		setLayout(null);
		add(pnlPhongBan);

		panelButon = new JPanel();
		panelButon.setBackground(Color.WHITE);
		panelButon.setBounds(80, 331, 1115, 85);
		add(panelButon);
		panelButon.setLayout(null);

		btnThem = new JButton();
		btnThem.setBounds(30, 23, 170, 40);
		panelButon.add(btnThem);
		btnThem.setBackground(new Color(255, 215, 0));
		btnThem.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnThem.setIcon(new ImageIcon(PhongBan_GUI.class.getResource("/image/icon/them.png")));
		btnThem.setText("Thêm");
		btnThem.setBorder(null);

		btnXoa = new JButton();
		btnXoa.setBounds(251, 23, 160, 40);
		panelButon.add(btnXoa);
		btnXoa.setBackground(new Color(255, 215, 0));
		btnXoa.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnXoa.setIcon(new ImageIcon(PhongBan_GUI.class.getResource("/image/icon/xoa.png")));
		btnXoa.setText("Xóa");
		btnXoa.setBorder(null);

		btnCapNhat = new JButton();
		btnCapNhat.setBounds(480, 23, 160, 40);
		panelButon.add(btnCapNhat);
		btnCapNhat.setBackground(new Color(255, 215, 0));
		btnCapNhat.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnCapNhat.setIcon(new ImageIcon(PhongBan_GUI.class.getResource("/image/icon/capNhat.png")));
		btnCapNhat.setText("Cập nhật");
		btnCapNhat.setBorder(null);

		btnLuu = new JButton();
		btnLuu.setBounds(697, 23, 160, 40);
		panelButon.add(btnLuu);
		btnLuu.setBackground(new Color(255, 0, 255));
		btnLuu.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnLuu.setIcon(new ImageIcon(PhongBan_GUI.class.getResource("/image/icon/luu.png")));
		btnLuu.setText("Lưu");
		btnLuu.setBorder(null);
		btnHuy = new JButton();
		btnHuy.setBounds(911, 23, 170, 40);
		panelButon.add(btnHuy);

		btnHuy.setBackground(new Color(255, 0, 255));
		btnHuy.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnHuy.setIcon(new ImageIcon(PhongBan_GUI.class.getResource("/image/icon/huy.png")));
		btnHuy.setText("Hủy");
		btnHuy.setBorder(null);
		
		btnHuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnHuyActionPerformed(evt);
			}
		});
		btnLuu.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnLuuActionPerformed(evt);
			}
		});
		btnCapNhat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnCapNhatActionPerformed(evt);
			}
		});
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnXoaActionPerformed(evt);
			}
		});
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnThemActionPerformed(evt);
			}
		});

		TitledBorder titledBorder1 = BorderFactory.createTitledBorder("Danh sách phòng ban");
		titledBorder1.setTitleFont(new Font("Times New Roman", Font.BOLD, 25));
		titledBorder1.setTitleJustification(TitledBorder.CENTER);
		titledBorder1.setTitleColor(Color.BLACK);
		titledBorder1.setBorder(new LineBorder(Color.BLACK));
		scrPhongBan.setBorder(titledBorder1);

		scrPhongBan.setBackground(Color.WHITE);
		tblPhongBan.getTableHeader().setBackground(new Color(128, 200, 255));
		tblPhongBan.setModel(new DefaultTableModel(
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
						"STT", "M\u00E3 ph\u00F2ng ban", "T\u00EAn ph\u00F2ng ban", "S\u1ED1 l\u01B0\u01A1ng nh\u00E2n vi\u00EAn"
				}
				));
		tblPhongBan.getColumnModel().getColumn(0).setPreferredWidth(50);
		tblPhongBan.getColumnModel().getColumn(1).setPreferredWidth(150);
		tblPhongBan.getColumnModel().getColumn(2).setPreferredWidth(200);
		tblPhongBan.getColumnModel().getColumn(3).setPreferredWidth(150);
		tblPhongBan.setSelectionBackground(new Color(255, 215, 0));
		tblPhongBan.getTableHeader().setReorderingAllowed(false);
		tblPhongBan.setRowHeight(30); 
		tblPhongBan.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		tblPhongBan.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 20));

		scrPhongBan.setViewportView(tblPhongBan);
		add(scrPhongBan);
		ScrollBarCustom scrollBar = new ScrollBarCustom();
		scrollBar.setForeground(Color.RED);
		scrPhongBan.setVerticalScrollBar(scrollBar);
	}

	private void txtMaPhongBanActionPerformed(java.awt.event.ActionEvent evt) {
	}

	private void txtTenPhongBanActionPerformed(java.awt.event.ActionEvent evt) {	
	}

	private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {
	}

	private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {
	}

	private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {
	}

	private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {
	}

	private void txtSoLuongNhanVienActionPerformed(java.awt.event.ActionEvent evt) {
	}

	private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {
		txtTenPhongBan.setEditable(false);
	}


	public void execute() {
		this.txtMaPhongBan.setBackground(Color.WHITE);
		this.txtMaPhongBan.setText("");
		this.txtTenPhongBan.setBackground(Color.WHITE);
		this.txtTenPhongBan.setText("");
		this.lbErrTenPhongBan.setText("");
		txtMaPhongBan.setEditable(false);
		txtTenPhongBan.setEditable(false);
		txtSoLuongNhanVien.setEditable(false);
		defaultTablePhongBan = (DefaultTableModel) tblPhongBan.getModel();
		taiDuLieuLenBangPhongBan();
	}

	public void taiDuLieuLenBangPhongBan() {
	    while (tblPhongBan.getRowCount() != 0) {
	        defaultTablePhongBan.removeRow(0);
	    }

	    ArrayList<PhongBan> danhSachPhongBan = phongBan_DAO.layDanhSachPhongBan();
	    NhanVien_Dao nhanVien_Dao = new NhanVien_Dao();

	    for (PhongBan phongBan : danhSachPhongBan) {
	        int soLuongNhanVien = nhanVien_Dao.laySoLuongNhanVienTheoPhongBan(phongBan.getMaPhongBan());
	        String data[] = {(defaultTablePhongBan.getRowCount() + 1) + "", phongBan.getMaPhongBan(), phongBan.getTenPhongBan(), String.valueOf(soLuongNhanVien)};
	        defaultTablePhongBan.addRow(data);
	    }

	    if (tblPhongBan.getRowCount() != 0) {
	        tblPhongBan.setRowSelectionInterval(0, 0);
	        hienThiDuLieuLenTxt(0);
	    }
	}


	public void hienThiDuLieuLenTxt(int dong) {
		txtMaPhongBan.setText(tblPhongBan.getValueAt(dong, 1).toString());
		txtTenPhongBan.setText(tblPhongBan.getValueAt(dong, 2).toString());
		txtSoLuongNhanVien.setText(tblPhongBan.getValueAt(dong, 3).toString());
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource();
		if (o.equals(tblPhongBan)) {

			int row = tblPhongBan.getSelectedRow();
			if (row != -1) {
				hienThiDuLieuLenTxt(row);
				if (!btnThem.isEnabled()) {
					btnThem.setEnabled(true);
					btnXoa.setEnabled(true);
					btnCapNhat.setEnabled(true);
					btnLuu.setEnabled(false);
					btnHuy.setEnabled(false);
					txtTenPhongBan.setEditable(false);
					lbErrTenPhongBan.setText("");
				}
			}
		}
	}

	public void khoaMoTxt(boolean b) {
		txtTenPhongBan.setEditable(b);
		txtTenPhongBan.requestFocus();
	}

	public void xoaTrang() {
		txtTenPhongBan.setText("");
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object o = e.getSource();
		if (o.equals(btnThem)) {
			oFlag = e.getSource();
			khoaMoTxt(true);
			xoaTrang();
			// đóng | mở button
			btnCapNhat.setEnabled(false);
			btnXoa.setEnabled(false);
			btnThem.setEnabled(false);
			btnHuy.setEnabled(true);
			btnLuu.setEnabled(true);
			txtMaPhongBan.setText(phongBan_DAO.layRaMaPhongBanDeThem());
			txtSoLuongNhanVien.setText("0");

		} else if (o.equals(btnLuu)) {
			// Xử lý sự kiện lưu
			// Xử lý Thêm phòng ban
			// btnFlag để lưu các JButton vừa click
			if (oFlag.equals(btnThem)) {
				String maPhongBan = txtMaPhongBan.getText();
				if (txtTenPhongBan.getText().equals("")) {
					lbErrTenPhongBan.setText(stErrKhongDeTrong);
					return;
				} else if (!txtTenPhongBan.getText().toLowerCase().matches("^([a-vxyỳọáầảấờễàạằệếýộậốũứĩõúữịỗìềểẩớặòùồợãụủíỹắẫựỉỏừỷởóéửỵẳẹèẽổẵẻỡơôưăêâđ]+)((\\s{1}[a-vxyỳọáầảấờễàạằệếýộậốũứĩõúữịỗìềểẩớặòùồợãụủíỹắẫựỉỏừỷởóéửỵẳẹèẽổẵẻỡơôưăêâđ]+){0,})$")) {
					lbErrTenPhongBan.setText(stErrTenPhongBan);
					return;
				} else {
					lbErrTenPhongBan.setText("");
				}
				String tenPhongBan = txtTenPhongBan.getText().trim();
				boolean coThemDuoc = phongBan_DAO.themMotPhongBan(new PhongBan(maPhongBan, tenPhongBan, 0));
				if (coThemDuoc) {
					// tải dữ liệu lại vào jtable
					taiDuLieuLenBangPhongBan();
					// đóng mở các button
					btnCapNhat.setEnabled(true);
					btnThem.setEnabled(true);
					btnXoa.setEnabled(true);
					btnLuu.setEnabled(false);
					btnHuy.setEnabled(false);
					oFlag = null;
					khoaMoTxt(false); // false là khóa lại, true là mở ra
					JOptionPane.showMessageDialog(null, stThemThanhCong, stThongbao, JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, stThemThatBai, stThongbao, JOptionPane.INFORMATION_MESSAGE);
				}
			} else if (oFlag.equals(btnCapNhat)) {
				String maPhongBan = txtMaPhongBan.getText();
				if (txtTenPhongBan.getText().equals("")) {
					lbErrTenPhongBan.setText(stErrKhongDeTrong);
					return;
				} else if (!txtTenPhongBan.getText().toLowerCase().matches("^([a-vxyỳọáầảấờễàạằệếýộậốũứĩõúữịỗìềểẩớặòùồợãụủíỹắẫựỉỏừỷởóéửỵẳẹèẽổẵẻỡơôưăêâđ]+)(\\s{1}[a-vxyỳọáầảấờễàạằệếýộậốũứĩõúữịỗìềểẩớặòùồợãụủíỹắẫựỉỏừỷởóéửỵẳẹèẽổẵẻỡơôưăêâđ]+){0,}$")) {
					lbErrTenPhongBan.setText(stErrTenPhongBan);
					return;
				}else {
					lbErrTenPhongBan.setText("");
				}
				int soLuongNhanVien = 0;
				try {
					soLuongNhanVien = Integer.parseInt(txtSoLuongNhanVien.getText());
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "");
				}
				String tenPhongBan = txtTenPhongBan.getText().trim();
				boolean coSuaDuoc = phongBan_DAO.suaMotPhongBan(new PhongBan(maPhongBan, tenPhongBan, soLuongNhanVien));
				if (coSuaDuoc) {
					// đóng mở button
					btnThem.setEnabled(true);
					btnXoa.setEnabled(true);
					btnCapNhat.setEnabled(true);
					btnHuy.setEnabled(false);
					btnLuu.setEnabled(false);
					oFlag = null;
					khoaMoTxt(false);
					taiDuLieuLenBangPhongBan();
					JOptionPane.showMessageDialog(null, stCapNhatThanhCong, stThongbao, JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, stCapNhatThatBai, stThongbao, JOptionPane.INFORMATION_MESSAGE);
				}
			}
		} else if (o.equals(btnCapNhat)) {
			// Xử lý cập nhật phòng ban
			khoaMoTxt(true);
			btnThem.setEnabled(false);
			btnXoa.setEnabled(false);
			btnCapNhat.setEnabled(false);
			btnHuy.setEnabled(true);
			btnLuu.setEnabled(true);
			// gán cờ
			oFlag = btnCapNhat;
		} else if (o.equals(btnXoa)) {

			int rowSelected = tblPhongBan.getSelectedRow();
			if (rowSelected != -1) {
				int coXacNhanXoa = JOptionPane.showConfirmDialog(null, stBanXacNhanXoa, stThongbao, JOptionPane.ERROR_MESSAGE);
				if (coXacNhanXoa == 0) {
					boolean coXoaDuoc = phongBan_DAO.xoaMotPhongBanTheoMa(tblPhongBan.getValueAt(tblPhongBan.getSelectedRow(), 1).toString());
					if (coXoaDuoc) {
						JOptionPane.showMessageDialog(null, stXoaThanhCong, stThongbao, JOptionPane.INFORMATION_MESSAGE);
						taiDuLieuLenBangPhongBan();
					} else {
						JOptionPane.showMessageDialog(null, stXoaThatBai, stThongbao, JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}

		} else if (o.equals(btnHuy)) {
			btnThem.setEnabled(true);
			btnCapNhat.setEnabled(true);
			btnXoa.setEnabled(true);
			btnHuy.setEnabled(false);
			btnLuu.setEnabled(false);
			lbErrTenPhongBan.setText("");
			if (tblPhongBan.getSelectedRow() != -1) {
				hienThiDuLieuLenTxt(tblPhongBan.getSelectedRow());
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
}
