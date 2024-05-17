/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

import Service.NhanVienService;
import ServiceImpl.NhanVienImpl;
import View.ViewNhanVienCT;
import View.View_DangKy;
import ViewModel.NhanVienModels;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.util.List;
import java.util.Random;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author VietTien
 */
public class View_NhanVien extends javax.swing.JPanel {

    private DefaultTableModel dtm = new DefaultTableModel();
    private List<NhanVienModels> list;
    private NhanVienService rs = new NhanVienImpl();
    private DefaultComboBoxModel cbo = new DefaultComboBoxModel();
    private List<String> listcbo;
    private List<String> listsdt;

    /**
     * Creates new form View_NhanVien
     */
    public View_NhanVien(String user, String pass) {
        initComponents();

        txtMa.setEditable(false);
        dtm = (DefaultTableModel) tbllistNv.getModel();
        cbo = (DefaultComboBoxModel) cbouser.getModel();
        list = rs.getList();
//        listcbo = rs.getListusename();
//        dodulieucbo(listcbo);
//
        dodulieu(list);
        listsdt = rs.getListsdt();
    }

    public View_NhanVien() {
        initComponents();

        dtm = (DefaultTableModel) tbllistNv.getModel();
        cbo = (DefaultComboBoxModel) cbouser.getModel();
        list = rs.getList();
//        listcbo = rs.getListusename();
//        dodulieucbo(listcbo);
//
        dodulieu(list);
        listsdt = rs.getListsdt();
    }

    public void dodulieucbo(List<String> list) {
        cbo.setSelectedItem("UserName");
        for (String nv : list) {
            cbo.addElement(nv);
        }
    }

    private boolean kiemTraDuLieutb() {
        if (txtMa.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã không để trống");
            return false;
        } else if (!txtsdt.getText().trim().isEmpty()) {
            String sdt = txtsdt.getText().trim().replace(".", "");
            if (!sdt.matches("\\d+") || Long.parseLong(sdt) < 0 || sdt.length() != 10) {
                JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ");
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(this, "Số điện thoại không để trống");
            return false;
        }

        if (txtTen.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên không để trống");
            return false;
        } else if (txtdiaChi.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Địa Chỉ không để trống");
            return false;
        } else if (txtEmail.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Email không để trống");
            return false;
        } else if (!isValidEmail(txtEmail.getText().trim())) {
            JOptionPane.showMessageDialog(this, "Email không hợp lệ");
            return false;
        }else if (CheckSdt(txtsdt.getText().trim())) {
            JOptionPane.showMessageDialog(this, "SDT đã tồn tại ");
            return false;
        }

        return true;
    }

    private boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(regex);
    }

    private boolean CheckSdt(String sdt) {
        for (int i = 0; i < listsdt.size(); i++) {
            if (sdt.equalsIgnoreCase(listsdt.get(i))) {
                return true;
            }
        }
        return false;
    }

    private NhanVienModels laydulieutuform() {
        NhanVienModels nv = new NhanVienModels();
        nv.setMaNV(txtMa.getText());
        nv.setTenNV(txtTen.getText());
        nv.setDiaChi(txtdiaChi.getText());
        nv.setSdt(txtsdt.getText());
        nv.setEmail(txtEmail.getText());
        nv.setNgaySinh(txtngaySinh.getDate());
        if (rdonam.isSelected() == true) {
            nv.setGioiTinh(1);
        } else {
            nv.setGioiTinh(0);
        }
        if (rdoHĐ.isSelected() == true) {
            nv.setTrangThai(1);
        } else {
            nv.setTrangThai(0);
        }
        nv.setHinhAnh(duongdanang);
        return nv;
    }

