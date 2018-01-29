package com.jarosciak.numberconverter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Main {

    static private ArrayList<Appointment> arrayOfAppointments = new ArrayList<>();
    static private Boolean loop = true;

    public static void main(String[] args) throws ParseException {
        // loop for asking questions
        while (loop) input();
    }


    private static void input() throws ParseException {
        // initialize scanner for input from command-line
        Scanner scanner = new Scanner(System.in);

        // Add next appointment if answer is YES
        System.out.print("Do you want to add an appointment? ");
        String addAppointment = scanner.next();
        Appointment meeting = new Appointment();

        // if the answer to above question is yes, then ask for appointment details
        if (addAppointment.toLowerCase().equals("yes")) {

            // Get start time and add it to meeting method
            System.out.print("Enter starting time: ");
            String start = scanner.next();
            meeting.setStartTime(start);

            // Get end time and add it to meeting method
            System.out.print("Enter ending time: ");
            String end = scanner.next();
            meeting.setEndTime(end);

            // Get appointment description and add it meeting method
            System.out.print("Enter appointment description: ");
            String description = scanner.next();
            meeting.setAppointmentDescription(description);

            // Calculate the difference between end and start time and add to meeting method
            SimpleDateFormat format = new SimpleDateFormat("HH:mm");
            long difference = (format.parse(end).getTime() - format.parse(start).getTime());
            meeting.setDurationTime(difference);

            // Finally add the Appointment object meeting to array list
            arrayOfAppointments.add(meeting);
        } else {
            // If user no longer wants to add any appointment, print the output
            output();
            // end the loop (while in main method will no longer run)
            loop = false;
        }
    }





    private static void output() throws ParseException {

        System.out.println("----------------------");
        System.out.println("|        OUTPUT      |");
        System.out.println("----------------------");

        // Initialize HashMap that will hold all naps we can find between appointments
        HashMap<String, Long> durationTime = new HashMap<>();

        // Calculate the differences between times (spaces between appointments)
        for (int i = 0; i < arrayOfAppointments.size(); i++) {
            // set format used for hours and minutes
            SimpleDateFormat format = new SimpleDateFormat("HH:mm");

            if (i == 0) {
                // 9:00 calculation
                long difference = (format.parse(arrayOfAppointments.get(i).getStartTime()).getTime() - format.parse("9:00").getTime());
                durationTime.put("9:00", difference);
            }

            if (i < (arrayOfAppointments.size() - 1)) {
                // after 9:00 before 17:00 calculation
                long difference = (format.parse(arrayOfAppointments.get(i + 1).getStartTime()).getTime()
                        - format.parse(arrayOfAppointments.get(i).getEndTime()).getTime());
                durationTime.put(arrayOfAppointments.get(i).getEndTime(), difference);
            } else {
                // the last entry is related to 17:00 calculation
                long difference = (format.parse("17:00").getTime() - format.parse(arrayOfAppointments.get(i).getEndTime()).getTime());
                durationTime.put(arrayOfAppointments.get(i).getEndTime(), difference);
            }
        }

        // get max nap duration from HashMap
        Long longestNapDuration = Collections.max(durationTime.entrySet(), Comparator.comparingLong(Map.Entry::getValue)).getValue();
        String longestNapTime = Collections.max(durationTime.entrySet(), Comparator.comparingLong(Map.Entry::getValue)).getKey();

        int napHours = Integer.parseInt(String.format("%02d", TimeUnit.MILLISECONDS.toHours(longestNapDuration)));
        int napMinutes = Integer.parseInt(String.format("%02d", TimeUnit.MILLISECONDS.toMinutes(longestNapDuration)
                - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(longestNapDuration))));

        // format answer
        if (napHours > 0) {
            System.out.println("You can take longest nap at " + longestNapTime + " and it will last for "
                    + napHours + " hours and " + napMinutes + " minutes.");
        } else {
            System.out.println("You can take longest nap at " + longestNapTime + " and it will last for "
                    + napMinutes + " minutes.");
        }

        // Get the total free time available in the day
        long sum = 8 * 60 * 60 * 1000; // milliseconds in one day from 9:00 to 17:00
        for (Appointment arrayOfAppointment : arrayOfAppointments) {
            sum -= arrayOfAppointment.getDurationTime();
        }

        int hours = Integer.parseInt(String.format("%02d", TimeUnit.MILLISECONDS.toHours(sum)));
        int minutes = Integer.parseInt(String.format("%02d", TimeUnit.MILLISECONDS.toMinutes(sum)
                - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(sum))));

        // format answer
        if (hours > 0) {
            System.out.println("Total free time during whole day is " + hours + " hours and " + minutes + " minutes.");
        } else {
            System.out.println("Total free time during whole day is " + minutes + " minutes.");
        }
    }
}



