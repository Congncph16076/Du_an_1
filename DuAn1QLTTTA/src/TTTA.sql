CREATE DATABASE QLTRUNGTAMTIENGANH1
GO
USE QLTRUNGTAMTIENGANH1
GO

IF OBJECT_ID('NGUOIDUNG') IS NOT NULL 
	DROP TABLE NGUOIDUNG
GO

CREATE TABLE NGUOIDUNG
(
	MANHANVIEN INT IDENTITY(1,1) NOT NULL,
	TENNHANVIEN NVARCHAR(100),
	GIOITINH bit,
	NGAYSINH DATE,
	DIACHI NVARCHAR(80),
	SDT NCHAR(13),
	EMAIL NVARCHAR(80),
	TENVAITRO INT NOT NULL,
	TENDANGNHAP NVARCHAR(20),
	MATKHAU NVARCHAR(20),
	CONSTRAINT PK_MAVAITRO PRIMARY KEY(MANHANVIEN),
)
GO

--SELECT TENDANGNHAP,MATKHAU from dbo.NGUOIDUNG
--WHERE TENDANGNHAP =? and matkhau=? AND TENVAITRO = 1

IF OBJECT_ID('LOAILOP') IS NOT NULL 
	DROP TABLE LOAILOP
GO


CREATE TABLE LOAILOP
(
	MALOAILOP INT IDENTITY(1,1),
	TENLOAILOP NVARCHAR(50),
	CONSTRAINT PK_MALOAILOP PRIMARY KEY(MALOAILOP)
)
GO

IF OBJECT_ID('CAPLOP') IS NOT NULL 
	DROP TABLE CAPLOP
GO

CREATE TABLE CAPLOP
(
	MACAPLOP INT IDENTITY(1,1),
	TENCAPLOP NVARCHAR(50),
	CONSTRAINT PK_MACAPLOP PRIMARY KEY(MACAPLOP)
)
GO

IF OBJECT_ID('LOP') IS NOT NULL 
	DROP TABLE LOP
GO

CREATE TABLE LOP
(
	MALOP INT IDENTITY(1,1),
	TENLOP NVARCHAR(100),
	SISO INT,
	CAHOC NVARCHAR(10),
	HOCPHI FLOAT,
	NGAYNHAPHOC DATE,
	NGAYKETTHUC DATE,
	MACAPLOP INT,
	MALOAILOP INT,
	MANHANVIEN INT,
	--MAKHOAHOC INT,
	CONSTRAINT PK_MALOP PRIMARY KEY(MALOP),
	CONSTRAINT FK_MACAPLOP FOREIGN KEY(MACAPLOP) REFERENCES dbo.CAPLOP,
	CONSTRAINT FK_MALOAILOP FOREIGN KEY(MALOAILOP) REFERENCES dbo.LOAILOP,
	CONSTRAINT FK_LOPGIANGVIEN FOREIGN KEY(MANHANVIEN) REFERENCES dbo.NGUOIDUNG,
	--CONSTRAINT FK_MAKHOAHOCDANGKI FOREIGN KEY(MAKHOAHOC) REFERENCES dbo.KHOAHOC
)
GO




IF OBJECT_ID('HOCVIEN') IS NOT NULL 
	DROP TABLE HOCVIEN
GO

CREATE TABLE HOCVIEN(
	MAHOCVIEN INT IDENTITY(1,1) NOT NULL,
	TENHOCVIEN NVARCHAR(80) NOT NULL,
	MALOP INT , 
	GIOITINH INT,
	NGAYSINH DATE,
	DIACHI NVARCHAR(80),
	SDT NCHAR(13),
	HOCPHINO FLOAT,
	EMAIL NVARCHAR(80),
	SOBUOINGHI INT,
	CONSTRAINT PK_MAHOCVIEN PRIMARY KEY(MAHOCVIEN),
	CONSTRAINT FK_LOP FOREIGN KEY(MALOP) REFERENCES dbo.LOP,
	--CONSTRAINT FK_MAHOCVIENDANGKI FOREIGN KEY(MAHOCVIEN) REFERENCES dbo.DANGKI
)
GO


IF OBJECT_ID('DOTTHI') IS NOT NULL 
	DROP TABLE DOTTHI
GO

CREATE TABLE DOTTHI
(
	MADOTTHI INT IDENTITY(1,1) NOT NULL,
	NGAYTHI DATE,
	CATHI INT,
	SISO INT,
	VANG INT,
	MALOP INT,
	CONSTRAINT PK_MADOTTHI PRIMARY KEY(MADOTTHI),
	CONSTRAINT FK_MAKHOAHOC FOREIGN KEY(MALOP) REFERENCES dbo.LOP
)
GO


IF OBJECT_ID('DANGKI') IS NOT NULL 
	DROP TABLE DANGKI
GO

CREATE TABLE DANGKI(
	madangki INT IDENTITY(1,1),
	TENHOCVIEN NVARCHAR(80),
	NGAYSINH DATE,
	GIOITINH INT,
	SDT NCHAR(13),
	EMAIL NVARCHAR(80),
	DIACHI NVARCHAR(80),
	TENCAPLOP NVARCHAR(80),
	TENLOAILOP NVARCHAR(80),
	HOCPHI FLOAT,
	CAHOC NVARCHAR(10),
	NGAYNHAPHOC DATE,
	NGAYDANGKI DATE,
	MAHOCVIEN INT,
	CONSTRAINT PK_SDT PRIMARY KEY(madangki),
	CONSTRAINT FK_DANGKIHOCVIEN FOREIGN KEY(MAHOCVIEN) REFERENCES dbo.HOCVIEN,
	--CONSTRAINT FK_MAHOCVIENDANGKI FOREIGN KEY(MAHOCVIEN) REFERENCES dbo.HOCVIEN,
	--CONSTRAINT FK_MALOPDANGKI FOREIGN KEY(MALOP) REFERENCES dbo.LOP,
	--CONSTRAINT FK_MABIENLAIDANGKI FOREIGN KEY(MABIENLAI) REFERENCES dbo.BIENLAI,
)
GO


IF OBJECT_ID('BIENLAI') IS NOT NULL 
	DROP TABLE BIENLAI
GO

CREATE TABLE BIENLAI(
	MABIENLAI INT IDENTITY(1,1),
	THANHTIEN FLOAT,
	MAHOCVIEN INT,
	MALOP INT,
	MANHANVIEN INT,
	MADANGKI INT,
	MADOTTHI INT,
	DIEMTHI FLOAT ,
	DIEMTHANHPHAN FLOAT,
	DIEMTONG FLOAT,
	NGAYTHUTIEN DATE,
	CONSTRAINT PK_MABIENLAI PRIMARY KEY(MABIENLAI),
	CONSTRAINT FK_BIENLAIHOCVIEN FOREIGN KEY(MAHOCVIEN) REFERENCES dbo.HOCVIEN,
	CONSTRAINT FK_BIENLAILOP FOREIGN KEY(MALOP) REFERENCES dbo.LOP,
	CONSTRAINT FK_MAKETOAN FOREIGN KEY(MANHANVIEN) REFERENCES dbo.NGUOIDUNG,
	CONSTRAINT FK_MABIENLAIMADOTTHI FOREIGN KEY(MADOTTHI) REFERENCES dbo.DOTTHI,
	CONSTRAINT FK_MABIENLAIDANGKI FOREIGN KEY(MADANGKI) REFERENCES dbo.DANGKI
)
GO


