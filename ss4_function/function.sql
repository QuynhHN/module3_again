use `library`;
-- Thống kê các đầu sách được mượn nhiều nhất--
select books.b_name as `Book name`, count(book_id) as 'amount'
from borrows
left join books on books.b_id=borrows.book_id 
group by borrows.book_id
order by `amount` desc;

select books.b_id, books.b_name,count(books.b_id) as sl from borrows
join books on books.b_id = borrows.book_id
group by books.b_id
having sl = (
select count(books.b_id) as sl from borrows
join books  on books.b_id = borrows.book_id
group by books.b_id
order by sl desc limit 1
);

-- Thống kê các đầu sách chưa được mượn--
select books.name_book,count(books.book_id) as sl from borrow
join books on books.book_id = borrow.book_id
group by books.book_id
having sl = (
select count(books.book_id) as sl from borrow
join books  on books.book_id = borrow.book_id
group by books.book_id
order by sl desc limit 0
);
-- - Lấy ra danh sách các học viên đã từng mượn sách và sắp xếp  theo số lượng mượn sách từ lớn đến nhỏ					
select name_student, count(borrow.borrow_id) as sl from students 
join borrow on students.student_id = borrow.student_id
where students.student_id <=4
group by students.name_student
order by sl desc;

-- - Lấy ra các học viên mượn sách nhiều nhất của thư viện				
select name_student, count(borrow.borrow_id) as sl from students 
join borrow on students.student_id = borrow.student_id
group by students.name_student
order by sl desc limit 2;

-- - Tao index cho cột  title của bảng books			
create index index_name_book on books(name_book);

-- - Tạo 1 view để lấy ra danh sách các quyển sách đã được mượn, có hiển thị thêm cột số lần đã được mượn					
create view list_book_borrow as select name_book, count(borrow.borrow_id) as sl from books 
join borrow on books.book_id = borrow.book_id
group by books.name_book
order by sl asc;

-- - Viết 1 stored procedure thêm mới book trong database với tham số kiểu IN					
delimiter //
create procedure add_books(in book_id int, name_book varchar(50),in page_size int,in author_id int,in category_id int
)
begin
insert into books(book_id, name_book, page_size, author_id, category_id) values (1,'tat den',4,2,3);
end // delimiter //;