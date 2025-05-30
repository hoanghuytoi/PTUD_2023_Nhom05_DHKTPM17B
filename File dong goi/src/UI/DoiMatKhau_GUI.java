package UI;

import Dao.NhanVien_Dao;
import Dao.CongNhan_Dao;
import Entity.NhanVien;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;


public class DoiMatKhau_GUI extends JPanel  implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtTenDangNhap;
	private JPasswordField txtMatKhau;
	private JPasswordField txtMatKhauMoi;
	private JPasswordField txtXacNhanMKMoi;
	private NhanVien_Dao nhanVien_Dao;
	private boolean isNhanVien;
	private String userName;
	private JCheckBox ckb1;
	private JCheckBox ckb2;
	private JCheckBox ckb3;
	private JLabel lbl_ErrMatKhauHT;
	private JLabel lbl_ErrMatKhauMoi;
	private JLabel lbl_ErrXacNhanMK;
	private JButton btnDongY;
	private JButton btnThoat;
	private JSeparator jSeparator1_1;
	private JSeparator jSeparator1_2;
	private JSeparator jSeparator1_3;
	private JLabel lblTieuDe;
	private JLabel lblTenDangNhap;
	private JLabel lblMatKhau;
	private JLabel lblMatKhauMoi;
	private JLabel lblXacNhanMK;
	
	private String stErrKhongDeTrong;
    private String stErrMatKhau;
	
	public DoiMatKhau_GUI(String fileName,String userName) throws IOException {
		setBackground(new Color(255, 255, 255));
		initComponents();
		this.userName = userName;		
		caiDatNgonNgu(fileName);
		try {
            ConnectionDB.ConnectDB.getInstance().connect();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        new CongNhan_Dao();
        nhanVien_Dao = new NhanVien_Dao();
        String loai = userName.substring(0, 4);
        if (loai.equals("PPNV")) {
            isNhanVien = true;
        } else {
            isNhanVien = false;
        }
        taiDuLieuLenTrang();
	}
	
	public void caiDatNgonNgu(String fileName) throws FileNotFoundException, IOException {
        FileInputStream fis = new FileInputStream(fileName);
        Properties prop = new Properties();
        prop.load(fis);

        btnDongY.setText(prop.getProperty("btnDongY"));
        btnThoat.setText(prop.getProperty("btnThoat"));

        lblTenDangNhap.setText(prop.getProperty("qlttcn_tenDangNhap"));
        lblMatKhau.setText(prop.getProperty("qlttcn_matKhau"));
        lblTieuDe.setText(prop.getProperty("dmk_TieuDe"));
        lblMatKhauMoi.setText(prop.getProperty("dmk_matKhau"));
        lblXacNhanMK.setText(prop.getProperty("dmk_xnmatKhau"));
        
        stErrMatKhau=prop.getProperty("qlttcn_errMatKhau");
        prop.getProperty("thongBao");
        prop.getProperty("capNhatThanhCong");
        prop.getProperty("capNhatThatBai");
        stErrKhongDeTrong = prop.getProperty("KhongDeTrong");
    }
	
	public void taiDuLieuLenTrang() {
        if (isNhanVien) {
            NhanVien nhanVien = nhanVien_Dao.layMotNhanVienTheoMaNhanVien(userName);
            txtTenDangNhap.setText(nhanVien.getMaNhanVien());
            txtMatKhau.setText(nhanVien.getMatKhau());   
        } 
    }
	
	private void initComponents() {
		setSize(1290, 750);
	    setLayout(null);
	    
	    lblTieuDe = new JLabel("ĐỔI MẬT KHẨU");
	    lblTieuDe.setHorizontalAlignment(SwingConstants.CENTER);
	    lblTieuDe.setForeground(new Color(0, 128, 0));
	    lblTieuDe.setFont(new Font("Tahoma", Font.BOLD, 30));
	    lblTieuDe.setBounds(10, 0, 1416, 90);
	    add(lblTieuDe);
	    
	    ckb1 = new JCheckBox("");
		ckb1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ckb1.isSelected())
				{
					txtMatKhau.setEchoChar((char)0);
				}else {
					txtMatKhau.setEchoChar('\u2022');
				}
			}
		});
		ckb1.setBackground(new Color(255, 255, 255));
		ckb1.setActionCommand("");
		ckb1.setIcon(new ImageIcon(DoiMatKhau_GUI.class.getResource("/image/icon/disableeyeBlue.png")));
		ckb1.setBounds(665, 292, 35, 40);
		add(ckb1);
		
		ckb2 = new JCheckBox("");
		ckb2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ckb2.isSelected())
				{
					txtMatKhauMoi.setEchoChar((char)0);
				}else {
					txtMatKhauMoi.setEchoChar('\u2022');
				}
			}
		});
		ckb2.setIcon(new ImageIcon(DoiMatKhau_GUI.class.getResource("/image/icon/disableeyeBlue.png")));
		ckb2.setBackground(Color.WHITE);
		ckb2.setActionCommand("");
		ckb2.setBounds(665, 393, 35, 40);
		add(ckb2);
		
		ckb3 = new JCheckBox("");
		ckb3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ckb3.isSelected())
				{
					txtXacNhanMKMoi.setEchoChar((char)0);
				}else {
					txtXacNhanMKMoi.setEchoChar('\u2022');
				}
			}
		});
		ckb3.setIcon(new ImageIcon(DoiMatKhau_GUI.class.getResource("/image/icon/disableeyeBlue.png")));
		ckb3.setBackground(Color.WHITE);
		ckb3.setActionCommand("");
		ckb3.setBounds(665, 489, 35, 40);
		add(ckb3);
		
		jSeparator1_1 = new JSeparator();
		jSeparator1_1.setForeground(Color.BLACK);
		jSeparator1_1.setBounds(365, 322, 250, 10);
		add(jSeparator1_1);
		
		jSeparator1_2 = new JSeparator();
		jSeparator1_2.setForeground(Color.BLACK);
		jSeparator1_2.setBounds(365, 424, 250, 10);
		add(jSeparator1_2);
		
		jSeparator1_3 = new JSeparator();
		jSeparator1_3.setForeground(Color.BLACK);
		jSeparator1_3.setBounds(365, 519, 250, 10);
		add(jSeparator1_3);
		
	    lblTenDangNhap = new JLabel("Tên đăng nhập:\r\n");
	    lblTenDangNhap.setFont(new Font("Tahoma", Font.BOLD, 16));
	    lblTenDangNhap.setBounds(144, 190, 176, 40);
	    add(lblTenDangNhap);
	    
	    txtTenDangNhap = new JTextField();
	    txtTenDangNhap.setForeground(new Color(0, 0, 0));
	    txtTenDangNhap.setBackground(new Color(255, 255, 255));
	    txtTenDangNhap.setFont(new Font("Times New Roman", Font.PLAIN, 18));
	    txtTenDangNhap.setEnabled(false);
	    txtTenDangNhap.setBounds(365, 177, 250, 40);
	    add(txtTenDangNhap);
	    
	    lblMatKhau = new JLabel("Mật khẩu hiện tại:");
	    lblMatKhau.setFont(new Font("Tahoma", Font.BOLD, 16));
	    lblMatKhau.setBounds(144, 300, 182, 32);
	    add(lblMatKhau);
	    
	    txtMatKhau = new JPasswordField();
	    txtMatKhau.setFont(new Font("Tahoma", Font.BOLD, 15));
	    txtMatKhau.setForeground(new Color(0, 0, 0));
	    txtMatKhau.setBorder(null);
	    txtMatKhau.setBounds(365, 277, 250, 40);
	    add(txtMatKhau);
	    
	    lblMatKhauMoi = new JLabel("Mật khẩu mới:");
	    lblMatKhauMoi.setFont(new Font("Tahoma", Font.BOLD, 16));
	    lblMatKhauMoi.setBounds(144, 402, 182, 32);
	    add(lblMatKhauMoi);
	    
	    lblXacNhanMK = new JLabel("Xác nhận mật khẩu mới:");
	    lblXacNhanMK.setFont(new Font("Tahoma", Font.BOLD, 16));
	    lblXacNhanMK.setBounds(144, 497, 211, 32);
	    add(lblXacNhanMK);
	    
	    txtMatKhauMoi = new JPasswordField();
	    txtMatKhauMoi.setFont(new Font("Tahoma", Font.BOLD, 16));
	    txtMatKhauMoi.setBorder(null);
	    txtMatKhauMoi.setBounds(365, 378, 250, 40);
	    add(txtMatKhauMoi);
	    
	    txtXacNhanMKMoi = new JPasswordField();
	    txtXacNhanMKMoi.setFont(new Font("Tahoma", Font.BOLD, 16));
	    txtXacNhanMKMoi.setBorder(null);
	    txtXacNhanMKMoi.setBounds(365, 475, 250, 40);
	    add(txtXacNhanMKMoi);
	    
	    btnDongY = new JButton("Đồng ý");
	  
	    btnDongY.setIcon(new ImageIcon(DoiMatKhau_GUI.class.getResource("/image/icon/luu.png")));
	    btnDongY.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
	    btnDongY.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
	    btnDongY.setBackground(new Color(0, 255, 128));
	    btnDongY.setBounds(838, 250, 310, 40);
	    add(btnDongY);
	    btnDongY.addActionListener(this);
	    
	    btnThoat = new JButton("Thoát");
	    btnThoat.setIcon(new ImageIcon(DoiMatKhau_GUI.class.getResource("/image/icon/dangxuat.png")));
	    btnThoat.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
	    btnThoat.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
	    btnThoat.setBackground(new Color(0, 255, 128));
	    btnThoat.setBounds(838, 344, 310, 40);
	    add(btnThoat);
	    btnThoat.addActionListener(this);
	    
	    lbl_ErrMatKhauHT = new JLabel();
	    lbl_ErrMatKhauHT.setForeground(new Color(255, 0, 0));
	    lbl_ErrMatKhauHT.setFont(new Font("Tahoma", Font.BOLD, 14));
	    lbl_ErrMatKhauHT.setBounds(365, 328, 250, 20);
	    add(lbl_ErrMatKhauHT);
	    
	    lbl_ErrMatKhauMoi = new JLabel();
	    lbl_ErrMatKhauMoi.setForeground(new Color(255, 0, 0));
	    lbl_ErrMatKhauMoi.setFont(new Font("Tahoma", Font.BOLD, 14));
	    lbl_ErrMatKhauMoi.setBounds(365, 429, 250, 20);
	    add(lbl_ErrMatKhauMoi);
	    
	    lbl_ErrXacNhanMK = new JLabel();
	    lbl_ErrXacNhanMK.setForeground(new Color(255, 0, 0));
	    lbl_ErrXacNhanMK.setFont(new Font("Tahoma", Font.BOLD, 14));
	    lbl_ErrXacNhanMK.setBounds(365, 527, 250, 20);
	    add(lbl_ErrXacNhanMK);
	    
	    JSeparator jSeparator1 = new JSeparator();
	    jSeparator1.setBounds(-345, 92, 1920, 20);
	    add(jSeparator1);
	
	}

	public boolean validateForm() {
	    Boolean flag = true;
	    String matKhau = new String(txtMatKhau.getPassword());
	    String matKhauMoi = new String(txtMatKhauMoi.getPassword());
	    String xacNhanMatKhauMoi = new String(txtXacNhanMKMoi.getPassword());

	    // Kiểm tra mật khẩu hiện tại
	    NhanVien nhanVienOld = nhanVien_Dao.layMotNhanVienTheoMaNhanVien(userName);
	    if (matKhau.equals("")) {
	    	lbl_ErrMatKhauHT.setText(stErrKhongDeTrong);
            flag = false;
        } else if (matKhau.length() < 6) {
        	lbl_ErrMatKhauHT.setText(stErrMatKhau);
            flag = false;
        } else if (!matKhau.equals(nhanVienOld.getMatKhau())) {
	        lbl_ErrMatKhauHT.setText("Mật khẩu hiện tại không đúng");
	        flag = false;
	    }
        else {
        	lbl_ErrMatKhauHT.setText("_______________________");
        }

	    // Kiểm tra mật khẩu mới
	    if (matKhauMoi.equals("")) {
	        lbl_ErrMatKhauMoi.setText(stErrKhongDeTrong);
	        flag = false;
	    } else if (matKhauMoi.length() < 6) {
	        lbl_ErrMatKhauMoi.setText(stErrMatKhau);
	        flag = false;
	    } else {
	        lbl_ErrMatKhauMoi.setText("_______________________");
	    }

	    // Kiểm tra xác nhận mật khẩu mới
	    if (xacNhanMatKhauMoi.equals("")) {
	        lbl_ErrXacNhanMK.setText(stErrKhongDeTrong);
	        flag = false;
	    } else if (xacNhanMatKhauMoi.length() < 6) {
	        lbl_ErrXacNhanMK.setText(stErrMatKhau);
	        flag = false;
	    } else if (!matKhauMoi.equalsIgnoreCase(xacNhanMatKhauMoi)) {
	        JOptionPane.showMessageDialog(this, "Mật khẩu mới không khớp");
	        flag = false;
	    } else {
	        lbl_ErrXacNhanMK.setText("_______________________");
	    }
	    return flag;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
	    Object o = e.getSource();
	    if (o.equals(btnDongY)) {
	        boolean isValid = validateForm();
	        if (!isValid) {
	            return;
	        }
	        if (isNhanVien) {
	            NhanVien nhanVienOld = nhanVien_Dao.layMotNhanVienTheoMaNhanVien(userName);
	            
	            // Thiết lập mật khẩu mới cho đối tượng NhanVien
	            try {
					nhanVienOld.setMatKhau(new String(txtMatKhauMoi.getPassword()));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	            
	            boolean coSuaDuoc = nhanVien_Dao.suaThongTinMotNhanVien(nhanVienOld);
	            if (coSuaDuoc) {
	                JOptionPane.showMessageDialog(null, "Đổi mật khẩu thành công", "Thông Báo", JOptionPane.INFORMATION_MESSAGE);
	            } else {
	                JOptionPane.showMessageDialog(null, "Đổi mật khẩu thất bại", "Thông Báo", JOptionPane.INFORMATION_MESSAGE);
	            }
	        }
	    }
	    if(o.equals(btnThoat))
	    {
	    	int tb = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn thoát !!!","Thông báo",JOptionPane.YES_NO_OPTION);
	    	if(tb == JOptionPane.YES_OPTION)
	    	{
	    		System.exit(0);
	    	}
	    }
	}
}
