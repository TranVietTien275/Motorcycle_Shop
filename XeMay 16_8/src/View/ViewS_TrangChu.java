/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import DomainModel.D_Accoun;
import DomainModel.D_KhachHang;
import DomainModel.D_SanPhamBig;
import DomainModel.D_SoKhung;
import Service.AccounService;
import Service.HoaDonServies;
import Service.IKhuyenMaiService;
import Service.KhachHangService;
import Service.NhanVienService;
import Service.SanPhamService;
import Service.TaoHDCTServices;
import Service.TaoHoaDonServices;
import ServiceImpl.AccounImpl;
import ServiceImpl.CTHoaDonImpl;
import ServiceImpl.CTSanPhamImpl;
import ServiceImpl.HoadonServicesImpl;
import ServiceImpl.KhachHangImpl;
import ServiceImpl.KhuyenMaiServiceImpl;
import ServiceImpl.NhanVienImpl;
import ServiceImpl.SanPhamImpl;
import ServiceImpl.TaoHDCTServicesImpl;
import ServiceImpl.TaoHoaDonServicesImpl;
import ServiceImpl.little.SoKhungImpl;
import View.Little.View_SoKhungBanHang;

import ViewModel.CTHD_Table;
import ViewModel.GioHangViewModel;
import ViewModel.HD_LoadTable;
import ViewModel.HoaDonMODEL;
import ViewModel.KhuyenMai_View;
import ViewModel.NhanVienModels;
import ViewModel.TaoHDCTMODEL;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author VietTien
 */
public class ViewS_TrangChu extends javax.swing.JFrame {

    public static ViewS_TrangChu Instance;
    public String soKhung;
    public String soMay;

    private final CTSanPhamImpl S_ctsp;
    DefaultTableModel dtmhd = new DefaultTableModel();
    private HoadonServicesImpl hoaDonServicesImpl = new HoadonServicesImpl();
    private TaoHoaDonServices rs = new TaoHoaDonServicesImpl();
    KhachHangService rskh = new KhachHangImpl();
    SanPhamService rssp = new SanPhamImpl();
    HoaDonServies rshd = new HoadonServicesImpl();
    List<String> b = new ArrayList<>();//tạo ds chứa số khung
    List<String> idsk = new ArrayList<>();
    List<String> idsp = new ArrayList<>();
    List<String> updatesk = new ArrayList<>();
    private final SoKhungImpl s_Sk;
    AccounService rsac = new AccounImpl();
    NhanVienService rsnv = new NhanVienImpl();
    IKhuyenMaiService rskm = new KhuyenMaiServiceImpl();
    private int count = 0;
    private ArrayList<GioHangViewModel> getGioHang = new ArrayList<>();
    private TaoHDCTServices hdctrs = new TaoHDCTServicesImpl();
    private CTHoaDonImpl cTHoaDonImpl = new CTHoaDonImpl();

    String sks = "";

    //chuyen trang
    private View_SanPhamBig view_SanPhamBig;
    private View_KhuyenMai view_KhuyenMai;
    private View_KhachHang view_KhachHang;
    private View_NhanVien view_NhanVien;
    private View_HoaDon_Paid view_HoaDon_Paid;
    private View_ThongKe view_ThongKe;
    private View_Khac view_Khac;
    private View_QuenMatKhau viewQuenMatKhau;

    public ViewS_TrangChu() {
        initComponents();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        //chuyen trang
        view_SanPhamBig = new View_SanPhamBig();
        view_KhuyenMai = new View_KhuyenMai();
        view_KhachHang = new View_KhachHang();
        view_NhanVien = new View_NhanVien();
        view_HoaDon_Paid = new View_HoaDon_Paid();
        view_ThongKe = new View_ThongKe();
        view_Khac = new View_Khac();

        txtMaHD.setEditable(false);
//        rdothanhtoanngay.setSelected(true);
        Instance = this;
        soKhung = "";

        this.S_ctsp = new CTSanPhamImpl();
        this.s_Sk = new SoKhungImpl();
        this.loadData(S_ctsp.getAllSanPham3());
        soKhung = "";
        soMay = "";
        
        loadKH(S_ctsp.getAllKH());
        loadKM(S_ctsp.getAllKM());
        loadDataDonHangCho(hoaDonServicesImpl.getAllHoaDonCho());

    }

    public ViewS_TrangChu(String User, String password) {
        initComponents();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        //chuyen trang
        view_SanPhamBig = new View_SanPhamBig();
        view_KhuyenMai = new View_KhuyenMai();
        view_KhachHang = new View_KhachHang();
        view_NhanVien = new View_NhanVien();
        view_HoaDon_Paid = new View_HoaDon_Paid();
        view_ThongKe = new View_ThongKe();
        //
        txtMaHD.setEditable(false); // Ngăn người dùng nhập dữ liệu
//        rdothanhtoanngay.setSelected(true);
        Instance = this;
        soKhung = "";
        soMay = "";

        D_Accoun ac = new D_Accoun();
        ac = rsac.getListUser(User);
        NhanVienModels nv = new NhanVienModels();
        nv = rsnv.getLisstma(ac.getIdtk());
        txtmanv.setText(nv.getMaNV());
//        this.setLocationRelativeTo(null);
        this.S_ctsp = new CTSanPhamImpl();
        loadKH(S_ctsp.getAllKH());
        loadKM(S_ctsp.getAllKM());
        this.s_Sk = new SoKhungImpl();
        this.loadData(S_ctsp.getAllSanPham3());
        loadDataDonHangCho(hoaDonServicesImpl.getAllHoaDonCho());

    }
    String sok = null;
    String som = null;

    public ViewS_TrangChu(D_SoKhung x) {
        initComponents();

        txtMaHD.setEditable(false);
//        rdothanhtoanngay.setSelected(true);
        Instance = this;
        soKhung = "";
        this.S_ctsp = new CTSanPhamImpl();
        this.s_Sk = new SoKhungImpl();

        sok = x.getSoKhung();
        som = x.getSomay();

        loadKH(S_ctsp.getAllKH());
        loadKM(S_ctsp.getAllKM());
        loadDataDonHangCho(hoaDonServicesImpl.getAllHoaDonCho());
    }

    private ArrayList<GioHangViewModel> tempGioHang = new ArrayList<>();

