package task3sim;

public class Task3Sim {
    public static void main(String[] args) {
        System.out.println("Task 3");
        Task3Model model = new Task3Model(2, 1, 1, 1, 5);
        model.simulate(1000);
    }
}

