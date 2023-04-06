package models;

import javax.persistence.*;

@Entity
@Table(schema = "public")
@NamedQueries({
        @NamedQuery(name = "Bidder.countBidderByUserId",query = "Select count(b) from Bidder b where b.userId=:userId"),
        @NamedQuery(name = "Bidder.BiddersByUserId",query = "Select b from Bidder b where b.userId=:userId"),
        @NamedQuery(name = "Bidder.findBiddersByProductId",query="SELECT b from Bidder b where b.productId=:productId order by b.currentBid DESC "),
//        @NamedQuery(name="Bidder.findBidderWithMaxBid",query = "select b from Bidder b where b.currentBid = (select max(currentBid) from Bidder)"),
//        @NamedQuery(name = "Bidder.changeBidder'sCurrentBid",query = "update Bidder set ")
        @NamedQuery(name="Bidder.findBidderByUserIdAndProductId",query = "select b from Bidder b where b.userId=:userId and b.productId=:productId"),
        @NamedQuery(name="Bidder.findMaxBid",query="select b from Bidder b where b.productId=:productId order by b.currentBid DESC")
})
public class Bidder{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String userId;

    private Long currentBid;

    private Long productId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getCurrentBid() {
        return currentBid;
    }

    public void setCurrentBid(Long currentBid) {
        this.currentBid = currentBid;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
