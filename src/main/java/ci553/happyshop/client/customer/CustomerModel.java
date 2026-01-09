package ci553.happyshop.client.customer;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import ci553.happyshop.catalogue.Order;
import ci553.happyshop.catalogue.Product;
import ci553.happyshop.orderManagement.OrderHub;
import ci553.happyshop.storageAccess.DatabaseRW;
import ci553.happyshop.utility.ProductListFormatter;
import ci553.happyshop.utility.StorageLocation;


/**
 * TODO
 * You can either directly modify the CustomerModel class to implement the required tasks,
 * or create a subclass of CustomerModel and override specific methods where appropriate.
 */
public class CustomerModel {
    public CustomerView cusView;
    public DatabaseRW databaseRW; //Interface type, not specific implementation
                                  //Benefits: Flexibility: Easily change the database implementation.

    private Product theProduct =null; // product found from search - not private to allow tests to access without reflection
    private ArrayList<Product> trolley =  new ArrayList<>(); // a list of products in trolley

    // Four UI elements to be passed to CustomerView for display updates.
    private String imageName = "search.jpg";                // Image to show in product preview (Search Page)
    private String displayLaSearchResult = "No Product was searched yet"; // Label showing search result message (Search Page)
    private String displayTaTrolley = "";                                // Text area content showing current trolley items (Trolley Page)
    private String displayTaReceipt = "";                                // Text area content showing receipt after checkout (Receipt Page)
    /** 
     * @throws SQLException
     */
    //Executes a search using the Textfields from the customer view
    //Displays the result
    void doSearch() throws SQLException{
        String productId = cusView.tfId.getText().trim();
        String productDesc = cusView.tfName.getText().trim();
        theProduct = search(productId, productDesc);

        // Display result
        if (theProduct != null && theProduct.getStockQuantity() > 0) {
            double unitPrice = theProduct.getUnitPrice();
            String description = theProduct.getProductDescription();
            int stock = theProduct.getStockQuantity();

            String baseInfo = String.format("Product ID: %s\n%s,\nPrice: £%.2f", theProduct.getProductId(), description, unitPrice);
            String quantityInfo = stock < 100 ? String.format("\n%d units left.", stock) : "";
            displayLaSearchResult = baseInfo + quantityInfo;
            System.out.println(displayLaSearchResult); // for testing purposes
        } else {
            displayLaSearchResult = "No Product was found matching that ID or Name.";
            System.out.println(displayLaSearchResult); // for testing purposes
        }
        updateView();
    }
    
    /** 
     * @param productId
     * @param productDesc
     * @return Product
     * @throws SQLException
     */
    //SELECT productID, description, image, unitPrice,inStock quantity
    Product search(String productId, String productDesc) throws SQLException {
        Product searchResult;
        // If no input provided
        if (productId.isEmpty() && productDesc.isEmpty()) {
            displayLaSearchResult = "Please type in an ID or Name.";
            System.out.println(displayLaSearchResult); // for testing purposes
            return null;
        }
        // Search logic for id and desc
        if (!productId.isEmpty() && !productDesc.isEmpty()) {
            // Search by ID first then to description if not found
            searchResult = databaseRW.searchByProductId(productId);
            if (searchResult == null) {
                searchResult = databaseRW.searchProduct(productDesc).getFirst();
            }
        } else if (!productId.isEmpty()) {
            searchResult = databaseRW.searchByProductId(productId);
        } else {
            searchResult = databaseRW.searchProduct(productDesc).getFirst();
        }
        return searchResult;

    }

    void addToTrolley(){
        if(theProduct == null){
            displayLaSearchResult = "Please search for an available product before adding it to the trolley";
            System.out.println("must search and get an available product before add to trolley");
        }

        // trolley.add(theProduct) — Product is appended to the end of the trolley.
        //Only if this product is not yet in trolley, we need a new item, otherwise is added to existing item
        if (!productAlreadyInTrolley()){
            trolley.add(theProduct);
        }
        trolley.sort(Comparator.comparing(Product::getProductId));
        displayTaTrolley = ProductListFormatter.buildString(trolley); //build a String for trolley so that we can show it
        displayTaReceipt=""; // Clear receipt to switch back to trolleyPage (receipt shows only when not empty)
        updateView();
    }

    /** 
     * @param trolley
     * @return boolean
     */
    boolean productAlreadyInTrolley(){ // making sure the quantities of products add up together
        for ( Product product : trolley) {
            if ( product.getProductId().equals(theProduct.getProductId())) {
                product.setOrderedQuantity(product.getOrderedQuantity() + 1);
                return true;
            }
        }
        return false;
    }

