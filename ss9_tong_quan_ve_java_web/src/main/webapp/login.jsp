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
    <div style="background-image: url("data:image/jpeg;base64,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")">
    <div class="login">
        <h2>Login</h2>
        <h3>${messengerLogin}</h3>
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
