
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Company Information</title>


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
            background-color: #007bff;
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
            padding: 10px 30px;
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
        <td> <button class="buttonCreate" onclick="newCompany()">Create</button></td>
        <td>ID</td>
        <td>Company Code</td>
        <td>Company Name</td>
        <td>Address</td>
        <td>E-Mail</td>
        <td>Action</td>
    </tr>

    </thead>
    <c:forEach items="${companies}" var="company" varStatus="status">
        <tr bgcolor=${status.index % 2 == 0?'white':'lightgray'}>
            <td></td>
            <td>${company.id}</td>
            <td>${company.companyCode}</td>
            <td>${company.companyName}</td>
            <td>${company.address}</td>
            <td>${company.eMail}</td>
            <td>
                <button class="button" onclick="editCompany(${company.id})">Edit</button>
                <button class="button" onclick="deleteCompany(${company.id})">Delete</button>
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
    function editCompany(id) {
        var targetUrl = "/companies/update/" + id;
        window.location.href = targetUrl;
    }

    function deleteCompany(id) {
        var targetUrl = "/companies/delete/" + id;
        window.location.href = targetUrl;
    }
    function newCompany() {
        var targetUrl = "/companies/new";
        window.location.href = targetUrl;
    }

</script>
</body>
</html>
