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

    @Transactional
    public List<Bidder> findBidderByProductId(Long productId){
        EntityManager em = entityManagerProvider.get();
        Query namedQuery = em.createNamedQuery("Bidder.findBiddersByProductId");
        namedQuery.setParameter("productId",productId);
        return namedQuery.getResultList();
    }

    @Transactional
    public Bidder findBidderWithMaxBid(){
        EntityManager em = entityManagerProvider.get();
        Query namedQuery = em.createNamedQuery("Bidder.findBidderWithMaxBid");
        List<Bidder> winners = namedQuery.getResultList();
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




}
