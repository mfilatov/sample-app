<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Add/Edit Order</title>
</head>
<body>
<form:form method="POST" action="./saveOrder" modelAttribute="order" var="order">
  <form:hidden path="id" />
  <table>
    <tr>
      <td>Order Number:</td>
      <td><form:input path="orderNumber" /></td>
    </tr>
    <tr>
      <td>Address:</td>
      <td><form:input path="address" /></td>
    </tr>
    <tr>
      <td>Order Status:</td>
      <td><form:input path="orderStatus" /></td>
    </tr>
    <tr>
      <td>Volume:</td>
      <td><form:input path="volume" /></td>
    </tr>
    <tr>
      <td>Quantity:</td>
      <td><form:input path="quantity" /></td>
    </tr>
    <tr>
      <td>Delivery Date:</td>
      <td><form:input path="deliveryDate" /></td>
    </tr>
    <tr>
      <td>Delivery Shift:</td>
      <td><form:input path="deliveryShift" /></td>
    </tr>
    <tr>
      <td>Purchase Number:</td>
      <td><form:input path="purchaseNumber" /></td>
    </tr>
    <tr>
      <td>Raw Data:</td>
      <td>${order.rawData}</td>
    </tr>
    <tr>
      <td colspan="2">
        <input type="submit" />
      </td>
    </tr>
  </table>
</form:form>
</body>
</html>
