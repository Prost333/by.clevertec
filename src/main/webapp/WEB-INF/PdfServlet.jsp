<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product Page</title>
</head>
<body>
 <h1>Product List</h1>
 <form action="/pdf" method="post">
    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Price</th>
        </tr>
        <%
            List<ProductDto> products = (List<ProductDto>) request.getAttribute("product");
            for (ProductDto product : products) {
        %>
        <tr>
            <td><%= product.getId() %></td>
            <td><%= product.getName() %></td>
            <td><%= product.getPrice() %></td>
        </tr>
        <% } %>
    </table>
        </form>


    <h1>Welcome to the Product Page</h1>
    <form action="/pdf" method="get">
        <label for="productId">Product ID:</label><br>
        <input type="text" id="productId" name="productId"><br>
        <input type="submit" value="Get Product PDF">
    </form>


</body>
</html>


