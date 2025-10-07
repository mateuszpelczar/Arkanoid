import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class PanelGlowny extends JPanel implements ActionListener {
    JButton przyciskOpenGame,closeButton;
    ArkanoidPanel arkanoidPanel;
    private Color kolorpanelu1=Color.gray;
    JLabel tekst;
    private Image background;

    PanelGlowny(ArkanoidPanel arkanoidPanel) {

        this.arkanoidPanel = arkanoidPanel;
        background = new ImageIcon("src/tlo.png").getImage(); //  ścieżka do pliku
        setBackground(getBackground());

        setLayout(null);

        przyciskOpenGame = new JButton("Rozpocznij");
        przyciskOpenGame.setBounds(325, 250, 100, 25);
        add(przyciskOpenGame);
        przyciskOpenGame.addActionListener(this);

        closeButton=new JButton("Wyjdz");
        closeButton.setBounds(325,280,100,25);
        add(closeButton);
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (background != null) {
            g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o == przyciskOpenGame) {
            PanelGra panelGra = new PanelGra(arkanoidPanel);
            arkanoidPanel.pokazOkno(panelGra);
        }

    }


}