    public void addGioHang() {
        int row = tlbBangSanPham.getSelectedRow();
        if (row == -1) {
            return;
        }

        GioHangViewModel gioHang = new GioHangViewModel();
        gioHang.setMaSP(tlbBangSanPham.getModel().getValueAt(row, 1).toString());
        gioHang.setTenSP(tlbBangSanPham.getValueAt(row, 2).toString());
        gioHang.setMauSac(tlbBangSanPham.getValueAt(row, 3).toString());
        gioHang.setGiaBan(Float.parseFloat(tlbBangSanPham.getValueAt(row, 4).toString()));
        gioHang.setTongTien(Float.parseFloat(tlbBangSanPham.getValueAt(row, 4).toString()));
        gioHang.setSoKhung(soKhung);
        gioHang.setSoMay(soMay);

        // Thêm dòng dữ liệu vào danh sách tạm thời
        tempGioHang.add(gioHang);

        // Cập nhật giao diện bảng giỏ hàng tạm thời
        loadDataGioHang(tempGioHang);

        // Thêm tất cả dữ liệu từ danh sách tạm thời vào danh sách chính và cập nhật giao diện bảng giỏ hàng chính
        getGioHang.addAll(tempGioHang);
        tempGioHang.clear();
        loadDataGioHang(getGioHang);
    }
    public void loadDataDonHangCho(ArrayList<HD_LoadTable> ctsp) {

        DefaultTableModel defaultTbalemode1 = (DefaultTableModel) tlbHoaDonCho.getModel();
        defaultTbalemode1.setRowCount(0);
        TableColumnModel columnModel = tlbHoaDonCho.getColumnModel();
        for (int col = 5; col <= 6; col++) {
            columnModel.getColumn(col).setCellRenderer(new View.CurrencyRenderer());
        }
        int stt = 1;
        for (HD_LoadTable x : ctsp) {

            defaultTbalemode1.addRow(new Object[]{
                x.getIdHoaDon(),
                stt,
                x.getMaHd(),
                x.getTenKH(),
                x.getMaKhuyenMai(),
                x.getTongTienHang(),
                x.getTienPhaiTra(),});
            stt++;
        }
    }

    public void loadData(ArrayList<D_SanPhamBig> ctsp) {

        DefaultTableModel defaultTbalemode = (DefaultTableModel) tlbBangSanPham.getModel();
        defaultTbalemode.setRowCount(0);
        TableColumnModel columnModel = tlbBangSanPham.getColumnModel();
        for (int col = 4; col <= 4; col++) {
            columnModel.getColumn(col).setCellRenderer(new View.CurrencyRenderer());
        }
        int stt = 1;
        for (D_SanPhamBig x : ctsp) {

            Object[] row = {
                stt,
                x.getMaSP(),
                x.getTenSP(),
                x.getMau().getMau(),
                x.getGiaBan(),
                x.getSoLuong(),
                x.getIdSP(),
                x.getGiaBan(),};
            defaultTbalemode.addRow(row);
            stt++;
        }

    }

    public void loadDataGioHang(ArrayList<GioHangViewModel> getlist) {
        DefaultTableModel defaultTableModel = (DefaultTableModel) tlbBangGioHang.getModel();
        defaultTableModel.setRowCount(0);
        TableColumnModel columnModel = tlbBangGioHang.getColumnModel();
        for (int col = 4; col <= 4; col++) {
            columnModel.getColumn(col).setCellRenderer(new View.CurrencyRenderer());
        }
        for (int col = 6; col <= 6; col++) {
            columnModel.getColumn(col).setCellRenderer(new View.CurrencyRenderer());
        }
        int stt = 1;
        double tong = 0;

        for (GioHangViewModel gh : getlist) {
            defaultTableModel.addRow(new Object[]{
                stt,
                gh.getMaSP(),
                gh.getTenSP(),
                gh.getMauSac(),
                gh.getGiaBan(),
                gh.getSoKhung(),
                gh.getSoMay(),});
            stt++;
//            tong += gh.tongTien();

        }
//        txtTongTien.setText("" + tong);

    }

    public void loadKH(ArrayList<String> list) {
        cbbokh.removeAllItems();
        DefaultComboBoxModel defaultComBoboxModel = (DefaultComboBoxModel) cbbokh.getModel();
        for (String string : list) {
            defaultComBoboxModel.addElement(string);
        }

    }

    public void loadKM(ArrayList<String> list) {
        cbogiamGia.removeAllItems();
        DefaultComboBoxModel defaultComBoboxModel = (DefaultComboBoxModel) cbogiamGia.getModel();

        for (String string : list) {
            defaultComBoboxModel.addElement(string);
        }

    }

    public void loadDataHDCTtoGioHang(List<CTHD_Table> ctsp) {
        DefaultTableModel defaultTableModel = (DefaultTableModel) tlbBangGioHang.getModel();
        defaultTableModel.setRowCount(0);
        int stt = 1;
        TableColumnModel columnModel = tlbBangGioHang.getColumnModel();
        for (int col = 5; col <= 5; col++) {
            columnModel.getColumn(col).setCellRenderer(new View.CurrencyRenderer());
        }

        for (CTHD_Table x : ctsp) {
            defaultTableModel.addRow(new Object[]{
                stt,
                x.getMaSP(),
                x.getTenSP(),
                x.getNamSX(),
                x.getMauSac(),
                x.getGiaBan(),
                x.getSoKhung(),
                x.getSoMay(),});
            stt++;
        }
    }

    public void tinhSoTienDu() {
//        try {
//        
//            txttienkhachdua.addKeyListener(new KeyAdapter() {
//                public void keyReleased(KeyEvent e) {
//                   
//                    
//                    float soTienKhachTra = Float.valueOf(txttienkhachdua.getText());
//                    String soTienCanTraString = txttienphaitra.getText();
//
//                    DecimalFormat decimalFormat = new DecimalFormat("#,###.##");
//    
//                    
//                    float value;
//                    
//                    try {
//                        value = Float.parseFloat(decimalFormat.parse(soTienCanTraString).toString());
//                        float soTienDu = soTienKhachTra - value;
//                        String temp = rshd.dinhDangTien(soTienDu);
//                        txttienthua.setText(temp);
//                    } catch (Exception ex) {
//                    }
//
//                }
//            });                        
//                
//        } catch (Exception e) {
//        }

try {
    txttienkhachdua.addKeyListener(new KeyAdapter() {
        public void keyReleased(KeyEvent e) {
            try {
                float soTienKhachTra = Float.valueOf(txttienkhachdua.getText());
                String soTienCanTraString = txttienphaitra.getText();

                DecimalFormat decimalFormat = new DecimalFormat("#,###.##");

                float value;

                try {
                    value = Float.parseFloat(decimalFormat.parse(soTienCanTraString).toString());
                    float soTienDu = soTienKhachTra - value;
                    String temp = rshd.dinhDangTien(soTienDu);
                    txttienthua.setText(temp);
                } catch (Exception ex) {
                    // Xử lý ngoại lệ mà không in ra lỗi
                }
            } catch (NumberFormatException ex) {
                // Xử lý ngoại lệ mà không in ra lỗi
            }
        }
    });
} catch (Exception e) {
    // Xử lý ngoại lệ mà không in ra lỗi
}
        
    }