IF OBJECT_ID('BUOIHOC') IS NOT NULL
	DROP TABLE BUOIHOC
GO

CREATE TABLE BUOIHOC(
	MABUOIHOC INT IDENTITY(1,1),
	NGAYHOC DATE,
	CAHOC NVARCHAR(20),
	GHICHU NVARCHAR(100),
	MALOPHOC INT,
	CONSTRAINT PK_MABUOIHOC PRIMARY KEY(MABUOIHOC),
	CONSTRAINT FK_BUOIHOCLOPHOC FOREIGN KEY(MALOPHOC) REFERENCES dbo.LOP
)
GO

IF OBJECT_ID('DIEMDANH') IS NOT NULL
	DROP TABLE DIEMDANH
GO


CREATE TABLE DIEMDANH(
	MADIEMDANH INT IDENTITY(1,1),
	TRANGTHAI BIT,
	GHICHU NVARCHAR(100),
	MABUOIHOC INT,
	MABIENLAI INT,
	CONSTRAINT PK_DIEMDANH PRIMARY KEY(MADIEMDANH),
	CONSTRAINT FK_DIEMDANHBUOIHOC FOREIGN KEY(MABUOIHOC) REFERENCES dbo.BUOIHOC,
	CONSTRAINT FK_DIEMDANHBIENLAI FOREIGN KEY(MABIENLAI) REFERENCES dbo.BIENLAI
)
GO



SELECT * FROM dbo.NGUOIDUNG

DELETE FROM dbo.NGUOIDUNG
DBCC CHECKIDENT (NGUOIDUNG, RESEED,0)
SELECT* FROM dbo.NGUOIDUNG
INSERT INTO dbo.NGUOIDUNG(TENNHANVIEN,GIOITINH,NGAYSINH,DIACHI,SDT,EMAIL,TENVAITRO,TENDANGNHAP,MATKHAU)
VALUES(N'Nguyễn Đức Vinh',1,'1997/11/21',N'Ninh Bình','0345401309',N'Vinh123@gmail.com',2,N'vinh11',N'123456'),
	  (N'Nguyễn Thu Hà',0,'1987/02/27',N'Hà Nam','0814575843',N'Ha221@gmail.com',2,N'ha12',N'270206'),
	  (N'Nguyễn Đức Tuấn',1,'1999/01/21',N'Hải Dương','0245674251',N'Tuan99@gmail.com',2,N'tuan08',N'tuan08'),
	  (N'Nguyễn Thị Nở',0,'1997/04/21',N'Nam Định','0253561468',N'No11@gmail.com',2,N'no01',N'1234567'),
	  (N'Nguyễn Chí Công',1,'2002/08/24',N'Nam Định','0845136356',N'congnc2408@gmail.com',2,N'congnc22',N'cong22'),
	  (N'Phạm Thị Thu',0,'1991/08/21',N'Ninh Bình','0914506901',N'Thu21@gmail.com',2,N'thutp03',N'thu03'),
	  (N'Phạm Thanh Nhàn',0,'1994/08/22',N'Hà Nội','0314506901',N'Nhan22@gmail.com',2,N'nhantp14',N'nha04'),
	  (N'Phạm Tiến Hưng',1,'1992/04/21',N'Hà Nam','0912306901',N'Hung3k@gmail.com',2,N'hung92',N'123'),
	  (N'Phạm Văn Tèo',1,'1995/09/11',N'Nam Định','0916506901',N'Teo22@gmail.com',2,N'teo95',N'1234'),
	  (N'Nguyễn Hải Linh',0,'1998/02/12',N'Hà Nội','0345723189',N'Linh89@gmail.com',1,N'phong01',N'12345'),
	  (N'Nguyễn Thị Linh',0,'1997/01/12',N'Hà Nội','0345343189',N'Linh03@gmail.com',1,N'linhh98',N'123456'),
	  (N'Lò Văn PHóng',1,'1991/02/01',N'Lạng Sơn','0345213189',N'Phonglon@gmail.com',1,N'linhnt33',N'123456'),
	  (N'Vũ Hương Giang',0,'2001/02/12',N'Hà Nội','0345113189',N'Giang22@gmail.com',1,N'gianghv02',N'123456'),
	  (N'Tạ Thanh Cảnh',1,'2002/02/28',N'Hà Nam','0862012535',N'Canhkiu@gmai.com',0,N'admin',N'admin')

DELETE FROM dbo.LOAILOP
DBCC CHECKIDENT (LOAILOP, RESEED,0)
SELECT* FROM dbo.LOAILOP

INSERT INTO dbo.LOAILOP(TENLOAILOP)
VALUES(N'Toeic'),
	  (N'Anh Văn Gia Tiếp'),
	  (N'Anh Văn Tổng Quát')

DELETE FROM dbo.CAPLOP
DBCC CHECKIDENT (CAPLOP, RESEED,0)
SELECT* FROM dbo.CAPLOP

INSERT INTO dbo.CAPLOP(TENCAPLOP)
VALUES(N'A'),(N'B'),(N'C')

DELETE FROM dbo.LOP
DBCC CHECKIDENT (LOP, RESEED,0)
SELECT* FROM dbo.LOP

INSERT INTO dbo.LOP(TENLOP,SISO,CAHOC,HOCPHI,NGAYNHAPHOC,NGAYKETTHUC,MACAPLOP,MALOAILOP,MANHANVIEN)
VALUES(N'Toeic cấp A',10,N'3',1000000.0,'2010/10/10','2011/01/10',1,1,2),
	  (N'Toeic cấp A',10,N'1',1000000.0,'2011/01/11','2011/04/11',1,1,3),
	  (N'Toeic cấp B',15,N'2',2500000.0,'2011/05/10','2011/09/10',1,2,4),
	  (N'Anh Văn Tổng Quát cấp A',10,N'6',1500000.0,'2011/05/10','2011/09/10',2,3,3),
	  (N'Anh Văn Tổng Quát cấp A',10,N'1',1500000.0,'2011/05/10','2011/09/10',2,3,2),
	  (N'Anh Văn Tổng Quát cấp B',15,N'2',3000000.0,'2011/10/10','2012/01/10',2,3,9),
	  (N'Anh Văn Tổng Quát cấp C',5,N'6',5000000.0,'2010/10/10','2011/01/10',3,3,7),
	  (N'Anh văn giao tiếp cấp C',5,N'1',5500000.0,'2010/10/10','2011/01/10',2,3,4),
	  (N'Anh Văn giao tiếp cấp B',15,N'3',3200000.0,'2011/05/10','2011/09/10',2,2,5),
	  (N'Anh Văn giao tiếp cấp A',10,N'1',1700000.0,'2011/05/10','2011/09/10',2,2,6)

