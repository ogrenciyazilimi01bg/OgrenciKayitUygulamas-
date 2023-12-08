import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Menu extends JFrame{
    private JButton OgrenciKayitFormuButton;
    private JPanel PanelMenu;
    private JButton DersKayitFormuButton;

    public  Menu(){
        setTitle("Menü");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null);
        setVisible(true);
        setContentPane(PanelMenu);
        DersKayitFormuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DersKayitFormu DersKayitFormu = new DersKayitFormu();
                DersKayitFormu.setVisible(true);
            }
        });
        //Burda OgrenciKayitFormuButton özellikler var
        OgrenciKayitFormuButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                OgrenciKayitFormuButton.setBackground(new Color( 0 ,0 , 255 ));
                super.mouseEntered(e);
            }
        });
        OgrenciKayitFormuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OgrenciKayitFormu OgrenciKayitFormu = new OgrenciKayitFormu();
                OgrenciKayitFormu.setVisible(true);
            }
        });
        OgrenciKayitFormuButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                OgrenciKayitFormuButton.setBackground(new Color(197,98,232));
                super.mouseExited(e);
            }
        });

        //Burda DersKayitFormuButton özellikler var
        DersKayitFormuButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                DersKayitFormuButton.setBackground(new Color(232, 71, 37));
                super.mouseEntered(e);
            }
        });
        DersKayitFormuButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                DersKayitFormuButton.setBackground(new Color(197, 98, 232));
                super.mouseExited(e);
            }
        });
    }
    public static void main(String[] args) {
        new Menu();
    }
}