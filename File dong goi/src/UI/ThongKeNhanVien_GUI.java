package UI;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import Dao.BangLuongNhanVien_Dao;
import Dao.NhanVien_Dao;
import Dao.PhongBan_Dao;
import Dao.ThongKeNhanVien_Dao;
import Entity.BangLuongNhanVien;
import Entity.NhanVien;
import Entity.PhongBan;

public class ThongKeNhanVien_GUI extends JPanel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblTieuDe;
	private ChartPanel pnBarChar;
	private JComboBox<String> cmbNam;
	private JComboBox<String> cmbThang;
	private String fileName;
	private JButton btnThongKe;
	private JPanel pnHitogramChar;
	private JPanel pnPieChar;
	private JComboBox<String> cmbTC1;
	private JButton btnInBangThongKe;
	private JLabel lblThang;
	private JLabel lblNam;
	private JLabel lblBangThongKe;
	private JLabel lblTieuChi;


	public ThongKeNhanVien_GUI(String fileName) throws FileNotFoundException, IOException {
		this.fileName = fileName;
		setBackground(new Color(255, 255, 255));
		initComponents();
		cmbNam.removeAllItems();
		for (int i = 2022; i <= LocalDate.now().getYear(); i++) {
			cmbNam.addItem(i + "");
		}
		cmbNam.setSelectedItem(LocalDate.now().getYear() + "");
		btnThongKe.addActionListener(this);
		thongKeNhanVienTheoPhongBan();
		thongKeTiLeGioiTinhNhanVien();
		caiDatNgonNgu(fileName);
	}
	
	public void caiDatNgonNgu(String fileName) throws FileNotFoundException, IOException {
		FileInputStream fis = new FileInputStream(fileName);
        Properties prop = new Properties();
        prop.load(fis);
        lblThang.setText(prop.getProperty("lnv_thang"));
		lblNam.setText(prop.getProperty("lnv_nam"));
        lblTieuDe.setText(prop.getProperty("TieuDe_ThongKeNV"));
        lblTieuChi.setText(prop.getProperty("thongKeNV_TieuChi"));
        btnThongKe.setText(prop.getProperty("btnThongKe"));
        btnInBangThongKe.setText(prop.getProperty("btnInBangThongKe"));
        lblBangThongKe.setText(prop.getProperty("TieuDe_lblBangThongKe"));

	}

	public void ChangeName(JTable table, int col_index, String col_name) {
        table.getColumnModel().getColumn(col_index).setHeaderValue(col_name);
    }
	
	private void initComponents() {
		setPreferredSize(new Dimension(1290, 750));
		setLayout(null);
		lblTieuDe = new JLabel("THỐNG KÊ LƯƠNG NHÂN VIÊN");
		lblTieuDe.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblTieuDe.setBounds(408, 11, 607, 30);
		add(lblTieuDe);

		lblTieuChi = new JLabel("Tiêu chí:");
		lblTieuChi.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblTieuChi.setBounds(216, 129, 62, 21);
		add(lblTieuChi);

		cmbTC1 = new JComboBox<String>();
		cmbTC1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		cmbTC1.setModel(new DefaultComboBoxModel<String>(new String[] {"Top 5 nhân viên có lương cao nhất","Top 5 nhân viên có lương thấp nhất"}));
		cmbTC1.setBounds(288, 124, 246, 30);
		add(cmbTC1);

		lblThang = new JLabel("Tháng:");
		lblThang.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblThang.setBounds(216, 76, 62, 17);
		add(lblThang);

		lblNam = new JLabel("Năm:");
		lblNam.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNam.setBounds(408, 77, 62, 14);
		add(lblNam);

		cmbThang = new JComboBox<String>();
		cmbThang.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		cmbThang.setModel(new DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
		cmbThang.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cmbThangActionPerformed(evt);
			}
		});
		cmbThang.setBounds(288, 69, 74, 30);
		add(cmbThang);

		cmbNam = new JComboBox<String>();
		cmbNam.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		cmbNam.setBounds(460, 69, 74, 30);
		cmbNam.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2023" }));
		cmbNam.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cmbNamActionPerformed(evt);
			}
		});
		add(cmbNam);

		btnThongKe = new JButton();
		btnThongKe.setIcon(new ImageIcon(ThongKeNhanVien_GUI.class.getResource("/image/icon/thongK.png")));
		btnThongKe.setText("Thống kê");
		btnThongKe.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnThongKe.setBorder(null);
		btnThongKe.setBackground(new Color(255, 215, 0));
		btnThongKe.setBounds(614, 122, 171, 32);
		add(btnThongKe);

		btnInBangThongKe = new JButton();
		btnInBangThongKe.setIcon(new ImageIcon(ThongKeNhanVien_GUI.class.getResource("/image/icon/in.png")));
		btnInBangThongKe.setText("In bảng thống kê");
		btnInBangThongKe.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnInBangThongKe.setBorder(null);
		btnInBangThongKe.setBackground(new Color(255, 215, 0));
		btnInBangThongKe.setBounds(840, 122, 191, 32);
		btnInBangThongKe.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnInBangThongKeActionPerformed(evt);
			}
		});
		add(btnInBangThongKe);

		pnBarChar = new ChartPanel((JFreeChart) null);
		pnBarChar.setBackground(new Color(255, 255, 255));
		pnBarChar.setBounds(33, 224, 700, 436);
		add(pnBarChar);

		pnHitogramChar = new JPanel();
		pnHitogramChar.setBounds(784, 189, 470, 250);
		add(pnHitogramChar);
		pnHitogramChar.setLayout(new BorderLayout());

		pnPieChar = new JPanel();
		pnPieChar.setBounds(786, 450, 468, 244);
		add(pnPieChar);
		pnPieChar.setLayout(new BorderLayout());

		lblBangThongKe = new JLabel("BẢNG THỐNG KÊ LƯƠNG NHÂN VIÊN");
		lblBangThongKe.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblBangThongKe.setBounds(176, 664, 418, 30);
		add(lblBangThongKe);

	}
	
	
	private void cmbThangActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub

	}
	private void cmbNamActionPerformed(java.awt.event.ActionEvent evt) {
		try {
			thongKeTongLuongNhanVienTheoThangNam();
		} catch (IOException ex) {
			Logger.getLogger(ThongKeNhanVien_GUI.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void thongKeNhanVienTheoPhongBan() throws FileNotFoundException, IOException {
		PhongBan_Dao phongBanDao = new PhongBan_Dao();
		ArrayList<PhongBan> phongBanList = phongBanDao.layDanhSachPhongBan();
		DefaultPieDataset barDataset = new DefaultPieDataset();
		if (phongBanList != null) {
			phongBanList.forEach(e -> extracted(barDataset, e));
		}
		FileInputStream fis = new FileInputStream(fileName);
		Properties prop = new Properties();
		prop.load(fis);
		JFreeChart piechart = ChartFactory.createPieChart(prop.getProperty("tk_tieudeThongeKeNhanVienPhongBan"), barDataset, true, true, true);
		PiePlot piePlot = (PiePlot) piechart.getPlot();
		piePlot.setBackgroundPaint(Color.white);
		ChartPanel barChartPanel = new ChartPanel(piechart);
		pnHitogramChar.removeAll();
		pnHitogramChar.add(barChartPanel, BorderLayout.CENTER);
		pnHitogramChar.validate();
	}

	@SuppressWarnings("removal")
	private void extracted(DefaultPieDataset barDataset, PhongBan e) {
		barDataset.setValue(e.getTenPhongBan(), new Double(e.getSoLuongNhanVien()));
	}

	public void thongKeTiLeGioiTinhNhanVien() throws FileNotFoundException, IOException {
		FileInputStream fis = new FileInputStream(fileName);
		Properties prop = new Properties();
		prop.load(fis);
		NhanVien_Dao daoNhanVien = new NhanVien_Dao();
		ArrayList<NhanVien> nhanVienList = daoNhanVien.layDanhSachNhanVien();
		double soLuongNam = 0;
		double soLuongNu = 0;
		if (nhanVienList != null) {
			for (NhanVien nhanVien : nhanVienList) {
				if (nhanVien.isGioiTinh()) {
					soLuongNam++;
				} else {
					soLuongNu++;
				}
			}
		}
		DefaultPieDataset barDataset = new DefaultPieDataset();
		String nam =prop.getProperty("nam");
		String nu =prop.getProperty("nu");
		barDataset.setValue(nam, soLuongNam);
		barDataset.setValue(nu, soLuongNu);
		JFreeChart piechart = ChartFactory.createPieChart(prop.getProperty("gioiTinh"), barDataset, true, true, true);
		PiePlot piePlot = (PiePlot) piechart.getPlot();
		piePlot.setBackgroundPaint(Color.white);
		ChartPanel barChartPanel = new ChartPanel(piechart);
		pnPieChar.removeAll();
		pnPieChar.add(barChartPanel, BorderLayout.CENTER);
		pnPieChar.validate();
	}
	
	public void thongKeTongLuongNhanVienTheoThangNam() throws FileNotFoundException, IOException {
	    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	    BangLuongNhanVien_Dao luongNhanVienDao = new BangLuongNhanVien_Dao();

	    String selectedThang = (String) cmbThang.getSelectedItem();
	    String selectedNam = (String) cmbNam.getSelectedItem();

	    ArrayList<BangLuongNhanVien> bangLuongList = luongNhanVienDao.danhSachBangLuongTheoThangNam(selectedThang, selectedNam);

	    double tongLuong = 0;
	    String manv = "";

	    for (BangLuongNhanVien e1 : bangLuongList) {
	        tongLuong = e1.getThucLanh();
	        manv = e1.getNhanVien().getHoTen();
	        dataset.setValue(new BigDecimal(tongLuong), "Luong", manv);
	    }

	    FileInputStream fis = new FileInputStream(fileName);
	    Properties prop = new Properties();
	    prop.load(fis);

	    JFreeChart chart = ChartFactory.createBarChart(
	            prop.getProperty("tk_tieudeThongeKeNhanVien"),
	            prop.getProperty("tk_thang"),
	            prop.getProperty("tk_soTien"),
	            dataset, PlotOrientation.VERTICAL, false, true, false);

	    CategoryPlot categoryPlot = chart.getCategoryPlot();
	    categoryPlot.setBackgroundPaint(Color.WHITE);
	    BarRenderer renderer = (BarRenderer) categoryPlot.getRenderer();
	    Color clr3 = new Color(204, 0, 51);
	    renderer.setSeriesPaint(0, clr3);

	    ChartPanel barpChartPanel = new ChartPanel(chart);
	    barpChartPanel.setBounds(10, 11, 680, 420);

	    try {
	        pnBarChar.removeAll();
	        pnBarChar.setLayout(null);
	        pnBarChar.add(barpChartPanel);
	        pnBarChar.revalidate();
	        pnBarChar.repaint();

	    } catch (RuntimeException ex) {
	        ex.printStackTrace();
	    }
	}
	
	private void btnInBangThongKeActionPerformed(java.awt.event.ActionEvent evt) {
	    try {
	    	
	        BufferedImage chartImage = new BufferedImage(pnBarChar.getWidth(), pnBarChar.getHeight(), BufferedImage.TYPE_INT_RGB);
	        Graphics2D g2 = chartImage.createGraphics();
	        pnBarChar.paint(g2);
	        g2.dispose();

	        // Print the chart image
	        PrinterJob job = PrinterJob.getPrinterJob();
	        job.setPrintable(new ImagePrintable(job, chartImage, "Bảng Thống Kê Lương Nhân Viên"));
	        boolean complete = job.printDialog();
	        if (complete) {
	            job.print();
	            JOptionPane.showMessageDialog(this, "Bảng thống kê đã được in thành công.", "In Bảng Thống Kê", JOptionPane.INFORMATION_MESSAGE);
	        } else {
	            JOptionPane.showMessageDialog(this, "In bảng thống kê bị hủy.", "In Bảng Thống Kê", JOptionPane.WARNING_MESSAGE);
	        }
	    } catch (PrinterException ex) {
	        JOptionPane.showMessageDialog(this, "Lỗi khi in bảng thống kê: " + ex.getMessage(), "Lỗi In Bảng Thống Kê", JOptionPane.ERROR_MESSAGE);
	    }
	}

	class ImagePrintable implements Printable {
	    private BufferedImage image;
	    private String title;

	    public ImagePrintable(PrinterJob printJob, BufferedImage chartImage, String title) {
	        this.image = chartImage;
	        this.title = title;
	    }

	    @Override
	    public int print(Graphics g, PageFormat pageFormat, int pageIndex) throws PrinterException {
	        if (pageIndex > 0) {
	            return NO_SUCH_PAGE;
	        }

	        Graphics2D g2d = (Graphics2D) g;
	        g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

	        double scaleX = pageFormat.getImageableWidth() / image.getWidth();
	        double scaleY = pageFormat.getImageableHeight() / image.getHeight();
	        double scale = Math.min(scaleX, scaleY);

	        g2d.scale(scale, scale);
	        g2d.drawImage(image, 0, 0, null);

	        // Add title
	        Font titleFont = new Font("SansSerif", Font.BOLD, 12);
	        FontMetrics titleFontMetrics = g2d.getFontMetrics(titleFont);
	        int titleWidth = titleFontMetrics.stringWidth(title);

	        g2d.setColor(Color.BLACK);
	        g2d.setFont(titleFont);
	        g2d.drawString(title, (int) ((pageFormat.getImageableWidth() - titleWidth) / 2), 20);

	        return PAGE_EXISTS;
	    }
	}
	
	
	private void thongKeTop5LuongNhanVienCaoNhat() {
	    try {
	        if (cmbTC1.getSelectedIndex() == 0) {
	            ThongKeNhanVien_Dao luongNhanVienDao = new ThongKeNhanVien_Dao();
	            ArrayList<BangLuongNhanVien> top5LuongList = luongNhanVienDao.layTop5NhanVienLuongCaoNhat();
	            DefaultCategoryDataset dataset = new DefaultCategoryDataset();

	            for (BangLuongNhanVien luong : top5LuongList) {
	                String hoTen = luong.getNhanVien().getHoTen();
	                double thucLanh = luong.getThucLanh();
	                dataset.addValue(thucLanh, "Luong", hoTen);
	            }
	            updateBarChart(dataset);
	        } else {
	        }
	    } catch (Exception ex) {
	        ex.printStackTrace(System.out);
	    }
	}

	private void updateBarChart(DefaultCategoryDataset dataset) {
	    FileInputStream fis = null;
	    try {
	        fis = new FileInputStream(fileName);
	        Properties prop = new Properties();
	        prop.load(fis);

	        JFreeChart chart = ChartFactory.createBarChart(
	                prop.getProperty("tk_tieudeThongeKeNhanVien"),
	                prop.getProperty("tk_thang"),
	                prop.getProperty("tk_soTien"),
	                dataset, PlotOrientation.VERTICAL, false, true, false);

	        CategoryPlot categoryPlot = chart.getCategoryPlot();
	        categoryPlot.setBackgroundPaint(Color.WHITE);
	        BarRenderer renderer = (BarRenderer) categoryPlot.getRenderer();
	        Color clr3 = new Color(204, 0, 51);
	        renderer.setSeriesPaint(0, clr3);

	        ChartPanel barpChartPanel = new ChartPanel(chart);
	        barpChartPanel.setBounds(10, 11, 680, 420);

	        try {
	            pnBarChar.removeAll();
	            pnBarChar.setLayout(null);
	            pnBarChar.add(barpChartPanel);
	            pnBarChar.revalidate();
	            pnBarChar.repaint();

	        } catch (RuntimeException ex) {
	            ex.printStackTrace();
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        if (fis != null) {
	            try {
	                fis.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}

	private void thongKeTop5LuongNhanVienThapNhat() {
	    try {
	        if (cmbTC1.getSelectedIndex() == 1) {
	            ThongKeNhanVien_Dao luongNhanVienDao = new ThongKeNhanVien_Dao();
	            ArrayList<BangLuongNhanVien> top5LuongList = luongNhanVienDao.layTop5NhanVienLuongThapNhat();
	            DefaultCategoryDataset dataset = new DefaultCategoryDataset();

	            for (BangLuongNhanVien luong : top5LuongList) {
	                String hoTen = luong.getNhanVien().getHoTen();
	                double thucLanh = luong.getThucLanh();
	                dataset.addValue(thucLanh, "Luong", hoTen);
	            }
	            updateBarChartTop5ThapNhat(dataset);
	        } else {
	        }
	    } catch (Exception ex) {
	        ex.printStackTrace(System.out);
	    }
	}

	private void updateBarChartTop5ThapNhat(DefaultCategoryDataset dataset) {
	    FileInputStream fis = null;
	    try {
	        fis = new FileInputStream(fileName);
	        Properties prop = new Properties();
	        prop.load(fis);

	        JFreeChart chart = ChartFactory.createBarChart(
	                prop.getProperty("tk_tieudeThongeKeNhanVien"),
	                prop.getProperty("tk_thang"),
	                prop.getProperty("tk_soTien"),
	                dataset, PlotOrientation.VERTICAL, false, true, false);

	        CategoryPlot categoryPlot = chart.getCategoryPlot();
	        categoryPlot.setBackgroundPaint(Color.WHITE);
	        BarRenderer renderer = (BarRenderer) categoryPlot.getRenderer();
	        Color clr3 = new Color(204, 0, 51);
	        renderer.setSeriesPaint(0, clr3);

	        ChartPanel barpChartPanel = new ChartPanel(chart);
	        barpChartPanel.setBounds(10, 11, 680, 420);

	        try {
	            pnBarChar.removeAll();
	            pnBarChar.setLayout(null);
	            pnBarChar.add(barpChartPanel);
	            pnBarChar.revalidate();
	            pnBarChar.repaint();

	        } catch (RuntimeException ex) {
	            ex.printStackTrace();
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        if (fis != null) {
	            try {
	                fis.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
	    if (e.getSource() == btnThongKe) {
	        if (cmbTC1.getSelectedIndex() == 0) {
			    thongKeTop5LuongNhanVienCaoNhat();
			} else if (cmbTC1.getSelectedIndex() == 1) {
			    thongKeTop5LuongNhanVienThapNhat();
			} else {
			}
	    }
	}
}
