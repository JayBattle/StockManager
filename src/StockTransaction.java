import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.Math;

/**
 * @Author: Jay Battle
 * @title: StockTransaction.java
 * @Project: Stock Manager
 * @Course: Data Structures
 * @References: My alma mater, Dr.Praveen Madiraju, Dale Joyce & Weems,
 * @Purpose: Implement a Double Linked List based Stock Manager
 * @languages: Java
 * @Platform: Java App
 * @DevelopmentEnviorment: Eclipse Neon
 * @Version: 2.0
 * @Created: 10/27/2015
 * @Status: In revision
 * @Description: A command line based interface for stock management
 */

/**
 * @Author: Jay Battle
 * @title: StockTransaction.java
 * @Project: Stock Manager
 * @References: My alma mater, Dr.Praveen Madiraju, Dale Joyce & Weems,
 * @Created: 10/27/2015
 * @Description: A command line based interface for stock management
 */



public class StockTransaction {
	
	public static int DebugLevel = 0;

	public static void main(String[] args) {
	
				String[] split = new String [10];
				String line;
		    	StockDoubleLinkedList stockCode = new StockDoubleLinkedList();
		    	StockDoubleLinkedList companyName = new StockDoubleLinkedList();
		    	StockDoubleLinkedList tStockCode = new StockDoubleLinkedList();
		    	StockDoubleLinkedList stockAction = new StockDoubleLinkedList();
		    	StockDoubleLinkedList stockAmount = new StockDoubleLinkedList();
		    	StockDoubleLinkedList stockPrice = new StockDoubleLinkedList();
		    	StockDoubleLinkedList Transactions = new StockDoubleLinkedList();
		    	StockDoubleLinkedList buyTransactions = new StockDoubleLinkedList();
		    	
				BufferedReader reader2 = null;
				try {
					reader2 = new BufferedReader(new FileReader("stocks.txt"));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				
				try {
					while ((line = reader2.readLine()) != null) {  
						split = line.split(";");
						stockCode.push(split[0]); 								
						companyName.push(split[1]); 
						if (DebugLevel<2) System.out.println(line);					
					}
				} catch (IOException f) {
					f.printStackTrace();
				}
				if (DebugLevel>0) System.out.println(stockCode);
				if (DebugLevel>0) System.out.println(companyName);
				
				BufferedReader reader = null;
				try {
					reader2 = new BufferedReader(new FileReader("transactions.txt"));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				
				try {
					while ((line = reader2.readLine()) != null) {  
						split = line.split(";");
						tStockCode.push(split[0]);
						stockAction.push(split[1]); 
						stockAmount.push(split[2]);
						split[3]= split[3].replace("$", "");
						stockPrice.push(split[3]);
						StockDoubleLinkedListNode inputTransaction = new StockDoubleLinkedListNode(split[0], split[1], Integer.parseInt(split[2]), Integer.parseInt(split[3]));
						Transactions.addLast(inputTransaction);
					}
				} catch (IOException f) {
					f.printStackTrace();
				}
				
				if (DebugLevel>0) System.out.println(tStockCode);
				if (DebugLevel>0) System.out.println(stockAction);
				if (DebugLevel>0) System.out.println(stockAmount);
				if (DebugLevel>0) System.out.println(stockPrice);
				if (DebugLevel>0) System.out.println(Transactions);	
				
				System.out.println("Java StockTransaction");
				System.out.println("--successfully read stocks.txt and transactions.txt");
				System.out.println("Please enter a input stock quote for realized gain(or loss) for the stock : ");
				Scanner scanner = new Scanner(System.in);
				String stockRequest = scanner.nextLine(); 
				scanner.close();
				if (DebugLevel>0) System.out.println(Transactions);	
				
				double StockSellPrice = 0.0;
				double capitalGain = 0.0;
				double remainingStocks;
				double StockSold = 0;
				boolean found = false;
				boolean found2 = false;
				
				while (!stockCode.isEmpty()) { 
					StockDoubleLinkedListNode temp = new StockDoubleLinkedListNode(null);
					StockDoubleLinkedListNode temp2 = new StockDoubleLinkedListNode(null);
					temp = stockCode.getFirst();
					stockCode.pop();
					if (DebugLevel>2) System.out.println("Stock Array = " + stockCode);
					if (DebugLevel>2) System.out.println(temp.getInfo());
					String stockMatch = (String) temp.getInfo();
					if (stockRequest.equals(stockMatch)) {
						found = true;
						found2 = true;
						while (!Transactions.isEmpty()) {
							if (DebugLevel>3) System.out.println(Transactions);
							temp2 = Transactions.getFirst();
							Transactions.pop();
							if (DebugLevel>3) System.out.println("Transactions Array = " + Transactions);
							if (DebugLevel>3) System.out.println(temp2.getInfo());
							if (DebugLevel>3) System.out.println(temp.getInfo());
							stockMatch = (String) temp2.getInfo();
							if (DebugLevel>3) System.out.println("Match " + Transactions);
							if (stockRequest.equals(stockMatch)) {
								if (DebugLevel>3) System.out.println(temp.getRecord().getTrade());
								if (DebugLevel>3) System.out.println(" ");								
								if (temp2.getRecord().getTrade().equals("buy")) {
									if (DebugLevel>4) System.out.println("Buy");
									buyTransactions.addLast(temp2);
									if (DebugLevel>4)System.out.println(buyTransactions + "" + temp2);
									if (DebugLevel>4)System.out.println("Shares Bought = " + temp2.getRecord().getShares());
									if (DebugLevel>4)System.out.println("Share Price = " +temp2.getRecord().getPricePerShare());
									if (DebugLevel>4)System.out.println("Stock Transactions Array = " +buyTransactions);							
								} else {
									if (DebugLevel>4)System.out.println("Sell");
									StockSold = temp2.getRecord().getShares();
									StockSellPrice = temp2.getRecord().getPricePerShare();
									if (DebugLevel>4)System.out.println(StockSold);	
									if (DebugLevel>4)System.out.println(StockSellPrice);								
									if (buyTransactions.isEmpty()) {
										System.out.println("Sorry, there is an error condition associated with " + companyName.getFirst().getInfo() + " The number of sold shares exceeds the total buy quantity.");
										System.exit(0);
									}
									remainingStocks = (buyTransactions.getFirst().getRecord().getShares() - StockSold);
									while (StockSold != 0) {
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
											if (DebugLevel>3) System.out.println(remainingStocks);
											StockSold = (-1*remainingStocks);
											if (DebugLevel>3)System.out.println(StockSold);
											remainingStocks = StockSold;
											if (buyTransactions.isEmpty()) {
												System.out.println("Sorry, there is an error condition associated with " + companyName.getFirst().getInfo() + " The number of sold shares exceeds the total buy quantity.");
												System.exit(0);
											}
										}	
										if (DebugLevel>2) System.out.println("Current Gain " + capitalGain);
									}
								}
							}
						}
					}
					if (found == false) { 
						companyName.pop();
					}
				}
				if (found2){
					StockDoubleLinkedListNode temp = new StockDoubleLinkedListNode(null);
					temp = companyName.getFirst();
					if (DebugLevel>1) System.out.println(temp.getInfo());
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
