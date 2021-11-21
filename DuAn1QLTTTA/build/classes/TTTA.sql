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
	TENVAITRO INT NOT NULL,
	TENDANGNHAP NVARCHAR(20),
	MATKHAU NVARCHAR(20),
	--MANHANVIEN INT,
	CONSTRAINT PK_MAVAITRO PRIMARY KEY(MANHANVIEN),
	--CONSTRAINT FK_MAGV FOREIGN KEY(MANHANVIEN) REFERENCES dbo.GIANGVIEN,
	--CONSTRAINT FK_MAGV1 FOREIGN KEY(MANHANVIEN) REFERENCES dbo.KETOAN
	
)
GO

--SELECT TENDANGNHAP,MATKHAU from dbo.NGUOIDUNG
--WHERE TENDANGNHAP =? and matkhau=? AND TENVAITRO = 1

IF OBJECT_ID('GIANGVIEN') IS NOT NULL 
	DROP TABLE GIANGVIEN 
GO

CREATE TABLE GIANGVIEN
(
	MAGIANGVIEN INT IDENTITY(1,1) NOT NULL,
	TENGIANGVIEN NVARCHAR(80) NOT NULL,
	GIOITINH int,
	NGAYSINH DATE,
	DIACHI NVARCHAR(80),
	SDT NCHAR(11),
	EMAIL NVARCHAR(80),
	CONSTRAINT PK_MAGIANGVIEN PRIMARY KEY(MAGIANGVIEN),
	CONSTRAINT FK_MAGIANGVIEN1 FOREIGN KEY(MAGIANGVIEN) REFERENCES dbo.NGUOIDUNG
)
GO

IF OBJECT_ID('KETOAN') IS NOT NULL 
	DROP TABLE KETOAN
GO

CREATE TABLE KETOAN
(
	MAKETOAN INT IDENTITY(1,1) NOT NULL,
	TENKETOAN NVARCHAR(80) NOT NULL,
	GIOITINH INT,
	NGAYSINH DATE,
	DIACHI NVARCHAR(80),
	SDT NCHAR(11),
	EMAIL NVARCHAR(80),
	CONSTRAINT PK_MAKETOAN PRIMARY KEY(MAKETOAN),
	CONSTRAINT FK_MAKETOAN1 FOREIGN KEY(MAKETOAN) REFERENCES dbo.NGUOIDUNG
)
GO

IF OBJECT_ID('KHOAHOC') IS NOT NULL 
	DROP TABLE KHOAHOC
GO

CREATE TABLE KHOAHOC
(
	MAKHOAHOC INT IDENTITY(1,1),
	TENKHOAHOC NVARCHAR(150),
	NGAYNHAPHOC DATE,
	NGAYKETTHUC DATE,
	CONSTRAINT PK_MAKHOAHOC PRIMARY KEY(MAKHOAHOC),
)
GO

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
	MACAPLOP INT,
	MALOAILOP INT,
	MAGIANGVIEN INT,
	MAKHOAHOC INT,
	CONSTRAINT PK_MALOP PRIMARY KEY(MALOP),
	CONSTRAINT FK_MACAPLOP FOREIGN KEY(MACAPLOP) REFERENCES dbo.CAPLOP,
	CONSTRAINT FK_MALOAILOP FOREIGN KEY(MALOAILOP) REFERENCES dbo.LOAILOP,
	CONSTRAINT FK_LOPGIANGVIEN FOREIGN KEY(MAGIANGVIEN) REFERENCES dbo.GIANGVIEN,
	CONSTRAINT FK_MAKHOAHOCDANGKI FOREIGN KEY(MAKHOAHOC) REFERENCES dbo.KHOAHOC
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
	SDT NCHAR(11),
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
	GIOTHI TIME,
	MAKHOAHOC INT,
	SISO INT,
	VANG INT,
	CONSTRAINT PK_MADOTTHI PRIMARY KEY(MADOTTHI),
	CONSTRAINT FK_MAKHOAHOC FOREIGN KEY(MAKHOAHOC) REFERENCES dbo.KHOAHOC
)
GO

IF OBJECT_ID('DIEMTHI') IS NOT NULL 
	DROP TABLE DIEMTHI
GO

