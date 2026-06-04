import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        Menu bro = new Menu();

        ArrayList<Participant> parts = new ArrayList<Participant>();

        // 2d aray for school activty shedule
        String[][] schedule = new String[5][3];

        int choice = 0;

        while (choice != 11) {
            //if user doesnt pick 11 then keep looping the menu
            bro.showMenu();

            choice = input.nextInt();
            input.nextLine();

            // 1 add participnt
            if (choice == 1) {

                System.out.print("name: ");
                String name = input.nextLine();

                System.out.print("grade: ");
                int grad = input.nextInt();
                System.out.print("id: ");
                int id = input.nextInt();

                System.out.print("score: ");
                int score = input.nextInt();
                input.nextLine();

                ArrayList<String> acts = new ArrayList<String>();
                System.out.print("activity 1: ");
                acts.add(input.nextLine());

                System.out.print("activity 2: ");
                acts.add(input.nextLine());

                parts.add(new Participant(name, grad, id, acts, score));
                System.out.println("added");
            }

            // 2 remov participnt
            else if (choice == 2) {

                System.out.print("id remove: ");
                int id = input.nextInt();

                for (int i = 0; i < parts.size(); i++) {
                    if (parts.get(i).getId() == id) {
                        parts.remove(i);
                        System.out.println("removed");
                        break;
                    }
                }
            }

            // 3 save or lod participnt from txt file
            else if (choice == 3) {

                System.out.println("1. Save");
                System.out.println("2. Load");
                int sub = input.nextInt();
                input.nextLine();

                // ===+324230hello2304920349===== save
                if (sub == 1) {

                    try {
                        PrintWriter out = new PrintWriter("participants.txt");

                        for (Participant p : parts) {
                            out.println(p.getName());
                            out.println(p.getGrad());
                            out.println(p.getId());
                            out.println(p.getScore());
                            out.println(p.getActs());
                        }

                        out.close();
                        System.out.println("saved");

                    } catch (IOException e) {
                        System.out.println("error saving");
                    }
                }

                // =====loadd=================
                else if (sub == 2) {

                    try {
                        Scanner file = new Scanner(new File("participants.txt"));
                        parts.clear();

                        while (file.hasNextLine()) {
                            String name = file.nextLine();
                            int grade = Integer.parseInt(file.nextLine());
                            int id = Integer.parseInt(file.nextLine());
                            int score = Integer.parseInt(file.nextLine());

                            String acts = file.nextLine();
                            ArrayList<String> actList = new ArrayList<String>();
                            actList.add(acts);

                            parts.add(new Participant(name, grade, id, actList, score));
                        }

                        file.close();
                        System.out.println("loaded");

                    } catch (IOException e) {
                        System.out.println("error loading");
                    }
                }
            }

            // 4 serch participnt
            else if (choice == 4) {

                System.out.print("name: ");
                String name = input.nextLine();

                for (Participant p : parts) {
                    if (p.getName().equals(name)) {
                        System.out.println(p);
                    }
                }
            }

            // 5 dispaly all participnts
            else if (choice == 5) {
                for (Participant p : parts) {
                    System.out.println(p);
                }
            }

            // 6 disply shedule
            else if (choice == 6) {

                System.out.print("enter student name: ");
                String name = input.nextLine();
                boolean found = false;

                for (int r = 0; r < schedule.length; r++) {

                    if (schedule[r][0] != null && schedule[r][0].equals(name)) {

                        System.out.print(schedule[r][0] + ": ");

                        for (int c = 1; c < schedule[r].length; c++) {
                            System.out.print(schedule[r][c] + " ");
                        }
                        System.out.println();
                        found = true;
                    }
                }

                if (!found) { System.out.println("student not found"); }
            }

            // 7 set activty shedule
            else if (choice == 7) {

                for (int r = 0; r < parts.size(); r++) {
                    schedule[r][0] = parts.get(r).getName();
                    System.out.println("Setting schedule for: " + schedule[r][0]);

                    for (int c = 1; c < schedule[r].length; c++) {
                        System.out.print("Activity " + c + ": ");
                        schedule[r][c] = input.nextLine();
                    }
                }
                System.out.println("schedule set");
            }

            // 8 change activty schedule
            else if (choice == 8) {

                System.out.print("row: ");
                int r = input.nextInt();
                System.out.print("col: ");
                int c = input.nextInt();
                input.nextLine();

                System.out.print("new activity: ");
                schedule[r][c] = input.nextLine();
                System.out.println("updated");
            }

            // 9 sort by scroe (selction sort)
            else if (choice == 9) {

                for (int i = 0; i < parts.size() - 1; i++) {
                    int max = i;

                    for (int j = i + 1; j < parts.size(); j++) {
                        if (parts.get(j).getScore() > parts.get(max).getScore()) {
                            max = j;
                        }
                    }

                    Participant te = parts.get(i);
                    parts.set(i, parts.get(max));
                    parts.set(max, te);
                }
                System.out.println("sorted");
            }

            // 10 reprot
            else if (choice == 10) {

                int total = 0;
                int hih = 0;
                String top = "";

                for (Participant p : parts) {
                    total += p.getScore();

                    if (p.getScore() > hih) {
                        hih = p.getScore();
                        top = p.getName();
                    }
                }

                if (parts.size() > 0) {
                    System.out.println("avg: " + (total / parts.size())); }

                System.out.println("highest: " + hih);
                System.out.println("top student: " + top);
            }
        }

        System.out.println("done");
    }
}