DELETE FROM dbo.HOCVIEN
DBCC CHECKIDENT (HOCVIEN, RESEED,0)
SELECT* FROM dbo.HOCVIEN

INSERT INTO dbo.HOCVIEN(TENHOCVIEN,MALOP,GIOITINH,NGAYSINH,DIACHI,SDT,HOCPHINO,EMAIL,SOBUOINGHI)
VALUES(N'Nguyễn Văn A',1,1,'1999/01/01',N'Đạo Lý- Lý Nhân-Hà Nam','0324553946',0,N'A@gmail.com',3),
	  (N'Nguyễn Văn B',1,1,'1998/01/01',N'Chân Lý- Lý Nhân-Hà Nam','0324553922',0,N'B@gmail.com',3),
	  (N'Nguyễn Văn C',1,1,'1997/01/21',N'Chân Lý- Lý Nhân-Hà Nam','0324553921',0,N'C@gmail.com',3),
	  (N'Nguyễn Văn D',1,1,'1999/02/01',N'Đạo Lý- Lý Nhân-Hà Nam','0324555922',0,N'D@gmail.com',3),
	  (N'Nguyễn Thị E',1,0,'2001/11/01',N'Ninh Bình','0324553932',0,N'E@gmail.com',3),
	  (N'Nguyễn Thị F',1,0,'2000/12/01',N'Hà Nội','0324553945',0,N'F@gmail.com',3),
	  (N'Nguyễn Thu H',1,0,'2000/11/11',N'Hà Nội','0812354781',0,N'H@gmail.com',3),
	  (N'Nguyễn Thu B',1,0,'2000/1/13',N'Hà Nội','0812352281',0,N'BT@gmail.com',3),
	  (N'Nguyễn Thu A',1,0,'2000/11/16',N'Hà Nội','0812324781',0,N'AN@gmail.com',3),
	  (N'Nguyễn Quỳnh',1,1,'2000/07/11',N'Hà Nội','0822354781',0,N'quynh@gmail.com',3),

	  (N'Nguyễn Chiến Thắng',2,1,'2000/07/24',N'Hà Nội','0813354781',0,N'Chien@gmail.com',4),
	  (N'Nguyễn Tiến H',2,1,'2001/07/23',N'Bắc Ninh','0813213741',1000000,N'tienH@gmail.com',1),
	  (N'Nguyễn Huy',2,1,'2000/06/24',N'Hà Nam','0814354781',0,N'huy1@gmail.com',4),
	  (N'Nguyễn Huy Tưởng',2,1,'2000/07/26',N'Hà Nội','0813365781',0,N'tuong@gmail.com',2),
	  (N'Hồ Hoài Ánh',2,0,'2002/04/24',N'Hà Nội','0813423781',0,N'anh@gmail.com',4),
	  (N'Nguyễn Thùy L',2,0,'2000/07/24',N'Hà Nội','0813354681',0,N'LT1@gmail.com',4),
	  (N'Nguyễn Thanh H',2,0,'1998/09/21',N'Ninh Bình','0813781075',0,N'hthanh@gmail.com',0),
	  (N'Nguyễn Quỳnh',2,0,'2001/07/11',N'Hà Nội','0822354781',0,N'quynh@gmail.com',3),
	  (N'Nguyễn Thu H',2,0,'2000/11/11',N'Hà Nội','0812354781',0,N'H@gmail.com',3),
	  (N'Nguyễn Thu B',2,0,'2000/1/13',N'Hà Nội','0812352281',0,N'BT@gmail.com',0),

	  (N'Nguyễn Văn A',3,1,'1999/01/01',N'Đạo Lý- Lý Nhân-Hà Nam','0324553946',0,N'A@gmail.com',3),
	  (N'Nguyễn Văn B',3,1,'1998/01/01',N'Chân Lý- Lý Nhân-Hà Nam','0324553922',0,N'B@gmail.com',3),
	  (N'Nguyễn Văn C',3,1,'1997/01/21',N'Chân Lý- Lý Nhân-Hà Nam','0324553921',0,N'C@gmail.com',3),
	  (N'Nguyễn Văn D',3,1,'1999/02/01',N'Đạo Lý- Lý Nhân-Hà Nam','0324555922',0,N'D@gmail.com',3),
	  (N'Nguyễn Thị E',3,0,'2001/11/01',N'Ninh Bình','0324553932',0,N'E@gmail.com',3),
	  (N'Nguyễn Thị F',3,0,'2000/12/01',N'Hà Nội','0324553945',0,N'F@gmail.com',3),
	  (N'Nguyễn Thu H',3,0,'2000/11/11',N'Hà Nội','0812354781',0,N'H@gmail.com',3),
	  (N'Nguyễn Thu B',3,0,'2000/1/13',N'Hà Nội','0812352281',0,N'BT@gmail.com',3),
	  (N'Nguyễn Thu A',3,0,'2000/11/16',N'Hà Nội','0812324781',0,N'AN@gmail.com',3),
	  (N'Nguyễn Quỳnh',3,1,'2000/07/11',N'Hà Nội','0822354781',0,N'quynh@gmail.com',3),
	  (N'Nguyễn Chiến Thắng',3,1,'2000/07/24',N'Hà Nội','0813354781',0,N'Chien@gmail.com',4),
	  (N'Nguyễn Tiến H',3,1,'2001/07/23',N'Bắc Ninh','0813213741',1000000,N'tienH@gmail.com',1),
	  (N'Nguyễn Huy',3,1,'2000/06/24',N'Hà Nam','0814354781',0,N'huy1@gmail.com',4),
	  (N'Nguyễn Huy Tưởng',3,1,'2000/07/26',N'Hà Nội','0813365781',0,N'tuong@gmail.com',2),
	  (N'Hồ Hoài Ánh',3,0,'2002/04/24',N'Hà Nội','0813423781',0,N'anh@gmail.com',4),

	  (N'Nguyễn Thị E',8,0,'2001/11/01',N'Ninh Bình','0324553932',2000000,N'E@gmail.com',3),
	  (N'Nguyễn Thị F',8,0,'2000/12/01',N'Hà Nội','0324553945',0,N'F@gmail.com',3),
	  (N'Nguyễn Thu H',8,0,'2000/11/11',N'Hà Nội','0812354781',1000000,N'H@gmail.com',3),
	  (N'Nguyễn Thu B',8,0,'2000/1/13',N'Hà Nội','0812352281',0,N'BT@gmail.com',3),
	  (N'Nguyễn Thu A',8,0,'2000/11/16',N'Hà Nội','0812324781',0,N'AN@gmail.com',3),

	  (N'Nguyễn Thị F',9,0,'2000/12/01',N'Hà Nội','0324553945',0,N'F@gmail.com',3),
	  (N'Nguyễn Thu H',9,0,'2000/11/11',N'Hà Nội','0812354781',0,N'H@gmail.com',3),
	  (N'Nguyễn Thu B',9,0,'2000/1/13',N'Hà Nội','0812352281',0,N'BT@gmail.com',3),
	  (N'Nguyễn Thu A',9,0,'2000/11/16',N'Hà Nội','0812324781',0,N'AN@gmail.com',3),
	  (N'Nguyễn Quỳnh',9,1,'2000/04/11',N'Hà Nội','0822354781',0,N'quynh@gmail.com',3),
	  (N'Nguyễn Văn A',9,1,'1999/01/01',N'Đạo Lý- Lý Nhân-Hà Nam','0324553946',0,N'A@gmail.com',3),
	  (N'Nguyễn Văn B',9,1,'1998/01/01',N'Chân Lý- Lý Nhân-Hà Nam','0324553922',0,N'B@gmail.com',3),
	  (N'Nguyễn Văn C',9,1,'1997/01/21',N'Chân Lý- Lý Nhân-Hà Nam','0324553921',0,N'C@gmail.com',3),
	  (N'Nguyễn Văn D',9,1,'1999/02/01',N'Đạo Lý- Lý Nhân-Hà Nam','0324555922',0,N'D@gmail.com',3),
	  (N'Nguyễn Thị E',9,0,'2001/11/01',N'Ninh Bình','0324553932',0,N'E@gmail.com',3),
	  (N'Nguyễn Huy',9,1,'2000/06/24',N'Hà Nam','0814354781',0,N'huy1@gmail.com',4),
	  (N'Nguyễn Huy Tưởng',9,1,'2000/07/26',N'Hà Nội','0813365781',0,N'tuong@gmail.com',2),
	  (N'Hồ Hoài Ánh',9,0,'2002/04/24',N'Hà Nội','0813423781',0,N'anh@gmail.com',4),
	  (N'Nguyễn Thùy L',9,0,'2000/07/24',N'Hà Nội','0813351181',0,N'LT1@gmail.com',4),
	  (N'Nguyễn Thanh H',9,0,'1998/09/21',N'Ninh Bình','0813781075',0,N'hthanh@gmail.com',0),

	  (N'Nguyễn Thu An',10,0,'2000/11/16',N'Hà Nội','0812324797',0,N'AN@gmail.com',3),
	  (N'Nguyễn Quỳnh Hương',10,1,'2000/07/11',N'Hà Nội','0822354781',0,N'quynhh@gmail.com',3),
	  (N'Nguyễn Văn A',10,1,'1999/01/01',N'Đạo Lý- Lý Nhân-Hà Nam','0324553946',0,N'A@gmail.com',3),
	  (N'Nguyễn Văn B',10,1,'1998/01/01',N'Chân Lý- Lý Nhân-Hà Nam','0324553922',0,N'B@gmail.com',3),
	  (N'Nguyễn Văn C',10,1,'1997/03/21',N'Chân Lý- Lý Nhân-Hà Nam','0324553921',0,N'C@gmail.com',3),
	  (N'Nguyễn Chiến Thắng',10,1,'2000/07/24',N'Hà Nội','0813354781',0,N'Chien@gmail.com',4),
	  (N'Nguyễn Tiến Hưng',10,1,'2001/08/23',N'Bắc Ninh','0813213741',1000000,N'tienHung@gmail.com',1),
	  (N'Nguyễn Huy Long',10,1,'2000/11/24',N'Hà Nam','0814354781',0,N'huylong@gmail.com',4),
	  (N'Nguyễn Huy Tưởng',10,1,'2000/12/26',N'Hà Nội','0813365781',0,N'tuong@gmail.com',2),
	  (N'Hồ Hoài Ánh',10,0,'2002/01/24',N'Hà Nội','0813423781',0,N'anhak@gmail.com',4),

	(N'Nguyễn Đức A',4,1,'1999/11/01',N'Hà Nội','0350221148',500000,N'AD@gmail.com',1),
	(N'Nguyễn Đức B',4,1,'1999/10/01',N'Hà Tĩnh','0350221147',500000,N'BD@gmail.com',2),
	(N'Nguyễn Đức C',4,1,'1999/09/01',N'Thái Nguyên','0350221146',500000,N'AD@gmail.com',3),
	(N'Nguyễn Đức D',4,1,'1999/08/01',N'Hải Phòng','0350221145',500000,N'AD@gmail.com',2),
	(N'Nguyễn Đức E',4,1,'1999/07/01',N'Ninh Bình','0350221144',500000,N'AD@gmail.com',0),
	(N'Nguyễn Thùy A',4,0,'1999/07/05',N'Ninh Bình','0350221143',500000,N'AT@gmail.com',1),
	(N'Nguyễn Thùy B',4,0,'1999/07/11',N'Thái Bình','0350221142',500000,N'BT@gmail.com',1),
	(N'Nguyễn Thùy C',4,0,'1999/07/21',N'Quảng Bình','0350221141',500000,N'CT@gmail.com',2),
	(N'Nguyễn Thùy D',4,0,'1999/07/12',N'Nam Định','0350221140',500000,N'DT@gmail.com',3),
	(N'Nguyễn Thùy E',4,0,'1999/06/01',N'Thái Bình','0350221138',500000,N'ET@gmail.com',0),
	(N'Nguyễn Mai A',5,0,'1998/06/15',N'Thái Bình','0350221132',500000,N'AM@gmail.com',1),
	(N'Nguyễn Mai B',5,0,'1998/08/15',N'Quảng Bình','0350221133',500000,N'BM@gmail.com',2),
	(N'Nguyễn Mai C',5,0,'1998/01/15',N'Quảng Ninh','0350221134',500000,N'CM@gmail.com',3),
	(N'Nguyễn Mai D',5,0,'1998/03/15',N'Thái Nguyên','0350221135',500000,N'DM@gmail.com',0),
	(N'Nguyễn Mai E',5,0,'1998/04/15',N'Hòa Bình','0350221136',500000,N'EM@gmail.com',1),
	(N'Nguyễn Quang A',5,1,'1998/04/10',N'Hòa Bình','0350221425',500000,N'AQ@gmail.com',2),
	(N'Nguyễn Quang B',5,1,'1998/04/01',N'Thái Bình','0350221424',500000,N'BQ@gmail.com',2),
	(N'Nguyễn Quang C',5,1,'1998/02/10',N'Ninh Bình','0350221423',500000,N'CQ@gmail.com',2),
	(N'Nguyễn Quang D',5,1,'1998/04/22',N'Hà Nội','0350221422',500000,N'DQ@gmail.com',1),
	(N'Nguyễn Công A',6,1,'1998/08/22',N'Hà Nội','035022150',2000000,N'AC@gmail.com',1),
	(N'Nguyễn Công B',6,1,'1998/08/23',N'Hà Nội','035022151',2000000,N'BC@gmail.com',2),
	(N'Nguyễn Công C',6,1,'1998/08/24',N'Hà Nội','035022152',2000000,N'CC@gmail.com',1),
	(N'Nguyễn Công D',6,1,'1998/08/25',N'Hà Nội','035022154',2000000,N'DC@gmail.com',0),
	(N'Nguyễn Công E',6,1,'1998/08/27',N'Hải Dương','035022155',2000000,N'EC@gmail.com',3),
	(N'Nguyễn Công F',6,1,'1998/08/28',N'Quảng Ninh','035022153',2000000,N'FC@gmail.com',1),
	(N'Nguyễn Nhật A',6,0,'1998/07/28',N'Hà Nội','035022158',2000000,N'AN@gmail.com',1),
	(N'Nguyễn Nhật B',6,0,'1998/11/28',N'Hà Nội','035022157',2000000,N'BN@gmail.com',2),
	(N'Nguyễn Nhật C',6,0,'1998/12/28',N'Bắc Giang','035022158',2000000,N'CN@gmail.com',0),
	(N'Nguyễn Nhật D',6,0,'1998/10/28',N'Vĩnh Phúc','035022159',2000000,N'DN@gmail.com',0),
	(N'Nguyễn Nhật E',6,0,'1998/03/28',N'Hà Nội','035022161',2000000,N'EN@gmail.com',0),
	(N'Nguyễn Nhật F',6,0,'1998/01/28',N'Hà Nội','035022162',2000000,N'FN@gmail.com',3),
	(N'Nguyễn Nhật G',6,0,'1998/08/28',N'Ninh Bình','035022163',2000000,N'GN@gmail.com',4),
	(N'Nguyễn Nhật H',6,0,'1998/09/28',N'Nam Định','035022164',2000000,N'HN@gmail.com',0),
	(N'Nguyễn Nhật K',6,0,'1998/02/28',N'Hà Nội','035022165',2000000,N'KN@gmail.com',1),
	(N'Nguyễn Phúc A',7,1,'1999/07/22',N'Hà Nội','035022191',4000000,N'AP@gmail.com',0),
	(N'Nguyễn Phúc B',7,1,'1999/05/22',N'Hà Nam','035022191',4000000,N'BP@gmail.com',0),
	(N'Nguyễn Phúc C',7,1,'1999/09/22',N'Hà Nam','035022191',4000000,N'CP@gmail.com',0),
	(N'Nguyễn Phúc D',7,1,'1999/06/22',N'Hà Nội','035022191',4000000,N'DP@gmail.com',1),
	(N'Nguyễn Phúc E',7,1,'1999/01/22',N'Hà Nội','035022191',4000000,N'EP@gmail.com',1)

