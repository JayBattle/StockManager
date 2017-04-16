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
 * @References: My alma mater, Dr.Praveen Madiraju, Dale Joyce & Weems,
 * @Created: 10/27/2015
 * @Description: A command line based interface for stock management
 */

public class StockTransaction {

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
				BufferedReader reader = null;
				try {
					reader = new BufferedReader(new FileReader("stocks.txt"));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}				
				try {
					while ((line = reader.readLine()) != null) {  
						split = line.split(";");
						stockCode.push(split[0]); 								
						companyName.push(split[1]);
						System.out.println(line);
					}
				} catch (IOException f) {
					f.printStackTrace();
				}
				try {
					reader = new BufferedReader(new FileReader("transactions.txt"));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}				
				try {
					while ((line = reader.readLine()) != null) {  
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
				
				System.out.println("Java Stock Manager");
				System.out.println("--successfully read stocks.txt and transactions.txt");
				System.out.println("Please enter a input stock quote for realized gain or loss for the stock: ");
				Scanner scanner = new Scanner(System.in);
				String stockRequest = scanner.nextLine(); 
				scanner.close();
				
				double StockSellPrice = 0.0;
				double capitalGain = 0.0;
				double remainingStocks;
				double StockSold = 0;
				boolean stockFound = false;
				boolean valueFound = false;
				
				while (!stockCode.isEmpty()) { 
					StockDoubleLinkedListNode nodeA = new StockDoubleLinkedListNode(null);
					StockDoubleLinkedListNode nodeB = new StockDoubleLinkedListNode(null);
					nodeA = stockCode.getFirst();
					stockCode.pop();
					String stockMatch = (String) nodeA.getInfo();
					if (stockRequest.equals(stockMatch)) {
						stockFound = true;
						valueFound = true;
						while (!Transactions.isEmpty()) {
							nodeB = Transactions.getFirst();
							Transactions.pop();
							stockMatch = (String) nodeB.getInfo();
							if (stockRequest.equals(stockMatch)) {						
								if (nodeB.getRecord().getTrade().equals("buy")) {
									buyTransactions.addLast(nodeB);							
								} else {
									StockSold = nodeB.getRecord().getShares();
									StockSellPrice = nodeB.getRecord().getPricePerShare();							
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
											StockSold = (-1*remainingStocks);
											remainingStocks = StockSold;
											if (buyTransactions.isEmpty()) {
												System.out.println("Sorry, there is an error condition associated with " + companyName.getFirst().getInfo() + " The number of sold shares exceeds the total buy quantity.");
												System.exit(0);
											}
										}	
									}
								}
							}
						}
					}
					if (stockFound == false) { 
						companyName.pop();
					}
				}
				if (valueFound){
					StockDoubleLinkedListNode tempNode = new StockDoubleLinkedListNode(null);
					tempNode = companyName.getFirst();
					if (capitalGain > 0) { 
						System.out.println("Congratulations, your realized gain for " + tempNode.getInfo() + " is : $" + capitalGain);
					} else if (capitalGain < 0) {
						System.out.println("Sorry, your realized loss for " + tempNode.getInfo() + " is : $" + capitalGain);
					} else {
						System.out.println("Sorry, no realized gain(or loss) reported for " + tempNode.getInfo());
					}
				} else {	
					System.out.println("Sorry, the stock quote does not exist in the system.");
				}
				System.exit(0);
		
	}
}
