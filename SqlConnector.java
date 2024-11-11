package databasee;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.sql.SQLException;


public class SqlConnector {
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String url = "jdbc:mysql://localhost:3306/your_database_name ";
		String user = "root";
		String password = "password";
		Scanner sc = new Scanner(System.in);
		ArrayList <Bankdatas> BankList = new ArrayList<>();
		ArrayList <Accounts> AccountList = new ArrayList<>();
		ArrayList <CurrentAcc> CurrAccList = new ArrayList<>();
		ArrayList <SavingsAcc> SavAccList = new ArrayList<>();
		ArrayList <DepositTrans> DepTransList = new ArrayList<>();
		ArrayList <WithdrawalTrans> WithTransList = new ArrayList<>();
		ArrayList <Transaction> TransactionList = new ArrayList<>();
		ArrayList <Customer> CustomerList = new ArrayList<>();
		try (Connection con = DriverManager.getConnection(url, user, password)){
			Statement st = con.createStatement();

		
			ResultSet result2 = st.executeQuery("select * from bank");
			while(result2.next()) {
				int id = result2.getInt("bank_id");
				String name= result2.getString("bank_name");
				String branch = result2.getString("bank_branch");
				Bankdatas bd =new Bankdatas(id,name,branch);
				BankList.add(bd);
				
			}
			
			ResultSet  acc = st.executeQuery("select * from account");
			while(acc.next()) {
				int acc_id  = acc.getInt("account_id");
				int cust_id= acc.getInt("customer_id");
				String account_type =acc.getString("account_type");
			    float balance = acc.getFloat("balance");
			    Timestamp date_time = acc.getTimestamp("created_at");
			    Accounts ac =new Accounts(acc_id,cust_id,account_type,balance,date_time);
			    AccountList.add(ac);
			    
			}
			
			ResultSet  cac = st.executeQuery("select * from currentaccount");
			while(cac.next()) {
				int acc_id  = cac.getInt("account_id");
			    float od_lim= cac.getFloat("overdraft_limit");
			    CurrentAcc ca =new CurrentAcc(acc_id,od_lim);
			    CurrAccList.add(ca);
			}
			
			ResultSet  sac = st.executeQuery("select * from savingsaccount");
			while(sac.next()) {
				int acc_id  = sac.getInt("account_id");
			    float int_rate= sac.getFloat("interest_rate");
			    SavingsAcc sa =new SavingsAcc(acc_id,int_rate);
			    SavAccList.add(sa);
			}
			
			ResultSet  dept = st.executeQuery("select * from deposittransaction");
			while(dept.next()) {
				int trans_id  = dept.getInt("transaction_id");
			    String trans_method= dept.getString("deposit_method");
			    DepositTrans sa =new DepositTrans(trans_id , trans_method);
			    DepTransList.add(sa);
			}
			
			ResultSet  wit = st.executeQuery("select * from withdrawaltransaction");
			while(wit.next()) {
				int trans_id  = wit.getInt("transaction_id");
			    String with_method= wit.getString("withdrawal_method");
			    WithdrawalTrans sa =new WithdrawalTrans(trans_id , with_method);
			    WithTransList.add(sa);
			}
			
			ResultSet  trans = st.executeQuery("select * from transaction");
			while(trans.next()) {
				   int trans_id = trans.getInt("transaction_id") ;
				   int acc_id  = trans.getInt("account_id");
				   String trans_type = trans.getString("transaction_type");
				   float amount = trans.getFloat("amount");
				   Timestamp  date = trans.getTimestamp("transaction_date");
				   Transaction tr = new Transaction(trans_id, acc_id ,trans_type,amount,date );
				   TransactionList.add(tr);
				   
			}
			ResultSet  cust = st.executeQuery("select * from Customer");
			while(cust.next()) {
				 int acc_no = cust.getInt("account_no");
			     int acc_id = cust.getInt("account_id");
				 String name = cust.getString("name");
				 String acc_type = cust.getString("account_type");
				 Customer cs = new Customer(acc_no,acc_id ,name,acc_type);
				 CustomerList.add(cs);
			}
			System.out.println("Enter your Option ");
			System.out.println("1. Customer Details Report:\n2. Transaction History Report:\n3. Total Balance Report: \n4. Withdraw amount ::\n5. Daily Transaction Summary Report: "
					);
			int x;
			x=sc.nextInt();
			
			switch (x) {
		    case 1:
		    	
		        for(int i=0 ;i<CustomerList.size();i++) {
		        	System.out.println("\nCustomer Name : " + CustomerList.get(i).name + 
		        			"\nAccount No : " +CustomerList.get(i).acc_no + 
		        			"\nAccount Type : " + CustomerList.get(i).acc_type);
		        }
		        break;
		    case 2:
		    	System.out.println("Enter an Account number : ");
		    	int ac =sc.nextInt();
		    	for(int i=0 ; i<CustomerList.size();i++) {
		    		if(CustomerList.get(i).acc_no == ac) {
		    			for (int j =0 ;j<TransactionList.size();j++) {
		    				if(TransactionList.get(j).acc_id==CustomerList.get(i).acc_id) {
		    					System.out.println("\nCustomer Name :"+CustomerList.get(i).name+"\nTransaction Type : " +TransactionList.get(j).trans_type + "\nTransaction id :" +TransactionList.get(j).trans_id+
		    							"\nAmount : " +TransactionList.get(j).amount +"\nAccount Type :"+CustomerList.get(i).acc_type );
		    					break;
		    				}
		    			}
		    		}
		    	}		        
		        break;
		    case 3:
		    	System.out.println("The Balance Details...");
		    	for(int i=0 ; i<CustomerList.size();i++) {
		    			for (int j =0 ;j<AccountList.size();j++) {
		    				if(AccountList.get(j).acc_id==CustomerList.get(i).acc_id) {
		    					System.out.println("\nCustomer Name :"+CustomerList.get(i).name+"\n Balance :" +AccountList.get(j).balance );
		    					break;
		    				}
		    			}		    		
		    	}
		        
		       
		}
			
			
			
			
			
			
		 }
		catch (SQLException e) {
	            e.printStackTrace();
	}
   
   
   

}
}
