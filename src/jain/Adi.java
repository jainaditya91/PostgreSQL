	package jain;
	import java.sql.*;

import javax.swing.JOptionPane;

import java.io.*;

	
	public class Adi {
			
			public Connection con;
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			
			
			void connect(){
							try{
					Class.forName("org.postgresql.Driver");
					con = DriverManager.getConnection("jdbc:postgresql://cop5725-postgresql.cs.fiu.edu:5432/fall16_ajain018", "fall16_ajain018", "5970907");
					String message="Connection Successfull";
					JOptionPane.showMessageDialog(null,message);
													}
							catch(Exception e){
												System.err.println(e); 
												}
							}
			
			void authentication(){
							try{
									System.out.println("Enter the username");
									String uname=br.readLine();
									System.out.println("Enter the password");
									String passw=br.readLine();
									String sql="{?=call dbms.verif(?,?)}";
									CallableStatement pstmt=con.prepareCall(sql);
										
									pstmt.setString(2,uname);
									pstmt.setString(3, passw);
									pstmt.registerOutParameter(1,Types.INTEGER);
									pstmt.execute();
									int result=pstmt.getInt(1);
										if (result==1){
											String message1="Congratulations!! You are authenticated Successfully";
											JOptionPane.showMessageDialog(null,message1);
														}
										else{
											String message2="Authentication unsuccessfull!! Username/Password Incorrect!! Please try again";
											JOptionPane.showMessageDialog(null,message2);
											}
							}
							catch(Exception e) {
												System.out.println("Exception occurred"+e);
												}
				
							}
			
			void update(){
					try{
							System.out.println("Enter your username");
							String name=br.readLine();
							System.out.println("Enter Old Password");
							String opas=br.readLine();
							System.out.println("Enter New Password");
							String npas=br.readLine();
							String sql1="{?=call dbms.update(?,?,?)}";
							CallableStatement cstmt=con.prepareCall(sql1);
							cstmt.setString(2,name);
							cstmt.setString(3,opas);
							cstmt.setString(4,npas);
							cstmt.registerOutParameter(1,Types.INTEGER);
							cstmt.execute();
							int reslt=cstmt.getInt(1);
								if(reslt==1){
									String message3="Password Updated Successfully!!";
									JOptionPane.showMessageDialog(null,message3);
											}
								else{
									String message4="Password Update UnSuccessfull. Incorrect Username/Old Password";
									JOptionPane.showMessageDialog(null,message4);
								}
					}
					catch(Exception e) {
									System.out.println("Exception occurred"+e);
										}
					
						}
			
			void retrieve(){
				try{
						System.out.println("Enter your Patient id");
						Integer id=Integer.parseInt(br.readLine());
						String sql2="{call dbms.retrieve(?)}";
						CallableStatement cstmt=con.prepareCall(sql2);
						cstmt.setInt(1, id);
						ResultSet rs=cstmt.executeQuery();
						while(rs.next())						
						System.out.println("First Name is "+rs.getString(2)+"Middle Initial is "+rs.getString(3)+"		Last Name is "+rs.getString(4)+"Date of Birth is "+rs.getString(5)+"		Gender is "+rs.getString(6)+"			Email id is "+rs.getString(7)+"			Password is "+rs.getString(8)+"Address 1 is "+rs.getString(9)+"					Address 2 is "+rs.getString(10)+"				Address 3 is "+rs.getString(11)+"				Contact Number is "+rs.getString(12)+"			SSN is "+rs.getString(13));
				}
				catch(Exception e) {
					System.out.println("Exception occurred"+e);
									}
				
						}
				
			void pref(){
				try{
						System.out.println("Enter the Credit Card Number");
						String cc=br.readLine();
						System.out.println("Enter the Patient ID");
						Integer id=Integer.parseInt(br.readLine());
						System.out.println("Do you want to set this as your preferred Card? If Yes, Please enter Yes; Otherwise enter No");
						String isp=br.readLine();
						String sql3="{call dbms.pr(?,?,?)}";
						CallableStatement st=con.prepareCall(sql3);
						st.setString(1,cc);
						st.setInt(2,id);
						st.setString(3,isp);
						ResultSet rs=st.executeQuery();
						while(rs.next()){
							
							int i=rs.getInt(1);
							if(i==0){
								String message6="Credit Card Number or Patient ID is incorrect";
								JOptionPane.showMessageDialog(null,message6);
							}
							else{
								String message7="Preference Updated Successfully!!";
								JOptionPane.showMessageDialog(null,message7);
							System.out.println("Credit Card number is "+ rs.getString(3));
							System.out.println("Address associated with the card is "+rs.getString(2));
							}
										}
						}
				catch(Exception e) {
					System.out.println("Exception occurred"+e);
									}
					}

			
			void medavailability(){
				try{
					System.out.println("Enter Medicine ID");
					Integer mid=Integer.parseInt(br.readLine());
					System.out.println("Enter Medicine Date");
					String dt=br.readLine();
					String sql6="{call dbms.medava(?,?)}";
					CallableStatement cst=con.prepareCall(sql6);
					cst.setInt(1,mid);
					cst.setString(2,dt);
					ResultSet rt=cst.executeQuery();
					while(rt.next()){
						int it=rt.getInt(1);
						
						if(it==0){
							String message8="Medicine Date or Medicine ID is incorrect";
							JOptionPane.showMessageDialog(null,message8);
						}
						else{
							System.out.println("Available Quantity of Medicine is "+rt.getString(2));
							}
									}
					}
				catch(Exception e) {
					System.out.println("Exception occurred"+e);
									}
				
					}

			void charges(){
				try{
						System.out.println("Enter the Patient id");
						Integer idd=Integer.parseInt(br.readLine());
						System.out.println("Enter the billed date");
						String dat=br.readLine();
						String sql7="{call dbms.charge(?,?)}";
						CallableStatement mt=con.prepareCall(sql7);
						mt.setInt(1,idd);
						mt.setString(2,dat);
						ResultSet resl=mt.executeQuery();
						while(resl.next())	{
							int paid=Integer.parseInt(resl.getString(4));
							int insurance =Integer.parseInt(resl.getString(7));
							int cccharge=Integer.parseInt(resl.getString(9));
							int room=Integer.parseInt(resl.getString(12));
							int lab=Integer.parseInt(resl.getString(13));
							int radiology=Integer.parseInt(resl.getString(14));
							int prescription=Integer.parseInt(resl.getString(15));
							int prov=Integer.parseInt(resl.getString(16));
							int nur=Integer.parseInt(resl.getString(17));
							int add=paid+insurance+cccharge+room+lab+radiology+prescription+prov+nur;
							System.out.println("Bill ID is "+resl.getString(1)+"	Services are "+resl.getString(2)+"		Billed Date is "+resl.getString(3)+"	Amount paid is "+resl.getString(4)+"		Due date is "+resl.getString(5)+"			Billing Address is "+resl.getString(6)+"			Insurance Stipend is "+resl.getString(7)+"		Amount balance is "+resl.getString(8)+"					Credit Card charge is "+resl.getString(9)+"				Patient ID is "+resl.getString(10)+"				Provider ID is "+resl.getString(11)+"	Room Cost is "+resl.getString(12)+"		Lab Cost is "+resl.getString(13)+"		Radiology Cost is "+resl.getString(14)+"		Prescription Cost is "+resl.getString(15)+"		Provider Cost is "+resl.getString(16)+"		Nurse Cost is "+resl.getString(17)+"		Total Bill is "+add);
											}
							
				}
				catch(Exception e) {
					System.out.println("Exception occurred"+e);
									}
				
						}
			public static void main(String args[]) {
				Adi a=new Adi();
				try
			   {
			        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			        a.connect();
			        for(int i=1;i!=0;i++)
			        {
			        System.out.println("\n Please select any option from below Menu:");
			        System.out.println("\n 1. Authenticate User \n 2. Update Password \n 3. Retrieve Patient's Information \n 4. Set Prefered Credit Card \n 5. Get Medicine Availablility \n 6. Bill \n 7. EXIT");
			        Integer in = Integer.parseInt(br.readLine());
			        
			        switch(in)
			        {
			        case 1:
			        {
			            a.authentication();
			    break;
			        }
			        case 2:
			        {
			            a.update();
			    break;
			        }
			        case 3:
			        {
			            a.retrieve();
			    break;
			        }
			        case 4:
			        {
			            a.pref();
			    break;
			        }
			        case 5:
			        {
			            a.medavailability();
			    break;
			        }
			        case 6:
			        {
			        	a.charges();
			        	break;
			        }
			        case 7:
			        {
			        	System.out.println("You have successfully exit");
			            System.exit(0);
			        }
			        default:
			        {
			            System.out.println("Sorry! You have selected invalid option");
			            
			        }
			       }
			        
			   }
			        }
			   
			
			        catch(Exception e) {
			        				}
			        }
	}