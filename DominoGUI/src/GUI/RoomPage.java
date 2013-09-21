/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

/**
 *
 * @author user
 */
public class RoomPage extends javax.swing.JFrame {

    /**
     * Creates new form RoomPage
     */
    public RoomPage() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        BackgroundPanel = new javax.swing.JPanel();
        RoomsScroll = new javax.swing.JScrollPane();
        RoomsList = new javax.swing.JList();
        EnterRoomButton = new javax.swing.JButton();
        GameImage = new javax.swing.JLabel();
        RoomListTitle = new javax.swing.JLabel();
        ExitButton = new javax.swing.JButton();
        NewRoomButton = new javax.swing.JButton();
        EnterButton = new javax.swing.JButton();
        BackgroundIm = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Domino Effect");
        setMaximizedBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setMinimumSize(new java.awt.Dimension(800, 600));
        setResizable(false);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        BackgroundPanel.setMinimumSize(new java.awt.Dimension(800, 600));
        BackgroundPanel.setOpaque(false);
        BackgroundPanel.setPreferredSize(new java.awt.Dimension(800, 600));

        RoomsList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        RoomsScroll.setViewportView(RoomsList);

        EnterRoomButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/image/button1.jpg"))); // NOI18N
        EnterRoomButton.setText("Entrar");

        RoomListTitle.setFont(new java.awt.Font("Segoe Print", 1, 18)); // NOI18N
        RoomListTitle.setForeground(java.awt.SystemColor.inactiveCaptionBorder);
        RoomListTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        RoomListTitle.setText("Salas disponíveis");

        ExitButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/image/buttons/ExitButton.jpg"))); // NOI18N
        ExitButton.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/image/buttons/ExitClicked.jpg"))); // NOI18N
        ExitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitButtonActionPerformed(evt);
            }
        });

        NewRoomButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/image/buttons/NewRoomButton.jpg"))); // NOI18N
        NewRoomButton.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/image/buttons/NewRoomClicked.jpg"))); // NOI18N
        NewRoomButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewRoomButtonActionPerformed(evt);
            }
        });

        EnterButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/image/buttons/EnterButton.jpg"))); // NOI18N
        EnterButton.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/image/buttons/EnterClicked.jpg"))); // NOI18N
        EnterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EnterButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout BackgroundPanelLayout = new javax.swing.GroupLayout(BackgroundPanel);
        BackgroundPanel.setLayout(BackgroundPanelLayout);
        BackgroundPanelLayout.setHorizontalGroup(
            BackgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BackgroundPanelLayout.createSequentialGroup()
                .addGroup(BackgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BackgroundPanelLayout.createSequentialGroup()
                        .addGap(271, 271, 271)
                        .addComponent(GameImage, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(BackgroundPanelLayout.createSequentialGroup()
                        .addGap(154, 154, 154)
                        .addGroup(BackgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(BackgroundPanelLayout.createSequentialGroup()
                                .addGap(150, 150, 150)
                                .addComponent(EnterRoomButton, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(RoomListTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(RoomsScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ExitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(BackgroundPanelLayout.createSequentialGroup()
                .addGap(214, 214, 214)
                .addComponent(EnterButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67)
                .addComponent(NewRoomButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        BackgroundPanelLayout.setVerticalGroup(
            BackgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BackgroundPanelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(RoomListTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(RoomsScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(BackgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(NewRoomButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, Short.MAX_VALUE)
                    .addComponent(EnterButton, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(102, 102, 102)
                .addComponent(EnterRoomButton, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(BackgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BackgroundPanelLayout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(GameImage, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(BackgroundPanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(ExitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(69, 69, 69))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        getContentPane().add(BackgroundPanel, gridBagConstraints);

        BackgroundIm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/image/backgrounds/BackgroundPrincipal.jpg"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        getContentPane().add(BackgroundIm, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ExitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitButtonActionPerformed
        ExitConfirmation exit = new ExitConfirmation(this, true);
        exit.setVisible(true);
    }//GEN-LAST:event_ExitButtonActionPerformed

    private void NewRoomButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewRoomButtonActionPerformed
        InfoCreate info = new InfoCreate(this, true);
        info.setVisible(true);
    }//GEN-LAST:event_NewRoomButtonActionPerformed

    private void EnterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EnterButtonActionPerformed
        InfoEnter info = new InfoEnter(this, true);
        info.setVisible(true);
    }//GEN-LAST:event_EnterButtonActionPerformed

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
            java.util.logging.Logger.getLogger(RoomPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RoomPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RoomPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RoomPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RoomPage().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BackgroundIm;
    private javax.swing.JPanel BackgroundPanel;
    private javax.swing.JButton EnterButton;
    private javax.swing.JButton EnterRoomButton;
    private javax.swing.JButton ExitButton;
    private javax.swing.JLabel GameImage;
    private javax.swing.JButton NewRoomButton;
    private javax.swing.JLabel RoomListTitle;
    private javax.swing.JList RoomsList;
    private javax.swing.JScrollPane RoomsScroll;
    // End of variables declaration//GEN-END:variables
}