    /** 
     * @throws IOException
     * @throws SQLException
     */
    void checkOut() throws IOException, SQLException {
        if(!trolley.isEmpty()){
            // Group the products in the trolley by productId to optimize stock checking
            // Check the database for sufficient stock for all products in the trolley.
            // If any products are insufficient, the update will be rolled back.
            // If all products are sufficient, the database will be updated, and insufficientProducts will be empty.
            // Note: If the trolley is already organized (merged and sorted), grouping is unnecessary.
            ArrayList<Product> groupedTrolley= groupProductsById(trolley);
            ArrayList<Product> insufficientProducts= databaseRW.purchaseStocks(groupedTrolley);

            if(insufficientProducts.isEmpty()){ // If stock is sufficient for all products
                //get OrderHub and tell it to make a new Order
                OrderHub orderHub =OrderHub.getOrderHub();
                Order theOrder = orderHub.newOrder(trolley);
                trolley.clear();
                displayTaTrolley ="";
                displayTaReceipt = String.format(
                        "Order_ID: %s\nOrdered_Date_Time: %s\n%s",
                        theOrder.getOrderId(),
                        theOrder.getOrderedDateTime(),
                        ProductListFormatter.buildString(theOrder.getProductList())
                );
                System.out.println(displayTaReceipt);
            }
            else{ // Some products have insufficient stock — build an error message to inform the customer
                StringBuilder errorMsg = new StringBuilder();
                for(Product p : insufficientProducts){
                    errorMsg.append("\u2022 "+ p.getProductId()).append(", ")
                            .append(p.getProductDescription()).append(" (Only ")
                            .append(p.getStockQuantity()).append(" available, ")
                            .append(p.getOrderedQuantity()).append(" requested)\n");
                }
                theProduct=null;

                //TODO
                // Add the following logic here:
                // 1. Remove products with insufficient stock from the trolley.
                // 2. Trigger a message window to notify the customer about the insufficient stock, rather than directly changing displayLaSearchResult.
                //You can use the provided RemoveProductNotifier class and its showRemovalMsg method for this purpose.
                //remember close the message window where appropriate (using method closeNotifierWindow() of RemoveProductNotifier class)
                displayLaSearchResult = "Checkout failed due to insufficient stock for the following products:\n" + errorMsg.toString();
                System.out.println("stock is not enough");
            }
        }
        else{
            displayTaTrolley = "Your trolley is empty";
            System.out.println("Your trolley is empty");
        }
        updateView();
    }

    /**
     * Groups products by their productId to optimize database queries and updates.
     * By grouping products, we can check the stock for a given `productId` once, rather than repeatedly
     */
    private ArrayList<Product> groupProductsById(ArrayList<Product> proList) {
        Map<String, Product> grouped = new HashMap<>();
        for (Product p : proList) {
            String id = p.getProductId();
            if (grouped.containsKey(id)) {
                Product existing = grouped.get(id);
                existing.setOrderedQuantity(existing.getOrderedQuantity() + p.getOrderedQuantity());
            } else {
                // Make a shallow copy to avoid modifying the original
                grouped.put(id,new Product(p.getProductId(),p.getProductDescription(),
                        p.getProductImageName(),p.getUnitPrice(),p.getStockQuantity()));
            }
        }
        return new ArrayList<>(grouped.values());
    }

    void cancel(){
        trolley.clear();
        displayTaTrolley="";
        updateView();
    }
    void closeReceipt(){
        displayTaReceipt="";
    }

    void updateView() {
        if(theProduct != null){
            imageName = theProduct.getProductImageName();
            String relativeImageUrl = StorageLocation.imageFolder +imageName; //relative file path, eg images/0001.jpg
            // Get the full absolute path to the image
            Path imageFullPath = Paths.get(relativeImageUrl).toAbsolutePath();
            imageName = imageFullPath.toUri().toString(); //get the image full Uri then convert to String
            System.out.println("Image absolute path: " + imageFullPath); // Debugging to ensure path is correct
        }
        else{
            imageName = "search.jpg";
        }
        cusView.update(imageName, displayLaSearchResult, displayTaTrolley,displayTaReceipt);
    }
     /** 
      * @return ArrayList<Product>
      */
     // extra notes:
     //Path.toUri(): Converts a Path object (a file or a directory path) to a URI object.
     //File.toURI(): Converts a File object (a file on the filesystem) to a URI object

    //for test only
    public ArrayList<Product> getTrolley() {
        //This should be removed in future
        return trolley;
    }
}