DELETE FROM dbo.DOTTHI
DBCC CHECKIDENT (DOTTHI, RESEED,0)
SELECT* FROM dbo.DOTTHI

INSERT INTO dbo.DOTTHI(NGAYTHI,CATHI,SISO,VANG,MALOP)
VALUES('2011/01/10',3,30,10,1),
	  ('2011/04/11',2,30,5,2),
	  ('2011/09/10',4,30,12,3),
	  ('2011/09/10',5,30,9,5),
	  ('2011/09/10',6,30,9,4),
	  ('2012/01/10',5,30,9,6),
	  ('2011/01/10',5,30,9,8),
	  ('2011/01/10',5,30,9,9),
	  ('2011/09/10',5,30,9,7),
	  ('2011/09/10',5,30,9,10)

DELETE FROM dbo.DANGKI
DBCC CHECKIDENT (DANGKI, RESEED,0)
SELECT* FROM dbo.DANGKI

INSERT INTO dbo.DANGKI(TENHOCVIEN,NGAYSINH,GIOITINH,SDT,EMAIL,DIACHI,TENCAPLOP,TENLOAILOP,HOCPHI,CAHOC,NGAYNHAPHOC,NGAYDANGKI,MAHOCVIEN)
VALUES
(N'Nguyễn Thị A','1999/01/01',0,'0324513946',N'A1@gmail.com',N'Đạo Lý- Lý NhâN-Hà Nam',N'A',N'Toeic',1000000.0,N'Ca 3','2012/03/10','2012/02/28',NULL),
(N'Nguyễn Thị B','1998/01/10',0,'0324213926',N'B1@gmail.com',N'Đạo Lý- Lý NhâN-Hà Nam',N'A',N'Toeic',1000000.0,N'Ca 2','2012/03/10','2012/02/28',NULL),
(N'Nguyễn Thị C','1997/01/01',1,'0324321126',N'C1@gmail.com',N'Đạo Lý- Lý NhâN-Hà Nam',N'A',N'Toeic',1000000.0,N'Ca 2','2012/03/10','2012/02/28',NULL),
(N'Nguyễn Văn A','1999/01/01',1,'0324553646',N'A@gmail.com',N'Đạo Lý- Lý Nhân-Hà Nam',N'A',N'Toeic',1000000.0,N'Ca 3','2010/10/10','2010/09/30',1),
(N'Nguyễn Văn B','1999/02/01',1,'0324523956',N'B@gmail.com',N'Đạo Lý- Lý Nhân-Hà Nam',N'A',N'Toeic',1000000.0,N'Ca 3','2010/10/10','2010/09/30',2),
(N'Nguyễn Văn C','1999/04/01',1,'0324553916',N'C@gmail.com',N'Đạo Lý- Lý Nhân-Hà Nam',N'A',N'Toeic',1000000.0,N'Ca 3','2010/10/10','2010/09/30',3)