CREATE TABLE DIEMTHI(
	MAHOCVIEN INT,
	MADOTTHI INT,
	DIEMTHI FLOAT ,
	CONSTRAINT PK_DIEMTHI PRIMARY KEY(MAHOCVIEN,MADOTTHI),
	CONSTRAINT FK_MAHOCVIEN FOREIGN KEY(MAHOCVIEN) REFERENCES dbo.HOCVIEN,
	CONSTRAINT FK_MADOTTHI FOREIGN KEY(MADOTTHI) REFERENCES dbo.DOTTHI
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
	SDT NCHAR(10),
	EMAIL NVARCHAR(80),
	DIACHI NVARCHAR(80),
	TENCAPLOP NVARCHAR(80),
	TENLOAILOP NVARCHAR(80),
	HOCPHI FLOAT,
	CAHOC NVARCHAR(10),
	NGAYNHAPHOC DATE,
	CONSTRAINT PK_SDT PRIMARY KEY(madangki),
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
	MAHOCPHI INT,
	MAHOCVIEN INT,
	MALOP INT,
	MAKETOAN INT,
	MADANGKI INT,
	CONSTRAINT PK_MABIENLAI PRIMARY KEY(MABIENLAI),
	CONSTRAINT FK_BIENLAIHOCVIEN FOREIGN KEY(MAHOCVIEN) REFERENCES dbo.HOCVIEN,
	CONSTRAINT FK_BIENLAILOP FOREIGN KEY(MALOP) REFERENCES dbo.LOP,
	CONSTRAINT FK_MAKETOAN FOREIGN KEY(MAKETOAN) REFERENCES dbo.KETOAN,
	CONSTRAINT FK_MABIENLAIDANGKI FOREIGN KEY(MADANGKI) REFERENCES dbo.DANGKI
)
GO

SELECT * FROM dbo.NGUOIDUNG

INSERT INTO dbo.NGUOIDUNG(TENVAITRO,TENDANGNHAP,MATKHAU)
VALUES
	  (2,N'vinh11',N'123456'),
	  (2,N'ha12',N'270206'),
	  (2,N'tuan08',N'tuan08'),
	  (2,N'no01',N'1234567'),
	  (2,N'congnc22',N'cong22'),
	  (2,N'thutp03',N'thu03'),
	  (2,N'nhantp14',N'nha04'),
	  (2,N'hung92',N'123'),
	  (2,N'teo95',N'1234'),
	  (0,N'admin',N'admin'),
	  (1,N'phong01',N'12345'),
	  (1,N'linhh98',N'123456'),
	  (1,N'linhnt33',N'123456'),
	  (1,N'gianghv02',N'123456')

INSERT INTO dbo.GIANGVIEN(TENGIANGVIEN,GIOITINH,NGAYSINH,DIACHI,SDT,EMAIL)
VALUES(N'Nguyễn Đức Vinh',1,'1997/11/21',N'Ninh Bình','0345401309',N'Vinh123@gmail.com'),
	  (N'Nguyễn Thu Hà',0,'1987/02/27',N'Hà Nam','0814575843',N'Ha221@gmail.com'),
	  (N'Nguyễn Đức Tuấn',1,'1999/01/21',N'Hải Dương','0245674251',N'Tuan99@gmail.com'),
	  (N'Nguyễn Thị Nở',0,'1997/04/21',N'Nam Định','0253561468',N'No11@gmail.com'),
	  (N'Nguyễn Chí Công',1,'2002/08/24',N'Nam Định','0845136356',N'congnc2408@gmail.com'),
	  (N'Phạm Thị Thu',0,'1991/08/21',N'Ninh Bình','0914506901',N'Thu21@gmail.com'),
	  (N'Phạm Thanh Nhàn',0,'1994/08/22',N'Hà Nội','0314506901',N'Nhan22@gmail.com'),
	  (N'Phạm Tiến Hưng',1,'1992/04/21',N'Hà Nam','0912306901',N'Hung3k@gmail.com'),
	  (N'Phạm Văn Tèo',1,'1995/09/11',N'Nam Định','0916506901',N'Teo22@gmail.com')

