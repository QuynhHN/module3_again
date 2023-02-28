use `case_study_database`;
-- Task 2.Hiển thị thông tin của tất cả nhân viên có trong hệ thống có tên bắt đầu là một trong các ký tự “H”, “T” hoặc “K” và có tối đa 15 kí tự.
SELECT 
    *
FROM
    nhan_vien
WHERE
    SUBSTRING_INDEX(nhan_vien.ho_ten, ' ', - 1) LIKE 'H%'
        OR SUBSTRING_INDEX(nhan_vien.ho_ten, ' ', - 1) LIKE 'T%'
        OR SUBSTRING_INDEX(nhan_vien.ho_ten, ' ', - 1) LIKE 'K%'
        AND LENGTH(ho_ten) <= 15;
-- Task 3.Hiển thị thông tin của tất cả khách hàng có độ tuổi từ 18 đến 50 tuổi và có địa chỉ ở “Đà Nẵng” hoặc “Quảng Trị”.
SELECT 
    *
FROM
    khach_hang
WHERE
    (YEAR(CURDATE()) - YEAR(ngay_sinh)) BETWEEN 18 AND 50
        AND dia_chi LIKE '%Đà Nẵng'
        OR dia_chi LIKE '%Quảng Trị';
-- Task 4.Đếm xem tương ứng với mỗi khách hàng đã từng đặt phòng bao nhiêu lần. Kết quả hiển thị được sắp xếp tăng dần theo số lần đặt phòng của khách hàng. Chỉ đếm những khách hàng nào có Tên loại khách hàng là “Diamond”.
SELECT 
    khach_hang.ma_khach_hang,
    khach_hang.ho_ten,
    COUNT(hop_dong.ma_khach_hang) AS 'Số lần đặt phòng của khách hàng'
FROM
    hop_dong
        INNER JOIN
    khach_hang ON khach_hang.ma_khach_hang = hop_dong.ma_khach_hang
        INNER JOIN
    loai_khach ON loai_khach.ma_loai_khach = khach_hang.ma_loai_khach
WHERE
    loai_khach.ten_loai_khach LIKE 'Diamond'
GROUP BY khach_hang.ma_khach_hang
ORDER BY COUNT(hop_dong.ma_khach_hang);
-- Task 5.Hiển thị ma_khach_hang, ho_ten, ten_loai_khach, ma_hop_dong, ten_dich_vu, ngay_lam_hop_dong, ngay_ket_thuc,
-- tong_tien (Với tổng tiền được tính theo công thức như sau: Chi Phí Thuê + Số Lượng * Giá, với Số Lượng và Giá là từ bảng dich_vu_di_kem, hop_dong_chi_tiet) cho tất cả các khách hàng đã từng đặt phòng. (những khách hàng nào chưa từng đặt phòng cũng phải hiển thị ra).
SELECT 
    khach_hang.ma_khach_hang,
    khach_hang.ho_ten,
    loai_khach.ten_loai_khach,
    hop_dong.ma_hop_dong,
    dich_vu.ten_dich_vu,
    hop_dong.ngay_lam_hop_dong,
    hop_dong.ngay_ket_thuc,
    SUM((dich_vu.chi_phi_thue + IFNULL((hop_dong_chi_tiet.so_luong * dich_vu_di_kem.gia),
            0))) AS 'Tổng tiền'
FROM
    hop_dong
        LEFT JOIN
    hop_dong_chi_tiet ON hop_dong_chi_tiet.ma_hop_dong = hop_dong.ma_hop_dong
        LEFT JOIN
    dich_vu_di_kem ON hop_dong_chi_tiet.ma_dich_vu_di_kem = dich_vu_di_kem.ma_dich_vu_di_kem
        LEFT JOIN
    dich_vu ON hop_dong.ma_dich_vu = dich_vu.ma_dich_vu
        RIGHT JOIN
    khach_hang ON hop_dong.ma_khach_hang = khach_hang.ma_khach_hang
        INNER JOIN
    loai_khach ON khach_hang.ma_loai_khach = loai_khach.ma_loai_khach
