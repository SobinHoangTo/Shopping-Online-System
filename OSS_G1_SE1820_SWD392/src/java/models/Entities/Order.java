package models.Entities;

public class Order {

    private int id;
    private int userId;
    private String address;
    private String paymentMethod;
    private String paymentStatus;
    private String shippingStatus;
    private String status;
    private double amount;

    public Order(int id, int userId, String address, String paymentMethod, String paymentStatus, String shippingStatus, String status, double amount) {
        this.id = id;
        this.userId = userId;
        this.address = address;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
        this.shippingStatus = shippingStatus;
        this.status = status;
        this.amount = amount;
    }

    public Order() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
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

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", userId=" + userId + ", address=" + address + ", paymentMethod=" + paymentMethod + ", paymentStatus=" + paymentStatus + ", shippingStatus=" + shippingStatus + ", status=" + status + ", amount=" + amount + '}';
    }

}
