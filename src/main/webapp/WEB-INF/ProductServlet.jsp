<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="java.util.List" %>
<%@ page import ="by.clevertec.Dto.ProductDto"%>
<html>
<head>
    <title>Product Page</title>
</head>
<body>
 <h1>Product List</h1>
 <form action="/product" method="get">
 <table>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Price</th>
        <th>Buy</th>
    </tr>
    <%
        List<ProductDto> products = (List<ProductDto>) request.getAttribute("products");
        for (ProductDto product : products) {
    %>
    <tr>
        <td><%= product.getId() %></td>
        <td><%= product.getName() %></td>
        <td><%= product.getPrice() %></td>
        <td><input type="button" value="Buy" onclick="window.location.href = 'pdf?productId=<%= product.getId() %>';" /></td>
    </tr>
    <% } %>
 </table>
 </form>
</body>
</html>