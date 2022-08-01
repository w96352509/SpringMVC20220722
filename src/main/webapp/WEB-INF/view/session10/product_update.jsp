<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@2.1.0/build/pure-min.css">
		<meta charset="UTF-8">
		<title>Product 修改表單</title>
	</head>
	<body style="padding: 15px">
		<form class="pure-form" method="post" action="/spring.mvc/mvc/product/${ index }">
			<fieldset>
				<legend>Product 修改表單</legend>
				<input type="hidden" name="_method" id="_method" value="PUT">
				<input type="text" placeholder="請輸入商品名稱" value="${ product.productName }" id="productName" name="productName"><p />
				<input type="number" placeholder="請輸入商品數量" value="${ product.quantity }" id="quantity" name="quantity"><p />
				<input type="number" placeholder="請輸入商品價格" value="${ product.price }" id="price" name="price"><p />
				<button type="submit" class="pure-button pure-button-primary">Update</button>
			</fieldset>
		</form>
	</body>
</html>