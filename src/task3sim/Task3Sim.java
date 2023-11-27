package task3sim;

import java.util.ArrayList;
import java.util.List;

public class Task3Sim {
    public static void main(String[] args) {
        Task3Create c = new Task3Create(1.0);
        Task3Process p1 = new Task3Process(1.0);
        Task3Process p2 = new Task3Process(2.0);
        Task3Process p3 = new Task3Process(3.0);
        System.out.printf("%d %d %d %d", c.getId(), p1.getId(), p2.getId(), p3.getId());
        c.setNextElement(p1);
        p1.setNextElement(p2);
        p2.setNextElement(p3);
        p1.setMaxqueue(5);
        p2.setMaxqueue(3);
        p3.setMaxqueue(1);
        c.setName("CREATOR");
        p1.setName("PROCESSOR 1");
        p2.setName("PROCESSOR 2");
        p3.setName("PROCESSOR 3");
        c.setDistribution("exp");
        p1.setDistribution("exp");
        p2.setDistribution("exp");
        p3.setDistribution("exp");
        List<Task3Element> list = new ArrayList<>();
        list.add(c);
        list.add(p1);
        list.add(p2);
        list.add(p3);
        Task3Model model = new Task3Model(list);
        model.simulate(1000.0);
    }
}