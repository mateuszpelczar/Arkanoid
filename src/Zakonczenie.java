import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Zakonczenie extends JPanel {

    JButton zakonczGre, odNowa;
    JLabel text, text1;
    int wynik;


    JFrame frame;

    Image endGameBackground;

    Zakonczenie(int wynik, JFrame frame) {
        this.wynik = wynik;
        this.frame = frame;

        endGameBackground = new ImageIcon("src/tlo1.png").getImage();


        zakonczGre = new JButton("Zakoncz gre");
        zakonczGre.setBounds(300, 400, 150, 40);
        zakonczGre.setBackground(Color.cyan);

        odNowa = new JButton("Rozpocznij od nowa");
        odNowa.setBounds(280, 350, 200, 40);
        odNowa.setBackground(Color.CYAN);
        odNowa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new ArkanoidPanel();
                revalidate();

            }
        });

        add(zakonczGre);
        add(odNowa);

        zakonczGre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        setLayout(null);
        text = new JLabel("Koniec gry");
        text.setBounds(295, 60, 300, 80);
        text.setFont(new Font("Arial", Font.BOLD, 30));

        text1 = new JLabel("Twoj wynik to: " + wynik);
        text1.setBounds(240, 160, 300, 80);
        text1.setFont(new Font("Arial", Font.BOLD, 30));


        add(text);
        add(text1);
        add(zakonczGre);

        repaint();


    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(endGameBackground, 0, 0, getWidth(), getHeight(), this);

    }
}
