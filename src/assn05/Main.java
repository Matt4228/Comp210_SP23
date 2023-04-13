package assn05;


import java.util.Random;

public class Main {

    public static void main(String[] args) {
        /*
        Part 1 - Write some tests to convince yourself that your code for Part 1 is working
         */
        SimpleEmergencyRoom simp = new SimpleEmergencyRoom();
        fillERless(simp);
        System.out.println(simp.size());
        int s = simp.size();
        for(int i = 0; i < s; i++) {
            Patient p = simp.dequeue();
            System.out.println("Value: " + p.getValue()
                    + ", Priority: " + p.getPriority());
        }







       /*
        Part 2 - Write some tests to convince yourself that your code for Part 2 is working
         */
        MaxBinHeapER er = new MaxBinHeapER();
        fillERless(er);

        int cs = er.size();
        for(int i = 0; i < cs; i++) {
            int pri = (int) er.testDequeue();
            System.out.println("Priority: " + pri);
        }
        System.out.println(er.size() + "\n\n\n");






        /*
        Part 3
         */
        MaxBinHeapER transfer = new MaxBinHeapER(makePatients());
        Prioritized[] arr = transfer.getAsArray();
        for(int i = 0; i < transfer.size(); i++) {
            System.out.println("Value: " + arr[i].getValue()
                    + ", Priority: " + arr[i].getPriority());
        }


        System.out.println("\n\n\n");
        double[] results = compareRuntimes();
        for(int i = 0; i < 4; i++) {
            System.out.println(i + ": " + results[i]);
        }


    }

    public static void fillER(MaxBinHeapER complexER) {
        for(int i = 0; i < 100000; i++) {
            complexER.enqueue(i);
        }
    }


    public static void fillER(SimpleEmergencyRoom simpleER) {
        for(int i = 0; i < 100000; i++) {
            simpleER.addPatient(i);
        }
    }

    public static void fillERless(SimpleEmergencyRoom simpleER) {
        for(int i = 0; i < 20; i++) {
            simpleER.addPatient(i);
        }
    }

    public static void fillERless(MaxBinHeapER complexER) {
        for(int i = 0; i < 10; i++) {
            Random random = new Random();
            int priority = (Integer) new java.lang.Integer(random.nextInt(100));
            complexER.enqueue(i, priority);
        }
    }

    public static Patient[] makePatients() {
        Patient[] patients = new Patient[10];
        for(int i = 0; i < 10; i++) {
            patients[i] = new Patient(i);
        }
        return patients;
    }
    
    public static double[] compareRuntimes() {
    	// Array which you will populate as part of Part 4
    	double[] results = new double[4];
    	
        SimpleEmergencyRoom simplePQ = new SimpleEmergencyRoom();
        fillER(simplePQ);

        // Code for (1) Here
        double simpleStart = System.nanoTime();
        for(int i = 0; i < simplePQ.size(); i++) {
            Patient p = simplePQ.dequeue();
        }
        double simpleEnd = System.nanoTime();

        results[0] = simpleEnd - simpleStart;
        results[1] = results[0]/100000;


        MaxBinHeapER binHeap = new MaxBinHeapER();
        fillER(binHeap);

        // Code for (2) Here
        double complexStart = System.nanoTime();
        for(int i = 0; i < binHeap.size(); i++) {
            binHeap.dequeue();
        }
        double complexEnd = System.nanoTime();

        results[2] = complexEnd-complexStart;
        results[3] = results[2]/100000;

        return results;
    }



}



