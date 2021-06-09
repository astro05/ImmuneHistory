package immunehistory.User;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

public class ConnectMSSQL {
    
    public Connection connection;
    String location="jdbc:sqlserver://192.168.0.10:1433;databaseName=immune_history;selectMethod=cursor";
    ConnectMSSQL()
    {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(
                    location, "sa", "123456");

            System.out.println("DATABASE NAME IS:" + connection.getMetaData().getDatabaseProductName());

         
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
            
    public void connectDB() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(
                    location, "sa", "123456");

            System.out.println("DATABASE NAME IS:" + connection.getMetaData().getDatabaseProductName());

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement
                    .executeQuery("SELECT user_name FROM user_table");
            
            
            while (resultSet.next()) {
                
                System.out.println("Customer NAME:" + 
                        resultSet.getString("user_name"));
                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    int  rowList(String sql)
    {
       int rowNum=0;
         try {
            

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement
                    .executeQuery(sql);
            
              while (resultSet.next()) {
                
              
                rowNum=Integer.parseInt(resultSet.getString("row"));
                
            }
                
            }
            
            
            catch (Exception e) {
            e.printStackTrace();
        }
        return rowNum;
    }
    public Object[] showAppointment(String sql) {
         ArrayList<String> SLno = new ArrayList<String>();
   
     ArrayList<String> ConsultId = new ArrayList<String>();
   
     ArrayList<String> DoctorName = new ArrayList<String>();
     
     ArrayList<String>  AppointmentDate= new ArrayList<String>();
     
     ArrayList<String> ConsultDate = new ArrayList<String>();
     
     ArrayList<String> Status = new ArrayList<String>();
     
     ArrayList<String> Category = new ArrayList<String>();
   
    
        try {
            
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement
                    .executeQuery(sql);
            
              while (resultSet.next()) {
                SLno.add(resultSet.getString("ap_sl_no"));
              DoctorName.add(resultSet.getString("doctor_name"));
              ConsultId.add(resultSet.getString("consult_id"));
              Category.add(resultSet.getString("category"));
              AppointmentDate.add(resultSet.getString("date_of_appointment_taken"));
              ConsultDate.add(resultSet.getString("date_of_consult"));
             Status.add(resultSet.getString("appoint_active")) ;  
            }
                
            }
            
            
            catch (Exception e) {
            e.printStackTrace();
        }
        
        return new Object[]{SLno,ConsultId,DoctorName,AppointmentDate,ConsultDate,Status,Category};
    }
    public static void main(String[] args) {
        ConnectMSSQL cnObj = new ConnectMSSQL();
        //cnObj.connectDB();
        //cnObj.rowList();
        Object[] obj ;
      obj= cnObj.showAppointment("SELECT ap_sl_no,appointment.consult_id,doctor_name,FORMAT (date_of_appointment_taken, 'dd-MM-yy')AS date_of_appointment_taken,FORMAT (date_of_consult, 'dd-MM-yy')AS date_of_consult,appoint_active FROM appointment INNER JOIN consult ON consult.consult_id=appointment.consult_id INNER JOIN doctor ON doctor.doctor_id=consult.doctor_id");
    ArrayList<String> res = new ArrayList<String>();
   res=(ArrayList)obj[1];
        System.out.println(res);
       
    }
    
    boolean loginValidation(String UserName,String Phone)
    {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(
                    location, "sa", "123456");

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement
                    .executeQuery("SELECT user_name,phone FROM user_table");
            
            
            while (resultSet.next()) {
                 
                        if(resultSet.getString("user_name").toString().equals(UserName)&& resultSet.getString("phone").toString().equals(Phone))
                        {
                            return true;
                        }
                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    ArrayList fetchData(String condition)
    {
        ArrayList<String> data = new ArrayList<String>();
          try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(
                    location, "sa", "123456");

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement
                    .executeQuery("SELECT * FROM user_table where phone="+condition);
            
            
            while (resultSet.next()) {
                 data.add(resultSet.getString("user_id"));
                data.add(resultSet.getString("user_name"));
                data.add(resultSet.getString("email"));
                data.add(resultSet.getString("phone"));
                data.add(resultSet.getString("date_of_birth"));
                data.add(resultSet.getString("gender"));
                data.add(resultSet.getString("address"));
                data.add(resultSet.getString("zip_code"));
                data.add(resultSet.getString("image_url"));
                    
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
          return data;
    }
    void insertData(String Name,String Phone,String Email,String DOB) throws SQLException, ClassNotFoundException
    {
        int birthyear= Integer.parseInt(DOB.substring(0, 4));
       int currentyear = Calendar.getInstance().get(Calendar.YEAR);
        int Age=currentyear-birthyear;
         System.out.println(birthyear);
          System.out.println(currentyear);
        System.out.println(Age);
         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(
                    location, "sa", "123456");
            
        System.out.println("INSERT INTO user_table(user_name, age,date_of_birth,phone,email)VALUES ('"+Name+"','"+Age+"','"+DOB+"','"+Phone+"','"+Email+"')");
            Statement statement = connection.createStatement();
           
            statement
                   .executeUpdate("INSERT INTO user_table(user_name, age,date_of_birth,phone,email)VALUES ('"+Name+"','"+Age+"','"+DOB+"','"+Phone+"','"+Email+"')");
        

    }
   void updateData(String Name,String Email,String Phone,String DateOfBirth,String Gender,String Address,String Zipcode,String imageURL,String Condition) throws SQLException, ClassNotFoundException
   {
       System.out.println("UPDATE user_table SET user_name="+Name+",email="+Email+",phone="+Phone+",date_of_birth="+DateOfBirth+",gender="+Gender+",address="+Address+",zip_code="+Zipcode+",image_url="+imageURL);
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(
                    location, "sa", "123456");

            Statement statement = connection.createStatement();
           
            statement
                   .executeUpdate("UPDATE user_table SET user_name='"+Name+"',email='"+Email+"',phone='"+Phone+"',date_of_birth='"+DateOfBirth+"',gender='"+Gender+"',address='"+Address+"',zip_code="+Zipcode+",image_url='"+imageURL+"'WHERE phone='"+Condition+"'");
   }
   
   Object[] fetchDoctorList()
   {
       ArrayList<String> doctorName = new ArrayList<String>();
       ArrayList<String> degree = new ArrayList<String>();
       ArrayList<String> age = new ArrayList<String>();
       ArrayList<String> category = new ArrayList<String>();
       ArrayList<String> hospital = new ArrayList<String>();
       ArrayList<String> email = new ArrayList<String>();
       ArrayList<String> phone = new ArrayList<String>();
          try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(
                    location, "sa", "123456");

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement
                    .executeQuery("SELECT * FROM doctor");
            
            
           while (resultSet.next())
           {
               
                 doctorName.add(resultSet.getString("doctor_name"));
                 degree.add(resultSet.getString("degree"));
                  age.add(resultSet.getString("age"));
                  category.add(resultSet.getString("category"));
                 hospital.add(resultSet.getString("hospital"));
                  email.add(resultSet.getString("email"));
                 phone.add(resultSet.getString("phone"));
              
           }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
          return new Object[]{doctorName,degree,age,category,hospital,email,phone};
   }
   
   Object[] fetchDoctorList(String searchKeyword)
   {
       ArrayList<String> doctorName = new ArrayList<String>();
       ArrayList<String> degree = new ArrayList<String>();
       ArrayList<String> age = new ArrayList<String>();
       ArrayList<String> category = new ArrayList<String>();
       ArrayList<String> hospital = new ArrayList<String>();
       ArrayList<String> email = new ArrayList<String>();
       ArrayList<String> phone = new ArrayList<String>();
          try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(
                    location, "sa", "123456");

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement
                    .executeQuery("SELECT * FROM doctor where doctor_name like '%"+searchKeyword+"%' OR   category like '%"+searchKeyword+"%' OR  degree like '%"+searchKeyword+"%' OR  phone like '%"+searchKeyword+"%'OR  email like '%"+searchKeyword+"%' OR  hospital like '%"+searchKeyword+"%'");
            
            
           while (resultSet.next())
           {
               
                 doctorName.add(resultSet.getString("doctor_name"));
                 degree.add(resultSet.getString("degree"));
                  age.add(resultSet.getString("age"));
                  category.add(resultSet.getString("category"));
                 hospital.add(resultSet.getString("hospital"));
                  email.add(resultSet.getString("email"));
                 phone.add(resultSet.getString("phone"));
              
           }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
          return new Object[]{doctorName,degree,age,category,hospital,email,phone};
   }
   Object[] fetchDoctorList(String searchKeyword,String value)
   {
       ArrayList<String> doctorName = new ArrayList<String>();
       ArrayList<String> degree = new ArrayList<String>();
       ArrayList<String> age = new ArrayList<String>();
       ArrayList<String> category = new ArrayList<String>();
       ArrayList<String> hospital = new ArrayList<String>();
       ArrayList<String> email = new ArrayList<String>();
       ArrayList<String> phone = new ArrayList<String>();
          try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(
                    location, "sa", "123456");

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement
                    .executeQuery("SELECT max(age) as age,max(doctor_name) as doctor_name,max(degree) as degree ,max(hospital) as hospital,max(phone) as phone ,max(email)as email,max(category) as category from doctor where doctor_name like '%"+searchKeyword+"%' OR  category like '%"+searchKeyword+"%' OR  phone like '%"+searchKeyword+"%'OR  email like '%"+searchKeyword+"%' OR  hospital like '%"+searchKeyword+"%' GROUP BY category");
            
            
           while (resultSet.next())
           {
               
                 doctorName.add(resultSet.getString("doctor_name"));
                 degree.add(resultSet.getString("degree"));
                  age.add(resultSet.getString("age"));
                  category.add(resultSet.getString("category"));
                 hospital.add(resultSet.getString("hospital"));
                  email.add(resultSet.getString("email"));
                 phone.add(resultSet.getString("phone"));
              
           }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
          return new Object[]{doctorName,degree,age,category,hospital,email,phone};
   }
   
   ArrayList fetchDoctorCategories()
    {
        ArrayList<String> data = new ArrayList<String>();
          try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(
                    location, "sa", "123456");

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement
                    .executeQuery("SELECT DISTINCT(category) FROM doctor");
            
            
            while (resultSet.next()) {
                 data.add(resultSet.getString("category"));     
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
          return data;
    }
   Object[] fetchDoctorBasedOnCategory(String category)
   {
       
        ArrayList<String> doctorname = new ArrayList<String>();
        ArrayList<String> degree = new ArrayList<String>();
          try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(
                    location, "sa", "123456");

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement
                    .executeQuery("SELECT doctor_name,degree FROM doctor where category='"+category+"'");
            
            
            while (resultSet.next()) {
                 doctorname.add(resultSet.getString("doctor_name")); 
                 degree.add(resultSet.getString("degree")); 
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
         return new Object[]{doctorname,degree};
   }
   ArrayList fetchDoctorTime(String condition1,String condition2)
    {
        ArrayList<String> data = new ArrayList<String>();
          try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(
                    location, "sa", "123456");

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement
                    .executeQuery("SELECT RIGHT(available_time,2)AS Sort,doctor.doctor_id,available_time FROM doctor_time INNER JOIN DOCTOR ON doctor.doctor_id=doctor_time.doctor_id where doctor_name='"+condition1+"' AND category='"+condition2+"' Order BY doctor.doctor_id,Sort ASC");
            
            
            while (resultSet.next()) {
                 data.add(resultSet.getString("available_time"));     
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
          return data;
    }
   ArrayList fetchSymptomps()
   {
        ArrayList<String> data = new ArrayList<String>();
          try {

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement
                    .executeQuery("SELECT symptom_name FROM symptom");
            
            
            while (resultSet.next()) {
                 data.add(resultSet.getString("symptom_name"));     
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
          return data;
   }
   ArrayList fetchDoctorBasedOnDisease(String problems)
   {
       String category=null;
      ArrayList<String> data = new ArrayList<String>();
          try {

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement
                    .executeQuery("SELECT disease_name.disease_id,disease_name,symptom_name,category_name,doctor_name FROM doctor INNER JOIN doctor_category ON doctor_category.category_name=doctor.category INNER JOIN disease_name ON doctor_category.category_id=disease_name.category_id INNER JOIN disease_info ON disease_info.disease_id=disease_name.disease_id INNER JOIN symptom ON disease_info.symptom_id=symptom.symptom_id where symptom_name LIKE '%"+problems+"%'");
            
            
            while (resultSet.next()) {
                 data.add(resultSet.getString("doctor_name")); 
                 category=resultSet.getString("category_name");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
         data.add(category);
          return data;
   }
   
    String fetchDoctorId(String name,String category)
    {
        String doctor_id=null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(
                    location, "sa", "123456");

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement
                    .executeQuery("SELECT doctor_id FROM doctor where doctor_name='"+name+"'AND category='"+category+"'");
            
            
            while (resultSet.next()) {
                 doctor_id=resultSet.getString("doctor_id");    
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return doctor_id;
    }
    
     void insertConsult(int doctor_id) throws SQLException, ClassNotFoundException
   {
     Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(
                    location, "sa", "123456");
            
       
            Statement statement = connection.createStatement();
           
            statement
                   .executeUpdate("INSERT INTO consult(doctor_id) VALUES ("+doctor_id+"");
          
   
 }
    
   void insertAppointment(int user_id,int doctor_id,String date_of_appointment,String  date_of_consult) throws SQLException, ClassNotFoundException
   {
     Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(
                    location, "sa", "123456");
            
       String consult_id=null;
            Statement statement = connection.createStatement();
            
              statement
                   .executeUpdate("INSERT INTO consult(doctor_id) VALUES ("+doctor_id+")");
              
              
               ResultSet resultSet = statement
                    .executeQuery("SELECT TOP 1 * FROM consult ORDER BY consult_id DESC");
            
            
            while (resultSet.next()) {
                 consult_id=resultSet.getString("consult_id");    
            }
            
         
           
            statement
                   .executeUpdate("INSERT INTO appointment(user_id,consult_id,date_of_appointment_taken,date_of_consult) VALUES ("+user_id+","+consult_id+",'"+date_of_appointment+"',convert(datetime,'"+date_of_consult+"',5))");
          
   
 }
   Object[] fetchVaxinationDetails()
   {
       
        ArrayList<String> VaxinId = new ArrayList<String>();
         ArrayList<String> VaxinName = new ArrayList<String>();
          ArrayList<String> VaxinDate = new ArrayList<String>();
          try {

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement
                    .executeQuery("SELECT user_id,vaxine_id,medicine_generic_name,vaxination_date FROM medicine_generic_name INNER JOIN vaxine_history ON vaxine_history.vaxine_id=medicine_generic_name.medicine_generic_id where user_id=11");
            
            
            while (resultSet.next()) {
                 VaxinId.add(resultSet.getString("vaxine_id"));  
                 VaxinName.add(resultSet.getString("medicine_generic_name"));  
                 VaxinDate.add(resultSet.getString("vaxination_date"));  
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
          return new Object[]{VaxinId,VaxinName,VaxinDate};
       
       
       
   }
   
   
   int  specializedDoctorNumber()
    {
       int rowNum=0;
         try {
            

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement
                    .executeQuery("SELECT COUNT(doctor_name)as row from doctor where degree like '%____%_%'");
            
              while (resultSet.next()) {
                
              
                rowNum=Integer.parseInt(resultSet.getString("row"));
                
            }
                
            }
            
            
            catch (Exception e) {
            e.printStackTrace();
        }
        return rowNum;
    }
}
