package task5sim;

import java.util.ArrayList;

public class Task5Model {
    private ArrayList<Task5Element> list = new ArrayList<>();
    double tnext, tcurr;
    int event;

    public Task5Model(ArrayList<Task5Element> elements) {
        list = elements;
        tnext = 0.0;
        event = 0;
        tcurr = tnext;
    }

    public void simulate(double time) {
        while (tcurr < time) {
            tnext = Double.MAX_VALUE;
            for (Task5Element e : list) {
                if (e.getTnext() < tnext) {
                    tnext = e.getTnext();
                    event = e.getId();

                }
            }
            System.out.println("\nIt's time for event in " +
                    list.get(event).getName() +
                    ", time = " + tnext);

            for (Task5Element e : list) {
                e.doStatistics(tnext - tcurr);
            }
            tcurr = tnext;
            for (Task5Element e : list) {
                e.setTcurr(tcurr);
            }
            list.get(event).outAct();
            for (Task5Element e : list) {
                if (e.getTnext() == tcurr) {
                    e.outAct();
                }
            }

            printInfo();
        }
        printResult();
    }

    public void printInfo() {
        for (Task5Element e : list) {
            e.printInfo();
        }
    }

    public void printResult() {
        System.out.println("\n-------------RESULTS-------------");
        for (Task5Element e : list) {
            e.printResult();
            if (e instanceof Task5Process) {
                Task5Process p = (Task5Process) e;
                System.out.println("mean length of queue = " +
                        p.getMeanQueue() / tcurr

                        + "\nfailure probability = " +
                        p.getFailure() / (double) p.getQuantity());

            }
        }
    }
}
