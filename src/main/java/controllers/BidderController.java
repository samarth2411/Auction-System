package controllers;

import com.google.inject.Inject;
import dao.BidderDao;
import dto.BidderDto;
import models.Bidder;
import ninja.Result;
import ninja.Results;
import ninja.params.Param;
import ninja.params.PathParam;

import java.util.List;

public class BidderController {
    @Inject
    BidderDao bidderDao;

    public Result addBidder(BidderDto bidderDto){
        Bidder bidder = bidderDao.addNewBidder(bidderDto);
        System.out.println("New Bidder Added ");
        return Results.json().render(bidder);
    }

    public Result countTotalBids(@Param("userId") String userId){
        System.out.println("total bids");
        Long count = bidderDao.countBids(userId);
        return Results.json().render(count);
    }

    public Result findBidder(@PathParam("productId") Long productId){
        try{
            List<Bidder> bidder = bidderDao.findBidderByProductId(productId);
            System.out.println("All the Bidders with product Id "+ productId + " Retreived");
            return Results.json().render(bidder);
        }
        catch(Exception e){
            e.printStackTrace();
            return Results.badRequest().json().render("Bad Request");
        }
    }

    public Result findBidderWithMaxBid(){
        try{
            Bidder bidder  = bidderDao.findBidderWithMaxBid();
            System.out.println("Bidder with max Bid Retreived");
            return Results.json().render(bidder);

        }
        catch(Exception e){
            e.printStackTrace();
            return Results.badRequest().json().render("Bad Request");
        }
    }

    public Result updateCurrentBid(@Param("productId") Long productId , @Param("userId") String userId ,
                                   @Param("currentBid") Long currentBid){
        try{
          Bidder bidder =  bidderDao.updateCurrentBid(productId,userId,currentBid);
            System.out.println("Current Bid update to "+currentBid);
            return Results.json().render(bidder);
        }
        catch(Exception e){
            e.printStackTrace();
            return Results.badRequest().json().render("Bad Request");
        }
    }
}