DELETE FROM dbo.BIENLAI
DBCC CHECKIDENT (BIENLAI, RESEED,0)
SELECT* FROM dbo.BIENLAI

INSERT INTO dbo.BIENLAI(THANHTIEN,MAHOCVIEN,MALOP,MANHANVIEN,MADANGKI,MADOTTHI,DIEMTHI,DIEMTHANHPHAN,DIEMTONG,NGAYTHUTIEN)
VALUES(1000000,null,null,10,1,NULL,NULL,NULL,NULL,'2012/02/28'),
	  (1000000,null,null,10,2,NULL,NULL,NULL,NULL,'2012/02/28'),
	  (1000000,null,null,11,3,NULL,NULL,NULL,NULL,'2012/02/28'),
	  (1000000,1,1,12,4,1,9,0,9,'2010/09/30'),
	  (1000000,2,1,13,5,1,7,0,7,'2010/09/30'),
	  (1000000,3,1,11,6,1,6,0,6,'2010/09/30')

DELETE FROM dbo.BUOIHOC
DBCC CHECKIDENT (BUOIHOC, RESEED,0)
SELECT* FROM dbo.BUOIHOC

INSERT INTO dbo.BUOIHOC(NGAYHOC,CAHOC,GHICHU,MALOPHOC)
VALUES('2010/10/10',N'3',N'Đủ',1),
	  ('2011/01/11',N'1',N'Đủ',2),
	  ('2011/05/10',N'2',N'Đủ',3),
	  ('2011/05/10',N'6',N'Đủ',4),
	  ('2011/05/10',N'1',N'Đủ',5),
	  ('2011/05/10',N'1',N'Đủ',9),
	  ('2011/05/10',N'3',N'Đủ',10),
	  ('2010/10/10',N'2',N'Đủ',6),
	  ('2010/10/10',N'6',N'Đủ',7),
	  ('2010/10/10',N'1',N'Đủ',8)

