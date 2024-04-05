public class Transaction {
	private final int TRANSACTION_COUNT = 3;
	private int id;
	private Product[] productArray = new Product[TRANSACTION_COUNT];
	private double totalPrice;
	private double transactionFee;

	Transaction(int id, Product[] productArray, double totalPrice, double transactionFee) {
		this.id = id;
		this.productArray = productArray;
		this.totalPrice = totalPrice;
		this.transactionFee = transactionFee;
	}

	public double getTransactionFee() {
		return transactionFee;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public Product[] getProductArray() {
		return productArray;
	}

	public int getId() {
		return id;
	}
}
