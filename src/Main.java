public class Main {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        Simulation sml = new Simulation();
        sml.getSimulation();
        while (true) {
            Simulation.chooseAction();
            Simulation.getAction(sml.parking, sml.carList);
        }
    }
}
