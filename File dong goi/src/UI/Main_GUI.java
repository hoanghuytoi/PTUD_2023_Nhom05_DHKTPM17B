package UI;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;

import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

import Custom_UI.MenuItem;
import Custom_UI.ScrollBarCustom;
import Dao.CongNhan_Dao;
import Dao.NhanVien_Dao;
import Entity.CongNhan;
import Entity.NhanVien;

/**
 * Hoàng Huy Tới
 */

public class Main_GUI extends JFrame {
	private JScrollPane jScrollPane;
    private JLabel lblTieuDe;
    private JLabel lblXinChao;
    private JPanel menus;
    private JPanel pnBody;
    private JPanel pnHeader;
    private JPanel pnMenu;
    private String fileName;
    private String userName;
    private NhanVien nhanVienDangNhap;
    
	private static final long serialVersionUID = 1L;
	
	// san pham
    private MenuItem capNhatSanPham = null;
    private MenuItem phanCongDoanSanPham = null;
    private MenuItem timKiemSanPham = null;
    
    // nhan vien
    private MenuItem capNhatNhanVien = null;
    private MenuItem chamCongNhanVien = null;
    private MenuItem tinhLuongNhanVien = null;
    private MenuItem timKiemNhanVien = null;
    private MenuItem phanCongCongNhan = null;
  
    // cong nhan
    private MenuItem capNhatCongNhan = null;
    private MenuItem chamCongCongNhan = null;
    private MenuItem tinhLuongCongNhan = null;
    private MenuItem timKiemCongNhan = null;
    
    // thong ke
    private MenuItem thongKeDoanhThu = null;
    private MenuItem thongKeNhanVien = null;
    private MenuItem thongKeCongNhan = null;
    
    // he thong
    private MenuItem thongTinCaNhan = null;
    private MenuItem doiMatKhau = null;
    private MenuItem dangXuat = null;
  
    private ImageIcon iconSubMenuNonSelect = null;
    private ImageIcon iconSubMenuSelect = null;
    private ImageIcon iconResetPassword = null;
    private ImageIcon iconLogOut = null;

    private CongNhan_Dao congNhan_DAO;
    private NhanVien_Dao nhanVien_DAO;
    private NhanVien nhanVien;
    private CongNhan congNhan;
    
    private MenuItem menuTrangChu;
    private MenuItem menuHopDong;
    private MenuItem menuPhongBan;
    private MenuItem menuToNhom;
    private MenuItem menuNhanVien;
    private MenuItem menuCongNhan;
    private MenuItem menuSanPham;
    private MenuItem menuThongKe;
    private MenuItem menuHoTro;
    private MenuItem menuHeThong;
	
    private String lblTrangChu;
    private String lblPhongBan;
    private String lblToNhom;
    private String lblHopDong;
    private String lblSanPham;
    private String lblCapNhat;
    private String lblPhanCongDoanSanPham;
    private String lblTimKiem;
    private String lblNhanVien;
    private String lblChamCong;
    private String lblPhanCong;
    private String lblLuong;
    private String lblCongNhan;
    private String lblThongKe;
    private String lblDoanhThu;
    private String lblHeThong;
    private String lblThongTinCaNhan;
    private String lblDangXuat;
    private String lblXacNhanDangXuat;
    private String lblHoTro;
    private String lblDoiMatKhau;
	private MenuItem menuNhanVienKT;
	private MenuItem menuCongNhanKT;
	private MenuItem menuCongNhanNV;
    
