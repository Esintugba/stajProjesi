<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Product Information</title>
</head>
<body>


<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f2f2f2;
        margin: 0;
        padding: 0;
    }

    table {
        width: 100%;
        border-collapse: collapse;
    }

    th, td {
        padding: 12px;
        text-align: center;
        border-bottom: 1px solid #ddd;
    }

    thead tr {
        font-weight: bold;
        background-color: #1aff00;
        color: #fff;
    }

    tbody tr:nth-child(even) {
        background-color: #f2f2f2;
    }
    .button {
        display: inline-block;
        background-color: #92bae8;
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
    .buttonCreate{
        display: inline-block;
        background-color: #92bae8;
        color: #fff;
        border: none;
        padding: 10px 10px;
        border-radius: 15px;
        cursor: pointer;
        font-size: 16px;
        transition: background-color 0.3s ease-in-out;
    }
    .buttonCreate:hover{
        background-color: #0056b3;
    }

</style>
</head>
</body>

<table>
    <thead>
    <tr style="font-weight: bold;" bgcolor="lightblue">
        <td> <button class="buttonCreate" onclick="newProduct()">Create</button></td>
        <td>ID</td>
        <td>Product Code</td>
        <td>Name</td>
        <td>Categories</td>
        <td>Explanation</td>
        <td>Price</td>
        <td>Company</td>
        <td>Actions</td>
    </tr>
    </thead>
    <c:forEach items="${products}" var="product" varStatus="status">
        <tr bgcolor=${status.index % 2 == 0?'white':'lightgray'}>
            <td></td>
            <td>${product.id}</td>
            <td>${product.productCode}</td>
            <td>${product.name}</td>
            <td>${product.categories}</td>
            <td>${product.explanation}</td>
            <td>${product.price}</td>
            <td>${product.company.id}</td>
            <td>
                <button class="button" onclick="editProduct(${product.id})">Edit</button>
                <button class="button" onclick="deleteProduct(${product.id})">Delete</button>
            </td>
        </tr>
    </c:forEach>
</table>
<c:if test="${not empty message}">
    <div style="color: blue;">
            ${message}
    </div>
</c:if>
</head>
<body>

<script>
    function editProduct(id) {
        var targetUrl = "/products/update/" + id;
        window.location.href = targetUrl;
    }
    function deleteProduct(id) {
        var targetUrl = "/products/delete/" + id;
        window.location.href = targetUrl;
    }
    function newProduct() {
        var targetUrl = "/products/new";
        window.location.href = targetUrl;
    }


</script>
</body>
</html>