GROUP BY hop_dong.ma_hop_dong , khach_hang.ma_khach_hang
ORDER BY khach_hang.ma_khach_hang;
-- Task 6.Hiển thị ma_dich_vu, ten_dich_vu, dien_tich, chi_phi_thue, ten_loai_dich_vu của tất cả các loại dịch vụ chưa từng được khách hàng thực hiện đặt từ quý 1 của năm 2021 (Quý 1 là tháng 1, 2, 3).
SELECT 
    dich_vu.ma_dich_vu,
    dich_vu.ten_dich_vu,
    dich_vu.dien_tich,
    dich_vu.chi_phi_thue,
    loai_dich_vu.ten_loai_dich_vu
FROM
    dich_vu
        INNER JOIN
    loai_dich_vu ON loai_dich_vu.ma_loai_dich_vu = dich_vu.ma_loai_dich_vu
        LEFT JOIN
    hop_dong ON dich_vu.ma_dich_vu = hop_dong.ma_dich_vu
WHERE
    (MONTH(hop_dong.ngay_lam_hop_dong) BETWEEN 4 AND 12
        AND YEAR(hop_dong.ngay_lam_hop_dong) = 2021)
GROUP BY dich_vu.ma_dich_vu
ORDER BY dich_vu.ma_dich_vu;
-- Task 7.	Hiển thị thông tin ma_dich_vu, ten_dich_vu, dien_tich, so_nguoi_toi_da, chi_phi_thue, ten_loai_dich_vu của tất cả các loại dịch vụ đã từng được khách hàng đặt phòng trong năm 2020
-- nhưng chưa từng được khách hàng đặt phòng trong năm 2021.
SELECT 
    dich_vu.ma_dich_vu,
    dich_vu.ten_dich_vu,
    dich_vu.dien_tich,
    dich_vu.so_nguoi_toi_da,
    dich_vu.chi_phi_thue,
    loai_dich_vu.ten_loai_dich_vu
FROM
    dich_vu
        JOIN
    loai_dich_vu ON loai_dich_vu.ma_loai_dich_vu = dich_vu.ma_loai_dich_vu
        JOIN
    hop_dong ON dich_vu.ma_dich_vu = hop_dong.ma_dich_vu
WHERE
    dich_vu.ma_dich_vu NOT IN (SELECT 
            hop_dong.ma_dich_vu
        FROM
            hop_dong
        WHERE
            YEAR(hop_dong.ngay_lam_hop_dong) = 2021
                AND YEAR(hop_dong.ngay_lam_hop_dong) != 2020)
GROUP BY dich_vu.ten_dich_vu;
SET sql_mode=(SELECT REPLACE(@@sql_mode,'ONLY_FULL_GROUP_BY',''));
-- and not exists (select * from hop_dong where hop_dong.ngay_lam_hop_dong = 2021) 
-- Task 8.	Hiển thị thông tin ho_ten khách hàng có trong hệ thống, với yêu cầu ho_ten không trùng nhau.
SELECT DISTINCT
    khach_hang.ho_ten
FROM
    khach_hang
ORDER BY khach_hang.ho_ten;
-- Task 9.	Thực hiện thống kê doanh thu theo tháng, nghĩa là tương ứng với mỗi tháng trong năm 2021 thì sẽ có bao nhiêu khách hàng thực hiện đặt phòng.
SELECT 
    MONTH(hop_dong.ngay_lam_hop_dong) AS tháng,
    COUNT(khach_hang.ma_khach_hang) AS so_luong
FROM
    hop_dong
        JOIN
    khach_hang ON hop_dong.ma_khach_hang = khach_hang.ma_khach_hang
WHERE
    YEAR(hop_dong.ngay_lam_hop_dong) = 2021
