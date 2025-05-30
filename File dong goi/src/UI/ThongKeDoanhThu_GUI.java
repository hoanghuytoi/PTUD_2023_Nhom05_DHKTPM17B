package UI;

import Dao.BangLuongCongNhan_Dao;
import Dao.BangLuongNhanVien_Dao;
import Dao.HopDong_Dao;
import Entity.BangLuongCongNhan;
import Entity.BangLuongNhanVien;
import Entity.HopDong;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JTable;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ThongKeDoanhThu_GUI extends javax.swing.JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String fileName;
	private JFreeChart chart;
	
	private JComboBox<String> cmbNam;
	private JPanel pnBarChar;
	private JLabel lblBangThongKeNV;
	private JPanel pnBarCharCN;
	private ChartPanel ChartPnlDT;
	private JPanel pnBarCharDT;
	private JLabel lblBangThongKeCN;
	private JLabel lblTieuDeDoanhThu;
	private JLabel lblNam;
	private JLabel lblBangThongKeDT;

	public ThongKeDoanhThu_GUI(String fileName) throws IOException {
		this.fileName = fileName;
		initComponents();
		cmbNam.removeAllItems();
		for (int i = 2022; i <= LocalDate.now().getYear(); i++) {
			cmbNam.addItem(i + "");
		}
		cmbNam.setSelectedItem(LocalDate.now().getYear() + "");
		
		excute();
		caiDatNgonNgu(fileName);
		thongKeTongLuongNhanVienTheoThangNam();
		thongKeTongLuongCongNhanTheoThang();
		thongKeTongDoanhThuTheoThang();
	}

	public void caiDatNgonNgu(String fileName) throws FileNotFoundException, IOException {
		FileInputStream fis = new FileInputStream(fileName);
        Properties prop = new Properties();
        prop.load(fis);
		lblNam.setText(prop.getProperty("lnv_nam"));
        lblTieuDeDoanhThu.setText(prop.getProperty("TieuDe_doanhThu"));
        lblBangThongKeNV.setText(prop.getProperty("TieuDe_thongKeNV"));
        lblBangThongKeCN.setText(prop.getProperty("TieuDe_thongKeCN"));
        lblBangThongKeDT.setText(prop.getProperty("TieuDe_thongKeDoanhThu"));
	}

	public void ChangeName(JTable table, int col_index, String col_name) {
        table.getColumnModel().getColumn(col_index).setHeaderValue(col_name);
    }

	public void excute() {
	}
	
	private void initComponents() {
		cmbNam = new javax.swing.JComboBox<>();
		cmbNam.setBounds(128, 181, 80, 40);
		cmbNam.setFont(new Font("Times New Roman", Font.PLAIN, 18));

		setBackground(new java.awt.Color(255, 255, 255));
		setPreferredSize(new java.awt.Dimension(1200, 700));

		cmbNam.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2023" }));
		cmbNam.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cmbNamActionPerformed(evt);
			}
		});
		setLayout(null);
		add(cmbNam);
		
		ChartPanel barpChartPanel = new ChartPanel(chart);
		barpChartPanel.setBounds(10, 376, 595, 284);
		barpChartPanel.setBackground(new Color(255, 255, 255));
		add(barpChartPanel);
		barpChartPanel.setLayout(null);
		pnBarChar = new javax.swing.JPanel();
		pnBarChar.setBounds(10, 5, 585, 268);
		barpChartPanel.add(pnBarChar);

		pnBarChar.setLayout(new java.awt.BorderLayout());

		lblBangThongKeNV = new JLabel("BẢNG THỐNG KÊ TỔNG TIỀN PHẢI CHI TRẢ CHO NHÂN VIÊN TRONG THÁNG");
		lblBangThongKeNV.setBounds(103, 659, 454, 30);
		lblBangThongKeNV.setFont(new Font("Times New Roman", Font.BOLD, 12));
		add(lblBangThongKeNV);
		
		ChartPanel ChartPnlCN = new ChartPanel((JFreeChart) null);
		ChartPnlCN.setBounds(653, 376, 537, 284);
		ChartPnlCN.setLayout(null);
		ChartPnlCN.setBackground(Color.WHITE);
		add(ChartPnlCN);
		
		pnBarCharCN = new JPanel();
		pnBarCharCN.setBounds(10, 5, 527, 268);
		ChartPnlCN.add(pnBarCharCN);
		pnBarCharCN.setLayout(new BorderLayout());
		
		lblBangThongKeCN = new JLabel("BẢNG THỐNG KÊ TỔNG TIỀN PHẢI CHI TRẢ CHO CÔNG NHÂN TRONG THÁNG");
		lblBangThongKeCN.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblBangThongKeCN.setBounds(687, 659, 471, 30);
		add(lblBangThongKeCN);
		
		lblTieuDeDoanhThu = new JLabel("THỐNG KÊ DOANH THU CÔNG TY");
		lblTieuDeDoanhThu.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblTieuDeDoanhThu.setBounds(433, 11, 419, 40);
		add(lblTieuDeDoanhThu);
		
		ChartPnlDT = new ChartPanel((JFreeChart) null);
		ChartPnlDT.setLayout(null);
		ChartPnlDT.setBackground(Color.WHITE);
		ChartPnlDT.setBounds(269, 91, 760, 284);
		add(ChartPnlDT);
		
		pnBarCharDT = new JPanel();
		pnBarCharDT.setBounds(10, 0, 740, 279);
		ChartPnlDT.add(pnBarCharDT);
		pnBarCharDT.setLayout(new BorderLayout());
		
		lblNam = new JLabel("Năm:");
		lblNam.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNam.setBounds(59, 187, 59, 29);
		add(lblNam);
		
		lblBangThongKeDT = new JLabel("BẢNG THỐNG KÊ TỔNG DOANH THU CỦA CÔNG TY TRONG THÁNG");
		lblBangThongKeDT.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblBangThongKeDT.setBounds(460, 72, 404, 30);
		add(lblBangThongKeDT);
	}

	private void cmbNamActionPerformed(java.awt.event.ActionEvent evt) {
		try {
			thongKeTongDoanhThuTheoThang();
            thongKeTongLuongNhanVienTheoThangNam();
            thongKeTongLuongCongNhanTheoThang();
        } catch (IOException ex) {
            Logger.getLogger(ThongKeDoanhThu_GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
	}

	public void thongKeTongLuongNhanVienTheoThangNam() throws FileNotFoundException, IOException {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        BangLuongNhanVien_Dao luongNhanVienDao = new BangLuongNhanVien_Dao();
        ArrayList<BangLuongNhanVien> bangLuongList = luongNhanVienDao.danhSachBangLuong();
        for (int i = 1; i < 13; i++) {
            String nam = "2022";
            try {
                if (cmbNam.getSelectedItem().toString() == null) {
                    nam = "2022";
                } else {
                    nam = cmbNam.getSelectedItem().toString();
                }
            } catch (Exception e) {
            }
            String value = i + "-" + nam;
            if (i < 10) {
                value = "0" + i + "-" + nam;
            }
            double tongLuong = 0;
            for (BangLuongNhanVien e : bangLuongList) {
                if (e.getLuongTheoThang().equalsIgnoreCase(value)) {
                    tongLuong += e.getThucLanh();
                }
            }
            dataset.setValue(new BigDecimal(tongLuong), "Amount","T"+ i + "");
        }
        FileInputStream fis = new FileInputStream(fileName);
        Properties prop = new Properties();
        prop.load(fis);
        JFreeChart chart = ChartFactory.createBarChart(prop.getProperty("tk_tieudeThongeKeNhanVien"), prop.getProperty("tk_thang"), prop.getProperty("tk_soTien"),
                dataset, PlotOrientation.VERTICAL, false, true, false);

        CategoryPlot categoryPlot = chart.getCategoryPlot();
        categoryPlot.setBackgroundPaint(Color.WHITE);
        BarRenderer renderer = (BarRenderer) categoryPlot.getRenderer();
        Color clr3 = new Color(204, 0, 51);
        renderer.setSeriesPaint(0, clr3);

        ChartPanel barpChartPanel = new ChartPanel(chart);
        pnBarChar.removeAll();
        pnBarChar.add(barpChartPanel, BorderLayout.CENTER);
        pnBarChar.validate();

    }
	
	public void thongKeTongLuongCongNhanTheoThang() throws FileNotFoundException, IOException  {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        BangLuongCongNhan_Dao bangLuongCongNhanDao = new BangLuongCongNhan_Dao();
        ArrayList<BangLuongCongNhan> bangLuongCongNhanList = bangLuongCongNhanDao.layDanhSachBangLuongCongNhan();
        if (bangLuongCongNhanList != null) {
            for (int i = 1; i < 13; i++) {
                String nam = "2022";
                try {
                    if (cmbNam.getSelectedItem().toString() == null) {
                        nam = "2022";
                    } else {
                        nam = cmbNam.getSelectedItem().toString();
                    }
                } catch (Exception e) {
                }
                String value = i + "-" + nam;
                if (i < 10) {
                    value = "0" + i + "-" + nam;
                }
                double tongLuong = 0;
                for (BangLuongCongNhan e : bangLuongCongNhanList) {
                    String ngayTinhLuong = e.getNgayTinh().toString().split("-")[1] + "-" + e.getNgayTinh().toString().split("-")[0];
                    System.out.println(ngayTinhLuong);
                    if (ngayTinhLuong.equalsIgnoreCase(value)) {
                        tongLuong += e.getTongLuong();
                    }
                }
                dataset.setValue(new BigDecimal(tongLuong), "Amount","T" + i + "");
            }
        }

        FileInputStream fis = new FileInputStream(fileName);
        Properties prop = new Properties();
        prop.load(fis);
        JFreeChart chart = ChartFactory.createBarChart(prop.getProperty("tk_tieudeThongKeCongNhan"), prop.getProperty("tk_thang"), prop.getProperty("tk_soTien"),
                dataset, PlotOrientation.VERTICAL, false, true, false);

        CategoryPlot categoryPlot = chart.getCategoryPlot();
        categoryPlot.setBackgroundPaint(Color.WHITE);
        BarRenderer renderer = (BarRenderer) categoryPlot.getRenderer();
        Color clr3 = new Color(204, 0, 51);
        renderer.setSeriesPaint(0, clr3);

        ChartPanel barpChartPanel = new ChartPanel(chart);
        pnBarCharCN.removeAll();
        pnBarCharCN.add(barpChartPanel, BorderLayout.CENTER);
        pnBarCharCN.validate();

    }
	
	public void thongKeTongDoanhThuTheoThang() throws FileNotFoundException, IOException {
	    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	    HopDong_Dao hopDongDao = new HopDong_Dao();
	    ArrayList<HopDong> dsHopDong = hopDongDao.layDanhSachHopDong();

	    for (int i = 1; i < 13; i++) {
	        double tongDoanhThu = 0;

	        for (HopDong hopDong : dsHopDong) {
	            Date ngayKyKet = (Date) hopDong.getNgayKyKetHD();
	            Calendar calendar = Calendar.getInstance();
	            calendar.setTime(ngayKyKet);

	            int thang = calendar.get(Calendar.MONTH) + 1;
	            int nam = calendar.get(Calendar.YEAR);

	            if (cmbNam.getSelectedItem() != null && thang == i && nam == Integer.parseInt(cmbNam.getSelectedItem().toString())) {
	                tongDoanhThu += hopDong.getTriGiaHD();
	            }
	        }
            dataset.setValue(new BigDecimal(tongDoanhThu), "Amount","T" + i + "");
	    }



	    FileInputStream fis = new FileInputStream(fileName);
	    Properties prop = new Properties();
	    prop.load(fis);

	    JFreeChart chart = ChartFactory.createBarChart(
	            prop.getProperty("tk_tieudeThongKeDoanhThu"),
	            prop.getProperty("tk_thang"),
	            prop.getProperty("tk_soTien"),
	            dataset, PlotOrientation.VERTICAL, false, true, false);

	    CategoryPlot categoryPlot = chart.getCategoryPlot();
	    categoryPlot.setBackgroundPaint(Color.WHITE);

	    BarRenderer renderer = (BarRenderer) categoryPlot.getRenderer();
	    Color clr3 = new Color(204, 0, 51);
	    renderer.setSeriesPaint(0, clr3);

	    ChartPanel barpChartPanel = new ChartPanel(chart);
	    pnBarCharDT.removeAll();
	    pnBarCharDT.add(barpChartPanel, BorderLayout.CENTER);
	    pnBarCharDT.validate();
	}
}
