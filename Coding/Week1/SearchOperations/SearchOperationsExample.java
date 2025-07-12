package Coding.SearchOperations;
import java.util.*;
public class SearchOperationsExample {
  public static void main(String[] args) {
    Product[] products={
      new Product(1,"Sunrise Coffee","Kitchen"),
      new Product(2,"Lenovo Laptop","Electronics"),
      new Product(3,"FaceWash","Beauty"),
      new Product(4,"Watch","Accessories"),
      new Product(5,"Shoes","Footwear")
    };
    Product result1=Product.linearsearch(products,"Shoes");
    System.out.println("Linear Search: "+result1.productName+" at Id: "+result1.productId);

    Arrays.sort(products, Comparator.comparing(p -> p.productName.toLowerCase()));
    Product result2=Product.binarysearch(products, "Watch");
    System.out.println("Binary Search: "+result2.productName+" at Id: "+result2.productId);
  }
}
