<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>New Company</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #79b675;
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
    </style>
</head>
<body>
<div class="container">
    <h2>New Company</h2>
    <form:form modelAttribute="company" method="post">
        <label for="companyCode">Company Code</label>
        <form:input path="companyCode"/>
        <form:errors path="companyCode" cssStyle="color:red"/>

        <label for="companyName">Company Name</label>
        <form:input path="companyName"/>
        <form:errors path="companyName" cssStyle="color:red"/>

        <label for="address">Address</label>
        <form:input path="address"/>
        <form:errors path="address" cssStyle="color:red"/>

        <label for="eMail">E-Mail</label>
        <form:input path="eMail"/>
        <form:errors path="eMail" cssStyle="color:red"/>

        <button class="button" onclick="newCompany()">Create</button>
    </form:form>
</div>
<script>
    function newCompany() {
        window.location.href = "/companies";
    }

</script>
</body>
</html>
