

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.Math;

/**
 * 
 * @title: StockTransaction.java
 * @Project: Assignment4
 * @Author: Jalen Battle
 * @Version: Created: 10/27/2015, Modified:
 * @Description: 
 * @References: Dr.Praveen Madiraju
 * 
 */
public class StockTransaction {
	
	public static int DebugLevel = 0;

	public static void main(String[] args) { 							// Start of main
		// TODO Auto-generated constructor stub
	
				// Declare Variables
				String[] split = new String [10];
				String line;
		    	DLList stockCode = new DLList();
		    	DLList companyName = new DLList();
		    	DLList tStockCode = new DLList();
		    	DLList stockAction = new DLList();
		    	DLList stockAmount = new DLList();
		    	DLList stockPrice = new DLList();
		    	DLList Transactions = new DLList();
		    	DLList buyTransactions = new DLList();
		    	
		    	// Read Stocks List
				BufferedReader reader2 = null;
				try {
					reader2 = new BufferedReader(new FileReader("stocks.txt"));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				// Read Stocks line by line
				try {
					while ((line = reader2.readLine()) != null) {  
						split = line.split(";"); 								// Splits each line at every space character
						stockCode.push(split[0]); 								// Saves the split information strings into the proper arrays
						companyName.push(split[1]); 
						if (DebugLevel<2) System.out.println(line); 							// Debug line						
					}
				} catch (IOException f) {
					// TODO Auto-generated catch block
					f.printStackTrace();
				}
				if (DebugLevel<0) System.out.println(stockCode);								// Debug line
				if (DebugLevel<0) System.out.println(companyName);								// Debug line
				
				// Read Transactions List
				BufferedReader reader = null;
				try {
					reader2 = new BufferedReader(new FileReader("transactions.txt"));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				// Read Transactions line by line
				try {
					while ((line = reader2.readLine()) != null) {  
						split = line.split(";"); 									// Splits each line at every space character
						tStockCode.push(split[0]); 									// Saves the split information strings into the proper arrays
						stockAction.push(split[1]); 
						stockAmount.push(split[2]);
						split[3]= split[3].replace("$", "");
						stockPrice.push(split[3]);
						DLLNode inputTransaction = new DLLNode(split[0], split[1], Integer.parseInt(split[2]), Integer.parseInt(split[3]));
						Transactions.addLast(inputTransaction);
					}
				} catch (IOException f) {
					// TODO Auto-generated catch block
					f.printStackTrace();
				}
				
				if (DebugLevel<0) System.out.println(tStockCode);							// Debug line
				if (DebugLevel<0) System.out.println(stockAction);							// Debug line
				if (DebugLevel<0) System.out.println(stockAmount);							// Debug line
				if (DebugLevel<0) System.out.println(stockPrice);							// Debug line
				if (DebugLevel<0) System.out.println(Transactions);							// Debug line
				
				//Request Input
				System.out.println("Java StockTransaction");
				System.out.println("--successfully read stocks.txt and transactions.txt");
				System.out.println("Please enter a input stock quote for realized gain(or loss) for the stock : ");
				Scanner scanner = new Scanner(System.in); // create a new instance of scanner
				String stockRequest = scanner.nextLine(); 
				scanner.close(); // closes scanner
				if (DebugLevel<0) System.out.println(Transactions);							// Debug line
				
				//Declare variables
				double StockSellPrice = 0.0;
				double capitalGain = 0.0;
				double remainingStocks;
				double StockSold = 0;
				boolean found = false;
				boolean found2 = false;
				
				//Loop through Stocks
				while (!stockCode.isEmpty()) { 
					DLLNode temp = new DLLNode(null);
					DLLNode temp2 = new DLLNode(null);
					temp = stockCode.getFirst();
					stockCode.pop();
					if (DebugLevel<2) System.out.println("Stock Array = " + stockCode);						// Debug line
					if (DebugLevel<2) System.out.println(temp.getInfo());									// Debug line
					String stockMatch = (String) temp.getInfo();
					if (stockRequest.equals(stockMatch)) {
						found = true;
						found2 = true;
						
						//loop through transactions
						while (!Transactions.isEmpty()) {
							if (DebugLevel<3) System.out.println(Transactions);								// Debug line
							temp2 = Transactions.getFirst();
							Transactions.pop();
							if (DebugLevel<3) System.out.println("Transactions Array = " + Transactions);	// Debug line
							if (DebugLevel<3) System.out.println(temp2.getInfo());							// Debug line
							if (DebugLevel<3) System.out.println(temp.getInfo());							// Debug line
							stockMatch = (String) temp2.getInfo();
							if (DebugLevel<3) System.out.println("Match " + Transactions);					// Debug line
							if (stockRequest.equals(stockMatch)) {
								if (DebugLevel<3) System.out.println(temp.getRecord().getBuyOrSell());		// Debug line
								if (DebugLevel<3) System.out.println(" ");									// Debug line
								
								//Check for Buys and Sells
								if (temp2.getRecord().getBuyOrSell().equals("buy")) {
									if (DebugLevel<4) System.out.println("Buy");							// Debug line
									buyTransactions.addLast(temp2);
									if (DebugLevel<4)System.out.println(buyTransactions + "" + temp2);								// Debug line
									if (DebugLevel<4)System.out.println("Shares Bought = " + temp2.getRecord().getShares());		// Debug line
									if (DebugLevel<4)System.out.println("Share Price = " +temp2.getRecord().getPricePerShare());	// Debug line
									if (DebugLevel<4)System.out.println("Stock Transactions Array = " +buyTransactions);			// Debug line							
								} else {
									if (DebugLevel<4)System.out.println("Sell");							// Debug line
									StockSold = temp2.getRecord().getShares();
									StockSellPrice = temp2.getRecord().getPricePerShare();
									if (DebugLevel<4)System.out.println(StockSold);						// Debug line
									if (DebugLevel<4)System.out.println(StockSellPrice);					// Debug line
									
									//calculate Capital Gains
									if (buyTransactions.isEmpty()) {
										System.out.println("Sorry, there is an error condition associated with " + companyName.getFirst().getInfo() + " The number of sold shares exceeds the total buy quantity.");
										System.exit(0);
									}
									remainingStocks = (buyTransactions.getFirst().getRecord().getShares() - StockSold);
									while (StockSold != 0) {
										//double quickStockPrice = buyTransactions.getLast().getRecord().getPricePerShare();
										if (remainingStocks > 0) {
											capitalGain += StockSold*(StockSellPrice-buyTransactions.getFirst().getRecord().getPricePerShare());
											buyTransactions.getFirst().getRecord().setShares((int) remainingStocks);
											StockSold = 0;
										}else if (remainingStocks == 0) {
											capitalGain += StockSold*(StockSellPrice-buyTransactions.getFirst().getRecord().getPricePerShare());
											buyTransactions.pop();
											StockSold = 0;
										} else {
											capitalGain += buyTransactions.getFirst().getRecord().getShares()*(StockSellPrice - buyTransactions.getFirst().getRecord().getPricePerShare());
											buyTransactions.pop();
											if (DebugLevel<3) System.out.println(remainingStocks);											// Debug line
											StockSold = (-1*remainingStocks);
											if (DebugLevel<3)System.out.println(StockSold);												// Debug line
											remainingStocks = StockSold;
											if (buyTransactions.isEmpty()) {
												System.out.println("Sorry, there is an error condition associated with " + companyName.getFirst().getInfo() + " The number of sold shares exceeds the total buy quantity.");
												System.exit(0);
											}
										}	
										if (DebugLevel<2) System.out.println("Current Gain " + capitalGain);								// Debug line
									}
								}
							}
						}
					}
					if (found == false) { 
						companyName.pop();
					}
				}
				
				// inform user (output)
				if (found2){
					DLLNode temp = new DLLNode(null);
					temp = companyName.getFirst();
					if (DebugLevel<1) System.out.println(temp.getInfo());					// Debug line
					if (capitalGain > 0) { 
						System.out.println("Congratulations, your realized gain for " + temp.getInfo() + " is : $" + capitalGain);
					} else if (capitalGain < 0) {
						System.out.println("Sorry, your realized loss for " + temp.getInfo() + " is : $" + capitalGain);
					} else {
						System.out.println("Sorry, no realized gain(or loss) reported for " + temp.getInfo());
					}
				} else {	
					System.out.println("Sorry, the stock quote does not exist in the system.");
				}
				System.exit(0);
		
	}
}
