/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import DAO.*;
import static java.lang.Integer.parseInt;
import static java.lang.Short.parseShort;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import service.User;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import javax.servlet.http.*;
import javax.servlet.*;




/**
 *
 * @author Matthieu
 */
public class BddController extends MultiActionController {    
    private String login;
    private String pwd;
    private User utilisateur;
    private HttpSession session;
    public BddController() {}
    
    //Liste des colonnes de la table Customer
    static List <String> ColonnesClient = Arrays.asList("customerId", "name", "addressline1", "addressline2", "zip", "discountCode");
    //Liste des colonnes de la table Product
    static List <String> ColonnesProduit = Arrays.asList("productId", "manufacturerId", "productCode", "purchaseCost", "quantityOnHand", "markup", "available", "description");     
    //Liste des colonnes de la table PurchaseOrder
    static List <String> ColonnesVente = Arrays.asList("orderNum", "customerId", "productId", "quantity", "shippingCost", "salesDate", "shippingDate", "freightCompany"); 

   
    public ModelAndView  menu(HttpServletRequest request,
			HttpServletResponse response){
        
        login=request.getUserPrincipal().getName();
       
        utilisateur =new User(login);
        
        session=request.getSession();
        session.setAttribute("user", utilisateur);
        System.out.println ("nbuser="+User.getCompteur());
        return new ModelAndView("menu").addObject("user",utilisateur); 
        
    }
    
    public ModelAndView  logout(HttpServletRequest request,
			HttpServletResponse response){
        
        
        request.getSession().invalidate();
        
        
        return new ModelAndView("deconnexion"); 
        
    }
    
   public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
       
       //Cr??ation d'une liste "colonne" qui permettera d'indiquer les en-t??te des r??sultats des requ??tes
       List<String> column = new ArrayList();
       List results = null;
       
     switch(request.getParameter("operation"))
     {
        case "Liste Clients":            
           //Ajout des ??l??ments 'client' dans la liste 'column'
            column = ColonnesClient;  
            
            //R??cup??ration des clients via le DAO Helper et sa m??thode getClients()
            results = new MagasinHelper().getClients();
            break;            
          
	case "Liste Produits":
            //Ajout des ??l??ments 'client' dans la liste 'column'
            column = ColonnesProduit;  
            
            //R??cup??ration des clients via le DAO Helper et sa m??thode getClients()
            results = new MagasinHelper().getProduits();
            break;    
            
	case "Liste Ventes":
            //Ajout des ??l??ments 'client' dans la liste 'column'
            column = ColonnesVente;
            
            //R??cup??ration des clients via le DAO Helper et sa m??thode getClients()
            results = new MagasinHelper().getVentes();    
            break; 
      }       
       
