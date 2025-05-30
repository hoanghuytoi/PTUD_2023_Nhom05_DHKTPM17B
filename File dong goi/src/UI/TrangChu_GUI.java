package UI;

import javax.swing.*;
import java.awt.*;

public class TrangChu_GUI extends JPanel {
	/**
	 * Hoàng Huy Tới
	 */

	private static final long serialVersionUID = 1L;

	public TrangChu_GUI() {
    	setBackground(new Color(255, 255, 255));
        setLayout(null);
        setSize(1290, 750);

        JLabel lblHinhAnh = new JLabel();
        lblHinhAnh.setBackground(new Color(255, 255, 255));
		lblHinhAnh.setIcon(new ImageIcon(NhanVien_GUI.class.getResource("/image/trangChu.png")));
        lblHinhAnh.setBounds(0, 0, 1290, 750);
        add(lblHinhAnh);

    }
}
