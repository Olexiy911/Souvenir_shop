package souvenir_shop;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Date date;

    private Double amount;

    private String currency;

    private Double baseamount;

    private String basecarrency;

    private String productName;

    public Purchase() {};

    public Purchase(String basecarrency, Double baseamount) {
        this.basecarrency = basecarrency;
        this.baseamount = baseamount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getBaseamount() {
        return baseamount;
    }

    public void setBaseamount(Double baseamount) {
        this.baseamount = baseamount;
    }

    public String getBasecarrency() {
        return basecarrency;
    }

    public void setBasecarrency(String basecarrency) {
        this.basecarrency = basecarrency;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
