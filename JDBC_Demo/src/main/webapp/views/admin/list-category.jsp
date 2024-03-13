<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Category List</title>
</head>
<body>
	<table id="example" class="table table-striped table-bordered"
		style="width: 100%">
		<thead>
			<tr>
				<th>STT</th>
				<th>Hình Ảnh</th>
				<th>Tên sản phẩm</th>
				<th>Tình trạng</th>		
				<th>Chức năng</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${cateList}" var="cate" varStatus="STT">
		<tr class="odd gradeX">
			<td>${STT.index+1}</td>
			<c:url value="/image?fname=${cate.image }" var="imgUrl"></c:url>
			<td><img height="150" width="200" src="${imgUrl}" /></td>
			<td>${cate.catename }</td>
			<td>${cate.status==1?"Còn hàng": "Hết hàng"}</td>
			<td><a
				href="<c:url value='/admin/category/edit?id=${cate.cateid}'/>"
				class="center">Sửa</a> | <a
				href="<c:url value='/admin/category/delete?id=${cate.cateid}'/>"
				class="center">Xóa</a></td>
		</tr>
	</c:forEach>
		
		</tbody>
	</table>
	
</body>
</html>