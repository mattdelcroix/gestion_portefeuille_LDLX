package DAO;
// Generated 19 mai 2020 23:06:09 by Hibernate Tools 4.3.1


import java.math.BigDecimal;

/**
 * DiscountCode generated by hbm2java
 */
public class old_DiscountCode  implements java.io.Serializable {


     private char discountCode;
     private BigDecimal rate;

    public old_DiscountCode() {
    }

	
    public old_DiscountCode(char discountCode) {
        this.discountCode = discountCode;
    }
    public old_DiscountCode(char discountCode, BigDecimal rate) {
       this.discountCode = discountCode;
       this.rate = rate;
    }
   
    public char getDiscountCode() {
        return this.discountCode;
    }
    
    public void setDiscountCode(char discountCode) {
        this.discountCode = discountCode;
    }
    public BigDecimal getRate() {
        return this.rate;
    }
    
    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }




}

