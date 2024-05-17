Create database A_TestXeMayHoanThien_Nhom1_DuAN1
go
use A_TestXeMayHoanThien_Nhom1_DuAn1
go



Create Table ChucVu(
idcv UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
tenChucVu nvarchar(50) not null,
)
--chạy lại Db xóa not null maNv
Create Table TaiKhoan(
idtk UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
userName varchar(50),
passWordd varchar(50),
idChucVu UNIQUEIDENTIFIER,
CONSTRAINT FK_ChucVu FOREIGN KEY(idChucVu) REFERENCES ChucVu(idcv)
)
Create Table NhanVien(
idNV UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
maNV varchar(50) unique,
tenNV nvarchar(50),
diaChi nvarchar(50),
gioiTinh bit default 1,
ngaySinh date ,
sdt varchar(50)unique,
email varchar(50)unique,
matkhau varchar(50),
hinhanh varchar(100),
vitri varchar(100),
trangThai bit default 1,
idTaiKhoan UNIQUEIDENTIFIER,

CONSTRAINT FK_TaiKhoan FOREIGN KEY(idTaiKhoan) REFERENCES TaiKhoan(idtk)
)


Create Table KhachHang(
idKH UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
maKH varchar(50)unique,
hoTen nvarchar(50),
diaChi nvarchar(50),
gioiTinh bit default 0,
sdt varchar(50)unique,

)
Create Table KhuyenMai(
idkm UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
maKhuyenMai varchar(50)unique,
tenKhuyenmai nvarchar(50),
thoiGianBd date,
thoiGianKT Date,
GiaTri varchar(50),
trangThai int

)




Create Table HoaDon(
idhd int IDENTITY(1, 1) PRIMARY KEY,
maHoaDon varchar(50)unique,
idKhachHang  UNIQUEIDENTIFIER ,
idNhanVien  UNIQUEIDENTIFIER,
idkhuyenmai  UNIQUEIDENTIFIER,
tongTienHang float,
soTienPhaiTra float,
tienKhachDua float,
tienThua float,
trangThai int,
ngaytao date,
CONSTRAINT FK_IDKHACHHANG FOREIGN KEY(idKhachHang) REFERENCES KHachHang(idKH),
CONSTRAINT FK_IDNHANVIEN FOREIGN KEY(idNhanVien) REFERENCES NhanVien(idNV),
CONSTRAINT FK_IDKHUYENMAI FOREIGN KEY(idkhuyenmai) REFERENCES khuyenMai(idkm),
)


Create Table LoaiXe(
id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
loaiXe nvarchar(50) unique,
)

Create Table DTXiLanh(
id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
dTXiLanh nvarchar(50) unique ,
)


Create Table DTBinhXang(
id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
dTBinhXang nvarchar(50) unique,
)

Create Table Mau(
id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
mauSac nvarchar(50) unique,
)

Create Table XuatXu(
id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
xuatXu nvarchar(50)unique,
)

Create Table SanPham(
id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
maSP varchar(50) unique,
tenSP varchar(50),
idDTBinhXang UNIQUEIDENTIFIER,
idDTXiLanh UNIQUEIDENTIFIER,
idMau UNIQUEIDENTIFIER,
idXuatXu UNIQUEIDENTIFIER,
idLoaiXe UNIQUEIDENTIFIER,
soLuong int,
namSX int,
giaBan float,
trangThai bit default 1,
CONSTRAINT FK_DTXiLanh FOREIGN KEY(idDTXiLanh) REFERENCES DTXiLanh(id),
CONSTRAINT FK_DTBinhXang FOREIGN KEY(idDTBinhxang) REFERENCES DTBinhXang(id),
CONSTRAINT FK_Mau FOREIGN KEY(idMau) REFERENCES Mau(id),
CONSTRAINT FK_XuatXu FOREIGN KEY(idXuatXu) REFERENCES XuatXu(id),
CONSTRAINT FK_LoaiXe FOREIGN KEY(idloaixe) REFERENCES loaiXe(id),

)




