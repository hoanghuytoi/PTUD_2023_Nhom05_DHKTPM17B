package UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

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
import Dao.ChamCongNhanVien_Dao;
import Dao.NhanVien_Dao;
import Dao.PhongBan_Dao;
import Entity.ChamCongNhanVien;
import Entity.NhanVien;
import Entity.PhongBan;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import javax.swing.JButton;
import javax.swing.JCheckBox;

public class ChamCongNhanVien_GUI extends JPanel {

	private static final long serialVersionUID = 1L;

	private JScrollPane scrChamCong;
	private JTable tblChamCong;
	private JTextField txtMaNV;
	private JTextField txtTenNV;

	private JComboBox<String> cmbCaLam;
	private JComboBox<String> cmbGio;
	private JComboBox<String> cmbGioTangCa;
	private JComboBox<String> cmbTinhTrang;

	private JScrollPane scrNhanVien;
	private JTable tblNhanVien;
	private JLabel lblTieuDe;
	private JLabel lblMaNV;
	private JLabel lblTenNV;
	private JLabel lblCaLam;
	private JLabel lblGioDiLam;
	private JLabel lblGioTangCa;
	private JLabel lblTinhTrang;
	private JLabel lblNgayChamCong;
	private JDateChooser dcsNgayChamCong;
	private JPanel pnlThongTinChamCong;
	private JButton btnLayDanhSach;
	private JButton btnLuu;
	private JButton btnHuy;
	private JButton btnChamCongTatCa;
	private JButton btnChamLai;
	private JButton btnCapNhat;

	private DefaultTableModel modelNhanVien;
	private DefaultTableModel modelChamCong;
	private NhanVien_Dao daoNhanVien;
	private ChamCongNhanVien_Dao daoChamCong;
	private ArrayList<NhanVien> listNhanVien;
	ArrayList<NhanVien> listNhanVienForEvtChamCongTatCa;
	private boolean checkChamCong = false;


	private String stThongbao;
	private String stCapNhatThanhCong;
	private String stChamCongThanhCong;
	private String stErrNgayChamCong;
	private NhanVien nhanvienDangNhap;
	private String stNgayThangKhongHople;

	private JLabel lblHienThi;
	private JComboBox<String> cmbHienThi;
	private JComboBox<String> cboPhongBan;

	private JButton btnTichTatCa;
	private JButton btnBoTich;

	private JCheckBox checkBoxCoMat;
	private JCheckBox checkBoxNghiCoPhep;

	public ChamCongNhanVien_GUI(NhanVien nhanVien, String fileName) throws ParseException, Exception {
		nhanvienDangNhap = nhanVien;
		ConnectionDB.ConnectDB.getInstance().connect();
		initComponents();
		excute();
		//taiDuLieuLenBangNhanVien();
		while (modelChamCong.getRowCount() != 0) {
			modelChamCong.removeRow(0);
		}
		cmbCaLam.setSelectedIndex(0);
		dcsNgayChamCong.setDate(new Date());
		btnChamCongTatCa.setEnabled(false);
		btnChamLai.setEnabled(false);

		PhongBan_Dao daoPhongBan = new PhongBan_Dao();
		ArrayList<PhongBan> phongBan = daoPhongBan.layDanhSachPhongBan();

		cboPhongBan.addItem("Tất cả");

		if (phongBan.size() > 0) {
			for (PhongBan pb : phongBan) {
				cboPhongBan.addItem(pb.getTenPhongBan());
			}
		}
		caiDatNgonNgu(fileName);
		setEditTextDateChooser();
	}

	public void caiDatNgonNgu(String fileName) throws FileNotFoundException, IOException {
		FileInputStream fis = new FileInputStream(fileName);
		Properties prop = new Properties();
		prop.load(fis);
		btnCapNhat.setText(prop.getProperty("btnCapNhat"));
		btnLuu.setText(prop.getProperty("btnLuu"));
		btnHuy.setText(prop.getProperty("btnHuy"));
		btnTichTatCa.setText(prop.getProperty("btnTichTatCa"));
		btnBoTich.setText(prop.getProperty("btnBoTich"));
		btnLayDanhSach.setText(prop.getProperty("chamCong_layDanhSachChamCong"));
		btnChamCongTatCa.setText(prop.getProperty("chamCong_btnChamCongTatCa"));
		btnChamLai.setText(prop.getProperty("chamCong_btnChamCong"));
		scrChamCong.setBorder(new TitledBorder(prop.getProperty("chamCong_tieuDeChamCong")));
		scrNhanVien.setBorder(new TitledBorder(prop.getProperty("chamCong_tieuDeDanhSachNhanVien")));
		pnlThongTinChamCong.setBorder(new TitledBorder(prop.getProperty("chamCong_tieuDeThongTinChamCong")));

		lblTieuDe.setText(prop.getProperty("chamCong_TieuDe"));
		lblNgayChamCong.setText(prop.getProperty("chamCong_NgayChamCong"));
		lblCaLam.setText(prop.getProperty("chamCong_CaLam"));
		lblMaNV.setText(prop.getProperty("chamCong_maNhanVien"));
		lblTenNV.setText(prop.getProperty("chamCong_hoTen"));
		lblTinhTrang.setText(prop.getProperty("chamCong_tinhTrang"));
		lblGioDiLam.setText(prop.getProperty("chamCong_gio"));
		lblGioTangCa.setText(prop.getProperty("chamCong_gioTangCa"));
		lblHienThi.setText(prop.getProperty("chamCong_hienThi"));

		doiNgonNguTable(tblChamCong, 0, prop.getProperty("chamCong_stt"));
		doiNgonNguTable(tblChamCong, 1, lblMaNV.getText());
		doiNgonNguTable(tblChamCong, 2, lblTenNV.getText());
		doiNgonNguTable(tblChamCong, 3, prop.getProperty("chamCong_phongBan"));
		doiNgonNguTable(tblChamCong, 4, prop.getProperty("chamCong_coMat"));
		doiNgonNguTable(tblChamCong, 5, prop.getProperty("chamCong_nghiCoPhep"));
		doiNgonNguTable(tblChamCong, 6, prop.getProperty("chamCong_gioTangCa"));

		doiNgonNguTable(tblNhanVien, 0, prop.getProperty("chamCong_stt"));
		doiNgonNguTable(tblNhanVien, 1, prop.getProperty("chamCong_maNhanVienChamCong"));
		doiNgonNguTable(tblNhanVien, 2, lblMaNV.getText());
		doiNgonNguTable(tblNhanVien, 3, lblTenNV.getText());
		doiNgonNguTable(tblNhanVien, 4, prop.getProperty("chamCong_sdt"));
		doiNgonNguTable(tblNhanVien, 5, prop.getProperty("chamCong_phongBan"));
		doiNgonNguTable(tblNhanVien, 6, prop.getProperty("chamCong_chucVu"));
		doiNgonNguTable(tblNhanVien, 7, lblNgayChamCong.getText());
		doiNgonNguTable(tblNhanVien, 8, lblCaLam.getText());
		doiNgonNguTable(tblNhanVien, 9, lblTinhTrang.getText());
		doiNgonNguTable(tblNhanVien, 10, lblGioDiLam.getText());
		doiNgonNguTable(tblNhanVien, 11, lblGioTangCa.getText());

		cmbHienThi.removeAllItems();
		cmbHienThi.addItem(prop.getProperty("cmbHienThiAll"));
		cmbHienThi.addItem(prop.getProperty("cmbHienThiLoc"));

		stChamCongThanhCong = prop.getProperty("chamCong_chamCongThanhCong");
		stErrNgayChamCong = prop.getProperty("chamCong_errNgayChamCong");
		stThongbao = prop.getProperty("thongBao");
		stCapNhatThanhCong = prop.getProperty("capNhatThanhCong");
		prop.getProperty("capNhatThatBai");
		stNgayThangKhongHople = prop.getProperty("ngayThangKhongHopLe");
	}

