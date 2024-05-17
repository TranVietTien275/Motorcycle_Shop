/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

import Service.CTHoaDonService;
import Service.CTSanPhamService;
import Service.HoaDonServies;
import ServiceImpl.CTHoaDonImpl;
import ServiceImpl.CTSanPhamImpl;
import ServiceImpl.HoadonServicesImpl;
import Utilities.ExportExcel;
import ViewModel.CTHoaDonNT;
import ViewModel.CTSanPhamHD;
import ViewModel.HoaDon;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.sql.Date;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author VietTien
 */
public class View_ThongKe extends javax.swing.JPanel {

    private HoaDonServies hoaDonServies = new HoadonServicesImpl();
    private CTHoaDonService ctHoaDonServies = new CTHoaDonImpl();
    private CTSanPhamService ctSanPhamService = new CTSanPhamImpl();
    private ExportExcel exportExcel = new ExportExcel();
    private DefaultComboBoxModel defaultComboBoxModel;
    private DefaultTableModel defaultTableModel;
    private JPanel jpnItem;

    public View_ThongKe() {
        initComponents();

        cbbNamDoanhThu.removeAllItems();
        
        addNamDoanhThu(hoaDonServies.getYear());
//        setBieuDoDoanhThu(jpnThongKe);
        lblSumDonHang.setText(String.valueOf(sumHoaDon(hoaDonServies.getList(0))) + " Đơn Hàng");
        lblSumDate.setText(sum(ctHoaDonServies.sumDate(getNgayHienTai(), 0)));
        lblSumMonth.setText(sum(ctHoaDonServies.sumMonth(getMonth(), 0)));
        lblSumYear.setText(sum(ctHoaDonServies.sumYear(getYear(), 0)));
    }

    private void loadDataThongKe(List<CTSanPhamHD> list) {
        defaultTableModel = (DefaultTableModel) tblSanPham.getModel();
        defaultTableModel.setRowCount(0);
        int count = 1;
        for (CTSanPhamHD sanPham : list) {
            defaultTableModel.addRow(new Object[]{
                count++,
                sanPham.getMaSP(),
                ctSanPhamService.getByNameSanPham(sanPham.getMaSP()),
                ctSanPhamService.getByNameDTBinhXang(sanPham.getMaSP()),
                ctSanPhamService.getByNameDTXiLanh(sanPham.getMaSP()),
                ctSanPhamService.getByNameMau(sanPham.getMaSP()),
                ctSanPhamService.getByNameXuatXu(sanPham.getMaSP()),
                ctSanPhamService.getByNameLoaiXe(sanPham.getMaSP()),
                sanPham.getSoLuong()
            });
        }

    }

    private void loadDataDoanhThu(List<Integer> list) {
        defaultTableModel = (DefaultTableModel) tblDoanhThu.getModel();
        defaultTableModel.setRowCount(0);

        TableColumnModel columnModel = tblDoanhThu.getColumnModel();
        for (int col = 2; col <= 4; col++) {
            columnModel.getColumn(col).setCellRenderer(new View.CurrencyRenderer());
        }

        for (Integer integer : list) {
            defaultTableModel.addRow(new Object[]{
                integer,
                ctHoaDonServies.getSoLuongDoanhThu(integer, 0),
                ctHoaDonServies.getTongTienHang(integer, 0),
                ctHoaDonServies.getTongTienHang(integer, 0) - ctHoaDonServies.getSoTienPhaiTra(integer, 0),
                ctHoaDonServies.getSoTienPhaiTra(integer, 0)
            });
        }
    }

    private void addNamDoanhThu(List<Integer> list) {
        defaultComboBoxModel = (DefaultComboBoxModel) cbbNamDoanhThu.getModel();
        for (Integer integer : list) {
            defaultComboBoxModel.addElement(integer);
        }
    }

//    private void setBieuDoDoanhThu(JPanel jpnItem) {
//        List<CTHoaDonNT> list = ctHoaDonServies.getListByHoaDon(getYear(), 0);
//        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//        if (list != null) {
//            for (CTHoaDonNT item : list) {
//                dataset.addValue(item.getDoanhThu(), "Sản phẩm", item.getNgayTao());
//            }
//        }
//        JFreeChart barChart = ChartFactory.createBarChart(
//                "Biểu đồ thống kê doanh thu ".toUpperCase(), "Thời gian(tháng)", "Doanh thu (VND)", dataset, PlotOrientation.VERTICAL, false, true, false);
//        ChartPanel chartPanel = new ChartPanel(barChart);
//        chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), 321));
//        jpnItem.removeAll();
//        jpnItem.setLayout(new CardLayout());
//        jpnItem.add(chartPanel);
//        jpnItem.validate();
//        jpnItem.repaint();
//    }