INSERT INTO dbo.KETOAN(TENKETOAN,GIOITINH,NGAYSINH,DIACHI,SDT,EMAIL)
VALUES(N'Nguyễn Hải Linh',0,'1998/02/12',N'Hà Nội','0345723189',N'Linh89@gmail.com'),
	  (N'Nguyễn Thị Linh',0,'1997/01/12',N'Hà Nội','0345343189',N'Linh03@gmail.com'),
	  (N'Lò Văn PHóng',1,'1991/02/01',N'Lạng Sơn','0345213189',N'Phonglon@gmail.com'),
	  (N'Vũ Hương Giang',0,'2001/02/12',N'Hà Nội','0345113189',N'Giang22@gmail.com')


INSERT INTO dbo.KHOAHOC(TENKHOAHOC,NGAYNHAPHOC,NGAYKETTHUC)
VALUES(N'Khóa 1','2010/10/10','2011/01/10'),
	  (N'Khóa 2','2011/01/11','2011/04/11'),
	  (N'Khóa 3','2011/05/10','2011/09/10'),
	  (N'Khóa 4','2011/10/10','2012/01/10')

INSERT INTO dbo.LOAILOP(TENLOAILOP)
VALUES(N'Toeic'),
	  (N'Anh Văn Gia Tiếp'),
	  (N'Anh Văn Tổng Quát')

INSERT INTO dbo.CAPLOP(TENCAPLOP)
VALUES(N'A'),(N'B'),(N'C')

INSERT INTO dbo.LOP(TENLOP,SISO,CAHOC,HOCPHI,MACAPLOP,MALOAILOP,MAGIANGVIEN,MAKHOAHOC)
VALUES(N'Toeic cấp A',10,N'Ca 3',1000000.0,1,1,2,1),
	  (N'Toeic cấp A',10,N'Ca 1',1000000.0,1,1,1,3),
	  (N'Toeic cấp B',15,N'Ca 2',2500000.0,1,2,4,2),
	  (N'Anh Văn Tổng Quát cấp A',10,N'Ca 6',1500000.0,2,3,1,3),
	  (N'Anh Văn Tổng Quát cấp A',10,N'Ca 1',1500000.0,2,3,2,1),
	  (N'Anh Văn Tổng Quát cấp B',15,N'Ca 2',3000000.0,2,3,9,1),
	  (N'Anh Văn Tổng Quát cấp C',5,N'Ca 6',5000000.0,3,3,7,2),
	  (N'Anh văn giao tiếp cấp C',5,N'Ca 1',5500000.0,2,3,4,4),
	  (N'Anh Văn giao tiếp cấp B',15,N'Ca 3',3200000.0,2,2,5,3),
	  (N'Anh Văn giao tiếp cấp A',10,N'Ca 1',1700000.0,2,2,6,2)

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





INSERT INTO dbo.DOTTHI(NGAYTHI,GIOTHI,MAKHOAHOC,SISO,VANG)
VALUES('2010/10/03','07:34:14',1,30,10),
	  ('2011/01/03','09:34:14',2,30,5),
	  ('2011/05/04','11:34:14',3,30,12),
	  ('2011/10/03','11:34:14',4,30,9)

INSERT INTO dbo.DIEMTHI(MAHOCVIEN,MADOTTHI,DIEMTHI)
VALUES(1,1,1),
	  (2,1,7),
	  (3,1,4.2),
	  (4,1,6),
	  (5,1,5),
	  (6,1,8),
	  (7,1,7),
	  (8,1,3),
	  (9,1,7),
	  (10,1,9)

INSERT INTO dbo.DANGKI(TENHOCVIEN,NGAYSINH,GIOITINH,SDT,EMAIL,DIACHI,TENCAPLOP,TENLOAILOP,HOCPHI,CAHOC,NGAYNHAPHOC)
VALUES
(N'Nguyễn Thị A','1999/01/01',0,'0324513946',N'A@gmail.com',N'Đạo Lý- Lý NhâN-Hà Nam',N'A',N'Toeic',1000000.0,N'Ca 3','2010/10/10'),
(N'Nguyễn Thị B','1998/01/10',0,'0324213946',N'A@gmail.com',N'Đạo Lý- Lý NhâN-Hà Nam',N'A',N'Toeic',1000000.0,N'Ca 2','2010/10/10'),
(N'Nguyễn Văn C','1997/01/01',1,'0324321946',N'A@gmail.com',N'Đạo Lý- Lý NhâN-Hà Nam',N'A',N'Toeic',1000000.0,N'Ca 2','2010/10/10')


