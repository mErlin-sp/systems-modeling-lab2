package task1_2sim;

public class Task12Sim {
    public static void main(String[] args) {
        System.out.println("Task 1-2");
        Task12Model model = new Task12Model(2, 1, 5);
        model.simulate(10000);
    }
}

