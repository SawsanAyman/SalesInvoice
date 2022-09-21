
package salesModel;

import java.util.ArrayList;


public class InvoiceData {
    private int num;
    private String date;
    private String customerName;
    private ArrayList<Line>  lines;

    public ArrayList<Line> getLines() {
        if (lines==null) {
            lines= new ArrayList<>();
        }
        return lines;
    }

    public InvoiceData() {
    }

    public InvoiceData(int num, String date, String customerName) {
        this.num = num;
        this.date = date;
        this.customerName = customerName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
  

    
    public double getTotal (){
        double total =0.0;
        for(Line line:getLines()){
            total=total+line.getTotalOfLine();
        
        }
    
    return total;
    }

    @Override
    public String toString() {
        return "invoiceData{" + "num=" + num + ", date=" + date + ", customerName=" + customerName + ", lines=" + lines + '}';
    }
    
    
    
    
}
