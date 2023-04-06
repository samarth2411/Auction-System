package dao;


import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.persist.Transactional;
import dto.BidderDto;
import models.AppUser;
import models.Bidder;
import models.Product;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class BidderDao {
    @Inject
    Provider<EntityManager> entityManagerProvider;

    @Transactional
    public Bidder addNewBidder(BidderDto bidderDto){
        EntityManager em = entityManagerProvider.get();
        Bidder bidder = new Bidder();
        bidder.setCurrentBid(bidderDto.getCurrentBid());
        bidder.setProductId(bidderDto.getProductId());
        bidder.setUserId(bidderDto.getUserId());
        em.persist(bidder);
        return bidder;
    }
    @Transactional
    public Long countBids(String userId){
        EntityManager em = entityManagerProvider.get();
        Long count = em.createNamedQuery("Bidder.countBidderByUserId",Long.class).setParameter("userId",userId).getSingleResult();
        return count;
    }

    public List<Bidder> bidders(String userId){
        EntityManager em = entityManagerProvider.get();
        List<Bidder> bidder = em.createNamedQuery("Bidder.BiddersByUserId").setParameter("userId",userId).getResultList();
        if(bidder.size()!=0){
            return bidder;
        }
        else{
            return null;
        }
    }

    @Transactional
    public List<Bidder> findBidderByProductId(Long productId){
        EntityManager em = entityManagerProvider.get();
        Query namedQuery = em.createNamedQuery("Bidder.findBiddersByProductId");
        namedQuery.setParameter("productId",productId);
        return namedQuery.getResultList();
    }

    @Transactional
    public Bidder findBidderWithMaxBid(Long productId){
        System.out.println("Find Bid with max bid is called");
        EntityManager em = entityManagerProvider.get();
        List<Bidder> winners  = em.createNamedQuery("Bidder.findMaxBid").setParameter("productId",productId).setMaxResults(1).getResultList();
        System.out.println("Winner Size for the Product Id "+ productId+ " is  "+ winners.size());
        if(winners.size()==0) {
            return null;
        }
        return winners.get(0);
    }

    @Transactional
    public Bidder updateCurrentBid(Long productId , String userId , Long currentBid){
        EntityManager em = entityManagerProvider.get();
        System.out.println(productId);
        System.out.println(userId);
        TypedQuery<Bidder> q = em.createQuery("select b from Bidder b where b.productId=:productId and b.userId=:userId", Bidder.class);
        List<Bidder> bidder = q.setParameter("productId",productId).setParameter("userId",userId).getResultList();
        if(bidder.size()!=0){
            bidder.get(0).setCurrentBid(currentBid);
            return bidder.get(0);
        }
        else{
            System.out.println("Bidder does not exist");
        }
        return null;
    }
    @Transactional
    public List<Product> getProducts(String userId){
        EntityManager em = entityManagerProvider.get();
        List<Product> ans = new ArrayList<>();
        System.out.println(userId);
        TypedQuery<Bidder> q = em.createQuery("select b from Bidder b where b.userId=:userId", Bidder.class);
        List<Bidder> bidders = q.setParameter("userId",userId).getResultList();
        if(bidders.size()!=0){
            for(Bidder b1 : bidders){
                Long productId = b1.getProductId();
                TypedQuery<Product> q1 = em.createQuery("select p from Product p where p.id=:productId", Product.class);
                List<Product> products = q1.setParameter("productId",productId).getResultList();
                if(products.size()!=0){
                    ans.add(products.get(0));
                }
            }

            return ans;
        }
        else{
            System.out.println("Bidder does not exist");
            return null;
        }
    }

    public Bidder getBidder(Long productId, String userId){
        EntityManager em = entityManagerProvider.get();
        System.out.println(productId);
        System.out.println(userId);
        Query namedQuery = em.createNamedQuery("Bidder.findBidderByUserIdAndProductId").setParameter("productId",productId).setParameter("userId",userId);
        List<Bidder> bidder = namedQuery.getResultList();
        if(bidder.size()!=0){
            return bidder.get(0);
        }
        else{
            return null;
        }
    }




}
