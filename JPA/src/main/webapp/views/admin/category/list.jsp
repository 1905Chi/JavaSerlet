<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>

<div class="page-content-wrapper">
	<div class="page-content">
		<div class="row">
			<div class="col-md-12">
				<!-- BEGIN EXAMPLE TABLE PORTLET-->
				<div class="portlet box grey-cascade">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-globe"></i>Quản lý Category
						</div>
						<div class="tools">
							<a href="javascript:;" class="collapse"> </a> <a
								href="#portlet-config" data-toggle="modal" class="config"> </a>
							<a href="javascript:;" class="reload"> </a> <a
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
												<div class="form-group" hidden="hidden">
													<label for="UserID">Category ID:</label> <input type="text"
														name="categoryId" value="${category.categoryId}"
														id="categoryId" class="form-control" readonly />
												</div>
												<div class="form-group">
													<label for="categoryName">Category Name:</label> <input
														type="text" class="form-control" name="categoryName"
														id="categoryname" value="${category.categoryName}" />
												</div>
												<div class="form-group">
													<label for="categoryCode">Category code:</label> <input
														type="text" class="form-control" name="categoryCode"
														value="${category.categoryCode }" id="categoryCode" />
												</div>
												<div class="form-group">
													<label for="images">Images:</label> <input type="file"
														class="form-control" name="images" id="images"
														value="${category.images}" />
												</div>
												<div class="form-check form-check-inline">
													<label for="status">Status:</label> <input id="statuson"
														class="form-check-input" type="radio" name="status"
														${category.status?'checked':''} value="true"> <label
														for="statuson" class="form-check-label">Hoạt động</label>
													<input id="statusoff" class="form-check-input" type="radio"
														name="status" ${!category.status?'checked':''}
														value="false"> <label for="statusoff"
														class="form-check-label">Khóa</label>
												</div>
												<br />
												<hr>
												<div class="form-group">
													<button class="btn green"
														formaction="<c:url value="/admin-category/create"/>">
														Create <i class="fa fa-plus"></i>
													</button>
													<button class="btn btn-warning"
														formaction="<c:url value="/admin-category/update"/>">
														Update <i class="fa fa-edit"></i>
													</button>
													<br /> <br />
													<button class="btn btn-danger"
														formaction="<c:url value="/admin-category/delete"/>">
														Delete <i class="fa fa-trash"></i>
													</button>
													<button class="btn btn-success"
														formaction="${pageContext.request.contextPath}/admin-category/reset">
														Reset <i class="fa fa-undo"></i>
													</button>
												</div>
											</form>
										</div>
									</div>
								</div>
								
								<div class="col-md-9" style="padding-right: 25px">
									<div class="row">
										
										
										
										
										
										<table class="table table-striped table-bordered table-hover" id="sample_2">
											<thead>
												<tr>
													<th>Ảnh đại diện</th>
													<th>Tên danh mục</th>
													<th>Code</th>
													<th>Trạng thái</th>
													<th>Hành động</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="item" items="${categorys1}">
													<tr class="odd gradeX">
														<td><c:url
																value="/image?fname=category/${item.images!=null?item.images:'uploads/abc.jpg'}"
																var="imgUrl"></c:url> <img width="50px" height="50px"
															src="${imgUrl}"></td>
														<td>${item.categoryName }</td>
														<td>${item.categoryCode }</td>
														<td><c:if test="${item.status == true}">
																<span class="label label-sm label-success"> Hoạt
																	động </span>
															</c:if> <c:if test="${item.status ==false}">
																<span class="label label-sm label-warning"> Khóa</span>
															</c:if></td>
														<td><a
															href="<c:url value='/admin-category/edit?categoryId=${item.categoryId }'/>"
															class="center">Edit</a> | <a
															href="<c:url value='/admin-category/delete?categoryId=${item.categoryId }'/>"
															class="center">Delete</a></td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									
											<c:forEach begin ="1" end= "${enpage}" var="i">
											<a href="/JPA/admin-category?index=${i}">${i}</a>
										</c:forEach>
										
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