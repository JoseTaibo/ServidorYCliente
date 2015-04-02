/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package j_taiboud3;

import static j_taiboud3.HiloC.elegida;
import java.awt.Checkbox;
import java.awt.Image;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author josetaibobueno@hotmail.com
 */
public class Servidor extends JFrame implements ActionListener{

    static ServerSocket servi;
    static Socket s = new Socket();
    static JTextField mensaje = new JTextField("");
    static JTextField mensaje2 = new JTextField("");
    static JLabel label1;
    JButton desconectar = new JButton("Desconectar Servidor");
    JButton salir = new JButton("Salir de la Aplicacion");
    private JScrollPane scrollpanel;
    static JTextArea textarea;
    static int contador = 0;
    static int[] indices = new int[5];
    static Servidor pantalla = new Servidor();
    static String[][] preguntas = new String[19][4];
    DataInputStream leer;
    static String[] a = new String[5];
    static String[] respuescorrectas = new String[]{"18", "12", "S/.1800", "4", "F", "Vicuña", "KLH", "70", "14", "47", "Q", "F", "1152", "Heytenic"};

    public Servidor() {

        super("Test Psicotécnicos");
       

        mensaje.setBounds(0, 400, 420, 80);
        add(mensaje);
        mensaje.setEditable(false);
        mensaje.setBackground(java.awt.Color.WHITE);

        mensaje2.setBounds(0, 480, 420, 80);
        add(mensaje2);
        mensaje2.setEditable(false);
        mensaje2.setBackground(java.awt.Color.WHITE);

        label1 = new JLabel(new ImageIcon(getClass().getResource("/fotos/fondo.png")));
        label1.setBounds(0, 70, 420, 420);
        add(label1);

        desconectar.setBounds(0, 60, 420, 50);
        desconectar.setLayout(null);
        desconectar.setBackground(java.awt.Color.YELLOW);
        desconectar.setVisible(true);
        add(desconectar);
        desconectar.addActionListener(this);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); //Se anula el cierre de la ventana para que se haga desde Salir

        salir.setBounds(0, 0, 420, 70);
        salir.setLayout(null);
        salir.setBackground(java.awt.Color.ORANGE);
        add(salir);
        salir.addActionListener(this);

        textarea = new JTextArea("Esperando a Usuarios.......\n");
        scrollpanel = new JScrollPane(textarea);
        scrollpanel.setBounds(0, 560, 420, 120);
        add(scrollpanel);
        textarea.setEditable(false);

        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/fotos/logo.png"));
        setIconImage(icon);

        setLocationRelativeTo(null); //Centrar el frame
        setResizable(false); //Se anula el poder redimensionar la pantalla
        salir.setVisible(true);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == desconectar) {
            try {
                servi.close();
                s.close();
                mensaje2.setText("Servidor esta desconectado");
            } catch (IOException ex) {

              //Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (e.getSource() == salir) {

            try {
                servi.close();
                s.close();
                System.exit(0);
            } catch (IOException ex) {
                //Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public static void main(String[] args) throws IOException {

        servi = new ServerSocket(1111);

        mensaje.setText("Server escucha por " + InetAddress.getLocalHost() + " desde el puerto " + servi.getLocalPort());
        mensaje2.setText("Servidor Iniciado......");

        //Servidor pantalla=new Servidor();
        pantalla.setLocationRelativeTo(null);
        pantalla.setBounds(0, 0, 420, 700);
        pantalla.setVisible(true);

        preguntas[0][0] = "Si  al   doble  de  un  número  le  resto  el  propio\n" + "número, se obtiene 18. Hallar el número.";
        preguntas[0][1] = "18";
        preguntas[0][2] = "36";
        preguntas[0][3] = "24";

        preguntas[1][0] = "Un alambre de 36m se ha dividido en dos partes, de  manera  que  una de ellas es  el  doble  de  la otra. Calcular la longitud de la parte menor.";
        preguntas[1][1] = "12";
        preguntas[1][2] = "6";
        preguntas[1][3] = "22";

        preguntas[2][0] = "Pedro tiene el doble que Luis,  y  Mateo el doble que Pedro y Luis juntos. Si entre los tres tienen 16 200 soles; hallar cuanto tiene Luis.";
        preguntas[2][1] = "S/.1800";
        preguntas[2][2] = "S/.2800";
        preguntas[2][3] = "S/.1801";

        preguntas[3][0] = "Calcular el 10% del 50% de 80.";
        preguntas[3][1] = "4";
        preguntas[3][2] = "2";
        preguntas[3][3] = "3";

        preguntas[4][0] = "¿Qué letra continúa? C; A; D; B; E; C; ...... ";
        preguntas[4][1] = "F";
        preguntas[4][2] = "A";
        preguntas[4][3] = "G";

        preguntas[5][0] = "¿Qué animal es el que aparece en el Escudo Peruano? ";
        preguntas[5][1] = "Vicuña";
        preguntas[5][2] = "Guanaco";
        preguntas[5][3] = "Palomo Cojo";

        preguntas[6][0] = "¿Hallar las letras que siguen: BCH, EFH, HIH, ..... ";
        preguntas[6][1] = "KLH";
        preguntas[6][2] = "MLN";
        preguntas[6][3] = "MNL";

        preguntas[7][0] = "Hallar el número que continúa en: 2; 10; 24; 44;...";
        preguntas[7][1] = "70";
        preguntas[7][2] = "72";
        preguntas[7][3] = "50";

        preguntas[8][0] = "Hallar el número que continúa en: 2; 6; 8; 10; 12; ...";
        preguntas[8][1] = "14";
        preguntas[8][2] = "16";
        preguntas[8][3] = "20";

        preguntas[9][0] = "Hallar el término que falta en la sucesión: 2, 5, 10, 18, 30, ?";
        preguntas[9][1] = "47";
        preguntas[9][2] = "72";
        preguntas[9][3] = "50";

        preguntas[10][0] = "Hallar la letra que continúa: L; D; Ñ; F; P; G; .....";
        preguntas[10][1] = "Q";
        preguntas[10][2] = "O";
        preguntas[10][3] = "P";

        preguntas[11][0] = "¿Qué letra continúa? C; A; D; B; E; C; ...... ";
        preguntas[11][1] = "F";
        preguntas[11][2] = "E";
        preguntas[11][3] = "W";

        preguntas[12][0] = "¿Hallar el tèrmino que continúa:1/8; 1/4; 1/2; 2; 24;...";
        preguntas[12][1] = "1152";
        preguntas[12][2] = "2345";
        preguntas[12][3] = "1273";

        preguntas[13][0] = "Pregunta Chistoza:¿Que palabra con asiduo dice Patricio Santiago Fernandez?";
        preguntas[13][1] = "Heytenic";
        preguntas[13][2] = "Sin Verguenza";
        preguntas[13][3] = "Falto a la democracia";

        while (true) {

            try {

                s = servi.accept();
                mensaje2.setText("Servidor iniciado....");
                HiloC c = new HiloC(s);
                c.start();

            } catch (IOException ex) {
                //Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}


    
    

    
