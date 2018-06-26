package proj4110;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


/**
 *
 * @author Paul Finnegan
 */
public class VendingMachineController implements Initializable {
   
/**
 * The data attributes required to 
 * specify the state of the vending machine.
 * 
 * For simplicity, there is no attribute for an
 * initial cash balance for dispensing change, 
 * this amount is often referred to as a "float".
 */    

    
    private boolean juice; //is the machine on or off?
    private int pepsiStock; //the max number of pepsis held by the machine
    private int spriteStock; //the number of sprites held by the machine
    private double changeInserted; 
    private double changeInSlot; //change dispensed
    private double pepsiPrice; 
    private double spritePrice;
    private int pepsiInventory; // the number of pepsis currently in the machine
    private int spriteInventory; // the number of sprites currently in the machine
    private int drinksInSlot; //total number of drinks (pepsi and sprite) dispensed
    
    //This object formats dollars and cents for display in the GUI.
    DecimalFormat df2 = new DecimalFormat("0.00");
    
    /**
     *  The controls for the GUI. 
     *  Much of this code is auto-generated in JavaFX Scene Builder
     */
    
    @FXML
    private Button power;
    @FXML
    private TextField status;
    @FXML
    private Button fiveCents;
    @FXML
    private Button tenCents;
    @FXML
    private Button cents25;
    @FXML
    private TextField moneyInserted;
    @FXML
    private TextField changeAmount;
    @FXML
    private Button takeChange;
    @FXML
    private Button cancel;
    @FXML
    private Button pepsi;
    @FXML
    private TextField slotCans;
    @FXML
    private Button takeCan;
    @FXML
    private Button sprite;
    @FXML
    private TextField pepsiStockDisplay;
    @FXML
    private TextField spriteStockDisplay;
    @FXML
    private Button restock;
    @FXML
    private Label pinLabel;
    @FXML
    private TextField pinField;
    @FXML
    private Button okPin;
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    //initial values for system attributes.
    juice = false;
    pepsiStock = 5;
    spriteStock = 7;
    changeInserted = 0.0;
    changeInSlot = 0.0;
    pepsiPrice = 0.75;
    spritePrice = 0.80;
    pepsiInventory = 5;
    spriteInventory = 7;
    drinksInSlot = 0 ;
    
    df2 = new DecimalFormat("0.00");
        
        power.setOnAction((event) -> {
            
            if(juice == false){
                status.setText("ON");
                juice = true;
                pepsiStockDisplay.setText(String.valueOf(pepsiInventory));
                spriteStockDisplay.setText(String.valueOf(spriteInventory));
            } else {
                
                status.setText("OFF");
                juice = false;
                changeInSlot = changeInserted  + changeInSlot;
                changeAmount.setText(df2.format(changeInSlot));
                moneyInserted.setText("0.00");
                changeInserted=0;
                pepsiStockDisplay.setText("");
                spriteStockDisplay.setText("");
                pinLabel.setVisible(false);
                pinField.setVisible(false);
                okPin.setVisible(false);
                
                
            }
            status.setStyle("-fx-base: #ffffff;");
            
        });
        
        restock.setOnAction((event) -> {
            
            if(juice == false){ 
                status.setText("Power OFF: Turn On"); 
                status.setStyle("-fx-base: #A32BC9;"); 
            } else { 
                pinLabel.setVisible(true); 
                pinField.setVisible(true);
                okPin.setVisible(true); 
            }
        });
        
        okPin.setOnAction((event) -> {
            
            if("1111".equals(pinField.getText())){
                status.setText("You have been replenished!"); 
                pepsiStock = 5; 
                pepsiInventory = pepsiStock;
                pepsiStockDisplay.setText(String.valueOf(pepsiStock)); 
                
                spriteStock = 7; 
                spriteInventory = spriteStock;
                spriteStockDisplay.setText(String.valueOf(spriteStock)); 
            } else { 
                status.setText("That pin is incorrect"); 
            }
        });
        
        fiveCents.setOnAction((event) -> {
                  
             if(juice == false){
                
                status.setText("OFF : TURN IT ON!");
                status.setStyle("-fx-base: #A32BC9;");
            } else {
                
                changeInserted += 0.05;
                moneyInserted.setText(String.valueOf(df2.format(changeInserted)));
                status.setText("COLLECTING MONEY");
                status.setStyle("-fx-base: #ffffff;");
             }
        });
        
