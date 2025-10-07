import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PanelGra extends JPanel {
    private int x = 0;
    private int belkaY, belkaX;
    private int szerokosc_belki = 100;
    private int wysokosc_belki = 15;

    private int ballX = 50;
    private int ballY = 200;
    private int ball_rozmiar = 20;
    private double ball_szybkosc_x = 2;
    private float ball_szybkosc_y = 2;
    private Color kolorpilka=Color.red;

    private Timer timer;
    private int liczbaCegiel=44;
    private Color kolorcegla_1=Color.blue;
    private List<Rectangle> cegly;
    protected int wynik=0;
    private Image tlo;
    JFrame frame;

    PanelGra(JFrame frame) {
        this.frame = frame;

        tlo = new ImageIcon("src/panslogo.png").getImage(); //  ścieżka do pliku e
        belkaY = getHeight() - wysokosc_belki; // Ustawienie belki na dole okna.
        belkaX = getWidth() - szerokosc_belki;



        //tworzenie pustego kursora,aby byl on niewidzialny podczas gry
        Cursor invisibleCursor = Toolkit.getDefaultToolkit().createCustomCursor(new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB), new Point(0, 0), "Niewidzialny kursor");
        setCursor(invisibleCursor);

        //inicjalizacja
        cegly = new ArrayList<>();
        int ceglyRzad = 11;
        int cegla_szerokosc = 60;
        int cegla_wysokosc = 30;
        int margines = 5;
        int startX = 10;
        int startY = 30;


        //wypelnienie listy ceglami
        for (int i = 0; i < liczbaCegiel; i++) {
            int wiersz = i / ceglyRzad; // - Ta linia kodu określa numer wiersza, w którym ma się znajdować cegła. Dzieli ona indeks i przez liczbę cegieł w rzędzie (cegielNaRzad) i zaokrągla wynik w dół do najbliższej liczby całkowitej. W ten sposób uzyskujemy indeks wiersza, licząc od zera.
            int kolumna = i % ceglyRzad;// Ta linia kodu określa numer kolumny, w której ma się znajdować cegła. Działa ona poprzez obliczenie reszty z dzielenia indeksu i przez liczbę cegieł w rzędzie (cegielNaRzad). W ten sposób uzyskujemy pozycję cegły wewnątrz wiersza.
            int ceglaX = startX + kolumna * (cegla_szerokosc + margines);// - Ta linia kodu oblicza położenie cegły wzdłuż osi X (poziomej) na podstawie jej numeru kolumny. Wartość startX określa początkową pozycję pierwszej cegły, a col * (ceglaWidth + margines) przesuwa kolejne cegły o odpowiednią ilość miejsca, aby utworzyć układ siatki.
            int ceglaY = startY + wiersz * (cegla_wysokosc + margines);// - Ta linia kodu oblicza położenie cegły wzdłuż osi Y (pionowej) na podstawie jej numeru wiersza. Wartość startY określa początkową pozycję pierwszej cegły, a row * (ceglaHeight + margines) przesuwa kolejne cegły o odpowiednią ilość miejsca, aby utworzyć układ siatki.
            Rectangle cegla = new Rectangle(ceglaX, ceglaY, cegla_szerokosc, cegla_wysokosc);// obiekt klasy Rectangle, reprezentuje cegłę, na podstawie obliczonych współrzędnych ceglaX, ceglaY oraz ustalonych wymiarów ceglaWidth i ceglaHeight. Ten prostokąt jest dodawany do listy cegly. Proces ten powtarza się dla każdej cegły, tworząc pełną listę cegieł na planszy gry.
            cegly.add(cegla);
        }

        //dzialanie belki na ruch myszy
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                x = e.getX() - szerokosc_belki / 2;
                // Zapewnienie, aby belka nie wyszła poza obszar okna
                if (x < 0) {
                    x = 0;
                } else if (x > getWidth() - szerokosc_belki) {
                    x = getWidth() - szerokosc_belki;
                }
                repaint();
            }
        });


        //objekt timer ktory co 10 milisekund przesuwa pilke,sprawdza kolizje z krawedziami oraz paletka i odswieza widok panelu
        timer = new Timer(10, e -> {
            ballX += ball_szybkosc_x;
            ballY += ball_szybkosc_y;
            //sprawdza czy pilka znajduje sie z lewej krawedzi panelu lub po nia
            //sprawdza czy prawa krawedz pilki znajduje sie na lub za prawa krawedzia panelu
            if (ballX <= 0 || ballX + ball_rozmiar >= getWidth()) {
                ball_szybkosc_x *= -1; //odbija pilke w przeciwnym kierunku poniewaz jeden warunek wyzej zostal spelniony
            }
            //pilka na gornej krawedzi panelu lub poza nia
            if (ballY <= 0) {
                //odbija w przeciwnym kierunku
                ball_szybkosc_y *= -1;
            }

            // Wykrywanie kolizji z belką
            //intersects-metoda, ktora sprawdza czy dwa prostokaty belka i pilka przecinaja sie
            if (new Rectangle(x, belkaY, szerokosc_belki, wysokosc_belki).intersects(new Rectangle(ballX, ballY, ball_rozmiar, ball_rozmiar))){
                ballY = belkaY - ball_rozmiar; // Zapobieganie "wklejaniu" się piłki do belki
                if (ballX <= x + szerokosc_belki / 2) { // Dotknięcie lewej krawędzi belki
                    ball_szybkosc_x = -Math.abs(ball_szybkosc_x); // Odbicie w lewo
                } else { // Dotknięcie prawej krawędzi belki
                    ball_szybkosc_x = Math.abs(ball_szybkosc_x); // Odbicie w prawo
                }
                ball_szybkosc_y *= -1;//pion
                //losowanie kierunku pilki na osi x po odbiciu pilki od belki
//                    ball_szybkosc_x = (Math.random() < 0.5) ? -Math.abs(ball_szybkosc_x) : Math.abs(ball_szybkosc_x);
                //ball_szybkosc_x=-1; dokoncz odbicie pilki od belki pod katem

                Random random1=new Random();
                int r=random1.nextInt(256);
                int g=random1.nextInt(256);
                int b=random1.nextInt(256);
                Color randomColorball=new Color(r,g,b);
                kolorpilka=randomColorball;

            }


            //wykrywanie kolizji z cegla
            Rectangle ballRect = new Rectangle(ballX, ballY, ball_rozmiar, ball_rozmiar);
            for (Rectangle cegla : cegly) {
                if (ballRect.intersects(cegla)) {
                    ball_szybkosc_y = -ball_szybkosc_y;
                    //losowanie losowej pozycji
                    ball_szybkosc_x=(Math.random() < 0.5) ? -Math.abs(ball_szybkosc_x) : Math.abs(ball_szybkosc_x);
                    cegly.remove(cegla);
                    ball_szybkosc_x += 1;
                    ball_szybkosc_y += 0.3;
                    wynik++;
                    Random random = new Random();
                    int r = random.nextInt(256);
                    int g = random.nextInt(256);
                    int b = random.nextInt(256);
                    Color randomColor = new Color(r, g, b);
                    kolorcegla_1 = randomColor;


                    break;

                }
            }

            //sprawadzenie czy wynik=liczba cegiel
            if(wynik==liczbaCegiel){
                timer.stop();
                this.removeAll();
                frame.setContentPane(new Zakonczenie(wynik, frame));
                frame.revalidate();
            }

            //sprawdzanie kolizji z dolna krawedzia
            if (ballY + ball_rozmiar >= getHeight()) {
//                koniecGry = true;
                timer.stop();
                this.removeAll();
                frame.setContentPane(new Zakonczenie(wynik, frame));
                frame.revalidate();


            }

            repaint();
        });


        timer.start();

    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Rysowanie tła
        if (tlo != null) {
            g.drawImage(tlo, 0, 0, getWidth(), getHeight(), this);
        }

        // Aktualizacja pozycji belki, aby była na samym dole okna
        belkaY = getHeight() - wysokosc_belki;

        // Rysowanie belki
        g.setColor(Color.red);
        g.fillRect(x, belkaY, szerokosc_belki, wysokosc_belki);

        // Rysowanie piłki
        g.setColor(kolorpilka);
        g.fillOval(ballX, ballY, ball_rozmiar, ball_rozmiar);


        //rysowanie cegiel

        g.setColor(kolorcegla_1);
        for (Rectangle cegla : cegly) {
            g.fillRect(cegla.x, cegla.y, cegla.width, cegla.height);


        }

        //rysowanie wyniku

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 25));
        g.drawString("Wynik: " + wynik, 20, 20); //lewy gorny rog wynik



    }
}
