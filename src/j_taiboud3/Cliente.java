/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package j_taiboud3;


import static j_taiboud3.Servidor.servi;
import java.awt.Checkbox;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author josetaibobueno@hotmail.com
 */
public class Cliente extends JFrame implements ActionListener{

    static String[][] preguntas;
    static Cliente pantallacliente;
    static JLabel label1, label2;
    static JTextArea mensaje;
    static JScrollPane scrollpanel;
    static JButton enviar;
    static JButton salir;
    static Socket client;
    static ButtonGroup[] group = new ButtonGroup[5];
    static JRadioButton[] radiogrupo = new JRadioButton[15];
    public static JTextField acertadas;
    public static JTextField falladas;
    static JRadioButton radio;
    static int contador;
    static String[] a1 = new String[5];
    static String[] pregun = new String[5];
    static String[] respu = new String[15];
    static JLabel a;
    DataInputStream leer;

    public Cliente() {

        super("Test Psicotécnicos");
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        setLayout(null);

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); //Se anula el cierre de la ventana para que se haga desde Salir
        enviar = new JButton("Enviar");
        enviar.setBounds(300, 620, 100, 30);
        add(enviar);
        enviar.setEnabled(true);
        enviar.addActionListener(this);

        salir = new JButton("Salir del cuestionario");
        salir.setBounds(500, 620, 170, 30);
        add(salir);
        salir.setEnabled(false);
        salir.addActionListener(this);

        a = new JLabel("Caja de Respuestas:");
        a.setBounds(700, 578, 180, 12);
        add(a);

        mensaje = new JTextArea();
        scrollpanel = new JScrollPane(mensaje);
        scrollpanel.setBounds(700, 590, 200, 75);
        add(scrollpanel);
        mensaje.setEditable(false);

        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/fotos/logo.png"));
        setIconImage(icon);

        setSize(1000, 700);

        //this.setLocationRelativeTo(null); //Centrar el frame
        this.setResizable(false); //Se anula el poder redimensionar la pantalla

        inicializar();
        disponer();

        setVisible(true);

    }

    public void inicializar() {

        try {

            leer = new DataInputStream(client.getInputStream());

            int contadorP = 0;
            int contadorR = 0;

            for (int i = 0; i < 20; i++) {
                String texto = leer.readUTF();
                if (i % 4 == 0) {

                    pregun[contadorP++] = texto;
                } else {

                    respu[contadorR++] = texto;

                }

            }
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void disponer() {

        
        for (int i = 0; i < pregun.length; i++) {

            JLabel a = new JLabel(pregun[i]);
            a.setBounds(80, 0 + i * 125, 800, 15);
            add(a);
            group[i] = new ButtonGroup();

            for (int j = i * 3; j < i * 3 + 3; j++) {
                radiogrupo[j] = new JRadioButton(respu[j]);
                radiogrupo[j].setBounds(84, (i) + 15 + j * 42, 170, 12);
                add(radiogrupo[j]);
                group[i].add(radiogrupo[j]);

            }

        }

        setVisible(true);

    }

    public static void main(String[] args) throws IOException {

        client = new Socket("localhost", 1111);

        Splash s = new Splash();
        s.setLocationRelativeTo(null);
        s.setVisible(true);

        
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == salir) {

            try {
                client.close();

            } catch (IOException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.exit(0);

        } else if (e.getSource() == enviar) {

            enviar();
            salir.setEnabled(true);
            enviar.setEnabled(false);

        }

    }

    public void enviar() {

        try {
            DataOutputStream escritor;
            escritor = new DataOutputStream(client.getOutputStream());

            for (int i = 0; i < radiogrupo.length; i++) {

                try {
                    if (radiogrupo[i].isSelected()) {
                        salir.setEnabled(true);
                        

                        String vacio = radiogrupo[i].getText();

                        escritor.writeUTF(vacio);

                        
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            try {

                DataInputStream leerr = new DataInputStream((client.getInputStream()));
                for (int i = 1; i < 6; i++) {
                    mensaje.append(i + contador + ":" + leerr.readUTF() + "\n");
                }

            } catch (IOException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
