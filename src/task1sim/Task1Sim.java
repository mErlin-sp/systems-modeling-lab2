package task1sim;

public class Task1Sim {
    public static void main(String[] args) {
        Task1Model model = new Task1Model(2,1,5);
        model.simulate(10000000);
    }
}

