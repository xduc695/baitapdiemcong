# B1: Dùng JRE 21 trên nền Alpine cho nhẹ (chỉ có môi trường chạy, không có công cụ build)
FROM eclipse-temurin:21-jre-alpine

# B2: Tạo thư mục app
WORKDIR /app

# B3: Copy file jar đã build từ máy thật vào container
# Lưu ý: Tên file phải khớp với file trong thư mục target của bạn
COPY target/*.jar app.jar

# B4: Mở cổng 8080
EXPOSE 8080

# B5: Chạy ứng dụng
ENTRYPOINT ["java", "-jar", "app.jar"]