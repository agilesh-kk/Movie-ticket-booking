import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;
import java.sql.*;


class movie1details{
    String name="KALKI_2898AD";
    String movieposter="kalki.jpeg";
    String runtime="3h 1m";
    String censorcertificate="U/A";
    String genre="Action, Sci-Fi, Thriller";
}
class movie2details{
    String name="RAAYAN";
    String movieposter="raayan.jpeg";
    String runtime="2h 25m";
    String censorcertificate="A";
    String genre="Action, Crime, Drama";
}
class movie3details{
    String name="DEADPOOL_AND_WOLVERINE";
    String movieposter="deadpoolandwolverine.jpeg";
    String runtime="2h 7m";
    String censorcertificate="A";
    String genre="Action, Adventure, Comedy";
}
class movie4details{
    String name="INDIAN_2";
    String movieposter="indian2.jpeg";
    String runtime="2h 52m";
    String censorcertificate="UA";
    String genre="Action, Thriller, Drama";
}

class CalendarPanel extends JPanel {
    private JLabel monthLabel;
    private JPanel calendarPanel;
    private LocalDate currentDate;
    public String datestring;

    public CalendarPanel() {
        // Initialize the panel
        setLayout(new BorderLayout());
        currentDate = LocalDate.now();

        // Create the header panel with month navigation
        JPanel headerPanel = new JPanel(new BorderLayout());
        monthLabel = new JLabel("", JLabel.CENTER);
        headerPanel.add(monthLabel, BorderLayout.CENTER);
        add(headerPanel, BorderLayout.NORTH);
        
        // Create the calendar panel
        calendarPanel = new JPanel(new GridLayout(0, 7));
        add(calendarPanel, BorderLayout.CENTER);

        // Update the calendar to display the current week
        updateCalendar();
    }

    private void updateCalendar() {
        // Clear the calendar panel
        calendarPanel.removeAll();

        // Set the month label
        DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        monthLabel.setText(currentDate.format(monthFormatter));

        // Add day labels (Sun, Mon, Tue, etc.)
        String[] dayNames = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        for (String dayName : dayNames) {
            JLabel dayLabel = new JLabel(dayName, JLabel.CENTER);
            dayLabel.setFont(new Font("Arial", Font.BOLD, 12));
            calendarPanel.add(dayLabel);
        }

        // Get the current date and the next 7 days
        LocalDate endDate = currentDate.plusDays(7);
        LocalDate dateIterator = currentDate;

        // Add empty labels for the days before the current date in the week
        int dayOfWeekValue = currentDate.getDayOfWeek().getValue();
        dayOfWeekValue = dayOfWeekValue == 7 ? 0 : dayOfWeekValue; // Adjust for Sunday
        for (int i = 0; i < dayOfWeekValue; i++) {
            calendarPanel.add(new JLabel(""));
        }

        // Add buttons for the current date and the next 7 days
        while (!dateIterator.isAfter(endDate)) {
            LocalDate finalDate = dateIterator; // Make a final copy of dateIterator
            JButton dayButton = new JButton(String.valueOf(finalDate.getDayOfMonth()));
            dayButton.setFont(new Font("Arial", Font.PLAIN, 12));
            dayButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    datestring = finalDate.format(dayFormatter); // Use the final copy
                }
            });
            calendarPanel.add(dayButton);
            dateIterator = dateIterator.plusDays(1);
        }

        // Refresh the panel
        calendarPanel.revalidate();
        calendarPanel.repaint();
    }
}

public class homescreen {
    public String name;
    public String runtime;
    public String censorcertificate;
    public String movieposter;
    public String genre;
    public String time;
    public String date;
    public String seats;
    public String numberofseats;
    public int cost;
    
    //updating seat numbers to data base
    public void updateseatnumbers(String ssns){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/movieticketbooking?serverTimezone=UTC", "root", "mypass");
            Statement stmt = con.createStatement();
            String tn = name;
            String btime = time;
            String bdate=date;
            String bseats=ssns;
            String sql = "INSERT INTO " + tn + " (date, time, bseats) VALUES (?, ?, ?);";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, bdate);
            pstmt.setString(2, btime);
            pstmt.setString(3, bseats);
            //stmt.executeUpdate(sql);
            // Execute the query
            int rowsAffected = pstmt.executeUpdate();
            System.out.println("Rows inserted: " + rowsAffected);

