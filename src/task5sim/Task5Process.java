package task5sim;

import java.util.ArrayList;
import java.util.List;

public class Task5Process extends Task5Element {
    private int queue, maxqueue, failure;
    private double meanQueue;
    private List<Task5Device> devices = new ArrayList<>();

    public Task5Process(double delay, int numDevices) {
        super(delay);
        queue = 0;
        maxqueue = Integer.MAX_VALUE;
        meanQueue = 0.0;

        for (int i = 0; i < numDevices; i++) {
            devices.add(new Task5Device());
        }
    }

//    @Override
//    public void inAct() {
//        if (super.getState() == 0) {
//            super.setState(1);
//            super.setTnext(super.getTcurr() + super.getDelay());
//        } else {
//            if (getQueue() < getMaxqueue()) {
//                setQueue(getQueue() + 1);
//            } else {
//                failure++;
//            }
//        }
//    }

    @Override
    public void inAct() {
        if (super.getState() == 0) {
            super.setState(1);
            super.setTnext(super.getTcurr() + super.getDelay());

            // Assign the inAct event to an available device
            for (Task5Device device : devices) {
                if (!device.isBusy()) {
                    device.inAct();
                    return;
                }
            }

            // If all devices are busy, enqueue the task
            enqueueTask();
        } else {
            enqueueTask();
        }
    }

    private void enqueueTask() {
        if (getQueue() < getMaxqueue()) {
            setQueue(getQueue() + 1);
        } else {
            failure++;
        }
    }

//    @Override
//    public void outAct() {
//        super.outAct();
//        super.setTnext(Double.MAX_VALUE);
//        super.setState(0);
//        if (getQueue() > 0) {
//            setQueue(getQueue() - 1);
//            super.setState(1);
//            super.setTnext(super.getTcurr() + super.getDelay());
//        }
//    }

    @Override
    public void outAct() {
        super.outAct();
        super.setTnext(Double.MAX_VALUE);
        super.setState(0);

        // Process outAct for each device
        for (Task5Device device : devices) {
            if (device.isBusy()) {
                device.outAct();
                if (getQueue() > 0) {
                    setQueue(getQueue() - 1);
                    super.setState(1);
                    super.setTnext(super.getTcurr() + super.getDelay());
                    return;
                }
            }
        }
    }

    public int getFailure() {
        return failure;
    }

    public int getQueue() {
        return queue;
    }

    public void setQueue(int queue) {
        this.queue = queue;
    }

    public int getMaxqueue() {
        return maxqueue;
    }

    public void setMaxqueue(int maxqueue) {
        this.maxqueue = maxqueue;
    }

    @Override
    public void printInfo() {

        super.printInfo();
        System.out.println("failure = " + this.getFailure());
    }

    @Override
    public void doStatistics(double delta) {
        meanQueue = getMeanQueue() + queue * delta;
        System.out.println("Mean Queue: " + meanQueue);
    }

    public double getMeanQueue() {
        return meanQueue;
    }
}