    public HoaDonMODEL taohd() throws ParseException {

        DecimalFormat df = new DecimalFormat("#,###.##");
        float floatTienHang = Float.parseFloat(df.parse(txttongtienhang.getText()).toString());
        float floatTongThanhToan = Float.parseFloat(df.parse(txttienphaitra.getText()).toString());
        float floattienkhachdua = Float.parseFloat(df.parse(txttienkhachdua.getText()).toString());
        float floatTienTraLai = Float.parseFloat(df.parse(txttienthua.getText()).toString());
        HoaDonMODEL h = new HoaDonMODEL();
        h.setMaHd(txtMaHD.getText());
        h.setNgayTao(txtngaytao.getDate());
        h.setTienKhachDua(floattienkhachdua);
        h.setTienPhaiTra(floatTongThanhToan);
        h.setTienThua(floatTienTraLai);
        h.setTongTienHang(floatTienHang);
        if (rdochothanhtoan.isSelected() == true) {
            h.setTrangThai(1);
        } else if (rdothanhtoanngay.isSelected() == true) {
            h.setTrangThai(0);
        } else {
            h.setTrangThai(2);
        }

        return h;
    }

    public HoaDonMODEL taohdcho() throws ParseException {

        DecimalFormat df = new DecimalFormat("#,###.##");
        float floatTienHang = Float.parseFloat(df.parse(txttongtienhang.getText()).toString());
        float floatTongThanhToan = Float.parseFloat(df.parse(txttienphaitra.getText()).toString());

        HoaDonMODEL h = new HoaDonMODEL();
        h.setMaHd(txtMaHD.getText());
        h.setNgayTao(txtngaytao.getDate());

        h.setTienPhaiTra(floatTongThanhToan);

        h.setTongTienHang(floatTienHang);
        if (rdochothanhtoan.isSelected() == true) {
            h.setTrangThai(1);
        } else if (rdothanhtoanngay.isSelected() == true) {
            h.setTrangThai(0);
        } else {
            h.setTrangThai(2);
        }

        return h;
    }

    public HoaDonMODEL suahd() throws ParseException {

        DecimalFormat df = new DecimalFormat("#,###.##");
        float floatTienHang = Float.parseFloat(df.parse(txttongtienhang.getText()).toString());
        float floatTongThanhToan = Float.parseFloat(df.parse(txttienphaitra.getText()).toString());
        float floattienkhachdua = Float.parseFloat(df.parse(txttienkhachdua.getText()).toString());
        float floatTienTraLai = Float.parseFloat(df.parse(txttienthua.getText()).toString());
        HoaDonMODEL h = new HoaDonMODEL();
        h.setNgayTao(txtngaytao.getDate());
        h.setTienKhachDua(floattienkhachdua);
        h.setTienPhaiTra(floatTongThanhToan);
        h.setTienThua(floatTienTraLai);
        h.setTongTienHang(floatTienHang);
        rdothanhtoanngay.setSelected(true);

        return h;
    }

    public HoaDonMODEL taohdhuydonhang() throws ParseException {

        DecimalFormat df = new DecimalFormat("#,###.##");
        float floatTienHang = Float.parseFloat(df.parse(txttongtienhang.getText()).toString());
        float floatTongThanhToan = Float.parseFloat(df.parse(txttienphaitra.getText()).toString());
        float floatTienTraLai = Float.parseFloat(df.parse(txttienthua.getText()).toString());
        HoaDonMODEL h = new HoaDonMODEL();
        h.setMaHd(txtMaHD.getText());
        h.setNgayTao(txtngaytao.getDate());
        h.setTienThua(floatTienTraLai);
        h.setTongTienHang(floatTienHang);
        if (rdochothanhtoan.isSelected() == true) {
            h.setTrangThai(1);
        } else if (rdothanhtoanngay.isSelected() == true) {
            h.setTrangThai(0);
        } else {
            h.setTrangThai(2);
        }

        return h;
    }

