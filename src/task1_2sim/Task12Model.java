package task1_2sim;

import sim.FunRand;

public class Task12Model {
    private double tnext;
    private double tcurr;
    private double t0, t1;
    private final double delayCreate, delayProcess;
    private int numCreate, numProcess, failure;
    private int state;
    private final int maxqueue;
    private int queue;
    private double totalDeviceLoad;


    public Task12Model(double delay0, double delay1, int maxQ) {
        delayCreate = delay0;
        delayProcess = delay1;
        tnext = 0.0;
        tcurr = tnext;
        t0 = tcurr;
        t1 = Double.MAX_VALUE;
        maxqueue = maxQ;
        totalDeviceLoad = 0.0;
    }

    public void simulate(double timeModeling) {
        while (tcurr < timeModeling) {
            tnext = t0;
            int nextEvent = 0;
            if (t1 < tnext) {
                tnext = t1;
                nextEvent = 1;

            }
            double dt = tnext - tcurr;
            totalDeviceLoad += (state * dt);
            tcurr = tnext;
            switch (nextEvent) {
                case 0:
                    event0();
                    break;
                case 1:
                    event1();
            }
            printInfo();
        }
        printStatistic();
    }

    public void printStatistic() {
        System.out.println("numCreate: " + numCreate + " numProcess: " + numProcess + " failure: " + failure);
        System.out.println("Average Device Load: " + calculateAverageDeviceLoad());
    }

    public void printInfo() {
        System.out.println("t= " + tcurr + " state= " + state + " queue= " + queue);
    }

    public void event0() {
        t0 = tcurr + getDelayOfCreate();
        numCreate++;
        if (state == 0) {
            state = 1;
            t1 = tcurr + getDelayOfProcess();
        } else {
            if (queue < maxqueue)
                queue++;
            else
                failure++;
        }

    }

    public void event1() {
        t1 = Double.MAX_VALUE;
        state = 0;
        if (queue > 0) {
            queue--;
            state = 1;
            t1 = tcurr + getDelayOfProcess();
        }
        numProcess++;
    }

    private double getDelayOfCreate() {
        return FunRand.Exp(delayCreate);
    }

    private double getDelayOfProcess() {
        return FunRand.Exp(delayProcess);
    }

    public double calculateAverageDeviceLoad() {
        return totalDeviceLoad / tcurr;
    }
}
