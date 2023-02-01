package assn02;

import java.util.LinkedList;

public class BatchesLL implements ProductionInt {
    public LinkedList<ProductBatch> batches = new LinkedList<>();
    public double highest = 0;
    public double lowest = 0;
    public int length = 0;

    public BatchesLL() {

    }

    public boolean add(ProductBatch batch) {
        double fee = batch.getFee();
        try {
            if(length == 0) {
                batches.add(batch);
                highest=batch.getFee();
                lowest=batch.getFee();
            }
            else {
                if(fee > batches.getFirst().getFee()) {
                    batches.addFirst(batch);
                    highest = fee;
                    if(length == 1) {
                        lowest = batches.getLast().getFee();
                    }
                    length++;
                }
                else if (fee <= highest && fee > lowest){
                    int i = 0;
                    while(batches.iterator().hasNext()) {
                        if(batches.get(i+1).getFee() < fee) {
                            batches.add(i, batch);
                            length++;
                            break;
                        }
                        i++;
                    }
                }
                else {
                    batches.add(batch);
                    length++;
                }

            }

            return true;
        } catch(Exception e) {
            System.out.println("failed to add batch from: " + batch.getDate() + " " + batch.getTime());
            return false;
        }
    }

    public void search(String date, String time) {

    }

    public String printAll() {
        String output = "";
        for(int i = 0; i < length; i++) {
            output += ("\ni tried " + i);
            output += ("\n" +batches.get(i).printBatch());
        }
        return output;
    }

    public int getSize() {
        return length;
    }
}
