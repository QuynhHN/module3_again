use `library`;
-- Thống kê các đầu sách được mượn nhiều nhất--
select books.b_id as `books id`,books.b_name as `Books name`, count(books.b_id) as 'amount'
from borrows
join books on books.b_id=borrows.book_id 
group by books.b_id
having amount= (
select count(books.b_id) as `amount` 
from borrows
join books  on books.b_id = borrows.book_id
group by books.b_id
order by amount desc limit 1
);

-- Thống kê các đầu sách chưa từng được mượn--
select books.b_name,books.b_id,count(books.b_id) as `amount` 
from borrows
join books on books.b_id = borrows.book_id
group by books.b_id
having amount = (
select count(books.b_id) as amount from borrows
join books on books.b_id = borrows.book_id
group by books.b_id
order by amount desc limit 0
);
-- Thống kê các đầu sách chưa được mượn --
select books.b_name,books.b_id
from borrows
inner join books on books.b_id = borrows.book_id
where  not exists (select books.b_id where curdate() between borrows.borrow_date and borrows.return_date)
group by books.b_id;

select books.b_name,books.b_id
from books
left join borrows on books.b_id=borrows.book_id 
where borrows.book_id not in (select borrows.book_id from borrows where curdate() between borrows.borrow_date and borrows.return_date)
group by books.b_id;

-- Lấy ra danh sách các học viên đã từng mượn sách và sắp xếp  theo số lượng mượn sách từ lớn đến nhỏ					
select s_name, count(borrows.id) as `amount` 
from students 
join borrows on students.s_id = borrows.student_id
where students.s_id
group by students.s_id
order by amount desc;

-- - Lấy ra các học viên mượn sách nhiều nhất của thư viện				
select s_name, count(borrows.id) as `amount`
from students 
join borrows on students.s_id = borrows.student_id
group by students.s_id
order by amount desc limit 2;
