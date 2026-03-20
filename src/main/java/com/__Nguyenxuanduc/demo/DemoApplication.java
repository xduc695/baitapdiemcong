package com.__Nguyenxuanduc.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
@SpringBootApplication
@RestController
public class DemoApplication {

	@Autowired
	private ProfileRepository profileRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	// API Lấy thông tin từ DB và hiển thị giao diện
	@GetMapping("/")
	public String getBeautifulProfile() {
		List<Profile> profiles = profileRepository.findAll();
		// Nếu DB trống, lấy dữ liệu mặc định của Đức
		Profile p = profiles.isEmpty() ?
				new Profile(1L, "Nguyễn Xuân Đức", "2004", "HUTECH", "Backend Developer Intern", "Java, Spring Boot, MySQL") :
				profiles.get(0);

		return "<!DOCTYPE html><html lang='vi'><head><meta charset='UTF-8'>" +
				"<title>Hồ Sơ Cá Nhân - Nguyễn Xuân Đức</title>" +
				"<style>" +
				"body{font-family:'Segoe UI',sans-serif; background:#f0f2f5; display:flex; justify-content:center; padding:40px;}" +
				".container{display:flex; gap:20px; max-width:1000px; width:100%;}" +
				".card{background:white; padding:30px; border-radius:15px; box-shadow:0 10px 25px rgba(0,0,0,0.1); flex:1; border-top:8px solid #004085;}" +
				".form-card{background:#fff; padding:30px; border-radius:15px; width:350px; box-shadow:0 10px 25px rgba(0,0,0,0.05);}" +
				"h1, h2{color:#004085; margin-top:0;} .highlight{color:#28a745; font-weight:bold;}" +
				"input, textarea{width:100%; padding:10px; margin:10px 0; border:1px solid #ddd; border-radius:5px; box-sizing:border-box;}" +
				"button{width:100%; padding:12px; background:#004085; color:white; border:none; border-radius:5px; cursor:pointer; font-weight:bold; transition:0.3s;}" +
				"button:hover{background:#002a5a;}" +
				"</style></head><body>" +
				"<div class='container'>" +
				"    " +
				"    <div class='card'>" +
				"        <h1>Hồ Sơ Cá Nhân</h1>" +
				"        <p><strong>Họ tên:</strong> " + p.getFullName() + " (" + p.getBirthYear() + ")</p>" +
				"        <p><strong>Trường:</strong> " + p.getSchool() + "</p>" +
				"        <p><strong>Vị trí:</strong> <span class='highlight'>" + p.getRole() + "</span></p>" +
				"        <p><strong>Kỹ năng:</strong> " + p.getSkills() + "</p>" +
				"        <hr><p style='color:gray; font-size:12px;'>Dữ liệu thực tế từ MySQL Laragon</p>" +
				"    </div>" +
				"    " +
				"    " +
				"    <div class='form-card'>" +
				"        <h2>Cập nhật Profile</h2>" +
				"        <form action='/save' method='POST'>" +
				"            <input type='text' name='fullName' placeholder='Họ và tên' value='" + p.getFullName() + "' required>" +
				"            <input type='text' name='birthYear' placeholder='Năm sinh' value='" + p.getBirthYear() + "'>" +
				"            <input type='text' name='school' placeholder='Trường đại học' value='" + p.getSchool() + "'>" +
				"            <input type='text' name='role' placeholder='Vị trí ứng tuyển' value='" + p.getRole() + "'>" +
				"            <textarea name='skills' rows='3' placeholder='Kỹ năng (cách nhau bởi dấu phẩy)'>" + p.getSkills() + "</textarea>" +
				"            <button type='submit'>LƯU THÔNG TIN</button>" +
				"        </form>" +
				"    </div>" +
				"</div></body></html>";
	}

	// API Cập nhật thông tin nhanh qua URL (Ví dụ: /update?name=Duc)
	@GetMapping("/update")
	public String updateProfile(@RequestParam("name") String name) {
		List<Profile> profiles = profileRepository.findAll();
		Profile p = profiles.isEmpty() ? new Profile() : profiles.get(0);
		p.setFullName(name);
		p.setBirthYear("2004");
		p.setSchool("HUTECH");
		p.setRole("Backend Developer Intern");
		p.setSkills("Java, Spring Boot, MySQL, Docker");

		profileRepository.save(p);
		return "Đã cập nhật tên thành: " + name + ". Quay lại trang chủ để xem!";
	}
	@PostMapping("/save")
	public String saveProfile(@RequestParam("fullName") String fullName, // Khớp với name='fullName'
							  @RequestParam("birthYear") String birthYear, // Khớp với name='birthYear'
							  @RequestParam("school") String school,   // Khớp với name='school'
							  @RequestParam("role") String role,     // Khớp với name='role'
							  @RequestParam("skills") String skills) { // Khớp với name='skills'
		List<Profile> profiles = profileRepository.findAll();
		Profile p = profiles.isEmpty() ? new Profile() : profiles.get(0);
		p.setFullName(fullName);
		p.setBirthYear(birthYear);
		p.setSchool(school);
		p.setRole(role);
		p.setSkills(skills);

		profileRepository.save(p);
		return "<html><body><script>alert('Lưu MySQL thành công!'); window.location.href='/';</script></body></html>";
	}
}