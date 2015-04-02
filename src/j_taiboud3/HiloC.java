

package j_taiboud3;


import static j_taiboud3.Servidor.preguntas;
import static j_taiboud3.Servidor.s;
import java.awt.Checkbox;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JRadioButton;

/**
 *
 * @author josetaibobueno@hotmail.com
 */
public class HiloC  extends Thread{
    
    
    static int elegida[] = new int[5];
    ServerSocket servi;
    static String[][] preguntas = new String[19][4];
    int contadora = 0;
    int contadorf = 0;
    static Socket s = new Socket();
    static String[] repuesusuarios;
    DataInputStream leer;
    DataOutputStream escribir;
    static int[] indices = new int[5];
    static String[] a = new String[5];
    static String[] respuescorrectas = new String[]{"18", "12", "S/1800", "4", "F", "Vicuña", "KLH", "70", "34", "47", "Q", "F", "1152", "Heytenic", "30", "17", "4", "$275", "Falla"};

    public HiloC(Socket s) throws IOException {
        this.s = s;

        leer = new DataInputStream((s.getInputStream()));

    }

    public void run() {

        try {

            preguntas[0][0] = "Si  al   doble  de  un  número  le  resto  el  propio número, se obtiene 18.Hallar el número.";
            preguntas[0][1] = "18";
            preguntas[0][2] = "36";
            preguntas[0][3] = "24";

            preguntas[1][0] = "Un alambre de 36m se ha dividido en dos partes, de  manera  que  una de ellas es  el  doble  de  la otra. Calcular la longitud de la parte menor.";
            preguntas[1][1] = "22";
            preguntas[1][2] = "6";
            preguntas[1][3] = "12";

            preguntas[2][0] = "Pedro tiene el doble que Luis,  y  Mateo el doble que Pedro y Luis juntos. Si entre los tres tienen 16 200 soles; hallar cuanto tiene Luis.";
            preguntas[2][1] = "S/1801";
            preguntas[2][2] = "S/2800";
            preguntas[2][3] = "S/1800";

            preguntas[3][0] = "Calcular el 10% del 50% de 80.";
            preguntas[3][1] = "2";
            preguntas[3][2] = "4";
            preguntas[3][3] = "3";

            preguntas[4][0] = "¿Qué letra continúa? C; A; D; B; E; C; ...... ";
            preguntas[4][1] = "A";
            preguntas[4][2] = "F";
            preguntas[4][3] = "G";

            preguntas[5][0] = "¿Qué animal es el que aparece en el Escudo Peruano? ";
            preguntas[5][1] = "Vicuña";
            preguntas[5][2] = "Guanaco";
            preguntas[5][3] = "Palomo Cojo";

            preguntas[6][0] = "¿Hallar las letras que siguen: BCH, EFH, HIH, ..... ";
            preguntas[6][1] = "OLH";
            preguntas[6][2] = "KLH";
            preguntas[6][3] = "MNL";

            preguntas[7][0] = "Hallar el número que continúa en: 2; 10; 24; 44;...";
            preguntas[7][1] = "70";
            preguntas[7][2] = "72";
            preguntas[7][3] = "50";

            preguntas[8][0] = "Hallar el número que continúa en: 2; 10; 18; 26;...";
            preguntas[8][1] = "28";
            preguntas[8][2] = "35";
            preguntas[8][3] = "34";

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

            preguntas[14][0] = "¿Cuánto es 2/4 de 60?";
            preguntas[14][1] = "30";
            preguntas[14][2] = "45";
            preguntas[14][3] = "21";

            preguntas[15][0] = "(0.25 * 34) / 0.5";
            preguntas[15][1] = "17";
            preguntas[15][2] = "12";
            preguntas[15][3] = "23";

            preguntas[16][0] = "Calcular el 10% del 50% de 80.";
            preguntas[16][1] = "4";
            preguntas[16][2] = "3";
            preguntas[16][3] = "2";

            preguntas[17][0] = "Dos hermanos ahorran $ 300. Si el mayor tiene 11 veces lo que tiene el menor. ¿Cuánto tiene el mayor?";
            preguntas[17][1] = "$275";
            preguntas[17][2] = "$285";
            preguntas[17][3] = "$255";

            preguntas[18][0] = "¿Como se llama el teatro mas famoso de cadiz";
            preguntas[18][1] = "Falla";
            preguntas[18][2] = "Cortes";
            preguntas[18][3] = "Gades";

            try {
                escribir = new DataOutputStream(s.getOutputStream());
            } catch (IOException ex) {
                Logger.getLogger(HiloC.class.getName()).log(Level.SEVERE, null, ex);
            }
            for (int i = 0; i < 5; i++) {

                int aleat = (int) (Math.random() * preguntas.length);

                indices[i] = aleat;
                asignar(aleat, i);
                try {
                    escribir.writeUTF(preguntas[elegida[i]][0]);
                } catch (IOException ex) {
                    Logger.getLogger(HiloC.class.getName()).log(Level.SEVERE, null, ex);
                }

                for (int j = 1; j <= 3; j++) {
                    try {
                        escribir.writeUTF(preguntas[elegida[i]][j]);
                    } catch (IOException ex) {
                        Logger.getLogger(HiloC.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

            }

            try {
                leer = new DataInputStream(s.getInputStream());

                for (int i = 0; i < a.length; i++) {

                    String h = leer.readUTF();
                    a[i] = h;

                }
            } catch (EOFException e) {

            }

            for (int j = 0; j < a.length; j++) {

                if (a[j].equals(respuescorrectas[indices[j]])) {

                    try {
                        DataOutputStream dos = new DataOutputStream(s.getOutputStream());
                    } catch (IOException ex) {
                        Logger.getLogger(HiloC.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    String acierto = "Correcta";
                    try {

                        escribir.writeUTF(acierto);

                    } catch (IOException ex) {
                        Logger.getLogger(HiloC.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else {

                    String fallado = "Fallada";
                    try {

                        escribir.writeUTF(fallado);

                    } catch (IOException ex) {
                        Logger.getLogger(HiloC.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(HiloC.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void asignar(int selecionado, int subindice) {

        for (int i = 0; i < elegida.length; i++) {

            if (elegida[i] == selecionado) {
                int aleat = (int) (Math.random() * preguntas.length);
                asignar(aleat, subindice);
            }
        }

        elegida[subindice] = selecionado;

    }

}
