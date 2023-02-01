package assn01;

import assn02.ProductBatch;

public interface ProductionInt {
    boolean add(ProductBatch batch);
    void search(String date, String time);
    String printAll();
    int getSize();
}
