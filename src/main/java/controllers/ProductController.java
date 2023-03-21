package controllers;

import com.google.inject.Inject;
import dao.ProductDao;
import dto.ProductDto;
import models.Product;
import ninja.Result;
import ninja.Results;
import ninja.params.Param;
import ninja.params.PathParam;

import java.util.List;

public class ProductController {
    @Inject
    ProductDao productDao;

    public Result CreateProduct(ProductDto productDto) throws Exception {
        System.out.println("Create Product Method Running");
        try{
            Product product = productDao.AddProduct(productDto);
            System.out.println("New Product "+product.getName()+" added");
            return Results.json().render(product);

        }
        catch (Exception e){
            e.printStackTrace();
            return Results.badRequest().json().render("Bad Request");
        }

    }

    public Result allNotInAuctionProducts(){
        System.out.println("All the Products Not in Auction Retreived");
        return Results.json().render(productDao.returnNotInAuctionProducts());
    }

    public Result count(){
        System.out.println("count of Products");
        return Results.json().render(productDao.count());
    }

    public Result totalWin(@Param("userId") String userId){
        Long winCount = productDao.winCount(userId);
        return Results.json().render(winCount);
    }

    public Result totalLoss(@Param("userId") String userId){
        Long lossCount = productDao.lossCount(userId);
        return Results.json().render(lossCount);
    }

    public Result allInAuctionProducts(){
        System.out.println("All In Auction Products Retreived");
        return Results.json().render(productDao.showAllInAuctionProduct());
    }

    public Result singleProductWithId(@Param("id") Long id) throws Exception{
        try {
            Product product = productDao.showProductWithId(id);
            System.out.println("Product with id " + id + "Retreived");
            return Results.json().render(product);
        }
        catch (Exception e){
            e.printStackTrace();
            return Results.badRequest().json().render("Bad Request");
        }
    }

    public Result getAllProducts(@PathParam("owner") String owner){
       List<Product> productList =  productDao.showAllProduct(owner);
       return Results.json().render(productList);
    }
}
