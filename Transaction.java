package databasee;

import java.sql.Timestamp;

public class Transaction {
   int trans_id ;
   int acc_id ;
   String trans_type;
   float amount ;
   Timestamp  date ;
   public Transaction(int trans_id ,
   int acc_id ,
   String trans_type,
   float amount ,
   Timestamp  date) {
	   this.acc_id= acc_id ;
	   this.amount= amount;
	   this.date= date ;
	   this .trans_id = trans_id ;
	   this.trans_type = trans_type ;
	   
	   
   }
}
