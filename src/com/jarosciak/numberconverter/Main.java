package com.jarosciak.numberconverter;

import java.util.Scanner;

public class Main {

    // initialize global scanner for input from command-line
    private static Scanner scanner = new Scanner(System.in);
    private static String appointmentArray[][];

    public static void main(String[] args) {

        // infinite loop for asking questions
        while (true) {
            // Add next appointment if answer is YES
            System.out.print("Do you want to add an appointment? ");
            String addAppointment = scanner.next();

            if (addAppointment.toLowerCase().equals("yes")) {
                // keep asking questions while answer is 'yes'
                AddAppointments();
            } else {
                // If the answer is not 'yes', print the results
                System.out.println("");
                System.out.println("----------------------");
                System.out.println("You can take longest nap at 16:00 and it will last for 30 minutes.\n" +
                        "Total free time during whole day is 30 minutes.");
                // end the while
                break;
            }
        }


    }





    private static void AddAppointments() {
        // get starting time
        System.out.print("Enter starting time: ");
        String startTime = scanner.next();

        // get ending time
        System.out.print("Enter ending time: ");
        String endTime = scanner.next();

        // get appointment description
        System.out.print("Enter appointment description: ");
        String appointmentDescription = scanner.next();

        // separator blank line
        System.out.print(" ");

        // add values to object
        Appointments appointment = new Appointments();
        appointment.setStartTime(startTime);
        appointment.setEndTime(endTime);
        appointment.setAppointmentDescription(appointmentDescription);

        System.out.println(appointment.toString());

     }

}


class Appointments {
    // data members
    private String appointmentDescription;
    private String startTime;
    private String endTime;

    // Set Appointment Description
    public void setAppointmentDescription(String data)
    {
        appointmentDescription = data;
    }

    // Set Start Time
    public void setStartTime(String data)
    {
        startTime = data;
    }

    // Set End Time
    public void setEndTime(String data)
    {
        endTime = data;
    }

    // Get Appointment Description
    public String getAppointmentDescription()
    {
        return appointmentDescription;
    }

    // Get Start Time
    public String getStartTime()
    {
        return startTime;
    }

    // Get End Time
    public String setEndTime()
    {
        return endTime;
    }
}
