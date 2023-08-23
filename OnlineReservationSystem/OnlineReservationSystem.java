import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public boolean authenticate(String password) {
        return this.password.equals(password);
    }
}

class Reservation {
    private String trainNumber;
    private String trainName;
    private String classType;
    private String dateOfJourney;
    private String fromPlace;
    private String toDestination;

    public Reservation(String trainNumber, String trainName, String classType, String dateOfJourney, String fromPlace, String toDestination) {
        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.classType = classType;
        this.dateOfJourney = dateOfJourney;
        this.fromPlace = fromPlace;
        this.toDestination = toDestination;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public String getTrainName() {
        return trainName;
    }

    public String getClassType() {
        return classType;
    }

    public String getDateOfJourney() {
        return dateOfJourney;
    }

    public String getFromPlace() {
        return fromPlace;
    }

    public String getToDestination() {
        return toDestination;
    }
}

public class OnlineReservationSystem {
    private static List<User> users = new ArrayList<>();
    private static List<Reservation> reservations = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Online Reservation System!");

        users.add(new User("user1", "password1"));
        users.add(new User("user2", "password2"));

        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        User user = authenticateUser(username, password);

        if (user != null) {
            System.out.println("Login successful! Welcome, " + user.getUsername());

            while (true) {
                System.out.println("\nReservation System Menu:");
                System.out.println("1. Make a reservation");
                System.out.println("2. Cancel a reservation");
                System.out.println("3. Show current bookings");
                System.out.println("4. Exit");
                System.out.print("Select an option: ");
                int option = scanner.nextInt();
                scanner.nextLine();

                if (option == 1) {
                    System.out.print("Train Number: ");
                    String trainNumber = scanner.nextLine();
                    System.out.print("Train Name: ");
                    String trainName = scanner.nextLine();
                    System.out.print("Class Type: ");
                    String classType = scanner.nextLine();
                    System.out.print("Date of Journey: ");
                    String dateOfJourney = scanner.nextLine();
                    System.out.print("From (Place): ");
                    String fromPlace = scanner.nextLine();
                    System.out.print("To Destination: ");
                    String toDestination = scanner.nextLine();

                    Reservation newReservation = new Reservation(trainNumber, trainName, classType, dateOfJourney, fromPlace, toDestination);
                    reservations.add(newReservation);

                    System.out.println("Reservation successful!");
                } else if (option == 2) {
                    System.out.print("Enter PNR number to cancel: ");
                    String pnrNumber = scanner.nextLine();

                    if (cancelReservationByPNR(pnrNumber)) {
                        System.out.println("Reservation canceled successfully.");
                    } else {
                        System.out.println("No reservation found with the provided PNR number.");
                    }
                } else if (option == 3) {
                    showCurrentBookings();
                } else if (option == 4) {
                    System.out.println("Exiting the system. Goodbye!");
                    break;
                } else {
                    System.out.println("Invalid option. Please select a valid option.");
                }
            }
        } else {
            System.out.println("Invalid username or password. Exiting.");
        }

        scanner.close();
    }

    private static User authenticateUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.authenticate(password)) {
                return user;
            }
        }
        return null;
    }


    private static void showCurrentBookings() {
        System.out.println("\nCurrent Bookings:");
        if (reservations.isEmpty()) {
            System.out.println("No bookings available.");
        } else {
            for (Reservation reservation : reservations) {
                System.out.println("Train: " + reservation.getTrainName() + " (" + reservation.getTrainNumber() + ")");
                System.out.println("Class: " + reservation.getClassType());
                System.out.println("Date: " + reservation.getDateOfJourney());
                System.out.println("From: " + reservation.getFromPlace());
                System.out.println("To: " + reservation.getToDestination());
                System.out.println("PNR: " + generatePNR(reservation));
                System.out.println("---------------------------");
            }
        }
    }

    private static boolean cancelReservationByPNR(String pnrNumber) {
        for (Reservation reservation : reservations) {
            if (pnrNumber.equalsIgnoreCase(generatePNR(reservation))) {
                reservations.remove(reservation);
                return true;
            }
        }
        return false;
    }

    private static String generatePNR(Reservation reservation) {
        return reservation.getTrainNumber()+ "_123" ;
    }
}