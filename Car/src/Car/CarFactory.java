package Car;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CarFactory {
    private static final String[] CAR_TYPES = {"Sedan", "SUV", "Truck", "Sports Car"};
    private static final String[] COLORS = {"Red", "Blue", "Green", "White", "Black", "Silver"};
    private static final int NUM_CARS = 1000;

    public static List<Car> generateCars() {
      List<Car> cars = new ArrayList<>();

      Random random = new Random();

      for (int i = 0; i < NUM_CARS; i++) {
          String carType = CAR_TYPES[random.nextInt(CAR_TYPES.length)];
          int year = 1973 + random.nextInt(50); // Year from 1973
          String color = randomColor();
          int miles = random.nextInt(250001); // Miles between 0 and 250000

          Car car = new Car();
          car.setYear(year);
          car.setColor(color);
          car.setMiles(miles);

          cars.add(car);
      }

      return cars;
  }

    private static String randomColor() {
      Random random = new Random();
      if (random.nextDouble() < 0.2) {
          return "Red"; // Make red slightly more popular
      } else {
          return COLORS[random.nextInt(COLORS.length)];
      }
  }

    public static void writeCarsToCSV(List<Car> cars, String filename) {
      try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
          // Write the header
          writer.write("CarType,Year,Color,Miles\n");

          // Write each car to the CSV file
          for (Car car : cars) {
              writer.write(car.getCarType() + "," + car.getYear() + "," + car.getColor() + "," + car.getMiles() + "\n");
          }

          System.out.println("Car data has been written to " + filename);
      } catch (IOException e) {
          e.printStackTrace();
      }
  }

    public static List<Car> loadCarsFromCSV(String filename) {
      List<Car> cars = new ArrayList<>();

      try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
          String line;
          boolean isFirstLine = true;
          while ((line = reader.readLine()) != null) {
              if (isFirstLine) {
                  isFirstLine = false;
                  continue;
              }
              String[] parts = line.split(",");
              if (parts.length == 4) {
                  String carType = parts[0].trim();
                  int year = Integer.parseInt(parts[1].trim());
                  String color = parts[2].trim();
                  int miles = Integer.parseInt(parts[3].trim());

                  Car car = new Car();
                  car.setCarType(carType);
                  car.setYear(year);
                  car.setColor(color);
                  car.setMiles(miles);

                  cars.add(car);
              }
          }
      } catch (IOException e) {
          e.printStackTrace();
      }

      return cars;
  }
}

