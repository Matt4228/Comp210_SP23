package assn05;


public class Main {

    public static void main(String[] args) {
        /*
        Part 1 - Write some tests to convince yourself that your code for Part 1 is working
         */
        SimpleEmergencyRoom simp = new SimpleEmergencyRoom();
        fillERless(simp);
        int s = simp.size();
        for(int i = 0; i < s; i++) {
            Patient p = simp.dequeue();
            System.out.println("Value: " + p.getValue()
                    + ", Priority: " + p.getPriority());
        }







       /*
        Part 2 - Write some tests to convince yourself that your code for Part 2 is working
         */





        /*
        Part 3
         */


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


        MaxBinHeapER binHeap = new MaxBinHeapER();
        fillER(binHeap);

        // Code for (2) Here

        return results;
    }



}



