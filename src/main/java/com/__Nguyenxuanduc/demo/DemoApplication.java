package com.__Nguyenxuanduc.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@RestController
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@GetMapping("/")
	public String getBeautifulProfile() {
		Profile p = new Profile(1L, "Nguyễn Xuân Đức", "2004", "HUTECH", "Backend Developer Intern", "Java, Spring Boot, MySQL, CI/CD");

		return "<!DOCTYPE html><html lang='vi'><head><meta charset='UTF-8'>" +
				"<title>Hồ Sơ Cá Nhân - Nguyễn Xuân Đức</title>" +
				"<style>" +
				"body{font-family:'Segoe UI',sans-serif; background:#f0f2f5; display:flex; justify-content:center; padding:40px;}" +
				".container{display:flex; gap:20px; max-width:800px; width:100%;}" +
				".card{background:white; padding:30px; border-radius:15px; box-shadow:0 10px 25px rgba(0,0,0,0.1); flex:1; border-top:8px solid #004085;}" +
				"h1, h2{color:#004085; margin-top:0;} .highlight{color:#28a745; font-weight:bold;}" +
				"</style></head><body>" +
				"<div class='container'>" +
				"    <div class='card'>" +
				"        <h1>Hồ Sơ Cá Nhân (No DB Version)</h1>" +
				"        <p><strong>Họ tên:</strong> " + p.getFullName() + " (" + p.getBirthYear() + ")</p>" +
				"        <p><strong>Trường:</strong> " + p.getSchool() + "</p>" +
				"        <p><strong>Vị trí:</strong> <span class='highlight'>" + p.getRole() + "</span></p>" +
				"        <p><strong>Kỹ năng:</strong> " + p.getSkills() + "</p>" +
				"        <hr><p style='color:red; font-size:14px; font-weight:bold;'>✅ Hệ thống CI/CD đã Deploy thành công! Đã ngắt kết nối Backend với MySQL.</p>" +
				"    </div>" +
				"</div></body></html>";
	}

	@GetMapping("/update")
	public String updateProfile() {
		return "Đã tắt DB nên tính năng cập nhật không khả dụng.";
	}

	@PostMapping("/save")
	public String saveProfile() {
		return "<html><body><script>alert('Đã vô hiệu hóa Database!'); window.location.href='/';</script></body></html>";
	}
}