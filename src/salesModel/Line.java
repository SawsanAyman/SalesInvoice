package salesModel;

public class Line {

    

    @Override
    public String toString() {
        return "line{" + "num=" + invoice.getNum() + ", itemName=" + itemName + ", price=" + price + ", count=" + count + '}';
    }
    private String itemName;
    private double price;
    private int count;
    private InvoiceData invoice;

    public Line() {
    }

    public Line( String itemName, double price, int count) {
      
        this.itemName = itemName;
        this.price = price;
        this.count = count;
    }

    public Line( String itemName, double price, int count, InvoiceData invoice) {
      
        this.itemName = itemName;
        this.price = price;
        this.count = count;
        this.invoice = invoice;
    }

    public InvoiceData getInvoice() {
        return invoice;
    }
    

    public double getTotalOfLine() {

        return price * count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

   

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
