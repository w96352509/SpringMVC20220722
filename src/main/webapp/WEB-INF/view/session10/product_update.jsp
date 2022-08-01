<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://unpkg.com/purecss@2.0.6/build/pure-min.css">
<meta charset="UTF-8">
<title>Product</title>
</head>
<body style="padding: 15px;">
	<form class="pure-form" method="put" action="/spring.mvc/mvc/product/update/">
		<fieldset>
			<legend>Product 表單</legend>
			<input type="text" placeholder="商品名稱" id="productName" name="productName" 
			       value="${product.productName}"
			/>
			<p />
			
			<input type="number" placeholder="商品數量" id="quantity" name="quantity" 
			       value="${product.quantity}"
			/>
			<p />
			
			<input type="number" placeholder="商品價格" id="price" name="price" 
			       value="${product.price}"
			/>
			<p />
			
			<button type="submit" class="pure-button pure-button-primary">更新</button>
		
		</fieldset>
	</form>
    <p />
</body>
</html>