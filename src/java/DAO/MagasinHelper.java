/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import java.util.*;
import java.sql.*;
import java.math.BigDecimal;

/**
 *
 * @author Matthieu
 */
public class MagasinHelper {
    Session session = null;

    public List<String> getColonnes() {
        return colonnes;
    }
    ArrayList <String> colonnes=new ArrayList();

    public MagasinHelper() {
        this.session = HibernateUtil.getSessionFactory().openSession();
    }
    
    public List getClients(){
        List <Customer> resultat=null;
        Transaction tx=null;
        try{
            if(!session.isOpen())session=HibernateUtil.getSessionFactory().openSession();
            session.flush();
            
             tx=session.beginTransaction();
           Query q=session.createQuery("select customerId, name, addressline1,addressline2,zip, discountCode  from Customer");
            //Query q=session.createQuery("from Customer");
            resultat=q.list();
            
            
        }
        catch (Exception e) {
        e.printStackTrace();
        }
       finally{
          if (session.isOpen())session.close();
        }
      
    return resultat;
}
    
    
    public List getClients(String name){
        List <Customer> resultat=null;
        Transaction tx=null;
        try{
            if(!session.isOpen())session=HibernateUtil.getSessionFactory().openSession();
            session.flush();
            
             tx=session.beginTransaction();
           //Query q=session.createQuery("select a.customerId, a.name, a.addressline1,a.addressline2,a.zip,b.rate from Customer a, DiscountCode b where a.discountCode=b.discountCode and a.name like :_name");
           Query q=session.createQuery("select customerId, name, addressline1,addressline2,zip, discountCode from Customer where name like :nom");
            //Query q=session.createQuery("from Customer");
            //q.setString("name", name);
            q.setString("nom", "%" + name + "%");
            resultat=q.list();
        }
        catch (Exception e) {
        e.printStackTrace();
        }
       finally{
          if (session.isOpen())session.close();
        }
      
    return resultat;
}
    
    public List getClients(int id){
        List <Customer> resultat=null;
        Transaction tx=null;
        try{
            if(!session.isOpen())session=HibernateUtil.getSessionFactory().openSession();
            session.flush();
            
             tx=session.beginTransaction();
           Query q=session.createQuery("select customerId, name, addressline1, addressline2, zip, discountCode from Customer where customerId =:id");
            //Query q=session.createQuery("from Customer"); 
            q.setInteger("id", id);
            resultat=q.list();
        }
        catch (Exception e) {
        e.printStackTrace();
        }
       finally{
          if (session.isOpen())session.close();
        }
      
    return resultat;
}
    
public List getProduits(){
        List <Product> resultat=null;
        Transaction tx=null;
        try{
            if(!session.isOpen())session=HibernateUtil.getSessionFactory().openSession();
            session.flush();
            
             tx=session.beginTransaction();
             
           Query q=session.createQuery("select productId, manufacturerId, productCode, purchaseCost, quantityOnHand, markup, available, description from Product");
            //Query q=session.createQuery("from Customer");
            resultat=q.list();
        }
        catch (Exception e) {
        e.printStackTrace();
        }
       finally{
          if (session.isOpen())session.close();
        }
      
    return resultat;
}
    
    public List getVentes(){
        List <Product> resultat=null;
        Transaction tx=null;
        try{
            if(!session.isOpen())session=HibernateUtil.getSessionFactory().openSession();
            session.flush();
            
             tx=session.beginTransaction();   
                                  
           Query q=session.createQuery("select orderNum, customerId, productId, quantity, shippingCost, salesDate, shippingDate, freightCompany from PurchaseOrder");
            //Query q=session.createQuery("from Customer");
            resultat=q.list();
        }
        catch (Exception e) {
        e.printStackTrace();
        }
       finally{
          if (session.isOpen())session.close();
        }
      
    return resultat;
}
    
