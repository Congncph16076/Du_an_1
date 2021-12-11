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
	CAHOC NVARCHAR(60),
	HOCPHI FLOAT,
	NGAYNHAPHOC DATE,
	NGAYKETTHUC DATE,
	MACAPLOP INT,
	MALOAILOP INT,
	MANHANVIEN INT,
	TrangThai BIT,
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
	HOCPHINO FLOAT,
	CAHOC NVARCHAR(60),
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
SELECT * FROM dbo.BIENLAI

IF OBJECT_ID('BUOIHOC') IS NOT NULL
	DROP TABLE BUOIHOC
GO

CREATE TABLE BUOIHOC(
	MABUOIHOC INT IDENTITY(1,1),
	NGAYHOC DATE,
	CAHOC NVARCHAR(60),
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
VALUES (N'A'),(N'B'),(N'C')

DELETE FROM dbo.LOP
DBCC CHECKIDENT (LOP, RESEED,0)
SELECT* FROM dbo.LOP

INSERT INTO dbo.LOP(TENLOP,SISO,CAHOC,HOCPHI,NGAYNHAPHOC,NGAYKETTHUC,MACAPLOP,MALOAILOP,MANHANVIEN,TrangThai)
VALUES(N'Toeic cấp A',10,N'Ca 3 (12h-14h) 357',1000000.0,'2010/10/10','2011/01/10',1,1,2,1),
	  (N'Toeic cấp A',10,N'Ca 1 (7h-9h) 246',1000000.0,'2011/01/11','2011/04/11',1,1,3,0),
	  (N'Toeic cấp B',15,N'Ca 2 (9h-11h) 357',2500000.0,'2011/05/10','2011/09/10',2,1,4,1),
	  (N'Anh Văn Tổng Quát cấp A',10,N'Ca 2 (9h-11h) 246',1500000.0,'2011/05/10','2011/09/10',1,3,3,0),
	  (N'Anh Văn Tổng Quát cấp A',10,N'Ca 1 (7h-9h) 357',1500000.0,'2011/05/10','2011/09/10',1,3,2,0),
	  (N'Anh Văn Tổng Quát cấp B',15,N'Ca 2 (9h-11h) 246',3000000.0,'2011/10/10','2012/01/10',2,3,9,1),
	  (N'Anh Văn Tổng Quát cấp C',5,N'Ca 4 (14h-16h) 246',5000000.0,'2010/10/10','2011/01/10',3,3,7,1),
	  (N'Anh văn giao tiếp cấp C',5,N'Ca 1 (7h-9h) 357',5500000.0,'2010/10/10','2011/01/10',3,3,4,0),
	  (N'Anh Văn giao tiếp cấp B',15,N'Ca 3 (12h-14h) 246',3200000.0,'2011/05/10','2011/09/10',2,2,5,1),
	  (N'Anh Văn giao tiếp cấp A',10,N'Ca 1 (7h-9h) 246',1700000.0,'2011/05/10','2011/09/10',1,2,6,1)



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
	  ('2011/09/10',5,30,9,4),
	  ('2011/09/10',6,30,9,5),
	  ('2012/01/10',5,30,9,6),
	  ('2011/01/10',5,30,9,8),
	  ('2011/01/10',5,30,9,9),
	  ('2011/09/10',5,30,9,7),
	  ('2011/09/10',5,30,9,10)

DELETE FROM dbo.DANGKI
DBCC CHECKIDENT (DANGKI, RESEED,0)
SELECT* FROM dbo.DANGKI

	



INSERT INTO dbo.DANGKI(TENHOCVIEN,NGAYSINH,GIOITINH,SDT,EMAIL,DIACHI,TENCAPLOP,TENLOAILOP,HOCPHI,HOCPHINO,CAHOC,NGAYNHAPHOC,NGAYDANGKI,MAHOCVIEN)
VALUES
(N'Nguyễn Văn A','1999/01/01',1,'0324553946','A@gmail.com',N'Đạo Lý- Lý Nhân-Hà Nam','A','Toeic',1000000,0,'Ca 3 (12h-14h) 357','2010/10/10','2010/09/27',1),
(N'Nguyễn Văn B','1998/01/01',1,'0324553922','B@gmail.com',N'Đạo Lý- Lý Nhân-Hà Nam','A','Toeic',1000000,0,'Ca 3 (12h-14h) 357','2010/10/10','2010/09/27',2),
(N'Nguyễn Văn C','1997/01/21',1,'0324553921','C@gmail.com',N'Đạo Lý- Lý Nhân-Hà Nam','A','Toeic',1000000,0,'Ca 3 (12h-14h) 357','2010/10/10','2010/09/27',3),
(N'Nguyễn Văn D','1999/02/01',1,'0324555922','D@gmail.com',N'Đạo Lý- Lý Nhân-Hà Nam','A','Toeic',1000000,0,'Ca 3 (12h-14h) 357','2010/10/10','2010/09/27',4),
(N'Nguyễn Thị E','2001/11/01',0,'0324553932','E@gmail.com',N'Ninh Bình','A','Toeic',1000000,0,'Ca 3 (12h-14h) 357','2010/10/10','2010/09/27',5),
(N'Nguyễn Thị F','2001/11/01',0,'0324553945','E@gmail.com',N'Ninh Bình','A','Toeic',1000000,0,'Ca 3 (12h-14h) 357','2010/10/10','2010/09/27',6),
(N'Nguyễn Thu H','2001/11/01',0,'0812354781','E@gmail.com',N'Ninh Bình','A','Toeic',1000000,0,'Ca 3 (12h-14h) 357','2010/10/10','2010/09/27',7),
(N'Nguyễn Thu B','2001/11/01',0,'0812352281','E@gmail.com',N'Ninh Bình','A','Toeic',1000000,0,'Ca 3 (12h-14h) 357','2010/10/10','2010/09/27',8),
(N'Nguyễn Thu A','2001/11/01',0,'0812324781','E@gmail.com',N'Ninh Bình','A','Toeic',1000000,0,'Ca 3 (12h-14h) 357','2010/10/10','2010/09/27',9),
(N'Nguyễn Quỳnh','2001/11/01',0,'0822354781','E@gmail.com',N'Ninh Bình','A','Toeic',1000000,0,'Ca 3 (12h-14h) 357','2010/10/10','2010/09/27',10),

(N'Nguyễn Thị A','1999/01/01',0,'0324513946',N'A1@gmail.com',N'Đạo Lý- Lý NhâN-Hà Nam',N'A',N'Toeic',1000000.0,0,N'Ca 3 (12h-14h) 246','2012/03/10','2012/02/28',NULL),
(N'Nguyễn Thị B','1998/01/10',0,'0324213926',N'B1@gmail.com',N'Đạo Lý- Lý NhâN-Hà Nam',N'A',N'Toeic',1000000.0,0,N'Ca 2 (9h-7h) 357','2012/03/10','2012/02/28',NULL),
(N'Nguyễn Thị C','1997/01/01',1,'0324321126',N'C1@gmail.com',N'Đạo Lý- Lý NhâN-Hà Nam',N'A',N'Toeic',1000000.0,0,N'Ca 2 (9h-7h) 357','2012/03/10','2012/02/28',NULL),
(N'Nguyễn Văn A','1999/01/01',1,'0324553646',N'A@gmail.com',N'Đạo Lý- Lý Nhân-Hà Nam',N'B',N'Toeic',2000000.0,500000,N'Ca 3 (12h-14h) 246','2010/10/10','2010/09/30',1),
(N'Nguyễn Văn B','1999/02/01',1,'0324523956',N'B@gmail.com',N'Đạo Lý- Lý Nhân-Hà Nam',N'B',N'Toeic',2000000.0,1000000,N'Ca 3 (12h-14h) 246','2010/10/10','2010/09/30',2),
(N'Nguyễn Văn C','1999/04/01',1,'0324553916',N'C@gmail.com',N'Đạo Lý- Lý Nhân-Hà Nam',N'B',N'Toeic',2000000.0,500000,N'Ca 3 (12h-14h) 246','2010/10/10','2010/09/30',3)

INSERT INTO dbo.DANGKI(TENHOCVIEN,NGAYSINH,GIOITINH,SDT,EMAIL,DIACHI,TENCAPLOP,TENLOAILOP,HOCPHI,HOCPHINO,CAHOC,NGAYNHAPHOC,NGAYDANGKI,MAHOCVIEN)
VALUES(N'Nguyễn Văn D','1999/02/01',1,'0841509943','D@gmail.com',N'Hà Tây','B','Anh văn giao tiếp',2000000,500000,'Ca 3 (12h-14h) 246','2012/01/10','2011/12/20',4)
INSERT INTO dbo.DANGKI(TENHOCVIEN,NGAYSINH,GIOITINH,SDT,EMAIL,DIACHI,TENCAPLOP,TENLOAILOP,HOCPHI,HOCPHINO,CAHOC,NGAYNHAPHOC,NGAYDANGKI,MAHOCVIEN)
VALUES(N'Nguyễn Văn Công','1998/02/01',1,'0841632243','congcc@gmail.com',N'Hà Tây','B','Anh văn giao tiếp',2000000,500000,'Ca 3 (12h-14h) 246','2012/01/10','2012/01/01',NULL)
INSERT INTO dbo.DANGKI(TENHOCVIEN,NGAYSINH,GIOITINH,SDT,EMAIL,DIACHI,TENCAPLOP,TENLOAILOP,HOCPHI,HOCPHINO,CAHOC,NGAYNHAPHOC,NGAYDANGKI,MAHOCVIEN)
VALUES(N'Nguyễn Văn Chiến','1997/11/01',1,'0841632343','chiencc@gmail.com',N'Hà Tây','B','Anh văn giao tiếp',2000000,500000,'Ca 3 (12h-14h) 357','2012/01/10','2012/01/01',NULL)

DELETE FROM dbo.BIENLAI
DBCC CHECKIDENT (BIENLAI, RESEED,0)
SELECT* FROM dbo.BIENLAI

INSERT INTO dbo.BIENLAI(THANHTIEN,MAHOCVIEN,MALOP,MANHANVIEN,MADANGKI,MADOTTHI,DIEMTHI,DIEMTHANHPHAN,DIEMTONG,NGAYTHUTIEN)
VALUES(100000,1,1,10,1,1,8,0,8,'2010/09/27'),
	  (100000,2,1,10,2,1,8,0,8,'2010/09/27'),
	  (100000,3,1,10,3,1,3,2,5,'2010/09/27'),
	  (100000,4,1,10,4,1,4,0,4,'2010/09/27'),
	  (100000,5,1,10,5,1,4,0,4,'2010/09/27'),
	  (100000,6,1,10,6,1,4,0,4,'2010/09/27'),
	  (100000,7,1,10,7,1,4,0,4,'2010/09/27'),
	  (100000,8,1,10,8,1,4,0,4,'2010/09/27'),
	  (100000,9,1,10,9,1,4,0,4,'2010/09/27'),
	  (100000,10,1,10,10,1,4,0,4,'2010/09/27'),
	  (100000,NULL,null,10,11,NULL,NULL,NULL,NULL,'2012/02/28'),
	  (100000,null,null,10,12,NULL,NULL,NULL,NULL,'2012/02/28'),
	  (100000,null,null,10,13,NULL,NULL,NULL,NULL,'2012/02/28'),
	  (1500000,1,NULL,12,14,NULL,NULL,NULL,NULL,'2010/09/30'),
	  (1000000,2,null,13,15,NULL,NULL,NULL,NULL,'2010/09/30'),
	  (1500000,3,null,11,16,NULL,NULL,NULL,NULL,'2010/09/30'),
	  (1500000,4,NULL,11,17,NULL,NULL,NULL,NULL,'2011/12/20'),
	  (1500000,NULL,NULL,11,18,NULL,NULL,NULL,NULL,'2012/01/01'),
	  (1500000,NULL,NULL,11,19,NULL,NULL,NULL,NULL,'2012/01/01')

DELETE FROM dbo.BUOIHOC
DBCC CHECKIDENT (BUOIHOC, RESEED,0)
SELECT* FROM dbo.BUOIHOC

INSERT INTO dbo.BUOIHOC(NGAYHOC,CAHOC,GHICHU,MALOPHOC)
VALUES('2010/10/10',N'Ca 3 (12h-14h) 357',N'Đủ',1),
	  ('2011/01/11',N'Ca 1 (7h-9h) 246',N'Đủ',2),
	  ('2011/05/10',N'Ca 2 (9h-11h) 357',N'Đủ',3),
	  ('2011/05/10',N'Ca 2 (9h-11h) 246',N'Đủ',4),
	  ('2011/05/10',N'Ca 1 (7h-9h) 357',N'Đủ',5),
	  ('2011/05/10',N'Ca 3 (12h-14h) 246',N'Đủ',9),
	  ('2011/05/10',N'Ca 1 (7h-9h) 246',N'Đủ',10),
	  ('2010/10/10',N'Ca 2 (9h-11h) 246',N'Đủ',6),
	  ('2010/10/10',N'Ca 4 (14h-16h) 246',N'Đủ',7),
	  ('2010/10/10',N'Ca 1 (7h-9h) 357',N'Đủ',8)


DELETE FROM dbo.DIEMDANH
DBCC CHECKIDENT (DIEMDANH, RESEED,0)
SELECT* FROM dbo.DIEMDANH

INSERT INTO dbo.DIEMDANH
(TRANGTHAI,GHICHU,MABUOIHOC,MABIENLAI)
VALUES(1,N'Đủ',1,1),
	  (1,N'Đủ',1,2),
	  (1,N'Đủ',1,3)
insert into dbo.DIEMDANH(TRANGTHAI,GHICHU,MABUOIHOC,MABIENLAI)
VALUES(1,N'Đủ',1,4)
-----------------------------------------------------------------------bắt đầu truy vấn  học viên ----------------------------------------------
--Thủ tục lưu quản lý học viên
CREATE PROC thong_tin_sv
AS
BEGIN
    SELECT MAHOCVIEN,TENHOCVIEN,lop.MALOP,dbo.LOP.TENLOP,GIOITINH,CONVERT(nvarchar(30),NGAYSINH,103) [ngaysinh],SDT,EMAIL,DIACHI,HOCPHINO,SOBUOINGHI FROM  dbo.HOCVIEN
	JOIN dbo.LOP ON LOP.MALOP = HOCVIEN.MALOP
	ORDER BY MAHOCVIEN DESC
END
DROP PROC dbo.thong_tin_sv
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
CREATE PROCEDURE tim_kiem_hoc_vien_theo_ten(@tensinhvien nvarchar(100))
AS
BEGIN
    SELECT MAHOCVIEN,TENHOCVIEN,dbo.LOP.malop,dbo.LOP.TENLOP,GIOITINH,CONVERT(nvarchar(50),ngaysinh,103) [ngaysinh],SDT,EMAIL,DIACHI,HOCPHINO,SOBUOINGHI FROM  dbo.HOCVIEN
	JOIN dbo.LOP ON LOP.MALOP = HOCVIEN.MALOP
	WHERE TENHOCVIEN like LTRIM(RTRIM(@tensinhvien))
	ORDER BY MAHOCVIEN DESC
END
drop proc tim_kiem_hoc_vien_theo_ten
EXEC dbo.tim_kiem_hoc_vien_theo_ten @tensinhvien = N'    %văn%      '
go


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
    SELECT MALOP,TENLOP,LOP.MANHANVIEN,TENNHANVIEN
	,HOCPHI,CAHOC,SISO,TENLOAILOP,TENCAPLOP
	,CONVERT(NVARCHAR(10),NGAYNHAPHOC,103) [ngaynhaphoc]
	,CONVERT(NVARCHAR(10),NGAYKETTHUC,103) [ngayketthuc],SISO,TrangThai FROM dbo.LOP
	JOIN dbo.CAPLOP ON CAPLOP.MACAPLOP = LOP.MACAPLOP
	JOIN dbo.LOAILOP ON LOAILOP.MALOAILOP = LOP.MALOAILOP
	JOIN dbo.NGUOIDUNG ON NGUOIDUNG.MANHANVIEN = LOP.MANHANVIEN
	ORDER BY MALOP DESC
	
END
EXEC dbo.thong_tin_lop
DROP PROC dbo.thong_tin_lop
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
DROP PROC dbo.xoa_update_lop
EXEC dbo.xoa_update_lop  @malop = 11 -- int
GO

CREATE PROC xoa_update_loai_lop(@maloailop int)
AS
BEGIN
    DELETE FROM dbo.LOAILOP
	WHERE MALOAILOP = @maloailop
	declare @max int
	select @max=max(MALOAILOP)from dbo.LOAILOP
	if @max IS NULL   
	  SET @max = 0
	DBCC CHECKIDENT (LOAILOP, RESEED,@max)
END
EXEC dbo.xoa_update_loai_lop @maloailop = 4 -- int
GO

CREATE PROC xoa_update_cap_lop(@macaplop INT)
AS
BEGIN
    DELETE FROM dbo.CAPLOP
	WHERE MACAPLOP = @macaplop
	declare @max int
	select @max=max(MACAPLOP)from dbo.CAPLOP
	if @max IS NULL   
	  SET @max = 0
	DBCC CHECKIDENT (CAPLOP, RESEED,@max)
END

EXEC dbo.xoa_update_cap_lop @macaplop = 4 -- int

SELECT * FROM dbo.LOAILOP
WHERE MALOAILOP = ?
SELECT* FROM dbo.CAPLOP
WHERE MACAPLOP = ?
SELECT * FROM dbo.LOP

-- tìm lớp
CREATE PROCEDURE tim_kiem_lop_theo_ten_lop(@tenlop NVARCHAR(50))
AS
BEGIN
    SELECT MALOP,TENLOP,LOP.MANHANVIEN,TENNHANVIEN,HOCPHI,CAHOC
	,SISO,TENLOAILOP,TENCAPLOP,CONVERT(NVARCHAR(10),NGAYNHAPHOC,103) [ngaynhaphoc]
	,CONVERT(NVARCHAR(10),NGAYKETTHUC,103) [ngayketthuc],SISO,TrangThai FROM dbo.LOP
	JOIN dbo.CAPLOP ON CAPLOP.MACAPLOP = LOP.MACAPLOP
	JOIN dbo.LOAILOP ON LOAILOP.MALOAILOP = LOP.MALOAILOP
	JOIN dbo.NGUOIDUNG ON NGUOIDUNG.MANHANVIEN = LOP.MANHANVIEN
	WHERE TENLOP LIKE LTRIM(RTRIM(@tenlop))
	ORDER BY MALOP DESC
END
DROP PROC dbo.tim_kiem_lop_theo_ten_lop
EXEC dbo.tim_kiem_lop_theo_ten_lop @tenlop = N'  %toeic%   '
GO

CREATE PROCEDURE click_table_lop(@malop int)
AS
BEGIN
    SELECT MALOP,TENLOP,LOP.MANHANVIEN,TENNHANVIEN,HOCPHI,CAHOC
	,SISO,TENLOAILOP,TENCAPLOP,CONVERT(NVARCHAR(10),NGAYNHAPHOC,103) [ngaynhaphoc]
	,CONVERT(NVARCHAR(10),NGAYKETTHUC,103) [ngayketthuc],SISO,TrangThai FROM dbo.LOP
	JOIN dbo.CAPLOP ON CAPLOP.MACAPLOP = LOP.MACAPLOP
	JOIN dbo.LOAILOP ON LOAILOP.MALOAILOP = LOP.MALOAILOP
	JOIN dbo.NGUOIDUNG ON NGUOIDUNG.MANHANVIEN = LOP.MANHANVIEN
	WHERE MALOP LIKE LTRIM(RTRIM(@malop))
	ORDER BY MALOP DESC
END
DROP PROC click_table_lop
EXEC click_table_lop @malop =1


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
GO
SELECT * FROM dbo.DOTTHI
EXEC dbo.xoa_update_dot_thi @madotthi = 21 -- int


--tìm kiếm đợt thi
CREATE PROCEDURE tim_kiem_dot_thi(@ngaythi NVARCHAR(20))
AS
BEGIN
   SELECT MADOTTHI,LOP.MALOP,TENLOP,CONVERT(NVARCHAR(20),NGAYTHI,103) [ngaythi],cathi,dbo.LOP.SISO,VANG FROM dbo.DOTTHI
	join dbo.LOP on LOP.MALOP = DOTTHI.MALOP
	WHERE CONVERT(NVARCHAR(20),NGAYTHI,103) LIKE LTRIM(RTRIM(@ngaythi)) 
	ORDER BY MADOTTHI desc
END
go
DROP PROC dbo.tim_kiem_dot_thi
EXEC dbo.tim_kiem_dot_thi @ngaythi ='10/01/2011'
GO
SELECT * FROM dbo.DOTTHI
GO


CREATE PROCEDURE click_ngay(@madothi int)
AS
BEGIN
     SELECT MADOTTHI,LOP.MALOP,TENLOP,CONVERT(NVARCHAR(20),NGAYTHI,103) [ngaythi],cathi,dbo.LOP.SISO,VANG FROM dbo.DOTTHI
	join dbo.LOP on LOP.MALOP = DOTTHI.MALOP
	WHERE MADOTTHI = @madothi
	ORDER BY MADOTTHI desc
END
EXEC dbo.click_ngay @madothi = 1 -- int

go
------------------------------------------------------------ kết thúc truy vấn đợt thi----------------------------------------------------
------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------
-------------------------------------------------------------bắt đầu truy vấn biên lai-----------------
-- thông tin biên lai

INSERT INTO dbo.BIENLAI(THANHTIEN,MAHOCVIEN,MADANGKI,NGAYTHUTIEN)
SELECT (HOCPHI-hocphino) [thanh tien],MAHOCVIEN,madangki,NGAYDANGKI  FROM dbo.DANGKI
WHERE madangki NOT IN(SELECT madangki FROM dbo.BIENLAI)

GO
DROP PROCEDURE xoa_update_bien_lai
CREATE PROCEDURE xoa_update_bien_lai(@mabienlai int)
AS
BEGIN
	DELETE  FROM dbo.BIENLAI
	WHERE MABIENLAI = @mabienlai
	declare @max int
	select @max=max(MABIENLAI)from dbo.BIENLAI
	if @max IS NULL   
	  SET @max = 0
	DBCC CHECKIDENT (BIENLAI, RESEED,@max)
END
GO
EXEC xoa_update_bien_lai @mabienlai = 7




DROP PROC thong_tin_bien_lai_moi
CREATE PROCEDURE thong_tin_bien_lai_moi
AS
BEGIN
    SELECT MABIENLAI,BIENLAI.MADANGKI,BIENLAI.MAHOCVIEN,dbo.DANGKI.TENHOCVIEN
	,THANHTIEN
	,MANHANVIEN
	,CONVERT(NVARCHAR(20),NGAYTHUTIEN,103) [ngaythutien]
	FROM dbo.BIENLAI
	JOIN dbo.DANGKI ON DANGKI.madangki = BIENLAI.MADANGKI
	WHERE BIENLAI.MALOP IS NULL 
	ORDER BY MABIENLAI desc
END
GO
DROP PROC thong_tin_bien_lai_moi
EXEC thong_tin_bien_lai_moi

INSERT INTO dbo.BIENLAI(THANHTIEN,MANHANVIEN,MADANGKI,NGAYTHUTIEN)
VALUES(1400000,12,8,'2011/01/12')
GO

INSERT INTO dbo.BIENLAI(THANHTIEN,MANHANVIEN,MADANGKI,NGAYTHUTIEN)
VALUES(?,?,?,?)
GO

SELECT * FROM dbo.BIENLAI
-----sủa db
UPDATE dbo.BIENLAI SET THANHTIEN = ?,MANHANVIEN = ?,MADANGKI = ?,NGAYTHUTIEN =?
WHERE MABIENLAI =?
go
CREATE PROC click_table_HV_moi(@maBienLai INT)
AS
BEGIN
	DECLARE @x INT 
	SELECT @x=MALOP FROM dbo.BIENLAI
	WHERE MABIENLAI = @maBienLai 
	IF @x IS not NULL

     SELECT MABIENLAI,BIENLAI.MADANGKI,TENHOCVIEN,LOP.HOCPHI,HOCPHINO=(LOP.HOCPHI-THANHTIEN),THANHTIEN
	,CONVERT(NVARCHAR(20),NGAYTHUTIEN,103) [ngaythutien]
	,BIENLAI.MANHANVIEN
	FROM dbo.BIENLAI
	JOIN dbo.DANGKI ON DANGKI.madangki = BIENLAI.MADANGKI
	JOIN dbo.LOP  on LOP.MALOP = BIENLAI.MALOP
	WHERE MABIENLAI = @maBienLai
	ORDER BY MABIENLAI DESC
	ELSE
    SELECT MABIENLAI,BIENLAI.MADANGKI,TENHOCVIEN,THANHTIEN
	,CONVERT(NVARCHAR(20),NGAYTHUTIEN,103) [ngaythutien]
	,BIENLAI.MANHANVIEN
	FROM dbo.BIENLAI
	JOIN dbo.DANGKI ON DANGKI.madangki = BIENLAI.MADANGKI
	
	WHERE BIENLAI.MAHOCVIEN IS NULL AND MABIENLAI = @maBienLai
END
DROP PROC click_table_HV_moi

EXEC dbo.click_table_HV_moi @maBienLai = 12
GO
SELECT * FROM dbo.BIENLAI
CREATE PROC tim_Kiem_HV_Moi(@ngaythu NVARCHAR(20))
AS
BEGIN
    SELECT MABIENLAI,BIENLAI.MADANGKI,TENHOCVIEN,HOCPHI,HOCPHINO=(HOCPHI-THANHTIEN),THANHTIEN
	,CONVERT(NVARCHAR(20),NGAYTHUTIEN,103) [ngaythutien]
	,MANHANVIEN
	FROM dbo.BIENLAI
	JOIN dbo.DANGKI ON DANGKI.madangki = BIENLAI.MADANGKI

	WHERE BIENLAI.MAHOCVIEN IS NULL AND CONVERT(NVARCHAR(20),NGAYTHUTIEN,103) = @ngaythu
END

DROP PROC tim_Kiem_HV_Moi
EXEC dbo.tim_Kiem_HV_Moi @ngaythu = '28/02/2012'


SELECT dbo.BIENLAI.MAHOCVIEN,TENHOCVIEN,BIENLAI.MALOP,DIEMTHI , DIEMTHANHPHAN , DIEMTONG= (DIEMTHI+DIEMTHANHPHAN) FROM dbo.BIENLAI
JOIN dbo.DOTTHI ON DOTTHI.MADOTTHI = BIENLAI.MADOTTHI
JOIN dbo.HOCVIEN ON HOCVIEN.MAHOCVIEN = BIENLAI.MAHOCVIEN
 JOIN lop ON LOP.MALOP = BIENLAI.MALOP
 WHERE TENLOP = N'Toeic cấp A'

 SELECT * FROM dbo.BIENLAI
 SELECT * FROM dbo.HOCVIEN
-- thêm hóa đơn
INSERT INTO dbo.BIENLAI(THANHTIEN,MAHOCVIEN,MANHANVIEN,MADANGKI,NGAYTHUTIEN)
VALUES(?,?,?,?,?)
-- sửa hóa đơn
UPDATE dbo.BIENLAI SET THANHTIEN =? ,MAHOCVIEN =?,MANHANVIEN =?,MADANGKI =?,NGAYTHUTIEN =?
WHERE MABIENLAI = ?
--xóa biên lai
DELETE FROM dbo.BIENLAI
WHERE MADANGKI =?
GO



SELECT * FROM dbo.buoihoc
DROP PROC dbo.tim_lop_chuyen
--------- thong tin bl
CREATE PROCEDURE thong_tin_bien_lai_hvcu
AS
BEGIN
    SELECT MABIENLAI,BIENLAI.MADANGKI,BIENLAI.MAHOCVIEN,dbo.DANGKI.TENHOCVIEN
	,THANHTIEN,TENLOP,LOP.HOCPHI,LOP.HOCPHI-THANHTIEN AS hocphino
	,BIENLAI.MANHANVIEN
	,CONVERT(NVARCHAR(20),NGAYTHUTIEN,103) [ngaythutien]
	FROM dbo.BIENLAI
	JOIN dbo.DANGKI ON DANGKI.madangki = BIENLAI.MADANGKI
	JOIN lop ON lop.MALOP=bienlai.MALOP
	WHERE BIENLAI.MALOP IS NOT NULL 
	ORDER BY MABIENLAI desc
	
END
EXEC dbo.thong_tin_bien_lai_hvcu
SELECT * FROM dbo.BIENLAI
DROP PROC dbo.thong_tin_bien_lai_hvcu


--tìm kiếm biên lai
CREATE PROCEDURE tim_kiem_bien_laiCu(@mabienlai INT)
AS
BEGIN
    SELECT MABIENLAI,BIENLAI.MADANGKI,BIENLAI.MAHOCVIEN,dbo.DANGKI.TENHOCVIEN
	,THANHTIEN
	,MANHANVIEN
	,CONVERT(NVARCHAR(20),NGAYTHUTIEN,103) [ngaythutien]
	FROM dbo.BIENLAI

	JOIN dbo.DANGKI ON DANGKI.madangki = BIENLAI.MADANGKI
	WHERE MABIENLAI LIKE LTRIM(RTRIM(@mabienlai))
END
GO
DROP PROCEDURE dbo.tim_kiem_bien_laiCu
EXEC dbo.tim_kiem_bien_laiCu @mabienlai = 4 -- int

SELECT *FROM dbo.DANGKI
SELECT * FROM dbo.BIENLAI
--------------------------------------------------------
CREATE PROCEDURE tim_kiem_bien_laiCu_theo_Ngay(@tenhv NVARCHAR(20))
AS
BEGIN
    SELECT MABIENLAI,BIENLAI.MAHOCVIEN,dbo.DANGKI.TENHOCVIEN,TENLOP
	,LOP.HOCPHI,HOCPHINO =(dbo.lop.HOCPHI-THANHTIEN),THANHTIEN
	,BIENLAI.MANHANVIEN
	,CONVERT(NVARCHAR(20),NGAYTHUTIEN,103) [ngaythutien]
	FROM dbo.BIENLAI
	JOIN dbo.HOCVIEN ON HOCVIEN.MAHOCVIEN = BIENLAI.MAHOCVIEN
	JOIN dbo.DANGKI ON DANGKI.madangki = BIENLAI.MADANGKI
	JOIN lop ON LOP.MALOP = BIENLAI.MALOP
	WHERE DANGKI.TENHOCVIEN =  @tenhv
END
GO
DROP PROC tim_kiem_bien_laiCu_theo_Ngay
EXEC tim_kiem_bien_laiCu_theo_Ngay @ngaythu = '30/09/2010'
GO

SELECT MABIENLAI,MALOP,TENHOCVIEN,MADOTTHI,DIEMTHI,DIEMTHANHPHAN,DIEMTONG=(DIEMTHI+DIEMTHANHPHAN) FROM dbo.BIENLAI
JOIN dbo.DANGKI ON DANGKI.madangki = BIENLAI.MADANGKI
WHERE (DIEMTHI+DIEMTHANHPHAN) IS  NULL AND MABIENLAI =12
UPDATE dbo.BIENLAI SET MALOP=null,MADOTTHI=null,DIEMTHI=null,DIEMTHANHPHAN=null,DIEMTONG=null
WHERE MABIENLAI = 12
GO

SELECT MABIENLAI,BIENLAI.MADANGKI,TENHOCVIEN,BIENLAI.MAHOCVIEN,TENLOAILOP,TENCAPLOP,HOCPHI,HOCPHINO=HOCPHI-THANHTIEN,THANHTIEN
,CONVERT(NVARCHAR(20),NGAYTHUTIEN,103) [ngaythutien] FROM dbo.BIENLAI
JOIN dbo.DANGKI ON DANGKI.madangki = BIENLAI.MADANGKI
WHERE MABIENLAI =?

------------------------------------------------------kết thúc truy vấn biên lai--------------------------------------------------
-----------------------------------------------------------------------------

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

CREATE  PROCEDURE tim_kiem_tk_nhan_vien(@MATKNV INT)
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

GO
SELECT TENLOP,COUNT(dbo.LOP.MALOP),CAHOC FROM dbo.HOCVIEN
JOIN lop ON LOP.MALOP = HOCVIEN.MALOP
GROUP BY LOP.MALOP,HOCVIEN.MALOP,TENLOP,CAHOC
HAVING LOP.MALOP = HOCVIEN.MALOP
GO
---- truy vấn đăng kí
CREATE PROC hoc_vien_moi
AS
BEGIN
    SELECT madangki,TENHOCVIEN,CONVERT(NVARCHAR(20),GIOITINH,103) [ngaysinh],GIOITINH,sdt,EMAIL,DIACHI,
	TENLOAILOP,TENCAPLOP,HOCPHI,CAHOC,CONVERT(NVARCHAR(20),NGAYNHAPHOC,103)[ngaynhaphoc]
	,CONVERT(NVARCHAR(20),NGAYDANGKI,103) [ngaydangki],MAHOCVIEN FROM dbo.DANGKI
	WHERE MAHOCVIEN is NULL
END
go

CREATE PROCEDURE hoc_vien_cu
AS
BEGIN
    SELECT madangki,TENHOCVIEN,CONVERT(NVARCHAR(20),GIOITINH,103) [ngaysinh],GIOITINH,sdt,EMAIL,DIACHI,
	TENLOAILOP,TENCAPLOP,HOCPHI,CAHOC,CONVERT(NVARCHAR(20),NGAYNHAPHOC,103)[ngaynhaphoc]
	,CONVERT(NVARCHAR(20),NGAYDANGKI,103) [ngaydangki],MAHOCVIEN FROM dbo.DANGKI
	WHERE MAHOCVIEN IS NOT NULL
	SELECT MAHOCVIEN,TENHOCVIEN,SDT,MALOP FROM dbo.HOCVIEN
	WHERE TENHOCVIEN = N'nguyễn văn C'
END
-- truy vấn xếp lớp by mr vinh
create procedure xep_lop(@madangki int)
as
begin
	declare @malop int,@b int,@c int, @ngaydki date, @ngaynhaphoc date

	select @malop= MALOP from LOP
	where MALOAILOP=(select MALOAILOP from LOAILOP where TENLOAILOP =( select  TENLOAILOP	from DANGKI	where madangki =  @madangki ))
		 and MACAPLOP=(select MACAPLOP	from CAPLOP	where TENCAPLOP = (select   TENCAPLOP	from DANGKI	where madangki =  @madangki) )
		 and CAHOC=(select CAHOC from DANGKI	where madangki =  @madangki )

		select @c=siso, @ngaynhaphoc=NGAYNHAPHOC from LOP
		where MALOP=@malop

		select @ngaydki=NGAYDANGKI from DANGKI
		where madangki=@madangki

		SELECT @b =COUNT(MAHOCVIEN)
		FROM HOCVIEN
		where MALOP=@malop

		if @b<@c and @malop is not null and @ngaydki<@ngaynhaphoc
		update BIENLAI set MALOP= @malop where MADANGKI = @madangki
		
		
END
EXEC dbo.xep_lop @madangki = 20 -- int
SELECT * FROM dbo.BIENLAI
---------------------------------------------------------

create procedure themHVDK(@mabienlai int)
	as
	BEGIN
		declare @thanhtien float, @hocphi float,@malop int, @ten nvarchar(50), @gioitinh int, @ngaysinh date, @diachi nvarchar(100),@sdt nvarchar(20), @email nvarchar(50)
							
		select @thanhtien=THANHTIEN,@malop=MALOP from BIENLAI
		where MABIENLAI=@mabienlai
		select @hocphi=HOCPHI from lop
		where MALOP=@malop
	
		select @ten=TENHOCVIEN,@gioitinh=GIOITINH,@ngaysinh= NGAYSINH,@diachi= DIACHI,@sdt= SDT,@email= email
		from DANGKI join BIENLAI on BIENLAI.MADANGKI=DANGKI.madangki
		where MABIENLAI=@mabienlai
		insert into HOCVIEN(TENHOCVIEN,MALOP,GIOITINH,NGAYSINH,DIACHI,SDT,HOCPHINO,EMAIL,SOBUOINGHI)
		values(@ten,@malop,@gioitinh,@ngaysinh,@diachi,@sdt,@hocphi-@thanhtien,@email,0)
		UPDATE bienlai SET MAHOCVIEN=(SELECT MAHOCVIEN FROM dbo.HOCVIEN WHERE TENHOCVIEN=@ten AND MALOP=@malop AND GIOITINH=@gioitinh AND NGAYSINH=@ngaysinh AND EMAIL=@email)
		WHERE MABIENLAI= @mabienlai
	END
	SELECT * FROM dbo.DANGKI
	SELECT * FROM dbo.BIENLAI
	EXEC themHVDK 11
	DROP PROC themhvdk

------
CREATE PROCEDURE xoa_ban_dang_ki(@madangki INT)
AS
BEGIN
	delete from BIENLAI
	where madangki = @madangki
    DELETE FROM dbo.DANGKI
	WHERE madangki = @madangki
	declare @max int
	select @max=max(@madangki)from dbo.DANGKI
	if @max IS NULL   
	  SET @max = 0
	DBCC CHECKIDENT (dangki, RESEED,@max)
END
GO
exec xoa_ban_dang_ki 1
drop proc xoa_ban_dang_ki
--thông tin đăng kí
create PROCEDURE thong_tin_dang_ki
AS
BEGIN
    SELECT madangki,TENHOCVIEN,CONVERT(NVARCHAR(30),NGAYSINH,103) [ngaysinh]
	,GIOITINH,SDT,EMAIL,DIACHI
	,TENCAPLOP,TENLOAILOP,CAHOC
	,CONVERT(NVARCHAR(30),NGAYNHAPHOC,103) [ngaynhaphoc]
	,CONVERT(NVARCHAR(30),NGAYDANGKI,103) [ngaydangki], MAHOCVIEN 
	FROM DANGKI
	order by madangki desc
END
go
exec dbo.thong_tin_dang_ki
drop proc thong_tin_dang_ki
-- tìm đăng kí
CREATE PROCEDURE tim_kiem_ban_dang_ki_theo_tendangki(@tendangki NVARCHAR(50))
AS
BEGIN
    SELECT madangki,TENHOCVIEN
	,CONVERT(NVARCHAR(30),NGAYSINH,103) [ngaysinh]
	,GIOITINH,SDT,EMAIL
	,DIACHI,TENCAPLOP,TENLOAILOP
	,HOCPHI,CAHOC,CONVERT(NVARCHAR(30)
	,NGAYNHAPHOC,103) [ngaynhaphoc]
	,CONVERT(NVARCHAR(30),NGAYDANGKI,103) [ngaydangki]
	, MAHOCVIEN FROM dbo.DANGKI
	WHERE TENHOCVIEN like LTRIM(RTRIM(@tendangki))
	ORDER BY madangki DESC
END
go
drop proc tim_kiem_ban_dang_ki_theo_tendangki
---------------------------------------------------------------------------Sua
CREATE PROCEDURE tim_kiem_ban_dang_ki_theo_mdk(@mdk int)
AS
BEGIN
    SELECT madangki,TENHOCVIEN
	,CONVERT(NVARCHAR(30),NGAYSINH,103) [ngaysinh]
	,GIOITINH,SDT,EMAIL,DIACHI
	,TENCAPLOP,TENLOAILOP
	,CAHOC
	,CONVERT(NVARCHAR(30),NGAYNHAPHOC,103) [ngaynhaphoc]
	,CONVERT(NVARCHAR(30),NGAYDANGKI,103) [ngaydangki]
	, MAHOCVIEN FROM dbo.DANGKI
	WHERE madangki = @mdk
	ORDER BY madangki DESC
END
drop proc tim_kiem_ban_dang_ki_theo_mdk

SELECT * FROM dbo.DANGKI
SELECT malop, COUNT(MALOP) [soluong] FROM dbo.HOCVIEN
GROUP BY MALOP
SELECT * FROM dbo.HOCVIEN
SELECT * FROM dbo.LOP
SELECT * FROM dbo.BIENLAI
GO
-------------------------truy vấn buổi học
CREATE PROC thong_tin_buoi_hoc
AS
BEGIN
    SELECT MABUOIHOC,MALOPHOC,TENLOP,CONVERT(NVARCHAR(20),NGAYHOC,103)[ngayhoc],BUOIHOC.CAHOC,GHICHU FROM dbo.BUOIHOC
	JOIN dbo.LOP ON LOP.MALOP = BUOIHOC.MALOPHOC
	ORDER BY MABUOIHOC DESC
END
DROP PROC thong_tin_buoi_hoc
EXEC dbo.thong_tin_buoi_hoc
GO
INSERT INTO dbo.BUOIHOC(NGAYHOC,CAHOC,GHICHU,MALOPHOC)
VALUES(?,?,?,?)
GO
UPDATE dbo.BUOIHOC SET NGAYHOC=?,CAHOC=?,GHICHU=?,MALOPHOC=?
WHERE MABUOIHOC =?
GO

SELECT MABUOIHOC,MALOPHOC,TENLOP,CONVERT(NVARCHAR(20),NGAYHOC,103)[ngayhoc],BUOIHOC.CAHOC,GHICHU FROM dbo.BUOIHOC
JOIN dbo.LOP ON LOP.MALOP = BUOIHOC.MALOPHOC
WHERE MABUOIHOC= 1
ORDER BY MABUOIHOC DESC


CREATE PROC tim_kiem_buoi_hoc_theo_ngay(@ngayhoc NVARCHAR(20))
AS
BEGIN
    SELECT MABUOIHOC,MALOPHOC,TENLOP,CONVERT(NVARCHAR(20),NGAYHOC,103)[ngayhoc],BUOIHOC.CAHOC,GHICHU FROM dbo.BUOIHOC
	JOIN dbo.LOP ON LOP.MALOP = BUOIHOC.MALOPHOC
	WHERE CONVERT(NVARCHAR(20),NGAYHOC,103) =@ngayhoc
	ORDER BY MABUOIHOC DESC
END
EXEC dbo.tim_kiem_buoi_hoc_theo_ngay @ngayhoc = N'10/10/2010' -- nvarchar(20)

DROP PROC dbo.tim_kiem_buoi_hoc_theo_ngay
GO
---truy vấn điểm danh
CREATE PROC thong_tin_diem_danh
AS
BEGIN
    SELECT MADIEMDANH,TRANGTHAI,MAHOCVIEN
	,CONVERT(NVARCHAR(20),BUOIHOC.NGAYHOC,103)[ngayhoc],BUOIHOC.CAHOC 
	,dbo.DIEMDANH.GHICHU,dbo.DIEMDANH.MABUOIHOC,BIENLAI.MABIENLAI FROM dbo.DIEMDANH
	 JOIN dbo.BUOIHOC ON BUOIHOC.MABUOIHOC = DIEMDANH.MABUOIHOC
	 JOIN dbo.BIENLAI ON BIENLAI.MABIENLAI = DIEMDANH.MABIENLAI 
	 ORDER BY MADIEMDANH DESC
END
DROP PROC dbo.thong_tin_diem_danh
EXEC dbo.thong_tin_diem_danh
SELECT * FROM dbo.DIEMDANH
INSERT INTO dbo.DIEMDANH(TRANGTHAI,GHICHU,MABUOIHOC,MABIENLAI)
VALUES(?,?,?,?)

UPDATE dbo.DIEMDANH SET TRANGTHAI=?,GHICHU=?,MABUOIHOC=?,MABIENLAI=?
WHERE MADIEMDANH = ?
GO

CREATE PROC tim_kiem_diem_danh_theo_ten_hoc_vien(@ngayhoc NVARCHAR(20))
AS
BEGIN
    SELECT MADIEMDANH,TRANGTHAI
	,CONVERT(NVARCHAR(20),NGAYHOC,103)[ngayhoc],BUOIHOC.CAHOC,MAHOCVIEN 
	,dbo.DIEMDANH.GHICHU,dbo.DIEMDANH.MABUOIHOC,BIENLAI.MABIENLAI FROM dbo.DIEMDANH
	 JOIN dbo.BUOIHOC ON BUOIHOC.MABUOIHOC = DIEMDANH.MABUOIHOC
	 JOIN dbo.BIENLAI ON BIENLAI.MABIENLAI = DIEMDANH.MABIENLAI 
	 WHERE CONVERT(NVARCHAR(20),NGAYHOC,103) =@ngayhoc
	 ORDER BY MADIEMDANH DESC
END
DROP PROC dbo.tim_kiem_diem_danh_theo_ten_hoc_vien

EXEC dbo.tim_kiem_diem_danh_theo_ten_hoc_vien @tenhocvien = N'%A% ' -- nvarchar(20)

---click table
CREATE PROC clickTableDiemDanh(@maDiemDanh int)
AS
BEGIN
    SELECT MADIEMDANH,TRANGTHAI
	,CONVERT(NVARCHAR(20),NGAYHOC,103)[ngayhoc],BUOIHOC.CAHOC 
	,dbo.DIEMDANH.GHICHU,dbo.DIEMDANH.MABUOIHOC,BIENLAI.MABIENLAI FROM dbo.DIEMDANH
	 JOIN dbo.BUOIHOC ON BUOIHOC.MABUOIHOC = DIEMDANH.MABUOIHOC
	 JOIN dbo.BIENLAI ON BIENLAI.MABIENLAI = DIEMDANH.MABIENLAI 
	 WHERE MADIEMDANH = @maDiemDanh
	 ORDER BY MADIEMDANH  DESC
END
DROP PROC clickTableDiemDanh
EXEC clickTableDiemDanh @maDiemDanh =1
go

SELECT TENLOP,COUNT(TENLOP)[soLuong],MONTH(NGAYNHAPHOC) [thang],YEAR(NGAYNHAPHOC) [nam] FROM dbo.LOP
GROUP BY TENLOP,NGAYNHAPHOC
HAVING YEAR(NGAYNHAPHOC) = ?

SELECT DAY(NGAYDANGKI) [ngay],MONTH(NGAYDANGKI) [thang],COUNT(madangki)[dangKi] FROM dbo.DANGKI
GROUP BY NGAYDANGKI
HAVING YEAR(NGAYDANGKI) =  '2010' AND MONTH(NGAYDANGKI) = 1
SELECT * FROM lop

SELECT * FROM dbo.DANGKI

SELECT MALOP,TENLOP,TrangThai FROM lop

CREATE PROC viewHV(@malop int,@mabuoihoc int)
AS
BEGIN
    SELECT TRANGTHAI,dbo.BIENLAI.MAHOCVIEN,tenHOCVIEN FROM dbo.DIEMDANH
	JOIN dbo.BIENLAI ON BIENLAI.MABIENLAI = DIEMDANH.MABIENLAI
	JOIN dbo.HOCVIEN ON HOCVIEN.MAHOCVIEN = BIENLAI.MAHOCVIEN
	WHERE MABUOIHOC =? AND dbo.HOCVIEN.MALOP =?

END


CREATE PROC themdd(@trangThai BIT,@maBuoiHoc INT,@maHocVien INT,@malop INT)
AS 
BEGIN
	
	DECLARE @mabienlai INT,@maDiemDanh INT 
	SELECT @mabienlai=MABIENLAI FROM dbo.BIENLAI
	WHERE MAHOCVIEN =@maHocVien AND MALOP =@malop
	SELECT  @maDiemDanh = MADIEMDANH FROM dbo.DIEMDANH
	WHERE MABUOIHOC =@maBuoiHoc AND MABIENLAI =@mabienlai 
	IF @maDiemDanh IS null
    BEGIN
        INSERT INTO dbo.DIEMDANH(TRANGTHAI,MABUOIHOC,MABIENLAI) VALUES(@trangThai,@maBuoiHoc,@mabienlai)
    END
	if @trangThai=0
	begin	
		update HOCVIEN set SOBUOINGHI=SOBUOINGHI+1
		where MAHOCVIEN=(select MAHOCVIEN from BIENLAI where MABIENLAI=@mabienlai)
	end
END

EXEC themdd 0,11,109,12
DROP PROC dbo.themdd
SELECT * FROM dbo.DIEMDANH
select * from hocvien
SELECT* FROM dbo.BIENLAI
WHERE MAHOCVIEN = 105
GO
CREATE PROC suadd(@trangThai BIT,@maBuoiHoc INT,@maHocVien INT,@malop INT )
AS 
BEGIN
	
	DECLARE @mabienlai INT, @xyz bit

	SELECT @mabienlai=MABIENLAI FROM dbo.BIENLAI
	WHERE MAHOCVIEN =@maHocVien  AND MALOP =@malop

	SELECT @xyz = TRANGTHAI FROM dbo.DIEMDANH
	WHERE MABUOIHOC =@maBuoiHoc AND MABIENLAI = @mabienlai

	UPDATE dbo.DIEMDANH SET TRANGTHAI =@trangThai
	WHERE  MABUOIHOC =@maBuoiHoc  AND MABIENLAI =@mabienlai

	IF @xyz = 0 AND @trangThai = 1
	BEGIN
		update HOCVIEN set SOBUOINGHI=SOBUOINGHI-1
		where MAHOCVIEN=(select MAHOCVIEN from BIENLAI where MABIENLAI=@mabienlai)	    
	END

	if @trangThai = 0 AND @xyz = 1
	begin	
		update HOCVIEN set SOBUOINGHI=SOBUOINGHI+1
		where MAHOCVIEN=(select MAHOCVIEN from BIENLAI where MABIENLAI=@mabienlai)
	end

END

DROP PROC dbo.suadd
EXEC dbo.suadd @trangThai = 1, -- bit
               @maBuoiHoc = 1,    -- int
               @maHocVien = 1,    -- int
               @malop = 1         -- int

UPDATE dbo.DIEMDANH SET TRANGTHAI = 0
WHERE MABUOIHOC =1 AND MABIENLAI=1



SELECT * FROM dbo.HOCVIEN
	
	SELECT LOAILOP.MALOAILOP,TENLOAILOP,COUNT(lop.MALOAILOP) [LL] FROM dbo.LOAILOP
	JOIN dbo.LOP ON LOP.MALOAILOP = LOAILOP.MALOAILOP
	GROUP BY LOAILOP.MALOAILOP,TENLOAILOP
	HAVING LOAILOP.MALOAILOP IN  (SELECT MALOAILOP FROM dbo.LOP)

    
	SELECT CAPLOP.MACAPLOP,TENCAPLOP,COUNT(LOP.MACAPLOP)[CL] FROM dbo.CAPLOP 
	JOIN dbo.LOP ON LOP.MACAPLOP = CAPLOP.MACAPLOP
	GROUP BY CAPLOP.MACAPLOP,TENCAPLOP
	HAVING CAPLOP.MACAPLOP IN(SELECT MACAPLOP FROM dbo.LOP)

	SELECT MALOP,TENLOP FROM lop
	WHERE TENLOP LIKE ?
-------------------------------------------------code moi 
CREATE PROC tim_lop_chuyen(@ll NVARCHAR(50),@cl NVARCHAR(50),@ch NVARCHAR(50))
AS
BEGIN
	--DECLARE @a INT, @b NVARCHAR(50), @c INT,@d int
	--select @a=malop,@b= tenlop,@d=siso from lop 
	--WHERE maloailop=(select maloailop from loailop where tenloailop='toeic') 
	--AND macaplop=(select macaplop from caplop where tencaplop='B') 
	--AND cahoc='Ca 3 (12h-14h) 357' 
	--AND trangthai=1
	--SELECT @c=COUNT(malop) FROM dbo.HOCVIEN
	--WHERE malop=@a 
	--IF @c<@d
	DECLARE @tenlop VARCHAR(100)
	SELECT @tenlop = TENLOP FROM dbo.LOP

	SELECT Lop.MALOP,TENLOP FROM dbo.LOP
	
	WHERE maloailop=(select maloailop from loailop where tenloailop=@ll) 
	AND macaplop=(select macaplop from caplop where tencaplop=@cl) 
	AND cahoc=@ch 
	AND trangthai=1
	AND SISO>  (SELECT COUNT(dbo.HOCVIEN.MALOP) FROM dbo.HOCVIEN 
												JOIN dbo.LOP on LOP.MALOP = HOCVIEN.MALOP
												WHERE TENLOP =@tenlop AND LOP.MALOP = HOCVIEN.MALOP)
END

	  EXEC dbo.tim_lop_chuyen @ll = N'Anh Văn Tổng Quát', -- nvarchar(50)
                        @cl = N'C', -- nvarchar(50)
                        @ch = N'Ca 4 (14h-16h) 246'  -- nvarchar(50)

CREATE PROC chuyen_lop(@malop INT,@malop2 INT,@mahv int)
AS
BEGIN
	DECLARE @lll NVARCHAR(50),@cll NVARCHAR(50),@chl NVARCHAR(50), @llh NVARCHAR(50),@clh NVARCHAR(50),@chh NVARCHAR(50)
	SELECT @lll=maloailop, @cll=MACAPLOP,@chl =CAHOC FROM lop
	WHERE malop = @malop
	SELECT @llh=maloailop, @clh=MACAPLOP,@chh =CAHOC FROM lop
	WHERE malop=@malop2
	
	if @lll=@llh AND @clh=@cll 
	BEGIN
		UPDATE bienlai SET malop=@malop WHERE malop=@malop2 AND MAHOCVIEN=@mahv
		UPDATE dbo.HOCVIEN SET MALOP=@malop WHERE malop=@malop2 AND MAHOCVIEN=@mahv 
		PRINT 'a'
	END
    IF @lll!=@llh OR @clh!=@cll 
	BEGIN
		
		UPDATE bienlai SET malop=@malop WHERE malop=@malop2 AND MAHOCVIEN=@mahv
		UPDATE dbo.HOCVIEN 
		SET MALOP=@malop, 
		SOBUOINGHI=0, 
		HOCPHINO=(select hocphi FROM lop WHERE malop = @malop)-(SELECT thanhtien FROM bienlai WHERE malop=@malop AND MAHOCVIEN=@mahv)
		WHERE malop=@malop2 AND MAHOCVIEN=@mahv
		
		PRINT 'b'
    end
END 
EXEC chuyen_lop 7,12,111 

------------diem thanh phan
CREATE PROC diem_thanh_phan(@malop INT)
AS
BEGIN
	DECLARE @x INT,@a INT,@mahv INT
	declare @b TABLE(
	id INT IDENTITY,
	mahocvien INT,
	sobuoinghi int
	)
	insert into @b SELECT MAHOCVIEN,SOBUOINGHI from hocvien WHERE malop = @malop
	SELECT * FROM @b 
	
	SET @x=1
    while @x<20
    BEGIN
		SELECT @a=sobuoinghi,@mahv=mahocvien FROM @b where id=@x
        
		
		IF @a=0
		UPDATE dbo.BIENLAI SET DIEMTHANHPHAN=10 where mabienlai= (SELECT mabienlai FROM bienlai where malop=@malop AND MAHOCVIEN=@mahv)
		IF @a=1
		UPDATE dbo.BIENLAI SET DIEMTHANHPHAN=8 where mabienlai= (SELECT mabienlai FROM bienlai where malop=@malop AND MAHOCVIEN=@mahv)
		IF @a=2
		UPDATE dbo.BIENLAI SET DIEMTHANHPHAN=6 where mabienlai= (SELECT mabienlai FROM bienlai where malop=@malop AND MAHOCVIEN=@mahv)
		IF @a=3
		UPDATE dbo.BIENLAI SET DIEMTHANHPHAN=4 where mabienlai= (SELECT mabienlai FROM bienlai where malop=@malop AND MAHOCVIEN=@mahv)
		IF @a>3
		UPDATE dbo.BIENLAI SET DIEMTHANHPHAN=0, DIEMTONG=0 where mabienlai= (SELECT mabienlai FROM bienlai where malop=@malop AND MAHOCVIEN=@mahv)
		
		set @x=@x+1
    END
END
EXEC diem_thanh_phan 13
Drop PROC diem_thanh_phan

	UPDATE dbo.BIENLAI SET DIEMTHANHPHAN=10 
	WHERE mabienlai= (SELECT mabienlai FROM bienlai where malop=11 AND MAHOCVIEN=111)

	SELECT*FROM dbo.bienlai
		SELECT*FROM dbo.HOCVIEN
