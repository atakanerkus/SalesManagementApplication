import java.util.Random;

public class TransactionManagement {
	FileIO file = new FileIO();
	Random random = new Random();
	public static final int TRANSACTION_PRODUCT_COUNT = 3; 
	public static final int TRANSACITON_PER_ASSISTANT_COUNT = 15;// constants are from the homework
	public static final int TRANSACTION_COUNT = FileIO.ASSISTANT_COUNT * TRANSACITON_PER_ASSISTANT_COUNT;// 1500
																										// transaction
																										// total
	public int lengthProducts = FileIO.PRODUCT_COUNT;
	private int productId = -1; // initialized to -1 to understand if there is an error
	private Product[] allProductArray = file.getProductArray();
	private int[][] quantityArray = new int[TRANSACTION_COUNT][TRANSACTION_PRODUCT_COUNT];
	private Transaction[][] transactionArray = new Transaction[TRANSACITON_PER_ASSISTANT_COUNT][FileIO.ASSISTANT_COUNT];

	private void setProducts(Product[] transactionProductArray) {
		int boundary = FileIO.PRODUCT_COUNT;
		for (int i = 0; i < TRANSACTION_PRODUCT_COUNT; i++) {
			productId = random.nextInt(boundary);
			transactionProductArray[i] = allProductArray[productId];
		}
	}

	private double calcTotalPrice(Product[] transactionProductArray, int[] quantityArrayOfTransaction) {
		int quantity;
		double totalPrice = 0.0;
		for (int j = 0; j < TRANSACTION_PRODUCT_COUNT; j++) {
			quantity = random.nextInt(10) + 1;
			quantityArrayOfTransaction[j] = quantity;
			totalPrice += transactionProductArray[j].getPrice() * quantity;
		}
		return totalPrice;
	}

	private double calcTransactionFee(double totalPrice) {
		double transactionFee = 0.0;
		if (totalPrice <= 499) {
			transactionFee = 0.01 * totalPrice;
		} else if (totalPrice <= 799) {
			transactionFee = 0.03 * totalPrice;
		} else if (totalPrice <= 999) {
			transactionFee = 0.05 * totalPrice;
		} else if (1000 <= totalPrice) {
			transactionFee = 0.09 * totalPrice;
		}
		return transactionFee;
	}

	public Transaction[][] transaction() {
		int transactionId = 0;
		double totalPrice;
		double transactionFee;
		file.readProducts();
		for (int i = 0; i < TRANSACITON_PER_ASSISTANT_COUNT; i++) {
			for (int j = 0; j < FileIO.ASSISTANT_COUNT; j++) {
				int[] quantityOfTransaction = new int[TRANSACTION_PRODUCT_COUNT];// we need to make a new array object for every row
																				// objects are passed by reference
				Product[] transactionProductArray = new Product[TRANSACTION_PRODUCT_COUNT];
				setProducts(transactionProductArray);
				totalPrice = calcTotalPrice(transactionProductArray, quantityOfTransaction);
				transactionFee = calcTransactionFee(totalPrice);
				quantityArray[transactionId] = quantityOfTransaction;
				Transaction transaction = new Transaction(transactionId, transactionProductArray, totalPrice,
						transactionFee);
				transactionId++;
				transactionArray[i][j] = transaction;
			}
		}
		return transactionArray;
	}

	public int[][] getQuantityArray() {
		return quantityArray;
	}

}