INSERT INTO dbo.BIENLAI(THANHTIEN,MAHOCVIEN,MALOP,MAKETOAN)
VALUES(1000000,1,1,1)
	
SELECT * FROM dbo.DANGKI
DELETE FROM dbo.DANGKI
SELECT * FROM dbo.HOCVIEN
SELECT * FROM dbo.BIENLAI
GO


ALTER PROCEDURE LUUBL(@DANGKI INT)
AS
BEGIN
    INSERT INTO dbo.BIENLAI(MADANGKI)
    SELECT dbo.DANGKI.madangki FROM dbo.DANGKI
	WHERE dbo.DANGKI.madangki =  @DANGKI AND dbo.DANGKI.madangki NOT IN(SELECT MADANGKI FROM dbo.BIENLAI)
	
END

EXEC dbo.LUUBL @DANGKI = 2 -- int
SELECT * FROM dbo.BIENLAI
GO
-----------------------------------------------------------------------bắt đầu truy vấn  học viên ----------------------------------------------
--Thủ tục lưu quản lý học viên
CREATE PROC thong_tin_sv
AS
BEGIN
    SELECT MAHOCVIEN,TENHOCVIEN,lop.MALOP,dbo.LOP.TENLOP,GIOITINH,NGAYSINH,SDT,EMAIL,DIACHI,HOCPHINO,SOBUOINGHI FROM  dbo.HOCVIEN
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

GO
CREATE PROCEDURE xoa_update_sinh_vien(@mahocvien INT,@malop INT)
AS
BEGIN
    DELETE from dbo.HOCVIEN 
	WHERE MAHOCVIEN = @mahocvien AND MALOP = @malop
	declare @max int
	select @max=max(MAHOCVIEN)from dbo.HOCVIEN
	if @max IS NULL   
	  SET @max = 0
	DBCC CHECKIDENT (HOCVIEN, RESEED,@max)
END
go

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
	INSERT INTO dbo.HOCVIEN(TENHOCVIEN,GIOITINH,NGAYSINH,DIACHI,SDT,EMAIL)
	SELECT TENHOCVIEN,GIOITINH,CONVERT(nvarchar(50),ngaysinh,103),DIACHI ,SDT,EMAIL FROM dbo.DANGKI
	WHERE dangki.SDT = @sdt AND dbo.DANGKI.SDT NOT IN (SELECT sdt FROM dbo.HOCVIEN)
	
END
GO

EXEC dbo.lay_thong_tin_hoc_vien_tu_bang_dang_ki @sdt = N'0324513946' -- nchar(10)
SELECT * ,CONVERT(nvarchar(50),ngaysinh,103) from hocvien
where sdt = N'0324513946'
delete from hocvien
where mahocvien = 115
-----------------------------------------------------------hết  truy vấn bảng học viên-----------------------------------------------------
--------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------------------
---------------------------------------------------------- bắt đầu truy vấn bảng lớp-------------------------------------------------------

-- lấy thông tin lớp
CREATE PROCEDURE thong_tin_lop
AS
BEGIN
    SELECT MALOP,TENLOP,dbo.GIANGVIEN.MAGIANGVIEN,TENGIANGVIEN,dbo.KHOAHOC.MAKHOAHOC,dbo.KHOAHOC.TENKHOAHOC,HOCPHI,CAHOC,SISO,dbo.CAPLOP.MACAPLOP,TENCAPLOP,dbo.LOAILOP.MALOAILOP,TENLOAILOP FROM dbo.LOP
	JOIN dbo.CAPLOP ON CAPLOP.MACAPLOP = LOP.MACAPLOP
	JOIN dbo.LOAILOP ON LOAILOP.MALOAILOP = LOP.MALOAILOP
	JOIN dbo.GIANGVIEN ON GIANGVIEN.MAGIANGVIEN = LOP.MAGIANGVIEN
	JOIN dbo.KHOAHOC ON dbo.KHOAHOC.MAKHOAHOC = LOP.MAKHOAHOC
	ORDER BY MALOP DESC
	
END
-- thêm lớp
INSERT INTO dbo.LOP(TENLOP,SISO,CAHOC,HOCPHI,MACAPLOP,MALOAILOP,MAGIANGVIEN,HOCPHI)
VALUES(?,?,?,?,?,?,?,?)
GO
--sửa thông tin lớp
UPDATE dbo.LOP SET TENLOP=?,SISO=?,CAHOC=?,MACAPLOP=?,MALOAILOP=?,MAGIANGVIEN=?,HOCPHI=?
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
go