Create Table SoKhung(
id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
idsp UNIQUEIDENTIFIER, 
SoKhung varchar(50) unique,
SoMay varchar(50) unique,
trangThai bit default 1,
CONSTRAINT FK_CTSanPham FOREIGN KEY(idsp) REFERENCES sanpham(id)
)


Create Table HoaDONChiTiet(
id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
idsp UNIQUEIDENTIFIER ,
idhd int,
idsk UNIQUEIDENTIFIER,
idkm UNIQUEIDENTIFIER,
slmua int,
trangthai bit,
CONSTRAINT FK_IDsp FOREIGN KEY(idsp) REFERENCES SanPham(id),
CONSTRAINT FK_idhd FOREIGN KEY(idhd) REFERENCES HoaDon(idhd),
CONSTRAINT FK_idsk FOREIGN KEY(idsk) REFERENCES SoKhung(id),
CONSTRAINT FK_idkm FOREIGN KEY(idkm) REFERENCES KhuyenMai(idkm),
)





	SELECT sp.id, sp.maSp, sp.tenSp,bx.dTBinhXang, xl.dTXiLanh, m.mauSac,  xx.xuatXu, lx.loaiXe,sp.namSX,sp.giaBan, sp.trangThai               
               	    
               FROM SanPham sp
                INNER JOIN DTBinhXang bx ON sp.idDTBinhXang = bx.id
                INNER JOIN DTXiLanh xl ON sp.idDTXiLanh = xl.id
               INNER JOIN Mau m ON sp.idMau = m.id
               INNER JOIN XuatXu xx ON sp.idXuatXu = xx.id
                INNER JOIN LoaiXe lx ON sp.idLoaiXe = lx.id
            

				select*from SanPham
                
               
               			
SELECT  sp.id, sp.maSp, sp.tenSp,  bx.dTBinhXang,  xl.dTXiLanh, m.mauSac, xx.xuatXu, 	lx.loaiXe, sp.namSX,sp.giaBan, sp.trangThai,                      
COUNT(sk.id) AS soLuongSoKhung
FROM SanPham sp
INNER JOIN DTBinhXang bx ON sp.idDTBinhXang = bx.id
INNER JOIN DTXiLanh xl ON sp.idDTXiLanh = xl.id
INNER JOIN Mau m ON sp.idMau = m.id
INNER JOIN XuatXu xx ON sp.idXuatXu = xx.id
INNER JOIN LoaiXe lx ON sp.idLoaiXe = lx.id
INNER JOIN SoKhung sk ON sp.id = sk.idsp
WHERE sk.trangThai = 1 and sp.trangThai = 1 
GROUP BY sp.id,sp.maSp, sp.tenSp, bx.dTBinhXang, xl.dTXiLanh, m.mauSac, xx.xuatXu,lx.loaiXe, sp.namSX,sp.giaBan, sp.trangThai 

select*from HoaDONChiTiet where idhd='9258C121-15B3-4D79-A293-4D252407ABF6'

select hd.idhd, hd.maHoaDon,kh.hoTen ,kh.sdt,nv.tenNV,nv.ngaySinh,km.maKhuyenMai,km.GiaTri,hd.tongTienHang,hd.soTienPhaiTra,hd.tienKhachDua,hd.tienThua,hd.ngaytao,hd.trangthai from hoadon hd 
inner join KhachHang kh on hd.idKhachHang = kh.idKH 
inner join NhanVien nv on hd.idNhanVien = nv.idNV
inner join KhuyenMai km on hd.idkhuyenmai = km.idkm ORDER BY hd.idhd DESC


