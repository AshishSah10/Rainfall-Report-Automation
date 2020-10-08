import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RainfallReport {

	//Write the required business logic as expected in the question description
	public List<AnnualRainfall> generateRainfallReport(String filePath) {
		//fill the code
		List<AnnualRainfall> annualRainfall = new ArrayList<>();
		try(BufferedReader in = new BufferedReader(
				new InputStreamReader(new FileInputStream(filePath), "UTF-8"))){
					String line;
					while((line = in.readLine()) != null) {
						//text.append(line).append("\n");
						String[] data = line.split(",");
						try {
						    this.validate(data[0]);
						    int cityPincode = Integer.parseInt(data[0]);
							String cityName = data[1];
							double[] monthlyRainfall = new double[12];
							for(int i = 1;i <= 12; i++)
								monthlyRainfall[i-1] = Integer.parseInt(data[1+i]);
							AnnualRainfall cityRainfall = new AnnualRainfall();
							cityRainfall.setCityPincode(cityPincode);
							cityRainfall.setCityName(cityName);
							cityRainfall.calculateAverageAnnualRainfall(monthlyRainfall);
							annualRainfall.add(cityRainfall);
						}catch(InvalidCityPincodeException e) {
							System.out.println(e.getMessage());
						}
						
					}
		}catch(IOException e) {
			e.printStackTrace();
		}
		return annualRainfall;
	}
	
	public List<AnnualRainfall>  findMaximumRainfallCities() {
			//fill the code
		List<AnnualRainfall> list1 = new ArrayList<>();
		DBHandler db1 = new DBHandler();
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = db1.establishConnection();
			stmt = conn.createStatement();
			String query = "Select * from AnnualRainfall";
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
		         //Retrieve by column name
				 AnnualRainfall cityAnnualRF = new AnnualRainfall();
		         int cityPincode  = rs.getInt("city_pincode");
		         String cityName = rs.getString("city_name");
		         double avgAnnualRainfall = rs.getDouble("average_annual_rainfall");
		         cityAnnualRF.setCityPincode(cityPincode);
		         cityAnnualRF.setCityName(cityName);
		         cityAnnualRF.setAverageAnnualRainfall(avgAnnualRainfall);
		         list1.add(cityAnnualRF);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			try{
		         if(stmt!=null)
		            conn.close();
		      }catch(SQLException se){
		      }
			try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }
		      }
		return list1;
	}
	
	
	public boolean validate(String cityPincode) throws InvalidCityPincodeException {
			//fill the code
    		if(cityPincode.length() == 5) {
    			return true;
    		}
    		throw new InvalidCityPincodeException("Invalid City Pincode");
	}

}
