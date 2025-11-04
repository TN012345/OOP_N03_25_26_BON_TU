package com.phenikaa.quanly;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.LocalDateTime;

/**
 * Xử lý lỗi toàn cục cho toàn bộ ứng dụng
 * Giúp tránh lỗi trắng trang hoặc crash khi có exception
 */
@ControllerAdvice
public class MyGlobal {
     // Biến toàn cục để lưu trạng thái lỗi
    public static int indexError = 0;

    // ✅ Bắt lỗi khi không tìm thấy trang (404)
    @ExceptionHandler(NoHandlerFoundException.class)
    public String handleNotFound(NoHandlerFoundException ex, Model model) {
        model.addAttribute("status", HttpStatus.NOT_FOUND.value());
        model.addAttribute("error", "Không tìm thấy trang yêu cầu");
        model.addAttribute("message", ex.getMessage());
        model.addAttribute("timestamp", LocalDateTime.now());
        return "error"; // render file templates/error.html
    }

    // ✅ Bắt lỗi khi đối tượng null hoặc lỗi dữ liệu
    @ExceptionHandler(NullPointerException.class)
    public String handleNullPointer(NullPointerException ex, Model model) {
        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        model.addAttribute("error", "Lỗi xử lý dữ liệu (NullPointer)");
        model.addAttribute("message", ex.getMessage());
        model.addAttribute("timestamp", LocalDateTime.now());
        return "error";
    }

    // ✅ Bắt tất cả lỗi khác
    @ExceptionHandler(Exception.class)
    public String handleAll(Exception ex, Model model) {
        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        model.addAttribute("error", "Đã xảy ra lỗi không mong muốn");
        model.addAttribute("message", ex.getMessage());
        model.addAttribute("timestamp", LocalDateTime.now());
        return "error";
    }
}
