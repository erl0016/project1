
public class OracleDataAdapter implements IDataAdapter {
    public int connect(String dbfile) {
        //...
        return CONNECTION_OPEN_OK;
    }

    public int disconnect() {
        // ...
        return CONNECTION_CLOSE_OK;

    }

    public ProductModel loadProduct(int id) {
        return null;
    }
    public int saveProduct(ProductModel model) {
        return ACTION_SAVED_OK;
    }
    public CustomerModel loadCustomer(int id) {
        return null;
    }
    public int saveCustomer(CustomerModel customerModel) {return ACTION_SAVED_OK;}
    public int savePurchase(PurchaseModel purchaseModel) {return ACTION_SAVED_OK;}
}
