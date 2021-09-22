package ua.training.delivery.entity;

import java.math.BigDecimal;

public class Receipt {
    private long id;
    private BigDecimal price;
    private boolean paid;
    private Order order;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public static ReceiptBuilder builder(){
        return new ReceiptBuilder();
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "id=" + id +
                ", price=" + price +
                ", paid=" + paid +
                ", order=" + order +
                '}';
    }

    public static class ReceiptBuilder{
       private final Receipt newReceipt;

       private ReceiptBuilder(){
           newReceipt = new Receipt();
       }

       public ReceiptBuilder id(long id){
           newReceipt.setId(id);
           return this;
       }

        public ReceiptBuilder price(BigDecimal price){
            newReceipt.setPrice(price);
            return this;
        }

        public ReceiptBuilder paid(boolean paid){
            newReceipt.setPaid(paid);
            return this;
        }

        public ReceiptBuilder order(Order order){
            newReceipt.setOrder(order);
            return this;
        }

        public Receipt build(){
           return newReceipt;
        }

    }
}