    private int sumHoaDon(List<HoaDon> list) {
        int count = 0;
        for (HoaDon bill : list) {
            count++;
        }
        return count;
    }

    private Date getNgayHienTai() {
        LocalDate localDate = LocalDate.now();
        return Date.valueOf(localDate);
    }

    private String getMonth() {
        LocalDate currentDate = LocalDate.now();
        int month = currentDate.getMonthValue();
        String monthString = String.format("%02d", month);
        return monthString;
    }

    private String getYear() {
        LocalDate currentDate = LocalDate.now();
        int year = currentDate.getYear();
        String yearString = String.valueOf(year);
        return yearString;
    }

//    private String sum(List<Double> list) {
//        for (Double sum : list) {
//            if (sum == null) {
//                return 0 + ".VND";
//            } else {
//                int intValue = (int) Math.round(sum);
//                return intValue + ".VND";
//            }
//        }
//        return 0 + ".VND";
//    }
    private String formatCurrency(double amount) {
        DecimalFormat decimalFormat = new DecimalFormat("#,##0");
        String formattedAmount = decimalFormat.format(amount);
        return formattedAmount + " VNĐ";
    }

    private String sum(List<Double> list) {
        double totalSum = 0.0;

        for (Double sum : list) {
            if (sum != null) {
                totalSum += sum;
            }
        }

        return formatCurrency(totalSum);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelThongKe1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblSumDate = new javax.swing.JLabel();
        lblgetDate1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblSumDonHang = new javax.swing.JLabel();
        lblSumHangPanelThongKe = new javax.swing.JLabel();
        lblSumHuyPanelThongKe = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lblSumMonth = new javax.swing.JLabel();
        lblgetMonth = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        lblSumYear = new javax.swing.JLabel();
        lblGetYear = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane18 = new javax.swing.JScrollPane();
        tblDoanhThu = new javax.swing.JTable();
        jLabel89 = new javax.swing.JLabel();
        cbbNamDoanhThu = new javax.swing.JComboBox<>();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane19 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        btnXuatFileExcelPanelThongKe = new javax.swing.JButton();
        jLabel87 = new javax.swing.JLabel();
        txtNgayBatDau = new com.toedter.calendar.JDateChooser();
        jLabel88 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jpnThongKe = new javax.swing.JPanel();
        txtNgayKetThuc = new com.toedter.calendar.JDateChooser();
        btnTimKiemPanelThongKe = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jButton1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(0, 153, 153));

        panelThongKe1.setBackground(new java.awt.Color(0, 153, 153));

