package task6sim;

import java.util.ArrayList;

public class Task6Sim {
    public static void main(String[] args) {
        System.out.println("Task 6");
        Task6Create c = new Task6Create(2.0);
        Task6Process p1 = new Task6Process(1.0);
        Task6Process p2 = new Task6Process(2.0);
        Task6Process p3 = new Task6Process(3.0);
        System.out.println("id0 = " + c.getId() + " id1=" + p1.getId() + " id2=" + p2.getId() + " id3=" + p3.getId());
        c.addNextElement(p1);
        p1.addNextElement(p2);
        p1.addNextElement(p3);
        p3.addNextElement(p1);
        p1.setMaxqueue(5);
        p2.setMaxqueue(5);
        p3.setMaxqueue(5);
        c.setName("CREATOR");
        p1.setName("PROCESSOR 1");
        p2.setName("PROCESSOR 2");
        p3.setName("PROCESSOR 3");
        c.setDistribution("exp");
        p1.setDistribution("exp");
        p2.setDistribution("exp");
        p3.setDistribution("exp");
        ArrayList<Task6Element> list = new ArrayList<>();
        list.add(c);
        list.add(p1);
        list.add(p2);
        list.add(p3);
        Task6Model model = new Task6Model(list);
        model.simulate(10000.0);
    }
}