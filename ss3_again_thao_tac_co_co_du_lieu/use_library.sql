use `library`;
-- Lấy ra toàn bộ sách có trong thư viện, cùng với tên thể loại và tác giả --
select books.b_name as 'Tên sách' , authors.a_name as 'Tác giả', category.c_name as 'Thể loại' from books
left join category on category.c_id = books.b_category_id
left join authors on authors.a_id = books.b_author_id;

-- Lấy ra danh sách các học viên đã từng mượn sách và sắp xếp danh sách theo theo tên từ a->z--
select students.s_name as 'Tên học viên' from borrows
left join students on students.s_id=borrows.student_id
group by s_name
order by s_name;

-- Lấy ra  2 quyển sách được mượn nhiều nhất--
select books.b_name, count(book_id) as 'amount'
from borrows
left join books on books.b_id=borrows.book_id 
group by borrows.book_id
order by `amount` desc limit 2;