-- tìm lớp
CREATE PROCEDURE tim_kiem_lop_theo_ma_lop(@tenlop NVARCHAR(50))
AS
BEGIN
    SELECT MALOP,TENLOP,dbo.GIANGVIEN.MAGIANGVIEN,TENGIANGVIEN,dbo.KHOAHOC.MAKHOAHOC,dbo.KHOAHOC.TENKHOAHOC,HOCPHI,CAHOC,SISO,dbo.CAPLOP.MACAPLOP,TENCAPLOP,dbo.LOAILOP.MALOAILOP,TENLOAILOP FROM dbo.LOP 
	JOIN dbo.CAPLOP ON CAPLOP.MACAPLOP = LOP.MACAPLOP
	JOIN dbo.LOAILOP ON LOAILOP.MALOAILOP = LOP.MALOAILOP
	JOIN dbo.GIANGVIEN ON GIANGVIEN.MAGIANGVIEN = LOP.MAGIANGVIEN
	JOIN dbo.KHOAHOC ON dbo.KHOAHOC.MAKHOAHOC = LOP.MAKHOAHOC
	WHERE TENLOP LIKE LTRIM(RTRIM(@tenlop))
END

EXEC dbo.tim_kiem_lop_theo_ma_lop @tenlop = N'%toeic%' -- nvarchar(50)
go
------------------------------------------------------------------------ kết thúc truy vấn lớp----------------------------------------------------
--------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------
------------------------------------------------------------------------bắt đầu truy vấn khóa học-------------------------------------------------
-- lấy thông tin khóa học
CREATE PROCEDURE thong_tin_khoa_hoc
AS
BEGIN
    SELECT MAKHOAHOC,TENKHOAHOC,CONVERT(NVARCHAR(20),NGAYNHAPHOC,103) [ngaybatdau],CONVERT(NVARCHAR(20),NGAYKETTHUC,103) [ngayketthuc] FROM dbo.KHOAHOC
END
--
--thêm khóa học
INSERT INTO dbo.KHOAHOC(TENKHOAHOC,NGAYNHAPHOC,NGAYKETTHUC)
VALUES(?,?,?)
--sửa khóa học
UPDATE dbo.KHOAHOC SET TENKHOAHOC=?,NGAYNHAPHOC=?,NGAYKETTHUC=?
WHERE MAKHOAHOC = ?
GO
--xóa khóa học
DELETE FROM dbo.KHOAHOC
WHERE MAKHOAHOC =?
GO

CREATE PROCEDURE xoa_update_khoa_hoc(@makh INT)
AS
BEGIN
	DELETE FROM dbo.KHOAHOC
	WHERE MAKHOAHOC =@makh
	declare @max int
	select @max=max(MAKHOAHOC)from dbo.KHOAHOC
	if @max IS NULL   
	  SET @max = 0
	DBCC CHECKIDENT (KHOAHOC, RESEED,@max)
END
go
--tìm kiếm khóa học
CREATE PROCEDURE tim_kiem_khoa_hoc(@tenkh NVARCHAR(50))
AS
BEGIN
    SELECT MAKHOAHOC,TENKHOAHOC,CONVERT(NVARCHAR(20),NGAYNHAPHOC,103) [ngaybatdau],CONVERT(NVARCHAR(20),NGAYKETTHUC,103) [ngayketthuc] FROM dbo.KHOAHOC
	WHERE TENKHOAHOC LIKE LTRIM(RTRIM(@tenkh))
END

EXEC tim_kiem_khoa_hoc N' %1%  '
GO
-------------------------------------------------------------------------kết thúc truy vấn khóa học-----------------------------------------------
------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------
------------------------------------------------------------------------ bắt đầu truy vấn đợt thi-------------------------------------------------
-- lấy thông tin đợt thi
ALTER PROCEDURE lay_thong_tin_dot_thi
AS
BEGIN
    SELECT MADOTTHI,DOTTHI.MAKHOAHOC,dbo.KHOAHOC.TENKHOAHOC,MALOP,TENLOP,CONVERT(NVARCHAR(20),NGAYTHI,103) [ngaythi],CONVERT(NVARCHAR(40),GIOTHI,114) [giothi],dbo.LOP.SISO,VANG FROM dbo.DOTTHI
	JOIN dbo.KHOAHOC ON KHOAHOC.MAKHOAHOC = DOTTHI.MAKHOAHOC
	JOIN lop ON dbo.KHOAHOC.MAKHOAHOC = lop.makhoahoc
	ORDER BY MADOTTHI desc
