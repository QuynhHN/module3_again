<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 03/10/2023
  Time: 2:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student</title>
    <link rel="stylesheet" href="css/bootstrap.css">
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        .table {
            text-align: center;
            margin-left: auto;
            margin-right: auto;
            border-collapse: collapse;
            border: 1px solid black;
            width: 100%;
        }

        .table, th {
            border: 1px solid #868585;
        }

        /*table{*/
        /*    border-collapse:collapse;*/
        /*    width:100%;*/
        /*}*/
        th {
            text-align: center;
            padding: 10px;
        }

        .table tr:nth-child(odd) {
            background-color: #eee;
        }

        .table tr:nth-child(even) {
            background-color: white;
        }

        /*.table tr:nth-child(1){*/
        /*    background-color:#b6effb;*/
        /*}*/
        h1 {
            margin-top: 100px;
            margin-bottom: 50px;
            text-align: center;
            color: #2c2972;
            font-weight: bolder;
            font-family: Bahnschrift;
            font-size: 30px;
        }

        td {
            border: 1px solid;
        }
    </style>
</head>
<body style="background: url('https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQSurL56wFqEFVIr6PG36pABCOkOcoggv2SKw&usqp=CAU');background-size: cover">
<h1>Danh sách học viên CodeGym</h1>
<p class="text-center text-danger">${msg}</p>
<table class="table" style="width: 600px">
    <thead>
    <tr style="background: #c49ca6">
        <th>No</th>
        <th>ID</th>
        <th>Name</th>
        <th>Gender</th>
        <th>Score</th>
        <th>Level</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="student" items="${studentList}" varStatus="loop">
        <tr>
            <td>${loop.count}</td>
            <td>${student.id}</td>
            <td>${student.name}</td>
            <td>
                <c:if test="${student.gender ==0}">Female</c:if>
                <c:if test="${student.gender ==1}">Male</c:if>


            </td>
            <td>${student.score}</td>
            <td>
                <c:choose>
                    <c:when test="${student.score >=90}">Giỏi</c:when>
                    <c:when test="${student.score >=80}">Khá</c:when>
                    <c:when test="${student.score >=60}">Trung bình</c:when>
                    <c:when test="${student.score <60}">Yếu</c:when>
                    <c:otherwise>Error</c:otherwise>
                </c:choose>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
<script src="js/bootstrap.bundle.js"></script>
</html>
