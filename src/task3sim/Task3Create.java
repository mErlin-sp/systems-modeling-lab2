package task3sim;

public class Task3Create extends Task3Element {
    public Task3Create(double delay) {
        super(delay);
    }

    @Override
    public void outAct() {
        super.outAct();
        super.setTnext(super.getTcurr() + super.getDelay());
        super.getNextElement().inAct();
    }
}
