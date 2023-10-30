package Car;

import java.util.List;

public class CarFactoryTester {
    public static void main(String[] args) {
        String filename = "car_data.csv";

        CarFactory.writeCarsToCSV(CarFactory.generateCars(), filename);
        displayCars(CarFactory.loadCarsFromCSV(filename));
    }

    private static void displayCars(List<Car> cars) {
        for (Car car : cars) {
            System.out.println(car.getCarType() + ", " + car.getYear() + ", " + car.getColor() + ", " + car.getMiles());
        }
    }
}

