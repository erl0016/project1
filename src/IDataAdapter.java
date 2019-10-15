
public interface IDataAdapter {

    public static final int CONNECTION_OPEN_OK = 100;
    public static final int CONNECTION_OPEN_FAILED = 101;

    public static final int CONNECTION_CLOSE_OK = 200;
    public static final int CONNECTION_CLOSE_FAILED = 201;

    public static final int ACTION_SAVED_OK = 0;
    public static final int ACTION_DUPLICATE_ERROR = 1;

    public int connect(String dbfile);
    public int disconnect();

    public ProductModel loadProduct(int id);
    public int saveProduct(ProductModel model);
    public int saveCustomer(CustomerModel customerModel);
    public int savePurchase(PurchaseModel purchaseModel);
    public CustomerModel loadCustomer(int id);
}
