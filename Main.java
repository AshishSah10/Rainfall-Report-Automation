import java.util.List;

public class Main {
   public static void main(String[] args) { 
   
	   //fill the code
	   String path = "C:\\Users\\LENOVO\\Desktop\\cogniztant\\RainFall\\src\\AllCityMonthlyRainfall.txt";
	   RainfallReport rainFallRep = new RainfallReport();
	   List<AnnualRainfall> list1 = rainFallRep.generateRainfallReport(path);
	   System.out.println(list1);
	   for(AnnualRainfall arf : list1){
	       System.out.println(arf.getCityPincode()+" "+arf.getCityName()+" "+arf.getAverageAnnualRainfall());
	   }
	   List<AnnualRainfall> list2 = rainFallRep.findMaximumRainfallCities();
	   for(AnnualRainfall arf : list2){
	       System.out.println(arf.getCityPincode()+" "+arf.getCityName()+" "+arf.getAverageAnnualRainfall());
	   }
   }
}
          