            // Close connection
            pstmt.close();
            con.close();
            con.close();
            System.out.println("Updated successfully");
        } catch (Exception e) {
            System.out.println("Not connected");
            e.printStackTrace();
        }
        
    }

    //final conclusion
    public void endwindow(){
        try{
            JFrame endwindow = new JFrame(name);
            endwindow.setSize(1300,800);
            JLayeredPane layeredPane = new JLayeredPane();
            layeredPane.setBounds(0, 0, 1300, 800);
            endwindow.add(layeredPane);
            BufferedImage backgroundImage = ImageIO.read(new File("endwindow5.jpeg"));
            JLabel background = new JLabel(new ImageIcon(backgroundImage));
            background.setBounds(0, 0, 1300, 800);
            layeredPane.add(background, Integer.valueOf(0));

            //customer name
            JLabel customername = new JLabel("Name");
            JTextField customernameinput = new JTextField();
            customername.setBounds(100, 100, 600, 50);
            customername.setFont(new Font("Times New Roman", Font.BOLD, 30));
            customername.setForeground(Color.BLACK);
            layeredPane.add(customername,Integer.valueOf(1));
            customernameinput.setBounds(200, 100, 400, 50);
            customernameinput.setFont(new Font("Times New Roman", Font.BOLD, 30));
            customernameinput.setForeground(Color.BLACK);
            layeredPane.add(customernameinput,Integer.valueOf(1));
            //customer age
            JLabel customerage = new JLabel("Age");
            JTextField customerageinput = new JTextField();
            customerage.setBounds(100, 200, 600, 50);
            customerage.setFont(new Font("Times New Roman", Font.BOLD, 30));
            customerage.setForeground(Color.BLACK);
            layeredPane.add(customerage,Integer.valueOf(1));
            customerageinput.setBounds(200, 200, 400, 50);
            customerageinput.setFont(new Font("Times New Roman", Font.BOLD, 30));
            customerageinput.setForeground(Color.BLACK);
            layeredPane.add(customerageinput,Integer.valueOf(1));

            //mode of payment
            JLabel modeofpayment = new JLabel("Mode of payment");
            modeofpayment.setBounds(300, 300, 600, 50);
            modeofpayment.setFont(new Font("Times New Roman", Font.BOLD, 30));
            modeofpayment.setForeground(Color.BLACK);
            layeredPane.add(modeofpayment,Integer.valueOf(1));
            //Gpay
            JRadioButton gpay = new JRadioButton("GPay");
            gpay.setBounds(50,350,400,50);
            gpay.setFont(new Font("Times New Roman", Font.BOLD, 25));
            //gpay.setForeground(Color.BLACK);
            //gpay.setBackground(Color.YELLOW);
            layeredPane.add(gpay,Integer.valueOf(1));
            //Paytm
            JRadioButton paytm = new JRadioButton("Paytm");
            paytm.setBounds(50,400,400,50);
            paytm.setFont(new Font("Times New Roman", Font.BOLD, 25));
            //paytm.setForeground(Color.BLACK);
            layeredPane.add(paytm,Integer.valueOf(1));
            //netbanking
            JRadioButton netbanking = new JRadioButton("Net Banking");
            netbanking.setBounds(50,450,400,50);
            netbanking.setFont(new Font("Times New Roman", Font.BOLD, 25));
            //netbanking.setForeground(Color.BLACK);
            layeredPane.add(netbanking,Integer.valueOf(1));
            ButtonGroup bg=new ButtonGroup();
            bg.add(paytm);bg.add(gpay);bg.add(netbanking);
            //layeredPane.add(bg,Integer.valueOf(1));

            //button
            JButton done=new JButton("DONE");
            done.setBounds(600,700,100,40);
            done.setBackground(Color.CYAN);
            done.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae){
                    try{
                        if (customernameinput.getText().trim().equals("")) {
                            JOptionPane.showMessageDialog(endwindow, "Enter your name!");
                        } 
                        else if (customerageinput.getText().trim().equals("")) {
                            JOptionPane.showMessageDialog(endwindow, "Enter your age!");
                        }
                        //else if()
                        else{
                            JOptionPane.showMessageDialog(endwindow, "Successfully booked your tickets");
                            System.exit(0);
                        }
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            });
            layeredPane.add(done,Integer.valueOf(1));
            //homescreenpage();

            //back button
            JButton back = new JButton("<-");
            back.setBounds(0,0,50,25);
            layeredPane.add(back,Integer.valueOf(1));
            back.addActionListener(
                 new ActionListener(){
                     public void actionPerformed(ActionEvent ae){
                         try {
                             endwindow.dispose();
                         } catch (Exception e) {
                             e.printStackTrace();
                         }
                     }
                 }
            );

            endwindow.setLayout(null);
            endwindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            endwindow.setVisible(true);

        }catch(IOException e){
            e.printStackTrace();
        }

    }
    
    //billing center
    public void bill(ArrayList<Integer> a){
        try{
            JFrame moviescreen = new JFrame(name);
            moviescreen.setSize(1300,800);
            JLayeredPane layeredPane = new JLayeredPane();
            layeredPane.setBounds(0, 0, 1300, 800);
            moviescreen.add(layeredPane);
            BufferedImage backgroundImage = ImageIO.read(new File("theater2.jpeg"));
            JLabel background = new JLabel(new ImageIcon(backgroundImage));
            background.setBounds(0, 0, 1300, 800);
            layeredPane.add(background, Integer.valueOf(0));

            JPanel panel1 = new JPanel();
            panel1.setBounds(50, 170, 203, 304);
            BufferedImage mp = ImageIO.read(new File(movieposter));
            JLabel movie1 = new JLabel(new ImageIcon(mp));
            panel1.add(movie1);
            layeredPane.add(panel1, Integer.valueOf(1));

            JLabel title = new JLabel(name);
            title.setBounds(350, 200,1000,90);
            title.setFont(new Font("Times New Roman", Font.BOLD, 60));
            title.setForeground(Color.ORANGE);
            title.setBackground(Color.WHITE);
            layeredPane.add(title, Integer.valueOf(1));

            cost=0;
            StringBuffer ssn = new StringBuffer();

            for(int i=0;i<a.size();i++){
                ssn.append(a.get(i));
                ssn.append("   ");
                if(a.get(i)<=60&&a.get(i)>=1){
                    cost=+200;
                }
                else if(a.get(i)>=31&&a.get(i)<=180){
                    cost+=150;
                }
                else{
                    cost+=100;
                }
            }
            String ssns;
            ssns=ssn.toString();

            //updating the seat infos
            updateseatnumbers(ssns);
            System.out.println(ssns);
                
            JLabel seatnumbers = new JLabel("<html>Selected seats: " + ssns + "<br>" + "Time: "+time + "<br>" + "Date: "+date + "<br>"+"Cost: "+cost+"</html>");
            seatnumbers.setBounds(350, 250,1000,300);
            seatnumbers.setFont(new Font("Times New Roman", Font.BOLD, 35));
            seatnumbers.setForeground(Color.ORANGE);
            seatnumbers.setBackground(Color.WHITE);
            layeredPane.add(seatnumbers, Integer.valueOf(1));

            //back button
            JButton back = new JButton("<-");
            back.setBounds(0,0,50,25);
            layeredPane.add(back,Integer.valueOf(1));
            back.addActionListener(
                 new ActionListener(){
                     public void actionPerformed(ActionEvent ae){
                         try {
                             moviescreen.dispose();
                         } catch (Exception e) {
                             e.printStackTrace();
                         }
                     }
                 }
            );

            //Pay button
            JButton pay=new JButton("PAY "+Integer.toString(cost));
            pay.setBounds(600,500,100,40);
            pay.setBackground(Color.YELLOW);
            layeredPane.add(pay, Integer.valueOf(1));
            pay.addActionListener(
                 new ActionListener(){
                     public void actionPerformed(ActionEvent ae){
                         try {
                             endwindow();
                         } catch (Exception e) {
                             e.printStackTrace();
                         }
                     }
                 }
            );

            moviescreen.setLayout(null);
            moviescreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            moviescreen.setVisible(true);
        
        }catch(IOException e){
            e.printStackTrace();
        }

    }

    //theater
    public void theater(){
        try{
        JFrame theater = new JFrame(name);
        theater.setSize(1300,800);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 1500, 1000);
        theater.add(layeredPane);
        BufferedImage backgroundImage = ImageIO.read(new File("theater3.jpeg"));
        JLabel background = new JLabel(new ImageIcon(backgroundImage));
        background.setBounds(0, 0, 1300, 800);
        layeredPane.add(background, Integer.valueOf(0));

         //back button
         JButton back = new JButton("<-");
         back.setBounds(0,0,50,25);
         layeredPane.add(back,Integer.valueOf(1));
         back.addActionListener(
                 new ActionListener(){
                     public void actionPerformed(ActionEvent ae){
                         try {
                             theater.dispose();
                         } catch (Exception e) {
                             e.printStackTrace();
                         }
                     }
                 }
             );

        final int seatSelectionLimit = Integer.parseInt(numberofseats);
        final int[] selectedSeatsCount = {0};

        //seat arrangement
        ArrayList<Integer> selectedseatnumbers = new ArrayList<Integer>();
        ArrayList<Integer> bookedSeats = new ArrayList<>();
        int k=1,y=100;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/movieticketbooking?serverTimezone=UTC", "root", "mypass");
            Statement stmt = con.createStatement();
            String tn = name;
            String bdate =date;
            String btime = time;
            String sql = "SELECT bseats FROM " + tn + " WHERE `date` = '" + bdate + "' AND `time` = '" + btime + "';";
            ResultSet rs = (ResultSet) stmt.executeQuery(sql);
            while (rs.next()) {
                String bookedSeatsStr = rs.getString("bseats"); // Assuming bseats contains seat numbers as a string
                String[] seatArray = bookedSeatsStr.split("\\s+|,"); // Split the string by spaces or commas

                // Add each booked seat number to the bookedSeats list
                for (String seat : seatArray) {
                    bookedSeats.add(Integer.parseInt(seat.trim()));
                }
            }
            System.out.println(bookedSeats);

        char ch = 'A';
        for(int i=1;i<=10;i++){
            int x=150;
            for(int j=1;j<=20;j++){
                JButton seatnumber=new JButton(Integer.toString(k));
                seatnumber.setBounds(x,y,30,30);
                seatnumber.setFont(new Font("Times New Roman", Font.PLAIN, 10));
                if (bookedSeats.contains(k)) {
                    seatnumber.setBackground(Color.BLACK); 
                    seatnumber.setEnabled(false);
                } else {
                    seatnumber.setBackground(Color.GREEN); // Set available seats to green
                }
                seatnumber.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent ae) {
                        if (seatnumber.getBackground() != Color.RED && selectedSeatsCount[0] < seatSelectionLimit) {
                            seatnumber.setBackground(Color.RED);
                            JButton clickedButton = (JButton) ae.getSource();
                            // Get the text from the button
                            selectedseatnumbers.add(Integer.parseInt(clickedButton.getText()));
                            System.out.println("Slected seats: "+ selectedseatnumbers);
                            selectedSeatsCount[0]++;
                        } else if (seatnumber.getBackground() == Color.RED) {
                            seatnumber.setBackground(Color.GREEN);
                            JButton clickedButton = (JButton) ae.getSource();
                            selectedseatnumbers.remove(Integer.valueOf(Integer.parseInt(clickedButton.getText())));
                            System.out.println("Slected seats: "+ selectedseatnumbers);
                            selectedSeatsCount[0]--;
                        } else {
                            JOptionPane.showMessageDialog(theater, "You can only select " + seatSelectionLimit + " seats.");
                        }
                    }
                });
                layeredPane.add(seatnumber,Integer.valueOf(1));
                k++;
                if(j==5||j==15){
                    x+=90;
                }
                else{
                x+=45;
                }
            }
            
            if(i==9){
                y+=85;
            }
            else if(i==3){
                y+=85;
            }
            else{
            y+=55;
            }
        }
    }catch(Exception e){
        e.printStackTrace();
    }

        //go to payment
        JButton payment=new JButton("PAY");
        payment.setBounds(600,700,100,40);
        payment.setBackground(Color.YELLOW);
        payment.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae){
                if(selectedseatnumbers.size()!=Integer.parseInt(numberofseats)){
                    JOptionPane.showMessageDialog(theater, "Select "+numberofseats+" seats");
                }
                else{
                    bill(selectedseatnumbers);
                }
            }
        });
        layeredPane.add(payment,Integer.valueOf(1));

        theater.setLayout(null);
        theater.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        theater.setVisible(true);
        
        }catch(IOException e){
            e.printStackTrace();
        }

    }
    
    //number of seat selection
    public void selectseats(){
        try{
        JFrame selectseats = new JFrame(name);
        selectseats.setSize(1300,800);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 1300, 800);
        selectseats.add(layeredPane);
        BufferedImage backgroundImage = ImageIO.read(new File("popcorn.jpeg"));
        JLabel background = new JLabel(new ImageIcon(backgroundImage));
        background.setBounds(0, 0, 1300, 800);
        layeredPane.add(background, Integer.valueOf(0));

        JLabel instruct = new JLabel("How Many Seats?");
        instruct.setBounds(500, 30, 600, 50);
        instruct.setFont(new Font("Times New Roman", Font.BOLD, 40));
        instruct.setForeground(Color.magenta);
        layeredPane.add(instruct,Integer.valueOf(1));

        int x=250,y=300;
        for(int i=0;i<9;i++){
            JButton number = new JButton(seats=Integer.toString(i + 1));
            number.setBounds(x, y, 80, 80);
            number.setBackground(Color.cyan);
            number.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent ae){
                        try {
                            JButton clickedButton = (JButton) ae.getSource();
                            if(number.getBackground()==Color.RED){
                                number.setBackground(Color.cyan);
                            }else{
                            number.setBackground(Color.red);
                            }
                            // Get the text from the button
                            numberofseats = clickedButton.getText();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            );
            x+=100;
            layeredPane.add(number,Integer.valueOf(1));
        }

        //continue button
        JButton Continue = new JButton("Continue");
        Continue.setBounds(600,500,100,40);
        Continue.setBackground(Color.yellow);
        layeredPane.add(Continue,Integer.valueOf(1));
        Continue.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent ae){
                        try {
                            theater();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            );

        //back button
        JButton back = new JButton("<-");
        back.setBounds(0,0,50,25);
        layeredPane.add(back,Integer.valueOf(1));
        back.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent ae){
                        try {
                            selectseats.dispose();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            );
        selectseats.setLayout(null);
        selectseats.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        selectseats.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        
    //selectign date and time
    public void dateandtime(){
        try{
        JFrame dateandtime = new JFrame(name);
        dateandtime.setSize(1300,800);
        dateandtime.getContentPane().setBackground(Color.gray);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 1300, 800);
        dateandtime.add(layeredPane);
        BufferedImage backgroundImage = ImageIO.read(new File("theater2.jpeg"));
        JLabel background = new JLabel(new ImageIcon(backgroundImage));
        background.setBounds(0, 0, 1300, 800);
        layeredPane.add(background, Integer.valueOf(0));

        JLabel acecinemas = new JLabel("ACE CINEMAS");
        acecinemas.setBounds(460, 20,1000,90);
        acecinemas.setFont(new Font("Times New Roman", Font.BOLD, 60));
        acecinemas.setForeground(Color.MAGENTA);
        acecinemas.setBackground(Color.WHITE);
        layeredPane.add(acecinemas, Integer.valueOf(1));

        String titles=name+"   "+censorcertificate;
        JLabel title = new JLabel(titles);
        title.setBounds(100, 100,1000,90);
        title.setFont(new Font("Times New Roman", Font.BOLD, 60));
        title.setForeground(Color.ORANGE);
        title.setBackground(Color.WHITE);
        layeredPane.add(title, Integer.valueOf(1));

        //Setting the title
        JLabel instruct = new JLabel("Select Date and Time");
        instruct.setBounds(450, 200, 500, 50);
        instruct.setFont(new Font("Times New Roman", Font.BOLD, 40));
        instruct.setForeground(Color.LIGHT_GRAY);
        layeredPane.add(instruct, Integer.valueOf(1));

        // Setting calendar
        CalendarPanel calendarPanel = new CalendarPanel();
        calendarPanel.setBounds(100, 300, 350, 350); // Adjust size and position
        layeredPane.add(calendarPanel, Integer.valueOf(1));
        //date=calendarPanel.datestring;
        //System.out.println(date);

        //back button
        JButton back = new JButton("<-");
        back.setBounds(0,0,50,25);
        layeredPane.add(back, Integer.valueOf(1));
        back.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent ae){
                        try {
                            dateandtime.dispose();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            );

        
        //Buttons for timings
        //setting the buttons 
        ButtonGroup group = new ButtonGroup();
        // Morning radio button
        JRadioButton morning = new JRadioButton("MORNING");
        morning.setBounds(600, 350, 200, 70);
        morning.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        layeredPane.add(morning, Integer.valueOf(1));
        group.add(morning);
        morning.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                time = "9 AM";
            }
        });

        // Afternoon radio button
        JRadioButton afternoon = new JRadioButton("AFTERNOON");
        afternoon.setBounds(900, 350, 200, 70);
        afternoon.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        layeredPane.add(afternoon, Integer.valueOf(1));
        group.add(afternoon);
        afternoon.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                time = "1 PM";
            }
        });

        // Evening radio button
        JRadioButton evening = new JRadioButton("EVENING");
        evening.setBounds(600, 450, 200, 70);
        evening.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        layeredPane.add(evening, Integer.valueOf(1));
        group.add(evening);
        evening.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                time = "5 PM";
            }
        });

        // Night radio button
        JRadioButton night = new JRadioButton("NIGHT");
        night.setBounds(900, 450, 200, 70);
        night.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        layeredPane.add(night, Integer.valueOf(1));
        group.add(night);
        night.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                time = "10 PM";
            }
        });
        
        JButton booktickets = new JButton("BOOK TICKETS");
        booktickets.setBounds(550, 700, 150, 50);
        booktickets.setBackground(Color.yellow);
        layeredPane.add(booktickets, Integer.valueOf(1));
        booktickets.addActionListener(
        new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                date=calendarPanel.datestring;
                if(calendarPanel.datestring==null&&time==null){
                    JOptionPane.showMessageDialog(dateandtime, "You must select Date as well as Time");
                }
                else if(calendarPanel.datestring==null){
                    JOptionPane.showMessageDialog(dateandtime, "You must select Date");
                }
                else if(time==null){
                    JOptionPane.showMessageDialog(dateandtime, "You must select Time");
                }
                else{
                try {
                    int result = JOptionPane.showOptionDialog(
                    null, 
                    "Date: "+calendarPanel.datestring+"  "+"Time: "+time, 
                    "Verify Details", 
                    JOptionPane.YES_NO_OPTION, 
                    JOptionPane.PLAIN_MESSAGE, 
                    null, 
                    new Object[] { "Continue", "Go Back" }, 
                    "Go Back"
                );

                // Handle the dialog options
                if (result == JOptionPane.YES_OPTION) {
                    selectseats();
                } else {
                    dateandtime.dispose();
                    dateandtime();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    }
);


        dateandtime.setLayout(null);
        dateandtime.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        dateandtime.setVisible(true);
        }catch(IOException e){
            e.printStackTrace();
        }

    }

    //viewing a particular movie details
    public void movieview(String name, String runtime, String censrocertificate, String moiveposter, String genre){
        try{
        JFrame moviescreen = new JFrame(name);
        moviescreen.setSize(1300,800);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 1300, 800);
        moviescreen.add(layeredPane);
        BufferedImage backgroundImage = ImageIO.read(new File("theater2.jpeg"));
        JLabel background = new JLabel(new ImageIcon(backgroundImage));
        background.setBounds(0, 0, 1300, 800);
        layeredPane.add(background, Integer.valueOf(0));

        JPanel panel1 = new JPanel();
        panel1.setBounds(50, 170, 203, 304);
        BufferedImage mp = ImageIO.read(new File(moiveposter));
        JLabel movie1 = new JLabel(new ImageIcon(mp));
        panel1.add(movie1);
        layeredPane.add(panel1, Integer.valueOf(1));

        JLabel title = new JLabel(name);
        title.setBounds(350, 200,1000,90);
        title.setFont(new Font("Times New Roman", Font.BOLD, 60));
        title.setForeground(Color.ORANGE);
        title.setBackground(Color.WHITE);
        layeredPane.add(title, Integer.valueOf(1));

        String detail = runtime + "        " + genre+ "        " + censorcertificate ;
        JLabel details = new JLabel(detail);
        details.setBounds(350, 300,1000,100);
        details.setFont(new Font("Times New Roman", Font.BOLD, 40));
        details.setForeground(Color.ORANGE);
        details.setBackground(Color.WHITE);
        layeredPane.add(details, Integer.valueOf(1));

        //back button
        JButton back = new JButton("<-");
        back.setBounds(0,0,50,25);
        layeredPane.add(back, Integer.valueOf(1));
        back.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent ae){
                        try {
                            moviescreen.dispose();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            );

        //book tickets buton
        JButton booktickets = new JButton("BOOK TICKETS");
        booktickets.setBounds(500,500,200,40);
        booktickets.setBackground(Color.yellow);
        layeredPane.add(booktickets, Integer.valueOf(1));
        booktickets.addActionListener(
    new ActionListener() {
        public void actionPerformed(ActionEvent ae) {
            try {
                // Check if the movie is A certified
                if (censorcertificate.equalsIgnoreCase("A")) {
                    // Create a custom dialog with options
                    int result = JOptionPane.showOptionDialog(
                        null, 
                        "This movie is rated 'A' and is only for viewers above 18. \nPlease carry a valid ID/Age Proof to the theatre. \nIf you are denied entry due to age or ID issues, you will not get a refund.", 
                        "Content Warning", 
                        JOptionPane.YES_NO_OPTION, 
                        JOptionPane.WARNING_MESSAGE, 
                        null, 
                        new Object[] { "Continue", "Go Back" }, 
                        "Go Back"
                    );

                    // Handle the dialog options
                    if (result == JOptionPane.YES_OPTION) {
                        dateandtime();
                    } 
                    else{
                        moviescreen.dispose();
                    }
                } else {
                    dateandtime();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
);


        moviescreen.setLayout(null);
        moviescreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        moviescreen.setVisible(true);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    //home screen page 
    public void homescreenpage(){
        
        try{
            //creating homescreen
            JFrame homescreen = new JFrame("Home screen");
            homescreen.setSize(1300,800);

            //adding background image
            JLayeredPane layeredPane = new JLayeredPane();
            layeredPane.setBounds(0, 0, 1300, 800);
            homescreen.add(layeredPane);
            BufferedImage backgroundImage = ImageIO.read(new File("theater.jpeg"));
            JLabel background = new JLabel(new ImageIcon(backgroundImage));
            background.setBounds(0, 0, 1300, 800);
            layeredPane.add(background, Integer.valueOf(0)); // Background layer

            //adding title bar
            JLabel title1 = new JLabel("WELCOME TO ACE CINEMAS");
            title1.setBounds(240, 30, 1000, 50);
            title1.setFont(new Font("Times New Roman", Font.BOLD, 50)); 
            //title1.setBackground(Color.PINK);
            title1.setForeground(Color.BLACK);
            layeredPane.add(title1, Integer.valueOf(1));
            JLabel title2 = new JLabel("NOW SHOWING");
            title2.setBounds(440, 70, 600, 50);
            title2.setFont(new Font("Times New Roman", Font.BOLD, 40)); 
            //title2.setBackground(Color.PINK);
            title2.setForeground(Color.BLACK);
            layeredPane.add(title2, Integer.valueOf(1));

            //adding movie posters and title buttons
            //movie1
            JPanel panel1 = new JPanel();
            panel1.setBounds(50, 170, 203, 304);
            movie1details m1d=new movie1details();
            BufferedImage banner1 = ImageIO.read(new File(m1d.movieposter));
            JLabel movie1 = new JLabel(new ImageIcon(banner1));
            JButton movie1Button = new JButton(m1d.name);
            movie1Button.setBounds(50,480,90,30);
            movie1Button.setBackground(Color.ORANGE);
            layeredPane.add(movie1Button, Integer.valueOf(1));
            panel1.add(movie1);
            layeredPane.add(panel1, Integer.valueOf(1));
            //adding commands to buttons
            movie1Button.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent ae){
                        try {
                            name=m1d.name;
                            runtime=m1d.runtime;
                            censorcertificate=m1d.censorcertificate;
                            movieposter=m1d.movieposter;
                            genre=m1d.genre;
                            movieview(name,runtime,censorcertificate,movieposter,genre);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            );

            //movie2
            JPanel panel2 = new JPanel();
            panel2.setBounds(350, 170, 203, 304);
            movie2details m2d =new movie2details();
            BufferedImage banner2 = ImageIO.read(new File(m2d.movieposter));
            JLabel movie2 = new JLabel(new ImageIcon(banner2));
            JButton movie2Button = new JButton(m2d.name);
            movie2Button.setBounds(350,480,90,30);
            movie2Button.setBackground(Color.ORANGE);
            layeredPane.add(movie2Button, Integer.valueOf(1));
            panel2.add(movie2);
            layeredPane.add(panel2, Integer.valueOf(1));
             //adding commands to buttons
            movie2Button.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent ae){
                        try {
                            name=m2d.name;
                            runtime=m2d.runtime;
                            censorcertificate=m2d.censorcertificate;
                            movieposter=m2d.movieposter;
                            genre=m2d.genre;
                            movieview(name,runtime,censorcertificate,movieposter,genre);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            );

            //movie3
            JPanel panel3 = new JPanel();
            panel3.setBounds(650, 170, 203, 304);
            movie3details m3d = new movie3details();
            BufferedImage banner3 = ImageIO.read(new File(m3d.movieposter));
            JLabel movie3 = new JLabel(new ImageIcon(banner3));
            JButton movie3Button = new JButton(m3d.name);
            movie3Button.setBounds(650,480,200,30);
            movie3Button.setBackground(Color.ORANGE);
            layeredPane.add(movie3Button, Integer.valueOf(1));
            panel3.add(movie3);
            layeredPane.add(panel3, Integer.valueOf(1));
            //adding commands to buttons
            movie3Button.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent ae){
                        try {
                            name=m3d.name;
                            runtime=m3d.runtime;
                            censorcertificate=m3d.censorcertificate;
                            movieposter=m3d.movieposter;
                            genre=m3d.genre;
                            movieview(name,runtime,censorcertificate,movieposter,genre);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            );

            //movie4
            JPanel panel4 = new JPanel();
            panel4.setBounds(950, 170, 203, 304);
            movie4details m4d = new movie4details();
            BufferedImage banner4 = ImageIO.read(new File(m4d.movieposter));
            JLabel movie4 = new JLabel(new ImageIcon(banner4));
            JButton movie4Button = new JButton(m4d.name);
            movie4Button.setBounds(950,480,90,30);
            movie4Button.setBackground(Color.ORANGE);
            layeredPane.add(movie4Button, Integer.valueOf(1));
            panel4.add(movie4);
            layeredPane.add(panel4, Integer.valueOf(1));
            //adding commands to buttons
            movie4Button.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent ae){
                        try {
                            name=m4d.name;
                            runtime=m4d.runtime;
                            censorcertificate=m4d.censorcertificate;
                            movieposter=m4d.movieposter;
                            genre=m4d.genre;
                            movieview(name,runtime,censorcertificate,movieposter,genre);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            );

            //exit button
            JButton exit = new JButton("EXIT");
            exit.setBounds(0, 0, 80, 25);
            layeredPane.add(exit, Integer.valueOf(1));
            exit.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent ae){
                        try {
                            System.exit(0);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            );
        
            homescreen.setLayout(null);
            homescreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            homescreen.setVisible(true);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    //main function
    public static void main(String[] args) {
        homescreen hm=new homescreen();
        hm.homescreenpage();
    }
}