    private void dodulieulenform(int index) {
        NhanVienModels nv = list.get(index);
        txtID.setText(nv.getMaNV());
        txtMa.setText(nv.getMaNV());
        txtTen.setText(nv.getTenNV());
        txtEmail.setText(nv.getEmail());
        txtdiaChi.setText(nv.getDiaChi());
        txtngaySinh.setDate(nv.getNgaySinh());
        txtsdt.setText(nv.getSdt());
        if (nv.getGioiTinh() == 1) {
            rdonam.setSelected(true);
        } else {
            rdonu.setSelected(true);
        }
        if (nv.getTrangThai() == 1) {
            rdoHĐ.setSelected(true);
        } else {
            rdoNghi.setSelected(true);
        }

        label.setIcon(RessiezImage(String.valueOf(list.get(index).getHinhAnh())));
        ;
    }

    public void cleaForm() {
        txtID.setText("");
        txtEmail.setText("");
        txtMa.setText("");
        txtTen.setText("");
        txtdiaChi.setText("");
        txtngaySinh.setDate(null);
        txtsdt.setText("");
        txttk.setText("");
        label.setIcon(null);
        rdoHĐ.setSelected(true);

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
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel16 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txttk = new javax.swing.JTextField();
        btnTk = new javax.swing.JButton();
        btnchonanh = new javax.swing.JButton();
        cbouser = new javax.swing.JComboBox<>();
        cboLoc = new javax.swing.JComboBox<>();
        btnloc = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        label = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnthem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        txtMa = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtTen = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtdiaChi = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        rdonam = new javax.swing.JRadioButton();
        rdonu = new javax.swing.JRadioButton();
        jButton2 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtsdt = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtngaySinh = new com.toedter.calendar.JDateChooser();
        jLabel9 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        rdoHĐ = new javax.swing.JRadioButton();
        rdoNghi = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbllistNv = new javax.swing.JTable();
        txtID = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();

        setBackground(new java.awt.Color(0, 153, 153));

        jPanel16.setBackground(new java.awt.Color(0, 153, 153));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("THÔNG TIN NHÂN VIÊN");

        jLabel11.setText("Tìm Kiếm");

        txttk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttkActionPerformed(evt);
            }
        });

        btnTk.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnTk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imegas/Search.png"))); // NOI18N
        btnTk.setText("Tìm TT");
        btnTk.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnTk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTkActionPerformed(evt);
            }
        });

        btnchonanh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imegas/Unknown person.png"))); // NOI18N
        btnchonanh.setText("Chọn Anh");
        btnchonanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnchonanhActionPerformed(evt);
            }
        });

        cbouser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbouserMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cbouserMouseExited(evt);
            }
        });
        cbouser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbouserActionPerformed(evt);
            }
        });
        cbouser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cbouserKeyReleased(evt);
            }
        });

        cboLoc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đang HĐ", "Đã Nghỉ" }));

        btnloc.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnloc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imegas/Open folder.png"))); // NOI18N
        btnloc.setText("Lọc TT");
        btnloc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlocActionPerformed(evt);
            }
        });

        jPanel3.setBorder(new javax.swing.border.MatteBorder(null));
        jPanel3.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 216, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 32, Short.MAX_VALUE)
        );

        btnthem.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnthem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imegas/Add.png"))); // NOI18N
        btnthem.setText("Thêm NV");
        btnthem.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnthem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemActionPerformed(evt);
            }
        });

        btnSua.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imegas/Edit.png"))); // NOI18N
        btnSua.setText("Sửa TT");
        btnSua.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imegas/Add.png"))); // NOI18N
        jButton5.setText("Xem TT ");
        jButton5.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imegas/User.png"))); // NOI18N
        jButton3.setText("Tạo Mới TK");
        jButton3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(0, 153, 153));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Mã Nhân Viên :");

        txtTen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Tên Nhân Viên :");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Địa Chỉ :");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Giới tính");

        buttonGroup2.add(rdonam);
        rdonam.setSelected(true);
        rdonam.setText("Nam");

        buttonGroup2.add(rdonu);
        rdonu.setText("Nữ");

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/add.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(rdonam)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                                .addComponent(rdonu)
                                .addGap(31, 31, 31))
                            .addComponent(txtdiaChi)
                            .addComponent(txtTen, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtMa)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4))
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(53, 53, 53)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtdiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(rdonam)
                    .addComponent(rdonu))
                .addGap(37, 37, 37))
        );

        jPanel5.setBackground(new java.awt.Color(0, 153, 153));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("SĐT :");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Ngày Sinh :");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Email");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("Trạng Thái");

        buttonGroup1.add(rdoHĐ);
        rdoHĐ.setText("ĐangHĐ");

        buttonGroup1.add(rdoNghi);
        rdoNghi.setText("Nghỉ");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtsdt, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel10))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addComponent(rdoHĐ)
                                .addGap(18, 18, 18)
                                .addComponent(rdoNghi)
                                .addGap(0, 16, Short.MAX_VALUE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtngaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtsdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8)
                    .addComponent(txtngaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(rdoHĐ)
                    .addComponent(rdoNghi))
                .addContainerGap())
        );

        tbllistNv.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tbllistNv.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        tbllistNv.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MãNV", "Họ và Tên", "Địa Chỉ", "Gioi Tính", "Trạng Thái"
            }
        ));
        tbllistNv.addHierarchyListener(new java.awt.event.HierarchyListener() {
            public void hierarchyChanged(java.awt.event.HierarchyEvent evt) {
                tbllistNvHierarchyChanged(evt);
            }
        });
        tbllistNv.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                tbllistNvAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        tbllistNv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbllistNvMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbllistNv);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/clear.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(32, 32, 32)
                                .addComponent(txttk, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addGap(163, 163, 163)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(95, 95, 95)
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnchonanh, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbouser, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(btnTk, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cboLoc, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnloc)
                                .addGap(129, 129, 129)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 847, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnthem, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(94, 94, 94)))))))
                .addGap(22, 22, 22))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(545, 545, 545))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txttk, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(50, 50, 50))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnchonanh)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbouser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboLoc, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnloc, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTk))
                        .addGap(48, 48, 48)))
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addGap(49, 49, 49)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addGap(57, 57, 57)
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnSua))
                                .addGap(40, 40, 40)
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton5)
                                    .addComponent(jButton4)))
                            .addComponent(btnthem)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1292, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 794, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 60, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents
    String duongdanang = "C:\\anh\\anh1.jpg";

    private void dodulieu(List<NhanVienModels> list) {
        dtm.setRowCount(0);
        for (NhanVienModels nhanVienModels : list) {
            dtm.addRow(nhanVienModels.rowData());
        }
    }

    public ImageIcon RessiezImage(String a) {
        ImageIcon MyImage = new ImageIcon(a);
        Image img = MyImage.getImage();
        Image newImage = img.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImage);
        return image;
    }
    private void txtTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenActionPerformed

    private void txttkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttkActionPerformed

    private void btnTkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTkActionPerformed
        // TODO add your handling code here:
        String ma = txttk.getText();
        List<NhanVienModels> list1 = rs.gettk(ma);
        dodulieu(list1);
    }//GEN-LAST:event_btnTkActionPerformed

    private void btnchonanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnchonanhActionPerformed
