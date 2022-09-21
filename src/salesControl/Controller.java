package salesControl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import salesModel.InvoiceData;
import salesModel.ItemTableModel;
import salesModel.Line;
import salesModel.TableModel;
import salesView.InvoiceDesign;

public class Controller implements ActionListener, ListSelectionListener {

    private InvoiceDesign design;

    public Controller(InvoiceDesign design) {
        this.design = design;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        //   System.out.println("Action : " + actionCommand);
        switch (actionCommand) {
            case "loadFile":
                loadFile();
                break;
            case "saveFile":
                saveFilee();
                break;
            case "newInvoice":
                createNewInvoice();
                break;
            case "deleteInvoice":
                deleteInvoice();
                break;
            case "newItem":
                createNewItem();
                break;
            case "deleteItem":
                deleteItem();

                break;

        }
    }

    private void loadFile() {
        JFileChooser fc = new JFileChooser();
        try {
            int clickResult = fc.showOpenDialog(null);
            if (clickResult == JFileChooser.APPROVE_OPTION) {
                File hFile = fc.getSelectedFile();
                Path hPath = Paths.get(hFile.getAbsolutePath());
                List<String> hLines = Files.readAllLines(hPath);
               // System.out.println("hFile readed");

                ArrayList<InvoiceData> listOfInvoices = new ArrayList<>();
                for (String hLine : hLines) {
                    String[] splitedHParts = hLine.split(",");
                    int invoiceNum = Integer.parseInt(splitedHParts[0]);
                    String date = splitedHParts[1];
                    String clientName = splitedHParts[2];
                    InvoiceData invoice = new InvoiceData(invoiceNum, date, clientName);
                    listOfInvoices.add(invoice);
                 //   System.out.println("hFile splited");
                }
                clickResult = fc.showOpenDialog(null);
                if (clickResult == JFileChooser.APPROVE_OPTION) {
                    File lFile = fc.getSelectedFile();
                    Path lPath = Paths.get(lFile.getAbsolutePath());
                    List<String> lLines = Files.readAllLines(lPath);
                 //   System.out.println("lFile readed");

                    for (String lLine : lLines) {
                        String[] splitedLParts = lLine.split(",");
                        int invoiceNum = Integer.parseInt(splitedLParts[0]);
                        String item = splitedLParts[1];
                        double price = Double.parseDouble(splitedLParts[2]);
                        int amount = Integer.parseInt(splitedLParts[3]);
                   //     System.out.println("lFile splited");

                        InvoiceData invoice = null;
                        for (InvoiceData selectedInvoice : listOfInvoices) {
                            if (selectedInvoice.getNum() == invoiceNum) {

                                invoice = selectedInvoice;
                                break;

                            }
                        }
                        Line newLine = new Line(item, price, amount, invoice);
                        invoice.getLines().add(newLine);

                    }
            //        System.out.println("invoices Linked with Lines");

                }
                design.setInvoice(listOfInvoices);

                TableModel tableModel = new TableModel(listOfInvoices);
                design.setTableModel(tableModel);
                design.getInvoicTable().setModel(tableModel);
                design.getTableModel().fireTableDataChanged();
            }

        } catch (IOException ex) {
            ex.printStackTrace();

        }
    }

    private void saveFilee() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void createNewInvoice() {
        }

    private void deleteInvoice() {
        int selectedRaw = design.getInvoicTable().getSelectedRow();
        if (selectedRaw > -1) {
            design.getInvoice().remove(selectedRaw);
            design.getTableModel().fireTableDataChanged();
        }
    }

    private void deleteItem() {
        int selectedInvoice = design.getInvoicTable().getSelectedRow();   
        int selectedRaw = design.getLineTable().getSelectedRow();
        if (selectedInvoice!=-1&&selectedRaw > -1) {
            InvoiceData invoiceData= design.getInvoice().get(selectedInvoice) ;
            invoiceData.getLines().remove(selectedRaw);
            ItemTableModel itemTableModel = new ItemTableModel(invoiceData.getLines());
            design.getLineTable().setModel(itemTableModel);
            itemTableModel.fireTableDataChanged();
        } 
        
    }

    private void createNewItem() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        int seletedLine = design.getInvoicTable().getSelectedRow();
        if (seletedLine != -1) {
            InvoiceData selectedInvoice = design.getInvoice().get(seletedLine);
            String invoiceNumLable = "" + selectedInvoice.getNum();
            String invoiceTotal = "" + selectedInvoice.getTotal();
            design.getInvoiceNumLabel().setText(invoiceNumLable);
            design.getDateLabel().setText(selectedInvoice.getDate());
            design.getClientNameLabel().setText(selectedInvoice.getCustomerName());
            design.getTotalLabel().setText(invoiceTotal);
            ItemTableModel itemTableModel = new ItemTableModel(selectedInvoice.getLines());
            design.getLineTable().setModel(itemTableModel);
            itemTableModel.fireTableDataChanged();
        }
    }
}