    public boolean checkForm() {
        if (txtmanv.getText().equals("................")) {

            JOptionPane.showMessageDialog(this,
                    "Không thể thanh toán nếu không có mã nhân viên",
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (txttienkhachdua.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(this,
                    "Không được bỏ trống tiền khách đưa",
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
//        if (rdoDonHangBiHuy.isSelected()) {
//            JOptionPane.showMessageDialog(this,
//                    "Nếu bạn muốn hủy đơn thì bạn nhấn nút Hủy Đơn Hàng bên phải ",
//                    "Lỗi",
//                    JOptionPane.ERROR_MESSAGE);
//            return false;
//        }
        // Kiểm tra Dung Tích Xi Lanh có phải số hợp lệ hay không
        try {
            int giaBan = Integer.parseInt(txttienkhachdua.getText());

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Có lỗi xẩy ra",
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        try {
            String tienThuaText = txttienthua.getText().replaceAll("\\.", ""); // Loại bỏ dấu phẩy
            double tienThua = Double.parseDouble(tienThuaText);

            if (tienThua < 0) {
                JOptionPane.showMessageDialog(this,
                        "Không hợp lệ, Tiền khách đưa bị thiếu",
                        "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Không hợp lệ , Tiền khách đưa bị thiếu",
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    public boolean checkFormthanhtoanhoadoncho() {
        if (txtmanv.getText().equals("................")) {

            JOptionPane.showMessageDialog(this,
                    "Không thể thanh toán nếu không có mã nhân viên",
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
//        if (txttienkhachdua.getText().trim().isEmpty()) {
//
//            JOptionPane.showMessageDialog(this,
//                    "Không được bỏ trống tiền khách đưa",
//                    "Lỗi",
//                    JOptionPane.ERROR_MESSAGE);
//            return false;
//        }
        if (rdochothanhtoan.isSelected() //                || rdoDonHangBiHuy.isSelected()
                ) {
            JOptionPane.showMessageDialog(this,
                    "Cần Chọn thanh toán ngay ",
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

//        try {
//            double giaBan = Integer.parseInt(txttienkhachdua.getText());
//
//        } catch (NumberFormatException e) {
//            JOptionPane.showMessageDialog(this,
//                    "Có lỗi xẩy ra",
//                    "Lỗi",
//                    JOptionPane.ERROR_MESSAGE);
//            return false;
//        }
        try {
            String tienThuaText = txttienthua.getText().replaceAll("\\.", ""); // Loại bỏ dấu phẩy
            double tienThua = Double.parseDouble(tienThuaText);

            if (tienThua < 0) {
                JOptionPane.showMessageDialog(this,
                        "Không hợp lệ, Tiền khách đưa bị thiếu",
                        "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Không hợp lệ , Tiền khách đưa bị thiếu",
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    public boolean checkFormHuyDonHang() {
        if (txtmanv.getText().equals("................")) {

            JOptionPane.showMessageDialog(this,
                    "không có mã nhân viên",
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (rdothanhtoanngay.isSelected() || rdochothanhtoan.isSelected()) {
            JOptionPane.showMessageDialog(this,
                    "Không Hợp Lệ",
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public void clearDonHang() {
        DefaultTableModel defaultTableModel22 = (DefaultTableModel) tlbBangGioHang.getModel();
        defaultTableModel22.setRowCount(0);
    }

    public void clearForm() {
        txtMaHD.setText("");
        txttienkhachdua.setText("");
        txttienphaitra.setText("");
        txttienthua.setText("");
        txttongtienhang.setText("");
//        rdothanhtoanngay.isSelected();
        txtngaytao.setDate(null);

    }

    //chuyentrang
    public void setPanel(JComponent com) {
        this.jPaneTrangChu.removeAll();
        this.jPaneTrangChu.setLayout(new BorderLayout());
        this.jPaneTrangChu.add(com);
        this.jPaneTrangChu.validate();
        this.jPaneTrangChu.repaint();
        this.jPaneTrangChu.revalidate();

    }

// if (shouldNavigateToHome) {
//        this.jPaneTrangChu.add(view_SanPhamBig);
//        shouldNavigateToHome = false; // Đặt lại biến để ngăn việc quay trở lại lần tiếp theo
//    }
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
        jPanel3 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        MenuBanHang = new javax.swing.JButton();
        jButton64 = new javax.swing.JButton();
        menuSanPham = new javax.swing.JButton();
        jButton66 = new javax.swing.JButton();
        jButton67 = new javax.swing.JButton();
        jPanel19 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();
        jButton69 = new javax.swing.JButton();
        jButton70 = new javax.swing.JButton();
        jButton71 = new javax.swing.JButton();
        jPaneTrangChu = new javax.swing.JPanel();
        txtid = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tlbBangSanPham = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        txtTimKiemSP = new javax.swing.JTextField();
        txtTimKiem = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tlbBangGioHang = new javax.swing.JTable();
        btnXoaKhoiGioHang = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tlbHoaDonCho = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        cbogiamGia = new javax.swing.JComboBox<>();
        txtTiengiamgia = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        txtMaHD = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        cbbokh = new javax.swing.JComboBox<>();
        txtSoDtKH = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        txtTimKiemTenKhachHang = new javax.swing.JTextField();
        jButton9 = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        txtmanv = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        rdothanhtoanngay = new javax.swing.JRadioButton();
        rdochothanhtoan = new javax.swing.JRadioButton();
        txttienkhachdua = new javax.swing.JTextField();
        txttongtienhang = new javax.swing.JLabel();
        txttienthua = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        txtngaytao = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        Update = new javax.swing.JButton();
        rdoHuy = new javax.swing.JRadioButton();
        txttienphaitra = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));
        jPanel1.setPreferredSize(new java.awt.Dimension(1491, 748));

        jPanel3.setBackground(new java.awt.Color(0, 153, 153));
        jPanel3.setPreferredSize(new java.awt.Dimension(1491, 748));
        jPanel3.setRequestFocusEnabled(false);

        jPanel14.setBackground(new java.awt.Color(0, 153, 153));

        MenuBanHang.setBackground(new java.awt.Color(0, 153, 153));
        MenuBanHang.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        MenuBanHang.setForeground(new java.awt.Color(255, 255, 255));
        MenuBanHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imegas/Home.png"))); // NOI18N
        MenuBanHang.setText("    QL Bán Hàng");
        MenuBanHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuBanHangActionPerformed(evt);
            }
        });

        jButton64.setBackground(new java.awt.Color(0, 153, 153));
        jButton64.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton64.setForeground(new java.awt.Color(255, 255, 255));
        jButton64.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/khachhang.png"))); // NOI18N
        jButton64.setText("   QL Nhân Viên");
        jButton64.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton64ActionPerformed(evt);
            }
        });

        menuSanPham.setBackground(new java.awt.Color(0, 153, 153));
        menuSanPham.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        menuSanPham.setForeground(new java.awt.Color(255, 255, 255));
        menuSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/sanpham.png"))); // NOI18N
        menuSanPham.setText("    QL Sản Phẩm");
        menuSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuSanPhamMouseClicked(evt);
            }
        });
        menuSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSanPhamActionPerformed(evt);
            }
        });

        jButton66.setBackground(new java.awt.Color(0, 153, 153));
        jButton66.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton66.setForeground(new java.awt.Color(255, 255, 255));
        jButton66.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/nhanvien.png"))); // NOI18N
        jButton66.setText("QL Khách Hàng");
        jButton66.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton66ActionPerformed(evt);
            }
        });

        jButton67.setBackground(new java.awt.Color(0, 153, 153));
        jButton67.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton67.setForeground(new java.awt.Color(255, 255, 255));
        jButton67.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/lichsu.png"))); // NOI18N
        jButton67.setText("       Khuyến Mãi");
        jButton67.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton67ActionPerformed(evt);
            }
        });

        jPanel19.setBackground(new java.awt.Color(0, 204, 204));
        jPanel19.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 153, 153));
        jLabel16.setText("QUẢN LÝ BÁN XE MÁY");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel16)
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel16)
                .addContainerGap())
        );

        jButton11.setBackground(new java.awt.Color(51, 51, 51));
        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/channels4_profile.jpg"))); // NOI18N

        jButton69.setBackground(new java.awt.Color(0, 153, 153));
        jButton69.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton69.setForeground(new java.awt.Color(255, 255, 255));
        jButton69.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/hoadon.png"))); // NOI18N
        jButton69.setText("Lịch Sử Hóa Đơn");
        jButton69.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton69ActionPerformed(evt);
            }
        });

        jButton70.setBackground(new java.awt.Color(0, 153, 153));
        jButton70.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton70.setForeground(new java.awt.Color(255, 255, 255));
        jButton70.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/file-invoice-dollar (1).png"))); // NOI18N
        jButton70.setText("           Thống Kê");
        jButton70.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton70ActionPerformed(evt);
            }
        });

        jButton71.setBackground(new java.awt.Color(0, 153, 153));
        jButton71.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton71.setForeground(new java.awt.Color(255, 255, 255));
        jButton71.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/dangnhap.png"))); // NOI18N
        jButton71.setText("          Đăng Xuất");
        jButton71.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton71ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton67, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(MenuBanHang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(menuSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton64, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton69, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton70, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton66, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton71, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(MenuBanHang, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(menuSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton66, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton64, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton67, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton69, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton70, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton71, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPaneTrangChu.setBackground(new java.awt.Color(0, 153, 153));
        jPaneTrangChu.setPreferredSize(new java.awt.Dimension(100, 100));

        txtid.setBackground(new java.awt.Color(0, 153, 153));
        txtid.setPreferredSize(new java.awt.Dimension(1286, 740));

        jPanel4.setBackground(new java.awt.Color(0, 153, 153));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 255, 255)));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sản Phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        tlbBangSanPham.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tlbBangSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã Sản Phẩm", "Tên Sản Phẩm", "Màu Sắc", "Giá", "Số Lượng", "Title 7", "Title 8"
            }
        ));
        tlbBangSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tlbBangSanPhamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tlbBangSanPham);
        if (tlbBangSanPham.getColumnModel().getColumnCount() > 0) {
            tlbBangSanPham.getColumnModel().getColumn(6).setMinWidth(0);
            tlbBangSanPham.getColumnModel().getColumn(6).setPreferredWidth(0);
            tlbBangSanPham.getColumnModel().getColumn(6).setMaxWidth(0);
            tlbBangSanPham.getColumnModel().getColumn(7).setMinWidth(0);
            tlbBangSanPham.getColumnModel().getColumn(7).setPreferredWidth(0);
            tlbBangSanPham.getColumnModel().getColumn(7).setMaxWidth(0);
        }

        jPanel5.setBackground(new java.awt.Color(0, 153, 153));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm Kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(0, 51, 204))); // NOI18N

        txtTimKiem.setText("Tìm Kiếm");
        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTimKiemSP, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTimKiem)
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiemSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTimKiem))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton4.setBackground(new java.awt.Color(0, 102, 102));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/update.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 833, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(0, 153, 153));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Giỏ Hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        tlbBangGioHang.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tlbBangGioHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã Sản Phẩm", "Tên Sản Phẩm", "Màu Sắc", "Giá Sản Phẩm", "Số Khung", "Số Máy"
            }
        ));
        tlbBangGioHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tlbBangGioHangMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tlbBangGioHang);

        btnXoaKhoiGioHang.setText("Xóa Khỏi Giỏ Hàng");
        btnXoaKhoiGioHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaKhoiGioHangActionPerformed(evt);
            }
        });

        jButton5.setText("Tạo Hóa Đơn");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 825, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnXoaKhoiGioHang)
                .addGap(40, 40, 40))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoaKhoiGioHang, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(0, 153, 153));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Đơn Hàng Chờ Thanh Toán", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tlbHoaDonCho.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tlbHoaDonCho.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "IdHD", "Stt", "Mã Hóa Đơn", "Tên Khách Hàng", "Mã Giảm Giá", "Tổng Tiền Hàng", "Tiền phải trả"
            }
        ));
        tlbHoaDonCho.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tlbHoaDonChoMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tlbHoaDonCho);
        if (tlbHoaDonCho.getColumnModel().getColumnCount() > 0) {
            tlbHoaDonCho.getColumnModel().getColumn(0).setMinWidth(0);
            tlbHoaDonCho.getColumnModel().getColumn(0).setPreferredWidth(0);
            tlbHoaDonCho.getColumnModel().getColumn(0).setMaxWidth(0);
        }

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 825, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 255)));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Mã Hóa Đơn : ");

        jPanel8.setBackground(new java.awt.Color(0, 153, 153));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Khuyến Mãi", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("Tiền mặt :");

        cbogiamGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbogiamGiaActionPerformed(evt);
            }
        });

        txtTiengiamgia.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtTiengiamgia.setForeground(new java.awt.Color(0, 51, 153));
        txtTiengiamgia.setText("0");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Mã khuyến mãi :");

        jLabel5.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel5.setText("VND");

        jButton8.setBackground(new java.awt.Color(0, 102, 102));
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/update.png"))); // NOI18N
        jButton8.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel10))
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(txtTiengiamgia, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addGap(68, 68, 68))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(cbogiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbogiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(13, 13, 13)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtTiengiamgia)
                    .addComponent(jLabel5))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel9.setBackground(new java.awt.Color(0, 153, 153));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Khách Hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setText("Tên Khách");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setText("SĐT");

        cbbokh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbbokhMouseClicked(evt);
            }
        });
        cbbokh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbokhActionPerformed(evt);
            }
        });

        txtSoDtKH.setText("0");

        jButton7.setBackground(new java.awt.Color(0, 102, 102));
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/update.png"))); // NOI18N
        jButton7.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton7MouseClicked(evt);
            }
        });
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton9.setText("Tìm Kiếm");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(53, 53, 53)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(txtSoDtKH, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cbbokh, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(txtTimKiemTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton9)))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiemTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbbokh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(txtSoDtKH))))
                .addContainerGap())
        );

        jPanel10.setBackground(new java.awt.Color(0, 153, 153));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nhân Viên", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel18.setText("Mã Nhân Viên ");

        txtmanv.setText("................");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18)
                .addGap(30, 30, 30)
                .addComponent(txtmanv, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtmanv))
                .addGap(0, 14, Short.MAX_VALUE))
        );

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Tổng Tiền Hàng :");

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel21.setText("Số Tiền Phải Trả :");

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel22.setText("Tiền Thừa :");

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel23.setText("Tiền Khách Đưa :");

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel25.setText("Trạng Thái :");

        buttonGroup1.add(rdothanhtoanngay);
        rdothanhtoanngay.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rdothanhtoanngay.setText("Thanh Toán Ngay ");

        buttonGroup1.add(rdochothanhtoan);
        rdochothanhtoan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rdochothanhtoan.setText("Chờ Thanh Toán");

        txttienkhachdua.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txttienkhachdua.setForeground(new java.awt.Color(0, 51, 153));
        txttienkhachdua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttienkhachduaActionPerformed(evt);
            }
        });
        txttienkhachdua.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txttienkhachduaKeyReleased(evt);
            }
        });

        txttongtienhang.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txttongtienhang.setForeground(new java.awt.Color(0, 0, 153));
        txttongtienhang.setText("0");

        txttienthua.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txttienthua.setForeground(new java.awt.Color(51, 0, 102));
        txttienthua.setText("0");

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel26.setText("Ngày Tạo :");

        jLabel3.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel3.setText("VND");

        jLabel6.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel6.setText("VND");

        jLabel7.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel7.setText("VND");

        jButton2.setBackground(new java.awt.Color(204, 255, 255));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/clear.png"))); // NOI18N
        jButton2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(0, 102, 102));
        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton6.setText("Hủy Đơn Hàng");
        jButton6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(0, 102, 102));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton3.setText("Thanh Toán");
        jButton3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        Update.setBackground(new java.awt.Color(0, 102, 102));
        Update.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Update.setText("Thanh Toán Hóa Đơn Chờ");
        Update.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoHuy);
        rdoHuy.setText("jRadioButton1");

        txttienphaitra.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txttienphaitra.setForeground(new java.awt.Color(0, 51, 153));
        txttienphaitra.setText("0");

        jButton1.setBackground(new java.awt.Color(0, 102, 102));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/update.png"))); // NOI18N
        jButton1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(Update, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(txtngaytao, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(rdoHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(rdochothanhtoan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(rdothanhtoanngay, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txttongtienhang, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel3))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(txttienkhachdua))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(txttienthua, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(jLabel7))))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txttienphaitra, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17))))
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txttongtienhang)
                    .addComponent(jLabel3))
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel21)
                        .addComponent(jLabel6)
                        .addComponent(txttienphaitra))
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(txttienkhachdua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(txttienthua)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(rdothanhtoanngay))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rdochothanhtoan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdoHuy)
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel26)
                        .addComponent(txtngaytao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(Update, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(12, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout txtidLayout = new javax.swing.GroupLayout(txtid);
        txtid.setLayout(txtidLayout);
        txtidLayout.setHorizontalGroup(
            txtidLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(txtidLayout.createSequentialGroup()
                .addGroup(txtidLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 394, Short.MAX_VALUE)
                .addContainerGap())
        );
        txtidLayout.setVerticalGroup(
            txtidLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(txtidLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(txtidLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(txtidLayout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(83, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPaneTrangChuLayout = new javax.swing.GroupLayout(jPaneTrangChu);
        jPaneTrangChu.setLayout(jPaneTrangChuLayout);
        jPaneTrangChuLayout.setHorizontalGroup(
            jPaneTrangChuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPaneTrangChuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtid, javax.swing.GroupLayout.DEFAULT_SIZE, 1253, Short.MAX_VALUE))
        );
        jPaneTrangChuLayout.setVerticalGroup(
            jPaneTrangChuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPaneTrangChuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 784, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPaneTrangChu, javax.swing.GroupLayout.DEFAULT_SIZE, 1259, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 796, Short.MAX_VALUE)
                    .addComponent(jPaneTrangChu, javax.swing.GroupLayout.DEFAULT_SIZE, 796, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1530, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 802, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void MenuBanHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuBanHangActionPerformed
        setPanel(txtid);

    }//GEN-LAST:event_MenuBanHangActionPerformed

    private void jButton64ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton64ActionPerformed

       if(txtmanv.getText()==null){
           setPanel(view_NhanVien);
       } else {
                JOptionPane.showMessageDialog(this,"Không quyền đăng nhập");
            }
    }//GEN-LAST:event_jButton64ActionPerformed

    private void menuSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSanPhamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuSanPhamActionPerformed

    private void jButton66ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton66ActionPerformed
        setPanel(view_KhachHang);
    }//GEN-LAST:event_jButton66ActionPerformed

    private void jButton67ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton67ActionPerformed
        setPanel(view_KhuyenMai);
    }//GEN-LAST:event_jButton67ActionPerformed

    private void jButton69ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton69ActionPerformed
        setPanel(view_HoaDon_Paid);
    }//GEN-LAST:event_jButton69ActionPerformed

    private void menuSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuSanPhamMouseClicked
        setPanel(view_SanPhamBig);
    }//GEN-LAST:event_menuSanPhamMouseClicked

    private void jButton70ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton70ActionPerformed
       if(txtmanv.getText()==null){
           setPanel(view_ThongKe);
       } else {
                JOptionPane.showMessageDialog(this,"Không quyền đăng nhập");
            }

    }//GEN-LAST:event_jButton70ActionPerformed

    private void jButton71ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton71ActionPerformed
        int x = JOptionPane.showConfirmDialog(this, "Bạn Chắc Chắn Muốn Đăng Xuất");
        if (x != JOptionPane.YES_OPTION) {
            return;
        }
        View_DangNhap view = new View_DangNhap();
        view.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton71ActionPerformed

    private void tlbBangSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tlbBangSanPhamMouseClicked
        int row = tlbBangSanPham.getSelectedRow();
        if (row == -1) {
            return;
        }
         JOptionPane.showMessageDialog(null,
                "Nếu đây không phải đơn hàng đầu tiên của giỏ hàng , thì bạn lưu ý không chọn số khung đã có ở giỏ hàng",
                "Lưu ý",
                JOptionPane.WARNING_MESSAGE);

        
        
        
        D_SanPhamBig sp = new D_SanPhamBig();
        sp.setMaSP(tlbBangSanPham.getModel().getValueAt(row, 1).toString());

        View_SoKhungBanHang sk = new View_SoKhungBanHang(sp);
        sk.setVisible(true);
        sk.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_tlbBangSanPhamMouseClicked

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        String ma = txtTimKiemSP.getText().trim();
        if (ma.equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập mã sản phẩm hoặc tên sản phẩm cần tìm");
            return;
        }
        DefaultTableModel dtm = (DefaultTableModel) tlbBangSanPham.getModel();
        dtm.setRowCount(0);
        int count = 0;
        int stt = 1;
        for (D_SanPhamBig x : S_ctsp.getAllSanPham3()) {
            if (x.getMaSP().equalsIgnoreCase(ma) || x.getTenSP().equalsIgnoreCase(ma)) {
                Object[] obj = {
                    stt,
                    x.getMaSP(),
                    x.getTenSP(),
                    x.getMau().getMau(),
                    x.getGiaBan(),
                    x.getSoLuong(),
                    x.getIdSP(),
                    x.getGiaBan(),};
                dtm.addRow(obj);
                count = 1;
                stt++;
            }
        }
        if (count
                == 0) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy mã sản phẩm hoặc tên sản phẩm nào như vậy");
        }
    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void tlbBangGioHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tlbBangGioHangMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tlbBangGioHangMouseClicked

    private void btnXoaKhoiGioHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaKhoiGioHangActionPerformed
        int row = tlbBangGioHang.getSelectedRow();
        if (row == -1) {
            return;
        } else {
            getGioHang.remove(row);
            loadDataGioHang(getGioHang);

        }
    }//GEN-LAST:event_btnXoaKhoiGioHangActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        if (getGioHang.size() == 0) {
            JOptionPane.showMessageDialog(this, "Chưa Có Sản Phẩm Nào Trong Giỏ Hàng");
            return;
        }

        // Tạo mã hóa đơn mới mỗi khi nhấn nút
        Random random = new Random();
        int randomNumber = random.nextInt(1000000);
        String formattedNumber = String.format("%06d", randomNumber);
        String maHD = "HD" + formattedNumber;

        txtMaHD.setText(maHD);

        float tong = 0;
        for (GioHangViewModel o : getGioHang) {
            tong += Float.valueOf(o.getTongTien());
        }
        DecimalFormat df = new DecimalFormat("#,###.##");
        float giamGia;
        try {
            giamGia = Float.parseFloat(df.parse(txtTiengiamgia.getText()).toString());
            float soTienCanTra = tong - giamGia;
            String dinhDangSoTienCanTra = rshd.dinhDangTien(soTienCanTra);
            String dinhDangTongTien = rshd.dinhDangTien(tong);
            txttongtienhang.setText(dinhDangTongTien);
            txttienphaitra.setText(dinhDangSoTienCanTra);
            txtngaytao.setDate(new Date());
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void tlbHoaDonChoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tlbHoaDonChoMouseClicked

        int row = tlbHoaDonCho.getSelectedRow();
        if (row == -1) {
            return;
        }

        DecimalFormat df = new DecimalFormat("#,###.##");

        txtMaHD.setText(tlbHoaDonCho.getValueAt(row, 2).toString());
        cbbokh.setSelectedItem(tlbHoaDonCho.getValueAt(row, 3).toString());
        cbogiamGia.setSelectedItem(tlbHoaDonCho.getValueAt(row, 4).toString());
        txtngaytao.setDate(new Date());
        // Lấy dữ liệu số và định dạng lại cho các ô số
        double tongTienHang = Double.parseDouble(tlbHoaDonCho.getValueAt(row, 5).toString());
        double tienPhaiTra = Double.parseDouble(tlbHoaDonCho.getValueAt(row, 6).toString());

        // Định dạng số theo kiểu "1.000.000"
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        txttongtienhang.setText(decimalFormat.format(tongTienHang));
        txttienphaitra.setText(decimalFormat.format(tienPhaiTra));
        rdoHuy.setSelected(true);

        //đẩy hóa đơn chờ lên giỏ hàng
        String idhd = tlbHoaDonCho.getValueAt(row, 0).toString();
        System.out.println(idhd);

        List<CTHD_Table> list = cTHoaDonImpl.getOneCTHD(idhd);
        System.out.println(list.size());
        loadDataHDCTtoGioHang(list);
    }//GEN-LAST:event_tlbHoaDonChoMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        this.loadData(S_ctsp.getAllSanPham3());
    }//GEN-LAST:event_jButton4ActionPerformed

    private void UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateActionPerformed
        if (checkFormthanhtoanhoadoncho()) {
            try {
                String ma = txtMaHD.getText();
                txtngaytao.setDate(new Date());
                String nv = txtmanv.getText();
                HoaDonMODEL hd = suahd();
                rshd.sua(hd, ma, nv);

                loadDataDonHangCho(hoaDonServicesImpl.getAllHoaDonCho());
                JOptionPane.showMessageDialog(null,
                        "Thanh Toán Hóa Đơn chờ thành công",
                        "Lưu ý",
                        JOptionPane.OK_CANCEL_OPTION);

                clearForm();
                clearDonHang();
            } catch (Exception ex) {
                //            Logger.getLogger(View_QLBanHang.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_UpdateActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        if (rdothanhtoanngay.isSelected()) {
            if (checkForm()) {
                try {
                    String kh = cbbokh.getSelectedItem().toString();
                    String nv = txtmanv.getText();
                    String gg = cbogiamGia.getSelectedItem().toString();
                    HoaDonMODEL hd = taohd();
                    rshd.them(hd, kh, nv, gg);
                    for (GioHangViewModel h : getGioHang) {
                        idsk.add(h.getSoKhung()); // lấy số khung đã có trong giỏ hàng
                    }
                    for (GioHangViewModel h : getGioHang) {
                        idsp.add(h.getMaSP()); // lấy số khung đã có trong giỏ hàng
                    }
                    String mahd = txtMaHD.getText();

                    for (int i = 0; i < getGioHang.size(); i++) {
                        TaoHDCTMODEL hdct = new TaoHDCTMODEL();
                        hdctrs.themdhct(hdct, idsp.get(i), mahd, idsk.get(i), cbogiamGia.getSelectedItem().toString());
                    }
                    idsk.clear();
                    idsp.clear();
                    for (GioHangViewModel h : getGioHang) {
                        b.add(h.getSoKhung()); // lấy số khung đã có trong giỏ hàng
                    }
                    for (int i = 0; i < b.size(); i++) {
                        s_Sk.suask(b.get(i));
                    }
                    this.loadData(S_ctsp.getAllSanPham3());
                    JOptionPane.showMessageDialog(this, "Đã Tạo Hóa Đơn Thành Công");
                    loadDataDonHangCho(hoaDonServicesImpl.getAllHoaDonCho());
                    clearForm();
                    clearDonHang();
                    getGioHang.clear();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        } else if (rdochothanhtoan.isSelected()) {
            try {
                String kh = cbbokh.getSelectedItem().toString();
                String nv = txtmanv.getText();
                String gg = cbogiamGia.getSelectedItem().toString();
                HoaDonMODEL hd = taohdcho();
                rshd.addhoadoncho(hd, kh, nv, gg);
                for (GioHangViewModel h : getGioHang) {
                    idsk.add(h.getSoKhung()); // lấy số khung đã có trong giỏ hàng
                }
                for (GioHangViewModel h : getGioHang) {
                    idsp.add(h.getMaSP()); // lấy số khung đã có trong giỏ hàng
                }
                String mahd = txtMaHD.getText();

                for (int i = 0; i < getGioHang.size(); i++) {
                    TaoHDCTMODEL hdct = new TaoHDCTMODEL();
                    hdctrs.themdhct(hdct, idsp.get(i), mahd, idsk.get(i), cbogiamGia.getSelectedItem().toString());
                }
                idsk.clear();
                idsp.clear();
                for (GioHangViewModel h : getGioHang) {
                    b.add(h.getSoKhung()); // lấy số khung đã có trong giỏ hàng
                }
                for (int i = 0; i < b.size(); i++) {
                    s_Sk.suask(b.get(i));
                }
                this.loadData(S_ctsp.getAllSanPham3());
                JOptionPane.showMessageDialog(this, "Đã Tạo Hóa Đơn Thành Công");
                loadDataDonHangCho(hoaDonServicesImpl.getAllHoaDonCho());
                clearForm();
                clearDonHang();
                getGioHang.clear();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        if (checkFormHuyDonHang()) {
            String ma = txtMaHD.getText();
            rshd.sua1(ma);
            this.loadData(S_ctsp.getAllSanPham3());

            JOptionPane.showMessageDialog(this, "Hủy đơn thành công");
            int row = tlbHoaDonCho.getSelectedRow();
            String idhd = tlbHoaDonCho.getValueAt(row, 0).toString();
            System.out.println(idhd);

            List<CTHD_Table> list = cTHoaDonImpl.getOneCTHD(idhd);

            for (CTHD_Table cTHD_Table : list) {
                updatesk.add(cTHD_Table.getSoKhung());
            }
            for (int i = 0; i < updatesk.size(); i++) {
                s_Sk.suask1(updatesk.get(i));
            }

            loadDataDonHangCho(hoaDonServicesImpl.getAllHoaDonCho());
            this.loadData(S_ctsp.getAllSanPham3());

            clearForm();
            clearDonHang();
            getGioHang.clear();
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        clearForm();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txttienkhachduaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttienkhachduaKeyReleased
        // TODO add your handling code here:
        tinhSoTienDu();
    }//GEN-LAST:event_txttienkhachduaKeyReleased

    private void txttienkhachduaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttienkhachduaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttienkhachduaActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton7MouseClicked
        loadKH(S_ctsp.getAllKH());

    }//GEN-LAST:event_jButton7MouseClicked

    private void cbbokhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbokhActionPerformed
        // TODO add your handling code here:
        try {
        
        String hoTen = cbbokh.getSelectedItem().toString();
        D_KhachHang kh = new D_KhachHang();
        kh = rskh.one(hoTen);
        txtSoDtKH.setText(kh.getSdt());
    
        } catch (Exception e) {
        }
    }//GEN-LAST:event_cbbokhActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (getGioHang.size() == 0) {
            JOptionPane.showMessageDialog(this, "Chưa Có Sản Phẩm Nào Trong Giỏ Hàng");
            return;
        }

        // Tạo mã hóa đơn mới mỗi khi nhấn nút
        float tong = 0;
        for (GioHangViewModel o : getGioHang) {
            tong += Float.valueOf(o.getTongTien());
        }
        DecimalFormat df = new DecimalFormat("#,###.##");
        float giamGia;
        try {
            giamGia = Float.parseFloat(df.parse(txtTiengiamgia.getText()).toString());
            float soTienCanTra = tong - giamGia;
            String dinhDangSoTienCanTra = rshd.dinhDangTien(soTienCanTra);
            String dinhDangTongTien = rshd.dinhDangTien(tong);
            txttongtienhang.setText(dinhDangTongTien);
            txttienphaitra.setText(dinhDangSoTienCanTra);
            txtngaytao.setDate(new Date());
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cbogiamGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbogiamGiaActionPerformed
        // TODO add your handling code here:
        try {
        
        KhuyenMai_View km = new KhuyenMai_View();
        String ma = cbogiamGia.getSelectedItem().toString();
        km = rskm.getGt(ma);
        int a = km.getGiaTri();
        txtTiengiamgia.setText(String.valueOf(a));
            
        } catch (Exception e) {
        }
    }//GEN-LAST:event_cbogiamGiaActionPerformed

    private void cbbokhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbbokhMouseClicked

    }//GEN-LAST:event_cbbokhMouseClicked

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        loadKM(S_ctsp.getAllKM());
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
         try {
        
        String makh = txtTimKiemTenKhachHang.getText();
       cbbokh.setSelectedItem(makh);
       txtTimKiemTenKhachHang.setText("");
       
           
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton9ActionPerformed

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
            java.util.logging.Logger.getLogger(ViewS_TrangChu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewS_TrangChu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewS_TrangChu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewS_TrangChu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewS_TrangChu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton MenuBanHang;
    private javax.swing.JButton Update;
    private javax.swing.JButton btnXoaKhoiGioHang;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbbokh;
    private javax.swing.JComboBox<String> cbogiamGia;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton64;
    private javax.swing.JButton jButton66;
    private javax.swing.JButton jButton67;
    private javax.swing.JButton jButton69;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton70;
    private javax.swing.JButton jButton71;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPaneTrangChu;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JButton menuSanPham;
    private javax.swing.JRadioButton rdoHuy;
    private javax.swing.JRadioButton rdochothanhtoan;
    private javax.swing.JRadioButton rdothanhtoanngay;
    private javax.swing.JTable tlbBangGioHang;
    private javax.swing.JTable tlbBangSanPham;
    private javax.swing.JTable tlbHoaDonCho;
    private javax.swing.JTextField txtMaHD;
    private javax.swing.JLabel txtSoDtKH;
    private javax.swing.JLabel txtTiengiamgia;
    private javax.swing.JButton txtTimKiem;
    private javax.swing.JTextField txtTimKiemSP;
    private javax.swing.JTextField txtTimKiemTenKhachHang;
    private javax.swing.JPanel txtid;
    private javax.swing.JLabel txtmanv;
    private com.toedter.calendar.JDateChooser txtngaytao;
    private javax.swing.JTextField txttienkhachdua;
    private javax.swing.JLabel txttienphaitra;
    private javax.swing.JLabel txttienthua;
    private javax.swing.JLabel txttongtienhang;
    // End of variables declaration//GEN-END:variables
}
