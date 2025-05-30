package UI;

import Entity.CongNhan;
import Entity.ToNhom;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Dao.CongNhan_Dao;
import Dao.ToNhom_Dao;

import javax.swing.JSeparator;
import javax.swing.JComboBox;

public class TimKiemCongNhan_GUI extends JPanel implements ActionListener {
	/**
	 *  ngoc minh
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnTimKiem;
	private JPanel pnlNV;
	private JLabel lblEmail;
	private JLabel lblErrEmail;
	private JLabel lblErrHoTen;
	private JLabel lblErrSoCCCD;
	private JLabel lblErrSoDienThoai;
	private JLabel lblHoTen;
	private JLabel lblMaCongNhan;
	private JLabel lblSoCCCD;
	private JLabel lblSoDienThoai;
	private JScrollPane scrTableCongNhan;
	private JTable tblCongNhan;
	private JTextField txtEmail;
	private JTextField txtHoTen;
	private JTextField txtMaCongNhan;
	private JTextField txtSoCCCD;
	private JTextField txtSoDienThoai;

	private DefaultTableModel model;
	private JSeparator jSeparator_2;
	private JSeparator jSeparator_3;
	private JSeparator jSeparator_4;
	private JLabel lblTieuDe;
	private JButton btnXoaRong;
	private JButton btnQuayLai;
	private JLabel lblGioiTinh;
	private JComboBox<String> cmbGioiTinh;
	private JLabel lblToNhom;
	private JComboBox<String> cmbToNhom;
	private CongNhan_Dao congNhan_DAO;
	private ToNhom_Dao toNhom_DAO;
	private String stThongbao;
	private String stThongBaoTimKiem;

	public TimKiemCongNhan_GUI(String fileName) throws IOException {
		initComponents();
		excute();

		try {
			ConnectionDB.ConnectDB.getInstance().connect();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		congNhan_DAO = new CongNhan_Dao();
        toNhom_DAO = new ToNhom_Dao();
		model = (DefaultTableModel) tblCongNhan.getModel();
        loadCmbToNhom();
        cmbToNhom.removeItemAt(0);

		//taiDuLieuLenBang("all", "all", "all", "all", "all");
		btnTimKiem.addActionListener(this);
		btnXoaRong.addActionListener(this);
		btnQuayLai.addActionListener(this);

		lblErrEmail.setText("");
		lblErrHoTen.setText("");
		lblErrSoCCCD.setText("");
		lblErrSoDienThoai.setText("");
		caiDatNgonNgu(fileName);

	}
	
	public void caiDatNgonNgu(String fileName) throws FileNotFoundException, IOException {
        FileInputStream fis = new FileInputStream(fileName);
        Properties prop = new Properties();
        prop.load(fis);
        
        lblTieuDe.setText(prop.getProperty("tkcn_TieuDe"));
        lblMaCongNhan.setText(prop.getProperty("maCongNhan"));
        lblHoTen.setText(prop.getProperty("hoTen"));
        lblSoCCCD.setText(prop.getProperty("soCCCD"));
        lblEmail.setText(prop.getProperty("email"));
        lblSoDienThoai.setText(prop.getProperty("sdt"));
        lblGioiTinh.setText(prop.getProperty("gioiTinh"));
        lblToNhom.setText(prop.getProperty("toNhom"));
        cmbGioiTinh.removeAllItems();
        cmbGioiTinh.addItem(prop.getProperty("nam"));
        cmbGioiTinh.addItem(prop.getProperty("nu"));
        cmbGioiTinh.addItem(prop.getProperty("cmbTatCa"));
        cmbToNhom.addItem("hello");
        
        cmbToNhom.removeItemAt(cmbToNhom.getItemCount() - 1);
        cmbToNhom.addItem(prop.getProperty("cmbTatCa"));
        cmbToNhom.setSelectedIndex(cmbToNhom.getItemCount() - 1);

        scrTableCongNhan.setBorder(new TitledBorder(prop.getProperty("congNhan_TableCN")));

        
        cmbGioiTinh.setSelectedIndex(cmbGioiTinh.getItemCount() - 1);
        btnTimKiem.setText(prop.getProperty("btnTimKiem"));
        btnQuayLai.setText(prop.getProperty("btnQuayLai"));
        btnXoaRong.setText(prop.getProperty("btnXoaRong"));
        ChangeName(tblCongNhan, 0, prop.getProperty("pcd_stt"));
        ChangeName(tblCongNhan, 1, lblMaCongNhan.getText());
        ChangeName(tblCongNhan, 2, lblHoTen.getText());
        ChangeName(tblCongNhan, 3, lblSoCCCD.getText());
        ChangeName(tblCongNhan, 4, lblGioiTinh.getText());
        ChangeName(tblCongNhan, 5, prop.getProperty("ngaySinh"));
        ChangeName(tblCongNhan, 6, lblSoDienThoai.getText());
        ChangeName(tblCongNhan, 7, prop.getProperty("diaChi"));
        ChangeName(tblCongNhan, 8, prop.getProperty("anhDaiDien"));
        ChangeName(tblCongNhan, 9, lblEmail.getText());
        ChangeName(tblCongNhan, 10, lblToNhom.getText());
        ChangeName(tblCongNhan, 11, prop.getProperty("ngayVaoLam"));

        stThongbao = prop.getProperty("thongBao");
        stThongBaoTimKiem = prop.getProperty("timKiem_KhongThay");
    }

    public void ChangeName(JTable table, int col_index, String col_name) {
        table.getColumnModel().getColumn(col_index).setHeaderValue(col_name);
    }

	public void loadCmbToNhom() {
        cmbToNhom.removeAllItems();
        ArrayList<ToNhom> dsToNhom = toNhom_DAO.layDanhSachToNhom();
        cmbToNhom.addItem("Tất cả");
        for (ToNhom toNhom : dsToNhom) {
            cmbToNhom.addItem(toNhom.getTenToNhom());
        }
    }
	
	private void initComponents() {
		setBackground(new Color(255, 255, 255));

		pnlNV = new JPanel();
		pnlNV.setBounds(10, 62, 1190, 376);
		lblErrHoTen = new JLabel();
		lblErrHoTen.setBounds(930, 60, 280, 0);
		txtHoTen = new JTextField();
		txtHoTen.setBounds(823, 30, 280, 40);
		lblHoTen = new JLabel();
		lblHoTen.setBounds(699, 40, 130, 40);
		txtEmail = new JTextField();
		txtEmail.setBounds(823, 90, 280, 40);
		lblErrEmail = new JLabel();
		lblErrEmail.setBounds(930, 130, 280, 0);
		lblEmail = new JLabel();
		lblEmail.setBounds(699, 100, 88, 40);
		txtSoCCCD = new JTextField();
		txtSoCCCD.setBounds(296, 90, 280, 40);
		lblErrSoCCCD = new JLabel();
		lblErrSoCCCD.setBounds(420, 130, 280, 0);
		lblSoCCCD = new JLabel();
		lblSoCCCD.setBounds(156, 105, 140, 35);
		txtMaCongNhan = new JTextField();
		txtMaCongNhan.setBounds(296, 30, 280, 40);
		lblMaCongNhan = new JLabel();
		lblMaCongNhan.setBounds(156, 40, 140, 40);
		txtSoDienThoai = new JTextField();
		txtSoDienThoai.setBounds(296, 150, 280, 40);
		lblSoDienThoai = new JLabel();
		lblSoDienThoai.setBounds(156, 160, 140, 40);
		lblErrSoDienThoai = new JLabel();
		lblErrSoDienThoai.setBounds(420, 190, 280, 0);
		btnTimKiem = new JButton();
		btnTimKiem.setBackground(new Color(255, 215, 0));
		btnTimKiem.setBounds(504, 306, 230, 40);
		scrTableCongNhan = new JScrollPane();
		scrTableCongNhan.setBounds(28, 448, 1226, 276);
		tblCongNhan = new JTable();

		setPreferredSize(new Dimension(1290, 750));

		pnlNV.setBackground(new Color(255, 255, 255));
		pnlNV.setPreferredSize(new Dimension(1250, 400));
		pnlNV.setLayout(null);

		lblErrHoTen.setFont(new Font("Segoe UI", 0, 13));
		lblErrHoTen.setForeground(new Color(204, 0, 0));
		lblErrHoTen.setText("đây là dòng thông báo lỗi");
		pnlNV.add(lblErrHoTen);

		txtHoTen.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtHoTen.setBorder(null);
		txtHoTen.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				txtHoTenActionPerformed(evt);
			}
		});
		pnlNV.add(txtHoTen);

		lblHoTen.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblHoTen.setText("Họ và tên:");
		pnlNV.add(lblHoTen);

		txtEmail.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtEmail.setBorder(null);
		pnlNV.add(txtEmail);

		lblErrEmail.setFont(new Font("Segoe UI", 0, 13));
		lblErrEmail.setForeground(new Color(204, 0, 0));
		lblErrEmail.setText("đây là dòng thông báo lỗi");
		pnlNV.add(lblErrEmail);

		lblEmail.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblEmail.setText("Email:");
		pnlNV.add(lblEmail);

		txtSoCCCD.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtSoCCCD.setBorder(null);
		pnlNV.add(txtSoCCCD);

		lblErrSoCCCD.setFont(new java.awt.Font("Segoe UI", 0, 13));
		lblErrSoCCCD.setForeground(new java.awt.Color(204, 0, 0));
		lblErrSoCCCD.setText("đây là dòng thông báo lỗi");
		pnlNV.add(lblErrSoCCCD);

		lblSoCCCD.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblSoCCCD.setText("Số CCCD:");
		pnlNV.add(lblSoCCCD);

		txtMaCongNhan.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtMaCongNhan.setBorder(null);
		pnlNV.add(txtMaCongNhan);

		lblMaCongNhan.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblMaCongNhan.setText("Mã công nhân:");
		pnlNV.add(lblMaCongNhan);

		txtSoDienThoai.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtSoDienThoai.setBorder(null);
		pnlNV.add(txtSoDienThoai);
		
		lblSoDienThoai.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblSoDienThoai.setText("Số điện thoại:");
		pnlNV.add(lblSoDienThoai);

		lblErrSoDienThoai.setFont(new Font("Segoe UI", 0, 13));
		lblErrSoDienThoai.setForeground(new Color(204, 0, 0));
		lblErrSoDienThoai.setText("đây là dòng thông báo lỗi");
		pnlNV.add(lblErrSoDienThoai);
		
		JSeparator jSeparator = new JSeparator();
		jSeparator.setForeground(Color.BLACK);
		jSeparator.setBounds(296, 70, 280, 10);
		pnlNV.add(jSeparator);
		
		JSeparator jSeparator_1 = new JSeparator();
		jSeparator_1.setForeground(Color.BLACK);
		jSeparator_1.setBounds(296, 130, 280, 10);
		pnlNV.add(jSeparator_1);
		
		jSeparator_2 = new JSeparator();
		jSeparator_2.setForeground(Color.BLACK);
		jSeparator_2.setBounds(296, 190, 280, 10);
		pnlNV.add(jSeparator_2);
		
		jSeparator_3 = new JSeparator();
		jSeparator_3.setForeground(Color.BLACK);
		jSeparator_3.setBounds(823, 130, 280, 10);
		pnlNV.add(jSeparator_3);
		
		jSeparator_4 = new JSeparator();
		jSeparator_4.setForeground(Color.BLACK);
		jSeparator_4.setBounds(823, 70, 280, 10);
		pnlNV.add(jSeparator_4);
		
		lblTieuDe = new JLabel();
		lblTieuDe.setBounds(485, 23, 404, 40);
		add(lblTieuDe);
		lblTieuDe.setText("TÌM KIẾM CÔNG NHÂN");
		lblTieuDe.setFont(new Font("Times New Roman", Font.BOLD, 25));
		
		lblGioiTinh = new JLabel();
		lblGioiTinh.setText("Giới tính:");
		lblGioiTinh.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblGioiTinh.setBounds(699, 170, 140, 30);
		pnlNV.add(lblGioiTinh);
		
		cmbGioiTinh = new JComboBox<String>();
        cmbGioiTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Nam", "Nữ", " " }));
		cmbGioiTinh.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		cmbGioiTinh.setBackground(Color.WHITE);
		cmbGioiTinh.setBounds(823, 160, 280, 40);
		pnlNV.add(cmbGioiTinh);
		
		lblToNhom = new JLabel();
		lblToNhom.setText("Tổ nhóm:");
		lblToNhom.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblToNhom.setBounds(156, 231, 140, 30);
		pnlNV.add(lblToNhom);
		
		cmbToNhom = new JComboBox<String>();
		cmbToNhom.setSelectedIndex(-1);
		cmbToNhom.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		cmbToNhom.setBackground(Color.WHITE);
		cmbToNhom.setBounds(296, 221, 280, 40);
		pnlNV.add(cmbToNhom);

		btnTimKiem.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnTimKiem.setIcon(new ImageIcon(TimKiemNhanVien_GUI.class.getResource("/image/icon/timKiem.png")));
		btnTimKiem.setText("Tìm kiếm");
		btnTimKiem.setBorder(null);
		btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnTimKiemActionPerformed(evt);
			}
		});
		setLayout(null);
		pnlNV.add(btnTimKiem);
		add(pnlNV);
		
		btnXoaRong = new JButton();
		btnXoaRong.setIcon(new ImageIcon(TimKiemNhanVien_GUI.class.getResource("/image/icon/xoaRong.png")));
		btnXoaRong.setText("Xóa rỗng");
		btnXoaRong.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnXoaRong.setBorder(null);
		btnXoaRong.setBackground(new Color(255, 192, 203));
		btnXoaRong.setBounds(188, 306, 230, 40);
		pnlNV.add(btnXoaRong);
		btnXoaRong.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnXoaRongActionPerformed(evt);
			}
		});
		
		btnQuayLai = new JButton();
		btnQuayLai.setIcon(new ImageIcon(TimKiemNhanVien_GUI.class.getResource("/image/icon/reset.png")));
		btnQuayLai.setText("Quay lại");
		btnQuayLai.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnQuayLai.setBorder(null);
		btnQuayLai.setBackground(new Color(255, 192, 203));
		btnQuayLai.setBounds(846, 306, 230, 40);
		pnlNV.add(btnQuayLai);
		btnQuayLai.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnQuayLaiActionPerformed(evt);
			}
		});
		
		tblCongNhan.getTableHeader().setBackground(new Color(128, 200, 255));
	    tblCongNhan.setSelectionBackground(new Color(255, 215, 0));
		tblCongNhan.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null, null, null, null, null,},
				{null, null, null, null, null, null, null, null, null, null, null, null,},
				{null, null, null, null, null, null, null, null, null, null, null, null,},
				{null, null, null, null, null, null, null, null, null, null, null, null,},
				{null, null, null, null, null, null, null, null, null, null, null, null,},
				{null, null, null, null, null, null, null, null, null, null, null, null,},
				{null, null, null, null, null, null, null, null, null, null, null, null,},
				{null, null, null, null, null, null, null, null, null, null, null, null,},
			},
			new String[] {
				"STT", "Mã công nhân", "H\u1ECD v\u00E0 t\u00EAn", "S\u1ED1 CCCD", "Gi\u1EDBi t\u00EDnh", "Ng\u00E0y sinh", "S\u1ED1 \u0111i\u1EC7n tho\u1EA1i", "\u0110\u1ECBa ch\u1EC9", "\u1EA2nh \u0111\u1EA1i di\u1EC7n", "Email", "Tổ nhóm", "Ng\u00E0y v\u00E0o l\u00E0m"
			}
		));
		tblCongNhan.getColumnModel().getColumn(0).setPreferredWidth(45);
		tblCongNhan.getColumnModel().getColumn(2).setPreferredWidth(115);
		scrTableCongNhan.setViewportView(tblCongNhan);
		scrTableCongNhan.setBackground(new java.awt.Color(255, 255, 255));
		LineBorder blackLineBorder = new LineBorder(Color.BLACK, 2);
		TitledBorder titledBorder = BorderFactory.createTitledBorder(blackLineBorder, "Danh sách nhân viên");
		scrTableCongNhan.setBorder(titledBorder);
		add(scrTableCongNhan);
	}
	
	private void btnQuayLaiActionPerformed(ActionEvent evt) {
	    taiDuLieuLenBang("all", "all", "all", "all", "all", "all", "all");
	}

	private void btnXoaRongActionPerformed(ActionEvent evt) {
		txtMaCongNhan.setText("");
		txtEmail.setText("");
		txtHoTen.setText("");
		txtSoCCCD.setText("");
		txtSoDienThoai.setText("");
	}

	public void taiDuLieuLenBang(String maCongNhan, String hoTen, String soCCCD, String email, String soDienThoai, String gioiTinh,
            String toNhom) {
       while (tblCongNhan.getRowCount() != 0) {
           model.removeRow(0);
       }
       ArrayList<CongNhan> dsCongNhan = congNhan_DAO.layDanhSachCongNhan();
       for (CongNhan congNhan : dsCongNhan) {
           if (!maCongNhan.equalsIgnoreCase("all") || !soCCCD.equalsIgnoreCase("all")
                   || !email.equalsIgnoreCase("all") || !hoTen.equalsIgnoreCase("all")
                   || !soDienThoai.equalsIgnoreCase("all") || !gioiTinh.equalsIgnoreCase("all") || !toNhom.equalsIgnoreCase("all")) {
               boolean flag = true;
               String gioiTinhStr = congNhan.isGioiTinh() ? "Nam" : "Nữ";
               if (!maCongNhan.equalsIgnoreCase("all") && !congNhan.getMaCongNhan().toLowerCase().contains(maCongNhan.toLowerCase())) {
                   flag = false;
               }
               if (!hoTen.equalsIgnoreCase("all") && !congNhan.getHoTen().toLowerCase().contains(hoTen.toLowerCase())) {
                   flag = false;
               }
               if (!soCCCD.equalsIgnoreCase("all") && !congNhan.getMaCCCD().toLowerCase().contains(soCCCD.toLowerCase())) {
                   flag = false;
               }
               if (!email.equalsIgnoreCase("all") && !congNhan.getEmail().toLowerCase().contains(email.toLowerCase())) {
                   flag = false;
               }
               if (!soDienThoai.equalsIgnoreCase("all") && !congNhan.getSoDienThoai().toLowerCase().contains(soDienThoai.toLowerCase())) {
                   flag = false;
               }
               if (!gioiTinh.equalsIgnoreCase("all") && !gioiTinhStr.equalsIgnoreCase(gioiTinh)) {
                   flag = false;
               }
               if (!toNhom.equalsIgnoreCase("all") && !congNhan.getToNhom().getTenToNhom().equalsIgnoreCase(toNhom)) {
                   flag = false;
               }
               if (flag) {
                   String data[] = {(model.getRowCount() + 1) + "", congNhan.getMaCongNhan(), congNhan.getHoTen(), congNhan.getMaCCCD(),
                        congNhan.isGioiTinh() ? "Nam" : "Nữ", congNhan.getNgaySinh().toString(), congNhan.getSoDienThoai(), congNhan.getDiaChi(),
                       congNhan.getAnhDaiDien(), congNhan.getEmail(), congNhan.getToNhom().getTenToNhom(), congNhan.getNgayVaoLam().toString()};
                   model.addRow(data);
               }

           } else {
               String data[] = {(model.getRowCount() + 1) + "", congNhan.getMaCongNhan(), congNhan.getHoTen(), congNhan.getMaCCCD(),
                    congNhan.isGioiTinh() ? "Nam" : "Nữ", congNhan.getNgaySinh().toString(), congNhan.getSoDienThoai(), congNhan.getDiaChi(),
                   congNhan.getAnhDaiDien(), congNhan.getEmail(), congNhan.getToNhom().getTenToNhom(), congNhan.getNgayVaoLam().toString()};
               model.addRow(data);
           }
       }
       if (tblCongNhan.getRowCount() == 0){
           JOptionPane.showMessageDialog(null, stThongBaoTimKiem, stThongbao, JOptionPane.INFORMATION_MESSAGE);
       }
   }

	public void excute() {
		tblCongNhan.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 13));
		tblCongNhan.getTableHeader().setOpaque(false);
		((DefaultTableCellRenderer) tblCongNhan.getTableHeader().getDefaultRenderer())
		.setHorizontalAlignment(JLabel.CENTER);
		tblCongNhan.setRowHeight(25);
	}

	private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {
	}

	private void txtHoTenActionPerformed(java.awt.event.ActionEvent evt) {
	}

	@Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(btnTimKiem)){
            String maCongNhan = txtMaCongNhan.getText().trim();
            String cccd = txtSoCCCD.getText().trim();
            String soDienThoai = txtSoDienThoai.getText().trim();
            String hoTen = txtHoTen.getText().trim();
            String email = txtEmail.getText().trim();
            String gioiTinh = cmbGioiTinh.getSelectedItem().toString();
            String toNhom = cmbToNhom.getSelectedItem().toString();
            if (maCongNhan.equals("")){
                maCongNhan = "all";
            }
            if (cccd.equals("")){
                cccd = "all";
            }
            if (soDienThoai.equals("")){
                soDienThoai = "all";
            }
            if (hoTen.equals("")){
                hoTen = "all";
            }
            if (email.equals("")){
                email = "all";
            }
            if (gioiTinh.equalsIgnoreCase("Tất cả")){
                gioiTinh = "all";
            }
            if (toNhom.equalsIgnoreCase("Tất cả")){
                toNhom = "all";
            }
            taiDuLieuLenBang(maCongNhan, hoTen, cccd, email, soDienThoai, gioiTinh, toNhom);
        }
    }

}
