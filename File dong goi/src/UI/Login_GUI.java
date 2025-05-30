package UI;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Dao.CongNhan_Dao;
import Dao.NhanVien_Dao;
import Entity.NhanVien;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import java.awt.Dimension;
import javax.swing.JSeparator;

public class Login_GUI extends JFrame {	
	/**
	 * Hoàng Huy Tới
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnDangNhap;
    private JCheckBox chkRemerberPassword;
    private JComboBox<String> cmbNgonNgu;
    private JLabel disable;
    private JLabel lblHello;
    private JPanel panelTieuDe;
    private JPanel panelThongTin;
    private JLabel imageUser;
    private JLabel imageExit;
    private JLabel lblLogo;
    private JLabel lblMatKhau;
    private JLabel lblNgonNgu;
    private JLabel lblQuenMatKhau;
    private JLabel lblTenDangNhap;
    private JLabel lblTieuDe;
    private JLabel lblTieuDeDangNhap;
    
    private JLabel show;
    private JTextField txtDangNhap;
    private JPasswordField txtMatKhau;
    
    private NhanVien_Dao nhanVien_DAO;
    private ArrayList<String> ngonNguList;
	private JSeparator jSeparator1;
	private JSeparator jSeparator1_1;

	public Login_GUI() {
		ngonNguList = new ArrayList<>();
        try {
            ConnectionDB.ConnectDB.getInstance().connect();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        new CongNhan_Dao();
        nhanVien_DAO = new NhanVien_Dao();
        File file = new File("config/VietNam.properties");
        File file1 = new File("config/English.properties");
        ngonNguList.add(file.getAbsolutePath());
        ngonNguList.add(file1.getAbsolutePath());
        initComponents();
        txtDangNhap.setText("PPNV100001");
        txtMatKhau.setText("111111");
        
        if (pref.get("userName", "").equals("") || pref.get("userName", "") == null) {
            txtDangNhap.setText("PPNV100001");
            NhanVien nhanVienDangNhap = nhanVien_DAO.layMotNhanVienTheoMaNhanVien(txtDangNhap.getText());
            if (nhanVienDangNhap != null) {
                txtMatKhau.setText(nhanVienDangNhap.getMatKhau());
            } else {
                JOptionPane.showMessageDialog(null, "Không tìm thấy nhân viên!", "Thông Báo Đăng nhập", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            getDataRemember();
        }
	}
    
	public void caiDatNgonNgu(String fileName) throws FileNotFoundException, IOException {
        FileInputStream fis = new FileInputStream(fileName);
        Properties prop = new Properties();
        prop.load(fis);
        lblTieuDe.setText(prop.getProperty("Login_lblTieuDe"));
        lblTieuDeDangNhap.setText(prop.getProperty("Login_lblTieuDeDangNhap"));
        lblTenDangNhap.setText(prop.getProperty("Login_lblTenDangNhap"));
        lblMatKhau.setText(prop.getProperty("Login_lblMatKhau"));
        chkRemerberPassword.setText(prop.getProperty("Login_lblNhoMatKhau"));
        lblQuenMatKhau.setText(prop.getProperty("Login_lblQuenMatKhau"));
        btnDangNhap.setText(prop.getProperty("Login_btnDangNhap"));
        lblNgonNgu.setText(prop.getProperty("Login_lblNgonNgu"));
        lblHello.setText((prop.getProperty("Login_lblHello")));
        
    }
	
	public void getDataRemember() {
        String userName = "";
        userName = pref.get("userName", userName);
        String passWord = "";
        passWord = pref.get("password", passWord);
        this.txtDangNhap.setText(userName);
        this.txtMatKhau.setText(passWord);
    }
	
    private void initComponents() {
        panelTieuDe = new JPanel();
        panelTieuDe.setBounds(0, 0, 510, 500);
        
        lblLogo = new JLabel();
        lblLogo.setBounds(-27, 54, 634, 446);
        lblLogo.setIcon(new ImageIcon(Login_GUI.class.getResource("/image/icon/login.PNG")));
        
        lblTieuDe = new JLabel();
        lblTieuDe.setBounds(0, 0, 510, 60);
        
        panelThongTin = new javax.swing.JPanel();
        panelThongTin.setBounds(510, 0, 525, 500);
        lblTieuDeDangNhap = new JLabel();
        lblTieuDeDangNhap.setBounds(129, 16, 286, 43);
        
        lblHello = new JLabel();
        lblHello.setBounds(162, 70, 238, 18);
        
        imageExit = new JLabel();
        imageExit.setBounds(475, 0, 50, 53);
        
        lblTenDangNhap = new JLabel();
        lblTenDangNhap.setBounds(60, 118, 360, 36);
        
        imageUser = new JLabel();
        imageUser.setBounds(450, 159, 20, 36);
        
        lblMatKhau = new JLabel();
        lblMatKhau.setBounds(60, 220, 360, 24);
        
        disable = new JLabel();
        disable.setBounds(450, 264, 20, 20);
        
        show = new JLabel();
        show.setBounds(450, 264, 31, 20);
        
        chkRemerberPassword = new JCheckBox();
        chkRemerberPassword.setBounds(60, 320, 165, 27);
        chkRemerberPassword.setBackground(new Color(255, 182, 193));
        
        lblNgonNgu = new JLabel();
        lblNgonNgu.setBounds(70, 450, 170, 18);
        
        btnDangNhap = new JButton();
        btnDangNhap.setBounds(60, 370, 410, 47);
        
        btnDangNhap.setBackground(new Color(255, 140, 0));
        
        lblQuenMatKhau = new JLabel();
        lblQuenMatKhau.setBounds(364, 323, 136, 18);
        
        cmbNgonNgu = new JComboBox<>();
        cmbNgonNgu.setBounds(280, 439, 190, 40);
        cmbNgonNgu.setFont(new Font("Times New Roman", Font.BOLD, 15));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1000, 500));
        getContentPane().setLayout(null);

        panelTieuDe.setBackground(new Color(255, 228, 225));
        panelTieuDe.setPreferredSize(new java.awt.Dimension(500, 500));
        panelTieuDe.setLayout(null);
        lblLogo.setPreferredSize(new java.awt.Dimension(400, 400));
        panelTieuDe.add(lblLogo);
        lblTieuDe.setFont(new Font("Times New Roman", Font.BOLD, 18));
        lblTieuDe.setForeground(new Color(0, 0, 0));
        lblTieuDe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTieuDe.setText("PHẦN MỀM QUẢN LÝ LƯƠNG SẢN PHẨM - PACEPRO");
        panelTieuDe.add(lblTieuDe);

        getContentPane().add(panelTieuDe);

        panelThongTin.setBackground(new Color(255, 182, 193));
        panelThongTin.setLayout(null);

        lblTieuDeDangNhap.setFont(new Font("Times New Roman", Font.BOLD, 36));
        lblTieuDeDangNhap.setForeground(new Color(0, 0, 0));
        lblTieuDeDangNhap.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTieuDeDangNhap.setText("ĐĂNG NHẬP");
        lblTieuDeDangNhap.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        panelThongTin.add(lblTieuDeDangNhap);

        lblHello.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        lblHello.setForeground(new Color(0, 0, 0));
        lblHello.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHello.setText("Chào mừng đến với hệ thống");
        panelThongTin.add(lblHello);

        imageExit.setIcon(new ImageIcon(getClass().getResource("/image/icon/close.png")));
        imageExit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        imageExit.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                lblExitMouseClicked(evt);
            }
        });
        panelThongTin.add(imageExit);

        lblTenDangNhap.setFont(new Font("Times New Roman", Font.BOLD, 23));
        lblTenDangNhap.setForeground(new Color(0, 0, 0));
        lblTenDangNhap.setText("Tên đăng nhập");
        panelThongTin.add(lblTenDangNhap);

        imageUser.setForeground(new Color(255, 255, 255));
        imageUser.setIcon(new ImageIcon(getClass().getResource("/image/icon/iconUser.png")));
        imageUser.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelThongTin.add(imageUser);

        lblMatKhau.setFont(new Font("Times New Roman", Font.BOLD, 23));
        lblMatKhau.setForeground(new Color(0, 0, 0));
        lblMatKhau.setText("Mật khẩu");
        panelThongTin.add(lblMatKhau);
        
        jSeparator1 = new JSeparator();
        jSeparator1.setBackground(new Color(0, 0, 0));
        jSeparator1.setForeground(Color.BLACK);
        jSeparator1.setBounds(60, 195, 355, 10);
        panelThongTin.add(jSeparator1);
        
        jSeparator1_1 = new JSeparator();
        jSeparator1_1.setBackground(new Color(0, 0, 0));
        jSeparator1_1.setForeground(Color.BLACK);
        jSeparator1_1.setBounds(60, 286, 355, 10);
        panelThongTin.add(jSeparator1_1);

        disable.setIcon(new ImageIcon(getClass().getResource("/image/icon/iconNoEyes.png")));
        disable.setCursor(new Cursor(Cursor.HAND_CURSOR));
        disable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                disableMouseClicked(evt);
            }
        });
        panelThongTin.add(disable);

        show.setIcon(new ImageIcon(getClass().getResource("/image/icon/iconEyes.png")));
        show.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        show.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                showMouseClicked(evt);
            }
        });
        panelThongTin.add(show);

        chkRemerberPassword.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
        chkRemerberPassword.setText("Nhớ mật khẩu?");
        chkRemerberPassword.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        chkRemerberPassword.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                chkRemerberPasswordMouseClicked(evt);
            }
        });
        chkRemerberPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkRemerberPasswordActionPerformed(evt);
            }
        });
        panelThongTin.add(chkRemerberPassword);

        lblNgonNgu.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblNgonNgu.setForeground(new Color(0, 0, 0));
        lblNgonNgu.setText("Chọn ngôn ngữ:");
        lblNgonNgu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblNgonNgu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNgonNguMouseClicked(evt);
            }
        });
        panelThongTin.add(lblNgonNgu);

        btnDangNhap.setFont(new Font("Times New Roman", Font.BOLD, 20));
        btnDangNhap.setForeground(new Color(0, 0, 0));
        btnDangNhap.setText("ĐĂNG NHẬP");
        btnDangNhap.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDangNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangNhapActionPerformed(evt);
            }
        });
        panelThongTin.add(btnDangNhap);

        lblQuenMatKhau.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
        lblQuenMatKhau.setForeground(new Color(0, 0, 0));
        lblQuenMatKhau.setText("Quên mật khẩu?");
        lblQuenMatKhau.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblQuenMatKhau.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblQuenMatKhauMouseClicked(evt);
            }
        });
        panelThongTin.add(lblQuenMatKhau);

        cmbNgonNgu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "VietNam", "English" }));
        cmbNgonNgu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbNgonNguActionPerformed(evt);
            }
        });
        panelThongTin.add(cmbNgonNgu);

        getContentPane().add(panelThongTin);
        
        txtDangNhap = new JTextField();
        txtDangNhap.setFont(new Font("Times New Roman", Font.BOLD, 20));
        txtDangNhap.setBackground(new Color(255, 182, 193));
        txtDangNhap.setBounds(60, 159, 355, 36);
        panelThongTin.add(txtDangNhap);
        txtDangNhap.setColumns(10);
        
        txtMatKhau = new JPasswordField();
        txtMatKhau.setFont(new Font("Times New Roman", Font.BOLD, 20));
        txtMatKhau.setBackground(new Color(255, 182, 193));
        txtMatKhau.setBounds(60, 248, 355, 36);
        panelThongTin.add(txtMatKhau);

        setSize(new Dimension(1035, 496));
        // Ẩn viền của textField
        txtDangNhap.setBorder(null); 
        txtMatKhau.setBorder(null);
        
        setLocationRelativeTo(null);
	}
	
	private void lblExitMouseClicked(java.awt.event.MouseEvent evt) {
        System.exit(0);
    }
	
	private void showMouseClicked(java.awt.event.MouseEvent evt) {
        this.txtMatKhau.setEchoChar((char) 8226);
        this.disable.setVisible(true);
        this.disable.setEnabled(true);
        this.show.setVisible(false);
        this.show.setEnabled(false);
    }

    private void disableMouseClicked(java.awt.event.MouseEvent evt) {
        this.txtMatKhau.setEchoChar((char) 0);
        this.disable.setVisible(false);
        this.disable.setEnabled(false);
        this.show.setVisible(true);
        this.show.setEnabled(true);
    }
    
    private void chkRemerberPasswordActionPerformed(java.awt.event.ActionEvent evt) {
    	
    }
    
    public Preferences pref = Preferences.userRoot().node("Remember");

    public void saveAccount(String userName, String password) {
        if (userName != null || password != null) {
            pref.put("userName", userName);
            pref.put("password", password);
        }
    }
    
    private void chkRemerberPasswordMouseClicked(java.awt.event.MouseEvent evt) {
        if (chkRemerberPassword.isSelected()) {
            saveAccount(this.txtDangNhap.getText(), new String(this.txtMatKhau.getPassword()));
        }
    }
    
    private void lblNgonNguMouseClicked(java.awt.event.MouseEvent evt) {
    }

    private void lblQuenMatKhauMouseClicked(java.awt.event.MouseEvent evt) {
        new QuenMatKhau_GUI().setVisible(true);
    }

    private void cmbNgonNguActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            caiDatNgonNgu(ngonNguList.get(cmbNgonNgu.getSelectedIndex()));
        } catch (IOException ex) {
            Logger.getLogger(Login_GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
 
    private void btnDangNhapActionPerformed(ActionEvent evt) {
        String userName = txtDangNhap.getText().trim();
        String password = new String(txtMatKhau.getPassword());

        // Kiểm tra nếu tài khoản hoặc mật khẩu trống
        if (userName.isEmpty() && password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập tài khoản và mật khẩu!", "Thông Báo Đăng nhập", JOptionPane.WARNING_MESSAGE);
            return;
        } else if (userName.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập tên đăng nhập!", "Thông Báo Đăng nhập", JOptionPane.WARNING_MESSAGE);
            return;
        } else if (password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập mật khẩu!", "Thông Báo Đăng nhập", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Kiểm tra chiều dài của tài khoản và mật khẩu
        if (userName.length() != 10 || password.length() < 6) {
            JOptionPane.showMessageDialog(null, "Tài khoản hoặc Mật khẩu không chính xác!", "Thông Báo Đăng nhập", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String loai = userName.substring(0, 4);

        if (loai.equals("PPNV")) {
            NhanVien nhanVien = nhanVien_DAO.layMotNhanVienTheoMaNhanVien(userName);

            if (nhanVien != null && nhanVien.getMatKhau().equals(password)) {
                try {
                    new Main_GUI(nhanVien.getMaNhanVien(), ngonNguList.get(cmbNgonNgu.getSelectedIndex())).setVisible(true);
                    this.setVisible(false);
                } catch (IOException ex) {
                    Logger.getLogger(Login_GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.setVisible(false);
            } else {
                if (nhanVien == null) {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy tài khoản!", "Thông Báo Đăng nhập", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Tài khoản hoặc Mật khẩu không chính xác!", "Thông Báo Đăng nhập", JOptionPane.ERROR_MESSAGE);
                }
                return;
            }
        } else {
            JOptionPane.showMessageDialog(null, "ERROR", "Thông Báo Đăng nhập", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
    }

    
    public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login_GUI frame = new Login_GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
