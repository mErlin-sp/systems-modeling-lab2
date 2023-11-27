package task3sim;

import java.util.List;

public class Task3Model {
    private List<Task3Element> list;
    double tnext, tcurr;
    int event;

    public Task3Model(List<Task3Element> elements) {
        list = elements;
        tnext = 0.0;
        event = 0;
        tcurr = tnext;
    }

    public void simulate(double time) {
        while (tcurr < time) {
            tnext = Double.MAX_VALUE;
            for (Task3Element e : list) {
                if (e.getTnext() < tnext) {
                    tnext = e.getTnext();
                    event = e.getId();

                }
            }
            System.out.println("\nIt's time for event in " +
                    list.get(event).getName() +
                    ", time = " + tnext);

            for (Task3Element e : list) {
                e.doStatistics(tnext - tcurr);
            }
            tcurr = tnext;
            for (Task3Element e : list) {
                e.setTcurr(tcurr);
            }
            list.get(event).outAct();
            for (Task3Element e : list) {
                if (e.getTnext() == tcurr) {
                    e.outAct();
                }
            }

            printInfo();
        }
        printResult();
    }

    public void printInfo() {
        for (Task3Element e : list) {
            e.printInfo();
        }
    }

    public void printResult() {
        System.out.println("\n-------------RESULTS-------------");
        for (Task3Element e : list) {
            e.printResult();
            if (e instanceof Task3Process) {
                Task3Process p = (Task3Process) e;
                System.out.println("mean length of queue = " +
                        p.getMeanQueue() / tcurr

                        + "\nfailure probability = " +
                        p.getFailure() / (double) p.getQuantity());

            }
        }
    }
}
