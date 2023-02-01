package assn02;

import assn01.BatchesLL;

import java.util.LinkedList;
import java.util.Iterator;
import java.util.Scanner;


public class JavaWarmUp {
    static BatchesLL batches = new BatchesLL();

    public static void main(String[] args) {
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
            batches.add(nextBatch);
        }

        System.out.println(" 1 " + batches.printAll());
        //Iterator i = batches.iterator();
        //do {
        //    System.out.println(((ProductBatch) i));
        //    System.out.println(((ProductBatch) i).printBatch());
        //} while (i.hasNext());
        //prints highest unit assembly fee
        //prints lowest per unit assembly fee
        //statistic of phone
        //statistic of laptop
        //statistic of smartwatch
;

    }
}
