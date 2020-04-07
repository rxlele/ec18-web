package com.ingsw.model;

import java.sql.Connection;
import com.ingsw.utils.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class CustomerDAO {
	
	private static Connection conn=DBConnection.connect();
	
public CustomerDAO() {
		
	}
	

	
	
	public static Customer findByEmail(String mail) {
        Customer u=null;
        try {
        	PreparedStatement st=conn.prepareStatement("SELECT * FROM public.\"Customer\" WHERE email =?;");
        	st.setString(1, mail);
            ResultSet rs=st.executeQuery();
            if (rs.next()) {
            	u=new Customer(rs.getString(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
        	System.out.println(e.getMessage());
        }
        return u; 
	}
	
	public static void update(Customer u) {
        try {
        	PreparedStatement st=conn.prepareStatement("UPDATE public.\"Customer\" SET passwd=crypt(?,gen_salt('bf')) WHERE email = ?;");
        	st.setString(1, u.getPasswd());
        	st.setString(2, u.getEmail());
        	st.execute();
        	st.close();
        } catch (SQLException e) {
        	System.out.println(e.getMessage());
        }
	}
	
	public void updateDetails(String firstname, String lastname, String address, String city, String province, String mail) {
        try {
        	PreparedStatement st=conn.prepareStatement("UPDATE public.\"Customer\" SET firstname=?, lastname=?, address=?, city=?, province=? WHERE email=?;");
        	st.setString(1, firstname);
        	st.setString(2, lastname);
        	st.setString(3, address);
        	st.setString(4, city);
        	st.setString(5, province);
        	st.setString(6, mail);	
        	st.execute();
        	st.close();
        } catch (SQLException e) {
        	System.out.println(e.getMessage());
        }
	}
	public boolean changePassword(String user, String old_password, String new_password) {
        try {
        	PreparedStatement st=conn.prepareStatement("UPDATE public.\"Customer\" SET passwd=crypt(?,gen_salt('bf')) WHERE email=? AND passwd=crypt(?, passwd);");
        	st.setString(1, new_password);
        	st.setString(2, user);
        	st.setString(3, old_password);
        	if(st.executeUpdate()==1)
        		return true;
        	return false;
        } catch (SQLException e) {
        	System.out.println(e.getMessage());
        	return false;
        }
	}
	public boolean isValid(String mail, String password) {
		try {
			PreparedStatement st = conn.prepareStatement("SELECT * FROM public.\"Customer\" WHERE email =? AND passwd=crypt(?, passwd);");
			PreparedStatement stAdmin = conn.prepareStatement("SELECT * FROM public.\"Admin\" WHERE email =? AND passwd=crypt(?, passwd);");
			st.setString(1, mail);
			st.setString(2, password);
			stAdmin.setString(1, mail);
			stAdmin.setString(2, password);
			ResultSet rs=null;
			if (mail.equals("admin@ec18"))
				rs = stAdmin.executeQuery();
			else
				rs = st.executeQuery();
			if (rs.next()) {
				rs.close();
				st.close();
				return true;
			}
		} catch (SQLException ex) {
			System.out.println("debug1" + ex.getMessage());
		}
		return false;
	}
	
	public boolean createUser(Customer newCustomer) {
		boolean exists=false;
		try {
			PreparedStatement st = conn.prepareStatement("SELECT * FROM public.\"Customer\" WHERE email = ?");
			st.setString(1, newCustomer.getEmail());
			ResultSet rs = st.executeQuery();
			if (!(rs.next())) {
				PreparedStatement st2=conn.prepareStatement("INSERT INTO public.\"Customer\" VALUES(?,?,?,?,?,?,crypt(?, gen_salt('bf')));");
				st2.setString(1, newCustomer.getFirstName());
				st2.setString(2, newCustomer.getLastName());
				st2.setString(3, newCustomer.getAddress());
				st2.setString(4, newCustomer.getCity());
				st2.setString(5, newCustomer.getProvince());
				st2.setString(6, newCustomer.getEmail());
				st2.setString(7, newCustomer.getPasswd());
				st2.execute();
				
			}
			else {
				exists=true;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return exists;
	}
}





