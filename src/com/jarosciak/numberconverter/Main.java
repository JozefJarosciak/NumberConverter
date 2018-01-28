package com.jarosciak.numberconverter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws ParseException {

        // initialize scanner for input from command-line
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> differencesArrayList = new ArrayList<String>();

        ArrayList<Appointment> arrayOfAppointments = new ArrayList<>();
        Appointment app = new Appointment();

        // infinite loop for asking questions
        while (true) {
            // Add next appointment if answer is YES
            System.out.print("Do you want to add an appointment? ");
            String addAppointment = scanner.next();

            if (addAppointment.toLowerCase().equals("yes")) {
                // get start time and add it to its array list
                System.out.print("Enter starting time: ");
                String start = scanner.next();
                app.setStartTime(start);

                // get end time and add it to its array list
                System.out.print("Enter ending time: ");
                String end = scanner.next();
                app.setEndTime(end);

                // get appointment description and add it to its array list
                System.out.print("Enter appointment description: ");
                String description = scanner.next();
                app.setAppointmentDescription(description);


                SimpleDateFormat format = new SimpleDateFormat("HH:mm");
                long difference = (format.parse(end).getTime() - format.parse(start).getTime()); // difference in second
                app.setDurationTime(difference);

                // add the object to array list
                arrayOfAppointments.add(app);

                // separator blank line
                //System.out.println(arrayOfAppointments.get(0).getStartTime());

            } else {
                // If the answer is not 'yes', print the results

                System.out.println("----------------------");
                System.out.println("|        OUTPUT      |");
                System.out.println("----------------------");

                /*

                for(int i = 0; i < arrayOfAppointments.size(); i++) {
                    // calculate the difference between time (spaces between appointments)
                    SimpleDateFormat format = new SimpleDateFormat("HH:mm");
                    Date date1 = format.parse(arrayOfAppointments.get(i).getStartTime());

                    if (i==0) {
                        long difference = (format.parse(arrayOfAppointments.get(i).getEndTime()).getTime() - format.parse("9:00").getTime()) / 1000; // difference in seconds
                        differencesArrayList.add(Long.toString(difference) + " - " + "9:00" );
                    }

                    if (i < (startArrayList.size()-1)) {
                        Date date2 = format.parse(startArrayList.get(i + 1));
                        long difference = (date2.getTime() - date1.getTime()) / 1000; // difference in seconds
                        differencesArrayList.add(Long.toString(difference)  + " - " +  endArrayList.get(i));
                    } else {
                        Date date2 = format.parse("17:00");
                        long difference = (date2.getTime() - date1.getTime()) / 1000; // difference in seconds
                        differencesArrayList.add(Long.toString(difference) + " - " + endArrayList.get(i));
                    }
                }

               Collections.sort(differencesArrayList);
*/



                // Get Total Free Time
                long sum = 8*3600000; // milliseconds in one day from 9:00 to 17:00
                for (int i = 0; i < arrayOfAppointments.size(); i++) {
                    sum -= arrayOfAppointments.get(i).getDurationTime();
                }

                System.out.println(sum);

                int hours = Integer.parseInt(String.format("%02d", TimeUnit.MILLISECONDS.toHours(sum)));

                int minutes = Integer.parseInt(String.format("%02d",TimeUnit.MILLISECONDS.toMinutes(sum) -
                                TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(sum))));


                if (hours>0) {
                    System.out.println("Total free time during whole day is " + hours + " hours and " + minutes + " minutes.");
               } else {
                    System.out.println("Total free time during whole day is " + minutes + " minutes.");
                }



                break;
            }
        }
    }





}