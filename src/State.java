import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public enum State {
    DRIVE {
        @Override
        public void changeState(Car car, Parking parking, LocalDateTime ldt) {
            if (new Random().nextInt(1, 101) <= 3) {
                car.setStateObj(PARKING);
                Map<String, ArrayList<Record>> records = parking.getCarRecords();
                ArrayList<Record> rec;
                if (records.get(car.getPlate()) != null) rec = records.get(car.getPlate());
                else rec = new ArrayList<>();
                rec.add(new Record(ldt));
                records.put(car.getPlate(), rec);
                parking.setCarRecords(records);
            }
        }
    },
    PARKING {
        @Override
        public void changeState(Car car, Parking parking, LocalDateTime ldt) {
            if (new Random().nextInt(1, 101) <= 3) {
                car.setStateObj(DRIVE);
                setTimeEnd(car, parking, ldt);

            }
        }
    };

    public abstract void changeState(Car car, Parking parking, LocalDateTime ldt);

    public void setTimeEnd(Car car, Parking parking, LocalDateTime ldt) {
        Map<String, ArrayList<Record>> carRecords = parking.getCarRecords();
        ArrayList<Record> recordCar = carRecords.get(car.getPlate());
        Record rec = recordCar.get(recordCar.size() - 1);
        rec.setTimeEnd(ldt);
        recordCar.set(recordCar.size() - 1, rec);
        carRecords.put(car.getPlate(), recordCar);
        parking.setCarRecords(carRecords);
    }
}
