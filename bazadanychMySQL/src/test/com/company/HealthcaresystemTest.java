package com.company;

import org.junit.After;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import java.sql.*;


import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class HealthcaresystemTest {

    Admin a = new Admin();
    Patients p=new Patients();
    Appointment ap1=new Appointment();

    private int AutoAppointmentID()/*This Method Returns AppointmentID */
    {
        int appID=0;
        try{
            Connection con=ConnectionProvider.getCon();
            assert con != null;
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery("Select MAX(AppointmentID) from Appointments");
            rs.next();
            appID = rs.getInt(1);
            if(rs.wasNull())
            {
                return 1;
            }
        }catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return appID+1;
    }

    @Test
    public void removingDoctorTest() {
        // Connection con=ConnectionProvider.getCon();
        Connection con = null;

        PrintStream save_out = System.out;
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        try {
            String url = "jdbc:mysql://localhost:3306/HealthcareManagementSystem";
            String uname = "root";
            String pass = "root";
            //Connection con=ConnectionProvider.getCon();
            con = DriverManager.getConnection(url, uname, pass);
            Statement st=con.createStatement();

            a.RemoveDoctor(5);


            ResultSet rs1 = st.executeQuery("select * from Doctors where DoctorID=" + 5);
            String res1 = rs1.getString(2);

            assertNull(rs1);


        } catch (Exception e) {
            System.out.println("EXCEPTION OCCURS" + e.getMessage());
            //Throwable thrown;
            Assertions.assertEquals("EXCEPTION OCCURSIllegal operation on empty result set.", "EXCEPTION OCCURS" + e.getMessage());
        }
    }

    @Test
    public void addremoveddoctor() {
    try {
        String url = "jdbc:mysql://localhost:3306/HealthcareManagementSystem";
        String uname = "root";
        String pass = "root";
        Connection con=ConnectionProvider.getCon();
        con = DriverManager.getConnection(url, uname, pass);

        Statement st = con.createStatement();
        st.executeUpdate("insert into Doctors " + "VALUES (5,'Madisson','Happibell','F','9982675837',33,500,'Doctor of Medicine','Heart','srivastvamadhu@gmail.com')");

    } catch (Exception e) {
        System.out.println(e.getMessage());

    }

}


    @Test
    public void viewdoctorqualificationTest()  {

        Connection con = null;
        try {
            String url = "jdbc:mysql://localhost:3306/HealthcareManagementSystem";
            String uname = "root";
            String pass = "root";
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, uname, pass);
            Statement st = con.createStatement();
            int docID = 18;
            ResultSet rs = st.executeQuery("select * from Doctors where DoctorID=" +docID);
            String DoctorQualifications = rs.getString(8);




            assertEquals("Doctor of medicine",DoctorQualifications );


        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }




    @Test
    public void patientdetailsTest() {

        Connection con = null;

        try {
            String url = "jdbc:mysql://localhost:3306/HealthcareManagementSystem";
            String uname = "root";
            String pass = "root";
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, uname, pass);
            Statement st = con.createStatement();
            int id = 1;
            ResultSet rs1 = st.executeQuery("select * from Patients where PatientID=" + id);
            String pname = rs1.getString(2);
            ResultSet rs2 = st.executeQuery("select * from Patients where PatientID=" + id);
            String plastname = rs2.getString(3);
            ResultSet rs3 = st.executeQuery("select * from Patients where PatientID=" + id);
            String pBloodgroup = rs3.getString(8);
            ResultSet rs4 = st.executeQuery("select * from Patients where PatientID=" + id);
            String padress = rs4.getString(9);
            ResultSet rs5 = st.executeQuery("select * from Patients where PatientID=" + id);
            String pcontactnumber = rs5.getString(5);

            assertEquals("Jake",pname );
            assertEquals("Smith",plastname );
            assertEquals("B+",pBloodgroup );
            assertEquals("6th Av 20, New York",padress );
            assertEquals("9828698648",pcontactnumber );



        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }


    @Test
public void addappointmenttest() {


        Connection con = null;

        try {

            String url = "jdbc:mysql://localhost:3306/HealthcareManagementSystem";
            String uname = "root";
            String pass = "root";
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, uname, pass);
            Statement st = con.createStatement();
            int Apid = 3;

            ap1.generateAppointment();


            ResultSet rs = st.executeQuery("select * from Appointments where AppointmentID=" + Apid);
            String res = rs.getString(1);
            ResultSet rs1 = st.executeQuery("select * from Appointments where AppointmentID=" + Apid);
            String res1 = rs1.getString(2);
            //rs1.getString(2);
            ResultSet rs2 = st.executeQuery("select * from Appointments where AppointmentID==" + Apid);
            String res2 = rs1.getString(3);
            //rs2.getString(3);
            ResultSet rs3 = st.executeQuery("select * from Appointments where AppointmentID=" + Apid);
            String res3 = rs3.getString(4);
            ResultSet rs4 = st.executeQuery("select * from Appointments where AppointmentID==" +Apid);
            String res4 = rs4.getString(5);
            ResultSet rs5 = st.executeQuery("select * from Appointments where AppointmentID==" +Apid);
            String res5 = rs5.getString(6);
            ResultSet rs6 = st.executeQuery("select * from Appointments where AppointmentID==" +Apid);
            String res6 = rs6.getString(7);
            ResultSet rs7 = st.executeQuery("select * from Appointments where AppointmentID==" +Apid);
            String res7 = rs7.getString(8);
            ResultSet rs8 = st.executeQuery("select * from Appointments where AppointmentID==" +Apid);
            String res8 = rs8.getString(9);
            assertEquals("3", res);
            assertEquals("Ear_infection", res1);
            assertEquals("4", res2);
            assertEquals("Nathan", res3);
            assertEquals("1", res4);
            assertEquals("Ear", res5);
            assertEquals("400", res6);
            assertEquals("Doctor of Medicine", res7);
            assertEquals("1", res8);

        } catch (Exception e) {
        }


    }

    @Test
    public void removeappointmenttest() {


    try
    {
        Connection con=ConnectionProvider.getCon();
        assert con != null;
        Statement st=con.createStatement();
        st.executeUpdate("delete  from  Appointments where AppointmentID= "+3);
        System.out.println("Appointment Removed Succesfully!!");

        ResultSet rs1 = st.executeQuery("select * from Appointments where AppointmentID=" + 3);
        String res1 = rs1.getString(2);

        assertNull(rs1);


    }
        catch(Exception e)
    { System.out.println("EXCEPTION OCCURS"+e.getMessage());
        //Throwable thrown;
        Assertions.assertEquals("EXCEPTION OCCURSIllegal operation on empty result set.", "EXCEPTION OCCURS"+e.getMessage());
    }


}











    @Test
    public void testGetConnection4(){

        Connection con = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (ClassNotFoundException exe) {
            // ex.printStackTrace();
            System.out.println(exe);

        }

        System.out.println("Connected to database!");

        try {


            String url = "jdbc:mysql://localhost:3306/HealthcareManagementSystem";
            String uname = "root";
            String pass = "root";
            con = DriverManager.getConnection(url, uname, pass);

        } catch (SQLException e) {

            System.out.println("EXCEPTION OCCURS"+e.getMessage());
        }
    }





}