    public Main_GUI(String userName, String fileName) throws IOException {
    	try {
            ConnectionDB.ConnectDB.getInstance().connect();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    	this.fileName = fileName;
        this.userName = userName;
        System.out.println(fileName);
        congNhan_DAO = new CongNhan_Dao();
        nhanVien_DAO = new NhanVien_Dao();
        nhanVienDangNhap = nhanVien_DAO.layMotNhanVienTheoMaNhanVien(userName);
        initComponents();
        caiDatNgonNgu(fileName);
        execute();
        
        String loai = userName.substring(0, 4);
        if (loai.equals("PPCN")) {
            congNhan = congNhan_DAO.layMotCongNhanTheoMa(userName);

        } 
        if (loai.equals("PPNV")) {
            nhanVien = nhanVien_DAO.layMotNhanVienTheoMaNhanVien(userName);
        }
        
        this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
    }
    
    public void caiDatNgonNgu(String fileName) throws FileNotFoundException, IOException {
        FileInputStream fis = new FileInputStream(fileName);
        Properties prop = new Properties();
        prop.load(fis);
        lblTieuDe.setText(prop.getProperty("Main_lblTieuDe"));
        lblTrangChu = prop.getProperty("Main_lblTrangChu");
        lblPhongBan = prop.getProperty("Main_lblPhongBan");
        lblToNhom = prop.getProperty("Main_lblToNhom");
        lblHopDong = prop.getProperty("Main_lblHopDong");
        lblSanPham = prop.getProperty("Main_lblSanPham");
        lblCapNhat = prop.getProperty("Main_lblCapNhat");
        lblPhanCongDoanSanPham = prop.getProperty("Main_lblPhanCongDoanSanPham");
        lblTimKiem = prop.getProperty("Main_lblTimKiem");
        lblNhanVien = prop.getProperty("Main_lblNhanVien");
        lblChamCong = prop.getProperty("Main_lblChamCong");
        lblPhanCong = prop.getProperty("Main_lblPhanCongViec");
        lblLuong = prop.getProperty("Main_lblLuong");
        lblCongNhan = prop.getProperty("Main_lblCongNhan");
        lblThongKe = prop.getProperty("Main_lblThongKe");
        lblHoTro = prop.getProperty("Main_lblHoTro");
        lblHeThong = prop.getProperty("Main_lblHeThong");
        lblThongTinCaNhan = prop.getProperty("Main_lblThongTinCaNhan");
        lblDangXuat = prop.getProperty("Main_lblDangXuat");
        lblXacNhanDangXuat = prop.getProperty("Main_lblXacNhanDangXuat");
        lblDoanhThu = prop.getProperty("Main_lblDoanhThu");
        lblDoiMatKhau = prop.getProperty("Main_lblDoiMatKhau");
    }
    
    private void initComponents() {
    	setTitle("PACEPRO - Hệ thống quản lý lương sản phẩm");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Main_GUI.class.getResource("/image/icon/salary.png")));
		
        pnHeader = new JPanel();
        lblTieuDe = new JLabel();
        lblXinChao = new JLabel();
        lblXinChao.setIcon(new ImageIcon(Main_GUI.class.getResource("/image/icon/logocty.png")));
        lblXinChao.setText("Welcome ");
        pnMenu = new JPanel();
        jScrollPane = new JScrollPane();
        menus = new JPanel();
        pnBody = new JPanel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBackground(new Color(255, 255, 255));

        pnHeader.setBackground(new Color(222, 184, 135));
        pnHeader.setForeground(new Color(41, 128, 185));
        pnHeader.setFont(new Font("Times New Roman", Font.BOLD, 30)); 
        pnHeader.setPreferredSize(new Dimension(1500, 75));
        pnHeader.setLayout(new AbsoluteLayout());

        lblTieuDe.setFont(new Font("Times New Roman", Font.BOLD, 30));
        lblTieuDe.setForeground(new Color(0, 0, 0));
        lblTieuDe.setHorizontalAlignment(SwingConstants.CENTER);
        lblTieuDe.setText("HỆ THỐNG QUẢN LÝ LƯƠNG SẢN PHẨM CÔNG TY - PACEPRO");
        pnHeader.add(lblTieuDe, new AbsoluteConstraints(250, 0, 1250, 75));

        lblXinChao.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
        lblXinChao.setForeground(new Color(0, 0, 0));
        lblXinChao.setHorizontalAlignment(SwingConstants.CENTER);
        pnHeader.add(lblXinChao, new AbsoluteConstraints(0, 4, 244, 70));

        getContentPane().add(pnHeader,BorderLayout.PAGE_START);

        pnMenu.setBackground(new Color(255, 255, 255));
        pnMenu.setPreferredSize(new Dimension(250, 725));

        jScrollPane.setBorder(null);