GROUP BY MONTH(hop_dong.ngay_lam_hop_dong)
ORDER BY tháng;
-- Task 10.	Hiển thị thông tin tương ứng với từng hợp đồng thì đã sử dụng bao nhiêu dịch vụ đi kèm. Kết quả hiển thị bao gồm ma_hop_dong, ngay_lam_hop_dong,
-- ngay_ket_thuc, tien_dat_coc, so_luong_dich_vu_di_kem (được tính dựa trên việc sum so_luong ở dich_vu_di_kem).
SELECT 
    hop_dong.ma_hop_dong,
    hop_dong.ngay_lam_hop_dong,
    hop_dong.ngay_ket_thuc,
    hop_dong.tien_dat_coc,
    SUM(hop_dong_chi_tiet.so_luong) AS so_luong_dich_vu_di_kem
FROM
    hop_dong
        LEFT JOIN
    hop_dong_chi_tiet ON hop_dong.ma_hop_dong = hop_dong_chi_tiet.ma_hop_dong
GROUP BY hop_dong.ma_hop_dong;
-- Task 11.	Hiển thị thông tin các dịch vụ đi kèm đã được sử dụng bởi những khách hàng có ten_loai_khach là “Diamond” và có dia_chi ở “Vinh” hoặc “Quảng Ngãi”
SELECT 
    dich_vu_di_kem.ma_dich_vu_di_kem,
    dich_vu_di_kem.ten_dich_vu_di_kem
FROM
    dich_vu_di_kem
        JOIN
    hop_dong_chi_tiet ON dich_vu_di_kem.ma_dich_vu_di_kem = hop_dong_chi_tiet.ma_dich_vu_di_kem
        JOIN
    hop_dong ON hop_dong_chi_tiet.ma_hop_dong = hop_dong.ma_hop_dong
        JOIN
    khach_hang ON hop_dong.ma_khach_hang = khach_hang.ma_khach_hang
        JOIN
    loai_khach ON khach_hang.ma_loai_khach = loai_khach.ma_loai_khach
WHERE
    loai_khach.ten_loai_khach LIKE 'Diamond'
        AND (khach_hang.dia_chi LIKE '%Vinh'
        OR khach_hang.dia_chi LIKE '%Quảng Ngãi')
GROUP BY dich_vu_di_kem.ma_dich_vu_di_kem;
-- Task 12.	Hiển thị thông tin ma_hop_dong, ho_ten (nhân viên), ho_ten (khách hàng), so_dien_thoai (khách hàng), ten_dich_vu, so_luong_dich_vu_di_kem 
-- (được tính dựa trên việc sum so_luong ở dich_vu_di_kem), tien_dat_coc của tất cả các dịch vụ đã từng được khách hàng đặt vào 3 tháng cuối năm 2020
-- nhưng chưa từng được khách hàng đặt vào 6 tháng đầu năm 2021
SELECT 
    hd.ma_hop_dong,
    nv.ho_ten as ho_ten_nv,
    kh.ho_ten as ho_ten_kh,
    kh.so_dien_thoai as so_dien_thoai_kh,
    dv.ten_dich_vu,
    IFNULL(SUM(so_luong), 0) so_luong_dich_vu_di_kem,
    tien_dat_coc
FROM
    hop_dong hd
        LEFT JOIN
    nhan_vien nv ON hd.ma_nhan_vien = nv.ma_nhan_vien
        LEFT JOIN
    khach_hang kh ON hd.ma_khach_hang = kh.ma_khach_hang
        LEFT JOIN
    dich_vu dv ON hd.ma_dich_vu = dv.ma_dich_vu
        LEFT JOIN
    hop_dong_chi_tiet hdct ON hd.ma_hop_dong = hdct.ma_hop_dong
