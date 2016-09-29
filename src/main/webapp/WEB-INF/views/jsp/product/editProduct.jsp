<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>etnShop</title>

<spring:url value="/resources/core/css/hello.css" var="coreCss" />
<spring:url value="/resources/core/css/bootstrap.min.css"
	var="bootstrapCss" />
<link href="${bootstrapCss}" rel="stylesheet" />
<link href="${coreCss}" rel="stylesheet" />
</head>

<body>
<div class="container">
	<h2>Edit product</h2>

	<form action="/etnshop/product/saveProduct" method="post">
		<input type="hidden" name="id" value="${product.id}" />
		Serial number: <input type="text" id="serialNumber" name="serialNumber" value="${product.serialNumber}"/>
		Name: <input type="text" id="name" name="name" value="${product.name}"/>
		<input  class="btn btn-primary btn-lg" type="submit" value="Save"/>
	</form>
	<hr>
	<p>
		<a class="btn btn-primary btn-lg" href="/etnshop/product/list" role="button">Back to list</a> 
	</p>
</div>

</body>
</html>