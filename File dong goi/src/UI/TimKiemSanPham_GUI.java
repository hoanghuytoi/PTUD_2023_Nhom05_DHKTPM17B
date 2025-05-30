package UI;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JTable;

import Custom_UI.ScrollBarCustom;
import Dao.SanPham_Dao;
import Entity.SanPham;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;

public class TimKiemSanPham_GUI extends JPanel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtMaSP;
	private JTextField txtTenSP;
	private JTextField txtSoLuong;
	private JTextField txtChatLieu;
	private JTextField txtKichThuoc;
	private JTextField txtDonGia;
	
	private JScrollPane scrSanPham;
    private JTable tblSanPham;
	private JLabel lblTieuDe;
	private JPanel panelSP;
	private JLabel lblMaSanPham;
	private JLabel lblTenSP;
	private JSeparator jSeparator1;
	private JLabel lblSoLuongSP;
	private JSeparator jSeparator2;
	private JLabel lblErrSoLuong;
	private JLabel lblChatLieu;
	private JSeparator jSeparator4;
	private JLabel lblKichThuoc;
	private JSeparator jSeparator5;
	private JSeparator jSeparator6;
	private JLabel lblDonGia;
	private JSeparator jSeparator;
	private JLabel lblErrDonGia;
	private JLabel lblErrTenSP;
	private JLabel lblErrKichThuoc;
	private JLabel lblErrChatLieu;
	
	private JButton btnXoaRong;
	private JButton btnTimKiem;

	private SanPham_Dao sanPham_DAO;
    private DefaultTableModel modelTableSanPham;
	private JButton btnQuayLai;
	
	private String stThongBao;
    private String stTimKiemKhongThay;
	private TitledBorder titledBorder;
    
	public TimKiemSanPham_GUI(String fileName)throws IOException  {
		initComponents();
        caiDatNgonNgu(fileName);
		modelTableSanPham = (DefaultTableModel) tblSanPham.getModel();
        try {
            ConnectionDB.ConnectDB.getInstance().connect();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        sanPham_DAO = new SanPham_Dao();
        btnXoaRong.addActionListener(this);
        btnTimKiem.addActionListener(this);
        lblErrDonGia.setText("");
        lblErrChatLieu.setText("");
        lblErrKichThuoc.setText("");
        lblErrTenSP.setText("");
        lblErrSoLuong.setText("");

	}

	public void caiDatNgonNgu(String fileName) throws FileNotFoundException, IOException {
        FileInputStream fis = new FileInputStream(fileName);
        Properties prop = new Properties();
        prop.load(fis);
        lblTieuDe.setText(prop.getProperty("sp_TieuDeTimKiem"));
        lblMaSanPham.setText(prop.getProperty("sp_maSanPham"));
        lblTenSP.setText(prop.getProperty("sp_tenSanPham"));
        lblChatLieu.setText(prop.getProperty("sp_chatLieu"));
        lblKichThuoc.setText(prop.getProperty("sp_kichThuoc"));
        lblSoLuongSP.setText(prop.getProperty("sp_soLuong"));
        lblDonGia.setText(prop.getProperty("sp_donGia"));
        scrSanPham.setBorder(new TitledBorder(prop.getProperty("sp_tieuDeTblSanPham")));

        doiNgonNguTable(tblSanPham, 0, prop.getProperty("sp_stt"));
        doiNgonNguTable(tblSanPham, 1, prop.getProperty("sp_maSanPham"));
        doiNgonNguTable(tblSanPham, 2, prop.getProperty("sp_tenSanPham"));
        doiNgonNguTable(tblSanPham, 3, prop.getProperty("sp_soLuong"));
        doiNgonNguTable(tblSanPham, 4, prop.getProperty("sp_chatLieu"));
        doiNgonNguTable(tblSanPham, 5, prop.getProperty("sp_kichThuoc"));
        doiNgonNguTable(tblSanPham, 6, prop.getProperty("sp_anhSanPham"));
        doiNgonNguTable(tblSanPham, 7, prop.getProperty("sp_donGia"));

        
        btnTimKiem.setText(prop.getProperty("btnTimKiem"));
        btnQuayLai.setText(prop.getProperty("btnQuayLai"));
        btnXoaRong.setText(prop.getProperty("btnXoaRong"));
        
        stThongBao = prop.getProperty("thongBao");
        stTimKiemKhongThay=prop.getProperty("timKiem_KhongThay");
        
    }

    public void doiNgonNguTable(JTable table, int col_index, String col_name) {
        table.getColumnModel().getColumn(col_index).setHeaderValue(col_name);
    }
    
	private void initComponents() {
		setSize(1290, 750);
		setBackground(Color.WHITE);
	    setLayout(null);
	    
	    lblTieuDe = new JLabel("TÌM KIẾM SẢN PHẨM");
	    lblTieuDe.setHorizontalAlignment(SwingConstants.CENTER);
	    lblTieuDe.setFont(new Font("Times New Roman", Font.BOLD, 25));
	    lblTieuDe.setBounds(438, 33, 490, 50);
	    add(lblTieuDe);
	    
	    panelSP = new JPanel();
	    panelSP.setLayout(null);
	    panelSP.setBackground(Color.WHITE);
	    panelSP.setBounds(46, 108, 1164, 245);
	    add(panelSP);
	    
	    lblMaSanPham = new JLabel();
	    lblMaSanPham.setText("Mã sản phẩm:");
	    lblMaSanPham.setFont(new Font("Times New Roman", Font.PLAIN, 18));
	    lblMaSanPham.setBounds(81, 35, 125, 30);
	    panelSP.add(lblMaSanPham);
	    
	    txtMaSP = new JTextField();
	    txtMaSP.setFont(new Font("Times New Roman", Font.PLAIN, 16));
	    txtMaSP.setBorder(null);
	    txtMaSP.setBounds(216, 21, 318, 36);
	    panelSP.add(txtMaSP);
	    
	    jSeparator = new JSeparator();
	    jSeparator.setForeground(Color.BLACK);
	    jSeparator.setBounds(216, 57, 318, 10);
	    panelSP.add(jSeparator);
	    
	    lblTenSP = new JLabel();
	    lblTenSP.setText("Tên sản phẩm:");
	    lblTenSP.setFont(new Font("Times New Roman", Font.PLAIN, 18));
	    lblTenSP.setBounds(81, 100, 125, 30);
	    panelSP.add(lblTenSP);
	    
	    txtTenSP = new JTextField();
	    txtTenSP.setFont(new Font("Times New Roman", Font.PLAIN, 16));
	    txtTenSP.setBorder(null);
	    txtTenSP.setBounds(216, 85, 318, 36);
	    panelSP.add(txtTenSP);
	    
	    jSeparator1 = new JSeparator();
	    jSeparator1.setForeground(Color.BLACK);
	    jSeparator1.setBounds(216, 122, 318, 10);
	    panelSP.add(jSeparator1);
	    
	    lblSoLuongSP = new JLabel();
	    lblSoLuongSP.setText("Số lượng:");
	    lblSoLuongSP.setFont(new Font("Times New Roman", Font.PLAIN, 18));
	    lblSoLuongSP.setBounds(81, 160, 125, 30);
	    panelSP.add(lblSoLuongSP);
	    
	    txtSoLuong = new JTextField();
	    txtSoLuong.setFont(new Font("Segoe UI", Font.PLAIN, 16));
	    txtSoLuong.setBorder(null);
	    txtSoLuong.setBounds(216, 149, 318, 30);
	    panelSP.add(txtSoLuong);
	    
	    jSeparator2 = new JSeparator();
	    jSeparator2.setForeground(Color.BLACK);
	    jSeparator2.setBounds(216, 180, 318, 10);
	    panelSP.add(jSeparator2);
	    
	    lblErrSoLuong = new JLabel();
	    lblErrSoLuong.setText("thông báo lỗi");
	    lblErrSoLuong.setForeground(new Color(204, 0, 0));
	    lblErrSoLuong.setFont(new Font("Times New Roman", Font.PLAIN, 13));
	    lblErrSoLuong.setBounds(216, 184, 325, 18);
	    panelSP.add(lblErrSoLuong);
	    
	    lblChatLieu = new JLabel();
	    lblChatLieu.setText("Chất liệu:");
	    lblChatLieu.setFont(new Font("Times New Roman", Font.PLAIN, 18));
	    lblChatLieu.setBounds(624, 35, 91, 30);
	    panelSP.add(lblChatLieu);
	    
	    txtChatLieu = new JTextField();
	    txtChatLieu.setFont(new Font("Times New Roman", Font.PLAIN, 16));
	    txtChatLieu.setBorder(null);
	    txtChatLieu.setBounds(746, 21, 341, 30);
	    panelSP.add(txtChatLieu);
	    
	    jSeparator4 = new JSeparator();
	    jSeparator4.setForeground(Color.BLACK);
	    jSeparator4.setBounds(746, 55, 341, 10);
	    panelSP.add(jSeparator4);
	    
	    lblKichThuoc = new JLabel();
	    lblKichThuoc.setText("Kích thước:");
	    lblKichThuoc.setFont(new Font("Times New Roman", Font.PLAIN, 18));
	    lblKichThuoc.setBounds(624, 97, 91, 30);
	    panelSP.add(lblKichThuoc);
	    
	    txtKichThuoc = new JTextField();
	    txtKichThuoc.setFont(new Font("Times New Roman", Font.PLAIN, 16));
	    txtKichThuoc.setBorder(null);
	    txtKichThuoc.setBounds(746, 91, 341, 30);
	    panelSP.add(txtKichThuoc);
	    
	    jSeparator5 = new JSeparator();
	    jSeparator5.setForeground(Color.BLACK);
	    jSeparator5.setBounds(746, 122, 341, 10);
	    panelSP.add(jSeparator5);
	    
	    jSeparator6 = new JSeparator();
	    jSeparator6.setForeground(Color.BLACK);
	    jSeparator6.setBounds(745, 180, 342, 10);
	    panelSP.add(jSeparator6);
	    
	    lblDonGia = new JLabel();
	    lblDonGia.setText("Đơn giá:");
	    lblDonGia.setFont(new Font("Times New Roman", Font.PLAIN, 18));
	    lblDonGia.setBounds(624, 160, 125, 30);
	    panelSP.add(lblDonGia);
	    
	    txtDonGia = new JTextField();
	    txtDonGia.setFont(new Font("Segoe UI", Font.PLAIN, 16));
	    txtDonGia.setBorder(null);
	    txtDonGia.setBounds(746, 149, 318, 30);
	    panelSP.add(txtDonGia);
	    
	    lblErrDonGia = new JLabel();
	    lblErrDonGia.setText("thông báo lỗi");
	    lblErrDonGia.setForeground(new Color(204, 0, 0));
	    lblErrDonGia.setFont(new Font("Times New Roman", Font.PLAIN, 13));
	    lblErrDonGia.setBounds(216, 252, 325, 18);
	    panelSP.add(lblErrDonGia);
	    
	    lblErrTenSP = new JLabel();
	    lblErrTenSP.setText("lblErrTenSP");
	    lblErrTenSP.setForeground(new Color(204, 0, 0));
	    lblErrTenSP.setFont(new Font("Times New Roman", Font.PLAIN, 13));
	    lblErrTenSP.setBounds(216, 122, 325, 17);
	    panelSP.add(lblErrTenSP);
	    
	    lblErrKichThuoc = new JLabel();
	    lblErrKichThuoc.setText("thông báo lỗi");
	    lblErrKichThuoc.setForeground(new Color(204, 0, 0));
	    lblErrKichThuoc.setFont(new Font("Times New Roman", Font.PLAIN, 13));
	    lblErrKichThuoc.setBounds(746, 122, 284, 24);
	    panelSP.add(lblErrKichThuoc);
	    
	    lblErrChatLieu = new JLabel();
	    lblErrChatLieu.setText("thông báo lỗi");
	    lblErrChatLieu.setForeground(new Color(204, 0, 0));
	    lblErrChatLieu.setFont(new Font("Times New Roman", Font.PLAIN, 13));
	    lblErrChatLieu.setBounds(746, 57, 284, 18);
	    panelSP.add(lblErrChatLieu);	    
 
	    btnXoaRong = new JButton();
	    btnXoaRong.setIcon(new ImageIcon(TimKiemSanPham_GUI.class.getResource("/image/icon/xoaRong.png")));
	    btnXoaRong.setText("Xóa rỗng");
	    btnXoaRong.setFont(new Font("Times New Roman", Font.PLAIN, 18));
	    btnXoaRong.setBorder(null);
	    btnXoaRong.setBackground(new Color(255, 192, 203));
	    btnXoaRong.setBounds(245, 364, 201, 40);
	    btnXoaRong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaRongActionPerformed(evt);
            }
        });
	    add(btnXoaRong);
	    
	    btnTimKiem = new JButton();
	    btnTimKiem.setIcon(new ImageIcon(TimKiemSanPham_GUI.class.getResource("/image/icon/timKiem.png")));
	    btnTimKiem.setText("Tìm kiếm");
	    btnTimKiem.setFont(new Font("Times New Roman", Font.PLAIN, 18));
	    btnTimKiem.setBorder(null);
	    btnTimKiem.setBackground(new Color(255, 215, 0));
	    btnTimKiem.setBounds(535, 364, 201, 40);
	    btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });
	    add(btnTimKiem);
	    
	    btnQuayLai = new JButton();
        btnQuayLai.setIcon(new ImageIcon(TimKiemSanPham_GUI.class.getResource("/image/icon/reset.png")));
        btnQuayLai.setText("Quay lại");
        btnQuayLai.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        btnQuayLai.setBorder(null);
        btnQuayLai.setBackground(new Color(255, 192, 203));
        btnQuayLai.setBounds(842, 364, 172, 40);
        add(btnQuayLai);
        btnQuayLai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuayLaiActionPerformed(evt);
            }
        });
	    
	    tblSanPham = new JTable();
        tblSanPham.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        tblSanPham.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 18));

	    tblSanPham.setModel(new DefaultTableModel(
	    	new Object[][] {
	    		{null, null, null, null, null, null, null, null},
	    		{null, null, null, null, null, null, null, null},
	    		{null, null, null, null, null, null, null, null},
	    		{null, null, null, null, null, null, null, null},
	    		{null, null, null, null, null, null, null, null},
	    		{null, null, null, null, null, null, null, null},
	    		{null, null, null, null, null, null, null, null},
	    		{null, null, null, null, null, null, null, null},
	    	},
	    	new String[] {
	    		"STT", "M\u00E3 s\u1EA3n ph\u1EA9m", "T\u00EAn s\u1EA3n ph\u1EA9m", "S\u1ED1 l\u01B0\u1EE3ng", "Ch\u1EA5t li\u1EC7u", "K\u00EDch th\u01B0\u1EDBc", "Ảnh sản phẩm", "\u0110\u01A1n gi\u00E1"
	    	}
	    ));
	    tblSanPham.getTableHeader().setBackground(new Color(128, 200, 255));
	    tblSanPham.setRowHeight(28);
	    tblSanPham.setSelectionBackground(new Color(255, 215, 0));
	    tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				tblSanPhamMouseClicked(evt);
			}
		});
	    
	    scrSanPham = new JScrollPane(tblSanPham);
	    scrSanPham.setBounds(46, 443, 1225, 252);
	    scrSanPham.setBackground(new java.awt.Color(255, 255, 255));
	    LineBorder blackLineBorder = new LineBorder(Color.BLACK, 2);
		titledBorder = BorderFactory.createTitledBorder(blackLineBorder, "Danh sách sản phẩm");
		scrSanPham.setBorder(titledBorder);
	   
        ScrollBarCustom scrollBar = new ScrollBarCustom();
        scrollBar.setForeground(Color.RED);
        scrSanPham.setVerticalScrollBar(scrollBar);
        add(scrSanPham);
        
	}

	public void taiDuLieuLenBang(String ma, String ten, String kichThuoc, String chatLieu, String soLuong, String donGia) {
	    while (tblSanPham.getRowCount() != 0) {
	        modelTableSanPham.removeRow(0);
	    }
	    ArrayList<SanPham> dsSanPham = sanPham_DAO.layDanhSachSanPham();

	    for (SanPham sanPham : dsSanPham) {
	        boolean flag = true;

	        // Kiểm tra các tiêu chí tìm kiếm
	        if (!ma.equalsIgnoreCase("all") && !sanPham.getMaSanPham().toLowerCase().contains(ma.toLowerCase())) {
	            flag = false;
	        }
	        if (!ten.equalsIgnoreCase("all") && !sanPham.getTenSanPham().toLowerCase().contains(ten.toLowerCase())) {
	            flag = false;
	        }
	        if (!chatLieu.equalsIgnoreCase("all") && !sanPham.getChatLieu().toLowerCase().contains(chatLieu.toLowerCase())) {
	            flag = false;
	        }
	        if (!kichThuoc.equalsIgnoreCase("all") && !String.valueOf(sanPham.getKichThuoc()).toLowerCase().contains(kichThuoc.toLowerCase())) {
	            flag = false;
	        }
	        if (!soLuong.equalsIgnoreCase("all") && !String.valueOf(sanPham.getSoLuongSanPham()).toLowerCase().contains(soLuong.toLowerCase())) {
	            flag = false;
	        }
	        if (!donGia.equalsIgnoreCase("all") && !String.valueOf(sanPham.getDonGia()).toLowerCase().contains(donGia.toLowerCase())) {
	            flag = false;
	        }

	        if (flag) {
	            String data[] = {(modelTableSanPham.getRowCount() + 1) + "", sanPham.getMaSanPham(), sanPham.getTenSanPham(), String.valueOf(sanPham.getSoLuongSanPham()), sanPham.getChatLieu(), String.valueOf(sanPham.getKichThuoc()), sanPham.getAnhSanPham(), String.valueOf(sanPham.getDonGia())};
	            modelTableSanPham.addRow(data);
	        }
	    }

	    if (tblSanPham.getRowCount() == 0) {
	        JOptionPane.showMessageDialog(null, stTimKiemKhongThay, stThongBao, JOptionPane.INFORMATION_MESSAGE);
	    }

	    if (tblSanPham.getRowCount() != 0) {
	        tblSanPham.setRowSelectionInterval(0, 0);
	    }
	}


	private void btnQuayLaiActionPerformed(ActionEvent evt) {
	    taiDuLieuLenBang("all", "all", "all", "all", "all", "all");
	}
	
	private void tblSanPhamMouseClicked(MouseEvent evt) {
		// TODO Auto-generated method stub
		
	}

	private void btnTimKiemActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		
	}

	private void btnXoaRongActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		txtChatLieu.setText("");
		txtDonGia.setText("");
		txtKichThuoc.setText("");
		txtMaSP.setText("");
		txtSoLuong.setText("");
		txtTenSP.setText("");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
        if (o.equals(btnTimKiem)) {
            String maSanPham = txtMaSP.getText().trim();
            String tenSanPham = txtTenSP.getText().trim();
            String chatLieu = txtChatLieu.getText().trim();
            String kichThuoc = txtKichThuoc.getText();
            String soLuong = txtSoLuong.getText();
            String donGia = txtDonGia.getText();
            
            if (maSanPham.trim().equalsIgnoreCase("")) {
                maSanPham = "all";
            }
            if (tenSanPham.equalsIgnoreCase("")) {
                tenSanPham = "all";
            }
            if (chatLieu.equalsIgnoreCase("")) {
                chatLieu = "all";
            }
            if (kichThuoc.equalsIgnoreCase("")){
                kichThuoc = "all";
            }
            if (soLuong.equalsIgnoreCase("")) {
            	soLuong = "all";
            }
            if (donGia.equalsIgnoreCase("")){
            	donGia = "all";
            }
            taiDuLieuLenBang(maSanPham, tenSanPham, kichThuoc, chatLieu,soLuong,donGia);
        }
	}
}