    public List getVentesClient(int customerId){
        //Cette fonction récupère les ventes d'un client donné.
        List <Product> resultat=null;
        Transaction tx=null;
        try{
            if(!session.isOpen())session=HibernateUtil.getSessionFactory().openSession();
            session.flush();
            
             tx=session.beginTransaction();   
                                  
           Query q=session.createQuery("select orderNum, customerId, productId, quantity, shippingCost, salesDate, shippingDate, freightCompany from PurchaseOrder where customerId = :customerId");
            //Query q=session.createQuery("from Customer");            
           q.setInteger("customerId", customerId);
            resultat=q.list();
        }
        catch (Exception e) {
        e.printStackTrace();
        }
       finally{
          if (session.isOpen())session.close();
        }
      
    return resultat;
}

public List getDiscountCode(){
List resultat=null;
        Transaction tx=null;
        try{
           if(!session.isOpen())
                session=HibernateUtil.getSessionFactory().openSession();
            session.flush();
            
             tx=session.beginTransaction();
            Query q=session.createQuery("select a.discountCode from DiscountCode a");
            resultat=q.list();
        }
        catch (Exception e) {
        e.printStackTrace();
        }
       finally{
           if (session.isOpen())session.close();
          
        }
      
    return resultat;


}
public List getMicroMarket(){
List <MicroMarket> resultat=null;
        Transaction tx=null;
        try{
            //if(!session.isOpen())
                session=HibernateUtil.getSessionFactory().openSession();
            session.flush();
            
             tx=session.beginTransaction();
            Query q=session.createQuery("from MicroMarket");
            resultat=q.list();
        }
        catch (Exception e) {
        e.printStackTrace();
        }
       finally{
           if (session.isOpen())session.close(); 
        
        }
        return resultat;
}
public List getZipCode(){
List resultat=null;
        Transaction tx=null;
        try{
            //if(!session.isOpen())
                session=HibernateUtil.getSessionFactory().openSession();
            session.flush();
            
             tx=session.beginTransaction();
            Query q=session.createQuery("select a.zipCode from MicroMarket a");
            resultat=q.list();
        }
        catch (Exception e) {
        e.printStackTrace();
        }
       finally{
           if (session.isOpen())session.close(); 
        
        }
        return resultat;
}
/*public void insertCustomer  (int _customerId, char _discountCode, String _zip) {
    
    Transaction tx=null;
        try{
            if(!session.isOpen())session=HibernateUtil.getSessionFactory().openSession();
            session.flush();
            
             tx=session.beginTransaction();
             Customer a =new Customer(_customerId,_discountCode,_zip);
             session.save(a);
             tx.commit();
        }
        catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            throw e;
        }
      finally{
           if (session.isOpen())session.close();
        }
    
}*/


/*public void updateCustomer  (int _customerId, String _name, String _adress, String _addressline2, String _zip) {
    
    Transaction tx=null;
        try{
            if(!session.isOpen())session=HibernateUtil.getSessionFactory().openSession();
            session.flush();
            
             tx=session.beginTransaction();
             Customer a =new Customer(_customerId, _name, _adress, _addressline2,_zip);
             session.update(a);
             tx.commit();
        }
        catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            throw e;
        }
      finally{
           if (session.isOpen())session.close();
        }
    
}*/

public void insertCustomer (int _customerId, String _name, String _adress, String address2, String _discountCode, String _zip) {
    
    Transaction tx=null;
        try{
            if(!session.isOpen())session=HibernateUtil.getSessionFactory().openSession();
            session.flush();
            
             tx=session.beginTransaction();
             Customer a =new Customer(_customerId,_name, _adress, address2, _discountCode, _zip);
             System.out.println(a.getZip());
             session.save(a);
             tx.commit();
        }
        catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            throw e;
        }
      finally{
           if (session.isOpen())session.close();
        }
    
}

public void updateCustomer (int _customerId, String _name, String _adress, String address2, String _discountCode, String _zip) {
    
    Transaction tx=null;
        try{
            if(!session.isOpen())session=HibernateUtil.getSessionFactory().openSession();
            session.flush();
            
             tx=session.beginTransaction();
             Customer a =new Customer(_customerId,_name, _adress, address2, _discountCode, _zip);
             System.out.println(a.getZip());
             session.update(a);
             tx.commit();
        }
        catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            throw e;
        }
      finally{
           if (session.isOpen())session.close();
        }
    
}


public void insertProduct (int productId, int manufacturerId, String productCode, BigDecimal purchaseCost, Integer quantityOnHand, BigDecimal markup, String available, String description) {
    
    Transaction tx=null;
        try{
            if(!session.isOpen())session=HibernateUtil.getSessionFactory().openSession();
            session.flush();
            
             tx=session.beginTransaction();
             Product a =new Product(productId, manufacturerId, productCode, purchaseCost, quantityOnHand, markup, available, description);
             session.save(a);
             tx.commit();
        }
        catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            throw e;
        }
      finally{
           if (session.isOpen())session.close();
        }
    
}


public void updateProduct (int productId, int manufacturerId, String productCode, BigDecimal purchaseCost, Integer quantityOnHand, BigDecimal markup, String available, String description) {
    
    Transaction tx=null;
        try{
            if(!session.isOpen())session=HibernateUtil.getSessionFactory().openSession();
            session.flush();
            
             tx=session.beginTransaction();
             Product a =new Product(productId, manufacturerId, productCode, purchaseCost, quantityOnHand, markup, available, description);
             session.update(a);
             tx.commit();
        }
        catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            throw e;
        }
      finally{
           if (session.isOpen())session.close();
        }
    
}


