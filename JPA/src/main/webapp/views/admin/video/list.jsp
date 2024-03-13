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

	<div class="page-content-wrapper">
		<div class="page-content">
			<div class="row flex-column">
				<div class="col-md-12">
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-globe"></i>Quản lý Video
							</div>
							<div class="tools">
								<a href="javascript:;" class="collapse"> </a> <a
									href="#portlet-config" data-toggle="modal" class="config">
								</a> <a href="javascript:;" class="reload"> </a> <a
									href="javascript:;" class="remove"> </a>
							</div>
						</div>
						<div class="portlet-body">
							<div class="table-toolbar">
								<!-- Hiển thị thông báo -->
								<%@include file="/common/info.jsp"%>
								<!-- Kết thúc hiển thị thông báo -->
								<div class="row">
									<div class="col-md-3">
										<div class="row">
											<div class="col-md-9">
												<form action="#" method="post" enctype="multipart/form-data">
													<br />
													<div class="form-group" >
														<label for="videoId">Video ID:</label> <input type="text"
															name="videoId" value="${video.videoId}"
															id="videoId" class="form-control" />
													</div>
													<div class="form-group">
														<label for="title">video Name:</label> <input
															type="text" class="form-control" name="title"
															id="title" value="${video.title}" />
													</div>
													<div class="form-group">
														<label for="poster">Images:</label> <input type="file"
															class="form-control" name="poster" id="poster"
															value="${video.poster}" />
													</div>
													<div class="form-group">
														<label for="views">Views:</label> <input type="text"
															class="form-control" name="views" id="views"
															value="${video.views}" />
													</div>
													<div class="form-group">
														<label for="description">Description:</label> <input
															type="text" class="form-control" name="description"
															id="description" value="${video.description}" />
													</div>
													<div class="form-group">
														<label for="categoryId">CategoryId:</label> <input type="text"
															class="form-control" name="categoryId" id="categoryId"
															value="${video.category.categoryId}" />
													</div>
													<div class="form-check form-check-inline">
														<label for="active">Status:</label> <input id="statuson"
															class="form-check-input" type="radio" name="active"
															${video.active?'checked':''} value="true"> <label
															for="activeon" class="form-check-label">Hoạt động</label>
														<input id="activeoff" class="form-check-input"
															type="radio" name="active" ${!video.active?'checked':''}
															value="false"> <label for="activeoff"
															class="form-check-label">Khóa</label>
													</div>
													<br />
													<hr>
													<div class="form-group">
														<button class="btn green"
															formaction="<c:url value="/admin-video/create"/>">
															Create <i class="fa fa-plus"></i>
														</button>
														<button class="btn btn-warning"
															formaction="<c:url value="/admin-video/update"/>">
															Update <i class="fa fa-edit"></i>
														</button>
														<br /> <br />
														<button class="btn btn-danger"
															formaction="<c:url value="/admin-video/delete"/>">
															Delete <i class="fa fa-trash"></i>
														</button>
														<button class="btn btn-success"
															formaction="${pageContext.request.contextPath}/admin-video/reset">
															Reset <i class="fa fa-undo"></i>
														</button>
													</div>
												</form>
											</div>
										</div>
									</div>

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
														<th>Chức năng</th>
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
															<td>${item.category.categoryId}</td>
															<td><c:if test="${item.active == true}">
																	<span class="label label-sm label-success"> Hoạt
																		động </span>
																</c:if> <c:if test="${item.active ==false}">
																	<span class="label label-sm label-warning"> Khóa</span>
																</c:if></td>
															<td><a
																href="<c:url value='/admin-video/edit?videoId=${item.videoId }'/>"
																class="center">Edit</a> | <a
																href="<c:url value='/admin-video/delete?videoId=${item.videoId }'/>"
																class="center">Delete</a></td>
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
															href="${pageContext.request.contextPath}/admin-video?index=${tag-1}">&laquo;</a></li>
													</c:if>
													<c:forEach begin="1" end="${endP}" var="i">
														<li class='${tag==i?"active":"" }'><a
															href="${pageContext.request.contextPath}/admin-video?index=${i}">${i}</a></li>
													</c:forEach>
													<c:if test="${tag<endP}">
														<li><a
															href="${pageContext.request.contextPath}/admin-video?index=${tag+1}">&raquo;</a></li>
													</c:if>
												</ul>
											</div>
										</div>

									</div>
								</div>
							</div>
						</div>
					</div>
					<!-- END EXAMPLE TABLE PORTLET-->
				</div>
			</div>
		</div>
	</div>
</body>
</html>