package Custom_UI;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JScrollBar;

public class ScrollBarCustom extends JScrollBar {

    private static final long serialVersionUID = 1L;

	public ScrollBarCustom() {
        setUI(new ModernScrollBarUI());
        setPreferredSize(new Dimension(10,10));
        setForeground(new Color(48, 144, 216));
        setBackground(Color.WHITE);
    }
}
