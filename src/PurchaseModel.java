import java.util.Date;

public class PurchaseModel {
    private int purchaseId, customerId, productId, quantity;
    private String purchaseDate;
    private double baseCost, taxCost, totalCost;

    public int getPurchaseId() {
        return purchaseId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public double getBaseCost() {
        return baseCost;
    }

    public double getTaxCost() {
        return taxCost;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setPurchaseId(int id) {
        purchaseId = id;
    }

    public void setCustomerId(int id) {
        customerId = id;
    }

    public void setQuantity(int quantityIn) {
        quantity = quantityIn;
    }

    public void setBaseCost(double baseCostIn) {
        baseCost = baseCostIn;
    }

    public void setTaxCost(double taxCostIn) {
        taxCost = taxCostIn;
    }

    public void setTotalCost(double totalCostIn) {
        totalCost = totalCostIn;
    }

    public void setPurchaseDate(String date) {
        purchaseDate = date;
    }

    public void setProductId(int id) {
        productId = id;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("(");
        sb.append(purchaseId).append(",");
        sb.append(customerId).append(",");
        sb.append("\"").append(purchaseDate).append("\"").append(",");
        sb.append(quantity).append(",");
        sb.append(baseCost).append(",");
        sb.append(taxCost).append(",");
        sb.append(totalCost).append(",");
        sb.append(productId).append(")");
        return sb.toString();
    }
}
