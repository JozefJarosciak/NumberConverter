package com.jarosciak.numberconverter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
    static ArrayList<Appointment> arrayOfAppointments = new ArrayList<>();


    public static void main(String[] args) throws ParseException {

        // infinite loop for asking questions
        while (true) {
            Input();
        }

    }



    public static void Input() throws ParseException {
        // initialize scanner for input from command-line
        Scanner scanner = new Scanner(System.in);
        // Add next appointment if answer is YES
        System.out.print("Do you want to add an appointment? ");
        String addAppointment = scanner.next();
        Appointment meeting = new Appointment();

        if (addAppointment.toLowerCase().equals("yes")) {
            // get start time and add it to its array list
            System.out.print("Enter starting time: ");
            String start = scanner.next();
            meeting.setStartTime(start);

            // get end time and add it to its array list
            System.out.print("Enter ending time: ");
            String end = scanner.next();
            meeting.setEndTime(end);

            // get appointment description and add it to its array list
            System.out.print("Enter appointment description: ");
            String description = scanner.next();
            meeting.setAppointmentDescription(description);


            SimpleDateFormat format = new SimpleDateFormat("HH:mm");
            long difference = (format.parse(end).getTime() - format.parse(start).getTime()); // difference in second
            meeting.setDurationTime(difference);

            // add the object to array list
            arrayOfAppointments.add(meeting);


        } else {
            // If the answer is not 'yes', print the results
            Output();
            System.exit(0);
        }


    }


    public static void Output() throws ParseException {

        System.out.println("----------------------");
        System.out.println("|        OUTPUT      |");
        System.out.println("----------------------");


        Map<String, Long> durationTime = new HashMap<String, Long>();

        for (int i = 0; i < arrayOfAppointments.size(); i++) {
            // calculate the difference between time (spaces between appointments)
            SimpleDateFormat format = new SimpleDateFormat("HH:mm");

            if (i == 0) {
                // 9:00 calculation
                long difference = (format.parse(arrayOfAppointments.get(i).getStartTime()).getTime() - format.parse("9:00").getTime());
                durationTime.put("9:00", difference);
            }

            if (i < (arrayOfAppointments.size() - 1)) {
                // after 9:00 before 17:00 calculation
                long difference = (format.parse(arrayOfAppointments.get(i + 1).getStartTime()).getTime() - format.parse(arrayOfAppointments.get(i).getEndTime()).getTime());
                durationTime.put(arrayOfAppointments.get(i).getEndTime(), difference);
            } else {
                // 17:00 calculation
                long difference = (format.parse("17:00").getTime() - format.parse(arrayOfAppointments.get(i).getEndTime()).getTime());
                durationTime.put(arrayOfAppointments.get(i).getEndTime(), difference);
            }
        }

        // get max nap duration from Map
        Long longestNapDuration = durationTime.entrySet().stream().max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1).get().getValue();
        String longestNapTime = durationTime.entrySet().stream().max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1).get().getKey();

        //     System.out.println("Longest: " + differencesArrayList.get(differencesArrayList.size()));


        int napHours = Integer.parseInt(String.format("%02d", TimeUnit.MILLISECONDS.toHours(longestNapDuration)));
        int napMinutes = Integer.parseInt(String.format("%02d", TimeUnit.MILLISECONDS.toMinutes(longestNapDuration)
                - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(longestNapDuration))));

        // format answer
        if (napHours > 0) {
            System.out.println("You can take longest nap at " + longestNapTime + " and it will last for " + napHours + " hours and " + napMinutes + " minutes.");
        } else {
            System.out.println("You can take longest nap at " + longestNapTime + " and it will last for " + napMinutes + " minutes.");
        }

        // Get Total Free Time
        long sum = 8 * 60 * 60 * 1000; // milliseconds in one day from 9:00 to 17:00
        for (int i = 0; i < arrayOfAppointments.size(); i++) {
            sum -= arrayOfAppointments.get(i).getDurationTime();
        }

        int hours = Integer.parseInt(String.format("%02d", TimeUnit.MILLISECONDS.toHours(sum)));
        int minutes = Integer.parseInt(String.format("%02d", TimeUnit.MILLISECONDS.toMinutes(sum) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(sum))));


        // format answer
        if (hours > 0) {
            System.out.println("Total free time during whole day is " + hours + " hours and " + minutes + " minutes.");
        } else {
            System.out.println("Total free time during whole day is " + minutes + " minutes.");
        }

    }

}



