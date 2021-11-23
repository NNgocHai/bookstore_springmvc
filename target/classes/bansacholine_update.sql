DROP DATABASE bansachonline;

CREATE DATABASE bansachonline;

use bansachonline;
CREATE TABLE ADMINS (
                        ma_Admin INT AUTO_INCREMENT,
                        taikhoan_Admin VARCHAR(20) UNIQUE,
                        matkhau_Admin VARCHAR(50),
                        hoten_Admin NVARCHAR(60),
                        gmail_Admin VARCHAR(100) UNIQUE,

                        CONSTRAINT AM_maAmin_PK PRIMARY KEY(ma_Admin)
);

CREATE TABLE CUSTOMER(
                         ma_Customer INT AUTO_INCREMENT,
                         taikhoan_Customer VARCHAR(20) UNIQUE,
                         matkhau_Customer VARCHAR(20),
                         hoten_Customer NVARCHAR(50),
                         gmail_Customer VARCHAR(100) UNIQUE,
                         sdt_Customer VARCHAR(10) UNIQUE,
                         vitien INT,

                         CONSTRAINT CUSTOMER_maCustomer_PK PRIMARY KEY(ma_Customer)
);

CREATE TABLE SHIPPER(
                        ma_Shipper INT AUTO_INCREMENT,
                        taikhoan_Shipper VARCHAR(20) UNIQUE,
                        matkhau_Shipper VARCHAR(20),
                        hoten_Shipper NVARCHAR(50),
                        gmail_Shipper VARCHAR(100) UNIQUE,

                        CONSTRAINT SHIPPER_maShipper_PK PRIMARY KEY(ma_Shipper)
);

CREATE TABLE DAUSACH
(
    ma_DauSach INT AUTO_INCREMENT,
    ten_DauSach NVARCHAR(50),

    CONSTRAINT DS_maDS_PK PRIMARY KEY(ma_DauSach)
);

CREATE TABLE CUONSACH
(
    ma_CuonSach INT AUTO_INCREMENT,
    ma_DauSach INT,
    ten_CuonSach NVARCHAR(50),
    tacgia NVARCHAR(100),
    soluong INT,
    anh_CuonSach VARCHAR(150),
    discount INT,
    giabia INT,
    mota varchar(5000),

    CONSTRAINT CS_maCuonSach_PK PRIMARY KEY(ma_CuonSach),
    CONSTRAINT CS_maDS_FK FOREIGN KEY (ma_DauSach) REFERENCES DAUSACH(ma_DauSach) ON DELETE CASCADE
);

CREATE TABLE GIOHANG
(
    ma_Customer INT,
    ma_CuonSach INT,
    soluong INT,

    CONSTRAINT GH_maGH_PK PRIMARY KEY(ma_Customer, ma_CuonSach),
    CONSTRAINT GH_maCS_FK FOREIGN KEY (ma_CuonSach) REFERENCES CUONSACH(ma_CuonSach) ON DELETE NO ACTION,
    CONSTRAINT GH_maCUS_FK FOREIGN KEY(ma_Customer) REFERENCES CUSTOMER(ma_Customer) ON DELETE CASCADE
);

CREATE TABLE DONHANG
(
    ma_DH INT AUTO_INCREMENT,
    ma_Customer INT,
    diachi NVARCHAR(100),
    sdt VARCHAR(15),
    ngaydat TIMESTAMP,
    tongtien INT,
    tinhtrang NVARCHAR(10),
    ghichu NVARCHAR(30),

    CONSTRAINT DH_maDH_PK PRIMARY KEY(ma_DH),
    CONSTRAINT DH_maCustomer_FK FOREIGN KEY(ma_Customer) REFERENCES CUSTOMER(ma_Customer) ON DELETE CASCADE
);

CREATE TABLE CHITIETDONHANG(
                               ma_DH INT,
                               ma_CuonSach INT,
                               soluong INT(10),
                               gia INT,

                               CONSTRAINT CTDH_maDH_PK PRIMARY KEY(ma_DH, ma_CuonSach),
                               CONSTRAINT CTDH_maDH_FK FOREIGN KEY(ma_DH) REFERENCES DONHANG(ma_DH) ON DELETE CASCADE,
                               CONSTRAINT CTDH_maCS_FK FOREIGN KEY(ma_CuonSach) REFERENCES CUONSACH (ma_CuonSach) ON DELETE CASCADE
);

CREATE TABLE GIAOHANG(
                         ma_DH INT,
                         ma_Shipper INT,

                         CONSTRAINT GIAOHANG_maDH_maShipper_PK PRIMARY KEY(ma_DH, ma_Shipper),
                         CONSTRAINT GIAOHANG_maDH_FK FOREIGN KEY(ma_DH) REFERENCES DONHANG(ma_DH) ON DELETE CASCADE,
                         CONSTRAINT GIAOHANG_maShipper_FK FOREIGN KEY(ma_Shipper) REFERENCES SHIPPER(ma_Shipper) ON DELETE NO ACTION
);

CREATE TABLE REVIEW
(
    ma_Review INT AUTO_INCREMENT,
    ma_Customer INT,
    ngay_Review TIMESTAMP,
    binhluan VARCHAR(4000),

    CONSTRAINT RV_maReview_PK PRIMARY KEY(ma_Review),
    CONSTRAINT RV_maCustomer_FK FOREIGN KEY(ma_Customer) REFERENCES CUSTOMER(ma_Customer) ON DELETE CASCADE
);

USE bansachonline;
INSERT INTO ADMINS (taikhoan_Admin, matkhau_Admin , hoten_Admin, gmail_Admin)
VALUES ('nhuantp', '18110331','Phạm Xuân Nhuận', 'nhuan.cntt.k18@gmail.com');
INSERT INTO ADMINS (taikhoan_Admin, matkhau_Admin , hoten_Admin, gmail_Admin)
VALUES ('hieuxt', '18110282','Hồ Văn Hiếu', '18110282@student.hcmute.edu.vn');
INSERT INTO ADMINS(taikhoan_Admin, matkhau_Admin , hoten_Admin, gmail_Admin)
VALUES ('haibmt','18110278','Nguyễn Ngọc Hải', 'ngochai247@gmail.com');
INSERT INTO ADMINS(taikhoan_Admin, matkhau_Admin , hoten_Admin, gmail_Admin)
VALUES ('tuthanhqn', '18110387','Nguyễn Thanh Tú', '18110387@student.hcmute.edu.vn');

