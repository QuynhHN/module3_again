use `library`;
-- Tạo index cho cột  title của bảng books--
create index index_b_name on books(b_name);
drop index index_b_name on books;

-- Tạo 1 view để lấy ra danh sách các quyển sách đã được mượn, có hiển thị thêm cột số lần đã được mượn--
create view view_borows_book as select books.b_name, count(borrows.id) as `amount`
from books
join borrows on books.b_id= borrows.book_id
group by books.b_id
order by amount asc;

-- Viết 1 stored procedure thêm mới book trong database với tham số kiểu IN--
delimiter //
create procedure add_books(in id int, name_book varchar(50),in page_size int,in author_id int,in category_id int
)
begin
insert into books(b_id, b_name, b_page_size, b_author_id, b_category_id) values (id,name_book,page_size,author_id,category_id);
end // 
delimiter ;
-- insert into books(b_id, b_name, b_page_size, b_author_id, b_category_id) values (6,'Tat',44,4,2);
call add_books(7,'Cam',45,3,2);
