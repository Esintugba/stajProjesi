<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Customer Information</title>
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
        background-color: #ff0051;
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
        <td> <button class="buttonCreate" onclick="newCustomer()">Create</button></td>
        <td>ID</td>
        <td>TC</td>
        <td>Customer Number</td>
        <td>First Name</td>
        <td>Last Name</td>
        <td>Gender</td>
        <td>E-Mail</td>
        <td>Actions</td>
    </tr>
    </thead>
    <c:forEach items="${customers}" var="customer" varStatus="status">
        <tr bgcolor=${status.index % 2 == 0?'white':'lightgray'}>
            <td></td>
            <td>${customer.id}</td>
            <td>${customer.tc}</td>
            <td>${customer.customerNumber}</td>
            <td>${customer.firstName}</td>
            <td>${customer.lastName}</td>
            <td>${customer.gender}</td>
            <td>${customer.eMail}</td>
            <td>
                <button class="button" onclick="editCustomer(${customer.id})">Edit</button>
                <button class="button" onclick="deleteCustomer(${customer.id})">Delete</button>
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
    function editCustomer(id) {
        var targetUrl = "/customers/update/" + id;
        window.location.href = targetUrl;
    }

    function deleteCustomer(id) {
        var targetUrl = "/customers/delete/" + id;
        window.location.href = targetUrl;
    }
    function newCustomer() {
        var targetUrl = "/customers/new";
        window.location.href = targetUrl;
    }

</script>
</body>
</html>
