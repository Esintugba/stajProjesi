<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Delete Product</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #356dcc;
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
            background-color: #1aff00;
            color: #fff;
            border: none;
            padding: 10px 20px;
            border-radius: 15px;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s ease-in-out;
        }

        .button:hover {
            background-color: #166b09;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Delete Product</h2>
    <form:form modelAttribute="product" method="post">
        <label for="productCode">Product Code</label>
        <form:input path="productCode"/>

        <label for="name">Product Name</label>
        <form:input path="name"/>

        <label for="categories">Categories</label>
        <form:input path="categories"/>

        <label for="explanation">Explanation</label>
        <form:input path="explanation"/>

        <label for="price">Price</label>
        <form:input path="price"/>

        <label for="company.id">Company Id</label>
        <form:input path="company.id"/>

        <button class="button" onclick="deleteProduct()">Delete</button>
    </form:form>
</div>
<script>
    function deleteProduct() {
        window.location.href = "/products";
    }

</script>
</body>
</html>
