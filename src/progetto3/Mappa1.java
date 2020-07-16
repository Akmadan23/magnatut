/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progetto3;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import static java.awt.event.KeyEvent.VK_A;
import static java.awt.event.KeyEvent.VK_D;
import static java.awt.event.KeyEvent.VK_DOWN;
import static java.awt.event.KeyEvent.VK_I;
import static java.awt.event.KeyEvent.VK_J;
import static java.awt.event.KeyEvent.VK_K;
import static java.awt.event.KeyEvent.VK_L;
import static java.awt.event.KeyEvent.VK_LEFT;
import static java.awt.event.KeyEvent.VK_P;
import static java.awt.event.KeyEvent.VK_RIGHT;
import static java.awt.event.KeyEvent.VK_S;
import static java.awt.event.KeyEvent.VK_UP;
import static java.awt.event.KeyEvent.VK_W;

/**
 * @author Azad Ahmadi
 * @author Samuele Aimi
 * @author Libaan Mohamed Hassan
 */

public final class Mappa1 extends javax.swing.JFrame {

    /**
     * Creates new form Mappa1
     * @param form1
     * @param ng
     * @param nb
     * @param nomi
     * @param colore
     */
    
    public Mappa1(
        progetto3.Menu form1, 
        int ng, 
        int nb, 
        String nomi [], 
        String colore []
    ) {
        initComponents();
        dimDot = 100;
        dimBot = nb;
        dimG = ng;
        f1 = form1;
        f1.setVisible(false);
        lblG1.setVisible(false);
        lblG2.setVisible(false);
        lblG3.setVisible(false);
        lblStatus.setVisible(false);
        
        // inizializzazione array
        x = new int [dimG];                                                     // coordinata X dei giocatori
        y = new int [dimG];                                                     // coordinata Y dei giocatori
        wh = new int [dimG];                                                    // ampiezza (width e height) dei giocatori
        xOld = new int [dimG];                                                  // ex coordinata X dei giocatori
        yOld = new int [dimG];                                                  // ex coordinata Y dei giocatori
        xBot = new int [dimBot];                                                // coordinata X dei bot
        yBot = new int [dimBot];                                                // coordinata Y dei bot
        xDot = new int [dimDot];                                                // coordinata X dei puntini
        yDot = new int [dimDot];                                                // coordinata Y dei puntini
        whDot = new int [dimDot];                                               // ampiezza dei puntini
        whBot = new int [dimBot];                                               // ampiezza dei bot
        xBotOld = new int [dimBot];                                             // ex coordinata X dei bot
        yBotOld = new int [dimBot];                                             // ex coordinata Y dei bot
        incMaxG = new int [dimG];                                               // contatore per l'incremento dell'ampiezza dei giocatori
        incMaxBot = new int [dimBot];                                           // contatore per l'incremento dell'ampiezza dei bot
        dir = new int [dimBot];                                                 // indice del puntino che il bot [ind] deve raggiungere
        newDir = new int [dimBot];                                              //    "    "    
        flagUp = new boolean [dimG];                                            // flag che segnalano  
        flagLeft = new boolean [dimG];                                          // se un determinato timer
        flagDown = new boolean [dimG];                                          // è in esecuzione
        flagRight = new boolean [dimG];
        flagInc = new boolean [dimG];
        flagBot = new boolean [dimBot];
        flagIncBot = new boolean [dimBot];
        flagGiocatore = new boolean [dimG];
        flagStatic = new boolean [dimG];
        flagBotTask = new boolean [dimBot];
        deadBot = new boolean [dimBot];
        thrBot = new ThreadBot [dimBot];
        timerStatic = new Timer [dimG];
        timerUp = new Timer [dimG];
        timerLeft = new Timer [dimG];
        timerDown = new Timer [dimG];
        timerRight = new Timer [dimG];
        timerBot = new Timer [dimBot];
        kl = new KeyListener [dimG];
        nome = new String [dimG];
        g = new Graphics [dimG];
        b = new Graphics [dimBot];
        s = new Graphics [dimBot];
        c = new Color [dimG];
        
        // inizializzazione oggetti
        d = new Disegno();
        klb = new KeyListenerBase();
        
        // inizializzazione variabili
        time = 10;                                                              // intervallo di tempo di esecuzione dei vari timer
        step = 1;                                                               // singolo passo per il movimento di giocatori e bot
        cont = 0;                                                               // contatore per i puntini generati automaticamente
        contWH = 0;                                                             // contatore per l'incremento dell'ampiezza dei giocatori
        contBotWH = 0;                                                          // contatore per l'incremento dell'ampiezza dei giocatori
        pausa = false;                                                          // flag che indica se si è in pausa o meno
        xy = false;                                                             // flag che indica la coordinata di movimento dei bot
        maxX = pnlCampo.getWidth();                                             // ampiezza massima del campo di gioco (X)
        maxY = pnlCampo.getHeight();                                            // ampiezza massima del campo di gioco (Y)
        numTot = dimG + dimBot;                                                 // numero totale di giocatori attivi tra giocatori reali e bot
        numBot = dimBot;                                                        // numero totale di giocatori reali attivi
        numG = dimG;                                                            // numero totale di bot attivi
        timeDot = 6000 / numTot;                                                // intervallo di tempo di esecuzione del timer di generazione di puntini
        
        // configurazione JFrame e JPanel
        frame = this;
        frame.setTitle("Magna Tut (" + numTot + " giocatori)");
        pnlCampo.addKeyListener(klb);
        pnlCampo.setFocusable(true);
        pnlCampo.setVisible(true);
        pnlCampo.add(d);
        
        for (int i = 0; i < dimG; i++) {                                        // per ogni giocatore
            if (nomi [i].equals("")) nome [i] = "Giocatore " + (i + 1);         // se non è stato scelto alcun nome assegna la stringa "Giocatore" + l'indice dell'array
            else nome [i] = nomi [i];                                           // altrimenti assegna alla variabile nome il nome selezionato nel menu iniziale
            
            if (g [i] == null) g [i] = getGraphics();                           // inizializza l'oggetto Graphics del giocatore
            flagUp [i] = false;                                                 // inizializza tutti i flag del giocatore a false
            flagLeft [i] = false;
            flagDown [i] = false;
            flagRight [i] = false;
            flagStatic [i] = false;
            flagGiocatore [i] = true;
            flagInc [i] = false;
            incMaxG [i] = 0;
            wh [i] = 20;
            
            kl [i] = new KeyListener(i);                                        // inizializza il keylistener del giocatore
            pnlCampo.addKeyListener(kl [i]);                                    // e lo aggiunge al pammello
            
            switch (colore [i]) {
                case "Nero":
                    c [i] = Color.black;
                    break;
                    
                case "Rosso":
                    c [i] = Color.red;
                    break;
                    
                case "Verde":
                    c [i] = Color.green;
                    break;
                    
                case "Giallo":
                    c [i] = Color.yellow;
                    break;
                    
                case "Blu":
                    c [i] = Color.blue;
                    break;
                    
                default:
                    break;
            }
        }
        
        switch (dimG) {                                                         // in base al numero di giocatori determina le coordinate di spawn
            case 1:
                x [0] = 50;
                y [0] = 50;
                
                if (dimBot > 0) {                                               // in base al numero di bot determina le coordinate di spawn
                    xBot [0] = maxX - 50;
                    yBot [0] = maxY - 50;
                }
                
                if (dimBot > 1) {
                    xBot [1] = maxX - 50;
                    yBot [1] = 50;
                }
                
                if (dimBot > 2) {
                    xBot [2] = 50;
                    yBot [2] = maxY - 50;
                }
                
                lblG1.setText(nome [0]);
                lblG1.setVisible(true);
                break;
                
            case 2:
                x [0] = 50;
                y [0] = 50;
                x [1] = maxX - 50;
                y [1] = maxY - 50;
                
                if (dimBot > 0) {                                               // in base al numero di bot determina le coordinate di spawn
                    xBot [0] = maxX - 50;
                    yBot [0] = 50;
                }
                
                if (dimBot > 1) {
                    xBot [1] = 50;
                    yBot [1] = maxY - 50;
                }
                
                if (dimBot > 2) {
                    xBot [2] = maxX / 2;
                    yBot [2] = maxY / 2;
                }
                
                lblG1.setText(nome [0]);
                lblG1.setVisible(true);
                
                lblG2.setText(nome [1]);
                lblG2.setVisible(true);
                break;
                
            case 3:
                x [0] = 50;
                y [0] = 50;
                x [1] = maxX - 50;
                y [1] = maxY - 50;
                x [2] = maxX - 50;
                y [2] = 50;
                
                if (dimBot > 0) {                                               // in base al numero di bot determina le coordinate di spawn
                    xBot [0] = 50;
                    yBot [0] = maxY - 50;
                }
                
                if (dimBot > 1) {
                    xBot [1] = maxX / 2;
                    yBot [1] = maxY / 2;
                }
                
                if (dimBot > 2) {
                    xBot [1] = 50;
                    yBot [1] = maxY / 2;
                    xBot [2] = maxX / 2;
                    yBot [2] = maxY - 50;
                }
                
                lblG1.setText(nome [0]);
                lblG1.setVisible(true);
                
                lblG2.setText(nome [1]);
                lblG2.setVisible(true);
                
                lblG3.setText(nome [2]);
                lblG3.setVisible(true);
                break;
                
            default:
                break;
        }
        
        for (int i = 0; i < dimBot; i++) {                                      // per ogni bot attivo
            if (b [i] == null) b [i] = getGraphics();                           // inizializza il relativo oggetto Graphics
            if (s [i] == null) s [i] = getGraphics();                           // inizializza la stringa identificativa
            flagBot [i] = true;                                                 // attiva il flag del bot
            flagBotTask [i] = false;                                            // permette l'esecuzione del TimerBot
            flagIncBot [i] = false;                                             // indica che all'inizi non bisogna incrementare l'ampiezza del bot
            deadBot [i] = false;                                                // questa variabile diventa vera solo quando il bot è definitivamente eliminato
            incMaxBot [i] = 0;                                                  // inizializza a 0 il contatore per l'incremento dell'ampiezza del bot
            whBot [i] = 20;                                                     // inizializza l'ampiezza del bot
            thrBot [i] = new ThreadBot(i);                                      // inizializza il thread del bot in questione
            thrBot [i].start();                                                 // e lo fa partire
        }
        
        dot = getGraphics();
        timerDotGenerator = new Timer();
        timerDotGenerator.schedule(new TaskDotGenerator(), 0, timeDot);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        opn1 = new javax.swing.JOptionPane();
        lblSide1 = new javax.swing.JLabel();
        lblSide2 = new javax.swing.JLabel();
        lblSide3 = new javax.swing.JLabel();
        pnlCampo = new javax.swing.JPanel();
        lblG1 = new javax.swing.JLabel();
        lblG2 = new javax.swing.JLabel();
        lblG3 = new javax.swing.JLabel();
        lblStatus = new javax.swing.JLabel();

        lblSide1.setText("jLabel2");

        lblSide2.setText("jLabel3");

        lblSide3.setText("jLabel4");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblG1.setText("Giocatore 1");

        lblG2.setText("Giocatore 2");

        lblG3.setText("Giocatore 3");

        lblStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblStatus.setText("jLabel1");

        javax.swing.GroupLayout pnlCampoLayout = new javax.swing.GroupLayout(pnlCampo);
        pnlCampo.setLayout(pnlCampoLayout);
        pnlCampoLayout.setHorizontalGroup(
            pnlCampoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCampoLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(lblG1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 938, Short.MAX_VALUE)
                .addGroup(pnlCampoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblG3)
                    .addComponent(lblG2))
                .addGap(50, 50, 50))
            .addGroup(pnlCampoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlCampoLayout.setVerticalGroup(
            pnlCampoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCampoLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(pnlCampoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblG1)
                    .addComponent(lblG3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 590, Short.MAX_VALUE)
                .addComponent(lblG2)
                .addGap(29, 29, 29)
                .addComponent(lblStatus)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlCampo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlCampo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    
    private class Disegno extends JPanel {
        public void paintGiocatore(int i) {
            super.invalidate();                                                 // queste tre istruzioni si occupano di ricaricare
            super.validate();                                                   // il jpanel per evitare lag
            super.repaint();
            
            g [i].clearRect(xOld [i], yOld [i], wh [i], wh [i]);                // cancella il rettangolo precedente
            g [i].fillRect(x [i], y [i], wh [i], wh [i]);                       // disegna un nuovo rettangolo nelle coordinate aggiornate
            g [i].setColor(c [i]);                                              // colora il rettangolo in base al colore del giocatore
        }
        
        public void paintBot(int i) {
            super.invalidate();
            super.validate();
            super.repaint();
            
            b [i].clearRect(xBotOld [i], yBotOld [i], whBot [i], whBot [i]);    // cancella il rettangolo precedente
            b [i].fillRect(xBot [i], yBot [i], whBot [i], whBot [i]);           // disegna un nuovo rettangolo nelle coordinate aggiornate
            b [i].setColor(Color.white);                                        // colora il rettangolo in base al colore del giocatore
            s [i].drawString("" + (i + 1), xBot [i] + 2, (yBot [i] + whBot [i] - 2)); // disegna la stringa identificativa del bot
            s [i].setColor(Color.black);                                        // colora la stringa identificativa del bot
        }
    
        public void paintDot(int xr, int yr, int whr) {                         // riceve come parametri coordinate e ampiezza del puntino generato
            dot.fillOval(xr, yr, whr, whr);                                     // disegna il puntino nelle coordinate ricevute
            dot.setColor(randomColor());                                        // colora il puntino con un colore estratto casualmente
        }
    }
    
    private class ThreadBot extends Thread {
        private final int ind;
        
        ThreadBot(int ind) {
            this.ind = ind;
        }
        
        @Override
        public void run() {
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(Mappa1.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            while (flagBot [ind]) {                                             // finchè il bot è attivo
                while (pausa) {}
                dir [ind] = radar(ind);                                         // esegue una "scansione" attraverso la funzione radar()
                xy = !xy;                                                       // inverte la coordinata di spostamento
                
                flagBotTask [ind] = true;                                   // attiva il flag relativo al timer di spostamento del bot
                timerBot [ind] = new Timer();                               // inizializza il timer di spostamento
                timerBot [ind].schedule(new TaskBot(ind, dir [ind], xy), time, time); // e lo fa partire

                while (flagBotTask [ind]) {                                     // mentre è in esecuzione il timer
                    /*if (pausa) {
                    while (pausa) {}
                    
                    try {
                    Thread.sleep(10);                                       // questa attesa evita che si blocchi il bot
                    } catch (InterruptedException ex) {
                    Logger.getLogger(Mappa1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    timerBot [ind] = new Timer();
                    timerBot [ind].schedule(new TaskBot(ind, dir [ind], xy), time, time);
                    }*/
                    
                    try {
                        Thread.sleep(10);                                       // questa attesa evita che si blocchi il bot
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Mappa1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    newDir [ind] = radar(ind);                                  // esegue una nuova scansione

                    if (newDir [ind] != dir [ind]) {                            // se trova un altro puntino più vincino di quello verso il quale è diretto
                        dir [ind] = newDir [ind];                               // assegna la nuova direzione da seguire
                        timerBot [ind].cancel();                                // interrompe il timer
                        flagBotTask [ind] = false;                              // permette l'uscita dal ciclo while
                        break;
                    }

                    if (!flagBot [ind]) {                                       // se il bot viene mangiato mentre si sposta
                        timerBot [ind].cancel();                                // interrompe il timer
                        flagBotTask [ind] = false;                              // permette l'uscita dal ciclo while
                        break;
                    }
                }
            }
        }
    }
    
    private class TaskDotGenerator extends TimerTask {
        // questo timer si occupa della generazione casuale di puntini
        // sul campo di gioco
        
        @Override
        public void run() {
            temp = cont;
            
            for (int i = 1; i < cont; i++) {                                    // controlla se ci sono elementi inuzilizzati (quindi già "mangiati") nell'array
                if (xDot [i] == maxX && yDot [i] == maxY) {                     // se ne trova uno
                    temp = i;                                                   // assegna alla variabile "temp" l'indice dell'elemento dell'array inutilizzato
                    break;                                                      // e interrompe il ciclo di controllo
                }
            }
            
            if (temp == cont)                                                   // se non ci sono posti inutilizzati nell'array quindi "temp" non ha cambiato valore
                cont++;                                                         // allora incrementa il contatore
            
            do {
                dotConfirm = true;
                xRand = (int) (Math.random() * (maxX - 50) + 30);               // assegna alla coordinata x del puntino un numero casuale tra 50 e maxX - 30
                yRand = (int) (Math.random() * (maxY - 50) + 30);               // assegna alla coordinata y del puntino un numero casuale tra 50 e maxY - 30
                whRand = (int) (Math.random() * (15 - 5) + 5);                  // assegna alla dimensione del puntino un numero casuale tra 5 e 15
                
                for (int i = 0; i < dimG; i++)                                  // controlla che il punto appena generato non si trovi in sovrapposizione ad un giocatore
                    if (xRand > x [i] && (xRand + whRand) < (x [i] + wh [i])) 
                        dotConfirm = false;
                
                
                for (int i = 0; i < dimBot; i++)                                // controlla che il punto appena generato non si trovi in sovrapposizione ad un giocatore
                    if (xRand > xBot [i] && (xRand + whRand) < (xBot [i] + whBot [i])) 
                        dotConfirm = false;
                
            } while (!dotConfirm);
            
            xDot [temp] = xRand;                                                // conferma le assegnazioni precenenti
            yDot [temp] = yRand;
            whDot [temp] = whRand;
            d.paintDot(xDot [temp], yDot [temp], whDot [temp]);                 // chiama la funzione per disegnare il puntino appena generato
        }
    }
    
    private class Task extends TimerTask {
        // questo timer si occupa di spostare il giocatore di indice [ind]
        // in base alla direzione relativa al tasto che è stato premuto
        
        private final int ind;      // indice dell'array del giocatore
        private final String dir;   // direzione
        
        Task(int ind, String dir) {
            this.ind = ind;                                                     // assegna alle variabili locali ind e dir appena dichiarate
            this.dir = dir;                                                     // i valori ricevuti come parametri
        }
        
        @Override
        public void run() {
            xOld [ind] = x [ind];                                               // assegna alle coordinate "old" le coordinate del giocatore
            yOld [ind] = y [ind];                                               // prima che queste vengano aggiornate
            
            switch (dir) {
                case "Up":                                                      // se la direzione passata come parameto è "Up"
                    y [ind] -= step;                                            // sposta la coordinata y dell'oggetto in alto di un pixel
                    break;
                    
                case "Left":                                                    // se la direzione passata come parameto è "Left"
                    x [ind] -= step;                                            // sposta la coordinata x dell'oggetto a sinistra di un pixel
                    break;
                    
                case "Down":                                                    // se la direzione passata come parameto è "Down"
                    y [ind] += step;                                            // sposta la coordinata y dell'oggetto in basso di un pixel
                    break;
                    
                case "Right":                                                   // se la direzione passata come parameto è "Right"
                    x [ind] += step;                                            // sposta la coordinata x dell'oggetto a destra di un pixel
                    break;
                    
                default:
                    break;
            }
            
            refresh(ind);
        }
    }
    
    private class TaskStatic extends TimerTask {
        // questo timer si occupa di aggiornare la posizione dei giocatori
        // anche quando sono fermi
        
        private final int ind;
        
        TaskStatic(int ind) {
            this.ind = ind;
        }
        
        @Override
        public void run() {
            if (flagUp [ind] || flagLeft [ind] || flagDown [ind] || flagRight [ind]) {
                flagStatic [ind] = false;
                this.cancel();
            }
            
            refresh(ind);
        }
    }
    
    private class TaskBot extends TimerTask {
        // questo timer si occupa di spostare il bot di indice [ind]
        // sull'asse X in base alla coordinata ricevuta come parametro
        
        private final int ind;      // indice dell'array del bot
        private final int dir;      // coordinata di destinazione
        private final boolean xy;   // indica se la coordinata è X o Y: true = x, false = y
        
        TaskBot(int ind, int dir, boolean xy) {
            this.ind = ind;                                                     // assegna alle variabili locali ind, dest e xy appena dichiarate
            this.dir = dir;                                                     // i valori ricevuti come parametri
            this.xy = xy;
        }
        
        @Override
        public void run() {
            xBotOld [ind] = xBot [ind];                                         // assegna alle coordinate "old" le coordinate del bot
            yBotOld [ind] = yBot [ind];                                         // prima che queste vengano aggiornate
            
            /* if (pausa) {
            this.cancel();
            }*/
            
            if (xy) {
                if (xBot [ind] < xDot [dir] && (xBot [ind] + whBot [ind]) > (xDot [dir] + whDot [dir])) { // se il bot si trova in corrispondenza della coordinata X di destinazione
                    flagBotTask [ind] = false;                                  // segnala che il Timer si è fermato
                    refreshBot(ind);                                            // ricalcola la posizione del bot
                    this.cancel();                                              // infine termina il Timer in esecuizione
                }

                // se invece il bot non si trova in corrispondenza della coordinata X di destinazione
                else if (xBot [ind] >= xDot [dir]) xBot [ind] -= step;          // si sposta nella direzione corretta
                else if ((xBot [ind] + whBot [ind]) <= (xDot [dir] + whDot [dir])) xBot [ind] += step;
            }
            
            else {
                if (yBot [ind] < yDot [dir] && (yBot [ind] + whBot [ind]) > (yDot [dir] + whDot [dir])) { // se il bot si trova in corrispondenza della coordinata Y di destinazione
                    flagBotTask [ind] = false;                                  // segnala che il Timer si è fermato
                    refreshBot(ind);                                            // ricalcola la posizione del bot
                    this.cancel();                                              // infine termina il Timer in esecuizione
                }

                // se invece il bot non si trova in corrispondenza della coordinata Y di destinazione
                else if (yBot [ind] >= yDot [dir]) yBot [ind] -= step;          // si sposta nella direzione corretta
                else if ((yBot [ind] + whBot [ind]) <= (yDot [dir] + whDot [dir])) yBot [ind] += step;
            }
            
            refreshBot(ind);
        }
    }
    
    private class KeyListenerBase extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent event) {
            switch (event.getKeyCode()) {
                case VK_P:
                    pausa = !pausa;                                             // inverte lo stato del flag "pausa"
                    
                    if (pausa) {                                                // se si è in pausa
                        for (int i = 0; i < dimG; i++) {                        // per ogni giocatore attivo
                            pnlCampo.removeKeyListener(kl [i]);                 // disattiva il relativo keylistener
                            if (flagUp [i]) timerUp [i].cancel();               // disattiva tutti i timer di movimento
                            if (flagLeft [i]) timerLeft [i].cancel();           // qualora ce ne fosse uno in esecuzione
                            if (flagDown [i]) timerDown [i].cancel();
                            if (flagRight [i]) timerRight [i].cancel();
                            if (flagStatic [i]) timerStatic [i].cancel();
                        }
                        
                        for (int i = 0; i < dimBot; i++) {
                            if (flagBotTask [i]) timerBot [i].cancel();
                            flagBotTask [i] = false;
                            flagBot [i] = false;
                        }
                        
                        timerDotGenerator.cancel();                             // disattiva la generazione dei puntini
                        lblStatus.setText("Pausa: premi nuovamente P per riprendere la partita.");
                        lblStatus.setVisible(true);
                    }
                    
                    else {                                                      // se si sta riprendendo il gioco
                        for (int i = 0; i < dimG; i++) {                        // riattiva i keylistener di tutti i giocatori
                            pnlCampo.addKeyListener(kl [i]);
                            timerStatic [i] = new Timer();
                            timerStatic [i].schedule(new TaskStatic(i), 0, time);
                        }
                        
                        for (int i = 0; i < dimBot; i++) {
                            if (!deadBot [i]) {
                                flagBot [i] = true;
                                thrBot [i] = new ThreadBot(i);
                                thrBot [i].start();
                            }
                        }
                        
                        lblStatus.setVisible(false);
                        timerDotGenerator = new Timer();                        // riattiva la generazione dei puntini
                        timerDotGenerator.schedule(new TaskDotGenerator(), timeDot, timeDot);
                    }
                    
                    break;
                    
                default:
                    break;
            }
        }
    }
    
    private class KeyListener extends KeyAdapter {
        private final int ind;
        private int key, kUp, kLeft, kDown, kRight;
        
        KeyListener(int ind) {
            this.ind = ind;
            
            switch (ind) {
                case 0:
                    kUp = VK_W;
                    kLeft = VK_A;
                    kDown = VK_S;
                    kRight = VK_D;
                    break;
                    
                case 1:
                    kUp = VK_UP;
                    kLeft = VK_LEFT;
                    kDown = VK_DOWN;
                    kRight = VK_RIGHT;
                    break;
                    
                case 2:
                    kUp = VK_I;
                    kLeft = VK_J;
                    kDown = VK_K;
                    kRight = VK_L;
                    break;
            }
        }
        
        @Override
        public void keyPressed(KeyEvent event) {
            key = event.getKeyCode();
            
            if (key == kUp) {
                if (flagLeft [ind]) timerLeft [ind].cancel();
                if (flagDown [ind]) timerDown [ind].cancel();
                if (flagRight [ind]) timerRight [ind].cancel();

                if (!flagUp [ind]) {
                    flagUp [ind] = true;
                    timerUp [ind] = new Timer();
                    timerUp [ind].schedule(new Task(ind, "Up"), time, time);
                }
            }
            
            else if (key == kLeft) {
                if (flagUp [ind]) timerUp [ind].cancel();
                if (flagDown [ind]) timerDown [ind].cancel();
                if (flagRight [ind]) timerRight [ind].cancel();

                if (!flagLeft [ind]) {
                    flagLeft [ind] = true;
                    timerLeft [ind] = new Timer();
                    timerLeft [ind].schedule(new Task(ind, "Left"), time, time);
                }
            }
            
            else if (key == kDown) {
                if (flagUp [ind]) timerUp [ind].cancel();
                if (flagLeft [ind]) timerLeft [ind].cancel();
                if (flagRight [ind]) timerRight [ind].cancel();

                if (!flagDown [ind]) {
                    flagDown [ind] = true;
                    timerDown [ind] = new Timer();
                    timerDown [ind].schedule(new Task(ind, "Down"), time, time);
                }
            }
            
            else if (key == kRight) {
                if (flagUp [ind]) timerUp [ind].cancel();
                if (flagLeft [ind]) timerLeft [ind].cancel();
                if (flagDown [ind]) timerDown [ind].cancel();

                if (!flagRight [ind]) {
                    flagRight [ind] = true;
                    timerRight [ind] = new Timer();
                    timerRight [ind].schedule(new Task(ind, "Right"), time, time);
                }
            }
        }
        
        @Override
        public void keyReleased(KeyEvent event) {
            key = event.getKeyCode();
            
            if (key == kUp) {
                timerUp [ind].cancel();
                flagUp [ind] = false;
                flagStatic [ind] = true;

                timerStatic [ind] = new Timer();
                timerStatic [ind].schedule(new TaskStatic(ind), 0, time);
            }
            
            else if (key == kLeft) {
                timerLeft [ind].cancel();
                flagLeft [ind] = false;
                flagStatic [ind] = true;

                timerStatic [ind] = new Timer();
                timerStatic [ind].schedule(new TaskStatic(ind), 0, time);
            }
            
            else if (key == kDown) {
                timerDown [ind].cancel();
                flagDown [ind] = false;
                flagStatic [ind] = true;

                timerStatic [ind] = new Timer();
                timerStatic [ind].schedule(new TaskStatic(ind), 0, time);
            }
            
            else if (key == kRight) {
                timerRight [ind].cancel();
                flagRight [ind] = false;
                flagStatic [ind] = true;

                timerStatic [ind] = new Timer();
                timerStatic [ind].schedule(new TaskStatic(ind), 0, time);
            }
        }
    }
    
    private void refresh(int ind) {
        if ((numTot == 1 && incMaxG [ind] == 0) || wh [ind] >= maxY) {          // se è l'ultimo giocatore rimasto in partita
            if (flagStatic [ind]) timerStatic [ind].cancel();
            timerDotGenerator.cancel();
            
            for (int i = 0; i < dimG; i++) {
                if (flagUp [i]) timerUp [i].cancel();
                if (flagLeft [i]) timerLeft [i].cancel();
                if (flagDown [i]) timerDown [i].cancel();
                if (flagRight [i]) timerRight [i].cancel();
                pnlCampo.removeKeyListener(kl [i]);
            }
            
            JOptionPane.showMessageDialog(
                this, 
                nome [ind] + " vince!", 
                "Fine della partita", 
                JOptionPane.INFORMATION_MESSAGE
            );
            
            System.exit(0);
        }
        
        if (ind == 0 && lblG1.isVisible()) lblG1.setVisible(false);
        else if (ind == 1 && lblG2.isVisible()) lblG2.setVisible(false);
        else if (ind == 2 && lblG3.isVisible()) lblG3.setVisible(false);
        
        // serie di controlli che impediscono al bot di uscire dal campo di gioco
        
        if (x [ind] < 0) 
            x [ind] = 0;
        
        else if (x [ind] > maxX - wh [ind]) 
            x [ind] = maxX - wh [ind];
        
        else if (y [ind] < 30) 
            y [ind] = 30;
        
        else if (y [ind] > maxY - wh [ind]) 
            y [ind] = maxY - wh [ind];
        
        // CONTROLLO INGLOBAMENTO PUNTINI
        
        for (int i = 0; i < cont; i++) {
            if (xDot [i] >= x [ind] && (xDot [i] + whDot [i]) <= (x [ind] + wh [ind])) {
                if (yDot [i] >= y [ind] && (yDot [i] + whDot [i]) <= (y [ind] + wh [ind])) {
                    // se il quadratino del giocatore in questione si sovrappone completamente ad un puntino generato casualmente:
                    flagInc [ind] = true;
                    incMaxG [ind] += 5;
                    xDot [i] = maxX;                                            // le coordinate del puntino vengono spostate fuori dal campo di gioco
                    yDot [i] = maxY;                                            // per non interferire nuovamente con i giocatori
                }
            }
        }
        
        // CONTROLLO INGLOBAMENTO BOT
            
        for (int i = 0; i < dimBot; i++) {
            if (flagBot [i]) {
                if (xBot [i] >= x [ind] && (xBot [i] + whBot [i]) <= (x [ind] + wh [ind])) {
                    if (yBot [i] >= y [ind] && (yBot [i] + whBot [i]) <= (y [ind] + wh [ind])) {
                        // se il quadratino del giocatore in questione si sovrappone completamente a quello di un bot:
                        if (flagBotTask [i]) timerBot [i].cancel();               // si fermano tutti i timer legati ad esso
                        if (flagBotTask [i]) timerBot [i].cancel();               // qualora ce ne fosse uno attivo
                        
                        numTot--;                                               // decrementa il numero di giocatori e bot attivi
                        flagInc [ind] = true;                                   // attiva il flag per far eseguire la funzione di incremento graduale dell'ampiezza
                        incMaxG [ind] += whBot [i];                             // alle dimensioni del giocatore in questione vengono sommate quelle del bot vittima
                        flagBot [i] = false;                                    // il bot in questione viene disattivato
                        deadBot [i] = true;                                     // e definitivamente eliminato
                        lblStatus.setText("Bot " + (i + 1) + " è stato eliminato da " + nome [ind]);
                        lblStatus.setVisible(true);
                    }
                }
            }
        }
        
        // CONTROLLO INGLOBAMENTO ALTRI GIOCATORI
        
        if (dimG == 2) {                                                        // se stanno giocando due giocatori
            if (ind == 0) {                                                     // se il giocatore in questione ha indice [0]
                if (flagGiocatore [1]) {                                        // e se l'altro giocatore è ancora attivo
                    if (x [ind] < x [1] && (x [ind] + wh [ind]) > (x [1] + wh [1])) {
                        if (y [ind] < y [1] && (y [ind] + wh [ind]) > (y [1] + wh [1])) {
                            // se il quadratino del giocatore in questione si sovrappone completamente a quello del giocatore con indice 1
                            if (flagStatic [1]) timerStatic [1].cancel();       // si fermano tutti i timer
                            if (flagUp [1]) timerUp [1].cancel();               // legati alla "vittima"
                            if (flagLeft [1]) timerLeft [1].cancel();           // nel caso ce ne fosse qualcuno
                            if (flagDown [1]) timerDown [1].cancel();           // in esecuzione
                            if (flagRight [1]) timerRight [1].cancel();
                            
                            numG--;
                            numTot--;                                           // decrementa il numero di giocatori e bot attivi
                            flagInc [ind] = true;                               // attiva il flag per far eseguire la funzione di incremento graduale dell'ampiezza
                            incMaxG [ind] += wh [1];                            // incrementa la somma di pixel da incrementare relativamente al giocatore corrente
                            flagGiocatore [1] = false;                          // la vittima viene disattivata: esce quindi definitivamente dal gioco
                            pnlCampo.removeKeyListener(kl [1]);                 // viene disattivato il relativo keylistener per impedire che continui a muoversi
                            lblStatus.setText(nome [1] + " è stato eliminato da " + nome [ind]);
                            lblStatus.setVisible(true);
                        }
                    }
                }
            }
            
            else if (ind == 1) {                                                // vedi sopra
                if (flagGiocatore [0]) {
                    if (x [ind] < x [0] && (x [ind] + wh [ind]) > (x [0] + wh [0])) {
                        if (y [ind] < y [0] && (y [ind] + wh [ind]) > (y [0] + wh [0])) {
                            if (flagStatic [0]) timerStatic [0].cancel();
                            if (flagUp [0]) timerUp [0].cancel();
                            if (flagLeft [0]) timerLeft [0].cancel();
                            if (flagDown [0]) timerDown [0].cancel();
                            if (flagRight [0]) timerRight [0].cancel();
                            
                            numG--;
                            numTot--;
                            flagInc [ind] = true;
                            incMaxG [ind] += wh [0];
                            flagGiocatore [0] = false;
                            pnlCampo.removeKeyListener(kl [0]);
                            lblStatus.setText(nome [0] + " è stato eliminato da " + nome [ind]);
                            lblStatus.setVisible(true);
                        }
                    }
                }
            }
        }
        
        else if (dimG == 3) {                                                   // se stanno giocando tre giocatori
            if (ind == 0) {                                                     // vedi sopra
                if (flagGiocatore [1]) {
                    if (x [ind] < x [1] && (x [ind] + wh [ind]) > (x [1] + wh [1])) {
                        if (y [ind] < y [1] && (y [ind] + wh [ind]) > (y [1] + wh [1])) {
                            if (flagStatic [1]) timerStatic [1].cancel();
                            if (flagUp [1]) timerUp [1].cancel();
                            if (flagLeft [1]) timerLeft [1].cancel();
                            if (flagDown [1]) timerDown [1].cancel();
                            if (flagRight [1]) timerRight [1].cancel();
                            
                            numG--;
                            numTot--;
                            flagInc [ind] = true;
                            incMaxG [ind] += wh [1];
                            flagGiocatore [1] = false;
                            pnlCampo.removeKeyListener(kl [1]);
                            lblStatus.setText(nome [1] + " è stato eliminato da " + nome [ind]);
                            lblStatus.setVisible(true);
                        }
                    }
                }
                
                if (flagGiocatore [2]) {
                    if (x [ind] < x [2] && (x [ind] + wh [ind]) > (x [2] + wh [2])) {
                        if (y [ind] < y [2] && (y [ind] + wh [ind]) > (y [2] + wh [2])) {
                            if (flagStatic [2]) timerStatic [2].cancel();
                            if (flagUp [2]) timerUp [2].cancel();
                            if (flagLeft [2]) timerLeft [2].cancel();
                            if (flagDown [2]) timerDown [2].cancel();
                            if (flagRight [2]) timerRight [2].cancel();
                            
                            numG--;
                            numTot--;
                            flagInc [ind] = true;
                            incMaxG [ind] += wh [2];
                            flagGiocatore [2] = false;
                            pnlCampo.removeKeyListener(kl [2]);
                            lblStatus.setText(nome [2] + " è stato eliminato da " + nome [ind]);
                            lblStatus.setVisible(true);
                        }
                    }
                }
            }
            
            else if (ind == 1) {                                                // vedi sopra
                if (flagGiocatore [0]) {
                    if (x [ind] < x [0] && (x [ind] + wh [ind]) > (x [0] + wh [0])) {
                        if (y [ind] < y [0] && (y [ind] + wh [ind]) > (y [0] + wh [0])) {
                            if (flagStatic [0]) timerStatic [0].cancel();
                            if (flagUp [0]) timerUp [0].cancel();
                            if (flagLeft [0]) timerLeft [0].cancel();
                            if (flagDown [0]) timerDown [0].cancel();
                            if (flagRight [0]) timerRight [0].cancel();
                            
                            numG--;
                            numTot--;
                            flagInc [ind] = true;
                            incMaxG [ind] += wh [0];
                            flagGiocatore [0] = false;
                            pnlCampo.removeKeyListener(kl [0]);
                            lblStatus.setText(nome [0] + " è stato eliminato da " + nome [ind]);
                            lblStatus.setVisible(true);
                        }
                    }
                }
                
                if (flagGiocatore [2]) {
                    if (x [ind] < x [2] && (x [ind] + wh [ind]) > (x [2] + wh [2])) {
                        if (y [ind] < y [2] && (y [ind] + wh [ind]) > (y [2] + wh [2])) {
                            if (flagStatic [2]) timerStatic [2].cancel();
                            if (flagUp [2]) timerUp [2].cancel();
                            if (flagLeft [2]) timerLeft [2].cancel();
                            if (flagDown [2]) timerDown [2].cancel();
                            if (flagRight [2]) timerRight [2].cancel();
                            
                            numG--;
                            numTot--;
                            flagInc [ind] = true;
                            incMaxG [ind] += wh [2];
                            flagGiocatore [2] = false;
                            pnlCampo.removeKeyListener(kl [2]);
                            lblStatus.setText(nome [2] + " è stato eliminato da " + nome [ind]);
                            lblStatus.setVisible(true);
                        }
                    }
                }
            }
            
            else if (ind == 2) {                                                // vedi sopra
                if (flagGiocatore [0]) {
                    if (x [ind] < x [0] && (x [ind] + wh [ind]) > (x [0] + wh [0])) {
                        if (y [ind] < y [0] && (y [ind] + wh [ind]) > (y [0] + wh [0])) {
                            if (flagStatic [0]) timerStatic [0].cancel();
                            if (flagUp [0]) timerUp [0].cancel();
                            if (flagLeft [0]) timerLeft [0].cancel();
                            if (flagDown [0]) timerDown [0].cancel();
                            if (flagRight [0]) timerRight [0].cancel();
                            
                            numG--;
                            numTot--;
                            flagInc [ind] = true;
                            incMaxG [ind] += wh [0];
                            flagGiocatore [0] = false;
                            pnlCampo.removeKeyListener(kl [0]);
                            lblStatus.setText(nome [0] + " è stato eliminato da " + nome [ind]);
                            lblStatus.setVisible(true);
                        }
                    }
                }
                
                if (flagGiocatore [1]) {
                    if (x [ind] < x [1] && (x [ind] + wh [ind]) > (x [1] + wh [1])) {
                        if (y [ind] < y [1] && (y [ind] + wh [ind]) > (y [1] + wh [1])) {
                            if (flagStatic [1]) timerStatic [1].cancel();
                            if (flagUp [1]) timerUp [1].cancel();
                            if (flagLeft [1]) timerLeft [1].cancel();
                            if (flagDown [1]) timerDown [1].cancel();
                            if (flagRight [1]) timerRight [1].cancel();
                            
                            numG--;
                            numTot--;
                            flagInc [ind] = true;
                            incMaxG [ind] += wh [1];
                            flagGiocatore [1] = false;
                            pnlCampo.removeKeyListener(kl [1]);
                            lblStatus.setText(nome [1] + " è stato eliminato da " + nome [ind]);
                            lblStatus.setVisible(true);
                        }
                    }
                }
            }
        }
        
        if (flagInc [ind]) incrementWH(ind);
        d.paintGiocatore(ind);
    }
    
    private void refreshBot(int ind) {
        if ((numTot == 1 && incMaxBot [ind] == 0) || whBot [ind] >= maxY) {     // se il bot con indice ind è l'unico rimasto in gioco
            flagBot [ind] = false;                                              // disattiva il relativo flag
            timerDotGenerator.cancel();                                         // interrompe la generazione di puntini
            
            for (int i = 0; i < dimG; i++) {
                if (flagUp [i]) timerUp [i].cancel();
                if (flagLeft [i]) timerLeft [i].cancel();
                if (flagDown [i]) timerDown [i].cancel();
                if (flagRight [i]) timerRight [i].cancel();
                if (flagStatic [i]) timerStatic [i].cancel();
                pnlCampo.removeKeyListener(kl [i]);
            }
            
            JOptionPane.showMessageDialog(
                this, 
                "Bot " + (ind + 1) + " vince!",                                 // indica tramite un option pane il vincitore
                "Fine della partita", 
                JOptionPane.INFORMATION_MESSAGE
            );
            
            System.exit(0);                                                     // una volta cliccato su "ok" termina il programma
        }
        
        if (numG == 0 && incMaxBot [ind] == 0) {
            timerDotGenerator.cancel();
                        
            for (int i = 0; i < dimBot; i++) {
                flagBot [i] = false;
            }
            
            if (dimG == 1) {
                JOptionPane.showMessageDialog(
                    this, 
                    "Hai perso!",                                               // indica tramite un option pane il vincitore
                    "Fine della partita", 
                    JOptionPane.INFORMATION_MESSAGE
                );
            }
            
            else {
                JOptionPane.showMessageDialog(
                    this, 
                    "Avete perso!",                                             // indica tramite un option pane il vincitore
                    "Fine della partita", 
                    JOptionPane.INFORMATION_MESSAGE
                );
            }
            
            System.exit(0);   
        }
        
        // serie di controlli che impediscono al bot di uscire dal campo di gioco
        if (xBot [ind] < 0) 
            xBot [ind] = 0;
        
        else if (xBot [ind] > maxX - whBot [ind]) 
            xBot [ind] = maxX - whBot [ind];
        
        else if (yBot [ind] < 30) 
            yBot [ind] = 30;
        
        else if (yBot [ind] > maxY - whBot [ind]) 
            yBot [ind] = maxY - whBot [ind];
        
        for (int i = 0; i < cont; i++) {
            if (xDot [i] >= xBot [ind] && (xDot [i] + whDot [i]) <= (xBot [ind] + whBot [ind])) {
                if (yDot [i] >= yBot [ind] && (yDot [i] + whDot [i]) <= (yBot [ind] + whBot [ind])) {
                    // se il quadratino del bot in questione si sovrappone completamente ad un puntino generato casualmente:
                    flagIncBot [ind] = true;                                    // attiva il flag per l'incremento del bot
                    incMaxBot [ind] += 5;                                       // la sua ampiezza incrementa di 5px
                    xDot [i] = maxX;                                            // le coordinate del puntino vengono spostate fuori dal campo di gioco
                    yDot [i] = maxY;                                            // per non interferire nuovamente con i giocatori
                }
            }
        }
        
        // CONTROLLO INGLOBAMENTO GIOCATORI
        
        for (int i = 0; i < dimG; i++) {
            if (flagGiocatore [i]) {
                    if (xBot [ind] < x [i] && (xBot [ind] + whBot [ind]) > (x [i] + wh [i])) {
                        if (yBot [ind] < y [i] && (yBot [ind] + whBot [ind]) > (y [i] + wh [i])) {
                            if (flagStatic [i]) timerStatic [i].cancel();
                            if (flagUp [i]) timerUp [i].cancel();
                            if (flagLeft [i]) timerLeft [i].cancel();
                            if (flagDown [i]) timerDown [i].cancel();
                            if (flagRight [i]) timerRight [i].cancel();
                            
                            numG--;
                            numTot--;
                            flagIncBot [ind] = true;
                            incMaxBot [ind] += wh [i];
                            flagGiocatore [i] = false;
                            pnlCampo.removeKeyListener(kl [i]);
                            lblStatus.setText(nome [i] + " è stato eliminato da Bot " + (ind + 1));
                            lblStatus.setVisible(true);
                        }
                    }
                }
        }
        
        // CONTROLLO INGLOBAMENTO ALTRI BOT
            
        for (int i = 0; i < dimBot; i++) {
            if (i != ind) {
                if (flagBot [i]) {
                    if (xBot [i] >= xBot [ind] && (xBot [i] + whBot [i]) <= (xBot [ind] + whBot [ind])) {
                        if (yBot [i] >= yBot [ind] && (yBot [i] + whBot [i]) <= (yBot [ind] + whBot [ind])) {
                            // se il quadratino del giocatore in questione si sovrappone completamente a quello di un bot:
                            if (flagBotTask [i]) timerBot [i].cancel();         // si fermano tutti i timer legati ad esso

                            numTot--;
                            flagIncBot [ind] = true;
                            incMaxBot [ind] += whBot [i];                       // alle dimensioni del giocatore in questione vengono sommate quelle del bot vittima
                            flagBot [i] = false;                                // il bot in questione viene disattivato
                            deadBot [i] = true;                                 // e definitivamente eliminato
                            lblStatus.setText("Bot " + (i + 1) + " è stato eliminato da Bot " + (ind + 1));
                            lblStatus.setVisible(true);
                        }
                    }
                }
            }
        }
        
        if (flagIncBot [ind]) incrementBotWH(ind);
        d.paintBot(ind);
    }
    
    private void incrementWH(int ind) {
        // questa funzione permette animazioni scorrevoli quando un giocatore 
        // ingloba un puntino o un altro giocatore
        // evitando movimenti "scattosi"
        
        if (contWH < incMaxG [ind]) {                                           // se non si è ancora raggiunta l'ampiezza desiderata
            wh [ind]++;                                                         // incrementa l'ampiezza del giocatore un pixel alla volta
            contWH++;                                                           // incrementa il contatore relativo all'incremento
        }
        
        else {                                                                  // se invece si è già raggiunta l'ampiezza desiderata
            contWH = 0;                                                         // azzera il contatore
            incMaxG [ind] = 0;                                                  // azzera la quantità di pixel da incrementare
            flagInc [ind] = false;                                              // e disattiva il flag di attivazione
        }
    }
    
    private void incrementBotWH(int ind) {
        // analoga ad incrementWH, ma per quanto riguarda i bot
        
        if (contBotWH < incMaxBot [ind]) {
            whBot [ind]++;
            contBotWH++;
        }
        
        else {
            contBotWH = 0;
            incMaxBot [ind] = 0;
            flagIncBot [ind] = false;
        }
    }
    
    private int radar(int ind) {                                                // questa funzione esegue una mappatura dei puntini generati
        while (true)                                                            // finchè non trova un obiettivo valido
            for (int i = 50; i < maxX; i += 50)                                 // prende in considerazione la zona intorno al bot con raggio 50px
                for (int j = 0; j < cont; j++)                                  // in questa "zona" guarda se ci sono dei puntini
                    if ((xBot [ind] - i) < xDot [j] && (xBot [ind] + whBot [ind] + i) > (xDot [j] + whDot [j]))
                        if ((yBot [ind] - i) < yDot [j] && (yBot [ind] + whBot [ind] + i) > (yDot [j] + whDot [j]))
                            if (xDot [j] != maxX && yDot [j] != maxY)           // verifica che il puntino in questione non sia stato già "mangiato"
                                return j;                                       // se si, restituisce l'indice dell'array dei puntini generati
    }
    
    private Color randomColor() {
        switch ((int) (Math.random() * 7)) {
            case 1: return Color.red;
            case 2: return Color.blue;
            case 3: return Color.cyan;
            case 4: return Color.green;
            case 5: return Color.yellow;
            case 6: return Color.orange;
            default: return Color.black;
        }
    }
    
    private final long time, timeDot;
    private final int step, maxX, maxY;
    private final int dimG, dimDot, dimBot;
    private int cont, contWH, contBotWH, numTot;
    private int temp, numG, numBot;
    private int xRand, yRand, whRand;
    private int[] x, y, wh, incMaxBot;
    private int[] xOld, yOld, incMaxG;
    private int[] xDot, yDot, whDot;
    private int[] xBot, yBot, whBot;
    private int[] xBotOld, yBotOld;
    private int[] dir, newDir;
    private boolean xy, pausa, dotConfirm;
    private boolean[] flagStatic, flagInc, flagIncBot, deadBot;
    private boolean[] flagGiocatore, flagBot, flagBotTask;
    private boolean[] flagUp, flagLeft, flagDown, flagRight;
    private Timer[] timerUp, timerLeft, timerDown, timerRight;
    private Timer[] timerBot, timerStatic;
    private Timer timerDotGenerator;
    private KeyListenerBase klb;
    private KeyListener[] kl;
    private ThreadBot[] thrBot;
    private String[] nome;
    private JFrame frame;
    private Disegno d;
    private Color[] c;
    private Menu f1;
    private final Graphics[] g;     // giocatore
    private final Graphics[] b;     // bot
    private final Graphics[] s;     // stringa identificativa
    private final Graphics dot;     // punti generati

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblG1;
    private javax.swing.JLabel lblG2;
    private javax.swing.JLabel lblG3;
    private javax.swing.JLabel lblSide1;
    private javax.swing.JLabel lblSide2;
    private javax.swing.JLabel lblSide3;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JOptionPane opn1;
    private javax.swing.JPanel pnlCampo;
    // End of variables declaration//GEN-END:variables
}
