<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }

        .container {
            text-align: center;
            background-color: #ffffff;
            border-radius: 50px;
            padding: 100px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            max-width: 800px;
            width: 100%;
        }

        h1 {
            color: #333;
            margin-bottom: 20px;
        }

        p {
            color: #777;
            margin-bottom: 30px;
        }

        ul {
            list-style: none;
            padding: 0;
            margin: 0;
        }

        li {
            margin-bottom: 10px;
        }

        a {
            text-decoration: none;
            color: #007bff;
        }

        input[type="submit"] {
            background-color: #fff200;
            color: #171616;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
            margin-top: 20px;
        }

        input[type="submit"]:hover {
            background-color: #fdd700;
        }

        .logout-btn {
            position: absolute;
            top: 80px;
            right: 80px;
        }
        .button {
            display: inline-block;
            background-color: #007bff;
            color: #fff;
            border: none;
            padding: 10px 20px;
            border-radius: 15px;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s ease-in-out;
        }

        .button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<div class="logout-btn">
    <form action="logout" method="post">
        <input type="submit" value="Logout">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    </form>
</div>
<div class="container">
    <h1>Welcome to the Application</h1>
    <p>Click on the links below to navigate</p>
    <td>
        <button class="button" onclick="Customer()">Customer</button>
        <button class="button" onclick="Product()">Product</button>
        <button class="button" onclick="Company()">Company</button>
    </td>

</div>
<script>
    function Customer() {
        window.location.href = "/customers";

    }

    function Product() {
        window.location.href = "/products";
    }

    function Company() {
        window.location.href = "/companies";
    }
</script>
</body>
</html>