INSERT INTO CUSTOMER (taikhoan_Customer, matkhau_Customer, hoten_Customer, gmail_Customer, sdt_Customer, vitien)
VALUES('taitran234', '123', 'Lương Trần Tài', 'luongtrantai23@gmail', '0987290386', '10000000');
INSERT INTO CUSTOMER(taikhoan_Customer, matkhau_Customer, hoten_Customer, gmail_Customer, sdt_Customer, vitien)
VALUES ('vanphuc37N', '222', 'Nguyễn Văn Phúc', 'bentre71@gmail.com', '113', '10000000');
INSERT INTO CUSTOMER(taikhoan_Customer, matkhau_Customer, hoten_Customer, gmail_Customer, sdt_Customer, vitien)
VALUES ('giang247', '333', 'Dương Ngọc Giang', 'giang49@gmail.com', '114', '10000000');
INSERT INTO CUSTOMER(taikhoan_Customer, matkhau_Customer, hoten_Customer, gmail_Customer, sdt_Customer, vitien)
VALUES ('nghiahuynh11', '444', 'Huỳnh Trọng Nghĩa', 'nghiapmp@gmail.com', '115', '10000000');
INSERT INTO CUSTOMER(taikhoan_Customer, matkhau_Customer, hoten_Customer, gmail_Customer, sdt_Customer, vitien)
VALUES ('phapcute09', '555', 'Nguyễn Văn Pháp', 'phapvanspkt@gmail.com', '116', '10000000');

INSERT INTO SHIPPER (taikhoan_Shipper, matkhau_Shipper, hoten_Shipper, gmail_Shipper)
VALUES('giang247', '19110193', 'Dương Ngọc Giang', 'giangxt@gmail.com');
INSERT INTO SHIPPER(taikhoan_Shipper, matkhau_Shipper, hoten_Shipper, gmail_Shipper)
VALUES ('dangkhoabt', '18110227', 'Phạm Đăng Khoa', 'khoaphambt71@gmail.com');

INSERT INTO DAUSACH (ten_DauSach)
VALUES ('Lịch sử - Truyền thống');
INSERT INTO DAUSACH(ten_DauSach)
VALUES ('Kiến thức khoa học');
INSERT INTO DAUSACH(ten_DauSach)
VALUES ('Văn học Việt Nam');
INSERT INTO DAUSACH(ten_DauSach)
VALUES ('Văn học nước ngoài');
INSERT INTO DAUSACH(ten_DauSach)
VALUES ('Truyện tranh');
INSERT INTO DAUSACH(ten_DauSach)
VALUES ('Manga - Comic');

