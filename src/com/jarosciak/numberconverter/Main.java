package com.jarosciak.numberconverter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws ParseException {

        // initialize scanner for input from command-line
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> startArrayList = new ArrayList<String>();
        ArrayList<String> endArrayList = new ArrayList<String>();
        ArrayList<String> descriptionArrayList = new ArrayList<String>();
        ArrayList<String> differencesArrayList = new ArrayList<String>();


        // infinite loop for asking questions
        while (true) {
            // Add next appointment if answer is YES
            System.out.print("Do you want to add an appointment? ");
            String addAppointment = scanner.next();

            if (addAppointment.toLowerCase().equals("yes")) {
                // get start time and add it to its array list
                System.out.print("Enter starting time: ");
                startArrayList.add(scanner.next());

                // get end time and add it to its array list
                System.out.print("Enter ending time: ");
                endArrayList.add(scanner.next());

                // get appointment description and add it to its array list
                System.out.print("Enter appointment description: ");
                descriptionArrayList.add(scanner.next());

                // separator blank line
                System.out.println();

            } else {
                // If the answer is not 'yes', print the results

                System.out.println("        OUTPUT      ");
                System.out.println("----------------------");


                for(int i = 0; i < startArrayList.size(); i++) {
                    // calculate the difference between time (spaces between appointments)
                    SimpleDateFormat format = new SimpleDateFormat("HH:mm");
                    Date date1 = format.parse(endArrayList.get(i));

                    if (i==0) {
                        Date date2 = format.parse(startArrayList.get(i));
                        Date date3 = format.parse("9:00");
                        long difference = (date2.getTime() - date3.getTime()) / 1000; // difference in seconds
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

                for (int i = 0; i < differencesArrayList.size(); i++) {
                    System.out.println(differencesArrayList.get(i));
                }



                break;
            }
        }
    }





}