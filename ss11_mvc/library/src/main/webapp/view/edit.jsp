<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 03/13/2023
  Time: 10:57 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="col-md-5 main">
    <h3>Update book</h3>
    <form action="" method="post">
        <input type="hidden" name="id" value="${books.id}">
        <label for="title">Input title</label>
        <input type="text" class="form-control" name="title" id="title" value="${books.name}">

        <label for="pageSize">Input page size</label>
        <input type="text" class="form-control" name="pageSize" id="pageSize" value="${books.pageSize}">

        <label for="author">Input author</label>
        <input type="text" class="form-control" name="author" id="author" value="${books.author}">

        <label for="categoryName">Input category name</label>
        <select class="form-control" id="categoryName" name="categoryName">
            <option value="${books.categoryName}">${books.categoryName}</option>
            <c:forEach var="booksCategory" items="${CategoryList}">
                <option value="${Category.categoryName}">${Category.categoryName}</option>
            </c:forEach>
        </select>

        <input type="submit" value="update" class="btn btn-secondary mt-3">
    </form>
</div>
</body>
</html>
