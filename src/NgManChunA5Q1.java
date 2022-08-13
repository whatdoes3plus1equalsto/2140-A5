
/*
 * NgManChunA5Q1.java
 * 
 * COMP 2140 SECTION D01
 * ASSIGNMENT   Assignment #5, question #1
 * @author      Man Chun Ng, 7924340
 * @version     12-August-2022
 * 
 * PURPOSE:     Building a hospital ER simulation by reading patients.txt
 */
import java.util.Scanner;
import java.io.*;

public class NgManChunA5Q1 {
    public static void main(String[] args) throws Exception {
        // application
        int clock = 0;
        boolean docAva = true;
        int nextID = 1;
        String file = "patients.txt";
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr); // wrapping
            String line = br.readLine();
            Scanner sc = new Scanner(line);
            PriorityQueue pq = new PriorityQueue();
            Patient pat;
            int arrival = 0;
            while (line != null) { // until EOF
                sc = new Scanner(line);
                arrival = sc.nextInt();
                pat = new Patient(nextID++, sc.nextInt(), sc.nextInt());
                pq.insert(pat);
                System.out.println("Patient " + pat.getID() + " arrived at time = " + arrival + " " + pat.toString());
                line = br.readLine();
            }
            sc.close();
            br.close();
            fr.close();
        } catch (IOException e) {
            // exception prevention
            e.printStackTrace();
            System.out.println("\nPlease make sure the file exist and try again.");
        }
    }

}// end of NgManChunA5Q1

class Patient {
    // Patoent class
    // storing information of a patient
    private int id; // unique id for patient, start with 1
    private int urgency; // urgent level, 1-10, 1 is the least 10 is the most, used for the priority
    private int time; // treatment time, represnt in minutes

    public Patient(int id, int urgency, int time) {
        // constructor
        this.id = id;
        this.urgency = urgency;
        this.time = time;
    }

    public String toString() {
        // toString
        return "with urgency = " + urgency + " and treatment time = " + time + ".";
    }

    // getter//

    public int getID() {
        return id;
    }

    public int getUR() {
        return urgency;
    }

    public int getTime() {
        return time;
    }
}// end of Patient class

class Node {
    // Node class

}// end of Node class

class PriorityQueue {
    // PriorityQueue class
    // Max heap
    public PriorityQueue() {
        // constructor
    }

    public void insert(Patient pat) {

    }

    public Patient deleteMax() {
        return null;
    }

    public Patient peek() {
        return null;
    }

    public boolean isEmpty() {
        return true;
    }
}// end of PriorityQueue