
package salesModel;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;


public class ItemTableModel extends AbstractTableModel  {
    
    private ArrayList<Line> lines;
private String[] columns = {"number", "item", "price", "amount","total"};

    public ItemTableModel(ArrayList<Line> line) {
        this.lines = line;
    }

    @Override
    public int getRowCount() {
        return lines.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }
     public String getColumnName(int column) {
        return columns[column];
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
          Line line = lines.get(rowIndex);
          switch (columnIndex){
              case 0 : return line.getInvoice().getNum();
              case 1 : return line.getItemName();
              case 2 : return line.getPrice();
              case 3 : return line.getCount();
              case 4 : return line.getTotalOfLine();
              default: return "";
           }
             
    }
    
}
