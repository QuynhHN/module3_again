<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 03/15/2023
  Time: 1:08 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Update</title>
  <link rel="stylesheet" href="../css/bootstrap-grid.css">
  <link rel="stylesheet" href="../css/bootstrap.css">
  <style>
    * {
      padding: 0;
      margin: 0 auto;
    }

    body {
      /*background: url("../../img/dark-cloth-with-plate-waffles.jpg") top center / cover no-repeat;*/
      display: flex;
      align-items: center;
      /* justify-content: center; */
      height: 900px;
      font-size: 24px;
    }

    label {
      color: #564040;
      font-size: 20px;
      font-family: "Apple Color Emoji";
      font-weight: bolder;
      margin-top: 10px;
      margin-bottom: 10px;
    }

    input {
      border-radius: 5px;
      font-size: 20px !important;
      font-family: "Apple Color Emoji";
    }

    h1 {
      margin: 5px;
      margin-bottom: 10px;
      color: #a46f0f;
      text-align: center;
      font-size: 36px;
      text-transform: uppercase;
    }
  </style>
</head>
<body>
<div class="col-md-5 main">
  <h1>Update book</h1>
  <form action="" method="post">
    <input type="hidden" name="id" value="${books.id}">
    <label for="title">Input title: </label>
    <input type="text" class="form-control" name="title" id="title"  value="${books.title}">

    <label for="pageSize">Input page size: </label>
    <input type="text" class="form-control" name="pageSizeBooks" id="pageSize" value="${books.pageSize}">

    <label for="authorId" id="authorId">Input author: </label>
    <select class="form-select" name="authorId" >
      <c:forEach var="author" items="${authorList}">
        <c:if test="${author.authorID == books.author.authorID}">
          <option value="${author.authorID}" selected>${author.authorName}</option>
        </c:if>
        <c:if test="${author.authorID != books.author.authorID}">
          <option value="${author.authorID}">${author.authorName}</option>
        </c:if>
      </c:forEach>
    </select>

    <label for="categoryID" id="categoryID">Input category name: </label>
    <select class="form-select" name="categoryID" >
      <c:forEach var="category" items="${categoryList}">
        <c:if test="${category.idCategory == books.category.idCategory}">
          <option value="${category.idCategory}" selected>${category.nameCategory}</option>
        </c:if>
        <c:if test="${category.idCategory != books.category.idCategory}">
          <option value="${category.idCategory}">${category.nameCategory}</option>
        </c:if>
      </c:forEach>
    </select>

    <input type="submit" value="Update" class="btn btn-secondary mt-3" style="background: #3d3c3c; margin-top: 10px;margin-left: 200px">
  </form>
</div>
<script src="../js/bootstrap.bundle.js"></script>
</body>
</html>

