<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>

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
	<c:url value="/product/detail" var="Pdetail" />
	<c:url value="/image?fname=" var="URL_slides"></c:url>

	<div class="container">
		<div class="row">
			<div class="col">
				<nav aria-label="breadcrumb">
					
				</nav>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-12 col-sm-3">
				<div class="card bg-light mb-3">
					<div class="card-header bg-primary text-white text-uppercase">
						<i class="fa fa-list"></i> Categories
					</div>
					<ul class="list-group category_block">
						<c:forEach items="${cateList}" var="list">
							<li class="list-group-item"><a
								href="detail?cid=${list.categoryId}">${list.categoryName}</a></li>
						</c:forEach>
					</ul>
				</div>
			</div>
			<div class="col">
				<div class="row">
					<c:forEach items="${videoAll}" var="video">
						<div class="col-12 col-md-6 col-lg-4">
							<div class="card">
								<img class="card-img-top" src="${URL_slides}${video.poster}"
									alt="Card image cap">
								<div class="card-body">
									<h4 class="card-title">${video.title}</h4>
									<p class="card-text">${video.description}</p>
									<div class="row">
										<div class="col">
											<p class="btn btn-danger btn-block">${product.views}</p>
										</div>
									</div>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>

	</div>

</body>
</html>

