<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 03/02/2023
  Time: 11:12 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <title>Login</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        /*body {*/
        /*    background: url('../img/bg.jpg') top center / cover no-repeat;*/
        /*}*/

        form {
            margin-top: 24px;
        }

        .login {
            width: 500px;
            height: 400px;
            border: 1px solid gray;
            border-radius: 10px;
            text-align: center;
            margin: auto;
        }

        .login h2 {
            margin-top: 30px;
            color: #868787;
            font-size: 50px;
            font-weight: bolder;
            font-family: sans-serif;
        }

        .login h3 {
            margin-left: 10px;
            font-size: 15px;
            color: red;
            font-family: Arial;
        }

        .login .form-control {
            width: 100px;
            height: 40px;
            margin-bottom: 10px;
            border-radius: 5px;
            border: 1px solid gray;
            padding-left: 20px;
        }

        .login .btn btn-primary {
            display: inline-block;
            width: 400px;
            height: 40px;
            margin-bottom: 10px;
            border-radius: 5px;
            border: none;
            background-color: #1fc127;
            color: #fff;
            text-decoration: none;
            line-height: 40px;
            opacity: 0.8;
        }

        .login .btn btn-primary:hover,
        .login a:hover {
            opacity: 1;
        }
        #use-name{
            width: 30px;
            contain: inline-size;
        }
    </style>
</head>
<body>
<form action="/login" method="post">
    <div>
    <div class="login">
        <h2>Login</h2>
        <h3 style="margin-top: 20px">${messengerLogin}</h3>
        <div class="input-group" style="margin: 77px 0 33px;text-indent: 31px">
            <label for="use-name">Use name </label>
            <input type="text" style="margin: 0 45px 0 18px;border-radius: 3px;"
                   class="form-control" name="useName" id="use-name" placeholder="Input User name">
        </div>
        <div class="input-group" style="text-indent: 31px">
            <label for="password">Password</label>
            <input type="password" style="margin: 0 45px 0 21px; border-radius: 3px;"
                   class="form-control" name="password" id="password" placeholder="Input your password">
        </div>
        <button type="submit" class="btn btn-primary" style="margin: 26px">Submit</button>
    </div>
    </div>

</form>


<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
</body>
</html>