        menus.setBackground(new Color(255, 182, 193));
        menus.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        menus.setLayout(new BoxLayout(menus,BoxLayout.Y_AXIS));
        jScrollPane.setViewportView(menus);
        
        ScrollBarCustom scrollBar = new ScrollBarCustom();

        // Chèn ScrollBarCustom vào JScrollPane
        jScrollPane.setVerticalScrollBar(scrollBar);

        javax.swing.GroupLayout pnMenuLayout = new GroupLayout(pnMenu);
        pnMenu.setLayout(pnMenuLayout);
        pnMenuLayout.setHorizontalGroup(
            pnMenuLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane,GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
        );
        pnMenuLayout.setVerticalGroup(
            pnMenuLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane, GroupLayout.DEFAULT_SIZE, 725, Short.MAX_VALUE)
        );

        getContentPane().add(pnMenu, BorderLayout.LINE_START);

        pnBody.setBackground(new Color(240, 230, 140));
        pnBody.setLayout(new BorderLayout());
        getContentPane().add(pnBody, BorderLayout.CENTER);

        setSize(new Dimension(1540, 835));
        setLocationRelativeTo(null);
    }
    
    private void execute() throws IOException {
    	ImageIcon iconTrangChu = new ImageIcon(getClass().getResource("/image/icon/home.png"));
    	ImageIcon iconPhongBan = new ImageIcon(getClass().getResource("/image/icon/phongban.png"));
        ImageIcon iconToNhom = new ImageIcon(getClass().getResource("/image/icon/tonhom.png"));
        ImageIcon iconHopDong = new ImageIcon(getClass().getResource("/image/icon/hopdong.png"));  
        ImageIcon iconSanPham = new ImageIcon(getClass().getResource("/image/icon/sanpham.png"));
        ImageIcon iconNhanVien = new ImageIcon(getClass().getResource("/image/icon/nhanvien.png"));
        ImageIcon iconCongNhan = new ImageIcon(getClass().getResource("/image/icon/congnhan.png"));
        ImageIcon iconThongKe = new ImageIcon(getClass().getResource("/image/icon/thongke.png"));
        ImageIcon iconHoTro = new ImageIcon(getClass().getResource("/image/icon/hotro.png"));
        ImageIcon iconHeThong = new ImageIcon(getClass().getResource("/image/icon/hethong.png"));
        
        iconSubMenuNonSelect = new ImageIcon(getClass().getResource("/image/icon/kochon.png"));
        iconSubMenuSelect = new ImageIcon(getClass().getResource("/image/icon/chon.png"));
        
        iconResetPassword = new ImageIcon(getClass().getResource("/image/icon/doimatkhau.png"));
        iconLogOut = new ImageIcon(getClass().getResource("/image/icon/dangxuat.png"));

        // subMenu sản phẩm
        capNhatSanPham = new MenuItem(iconSubMenuNonSelect,lblCapNhat, ((e) -> {
            pnBody.removeAll();
            try {
                pnBody.add(new SanPham_GUI(fileName), BorderLayout.CENTER);
            } catch (Exception ex) {
                Logger.getLogger(Main_GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            pnBody.repaint();
            pnBody.revalidate();
            iconSubMenuMacDinh(capNhatSanPham);
        }));
        
        phanCongDoanSanPham = new MenuItem(iconSubMenuNonSelect, lblPhanCongDoanSanPham, ((e) -> {
            pnBody.removeAll();
            try {
                pnBody.add(new CongDoanSanPham_GUI(fileName), BorderLayout.CENTER);
            } catch (Exception ex) {
                Logger.getLogger(Main_GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            pnBody.repaint();
            pnBody.revalidate();
            iconSubMenuMacDinh(phanCongDoanSanPham);
        }));
        
        timKiemSanPham = new MenuItem(iconSubMenuNonSelect, lblTimKiem, ((e) -> {
            pnBody.removeAll();
            try {
                pnBody.add(new TimKiemSanPham_GUI(fileName), BorderLayout.CENTER);
            } catch (Exception ex) {
                Logger.getLogger(Main_GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            pnBody.repaint();
            pnBody.revalidate();
            iconSubMenuMacDinh(timKiemSanPham);
        }));
        
        // subMenu nhan vien
        capNhatNhanVien = new MenuItem(iconSubMenuNonSelect, lblCapNhat, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pnBody.removeAll();
                try {
                    pnBody.add(new NhanVien_GUI(fileName), BorderLayout.CENTER);
                } catch (Exception ex) {
                    Logger.getLogger(Main_GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                pnBody.repaint();
                pnBody.revalidate();
                iconSubMenuMacDinh((capNhatNhanVien));
            }
        });
        
        chamCongNhanVien = new MenuItem(iconSubMenuNonSelect, lblChamCong, (ActionEvent e) -> {
            pnBody.removeAll();
            try {
                pnBody.add(new ChamCongNhanVien_GUI(nhanVienDangNhap,fileName), BorderLayout.CENTER);
            } catch (Exception ex) {
                Logger.getLogger(Main_GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            pnBody.repaint();
            pnBody.revalidate();
            iconSubMenuMacDinh((chamCongNhanVien));
        });
        
        tinhLuongNhanVien = new MenuItem(iconSubMenuNonSelect, lblLuong, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pnBody.removeAll();
                try {
                    pnBody.add(new BangLuongNhanVien_GUI(fileName), BorderLayout.CENTER);
                } catch (Exception ex) {
                    Logger.getLogger(Main_GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                pnBody.repaint();
                pnBody.revalidate();
                iconSubMenuMacDinh(tinhLuongNhanVien);
            }

        });
        
        timKiemNhanVien = new MenuItem(iconSubMenuNonSelect, lblTimKiem, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pnBody.removeAll();
                try {
                    pnBody.add(new TimKiemNhanVien_GUI(fileName), BorderLayout.CENTER);
                } catch (Exception ex) {
                    Logger.getLogger(Main_GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                pnBody.repaint();
                pnBody.revalidate();
                iconSubMenuMacDinh(timKiemNhanVien);
            }

        });
        
        // subMenu công nhân
        capNhatCongNhan = new MenuItem(iconSubMenuNonSelect, lblCapNhat, ((e) -> {
            pnBody.removeAll();
            try {
                pnBody.add(new CongNhan_GUI(fileName), BorderLayout.CENTER);
            } catch (Exception ex) {
                Logger.getLogger(Main_GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            pnBody.repaint();
            pnBody.revalidate();
            iconSubMenuMacDinh(capNhatCongNhan);
        }));
        
        phanCongCongNhan = new MenuItem(iconSubMenuNonSelect, lblPhanCong, ((e) -> {
            pnBody.removeAll();
            try {
                pnBody.add(new PhanCongCongNhan_GUI(fileName), BorderLayout.CENTER);
            } catch (Exception ex) {
                Logger.getLogger(Main_GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            pnBody.repaint();
            pnBody.revalidate();
            iconSubMenuMacDinh(phanCongCongNhan);
        }));
        
        chamCongCongNhan = new MenuItem(iconSubMenuNonSelect, lblChamCong, ((e) -> {
            pnBody.removeAll();
            try {
                pnBody.add(new ChamCongCongNhan_GUI(fileName), BorderLayout.CENTER);
            } catch (Exception ex) {
                Logger.getLogger(Main_GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            pnBody.repaint();
            pnBody.revalidate();
            iconSubMenuMacDinh(chamCongCongNhan);
        }));
        
        timKiemCongNhan = new MenuItem(iconSubMenuNonSelect, lblTimKiem, ((e) -> {
            pnBody.removeAll();
            try {
                pnBody.add(new TimKiemCongNhan_GUI(fileName), BorderLayout.CENTER);
            } catch (Exception ex) {
                Logger.getLogger(Main_GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            pnBody.repaint();
            pnBody.revalidate();
            iconSubMenuMacDinh(timKiemCongNhan);
        }));
        
        tinhLuongCongNhan = new MenuItem(iconSubMenuNonSelect, lblLuong, ((e) -> {
            pnBody.removeAll();
            try {
                pnBody.add(new BangLuongCongNhan_GUI(fileName), BorderLayout.CENTER);
            } catch (Exception ex) {
                Logger.getLogger(Main_GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            pnBody.repaint();
            pnBody.revalidate();
            iconSubMenuMacDinh((tinhLuongCongNhan));
        }));
        
        // subMenu thống kê
        thongKeDoanhThu = new MenuItem(iconSubMenuNonSelect, lblDoanhThu, ((e) -> {
            pnBody.removeAll();
            try {
                pnBody.add(new ThongKeDoanhThu_GUI(fileName), BorderLayout.CENTER);
            } catch (Exception ex) {
                Logger.getLogger(Main_GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            pnBody.repaint();
            pnBody.revalidate();
            iconSubMenuMacDinh((thongKeDoanhThu));
            
        }));
        
        thongKeNhanVien = new MenuItem(iconSubMenuNonSelect, lblNhanVien, ((e) -> {
            pnBody.removeAll();
            try {
                pnBody.add(new ThongKeNhanVien_GUI(fileName), BorderLayout.CENTER);
            } catch (Exception ex) {
                Logger.getLogger(Main_GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            pnBody.repaint();
            pnBody.revalidate();
            iconSubMenuMacDinh((thongKeNhanVien));
            
        }));
        
        thongKeCongNhan = new MenuItem(iconSubMenuNonSelect, lblCongNhan, ((e) -> {
            pnBody.removeAll();
            try {
                pnBody.add(new ThongKeCongNhan_GUI(fileName), BorderLayout.CENTER);
            } catch (Exception ex) {
                Logger.getLogger(Main_GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            pnBody.repaint();
            pnBody.revalidate();
            iconSubMenuMacDinh((thongKeCongNhan));
        }));
        
        // subMenu he thong
        thongTinCaNhan = new MenuItem(iconSubMenuNonSelect, lblThongTinCaNhan, ((e) -> {
            pnBody.removeAll();
            String username = "";
            if (congNhan != null) {
                username = congNhan.getMaCongNhan();

            } else if (nhanVien != null) {
                username = nhanVien.getMaNhanVien();
            }
            try {
                pnBody.add(new ThongTinCaNhan_GUI(this.fileName,username), BorderLayout.CENTER);
            } catch (IOException ex) {
                Logger.getLogger(Main_GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            pnBody.repaint();
            pnBody.revalidate();
            iconSubMenuMacDinh((thongTinCaNhan));
        }));
        
        doiMatKhau = new MenuItem(iconResetPassword, lblDoiMatKhau, ((e) -> {
            pnBody.removeAll();
            String userName = "";
            if (congNhan != null) {
                userName = congNhan.getMaCongNhan();

            } else if (nhanVien != null) {
                userName = nhanVien.getMaNhanVien();
            }
            try {
                pnBody.add(new DoiMatKhau_GUI(this.fileName,userName), BorderLayout.CENTER);
            } catch (Exception ex) {
                Logger.getLogger(Main_GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            pnBody.repaint();
            pnBody.revalidate();
            iconSubMenuMacDinh((doiMatKhau));
        }));
        
        dangXuat = new MenuItem(iconLogOut, lblDangXuat, ((e) -> {
        	iconSubMenuMacDinh((dangXuat));
            if (JOptionPane.showConfirmDialog(null, lblXacNhanDangXuat, null, JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
                this.setVisible(false);
                Login_GUI loginGUI = new Login_GUI();
                loginGUI.setLocationRelativeTo(null);
                loginGUI.setVisible(true);
                loginGUI.setResizable(false);
            } else {
                dangXuat.setIcon(iconLogOut);
            }
        }));
        
        
        // menu Trang chủ
        menuTrangChu = new MenuItem(iconTrangChu, lblTrangChu, (ActionEvent e) -> {
            pnBody.removeAll();
            try {
                pnBody.add(new TrangChu_GUI(), BorderLayout.CENTER);
            } catch (Exception ex) {
                Logger.getLogger(Main_GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            pnBody.repaint();
            pnBody.revalidate();
            setNonSelectMenu(menuTrangChu, menuHopDong, menuPhongBan, menuToNhom, menuNhanVien, menuCongNhan, menuSanPham, menuThongKe, menuHoTro,menuHeThong);
            setSelectMenu(menuTrangChu);

        });
        
        // menu Phòng ban
        menuPhongBan = new MenuItem(iconPhongBan, lblPhongBan, (ActionEvent e) -> {
            pnBody.removeAll();
            try {
                pnBody.add(new PhongBan_GUI(nhanVienDangNhap, fileName), BorderLayout.CENTER);
            } catch (Exception ex) {
                Logger.getLogger(Main_GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            pnBody.repaint();
            pnBody.revalidate();
            setNonSelectMenu(menuTrangChu, menuHopDong, menuPhongBan, menuToNhom, menuNhanVien, menuCongNhan, menuSanPham, menuThongKe, menuHoTro,menuHeThong);
            setSelectMenu(menuPhongBan);
        });
        
        // menu Tổ nhóm
        menuToNhom = new MenuItem(iconToNhom, lblToNhom, (ActionEvent e) -> {
            pnBody.removeAll();
            try {
                pnBody.add(new ToNhom_GUI(fileName), BorderLayout.CENTER);
            } catch (Exception ex) {
                Logger.getLogger(Main_GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            pnBody.repaint();
            pnBody.revalidate();
            setNonSelectMenu(menuTrangChu, menuHopDong, menuPhongBan, menuToNhom, menuNhanVien, menuCongNhan, menuSanPham, menuThongKe, menuHoTro,menuHeThong);
            setSelectMenu(menuToNhom);
        });
        
        // menu Hộp đồng
        menuHopDong = new MenuItem(iconHopDong, lblHopDong, (ActionEvent e) -> {
            pnBody.removeAll();
            try {
                pnBody.add(new HopDong_GUI(nhanVienDangNhap,fileName), BorderLayout.CENTER);
            } catch (Exception ex) {
                Logger.getLogger(Main_GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            pnBody.repaint();
            pnBody.revalidate();
            setNonSelectMenu(menuTrangChu, menuHopDong, menuPhongBan, menuToNhom, menuNhanVien, menuCongNhan, menuSanPham, menuThongKe, menuHoTro,menuHeThong);
            setSelectMenu(menuHopDong);
        });
        
        //menu Sản phẩm
        menuSanPham = new MenuItem(iconSanPham, lblSanPham, (ActionEvent e) -> {
            pnBody.repaint();
            pnBody.revalidate();
            setNonSelectMenu(menuTrangChu, menuHopDong, menuPhongBan, menuToNhom, menuNhanVien, menuCongNhan, menuSanPham, menuThongKe, menuHoTro,menuHeThong);
            setSelectMenu(menuSanPham);
        },capNhatSanPham,phanCongDoanSanPham,timKiemSanPham);
        
        // menu Nhân viên
        menuNhanVien = new MenuItem(iconNhanVien, lblNhanVien, (ActionEvent e) -> {
            pnBody.repaint();
            pnBody.revalidate();
            setNonSelectMenu(menuTrangChu, menuHopDong, menuPhongBan, menuToNhom, menuNhanVien, menuCongNhan, menuSanPham, menuThongKe, menuHoTro,menuHeThong);
            setSelectMenu(menuNhanVien);
            
            // Show capNhatNhanVien and chamCongNhanVien
            capNhatNhanVien.setVisible(true);
            chamCongNhanVien.setVisible(true);
            
        }, capNhatNhanVien, chamCongNhanVien, tinhLuongNhanVien, timKiemNhanVien);
        
        // menu Công nhân
        menuCongNhan = new MenuItem(iconCongNhan, lblCongNhan, (ActionEvent e) -> {
            pnBody.repaint();
            pnBody.revalidate();
            setNonSelectMenu(menuTrangChu, menuHopDong, menuPhongBan, menuToNhom, menuNhanVien, menuCongNhan, menuSanPham, menuThongKe, menuHoTro,menuHeThong);
            setSelectMenu(menuCongNhan);
        }, capNhatCongNhan,phanCongCongNhan, chamCongCongNhan, tinhLuongCongNhan, timKiemCongNhan);
        
        // menu Thống kê
        menuThongKe = new MenuItem(iconThongKe, lblThongKe, (ActionEvent e) -> {
            pnBody.repaint();
            pnBody.revalidate();
            setNonSelectMenu(menuTrangChu, menuHopDong, menuPhongBan, menuToNhom, menuNhanVien, menuCongNhan, menuSanPham, menuThongKe, menuHoTro,menuHeThong);
            setSelectMenu(menuThongKe);
        },thongKeDoanhThu,thongKeNhanVien,thongKeCongNhan);
        
        // menu Hỗ trợ
        menuHoTro = new MenuItem(iconHoTro, lblHoTro, (ActionEvent e) -> {
            pnBody.removeAll();
            try {
                pnBody.add(new HuongDanSuDung_GUI(fileName), BorderLayout.CENTER);
            } catch (Exception ex) {
                Logger.getLogger(Main_GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            pnBody.repaint();
            pnBody.revalidate();
            setNonSelectMenu(menuTrangChu, menuHopDong, menuPhongBan, menuToNhom, menuNhanVien, menuCongNhan, menuSanPham, menuThongKe, menuHoTro,menuHeThong);
            setSelectMenu(menuHoTro);
        });
        
        // menu Tài khoản
        menuHeThong = new MenuItem(iconHeThong, lblHeThong, (ActionEvent e) -> {
            pnBody.repaint();
            pnBody.revalidate();
            setNonSelectMenu(menuTrangChu, menuHopDong, menuPhongBan, menuToNhom, menuNhanVien, menuCongNhan, menuSanPham, menuThongKe, menuHoTro,menuHeThong);
            setSelectMenu(menuHeThong);
        },thongTinCaNhan,doiMatKhau,dangXuat);
        
        addMenu(menuTrangChu,menuPhongBan,menuToNhom,menuHopDong,menuSanPham,menuNhanVien,menuCongNhan,menuThongKe,menuHoTro, menuHeThong);
   
        
        
        // menu nhân viên khi KT đăng nhập
        menuNhanVienKT = new MenuItem(iconNhanVien, lblNhanVien, (ActionEvent e) -> {
            pnBody.repaint();
            pnBody.revalidate();
            setNonSelectMenu(menuTrangChu, menuHopDong, menuPhongBan, menuToNhom, menuNhanVienKT, menuCongNhanKT, menuSanPham, menuThongKe, menuHoTro, menuHeThong);
            setSelectMenu(menuNhanVienKT);
            
        },tinhLuongNhanVien, timKiemNhanVien);
        
        // menu Công nhân khi KT đăng nhập
        menuCongNhanKT = new MenuItem(iconCongNhan, lblCongNhan, (ActionEvent e) -> {
            pnBody.repaint();
            pnBody.revalidate();
            setNonSelectMenu(menuTrangChu, menuHopDong, menuPhongBan, menuToNhom, menuNhanVienKT, menuCongNhanKT, menuSanPham, menuThongKe, menuHoTro, menuHeThong);
            setSelectMenu(menuCongNhanKT);
        }, tinhLuongCongNhan, timKiemCongNhan);
        
        // menu Công nhân khi NV đăng nhập
        menuCongNhanNV = new MenuItem(iconCongNhan, lblCongNhan, (ActionEvent e) -> {
            pnBody.repaint();
            pnBody.revalidate();
            menuCongNhan.setVisible(false);
            setNonSelectMenu(menuTrangChu, menuHopDong, menuPhongBan, menuToNhom, menuNhanVien, menuCongNhanNV, menuSanPham, menuThongKe, menuHoTro, menuHeThong);
            setSelectMenu(menuCongNhanNV);
        }, capNhatCongNhan,phanCongCongNhan, chamCongCongNhan, timKiemCongNhan);
        
        if (this.userName.contains("PPNV")) {
            pnBody.removeAll();
            pnBody.add(new TrangChu_GUI(), BorderLayout.CENTER);
            pnBody.repaint();
            pnBody.revalidate();
            setSelectMenu(menuTrangChu);
            NhanVien_Dao nhanVienDao = new NhanVien_Dao();
            NhanVien nhanVien = nhanVienDao.layMotNhanVienTheoMaNhanVien(userName);
            if (nhanVien.getChucVu().equalsIgnoreCase("Quản lý")) {
                addMenu(menuTrangChu, menuHopDong, menuSanPham, menuNhanVien, menuCongNhan, menuPhongBan, menuToNhom, menuThongKe, menuHoTro, menuHeThong);
            }
            else if (nhanVien.getChucVu().equalsIgnoreCase("Nhân viên kế toán")) {
                addMenu(menuTrangChu, menuPhongBan, menuToNhom, menuHopDong, menuSanPham, menuNhanVienKT, menuCongNhanKT, menuThongKe, menuHoTro, menuHeThong);
                menuToNhom.setVisible(false);
                menuPhongBan.setVisible(false);
                menuHopDong.setVisible(false);
                menuSanPham.setVisible(false);
                menuNhanVien.setVisible(false);
                menuCongNhan.setVisible(false);
                menuHoTro.setVisible(false);                
            } else {
                addMenu(menuTrangChu, menuPhongBan, menuToNhom, menuHopDong, menuSanPham, menuNhanVien, menuCongNhanNV, menuThongKe, menuHoTro, menuHeThong);
                menuToNhom.setVisible(false);
                menuPhongBan.setVisible(false);
                menuHopDong.setVisible(false);
                menuNhanVien.setVisible(false);
                menuCongNhan.setVisible(false);
                menuThongKe.setVisible(false);
                menuHoTro.setVisible(false);
            }
        }        
    }
	
    public void iconSubMenuMacDinh(MenuItem menu) {
        resetSelect();
        menu.setIcon(iconSubMenuSelect);
    }
    
    public void resetSelect() {
    	/// san pham
        capNhatSanPham.setIcon(iconSubMenuNonSelect);
        phanCongDoanSanPham.setIcon(iconSubMenuNonSelect);
        timKiemSanPham.setIcon(iconSubMenuNonSelect);
        
    	// nhan vien
        capNhatNhanVien.setIcon(iconSubMenuNonSelect);
        chamCongNhanVien.setIcon(iconSubMenuNonSelect);
        tinhLuongNhanVien.setIcon(iconSubMenuNonSelect);
        timKiemNhanVien.setIcon(iconSubMenuNonSelect);
        phanCongCongNhan.setIcon(iconSubMenuNonSelect);
    	
        // công nhân
        capNhatCongNhan.setIcon(iconSubMenuNonSelect);
        chamCongCongNhan.setIcon(iconSubMenuNonSelect);
        tinhLuongCongNhan.setIcon(iconSubMenuNonSelect);
        timKiemCongNhan.setIcon(iconSubMenuNonSelect);
        
        // thong ke
        thongKeDoanhThu.setIcon(iconSubMenuNonSelect);
        thongKeNhanVien.setIcon(iconSubMenuNonSelect);
        thongKeCongNhan.setIcon(iconSubMenuNonSelect);
        
        // he thong
        thongTinCaNhan.setIcon(iconSubMenuNonSelect);
        doiMatKhau.setIcon(iconResetPassword);
        dangXuat.setIcon(iconLogOut);

    }
	private void addMenu(MenuItem... menu) {
        for (MenuItem menu1 : menu) {
            menus.add(menu1);
            ArrayList<MenuItem> subMenu = menu1.getSubMenu();
            subMenu.forEach(menuItem -> {
                addMenu(menuItem);
            });
        }
        menus.revalidate();
    }
	
	public void setSelectMenu(JPanel pnelSelect) {
        pnelSelect.setBackground(new Color(0, 206, 201));
        for (Component jc : pnelSelect.getComponents()) {
            if (jc instanceof JLabel) {
                JLabel label = (JLabel) jc;
                label.setForeground(new Color(255, 255, 255));
                label.setFont(new Font("Times New Roman", Font.BOLD, 16));
            }
        }
    }

    public void setNonSelectMenu(JPanel... jPanels) {
        for (JPanel jPanel : jPanels) {
            jPanel.setBackground(new Color(255, 255, 255));
            for (Component jc : jPanel.getComponents()) {
                if (jc instanceof JLabel) {
                    JLabel label = (JLabel) jc;
                    label.setForeground(new Color(12, 12, 12));
                    label.setFont(new Font("Times New Roman", Font.BOLD, 16));
                }
            }
        }
    }

}

