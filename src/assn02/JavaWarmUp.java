package assn02;

import java.util.Scanner;
import java.util.LinkedList;
import java.util.Iterator;


public class JavaWarmUp {
    static String[] types = {"laptop", "phone", "smart_watch"};
    static LinkedList<ProductBatch> batchesLL = new LinkedList<>();

    public static void main(String[] args) {
        getInput();

        ProductBatch[] batchesArr = new ProductBatch[batchesLL.size()];

        populateArr(batchesArr);

        //testPrint();

        print(batchesArr);

    }

    public static void getInput() {
        Scanner sc = new Scanner(System.in);

        //9/21/22 14:34 laptop 49.36 1525 1044.6 1779
        int ListLength = sc.nextInt();
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

    public static void sortedAdd(ProductBatch batch) {
        Iterator j = batchesLL.iterator();
        if(batchesLL.size() == 0) {
            batchesLL.add(batch);
        } else {
            int index = 0;
            while(j.hasNext()) {
                if(((ProductBatch) j.next()).getFee() < batch.getFee()) {
                    batchesLL.add(index, batch);
                    break;
                }
                index++;
            }
        }
        if(!j.hasNext()) {
            batchesLL.add(batch);
        }
    }

    public static void populateArr(ProductBatch[] batchesArr) {
        Iterator k = batchesLL.iterator();
        int index = 0;
        while(k.hasNext()) {
            batchesArr[index] = (ProductBatch) k.next();
            index++;
        }
    }


    public static int getQuantity(ProductBatch[] batchesArr, String targetType) {
        int quantity = 0;
        for(ProductBatch batch: batchesArr) {
            if(batch.getType().equals(targetType)) {
                quantity += batch.getQuant();
            }
        }
        return quantity;
    }

    public static double getAvgFee(ProductBatch[] batchesArr, String targetType) {
        double weightedTotal = 0;
        int quantity = 0;
        for(ProductBatch batch: batchesArr) {
            if(batch.getType().equals(targetType)) {
                weightedTotal += batch.getFee()* batch.getQuant();
                quantity += batch.getQuant();
            }
        }

        return ( ((int)(100 * weightedTotal/quantity))/100.0 );
    }

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
        return ( ((int)(100 * rawANP))/100.0 );
    }





    public static void testPrint() {
        Iterator i = batchesLL.iterator();
        while(i.hasNext()) {
            System.out.println(((ProductBatch) i.next()).printBatch());
        }
    }













    public static void print(ProductBatch[] batchesArr) {
        System.out.println("Highest per unit assembly fee:");
        ProductBatch high = batchesLL.getFirst();
        System.out.println("\tWhen: " + high.getDate() + " " + high.getTime());
        System.out.println("\tCategory: " + high.getType());
        System.out.println("\tPrice: " + high.getFee());

        System.out.println("Lowest per unit assembly fee:");
        ProductBatch low = batchesLL.getLast();
        System.out.println("\tWhen: " + low.getDate() + " " + low.getTime());
        System.out.println("\tCategory: " + low.getType());
        System.out.println("\tPrice: " + low.getFee());

        System.out.println("Statistic of phone");
        System.out.println("\tQuantity: " + getQuantity(batchesArr, "phone"));
        System.out.println("\tAverage Assembling fee: " + getAvgFee(batchesArr, "phone"));
        System.out.println("\tAverage Net Profit: " + getAvgNetProfit(batchesArr, "phone"));

        System.out.println("Statistic of laptop");
        System.out.println("\tQuantity: " + getQuantity(batchesArr, "laptop"));
        System.out.println("\tAverage Assembling fee: " + getAvgFee(batchesArr, "laptop"));
        System.out.println("\tAverage Net Profit: " + getAvgNetProfit(batchesArr, "laptop"));

        System.out.println("Statistic of smart_watch");
        System.out.println("\tQuantity: " + getQuantity(batchesArr, "smart_watch"));
        System.out.println("\tAverage Assembling fee: " + getAvgFee(batchesArr, "smart_watch"));
        System.out.println("\tAverage Net Profit: " + getAvgNetProfit(batchesArr, "smart_watch"));
    }


}
