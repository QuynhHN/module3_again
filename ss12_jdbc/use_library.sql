use `library`;
-- Lấy ra toàn bộ sách có trong thư viện, cùng với tên thể loại và tác giả --
select books.b_name as 'Book name' , authors.a_name as 'Author', category.c_name as 'Category' from books
left join category on category.c_id = books.b_category_id
left join authors on authors.a_id = books.b_author_id;

-- Lấy ra danh sách các học viên đã từng mượn sách và sắp xếp danh sách theo theo tên từ a->z--
select students.s_name as 'Student name' from borrows
left join students on students.s_id=borrows.student_id
group by s_name
order by s_name;

-- Lấy ra  2 quyển sách được mượn nhiều nhất--
select books.b_name as `Book name`, count(book_id) as 'amount'
from borrows
left join books on books.b_id=borrows.book_id 
group by borrows.book_id
order by `amount` desc limit 2;
-- Viết 1 stored procedure thêm mới book trong database
DELIMITER //
create procedure update_books (in books_id int, in books_name varchar(50),in books_page_size int, in books_author_id int, in books_category_id int)
begin
insert into books(b_name,b_page_size,b_author_id,b_category_id) values (books_name,books_page_size,books_author_id,books_category_id);
end; //
DELIMITER ;
call update_books(?,?,?,?);
-- Viết 1 stored procedure để  xoá book theo id trong database
DELIMITER //
create procedure delete_books (in books_id int)
begin
delete from books where b_id = books_id;
end; //
DELIMITER ;


