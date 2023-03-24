/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos.system;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author savinda
 */
public class Pos_system extends javax.swing.JFrame {

    /**
     * Creates new form Pos_system
     */
    public Pos_system() {
        initComponents();
        con = DBconnect.connect();
        tableLoad();
    }
    
    Connection con; 
    PreparedStatement pst;
    ResultSet rs;
    int total;
    
    public void tableLoad(){
        try{
            String sql = "SELECT part_no, description, qty,price FROM parts";
            pst = (PreparedStatement) con.prepareStatement(sql);
            rs = pst.executeQuery();
            jTableAllItems.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    //balance calculate
    public void balance(int pay, int total){
                
        int balance =  pay - total;
        
        balanceBox.setText(String.valueOf(balance));
    }
    
    //bill display
    public void bill(){
        
        String total = subTotalBox.getText();
        String pay = payBox.getText();
        String balance = balanceBox.getText();
        
        DefaultTableModel model = new DefaultTableModel();
        
        model = (DefaultTableModel)jTable.getModel();
        
        billArea.setText(billArea.getText() + "*****************************\n");
        billArea.setText(billArea.getText() + "*          INVOICE          *\n");
        billArea.setText(billArea.getText() + "*****************************\n");
        
        billArea.setText(billArea.getText() + "Product\t\t"+ "Price\t" + "Amount" + "\n");
        billArea.setText(billArea.getText() + "\n");
        
        //get and print details in table
        for(int i =0; i< model.getRowCount(); ++i){
            String pname = (String)model.getValueAt(i,1);
            String price = (String)model.getValueAt(i,3);
            String amount = (String)model.getValueAt(i,4);
            
            billArea.setText(billArea.getText() + pname + "\t" + price + "\t"+ amount + "\n");
        }
        
        billArea.setText(billArea.getText() + "\n");
        billArea.setText(billArea.getText() + "Sub Total :"+ total+ ".00" + "\n");
        billArea.setText(billArea.getText() + "paid :"+ pay+ ".00" + "\n");
        billArea.setText(billArea.getText() + "Balance :"+ balance+ ".00" + "\n");
        billArea.setText(billArea.getText() + "\n");
        
        billArea.setText(billArea.getText() + "\tThank You!");
         
    }
    
    public void selectItem(){
        String pCode = productCodeBox.getText();
            try {
                pst = (PreparedStatement) con.prepareStatement("select * from parts where part_no = ?");
                
                pst.setString(1, pCode);
                
                rs = pst.executeQuery();
                
                //if product id not available
                if(rs.next() == false){
                    JOptionPane.showMessageDialog(this, "product code not found");
                }else{
                    String pname = rs.getString("description");
                    String price = rs.getString("price");
                    
                    productNameBox.setText(pname.trim());
                    priceBox.setText(price.trim());
                }
                
                        } catch (Exception ex) {
                JOptionPane.showMessageDialog(rootPane, ex);
            }
        
    }
    
    //when select how many items buy calculate total price for that particular item
    public void calculateTotalOfItem(){
        int qty = Integer.parseInt(qtyBox.getValue().toString());
        int price = Integer.parseInt(priceBox.getText());
        
        int total = qty * price;
        
        TotalBox.setText(String.valueOf(total));
    }
    
    //adding selected item to table
    public void itemAddToTable(){
        
        DefaultTableModel model = new DefaultTableModel();
        
        model = (DefaultTableModel)jTable.getModel();
        
        model. addRow(new Object[]
        {
            productCodeBox.getText(),
            productNameBox.getText(),
            qtyBox.getValue().toString(),
            priceBox.getText(),
            TotalBox.getText(),
        } 
                );
        
        int sum =  0 ;
        
        for (int i = 0; i<jTable.getRowCount(); ++i){
            sum = sum + Integer.parseInt(jTable.getValueAt(i, 4).toString());
        }
        
        subTotalBox.setText(Integer.toString(sum));
        
        clearAllCells();   
    }
    
    public void clearAllCells(){
        productCodeBox.setText("");
            productNameBox.setText("");
            qtyBox.setValue(0);
            priceBox.setText("");
            TotalBox.setText("");
            payBox.setText("");
            balanceBox.setText("");
    }
    
    //clear all parts of interface
    public void clear(){
        
        clearAllCells();
        
        //clear table data
        DefaultTableModel model = new DefaultTableModel();
        model = (DefaultTableModel)jTable.getModel();
        model.setRowCount(0);
        
        billArea.setText(" ");
        subTotalBox.setText("");
        
        total = 0;
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        TotalBox = new javax.swing.JTextField();
        productCodeBox = new javax.swing.JTextField();
        productNameBox = new javax.swing.JTextField();
        priceBox = new javax.swing.JTextField();
        qtyBox = new javax.swing.JSpinner();
        addBtn = new javax.swing.JButton();
        clearBtn = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        balanceBox = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        subTotalBox = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        payBox = new javax.swing.JTextField();
        calculate = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        billArea = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableAllItems = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sales", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel1.setForeground(new java.awt.Color(51, 51, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Total");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 30, 70, 20));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Product Code");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 32, 130, 20));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Product Name");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 30, 150, 20));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Qty");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 30, 70, 20));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Price");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 30, 70, 20));

        TotalBox.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        TotalBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TotalBoxActionPerformed(evt);
            }
        });
        jPanel1.add(TotalBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 60, 130, 30));

        productCodeBox.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        productCodeBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productCodeBoxActionPerformed(evt);
            }
        });
        productCodeBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                productCodeBoxKeyPressed(evt);
            }
        });
        jPanel1.add(productCodeBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 120, 30));

        productNameBox.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel1.add(productNameBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 60, 130, 30));

        priceBox.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel1.add(priceBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 60, 130, 30));

        qtyBox.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        qtyBox.setRequestFocusEnabled(false);
        qtyBox.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                qtyBoxStateChanged(evt);
            }
        });
        jPanel1.add(qtyBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 60, 40, 30));

        addBtn.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        addBtn.setText("Add");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });
        jPanel1.add(addBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 130, 80, 40));

        clearBtn.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        clearBtn.setText("Clear All");
        clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtnActionPerformed(evt);
            }
        });
        jPanel1.add(clearBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(609, 135, 120, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 13, 780, 200));

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Total");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, -1, -1));

        balanceBox.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        balanceBox.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        balanceBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                balanceBoxActionPerformed(evt);
            }
        });
        jPanel2.add(balanceBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 160, 130, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("pay");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, -1, -1));

        subTotalBox.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        subTotalBox.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPanel2.add(subTotalBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(102, 36, 130, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setText("balance");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 140, -1, -1));

        payBox.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        payBox.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPanel2.add(payBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, 130, -1));

        calculate.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        calculate.setText("Calculate Bill");
        calculate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calculateActionPerformed(evt);
            }
        });
        jPanel2.add(calculate, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 200, -1, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 20, 360, 230));

        jTable.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product code", "product Name", "Qty", "Price", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 770, 460));

        billArea.setColumns(20);
        billArea.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        billArea.setRows(5);
        jScrollPane2.setViewportView(billArea);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 260, 340, 370));

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton1.setText("Print");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 650, 90, 40));

        jTableAllItems.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(jTableAllItems);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 10, 570, 690));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void productCodeBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productCodeBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_productCodeBoxActionPerformed

    private void balanceBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_balanceBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_balanceBoxActionPerformed

    private void productCodeBoxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_productCodeBoxKeyPressed
        
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            
            selectItem();
            
        }
    }//GEN-LAST:event_productCodeBoxKeyPressed

    private void qtyBoxStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_qtyBoxStateChanged

        calculateTotalOfItem();
        
    }//GEN-LAST:event_qtyBoxStateChanged

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        //pnli quantity has value go to next step
        int Qty = Integer.parseInt(qtyBox.getValue().toString());
        if(Qty>=1){
        itemAddToTable();
        }
            
    }//GEN-LAST:event_addBtnActionPerformed

    private void TotalBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TotalBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TotalBoxActionPerformed

    private void calculateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calculateActionPerformed
        total = Integer.parseInt(subTotalBox.getText());
        int pay = Integer.parseInt(payBox.getText());
        
        if(pay>=total){
        balance(pay,total);
        bill();
        }
        else{
            JOptionPane.showMessageDialog(rootPane, "Payment Not Enough");
        }
        
    }//GEN-LAST:event_calculateActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //printing bill
        try {
            billArea.print();
        } catch (PrinterException ex) {
            Logger.getLogger(Pos_system.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
        clear();
    }//GEN-LAST:event_clearBtnActionPerformed

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
            java.util.logging.Logger.getLogger(Pos_system.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pos_system.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pos_system.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pos_system.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Pos_system().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField TotalBox;
    private javax.swing.JButton addBtn;
    private javax.swing.JTextField balanceBox;
    private javax.swing.JTextArea billArea;
    private javax.swing.JButton calculate;
    private javax.swing.JButton clearBtn;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable;
    private javax.swing.JTable jTableAllItems;
    private javax.swing.JTextField payBox;
    private javax.swing.JTextField priceBox;
    private javax.swing.JTextField productCodeBox;
    private javax.swing.JTextField productNameBox;
    private javax.swing.JSpinner qtyBox;
    private javax.swing.JTextField subTotalBox;
    // End of variables declaration//GEN-END:variables
}
