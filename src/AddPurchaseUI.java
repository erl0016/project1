import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.Format;
import java.util.Calendar;
import java.util.Date;

public class AddPurchaseUI {
    public JFrame view;

    public JButton btnAdd = new JButton("Add");
    public JButton btnCancel = new JButton("Cancel");

    public JTextField txtPurchaseID = new JTextField(10);
    public JTextField txtProductID = new JTextField(10);
    public JTextField txtCustomerID = new JTextField(10);
    public JTextField txtQuantity = new JTextField(10);

    public JLabel labPrice = new JLabel("Product Price: ");
    public JLabel labDate = new JLabel("Date of Purchase: ");

    public JLabel labCustomerName = new JLabel("Customer Name: ");
    public JLabel labProductName = new JLabel("Product Name: ");

    public JLabel labCost = new JLabel("Cost: $0.00 ");
    public JLabel labTax = new JLabel("Tax: $0.00");
    public JLabel labTotalCost = new JLabel("Total Cost: $0.00");

    ProductModel product;
    CustomerModel customerModel;
    PurchaseModel purchaseModel;

    private final double TAX_RATE = 0.07;

    public AddPurchaseUI() {
        this.view = new JFrame();

        view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        view.setTitle("Add Purchase");
        view.setSize(600, 400);
        view.getContentPane().setLayout(new BoxLayout(view.getContentPane(), BoxLayout.PAGE_AXIS));

        JPanel line = new JPanel(new FlowLayout());
        line.add(new JLabel("PurchaseID "));
        line.add(txtPurchaseID);
        line.add(labDate);
        view.getContentPane().add(line);

        line = new JPanel(new FlowLayout());
        line.add(new JLabel("ProductID "));
        line.add(txtProductID);
        line.add(labProductName);
        view.getContentPane().add(line);

        line = new JPanel(new FlowLayout());
        line.add(new JLabel("CustomerID "));
        line.add(txtCustomerID);
        line.add(labCustomerName);
        view.getContentPane().add(line);

        line = new JPanel(new FlowLayout());
        line.add(new JLabel("Quantity "));
        line.add(txtQuantity);
        line.add(labPrice);
        view.getContentPane().add(line);

        line = new JPanel(new FlowLayout());
        line.add(labCost);
        line.add(labTax);
        line.add(labTotalCost);
        view.getContentPane().add(line);

        line = new JPanel(new FlowLayout());
        line.add(btnAdd);
        line.add(btnCancel);
        view.getContentPane().add(line);

        txtQuantity.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent focusEvent) {

            }

            @Override
            public void focusLost(FocusEvent focusEvent) {
                process();
            }

