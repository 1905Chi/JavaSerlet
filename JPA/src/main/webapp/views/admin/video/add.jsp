<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url value="/templates/" var="URL"></c:url>
<!DOCTYPE html>
<html lang="fr">
<head>
<!-- Site meta -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Free Bootstrap 4 Ecommerce Template</title>

<!-- CSS -->
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	rel="stylesheet" type="text/css">
<link
	href="//maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link href="//fonts.googleapis.com/css?family=Open+Sans:400,300,600"
	rel="stylesheet" type="text/css">

<link href="${URL}css/styles.css" rel="stylesheet" type="text/css">

</head>
<body>

	<div class="col-md-9" style="padding-right: 25px">
		<div class="row">
			<table class="table table-striped table-bordered table-hover"
				id="sample_2">
				<thead>
					<tr>
						<th>Poster</th>
						<th>Tên danh mục</th>
						<th>Mô tả</th>
						<th>Số lượt xem</th>
						<th>Mã loại video</th>
						<th>Trạng thái</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${videos}">
						<tr class="odd gradeX">
							<td><c:url
									value="/image?fname=video/${item.poster!=null?item.poster:'uploads/abc.jpg'}"
									var="imgUrl"></c:url> <img width="50px" height="50px"
								src="${imgUrl}"></td>
							<td>${item.title }</td>
							<td>${item.description }</td>
							<td>${item.views}</td>
							<td>${item.categoryId}</td>
							<td><c:if test="${item.active == true}">
									<span class="label label-sm label-success"> Hoạt động </span>
								</c:if> <c:if test="${item.active ==false}">
									<span class="label label-sm label-warning"> Khóa</span>
								</c:if></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div>
			<div class="col-md-8 col-sm-8">
				<ul class="pagination pull-right">
					<c:if test="${tag>1}">
						<li><a
							href="${pageContext.request.contextPath}/admin-video/pagesize?index=${tag-1}">&laquo;</a></li>
					</c:if>
					<c:forEach begin="1" end="${endP}" var="i">
						<li class='${tag==i?"active":"" }'><a
							href="${pageContext.request.contextPath}/admin-video/pagesize?index=${i}">${i}</a></li>
					</c:forEach>
					<c:if test="${tag<endP}">
						<li><a
							href="${pageContext.request.contextPath}/admin-video/pagesize?index=${tag+1}">&raquo;</a></li>
					</c:if>
				</ul>
			</div>
		</div>

	</div>
</body>
</html>
