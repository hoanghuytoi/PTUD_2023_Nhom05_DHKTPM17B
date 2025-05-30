package Custom_UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;
import javax.swing.border.BevelBorder;

public class MenuItem extends JPanel {

    /**
	 * Hoàng Huy Tới
	 */
	private static final long serialVersionUID = 1L;
	private JSeparator jSeparator;
    private javax.swing.JLabel lblIcon;
    private javax.swing.JLabel lblName;
    
    private final ArrayList<MenuItem> subMenu = new ArrayList<>();
    private ActionListener act;
    
    private boolean showing = false;
    
    public void setShowing(boolean showing) {
        this.showing = showing;
    }
    
    public MenuItem(Icon icon, String menuName, ActionListener act, MenuItem... subMenu) {
    	setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        initComponents();
        lblIcon.setIcon(icon);
        lblName.setText(menuName);
        if (act != null) {
            this.act = act;
        }
        int size=80;
        this.setSize(new Dimension(Integer.MAX_VALUE, size));
        this.setMaximumSize(new Dimension(Integer.MAX_VALUE, size));
        this.setMinimumSize(new Dimension(Integer.MAX_VALUE, size));
        for (int i = 0; i < subMenu.length; i++) {
            this.subMenu.add(subMenu[i]);
            subMenu[i].setVisible(false);
        }
    }
    
    public void setIcon(Icon icon){
        lblIcon.setIcon(icon);
    }
    
    private void showMenu() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < subMenu.size(); i++) {
                    sleep();
                    subMenu.get(i).setVisible(true);
                }
                showing = true;
                getParent().repaint();
                getParent().revalidate();
            }
        }).start();
    }

    private void hideMenu() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = subMenu.size() - 1; i >= 0; i--) {
                    sleep();
                    subMenu.get(i).setVisible(false);
                    subMenu.get(i).hideMenu();
                }
                getParent().repaint();
                getParent().revalidate();
                showing = false;
            }
        }).start();
    }

    private void sleep() {
        try {
            Thread.sleep(20);
        } catch (Exception e) {
        }
    }

    private void initComponents() {
        jSeparator = new JSeparator();
        lblIcon = new JLabel();
        lblName = new JLabel();

        setBackground(new Color(255, 255, 255));
        setMinimumSize(new Dimension(32767, 32767));
        setPreferredSize(new Dimension(210, 65));
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                formMousePressed(evt);
            }
        });
        setLayout(new AbsoluteLayout());

        lblIcon.setHorizontalAlignment(SwingConstants.CENTER);
        lblIcon.setIcon(new ImageIcon(getClass().getResource("/image/icon/congnhan.png"))); 
        add(lblIcon, new AbsoluteConstraints(0, 0, 70, 70));

        lblName.setFont(new Font("Times New Roman", Font.BOLD, 16));
        lblName.setHorizontalAlignment(SwingConstants.LEFT);
        lblName.setText("Menu name here....");
        lblName.setPreferredSize(new Dimension(113, 50));
        add(lblName, new AbsoluteConstraints(80, 10, 170, -1));
        add(jSeparator, new AbsoluteConstraints(0, 80, 250, -1));
        
    }
    
    private void formMousePressed(MouseEvent evt) {
        if (showing) {
            hideMenu();
            showing = false;
        } else {
            showMenu();
            showing = true;
        }
        if(this.act !=null){
            act.actionPerformed(null);
        }
    }
    
    public ArrayList<MenuItem> getSubMenu() {
        return subMenu;
    }
}
