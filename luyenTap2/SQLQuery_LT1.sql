CREATE TABLE Bill (
    maDonHang        NVARCHAR(30)   NOT NULL PRIMARY KEY,  
    emailNhanHoaDon  VARCHAR(255)   NOT NULL,            
    ngayDatHang      DATETIME2(0)   NOT NULL,
    loaiDoUong       NVARCHAR(20)   NOT NULL,
    sizeDoUong             CHAR(2)        NOT NULL,
    soCocDat         INT            NOT NULL,

    CONSTRAINT CK_Bill_loaiDoUong
        CHECK (loaiDoUong IN (N'Truyền thống', N'matcha', N'cam sả', N'sữa đặc')),

    CONSTRAINT CK_Bill_sizeDoUong
        CHECK (sizeDoUong IN ('M','L','XL'))
);

INSERT INTO Bill
(maDonHang, emailNhanHoaDon, ngayDatHang, loaiDoUong, sizeDoUong, soCocDat)
VALUES
(N'DH001', 'bachcvb@gmail.com', '2026-02-01 09:15:00', N'Truyền thống', 'M', 2),
(N'DH002', 'user1@example.com', '2026-02-01 10:05:00', N'matcha',      'L', 1),
(N'DH003', 'user2@example.com', '2026-02-02 14:30:00', N'cam sả',      'XL', 3),
(N'DH004', 'user3@example.com', '2026-02-02 16:00:00', N'sữa đặc',     'M', 1);

CREATE TABLE beer_type (
    id INT IDENTITY(1,1) PRIMARY KEY,
    type_name NVARCHAR(100) NOT NULL
);

INSERT INTO beer_type (type_name) VALUES
(N'Trúc Bạch'),
(N'Tiger'),
(N'Hà Nội'),
(N'Ôm')

alter table beer_order
add beer_type_id int,
CONSTRAINT FK_beer_type
FOREIGN KEY(beer_type_id)
REFERENCES beer_type(id)

INSERT INTO beer_order (name, age, cups, beer_type_id)
VALUES
(N'Nguyễn Ngọc T', 20, 4, 1),
(N'Nguyễn Tấn S', 30, 5, 2),
(N'Bách bạo chúa', 18, 1, 3),
(N'Bách bạo chúa Jr', 19, 2, 1);

Select count(bo.id) as id_của_beer_order, bo.beer_type_id as id_loại_bia, bt.type_name as loại_bia ,bo.name as tên, bo.cups as số_cốc, bo.age as tuổi 
FROM beer_order bo INNER JOIN beer_type bt ON bo.Id = bt.id


CREATE DATABASE JAV20302;
GO

USE JAV20302;
GO

CREATE TABLE BeerOrder (
    orderId INT IDENTITY(1,1) PRIMARY KEY,
    customerId INT,
    customerName NVARCHAR(100),
    beerName NVARCHAR(100),
    quantity INT
);
GO
INSERT INTO BeerOrder (customerId, customerName, beerName, quantity)
VALUES
(1, N'Nguyễn Văn A', N'Heineken', 2),
(2, N'Trần Thị B', N'Tiger', 3),
(3, N'Lê Văn C', N'Bia Sài Gòn', 5);