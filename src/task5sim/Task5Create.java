package task5sim;

public class Task5Create extends Task5Element {
    public Task5Create(double delay) {
        super(delay);
    }

    @Override
    public void outAct() {
        super.outAct();
        super.setTnext(super.getTcurr() + super.getDelay());
        super.getNextElement().inAct();
    }
}
