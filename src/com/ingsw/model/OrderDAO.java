package com.ingsw.model;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ingsw.utils.DBConnection;


public class OrderDAO {
	
	private static Connection conn=DBConnection.connect();
	
	public List<Order> findByCustomer(String mail) {
		List<Order> orderList=new ArrayList<>();
		try {
			PreparedStatement st= conn.prepareStatement("SELECT * FROM public.\"Order\" WHERE customer=? ORDER BY \"orderN\" desc;");
			st.setString(1, mail);
			ResultSet rs=st.executeQuery();	
			boolean anyResults = false;
			while (rs.next()) {
				anyResults=true;
				List<Item> listProducts=new ArrayList<>();
				Array products=rs.getArray(4);
				if (products!=null) {
					try {
						Integer [] ar = (Integer[])products.getArray();
						List<Integer> listProductsIDs=new ArrayList<>(Arrays.asList(ar));
						ItemDAO items=new ItemDAO();
						for (Integer i:listProductsIDs) {
							listProducts.add(items.findByID(i));
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				List<String> listColors=new ArrayList<>();
				Array colorArray=rs.getArray(5);
				if (colorArray!=null) {
					try {
						String [] ar = (String[])colorArray.getArray();
						listColors=new ArrayList<>(Arrays.asList(ar));
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				List<Integer> listShipping=new ArrayList<>();
				Array shippingArray=rs.getArray(6);
				if (shippingArray!=null) {
					try {
						Integer [] ar = (Integer[])shippingArray.getArray();
						listShipping=new ArrayList<>(Arrays.asList(ar));
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				List<Integer> listFeedback=new ArrayList<>();
				Array feedbackArray=rs.getArray(8);
				if (feedbackArray!=null) {
					try {
						Integer [] ar = (Integer[])feedbackArray.getArray();
						listFeedback=new ArrayList<>(Arrays.asList(ar));
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
						Order o=new Order(rs.getInt(1),rs.getDate(2),rs.getDouble(3),CustomerDAO.findByEmail(rs.getString(7).toString()),listProducts,listColors,listShipping,listFeedback);
				orderList.add(o);
			}
			if (!anyResults) {
				rs.close();
				st.close();
				return null;
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderList;
	}
	public void createOrder(Order myOrder) {
		try {
			PreparedStatement st= conn.prepareStatement("INSERT INTO public.\"Order\"(\"orderN\", date,\"totalPrice\", \"productID\", colors, shipping, customer, feedback) VALUES (?, ?, ?, ?, ?, ?, ?,?);");
			st.setInt(1, myOrder.getOrderN());
			st.setDate(2, new java.sql.Date(myOrder.getDate().getTime()));
			st.setDouble(3, myOrder.getTotalPrice());
			List<Integer> productIDs=new ArrayList<>();
			for (Item i:myOrder.getProducts()) {
				productIDs.add(i.getID());
			}
			final Integer[] products = productIDs.toArray(new Integer[productIDs.size()]);
			final java.sql.Array productsArray = conn.createArrayOf("INTEGER", products);
			st.setArray(4, productsArray);
			final Integer[] shipping=myOrder.getShipping().toArray(new Integer[myOrder.getShipping().size()]);
			final java.sql.Array shippingArray=conn.createArrayOf("INTEGER", shipping);
			st.setArray(6, shippingArray);
			final String[] colors=myOrder.getColors().toArray(new String[myOrder.getColors().size()]);
			final java.sql.Array colorsArray=conn.createArrayOf("TEXT",colors);
			st.setArray(5, colorsArray);
			st.setString(7, myOrder.getCustomer().getEmail());
			final Integer[] feedback=myOrder.getFeedback().toArray(new Integer[myOrder.getFeedback().size()]);
			final java.sql.Array feedbackArray=conn.createArrayOf("INTEGER", feedback);
			st.setArray(8, feedbackArray);
			st.execute();	
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static int getNext() {
		int orderN=0;
		try {
			
			PreparedStatement st= conn.prepareStatement("SELECT MAX(\"orderN\") FROM public.\"Order\";");
			ResultSet rs=st.executeQuery();	
			boolean anyResults = false;
			while (rs.next()) {
				anyResults=true;
				if (rs.getString(1)!=null)
					orderN= Integer.parseInt(rs.getString(1));
			}
			if (!anyResults) {
				rs.close();
				st.close();
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderN;
	}
	public Order findByOrderN(int orderN) {
		Order order=null;
		try {
			PreparedStatement st= conn.prepareStatement("SELECT * FROM public.\"Order\" WHERE \"orderN\"=?;");
			st.setInt(1, orderN);
			ResultSet rs=st.executeQuery();	
			boolean anyResults = false;
			while (rs.next()) {
				anyResults=true;
				List<Item> listProducts=new ArrayList<>();
				Array products=rs.getArray(4);
				if (products!=null) {
					try {
						Integer [] ar = (Integer[])products.getArray();
						List<Integer> listProductsIDs=new ArrayList<>(Arrays.asList(ar));
						ItemDAO items=new ItemDAO();
						for (Integer i:listProductsIDs) {
							listProducts.add(items.findByID(i));
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				List<String> listColors=new ArrayList<>();
				Array colorArray=rs.getArray(5);
				if (colorArray!=null) {
					try {
						String [] ar = (String[])colorArray.getArray();
						listColors=new ArrayList<>(Arrays.asList(ar));
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				List<Integer> listShipping=new ArrayList<>();
				Array shippingArray=rs.getArray(6);
				if (shippingArray!=null) {
					try {
						Integer [] ar = (Integer[])shippingArray.getArray();
						listShipping=new ArrayList<>(Arrays.asList(ar));
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				List<Integer> listFeedback=new ArrayList<>();
				Array feedbackArray=rs.getArray(8);
				if (feedbackArray!=null) {
					try {
						Integer [] ar = (Integer[])feedbackArray.getArray();
						listFeedback=new ArrayList<>(Arrays.asList(ar));
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
						order=new Order(rs.getInt(1),rs.getDate(2),rs.getDouble(3),CustomerDAO.findByEmail(rs.getString(7).toString()),listProducts,listColors,listShipping,listFeedback);
				
			}
			if (!anyResults) {
				rs.close();
				st.close();
				return null;
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return order;
	}
	public void addFeedback(int orderN, int rating, int index) {
		Order order=findByOrderN(orderN);
		order.getFeedback().add(index, rating);
		order.getProducts().get(index).getFeedback().add(rating);
		try {
			PreparedStatement st= conn.prepareStatement("UPDATE public.\"Order\" SET feedback[?]=? WHERE \"orderN\"=?");
			st.setInt(1, index+1);
			st.setInt(2, rating);
			st.setInt(3, orderN);
			st.execute();	
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			PreparedStatement st= conn.prepareStatement("UPDATE public.\"Item\" SET feedback=feedback || ? WHERE \"productID\"=?");
			st.setInt(1, rating);
			st.setInt(2, order.getProducts().get(index).getID());
			st.execute();	
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			PreparedStatement st= conn.prepareStatement("SELECT getRating(?);");
			st.setInt(1, order.getProducts().get(index).getID());
			st.execute();	
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
	}
	
	public int getTotalSold(int time) {
		int total=0;
		try {
			PreparedStatement st=null;
			switch (time) {
			case 0:
				st= conn.prepareStatement("select count(products) from (select unnest(\"productID\")as products from \"Order\") as d  order by count desc;");
				ResultSet rs=st.executeQuery();	
				boolean anyResults = false;
				while (rs.next()) {
					anyResults=true;
					total=rs.getInt(1);
				}
				if (!anyResults) {
					rs.close();
					st.close();
		
				}
				rs.close();
				st.close();
				break;
			case 1:
				PreparedStatement create_view=conn.prepareStatement("CREATE OR REPLACE VIEW last_week_orders AS\r\n" + 
						"	SELECT * FROM public.\"Order\"\r\n" + 
						"	WHERE date>=CURRENT_DATE - interval '7 day'");
				create_view.execute();
				st= conn.prepareStatement("select count(products) from (select unnest(\"productID\")as products from last_week_orders) as d  order by count desc;");
				ResultSet rs1=st.executeQuery();	
				boolean anyResults1 = false;
				while (rs1.next()) {
					anyResults1=true;
					total=rs1.getInt(1);
				}
				if (!anyResults1) {
					rs1.close();
					st.close();
		
				}
				rs1.close();
				st.close();
				break;
			case 2:
				PreparedStatement create_view2=conn.prepareStatement("CREATE OR REPLACE VIEW last_month_orders AS\r\n" + 
						"	SELECT * FROM public.\"Order\"\r\n" + 
						"	WHERE date>=CURRENT_DATE - interval '30 day'");
				create_view2.execute();
				st= conn.prepareStatement("select count(products) from (select unnest(\"productID\")as products from last_month_orders) as d  order by count desc;");
				ResultSet rs2=st.executeQuery();	
				boolean anyResults2 = false;
				while (rs2.next()) {
					anyResults2=true;
					total=rs2.getInt(1);
				}
				if (!anyResults2) {
					rs2.close();
					st.close();
		
				}
				rs2.close();
				st.close();
				break;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return total;
	}
	
	public ArrayList<Item> getBestSellers(boolean isAndroid){
		ArrayList<Item> itemList=new ArrayList<>();
		try {
			PreparedStatement st=null;
			if (isAndroid)
				st= conn.prepareStatement("select products,count(products),\"isDeleted\" from (select unnest(\"productID\")as products from \"Order\" ) as d join \"Item\" on products=\"Item\".\"productID\" where \"isDeleted\"=false or \"isDeleted\" IS NULL group by products,\"isDeleted\" order by count desc limit 10;");
			else
				st= conn.prepareStatement("select products,count(products),\"isDeleted\" from (select unnest(\"productID\")as products from \"Order\" ) as d join \"Item\" on products=\"Item\".\"productID\" where \"isDeleted\"=false or \"isDeleted\" IS NULL group by products,\"isDeleted\" order by count desc;");

			ResultSet rs=st.executeQuery();	
			boolean anyResults = false;
			while (rs.next()) {
				anyResults=true;
				Item item=new ItemDAO().findByID(rs.getInt(1));
				itemList.add(item);
			}
			if (!anyResults) {
				rs.close();
				st.close();
				return null;
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return itemList;
	}
	public ArrayList<Item> getBestSellers(int time){
		ArrayList<Item> itemList=new ArrayList<>();
		try {
			PreparedStatement st=null;
			switch (time) {
			case 0:
				st= conn.prepareStatement("select products,count(products) from (select unnest(\"productID\")as products from \"Order\") as d group by products order by count desc;");
				ResultSet rs=st.executeQuery();	
				boolean anyResults = false;
				while (rs.next()) {
					anyResults=true;
					Item item=new ItemDAO().findByID(rs.getInt(1));
					itemList.add(item);
				}
				if (!anyResults) {
					rs.close();
					st.close();
					return null;
				}
				rs.close();
				st.close();
				break;
			case 1:
				PreparedStatement create_view=conn.prepareStatement("CREATE OR REPLACE VIEW last_week_orders AS\r\n" + 
						"	SELECT * FROM public.\"Order\"\r\n" + 
						"	WHERE date>=CURRENT_DATE - interval '7 day'");
				create_view.execute();
				st= conn.prepareStatement("select products,count(products) from (select unnest(\"productID\")as products from last_week_orders) as d group by products order by count desc;");
				ResultSet rs1=st.executeQuery();	
				boolean anyResults1 = false;
				while (rs1.next()) {
					anyResults1=true;
					Item item=new ItemDAO().findByID(rs1.getInt(1));
					itemList.add(item);
				}
				if (!anyResults1) {
					rs1.close();
					st.close();
					return null;
				}
				rs1.close();
				st.close();
				break;
			case 2:
				PreparedStatement create_view2=conn.prepareStatement("CREATE OR REPLACE VIEW last_month_orders AS\r\n" + 
						"	SELECT * FROM public.\"Order\"\r\n" + 
						"	WHERE date>=CURRENT_DATE - interval '30 day'");
				create_view2.execute();
				st= conn.prepareStatement("select products,count(products) from (select unnest(\"productID\")as products from last_month_orders) as d group by products order by count desc;");
				ResultSet rs2=st.executeQuery();	
				boolean anyResults2 = false;
				while (rs2.next()) {
					anyResults2=true;
					Item item=new ItemDAO().findByID(rs2.getInt(1));
					itemList.add(item);
				}
				if (!anyResults2) {
					rs2.close();
					st.close();
					return null;
				}
				rs2.close();
				st.close();
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return itemList;
	}
	public List<Integer> getBestSellersData(int time){
		List<Integer> orderDataList=new ArrayList<>();
		try {
			PreparedStatement st=null;
			switch (time) {
			case 0:
				st= conn.prepareStatement("select count(products) from (select unnest(\"productID\")as products from \"Order\") as d group by products order by count desc;");
				ResultSet rs=st.executeQuery();	
				boolean anyResults = false;
				while (rs.next()) {
					anyResults=true;
					orderDataList.add(rs.getInt(1));
				}
				if (!anyResults) {
					rs.close();
					st.close();
				}
				rs.close();
				st.close();
				break;
			case 1:
				PreparedStatement create_view=conn.prepareStatement("CREATE OR REPLACE VIEW last_week_orders AS\r\n" + 
						"	SELECT * FROM public.\"Order\"\r\n" + 
						"	WHERE date>=CURRENT_DATE - interval '7 day'");
				create_view.execute();
				st= conn.prepareStatement("select count(products) from (select unnest(\"productID\")as products from last_week_orders) as d group by products order by count desc;");
				ResultSet rs1=st.executeQuery();	
				boolean anyResults1 = false;
				while (rs1.next()) {
					anyResults1=true;
					orderDataList.add(rs1.getInt(1));
				}
				if (!anyResults1) {
					rs1.close();
					st.close();
				}
				rs1.close();
				st.close();
				break;
			case 2:
				PreparedStatement create_view2=conn.prepareStatement("CREATE OR REPLACE VIEW last_month_orders AS\r\n" + 
						"	SELECT * FROM public.\"Order\"\r\n" + 
						"	WHERE date>=CURRENT_DATE - interval '30 day'");
				create_view2.execute();
				st= conn.prepareStatement("select count(products) from (select unnest(\"productID\")as products from last_month_orders) as d group by products order by count desc;");
				ResultSet rs2=st.executeQuery();	
				boolean anyResults2 = false;
				while (rs2.next()) {
					anyResults2=true;
					orderDataList.add(rs2.getInt(1));
				}
				if (!anyResults2) {
					rs2.close();
					st.close();
				}
				rs2.close();
				st.close();
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderDataList;
	}
	public List<Double> getWeeklyReceipts(){
		List<Double> receiptsList=new ArrayList<>();
		try {
			PreparedStatement st=null;
				st= conn.prepareStatement("SELECT t.day::date,b\r\n" + 
						"FROM   generate_series( CURRENT_DATE  - interval '6 day', CURRENT_DATE, interval  '1 day') AS t(day)\r\n" + 
						"\r\n" + 
						"LEFT JOIN\r\n" + 
						"\r\n" + 
						"(SELECT date,sum(\"totalPrice\") as b\r\n" + 
						"from \"Order\" as b\r\n" + 
						"WHERE date >CURRENT_DATE - interval '7 day'\r\n" + 
						"group by date\r\n" + 
						"order by date desc) AS foo on t.day=foo.date");

			ResultSet rs=st.executeQuery();	
			boolean anyResults = false;
			while (rs.next()) {
				anyResults=true;
				System.out.println(rs.getDouble(2));
				if (rs.getDouble(2)==0.0)
					receiptsList.add(0.0);
				else {
					receiptsList.add(Cart.round(rs.getDouble(2), 2));
				}
			}
			if (!anyResults) {
				rs.close();
				st.close();
				return null;
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return receiptsList;
	}
	
	public double getTotalReceipts() {
		double total=0.0;
		PreparedStatement st=null;
		try {
			st= conn.prepareStatement("select sum(\"totalPrice\") from \"Order\";");
			ResultSet rs=st.executeQuery();	
			boolean anyResults = false;
			while (rs.next()) {
				anyResults=true;
				total=rs.getDouble(1);
			}
			if (!anyResults) {
				rs.close();
				st.close();
			}
			rs.close();
			st.close();			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return total;
	}
	
	public List<String> bestDayOfReceipt() {
		String pattern = "dd-MM-yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

		List<String> best=new ArrayList<String>();
		PreparedStatement st=null;
		try {
			st= conn.prepareStatement("select date,sum(\"totalPrice\") from \"Order\" group by date order by sum desc limit 1;");
			ResultSet rs=st.executeQuery();	
			boolean anyResults = false;
			while (rs.next()) {
				anyResults=true;
				best.add(simpleDateFormat.format(rs.getDate(1)));
				best.add(Double.toString(rs.getDouble(2)));
			}
			if (!anyResults) {
				rs.close();
				st.close();
			}
			rs.close();
			st.close();			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return best;
	}
}