        tenCents.setOnAction((event) -> {
                
             if(juice == false){
                
                status.setText("OFF : TURN IT ON!");
                status.setStyle("-fx-base: #A32BC9;");
            } else {
                
                changeInserted += 0.10;
                moneyInserted.setText(String.valueOf(df2.format(changeInserted)));
                status.setText("COLLECTING MONEY");
                status.setStyle("-fx-base: #ffffff;");
             }
        });
        
        cents25.setOnAction((event) -> {
            
             if(juice == false){
                
                status.setText("OFF : TURN IT ON!");
                status.setStyle("-fx-base: #A32BC9;");
            } else {
                
                changeInserted += 0.25;
                moneyInserted.setText(String.valueOf(df2.format(changeInserted)));
                status.setText("COLLECTING MONEY");
                status.setStyle("-fx-base: #ffffff;");
            }
            
            
        });
        
        takeChange.setOnAction((event) -> {
            
            if(juice == false){ 
                status.setText("Power OFF: Turn On to Take Change");
                status.setStyle("-fx-base: #A32BC9;");
            } else { 
                changeAmount.setText(String.valueOf(df2.format(changeInSlot - changeInSlot)));
                moneyInserted.setText("0.00");
                status.setText("Thank you for your purchase!");
            }
        });
        
        takeCan.setOnAction((event) -> {
            if (juice == false){ 
                status.setText("Power OFF: Turn On");
                status.setStyle("-fx-base: #A32BC9;"); 
            } else if (drinksInSlot > 0){ 
                status.setText("Thank you for taking your drink!");
                drinksInSlot = 0; 
                slotCans.setText(String.valueOf(drinksInSlot)); 
            }
            
        });
        
        
        cancel.setOnAction((event) -> {
            
            if(juice == false ){
                return;
            } else if (changeInserted == 0) {    
                
                return;
                
            } else {
                           
                status.setText("TRANSACTION CANCELLED: TAKE YOUR CHANGE.");
                changeInSlot = changeInserted  + changeInSlot;
                changeAmount.setText(String.valueOf(df2.format(changeInSlot)));
                moneyInserted.setText("0.00");
                changeInserted=0;
                
                
            }
            status.setStyle("-fx-base: #ffffff;");
            
        });
        
        pepsi.setOnAction((ActionEvent event) -> {
         
            if (juice == false){ 
                   status.setText("Power OFF: Turn On");
                status.setStyle("-fx-base: #A32BC9;");
            } else if (changeInserted >= pepsiPrice && pepsiStock >= 0){
               while(pepsiStock >= 0){
                    status.setText("You took a Pespi can");
                    pepsiInventory = pepsiInventory-1;
                    pepsiStockDisplay.setText(String.valueOf(pepsiInventory));
                    pepsiStock = pepsiInventory-1;
                    drinksInSlot+=1;
                       
                    changeInserted =  changeInserted - pepsiPrice;
                    changeAmount.setText(String.valueOf(df2.format(pepsiStock -1)));
                    changeAmount.setText(String.valueOf(df2.format(changeInserted)));
                    slotCans.setText(String.valueOf(drinksInSlot));
                    return;
            } 
            } else if(changeInserted >= pepsiPrice && pepsiStock < 0) {
                status.setText("NO PEPSI AVAILABLE! PLEASE RESTOCK"); 
            } else {
                status.setText("You do not have enough change!");
            }
        });
        
         sprite.setOnAction((ActionEvent event) -> {
                if (juice == false){ 
                   status.setText("Power OFF: Turn On");
                status.setStyle("-fx-base: #A32BC9;");
            } else if (changeInserted >= spritePrice && spriteStock >= 0){
               while(spriteStock >= 0){
                    status.setText("You took a Sprite can");
                    spriteInventory = spriteInventory-1;
                    spriteStockDisplay.setText(String.valueOf(spriteInventory));
                    spriteStock = spriteInventory-1;
                    drinksInSlot+=1;
                       
                    changeInserted =  changeInserted - spritePrice;
                    changeAmount.setText(String.valueOf(df2.format(spriteStock -1)));
                    changeAmount.setText(String.valueOf(df2.format(changeInserted)));
                    slotCans.setText(String.valueOf(drinksInSlot));
                    return;
               }
            } else if (changeInserted >= spritePrice && spriteStock < 0){ 
                status.setText("NO SPRITE AVAILABLE! PLEASE RESTOCK");
            } else { 
                status.setText("You do not have enough change!");
            }
        });
        
        
    }    

   

    
    
}