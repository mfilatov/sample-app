<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>All Orders</title>
</head>
<body>
<table width="600px">
    <tr>
      <td><b>Order Number</b></td>
      <td><b>Address</b></td>
      <td><b>Order Status</b></td>
      <td><b>Volume</b></td>
      <td><b>Quantity</b></td>
      <td><b>Delivery Date</b></td>
      <td><b>Delivery Shift</b></td>
    </tr>
    <c:forEach var="order" items="${orders}">
        <tr>
            <td>${order.orderNumber}</td>
            <td>${order.address}</td>
            <td>${order.orderStatus}</td>
            <td>${order.volume}</td>
            <td>${order.quantity}</td>
            <td>${order.deliveryDate}</td>
            <td>${order.deliveryShift}</td>
            <td><a href="/editOrder?id=${order.id}">Edit</a> | <a href="/deleteOrder?id=${order.id}">Delete</a></td>
        </tr>
    </c:forEach>
    <tr>
        <td colspan="5">
            <a href="/addOrder">Add Order</a>
        </td>
    </tr>
</table>
</body>
</html>