use bansachonline;
INSERT INTO CUONSACH (ma_DauSach, ten_CuonSach, tacgia, soluong,anh_CuonSach, discount, giabia, mota)
VALUES ( '3', 'Ở đây có bí kíp cưa crush', 'Văn Nghĩa', '20','Ởđâycóbíkípcưacrush.jpg', '10', '55000', 'Crush chưa hẳn chỉ người bạn yêu, nhưng crush cũng không chỉ người mà bạn mới vừa rung rinh một tẹo. Crush nằm trên lằn ranh của yêu tha thiết đắm đuối nhiều năm và cảm nắng vài ngày là hết.\nBạn có muốn biến crush trở thành báu vật thực sự của riêng mình hay không?\nHãy đọc quyển sách này và nắm trọn bí kíp, bạn nha.');
INSERT INTO CUONSACH (ma_DauSach, ten_CuonSach, tacgia, soluong,anh_CuonSach, discount, giabia, mota)
VALUES ( '3', 'Người mẹ cầm súng', 'Nguyễn Thi', '20','Ngườimẹcầmsúng.jpg', '10', '30000', '“Văn Nguyễn Thi không phải là thứ văn đa nghĩa, vậy mà vẫn khiến người sành văn đọc hoài. Có phải những câu chuyện hết sức bình dị ấy hàm chứa sức sống của một đất nước trong thời bão lửa chiến tranh, nhà văn là nhân chứng trung thực? Hay là do ngôn ngữ, lời ăn tiếng nói hàng ngày của người Nam Bộ, được nhà văn chọn lọc, nâng cấp trở nên giàu sức biểu cảm đến vậy?”');
INSERT INTO CUONSACH (ma_DauSach, ten_CuonSach, tacgia, soluong,anh_CuonSach, discount, giabia, mota)
VALUES ('3', 'Dế mèn phiêu lưu ký', 'Tô Hoài', '10','Dếmènphiêulưuký.jpg', '0', '200000', 'Nét chữ nết người, yêu mến nhà văn Tô Hoài và tác phẩm “Dế Mèn phiêu lưu ký” không thể bỏ qua cuốn sách độc đáo này!');
INSERT INTO CUONSACH (ma_DauSach, ten_CuonSach, tacgia, soluong,anh_CuonSach, discount, giabia, mota)
VALUES ('3', 'Trong sương thương má', 'Trương Chí Hùng', '10','Trongsươngthươngmá.jpg', '10', '40000', 'Mỗi trang văn trong quyển sách này có thể xem như lát cắt từ kí ức của một đứa trẻ miền Tây. Đọc Trong sương thương má, biết đâu bạn sẽ bắt gặp khung trời tuổi thơ mình trong đó. Mà tuổi thơ nào cũng chỉ đong đầy khi có má có ba…');
INSERT INTO CUONSACH (ma_DauSach, ten_CuonSach, tacgia, soluong,anh_CuonSach, discount, giabia, mota)
VALUES ('3', 'Nhâm nhi Tết Tân Sửu 2021', 'Nhiều tác giả', '10','NhâmnhiTếtTânSửu2021.jpg', '0', '150000', 'Hãy cùng Sáo và Nghé thăm xuân vùng cao, ghé vào bếp nhà nào ấm lửa, rong chơi trên cao nguyên bảng lảng mây trời, ngắm cây nêu ở làng quê Bắc, chậu mai e ấp nụ xinh phương Nam, nếm canh miến măng, gà luộc, thịt đông, nem rán, bún thang, lại thêm thịt kho hột vịt, canh khổ qua, dưa món củ kiệu tôm khô.');
INSERT INTO CUONSACH (ma_DauSach, ten_CuonSach, tacgia, soluong,anh_CuonSach, discount, giabia, mota)
VALUES ('3', 'Các bạn của đam đam', 'Vũ Hùng', '20','Cácbạncủađamđam.jpg', '10', '40000', 'Các bạn của Đam Đam là câu chuyện tình bạn xúc động giữa cậu bé Đam Đam và chú chó Phay Phay. Cả hai lớn lên bên nhau, bắt đầu những cuộc đi săn trong rừng sâu. Rồi Đam Đam và Phay Phay có thêm chú khỉ Xa-ni làm bạn. Cả ba cùng nhau có những kỉ niệm khó quên.');
INSERT INTO CUONSACH (ma_DauSach, ten_CuonSach, tacgia, soluong,anh_CuonSach, discount, giabia, mota)
VALUES ('3', 'Qua khỏi dốc là nhà', 'PhanThúy Hằng', '10','Quakhỏidốclànhà.jpg', '5', '56000', 'Cuốn sách kết thúc ở chỗ kết thúc khoảng đời đẹp nhất với một con người – thời đi học – khiến ta nghẹn giọng, vì nỗi buồn nặng như đất, mạnh mẽ và khô rốc ở trong đó.');
INSERT INTO CUONSACH (ma_DauSach, ten_CuonSach, tacgia, soluong,anh_CuonSach, discount, giabia, mota)
VALUES ('3', 'Cuộc trùng phùng trong mơ', 'Nguyễn Đông Thức', '10','Cuộctrùngphùngtrongmơ.jpg', '0', '23000', 'Với cách viết lôi cuốn, sống động mà cũng thật truyền cảm, nhà văn Nguyễn Đông Thức đưa độc giả vào một hành trình trưởng thành đầy những bất ngờ. Để ở chặng cuối, ta cảm nhận một điều giản dị: Dù gian nan trắc trở thế nào, hạnh phúc sẽ đến với ta, một khi ta có tình bạn thật ấm áp.');
INSERT INTO CUONSACH (ma_DauSach, ten_CuonSach, tacgia, soluong,anh_CuonSach, discount, giabia, mota)
VALUES ('3', 'Sự tích hoa phượng', 'Phạm Sông Đông', '10','Sựtíchhoaphượng.jpg', '10', '18000', 'Hoa phượng năm cánh với sắc màu rực rỡ luôn gắn bó với tuổi thơ, học trò và mùa hạ. Thế nhưng không phải ai cũng biết, có ít nhất một truyện cổ tích vừa mang hơi hướng dân gian lại vừa có phong cách hiện đại về loài hoa thân thương này. Đó là câu chuyện cảm động về nghĩa thầy trò, lòng nhân ái, ý chí chuyên cần và sự lương thiện như ánh sáng xua đi bóng tối xấu xa, hắc ám…');
INSERT INTO CUONSACH (ma_DauSach, ten_CuonSach, tacgia, soluong,anh_CuonSach, discount, giabia, mota)
VALUES ('3', 'Thơ Xuân Hương', 'Hồ Xuân Hương', '10','ThơXuânHương.jpg', '10', '28000', '“Thơ Hồ Xuân Hương là tiếng nói tâm tình của người phụ nữ, thể hiện một bản lĩnh sống mạnh mẽ khác thường”. (Giáo sư LÊ TRÍ VIỄN)\n“Ngôn ngữ dân tộc dưới ngòi bút của Hồ Xuân Hương vừa súc tích, chính xác lại vừa uyển chuyển, linh hoạt phong phú về nghĩa, đặc sắc về tạo hình, dồi dào về âm thanh, nhịp điệu.” (Giáo sư NGUYỄN LỘC)');
INSERT INTO CUONSACH (ma_DauSach, ten_CuonSach, tacgia, soluong,anh_CuonSach, discount, giabia, mota)
VALUES ('3', 'Truyện Tây Bắc', 'Tô Hoài', '10','TruyệnTâyBắc.jpg', '10', '50000', '“Đọc Truyện Tây Bắc, chúng ta có cảm tưởng đó vừa là một bản cáo trạng, vừa là một khúc tình ca: cáo trạng đối với phong kiến miền núi và thực dân, tình ca ngợi khen cảnh đẹp, tập quán hay, tinh thần cách mạng, quan hệ giữa người và người Tây Bắc, như bản tình ca viết với một bút pháp trữ tình nồng đượm và nên thơ.”');
INSERT INTO CUONSACH (ma_DauSach, ten_CuonSach, tacgia, soluong,anh_CuonSach, discount, giabia, mota)
VALUES ('3', 'Những hạnh phúc rực rỡ', 'Nhiều tác giả', '10','Nhữnghạnhphúcrựcrỡ.jpg', '5', '38000', 'Những hạnh phúc rực rỡ - cuốn sách đến từ những tác giả được yêu thích nhất của Văn Học Trẻ: Raxu Nguyễn, Jathy, Bảo Châu, Huy Hải, sẽ là món quà ý nghĩa dành cho tất cả chúng ta, đặc biệt là những người trẻ đang kiếm tìm cho mình hạnh phúc');
INSERT INTO CUONSACH (ma_DauSach, ten_CuonSach, tacgia, soluong,anh_CuonSach, discount, giabia, mota)
VALUES ('3', 'Nhạc đồng quê', 'Nguyễn Văn Thanh', '10','Nhạcđồngquê.jpg', '0', '16000', 'Truyện đồng thoại vừa cổ tích vừa hiện đại, thoáng chút ưu phiền, trầm lắng trăn trở nhưng vẫn lấp lánh niềm vui, lạc quan và hi vọng như dư âm của giai điệu Nhạc đồng quê.');
INSERT INTO CUONSACH (ma_DauSach, ten_CuonSach, tacgia, soluong,anh_CuonSach, discount, giabia, mota)
VALUES ('4', 'Đóa hoa hạnh phúc', 'Koyoharu Gotouge', '15','Đóahoahạnhphúc.jpg', '10', '50000', 'cuốn sách nhỏ này còn chứa những câu chuyện về Zenitsu, Inosuke và các thành viên trong đội Diệt Quỷ chưa từng được nhắc tới trong truyện chính! Không những thế, phần phụ lục “Học viện Diệt Quỷ” rất được yêu thích cũng được viết thành tiểu thuyết!! Sách tặng kèm 01 lót ly cho những bản in đầu. Xin trân trọng giới thiệu cùng bạn đọc.');
INSERT INTO CUONSACH (ma_DauSach, ten_CuonSach, tacgia, soluong,anh_CuonSach, discount, giabia, mota)
VALUES ('4', 'Mùa đông bất tận', 'Laura Ingalls Wilder', '10','Mùađôngbấttận.jpg', '10', '80000', 'Những trận bão tuyết kinh hoàng giày xéo thị trấn De Smet. Tất cả mọi người trong đó có nhà Laura, mòn mỏi, buồn lo với những bữa ăn ngày càng ít ỏi và phải chống chọi với nguy cơ chết đói. Almanzo Wilder đã quyết tâm vượt bão tuyết, chấp nhận mọi rủi ro và cùng với Cap Garland đi mua lúa mì cứu thị trấn. Cuối cùng mùa xuân cũng về, mặt trời lên, cỏ xanh mướt và mọi người có thể ca hát trong no ấm, quên đi những nỗi sợ hãi suốt mùa đông khắc nghiệt...');
INSERT INTO CUONSACH (ma_DauSach, ten_CuonSach, tacgia, soluong,anh_CuonSach, discount, giabia, mota)
VALUES ( '4', 'Thần thoại Hy Lạp', 'Phạm Văn Khỏa', '10','ThầnthoạiHyLạp.jpg','10', '86000', 'Chuyện những người Argonaut giành lại bộ lông cừu vàng từ miền đất Colchis thần thoại, cuộc trường chinh ròng rã một thập niên các anh hùng Hy Lạp công phá thành Troy, hải trình đầy bão táp dũng tướng Odysseus tài trí về quê xứ… là những bản trường ca của binh lửa và giáo gươm, trong một vũ trụ thần thoại có trung tâm là vẻ đẹp của con người.');
INSERT INTO CUONSACH (ma_DauSach, ten_CuonSach, tacgia, soluong,anh_CuonSach, discount, giabia, mota)
VALUES ('4', 'Câu chuyện đời tôi', 'Helen Keller', '10','Câuchuyệnđờitôi.jpg', '10', '45000', 'Đây cũng là câu chuyện nổi tiếng về cô gái vừa khiếm thính, vừa khiếm thị đầu tiên trên thế giới giành được bằng đại học. Và hơn tất cả, đây là câu chuyện của trái tim − điều khiến cho tác giả Helen Keller trở  thành  “Một  trong  những  người  phụ  nữ vĩ đại nhất trong thời đại của chúng ta.”');
INSERT INTO CUONSACH (ma_DauSach, ten_CuonSach, tacgia, soluong,anh_CuonSach, discount, giabia, mota)
VALUES ('4', 'Sự tích các loài vật', 'Rudyard Kipling', '30','Sựtíchcácloàivật.jpg', '0', '160000', 'Những điều thần diệu người ta thường tìm thấy trong các tác phẩm kì diệu. “Sự tích các loài vật - Chuyện như thế đó” là một tác phẩm như thế. Trong cuốn sách này, nội dung các chuyện kể chúng tôi dịch theo bản tiếng Pháp của dịch giả Nathalie Peronny và sử dụng minh họa của họa sĩ Pháp Sébastien Pelon. Chúng tôi hi vọng những nét minh họa vừa mềm mại, vừa tinh nghịch, mới mẻ, sẽ làm tăng sức hấp dẫn của 12 chuyện kể');
INSERT INTO CUONSACH (ma_DauSach, ten_CuonSach, tacgia, soluong,anh_CuonSach, discount, giabia, mota)
VALUES ('4', 'Phòng khám cho ma', 'Nhiều tác giả', '10','Phòngkhámchoma.jpg', '0', '45000', 'Bởi những nguyên do hết sức tình cờ, tớ có dịp đặt chân vào thế giới yêu ma và trở thành phụ tá bất đắc dĩ cho bác sĩ Hozuki Kyujuro, người duy nhất trên đời chữa bệnh cho yêu ma quỷ quái. Những  cuộc phiêu lưu cùng bác sĩ cuốn tớ vào biết bao rắc rối. Một hôm, đúng đợt khám sức khỏe định kì ở trường tớ, bác sĩ Hozuki bất ngờ xuất hiện. Bác sĩ làm gì ở thế giới loài người nhỉ?');
INSERT INTO CUONSACH (ma_DauSach, ten_CuonSach, tacgia, soluong,anh_CuonSach, discount, giabia, mota)
VALUES ('4', 'Lớn lên trên đảo vắng', 'Johann David Wyss', '10','Lớnlêntrênđảovắng.jpg', '0', '58000', 'Những chi tiết thú vị về cuộc sống nơi hoang dã, những bài học bổ ích về thế giới tự nhiên đã khiến "Lớn lên trên đảo vắng" của Johann David Wyss trở thành một trong những tác phẩm nổi tiếng nhất mọi thời đại');
INSERT INTO CUONSACH (ma_DauSach, ten_CuonSach, tacgia, soluong,anh_CuonSach, discount, giabia, mota)
VALUES ('4', 'Đảo giấu vàng', 'R.L.Stevenson', '10','Đảogiấuvàng.jpg', '0', '36000', 'Ngay sau khi xuất bản, câu chuyện đi tìm kho báu của nhà văn Stevenson đã tạo nên một tiếng vang lớn. Thủ tướng Anh khi đó là William Gladstone đã thức tới hai giờ sáng để đọc hết cuốn sách. Đến nay, đã có hơn 50 bộ phim truyện và phim truyền hình được chuyển thể từ tác phẩm của Stevenson. Và trải qua một thời gian dài, Đảo giấu vàng đã trở nên gắn bó với nhiều thế hệ độc giả nhỏ tuổi trên toàn thế giới');
INSERT INTO CUONSACH (ma_DauSach, ten_CuonSach, tacgia, soluong,anh_CuonSach, discount, giabia, mota)
VALUES ('4', 'Công chúa nhỏ', 'Hodgson Burnett', '10','Côngchúanhỏ.jpg', '10', '68000', 'Với tâm hồn trong trẻo và cao quý của một nàng công chúa, Sara có thể can đảm vượt qua tất cả những nghiệt ngã để vươn tới cuộc sống tốt đẹp trong tương lai? Đó là một câu chuyện cảm động sâu sắc về nhân cách của nàng công chúa bé nhỏ và, chắc chắn rồi, về cả những phép màu...');
INSERT INTO CUONSACH (ma_DauSach, ten_CuonSach, tacgia, soluong,anh_CuonSach, discount, giabia, mota)
VALUES ('4', 'Hoàng tử bé', 'Saint Exupéry', '10','Hoàngtửbé.jpg', '0', '25000', '“Hoàng Tử Bé” (tên tiếng Pháp: Le Petit Prince) xuất bản năm 1943 và là tác phẩm nối tiếng nhất trong sự nghiệp của nhà văn phi công Pháp Antoine de Saint-Exupéry. Tác phẩm đã được dịch sang hơn 250 ngôn ngữ và cho đến nay đã bán được hơn 200 triệu bản khắp thế giới.');
INSERT INTO CUONSACH (ma_DauSach, ten_CuonSach, tacgia, soluong,anh_CuonSach, discount, giabia, mota)
VALUES ('4', 'Lối thoát tử thần', 'Dames Dashner', '10','Lốithoáttửthần.jpg', '10', '95000', 'Trong một thế giới bị tàn phá vì biến đổi khí hậu, Thomas và các bạn làm mọi cách để sống sót và tồn tại. Nhưng, hiểm họa lớn nhất lại đến từ phía khó ngờ nhất: Con người. Con người mù quáng vì tham vọng. Con người điên cuồng vì hận thù. Con người vì virus Nhật trùng mà mất hết nhân tính. Ai sẽ sống sót? Ai sẽ chấp nhận hi sinh vì sự tồn vong của nhân loại? Lối thoát nào đang chờ đợi những người trẻ sau quá nhiều mất mát đau thương?');
INSERT INTO CUONSACH (ma_DauSach, ten_CuonSach, tacgia, soluong,anh_CuonSach, discount, giabia, mota)
VALUES ('1', 'Chị Sáu ở Côn Đảo', ' Lê Quang Vịnh', '10','ChịSáuởCônĐảo.jpg', '10', '10000', 'Truyện kể về chị Võ Thị Sáu, người con gái Đất Đỏ anh hùng.');
INSERT INTO CUONSACH (ma_DauSach, ten_CuonSach, tacgia, soluong,anh_CuonSach, discount, giabia, mota)
VALUES ('1', 'Lý Thường Kiệt', 'Tạ Huy Long', '10','LýThườngKiệt.jpg', '10', '15000', 'Lý Thường Kiệt tên thật là Ngô Tuấn, một vị tướng kiệt xuất trong lịch sử Việt Nam. Ông nhiều lần thống lĩnh ba quân “phá Tống, bình Chiêm”, giữ bờ cõi nhiều năm trong cảnh thanh bình. Ông lại đem tài sức giúp vua Lý phát mở mang canh nông, xây dựng nhà học, góp phần gây dựng nền văn hiến Đại Việt');
INSERT INTO CUONSACH (ma_DauSach, ten_CuonSach, tacgia, soluong,anh_CuonSach, discount, giabia, mota)
VALUES ('1', 'Suốt đời học Bác', 'Kiều Mai Sơn', '10','SuốtđờihọcBác.jpg', '10', '40000', 'Bác Hồ, vị lãnh tụ kính yêu của dân tộc Việt Nam, người chiến sĩ cộng sản suốt đời tận tụy vì nước, vì dânvới những phẩm chất giản dị mà cao đẹp luôn là tấm gương sáng cho nhiều thế hệ tìm hiểu, học tập, noi theo.');
INSERT INTO CUONSACH (ma_DauSach, ten_CuonSach, tacgia, soluong,anh_CuonSach, discount, giabia, mota)
VALUES ('1', 'Người lính Điện Biên kể chuyện', 'Kiều Mai Sơn', '20','NgườilínhĐiệnBiênkểchuyện.jpg', '10', '30000', '“Mỗi lần trở lại Điện Biên Phủ, tôi tới nghĩa trang liệt sĩ dưới chân đồi A1 thắp nén hương tưởng niệm những người đồng đội đã nằm lại đây. Đứng trước rất nhiều ngôi mộ không có tên, tôi hình dung ra anh chiến sĩ trẻ tới chiến trường giữa trận đánh, chiến đấu bên những người đồng đội chưa kịp biết tên mình và mình cũng chưa kịp biết là ở đơn vị nào...”');
INSERT INTO CUONSACH (ma_DauSach, ten_CuonSach, tacgia, soluong,anh_CuonSach, discount, giabia, mota)
VALUES ('1', 'Chu Văn An', 'Nhiều tác giả', '20','ChuVănAn.jpg', '0', '45', 'hân kỉ niệm 650 năm ngày mất Chu Văn An (1370 - 2020), Nhà xuất bản Kim Đồng trân trọng gửi tới các em cuốn sách này - nơi những trang sách ghi lại nét đẹp nhân cách người thầy mẫu mực - nhà giáo Chu Văn An');
INSERT INTO CUONSACH (ma_DauSach, ten_CuonSach, tacgia, soluong,anh_CuonSach, discount, giabia, mota)
VALUES ('1', 'Nghìn xưa Văn Hiến', 'Trần Quốc Vượng', '10','NghìnxưaVănHiến.jpg', '0', '198000', 'Cuốn sách “Nghìn xưa văn hiến” được xây dựng từnhững năm 60 - 70 của thế kỉ trước với vai trò chủ đạo củanhà sử học Trần Quốc Vượng, cùng sự đóng góp công phucủa các nhà nghiên cứu lịch sử và văn hóa truyền thông cóuy tín là Nguyễn Từ Chi, Nguyễn Cao Lũy, Nguyễn Trần Đản.');
INSERT INTO CUONSACH (ma_DauSach, ten_CuonSach, tacgia, soluong,anh_CuonSach, discount, giabia, mota)
VALUES ('1', 'Kim Đồng', 'Tô Hoài', '20','KimĐồng.jpg', '0', '24000','Cuốn sách là câu chuyện cảm động về một trong những đội viên đầu tiên của Đội Thiếu niên Tiền phong Hồ Chí Minh - anh Kim Đồng.');
INSERT INTO CUONSACH (ma_DauSach, ten_CuonSach, tacgia, soluong,anh_CuonSach, discount, giabia, mota)
VALUES ('1', 'Lý Tự Trọng', 'Nhiều tác giả', '20', 'LýTựTrọng.jpg','10', '30000', 'Câu chuyện về cuộc đời và tấm gương hi sinh anh dũng của anh Lý Tự Trọng đã được kể bằng nhiều hình thức nghệ thuật như văn học, điện ảnh, âm nhạc… Đây là lần đầu tiên hình tượng những người anh hùng thiếu niên Lý Tự Trọng được tái hiện trong tác phẩm sách tranh minh họa màu khổ lớn');
INSERT INTO CUONSACH (ma_DauSach, ten_CuonSach, tacgia, soluong,anh_CuonSach, discount, giabia, mota)
VALUES ('1', 'Trong giông gió Trường Sa', 'Nhiều tác giả', '20','TronggiônggióTrườngSa.jpg', '10', '30000', 'Qua những trang viết giàu hình ảnh và đầy xúc cảm, độc giả sẽ được trải nghiệm cuộc sống trên đảo với nhiều cung bậc cảm xúc: một Trường Sa thật dữ dội, khắc nghiệt nhưng cũng thật hiền hòa, dịu êm; cuộc sống trên đảo có lúc cô đơn rợn ngợp nhưng cũng thật thú vị, đầm ấm, chứa chan tình người. Đọc xong cuốn sách, chắc hẳn mỗi độc giả sẽ đều ấp ủ mong muốn một lần được đến với Trường Sa');
INSERT INTO CUONSACH (ma_DauSach, ten_CuonSach, tacgia, soluong,anh_CuonSach, discount, giabia, mota)
VALUES ('2', 'Vén màn ảo thuật', 'John Wood', '20','Vénmànảothuật.jpg', '10', '42000', 'Cuốn sách giới thiệu về Ảo thuật đánh lừa thị giác. Vậy là, bạn muốn trở thành một ảo thuật gia phải không nào? Chà, bạn tìm tới đúng chỗ rồi đấy. Rất nhiều bí mật đang chờ bạn khám phá bên trong cuốn sách này. Đọc xong sách, bạn sẽ trở thành bậc thầy về ảo thuật đánh lừa thị giác. Cùng bắt đầu nhé!');
INSERT INTO CUONSACH (ma_DauSach, ten_CuonSach, tacgia, soluong,anh_CuonSach, discount, giabia, mota)
VALUES ('2', 'Từ điển cảm xúc cho bé', 'Chuyện', '20','Từđiểncảmxúcchobé.jpg', '10', '124000', 'Từ điển cảm xúc cho bé là cuộc phiêu lưu của bố mẹ và bé vào thế giới ngôn từ. Những từ ngữ mới lạ mô tả rất nhiều cảm xúc, hành động, tình cảm khác nhau mà bố mẹ có thể dạy cho các bé, gieo những hạt mầm đầu tiên vào tâm trí trẻ thơ, giúp bé yêu ngôn ngữ, chăm đọc sách, ngày càng hiểu biết cuộc sống và thế giới này.');
INSERT INTO CUONSACH (ma_DauSach, ten_CuonSach, tacgia, soluong,anh_CuonSach, discount, giabia, mota)
VALUES ('2', 'Vòng quanh thế giới', 'Hoài Nam', '10','Vòngquanhthếgiới.jpg', '0', '12000', 'Mỗi đất nước trên thế giới đều có rất nhiều điều thú vị để khám phá. Với chuyến du hành Vòng quanh thế giới, bạn sẽ được ghé thăm rất nhiều quốc gia, địa điểm nổi tiếng, biết thêm nhiều truyền thống văn hóa, lễ hội lí thú của con người khắp mọi nơi. Nào, còn chần chừ gì nữa, lên đường thôi!');
INSERT INTO CUONSACH (ma_DauSach, ten_CuonSach, tacgia, soluong,anh_CuonSach, discount, giabia, mota)
VALUES ('2', 'Châu Âu huyền bí', 'Phillip Ngo', '10','ChâuÂuhuyềnbí.jpg', '0', '85000', 'Hi vọng các bạn cùng tôi bước vào chuyến phiêu lưu kì bí, tha hồ sởn gai ốc và cũng học rất nhiều điều về lịch sử, văn hóa của châu Âu.');
INSERT INTO CUONSACH (ma_DauSach, ten_CuonSach, tacgia, soluong,anh_CuonSach, discount, giabia, mota)
VALUES ('5', 'Không sao đâu con', 'Shen Nuan', '10','Khôngsaođâucon.jpg', '0', '33000', 'Thật vui khi được dự sinh nhật bạn thân, phải không các em? Bạn heo con trong câu chuyện này hớn hở mang một đoá hoa tươi đến làm quà sinh nhật bạn. Nhưng, trên đường đi, cậu gặp phải rất nhiều rắc rối. Chuyện gì xảy ra thế nhỉ? Chúng mình cùng đọc truyện nhé!');
INSERT INTO CUONSACH (ma_DauSach, ten_CuonSach, tacgia, soluong,anh_CuonSach, discount, giabia, mota)
VALUES ('5', 'Cần cả ngôi làng', 'Hillary Rodham Clinton', '10','Cầncảngôilàng.jpg', '0', '40000', 'Nếu thế giới là một ngôi làng, hãy chung tay xây một ngôi làng đáng sống cho mọi trẻ em trên đời.');
INSERT INTO CUONSACH (ma_DauSach, ten_CuonSach, tacgia, soluong,anh_CuonSach, discount, giabia, mota)
VALUES ('5', 'Tý Quậy - Tập 1', 'Đào Hải', '10','TýQuậy-Tập1.jpg', '0', '40000', '“ Những trò đùa vui học trò ai cũng đã từng kinh qua, những nghịch ngợm nho nhỏ trên đường trưởng thành… bạn đọc nhí sẽ cười nghiêng ngả, cha mẹ cũng bật cười : “ Sao Tý và Tèo giống mình ngày xưa quá vậy!” Sau những trận cười ấy, hẳn bạn sẽ nhận ra điều nhắn nhủ giản đơn mà sâu sắc của tác giả, giúp chúng ta  học hỏi và tiến bộ hơn sau mỗi tiếng cười vui vẻ.');
INSERT INTO CUONSACH (ma_DauSach, ten_CuonSach, tacgia, soluong,anh_CuonSach, discount, giabia, mota)
VALUES ('5', 'Tý Quậy - Tập 2', 'Đào Hải', '10','TýQuậy-Tập2.jpg', '0', '40000', '“ Những trò đùa vui học trò ai cũng đã từng kinh qua, những nghịch ngợm nho nhỏ trên đường trưởng thành… bạn đọc nhí sẽ cười nghiêng ngả, cha mẹ cũng bật cười : “ Sao Tý và Tèo giống mình ngày xưa quá vậy!” Sau những trận cười ấy, hẳn bạn sẽ nhận ra điều nhắn nhủ giản đơn mà sâu sắc của tác giả, giúp chúng ta  học hỏi và tiến bộ hơn sau mỗi tiếng cười vui vẻ.');
INSERT INTO CUONSACH (ma_DauSach, ten_CuonSach, tacgia, soluong,anh_CuonSach, discount, giabia, mota)
VALUES ('5', 'Tý Quậy - Tập 3', 'Đào Hải', '10','TýQuậy-Tập3.jpg', '0', '40000', '“ Những trò đùa vui học trò ai cũng đã từng kinh qua, những nghịch ngợm nho nhỏ trên đường trưởng thành… bạn đọc nhí sẽ cười nghiêng ngả, cha mẹ cũng bật cười : “ Sao Tý và Tèo giống mình ngày xưa quá vậy!” Sau những trận cười ấy, hẳn bạn sẽ nhận ra điều nhắn nhủ giản đơn mà sâu sắc của tác giả, giúp chúng ta  học hỏi và tiến bộ hơn sau mỗi tiếng cười vui vẻ.');
INSERT INTO CUONSACH (ma_DauSach, ten_CuonSach, tacgia, soluong,anh_CuonSach, discount, giabia, mota)
VALUES ('5', 'Tý Quậy - Tập 4', 'Đào Hải', '10','TýQuậy-Tập4.jpg', '0', '40000', 'Chúng ta như tìm thấy chính mình trong hình ảnh của Tý và Tèo những lúc nghịch ngợm, bị điểm kém, bị bố mẹ mắng... hay những phút giây nhận ra lỗi lầm và thấy mình ngày một trưởng thành, lớn khôn hơn, biết yêu thương cha mẹ, kính trọng thầy cô và yêu quý bè bạn.');
INSERT INTO CUONSACH (ma_DauSach, ten_CuonSach, tacgia, soluong,anh_CuonSach, discount, giabia, mota)
VALUES ('5', 'Tý Quậy - Tập 5', 'Đào Hải', '10','TýQuậy-Tập5.jpg', '0', '40000', 'Chúng ta như tìm thấy chính mình trong hình ảnh của Tý và Tèo những lúc nghịch ngợm, bị điểm kém, bị bố mẹ mắng... hay những phút giây nhận ra lỗi lầm và thấy mình ngày một trưởng thành, lớn khôn hơn, biết yêu thương cha mẹ, kính trọng thầy cô và yêu quý bè bạn.');
INSERT INTO CUONSACH (ma_DauSach, ten_CuonSach, tacgia, soluong,anh_CuonSach, discount, giabia, mota)
VALUES ('5', 'Tý Quậy - Tập 6', 'Đào Hải', '10','TýQuậy-Tập6.jpg', '0', '40000', 'Chúng ta như tìm thấy chính mình trong hình ảnh của Tý và Tèo những lúc nghịch ngợm, bị điểm kém, bị bố mẹ mắng... hay những phút giây nhận ra lỗi lầm và thấy mình ngày một trưởng thành, lớn khôn hơn, biết yêu thương cha mẹ, kính trọng thầy cô và yêu quý bè bạn.');
INSERT INTO CUONSACH (ma_DauSach, ten_CuonSach, tacgia, soluong,anh_CuonSach, discount, giabia, mota)
VALUES ('5', 'Tý Quậy - Tập 7', 'Đào Hải', '10','TýQuậy-Tập7.jpg', '0', '40000', '“ Những trò đùa vui học trò ai cũng đã từng kinh qua, những nghịch ngợm nho nhỏ trên đường trưởng thành… bạn đọc nhí sẽ cười nghiêng ngả, cha mẹ cũng bật cười : “ Sao Tý và Tèo giống mình ngày xưa quá vậy!” Sau những trận cười ấy, hẳn bạn sẽ nhận ra điều nhắn nhủ giản đơn mà sâu sắc của tác giả, giúp chúng ta  học hỏi và tiến bộ hơn sau mỗi tiếng cười vui vẻ.');
INSERT INTO CUONSACH (ma_DauSach, ten_CuonSach, tacgia, soluong,anh_CuonSach, discount, giabia, mota)
VALUES ('5', 'Bật đèn soi bí mật', 'Carron Brown', '10','Bậtđènsoibímật.jpg', '10', '45000', 'Sách tranh kiến thức đơn giản về thiên nhiên, phương tiện giao thông và kì quan thế giới với thể nghiệm mới - soi đèn pin để tìm đáp án');
INSERT INTO CUONSACH (ma_DauSach, ten_CuonSach, tacgia, soluong,anh_CuonSach, discount, giabia, mota)
VALUES ('6', 'Gintama - Tập 65', 'Hideaki Sorachi', '10','Gintama-Tập65.jpg', '10', '20000', 'Quỷ Biển buộc phải quyết chiến với Utsuro nhằm bảo vệ gia đình. Trước kẻ địch quá mạnh, ông ta chỉ còn cách lấy mạng đổi mạng. Mặt khác, Kamui vô cùng thèm khát danh hiệu “mạnh nhất vũ trụ” của cha mình, bản năng bộ tộc Yato trong cậu liền trỗi dậy.');
INSERT INTO CUONSACH (ma_DauSach, ten_CuonSach, tacgia, soluong,anh_CuonSach, discount, giabia, mota)
VALUES ('6', 'Đạo làm chồng đảm - Tập 2', 'Kousuke Oono', '10','Đạolàmchồngđảm-Tập2.jpg', '5', '30000', 'Huyền thoại của giới xã hội đen - Tatsu Bất Tử - sau khi lấy vợ bỗng lột xác trở thành một người chồng siêu đảm đang! Từ NẤU CƠM, GIẶT GIŨ đến ĐI CHỢ, QUÉT NHÀ… mọi thứ đều CHU TOÀN TUYỆT ĐỐI!!');
INSERT INTO CUONSACH (ma_DauSach, ten_CuonSach, tacgia, soluong,anh_CuonSach, discount, giabia, mota)
VALUES ('6', 'Doraemon Plus - Tập 2', 'Fujiko F Fujio', '10','DoraemonPlus-Tập2.jpg', '20', '20000', 'Doraemon Plus tập hợp những truyện ngắn chưa từng có trong bộ truyện Doraemon 45 tập đã rất quen thuộc với tất cả chúng ta. Đặc biệt Doraemon Plus tập 6 còn giới thiệu 21 truyện ngắn cực kì hấp dẫn và chưa từng được công bố!');
INSERT INTO CUONSACH (ma_DauSach, ten_CuonSach, tacgia, soluong,anh_CuonSach, discount, giabia, mota)
VALUES ('6', 'Gintama - Tập 63', 'Hideaki Sorachi', '10','Gintama-Tập63.jpg', '10', '20000', 'Cuộc chiến với tam hung tinh của Mưa Xuân và đám cường địch đã khiến Katsura, Sakamoto, Gintoki dần kiệt sức. Katsura đọ kiếm với gã lực sĩ đội trưởng đội 4, trong khi đó Sakamoto, Gintoki cũng khổ chiến với tinh thần của những võ sĩ cuối cùng còn lại…');
INSERT INTO CUONSACH (ma_DauSach, ten_CuonSach, tacgia, soluong,anh_CuonSach, discount, giabia, mota)
VALUES ('6', 'Gintama - Tập 64', 'Hideaki Sorachi', '10','Gintama-Tập64.jpg', '10', '20000', 'Khi cuộc chiến giữa các phe phái Mưa Xuân, Quỷ Biển và nhóm Gintoki lên tới đỉnh điểm gay cấn thì cuối cùng, “người đó” đã tỉnh lại… Lần nào gặp nhau, hai cha con Kamui và Quỷ Biển cũng tranh cãi gay gắt, nay có thêm sự góp mặt của Kagura, bí mật về gia đình họ đã dần được hé lộ…');
INSERT INTO CUONSACH (ma_DauSach, ten_CuonSach, tacgia, soluong,anh_CuonSach, discount, giabia, mota)
VALUES ('6', 'Gintama - Tập 66', 'Hideaki Sorachi', '10','Gintama-Tập65.jpg', '10', '20000', 'Khi xưa, thầy Shoyo đã tới thôn Tùng Hạ mở lớp học cùng đệ tử đầu tiên là Oboro. Sau đó, Oboro rời khỏi đó, trở thành thủ lĩnh của Naraku. Chính hắn đã tiết lộ âm mưu kinh thiên động địa mà Utsuro suy tính bấy lâu. Utsuro đã chọn cách thức nào để kết thúc sinh mệnh vĩnh cửu của mình đây?');
INSERT INTO CUONSACH (ma_DauSach, ten_CuonSach, tacgia, soluong,anh_CuonSach, discount, giabia, mota)
VALUES ('6', 'Gintama - Tập 67', 'Hideaki Sorachi', '10','Gintama-Tập66.jpg', '10', '20000', 'Để ngăn chặn chiến tranh tầm cỡ vũ trụ do Utsuro khởi xướng. Tokugawa Nobunobu âm mưu liên minh mới quân giải phóng. Cuộc liên minh ấy sẽ đem lại kết quả gì đây? Mặt khác, đội quân bất tử Utsuro phóng thích đang hoành hành khắp Edo, tình hình nguy khốn hơn bao giờ hết. Ấy thế mà, lại một lần nữa...');
INSERT INTO CUONSACH (ma_DauSach, ten_CuonSach, tacgia, soluong,anh_CuonSach, discount, giabia, mota)
VALUES ('6', 'Gintama - Tập 68', 'Hideaki Sorachi', '10','Gintama-Tập67.jpg', '10', '20000', 'Người dân Edo và lính Mạc Phủ cũ đang nhất tề đứng dậy chống lại gọng kìm của không quân và lục quân giải phóng. Giữ lúc dầu sôi lửa bỏng, kĩ sư vĩ đại Gengai đã tung ra vũ khí bí mật vô cùng lợi hại. Cùng lúc đó, Tiệm Vạn Năng cũng tái xuất giang hồ!!');
INSERT INTO CUONSACH (ma_DauSach, ten_CuonSach, tacgia, soluong,anh_CuonSach, discount, giabia, mota)
VALUES ('6', 'Gintama - Tập 69', 'Hideaki Sorachi', '10','Gintama-Tập68.jpg', '10', '20000', 'Nhằm hồi phục sau thương tích do đại bác khổng lồ Gengai gây ra, quân Shinra đã bắt cóc Otae và mọi người rồi đuổi theo “kẻ khai thác năng lượng”. Trước tình hình Edo đang tan rã, nhóm “hộ vệ Kabuki” hết sức đau lòng, họ buộc phải quay lại và giải cứu cho những con tin...');
INSERT INTO CUONSACH (ma_DauSach, ten_CuonSach, tacgia, soluong,anh_CuonSach, discount, giabia, mota)
VALUES ('6', 'Gintama - Tập 70', 'Hideaki Sorachi', '10','Gintama-Tập69.jpg', '10', '20000', 'Quân giải phóng đã bắt cóc ông Gengai để ngăn chặn virus-những cỗ máy siêu nhỏ- đang lây lan khắp Địa Cầu. Chúng cưỡng ép ông bằng cách bổ sung Trái Đất vào danh sách những hành tinh chờ phá huỷ. Ngay thời điểm nguy khốn ấy, bất ngờ xuất hiện một đạo quân tiếp viện...');
INSERT INTO CUONSACH (ma_DauSach, ten_CuonSach, tacgia, soluong,anh_CuonSach, discount, giabia, mota)
VALUES ('6', 'Gintama - Tập 71', 'Hideaki Sorachi', '10','Gintama-Tập70.jpg', '10', '20000', 'Ý đồ của Utsuro đã rõ! Hắn sẽ cholong mạch Altana va chạm với vũ khí của quân giải phóng để tạo ra một cú nổ lớn đủ sức phá tan tành cả thế giới. Để ngăn chặn họng pháo khổng lồ của quân giải phóng, Binh Đoàn Quỷ đã kêu gọi toàn quân tham chiến. Và rồi, vào giờ khắc quan trọng, một vị anh hùng đã giáng thế!?');
INSERT INTO CUONSACH (ma_DauSach, ten_CuonSach, tacgia, soluong,anh_CuonSach, discount, giabia, mota)
VALUES ('6', 'Gintama - Tập 72', 'Hideaki Sorachi', '10','Gintama-Tập71.jpg', '10', '20000', 'Ensho và Takasugi. Thứ trói buộc những kẻ sẵn sàng vứt bỏ mạng sống của mình nơi chiến trường phải chăng là xiềng xích của thù hận? Trên mặt đất lúc này, còn có một nhóm người nọ cũng đang cật lực góp phần ngăn chặn họng pháo khổng lồ kia. Thậm chí là cả cún con Sadaharu...');
INSERT INTO CUONSACH (ma_DauSach, ten_CuonSach, tacgia, soluong,anh_CuonSach, discount, giabia, mota)
VALUES ('6', 'Gintama - Tập 73', 'Hideaki Sorachi', '10','Gintama-Tập72.jpg', '10', '20000', 'Chiến hạm Amenotori đã mất kiểm soát và vẫn đang tiếp tục rơi tự do xuống Địa Cầu. Trước cảnhdân tình đang căng thẳng nghĩ cách ngăn con tàu này va chạm với nguồn Altana sôi sục bên dưới, chú Hasegawa đã nảy ra một ý tưởng không ai ngờ tới!? Giữa lúc dầu sôi lửa bỏng, cơ thể Utsuro đã có một thay đổi lạ lùng khi giao chiến với tiệm Vạn Năng...');
INSERT INTO CUONSACH (ma_DauSach, ten_CuonSach, tacgia, soluong,anh_CuonSach, discount, giabia, mota)
VALUES ('6', 'Gintama - Tập 74', 'Hideaki Sorachi', '10','Gintama-Tập73.jpg', '10', '20000', 'Cảm xúc của Utsuro đã bắt đầu lay động khi chứng kiến những con người với sinh mệnh ngắn ngủi đang không ngần ngại xả thân vì nhau. Thêm nữa, các phản ứng của long mạch Altana đã suy yếu khiến Utsuro không thể chống đỡ sự tấn công dồn dập của bọn Gintoki. Trận chiến sinh tử đang dần đi vào hồi kết và vận mệnh của Trái Đất sẽ ra sao?');
INSERT INTO CUONSACH (ma_DauSach, ten_CuonSach, tacgia, soluong,anh_CuonSach, discount, giabia, mota)
VALUES ('6', 'Gintama - Tập 75', 'Hideaki Sorachi', '10','Gintama-Tập74.jpg', '10', '20000', 'Gintoki bị một đám không rõ thân thế truy đuổi gắt gao. Chúng lăm le cướp đi một món hàng cực kì quý giá trên người anh. Sau 2 năm ở ẩn và tu luyện đạt tới trí tuệ siêu việt, Gintoki đã thu về được thành quả gì?');

