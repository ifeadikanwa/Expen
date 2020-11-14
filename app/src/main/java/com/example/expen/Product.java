package com.example.expen;

public class Product {
    public class Products {
        private String productName;
        private Double productPrice;
        private String productID;

        public Products() {
        }

        public Products(String productName, Double productPrice, String productID) {
            this.productName = productName;
            this.productPrice = productPrice;
            this.productID = productID;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public Double getProductPrice() {
            return productPrice;
        }

        public void setProductPrice(Double productPrice) {
            this.productPrice = productPrice;
        }

        public String getProductID() {
            return productID;
        }

        public void setProductID(String productID) {
            this.productID = productID;
        }
    }
}
