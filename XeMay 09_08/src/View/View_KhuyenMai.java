/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

import Service.IKhuyenMaiService;
import ServiceImpl.KhuyenMaiServiceImpl;
import ViewModel.KhuyenMai_View;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author VietTien
 */
public class View_KhuyenMai extends javax.swing.JPanel {

    private IKhuyenMaiService khuyenMaiService;
    private List<KhuyenMai_View> list;
    private boolean flag = false;

    public View_KhuyenMai() {
        initComponents();
       
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);

//        setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.khuyenMaiService = new KhuyenMaiServiceImpl();
        list = khuyenMaiService.getall();
        this.taiDuLieu();
    }

    private void xoaDuLieu() {
        this.txtgia.setText("");
        this.txtma.setText("");
        // this.txtngaybd.setDate("yyyy/MM/dd");
        this.txtten.setText("");

    }

    private void taiDuLieu() {
        List<KhuyenMai_View> list = this.khuyenMaiService.getall();
        DefaultTableModel dtm = (DefaultTableModel) this.tblbang.getModel();
        dtm.setRowCount(0);
        DefaultTableModel dtm2 = (DefaultTableModel) this.tlbBang2.getModel();
        dtm2.setRowCount(0);
        for (KhuyenMai_View km : list) {
            if (km.getTrangThai() == 1) {
                Object[] rowData = {
                    km.getMaKH(),
                    km.getTenKH(),
                    km.getThoiGianKM(),
                    km.getThoiGianKT(),
                    km.getGiaTri(),
                    km.getTrangThai() == 1 ? "Yes" : "No"

                };
                dtm.addRow(rowData);
            }else{
                   Object[] rowData = {
                    km.getMaKH(),
                    km.getTenKH(),
                    km.getThoiGianKM(),
                    km.getThoiGianKT(),
                    km.getGiaTri(),
                    km.getTrangThai() == 1 ? "Yes" : "No"

                };
                dtm2.addRow(rowData);
            }

        }
    }

    

    private KhuyenMai_View getFrom() {

//        String ma = this.txtma.getText();
//        String ten = this.txtten.getText();
//        int loai = 0;
//        Date ngaybd = this.txtngaybd.getDate();
//        Date ngaykt = this.txtngaykt.getDate();
//        String gia = this.txtgia.getText();
//        int gia1 = Integer.parseInt(gia);
//        
//         int t = 0;
//        if (rdoYes.isSelected()) {
//            t = 1;
//        }
//        int kh.setTrangThai(t);
//        int trangThai = Integer.parseInt(gia);
//
//        KhuyenMai_View km = new KhuyenMai_View("", ma, ten, ngaybd, ngaykt, gia1);
//        return km;
        KhuyenMai_View km = new KhuyenMai_View();

        km.setMaKH(txtma.getText());
        km.setTenKH(txtten.getText());
        km.setThoiGianKM(txtngaybd.getDate());
        km.setThoiGianKT(txtngaykt.getDate());
        km.setGiaTri(Integer.parseInt(txtgia.getText()));
        int t = 0;
        if (rdoYes.isSelected()) {
            t = 1;
        }
        km.setTrangThai(t);

        return km;

    }

    public boolean checkForm() {

        if (txtma.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không được bỏ trống Mã");
            return false;
        }
        if (txtten.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không được bỏ trống Tên");
            return false;
        }
        if (txtgia.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không được bỏ trống Năm Sản Xuất");
            return false;
        }

        if (txtngaybd.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Không được bỏ trống Ngày Bắt Đầu");
            return false;
        }

        if (txtngaykt.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Không được bỏ trống Ngày Kết Thúc");
            return false;
        }

        Date ngayBatDau = txtngaybd.getDate();
        Date ngayKetThuc = txtngaykt.getDate();

        if (ngayBatDau.after(ngayKetThuc)) {
            JOptionPane.showMessageDialog(this, "Ngày Bắt Đầu phải trước Ngày Kết Thúc");
            return false;
        }

        try {
            double gia = Double.parseDouble(txtgia.getText());
            if (gia < 0 || gia > 2000000) {
                JOptionPane.showMessageDialog(this, "Giá trị không hợp lệ");
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Năm Sản Xuất không hợp lệ, vui lòng thử lại với giá trị là số");
            return false;
        }

        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        txtma = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtten = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtngaybd = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        txtngaykt = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        txtgia = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        rdoYes = new javax.swing.JRadioButton();
        rdoNo = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        btnsua = new javax.swing.JButton();
        btnthem = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblbang = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tlbBang2 = new javax.swing.JTable();

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));

        jButton1.setBackground(new java.awt.Color(0, 102, 102));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 255, 255));
        jLabel3.setText("Quản Lý Khuyến Mãi");

        jPanel3.setBackground(new java.awt.Color(0, 153, 153));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Mã Khuyến Mãi :");

        txtten.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttenActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Tên khuyến mãi :");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Thời Gian BĐ :");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Thời Gian KT :");

        txtgia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtgiaKeyReleased(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Giá Trị :");

        buttonGroup1.add(rdoYes);
        rdoYes.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rdoYes.setText("Hoạt Động");

        buttonGroup1.add(rdoNo);
        rdoNo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rdoNo.setText("Không Hoạt Động");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Trạng Thái :");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(54, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtngaykt, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtngaybd, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel4)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addGap(18, 18, 18)
                            .addComponent(txtten, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addComponent(txtgia, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(rdoYes)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rdoNo)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtma)))
                .addGap(13, 13, 13))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtma, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(41, 41, 41)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(57, 57, 57)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtngaybd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(50, 50, 50)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtngaykt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(55, 55, 55)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtgia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoYes)
                    .addComponent(rdoNo)
                    .addComponent(jLabel8)))
        );

        jPanel4.setBackground(new java.awt.Color(0, 153, 153));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/clear.png"))); // NOI18N
        jButton2.setText("Clear");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        btnsua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/update.png"))); // NOI18N
        btnsua.setText("Sua");
        btnsua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsuaActionPerformed(evt);
            }
        });

        btnthem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/add.png"))); // NOI18N
        btnthem.setText("Them");
        btnthem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnthemMouseClicked(evt);
            }
        });
        btnthem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnthem)
                .addGap(18, 18, 18)
                .addComponent(btnsua, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnsua, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnthem, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        tblbang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Khuyến Mãi", "Tên Khuyến Mãi", "Ngày Bắt Đầu", "Ngày Kết Thúc", "Giá Trị", "Trạng Thái"
            }
        ));
        tblbang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblbangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblbang);

        jTabbedPane1.addTab("Mã Còn Được Sử Dụng", jScrollPane1);

        tlbBang2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã Khuyến Mãi", "Tên Khuyến Mãi", "Ngày Bắt Đầu", "Ngày Kết Thúc", "Giá Trị", "Trạng Thái"
            }
        ));
        jScrollPane2.setViewportView(tlbBang2);
        if (tlbBang2.getColumnModel().getColumnCount() > 0) {
            tlbBang2.getColumnModel().getColumn(1).setResizable(false);
        }

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 747, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 496, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Mã Bị Tạm Dừng", jPanel5);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(414, 414, 414))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 747, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(482, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 467, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(375, 375, 375))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(48, 48, 48)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTabbedPane1))
                .addContainerGap(377, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 12, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txttenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttenActionPerformed

    private void txtgiaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtgiaKeyReleased

    }//GEN-LAST:event_txtgiaKeyReleased

    private void tblbangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblbangMouseClicked
        try {

            int row = this.tblbang.getSelectedRow();
            if (row == -1) {
                return;
            }
            KhuyenMai_View kmv = list.get(row);

            txtma.setText(tblbang.getValueAt(row, 0).toString());
            txtten.setText(tblbang.getValueAt(row, 1).toString());
            String ngayBD = this.tblbang.getValueAt(row, 2).toString();
            String ngayKT = this.tblbang.getValueAt(row, 3).toString();
            txtgia.setText(tblbang.getValueAt(row, 4).toString());
            String trangthai = this.tblbang.getValueAt(row, 5).toString();

          
            this.txtngaybd.setDate(kmv.getThoiGianKM());
            this.txtngaykt.setDate(kmv.getThoiGianKT());

           

            if (trangthai.equalsIgnoreCase("Yes")) {
                rdoYes.setSelected(true);
            } else {
                rdoNo.setSelected(true);

            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_tblbangMouseClicked

    private void btnsuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsuaActionPerformed
        if (checkForm()) {
            KhuyenMai_View ds = this.getFrom();
            if (khuyenMaiService.update(ds)) {
                JOptionPane.showMessageDialog(this, "Update đã thêm thành công 1 mã giảm giá");
                taiDuLieu();
                clearForm();
            } else {
                JOptionPane.showMessageDialog(this, "Có lỗi xẩy ra");
            }
        }
    }//GEN-LAST:event_btnsuaActionPerformed

    private void btnthemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnthemMouseClicked

    }//GEN-LAST:event_btnthemMouseClicked

    private void btnthemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemActionPerformed
