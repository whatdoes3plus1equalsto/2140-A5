
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

import javax.naming.PartialResultException;

import java.io.*;

public class NgManChunA5Q1 {
    public static void main(String[] args) throws Exception {
        // application
        int clock = 0;
        int docAvaTime = 0;
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
                if (docAvaTime <= clock) {
                    System.out.println("Doctor is available at time = " + clock);
                }
                clock = arrival;
                pat = new Patient(nextID++, sc.nextInt(), sc.nextInt());
                pq.insert(pat, arrival);
                line = br.readLine();
            }
            while (!pq.isEmpty()) {
                pat = pq.deleteMax();
                System.out.println(
                        "Patient " + pat.getID() + " in for treatment at time = " + clock + " " + pat.toString());
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
    private Patient[] heap; // the heap
    private int heapSize; // number of patients queueing

    public PriorityQueue() {
        // constructor
        // initialize the heap
        heap = new Patient[50];
        heapSize = 0;
    }

    public void insert(Patient pat, int arrival) {
        // insert a patient to the maxheap
        if (heapSize >= heap.length - 1) {
            // double the heap size
            doubleArraySize();
        }
        // insert
        heap[heapSize] = pat;
        heapSize++;
        // remap the heap
        siftUp(heapSize - 1);

        System.out.println("Patient " + pat.getID() + " arrived at time = " + arrival + " " + pat.toString());
    }

    public Patient deleteMax() {
        // return the top of the heap or remap the heap
        if (!isEmpty()) {
            Patient max = heap[0];
            heap[0] = heap[--heapSize];
            siftDown(0);
            return max;
        } else {
            return null;
        }
    }

    public Patient peek() {
        // return the head of the heap
        if (isEmpty()) {
            return null;
        } else {
            return heap[0];
        }
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }

    private void doubleArraySize() {
        // helper method to double the array size
        Patient[] temp = new Patient[heap.length];
        System.arraycopy(heap, 0, temp, 0, heap.length);
        heap = new Patient[heap.length * 2];
        System.arraycopy(temp, 0, heap, 0, temp.length);
    }

    private int parent(int child) {
        // return the index of its parent
        return (child - 1) / 2;
    }

    private int rightChild(int parent) {
        // return the index of the right child
        return 2 * parent + 2;
    }

    private int leftChild(int parent) {
        // return the index of the left child
        return 2 * parent + 1;
    }

    private void siftUp(int index) {
        // healper method to sift up
        Patient toSift = heap[index];
        int i = index;
        int parent = parent(index);

        while (i > 0 && heap[parent].getUR() < toSift.getUR()) {
            heap[i] = heap[parent]; // move the "hole" up to the parent
            i = parent;
            parent = parent(i);
        } // end while
        heap[i] = toSift; // put the sifted item into the correct position
    }

    private void siftDown(int index) {
        // helper method to sift down
        int left = leftChild(index);
        int right = rightChild(index);
        int toSift;
        if (right < heapSize && heap[right].getUR() > heap[left].getUR()) {
            toSift = right;
        } else {
            toSift = left;
        }

        if (toSift < heapSize && heap[toSift].getUR() > heap[index].getUR()) {
            Patient temp = heap[index];
            heap[index] = heap[toSift];
            heap[toSift] = temp;
            siftDown(toSift);
        }
    }
}// end of PriorityQueue