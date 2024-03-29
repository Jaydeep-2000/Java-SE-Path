package com.javacollections.collections._3_lists;

import com.javacollections.collections.common.Product;
import java.lang.Iterable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Shipment implements Iterable<Product>{
    private static final int MISSING_PRODUCT = -1;
    private static final int LIGHT_VAN_MAX_WEIGHT = 20;

    private final List<Product> products =new ArrayList<>();
    private List<Product> lightVanProducts;
    private List<Product> heavyVanProducts;

    public void add(Product product){
        products.add(product);
    }

    public boolean replace(Product oldProduct, Product newProduct){
//        int position = products.indexOf(oldProduct);
//        if(position == MISSING_PRODUCT){
//            return false;
//        }
//        else {
//            products.set(position, newProduct);
//            return true;
//        }

//        products.replaceAll(product -> {
//            if(product == oldProduct){
//                return newProduct;
//            }
//            else{
//                return product;
//            }
//        });
//        return true;

        boolean update = products.contains(oldProduct);
        if(update) {
            products.replaceAll(product -> product == oldProduct ? newProduct : product);
            return true;
        }
        return true;
    }

    public void prepare(){
        //sort the product list
        products.sort(Product.BY_WEIGHT);

        //find the split point
        int splitPoint = findSplitpoint();

        //create two subviews of the list
        lightVanProducts = products.subList(0, splitPoint);
        heavyVanProducts = products.subList(splitPoint, products.size());
    }

    private int findSplitpoint() {
        int size = products.size();
        for(int i=0; i<size; i++){
            var product = products.get(i);
            if(product.weight() > LIGHT_VAN_MAX_WEIGHT){
                return i;
            }
        }
        return products.size();
    }

    @Override
    public Iterator<Product> iterator() {
        return products.iterator();
    }

    public List<Product> getLightVanProducts() {
        return lightVanProducts;
    }

    public void setLightVanProducts(List<Product> lightVanProducts) {
        this.lightVanProducts = lightVanProducts;
    }

    public List<Product> getHeavyVanProducts() {
        return heavyVanProducts;
    }

    public void setHeavyVanProducts(List<Product> heavyVanProducts) {
        this.heavyVanProducts = heavyVanProducts;
    }
}
