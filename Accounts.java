package databasee;
import java.sql.Timestamp;
public class Accounts {
	int acc_id ;
	int cust_id;
	String account_type;
    float balance ;
    Timestamp date_time;
    
      
    public Accounts(int acc_id ,
	int cust_id,
	String account_type,
    float balance ,
    Timestamp date_time) {
    	 this.acc_id=acc_id;
    	 this.account_type=account_type;
    	 this.balance = balance ;
    	 this .cust_id= cust_id ;
    	 this.date_time =date_time;
    }

    
}
