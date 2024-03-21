package com.example.fashionmanager.enums;

public enum OrderStatus {
    PENDINGCONFIRMATION //Chờ xác nhận
    , CONFIRMED //Đã xác nhận
    , PROCESSING //Đang xử lý
    , SHIPPEDTOCARRIER //Đã giao cho dịch vụ vận chuyển
    , INTRANSIT //Đang vận chuyển
    , DELIVERED //Giao thành công
    , CANCELLED //Hủy đơn hàng
    , RETURN_EXCHANGE //Trả hàng/Đổi hàng
    , REFUNDED //Hoàn tiền
    , CLOSED //Đã đóng
}
