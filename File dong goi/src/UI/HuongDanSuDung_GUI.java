package UI;

import javax.swing.*;
import javax.swing.border.MatteBorder;

import Custom_UI.ScrollBarCustom;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class HuongDanSuDung_GUI extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblHDSD;
    private JPanel panel;
    private JLabel lblPhongBan;
    private JLabel lblToNhom;
    private JLabel lblHopDong;
    private JLabel lblSanPham;
    private JLabel lblNhanVien;
	private JLabel lblCongNhan;
	private JLabel lblThongKe;
	private JPanel panelBody;
	private JScrollPane scrollPane;

    public HuongDanSuDung_GUI(String fileName) throws IOException{
        setBackground(new Color(255, 255, 255));
        initComponents();
        showImages("/image/hdsd/PB.PNG");
		caiDatNgonNgu(fileName);
    }
    
    public void caiDatNgonNgu(String fileName) throws FileNotFoundException, IOException {
    	FileInputStream fis = new FileInputStream(fileName);
        Properties prop = new Properties();
        prop.load(fis);
        lblHDSD.setText(prop.getProperty("HDSD_TieuDe"));
        lblPhongBan.setText(prop.getProperty("Main_lblPhongBan"));
        lblToNhom.setText(prop.getProperty("Main_lblToNhom"));
        lblHopDong.setText(prop.getProperty("Main_lblHopDong"));
        lblSanPham.setText(prop.getProperty("Main_lblSanPham"));
        lblNhanVien.setText(prop.getProperty("Main_lblNhanVien"));
        lblCongNhan.setText(prop.getProperty("Main_lblCongNhan"));
        lblThongKe.setText(prop.getProperty("Main_lblThongKe"));
    }
	
	public void doiNgonNguTable(JTable table, int col_index, String col_name) {
        table.getColumnModel().getColumn(col_index).setHeaderValue(col_name);
    }

    private void initComponents() {
        setSize(1290, 750);
        setLayout(null);

        lblHDSD = new JLabel("HƯỚNG DẪN SỬ DỤNG ");
        lblHDSD.setIcon(new ImageIcon(HuongDanSuDung_GUI.class.getResource("/image/icon/hotro.png")));
        lblHDSD.setBounds(499, 11, 359, 51);
        lblHDSD.setHorizontalAlignment(SwingConstants.CENTER);
        lblHDSD.setFont(new Font("Times New Roman", Font.BOLD, 25));
        add(lblHDSD);

        panel = new JPanel();
        panel.setBackground(new Color(255, 255, 255));
        panel.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(255, 165, 0)));
        panel.setBounds(86, 83, 200, 618);
        add(panel);
        panel.setLayout(new GridLayout(0, 1, 0, 0));

        // Menu items
        lblPhongBan = createMenuItem("Phòng ban");
        lblToNhom = createMenuItem("Tổ nhóm");
        lblHopDong = createMenuItem("Hơp đồng");
        lblSanPham = createMenuItem("Sản phẩm");
        lblNhanVien = createMenuItem("Nhân viên");     
        lblCongNhan = createMenuItem("Công nhân");
        lblThongKe = createMenuItem("Thống kê");

        panel.add(lblPhongBan);
        lblPhongBan.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	showImages("/image/hdsd/PB.PNG");
            }
        });
        
        panel.add(lblToNhom);
        lblToNhom.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	showImages("/image/hdsd/TN.PNG");
            }
        });
        
        panel.add(lblHopDong);
        lblHopDong.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	showImages("/image/hdsd/HD.PNG");
            }
        });
        
        panel.add(lblSanPham);
        lblSanPham.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showImages("/image/hdsd/SP.PNG", "/image/hdsd/CDSP.PNG", "/image/hdsd/TKSP.PNG");
            }
        });
        
        panel.add(lblNhanVien);
        lblNhanVien.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showImages("/image/hdsd/NV.PNG", "/image/hdsd/CCNV.PNG", "/image/hdsd/LNV.PNG", "/image/hdsd/TKNV.PNG");
            }
        });
        
        panel.add(lblCongNhan);
        lblCongNhan.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showImages("/image/hdsd/CN.PNG", "/image/hdsd/PCCN.PNG", "/image/hdsd/CCCN.PNG", "/image/hdsd/LCN.PNG", "/image/hdsd/TKCN.PNG");
            }
        });
        
        panel.add(lblThongKe);
        lblThongKe.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showImages("/image/hdsd/TKDT.PNG", "/image/hdsd/TKeNV.PNG", "/image/hdsd/TKeCN.PNG");
            }
        });
        
        panelBody = new JPanel();
        panelBody.setBorder(new MatteBorder(3, 5, 3, 3, (Color) new Color(255, 215, 0)));
        panelBody.setBackground(new Color(255, 255, 255));
        panelBody.setLayout(new BorderLayout());

        scrollPane = new JScrollPane(panelBody);
        scrollPane.setBorder(null);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(286, 83, 948, 618);

        ScrollBarCustom scrollBar = new ScrollBarCustom();
		scrollBar.setForeground(Color.RED);
		scrollPane.setVerticalScrollBar(scrollBar);
		
        add(scrollPane);
    }

    private JLabel createMenuItem(String text) {
        JLabel lblMenuItem = new JLabel(" " + text);
        lblMenuItem.setIcon(new ImageIcon(HuongDanSuDung_GUI.class.getResource("/image/icon/dot.png")));
        lblMenuItem.setHorizontalAlignment(SwingConstants.LEFT);
        lblMenuItem.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
        lblMenuItem.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
        return lblMenuItem;
    }

    private void showImages(String... imagePaths) {
        panelBody.removeAll();

        JPanel imagePanel = new JPanel();
        imagePanel.setBackground(Color.WHITE);
        imagePanel.setLayout(new BoxLayout(imagePanel, BoxLayout.Y_AXIS)); // Sử dụng BoxLayout cho sắp xếp theo chiều dọc

        for (String imagePath : imagePaths) {
            JLabel imageLabel = new JLabel();
            ImageIcon imageIcon = new ImageIcon(getClass().getResource(imagePath));
            imageLabel.setIcon(imageIcon);

            // Tạo một Box để chứa imageLabel và canh giữa nó
            Box box = Box.createHorizontalBox();
            box.add(Box.createHorizontalGlue()); // Canh giữa
            box.add(imageLabel);
            box.add(Box.createHorizontalGlue()); // Canh giữa

            imagePanel.add(box);

            // Thêm khoảng cách tùy chọn giữa các ảnh
            imagePanel.add(Box.createRigidArea(new Dimension(0, 10))); // Điều chỉnh khoảng cách nếu cần thiết
        }

        // Đưa imagePanel vào JScrollPane và thêm vào panelBody
        JScrollPane scrollPane = new JScrollPane(imagePanel);
        panelBody.add(scrollPane, BorderLayout.CENTER);

        panelBody.revalidate();
        panelBody.repaint();
    }

}
