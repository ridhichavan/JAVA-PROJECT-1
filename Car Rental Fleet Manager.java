import java.util.Scanner;

public class CarRentalFleetManager {

 // MODULE 1: Primitive arrays — variables & data types
 static int[] carId = {101, 102, 103, 104, 105};
 static String[] carName = {"Swift","Baleno","Creta","City","Nexon"};
 static int[] pricePerDay = {1500, 1700, 2500, 2200, 1800};
 static boolean[] available = {true, true, false, true, true};
 static int[] rentedDays = {0, 0, 0, 0, 0};

 // MODULE 3: Method — display all fleet cars
 static void displayCars() {
 System.out.println("\n===== AVAILABLE CARS =====");
 for (int i = 0; i < carId.length; i++) { // MODULE 2: for loop
 System.out.println("ID: " + carId[i] + " | " + carName[i]
 + " | Rs." + pricePerDay[i] + "/day | "
 + (available[i] ? "Available" : "Rented")); // MODULE 2: ternary
 }
 }

 // MODULE 3: Method overloading — search by ID (linear search)
 static int searchCar(int id) {
 for (int i = 0; i < carId.length; i++) {
 if (carId[i] == id) return i; // MODULE 2: if
 }
 return -1;
 }

 // MODULE 3: Method overloading — search by name
 static int searchCar(String name) {
 for (int i = 0; i < carName.length; i++) {
 if (carName[i].equalsIgnoreCase(name)) return i;
 }
 return -1;
 }

 // MODULE 3: Method overloading — calculate rent (no discount)
 static int calculateRent(int price, int days) {
 return price * days; // MODULE 1: operator *
 }

 // MODULE 3: Method overloading — calculate rent (with discount)
 static int calculateRent(int price, int days, int discountPercent) {
 int total = price * days;
 int discount = (total * discountPercent) / 100;
 return total - discount;
 }

 // MODULE 3: Method — rent a car
 static void rentCar(Scanner sc) {
 System.out.print("Enter Car ID: ");
 int id = sc.nextInt(); // MODULE 1: Scanner I/O
 int index = searchCar(id);
 if (index == -1) { // MODULE 2: if-else
 System.out.println("Car not found!");
 return;
 }
 if (available[index]) { // MODULE 2: nested if
 System.out.print("Enter number of days: ");
 int days = sc.nextInt();
 if (days > 0) {
 available[index] = false;
 rentedDays[index] = days;
 int cost = calculateRent(pricePerDay[index], days);
 System.out.println("Car rented! Total Cost: Rs." + cost);
 } else { System.out.println("Invalid days!"); }
 } else { System.out.println("Car is not available!"); }
 }

 // MODULE 3: Method — return a car
 static void returnCar(Scanner sc) {
 System.out.print("Enter Car ID: ");
 int id = sc.nextInt();
 int index = searchCar(id);
 if (index != -1 && !available[index]) {
 available[index] = true;
 rentedDays[index] = 0;
 System.out.println("Car returned successfully!");
 } else { System.out.println("Invalid ID or car not rented!"); }
 }

 // MODULE 3: Sorting — Bubble Sort on parallel arrays
 static void sortCarsByPrice() {
 for (int i = 0; i < carId.length - 1; i++) {
 for (int j = 0; j < carId.length - i - 1; j++) {
 if (pricePerDay[j] > pricePerDay[j + 1]) {
 // Swap all parallel arrays
 int tmpP = pricePerDay[j]; pricePerDay[j] = pricePerDay[j+1]; pricePerDay[j+1] = tmpP;
 int tmpI = carId[j]; carId[j] = carId[j+1]; carId[j+1] = tmpI;
 String tmpN = carName[j]; carName[j] = carName[j+1]; carName[j+1] = tmpN;
 boolean tmpA = available[j]; available[j] = available[j+1]; available[j+1] = tmpA;
 }
 }
 }
 System.out.println("Sorted by price!"); displayCars();
 }

 // MODULE 3: Method — fleet summary
 static void displaySummary() {
 int avail = 0, rented = 0, revenue = 0;
 for (int i = 0; i < available.length; i++) { // MODULE 2: for loop
 if (available[i]) avail++;
 else { rented++; revenue += pricePerDay[i] * rentedDays[i]; }
 }
 System.out.println("\n===== FLEET SUMMARY =====");
 System.out.println("Total: " + carId.length + " | Available: " + avail
 + " | Rented: " + rented + " | Revenue: Rs." + revenue);
 }

 // MODULE 1: Main method — program entry point
 public static void main(String[] args) {
 Scanner sc = new Scanner(System.in);
 int choice;
 do { // MODULE 2: do-while loop
 System.out.println("\n=== CAR RENTAL FLEET MANAGER ===");
 System.out.println("1.Display 2.Search 3.Rent 4.Return");
 System.out.println("5.Calc Rent 6.Sort 7.Summary 8.Exit");
 System.out.print("Choice: ");
 choice = sc.nextInt();
 switch (choice) { // MODULE 2: switch
 case 1: displayCars(); break;
 case 2:
 System.out.print("Enter Car ID: ");
 int sid = sc.nextInt();
 int idx = searchCar(sid);
 if (idx != -1)
 System.out.println("Found: " + carName[idx] + " Rs." + pricePerDay[idx]);
 else System.out.println("Not found.");
 break;
 case 3: rentCar(sc); break;
 case 4: returnCar(sc); break;
 case 5:
 System.out.print("Price/day: "); int p = sc.nextInt();
 System.out.print("Days: "); int d = sc.nextInt();
 System.out.println("Rent: Rs." + calculateRent(p, d));
 break;
 case 6: sortCarsByPrice(); break;
 case 7: displaySummary(); break;
 case 8: System.out.println("Thank you!"); break;
 default: System.out.println("Invalid choice!");
 }
 } while (choice != 8);
 sc.close();
 }
}