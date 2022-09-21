package salesModel;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class TableModel extends AbstractTableModel {

    private ArrayList<InvoiceData> invoice;
    private String[] columns = {"number", "date", "clientName", "total"};

    public TableModel(ArrayList<InvoiceData> invoice) {
        this.invoice = invoice;
    }

    @Override
    public int getRowCount() {
        return invoice.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        InvoiceData invoiceData  = invoice.get(rowIndex);
        switch(columnIndex){
        
            case 0:
                return  invoiceData.getNum();
            case 1:
                return  invoiceData.getDate();
            case 2:
                return  invoiceData.getCustomerName();
            case 3:
                return  invoiceData.getTotal();
            default:
                return "";
        }

    }

}