       //R??cup??ration des clients via le DAO "MagasinHelper() et sa m??thode getClients().       
       //La variable typeListe va permettre d'afficher les bonnes donn??es dans les d??tails d'affichage.
       //Renvoi sur la vue "resultat.jsp".       
       return new ModelAndView("resultat").addObject("liste", results).addObject("colonnes", column).addObject("typeListe", request.getParameter("operation"));      
   }
   
 public ModelAndView add(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
       
     
       ModelAndView mv = new ModelAndView("form_inscription");
       
       mv.addObject("user",session.getAttribute("user"));
      mv.addObject("discount",new MagasinHelper().getDiscountCode());
      mv.addObject("code",new MagasinHelper().getZipCode());
      return mv;
   }
 
  public ModelAndView form(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
      
      List colonnes = new ArrayList();
      
      switch(request.getParameter("typeListe"))
     {
        case "Liste Clients":
            //R??cup??ration des colonnes de la table client.
            colonnes = ColonnesClient;  
            break;            
          
	case "Liste Produits":
            //R??cup??ration des colonnes de la table Produit.
            colonnes = ColonnesProduit;  
            break;              
            
	case "Liste Ventes":
            //R??cup??ration des colonnes de la table vente.
            colonnes = ColonnesVente;  
            break;                       
      }      
     
       ModelAndView mv = new ModelAndView("form");
       mv.addObject("colonnes", colonnes);
       mv.addObject("typeListe", request.getParameter("typeListe"));
       
      mv.addObject("user",session.getAttribute("user"));
      return mv;
   }
 
 public ModelAndView update(HttpServletRequest request,
			HttpServletResponse response) throws Exception {   
     
    // Gestion du cast BigDecimal
    DecimalFormatSymbols symbols = new DecimalFormatSymbols();
    symbols.setGroupingSeparator(',');
    symbols.setDecimalSeparator('.');
    String pattern = "#,##0.0#";
    DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
    decimalFormat.setParseBigDecimal(true);
    
    // Gestion du cast du format Date
    SimpleDateFormat formatter = new SimpleDateFormat("yyy-mm-dd", Locale.ENGLISH);
     
     switch(request.getParameter("typeListe"))
     {
        case "Liste Clients":
            //On v??rifie que les donn??es du formulaire sont pr??sent
            for(int i=0; i < ColonnesClient.size(); i++){
                if(request.getParameter(ColonnesClient.get(i)).equals("")) return new ModelAndView("error"); 
            }           
            
            //On r??cup??re l'identifiant de l'entr??e ?? supprimer et on l'envoie en parametre de la m??thode deleteCustomer.
            //Afin d'accroitre la s??curit??, nous pourrions envisager de d??velopper la m??thode deleteCustomer afin de controller tous les champs avant la suppression.
            //int _customerId, String _name, String _adress, String addressline2, String _discountCode, String _zip
            System.out.println("zip : " + request.getParameter("zip"));
            new MagasinHelper().updateCustomer(parseInt(request.getParameter("customerId")), request.getParameter("name"), request.getParameter("addressline1"), request.getParameter("addressline2"), request.getParameter("discountCode"), request.getParameter("zip"));             
           //, request.getParameter("discountCode").charAt(0)
            break;            
          
	case "Liste Produits":
            //On v??rifie que les donn??es du formulaire sont pr??sent
            for(int i=0; i < ColonnesProduit.size(); i++){
                if(request.getParameter(ColonnesProduit.get(i)).equals("")) return new ModelAndView("error"); 
            }     
            
            //On r??cup??re l'identifiant de l'entr??e ?? supprimer et on l'envoie en parametre de la m??thode updateProduct.
            //Afin d'accroitre la s??curit??, nous pourrions envisager de d??velopper la m??thode updateProduct afin de controller tous les champs avant la suppression.
            new MagasinHelper().updateProduct(parseInt(request.getParameter("productId")), parseInt(request.getParameter("manufacturerId")),request.getParameter("productCode"), (BigDecimal) decimalFormat.parse(request.getParameter("purchaseCost")), parseInt(request.getParameter("quantityOnHand")), (BigDecimal) decimalFormat.parse(request.getParameter("markup")), request.getParameter("available"), request.getParameter("description"));
            break;       
            
	case "Liste Ventes":
            //On v??rifie que les donn??es du formulaire sont pr??sent
            for(int i=0; i < ColonnesVente.size(); i++){
                if(request.getParameter(ColonnesVente.get(i)).equals("")) return new ModelAndView("error"); 
            }            
            
            //On r??cup??re l'identifiant de l'entr??e ?? supprimer et on l'envoie en parametre de la m??thode deleteCustomer.
            //Afin d'accroitre la s??curit??, nous pourrions envisager de d??velopper la m??thode deleteCustomer afin de controller tous les champs avant la suppression.
            new MagasinHelper().updatePurchaseOrder(parseInt(request.getParameter("orderNum")),parseInt(request.getParameter("customerId")),parseInt(request.getParameter("productId")),
                 parseShort(request.getParameter("quantity")),(BigDecimal) decimalFormat.parse(request.getParameter("shippingCost")),formatter.parse(request.getParameter("salesDate")),
                 formatter.parse(request.getParameter("shippingDate")),request.getParameter("freightCompany")); 
            break;             
      }       
       return new ModelAndView("confirm").addObject("confirm","enregistrement effectu??");
   
   }
 
  public ModelAndView delete(HttpServletRequest request,
			HttpServletResponse response) throws Exception {      
      
      switch(request.getParameter("typeListe"))
     {
        case "Liste Clients":
            //On v??rifie que les donn??es du formulaire sont pr??sent
            for(int i=0; i < ColonnesClient.size(); i++){
                if(request.getParameter(ColonnesClient.get(i)).equals("")) return new ModelAndView("error"); 
            }           
            
            //On r??cup??re l'identifiant de l'entr??e ?? supprimer et on l'envoie en parametre de la m??thode deleteCustomer.
            //Afin d'accroitre la s??curit??, nous pourrions envisager de d??velopper la m??thode deleteCustomer afin de controller tous les champs avant la suppression.
            new MagasinHelper().deleteCustomer(parseInt(request.getParameter("customerId")));             
            break;            
          
	case "Liste Produits":
            //On v??rifie que les donn??es du formulaire sont pr??sent
            for(int i=0; i < ColonnesProduit.size(); i++){
                if(request.getParameter(ColonnesProduit.get(i)).equals("")) return new ModelAndView("error"); 
            }           
            
            //On r??cup??re l'identifiant de l'entr??e ?? supprimer et on l'envoie en parametre de la m??thode deleteCustomer.
            //Afin d'accroitre la s??curit??, nous pourrions envisager de d??velopper la m??thode deleteCustomer afin de controller tous les champs avant la suppression.
            new MagasinHelper().deleteProduct(parseInt(request.getParameter("productId"))); 
            break;      
            
	case "Liste Ventes":
            //On v??rifie que les donn??es du formulaire sont pr??sent
            for(int i=0; i < ColonnesVente.size(); i++){
                if(request.getParameter(ColonnesVente.get(i)).equals("")) return new ModelAndView("error"); 
            }
            
            //On r??cup??re l'identifiant de l'entr??e ?? supprimer et on l'envoie en parametre de la m??thode deleteCustomer.
            //Afin d'accroitre la s??curit??, nous pourrions envisager de d??velopper la m??thode deleteCustomer afin de controller tous les champs avant la suppression.
            new MagasinHelper().deletePurchaseOrder(parseInt(request.getParameter("orderNum"))); 
            break;             
      }       
      
      response.sendRedirect("menu.htm"); //Redirection sur la page Menu. 
      
      return null;
   }
 
 public static String upperCaseFirst(String value) {
     //Cette fonction est ajout??e pour permettre le principe de l'hydratation. 
        // Convert String to char array.
        char[] array = value.toCharArray();
        // Modify first element in array.
        array[0] = Character.toUpperCase(array[0]);
        // Return string.
        return new String(array);
    }
 
 
 public ModelAndView detail(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
     //Gestion de l'affichage des d??tails pour les clients, les produits et les ventes.
     //Pour chaque type d'affichage on doit passer en parametre un objet "colonne" (reprennant toutes les colonnes de la table) et un objet "resultat" (reprenant l'entr??e selectionn??e)
     List<String> column = new ArrayList();     
     List<String> result = new ArrayList(); 
     
     
     ModelAndView mv = new ModelAndView("detail");
     
     switch(request.getParameter("operation"))
     {
        case "Liste Clients":            
           //Ajout des ??l??ments 'client' dans la liste 'column'
            column = ColonnesClient;  
            //R??cup??ration du client choisi via le DAO Helper et sa m??thode getClient()
            
            System.out.println("id" + request.getParameter("num"));
            List<Customer> clients = new MagasinHelper().getClient(parseInt(request.getParameter("num")));             
            Customer client = clients.get(0);            
            
            //Utilisation de la reflexion Java
            Class<Customer> customer = Customer.class;            
            
            for(int i=0; i < column.size(); i++){
                //Ici, nous utilisons le principe d'hidratation afin d'invoquer toutes les m??thodes de la classe Client.
                //Ce principe nous permet d'alleger le code et permet une forte maintenabilit??. 
                //En utilisant ce principe, il faudra faire attention que les noms des m??thodes correspondent bien ?? le nom de la colonne
                //(avec la premiere lettre en majuscule) pr??c??d??e de "get".
                //On utilisera le cast en String pour toutes les valeurs (integer compris)
                //On remarquera qu'avec cette m??thode, il est difficile de g??rer les int??grit??s r??f??rentielle.
                result.add(String.valueOf(customer.getMethod("get" + upperCaseFirst(column.get(i))).invoke(client)));                
            }
            
            //Ajout du r??sultat de la requ??te
            mv.addObject("resultat", new MagasinHelper().getClient(request.getParameter("num")));
            
            //Pour les clients, on affichera un champs sp??cial : code (getDiscountCode pour les clients)
            //mv.addObject("code",new MagasinHelper().getDiscountCode());
            break;            
          
	case "Liste Produits":
            //Ajout des ??l??ments 'produit' dans la liste 'column'
            column = ColonnesProduit;             
            //R??cup??ration du produit choisi via le DAO Helper et sa m??thode getProduit()
            Product produit = new MagasinHelper().getProduit(request.getParameter("num"));            
            //Utilisation de la reflexion Java
            Class<Product> product = Product.class;            
            
            for(int i=0; i < column.size(); i++){
                //Ici, nous utilisons le principe d'hidratation afin d'invoquer toutes les m??thodes de la classe Produit.
                //Ce principe nous permet d'alleger le code et permet une forte maintenabilit??. 
                //En utilisant ce principe, il faudra faire attention que les noms des m??thodes correspondent bien ?? le nom de la colonne (avec la premiere lettre en majuscule) pr??c??d??e de "get".
                //On utilisera le cast en String pour toutes les valeurs (integer compris)
                //On remarquera qu'avec cette m??thode, il est difficile de g??rer les int??grit??s r??f??rentielle.
                result.add(String.valueOf(product.getMethod("get" + upperCaseFirst(column.get(i))).invoke(produit)));                
            }
            break;
            
	case "Liste Ventes":
            //Ajout des ??l??ments 'vente' dans la liste 'column'
            column = ColonnesVente;            
            //R??cup??ration de la vente choisie via le DAO Helper et sa m??thode getVente()
            PurchaseOrder vente = new MagasinHelper().getVente(request.getParameter("num"));            
            //Utilisation de la reflexion Java
            Class<PurchaseOrder> purchaseOrder = PurchaseOrder.class;        
            
            for(int i=0; i < column.size(); i++){
                //Ici, nous utilisons le principe d'hidratation afin d'invoquer toutes les m??thodes de la classe Produit.
                //Ce principe nous permet d'alleger le code et permet une forte maintenabilit??. 
                //En utilisant ce principe, il faudra faire attention que les noms des m??thodes correspondent bien ?? le nom de la colonne (avec la premiere lettre en majuscule) pr??c??d??e de "get".
                //On utilisera le cast en String pour toutes les valeurs (integer compris)                
                //On remarquera qu'avec cette m??thode, il est difficile de g??rer les int??grit??s r??f??rentielle.
                result.add(String.valueOf(purchaseOrder.getMethod("get" + upperCaseFirst(column.get(i))).invoke(vente)));                
            }            
            break; 
      }       
       
     //mv.addObject("user",session.getAttribute("user"));
     
     //Ajout du type d'affichage ('Liste Clients', 'Liste Produits', 'Liste Ventes')
     mv.addObject("typeListe", request.getParameter("operation"));    
     //Ajout des colonnes de la table requ??t??e.
     mv.addObject("colonnes", column);     
     //Ajout du r??sultat de la requ??te
     mv.addObject("resultat", result);
     
     //Retourne l'instance de ModelAndView.
      return mv;
   }
 

 
 public ModelAndView find(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
     
       ModelAndView mv = new ModelAndView("resultat");
       MagasinHelper requeteur = new MagasinHelper(); 
       List resultat = new ArrayList();
       
       //V??rification qu'au moins un champs du formulaire soit rempli
       if(request.getParameter("id").equals("") && request.getParameter("nom").equals("")) return new ModelAndView("error"); 
       
       //Si l'id est renseign?? et qu'une entr??e est trouv??e en bdd, alors on renvoie cet entr??e ?? la vue "resultat"
       if(!request.getParameter("id").equals("")) {
           
       System.out.println("id");
          /* if(requeteur.getClients(parseInt(request.getParameter("id"))).isEmpty()){
               //Si la requ??te ne renvoie pas de r??sultat, on affiche la page erreur :
               mv= new ModelAndView("error");         
               mv.addObject("erreur", "0 enregistrement");
               return mv;               
           } else {     */          
               //Si la requ??te renvoie un r??sultat, on envoie le r??sulat sur la vue "resultat"
               //M??me si il n'y a qu'un seul r??sultat, on ajoute ce dernier dans une liste afin de limiter les traitements sur la vue.
               resultat = requeteur.getClients(parseInt(request.getParameter("id")));          
           //}                   
        } else if(!request.getParameter("nom").equals("")) {
            
       System.out.println("nom");
            /*if(requeteur.getClients(request.getParameter("nom")).isEmpty()){
               //Si la requ??te ne renvoie pas de r??sultat, on affiche la page erreur :
               mv= new ModelAndView("error");         
               mv.addObject("erreur", "0 enregistrement");
               return mv;               
           } else {*/
               //Si la requ??te renvoie un r??sultat, on envoie le r??sulat sur la vue "resultat"
               //R??cup??ration des clients qui on le nom saisi, puis on les ajoute dans la liste afin d'y ??tre trait??s
               resultat = requeteur.getClients((String) request.getParameter("nom"));            
           //}
            
        }
       
        mv.addObject("typeListe", "Liste Clients");
        mv.addObject("colonnes", ColonnesClient);
        mv.addObject("liste", resultat);  
        mv.addObject("user",session.getAttribute("user"));
        return mv;
   }
 public ModelAndView formfind(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
      return new ModelAndView("recherche");
   }
 

 public ModelAndView achats(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
     
       MagasinHelper requeteur = new MagasinHelper();        
       List resultat = new ArrayList();
     
     //V??rification de l'id client est bien fournit
     if(request.getParameter("customerId").equals("")) return new ModelAndView("error");
     
     //R??cup??ration des achats du client
     resultat = requeteur.getVentesClient(parseInt(request.getParameter("customerId")));
          
     ModelAndView mv = new ModelAndView("resultat");     
     
     mv.addObject("typeListe", "Liste Ventes");
     mv.addObject("colonnes", ColonnesVente);
     mv.addObject("liste", resultat);  
     mv.addObject("user",session.getAttribute("user"));
     
     return mv;
   }
   
    public ModelAndView save(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
       // Gestion du cast BigDecimal
    DecimalFormatSymbols symbols = new DecimalFormatSymbols();
    symbols.setGroupingSeparator(',');
    symbols.setDecimalSeparator('.');
    String pattern = "#,##0.0#";
    DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
    decimalFormat.setParseBigDecimal(true);
    
    // Gestion du cast du format Date
    SimpleDateFormat formatter = new SimpleDateFormat("yyy-mm-dd", Locale.ENGLISH);
     
     switch(request.getParameter("typeListe"))
     {
        case "Liste Clients":
            //On v??rifie que les donn??es du formulaire sont pr??sent
            for(int i=0; i < ColonnesClient.size(); i++){
                if(request.getParameter(ColonnesClient.get(i)).equals("")) return new ModelAndView("error"); 
            }           
            
            //On r??cup??re l'identifiant de l'entr??e ?? supprimer et on l'envoie en parametre de la m??thode deleteCustomer.
            //Afin d'accroitre la s??curit??, nous pourrions envisager de d??velopper la m??thode deleteCustomer afin de controller tous les champs avant la suppression.
            new MagasinHelper().insertCustomer(parseInt(request.getParameter("customerId")), request.getParameter("name"), request.getParameter("addressline1"), request.getParameter("addressline2"), request.getParameter("discountCode"), request.getParameter("zip"));             
           //, request.getParameter("discountCode").charAt(0)
            break;            
          
	case "Liste Produits":
            //On v??rifie que les donn??es du formulaire sont pr??sent
            for(int i=0; i < ColonnesProduit.size(); i++){
                if(request.getParameter(ColonnesProduit.get(i)).equals("")) return new ModelAndView("error"); 
            }     
            
            //On r??cup??re l'identifiant de l'entr??e ?? supprimer et on l'envoie en parametre de la m??thode updateProduct.
            //Afin d'accroitre la s??curit??, nous pourrions envisager de d??velopper la m??thode updateProduct afin de controller tous les champs avant la suppression.
            new MagasinHelper().insertProduct(parseInt(request.getParameter("productId")), parseInt(request.getParameter("manufacturerId")),request.getParameter("productCode"), (BigDecimal) decimalFormat.parse(request.getParameter("purchaseCost")), parseInt(request.getParameter("quantityOnHand")), (BigDecimal) decimalFormat.parse(request.getParameter("markup")), request.getParameter("available"), request.getParameter("description"));
            break;       
            
	case "Liste Ventes":
            //On v??rifie que les donn??es du formulaire sont pr??sent
            for(int i=0; i < ColonnesVente.size(); i++){
                if(request.getParameter(ColonnesVente.get(i)).equals("")) return new ModelAndView("error"); 
            }            
            
            //On r??cup??re l'identifiant de l'entr??e ?? supprimer et on l'envoie en parametre de la m??thode deleteCustomer.
            //Afin d'accroitre la s??curit??, nous pourrions envisager de d??velopper la m??thode deleteCustomer afin de controller tous les champs avant la suppression.
            new MagasinHelper().insertPurchaseOrder(parseInt(request.getParameter("orderNum")),parseInt(request.getParameter("customerId")),parseInt(request.getParameter("productId")),
                 parseShort(request.getParameter("quantity")),(BigDecimal) decimalFormat.parse(request.getParameter("shippingCost")),formatter.parse(request.getParameter("salesDate")),
                 formatter.parse(request.getParameter("shippingDate")),request.getParameter("freightCompany")); 
            break;           
      }       
       return new ModelAndView("confirm").addObject("confirm","enregistrement effectu??");
   
   }
      
}
	
    