select hdct.id,sp.maSP,sp.tenSP,sp.namSX,Mau.mauSac,sp.giaban,sk.sokhung,hd.trangThai,hd.maHoaDon from HoaDONChiTiet hdct
inner join SanPham sp on hdct.idsp = sp.id inner join Mau on sp.idMau = Mau.id 
inner join SoKhung sk on hdct.idsk = sk.id 
inner join HoaDon hd on hdct.idhd = hd.idhd inner join KhuyenMai km on hd.idkhuyenmai = km.idkm
 where hd.idhd =?


 select hd.idhd, hd.maHoaDon,kh.hoTen,km.maKhuyenMai,hd.tongTienHang,hd.soTienPhaiTra,hd.tienKhachDua,hd.tienThua from hoadon hd 
         	inner join KhachHang kh on hd.idKhachHang = kh.idKH 
           		inner join NhanVien nv on hd.idNhanVien = nv.idNV
            		inner join KhuyenMai km on hd.idkhuyenmai = km.idkm
              		where hd.trangThai=1


					delete from sokhung where id='C4EE8FD6-944E-46FA-B7C9-3D04852713D7'
					insert into SoKhung(idsp,SoKhung,SoMay,trangThai) values('38B887BF-A438-462D-BC12-44978080C39F','22266','55655',1)

					select*from hoadonchitiet 


					
					delete from HoaDON where idhd =2
					delete from HoaDON where idhd =3
					delete from HoaDON where idhd =4
					delete from HoaDON where idhd =5
					delete from HoaDON where idhd =6
					delete from HoaDON where idhd =7
					delete from HoaDON where idhd =8
					delete from HoaDON where idhd =9
					delete from HoaDON where idhd =10
					delete from HoaDON where idhd =11
					delete from HoaDON where idhd =12

					

						select sk.id,sp.maSP,sk.sokhung,sk.SoMay,sk.trangthai from SoKhung sk
                                      inner join SanPham sp on sk.idsp=sp.id where sk.trangThai=1 and maSP='M1'

									  UPDATE [dbo].[SoKhung]
									  asc
                           SET 
                              [trangThai] = 0
                         WHERE SoKhung = '56785'
                       select hdct.id,sp.maSP,sp.tenSP,sp.namSX,Mau.mauSac,sp.giaban,sk.sokhung,sk.SoMay,hd.trangThai,hd.maHoaDon from HoaDONChiTiet hdct
               inner join SanPham sp on hdct.idsp = sp.id inner join Mau on sp.idMau = Mau.id
                inner join SoKhung sk on hdct.idsk = sk.id 
               inner join HoaDon hd on hdct.idhd = hd.idhd inner join KhuyenMai km on hd.idkhuyenmai = km.idkm
                 where hd.idhd =11

				 select * from sanpham

				 select hdct.id,sp.maSP,sp.tenSP,sp.namSX,Mau.mauSac,sp.giaban,sk.sokhung,sk.SoMay,hd.trangThai,hd.idhd from HoaDONChiTiet hdct
                inner join SanPham sp on hdct.idsp = sp.id inner join Mau on sp.idMau = Mau.id 
                inner join SoKhung sk on hdct.idsk = sk.id 
                inner join HoaDon hd on hdct.idhd = hd.idhd inner join KhuyenMai km on hd.idkhuyenmai = km.idkm ORDER BY hd.idhd asc

				select * from SoKhung inner join SanPham on SoKhung.idsp = SanPham.id where SanPham.maSP ='M7'

				select hd.idhd, hd.maHoaDon,kh.hoTen ,kh.sdt,nv.tenNV,nv.ngaySinh,km.maKhuyenMai,km.GiaTri,hd.tongTienHang,hd.soTienPhaiTra,hd.tienKhachDua,hd.tienThua,hd.ngaytao,hd.trangthai from hoadon hd 
                inner join KhachHang kh on hd.idKhachHang = kh.idKH 
                inner join NhanVien nv on hd.idNhanVien = nv.idNV
                inner join KhuyenMai km on hd.idkhuyenmai = km.idkm 


				select *  from KhuyenMai

				delete from KhuyenMai where idkm ='728D608D-ACF1-49F6-80DB-B552ED2DEEE1'

				select hd.idhd, hd.maHoaDon,kh.hoTen ,kh.sdt,nv.tenNV,nv.ngaySinh,km.maKhuyenMai,km.GiaTri,hd.tongTienHang,hd.soTienPhaiTra,hd.tienKhachDua,hd.tienThua,hd.ngaytao,hd.trangthai from hoadon hd 
               inner join KhachHang kh on hd.idKhachHang = kh.idKH 
               inner join NhanVien nv on hd.idNhanVien = nv.idNV
                inner join KhuyenMai km on hd.idkhuyenmai = km.idkm  where hd.ngaytao=? 

						
				