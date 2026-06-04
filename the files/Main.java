import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        Menu bro = new Menu();

        ArrayList<Participant> parts = new ArrayList<Participant>();

        // 2D array for school activity schedule
        String[][] schedule = new String[5][3];

        int choice = 0;

        while (choice != 11) {

            bro.showMenu();

            choice = in.nextInt();
            in.nextLine();

            // 1 ADD PARTICIPANT
            if (choice == 1) {

                System.out.print("name: ");
                String name = in.nextLine();

                System.out.print("grade: ");
                int grad = in.nextInt();

                System.out.print("id: ");
                int id = in.nextInt();

                System.out.print("score: ");
                int score = in.nextInt();
                in.nextLine();

                ArrayList<String> acts = new ArrayList<String>();

                System.out.print("activity 1: ");
                acts.add(in.nextLine());

                System.out.print("activity 2: ");
                acts.add(in.nextLine());

                parts.add(new Participant(name, grad, id, acts, score));

                System.out.println("added");
            }

            // 2 REMOVE PARTICIPANT
            else if (choice == 2) {

                System.out.print("id remove: ");
                int id = in.nextInt();

                for (int i = 0; i < parts.size(); i++) {
                    if (parts.get(i).getId() == id) {
                        parts.remove(i);
                        System.out.println("removed");
                        break;
                    }
                }
            }

            // 3 save or load participant from text file)
            else if (choice == 3) {

                System.out.println("1. Save");
                System.out.println("2. Load");
                int sub = in.nextInt();
                in.nextLine();

                // ================= SAVE =================
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

                // ================= LOAD =================
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

            // 4 SEARCH PARTICIPANT
            else if (choice == 4) {

                System.out.print("name: ");
                String name = in.nextLine();

                for (Participant p : parts) {
                    if (p.getName().equals(name)) {
                        System.out.println(p);
                    }
                }
            }

            // 5 DISPLAY ALL PARTICIPANTS
            else if (choice == 5) {

                for (Participant p : parts) {
                    System.out.println(p);
                }
            }

            // 6 DISPLAY SCHEDULE
            else if (choice == 6) {

                System.out.print("enter student name: ");
                String name = in.nextLine();

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

                if (!found) {
                    System.out.println("student not found");
                }
            }

            // 7 SET ACTIVITY SCHEDULE
            else if (choice == 7) {

                for (int r = 0; r < parts.size(); r++) {

                    schedule[r][0] = parts.get(r).getName();

                    System.out.println("Setting schedule for: " + schedule[r][0]);

                    for (int c = 1; c < schedule[r].length; c++) {

                        System.out.print("Activity " + c + ": ");
                        schedule[r][c] = in.nextLine();
                    }
                }

                System.out.println("schedule set");
            }

            // 8 CHANGE ACTIVITY SCHEDULE
            else if (choice == 8) {

                System.out.print("row: ");
                int r = in.nextInt();

                System.out.print("col: ");
                int c = in.nextInt();
                in.nextLine();

                System.out.print("new activity: ");
                schedule[r][c] = in.nextLine();

                System.out.println("updated");
            }

            // 9 SORT BY SCORE (selection sort)
            else if (choice == 9) {

                for (int i = 0; i < parts.size() - 1; i++) {

                    int max = i;

                    for (int j = i + 1; j < parts.size(); j++) {
                        if (parts.get(j).getScore() > parts.get(max).getScore()) {
                            max = j;
                        }
                    }

                    Participant temp = parts.get(i);
                    parts.set(i, parts.get(max));
                    parts.set(max, temp);
                }

                System.out.println("sorted");
            }

            // 10 REPORT
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
                    System.out.println("avg: " + (total / parts.size()));
                }

                System.out.println("highest: " + hih);
                System.out.println("top student: " + top);
            }
        }

        System.out.println("done");
    }
}