DELETE FROM dbo.DIEMDANH
DBCC CHECKIDENT (DIEMDANH, RESEED,0)
SELECT* FROM dbo.DIEMDANH

INSERT INTO dbo.DIEMDANH
(TRANGTHAI,GHICHU,MABUOIHOC,MABIENLAI)
VALUES(1,N'Đủ',1,4),
	(1,N'Đủ',1,5),
	(1,N'Đủ',1,6)

-----------------------------------------------------------------------bắt đầu truy vấn  học viên ----------------------------------------------
--Thủ tục lưu quản lý học viên
ALTER PROC thong_tin_sv
AS
BEGIN
    SELECT MAHOCVIEN,TENHOCVIEN,lop.MALOP,dbo.LOP.TENLOP,GIOITINH,NGAYSINH,SDT,EMAIL,DIACHI,HOCPHINO FROM  dbo.HOCVIEN
	JOIN dbo.LOP ON LOP.MALOP = HOCVIEN.MALOP
	ORDER BY MAHOCVIEN DESC
END

--thêm học viên
INSERT dbo.HOCVIEN(TENHOCVIEN,MALOP,GIOITINH,NGAYSINH,SDT,DIACHI,EMAIL,HOCPHINO,SOBUOINGHI)
VALUES(?,?,?,?,?,?,?,?,?)
GO
-- SỬA THÔNG TIN HỌC VIÊN
UPDATE dbo.HOCVIEN SET TENHOCVIEN = ?,MALOP = ?,GIOITINH = ?,NGAYSINH = ?,SDT =?,DIACHI =?,EMAIL = ?, HOCPHINO = ?,SOBUOINGHI = ?
WHERE MAHOCVIEN = ? AND  MALOP = ?
GO
--xóa thông tin học viên

--tìm kiếm học viên theo tên và lớp
CREATE PROCEDURE tim_kiem_hoc_vien_theo_ten_va_ten_lop(@tensinhvien nvarchar(100),@tenlop nvarchar(50))
AS
BEGIN
    SELECT MAHOCVIEN,TENHOCVIEN,dbo.LOP.malop,dbo.LOP.TENLOP,GIOITINH,CONVERT(nvarchar(50),ngaysinh,103) [ngaysinh],SDT,EMAIL,DIACHI,HOCPHINO,SOBUOINGHI FROM  dbo.HOCVIEN
	JOIN dbo.LOP ON LOP.MALOP = HOCVIEN.MALOP
	WHERE TENHOCVIEN like LTRIM(RTRIM(@tensinhvien)) AND TENLOP LIKE LTRIM(RTRIM(@tenlop))
	ORDER BY MAHOCVIEN DESC
END

EXEC dbo.tim_kiem_hoc_vien_theo_ten_va_ten_lop @tensinhvien = N'    %văn%      ', -- nvarchar(100)
                                               @tenlop = N' %anh văn%   '       -- nvarchar(50)


--lấy thông tin sinh viên từ bảng đăng kí
CREATE PROCEDURE lay_thong_tin_hoc_vien_tu_bang_dang_ki(@sdt NCHAR(10))
AS
BEGIN
	INSERT INTO dbo.HOCVIEN(TENHOCVIEN,GIOITINH,NGAYSINH,DIACHI,SDT,EMAIL,HOCPHINO)
	SELECT dbo.DANGKI.TENHOCVIEN,dbo.DANGKI.GIOITINH,CONVERT(nvarchar(50),dbo.DANGKI.ngaysinh,103) [ngaysinh],dbo.DANGKI.DIACHI ,dbo.DANGKI.SDT,dbo.DANGKI.EMAIL FROM dbo.DANGKI
	WHERE dangki.SDT = @sdt AND dbo.DANGKI.SDT NOT IN (SELECT sdt FROM dbo.HOCVIEN)
	 
END
GO

EXEC dbo.lay_thong_tin_hoc_vien_tu_bang_dang_ki @sdt = N'0324513946' -- nchar(10)
SELECT * ,CONVERT(nvarchar(50),ngaysinh,103) from hocvien
where sdt = N'0324513946'

-----------------------------------------------------------hết  truy vấn bảng học viên-----------------------------------------------------
--------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------------------
---------------------------------------------------------- bắt đầu truy vấn bảng lớp-------------------------------------------------------

-- lấy thông tin lớp
CREATE PROCEDURE thong_tin_lop
AS
BEGIN
    SELECT MALOP,TENLOP,LOP.MANHANVIEN,TENNHANVIEN,HOCPHI,CAHOC,SISO,TENCAPLOP,TENLOAILOP,NGAYNHAPHOC,NGAYKETTHUC FROM dbo.LOP
	JOIN dbo.CAPLOP ON CAPLOP.MACAPLOP = LOP.MACAPLOP
	JOIN dbo.LOAILOP ON LOAILOP.MALOAILOP = LOP.MALOAILOP
	JOIN dbo.NGUOIDUNG ON NGUOIDUNG.MANHANVIEN = LOP.MANHANVIEN
	ORDER BY MALOP DESC
	
END
-- thêm lớp
INSERT INTO dbo.LOP(TENLOP,SISO,CAHOC,HOCPHI,NGAYNHAPHOC,NGAYKETTHUC,MACAPLOP,MALOAILOP,MANHANVIEN)
VALUES(?,?,?,?,?,?,?,?,?)
GO
--sửa thông tin lớp
UPDATE dbo.LOP SET TENLOP=?,SISO=?,CAHOC=?,MACAPLOP=?,MALOAILOP=?,HOCPHI=?,HOCPHI=?,NGAYNHAPHOC=?,NGAYKETTHUC=?
WHERE MALOP =?
GO
--xóa lớp
DELETE FROM dbo.LOP
WHERE MALOP = ?
GO
CREATE PROCEDURE xoa_update_lop(@malop INT)
AS
BEGIN
    DELETE FROM dbo.LOP
	WHERE MALOP = @malop
	declare @max int
	select @max=max(MALOP)from dbo.LOP
	if @max IS NULL   
	  SET @max = 0
	DBCC CHECKIDENT (LOP, RESEED,@max)
