package j_taiboud3;


import j_taiboud3.Cliente;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;


/**
 *
 * @author josetaibobueno@hotmail.com
 */


public class Splash extends javax.swing.JFrame {

    private Timer t;
    private ActionListener al;

    public Splash() {
        al = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                if (jProgressBar1.getValue() < 100) {
                    jProgressBar1.setValue(jProgressBar1.getValue() + 5);
                } else {
                    t.stop();
                    mostrarventana();
                }
            }
        };
        t = new Timer(200, al);
        initComponents();
        setSize(420, 378);
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        t.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());
        getContentPane().setLayout(null);
        getContentPane().add(jProgressBar1);
        jProgressBar1.setBounds(130, 320, 150, 20);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fotos/fondo.PNG"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 434, 350);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("fotos/logo.png"));

        return retValue;
    }

    private void mostrarventana() {
        Menu c = new Menu();
        c.setLocationRelativeTo(null);
        c.setVisible(true);
        this.dispose();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JProgressBar jProgressBar1;
    // End of variables declaration//GEN-END:variables
}
