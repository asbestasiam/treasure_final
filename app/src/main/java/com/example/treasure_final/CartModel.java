package com.example.treasure_final;

import androidx.arch.core.internal.SafeIterableMap;
import androidx.collection.ArraySet;

public class CartModel {

    public ArraySet<Object> getProductname;
    private String cartid;
    private String productname;
    private String productimage;
    private String productprice;
    private String productQty;

    public CartModel(String cartid, CharSequence title, String image, String price, String qty, String uid) {
        this.cartid = cartid;

    }

    public CartModel(String cartid, String productname, String productimage, String productprice, String productQty) {
        this.cartid = cartid;
        this.productname = productname;
        this.productimage = productimage;
        this.productprice = productprice;
        this.productQty = productQty;
    }

    public void setCartid(String cartid) {
        this.cartid = cartid;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public void setProductimage(String productimage) {
        this.productimage = productimage;
    }

    public void setProductprice(String productprice) {
        this.productprice = productprice;
    }

    public void setProductQty(String productQty) {
        this.productQty = productQty;
    }

    public Object getProductImage() {
        return null;
    }

    public Integer getProductPrice() {
        return null;
    }
}
