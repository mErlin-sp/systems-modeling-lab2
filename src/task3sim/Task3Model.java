package task3sim;

import sim.FunRand;

public class Task3Model {
    private double tnext;
    private double tcurr;
    private double t0, t1;
    private final double delayCreate, delayProcess1, delayProcess2, delayProcess3;
    private int numCreate, numProcess, failure;
    private int state;
    private final int maxqueue;
    private int queue;
    private final double totalDeviceLoad;


    public Task3Model(double delay0, double delay1, double delay2, double delay3, int maxQ) {
        delayCreate = delay0;
        delayProcess1 = delay1;
        delayProcess2 = delay2;
        delayProcess3 = delay3;
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
            tcurr = tnext;
            switch (nextEvent) {
                case 0:
                    eventCreate();
                    break;
                case 1:
                    eventDispose();
            }
            printInfo();
        }
        printStatistic();
    }

    public void printStatistic() {
        System.out.println("numCreate: " + numCreate + " numProcess: " + numProcess + " failure: " + failure);
    }

    public void printInfo() {
        System.out.println("t= " + tcurr + " state= " + state + " queue= " + queue);
    }

    public void eventCreate() {
        t0 = tcurr + getDelayOfCreate();
        numCreate++;
        if (state == 0) {
            state = 1;
            t1 = tcurr + getDelayOfProcess1() + getDelayOfProcess2() + getDelayOfProcess3();
        } else {
            if (queue < maxqueue)
                queue++;
            else
                failure++;
        }
    }

    public void eventDispose() {
        t1 = Double.MAX_VALUE;
        state = 0;
        if (queue > 0) {
            queue--;
            state = 1;
            t1 = tcurr + getDelayOfProcess1() + getDelayOfProcess2() + getDelayOfProcess3();
        }
        numProcess += 3;
    }

    private double getDelayOfCreate() {
        return FunRand.Exp(delayCreate);
    }

    private double getDelayOfProcess1() {
        return FunRand.Exp(delayProcess1);
    }

    private double getDelayOfProcess2() {
        return FunRand.Exp(delayProcess2);
    }

    private double getDelayOfProcess3() {
        return FunRand.Exp(delayProcess3);
    }
}