public void insertPurchaseOrder(int orderNum, int customerId, int productId, Short quantity, BigDecimal shippingCost, java.util.Date salesDate, java.util.Date shippingDate, String freightCompany) {
    
    Transaction tx=null;
        try{
            if(!session.isOpen())session=HibernateUtil.getSessionFactory().openSession();
            session.flush();
            
             tx=session.beginTransaction();
             PurchaseOrder a =new PurchaseOrder(orderNum, customerId, productId, quantity, shippingCost, salesDate, shippingDate, freightCompany);
             session.save(a);
             tx.commit();
        }
        catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            throw e;
        }
      finally{
           if (session.isOpen())session.close();
        }
    
}

    
public void updatePurchaseOrder(int orderNum, int customerId, int productId, Short quantity, BigDecimal shippingCost, java.util.Date salesDate, java.util.Date shippingDate, String freightCompany) {
    
    Transaction tx=null;
        try{
            if(!session.isOpen())session=HibernateUtil.getSessionFactory().openSession();
            session.flush();
            
             tx=session.beginTransaction();
             PurchaseOrder a =new PurchaseOrder(orderNum, customerId, productId, quantity, shippingCost, salesDate, shippingDate, freightCompany);
             session.update(a);
             tx.commit();
        }
        catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            throw e;
        }
      finally{
           if (session.isOpen())session.close();
        }
    
}









public List getClient(int id){   
   
   List<Customer> client=null;
    Transaction tx=null;
        try{
            if(!session.isOpen())session=HibernateUtil.getSessionFactory().openSession();
            session.flush();
            tx=session.beginTransaction();
           Query q=session.createQuery("from Customer where customerId = :_id"); //select customerId, name, addressline1, addressline2, zip
           q.setInteger("_id", id);
           System.out.println(q.list().size());
           client = q.list();//.iterator().next();
      }
       catch (Exception e) {
        e.printStackTrace();
        }
       finally{
          if (session.isOpen())session.close();
        }
      
    return client;
}











public Customer getClient(String name){  
   
   Customer client=null;
    Transaction tx=null;
        try{
            if(!session.isOpen())session=HibernateUtil.getSessionFactory().openSession();
            session.flush();
            tx=session.beginTransaction();
           Query q=session.createQuery("from Customer where a.name =:_name"); //select customerId, name, addressline1,addressline2,zip 
           q.setString("_name", name);
           if(q.list().size() > 0) client=(Customer)q.list().iterator().next();
      }
       catch (Exception e) {
        e.printStackTrace();
        }
       finally{
          if (session.isOpen())session.close();
        }
      
    return client;
}

public Product getProduit(int id){
    //Création de la méthode getProduit(int id) qui va retourner l'entrée correspondante de la bdd.  
   
   Product produit=null;
    Transaction tx=null;
        try{
            if(!session.isOpen())session=HibernateUtil.getSessionFactory().openSession();
            session.flush();
            tx=session.beginTransaction();
           Query q=session.createQuery(" from Product a  where a.productId =:_id");
           q.setInteger("_id", id);
           produit=(Product)q.list().iterator().next();
      }
       catch (Exception e) {
        System.out.println("erreur toto"+e);
        e.printStackTrace();
        }
       finally{
          if (session.isOpen())session.close();
        }
      
    return produit;
}

public Product getProduit(String id){
    //Création de la méthode getProduit(int id) qui va retourner l'entrée correspondante de la bdd.  
   
   Product produit=null;
    Transaction tx=null;
        try{
            if(!session.isOpen())session=HibernateUtil.getSessionFactory().openSession();
            session.flush();
            tx=session.beginTransaction();
           Query q=session.createQuery(" from Product a  where a.productId =:_id");
           q.setString("_id", id);
           produit=(Product)q.list().iterator().next();
      }
       catch (Exception e) {
        System.out.println("erreur toto"+e);
        e.printStackTrace();
        }
       finally{
          if (session.isOpen())session.close();
        }
      
    return produit;
}

public PurchaseOrder getVente(int id){
    //Création de la méthode getProduit(int id) qui va retourner l'entrée correspondante de la bdd.  
   
   PurchaseOrder vente=null;
    Transaction tx=null;
        try{
            if(!session.isOpen())session=HibernateUtil.getSessionFactory().openSession();
            session.flush();
            tx=session.beginTransaction();
           Query q=session.createQuery(" from PurchaseOrder a  where a.orderNum =:_id");
           q.setInteger("_id", id);
           vente=(PurchaseOrder)q.list().iterator().next();
      }
       catch (Exception e) {
        System.out.println("erreur toto"+e);
        e.printStackTrace();
        }
       finally{
          if (session.isOpen())session.close();
        }
      
    return vente;
}