END
GO
EXEC dbo.xoa_update_nguoi_dung @manhanvien = 17 -- int
-- int

SELECT * FROM dbo.LOAILOP
SELECT* FROM dbo.CAPLOP
SELECT * FROM dbo.LOP
-- tìm lớp
CREATE PROCEDURE tim_kiem_lop_theo_ma_lop(@tenlop NVARCHAR(50))
AS
BEGIN
    SELECT MALOP,TENLOP,LOP.MANHANVIEN,TENNHANVIEN,HOCPHI,CAHOC,SISO,dbo.CAPLOP.MACAPLOP,TENCAPLOP,dbo.LOAILOP.MALOAILOP,TENLOAILOP,NGAYNHAPHOC,NGAYKETTHUC FROM dbo.LOP
	JOIN dbo.CAPLOP ON CAPLOP.MACAPLOP = LOP.MACAPLOP
	JOIN dbo.LOAILOP ON LOAILOP.MALOAILOP = LOP.MALOAILOP
	JOIN dbo.NGUOIDUNG ON NGUOIDUNG.MANHANVIEN = LOP.MANHANVIEN
	WHERE TENLOP LIKE LTRIM(RTRIM(@tenlop))
	ORDER BY MALOP DESC
END

EXEC dbo.tim_kiem_lop_theo_ma_lop @tenlop = N'%toeic%' -- nvarchar(50)
GO
SELECT * FROM dbo.NGUOIDUNG

------------------------------------------------------------------------ kết thúc truy vấn lớp----------------------------------------------------
--------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------
------------------------------------------------------------------------ bắt đầu truy vấn đợt thi-------------------------------------------------
-- lấy thông tin đợt thi
CREATE PROCEDURE lay_thong_tin_dot_thi
AS
BEGIN
    SELECT MADOTTHI,LOP.MALOP,TENLOP,CONVERT(NVARCHAR(20),NGAYTHI,103) [ngaythi],cathi,dbo.LOP.SISO,VANG FROM dbo.DOTTHI
	join dbo.LOP on LOP.MALOP = DOTTHI.MALOP
	ORDER BY MADOTTHI desc
END
SELECT * FROM dbo.DOTTHI
--thêm đợt thi
INSERT INTO dbo.DOTTHI(NGAYTHI,cathi,SISO,VANG,MALOP)
VALUES(?,?,?,?,?)
GO
-- sửa thông tin đợt thi
UPDATE dbo.DOTTHI SET NGAYTHI=?,cathi= ?,SISO=?,VANG=?,MALOP=?
WHERE MADOTTHI = ?
GO
--xóa đợt thi

GO
CREATE PROCEDURE xoa_update_dot_thi(@madotthi INT)
AS
BEGIN
	DELETE  FROM dbo.DOTTHI
	WHERE MADOTTHI =@madotthi
	declare @max int
	select @max=max(MADOTTHI)from dbo.DOTTHI
	if @max IS NULL   
	  SET @max = 0
	DBCC CHECKIDENT (DOTTHI, RESEED,@max)
END
go

--tìm kiếm đợt thi
CREATE PROCEDURE tim_kiem_dot_thi(@malop int)
AS
BEGIN
   SELECT MADOTTHI,LOP.MALOP,TENLOP,CONVERT(NVARCHAR(20),NGAYTHI,103) [ngaythi],cathi,dbo.LOP.SISO,VANG FROM dbo.DOTTHI
	join dbo.LOP on LOP.MALOP = DOTTHI.MALOP
	WHERE LOP.MALOP LIKE LTRIM(RTRIM(@malop)) 
	ORDER BY MADOTTHI desc
END

EXEC dbo.tim_kiem_dot_thi @malop =  1 
go
------------------------------------------------------------ kết thúc truy vấn đợt thi----------------------------------------------------
------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------
-------------------------------------------------------------bắt đầu truy vấn biên lai-----------------
-- thông tin biên lai
CREATE PROCEDURE thong_tin_bien_lai_hvcu
AS
BEGIN
    SELECT MABIENLAI,TENHOCVIEN,TENLOP,HOCPHI,HOCPHINO,THANHTIEN = (HOCPHI-HOCPHINO),NGUOIDUNG.MANHANVIEN,TENNHANVIEN,DIEMTHI,DIEMTHANHPHAN,DIEMTONG=(DIEMTHI+DIEMTHANHPHAN) FROM dbo.BIENLAI
	JOIN dbo.HOCVIEN ON HOCVIEN.MAHOCVIEN = BIENLAI.MAHOCVIEN
	JOIN dbo.LOP ON LOP.MALOP = BIENLAI.MALOP
	JOIN dbo.NGUOIDUNG ON NGUOIDUNG.MANHANVIEN = BIENLAI.MANHANVIEN
END
EXEC dbo.thong_tin_bien_lai_hvcu

CREATE PROCEDURE thong_tin_bien_lai_moi
AS
BEGIN
    SELECT TENHOCVIEN,CONVERT(NVARCHAR(30),NGAYSINH,103), [ngaysinh] FROM dbo.BIENLAI
	JOIN dbo.DANGKI ON DANGKI.madangki = BIENLAI.MADANGKI
END
-- thêm hóa đơn
INSERT INTO dbo.BIENLAI(THANHTIEN,MAHOCPHI,MAHOCVIEN,MALOP,MAKETOAN,MADANGKI)
VALUES(?,?,?,?,?,?)
-- sửa hóa đơn
UPDATE dbo.BIENLAI SET THANHTIEN=?,MAHOCPHI=?,MAHOCVIEN=?,MALOP=?,MAKETOAN=?,MADANGKI=?
WHERE MADANGKI =?
GO
--xóa biên lai
DELETE FROM dbo.BIENLAI
WHERE MADANGKI =?
GO

--tìm kiếm biên lai
CREATE PROCEDURE tim_kiem_bien_lai(@mabienlai INT)
AS
BEGIN
    SELECT MABIENLAI,TENHOCVIEN,TENLOP,HOCPHI,HOCPHINO,THANHTIEN = (HOCPHI-HOCPHINO),TENKETOAN FROM dbo.BIENLAI
	JOIN dbo.HOCVIEN ON HOCVIEN.MAHOCVIEN = BIENLAI.MAHOCVIEN
	JOIN dbo.LOP ON LOP.MALOP = BIENLAI.MALOP
	JOIN dbo.KETOAN ON KETOAN.MAKETOAN = BIENLAI.MAKETOAN
	WHERE MABIENLAI LIKE LTRIM(RTRIM(@mabienlai))