        jPanel4.setBackground(new java.awt.Color(102, 204, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Tổng Doanh Thu Ngày");

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/ngay.png"))); // NOI18N

        lblSumDate.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblSumDate.setForeground(new java.awt.Color(255, 0, 0));
        lblSumDate.setText(" ");

        lblgetDate1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblgetDate1.setText(" ");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lblSumDate, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblgetDate1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblgetDate1)))
                .addGap(18, 18, 18)
                .addComponent(lblSumDate, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(102, 204, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setText("Tổng Đơn Hàng");

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/giohang.png"))); // NOI18N

        lblSumDonHang.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblSumDonHang.setForeground(new java.awt.Color(255, 0, 0));
        lblSumDonHang.setText(" ");

        lblSumHangPanelThongKe.setText(" ");

        lblSumHuyPanelThongKe.setText(" ");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSumHuyPanelThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(lblSumDonHang, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(93, 93, 93)
                        .addComponent(lblSumHangPanelThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(83, 83, 83)
                        .addComponent(jLabel9)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblSumHangPanelThongKe))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblSumDonHang, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblSumHuyPanelThongKe)
                .addGap(15, 15, 15))
        );

        jPanel6.setBackground(new java.awt.Color(102, 204, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setText("Tổng Doanh Thu Tháng");

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/thang.png"))); // NOI18N

        lblSumMonth.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblSumMonth.setForeground(new java.awt.Color(255, 0, 0));
        lblSumMonth.setText(" ");

        lblgetMonth.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblgetMonth.setText(" ");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(lblSumMonth, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(lblgetMonth, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                        .addComponent(jLabel13))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblgetMonth))
                    .addComponent(jLabel13))
                .addGap(18, 18, 18)
                .addComponent(lblSumMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(102, 204, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel14.setText("Tổng Doanh Thu Năm");

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/nam.png"))); // NOI18N

        lblSumYear.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblSumYear.setForeground(new java.awt.Color(255, 0, 0));
        lblSumYear.setText(" ");

        lblGetYear.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblGetYear.setText(" ");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(lblGetYear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel17))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(lblSumYear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblGetYear))
                    .addComponent(jLabel17))
                .addGap(18, 18, 18)
                .addComponent(lblSumYear, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(0, 153, 153));

        tblDoanhThu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tháng", "Tổng Số Sản Phẩm", "Tổng Giá Bán", "Tổng Giá Giảm", "Doanh Thu"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDoanhThu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDoanhThuMouseClicked(evt);
            }
        });
        jScrollPane18.setViewportView(tblDoanhThu);

        jLabel89.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel89.setText("Năm");

        cbbNamDoanhThu.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cbbNamDoanhThu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbbNamDoanhThuMouseClicked(evt);
            }
        });
        cbbNamDoanhThu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbNamDoanhThuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel89)
                .addGap(18, 18, 18)
                .addComponent(cbbNamDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane18, javax.swing.GroupLayout.DEFAULT_SIZE, 1055, Short.MAX_VALUE)
                .addGap(46, 46, 46))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel89)
                    .addComponent(cbbNamDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane18, javax.swing.GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Doanh Thu", jPanel8);

        jPanel9.setBackground(new java.awt.Color(246, 248, 250));

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã SP", "Tên SP", "DT Bình Xăng", "DT Xi Lanh", "Màu Sắc", "Xuất Xứ", "Loại Xe", "Số Lượng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane19.setViewportView(tblSanPham);

        btnXuatFileExcelPanelThongKe.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnXuatFileExcelPanelThongKe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/excel.png"))); // NOI18N
        btnXuatFileExcelPanelThongKe.setText("Xuất File Excel");
        btnXuatFileExcelPanelThongKe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatFileExcelPanelThongKeActionPerformed(evt);
            }
        });

        jLabel87.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel87.setText("Ngày Bắt Đầu");

        jLabel88.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel88.setText("Ngày Kết Thúc");

        jPanel10.setBackground(new java.awt.Color(246, 248, 250));

        javax.swing.GroupLayout jpnThongKeLayout = new javax.swing.GroupLayout(jpnThongKe);
        jpnThongKe.setLayout(jpnThongKeLayout);
        jpnThongKeLayout.setHorizontalGroup(
            jpnThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1275, Short.MAX_VALUE)
        );
        jpnThongKeLayout.setVerticalGroup(
            jpnThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 443, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpnThongKe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpnThongKe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnTimKiemPanelThongKe.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnTimKiemPanelThongKe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/search.png"))); // NOI18N
        btnTimKiemPanelThongKe.setText("Tìm Kiếm");
        btnTimKiemPanelThongKe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemPanelThongKeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jScrollPane19, javax.swing.GroupLayout.DEFAULT_SIZE, 1281, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnXuatFileExcelPanelThongKe)
                .addGap(60, 60, 60))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(321, 321, 321)
                .addComponent(jLabel87)
                .addGap(18, 18, 18)
                .addComponent(txtNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(jLabel88)
                .addGap(18, 18, 18)
                .addComponent(txtNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(btnTimKiemPanelThongKe)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel9Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel87)
                        .addComponent(txtNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel88)
                        .addComponent(txtNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnTimKiemPanelThongKe, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnXuatFileExcelPanelThongKe)
                .addGap(19, 19, 19))
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel9Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Sản Phẩm", jPanel9);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/refresh (4).png"))); // NOI18N
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelThongKe1Layout = new javax.swing.GroupLayout(panelThongKe1);
        panelThongKe1.setLayout(panelThongKe1Layout);
        panelThongKe1Layout.setHorizontalGroup(
            panelThongKe1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelThongKe1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelThongKe1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator7)
                    .addComponent(jSeparator5)
                    .addGroup(panelThongKe1Layout.createSequentialGroup()
                        .addGroup(panelThongKe1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelThongKe1Layout.createSequentialGroup()
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24)
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1287, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1))
                        .addGap(0, 308, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelThongKe1Layout.setVerticalGroup(
            panelThongKe1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelThongKe1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelThongKe1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addGap(35, 35, 35)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1619, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panelThongKe1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panelThongKe1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblDoanhThuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDoanhThuMouseClicked
        int row = tblDoanhThu.getSelectedRow();
        lblgetMonth.setText(tblDoanhThu.getValueAt(row, 0).toString());
        lblSumMonth.setText(tblDoanhThu.getValueAt(row, 4).toString() + ".VND");
    }//GEN-LAST:event_tblDoanhThuMouseClicked

    private void cbbNamDoanhThuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbbNamDoanhThuMouseClicked

    }//GEN-LAST:event_cbbNamDoanhThuMouseClicked

    private void cbbNamDoanhThuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbNamDoanhThuActionPerformed
        int year = (int) cbbNamDoanhThu.getSelectedItem();
        loadDataDoanhThu(ctHoaDonServies.getListDoanhThu(String.valueOf(year), 0));

        int selectedIndex = cbbNamDoanhThu.getSelectedIndex();
        Object selectedItem = cbbNamDoanhThu.getItemAt(selectedIndex);
        String selectedYear = selectedItem.toString();
        lblGetYear.setText(selectedYear);
        lblSumYear.setText(sum(ctHoaDonServies.sumYear(selectedYear, 0)));
        
        

    }//GEN-LAST:event_cbbNamDoanhThuActionPerformed

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked

    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void btnXuatFileExcelPanelThongKeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatFileExcelPanelThongKeActionPerformed
        try {
            String ngayBatDau = ((JTextField) txtNgayBatDau.getDateEditor().getUiComponent()).getText();
            String ngayKetThuc = ((JTextField) txtNgayKetThuc.getDateEditor().getUiComponent()).getText();
            exportExcel.excel(ctHoaDonServies.getListThongKe(ngayBatDau, ngayKetThuc, 0), "Thống kê ngày " + ngayBatDau + " " + ngayKetThuc);
            JOptionPane.showMessageDialog(this, "Xuất thành công");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnXuatFileExcelPanelThongKeActionPerformed

    private void btnTimKiemPanelThongKeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemPanelThongKeActionPerformed
        String ngayBatDau = ((JTextField) txtNgayBatDau.getDateEditor().getUiComponent()).getText();
        String ngayKetThuc = ((JTextField) txtNgayKetThuc.getDateEditor().getUiComponent()).getText();
        loadDataThongKe(ctHoaDonServies.getListThongKe(ngayBatDau, ngayKetThuc, 0));
    }//GEN-LAST:event_btnTimKiemPanelThongKeActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        addNamDoanhThu(hoaDonServies.getYear());
