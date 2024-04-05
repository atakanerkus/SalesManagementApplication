import java.util.Random;

public class SalaryManagement {
	FileIO file = new FileIO();
	Random random = new Random();
	TransactionManagement tranManage = new TransactionManagement();
	private Transaction[][] transactionArray = tranManage.transaction();
	private ShopAssistant[] shopAssistantArray = file.getShopAssistantArray();
	double[] totalRevenueArray = new double[FileIO.ASSISTANT_COUNT];
	int[] seniorityArray = new int[FileIO.ASSISTANT_COUNT];
	double[] weeklySalaryArray = new double[FileIO.ASSISTANT_COUNT];
	double[] commisionArray = new double[FileIO.ASSISTANT_COUNT];
	double[] totalSalaryArray = new double[FileIO.ASSISTANT_COUNT];

	public ShopAssistant[] getShopAssistantArray() {
        file.readShopAssistants();
        for (int i = 0; i < FileIO.ASSISTANT_COUNT; i++) {
            shopAssistantArray[i] = file.getShopAssistantArray()[i];
        }
        // return a copy of the array to prevent privacy leaks
        ShopAssistant[] temp = new ShopAssistant[FileIO.ASSISTANT_COUNT];
        for (int i = 0; i < FileIO.ASSISTANT_COUNT; i++) {
            temp[i] = shopAssistantArray[i];
        }
        return temp;
    }

	public double[] getTotalRevenueArray() {
		double totalRevenue = 0.0;
		for (int j = 0; j < FileIO.ASSISTANT_COUNT; j++) {
			for (int i = 0; i < TransactionManagement.TRANSACITON_PER_ASSISTANT_COUNT; i++) {
				totalRevenue += transactionArray[i][j].getTotalPrice() + transactionArray[i][j].getTransactionFee();
			}
			totalRevenueArray[j] = totalRevenue;
			totalRevenue = 0;// needs to be reseted after every assistant's revenue calculated
		}
		double[] temp = new double[FileIO.ASSISTANT_COUNT];
		for (int i = 0; i < FileIO.ASSISTANT_COUNT; i++) {
			temp[i] = totalRevenueArray[i];
		}
		return temp;
	}

	public int[] getSeniorityArray() {
		for (int i = 0; i < FileIO.ASSISTANT_COUNT; i++) {
			int seniority = random.nextInt(15);
			seniorityArray[i] = seniority;
		}
		int [] temp = new int[FileIO.ASSISTANT_COUNT];
		for (int i = 0; i<FileIO.ASSISTANT_COUNT; i++) {
			temp[i] = seniorityArray[i];
		}
		return temp; //to prevent privacy leak
	}
	
	public double[] getWeeklySalaryArray(int[] seniorityArray) {
		double weeklySalary = 0.0;
		for (int i = 0; i < FileIO.ASSISTANT_COUNT; i++) {
			if (seniorityArray[i] < 1) {
				weeklySalary = 1500;
				weeklySalaryArray[i] = weeklySalary;
			} else if (seniorityArray[i] < 3) {
				weeklySalary = 2000;
				weeklySalaryArray[i] = weeklySalary;
			} else if (seniorityArray[i] < 5) {
				weeklySalary = 2500;
				weeklySalaryArray[i] = weeklySalary;
			} else {
				weeklySalary = 3000;
				weeklySalaryArray[i] = weeklySalary;
			}
		}
		double [] temp = new double[FileIO.ASSISTANT_COUNT];
		for (int i = 0; i<FileIO.ASSISTANT_COUNT; i++) {
			temp[i] = weeklySalaryArray[i];
		}
		return temp; //to prevent privacy leak
	}

	public double[] getCommisionArray(double[] totalRevenueArray) {
		double commision = 0.0;
		for (int i = 0; i < FileIO.ASSISTANT_COUNT; i++) {
			if (totalRevenueArray[i] > 7500) {
				commision = (totalRevenueArray[i] * 3) / 100;
				commisionArray[i] = commision;
			} else {
				commision = (totalRevenueArray[i]) / 100;
				commisionArray[i] = commision;
			}
		}
		double [] temp = new double[FileIO.ASSISTANT_COUNT];
		for (int i = 0; i<FileIO.ASSISTANT_COUNT; i++) {
			temp[i] = commisionArray[i];
		}
		return temp; //to prevent privacy leak
	}

	public double[] getTotalSalaryArray(double[] commisionArray, double[] weeklySalaryArray) {
		for (int i = 0; i < FileIO.ASSISTANT_COUNT; i++) {
			totalSalaryArray[i] = commisionArray[i] + weeklySalaryArray[i] * 4;// since program runs for a month
																				// (4weeks)
		}
		double [] temp = new double[FileIO.ASSISTANT_COUNT];
		for (int i = 0; i<FileIO.ASSISTANT_COUNT; i++) {
			temp[i] = totalSalaryArray[i];
		}
		return temp; //to prevent privacy leak
	}
}