WHERE
    (QUARTER(ngay_lam_hop_dong) = 4
        AND YEAR(ngay_lam_hop_dong) = 2020)
        AND hd.ma_hop_dong NOT IN (MONTH(ngay_lam_hop_dong) BETWEEN 1 AND 6
        AND YEAR(ngay_lam_hop_dong) = 2021)
GROUP BY hd.ma_hop_dong;
-- Task 13.	Hiển thị thông tin các Dịch vụ đi kèm được sử dụng nhiều nhất bởi các Khách hàng đã đặt phòng.
create view view_dich_vu_di_kem as 
select sum(ifnull(so_luong,0)) as so_luong_dich_vu_di_kem 
from hop_dong_chi_tiet
group by ma_dich_vu_di_kem;
select dich_vu_di_kem.ma_dich_vu_di_kem, dich_vu_di_kem.ten_dich_vu_di_kem, dich_vu_di_kem.gia, dich_vu_di_kem.don_vi, dich_vu_di_kem.trang_thai,
sum(ifnull(hop_dong_chi_tiet.so_luong,0)) as so_luong_dich_vu_di_kem
from hop_dong_chi_tiet as hdct
join dich_vu_di_kem as dvdk on dvdk.ma_dich_vu_di_kem = hdct.ma_dich_vu_di_kem
group by hdct.ma_dich_vu_di_kem
having so_luong_dich_vu_di_kem = (select max(view_dich_vu_di_kem.so_luong_dich_vu_di_kem)from view_dich_vu_di_kem); 
-- Task 14.	Hiển thị thông tin tất cả các Dịch vụ đi kèm chỉ mới được sử dụng một lần duy nhất. Thông tin hiển thị bao gồm ma_hop_dong,
-- ten_loai_dich_vu, ten_dich_vu_di_kem, so_lan_su_dung
SELECT 
    hd.ma_hop_dong,
    ldv.ten_loai_dich_vu,
    dvdk.ten_dich_vu_di_kem,
    COUNT(hdct.ma_hop_dong_chi_tiet) AS so_lan_su_dung
FROM
    hop_dong AS hd
        JOIN
    dich_vu AS dv ON hd.ma_dich_vu = dv.ma_dich_vu
        JOIN
    loai_dich_vu AS ldv ON ldv.ma_loai_dich_vu = dv.ma_loai_dich_vu
        JOIN
    hop_dong_chi_tiet AS hdct ON hd.ma_hop_dong = hdct.ma_hop_dong
        JOIN
    dich_vu_di_kem AS dvdk ON dvdk.ma_dich_vu_di_kem = hdct.ma_dich_vu_di_kem
GROUP BY hdct.ma_dich_vu_di_kem
HAVING so_lan_su_dung = 1
ORDER BY hd.ma_hop_dong;

-- Task 15.	Hiển thi thông tin của tất cả nhân viên bao gồm ma_nhan_vien, ho_ten, ten_trinh_do, ten_bo_phan, so_dien_thoai,
-- dia_chi mới chỉ lập được tối đa 3 hợp đồng từ năm 2020 đến 2021.
SELECT 
    nv.ma_nhan_vien,
    nv.ho_ten,
    td.ten_trinh_do,
    bp.ten_bo_phan,
    nv.so_dien_thoai,
    nv.dia_chi
FROM
    nhan_vien AS nv
        JOIN
    trinh_do AS td ON nv.ma_trinh_do = td.ma_trinh_do
        JOIN
    bo_phan AS bp ON bp.ma_bo_phan = nv.ma_bo_phan
        JOIN
    hop_dong AS hd ON nv.ma_nhan_vien = hd.ma_nhan_vien
WHERE
    YEAR(ngay_lam_hop_dong) BETWEEN 2020 AND 2021
