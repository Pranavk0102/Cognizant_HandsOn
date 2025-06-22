package Coding.SearchOperations;
public class Product {
  int productId;
  String productName;
  String category;

  Product(int productId,String productName,String category){
    this.productId=productId;
    this.productName=productName;
    this.category=category;
  }
  public static Product linearsearch(Product[] products,String Search){
    for(int i=0;i<products.length;i++){
        if(products[i].productName.equalsIgnoreCase(Search)){
          return products[i];
        }
    }
    return null;
  }


public static Product binarysearch(Product[] products,String Search){
  int left=0;
  int right=products.length-1;
  while(left<=right){
    int mid=(left+right)/2;
    int result=products[mid].productName.compareToIgnoreCase(Search);
    if(result==0){
      return products[mid];
    }
    else if(result<0){
      left=mid+1;
    }
    else{
      right=mid-1;
    }
    
  }
  return null;
}
}



