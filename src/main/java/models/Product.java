package models;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(schema = "public")
@NamedQueries({
        @NamedQuery(name="ProductEntity.GetByProductNotInAuction", query = "SELECT p from Product p where p.inAuction=false"),
        @NamedQuery(name="ProductEntity.CountByProductNotInAuction", query = "SELECT count(p) from Product p where p.inAuction=false"),
        @NamedQuery(name = "Product.WinCountByUserId",query = "select count(pw) from Product pw where pw.winner=:userId"),
        @NamedQuery(name = "Product.LostCountByUserId",query = "select count(pw) from Product pw where pw.winner!=:userId"),
        @NamedQuery(name="ProductEntity.GetByProductInAuction", query = "SELECT p from Product p where p.inAuction=true"),
        @NamedQuery(name="ProductEntity.GetByProductId", query = "SELECT p from Product p where p.id=:id"),
        @NamedQuery(name = "Product.getAllProductsByEmail",query = "select p from Product p where p.owner=:owner")
}
)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;

    private String winner;
    private String owner;
    private boolean inAuction;
    private Long startTime;
    private Long endTime;

    private Long price;

    private boolean isActive;


    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public boolean isInAuction() {
        return inAuction;
    }

    public void setInAuction(boolean inAuction) {
        this.inAuction = inAuction;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }
}
