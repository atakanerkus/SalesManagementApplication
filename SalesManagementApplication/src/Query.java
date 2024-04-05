public class Query {
	SalaryManagement salaryManagement = new SalaryManagement();
	TransactionManagement tManagement = new TransactionManagement();

	private ShopAssistant[] shopAssistantArray = salaryManagement.getShopAssistantArray();
	private double[] totalRevenueArray = salaryManagement.getTotalRevenueArray();
	private int[] seniorityArray = salaryManagement.getSeniorityArray();
	private double[] weeklySalaryArray = salaryManagement.getWeeklySalaryArray(seniorityArray);
	private double[] commusionArray = salaryManagement.getCommisionArray(totalRevenueArray);
	private double[] totalSalaryArray = salaryManagement.getTotalSalaryArray(commusionArray, weeklySalaryArray);
	private Transaction[][] transactionArray = tManagement.transaction();
	private int[][] quantityArray = tManagement.getQuantityArray();
	private double totalCommission = 0.0;
	private double totalFee = 0.0;
	private double totalPrice = 0.0;
	private double totalEarned = 0.0;
	private double totalSalary = 0.0;
	
	public void callByOrder() {
		printHighestTotalPriceTransaction(); // 1.
		printExpensiveProductOfLowestTransaction(); // 2.
		printLowestTransactionFee(); // 3.
		printHighestSalaryShopAssistant(); // 4.
		printTotalRevenueEarned(); // 5.
		printTotalProfitEarned(); // 6.
	}

	public void printHighestTotalPriceTransaction() {
		double highest = Double.MIN_VALUE;// everything is bigger than Double.MIN_VALUE
		int indexOfHighestRow = -1;
		int indexOfHighestColumn = -1;
		String products = "";
		for (int i = 0; i < FileIO.ASSISTANT_COUNT; i++) {
			for (int j = 0; j < TransactionManagement.TRANSACITON_PER_ASSISTANT_COUNT; j++) {
				if (transactionArray[j][i].getTotalPrice() > highest) { // QUERY-1
					highest = transactionArray[j][i].getTotalPrice();
					indexOfHighestRow = i;
					indexOfHighestColumn = j;
				}
			}
		}
		Transaction transaction = transactionArray[indexOfHighestColumn][indexOfHighestRow];// HighestTotalPriceTransaction
		Product[] productArrayOfT = transaction.getProductArray();
		for (int i = 0; i < TransactionManagement.TRANSACTION_PRODUCT_COUNT; i++) {
			products += productArrayOfT[i].getName() + "   ";
		}
		System.out.println("HIGHEST-TOTAL-PRICE TRANSACTION");
		System.out.println("TOTAL_PRICE: " + String.format("%.2f", transactionArray[indexOfHighestColumn][indexOfHighestRow].getTotalPrice())+" TL");
		System.out.println("ID: " + transactionArray[indexOfHighestColumn][indexOfHighestRow].getId());
		System.out.println("PRODUCTS: " + products);
		System.out.println("TRANSACTION_FEE: " + String.format("%.2f", transactionArray[indexOfHighestColumn][indexOfHighestRow].getTransactionFee())+" TL");
		System.out.println("-------------------------------------");
	}

	public void printExpensiveProductOfLowestTransaction() {
		double lowest = Double.MAX_VALUE;// everything is smaller than Double.MAX_VALUE
		double highest = Double.MIN_VALUE;// everything is bigger than Double.MIN_VALUE
		int indexOfLowestRow = -1;
		int indexOfLowestColumn = -1;
		int indexOfExpensiveProduct = -1;
		String productsString = "";
		for (int i = 0; i < FileIO.ASSISTANT_COUNT; i++) {
			for (int j = 0; j < TransactionManagement.TRANSACITON_PER_ASSISTANT_COUNT; j++) {
				if (transactionArray[j][i].getTransactionFee() < lowest) { // QUERY-2
					lowest = transactionArray[j][i].getTransactionFee();// LowestTransactionFee
					indexOfLowestRow = i;
					indexOfLowestColumn = j;
					break;
				}
			}
		}
		Product[] products = transactionArray[indexOfLowestColumn][indexOfLowestRow].getProductArray();// Products of
																										// LowestTransaction
		for (int i = 0; i < TransactionManagement.TRANSACTION_PRODUCT_COUNT; i++) {
			if (products[i].getPrice() > highest) {
				highest = products[i].getPrice(); // highest valued Product of LowestTransaction
				indexOfExpensiveProduct = i;
			}
			productsString += transactionArray[indexOfLowestColumn][indexOfLowestRow].getProductArray()[i].getName()
					+ "   ";
		}
		int id = transactionArray[indexOfLowestColumn][indexOfLowestRow].getId();// id of the LowestTransactionFee's
																					// transactionId
		System.out.println("MOST-EXPENSIVE PRODUCT IN THE LOWEST-PRICE TRANSACTION");
		System.out.println("");
		System.out.println("LOWEST-PRICE TRANSACTION");
		System.out.println("ID: " + transactionArray[indexOfLowestColumn][indexOfLowestRow].getId());
		System.out.println("QUANTITIES: " + quantityArray[id][0] + "," + quantityArray[id][1] + "," + quantityArray[id][2]);
		System.out.println("PRODUCTS: " + productsString);
		System.out.println("TOTAL-PRICE: " + String.format("%.2f", transactionArray[indexOfLowestColumn][indexOfLowestRow].getTotalPrice())+" TL");
		System.out.println("TRANSACTION-FEE: " + String.format("%.2f", transactionArray[indexOfLowestColumn][indexOfLowestRow].getTransactionFee())+" TL");
		System.out.println("");
		System.out.println("MOST-EXPENSIVE PRODUCT IN THE TRANSACTION");
		System.out.println("ID: " + products[indexOfExpensiveProduct].getId());
		System.out.println("NAME: " + products[indexOfExpensiveProduct].getName());
		System.out.println("PRICE: " + String.format("%.2f", products[indexOfExpensiveProduct].getPrice())+" TL");
		System.out.println("-------------------------------------");
	}

	public void printLowestTransactionFee() {
		double lowest = Double.MAX_VALUE; // everything is bigger than Double.MAX_VALUE
		int indexOfLowestRow = -1; // -1 to understand if there is an error
		int indexOfLowestColumn = -1;
		String products = "";
		for (int i = 0; i < FileIO.ASSISTANT_COUNT; i++) {
			for (int j = 0; j < TransactionManagement.TRANSACITON_PER_ASSISTANT_COUNT; j++) {
				if (transactionArray[j][i].getTotalPrice() < lowest) { // QUERY-3
					lowest = transactionArray[j][i].getTotalPrice();
					indexOfLowestRow = i;
					indexOfLowestColumn = j;
				}
			}
		}
		for (int i = 0; i < TransactionManagement.TRANSACTION_PRODUCT_COUNT; i++) {
			products += transactionArray[indexOfLowestColumn][indexOfLowestRow].getProductArray()[i].getName() + "   ";
		}
		System.out.println("LOWEST-TRANSACTION FEE");
		System.out.println("TRANSACTION_FEE: " + String.format("%.2f", transactionArray[indexOfLowestColumn][indexOfLowestRow].getTransactionFee())+" TL");
		System.out.println("ID: " + transactionArray[indexOfLowestColumn][indexOfLowestRow].getId());
		System.out.println("PRODUCTS: " + products);
		System.out.println("TOTAL_PRICE: " + String.format("%.2f", transactionArray[indexOfLowestColumn][indexOfLowestRow].getTotalPrice())+" TL");
		System.out.println("-------------------------------------");
	}

	public void printHighestSalaryShopAssistant() {
		double highest = 0.0;
		int indexOfHighest = -1; // -1 to understand if there is an error
		for (int i = 0; i < totalSalaryArray.length; i++) {
			if (totalSalaryArray[i] > highest) {
				highest = totalSalaryArray[i]; // QUERY-4
				indexOfHighest = i;
			}
		}
		System.out.println("HIGHEST-SALARY SHOP ASSISTANT");
		System.out.println("ID: " + shopAssistantArray[indexOfHighest].getId());
		System.out.println("NAME-SURNAME: " + shopAssistantArray[indexOfHighest].getName() + " " + shopAssistantArray[indexOfHighest].getSurname());
		System.out.println("SENIORITY: " + seniorityArray[indexOfHighest]);
		System.out.println("WEEKLY BASIS SALARY: " + String.format("%.2f", weeklySalaryArray[indexOfHighest])+" TL");
		System.out.println("COMMISSION: " + String.format("%.2f", commusionArray[indexOfHighest])+" TL");
		System.out.println("TOTAL SALARY: " + String.format("%.2f", totalSalaryArray[indexOfHighest])+" TL");
		System.out.println("-------------------------------------");
	}

	public void printTotalRevenueEarned() {
		for (int i = 0; i < TransactionManagement.TRANSACITON_PER_ASSISTANT_COUNT; i++) {
			for (int j = 0; j < FileIO.ASSISTANT_COUNT; j++) {
				totalPrice += transactionArray[i][j].getTotalPrice();
				totalFee += transactionArray[i][j].getTransactionFee();
			}
		}
		totalEarned = totalFee + totalPrice;
		System.out.println("TOTAL-REVENUE EARNED");
		System.out.println("TOTAL-FEE EARNED = " + String.format("%.2f", totalFee)+" TL");
		System.out.println("GROSS-PRODUCT MONEY EARNED(totalPrice) = " + String.format("%.2f", totalPrice)+" TL");
		System.out.println("TOTAL-REVENUE EARNED = " + String.format("%.2f", totalEarned)+" TL"); // QUERY-5
		System.out.println("-------------------------------------");
	}

	public void printTotalProfitEarned() {
		for (int i = 0; i < totalSalaryArray.length; i++) { // QUERY-6
			totalCommission += commusionArray[i];
			totalSalary += totalSalaryArray[i];
		}
		System.out.println("TOTAL-PROFIT EARNED");
		System.out.println("TOTAL-FEE EARNED = " + String.format("%.2f", totalFee)+" TL");
		System.out.println("GROSS-PRODUCT MONEY EARNED = " + String.format("%.2f", totalPrice)+" TL");
		System.out.println("TOTAL-REVENUE EARNED = " + String.format("%.2f", totalEarned)+" TL");
		System.out.println("TOTAL-COMMISSION OF SHOP-ASSISTANTS = " + String.format("%.2f", totalCommission)+" TL");
		System.out.println("TOTAL-SALARY OF SHOP-ASSISTANTS = " + String.format("%.2f", totalSalary)+" TL");
		System.out.println("TOTAL-BALANCE AFTER ASSISTANT-EXPENSES = " + String.format("%.2f", (totalEarned - totalSalary))+" TL");
		System.out.println("-------------------------------------");
	}
}