GROUP BY nv.ma_nhan_vien
HAVING COUNT(hd.ma_hop_dong) <= 3
ORDER BY nv.ma_nhan_vien;
-- Task 16.	Xóa những Nhân viên chưa từng lập được hợp đồng nào từ năm 2019 đến năm 2021.
DELETE FROM nhan_vien 
WHERE
    ma_nhan_vien IN (SELECT 
        cid
    FROM
        (SELECT DISTINCT
            nv.ma_nhan_vien AS cid
        FROM
            nhan_vien AS nv
        JOIN hop_dong AS hd ON nv.ma_nhan_vien = hd.ma_nhan_vien
        
        WHERE
            YEAR(ngay_lam_hop_dong) BETWEEN 2019 AND 2021
        GROUP BY nv.ma_nhan_vien
        HAVING COUNT(hd.ma_hop_dong) < 1) AS c);
-- SET SQL_SAFE_UPDATES = 0;
-- Task 17.	Cập nhật thông tin những khách hàng có ten_loai_khach từ Platinum lên Diamond,
-- chỉ cập nhật những khách hàng đã từng đặt phòng với Tổng Tiền thanh toán trong năm 2021 là lớn hơn 10.000.000 VNĐ.
CREATE VIEW v_tong_tien AS
    SELECT 
        kh.ma_khach_hang,
        lk.ten_loai_khach,
        SUM(IFNULL(dv.chi_phi_thue, 0) + IFNULL(hdct.so_luong, 0) * IFNULL(dvdk.gia, 0)) tong_tien
    FROM
        khach_hang kh
            JOIN
        loai_khach lk ON lk.ma_loai_khach = kh.ma_loai_khach
            JOIN
        hop_dong hd ON hd.ma_khach_hang = kh.ma_khach_hang
            JOIN
        dich_vu dv ON dv.ma_dich_vu = hd.ma_dich_vu
            JOIN
        hop_dong_chi_tiet hdct ON hdct.ma_hop_dong = hd.ma_hop_dong
            JOIN
        dich_vu_di_kem dvdk ON dvdk.ma_dich_vu_di_kem = hdct.ma_dich_vu_di_kem
    WHERE
        lk.ten_loai_khach = 'Platinium'
            AND YEAR(ngay_lam_hop_dong) = 2021
    GROUP BY lk.ma_loai_khach , kh.ma_khach_hang;

UPDATE khach_hang 
SET 
    ma_loai_khach = 1
WHERE
    ma_khach_hang = (SELECT 
            v_tong_tien.ma_khach_hang
        FROM
            v_tong_tien
        WHERE
            v_tong_tien.tong_tien > '1000000'
                AND ten_loai_khach = 'Platinium');

SELECT 
    kh.ma_khach_hang, kh.ho_ten, lk.ten_loai_khach
FROM
    khach_hang kh
        JOIN
    loai_khach lk ON kh.ma_loai_khach = lk.ma_loai_khach
WHERE
    kh.ho_ten = 'Nguyễn Tâm Đắc';
-- Task 18.	Xóa những khách hàng có hợp đồng trước năm 2021 (chú ý ràng buộc giữa các bảng).
DELETE FROM khach_hang 
WHERE
    ma_khach_hang IN (SELECT 
        idkh
    FROM
        (SELECT DISTINCT
            khach_hang.ma_khach_hang AS idkh
        FROM
            khach_hang
        JOIN hop_dong ON khach_hang.ma_khach_hang = hop_dong.ma_khach_hang
        
        WHERE
            YEAR(ngay_lam_hop_dong) < 2021) AS c);
SET FOREIGN_KEY_CHECKS=0;
-- Task 19.	Cập nhật giá cho các dịch vụ đi kèm được sử dụng trên 10 lần trong năm 2020 lên gấp đôi.
UPDATE dich_vu_di_kem 
SET 
    gia = gia * 10
WHERE
    ma_dich_vu_di_kem IN (SELECT 
            id
        FROM
            (SELECT 
                ma_dich_vu_di_kem AS id
            FROM
                dich_vu_di_kem
            GROUP BY ma_dich_vu_di_kem
            HAVING COUNT(ma_dich_vu_di_kem) > 10) AS c);