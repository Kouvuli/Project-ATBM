
  - Trong folder source-code:
   (+) Oracle-Hibernate-App: App phân hệ 1
   (+) OracleQLYT: App phân hệ 2

  - Vì nhóm em xài 2 CSDL nên trong thư mục sql-scripts sẽ có 2 file .sql:
   (+) ATBMCQ-13_19120617_19120622_19120641_19120644.sql: về phân hệ 2 đã hoàn thành các chỉ tiêu ở mức độ CSDL, có cài đặt mã hóa với OLS dẫn đến thay đổi cấu trúc các quan hệ mà giao diện chưa thiết lập nên không thể được dùng để tương tác với giao diện app
   (+) ATBMCQ-13_19120617_19120622_19120641_19120644_PH2_APPDATA.sql: CSDL phân hệ 2, gắn liền với các giao diện đã thiết lập (để giao diện hoạt động cần data của script này)
