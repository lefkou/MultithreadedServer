
import javax.swing.JTextArea;
import javax.swing.Timer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lef
 */
public class GUI extends javax.swing.JFrame {
    
    
    /**
     * Creates new form GUI
     */
    public Server server;
    public boolean isServerRunning = false;
    Timer currentTimeTimer;
    Timer updateInfoPanelTimer;
    public GUI(){
        server = new Server("localhost", 8189, this);
        this.currentTimeTimer = new Timer(500,(e) -> {
            serverTime_textField.setText(server.getCurrentServerTime());
        });
        this.updateInfoPanelTimer = new Timer(100,(e) -> {
            shareInfo_textArea.setText(StockMarket.status());
        });
        initComponents();
        currentTimeTimer.start();
        updateInfoPanelTimer.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        serverLog_textArea = new javax.swing.JTextArea();
        startServer_button = new javax.swing.JButton();
        stopServer_button = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        shareInfo_textArea = new javax.swing.JTextArea();
        serverTime_textField = new javax.swing.JTextField();
        serverLog_label = new javax.swing.JLabel();
        shareInfo_label = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        serverLog_textArea.setEditable(false);
        serverLog_textArea.setColumns(20);
        serverLog_textArea.setRows(5);
        jScrollPane1.setViewportView(serverLog_textArea);

        startServer_button.setText("Start Server");
        startServer_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startServer_buttonActionPerformed(evt);
            }
        });

        stopServer_button.setText("Stop Server");
        stopServer_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopServer_buttonActionPerformed(evt);
            }
        });

        shareInfo_textArea.setEditable(false);
        shareInfo_textArea.setColumns(20);
        shareInfo_textArea.setRows(5);
        shareInfo_textArea.setToolTipText("");
        jScrollPane2.setViewportView(shareInfo_textArea);
        shareInfo_textArea.getAccessibleContext().setAccessibleParent(jScrollPane1);

        serverTime_textField.setEditable(false);
        serverTime_textField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        serverTime_textField.setText("Time");
        serverTime_textField.setBorder(null);
        serverTime_textField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                serverTime_textFieldActionPerformed(evt);
            }
        });

        serverLog_label.setText("Server Log");

        shareInfo_label.setText("Share Information");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(160, 160, 160)
                        .addComponent(startServer_button, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(serverTime_textField, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stopServer_button, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(serverLog_label))
                        .addGap(53, 53, 53)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(shareInfo_label)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(serverLog_label)
                    .addComponent(shareInfo_label))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(serverTime_textField, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(startServer_button, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(13, 13, 13))
                    .addComponent(stopServer_button, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void serverTime_textFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_serverTime_textFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_serverTime_textFieldActionPerformed

    private void startServer_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startServer_buttonActionPerformed
        // start server and assign gui to server to use
        if(!isServerRunning){
            server = new Server("localhost", 8189, this);
            server.execute();
            isServerRunning = true; 
        }
        
        
    }//GEN-LAST:event_startServer_buttonActionPerformed

    private void stopServer_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopServer_buttonActionPerformed
        // stop server and print
        if(isServerRunning){
            server.stop();
            server.cancel(true);
            this.isServerRunning = false;
        }

    }//GEN-LAST:event_stopServer_buttonActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel serverLog_label;
    private javax.swing.JTextArea serverLog_textArea;
    private javax.swing.JTextField serverTime_textField;
    private javax.swing.JLabel shareInfo_label;
    private javax.swing.JTextArea shareInfo_textArea;
    private javax.swing.JButton startServer_button;
    private javax.swing.JButton stopServer_button;
    // End of variables declaration//GEN-END:variables

    public JTextArea getServerLog_textArea() {
        return serverLog_textArea;
    }
}