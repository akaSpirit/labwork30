import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public class Parking {
    final int parkingSize;
    List<Car> parkingList = new ArrayList<>();
    List<Bill> bills = new ArrayList<>();

    private Map<String, ArrayList<Record>> carRecords = new HashMap<>();

    public Map<String, ArrayList<Record>> getCarRecords() {
        return carRecords;
    }

    public void setCarRecords(Map<String, ArrayList<Record>> carRecords) {
        this.carRecords = carRecords;
    }

    public Parking(int parkingSize) {
        this.parkingSize = parkingSize;
    }

//    public boolean park(Car car) {
//        if (parkingList.size() >= parkingSize || parkingList.contains(car)) return false;
//        parkingList.add(car);
//        return true;
//    }
//
//    public void leave(Car car) {
//        parkingList.remove(car);
//    }

    public void setTimeEnd(Set<Car> cars, LocalDateTime timeEnd) {
        ArrayList<Record> rec;
        for (Car car : cars) {
            if (carRecords.containsKey(car.getPlate())) {
                rec = carRecords.get(car.getPlate());
                rec.get(rec.size() - 1).setTimeEnd(timeEnd);
            }
        }
    }

    public void printParking(Set<Car> cars) {
        for (Car car : cars) {
            if (carRecords.containsKey(car.getPlate())) {
                ArrayList<Record> cr = carRecords.get(car.getPlate());
                System.out.printf("\n                 Car: %s%n", car.getPlate());
                System.out.println("+-------------+---------------------+---------------------+");
                System.out.println("|  Parking №  |      Time Start     |       Time End      |");
                System.out.println("+-------------+---------------------+---------------------+");
                for (int i = 0; i < cr.size(); i++) {
                    System.out.printf("|%7s      |", i + 1);
                    cr.get(i).printRecord();
                }
            }
        }
    }

    public double getCarBill(ArrayList<Record> recordCar) {
        double bill = 0;
        long minute = 0;
        for (Record r : recordCar) {
            LocalDateTime start = r.getTimeStart();
            LocalDateTime end = r.getTimeEnd();
            for (LocalDateTime i = start; i.isBefore(end); i = i.plusMinutes(5)) {
                if (i.toLocalTime().isAfter(LocalTime.of(9, 0)) && i.toLocalTime().isBefore(LocalTime.of(21, 0))) {
                    minute += 5;
                    if (minute >= 30) {
                        bill = minute * 0.02;
                    }
                }
            }
        }
        return bill;
    }

    public void getCarsBills(Set<Car> cars) {
        ArrayList<Record> recordCar;
        for (Car car : cars) {
            if (carRecords.containsKey(car.getPlate())) {
                recordCar = carRecords.get(car.getPlate());
                bills.add(new Bill(car.getPlate(), getCarBill(recordCar)));
            }
        }
    }

    public void printBills() {
        System.out.println("\n+-------+--------------+----------+\n" +
                "| Car № |    Plate     |  Bill($) |\n" +
                "+-------+--------------+----------+");
        for (int i = 0; i < bills.size(); i++) {
            System.out.printf("| %4s  | %-12s |  %.2f  |%n", i + 1, bills.get(i).getPlate(), bills.get(i).getBill());
            System.out.println("+-------+--------------+----------+");
        }
    }

    public double getAverageBillPerMonth() {
        double dailyBill = 0;
        double averageBill = 0;
        for (int i = 0; i < bills.size(); i++) {
            dailyBill += bills.get(i).getBill();
        }
        return averageBill = dailyBill / 30;
    }

}