public PurchaseOrder getVente(String id){
    //Création de la méthode getProduit(int id) qui va retourner l'entrée correspondante de la bdd.  
   
   PurchaseOrder vente=null;
    Transaction tx=null;
        try{
            if(!session.isOpen())session=HibernateUtil.getSessionFactory().openSession();
            session.flush();
            tx=session.beginTransaction();
           Query q=session.createQuery(" from PurchaseOrder a  where a.orderNum =:_id");
           q.setString("_id", id);
           vente=(PurchaseOrder)q.list().iterator().next();
      }
       catch (Exception e) {
        System.out.println("erreur toto"+e);
        e.printStackTrace();
        }
       finally{
          if (session.isOpen())session.close();
        }
      
    return vente;
}



public void deleteCustomer (int _id) {
    
    Transaction tx=null;
        try{
            //Suppression de toutes les ventes concernant ce client avant suppression du produit.
            deleteCustomerPurchaseOrder(_id);
            
            if(!session.isOpen())session=HibernateUtil.getSessionFactory().openSession();
            session.flush();
            
             tx=session.beginTransaction();
             Query q=session.createQuery(" from Customer a  where a.customerId =:_id");
             q.setInteger("_id",_id);
             session.delete((Customer)q.list().iterator().next());
             tx.commit();
        }
        catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            throw e;
        }
      finally{
           if (session.isOpen())session.close();
        }
    
}

public void deleteProduct (int _id) {    
    Transaction tx=null;
        try{
            //Suppression de toutes les ventes concernant ce produit avant suppression du produit.
            deleteProductPurchaseOrder(_id);
            
            if(!session.isOpen())session=HibernateUtil.getSessionFactory().openSession();
            session.flush();
            
             tx=session.beginTransaction();
             Query q=session.createQuery(" from Product a  where a.productId =:_id");
             q.setInteger("_id",_id);
             session.delete((Product)q.list().iterator().next());
             tx.commit();
        }
        catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            throw e;
        }
      finally{
           if (session.isOpen())session.close();
        }
    
}

public void deleteCustomerPurchaseOrder (int _id) {    
    Transaction tx=null;
        try{
            if(!session.isOpen())session=HibernateUtil.getSessionFactory().openSession();
            session.flush();
            
             tx=session.beginTransaction();
             Query q=session.createQuery(" from PurchaseOrder a  where a.customerId =:_id");
             q.setInteger("_id",_id);
             session.delete((PurchaseOrder)q.list().iterator().next());
             tx.commit();
        }
        catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            throw e;
        }
      finally{
           if (session.isOpen())session.close();
        }
    
}

public void deleteProductPurchaseOrder (int _id) {    
    Transaction tx=null;
        try{
            if(!session.isOpen())session=HibernateUtil.getSessionFactory().openSession();
            session.flush();
            
             tx=session.beginTransaction();
             Query q=session.createQuery(" from PurchaseOrder a  where a.productId =:_id");
             q.setInteger("_id",_id);
             if(q.list().size() > 0) session.delete((PurchaseOrder)q.list().iterator().next());
             tx.commit();
        }
        catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            throw e;
        }
      finally{
           if (session.isOpen())session.close();
        }
    
}

public void deletePurchaseOrder (int _id) {    
    Transaction tx=null;
        try{
            if(!session.isOpen())session=HibernateUtil.getSessionFactory().openSession();
            session.flush();
            
             tx=session.beginTransaction();
             Query q=session.createQuery(" from PurchaseOrder a  where a.orderNum =:_id");
             q.setInteger("_id",_id);
             session.delete((PurchaseOrder)q.list().iterator().next());
             tx.commit();
        }
        catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            throw e;
        }
      finally{
           if (session.isOpen())session.close();
        }
    
}


 public List<PurchaseOrder> getAchats(int id){
        List resultat=null;
        Transaction tx=null;
        try{
            if(!session.isOpen())session=HibernateUtil.getSessionFactory().openSession();
            session.flush();
            
             tx=session.beginTransaction();
           Query q=session.createQuery(" from PurchaseOrder  a where a.customerId=:_id");
           q.setInteger("_id", id);
            resultat=(List<PurchaseOrder>)q.list();
        }
        catch (Exception e) {
        e.printStackTrace();
        }
       finally{
          if (session.isOpen())session.close();
        }
      
    return resultat;
}


}
