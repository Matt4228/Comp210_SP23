package assn02;

import java.util.Scanner;
import java.util.LinkedList;
import java.util.Iterator;


public class JavaWarmUp {
    static String[] types = {"laptop", "phone", "smart_watch"};
    static LinkedList<ProductBatch> batchesLL = new LinkedList<>();

    public static void main(String[] args) {
        //method takes input from the console and adds it to a sorted LinkedList
        getInput();

        //just to really warm up for data structures I chose to work with data as a list and as an array
        ProductBatch[] batchesArr = new ProductBatch[batchesLL.size()];

        //copies the sorted list to the array
        populateArr(batchesArr);

        //debugging method for testing LinkedList insertions
        //testPrint();

        //processes and prints the data
        print(batchesArr);

    }


    public static void getInput() {
        Scanner sc = new Scanner(System.in);

        //data is given in the following format:
        //9/21/22 14:34 laptop 49.36 1525 1044.6 1779


        int ListLength = sc.nextInt();

        //defines the fields for each ProductBatch and then adds it to the LL
        for(int i = 0; i < ListLength; i++) {
            String date = sc.next();
            String time = sc.next();
            String type = sc.next();
            double fee = sc.nextDouble();
            int quant = sc.nextInt();
            double duration = sc.nextDouble();
            double cost = sc.nextDouble();
            ProductBatch nextBatch = new ProductBatch(date, time, type,
                    fee, quant, duration, cost);
            sortedAdd(nextBatch);
        }
    }

    /**
     * sorts the data on insert based on fee in descending order
     * @param batch
     */
    public static void sortedAdd(ProductBatch batch) {
        Iterator j = batchesLL.iterator();

        //empty LL case
        if(batchesLL.size() == 0) {
            batchesLL.add(batch);
        } else {
            //highest < the fee of batch j < lowest
            int index = 0;
            while(j.hasNext()) {
                if(((ProductBatch) j.next()).getFee() < batch.getFee()) {
                    batchesLL.add(index, batch);
                    break;
                }
                index++;
            }
        }
        //tail case
        if(!j.hasNext()) {
            batchesLL.add(batch);
        }
    }

    /**
     * copies the LL data to the Array implementation
     * @param batchesArr
     */
    public static void populateArr(ProductBatch[] batchesArr) {
        Iterator k = batchesLL.iterator();
        int index = 0;
        while(k.hasNext()) {
            batchesArr[index] = (ProductBatch) k.next();
            index++;
        }
    }


    /**
     * finds a statistic for the total quantity based on type
     * @param batchesArr
     * @param targetType
     * @return
     */
    public static int getQuantity(ProductBatch[] batchesArr, String targetType) {
        int quantity = 0;
        for(ProductBatch batch: batchesArr) {
            if(batch.getType().equals(targetType)) {
                quantity += batch.getQuant();
            }
        }
        return quantity;
    }

    /**
     * finds a statistic for the average assembling fee based on type
     * @param batchesArr
     * @param targetType
     * @return
     */
    public static double getAvgFee(ProductBatch[] batchesArr, String targetType) {
        double weightedTotal = 0;
        int quantity = 0;
        for(ProductBatch batch: batchesArr) {
            if(batch.getType().equals(targetType)) {
                weightedTotal += batch.getFee()* batch.getQuant();
                quantity += batch.getQuant();
            }
        }

        return ( weightedTotal/quantity);
    }

    /**
     * finds a statistic for the average net profit per unit based on type
     * @param batchesArr
     * @param targetType
     * @return
     */
    public static double getAvgNetProfit(ProductBatch[] batchesArr, String targetType) {
        double totalFee = 0;
        double totalWages = 0;
        double totalCost = 0;
        int quantity = 0;
        for(ProductBatch batch: batchesArr) {
            if(batch.getType().equals(targetType)) {
                totalFee += batch.getFee() * batch.getQuant();
                totalWages += batch.getDuration() * 16;
                totalCost += batch.getCost();
                quantity += batch.getQuant();
            }
        }
        double rawANP = (totalFee - totalWages - totalCost)/quantity;
        return (rawANP);
    }

    //prints the contents of the linked list to test sorting algorithm
    public static void testPrint() {
        Iterator i = batchesLL.iterator();
        while(i.hasNext()) {
            System.out.println(((ProductBatch) i.next()).printBatch());
        }
    }

    //print method
    //refactoring notes:
    //the highest and lowest are relatively close to being efficient
    //the statistic pieces should be refactored to use loops to simplify the code
    //ideally I would collect all the necessary data for each statistic in one iteration on the list
    public static void print(ProductBatch[] batchesArr) {
        System.out.println("Highest per unit assembling fee:");
        ProductBatch high = batchesLL.getFirst();
        System.out.println("\tWhen: " + high.getDate() + " " + high.getTime());
        System.out.println("\tCategory: " + high.getType());
        System.out.println("\tPrice: " + high.getFee());

        System.out.println("Lowest per unit assembling fee:");
        ProductBatch low = batchesLL.getLast();
        System.out.println("\tWhen: " + low.getDate() + " " + low.getTime());
        System.out.println("\tCategory: " + low.getType());
        System.out.println("\tPrice: " + low.getFee());

        System.out.println("Statistic of phone");
        System.out.println("\tQuantity: " + getQuantity(batchesArr, "phone"));
        System.out.println("\tAverage Assembling fee: " + String.format("%.2f", getAvgFee(batchesArr, "phone")));
        System.out.println("\tAverage Net Profit: " + String.format("%.2f", getAvgNetProfit(batchesArr, "phone")));

        System.out.println("Statistic of laptop");
        System.out.println("\tQuantity: " + getQuantity(batchesArr, "laptop"));
        System.out.println("\tAverage Assembling fee: " + String.format("%.2f", getAvgFee(batchesArr, "laptop")));
        System.out.println("\tAverage Net Profit: " + String.format("%.2f", getAvgNetProfit(batchesArr, "laptop")));

        System.out.println("Statistic of smart_watch");
        System.out.println("\tQuantity: " + getQuantity(batchesArr, "smart_watch"));
        System.out.println("\tAverage Assembling fee: " + String.format("%.2f", getAvgFee(batchesArr, "smart_watch")));
        System.out.println("\tAverage Net Profit: " + String.format("%.2f", getAvgNetProfit(batchesArr, "smart_watch")));
    }


}