	public void doiNgonNguTable(JTable table, int col_index, String col_name) {
		table.getColumnModel().getColumn(col_index).setHeaderValue(col_name);
	}

	public void setEditTextDateChooser() {
		JTextFieldDateEditor ngayChamCong = (JTextFieldDateEditor) dcsNgayChamCong.getDateEditor();
		ngayChamCong.setEnabled(false);

	}

	public void excute() throws ParseException {
		tblNhanVien.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
		tblNhanVien.getTableHeader().setOpaque(false);
		((DefaultTableCellRenderer) tblNhanVien.getTableHeader().getDefaultRenderer())
		.setHorizontalAlignment(JLabel.CENTER);
		tblNhanVien.setRowHeight(25);

		tblChamCong.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
		tblChamCong.getTableHeader().setOpaque(false);
		((DefaultTableCellRenderer) tblChamCong.getTableHeader().getDefaultRenderer())
		.setHorizontalAlignment(JLabel.CENTER);
		tblChamCong.setRowHeight(25);

		modelNhanVien = (DefaultTableModel) tblNhanVien.getModel();
		modelChamCong = (DefaultTableModel) tblChamCong.getModel();
		setHidden(btnLuu, btnHuy, btnChamLai);
		setShow(btnCapNhat);
	}

	@SuppressWarnings("serial")
	private void initComponents() {
		setBackground(new Color(255, 255, 255));
		setSize(1290, 750);
		setLayout(null);

		lblTieuDe = new JLabel("CHẤM CÔNG NHÂN VIÊN");
		lblTieuDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblTieuDe.setFont(new Font("Times New Roman", Font.BOLD, 23));
		lblTieuDe.setBounds(44, 11, 299, 50);
		add(lblTieuDe);

		tblChamCong = new JTable();
		tblChamCong.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		tblChamCong.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 15));
		tblChamCong.setRowHeight(20);

		tblChamCong.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][]{
			        {null, null, null, null, null, null},
			        {null, null, null, null, null, null},
			    },
			    new String[]{
			        "STT", "Mã nhân viên", "Họ và tên", "Phòng ban", "Có mặt", "Nghỉ có phép", "Giờ tăng ca"
			    }
			) {
			    @Override
			    public Class<?> getColumnClass(int columnIndex) {
			        return columnIndex == 4 || columnIndex == 5 ? Boolean.class : columnIndex == 6 ? Integer.class : Object.class;
			    }

			    @Override
			    public Object getValueAt(int row, int column) {
			        if (column == 6) {
			            return super.getValueAt(row, column);
			        }
			        return super.getValueAt(row, column);
			    }

			    @Override
			    public boolean isCellEditable(int row, int column) {
			        return column == 4 || column == 5;
			    }
			});

		checkBoxCoMat = new JCheckBox();
		tblChamCong.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(checkBoxCoMat));

		checkBoxNghiCoPhep = new JCheckBox();
		tblChamCong.getColumnModel().getColumn(5).setCellEditor(new DefaultCellEditor(checkBoxNghiCoPhep));

		// Thêm ItemListener cho checkbox "Có mặt"
		ItemListener coMatListener = new ItemListener() {
		    @Override
		    public void itemStateChanged(ItemEvent e) {
		        int selectedRow = tblChamCong.getSelectedRow();
		        if (selectedRow != -1 && e.getStateChange() == ItemEvent.SELECTED) {
		            // Nếu ô "Có mặt" được tích, hủy tích ô "Nghỉ có phép"
		            modelChamCong.setValueAt(true, selectedRow, 4);  // Chọn "Có mặt"
		            modelChamCong.setValueAt(false, selectedRow, 5); // Hủy chọn "Nghỉ có phép"
		        }
		    }
		};

		// Thêm ItemListener cho checkbox "Nghỉ có phép"
		ItemListener nghiCoPhepListener = new ItemListener() {
		    @Override
		    public void itemStateChanged(ItemEvent e) {
		        int selectedRow = tblChamCong.getSelectedRow();
		        if (selectedRow != -1 && e.getStateChange() == ItemEvent.SELECTED) {
		            // Nếu ô "Nghỉ có phép" được tích, hủy tích ô "Có mặt"
		        	modelChamCong.setValueAt(true, selectedRow, 5);  // Chọn "Nghỉ có phép"
		        	modelChamCong.setValueAt(false, selectedRow, 4); // Hủy chọn "Có mặt"
		        }
		    }
		};

		checkBoxCoMat.addItemListener(coMatListener);
		checkBoxNghiCoPhep.addItemListener(nghiCoPhepListener);

		tblChamCong.getTableHeader().setBackground(new Color(128, 200, 255));
		tblChamCong.setSelectionBackground(new Color(255, 215, 0));

		tblChamCong.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent evt) {
		        tblChamCongMouseClicked(evt);
		        int column = tblChamCong.columnAtPoint(evt.getPoint());
		        int row = tblChamCong.rowAtPoint(evt.getPoint());

		        if (column == 6 && row != -1) {
		            Boolean isCoMat = (Boolean) modelChamCong.getValueAt(row, 4);
		            Boolean isNghiCoPhep = (Boolean) modelChamCong.getValueAt(row, 5);

		            if (isCoMat != null && isCoMat) {
		                if (isNghiCoPhep == null || !isNghiCoPhep) {
		                    String newOvertime = JOptionPane.showInputDialog("Nhập giờ tăng ca mới:");
		                    if (newOvertime != null && !newOvertime.isEmpty()) {
		                        try {
		                            int overtimeValue = Integer.parseInt(newOvertime);

		                            if (isValidOvertime(overtimeValue)) {
		                                modelChamCong.setValueAt(overtimeValue, row, 6);
		                            } else {
		                                JOptionPane.showMessageDialog(null, "Vui lòng nhập một giờ tăng ca hợp lệ (1, 2, 3, 4).", "Lỗi", JOptionPane.ERROR_MESSAGE);
		                            }
		                        } catch (NumberFormatException e) {
		                            JOptionPane.showMessageDialog(null, "Vui lòng nhập một số nguyên cho giờ tăng ca.", "Lỗi", JOptionPane.ERROR_MESSAGE);
		                        }
		                    }
		                } else {
		                    JOptionPane.showMessageDialog(null, "Nhân viên đang nghỉ có phép! Không thể nhập giờ tăng ca!!!", "Lỗi", JOptionPane.ERROR_MESSAGE);
		                }
		            } else {
		                JOptionPane.showMessageDialog(null, "Nhân viên không có mặt! Không thể nhập giờ tăng ca!!!", "Lỗi", JOptionPane.ERROR_MESSAGE);
		            }
		        }
		    }

		    private boolean isValidOvertime(int value) {
		        int[] allowedOvertimes = {1, 2, 3, 4};

		        for (int allowedValue : allowedOvertimes) {
		            if (value == allowedValue) {
		                return true;
		            }
		        }

		        return false;
		    }
		});

		scrChamCong = new JScrollPane(tblChamCong);
		scrChamCong.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				scrChamCongMouseClicked(evt);
			}
		});
		LineBorder blackLineBorder = new LineBorder(Color.BLACK, 2);
		TitledBorder titledBorder = BorderFactory.createTitledBorder(blackLineBorder, "Danh sách nhân viên cần chấm công");
		scrChamCong.setBorder(titledBorder);
		scrChamCong.setBackground(Color.WHITE);
		scrChamCong.setBounds(44, 76, 639, 273);
		add(scrChamCong);

		ScrollBarCustom scrollBar = new ScrollBarCustom();
		scrollBar.setForeground(Color.RED);
		scrChamCong.setVerticalScrollBar(scrollBar);

		lblNgayChamCong = new JLabel();
		lblNgayChamCong.setText("Ngày chấm công:");
		lblNgayChamCong.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNgayChamCong.setBounds(44, 381, 115, 41);
		add(lblNgayChamCong);

		lblCaLam = new JLabel();
		lblCaLam.setBounds(467, 386, 57, 30);
		add(lblCaLam);
		lblCaLam.setText("Ca làm:");
		lblCaLam.setFont(new Font("Times New Roman", Font.PLAIN, 16));

		cmbCaLam = new JComboBox<String>();
		cmbCaLam.setBounds(517, 381, 111, 41);
		add(cmbCaLam);
		cmbCaLam.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		cmbCaLam.setBackground(Color.WHITE);

		cmbCaLam.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ca sáng(4h)","Ca chiều(4h)"}));
		cmbCaLam.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cmbCaLamActionPerformed(evt);
			}
		});

		dcsNgayChamCong = new JDateChooser();
		dcsNgayChamCong.setDateFormatString("yyyy-MM-dd");
		dcsNgayChamCong.setBounds(152, 381, 98, 41);
		dcsNgayChamCong.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				dcsNgayChamCongMouseClicked(evt);
			}
		});
		dcsNgayChamCong.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
			public void propertyChange(java.beans.PropertyChangeEvent evt) {
				dcsNgayChamCongPropertyChange(evt);
			}
		});
		add(dcsNgayChamCong);

		JLabel lblMuiTen = new JLabel();
		lblMuiTen.setIcon(new ImageIcon(ChamCongNhanVien_GUI.class.getResource("/image/icon/muitenNgang.png")));
		lblMuiTen.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblMuiTen.setBounds(703, 206, 57, 30);
		add(lblMuiTen);

		tblNhanVien = new JTable();
		tblNhanVien.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		tblNhanVien.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 15));
		tblNhanVien.setRowHeight(20);

		tblNhanVien.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null, null, null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null, null, null, null, null, null},
				},
				new String[] {
						"STT", "M\u00E3 ng\u01B0\u1EDDi ch\u1EA5m", "M\u00E3 nh\u00E2n vi\u00EAn", "H\u1ECD v\u00E0 t\u00EAn", "S\u0110T", "Ph\u00F2ng ban", "Ch\u1EE9c v\u1EE5", "Ng\u00E0y ch\u1EA5m c\u00F4ng", "Ca l\u00E0m", "Tr\u1EA1ng th\u00E1i", "Gi\u1EDD \u0111i l\u00E0m", "Gi\u1EDD t\u0103ng ca"
				}
				));
		tblNhanVien.setSelectionBackground(new java.awt.Color(232, 57, 95));
		tblNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				tblNhanVienMouseClicked(evt);
			}
		});

		tblNhanVien.getTableHeader().setBackground(new Color(128, 200, 255));
		tblNhanVien.setSelectionBackground(new Color(255, 215, 0));


		scrNhanVien = new JScrollPane(tblNhanVien);
		LineBorder blackLineBorder1 = new LineBorder(Color.BLACK, 2);
		TitledBorder titledBorder1 = BorderFactory.createTitledBorder(blackLineBorder1, "Danh sách chấm công nhân viên");
		scrNhanVien.setBorder(titledBorder1);
		scrNhanVien.setBackground(Color.WHITE);
		scrNhanVien.setBounds(44, 535, 1200, 185);
		add(scrNhanVien);

		ScrollBarCustom scrollBar1 = new ScrollBarCustom();
		scrollBar1.setForeground(Color.RED);
		scrNhanVien.setVerticalScrollBar(scrollBar1);

		pnlThongTinChamCong = new JPanel();
		LineBorder blackLineBorder2 = new LineBorder(Color.BLACK, 2);
		TitledBorder titledBorder2 = BorderFactory.createTitledBorder(blackLineBorder2, "Thông tin chấm công");
		pnlThongTinChamCong.setBorder(titledBorder2);
		pnlThongTinChamCong.setBackground(new Color(255, 255, 255));
		pnlThongTinChamCong.setBounds(757, 56, 487, 314);
		add(pnlThongTinChamCong);
		pnlThongTinChamCong.setLayout(null);

		cmbTinhTrang = new JComboBox<String>();
		cmbTinhTrang.setBounds(149, 139, 301, 36);
		pnlThongTinChamCong.add(cmbTinhTrang);
		cmbTinhTrang.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		cmbTinhTrang.setBackground(Color.WHITE);

		cmbTinhTrang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đi làm", "Nghỉ có phép", "Nghỉ không phép" }));
		cmbTinhTrang.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cmbTinhTrangActionPerformed(evt);
			}
		});

		lblMaNV = new JLabel();
		lblMaNV.setBounds(27, 33, 84, 20);
		pnlThongTinChamCong.add(lblMaNV);
		lblMaNV.setText("Mã nhân viên:");
		lblMaNV.setFont(new Font("Times New Roman", Font.PLAIN, 16));

		txtMaNV = new JTextField();
		txtMaNV.setBounds(149, 11, 301, 36);
		pnlThongTinChamCong.add(txtMaNV);
		txtMaNV.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtMaNV.setBorder(null);

		JSeparator jSeparator = new JSeparator();
		jSeparator.setBounds(149, 47, 301, 10);
		pnlThongTinChamCong.add(jSeparator);
		jSeparator.setForeground(Color.BLACK);

		lblTenNV = new JLabel();
		lblTenNV.setBounds(29, 86, 102, 30);
		pnlThongTinChamCong.add(lblTenNV);
		lblTenNV.setText("Họ và tên:");
		lblTenNV.setFont(new Font("Times New Roman", Font.PLAIN, 16));

		txtTenNV = new JTextField();
		txtTenNV.setBounds(149, 68, 301, 36);
		pnlThongTinChamCong.add(txtTenNV);
		txtTenNV.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtTenNV.setBorder(null);

		JSeparator jSeparator1 = new JSeparator();
		jSeparator1.setBounds(149, 106, 301, 10);
		pnlThongTinChamCong.add(jSeparator1);
		jSeparator1.setForeground(Color.BLACK);

		lblTinhTrang = new JLabel();
		lblTinhTrang.setBounds(27, 150, 102, 30);
		pnlThongTinChamCong.add(lblTinhTrang);
		lblTinhTrang.setText("Tình trạng:");
		lblTinhTrang.setFont(new Font("Times New Roman", Font.PLAIN, 16));

		lblGioDiLam = new JLabel();
		lblGioDiLam.setBounds(29, 209, 102, 30);
		pnlThongTinChamCong.add(lblGioDiLam);
		lblGioDiLam.setText("Giờ đi làm:");
		lblGioDiLam.setFont(new Font("Times New Roman", Font.PLAIN, 16));

		JLabel lblGio_1 = new JLabel();
		lblGio_1.setText("giờ");
		lblGio_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblGio_1.setBounds(216, 270, 57, 30);
		pnlThongTinChamCong.add(lblGio_1);

		cmbGio = new JComboBox<String>();
		cmbGio.setBounds(149, 196, 111, 43);
		pnlThongTinChamCong.add(cmbGio);
		cmbGio.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		cmbGio.setBackground(Color.WHITE);
		cmbGio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"7h30-11h30","13h00-17h00"}));

		lblGioTangCa = new JLabel();
		lblGioTangCa.setBounds(27, 270, 102, 30);
		pnlThongTinChamCong.add(lblGioTangCa);
		lblGioTangCa.setText("Giờ tăng ca:");
		lblGioTangCa.setFont(new Font("Times New Roman", Font.PLAIN, 16));

		cmbGioTangCa = new JComboBox<String>();
		cmbGioTangCa.setBounds(149, 260, 57, 40);
		pnlThongTinChamCong.add(cmbGioTangCa);
		cmbGioTangCa.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		cmbGioTangCa.setBackground(Color.WHITE);

		cmbGioTangCa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2","3", "4"}));
		cmbGioTangCa.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cmbGioTangCaActionPerformed(evt);
			}
		});

		cboPhongBan = new JComboBox<String>();
		cboPhongBan.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		cboPhongBan.setBackground(Color.WHITE);
		cboPhongBan.setBounds(663, 380, 244, 40);
		add(cboPhongBan);

		lblHienThi = new JLabel();
		lblHienThi.setText("Hiển thị:");
		lblHienThi.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblHienThi.setBounds(44, 471, 51, 40);
		add(lblHienThi);

		cmbHienThi = new JComboBox<String>();
		cmbHienThi.setBounds(105, 471, 145, 40);
		cmbHienThi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Theo ngày chấm"}));
		cmbHienThi.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cmbHienThiActionPerformed(evt);
			}
		});
		add(cmbHienThi);

		btnChamCongTatCa = new JButton();
		btnChamCongTatCa.setIcon(new ImageIcon(ChamCongNhanVien_GUI.class.getResource("/image/icon/them.png")));
		btnChamCongTatCa.setText("Chấm công tất cả");
		btnChamCongTatCa.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnChamCongTatCa.setBorder(null);
		btnChamCongTatCa.setBackground(new Color(255, 215, 0));
		btnChamCongTatCa.setBounds(287, 470, 170, 40);
		btnChamCongTatCa.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnChamCongTatCaActionPerformed(evt);
			}
		});
		add(btnChamCongTatCa);

		btnChamLai = new JButton();
		btnChamLai.setIcon(new ImageIcon(ChamCongNhanVien_GUI.class.getResource("/image/icon/them.png")));
		btnChamLai.setText("Chấm lại");
		btnChamLai.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnChamLai.setBorder(null);
		btnChamLai.setBackground(new Color(255, 215, 0));
		btnChamLai.setBounds(488, 470, 170, 40);
		btnChamLai.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnChamLaiActionPerformed(evt);
			}
		});
		add(btnChamLai);

		btnCapNhat = new JButton();
		btnCapNhat.setIcon(new ImageIcon(ChamCongNhanVien_GUI.class.getResource("/image/icon/capNhat.png")));
		btnCapNhat.setText("Cập nhật");
		btnCapNhat.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnCapNhat.setBorder(null);
		btnCapNhat.setBackground(new Color(255, 215, 0));
		btnCapNhat.setBounds(690, 470, 170, 40);
		btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnCapNhatActionPerformed(evt);
			}
		});
		add(btnCapNhat);

		btnLayDanhSach = new JButton();
		btnLayDanhSach.setIcon(new ImageIcon(ChamCongNhanVien_GUI.class.getResource("/image/icon/them.png")));
		btnLayDanhSach.setText("Lấy danh sách");
		btnLayDanhSach.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnLayDanhSach.setBorder(null);
		btnLayDanhSach.setBackground(new Color(0, 255, 127));
		btnLayDanhSach.setBounds(287, 380, 160, 40);
		btnLayDanhSach.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnLayDanhSachActionPerformed(evt);
			}
		});
		add(btnLayDanhSach);

		btnLuu = new JButton();
		btnLuu.setIcon(new ImageIcon(ChamCongNhanVien_GUI.class.getResource("/image/icon/luu.png")));
		btnLuu.setText("Lưu");
		btnLuu.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnLuu.setEnabled(false);
		btnLuu.setBorder(null);
		btnLuu.setBackground(Color.MAGENTA);
		btnLuu.setBounds(885, 471, 160, 40);
		btnLuu.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnLuuActionPerformed(evt);
			}
		});
		add(btnLuu);

		btnHuy = new JButton();
		btnHuy.setIcon(new ImageIcon(ChamCongNhanVien_GUI.class.getResource("/image/icon/huy.png")));
		btnHuy.setText("Hủy");
		btnHuy.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnHuy.setEnabled(false);
		btnHuy.setBorder(null);
		btnHuy.setBackground(Color.MAGENTA);
		btnHuy.setBounds(1075, 471, 160, 40);
		btnHuy.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnHuyActionPerformed(evt);
			}
		});
		add(btnHuy);

		btnTichTatCa = new JButton();
		btnTichTatCa.setIcon(new ImageIcon(ChamCongNhanVien_GUI.class.getResource("/image/icon/them.png")));
		btnTichTatCa.setText("Có mặt tất cả");
		btnTichTatCa.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnTichTatCa.setBorder(null);
		btnTichTatCa.setBackground(new Color(0, 255, 127));
		btnTichTatCa.setBounds(937, 380, 160, 40);
		add(btnTichTatCa);
		btnTichTatCa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int rowCount = tblChamCong.getRowCount();
				for (int i = 0; i < rowCount; i++) {
					tblChamCong.getModel().setValueAt(true, i, 4); // Cột "Có mặt" là cột thứ 4
				}
			}
		});

		btnBoTich = new JButton();
		btnBoTich.setIcon(new ImageIcon(ChamCongNhanVien_GUI.class.getResource("/image/icon/xoa.png")));
		btnBoTich.setText("Bỏ tích");
		btnBoTich.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnBoTich.setBorder(null);
		btnBoTich.setBackground(new Color(0, 255, 127));
		btnBoTich.setBounds(1127, 381, 115, 40);
		add(btnBoTich);
		btnBoTich.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int rowCount = tblChamCong.getRowCount();
				for (int i = 0; i < rowCount; i++) {
					tblChamCong.getModel().setValueAt(false, i, 4); // Cột "Có mặt" là cột thứ 4
				}
			}
		});
	}

	public void hienThiChamCongTheoNgay() {
		if (cmbHienThi.getSelectedIndex() == 1) {
			daoChamCong = new ChamCongNhanVien_Dao();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String strDate = formatter.format(dcsNgayChamCong.getDate());
			ArrayList<ChamCongNhanVien> listChamCong = daoChamCong.layDanhSachChamCongTheoNgay(strDate);

			// Sắp xếp danh sách chấm công theo phòng ban
			Collections.sort(listChamCong, new Comparator<ChamCongNhanVien>() {
				@Override
				public int compare(ChamCongNhanVien cc1, ChamCongNhanVien cc2) {
					return cc1.getNhanVien().getPhongBan().getTenPhongBan()
							.compareTo(cc2.getNhanVien().getPhongBan().getTenPhongBan());
				}
			});

			// Xóa dữ liệu trên bảng
			while (modelNhanVien.getRowCount() != 0) {
				modelNhanVien.removeRow(0);
			}

			// Hiển thị dữ liệu mới theo phòng ban
			for (ChamCongNhanVien chamCong : listChamCong) {
				String data[] = {(modelNhanVien.getRowCount() + 1) + "", chamCong.getNguoiChamCong().getMaNhanVien(),
						chamCong.getNhanVien().getMaNhanVien(), chamCong.getNhanVien().getHoTen(), chamCong.getNhanVien().getSoDienThoai(),
						chamCong.getNhanVien().getPhongBan().getTenPhongBan(), chamCong.getNhanVien().getChucVu(), chamCong.getNgayChamCong().toString(),
						chamCong.getCaLam(), chamCong.getTrangThaiDiLam(), String.valueOf(chamCong.getGioDiLam()), String.valueOf(chamCong.getGioTangCa())};
				modelNhanVien.addRow(data);
			}

			// Chọn dòng đầu tiên nếu có dữ liệu
			if (tblNhanVien.getRowCount() > 0) {
				tblNhanVien.setRowSelectionInterval(0, 0);
				try {
					hienThiDuLieuLenTxtBangNhanVien(0);
				} catch (ParseException ex) {
					Logger.getLogger(ChamCongNhanVien_GUI.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		} else {
			try {
				taiDuLieuLenBangNhanVien();
			} catch (ParseException ex) {
				Logger.getLogger(ChamCongNhanVien_GUI.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}


	private void cmbHienThiActionPerformed(ActionEvent evt) {
		hienThiChamCongTheoNgay();
	}

	public void hienThiChamCongTheoCaLam(String caLam) throws ParseException {
	    while (modelNhanVien.getRowCount() != 0) {
	        modelNhanVien.removeRow(0);
	    }

	    daoChamCong = new ChamCongNhanVien_Dao();
	    ArrayList<ChamCongNhanVien> listChamCong = daoChamCong.danhSachChamCongNhanVien();

	    // Lọc danh sách chấm công theo ca làm
	    List<ChamCongNhanVien> chamCongTheoCaLam = listChamCong.stream()
	            .filter(chamCong -> chamCong.getCaLam().equals(caLam))
	            .collect(Collectors.toList());

	    if (chamCongTheoCaLam != null) {
	        for (ChamCongNhanVien chamCong : chamCongTheoCaLam) {
	            if (chamCong.getNguoiChamCong() != null) {
	                String data[] = {(modelNhanVien.getRowCount() + 1) + "", chamCong.getNguoiChamCong().getMaNhanVien(),
	                        chamCong.getNhanVien().getMaNhanVien(), chamCong.getNhanVien().getHoTen(), chamCong.getNhanVien().getSoDienThoai(),
	                        chamCong.getNhanVien().getPhongBan().getTenPhongBan(), chamCong.getNhanVien().getChucVu(), chamCong.getNgayChamCong().toString(),
	                        chamCong.getCaLam(), chamCong.getTrangThaiDiLam(), chamCong.getGioDiLam(), String.valueOf(chamCong.getGioTangCa())};
	                modelNhanVien.addRow(data);
	            }
	        }
	        if (tblNhanVien.getRowCount() > 0) {
	            tblNhanVien.setRowSelectionInterval(0, 0);
	            hienThiDuLieuLenTxtBangNhanVien(0);
	            setEditInput(false);
	        }
	    }
	    setEditInput(false);
	}

	
	private void cmbGioTangCaActionPerformed(ActionEvent evt) {
	}

	public void taiDuLieuLenBangNhanVien() throws ParseException {
		while (modelNhanVien.getRowCount() != 0) {
			modelNhanVien.removeRow(0);
		}
		daoChamCong = new ChamCongNhanVien_Dao();
		ArrayList<ChamCongNhanVien> listChamCong = daoChamCong.danhSachChamCongNhanVien();

		// Giữ lại chỉ 10 chấm công gần nhất
		int count = Math.min(10, listChamCong.size());
		List<ChamCongNhanVien> recentChamCong = listChamCong.subList(0, count);

		if (recentChamCong != null) {
			for (ChamCongNhanVien chamCong : recentChamCong) {
				if (chamCong.getNguoiChamCong() != null) {
					String data[] = {(modelNhanVien.getRowCount() + 1) + "", chamCong.getNguoiChamCong().getMaNhanVien(),
							chamCong.getNhanVien().getMaNhanVien(), chamCong.getNhanVien().getHoTen(), chamCong.getNhanVien().getSoDienThoai(),
							chamCong.getNhanVien().getPhongBan().getTenPhongBan(), chamCong.getNhanVien().getChucVu(), chamCong.getNgayChamCong().toString(),
							chamCong.getCaLam(), chamCong.getTrangThaiDiLam(), chamCong.getGioDiLam(), String.valueOf(chamCong.getGioTangCa())};
					modelNhanVien.addRow(data);
				}
			}
			if (tblNhanVien.getRowCount() > 0) {
				tblNhanVien.setRowSelectionInterval(0, 0);
				hienThiDuLieuLenTxtBangNhanVien(0);
				setEditInput(false);
			}
		}
		setEditInput(false);
	}

	public void taiDuLieuLenBangChamCongEvt() {
		daoChamCong = new ChamCongNhanVien_Dao();
		daoNhanVien = new NhanVien_Dao();
		ArrayList<ChamCongNhanVien> listChamCong = daoChamCong.danhSachChamCongNhanVien();
		listNhanVien = daoNhanVien.layDanhSachNhanVien();
		ArrayList<NhanVien> listTemp1 = new ArrayList<>();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = formatter.format(dcsNgayChamCong.getDate());
		listChamCong.forEach(e -> {
			if (e.getCaLam().equalsIgnoreCase(cmbCaLam.getSelectedItem().toString()) && strDate.equalsIgnoreCase(e.getNgayChamCong().toString())) {
				listTemp1.add(e.getNhanVien());
			}
		});
		String selectedPhongBan = cboPhongBan.getSelectedItem().toString();

		listNhanVienForEvtChamCongTatCa = new ArrayList<>();
		listNhanVien.forEach(e -> {
			boolean flag = false;

			if ("Tất cả".equalsIgnoreCase(selectedPhongBan) || selectedPhongBan.equalsIgnoreCase(e.getPhongBan().getTenPhongBan())) {
				if (e.getNgayVaoLam().after(dcsNgayChamCong.getDate())) {
				} else {
					for (NhanVien nhanVien : listTemp1) {
						if (nhanVien.getMaNhanVien().equalsIgnoreCase(e.getMaNhanVien())) {
							flag = true;
						}
					}
					if (!flag) {
						listNhanVienForEvtChamCongTatCa.add(e);
						String data[] = {(modelChamCong.getRowCount() + 1) + "", e.getMaNhanVien(), e.getHoTen(), e.getPhongBan().getTenPhongBan()};
						modelChamCong.addRow(data);
					}
				}
			}
		});
		if (modelChamCong.getRowCount() == 0) {
			JOptionPane.showMessageDialog(null, "Đã chấm công rồi không có dữ liệu chấm công!!!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public void taiDuLieuLenBangChamCong() throws ParseException, InterruptedException {
		while (modelChamCong.getRowCount() != 0) {
			modelChamCong.removeRow(0);
		}
		taiDuLieuLenBangChamCongEvt();
		if (tblNhanVien.getRowCount() != 0) {
			tblNhanVien.setRowSelectionInterval(0, 0);
		}
		if (modelChamCong.getRowCount() > 0) {
			tblChamCong.setRowSelectionInterval(0, 0);
			hienThiDuLieuLenTxtBangNhanVien(0);
			setEditInput(false);
			btnChamLai.setEnabled(false);
		} else {
			btnChamLai.setEnabled(false);
		}
	}

	public void setComBoBoxToReset() {
		cmbCaLam.removeAllItems();
		cmbCaLam.addItem("Ca sáng(4h)");
		cmbCaLam.addItem("Ca chiều(4h)");
	}

	public void setComBoBoxToSunDay() {
		cmbCaLam.removeAllItems();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dcsNgayChamCong.getDate());

		int selectedDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

		if (selectedDayOfWeek == Calendar.SUNDAY) {
			cmbCaLam.addItem("Sáng chủ nhật");
			cmbCaLam.addItem("Chiều chủ nhật");
		} else if (selectedDayOfWeek == Calendar.SATURDAY) {
			cmbCaLam.addItem("Sáng thứ bảy");
			cmbCaLam.addItem("Chiều thứ bảy");
		} else {
			setComBoBoxToReset();
		}
	}

	private void dcsNgayChamCongPropertyChange(java.beans.PropertyChangeEvent evt) {
		if ("date".equals(evt.getPropertyName())) {
			setComBoBoxToSunDay();
		}

		btnChamLai.setEnabled(false);
		hienThiChamCongTheoNgay();
		while (modelChamCong.getRowCount() > 0) {
			modelChamCong.removeRow(0);
		}
	}

	public void hienThiDuLieuLenTxtBangNhanVien(int dong) throws ParseException {
		if (modelNhanVien.getRowCount() > 0) {
			txtMaNV.setText(tblNhanVien.getValueAt(dong, 2).toString());
			txtTenNV.setText(tblNhanVien.getValueAt(dong, 3).toString());
			cmbTinhTrang.setSelectedItem(tblNhanVien.getValueAt(dong, 9).toString());
			if (cmbTinhTrang.getSelectedIndex() != 1 && cmbTinhTrang.getSelectedIndex() != 2) {
				cmbGio.setSelectedItem(tblNhanVien.getValueAt(dong, 10).toString());
				cmbGioTangCa.setSelectedItem(tblNhanVien.getValueAt(dong, 11));
				cmbGio.setEditable(true);
			} else {
				cmbGio.setEditable(true);
			}
		}
	}

	public void hienThiDuLieuLenTxtBangChamCong(int dong) throws ParseException {
		txtMaNV.setText(tblChamCong.getValueAt(dong, 1).toString());
		txtTenNV.setText(tblChamCong.getValueAt(dong, 2).toString());
	}

	public void setHidden(JButton... btn) {
		for (JButton jButton : btn) {
			jButton.setEnabled(false);
		}
	}

	public void setShow(JButton... btn) {
		for (JButton jButton : btn) {
			jButton.setEnabled(true);
		}
	}

	public void setEditInput(boolean check) {
		cmbTinhTrang.setEnabled(check);
		cmbGio.setEnabled(check);
		cmbGioTangCa.setEnabled(check);
	}

	private void tblNhanVienMouseClicked(java.awt.event.MouseEvent evt) {
		try {
			capNhatCmbGio();
			hienThiDuLieuLenTxtBangNhanVien(tblNhanVien.getSelectedRow());
			setEditInput(false);
			setShow(btnCapNhat);
			setHidden(btnHuy, btnLuu, btnChamLai);
			checkChamCong = false;
		} catch (ParseException ex) {
			JOptionPane.showMessageDialog(null, "ERRO");
		}
		if (tblChamCong.getSelectedRow() != -1) {
			tblChamCong.removeRowSelectionInterval(tblChamCong.getSelectedRow(), 0);
		}
	}

	private void tblChamCongMouseClicked(java.awt.event.MouseEvent evt) {
		try {
			capNhatCmbGio();
			hienThiDuLieuLenTxtBangChamCong(tblChamCong.getSelectedRow());
			setEditInput(false);
			setHidden(btnHuy, btnLuu, btnCapNhat);
			setShow(btnChamLai, btnChamCongTatCa);
			checkChamCong = false;
		} catch (ParseException ex) {
			JOptionPane.showMessageDialog(null, "ERRO");
		}
		if (tblNhanVien.getSelectedRow() != -1) {
			tblNhanVien.removeRowSelectionInterval(tblNhanVien.getSelectedRow(), 0);
		}
	}

	private void dcsNgayChamCongMouseClicked(java.awt.event.MouseEvent evt) {
	}

	public void thayDouCmbBox() {
		if (cmbTinhTrang.getSelectedIndex() == 0 ) {
			cmbGio.setEnabled(false);
			cmbGioTangCa.setEnabled(true);
		}
		else if (cmbTinhTrang.getSelectedIndex() == 1 || cmbTinhTrang.getSelectedIndex() == 2) {
			cmbGio.setEnabled(false);
			cmbGioTangCa.setEnabled(false);
		} else {
			cmbGio.setEnabled(true);
			cmbGio.setEditable(false);
			cmbGioTangCa.setEnabled(true);
			cmbGioTangCa.setEditable(false);
		}
	}
	private void cmbTinhTrangActionPerformed(java.awt.event.ActionEvent evt) {
		thayDouCmbBox();
	}

	private void scrChamCongMouseClicked(java.awt.event.MouseEvent evt) {
	}

	public void capNhatCmbGio() {
		cmbGio.removeAllItems();
		if (cmbCaLam.getSelectedIndex() == 0) {
			cmbGio.addItem("7h30-11h30");
		} else if (cmbCaLam.getSelectedIndex() == 1) {
			cmbGio.addItem("13h-17h");
		}
	}

	private void cmbCaLamActionPerformed(java.awt.event.ActionEvent evt) {
		capNhatCmbGio();
		setHidden(btnHuy, btnLuu, btnChamCongTatCa, btnChamLai, btnCapNhat);
		setEditInput(false);
		btnChamLai.setEnabled(false);
		hienThiChamCongTheoNgay();
		while (modelChamCong.getRowCount() > 0) {
			modelChamCong.removeRow(0);
		}
		String selectedShift = (String) cmbCaLam.getSelectedItem();
	    if (selectedShift != null && !selectedShift.isEmpty()) {
	        try {
				hienThiChamCongTheoCaLam(selectedShift);
			} catch (ParseException e) {
				e.printStackTrace();
			}
	    }
	}

	private void btnLayDanhSachActionPerformed(java.awt.event.ActionEvent evt) {
		try {
			if (dcsNgayChamCong.getDate().after(new Date())) {
				JOptionPane.showMessageDialog(null, stErrNgayChamCong, stThongbao, JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			try {
				taiDuLieuLenBangChamCong();
			} catch (ParseException ex) {
				Logger.getLogger(ChamCongNhanVien_GUI.class.getName()).log(Level.SEVERE, null, ex);
			} catch (InterruptedException ex) {
				Logger.getLogger(ChamCongNhanVien_GUI.class.getName()).log(Level.SEVERE, null, ex);
			}
			if (tblChamCong.getRowCount() != 0) {
				setShow(btnChamCongTatCa);
			} else {
				setHidden(btnChamLai, btnChamCongTatCa);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, stNgayThangKhongHople, stThongbao, JOptionPane.ERROR_MESSAGE);
		}
	}

	private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {
		setHidden(btnCapNhat, btnChamLai, btnChamCongTatCa);
		cmbCaLam.setEnabled(false);
		setShow(btnHuy, btnLuu);
		setEditInput(true);
		thayDouCmbBox();
	}

	private void btnChamCongTatCaActionPerformed(java.awt.event.ActionEvent evt) {
	    daoChamCong = new ChamCongNhanVien_Dao();
	    String gio = "";
	    int gioTangCa = 0;
	    if (cmbCaLam.getSelectedIndex() == 0) {
	        gio = "7h30-11h30";
	    } else if (cmbCaLam.getSelectedIndex() == 1) {
	        gio = "13h00-17h00";
	    }

	    for (int i = 0; i < modelChamCong.getRowCount(); i++) {
	        NhanVien nhanVien = listNhanVienForEvtChamCongTatCa.get(i);

	        // Sử dụng giá trị từ cột "Có mặt" và "Nghỉ có phép"
	        Boolean isCoMat = (Boolean) modelChamCong.getValueAt(i, 4);
	        Boolean isNghiCoPhep = (Boolean) modelChamCong.getValueAt(i, 5);

	        // Kiểm tra giá trị null trước khi gọi booleanValue()
	        boolean coMat = isCoMat != null && isCoMat.booleanValue();
	        boolean nghiCoPhep = isNghiCoPhep != null && isNghiCoPhep.booleanValue();

	        String tinhTrang = "";
	        if (coMat) {
	            tinhTrang = "Đi làm";
	        } else if (nghiCoPhep) {
	            tinhTrang = "Nghỉ có phép";
	        } else {
	            tinhTrang = "Nghỉ không phép";
	        }

	        // Thêm giờ tăng ca từ tableChamCong vào tableNhanVien
	        Object gioTangCaObject = modelChamCong.getValueAt(i, 6);
	        gioTangCa = gioTangCaObject != null ? (Integer) gioTangCaObject : 0;

	        ChamCongNhanVien chamCong = new ChamCongNhanVien(
	            nhanVien,
	            dcsNgayChamCong.getDate(),
	            cmbCaLam.getSelectedItem().toString(),
	            tinhTrang,
	            gio,
	            gioTangCa,
	            nhanvienDangNhap
	        );

	        daoChamCong.themMotChamCongNhanVien(chamCong);
	    }

	    btnChamCongTatCa.setEnabled(false);
	    hienThiChamCongTheoNgay();

	    // Xóa dữ liệu trong bảng
	    while (modelChamCong.getRowCount() != 0) {
	        modelChamCong.removeRow(0);
	    }

	    btnChamLai.setEnabled(false);
	    JOptionPane.showMessageDialog(null, stChamCongThanhCong);
	}

	private void btnLuuActionPerformed(java.awt.event.ActionEvent evt){
		String gioLam = "";
		int gioTangCa = 0;

		if (cmbTinhTrang.getSelectedIndex() == 0) {
			gioLam = cmbGio.getSelectedItem().toString();
		} else if (cmbGio.isEnabled()) {
			gioLam = cmbGio.getSelectedItem().toString();
		} else {
			gioLam = "";
		}

		if (cmbTinhTrang.getSelectedIndex() == 0) {
			try {
				gioTangCa = Integer.parseInt(cmbGioTangCa.getSelectedItem().toString());
			} catch (NumberFormatException e) {
			}
		} else if (cmbGio.isEnabled()) {
			try {
				gioTangCa = Integer.parseInt(cmbGioTangCa.getSelectedItem().toString());
			} catch (NumberFormatException e) {
			}
		} else {
			gioTangCa = 0;
		}

		daoChamCong = new ChamCongNhanVien_Dao();
		daoNhanVien = new NhanVien_Dao();
		NhanVien nhanVienDuocChamCong = daoNhanVien.layMotNhanVienTheoMaNhanVien(txtMaNV.getText());
		if (checkChamCong) {
			ChamCongNhanVien chamcong = new ChamCongNhanVien(nhanVienDuocChamCong, dcsNgayChamCong.getDate(),
					cmbCaLam.getSelectedItem().toString(), cmbTinhTrang.getSelectedItem().toString(), gioLam, gioTangCa, nhanvienDangNhap);
			if (daoChamCong.themMotChamCongNhanVien(chamcong)) {
				try {
					JOptionPane.showMessageDialog(null, stChamCongThanhCong);
					taiDuLieuLenBangChamCong();
					hienThiChamCongTheoNgay();
					setShow(btnCapNhat, btnChamLai);
					setHidden(btnLuu, btnHuy);
				} catch (ParseException | InterruptedException ex) {
					Logger.getLogger(ChamCongNhanVien_GUI.class.getName()).log(Level.SEVERE, null, ex);
				} finally {
					if (modelChamCong.getRowCount() > 0) {
						setShow(btnChamCongTatCa);
					}
					setHidden(btnLuu, btnHuy, btnCapNhat, btnChamLai);
					checkChamCong = false;
				}
			}
			checkChamCong = false;
		} else {
			String ngayCham = tblNhanVien.getValueAt(tblNhanVien.getSelectedRow(), 7).toString();
			SimpleDateFormat dfm = new SimpleDateFormat("yyyy-MM-dd");
			Date kq = null;
			try {
				kq = dfm.parse(ngayCham);
			} catch (ParseException ex) {
				Logger.getLogger(ChamCongNhanVien_GUI.class.getName()).log(Level.SEVERE, null, ex);
			}
			ChamCongNhanVien chamcong = new ChamCongNhanVien(nhanVienDuocChamCong, kq,
					tblNhanVien.getValueAt(tblNhanVien.getSelectedRow(), 8).toString(), cmbTinhTrang.getSelectedItem().toString(), gioLam, gioTangCa, nhanvienDangNhap);
			if (daoChamCong.suaMotChamCongNhanVien(chamcong)) {
				//taiDuLieuLenBangNhanVien();
				hienThiChamCongTheoNgay();
				JOptionPane.showMessageDialog(null, stCapNhatThanhCong);
				if (modelChamCong.getRowCount() > 0) {
					setShow(btnChamCongTatCa, btnChamLai);
				}
				setHidden(btnLuu, btnHuy, btnCapNhat);
			}
		}
		cmbGio.setEnabled(false);
	}

	private void btnChamLaiActionPerformed(java.awt.event.ActionEvent evt) {
		setShow(btnLuu, btnHuy);
		setHidden(btnChamLai, btnCapNhat, btnChamCongTatCa);
		setEditInput(true);
		checkChamCong = true;
		cmbTinhTrang.setSelectedIndex(0);
		cmbGio.setEditable(false);
		cmbGioTangCa.setEditable(false);
		cmbGioTangCa.setEditable(false);
	}

	private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {
		setShow(btnChamLai, btnChamCongTatCa,btnCapNhat);
		if (modelChamCong.getRowCount() > 0) {
			setShow(btnChamLai, btnChamCongTatCa);
		} else {
			setHidden(btnChamLai, btnChamCongTatCa);
		}
		setHidden(btnLuu, btnHuy, btnCapNhat);
		checkChamCong = false;
		this.cmbTinhTrang.setEnabled(false);
		cmbGio.setEnabled(false);
	}

}