## Getting Started Locally
Tiến hành clone backend về máy
Chạy build Maven bằng lệnh:
```bash
./mvnw clean package -DskipTests
```

Sau đó dùng lệnh này để chạy dự án java:
```bash
java -jar target/GamesShop-0.0.1-SNAPSHOT.jar  
```

Sau đó có thể truy cập vào [Games Shop Demo](https://game-shop-fe.vercel.app/). Tuy nhiên chỉ có thể truy cập và hoạt động bình thường trên local.

## Điểm mạnh của dự án:
- Database đã được (deploy) triển khai trên Railway, một dịch vụ cloud-hosted, do đó không cần thiết phải cài đặt MySQL cục bộ hoặc duy trì máy chủ vật lý riêng để duy trì kết nối và phục vụ truy vấn.

## Điểm hạn chế của dự án:
- Trước khi chạy các lệnh khởi động dự án cần phải cung cấp đúng các key trong .env để dự án được build thành công.
- Do database được deploy bằng railway đặt tại Singapore và frontend được deploy bằng vercel cũng đặt tại Singapore; nhưng backend đang chạy local do đó trong quá trình giao tiếp giữa frontend và backend, quá trình truy vấn giữa backend và database sẽ có độ trễ khá lớn.
- Database trên railway cũng hạn chế kết nối với các cổng public do đó đôi khi nếu chạy backend local và truy vấn quá nhiều thì có thể database sẽ từ chối kết nối.