END
--thêm đợt thi
INSERT INTO dbo.DOTTHI(NGAYTHI,GIOTHI,MAKHOAHOC,SISO,VANG)
VALUES(?,?,?,?,?)
GO
-- sửa thông tin đợt thi
UPDATE dbo.DOTTHI SET NGAYTHI=?,GIOTHI=?,MAKHOAHOC=?,SISO=?,VANG=?
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
CREATE PROCEDURE tim_kiem_dot_thi(@tenlop NVARCHAR(50),@tenkhoahoc NVARCHAR(20))
AS
BEGIN
    SELECT MADOTTHI,DOTTHI.MAKHOAHOC,dbo.KHOAHOC.TENKHOAHOC,MALOP,TENLOP,CONVERT(NVARCHAR(20),NGAYTHI,103) [ngaythi],CONVERT(NVARCHAR(40),GIOTHI,114) [giothi],dbo.LOP.SISO,VANG FROM dbo.DOTTHI
	JOIN dbo.KHOAHOC ON KHOAHOC.MAKHOAHOC = DOTTHI.MAKHOAHOC
	JOIN lop ON dbo.KHOAHOC.MAKHOAHOC = lop.makhoahoc
	WHERE TENLOP LIKE LTRIM(RTRIM(@tenlop)) AND TENKHOAHOC like LTRIM(RTRIM(@tenkhoahoc))
	ORDER BY MADOTTHI desc
END

EXEC dbo.tim_kiem_dot_thi @tenlop = N' %toeic% ',    -- nvarchar(50)
                          @tenkhoahoc = N'  %1% ' -- nvarchar(20)
go
------------------------------------------------------------ kết thúc truy vấn đợt thi----------------------------------------------------
------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------
-------------------------------------------------------------bắt đầu truy vấn biên lai-----------------
-- thông tin biên lai
CREATE PROCEDURE thong_tin_bien_lai
AS
BEGIN
    SELECT MABIENLAI,TENHOCVIEN,TENLOP,HOCPHI,HOCPHINO,THANHTIEN = (HOCPHI-HOCPHINO),TENKETOAN FROM dbo.BIENLAI
	JOIN dbo.HOCVIEN ON HOCVIEN.MAHOCVIEN = BIENLAI.MAHOCVIEN
	JOIN dbo.LOP ON LOP.MALOP = BIENLAI.MALOP
	JOIN dbo.KETOAN ON KETOAN.MAKETOAN = BIENLAI.MAKETOAN

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
-------------------------------------------------------------------bắt đầu truy vấn kế toán--------------------------------
-- thông tin kế toán
CREATE PROCEDURE thong_tin_ke_toan
AS
BEGIN
    SELECT MAKETOAN,TENKETOAN,GIOITINH,CONVERT(NVARCHAR(20),NGAYSINH,103) [ngaysinh],DIACHI,SDT,EMAIL FROM dbo.KETOAN
	ORDER BY MAKETOAN DESC
END
--thêm kế toán
INSERT INTO dbo.KETOAN(TENKETOAN,GIOITINH,NGAYSINH,DIACHI,SDT,EMAIL)
VALUES(?,?,?,?,?,?)
GO
-- sửa thông tin  kế toán
UPDATE dbo.KETOAN SET TENKETOAN=?,GIOITINH=?,NGAYSINH=?,DIACHI=?,SDT=?,EMAIL=?
WHERE MAKETOAN = ?
GO
--xóa kế toán 
CREATE PROCEDURE xoa_update_Ke_Toan(@maketoan int)
AS
BEGIN
    DELETE FROM dbo.KETOAN
	WHERE MAKETOAN = @maketoan
	declare @max int
	select @max=max(MAKETOAN)from dbo.KETOAN
	if @max IS NULL   
	  SET @max = 0
	DBCC CHECKIDENT (KETOAN, RESEED,@max)
END
go
-- tìm kiếm nhân viên kế toán

