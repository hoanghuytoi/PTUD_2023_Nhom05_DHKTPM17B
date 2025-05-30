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
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JSeparator;
import javax.swing.JTable;

import Custom_UI.ScrollBarCustom;
import Dao.CongNhan_Dao;
import Dao.ToNhom_Dao;
import Entity.ToNhom;

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

public class ToNhom_GUI extends JPanel implements MouseListener, ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnCapNhat;
	private JButton btnHuy;
	private JButton btnLuu;
	private JButton btnThem;
	private JButton btnXoa;
	private JLabel lblAnhTN;
	private JSeparator jSeparator1;
	private JSeparator jSeparator2;
	private JSeparator jSeparator3;
	private JLabel lbErrTenToNhom;
	private JLabel lblMaToNhom;
	private JLabel lblSoLuongCongNhan;
	private JLabel lblTenToNhom;
	private JPanel pnlToNhom;
	private JPanel panelButon;
	private JScrollPane scrToNhom;
	private JTable tblToNhom;
	private JTextField txtMaToNhom;
	private JTextField txtSoLuongCongNhan;
	private JTextField txtTenToNhom;

	private ToNhom_Dao toNhom_DAO;
	private DefaultTableModel defaultTableToNhom;
	private Object oFlag;
	private String khongDeTrong;
    private String stThongbao;
    private String stBanXacNhanXoa;
    private String stXoaThanhCong;
    private String stXoaThatBai;
    private String stThemThanhCong;
    private String stThemThatBai;
    private String stCapNhatThanhCong;
    private String stCapNhatThatBai;
    private String stTenToTheoMau;
    private String stDaTonTai;
	private JLabel lblTieuDe;


	public ToNhom_GUI(String fileName) throws IOException {
		setBackground(Color.WHITE);
		initComponents();
		caiDatNgonNgu(fileName);
		try {
			ConnectionDB.ConnectDB.getInstance().connect();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
        tblToNhom.addMouseListener(this);
		toNhom_DAO = new ToNhom_Dao();
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnCapNhat.addActionListener(this);
		btnHuy.addActionListener(this);
		btnLuu.addActionListener(this);
		btnLuu.setEnabled(false);
		btnHuy.setEnabled(false);
		execute();
	}
	
	public void caiDatNgonNgu(String fileName) throws FileNotFoundException, IOException {
        FileInputStream fis = new FileInputStream(fileName);
        Properties prop = new Properties();
        prop.load(fis);
        lblTieuDe.setText(prop.getProperty("toNhom_TieuDe"));
        lblMaToNhom.setText(prop.getProperty("toNhom_maToNhom"));
        lblTenToNhom.setText(prop.getProperty("toNhom_tenToNhom"));
        lblSoLuongCongNhan.setText(prop.getProperty("toNhom_soLuongCongNhan"));
        pnlToNhom.setBorder(new TitledBorder(prop.getProperty("toNhom_ThongTinTN")));
		scrToNhom.setBorder(new TitledBorder(prop.getProperty("toNhom_TableTN")));
		
        doiNgonNguTable(tblToNhom, 0, prop.getProperty("toNhom_STT"));
        doiNgonNguTable(tblToNhom, 1, lblMaToNhom.getText());
        doiNgonNguTable(tblToNhom, 2, lblTenToNhom.getText());
        doiNgonNguTable(tblToNhom, 3, lblSoLuongCongNhan.getText());
        
        btnThem.setText(prop.getProperty("btnThem"));
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
        khongDeTrong = prop.getProperty("khongDeTrong");
        stTenToTheoMau = prop.getProperty("toNhom_tenTheoMau");
        stDaTonTai = prop.getProperty("toNhom_tenDaTonTai");
    }

	public void doiNgonNguTable(JTable table, int col_index, String col_name) {
		table.getColumnModel().getColumn(col_index).setHeaderValue(col_name);
	}

	private void initComponents() {
		lblTieuDe = new JLabel("QUẢN LÝ TỔ NHÓM");
		lblTieuDe.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblTieuDe.setBounds(510, 11, 296, 55);
		add(lblTieuDe);

		pnlToNhom = new JPanel();
		pnlToNhom.setBounds(80, 77, 1115, 257);
		LineBorder blackLineBorder = new LineBorder(Color.BLACK, 2);
		TitledBorder titledBorder = BorderFactory.createTitledBorder(blackLineBorder, "Thông tin tổ nhóm");
		pnlToNhom.setBorder(titledBorder);

		txtMaToNhom = new JTextField();
		txtMaToNhom.setBounds(690, 30, 290, 30);
		jSeparator1 = new JSeparator();
		jSeparator1.setForeground(new Color(0, 0, 0));
		jSeparator1.setBounds(690, 60, 290, 10);
		lblTenToNhom = new JLabel();
		lblTenToNhom.setBounds(500, 93, 190, 42);
		txtTenToNhom = new JTextField();
		txtTenToNhom.setBounds(690, 90, 290, 30);
		lblAnhTN = 
				new JLabel();
		lblAnhTN.setBounds(107, 29, 270, 217);
		lbErrTenToNhom = new JLabel();
		lbErrTenToNhom.setBounds(690, 125, 290, 29);
		lblMaToNhom = new JLabel();
		lblMaToNhom.setBounds(500, 25, 190, 50);
		lblSoLuongCongNhan = new JLabel();
		lblSoLuongCongNhan.setBounds(500, 153, 190, 42);
		txtSoLuongCongNhan = new JTextField();
		txtSoLuongCongNhan.setBackground(Color.WHITE);
		txtSoLuongCongNhan.setBounds(690, 150, 290, 30);
		jSeparator2 = new JSeparator();
		jSeparator2.setForeground(Color.BLACK);
		jSeparator2.setBounds(690, 125, 290, 10);
		pnlToNhom.add(jSeparator2);

		jSeparator3 = new JSeparator();
		jSeparator3.setForeground(new Color(0, 0, 0));
		jSeparator3.setBounds(690, 180, 290, 10);
		scrToNhom = new JScrollPane();
		scrToNhom.setBounds(80, 452, 1115, 248);
		tblToNhom = new JTable();

		setPreferredSize(new Dimension(1290, 750));

		pnlToNhom.setBackground(new Color(255, 255, 255));
		pnlToNhom.setPreferredSize(new java.awt.Dimension(1250, 300));

		txtMaToNhom.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtMaToNhom.setBorder(null);
		txtMaToNhom.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				txtMaPhongBanActionPerformed(evt);
			}
		});
		pnlToNhom.setLayout(null);
		pnlToNhom.add(txtMaToNhom);
		pnlToNhom.add(jSeparator1);

		lblTenToNhom.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblTenToNhom.setText("Tên tổ nhóm:");
		pnlToNhom.add(lblTenToNhom);

		txtTenToNhom.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtTenToNhom.setBorder(null);
		txtTenToNhom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				txtTenPhongBanActionPerformed(evt);
			}
		});
		pnlToNhom.add(txtTenToNhom);

		lblAnhTN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/toNhom.png")));
		pnlToNhom.add(lblAnhTN);

		lbErrTenToNhom.setFont(new Font("Segoe UI", 0, 13));
		lbErrTenToNhom.setForeground(new Color(255, 0, 0));
		lbErrTenToNhom.setText("đây là dòng thông báo lỗi");
		pnlToNhom.add(lbErrTenToNhom);

		lblMaToNhom.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblMaToNhom.setText("Mã tổ nhóm:");
		pnlToNhom.add(lblMaToNhom);

		lblSoLuongCongNhan.setFont(new Font("Times New Roman", Font.PLAIN, 18)); 
		lblSoLuongCongNhan.setText("Số lượng công nhân:");
		pnlToNhom.add(lblSoLuongCongNhan);

		txtSoLuongCongNhan.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtSoLuongCongNhan.setBorder(null);
		txtSoLuongCongNhan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				txtSoLuongNhanVienActionPerformed(evt);
			}
		});
		pnlToNhom.add(txtSoLuongCongNhan);
		pnlToNhom.add(jSeparator3);
		setLayout(null);
		add(pnlToNhom);

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

		TitledBorder titledBorder1 = BorderFactory.createTitledBorder("Danh sách tổ nhóm");
		titledBorder1.setTitleFont(new Font("Times New Roman", Font.BOLD, 25));
		titledBorder1.setTitleJustification(TitledBorder.CENTER);
		titledBorder1.setTitleColor(Color.BLACK);
		titledBorder1.setBorder(new LineBorder(Color.BLACK));
		scrToNhom.setBorder(titledBorder1);

		scrToNhom.setBackground(Color.WHITE);
		tblToNhom.getTableHeader().setBackground(new Color(128, 200, 255));
		tblToNhom.setModel(new DefaultTableModel(
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
						"STT", "Mã tổ nhóm", "Tên tổ nhóm", "Số lượng công nhân"
				}
				));
		tblToNhom.getColumnModel().getColumn(0).setPreferredWidth(50);
		tblToNhom.getColumnModel().getColumn(1).setPreferredWidth(150);
		tblToNhom.getColumnModel().getColumn(2).setPreferredWidth(200);
		tblToNhom.getColumnModel().getColumn(3).setPreferredWidth(150);
		tblToNhom.setSelectionBackground(new Color(255, 215, 0));
		tblToNhom.getTableHeader().setReorderingAllowed(false);
		tblToNhom.setRowHeight(30); 
		tblToNhom.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		tblToNhom.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 20));

		scrToNhom.setViewportView(tblToNhom);
		add(scrToNhom);
		ScrollBarCustom scrollBar = new ScrollBarCustom();
		scrollBar.setForeground(Color.RED);
		scrToNhom.setVerticalScrollBar(scrollBar);
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
		txtTenToNhom.setEditable(false);
	}


	public void execute() {
		this.txtMaToNhom.setBackground(Color.WHITE);
		this.txtMaToNhom.setText("");
		this.txtTenToNhom.setBackground(Color.WHITE);
		this.txtTenToNhom.setText("");
		this.lbErrTenToNhom.setText("");
		txtMaToNhom.setEditable(false);
		txtTenToNhom.setEditable(false);
		txtSoLuongCongNhan.setEditable(false);
		defaultTableToNhom = (DefaultTableModel) tblToNhom.getModel();
		taiDuLieuLenBang();
	}

	public void taiDuLieuLenBang() {
		while (tblToNhom.getRowCount() != 0) {
			defaultTableToNhom.removeRow(0);
		}
		ArrayList<ToNhom> danhSachToNhom = toNhom_DAO.layDanhSachToNhom();
		CongNhan_Dao congNhan_dao = new CongNhan_Dao();
		for (ToNhom toNhom : danhSachToNhom) {
	        int soLuongCongNhan = congNhan_dao.laySoLuongCongNhanTheoToNhom(toNhom.getMaToNhom());
			String data[] = {(defaultTableToNhom.getRowCount() + 1) + "", toNhom.getMaToNhom(), toNhom.getTenToNhom(), String.valueOf(soLuongCongNhan)};
			defaultTableToNhom.addRow(data);
		}
		if (tblToNhom.getRowCount() != 0) {
			tblToNhom.setRowSelectionInterval(0, 0);
			hienThiDuLieuLenTxt(0);
		}
	}

	public void hienThiDuLieuLenTxt(int dong) {
		txtMaToNhom.setText(tblToNhom.getValueAt(dong, 1).toString());
		txtTenToNhom.setText(tblToNhom.getValueAt(dong, 2).toString());
		txtSoLuongCongNhan.setText(tblToNhom.getValueAt(dong, 3).toString());
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

	@Override
	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource();
		if (o.equals(tblToNhom)) {

			int row = tblToNhom.getSelectedRow();
			if (row != -1) {
				hienThiDuLieuLenTxt(row);
				if (!btnThem.isEnabled()) {
					btnThem.setEnabled(true);
					btnXoa.setEnabled(true);
					btnCapNhat.setEnabled(true);
					btnLuu.setEnabled(false);
					btnHuy.setEnabled(false);
					txtTenToNhom.setEditable(false);
					lbErrTenToNhom.setText("");
				}
			}
		}
	}

	public void xoaTrang() {
		txtTenToNhom.setText("");
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object o = e.getSource();
		if (o.equals(btnThem)) {
			// gán cờ 
			oFlag = e.getSource();
			khoaMoTxt(true);
			xoaTrang();
			// đóng | mở button
			btnCapNhat.setEnabled(false);
			btnXoa.setEnabled(false);
			btnThem.setEnabled(false);
			btnHuy.setEnabled(true);
			btnLuu.setEnabled(true);
			txtMaToNhom.setText(toNhom_DAO.layRaMaToNhomDeThem());
			txtSoLuongCongNhan.setText("0");

		} else if (o.equals(btnLuu)) {
			if (oFlag.equals(btnThem)) {
				String maToNhom = txtMaToNhom.getText();
				if (txtTenToNhom.getText().equals("")) {
					lbErrTenToNhom.setText(khongDeTrong);
					return;
				} else if (!txtTenToNhom.getText().toLowerCase().matches("^tổ [1-9][0-9]*$")) {
                    lbErrTenToNhom.setText(stTenToTheoMau);
                    return;
                } else {
                    lbErrTenToNhom.setText("");
                }
                ToNhom_Dao toNhomDao = new ToNhom_Dao();
                ArrayList<ToNhom> toNhomList = toNhomDao.layDanhSachToNhom();
                for (ToNhom toNhom : toNhomList) {
                    if (toNhom.getTenToNhom().equalsIgnoreCase(txtTenToNhom.getText())) {
                        JOptionPane.showMessageDialog(this, stDaTonTai, stThongbao, JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                }
				String tenToNhom = txtTenToNhom.getText().trim();
				boolean coThemDuoc = toNhom_DAO.themToNhom(new ToNhom(maToNhom, tenToNhom, 0));
				if (coThemDuoc) {
					taiDuLieuLenBang();
					btnCapNhat.setEnabled(true);
					btnThem.setEnabled(true);
					btnXoa.setEnabled(true);
					btnLuu.setEnabled(false);
					btnHuy.setEnabled(false);
					oFlag = null;
					khoaMoTxt(false);
					JOptionPane.showMessageDialog(null, stThemThanhCong, stThongbao, JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, stThemThatBai, stThongbao, JOptionPane.INFORMATION_MESSAGE);
				}
			} else if (oFlag.equals(btnCapNhat)) {
				String maToNhom = txtMaToNhom.getText();
                if (txtTenToNhom.getText().equals("")) {
                    lbErrTenToNhom.setText(khongDeTrong);
                    return;
                } else if (!txtTenToNhom.getText().toLowerCase().matches("^tổ [1-9][0-9]*$")) {
                    lbErrTenToNhom.setText(stTenToTheoMau);
                    return;
                } else {
                    lbErrTenToNhom.setText("");
                }
                int soLuongCongNhan = 0;
                try {
                    soLuongCongNhan = Integer.parseInt(txtSoLuongCongNhan.getText());
                } catch (Exception e2) {
                    JOptionPane.showMessageDialog(null, "Số lượng công nhân phải là số");
                }
                String tenToNhom = txtTenToNhom.getText().trim();
                boolean coSuaDuoc = toNhom_DAO.suaToNhom(new ToNhom(maToNhom, tenToNhom, soLuongCongNhan));
                if (coSuaDuoc) {
                    // đóng mở button
                    btnThem.setEnabled(true);
                    btnXoa.setEnabled(true);
                    btnCapNhat.setEnabled(true);
                    btnHuy.setEnabled(false);
                    btnLuu.setEnabled(false);
                    oFlag = null;
                    khoaMoTxt(false);
                    taiDuLieuLenBang();
                    JOptionPane.showMessageDialog(null, stCapNhatThanhCong, stThongbao, JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, stCapNhatThatBai, stThongbao, JOptionPane.INFORMATION_MESSAGE);
                }
			}
		}
		else if (o.equals(btnCapNhat)) {
			khoaMoTxt(true);
			btnThem.setEnabled(false);
			btnXoa.setEnabled(false);
			btnCapNhat.setEnabled(false);
			btnHuy.setEnabled(true);
			btnLuu.setEnabled(true);
			// gán cờ
			oFlag = btnCapNhat;
		} else if (o.equals(btnXoa)) {

			int rowSelected = tblToNhom.getSelectedRow();
			if (rowSelected != -1) {
				int coXacNhanXoa = JOptionPane.showConfirmDialog(null, stBanXacNhanXoa, stThongbao, JOptionPane.ERROR_MESSAGE);
				if (coXacNhanXoa == 0) {
					boolean coXoaDuoc = toNhom_DAO.xoaMotToNhomTheoMa(tblToNhom.getValueAt(tblToNhom.getSelectedRow(), 1).toString());
					if (coXoaDuoc) {
						JOptionPane.showMessageDialog(null, stXoaThanhCong, stThongbao, JOptionPane.INFORMATION_MESSAGE);
						taiDuLieuLenBang();
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
			lbErrTenToNhom.setText("");
			if (tblToNhom.getSelectedRow() != -1) {
				hienThiDuLieuLenTxt(tblToNhom.getSelectedRow());
			}
		}
	}

	public void khoaMoTxt(boolean b) {
		txtTenToNhom.setEditable(b);
		txtTenToNhom.requestFocus();
	}
}
