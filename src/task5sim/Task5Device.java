package task5sim;

public class Task5Device extends Task5Element {
    private boolean busy;

    public Task5Device() {
        super(0); // Пристрій не має затримки
        busy = false;
    }

    public boolean isBusy() {
        return busy;
    }

    public void inAct() {
        busy = true;
    }

    public void outAct() {
        busy = false;
    }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("busy = " + this.isBusy());
    }

    @Override
    public void doStatistics(double delta) {
        // Можна додати статистику для пристрою, якщо необхідно
    }
}