//        setBieuDoDoanhThu(jpnThongKe);
        lblSumDonHang.setText(String.valueOf(sumHoaDon(hoaDonServies.getList(0))) + " Đơn Hàng");
        lblSumDate.setText(sum(ctHoaDonServies.sumDate(getNgayHienTai(), 0)));
        lblSumMonth.setText(sum(ctHoaDonServies.sumMonth(getMonth(), 0)));
        lblSumYear.setText(sum(ctHoaDonServies.sumYear(getYear(), 0)));
        
        
        
int year = (int) cbbNamDoanhThu.getSelectedItem();
        loadDataDoanhThu(ctHoaDonServies.getListDoanhThu(String.valueOf(year), 0));
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTimKiemPanelThongKe;
    private javax.swing.JButton btnXuatFileExcelPanelThongKe;
    private javax.swing.JComboBox<String> cbbNamDoanhThu;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel jpnThongKe;
    private javax.swing.JLabel lblGetYear;
    private javax.swing.JLabel lblSumDate;
    private javax.swing.JLabel lblSumDonHang;
    private javax.swing.JLabel lblSumHangPanelThongKe;
    private javax.swing.JLabel lblSumHuyPanelThongKe;
    private javax.swing.JLabel lblSumMonth;
    private javax.swing.JLabel lblSumYear;
    private javax.swing.JLabel lblgetDate1;
    private javax.swing.JLabel lblgetMonth;
    private javax.swing.JPanel panelThongKe1;
    private javax.swing.JTable tblDoanhThu;
    private javax.swing.JTable tblSanPham;
    private com.toedter.calendar.JDateChooser txtNgayBatDau;
    private com.toedter.calendar.JDateChooser txtNgayKetThuc;
    // End of variables declaration//GEN-END:variables
}
