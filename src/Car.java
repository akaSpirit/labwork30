import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Car implements Comparable<Car> {
    private State stateObj;
    private final String plate;

    public Car(String plate, State stateObj) {
        this.stateObj = stateObj;
        this.plate = plate;
    }

    public void setStateObj(State stateObj) {
        this.stateObj = stateObj;
    }

    public String getPlate() {
        return plate;
    }

    public void setDriveState() {
        stateObj = State.DRIVE;
    }

    public static String generatePlate() {
        int regRandom = new Random().nextInt(0, 9);
        List<String> region = List.of("01KG", "02KG", "03KG", "04KG", "05KG", "06KG", "07KG", "08KG", "09KG");
        int number = new Random().nextInt(100, 1000);
        char firstLetter = (char) (new Random().nextInt(26) + 'A');
        char secondLetter = (char) (new Random().nextInt(26) + 'A');
        char thirdLetter = (char) (new Random().nextInt(26) + 'A');
        String letters = String.format("%s%s%s", firstLetter, secondLetter, thirdLetter);
        return String.format("%s %s %s", region.get(regRandom), number, letters);
    }

    public void changeState(Parking parking, LocalDateTime ldt) {
        stateObj.changeState(this, parking, ldt);
    }


    @Override
    public String toString() {
        return String.format("Car: %s | State: %s", plate, stateObj);
    }

    @Override
    public int compareTo(Car c) {
        return getPlate().compareTo(c.getPlate());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(plate, car.plate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(plate);
    }

}
