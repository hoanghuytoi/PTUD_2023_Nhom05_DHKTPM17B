package UI;

import Dao.NhanVien_Dao;
import Dao.CongNhan_Dao;
import Entity.NhanVien;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import java.awt.Color;
import com.toedter.calendar.JDateChooser;



import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;
import javax.swing.JRadioButton;
import javax.swing.JPasswordField;
import javax.swing.ButtonGroup;
import java.awt.SystemColor;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.border.EmptyBorder;

public class ThongTinCaNhan_GUI extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private NhanVien_Dao nhanVien_Dao;
	private boolean isNhanVien;
	private String userName;
	private JTextField txtDangNhap;
	private JTextField txtSoDienThoai;
	private JTextField txtHoTen;
	private JTextField txtEmail;
	private JTextField txtDiaChi;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JPasswordField txtMatKhau;
	private JTextField txtCCCD;
	private JDateChooser dctNgayVaoLam;
	private JDateChooser dtcNgaySinh;
	private JRadioButton rdNam;
	private JRadioButton rdNu;
	private JLabel lblAnhDaiDien;
	private JButton btnLuu;
	private JLabel lbl_ErrSDT;
	private JLabel lbl_ErrHoVaTen;
	private JLabel lbl_ErrEmail;
	private JLabel lbl_ErrDiaChi;
	private JLabel lbl_ErrMatKhau;
	private JLabel lbl_ErrNgaySinh;
	private JLabel lblTieuDe;
	private JLabel lblTenDangNhap;
	private JLabel lblNgayVaoLam;
	private JLabel lblSDT;
	private JLabel lblNgaySinh;
	private JButton btnAnhDaiDien;
	private JLabel lblHoVaTen;
	private JLabel lblEmail;
	private JLabel lblDiaChi;
	private JLabel lblGioiTinh;
	private JLabel lblMatKhau;
	private JLabel lblCCCD;

	private String stErrKhongDeTrong;
    private String stThongbao;
    private String stCapNhatThanhCong;
    private String stCapNhatThatBai;
    private String stErrHoTen;
    private String stErrEmail;
    private String stErrSdt;
    private String stErrNgaySinh;
    private String stErrBanChuaDu18Tuoi;
    private String stErrMatKhau;
    
	public ThongTinCaNhan_GUI(String fileName, String userName) throws IOException{
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

        btnLuu.setText(prop.getProperty("btnLuu"));

        lblTenDangNhap.setText(prop.getProperty("qlttcn_tenDangNhap"));
        lblMatKhau.setText(prop.getProperty("qlttcn_matKhau"));
        lblHoVaTen.setText(prop.getProperty("hoTen"));
        lblCCCD.setText(prop.getProperty("soCCCD"));
        lblEmail.setText(prop.getProperty("email"));
        lblSDT.setText(prop.getProperty("sdt"));
        lblDiaChi.setText(prop.getProperty("diaChi"));
        lblNgaySinh.setText(prop.getProperty("ngaySinh"));
        lblGioiTinh.setText(prop.getProperty("gioiTinh"));
        lblNgayVaoLam.setText(prop.getProperty("ngayVaoLam"));
        btnAnhDaiDien.setText(prop.getProperty("anhDaiDien"));
        lblTieuDe.setText(prop.getProperty("qlttcn_TieuDe"));
        
        rdNam.setText(prop.getProperty("nam"));
        rdNu.setText(prop.getProperty("nu"));
        
        stErrBanChuaDu18Tuoi=prop.getProperty("qlttcn_khongDuTuoi");
        stErrMatKhau=prop.getProperty("qlttcn_errMatKhau");
        stThongbao = prop.getProperty("thongBao");
        stCapNhatThanhCong = prop.getProperty("capNhatThanhCong");
        stCapNhatThatBai = prop.getProperty("capNhatThatBai");
        stErrKhongDeTrong = prop.getProperty("KhongDeTrong");
        stErrHoTen = prop.getProperty("hoTenKhongHopLe");
        stErrEmail = prop.getProperty("emailKhongHopLe");
        stErrSdt = prop.getProperty("sdtKhongHopLe");
        stErrNgaySinh = prop.getProperty("ngaySinhKhongHopLe");
    }
	
	public void taiDuLieuLenTrang() {
		if (isNhanVien) {
			NhanVien nhanVien = nhanVien_Dao.layMotNhanVienTheoMaNhanVien(userName);
			txtDangNhap.setText(nhanVien.getMaNhanVien());
			txtCCCD.setText(nhanVien.getMaCCCD());
			txtHoTen.setText(nhanVien.getHoTen());
			txtEmail.setText(nhanVien.getEmail());
			txtSoDienThoai.setText(nhanVien.getSoDienThoai());
			txtDiaChi.setText(nhanVien.getDiaChi());
			dtcNgaySinh.setDate(nhanVien.getNgaySinh());
			dctNgayVaoLam.setDate(nhanVien.getNgayVaoLam());
			if (nhanVien.isGioiTinh()) {
				rdNam.setSelected(true);
			} else {
				rdNu.setSelected(true);
			}
			txtMatKhau.setText(nhanVien.getMatKhau());
			lblAnhDaiDien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/anhNhanVien/" + nhanVien.getAnhDaiDien())));
		} 
	}

	private void initComponents() {
		setSize(1290, 750);
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setForeground(new Color(128, 255, 255));
		panel.setBackground(new Color(146, 175, 237));
		panel.setBounds(1280, 111, -1279, -105);
		add(panel);
		panel.setLayout(null);

		lblTieuDe = new JLabel("THÔNG TIN CÁ NHÂN");
		lblTieuDe.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblTieuDe.setBounds(491, 10, 545, 66);
		add(lblTieuDe);

		setBackground(new Color(255, 255, 255));
		setFont(new Font("Tahoma", Font.BOLD, 20));
		setForeground(new Color(255, 0, 0));
		setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));

		JCheckBox ckbox = new JCheckBox("");
		ckbox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ckbox.isSelected())
				{
					txtMatKhau.setEchoChar((char)0);
				}else {
					txtMatKhau.setEchoChar('\u2022');
				}

			}
		});
		ckbox.setBackground(new Color(255, 255, 255));
		ckbox.setIcon(new ImageIcon(ThongTinCaNhan_GUI.class.getResource("/image/icon/disableeyeBlue.png")));
		ckbox.setBounds(1164, 461, 43, 40);
		add(ckbox);

		JSeparator jSeparator1 = new JSeparator();
		jSeparator1.setForeground(Color.BLACK);
		jSeparator1.setBounds(444, 170, 240, 10);
		add(jSeparator1);

		JSeparator jSeparator1_1 = new JSeparator();
		jSeparator1_1.setForeground(Color.BLACK);
		jSeparator1_1.setBounds(444, 244, 240, 10);
		add(jSeparator1_1);

		JSeparator jSeparator1_2 = new JSeparator();
		jSeparator1_2.setForeground(Color.BLACK);
		jSeparator1_2.setBounds(444, 317, 240, 10);
		add(jSeparator1_2);

		JSeparator jSeparator1_3 = new JSeparator();
		jSeparator1_3.setForeground(Color.BLACK);
		jSeparator1_3.setBounds(903, 170, 240, 10);
		add(jSeparator1_3);

		JSeparator jSeparator1_4 = new JSeparator();
		jSeparator1_4.setForeground(Color.BLACK);
		jSeparator1_4.setBounds(903, 253, 240, 10);
		add(jSeparator1_4);

		JSeparator jSeparator1_5 = new JSeparator();
		jSeparator1_5.setForeground(Color.BLACK);
		jSeparator1_5.setBounds(903, 324, 240, 10);
		add(jSeparator1_5);

		JSeparator jSeparator2;
		jSeparator2 = new JSeparator();
		jSeparator2.setBounds(-301, 86, 1920, 20);
		add(jSeparator2);

		lblCCCD = new JLabel("CCCD\t\t:");
		lblCCCD.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCCCD.setBounds(279, 214, 120, 40);
		add(lblCCCD);

		dtcNgaySinh = new JDateChooser();
		dtcNgaySinh.setDateFormatString("yyyy-MM-dd");
		dtcNgaySinh.setBounds(444, 372, 240, 40);
		add(dtcNgaySinh);

		txtDangNhap = new JTextField();
		txtDangNhap.setForeground(new Color(0, 0, 0));
		txtDangNhap.setBorder(null);
		txtDangNhap.setBackground(new Color(255, 255, 255));
		txtDangNhap.setEnabled(false);
		txtDangNhap.setFont(new Font("Times New Roman", Font.BOLD, 18));
		txtDangNhap.setBounds(444, 131, 240, 34);
		add(txtDangNhap);

		txtCCCD = new JTextField();
		txtCCCD.setForeground(new Color(0, 0, 0));
		txtCCCD.setEnabled(false);
		txtCCCD.setBackground(new Color(255, 255, 255));
		txtCCCD.setFont(new Font("Times New Roman", Font.BOLD, 18));
		txtCCCD.setBounds(444, 202, 240, 40);
		txtCCCD.setBorder(null);
		add(txtCCCD);


		lblTenDangNhap = new JLabel("Tên đăng nhập:\r\n");
		lblTenDangNhap.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTenDangNhap.setBounds(279, 146, 132, 40);
		add(lblTenDangNhap);

		txtSoDienThoai = new JTextField();
		txtSoDienThoai.setBorder(null);
		txtSoDienThoai.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtSoDienThoai.setBounds(444, 279, 240, 34);
		add(txtSoDienThoai);

		lblNgayVaoLam = new JLabel("Ngày vào làm:");
		lblNgayVaoLam.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNgayVaoLam.setBounds(279, 458, 155, 40);
		add(lblNgayVaoLam);

		lblSDT = new JLabel("Số điện thoại:\r\n");
		lblSDT.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblSDT.setBounds(279, 294, 120, 40);
		add(lblSDT);

		lblNgaySinh = new JLabel("Ngày Sinh:");
		lblNgaySinh.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNgaySinh.setBounds(279, 372, 120, 40);
		add(lblNgaySinh);

		dctNgayVaoLam = new JDateChooser();
		dctNgayVaoLam.setForeground(new Color(0, 0, 0));
		dctNgayVaoLam.setBackground(SystemColor.activeCaptionBorder);
		dctNgayVaoLam.setEnabled(false);
		dctNgayVaoLam.setDateFormatString("yyyy-MM-dd");
		dctNgayVaoLam.setBounds(444, 458, 240, 40);
		add(dctNgayVaoLam);

		lblAnhDaiDien = new JLabel("");
		lblAnhDaiDien.setBorder(new EmptyBorder(0, 0, 0, 0));
		lblAnhDaiDien.setIcon(new ImageIcon(ThongTinCaNhan_GUI.class.getResource("/image/anhNhanVien/man.png")));
		lblAnhDaiDien.setBounds(56, 222, 132, 140);
		add(lblAnhDaiDien);

		btnAnhDaiDien = new JButton("Ảnh đại diện");
		btnAnhDaiDien.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnAnhDaiDien.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		btnAnhDaiDien.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				btnAnhDaiDienMouseClicked(evt);
			}
		});
		btnAnhDaiDien.setBackground(new Color(0, 255, 128));
		btnAnhDaiDien.setBounds(56, 372, 132, 40);
		add(btnAnhDaiDien);

		lblHoVaTen = new JLabel("Họ và tên:\r\n");
		lblHoVaTen.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblHoVaTen.setBounds(782, 146, 120, 40);
		add(lblHoVaTen);

		txtHoTen = new JTextField();
		txtHoTen.setBorder(null);
		txtHoTen.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtHoTen.setBounds(903, 128, 240, 40);
		add(txtHoTen);

		lblEmail = new JLabel("Email:\r\n");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblEmail.setBounds(782, 224, 120, 40);
		add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setBorder(null);
		txtEmail.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtEmail.setBounds(903, 214, 240, 34);
		add(txtEmail);

		lblDiaChi = new JLabel("Địa chỉ:");
		lblDiaChi.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDiaChi.setBounds(782, 294, 120, 40);
		add(lblDiaChi);

		txtDiaChi = new JTextField();
		txtDiaChi.setBorder(null);
		txtDiaChi.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtDiaChi.setBounds(903, 281, 240, 40);
		add(txtDiaChi);

		lblGioiTinh = new JLabel("Giới tính:");
		lblGioiTinh.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblGioiTinh.setBounds(782, 372, 120, 40);
		add(lblGioiTinh);

		lblMatKhau = new JLabel("Mật khẩu:");
		lblMatKhau.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblMatKhau.setBounds(782, 458, 120, 40);
		add(lblMatKhau);

		rdNam = new JRadioButton("Nam");
		rdNam.setBackground(new Color(255, 255, 255));
		buttonGroup.add(rdNam);
		rdNam.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		rdNam.setBounds(933, 382, 103, 21);
		add(rdNam);

		rdNu = new JRadioButton("Nữ");
		rdNu.setBackground(new Color(255, 255, 255));
		buttonGroup.add(rdNu);
		rdNu.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		rdNu.setBounds(1040, 382, 103, 21);
		add(rdNu);

		btnLuu = new JButton("Lưu");
		btnLuu.addActionListener(this);   	
		btnLuu.setIcon(new ImageIcon(ThongTinCaNhan_GUI.class.getResource("/image/icon/them.png")));
		btnLuu.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		btnLuu.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnLuu.setBackground(new Color(0, 255, 128));
		btnLuu.setBounds(626, 592, 160, 40);
		add(btnLuu);

		txtMatKhau = new JPasswordField();
		txtMatKhau.setEnabled(false);
		txtMatKhau.setDisabledTextColor(new Color(0, 0, 0));
		txtMatKhau.setFont(new Font("Times New Roman", Font.BOLD, 18));
		txtMatKhau.setForeground(new Color(0, 0, 0));
		txtMatKhau.setBounds(903, 458, 240, 40);
		add(txtMatKhau); 

		lbl_ErrSDT = new JLabel();
		lbl_ErrSDT.setForeground(new Color(255, 0, 0));
		lbl_ErrSDT.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl_ErrSDT.setBounds(444, 324, 240, 27);
		add(lbl_ErrSDT);

		lbl_ErrHoVaTen = new JLabel();
		lbl_ErrHoVaTen.setForeground(new Color(255, 0, 0));
		lbl_ErrHoVaTen.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl_ErrHoVaTen.setBounds(903, 179, 240, 20);
		add(lbl_ErrHoVaTen);

		lbl_ErrEmail = new JLabel();
		lbl_ErrEmail.setForeground(new Color(255, 0, 0));
		lbl_ErrEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl_ErrEmail.setBounds(903, 260, 240, 20);
		add(lbl_ErrEmail);

		lbl_ErrDiaChi = new JLabel();
		lbl_ErrDiaChi.setForeground(new Color(255, 0, 0));
		lbl_ErrDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl_ErrDiaChi.setBounds(903, 331, 240, 20);
		add(lbl_ErrDiaChi);

		lbl_ErrMatKhau = new JLabel();
		lbl_ErrMatKhau.setForeground(new Color(255, 0, 0));
		lbl_ErrMatKhau.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl_ErrMatKhau.setBounds(903, 502, 240, 20);
		add(lbl_ErrMatKhau);

		lbl_ErrNgaySinh = new JLabel();
		lbl_ErrNgaySinh.setForeground(new Color(255, 0, 0));
		lbl_ErrNgaySinh.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl_ErrNgaySinh.setBounds(444, 420, 240, 27);
		add(lbl_ErrNgaySinh);
	}

	private void btnAnhDaiDienMouseClicked(java.awt.event.MouseEvent evt) {
		JFileChooser fileChooser = new JFileChooser("d:");
		fileChooser.setCurrentDirectory(new File(".\\src\\image\\anhNhanVien"));
		int respone = fileChooser.showSaveDialog(null);
		if (respone == JFileChooser.APPROVE_OPTION) {
			File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
			System.out.println(file);
			String path = file.toString().split("src")[1].replace('\\', '/');
			System.out.println("path file split: " + file.toString().split("src")[1]);
			this.lblAnhDaiDien.setIcon(new ImageIcon(this.getClass().getResource(path)));
			System.out.println(this.lblAnhDaiDien.getIcon().toString());
		}
	}

	public boolean validateForm() {
        Boolean flag = true;
        String hoTen = txtHoTen.getText().trim();
        String email = txtEmail.getText().trim();
        String soDienThoai = txtSoDienThoai.getText().trim();
        String diaChi = txtDiaChi.getText().trim();
        String matKhau = new String(txtMatKhau.getPassword());
        Date ngaySinh = dtcNgaySinh.getDate();

        if (hoTen.equals("")) {
            lbl_ErrHoVaTen.setText(stErrKhongDeTrong);
            flag = false;
        } else if (!hoTen.matches("^([A-ZĐÂÁƯ]{1}[a-zvxyỳọáầảấờễàạằệếýộậốũứĩõúữịỗìềểẩớặòùồợãụủíỹắẫựỉỏừỷởóéửỵẳẹèẽổẵẻỡơôưăêâđ]+)"
                + "((\\s{1}[A-ZĐÂÁƯ][{1}a-vxyỳọáầảấờễàạằệếýộậốũứĩõúữịỗìềểẩớặòùồợãụủíỹắẫựỉỏừỷởóéửỵẳẹèẽổẵẻỡơôưăêâđ]+){1,})$")) {
        	lbl_ErrHoVaTen.setText(stErrHoTen);
            flag = false;
        } else {
        	lbl_ErrHoVaTen.setText("");
        }
        if (email.equals("")) {
            lbl_ErrEmail.setText(stErrKhongDeTrong);
            flag = false;
        } else if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
        	lbl_ErrEmail.setText(stErrEmail);
            flag = false;
        } else {
        	lbl_ErrEmail.setText("");
        }
        if (soDienThoai.equals("")) {
            lbl_ErrSDT.setText(stErrKhongDeTrong);
            flag = false;
        } else if (!soDienThoai.matches("^\\+84[1-9][0-9]{8}$")) {
        	lbl_ErrSDT.setText(stErrSdt);
            flag = false;
        } else {
        	lbl_ErrSDT.setText("");
        }
        if (diaChi.equals("")) {
            lbl_ErrDiaChi.setText(stErrKhongDeTrong);
            flag = false;
        } else {
        	lbl_ErrDiaChi.setText("");
        }
        if (!ngaySinh.before(new Date())) {
            lbl_ErrNgaySinh.setText(stErrNgaySinh);
            flag = false;
        } else if (calculateAgeWithJava7(ngaySinh, new Date()) < 18) {
        	lbl_ErrNgaySinh.setText(stErrBanChuaDu18Tuoi);
            flag = false;
        } else {
        	lbl_ErrNgaySinh.setText("");
        }

        if (matKhau.equals("")) {
            lbl_ErrMatKhau.setText(stErrKhongDeTrong);
            flag = false;
        } else if (matKhau.length() < 6) {
        	lbl_ErrMatKhau.setText(stErrMatKhau);
            flag = false;
        } else {
        	lbl_ErrMatKhau.setText("");
        }
        return flag;
    }

	public int calculateAgeWithJava7(Date birthDate, Date currentDate) {                                                                          
		DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		int d1 = Integer.parseInt(formatter.format(birthDate));
		int d2 = Integer.parseInt(formatter.format(currentDate));
		int age = (d2 - d1) / 10000;
		return age;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btnLuu))
		{
			boolean isValid = validateForm();
			if (!isValid) {
				return;
			}
			if (isNhanVien) {
				NhanVien nhanVienOld = nhanVien_Dao.layMotNhanVienTheoMaNhanVien(userName);
				boolean coSuaDuoc = nhanVien_Dao.suaThongTinMotNhanVien(new NhanVien(nhanVienOld.getMaNhanVien(),
						txtHoTen.getText(), dtcNgaySinh.getDate(), txtCCCD.getText(), txtSoDienThoai.getText(),
						txtEmail.getText(), new String(txtMatKhau.getPassword()), nhanVienOld.getChucVu(), nhanVienOld.getNgayVaoLam(),
						nhanVienOld.getLuongCoBan(), rdNam.isSelected() ? true : false,
								lblAnhDaiDien.getIcon().toString().split("anhNhanVien/")[1], txtDiaChi.getText(), nhanVienOld.getPhongBan()));
				if (coSuaDuoc) {
                    JOptionPane.showMessageDialog(null,stCapNhatThanhCong, stThongbao, JOptionPane.INFORMATION_MESSAGE);
                    taiDuLieuLenTrang();
                } else {
                    JOptionPane.showMessageDialog(null,stCapNhatThatBai,stThongbao, JOptionPane.INFORMATION_MESSAGE);
                }
			} 
		}

	}
}
