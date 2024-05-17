/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Service;


import ViewModel.KhuyenMai_View;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface IKhuyenMaiService {

    List<KhuyenMai_View> getall();
    
    KhuyenMai_View getGt(String ma);

    public boolean insert(KhuyenMai_View km);

    public boolean update(KhuyenMai_View km);

    public boolean delete(KhuyenMai_View km);

}
