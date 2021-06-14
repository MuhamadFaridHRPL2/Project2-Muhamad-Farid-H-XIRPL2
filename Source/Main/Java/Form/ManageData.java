package form;

import classes.DatabaseConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class ManageData extends javax.swing.JDialog {

    /**
     * Creates new form ManageData
     */
    Connection koneksi;
    String action;
    
    public ManageData(java.awt.Frame parent, boolean modal, String action, String nis) {
        super(parent, modal);
        initComponents();
        koneksi = DatabaseConnection.getConnection("localhost", "3306", "root", "", "db_sekolah");
        
        this.action = action;
        headingForm.setText(this.action + " Data");
        
        if(this.action.equals("Edit")){
            inputNIS.setEnabled(false);
            showData(nis);
        }
    }
    
    public void showData(String nis){
         try{
            Statement stmt = koneksi.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String query = "SELECT * FROM t_siswa WHERE nis = '" + nis + "'";
            System.out.println(query);
            ResultSet rs = stmt.executeQuery(query);
            rs.first();
            System.out.println(rs.first());
            inputNIS.setText(rs.getString("nis"));
            inputNama.setText(rs.getString("nama"));
            comboBoxKelas.setSelectedItem(rs.getString("kelas"));
            comboBoxJurusan.setSelectedItem(rs.getString("jurusan"));
        } catch(SQLException ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan di Query");
        }
    }
    
    public void insertData(){
        String nis = inputNIS.getText();
        String nama = inputNama.getText();
        String kelas = comboBoxKelas.getSelectedItem().toString();
        String jurusan = comboBoxJurusan.getSelectedItem().toString();
        
        try{
            Statement stmt = koneksi.createStatement();
            String query = "INSERT INTO t_siswa(nis, nama, kelas, jurusan) " 
                    + "VALUES('" + nis + "','" + nama + "','" + kelas + "','" + jurusan + "')";
            System.out.println(query);
            int berhasil = stmt.executeUpdate(query);
            if(berhasil == 1){
                JOptionPane.showMessageDialog(null, "Data Berhasil Dimasukkan");
            } else{
                JOptionPane.showMessageDialog(null, "Data Gagal Dimasukkan");
            }
        } catch(SQLException ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan pada Database");
        }
    }
    
      public void updateData(){
        String nis = inputNIS.getText();
        String nama = inputNama.getText();
        String kelas = comboBoxKelas.getSelectedItem().toString();
        String jurusan = comboBoxJurusan.getSelectedItem().toString();
        
        try{
            Statement stmt = koneksi.createStatement();
            String query = "UPDATE t_siswa SET nama = '" + nama + "', "
                    + "kelas = '" + kelas + "', "
                    + "jurusan = '" + jurusan + "' WHERE nis = '" + nis + "'";
            System.out.println(query);
            int berhasil = stmt.executeUpdate(query);
            if(berhasil == 1){
                JOptionPane.showMessageDialog(null, "Data Berhasil Dimasukkan");
            } else{
                JOptionPane.showMessageDialog(null, "Data Gagal Dimasukkan");
            }
        } catch(SQLException ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan pada Database");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        headingForm = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        labelNIS = new javax.swing.JLabel();
        inputNIS = new javax.swing.JTextField();
        inputNama = new javax.swing.JTextField();
        labelNama = new javax.swing.JLabel();
        labelKelas = new javax.swing.JLabel();
        comboBoxKelas = new javax.swing.JComboBox<>();
        labelJurusan = new javax.swing.JLabel();
        comboBoxJurusan = new javax.swing.JComboBox<>();
        btnSave = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        headingForm.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        headingForm.setText("Add Data");

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        labelNIS.setText("NIS");

        labelNama.setText("Nama Lengkap");

        labelKelas.setText("Kelas");

        comboBoxKelas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "X TKI", "XI TKJ 1", "XI TKJ 2", "XI RPL 1", "XI RPL 2", "XI RPL 3", "XI MM" }));
        comboBoxKelas.setToolTipText("");
        comboBoxKelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxKelasActionPerformed(evt);
            }
        });

        labelJurusan.setText("Kelas");

        comboBoxJurusan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Teknik Komputer dan Informatika", "Teknik Komputer dan Jaringan", "Rekayasa Perangkat Lunak", "Multimedia" }));
        comboBoxJurusan.setToolTipText("");
        comboBoxJurusan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxJurusanActionPerformed(evt);
            }
        });

        btnSave.setText("Simpan");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(headingForm)
                        .addGap(0, 274, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelJurusan)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(labelNama)
                                        .addComponent(labelKelas)
                                        .addComponent(labelNIS))
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(comboBoxJurusan, 0, 236, Short.MAX_VALUE)
                                        .addComponent(comboBoxKelas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(inputNama)
                                        .addComponent(inputNIS)))))
                        .addGap(19, 19, 19)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(headingForm)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNIS)
                    .addComponent(inputNIS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNama)
                    .addComponent(inputNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboBoxKelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelKelas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboBoxJurusan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelJurusan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comboBoxKelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxKelasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBoxKelasActionPerformed

    private void comboBoxJurusanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxJurusanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBoxJurusanActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        if(action.equals("Edit")) updateData();
        else insertData();
    }//GEN-LAST:event_btnSaveActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox<String> comboBoxJurusan;
    private javax.swing.JComboBox<String> comboBoxKelas;
    private javax.swing.JLabel headingForm;
    private javax.swing.JTextField inputNIS;
    private javax.swing.JTextField inputNama;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel labelJurusan;
    private javax.swing.JLabel labelKelas;
    private javax.swing.JLabel labelNIS;
    private javax.swing.JLabel labelNama;
    // End of variables declaration//GEN-END:variables
}