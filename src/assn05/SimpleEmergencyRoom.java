package assn05;

import java.util.ArrayList;
import java.util.List;

public class SimpleEmergencyRoom {
    private List<Patient> patients;

    public SimpleEmergencyRoom() {
        patients = new ArrayList<>();
    }

    // TODO: dequeue
    public Patient dequeue() {
    	Patient nextPatient = patients.get(0);
    	for(int i = 1; i < patients.size(); i++) {
    	    if(patients.get(i).getPriority().compareTo(nextPatient.getPriority()) > 0 ) {
    	        nextPatient = patients.get(i);
    	        patients.remove(i);
            }
        }
    	return nextPatient;
    }

    public <V, P> void addPatient(V value, P priority) {
        Patient patient = new Patient(value, (Integer) priority);
        patients.add(patient);
    }

    public <V> void addPatient(V value) {
        Patient patient = new Patient(value);
        patients.add(patient);
    }

    public List getPatients() {
        return patients;
    }

    public int size() {
        return patients.size();
    }

}
