import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileIO {
	public static final int PRODUCT_COUNT = 90;
	public static final int ASSISTANT_COUNT = 100;
	private Product[] products = new Product[PRODUCT_COUNT];
	private ShopAssistant[] assistants = new ShopAssistant[ASSISTANT_COUNT];
	private String line = "";
	private String splitBy = ";";// default delimiter for csv
	private int id = -1; // initialized to -1 to understand if there is an error
	private String name = "";
	private String surname = "";
	private String phoneNumber = "";
	private double price = 0.0;

	public void readProducts() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("products.csv"));
			int index = 0;
			while ((line = br.readLine()) != null) {
				String[] temp = line.split(splitBy);
				id = Integer.parseInt(temp[0]);
				name = temp[1];
				temp[2] = temp[2].replace(",", ".");// need to replace ',' to '.' to parse into double
				price = Double.parseDouble(temp[2]);
				products[index] = new Product(id, name, price);
				index++;
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void readShopAssistants() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("shopAssistants.csv"));
			int index = 0;
			while ((line = br.readLine()) != null) {
				String[] temp = line.split(splitBy);
				id = Integer.parseInt(temp[0]);
				name = temp[1];
				surname = temp[2];
				phoneNumber = temp[3];
				assistants[index] = new ShopAssistant(id, name, surname, phoneNumber);
				index++;
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Product[] getProductArray() {
		return products;
	}

	public ShopAssistant[] getShopAssistantArray() {
		return assistants;
	}
	
}
