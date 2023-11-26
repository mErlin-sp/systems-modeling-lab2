package task6sim;

import java.util.ArrayList;

public class Task6Model {
    private ArrayList<Task6Element> list;
    double tnext, tcurr;
    int event;

    public Task6Model(ArrayList<Task6Element> elements) {
        list = elements;
        tnext = 0.0;
        event = 0;
        tcurr = tnext;
    }

    public void simulate(double time) {
        while (tcurr < time) {
            tnext = Double.MAX_VALUE;
            for (Task6Element e : list) {
                if (e.getTnext() < tnext) {
                    tnext = e.getTnext();
                    event = e.getId();

                }
            }
            System.out.println("\nIt's time for event in " +
                    list.get(event).getName() +
                    ", time = " + tnext);

            for (Task6Element e : list) {
                e.doStatistics(tnext - tcurr);
            }
            tcurr = tnext;
            for (Task6Element e : list) {
                e.setTcurr(tcurr);
            }
            list.get(event).outAct();
            for (Task6Element e : list) {
                if (e.getTnext() == tcurr) {
                    e.outAct();
                }
            }

            printInfo();
        }
        printResult();
    }

    public void printInfo() {
        for (Task6Element e : list) {
            e.printInfo();
        }
    }

    public void printResult() {
        System.out.println("\n-------------RESULTS-------------");
        for (Task6Element e : list) {
            e.printResult();
            if (e instanceof Task6Process) {
                Task6Process p = (Task6Process) e;
                System.out.println("mean length of queue = " +
                        p.getMeanQueue() / tcurr

                        + "\nfailure probability = " +
                        p.getFailure() / (double) p.getQuantity());

            }
        }
    }
}
