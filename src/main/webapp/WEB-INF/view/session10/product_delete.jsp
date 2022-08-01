<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@2.1.0/build/pure-min.css">
		<meta charset="UTF-8">
		<title>Product 修改表單</title>
		<script type="text/javascript">
		 function checkDelete(){
			 var result = confirm('確認刪除?');
			 if(result == true){
				 alert('確認刪除');
				 return true;
			 }else{
				 alert('取消刪除');
				 return false;
			 }
		 }
		</script>
	</head>
	<body style="padding: 15px">
		<form class="pure-form" method="post" onsubmit="return checkDelete()" action="/spring.mvc/mvc/product/${ index }">
			<fieldset>
				<legend>Product 刪除表單</legend>
				<input type="hidden" name="_method" id="_method" value="DELETE">
				 商品名稱：${ product.productName }<p />
	             商品數量：${ product.quantity }<p />
	             商品價格：${ product.price }<p />
				<button type="submit" class="pure-button pure-button-primary">delete</button>
				<button type="button"
	        		onclick="window.location.href='/spring.mvc/mvc/product/';" 
	        		class="pure-button pure-button-primary">back</button>
			</fieldset>
		</form>
	</body>
</html>