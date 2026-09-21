import java.util.ArrayList;
import java.util.Scanner;

public class CarRentalManager {
    private ArrayList<Car> cars;
    private ArrayList<Customer> customers;
    private ArrayList<Rental> rentals;
    private Scanner input;

    public CarRentalManager() {
        cars = new ArrayList<>();
        customers = new ArrayList<>();
        rentals = new ArrayList<>();
        input = new Scanner(System.in);
    }

    public static void main(String[] args) {
        CarRentalManager manager = new CarRentalManager();
        manager.run();
    }

    public void run() {
        while (true) {
            System.out.println("=== Car Rental Management System ===");
            System.out.println("1. Add Car");
            System.out.println("2. Register Customer");
            System.out.println("3. Rent Car");
            System.out.println("4. Return Car");
            System.out.println("5. Display Cars");
            System.out.println("6. Display Customers");
            System.out.println("7. Display Rentals");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");

            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    addCar();
                    break;
                case 2:
                    registerCustomer();
                    break;
                case 3:
                    rentCar();
                    break;
                case 4:
                    returnCar();
                    break;
                case 5:
                    displayCars();
                    break;
                case 6:
                    displayCustomers();
                    break;
                case 7:
                    displayRentals();
                    break;
                case 8:
                    System.out.println("Exiting system...");
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void addCar() {
        System.out.print("Enter car ID: ");
        int carId = input.nextInt();
        input.nextLine();

        System.out.print("Enter brand: ");
        String brand = input.nextLine();

        System.out.print("Enter model: ");
        String model = input.nextLine();

        System.out.print("Enter year: ");
        int year = input.nextInt();
        input.nextLine();

        cars.add(new Car(carId, brand, model, year));
        System.out.println("Car added successfully.");
    }

    private void registerCustomer() {
        System.out.print("Enter customer ID: ");
        int customerId = input.nextInt();
        input.nextLine();

        System.out.print("Enter customer name: ");
        String name = input.nextLine();

        System.out.print("Enter phone number: ");
        String phoneNumber = input.nextLine();

        customers.add(new Customer(customerId, name, phoneNumber));
        System.out.println("Customer registered successfully.");
    }

    private void rentCar() {
        System.out.print("Enter car ID to rent: ");
        int carId = input.nextInt();

        System.out.print("Enter customer ID: ");
        int customerId = input.nextInt();

        input.nextLine();

        Car car = findCarById(carId);
        Customer customer = findCustomerById(customerId);

        if (car != null && customer != null && car.isAvailable()) {
            car.setAvailable(false);
            rentals.add(new Rental(car, customer));
            System.out.println("Car rented successfully.");
        } else {
            System.out.println("Rental failed. Car not available or invalid customer/car.");
        }
    }

    private void returnCar() {
        System.out.print("Enter car ID to return: ");
        int carId = input.nextInt();
        input.nextLine();

        Car car = findCarById(carId);

        if (car != null && !car.isAvailable()) {
            car.setAvailable(true);
            System.out.println("Car returned successfully.");
        } else {
            System.out.println("Return failed. Car not found or already available.");
        }
    }

    private void displayCars() {
        for (Car car : cars) {
            System.out.println(car);
        }
    }

    private void displayCustomers() {
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }

    private void displayRentals() {
        for (Rental rental : rentals) {
            System.out.println(rental);
        }
    }

    private Car findCarById(int carId) {
        for (Car car : cars) {
            if (car.getCarId() == carId) {
                return car;
            }
        }
        return null;
    }

    private Customer findCustomerById(int customerId) {
        for (Customer customer : customers) {
            if (customer.getCustomerId() == customerId) {
                return customer;
            }
        }
        return null;
    }
}

class Car {
    private int carId;
    private String brand;
    private String model;
    private int year;
    private boolean available;

    public Car(int carId, String brand, String model, int year) {
        this.carId = carId;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.available = true;
    }

    public int getCarId() { return carId; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }

    @Override
    public String toString() {
        return "Car{" +
                "carId=" + carId +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", available=" + available +
                '}';
    }
}

class Customer {
    private int customerId;
    private String name;
    private String phoneNumber;

    public Customer(int customerId, String name, String phoneNumber) {
        this.customerId = customerId;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public int getCustomerId() { return customerId; }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}

class Rental {
    private Car car;
    private Customer customer;

    public Rental(Car car, Customer customer) {
        this.car = car;
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Rental{" +
                "car=" + car +
                ", customer=" + customer +
                '}';
    }
}