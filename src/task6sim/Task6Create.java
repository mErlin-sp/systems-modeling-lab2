package task6sim;

public class Task6Create extends Task6Element {
    public Task6Create(double delay) {
        super(delay);
    }

    @Override
    public void outAct() {
        super.outAct();
        super.setTnext(super.getTcurr() + super.getDelay());
        for (Task6Element element : super.getNextElements()) {
            element.inAct();
        }
    }
}
