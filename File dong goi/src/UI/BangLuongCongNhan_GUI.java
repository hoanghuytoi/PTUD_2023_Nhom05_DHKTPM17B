package UI;

import Entity.BangLuongCongNhan;
import XuatFile.xuatIreport;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Custom_UI.ScrollBarCustom;
import Dao.BangLuongCongNhan_Dao;
import Dao.ChamCongCongNhan_Dao;

public class BangLuongCongNhan_GUI extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnChiTiet;
	private JButton btnTinhLuong;
	private JButton btnInBangLuong;
	private JComboBox<String> cmbHienThi;
	private JComboBox<String> cmbNamTinh;
	private JComboBox<String> cmbThangTinh;
	private JScrollPane scrBangLuong;
	private JTable tblBangLuong;

	private DefaultTableModel modelTableChamCong;
	private BangLuongCongNhan_Dao bangLuongCN_DAO;
	private DecimalFormat nf;
	private String stTinhLuongThanhCong;
	private String stTinhLuongThatBai;
	private JLabel lblTieuDe;
	private JLabel lblHienThi;
	private JLabel lblThang;
	private JLabel lblNam;

	public BangLuongCongNhan_GUI(String fileName) throws IOException {
		initComponents();
		excute();
		nf = new DecimalFormat("#,###.00");
		new DecimalFormat("#");
		modelTableChamCong = (DefaultTableModel) tblBangLuong.getModel();
		try {
			ConnectionDB.ConnectDB.getInstance().connect();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		new ChamCongCongNhan_Dao();
		bangLuongCN_DAO = new BangLuongCongNhan_Dao();
		btnTinhLuong.addActionListener(this);
		btnInBangLuong.addActionListener(this);
		btnInBangLuong.setEnabled(false);
		cmbNamTinh.removeAllItems();
		LocalDate lcDate = LocalDate.now();
		for (int i = 2023; i <= lcDate.getYear(); i++) {
			cmbNamTinh.addItem(i + "");
		}
		caiDatNgonNguChoView(fileName);
		cmbThangTinh.addItemListener(this::checkThangChon);
		cmbHienThi.addItemListener(this::hienThiBangLuongTheoNgay);
		taiDuLieuLenBang();
	}

	public void caiDatNgonNguChoView(String fileName) throws FileNotFoundException, IOException {
        FileInputStream fis = new FileInputStream(fileName);
        Properties prop = new Properties();
        prop.load(fis);
		lblTieuDe.setText(prop.getProperty("lcn_tieuDeLuong"));
        lblThang.setText(prop.getProperty("lnv_thang"));
        lblNam.setText(prop.getProperty("lnv_nam"));
        btnTinhLuong.setText(prop.getProperty("lnv_btnTinhLuong"));
        btnInBangLuong.setText(prop.getProperty("lnv_btnInBangLuong"));
		btnChiTiet.setText(prop.getProperty("lnv_btnChiTiet"));
        lblHienThi.setText(prop.getProperty("lnv_hienThi"));
        
		scrBangLuong.setBorder(new TitledBorder(prop.getProperty("lnv_tieuDe")));

        cmbHienThi.removeAllItems();
        cmbHienThi.addItem(prop.getProperty("lnv_cmb0"));
        cmbHienThi.addItem(prop.getProperty("lnv_cmb1"));
        scrBangLuong.setBorder(new TitledBorder(prop.getProperty("lnv_tieuDe")));
        ChangeName(tblBangLuong, 0, prop.getProperty("lnv_stt"));
        ChangeName(tblBangLuong, 1, prop.getProperty("lnv_maLuong"));
        ChangeName(tblBangLuong, 2, prop.getProperty("maCongNhan"));
        ChangeName(tblBangLuong, 3, prop.getProperty("tenCongNhan"));
        ChangeName(tblBangLuong, 4, prop.getProperty("soCCCD"));
        ChangeName(tblBangLuong, 5, prop.getProperty("lnv_soBuoiDiLam"));
		ChangeName(tblBangLuong, 6, prop.getProperty("lnv_soBuoiNghi"));
		ChangeName(tblBangLuong, 7, prop.getProperty("lnv_soPhepNghi"));
		ChangeName(tblBangLuong, 8, prop.getProperty("lcn_soLuongSP"));
        ChangeName(tblBangLuong, 9, prop.getProperty("lnv_luongThang"));
        ChangeName(tblBangLuong, 10, prop.getProperty("lnv_ngayTinh"));
        ChangeName(tblBangLuong, 11, prop.getProperty("lnv_tongLuong"));
		ChangeName(tblBangLuong, 12, prop.getProperty("lnv_donViTien"));

        stTinhLuongThanhCong = prop.getProperty("tinhLuongThanhCong");
        stTinhLuongThatBai = prop.getProperty("tinhLuongThatBai");
    }
	
	public void ChangeName(JTable table, int col_index, String col_name) {
        table.getColumnModel().getColumn(col_index).setHeaderValue(col_name);
    }
	
	private void initComponents() {
		setSize(1290, 750);
		setLayout(null);
		setBackground(new Color(255, 255, 255));
		lblTieuDe = new JLabel("BẢNG LƯƠNG CÔNG NHÂN");
		lblTieuDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblTieuDe.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblTieuDe.setBounds(415, 24, 490, 52);
		add(lblTieuDe);

		tblBangLuong = new JTable();
		tblBangLuong.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		tblBangLuong.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 11));
		tblBangLuong.getTableHeader().setBackground(new Color(128, 200, 255));

		tblBangLuong.setModel(new DefaultTableModel(
				new Object [][] {
	                {null, null, null, null, null, null, null, null, null, null, null, null, null},
	                {null, null, null, null, null, null, null, null, null, null, null, null, null},
	                {null, null, null, null, null, null, null, null, null, null, null, null, null},
	                {null, null, null, null, null, null, null, null, null, null, null, null, null},
	                {null, null, null, null, null, null, null, null, null, null, null, null, null},
	                {null, null, null, null, null, null, null, null, null, null, null, null, null},
	                {null, null, null, null, null, null, null, null, null, null, null, null, null},
	                {null, null, null, null, null, null, null, null, null, null, null, null, null}
	            },
	            new String [] {
	                "STT", "Mã lương", "Mã công nhân", "Tên công Nhân", "Mã CCCD", "Số ngày đi làm", "Số ngày có phép", "Số ngày nghỉ không phép","Số lượng sản phẩm làm được", "Ngày tính lương", "Lương tháng", "Tổng Lương", "Đơn vị tiền"
	            }
		));

		tblBangLuong.setSelectionBackground(new Color(255, 215, 0));
		tblBangLuong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblBangLuongMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblBangLuongMouseReleased(evt);
            }
        });

		scrBangLuong = new JScrollPane(tblBangLuong);
		LineBorder blackLineBorder1 = new LineBorder(Color.BLACK, 2);
		TitledBorder titledBorder1 = BorderFactory.createTitledBorder(blackLineBorder1, "Bảng lương nhân viên");
		scrBangLuong.setBorder(titledBorder1);
		scrBangLuong.setBackground(new java.awt.Color(255, 255, 255));
		scrBangLuong.setBounds(0, 305, 1280, 393);
		add(scrBangLuong);

		ScrollBarCustom scrollBar = new ScrollBarCustom();
		scrollBar.setForeground(Color.RED);
		scrBangLuong.setVerticalScrollBar(scrollBar);

		JPanel panelButton = new JPanel();
		panelButton.setBounds(10, 197, 1270, 62);
		add(panelButton);
		panelButton.setLayout(null);
		panelButton.setBackground(Color.WHITE);

		btnTinhLuong = new JButton();
		btnTinhLuong.setIcon(new ImageIcon(BangLuongNhanVien_GUI.class.getResource("/image/icon/tinhLuong.png")));
		btnTinhLuong.setText("Tính lương");
		btnTinhLuong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnTinhLuong.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnTinhLuong.setBorder(null);
		btnTinhLuong.setBackground(new Color(255, 215, 0));
		btnTinhLuong.setBounds(411, 11, 170, 40);
		btnTinhLuong.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnTinhLuongActionPerformed(evt);
			}
		});		
		panelButton.add(btnTinhLuong);

		btnInBangLuong = new JButton();
		btnInBangLuong.setIcon(new ImageIcon(BangLuongNhanVien_GUI.class.getResource("/image/icon/in.png")));
		btnInBangLuong.setText("In bảng lương");
		btnInBangLuong.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnInBangLuong.setBorder(null);
		btnInBangLuong.setBackground(new Color(255, 215, 0));
		btnInBangLuong.setBounds(697, 11, 170, 40);
		btnInBangLuong.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnInBangLuongActionPerformed(evt);
			}
		});
		panelButton.add(btnInBangLuong);

		btnChiTiet = new JButton();
		btnChiTiet.setIcon(new ImageIcon(BangLuongNhanVien_GUI.class.getResource("/image/icon/xemChiTiet.png")));
		btnChiTiet.setText("Xem chi tiết");
		btnChiTiet.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnChiTiet.setBorder(null);
		btnChiTiet.setBackground(new Color(255, 215, 0));
		btnChiTiet.setBounds(972, 11, 170, 40);
		btnChiTiet.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnChiTietActionPerformed(evt);
			}
		});
		panelButton.add(btnChiTiet);

		lblHienThi = new JLabel();
		lblHienThi.setText("Hiển thị:");
		lblHienThi.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblHienThi.setBounds(28, 11, 114, 40);
		panelButton.add(lblHienThi);

		cmbHienThi = new JComboBox<String>();
		cmbHienThi.setBounds(139, 11, 145, 40);
		cmbHienThi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Theo tháng/năm" }));
		cmbHienThi.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cmbHienThiActionPerformed(evt);
			}
		});
		panelButton.add(cmbHienThi);

		lblThang = new JLabel();
		lblThang.setBounds(498, 100, 66, 41);
		add(lblThang);
		lblThang.setText("Tháng");
		lblThang.setFont(new Font("Times New Roman", Font.BOLD, 20));

		cmbThangTinh = new JComboBox<String>();
		cmbThangTinh.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		cmbThangTinh.setBackground(Color.WHITE);
		cmbThangTinh.setBounds(586, 103, 57, 37);
		add(cmbThangTinh);

		cmbThangTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
		cmbThangTinh.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cmbThangTinhActionPerformed(evt);
			}
		});

		lblNam = new JLabel();
		lblNam.setText("Năm");
		lblNam.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNam.setBounds(676, 99, 66, 41);
		add(lblNam);

		cmbNamTinh = new JComboBox<String>();
		cmbNamTinh.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		cmbNamTinh.setBackground(Color.WHITE);
		cmbNamTinh.setBounds(745, 103, 78, 37);
		add(cmbNamTinh);

		cmbNamTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2023" }));
		cmbNamTinh.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cmbNamTinhActionPerformed(evt);
			}
		});
	}

	private void btnChiTietActionPerformed(ActionEvent evt) {
		int selectedRow = tblBangLuong.getSelectedRow();
	    if (selectedRow != -1) {
	        try {
	            String maBangLuong = tblBangLuong.getValueAt(selectedRow, 1).toString();
	            bangLuongCN_DAO = new BangLuongCongNhan_Dao();
	            BangLuongCongNhan selectedBangLuong = bangLuongCN_DAO.layDanhSachBangLuongCNTheoMaBangLuong(maBangLuong);
	            
	            if (selectedBangLuong != null) {
	                xuatIreport r = new xuatIreport();

	                ArrayList<BangLuongCongNhan> dsBangLuong = new ArrayList<>();
	                dsBangLuong.add(selectedBangLuong);

	                r.xuatChiTietLuongCN(dsBangLuong);
	            } else {
	                System.out.println("Không tìm thấy thông tin bảng lương.");
	            }
	        } catch (Exception e) {
	            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra khi xử lý chi tiết: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
	            e.printStackTrace();
	        }
	    } else {
	        JOptionPane.showMessageDialog(this, "Vui lòng chọn một bảng lương để xem", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
	    }
	}

	public void setNamChoCmbNam() {
		cmbNamTinh.removeAllItems();
		cmbNamTinh.addItem(Calendar.getInstance().get(Calendar.YEAR) + "");
		cmbNamTinh.setEnabled(false);
	}

	public void hienThiBangLuongTheoNgay(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			String item = e.getItem().toString();
			btnInBangLuong.setEnabled(false);
			if (item.equalsIgnoreCase("Tất cả")) {
				taiDuLieuLenBang();
			} else {
				taiDuLieuLenBangTheoNgayThang();
				if (tblBangLuong.getRowCount() != 0) {
					btnInBangLuong.setEnabled(true);
				}
			}

		}
	}

	public void taiDuLieuLenBangTheoNgayThang() {
		int thang = Integer.parseInt((cmbThangTinh.getSelectedItem().toString()));
		int nam = Integer.parseInt(cmbNamTinh.getSelectedItem().toString());
		while (tblBangLuong.getRowCount() != 0) {
			modelTableChamCong.removeRow(0);
		}
		ArrayList<BangLuongCongNhan> dsBangLuong = bangLuongCN_DAO.layDanhSachBangLuongTheoThangNam(thang, nam);
		for (BangLuongCongNhan bangLuong : dsBangLuong) {
			String data[] = {(modelTableChamCong.getRowCount() + 1) + "", bangLuong.getMaBangLuong(), bangLuong.getCongNhan().getMaCongNhan(),
					bangLuong.getCongNhan().getHoTen(), bangLuong.getCongNhan().getMaCCCD(), bangLuong.getSoNgayDiLam() + "",
					bangLuong.getSoNgayNghi() + "", bangLuong.getSoPhepNghi() + "",bangLuong.getSoLuongSanPhamLam() + "" , bangLuong.getLuongTheoThang(), bangLuong.getNgayTinh().toString(),
					(bangLuong.getTongLuong() == 0) ? "0" : nf.format(bangLuong.getTongLuong()),bangLuong.getDonViTien()};
			modelTableChamCong.addRow(data);
		}
	}

	public void checkThangChon(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			String thangString = e.getItem().toString();

			int thang = Integer.parseInt(thangString);
			int nam = Calendar.getInstance().get(Calendar.YEAR);
			if (LocalDate.of(nam, thang, 1).isAfter(LocalDate.now())) {
				JOptionPane.showMessageDialog(null, "Tháng tinh phải là tháng trước hoặc bằng tháng hiện tại");
				System.out.println(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonthValue());
				cmbThangTinh.setSelectedItem(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonthValue() + "");
			}
		}
	}

	public void taiDuLieuLenBang() {
	    // Xóa tất cả các dòng trong bảng
	    modelTableChamCong.setRowCount(0);

	    ArrayList<BangLuongCongNhan> dsBangLuong = bangLuongCN_DAO.layDanhSachBangLuongCongNhan();
	    for (BangLuongCongNhan bangLuong : dsBangLuong) {
	        String data[] = {
	            (modelTableChamCong.getRowCount() + 1) + "",
	            bangLuong.getMaBangLuong(),
	            bangLuong.getCongNhan().getMaCongNhan(),
	            bangLuong.getCongNhan().getHoTen(),
	            bangLuong.getCongNhan().getMaCCCD(),
	            bangLuong.getSoNgayDiLam() + "",
	            bangLuong.getSoNgayNghi() + "",
	            bangLuong.getSoPhepNghi() + "",
	            bangLuong.getSoLuongSanPhamLam() + "",
	            bangLuong.getLuongTheoThang(),
	            bangLuong.getNgayTinh().toString(),
	            (bangLuong.getTongLuong() == 0) ? "0" : nf.format(bangLuong.getTongLuong()),
	            bangLuong.getDonViTien()
	        };
	        modelTableChamCong.addRow(data);
	    }
	}

	public void excute() {
		tblBangLuong.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
		tblBangLuong.getTableHeader().setOpaque(false);
		((DefaultTableCellRenderer) tblBangLuong.getTableHeader().getDefaultRenderer())
		.setHorizontalAlignment(JLabel.CENTER);
		tblBangLuong.setRowHeight(25);

	}

	private void btnTinhLuongActionPerformed(java.awt.event.ActionEvent evt) {
	}


	private void cmbThangTinhActionPerformed(java.awt.event.ActionEvent evt) {
		if (cmbHienThi.getSelectedIndex() == 0) {
			taiDuLieuLenBang();
		} else {
			taiDuLieuLenBangTheoNgayThang();
			caiDatGuiThongTin();
		}
		if (tblBangLuong.getRowCount() < 0) {
			btnTinhLuong.setEnabled(false);
		}
		if (cmbHienThi.getSelectedIndex() == 1) {
			if (tblBangLuong.getRowCount() > 0) {
				btnInBangLuong.setEnabled(true);
			} else {
				btnInBangLuong.setEnabled(false);
			}
		}
	}

	private void btnInBangLuongActionPerformed(java.awt.event.ActionEvent evt) {
		MessageFormat header = new MessageFormat("Bảng Lương Công Nhân trong tháng " + cmbThangTinh.getSelectedItem() + " năm " + cmbNamTinh.getSelectedItem().toString());
		MessageFormat footer = new MessageFormat("Nhóm 05");
		try {
			tblBangLuong.print(JTable.PrintMode.FIT_WIDTH, header, footer);
		} catch (Exception e) {
			e.getMessage();
		}
	}

	private void tblBangLuongMousePressed(java.awt.event.MouseEvent evt) {
	}

	private void cmbHienThiActionPerformed(java.awt.event.ActionEvent evt) {
		if (cmbHienThi.getSelectedIndex() == 0) {
			taiDuLieuLenBang();
		} else {
			taiDuLieuLenBangTheoNgayThang();
		}
		if (cmbHienThi.getSelectedIndex() == 1) {
			if (tblBangLuong.getRowCount() > 0) {
				btnInBangLuong.setEnabled(true);
			}
		} else {
			btnInBangLuong.setEnabled(false);
		}
	}

	private void tblBangLuongMouseReleased(java.awt.event.MouseEvent evt) {
	}

	private void cmbNamTinhActionPerformed(java.awt.event.ActionEvent evt) {
		if (cmbHienThi.getSelectedIndex() == 0) {
			taiDuLieuLenBang();
			btnInBangLuong.setEnabled(false);
		} else {
			taiDuLieuLenBangTheoNgayThang();
			caiDatGuiThongTin();
		}
		if (tblBangLuong.getRowCount() < 0) {
			btnTinhLuong.setEnabled(false);
		}
		if (cmbHienThi.getSelectedIndex() == 1) {
			if (tblBangLuong.getRowCount() > 0) {
				btnInBangLuong.setEnabled(true);
			} else {
				btnInBangLuong.setEnabled(false);
			}
		}
	}

	public void caiDatGuiThongTin() {
		if (cmbHienThi.getSelectedIndex() == 1) {
			if (modelTableChamCong.getRowCount() > 0) {
				btnInBangLuong.setEnabled(true);
			}
		} else {
			btnInBangLuong.setEnabled(false);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnTinhLuong)) {
			int thang = Integer.parseInt(cmbThangTinh.getSelectedItem().toString());
			int nam = Integer.parseInt(cmbNamTinh.getSelectedItem().toString());
			boolean tinhLuong = bangLuongCN_DAO.tinhLuongCongNhan(thang, nam);
			if (tinhLuong) {
				JOptionPane.showMessageDialog(this, stTinhLuongThanhCong);
				taiDuLieuLenBang();
			} else {
				JOptionPane.showMessageDialog(this, stTinhLuongThatBai);
			}

		}
	}

}