//        if (checkForm()) {
//            KhuyenMai_View ds = this.getFrom();
//            if (khuyenMaiService.insert(ds)) {
//                JOptionPane.showMessageDialog(this, "Bạn đã thêm thành công 1 mã giảm giá");
//                taiDuLieu();
//                clearForm();
//            } else {
//                JOptionPane.showMessageDialog(this, "Có lỗi xẩy ra");
//            }
//        }
        if (checkForm()) {
            KhuyenMai_View ds = this.getFrom();

            if (this.khuyenMaiService.insert(ds)) {

                JOptionPane.showMessageDialog(this, "Bạn đã thêm thành công 1 mã giảm giá");
                taiDuLieu();
                clearForm();

            } else {
                JOptionPane.showMessageDialog(this,
                        "Mã sản phẩm này đã tồn tại ! Bạn có thể tìm ở mục tìm kiếm",
                        "Lỗi",
                        JOptionPane.ALLBITS);
                clearForm();

            }

        }
    }//GEN-LAST:event_btnthemActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.clearForm();
    }//GEN-LAST:event_jButton2ActionPerformed
    public void clearForm() {
        txtma.setText("");
        txtten.setText("");
        txtgia.setText("");
        txtngaybd.setDate(null);
        txtngaykt.setDate(null);

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnsua;
    private javax.swing.JButton btnthem;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JRadioButton rdoNo;
    private javax.swing.JRadioButton rdoYes;
    private javax.swing.JTable tblbang;
    private javax.swing.JTable tlbBang2;
    private javax.swing.JTextField txtgia;
    private javax.swing.JTextField txtma;
    private com.toedter.calendar.JDateChooser txtngaybd;
    private com.toedter.calendar.JDateChooser txtngaykt;
    private javax.swing.JTextField txtten;
    // End of variables declaration//GEN-END:variables
}
