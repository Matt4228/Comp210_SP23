package assn01;

import assn02.ProductBatch;

import java.util.LinkedList;

public class BatchesLL implements ProductionInt {
    public LinkedList<ProductBatch> batches = new LinkedList<>();
    public int length = 0;

    public boolean add(ProductBatch batch) {
        try {
            batches.add(batch);
            length++;
            return true;
        } catch(Exception e) {
            System.out.println("failed to add batch from: " + batch.getDate() + " " + batch.getTime());
            return false;
        }
    }

    public void search(String date, String time) {

    }

    public void printAll() {

    }

    public int getSize() {

    }
}