INSERT INTO GIOHANG(ma_Customer, ma_CuonSach, soluong)
VALUES ('1', '1', '2');
INSERT INTO GIOHANG(ma_Customer, ma_CuonSach, soluong)
VALUES ('1', '2', '1');
INSERT INTO GIOHANG(ma_Customer, ma_CuonSach, soluong)
VALUES ( '2', '7', '2');
INSERT INTO GIOHANG(ma_Customer, ma_CuonSach, soluong)
VALUES ('3','5', '1');

INSERT INTO DONHANG(ma_Customer, diachi, sdt, tongtien, ngaydat, tinhtrang, ghichu)
VALUES ('3', 'KTX Khu D1', '0987935915', '190000', '2020-12-05', 'Đã giao','Đã thanh toán');
INSERT INTO DONHANG(ma_Customer, diachi, sdt, tongtien, ngaydat, tinhtrang, ghichu)
VALUES ('1', 'Xuân Thọ - Đà Lạt', '0368103208', '135000', '2020-12-04', 'Chưa giao','Chưa thanh toán');
INSERT INTO DONHANG(ma_Customer, diachi, sdt, tongtien, ngaydat, tinhtrang, ghichu)
VALUES ('3', 'Bến Tre', '0327300035', '1595000', '2020-12-06', 'Đã giao','Đã thanh toán');

INSERT INTO CHITIETDONHANG
VALUES ('1', '7', '2', '95000');
INSERT INTO CHITIETDONHANG
VALUES ('2', '2', '1', '135000');
INSERT INTO CHITIETDONHANG
VALUES ('3', '4', '1', '1400000');
INSERT INTO CHITIETDONHANG
VALUES ('3', '5', '3', '65000');

INSERT INTO GIAOHANG
VALUES('1', '1');
INSERT INTO GIAOHANG
VALUES('3', '2');

INSERT INTO REVIEW(ma_Customer, ngay_Review, binhluan)
VALUES ('3', '2020-12-12', 'Sách còn mới, như quảng cáo. Nói chung tốt, sẽ quay lại ủng hộ shop tiếp ạ');