END
GO
------------------------------------------------------kết thúc truy vấn biên lai--------------------------------------------------
-----------------------------------------------------------------------------
---------------------------------------------------------------------------
------------------------------------------------------bắt đầu truy vấn điểm thi---------------------------------------------------
-------------------------------------------------------------------------
-----------------------------------------------------------------------
--thông tin điểm thi
CREATE PROCEDURE thong_tin_diem_thi
AS
BEGIN
    SELECT DIEMTHI.MAHOCVIEN,TENHOCVIEN,DIEMTHI.MADOTTHI,DIEMTHI,CONVERT(NVARCHAR(20),NGAYTHI,103) [NGAYTHI],CONVERT(NVARCHAR(30),GIOTHI,114)[GIOTHI] FROM dbo.DIEMTHI
	JOIN dbo.DOTTHI ON DOTTHI.MADOTTHI = DIEMTHI.MADOTTHI
	JOIN dbo.HOCVIEN ON HOCVIEN.MAHOCVIEN = DIEMTHI.MAHOCVIEN

END
--THÊM ĐIỂM THI
INSERT INTO dbo.DIEMTHI(MAHOCVIEN,MADOTTHI,DIEMTHI)
VALUES(?,?,?)
--sửa điểm thi
UPDATE dbo.DIEMTHI SET DIEMTHI= ?
WHERE MAHOCVIEN=?,MADOTTHI=?
GO

CREATE PROCEDURE xoa_update_diem_thi(@mahocvien INT,@madotthi INT )
AS
BEGIN
	DELETE  FROM dbo.DIEMTHI
	WHERE MADOTTHI =@madotthi AND MAHOCVIEN =@mahocvien
	declare @max int
	select @max=max(MADOTTHI)from dbo.DOTTHI
	if @max IS NULL   
	  SET @max = 0
	DBCC CHECKIDENT (DOTTHI, RESEED,@max)
END
go
--xóa điểm thi

--tìm kiếm điểm thi
CREATE PROCEDURE tim_kiem_diem_thi(@tenhocvien NVARCHAR(100))
AS
BEGIN
    SELECT DIEMTHI.MAHOCVIEN,TENHOCVIEN,DIEMTHI.MADOTTHI,DIEMTHI,CONVERT(NVARCHAR(20),NGAYTHI,103) [NGAYTHI],CONVERT(NVARCHAR(30),GIOTHI,114)[GIOTHI] FROM dbo.DIEMTHI
	JOIN dbo.DOTTHI ON DOTTHI.MADOTTHI = DIEMTHI.MADOTTHI
	JOIN dbo.HOCVIEN ON HOCVIEN.MAHOCVIEN = DIEMTHI.MAHOCVIEN
	WHERE TENHOCVIEN LIKE LTRIM(RTRIM(@tenhocvien))
END

EXEC dbo.tim_kiem_diem_thi @tenhocvien = N'  %văn%  ' -- nvarchar(100)
go
-------------------------------------------------------------------kết thúc truy vấn điểm thi-----------------------------
------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------
-------------------------------------------------------------------bắt đầu truy vấn quản lý tài khoản nhân viên --------------------------------
--thông tin tài khoản
ALTER PROCEDURE thong_tin_tai_khoan
AS
BEGIN
    SELECT MANHANVIEN,TENNHANVIEN,GIOITINH,CONVERT(NVARCHAR(30),NGAYSINH,103) [ngaysinh],DIACHI,SDT,EMAIL,TENVAITRO,TENDANGNHAP,MATKHAU FROM dbo.NGUOIDUNG
	order by MANHANVIEN desc
END
EXEC dbo.thong_tin_tai_khoan

SELECT * FROM dbo.NGUOIDUNG
WHERE TENVAITRO =1

--thêm tài khoản kế toán
INSERT INTO dbo.NGUOIDUNG(TENDANGNHAP,MATKHAU,TENVAITRO)
VALUES(?,?,1)
GO
INSERT INTO dbo.NGUOIDUNG(TENDANGNHAP,MATKHAU,TENVAITRO)
VALUES(N'hahah',N'123',2)

--thêm tài khoản giảng viên
INSERT INTO dbo.NGUOIDUNG(TENDANGNHAP,MATKHAU,TENVAITRO)
VALUES(?,?,2)
--sửa thông tin tài khoản
UPDATE dbo.NGUOIDUNG SET TENDANGNHAP=?,MATKHAU=?,TENVAITRO=?
WHERE MANHANVIEN = ?

GO
--xóa tài khoản

CREATE PROCEDURE xoa_update_nguoi_dung(@manhanvien int)
AS
BEGIN
    DELETE FROM dbo.NGUOIDUNG 
	WHERE MANHANVIEN = @manhanvien
	declare @max int
	select @max=max(MANHANVIEN)from dbo.NGUOIDUNG
	if @max IS NULL   
	  SET @max = 0
	DBCC CHECKIDENT (NGUOIDUNG, RESEED,@max)
END
go
--tìm kiếm tài khoản nhân viên theo mã nhân viên

ALTER  PROCEDURE tim_kiem_tk_nhan_vien(@MATKNV INT)
AS
BEGIN
    SELECT MANHANVIEN,TENNHANVIEN,GIOITINH,CONVERT(NVARCHAR(30),NGAYSINH,103) [ngaysinh],DIACHI,SDT,EMAIL,TENVAITRO,TENDANGNHAP,MATKHAU FROM dbo.NGUOIDUNG
	WHERE MANHANVIEN LIKE LTRIM(RTRIM(@MATKNV))
END

EXEC dbo.tim_kiem_tk_nhan_vien @MATKNV = 1 -- nvarchar(40)
go
-------------------------------------------------------------------kế thúc truy vấn tài khoản nhân viên---------------------------------
SELECT * from dbo.NGUOIDUNG
WHERE TENDANGNHAP =? AND MATKHAU = ? AND TENVAITRO = 0
GO
UPDATE dbo.NGUOIDUNG SET MATKHAU = ? 
WHERE TENDANGNHAP = ? AND MATKHAU = ? AND TENVAITRO =0

INSERT INTO dbo.NGUOIDUNG(TENNHANVIEN,GIOITINH,NGAYSINH,DIACHI,SDT,EMAIL,TENVAITRO,TENDANGNHAP,MATKHAU)
VALUES(?,?,?,?,?,?,?,?,?,?)

UPDATE dbo.NGUOIDUNG SET TENNHANVIEN = ?,GIOITINH = ?,NGAYSINH = ?,DIACHI = ?,SDT = ?,EMAIL = ?,TENVAITRO = ?,TENDANGNHAP = ?,MATKHAU = ?
WHERE MANHANVIEN = ?

