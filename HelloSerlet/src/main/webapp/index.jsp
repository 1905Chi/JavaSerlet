<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">

<title>đăng ký tài khoản</title>
</head>
<body>
	<main class="container">
		<header class="row">
			<div class="col">
				<div class="alert alert-success col">
					<h1>Đăng ký tài khoản</h1>
				</div>
			</div>
		</header>
		<section class="row">
			<div class="col-7">
				<div class="row">
					<div class="col">
						<form action="register" method="post">
							<div class="form-group">
								<label for="username">Tên đăng nhập:</label> <input
									type="text" id= "username"
									name="username" class="form-control" />
							</div>
							<div class="form-group">
								<label for="password">Mật khẩu: </label>
								 <input	type="password" id="password" name="password" class="from-group" />
							</div>
							<div class="form-group">
								<label for="gender">Giới tính:</label>
								<div class="form-check form-check-inline">
									<input type="radio" class="form-check-input" id="genderM"
										name="gender" value="Nam" /> <label for="gender">Nam</label>
								</div>
								<div class="form-check form-check-inline">
									<input type="radio" class="form-check-input"
										id="genderN" name="gender" value="Nữ"/> <label for="gender">Nữ</label>
								</div>
								<div class="form-check form-check-inline">
									<input type="checkbox" class="form-check-input"
										id="married" name="married" />
										 <label for="married" value="Đã có gia đình">Đã có gia đình</label>
								</div>
								<div class="form-check form-check-inline">
									<label for="nation">Quốc tịch</label> <select
										name="nation" id=" nation" class="form-control">
										<option value="Việt Nam">Việt Nam</option>
										<option value="Mỹ">Mỹ</option>
										<option value="HÀn Quốc">Hàn Quốc</option>
										<option value="Nhật Bản">Nhật Bản</option>
										<option value="Trung Quốc">Trung Quốc</option>
										<option value="Anh">Anh</option>
									</select>
								</div>
								<div class="form-group">
									<label for="favorite">Sở thích: </label>
									<div >
										 <input type="checkbox" class="form-check-input" id="favorite"
											name="favorite" value="sport" /> <label for="favorite">Thể thao</label>
											
										 <input type="checkbox" class="form-check-input" id="favirite"
											name="favorite" value="music" /> <label for="favorite">Âm nhạc</label>
											
							 			 <input type="checkbox" class="form-check-input" id="favorite"
											name="favorite" value="tech" /> <label for="favorite">Công nghệ</label>
											
											
									</div>
									<div class="form-group">
									<label for="note">Ghi chú</label>
									<textarea name="note" id="note" cols="30" rows="10"
										class="form-control"></textarea>
								</div>
								</div>
								<div class="form-group">
									<button type="submit" class="btn btn-primary">Đăng ký</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
			<div class="col-5">
				<h1>Thông tin đã được đăng ký</h1>
				<ul>
					<li>Tên đăng nhập: ${username}</li>
					<li>Mật khẩu: ${password }</li>
					<li>Giới tính: ${gender }</li>
					<li>Lập gia đình: ${married }</li>
					<li>Quốc tịch: ${nation }</li>
					<li>Sở thích: ${favorite }</li>
					<li>Ghi chú: ${note }</li>
				</ul>
			</div>
		</section>
	</main>
</body>

</html>