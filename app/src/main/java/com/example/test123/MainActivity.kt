package com.example.test123

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    // biến kiểm tra xem Toast đã dc hiện thị chưa
    private var showToast: Boolean = false

    // phương thức onCreate - được khởi chạy khi app bắt đầu hoạt động hoặc sau khi gọi onPause/onStop
    // Khởi tạo các đối tượng UI trc khi hiện thị cho người dùng
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Bỏ qua các khung hệ thống của điện thoại
        enableEdgeToEdge()

        // Xác định giao diện người dùng của ứng dụng sẽ trông như thế nào
        // Chứa liên kết đến tệp đánh dấu activity_main.xml
        setContentView(R.layout.activity_main)

        // Định nghĩa khung ứng dụng
        // Cần thiết để đảm bảo rằng các thành phần UI không được đặt bên ngoài màn hình
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        /*
         Các loại tin nhắn trong Log:
         Verbose - là một giao thức hoàn chỉnh cho các hoạt động của hệ thống.
         Debug - thông tin gỡ lỗi: các thông số được tạo ra trong quá trình vận hành ứng dụng.
         Info - thông tin.
         Warn - cảnh báo.
         Error - lỗi xảy ra trong quá trình vận hành ứng dụng.
         Trong giao thức, chúng được đánh dấu bằng màu nâu đỏ để có thể nhận thấy ngay lập tức.
         */
        Log.i("phương thức vòng đời", "onStart")

        // Check xem Bundle có bằng null hay ko
        // Vì lần đầu mở app nó sẽ là null, vì chưa dc khởi tạo
        if(savedInstanceState != null){
            showToast = savedInstanceState.getBoolean("SHOW_TOAST")
        }

        // nếu showToast = false, mới hiện thị thông báo
        if(!showToast){
            // Hiện thị thông báo ra màn hình
            Toast.makeText(this, "Hello User", Toast.LENGTH_LONG).show()
            showToast = true
        }

    }

    // Phương thức onStart - chạy sau onCreate
    // Chuẩn bị giao diện người dùng để hiển thị cho người dùng
    override fun onStart(){
        super.onStart()
        Log.e("phương thức vòng đời", "onStart")
    }

    // Phương thức onResume - chạy sau onStart/onPause
    // Hiển thị cho người dùng giao diện hoạt động
    // Sau khi gọi nó, người dùng có thể tương tác với giao diện
    override fun onResume(){
        super.onResume()
        Log.e("phương thức vòng đời", "onResume")
    }

    // Phương thức onPause - chạy sau onResume
    // Chuẩn bị cho hoạt động chuyển sang chế độ ngủ, tiếp tục hoặc khởi động lại
    override fun onPause(){
        super.onPause()
        Log.e("phương thức vòng đời", "onPause")
    }

    // Phương thức onStop - chạy sau onPause
    // Đưa hoạt động vào "chế độ ngủ" và chuẩn bị tiếp tục hoặc hủy bỏ hoạt động
    override fun onStop(){
        super.onStop()
        Log.e("phương thức vòng đời", "onStop")
    }

    // Phương thức onRestart - chạy sau onStop
    // Tải lại giao diện người dùng nhưng không tạo lại nó (Ko vào lại onCreate)
    override fun onRestart() {
        super.onRestart()
        Log.d("Метод жизненного цикла", "onRestart")
    }

    // Phương thức onDestroy - chạy sau onStop
    // Được gọi trước khi hoạt động bị hủy sau khi hoàn thành (tắt app)
    override fun onDestroy() {
        super.onDestroy()
        Log.d("Метод жизненного цикла", "onDestroy")
    }


    // Phương thức onSaveInstanceState - được gọi để ghi giá trị vào Bundle storage
    // trước khi khởi động lại app (sau khi xoay màn hình chẳng hạn -> nó cũng onRestart lại)
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        // Ghi giá trị showToast vào bộ lưu trữ Bundle
        // lưu thêm key để lấy ra dc biến dựa trên key
        outState.putBoolean("SHOW_TOAST", showToast)
    }
}