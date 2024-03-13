<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
								<i class="fa fa-globe"></i>tìm kiếm video
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
														<label for="title">video name:</label> 
														<input type="text"
															name="title" value="${title}"
															id="title" class="form-control"  />
													</div>
												
													<br />
													<hr>
													<div class="form-group">
														<button class="btn green"
															formaction="<c:url value="/admin-video/search"/>">
															search <i class="fa fa-plus"></i>
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
														<th>Trạng thái</th>
														<th>Hành động</th>
														<th>Mã loại video</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="item" items="${listvideoname}">
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