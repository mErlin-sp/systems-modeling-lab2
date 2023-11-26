package task5sim;

import java.util.ArrayList;

public class Task5Sim {
    public static void main(String[] args) {
        System.out.println("Task5. Multiple devices");
        Task5Create c = new Task5Create(2.0);
        Task5Process p = new Task5Process(1.0, 10);
        System.out.println("id0 = " + c.getId() + " id1=" +
                p.getId());
        c.setNextElement(p);
        p.setMaxqueue(5);
        c.setName("CREATOR");
        p.setName("PROCESSOR");
        c.setDistribution("exp");
        p.setDistribution("exp");
        ArrayList<Task5Element> list = new ArrayList<>();
        list.add(c);
        list.add(p);
        Task5Model model = new Task5Model(list);
        model.simulate(1000.0);
    }
}