//        // TODO add your handling code here:
        try {
            JFileChooser f = new JFileChooser("C:\\anh");
            f.setDialogTitle("Mở file");
            f.showOpenDialog(null);
            File ftenanh = f.getSelectedFile();
            duongdanang = ftenanh.getAbsolutePath();
            if (duongdanang != null) {
                label.setIcon(RessiezImage(String.valueOf(duongdanang)));
                System.out.println(duongdanang);
            }

        } catch (Exception e) {
            System.out.println("Chưa Chọn ảnh");;
            System.out.println(duongdanang);;

        }
    }//GEN-LAST:event_btnchonanhActionPerformed

    private void cbouserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbouserMouseClicked
//        // TODO add your handling code here:
//        // TODO add your handling code here:
//        // TODO add your handling code here:
        cbouser.removeAllItems();
        listcbo = rs.getListusename();
        dodulieucbo(listcbo);
    }//GEN-LAST:event_cbouserMouseClicked

    private void cbouserMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbouserMouseExited

    }//GEN-LAST:event_cbouserMouseExited

    private void cbouserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbouserActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbouserActionPerformed

    private void btnlocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlocActionPerformed
        // TODO add your handling code here:
        if (cboLoc.getSelectedItem().toString().equalsIgnoreCase("Đang HĐ")) {
            int t = 1;
            List<NhanVienModels> listl = rs.getListloc(t);
            dodulieu(listl);
        }
        if (cboLoc.getSelectedItem().toString().equalsIgnoreCase("Đã Nghỉ")) {
            int t = 0;
            List<NhanVienModels> listl = rs.getListloc(t);
            dodulieu(listl);
        }
    }//GEN-LAST:event_btnlocActionPerformed

    private void tbllistNvHierarchyChanged(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_tbllistNvHierarchyChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_tbllistNvHierarchyChanged

    private void tbllistNvAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_tbllistNvAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_tbllistNvAncestorAdded

    private void tbllistNvMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbllistNvMouseClicked
        // TODO add your handling code here:
        int index = tbllistNv.getSelectedRow();
        dodulieulenform(index);
    }//GEN-LAST:event_tbllistNvMouseClicked

    private void btnthemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemActionPerformed

        // TODO add your handling code here:
        if (kiemTraDuLieutb()) {
            int index = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn thêm");
            if (index == 0) {
                NhanVienModels tb = laydulieutuform();
                String user = cbouser.getSelectedItem().toString();
                rs.them(tb, user);
                JOptionPane.showMessageDialog(this, "Thêm thành công");
                list = rs.getList();
                dodulieu(list);
                cleaForm();
            } else if (index == 1) {
                JOptionPane.showMessageDialog(this, "Đã hủy");
            }
        }


    }//GEN-LAST:event_btnthemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        int index1 = tbllistNv.getSelectedRow();
        if(index1<=-1){
            JOptionPane.showMessageDialog(this,"Chưa chọn ĐT sửa");
        }else{
        if (kiemTraDuLieutb()) {
            int index = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn Sửa");
            if (index == 0) {
                String id = txtID.getText();
                NhanVienModels tb = laydulieutuform();
                rs.sua(tb, id);
                JOptionPane.showMessageDialog(this, "Sửa thành công");
                list = rs.getList();
                cleaForm();
                dodulieu(list);
            } else if (index == 1) {
                JOptionPane.showMessageDialog(this, "Đã hủy");
            }
        }
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        int index = tbllistNv.getSelectedRow();
        NhanVienModels kh = list.get(index);
        ViewNhanVienCT nv = new ViewNhanVienCT(kh.getMaNV());
        nv.setVisible(true);

    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        View_DangKy su = new View_DangKy();
        su.setVisible(true);

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

    }//GEN-LAST:event_jButton1ActionPerformed

    private void cbouserKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbouserKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_cbouserKeyReleased

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Random random = new Random();
        int randomNumber = random.nextInt(1000000);
        String formattedNumber = String.format("%06d", randomNumber);
        String manv = "Nv" + formattedNumber;

        txtMa.setText(manv);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        cleaForm();
    }//GEN-LAST:event_jButton4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnTk;
    private javax.swing.JButton btnchonanh;
    private javax.swing.JButton btnloc;
    private javax.swing.JButton btnthem;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cboLoc;
    private javax.swing.JComboBox<String> cbouser;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label;
    private javax.swing.JRadioButton rdoHĐ;
    private javax.swing.JRadioButton rdoNghi;
    private javax.swing.JRadioButton rdonam;
    private javax.swing.JRadioButton rdonu;
    private javax.swing.JTable tbllistNv;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtTen;
    private javax.swing.JTextField txtdiaChi;
    private com.toedter.calendar.JDateChooser txtngaySinh;
    private javax.swing.JTextField txtsdt;
    private javax.swing.JTextField txttk;
    // End of variables declaration//GEN-END:variables
}
