import javax.sound.sampled.*;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class ArkanoidPanel extends JFrame  {

    public static void main(String[] args) {
        new ArkanoidPanel();
    }

    PanelGlowny panelGlowny;
    Zakonczenie zakonczenie;
    JLabel napis,napis1;



    ArkanoidPanel() {
        super("Arkanoid game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(0, 0, 750, 600);
        setResizable(false);



        panelGlowny = new PanelGlowny(this);
        //tworzenie labeli
        setLayout(null);
        napis=new JLabel("Witaj w grze");
        napis.setBounds(305,140,200,50);
        napis.setFont(new Font(Font.SANS_SERIF, Font.BOLD,28));
        napis.setForeground(Color.white);
        panelGlowny.add(napis);

        napis1=new JLabel("ARKANOID");
        napis1.setBounds(305,145,300,150);
        napis1.setFont(new Font(Font.SERIF,Font.BOLD,30));
        napis1.setForeground(Color.white);
        panelGlowny.add(napis1);

        setLocationRelativeTo(null);
        setResizable(false);

        setContentPane(panelGlowny);
        setVisible(true);


    }

    public void pokazOkno(JPanel panel) {
        panelGlowny.removeAll();
        setContentPane(panel);
        panel.revalidate();
        panel.repaint();
    }

}