            private void process() {
                purchaseModel = new PurchaseModel();
                String s = txtProductID.getText();
                String c = txtCustomerID.getText();
                String q = txtQuantity.getText();

                if (s.length() == 0) {
                    labProductName.setText("Product Name: [not specified!]");
                    return;
                }
                System.out.println("ProductID = " + s);

                if (c.length() == 0) {
                    labCustomerName.setText("Customer Name: [not specified!]");
                }
                System.out.println("CustomerID = " + c);

                int prodID, custID;

                try {
                    prodID = Integer.parseInt(s);
                    custID = Integer.parseInt(c);

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null,
                            "Error: Invalid ProductID", "Error Message",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                product = StoreManager.getInstance().getDataAdapter().loadProduct(prodID);
                customerModel = StoreManager.getInstance().getDataAdapter().loadCustomer(custID);

                if (product == null) {
                    JOptionPane.showMessageDialog(null,
                            "Error: No product with id = " + prodID + " in store!", "Error Message",
                            JOptionPane.ERROR_MESSAGE);
                    labProductName.setText("Product Name: ");

                    return;
                }
                if (customerModel == null) {
                    JOptionPane.showMessageDialog(null,
                            "Error: No customer with id = " + custID + " in store!", "Error Message",
                            JOptionPane.ERROR_MESSAGE);
                    labCustomerName.setText("Customer Name: ");

                    return;
                }

                labProductName.setText("Product Name: " + product.getProdName());
                labCustomerName.setText("Customer Name: " + customerModel.getName());
                labPrice.setText("Calculated Price: " + product.getProdPrice());
                calculateSubPrices(product, q);
            }
        });

        btnAdd.addActionListener(new AddButtonListener());
        btnCancel.addActionListener(actionEvent -> view.dispose());
    }

    public void run() {
        Date purchaseDate = Calendar.getInstance().getTime();
        labDate.setText(String.valueOf(purchaseDate));
        view.setVisible(true);
    }

    private void calculateSubPrices(ProductModel productModel, String quantityIn) {
        double price = productModel.getProdPrice();
        double tax = price * TAX_RATE;
        int quantity = Integer.parseInt(quantityIn);
        double baseCost =  price * quantity;
        double total = baseCost + tax;

        purchaseModel.setBaseCost(baseCost);
        purchaseModel.setTaxCost(tax);
        purchaseModel.setTotalCost(total);

        labCost.setText("Base Cost: " + baseCost);
        labTax.setText("Tax Cost: " + tax);
        labTotalCost.setText("Total Cost: " + total);
    }

    private String generateReceipt(PurchaseModel purchaseModel) {
        StringBuilder receipt = new StringBuilder();
        receipt.append("Purchase ID: " + purchaseModel.getPurchaseId()).append("\r\n");
        receipt.append("Customer ID: " + purchaseModel.getCustomerId()).append("\r\n");
        receipt.append("Product ID: " + purchaseModel.getProductId()).append("\r\n");
        receipt.append("Date of Purchase: " + purchaseModel.getPurchaseDate()).append("\r\n");
        receipt.append("Subcost: " + purchaseModel.getBaseCost()).append("\r\n");
        receipt.append("Tax: " + purchaseModel.getTaxCost()).append("\r\n");
        receipt.append("Total Cost: " + purchaseModel.getTotalCost()).append("\r\n");
        receipt.append("Thank you for your purchase!");
        return receipt.toString();
    }

    class AddButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            Object source = actionEvent.getSource();
            String sourceClass = source.getClass().toString();
            if (sourceClass.contains("JButton")) {
                JButton jButton = (JButton)source;
                switch (jButton.getText()) {
                    case "Cancel": {
                        int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to cancel?",
                                "Confirm Cancel", 0, 1);
                        if (response == 0) {
                            //confirm cancellation request
                            break;
                        }
                        break;
                    }
                    case "Add": {

                        //PurchaseId
                        String purchaseId = txtPurchaseID.getText();
                        if (purchaseId.length() == 0) {
                            JOptionPane.showMessageDialog(null, "Id cannot be null!");
                            return;
                        }
                        try {
                            purchaseModel.setPurchaseId(Integer.parseInt(purchaseId));
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "PurchaseId is invalid!");
                            return;
                        }

                        //CustomerId
                        String customerId = txtCustomerID.getText();
                        if (customerId.length() == 0) {
                            JOptionPane.showMessageDialog(null, "CustomerId cannot be empty!");
                            return;
                        }
                        purchaseModel.setCustomerId(Integer.parseInt(customerId));

                        //ProductId
                        String productId = txtProductID.getText();
                        try {
                            purchaseModel.setProductId(Integer.parseInt(productId));
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "ProductId is invalid!");
                            return;
                        }

                        //Quantity
                        String quantity = txtQuantity.getText();
                        try {
                            purchaseModel.setQuantity(Integer.parseInt(quantity));
                        }
                        catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Quantity is invalid!");
                            return;
                        }

                        //Date
                        String date = labDate.getText();
                        try {
                            purchaseModel.setPurchaseDate(date);
                        }
                        catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Date is invalid!");
                            return;
                        }

                        switch (StoreManager.getInstance().getDataAdapter().savePurchase(purchaseModel)) {
                            case SQLiteDataAdapter.ACTION_DUPLICATE_ERROR:
                                JOptionPane.showMessageDialog(null, "Purchase NOT added successfully! Duplicate purchase ID!");
                                break;
                            default:
                                JOptionPane.showMessageDialog(null, "Purchase added successfully!" + purchaseModel);
                                System.out.println(generateReceipt(purchaseModel));
                        }
                        break;
                    }
                    default: {
                        System.out.println("Something occurred during panel sub-operation.");
                        break;
                    }
                }
            }
        }
    }
}
