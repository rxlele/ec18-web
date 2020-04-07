package com.ingsw.model;

import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ingsw.utils.DBConnection;

public class ItemDAO {
	
	 public static final Charset ISO_8859_1 = Charset.forName("ISO-8859-1");
	 public static final Charset UTF_8 = Charset.forName("UTF-8");
	
	 /** La query per la lettura di un singolo prodotto. */
    private static final String READ_QUERY = "SELECT * FROM public.\"Item\" WHERE \"productID\"=?;";
    /** La query per filtrare i prodotti. */
    private static final String FILTER_QUERY="SELECT * FROM public.\"Item\" WHERE (\"isDeleted\"=false OR \"isDeleted\" IS NULL) AND";
    /** La query per cercare i prodotti. */
    private static final String SEARCH_QUERY="SELECT * FROM public.\"Item\" WHERE (\"isDeleted\"=false OR \"isDeleted\" IS NULL) AND (name ILIKE ? OR description ILIKE ?);";
    
    private static Connection conn=DBConnection.connect();
    
	public ItemDAO() {}
	
	@SuppressWarnings("deprecation")
	public ArrayList<Item> filterByCategory(String type, String name){
		ArrayList<Item> itemList=new ArrayList<>();
		String query="";
		switch (type) {
			case "category": {
		    	query=" category='"+name+"';";  
				break;
			}
			case "price": {
				if (Integer.parseInt(name)==900) {
					query="( (\"onSale\"=false AND price>="+name+") OR (\"onSale\"=true AND price>="+(Integer.parseInt(name)+Integer.parseInt(name)*0.2)+"));";
				}
				else {
					query="((\"onSale\"=false AND price>="+name+" AND price<="+(Integer.parseInt(name)+100)+") OR "
							+ "(\"onSale\"=true AND price>="+(Integer.parseInt(name)+(Integer.parseInt(name)*0.2))+" AND price<="+(((100+Integer.parseInt(name))+((Integer.parseInt(name)+100)*0.2)))+"));";
				}
				break;
			}
			case "brand": {
				query=" '"+name+"' LIKE ANY(tag)";
				break;
			}
			case "offer": {
		    	query=" \"onSale\"=true;";  
				break;
			}
		}
		
		try {
			PreparedStatement st= conn.prepareStatement(FILTER_QUERY+query);
			System.out.println(st.toString());
			ResultSet rs=st.executeQuery();	
			boolean anyResults = false;
			while (rs.next()) {
				anyResults=true;
				Item i=new Item(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDouble(4),
				  rs.getArray(5),rs.getBoolean(6),rs.getInt(7),rs.getString(8),
				  rs.getArray(9),rs.getArray(10),rs.getArray(11));
				itemList.add(i);
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
	
	@SuppressWarnings("deprecation")
	public ArrayList<Item> searchItem(String name){
		ArrayList<Item> itemList=new ArrayList<>();
		try {
			PreparedStatement st= conn.prepareStatement(SEARCH_QUERY);
			System.out.println(st.toString());
			st.setString(1,"%"+name+"%");
			st.setString(2,"%"+name+"%");
			ResultSet rs=st.executeQuery();	
			boolean anyResults = false;
			while (rs.next()) {
				anyResults=true;
				Item i=new Item(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDouble(4),
						  rs.getArray(5),rs.getBoolean(6),rs.getInt(7),rs.getString(8),
						  rs.getArray(9),rs.getArray(10),rs.getArray(11));
				itemList.add(i);
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
	public void adminInsertItem(String name, String description, double price, List<String> path_img, boolean onSale, String category, List<String> tag, List<String> colors) {
		try {
			PreparedStatement st= conn.prepareStatement("INSERT INTO public.\"Item\"(\"productID\", name, description, price, path_img, \"onSale\",category, tag, colors)VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
			st.setInt(1, nextProductID());
			st.setString(2, name);
			st.setString(3,description);
			st.setDouble(4, price);
			final String[] imgs = path_img.toArray(new String[path_img.size()]);
			final java.sql.Array imgArray = conn.createArrayOf("TEXT", imgs);
			st.setArray(5, imgArray);
			st.setBoolean(6, onSale);
			st.setString(7, category);
			final String[] tags = tag.toArray(new String[tag.size()]);
			final java.sql.Array tagArray = conn.createArrayOf("TEXT", tags);
			st.setArray(8, tagArray);
			final String[] color = colors.toArray(new String[colors.size()]);
			final java.sql.Array colorArray = conn.createArrayOf("TEXT", color);
			st.setArray(9, colorArray);
			st.execute();	
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int updateItem(int productID, String name, String description, double price, List<String> path_img, 
			boolean onSale, String category, List<String> tag, List<String> colors, int photosMode) {
		int result=0;
		PreparedStatement st=null;
		try {
			final String[] imgs = path_img.toArray(new String[path_img.size()]);
			final java.sql.Array imgArray = conn.createArrayOf("TEXT", imgs);
			switch (photosMode) {
			case 1: //DELETE_AND_INSERT
				st= conn.prepareStatement("UPDATE public.\"Item\" SET name=?, description=?, price=?, path_img=?, \"onSale\"=?, "
						+ "category=?, tag=?, colors=? WHERE \"productID\"=?;");
				st.setArray(4, imgArray);
				break;
			case 2: //INSERT_ONLY
				st= conn.prepareStatement("UPDATE public.\"Item\" SET name=?, description=?, price=?, path_img=path_img || ?, \"onSale\"=?, "
						+ "category=?, tag=?, colors=? WHERE \"productID\"=?;");
				st.setArray(4, imgArray);
				break;
			case 3: //DELETE_AND_FIX
				st= conn.prepareStatement("UPDATE public.\"Item\" SET name=?, description=?, price=?, path_img=?, \"onSale\"=?, "
						+ "category=?, tag=?, colors=? WHERE \"productID\"=?;");
				st.setArray(4, imgArray);
				break;
			case 4: //NO_CHANGES
				st= conn.prepareStatement("UPDATE public.\"Item\" SET name=?, description=?, price=?, \"onSale\"=?, "
						+ "category=?, tag=?, colors=? WHERE \"productID\"=?;");
				break;
			}
			if (photosMode!=4) {
				st.setString(1, name);
				byte[] ptext = description.getBytes(ISO_8859_1); 
				String descriptionUTF8 = new String(ptext, UTF_8); 
				st.setString(2,descriptionUTF8);
				st.setDouble(3, price);
				st.setBoolean(5, onSale);
				st.setString(6, category);
				final String[] tags = tag.toArray(new String[tag.size()]);
				final java.sql.Array tagArray = conn.createArrayOf("TEXT", tags);
				st.setArray(7, tagArray);
				final String[] color = colors.toArray(new String[colors.size()]);
				final java.sql.Array colorArray = conn.createArrayOf("TEXT", color);
				st.setArray(8, colorArray);
				st.setInt(9, productID);
				result=st.executeUpdate();	
				st.close();
			} else {
				st.setString(1, name);
				st.setString(2,description);
				st.setDouble(3, price);
				st.setBoolean(4, onSale);
				st.setString(5, category);
				final String[] tags = tag.toArray(new String[tag.size()]);
				final java.sql.Array tagArray = conn.createArrayOf("TEXT", tags);
				st.setArray(6, tagArray);
				final String[] color = colors.toArray(new String[colors.size()]);
				final java.sql.Array colorArray = conn.createArrayOf("TEXT", color);
				st.setArray(7, colorArray);
				st.setInt(8, productID);
				result=st.executeUpdate();	
				st.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public Item findByID(int ID) {
		Item i=null;
		try {
			PreparedStatement st= conn.prepareStatement(READ_QUERY);
			st.setInt(1,ID);
			ResultSet rs=st.executeQuery();	
			boolean anyResults = false;
			while (rs.next()) {
				anyResults=true;
				i=new Item(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDouble(4),
						  rs.getArray(5),rs.getBoolean(6),rs.getInt(7),rs.getString(8),
						  rs.getArray(9),rs.getArray(10),rs.getArray(11),rs.getBoolean(12),rs.getInt(13));
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
		return i;
	}
	
	public int nextProductID() {
		try {
			PreparedStatement st= conn.prepareStatement("SELECT MAX(\"productID\") FROM public.\"Item\";");
			ResultSet rs=st.executeQuery();	
			boolean anyResults = false;
			while (rs.next()) {
				anyResults=true;
				return Integer.parseInt(rs.getString(1))+1;
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
		return 0;
	}
	public void insertPhoto(int productID, String path) {
		try {
			PreparedStatement st= conn.prepareStatement("UPDATE public.\"Item\" SET path_img= array_append(path_img,?::text)  WHERE \"productID\"=?;");
			st.setString(1, path);
			st.setInt(2, productID);
			st.execute();	
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("deprecation")
	public ArrayList<Item> refreshItems(){
		ArrayList<Item> itemList=new ArrayList<>();
		try {
			PreparedStatement st= conn.prepareStatement("SELECT * FROM public.\"Item\" WHERE \"isDeleted\"=false OR \"isDeleted\" IS NULL ORDER BY \"productID\" asc;");
			ResultSet rs=st.executeQuery();	
			boolean anyResults = false;
			while (rs.next()) {
				anyResults=true;
				Item i=new Item(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDouble(4),
						  rs.getArray(5),rs.getBoolean(6),rs.getInt(7),rs.getString(8),
						  rs.getArray(9),rs.getArray(10),rs.getArray(11));
				itemList.add(i);
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
	@SuppressWarnings("deprecation")
	public ArrayList<Item> getLatestItems(){
		ArrayList<Item> itemList=new ArrayList<>();
		try {
			PreparedStatement st= conn.prepareStatement("SELECT * FROM public.\"Item\" WHERE (\"isDeleted\"=false OR \"isDeleted\" IS NULL) ORDER BY \"productID\" desc limit 10;");
			ResultSet rs=st.executeQuery();	
			boolean anyResults = false;
			while (rs.next()) {
				anyResults=true;
				Item i=new Item(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDouble(4),
						  rs.getArray(5),rs.getBoolean(6),rs.getInt(7),rs.getString(8),
						  rs.getArray(9),rs.getArray(10),rs.getArray(11));
				itemList.add(i);
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
	
	public int deleteItem(int productID) {
		int success;
		try {
			PreparedStatement st=conn.prepareStatement("UPDATE public.\"Item\" SET \"isDeleted\"=true WHERE \"productID\"=?;");
			st.setInt(1, productID);
			success=st.executeUpdate();
		} catch (SQLException e) {
			success=-1;
		}
		return success; 	
	}
	
	@SuppressWarnings("deprecation")
	public List<Item> getFilters(String filterType, String subFilter){
		System.out.println("filterType: "+filterType+"   subFilter: "+subFilter);
		List<Item> result=new ArrayList<Item>();
		try {
			PreparedStatement st=null;
			switch (filterType) {
			case "Categoria":
				st=conn.prepareStatement("SELECT * FROM public.\"Item\" WHERE (\"isDeleted\"=false OR \"isDeleted\" IS NULL) AND category=?;");
				st.setString(1, subFilter);
				break;
			case "In offerta":
				st=conn.prepareStatement("SELECT * FROM public.\"Item\" WHERE (\"isDeleted\"=false OR \"isDeleted\" IS NULL) AND \"onSale\"=true;");
				break;
			case "Prezzo":
				switch (subFilter) {
				case "€ 0-100":
					st=conn.prepareStatement("SELECT * FROM public.\"Item\" WHERE (\"isDeleted\"=false OR \"isDeleted\" IS NULL) AND (\"onSale\"=true AND price>=0 AND price<=120)"
							+ "OR (\"onSale\"=false AND price>=0 AND price<=100)");
					break;
				case "€ 100-200":
					st=conn.prepareStatement("SELECT * FROM public.\"Item\" WHERE (\"isDeleted\"=false OR \"isDeleted\" IS NULL) AND (\"onSale\"=true AND price>=120 AND price<=240)"
							+ "OR (\"onSale\"=false AND price>=100 AND price<=200)");
					break;
				case "€ 200-300":
					st=conn.prepareStatement("SELECT * FROM public.\"Item\" WHERE (\"isDeleted\"=false OR \"isDeleted\" IS NULL) AND (\"onSale\"=true AND price>=240 AND price<=360)"
							+ "OR (\"onSale\"=false AND price>=200 AND price<=300)");
					break;
				case "€ 300-400":
					st=conn.prepareStatement("SELECT * FROM public.\"Item\" WHERE (\"isDeleted\"=false OR \"isDeleted\" IS NULL) AND (\"onSale\"=true AND price>=360 AND price<=480)"
							+ "OR (\"onSale\"=false AND price>=300 AND price<=400)");
					break;
				case "€ 400-500":
					st=conn.prepareStatement("SELECT * FROM public.\"Item\" WHERE (\"isDeleted\"=false OR \"isDeleted\" IS NULL) AND (\"onSale\"=true AND price>=480 AND price<=600)"
							+ "OR (\"onSale\"=false AND price>=400 AND price<=500)");
					break;
				case "€ 500-600":
					st=conn.prepareStatement("SELECT * FROM public.\"Item\" WHERE (\"isDeleted\"=false OR \"isDeleted\" IS NULL) AND (\"onSale\"=true AND price>=600 AND price<=720)"
							+ "OR (\"onSale\"=false AND price>=500 AND price<=600)");
					break;
				case "€ 600-700":
					st=conn.prepareStatement("SELECT * FROM public.\"Item\" WHERE (\"isDeleted\"=false OR \"isDeleted\" IS NULL) AND (\"onSale\"=true AND price>=720 AND price<=840)"
							+ "OR (\"onSale\"=false AND price>=600 AND price<=700)");
					break;
				case "€ 700-800":
					st=conn.prepareStatement("SELECT * FROM public.\"Item\" WHERE (\"isDeleted\"=false OR \"isDeleted\" IS NULL) AND (\"onSale\"=true AND price>=840 AND price<=960)"
							+ "OR (\"onSale\"=false AND price>=700 AND price<=800)");
					break;
				case "€ 800-900":
					st=conn.prepareStatement("SELECT * FROM public.\"Item\" WHERE (\"isDeleted\"=false OR \"isDeleted\" IS NULL) AND (\"onSale\"=true AND price>=960 AND price<=1180)"
							+ "OR (\"onSale\"=false AND price>=800 AND price<=900)");
					break;
				case "€ 900+":
					st=conn.prepareStatement("SELECT * FROM public.\"Item\" WHERE (\"isDeleted\"=false OR \"isDeleted\" IS NULL) AND (\"onSale\"=true AND price>=1180)"
							+ "OR (\"onSale\"=false AND price>= 900)");
					break;
				default:
					
				}
				break;
			case "Brand":
				result=getItemsByBrand(subFilter);
				break;
			case "Ultimi arrivi":
				result=getLatestItems();
				break;
			case "Più venduti":
				result=new OrderDAO().getBestSellers(true);
				break;
			default:
				result=refreshItems();
				break;
			}
			if (result.isEmpty()) {
				if (st!=null) {
					ResultSet rs=st.executeQuery();	
					boolean anyResults = false;
					while (rs.next()) {
						anyResults=true;
						Item i=new Item(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDouble(4),
								  rs.getArray(5),rs.getBoolean(6),rs.getInt(7),rs.getString(8),
								  rs.getArray(9),rs.getArray(10),rs.getArray(11));
						result.add(i);
					}
					if (!anyResults) {
						rs.close();
						st.close();
						return null;
					}
					rs.close();
					st.close();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	public List<String> getBrands(){
		List<String> brands=new ArrayList<String>();
		try {
			PreparedStatement st= conn.prepareStatement("SELECT DISTINCT tag[1]	FROM public.\"Item\" WHERE (\"isDeleted\"=false OR \"isDeleted\" IS NULL);");
			ResultSet rs=st.executeQuery();	
			boolean anyResults = false;
			while (rs.next()) {
				anyResults=true;
				brands.add(rs.getString(1));
				
				
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
		return brands;
	}
	@SuppressWarnings("deprecation")
	public List<Item> getItemsByBrand(String brand){
		List<Item> itemList=new ArrayList<Item>();
		try {
			PreparedStatement st= conn.prepareStatement("SELECT * FROM public.\"Item\" WHERE tag[1]=? AND (\"isDeleted\"=false OR \"isDeleted\" IS NULL);");
			st.setString(1, brand);
			ResultSet rs=st.executeQuery();	
			boolean anyResults = false;
			while (rs.next()) {
				anyResults=true;
				Item i=new Item(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDouble(4),
					  rs.getArray(5),rs.getBoolean(6),rs.getInt(7),rs.getString(8),
					  rs.getArray(9),rs.getArray(10),rs.getArray(11));
				itemList.add(i);
			}
			if (!anyResults) {
				rs.close();
				st.close();
				return null;
			}
			rs.close();
			st.close();
		}  catch (SQLException e) {
				e.printStackTrace();
		}
		return itemList;
	}
	public List<Item> getMostLiked(){
		List<Item> itemList=new ArrayList<Item>();
		try {
			PreparedStatement st= conn.prepareStatement("select * from public.\"Item\" where rating IS NOT NULL order by rating desc;"); 
			ResultSet rs=st.executeQuery();	
			boolean anyResults = false;
			while (rs.next()) {
				anyResults=true;
				Item i=new Item(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDouble(4),
					  rs.getArray(5),rs.getBoolean(6),rs.getInt(7),rs.getString(8),
					  rs.getArray(9),rs.getArray(10),rs.getArray(11),false,rs.getInt(13));
				itemList.add(i);
			}
			if (!anyResults) {
				rs.close();
				st.close();
				return null;
			}
			rs.close();
			st.close();
		}  catch (SQLException e) {
				e.printStackTrace();
		}
		return itemList;
	}
	public int getTotalFeedbacks() {
		int total=0;
		try {
			PreparedStatement st=null;
			st= conn.prepareStatement("select count(products) from (select unnest(feedback)as products from \"Item\") as d  order by count desc;");
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return total;
	}
	public List<Integer> getFeedbackNumbers(){
		List<Integer> feedbackList=new ArrayList<>();
		try {
			PreparedStatement st= conn.prepareStatement("select products,count(products) from (select unnest(feedback)as products from \"Item\") as d group by products order by products desc;");
			ResultSet rs=st.executeQuery();	
			boolean anyResults = false;
			while (rs.next()) {
				anyResults=true;
				feedbackList.add(rs.getInt(2));
			}
			if (!anyResults) {
				rs.close();
				st.close();
				return null;
			}
			rs.close();
			st.close();
		}  catch (SQLException e) {
				e.printStackTrace();
		}
		return feedbackList;
	}
	
	public List<Item> getMostClicked(){
		List<Item> itemList=new ArrayList<>();
		try {
			PreparedStatement st= conn.prepareStatement("select * from \"Item\" order by clicks desc;");
			ResultSet rs=st.executeQuery();	
			boolean anyResults = false;
			while (rs.next()) {
				anyResults=true;
				Item i=new Item(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDouble(4),
						  rs.getArray(5),rs.getBoolean(6),rs.getInt(7),rs.getString(8),
						  rs.getArray(9),rs.getArray(10),rs.getArray(11),false,rs.getInt(13));
				itemList.add(i);
			}
			if (!anyResults) {
				rs.close();
				st.close();
				return null;
			}
			rs.close();
			st.close();
		}  catch (SQLException e) {
				e.printStackTrace();
		}
		return itemList;
	}
	public void addClick(int ID) {
		try {
			PreparedStatement st= conn.prepareStatement("update \"Item\" set clicks=clicks+1 where \"productID\"=?");
			st.setInt(1, ID);
			st.execute();
			st.close();
		}  catch (SQLException e) {
				e.printStackTrace();
		}
	}
	public void deleteFromDB(int ID) {
		try {
			PreparedStatement st=conn.prepareStatement("DELETE FROM public.\"Item\" WHERE \"productID\"=?;");
			st.setInt(1, ID);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}