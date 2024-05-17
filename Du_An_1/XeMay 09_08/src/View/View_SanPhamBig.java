/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

import DomainModel.D_DTBinhXang;
import DomainModel.D_DTXiLanh;
import DomainModel.D_LoaiXe;
import DomainModel.D_Mau;
import DomainModel.D_SanPhamBig;
import DomainModel.D_SoKhung;
import DomainModel.D_XuatXu;
import ServiceImpl.CTSanPhamImpl;
import ServiceImpl.little.DTBinhXangImpl;
import ServiceImpl.little.DTXiLanhImpl;
import ServiceImpl.little.LoaiXeImpl;
import ServiceImpl.little.MauImpl;
import ServiceImpl.little.SoKhungImpl;
import ServiceImpl.little.XuatXuImpl;
import View.Little.View_DTBinhXang;
import View.Little.View_DTXiLanh;
import View.Little.View_LoaiXe;
import View.Little.View_Mau;
import View.Little.View_SoKhung;
import View.Little.View_XuatXu;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class View_SanPhamBig extends javax.swing.JPanel {

    private DefaultTableModel defaultTbalemode1;
    private DefaultTableModel defaultTbalemode2;
    private DefaultComboBoxModel defaultComBoboxModel;

    private final CTSanPhamImpl cTSanPhamImpl;
    private final DTBinhXangImpl dTbinhXangImpl;
    private final DTXiLanhImpl dTXiLanhImpl;
    private final MauImpl mauImpl;
    private final XuatXuImpl xuatXuImpl;
    private final LoaiXeImpl loaiXeImpl;
    private final SoKhungImpl soKhungImpl;
    private final D_SanPhamBig d_SanPhamBig;
    private final List<D_SanPhamBig> list;
    int sl = 0;

    public View_SanPhamBig() {
        initComponents();
        txtMaSP.setEditable(false);

        this.cTSanPhamImpl = new CTSanPhamImpl();

        this.dTbinhXangImpl = new DTBinhXangImpl();
        this.dTXiLanhImpl = new DTXiLanhImpl();
        this.mauImpl = new MauImpl();
        this.xuatXuImpl = new XuatXuImpl();
        this.loaiXeImpl = new LoaiXeImpl();
        this.soKhungImpl = new SoKhungImpl();
        list = cTSanPhamImpl.getAllSanPham();
        this.d_SanPhamBig = new D_SanPhamBig();
        loadData(cTSanPhamImpl.getAllSanPham2());
        loadBX(cTSanPhamImpl.getDTBX());
        loadXL(cTSanPhamImpl.getDTXL());
        loadMau(cTSanPhamImpl.getMau());
        loadXuatXu(cTSanPhamImpl.getXuatXu());
        loadLoaiXe(cTSanPhamImpl.getLoaiXe());
//        loadSoKhung(ServiceSP.getSoKhung());

    }

    public View_SanPhamBig(D_SoKhung x) {
        initComponents();

        this.cTSanPhamImpl = new CTSanPhamImpl();

        this.dTbinhXangImpl = new DTBinhXangImpl();
        this.dTXiLanhImpl = new DTXiLanhImpl();
        this.mauImpl = new MauImpl();
        this.xuatXuImpl = new XuatXuImpl();
        this.loaiXeImpl = new LoaiXeImpl();
        this.soKhungImpl = new SoKhungImpl();
        list = cTSanPhamImpl.getAllSanPham();
        this.d_SanPhamBig = new D_SanPhamBig();
        loadData(cTSanPhamImpl.getAllSanPham2());
        loadBX(cTSanPhamImpl.getDTBX());
        loadXL(cTSanPhamImpl.getDTXL());
        loadMau(cTSanPhamImpl.getMau());
        loadXuatXu(cTSanPhamImpl.getXuatXu());
        loadLoaiXe(cTSanPhamImpl.getLoaiXe());
//        loadSoKhung(ServiceSP.getSoKhung());

    }

    public View_SanPhamBig(ViewS_TrangChu viewS_TrangChu) {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        initComponents();
        this.cTSanPhamImpl = new CTSanPhamImpl();

        this.dTbinhXangImpl = new DTBinhXangImpl();
        this.dTXiLanhImpl = new DTXiLanhImpl();
        this.mauImpl = new MauImpl();
        this.xuatXuImpl = new XuatXuImpl();
        this.loaiXeImpl = new LoaiXeImpl();
        this.soKhungImpl = new SoKhungImpl();
        list = cTSanPhamImpl.getAllSanPham();
        this.d_SanPhamBig = new D_SanPhamBig();
        loadData(cTSanPhamImpl.getAllSanPham2());
        loadBX(cTSanPhamImpl.getDTBX());
        loadXL(cTSanPhamImpl.getDTXL());
        loadMau(cTSanPhamImpl.getMau());
        loadXuatXu(cTSanPhamImpl.getXuatXu());
        loadLoaiXe(cTSanPhamImpl.getLoaiXe());
//        loadSoKhung(ServiceSP.getSoKhung());
    }
//  

    public void loadData(ArrayList<D_SanPhamBig> ctsp) {

        defaultTbalemode1 = (DefaultTableModel) tlbBang1.getModel();
        defaultTbalemode1.setRowCount(0);

        defaultTbalemode2 = (DefaultTableModel) bang2.getModel();
        defaultTbalemode2.setRowCount(0);
        TableColumnModel columnModel = tlbBang1.getColumnModel();
        for (int col = 9; col <= 9; col++) {
            columnModel.getColumn(col).setCellRenderer(new CurrencyRenderer());
        }

        for (D_SanPhamBig x : ctsp) {
            if (x.getTrangThai() == 1) {
                defaultTbalemode1.addRow(new Object[]{
                    x.getIdSP(),
                    x.getMaSP(),
                    x.getTenSP(),
                    x.getDtBinhXang().getDTBinhXang(),
                    x.getdTXiLanh().getDTXiLanh(),
                    x.getMau().getMau(),
                    x.getXuatXu().getXuatXu(),
                    x.getLoaiXe().getLaoiXe(),
                    x.getNamSX(),
                    x.getGiaBan(),
                    x.getTrangThai() == 1 ? "Yes" : "No",});
            } else {
                defaultTbalemode2.addRow(new Object[]{
                    x.getIdSP(),
                    x.getMaSP(),
                    x.getTenSP(),
                    x.getDtBinhXang().getDTBinhXang(),
                    x.getdTXiLanh().getDTXiLanh(),
                    x.getMau().getMau(),
                    x.getXuatXu().getXuatXu(),
                    x.getLoaiXe().getLaoiXe(),
                    x.getNamSX(),
                    x.getGiaBan(),
                    x.getTrangThai() == 1 ? "Yes" : "No",});
            }
        }
    }

    public void loadBX(ArrayList<String> list) {
        cbbBinhXang.removeAllItems();
        defaultComBoboxModel = (DefaultComboBoxModel) cbbBinhXang.getModel();

        for (String string : list) {
            defaultComBoboxModel.addElement(string);
        }

    }

    public void loadXL(ArrayList<String> list) {
        cbbXiLanh.removeAllItems();
        defaultComBoboxModel = (DefaultComboBoxModel) cbbXiLanh.getModel();

        for (String string : list) {
            defaultComBoboxModel.addElement(string);
        }

    }

    public void loadMau(ArrayList<String> list) {
        cbbMau.removeAllItems();
        defaultComBoboxModel = (DefaultComboBoxModel) cbbMau.getModel();

        for (String string : list) {
            defaultComBoboxModel.addElement(string);
        }

    }

    public void loadXuatXu(ArrayList<String> list) {
        cbbXuatXu.removeAllItems();
        defaultComBoboxModel = (DefaultComboBoxModel) cbbXuatXu.getModel();

        for (String string : list) {
            defaultComBoboxModel.addElement(string);
        }

    }

    public void loadLoaiXe(ArrayList<String> list) {
        cbbLoaiXe.removeAllItems();
        defaultComBoboxModel = (DefaultComboBoxModel) cbbLoaiXe.getModel();

        for (String string : list) {
            defaultComBoboxModel.addElement(string);
        }

    }

    public D_SanPhamBig getForm() {
        D_SanPhamBig kh = new D_SanPhamBig();

        kh.setIdSP(txtidctsp.getText());
        kh.setMaSP(txtMaSP.getText());
        kh.setTenSP(txtTenSP.getText());

        String index1 = cbbBinhXang.getSelectedItem().toString();
        D_DTBinhXang dtbx1 = dTbinhXangImpl.getOne(index1);
        kh.setDtBinhXang(dtbx1);

        String index2 = cbbXiLanh.getSelectedItem().toString();
        D_DTXiLanh dtbx2 = dTXiLanhImpl.getOne(index2);
        kh.setdTXiLanh(dtbx2);

        String index3 = cbbMau.getSelectedItem().toString();
        D_Mau dtbx3 = mauImpl.getOne(index3);
        kh.setMau(dtbx3);

        String index4 = cbbXuatXu.getSelectedItem().toString();
        D_XuatXu dtbx4 = xuatXuImpl.getOne(index4);
        kh.setXuatXu(dtbx4);

        String index5 = cbbLoaiXe.getSelectedItem().toString();
        D_LoaiXe dtbx5 = loaiXeImpl.getOne(index5);
        kh.setLoaiXe(dtbx5);

        kh.setNamSX(Integer.parseInt(txtNamSX.getText()));
        kh.setGiaBan(Double.parseDouble(txtGiaBan.getText()));

        int t = 0;
        if (rdoYes.isSelected()) {
            t = 1;
        }
        kh.setTrangThai(t);

        return kh;

    }

    public boolean checkForm() {

        if (txtMaSP.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(this,
                    "Không được bỏ trống Mã",
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (txtTenSP.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(this,
                    "Không được bỏ trống Tên",
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (txtNamSX.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(this,
                    "Không được bỏ trống Năm Sản Xuất",
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (txtGiaBan.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(this,
                    "Không được bỏ trống Giá Bán",
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return false;

        }
        try {
            int nsx = Integer.parseInt(txtNamSX.getText());
            if (nsx < 1800 || nsx > 2023) {

                JOptionPane.showMessageDialog(this,
                        "Năm Sản Xuất Không hợp lệ",
                        "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Năm Sản Xuất không hợp lệ, vui lòng thử lại với năm sản xuất là số",
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Kiểm tra Dung Tích Xi Lanh có phải số hợp lệ hay không
        try {
            double giaBan = Double.parseDouble(txtGiaBan.getText());
            if (giaBan < 15000000 || giaBan > 1000000000) {
                JOptionPane.showMessageDialog(this,
                        "Giá bán không hợp lệ",
                        "Lỗi",
                        JOptionPane.ERROR_MESSAGE);

                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Giá bán không hợp lệ, vui lòng thử lại với giá trị số",
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Tiếp tục kiểm tra các trường khác nếu cần thiết
        return true;
    }

    public void clearForm() {
        cbbBinhXang.setSelectedIndex(0);
        cbbXiLanh.setSelectedIndex(0);
        cbbMau.setSelectedIndex(0);
        cbbXuatXu.setSelectedIndex(0);
        cbbLoaiXe.setSelectedIndex(0);
        txtMaSP.setText("");
        txtTenSP.setText("");
        txtNamSX.setText("");
        txtGiaBan.setText("");
        rdoYes.setSelected(true);

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
        jpSanPham = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tlbBang1 = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        rdoYes = new javax.swing.JRadioButton();
        rdoNo = new javax.swing.JRadioButton();
        jPanel5 = new javax.swing.JPanel();
        btnUpdate = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        txtGiaBan = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cbbBinhXang = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        cbbXiLanh = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        cbbMau = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        cbbXuatXu = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        txtTimKiem = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtMaSP = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtTenSP = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        cbbLoaiXe = new javax.swing.JComboBox<>();
        jButton8 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        txtNamSX = new javax.swing.JTextField();
        txtId = new javax.swing.JTextField();
        txtidctsp = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        bang2 = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        txtTimKiem2 = new javax.swing.JTextField();
        cbbTimKiem2 = new javax.swing.JButton();
        cbbKhoiPhuc = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tlbBang2 = new javax.swing.JTable();

        setBackground(new java.awt.Color(0, 153, 153));
        setPreferredSize(new java.awt.Dimension(1220, 748));

        jPanel3.setBackground(new java.awt.Color(0, 153, 153));

        jPanel12.setBackground(new java.awt.Color(0, 153, 153));
        jPanel12.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jScrollPane2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane2MouseClicked(evt);
            }
        });

        tlbBang1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tlbBang1.setForeground(new java.awt.Color(0, 51, 153));
        tlbBang1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "id", "Mã Sản Phẩm", "Tên Sản Phẩm", "DT Bình Xăng", "DT Xi Lanh", "Màu", "Xuất Xứ", "Loại Xe", "NSX", "Giá Bán", "Trạng Thái", "Số Lượng"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tlbBang1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tlbBang1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tlbBang1);
        if (tlbBang1.getColumnModel().getColumnCount() > 0) {
            tlbBang1.getColumnModel().getColumn(0).setMinWidth(0);
            tlbBang1.getColumnModel().getColumn(0).setPreferredWidth(0);
            tlbBang1.getColumnModel().getColumn(0).setMaxWidth(0);
            tlbBang1.getColumnModel().getColumn(11).setMinWidth(0);
            tlbBang1.getColumnModel().getColumn(11).setPreferredWidth(0);
            tlbBang1.getColumnModel().getColumn(11).setMaxWidth(0);
        }

        jPanel6.setBackground(new java.awt.Color(0, 153, 153));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setText("Trạng Thái :");

        buttonGroup1.add(rdoYes);
        rdoYes.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rdoYes.setForeground(new java.awt.Color(0, 51, 51));
        rdoYes.setText("Còn Bán");
        rdoYes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoYesActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoNo);
        rdoNo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rdoNo.setForeground(new java.awt.Color(0, 51, 51));
        rdoNo.setText("Không còn bán");

        jPanel5.setBackground(new java.awt.Color(0, 153, 153));

        btnUpdate.setBackground(new java.awt.Color(0, 204, 204));
        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/refresh (6).png"))); // NOI18N
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        jButton10.setBackground(new java.awt.Color(0, 204, 204));
        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/broom (1).png"))); // NOI18N
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        btnThem.setBackground(new java.awt.Color(0, 204, 204));
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/plus (1).png"))); // NOI18N
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnThem, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(60, 60, 60))
        );

        txtGiaBan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("VND");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setText("Giá Bán :");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdoYes, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdoNo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(40, 40, 40))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(rdoYes)
                    .addComponent(rdoNo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Dung Tích Bình Xăng :");

        cbbBinhXang.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cbbBinhXang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbBinhXangActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Dung Tích Xi Lanh :");

        cbbXiLanh.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Màu Sắc :");

        cbbMau.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cbbMau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbMauActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel17.setText("Loại Xe :");

        cbbXuatXu.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jButton2.setBackground(new java.awt.Color(0, 102, 102));
        jButton2.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jButton2.setText("Chi tiết");
        jButton2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(0, 102, 102));
        jButton3.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jButton3.setText("Chi tiết");
        jButton3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(0, 102, 102));
        jButton4.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jButton4.setText("Chi tiết");
        jButton4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(0, 102, 102));
        jButton5.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jButton5.setText("Chi tiết");
        jButton5.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jPanel7.setBackground(new java.awt.Color(0, 153, 153));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm Kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jButton7.setText("Tìm Kiếm");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(jButton7)
                .addContainerGap(58, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Mã Sản Phẩm :");

        txtMaSP.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Tên Sản Phẩm :");

        txtTenSP.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel18.setText("Xuất Xứ :");

        cbbLoaiXe.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jButton8.setBackground(new java.awt.Color(0, 102, 102));
        jButton8.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jButton8.setText("Chi tiết");
        jButton8.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton12.setBackground(new java.awt.Color(0, 102, 102));
        jButton12.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jButton12.setText("Update Số Khung,Số Máy");
        jButton12.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setText("Năm Sản Xuất :");

        txtNamSX.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/add.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton9.setBackground(new java.awt.Color(0, 102, 102));
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/update.png"))); // NOI18N
        jButton9.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton9MouseClicked(evt);
            }
        });
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addComponent(cbbXiLanh, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3))
                            .addComponent(jButton9))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel12Layout.createSequentialGroup()
                                        .addGap(317, 317, 317)
                                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel12Layout.createSequentialGroup()
                                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel12Layout.createSequentialGroup()
                                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(txtMaSP, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                                                    .addComponent(txtTenSP, javax.swing.GroupLayout.Alignment.LEADING))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel12Layout.createSequentialGroup()
                                                .addComponent(cbbBinhXang, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButton2))
                                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(39, 39, 39)
                                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel12Layout.createSequentialGroup()
                                                .addComponent(cbbLoaiXe, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButton8))
                                            .addGroup(jPanel12Layout.createSequentialGroup()
                                                .addComponent(cbbXuatXu, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButton5))
                                            .addGroup(jPanel12Layout.createSequentialGroup()
                                                .addComponent(cbbMau, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jButton4))
                                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtNamSX, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtidctsp, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton12))
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(txtidctsp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cbbMau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton4))
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel18))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbbXuatXu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton5))
                                .addGap(26, 26, 26)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel17))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cbbBinhXang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton2)
                                    .addComponent(cbbLoaiXe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton8))
                                .addGap(26, 26, 26)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel15)))
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbbXiLanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3)
                            .addComponent(txtNamSX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(58, 58, 58))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36))))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(86, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jpSanPham.addTab("Sản Phẩm", jPanel3);

        jPanel8.setBackground(new java.awt.Color(0, 153, 153));

        jPanel4.setBackground(new java.awt.Color(0, 153, 153));

        bang2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "id", "Mã Sản Phẩm", "Tên Sản Phẩm", "Dung Tích Binh Xăng", "Dung Tích Xi Lanh", "Màu", "Xuất Xứ", "Loại Xe", "Năm Sản Xuất", "Giá Bán", "Trạng Thái", "Số Lượng"
            }
        ));
        bang2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bang2MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(bang2);
        if (bang2.getColumnModel().getColumnCount() > 0) {
            bang2.getColumnModel().getColumn(0).setMinWidth(0);
            bang2.getColumnModel().getColumn(0).setPreferredWidth(0);
            bang2.getColumnModel().getColumn(0).setMaxWidth(0);
            bang2.getColumnModel().getColumn(11).setMinWidth(0);
            bang2.getColumnModel().getColumn(11).setPreferredWidth(0);
            bang2.getColumnModel().getColumn(11).setMaxWidth(0);
        }

        jPanel9.setBackground(new java.awt.Color(0, 153, 153));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm Kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        cbbTimKiem2.setText("Tìm Kiếm");
        cbbTimKiem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbTimKiem2ActionPerformed(evt);
            }
        });

        cbbKhoiPhuc.setText("Khôi Phục");
        cbbKhoiPhuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbKhoiPhucActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTimKiem2, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(cbbTimKiem2)
                .addGap(28, 28, 28)
                .addComponent(cbbKhoiPhuc, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiem2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbTimKiem2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbKhoiPhuc, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 73, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 141, Short.MAX_VALUE))
        );

        jpSanPham.addTab("Sản Phẩm Không Còn Bán", jPanel8);

        tlbBang2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã Sản Phẩm", "Tên Sản Phẩm", "Dung Tích Bình Xăng", "Dung Tích Xi Lanh", "Màu", "Xuất Xứ", "Số Khung", "Giá", "Số Lượng"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tlbBang2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1275, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(326, Short.MAX_VALUE))
        );

        jpSanPham.addTab("", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpSanPham)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jpSanPham)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tlbBang1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tlbBang1MouseClicked
        int row = tlbBang1.getSelectedRow();
        if (row == -1) {
            return;
        }

        txtidctsp.setText(tlbBang1.getValueAt(row, 0).toString());
        txtMaSP.setText(tlbBang1.getValueAt(row, 1).toString());
        txtTenSP.setText(tlbBang1.getValueAt(row, 2).toString());
        cbbBinhXang.setSelectedItem(tlbBang1.getValueAt(row, 3).toString());
        cbbXiLanh.setSelectedItem(tlbBang1.getValueAt(row, 4).toString());
        cbbMau.setSelectedItem(tlbBang1.getValueAt(row, 5).toString());
        cbbXuatXu.setSelectedItem(tlbBang1.getValueAt(row, 6).toString());
        cbbLoaiXe.setSelectedItem(tlbBang1.getValueAt(row, 7).toString());
        txtNamSX.setText(tlbBang1.getValueAt(row, 8).toString());

        DecimalFormat df = new DecimalFormat("########");
        double gianBan = Double.parseDouble(tlbBang1.getValueAt(row, 9).toString());
        txtGiaBan.setText(df.format(gianBan));

        String trangthai = tlbBang1.getValueAt(row, 10).toString();
        if (trangthai.equalsIgnoreCase("Yes")) {
            rdoYes.setSelected(true);
        } else {
            rdoNo.setSelected(true);

        }
        //        txtidctsp.setText(tlbBang1.getValueAt(row, 9).toString());
        //        label.setIcon(RessiezImage(String.valueOf(list.get(row).getHinhAnh())));
    }//GEN-LAST:event_tlbBang1MouseClicked

    private void jScrollPane2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane2MouseClicked

    private void rdoYesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoYesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoYesActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        if (checkForm()) {
            D_SanPhamBig ds = this.getForm();

            if (this.cTSanPhamImpl.update(ds)) {
                JOptionPane.showMessageDialog(this, "Bạn đã update thành công");
                clearForm();
                loadData(cTSanPhamImpl.getAllSanPham2());

            } else {

                JOptionPane.showMessageDialog(this,
                        "Mã sản phẩm này đã tồn tại ! Bạn có thể tìm ở mục tìm kiếm",
                        "Lỗi",
                        JOptionPane.ERROR_MESSAGE);

            }
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        this.clearForm();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        try {

            if (checkForm()) {
                D_SanPhamBig ds = this.getForm();

                if (this.cTSanPhamImpl.add(ds)) {

                    loadData(cTSanPhamImpl.getAllSanPham2());
                    
                   int cf= JOptionPane.showConfirmDialog(this, "Thêm thành công,mời bạn thêm số khung cho sản phẩm mới");
                   clearForm();
                   if(cf!=JOptionPane.YES_OPTION){
                       return;                               
                   }
                    

                    View_SoKhung view = new View_SoKhung(this);
                    view.setVisible(true);
                    view.setLocationRelativeTo(null);
                    view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                } else {
                    JOptionPane.showMessageDialog(this,
                            "Mã sản phẩm này đã tồn tại ! Bạn có thể tìm ở mục tìm kiếm",
                            "Lỗi",
                            JOptionPane.ERROR_MESSAGE);
                    clearForm();

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void cbbBinhXangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbBinhXangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbBinhXangActionPerformed

    private void cbbMauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbMauActionPerformed
        //        String x = cbbMau.getSelectedItem().toString();
        //        D_Mau mau = mS.getOne(x);
        //        System.out.println(mau.getId());
    }//GEN-LAST:event_cbbMauActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        View_DTBinhXang bx = new View_DTBinhXang();
        bx.setVisible(true);
        bx.setLocationRelativeTo(null);
        bx.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        View_DTXiLanh bx = new View_DTXiLanh();
        bx.setVisible(true);
        bx.setLocationRelativeTo(null);
        bx.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        View_Mau bx = new View_Mau();
        bx.setVisible(true);
        bx.setLocationRelativeTo(null);
        bx.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        View_XuatXu bx = new View_XuatXu();
        bx.setVisible(true);
        bx.setLocationRelativeTo(null);
        bx.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        String ma = txtTimKiem.getText().trim();
        if (ma.equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập mã sản phẩm hoặc tên sản phẩm cần tìm");
            return;
        }
        DefaultTableModel dtm = (DefaultTableModel) tlbBang1.getModel();
        dtm.setRowCount(0);
        int count = 0;
        for (D_SanPhamBig x : cTSanPhamImpl.getAllSanPham2()) {
            if (x.getMaSP().contains(ma) || x.getTenSP().contains(ma)) {
                Object[] obj = {
                    x.getIdSP(),
                    x.getMaSP(),
                    x.getTenSP(),
                    x.getDtBinhXang().getDTBinhXang(),
                    x.getdTXiLanh().getDTXiLanh(),
                    x.getMau().getMau(),
                    x.getXuatXu().getXuatXu(),
                    x.getLoaiXe().getLaoiXe(),
                    x.getNamSX(),
                    x.getGiaBan(),
                    x.getTrangThai() == 1 ? "Yes" : "No",
                    x.getSoLuong()
                };
                dtm.addRow(obj);
                count = 1;
            }
        }
        if (count
                == 0) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy mã sản phẩm hoặc tên sản phẩm nào như vậy");
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        View_LoaiXe bx = new View_LoaiXe();
        bx.setVisible(true);

    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        View_SoKhung view = new View_SoKhung(this);
        view.setVisible(true);
        view.setLocationRelativeTo(null);
        view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_jButton12ActionPerformed

    private void bang2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bang2MouseClicked
        int row = bang2.getSelectedRow();
        if (row == -1) {
            return;
        }

        txtidctsp.setText(bang2.getValueAt(row, 0).toString());
        txtMaSP.setText(bang2.getValueAt(row, 1).toString());
        txtTenSP.setText(bang2.getValueAt(row, 2).toString());
        cbbBinhXang.setSelectedItem(bang2.getValueAt(row, 3).toString());
        cbbXiLanh.setSelectedItem(bang2.getValueAt(row, 4).toString());
        cbbMau.setSelectedItem(bang2.getValueAt(row, 5).toString());
        cbbXuatXu.setSelectedItem(bang2.getValueAt(row, 6).toString());
        cbbLoaiXe.setSelectedItem(bang2.getValueAt(row, 7).toString());
        txtNamSX.setText(bang2.getValueAt(row, 8).toString());

        txtGiaBan.setText(bang2.getValueAt(row, 9).toString());

        String trangthai = bang2.getValueAt(row, 10).toString();
        if (trangthai.equalsIgnoreCase("Yes")) {
            rdoYes.setSelected(true);
        } else {
            rdoNo.setSelected(true);

        }


    }//GEN-LAST:event_bang2MouseClicked

    private void cbbTimKiem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbTimKiem2ActionPerformed
        String ma = txtTimKiem2.getText().trim();
        if (ma.equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập mã sản phẩm hoặc tên sản phẩm cần tìm");
            return;
        }
        DefaultTableModel dtm = (DefaultTableModel) bang2.getModel();
        dtm.setRowCount(0);
        int count = 0;
        for (D_SanPhamBig x : cTSanPhamImpl.getAllSanPham2()) {
            if (x.getMaSP().contains(ma) || x.getTenSP().contains(ma)) {
                Object[] obj = {
                    x.getIdSP(),
                    x.getMaSP(),
                    x.getTenSP(),
                    x.getDtBinhXang().getDTBinhXang(),
                    x.getdTXiLanh().getDTXiLanh(),
                    x.getMau().getMau(),
                    x.getXuatXu().getXuatXu(),
                    x.getLoaiXe().getLaoiXe(),
                    x.getNamSX(),
                    x.getGiaBan(),
                    x.getTrangThai() == 1 ? "Yes" : "No",
                    x.getSoLuong()
                };
                dtm.addRow(obj);
                count = 1;
            }
        }
        if (count == 0) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy mã sản phẩm hoặc tên sản phẩm nào như vậy");
        }
    }//GEN-LAST:event_cbbTimKiem2ActionPerformed

    private void cbbKhoiPhucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbKhoiPhucActionPerformed
        D_SanPhamBig ds = this.getForm();

        int row = this.bang2.getSelectedRow();
        if (row == -1) {
            return;
        }

        if (this.cTSanPhamImpl.updateTrangthai(ds) != null) {
            JOptionPane.showMessageDialog(this, "Bạn đã khôi phục sản sản phẩm ");
            clearForm();
            loadData(cTSanPhamImpl.getAllSanPham2());

        } else {
            JOptionPane.showMessageDialog(this, "Có lỗi đang xẩy ra");

        }
    }//GEN-LAST:event_cbbKhoiPhucActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Random random = new Random();
        int randomNumber = random.nextInt(1000000);
        String formattedNumber = String.format("%06d", randomNumber);
        String masp = "SP" + formattedNumber;

        txtMaSP.setText(masp);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton9MouseClicked

    }//GEN-LAST:event_jButton9MouseClicked

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        loadData(cTSanPhamImpl.getAllSanPham2());
        loadBX(cTSanPhamImpl.getDTBX());
        loadXL(cTSanPhamImpl.getDTXL());
        loadMau(cTSanPhamImpl.getMau());
        loadXuatXu(cTSanPhamImpl.getXuatXu());
        loadLoaiXe(cTSanPhamImpl.getLoaiXe());
    }//GEN-LAST:event_jButton9ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable bang2;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnUpdate;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbbBinhXang;
    private javax.swing.JButton cbbKhoiPhuc;
    private javax.swing.JComboBox<String> cbbLoaiXe;
    private javax.swing.JComboBox<String> cbbMau;
    private javax.swing.JButton cbbTimKiem2;
    private javax.swing.JComboBox<String> cbbXiLanh;
    private javax.swing.JComboBox<String> cbbXuatXu;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jpSanPham;
    private javax.swing.JRadioButton rdoNo;
    private javax.swing.JRadioButton rdoYes;
    private javax.swing.JTable tlbBang1;
    private javax.swing.JTable tlbBang2;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtMaSP;
    private javax.swing.JTextField txtNamSX;
    private javax.swing.JTextField txtTenSP;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JTextField txtTimKiem2;
    private javax.swing.JTextField txtidctsp;
    // End of variables declaration//GEN-END:variables
}
