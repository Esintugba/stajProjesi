<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Delete Customer</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #bb8eb0;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .container {
            text-align: center;
            background-color: #ffffff;
            border-radius: 50px;
            padding: 50px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            max-width: 600px;
            width: 100%;
        }

        form {
            margin-top: 10px;
        }

        label {
            display: block;
            margin-bottom: 5px;
        }

        input[type="text"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 3px;
        }

        .button {
            display: inline-block;
            background-color: #93425b;
            color: #fff;
            border: none;
            padding: 10px 20px;
            border-radius: 15px;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s ease-in-out;
        }

        .button:hover {
            background-color: #ff0051;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Delete Customer</h2>
    <form:form modelAttribute="customer" method="post">

        <label for="tc">Tc</label>
        <form:input path="tc"/>

        <label for="customerNumber">Customer Number</label>
        <form:input path="customerNumber"/>

        <label for="firstName">First Name</label>
        <form:input path="firstName"/>

        <label for="lastName">Last Name</label>
        <form:input path="lastName"/>

        <label for="gender">Gender</label>
        <form:input path="gender"/>

        <label for="eMail">E-Mail</label>
        <form:input path="eMail"/>

        <button class="button" onclick="deleteCustomer()">Delete</button>
    </form:form>
</div>
<script>
    function deleteCustomer() {
        window.location.href = "/customers";
    }

</script>
</body>
</html>