package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.Scanner;

public class DataSource {

    public static ObservableList<StudentRecord> marks = FXCollections.observableArrayList();

    public static ObservableList<StudentRecord> getAllMarks() {

/*
        marks.add(new StudentRecord("100100100", 75.0f, 68.0f, 54.25f));
*/
        return marks;

    }
    public static void addMark(String studentId, float assignment, float midterm, float finalExam){
        marks.add(new StudentRecord(studentId, assignment, midterm, finalExam));
    }
    public static void setAllMarks(File file) throws IOException {

        //@SuppressWarnings("resource")
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String s;
        String[] fields;
        marks.clear();


        while ((s = reader.readLine()) != null) {
            //System.out.println(s);//debug for seeing loaded values
            fields = s.split(",");
            marks.add(new StudentRecord(fields[0], Float.parseFloat(fields[1]), Float.parseFloat(fields[2]), Float.parseFloat(fields[3])));
        }


    }

    public static void saveAllMarks(String name) throws Exception{



        Writer writer = null;
        try {
            File file = new File("C:\\Users\\denis\\IdeaProjects\\Lab08\\src\\sample\\" + name);
            writer = new BufferedWriter(new FileWriter(file));
            for (StudentRecord record : marks) {

                String text = record.getStudentid() + "," + record.getAssignments() + "," +record.getMidterm() + "," + record.getFinalexam() + "\n";


                writer.write(text);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {

            writer.flush();
            writer.close();
        }


    }


    public static ObservableList<StudentRecord> removeAll(){

        for(int i=0;i<marks.size();i++){
            marks.remove(i);
        }

        return marks;

    }

}
