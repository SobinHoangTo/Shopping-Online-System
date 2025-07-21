package models.Entities;

public class Order {

    private int id;
    private Integer userId;
    private String address;
    private String phone;
    private String name;
    private String paymentMethod;
    private String paymentStatus;
    private String shippingStatus;
    private String status;
    private String trackingNumber;
    private double amount;

    public Order(Integer userId, String address, String phone, String name, String paymentMethod, String paymentStatus, String shippingStatus, String status, String trackingNumber, double amount) {
        if (userId != null) {
            this.userId = userId;
        }
        this.address = address;
        this.phone = phone;
        this.name = name;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
        this.shippingStatus = shippingStatus;
        this.status = status;
        this.trackingNumber = trackingNumber;
        this.amount = amount;
    }

    public Order() {
    }

    public Order(int id, Integer userId, String address, String phone, String name, String paymentMethod, String paymentStatus, String shippingStatus, String status, String trackingNumber, double amount) {
        this.id = id;
        this.userId = userId;
        this.address = address;
        this.phone = phone;
        this.name = name;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
        this.shippingStatus = shippingStatus;
        this.status = status;
        this.trackingNumber = trackingNumber;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getShippingStatus() {
        return shippingStatus;
    }

    public void setShippingStatus(String shippingStatus) {
        this.shippingStatus = shippingStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", userId=" + userId + ", address=" + address + ", phone=" + phone + ", name=" + name + ", paymentMethod=" + paymentMethod + ", paymentStatus=" + paymentStatus + ", shippingStatus=" + shippingStatus + ", status=" + status + ", trackingNumber=" + trackingNumber + ", amount=" + amount + '}';
    }

}
