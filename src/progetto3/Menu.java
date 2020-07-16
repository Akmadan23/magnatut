/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progetto3;
import javax.swing.JOptionPane;

/**
 *
 * @author azadahmadi
 */
public class Menu extends javax.swing.JFrame {

    /**
     * Creates new form Menu
     */
    public Menu() {
        initComponents();
        a = false;
        c = true;
        rbn1.setSelected(true);
        cbxColor2.setEnabled(false);
        cbxColor3.setEnabled(false);
        cbxBot2.setEnabled(false);
        cbxBot3.setEnabled(false);
        txt2.setEnabled(false);
        txt3.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btgGiocatori = new javax.swing.ButtonGroup();
        btgTema = new javax.swing.ButtonGroup();
        btgMusica = new javax.swing.ButtonGroup();
        opn1 = new javax.swing.JOptionPane();
        btnStart = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        rbn1 = new javax.swing.JRadioButton();
        lblTitle = new javax.swing.JLabel();
        rbn3 = new javax.swing.JRadioButton();
        cbxColor1 = new javax.swing.JComboBox<>();
        cbxColor2 = new javax.swing.JComboBox<>();
        cbxColor3 = new javax.swing.JComboBox<>();
        rbn2 = new javax.swing.JRadioButton();
        btnControlli = new javax.swing.JButton();
        txt3 = new javax.swing.JTextField();
        txt2 = new javax.swing.JTextField();
        txt1 = new javax.swing.JTextField();
        lblNome = new javax.swing.JLabel();
        lblColore = new javax.swing.JLabel();
        lblBot = new javax.swing.JLabel();
        cbxBot1 = new javax.swing.JComboBox<>();
        cbxBot2 = new javax.swing.JComboBox<>();
        cbxBot3 = new javax.swing.JComboBox<>();
        sep1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnStart.setText("Inizia!");
        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartActionPerformed(evt);
            }
        });

        btnExit.setText("Esci");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        btgGiocatori.add(rbn1);
        rbn1.setText("1 Giocatore");
        rbn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbn1ActionPerformed(evt);
            }
        });

        lblTitle.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(0, 0, 204));
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("Magna Tut");

        btgGiocatori.add(rbn3);
        rbn3.setText("3 Giocatori");
        rbn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbn3ActionPerformed(evt);
            }
        });

        cbxColor1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nero", "Rosso", "Blu", "Verde", "Giallo" }));

        cbxColor2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nero", "Rosso", "Blu", "Verde", "Giallo" }));
        cbxColor2.setSelectedIndex(1);

        cbxColor3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nero", "Rosso", "Blu", "Verde", "Giallo" }));
        cbxColor3.setSelectedIndex(2);

        btgGiocatori.add(rbn2);
        rbn2.setText("2 Giocatori");
        rbn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbn2ActionPerformed(evt);
            }
        });

        btnControlli.setText("Controlli");
        btnControlli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnControlliActionPerformed(evt);
            }
        });

        lblNome.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNome.setText("Nome:");

        lblColore.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblColore.setText("Colore:");

        lblBot.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBot.setText("N° di Bot:");

        cbxBot1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3" }));

        cbxBot2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3" }));

        cbxBot3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sep1)
                    .addComponent(lblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnControlli, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnExit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnStart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rbn1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rbn3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rbn2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(txt3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(cbxColor3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(txt2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(cbxColor2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(lblNome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txt1, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(cbxColor1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(lblColore, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cbxBot3, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbxBot2, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbxBot1, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblBot, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 3, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblColore)
                        .addComponent(lblNome))
                    .addComponent(lblBot, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxColor1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt1)
                    .addComponent(rbn1)
                    .addComponent(cbxBot1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxColor2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt2)
                    .addComponent(rbn2)
                    .addComponent(cbxBot2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxColor3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt3)
                    .addComponent(rbn3)
                    .addComponent(cbxBot3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sep1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnStart)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnControlli)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnExit)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartActionPerformed
        if (rbn1.isSelected()) ng = 1;
        else if (rbn2.isSelected()) ng = 2;
        else if (rbn3.isSelected()) ng = 3;
        
        colori = new String [ng];
        nomi = new String [ng];
        
        switch (ng) {
            case 1:
                nb = Integer.parseInt(cbxBot1.getSelectedItem().toString());
                colori [0] = cbxColor1.getSelectedItem().toString();
                nomi [0] = txt1.getText();
                break;
                
            case 2:
                nb = Integer.parseInt(cbxBot2.getSelectedItem().toString());
                colori [0] = cbxColor1.getSelectedItem().toString();
                colori [1] = cbxColor2.getSelectedItem().toString();
                nomi [0] = txt1.getText();
                nomi [1] = txt2.getText();
                
                if (colori [0].equals(colori [1])) {
                    c = false;
                    opn1.showMessageDialog(
                            this,
                            "Seleziona colori diversi per i due giocatori",
                            "Attenzione",
                            JOptionPane.WARNING_MESSAGE
                    );
                }   
                
                break;
                
            case 3:
                nb = Integer.parseInt(cbxBot3.getSelectedItem().toString());
                colori [0] = cbxColor1.getSelectedItem().toString();
                colori [1] = cbxColor2.getSelectedItem().toString();
                colori [2] = cbxColor3.getSelectedItem().toString();
                nomi [0] = txt1.getText();
                nomi [1] = txt2.getText();
                nomi [2] = txt3.getText();
                
                if (colori [0].equals(colori [1]) || colori [1].equals(colori [2]) || colori [2].equals(colori [0])) {
                    c = false;
                    opn1.showMessageDialog(
                            this,
                            "Seleziona colori diversi per i due giocatori",
                            "Attenzione",
                            JOptionPane.WARNING_MESSAGE
                    );
                }
                
                break;
                
            default:
                break;
        }
        
        if (!a && c) {
            f2 = new Mappa1(this, ng, nb, nomi, colori);
            a = true;
        }
        
        if (c) f2.setVisible(true);
        else c = true;
    }//GEN-LAST:event_btnStartActionPerformed

    private void rbn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbn1ActionPerformed
        cbxColor2.setEnabled(false);
        cbxColor3.setEnabled(false);
        cbxBot1.setEnabled(true);
        cbxBot2.setEnabled(false);
        cbxBot3.setEnabled(false);
        txt2.setEnabled(false);
        txt3.setEnabled(false);
    }//GEN-LAST:event_rbn1ActionPerformed

    private void rbn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbn2ActionPerformed
        cbxColor2.setEnabled(true);
        cbxColor3.setEnabled(false);
        cbxBot1.setEnabled(false);
        cbxBot2.setEnabled(true);
        cbxBot3.setEnabled(false);
        txt2.setEnabled(true);
        txt3.setEnabled(false);
    }//GEN-LAST:event_rbn2ActionPerformed

    private void rbn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbn3ActionPerformed
        cbxColor2.setEnabled(true);
        cbxColor3.setEnabled(true);
        cbxBot1.setEnabled(false);
        cbxBot2.setEnabled(false);
        cbxBot3.setEnabled(true);
        txt2.setEnabled(true);
        txt3.setEnabled(true);
    }//GEN-LAST:event_rbn3ActionPerformed

    private void btnControlliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnControlliActionPerformed
        opn1.showMessageDialog(
            this,
                "P: mette in pausa \n\n"
                + "Comandi per muoversi: \n"
                + "Giocatore 1: W-A-S-D \n"
                + "Giocatore 2: Frecce direzionali \n"
                + "Giocatore 3: I-J-K-L",
            "Controlli",
            JOptionPane.INFORMATION_MESSAGE
        );
    }//GEN-LAST:event_btnControlliActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnExitActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }
    
    private int ng;     // numero di giocatori
    private int nb;     // numero di bot
    private boolean a, c;
    private String colori [], nomi [];
    private Mappa1 f2;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btgGiocatori;
    private javax.swing.ButtonGroup btgMusica;
    private javax.swing.ButtonGroup btgTema;
    private javax.swing.JButton btnControlli;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnStart;
    private javax.swing.JComboBox<String> cbxBot1;
    private javax.swing.JComboBox<String> cbxBot2;
    private javax.swing.JComboBox<String> cbxBot3;
    private javax.swing.JComboBox<String> cbxColor1;
    private javax.swing.JComboBox<String> cbxColor2;
    private javax.swing.JComboBox<String> cbxColor3;
    private javax.swing.JLabel lblBot;
    private javax.swing.JLabel lblColore;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JOptionPane opn1;
    private javax.swing.JRadioButton rbn1;
    private javax.swing.JRadioButton rbn2;
    private javax.swing.JRadioButton rbn3;
    private javax.swing.JSeparator sep1;
    private javax.swing.JTextField txt1;
    private javax.swing.JTextField txt2;
    private javax.swing.JTextField txt3;
    // End of variables declaration//GEN-END:variables
}