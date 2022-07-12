import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Simulation {
    private static String str;
    private static int input;
    Set<Car> carList = new TreeSet<>();
    Parking parking = new Parking(20);
    List<Bill> bills = parking.bills;

    public void getSimulation() {

        for (int i = 0; i < 200; i++) carList.add(new Car(Car.generatePlate(), State.DRIVE));

        LocalDateTime ldt = LocalDateTime.now();
        for (LocalDateTime i = ldt; i.isBefore(ldt.plusDays(30)); i = i.plusMinutes(5)) {
            for (Car c : carList) {
                c.changeState(parking, i);
            }
            if (i.plusMinutes(5).equals(ldt.plusDays(30))) {
                carList.forEach(Car::setDriveState);
                parking.setTimeEnd(carList, i);
            }
        }
        parking.getCarsBills(carList);
    }

    public static void chooseAction() {
        System.out.println("\nChoose action: \n" +
                "1 - Print parking records\n" +
                "2 - Print cars bills\n");
//                "3 - Print bills histogram");
        while (true) {
            System.out.print("Enter number: ");
            try {
                str = new Scanner(System.in).nextLine();
                checkInput(str);
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void checkInput(String str) throws Exception {
        input = Integer.parseInt(str);
        if (input < 1 || input > 2)
            throw new Exception("No such action");
    }

    public static void getAction(Parking parking, Set<Car> carList) {
        switch (input) {
            case 1 -> parking.printParking(carList);
            case 2 -> parking.printBills();
//            case 3 -> parking.getAverageBillPerMonth();
        }
    }

//    public static void printBillsCanvas(List<Bill> bills) {
//        Canvas canvas = new Canvas(bills.size() + 2, 20);
//        canvas.drawRectangle(0, 1, canvas.getWidth(), canvas.getHeight(), "#");
//        String text = "Parking!";
//        canvas.printTextLine(canvas.getWidth() / 2 - text.length() / 2, 0, text);
//        for (int i = 0; i < bills.size(); i++) {
//            for (int j = 0; j < bills.get(i).getBill(); j++) {
//                canvas.setPixel(i + 2, (canvas.getHeight() - 1) - j, "*");
//            }
//        }
//    }

}