CREATE PROCEDURE tim_kiem_nhan_vien_ke_toan(@tenketoan NVARCHAR(100))
AS
BEGIN
    SELECT MAKETOAN,TENKETOAN,GIOITINH,CONVERT(NVARCHAR(20),NGAYSINH,103) [ngaysinh],DIACHI,SDT,EMAIL FROM dbo.KETOAN
	WHERE TENKETOAN like LTRIM(RTRIM(@tenketoan))
END
GO
EXEC dbo.tim_kiem_nhan_vien_ke_toan @tenketoan = N'   %nguyễn hải linh%   ' -- nvarchar(50)
GO
-------------------------------------------------------------------kết thúc truy vấn kế toán-----------------------------
------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------
-------------------------------------------------------------------bắt đầu truy vấn  giảng viên--------------------------------
--thông tin giảng viên
alter PROCEDURE thong_tin_giang_vien
AS
BEGIN
    SELECT MAGIANGVIEN,TENGIANGVIEN,GIOITINH,CONVERT(NVARCHAR(20),NGAYSINH,103) [ngaysinh],DIACHI,sdt,EMAIL FROM dbo.GIANGVIEN
	order by MAGIANGVIEN desc
END

GO
exec thong_tin_giang_vien
--thêm giảng viên
INSERT INTO dbo.GIANGVIEN(TENGIANGVIEN,GIOITINH,NGAYSINH,DIACHI,SDT,EMAIL)
VALUES(?,?,?,?,?,?)

GO
-- sửa thông tin giảng viên

UPDATE dbo.GIANGVIEN SET TENGIANGVIEN= ?,GIOITINH= ?,NGAYSINH= ?,DIACHI= ?,SDT= ?,EMAIL= ?
WHERE MAGIANGVIEN = ?

GO
--tìm kiếm giảng viên
CREATE PROCEDURE tim_kiem_giang_vien(@tengiangvien NVARCHAR(100))
AS
BEGIN
    SELECT MAGIANGVIEN,TENGIANGVIEN,GIOITINH,CONVERT(NVARCHAR(20),NGAYSINH,103) [ngaysinh],DIACHI,sdt,EMAIL FROM dbo.GIANGVIEN
	WHERE TENGIANGVIEN LIKE LTRIM(RTRIM(@tengiangvien))
	order by MAGIANGVIEN desc
END

EXEC dbo.tim_kiem_giang_vien @tengiangvien = N'%đức%' -- nvarchar(100)
GO
CREATE PROCEDURE xoa_update_giang_vien(@MAGIANGVIEN int)
AS
BEGIN
    DELETE FROM dbo.GIANGVIEN 
	WHERE MAGIANGVIEN = @MAGIANGVIEN
	declare @max int
	select @max=max(MAGIANGVIEN)from dbo.GIANGVIEN
	if @max IS NULL   
	  SET @max = 0
	DBCC CHECKIDENT (GIANGVIEN, RESEED,@max)
END
go





-------------------------------------------------------------------kết thúc truy vấn giảng viên-----------------------------
------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------
-------------------------------------------------------------------bắt đầu truy vấn quản lý tài khoản nhân viên --------------------------------
--thông tin tài khoản
CREATE PROCEDURE thong_tin_tai_khoan
AS
BEGIN
    SELECT MANHANVIEN,TENDANGNHAP,MATKHAU,TENVAITRO FROM dbo.NGUOIDUNG
	order by MANHANVIEN desc
END
SELECT * FROM dbo.NGUOIDUNG
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
    SELECT MANHANVIEN,TENDANGNHAP,MATKHAU,TENVAITRO FROM dbo.NGUOIDUNG
	WHERE MANHANVIEN LIKE LTRIM(RTRIM(@MATKNV))
END

EXEC dbo.tim_kiem_tk_nhan_vien @MATKNV = 1 -- nvarchar(40)
go
-------------------------------------------------------------------kế thúc truy vấn tài khoản nhân viên---------------------------------
SELECT * from dbo.NGUOIDUNG
WHERE TENDANGNHAP =? AND MATKHAU = ? AND TENVAITRO = 0

UPDATE dbo.NGUOIDUNG SET MATKHAU='1234567'
WHERE TENVAITRO = 2 AND TENDANGNHAP='no01' AND MATKHAU='1234567'