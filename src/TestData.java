import java.time.LocalDate;
import java.time.Month;
import java.util.Random;

public class TestData {
	Random random= new Random();
	
	String expectedDefaultLanguage="en";
	String expectedPhoneNumber="+966554400000";
	String expectedCurrency="SAR";
	String expectedDefaultHotelsClass="sc-ejGVNB jHDdbe";//not selected
	
    LocalDate tomorrow = LocalDate.now().plusDays(1);
    int day = tomorrow.getDayOfMonth();
    String expectedDepartureDay= String.valueOf(day);
    String month = tomorrow.getMonth().name(); 
    String expectedDepartureMonth = month.substring(0, 1) + month.substring(1).toLowerCase();

    
    LocalDate twoDaysFromNow = LocalDate.now().plusDays(2);
    int dayReturn = twoDaysFromNow.getDayOfMonth();
    String expectedReturnDay= String.valueOf(dayReturn);
    String monthReturn = twoDaysFromNow.getMonth().name(); 
    String expectedReturnMonth = monthReturn.substring(0, 1) + monthReturn.substring(1).toLowerCase();
    
    boolean[]languageChange= {true,false};
    int randomLanguageIndex=random.nextInt(languageChange.length);
    boolean switchLanguage=languageChange[randomLanguageIndex];
    String expectedLanguage="";
    
    String expectedStaysTabTitleInEnglish="Hotels, book cheap hotels & get discounted prices on Almosafer.";
    String expectedStaysTabTitleInArabic="فنادق، احجز فنادق رخيصة واحصل على أسعار منخفضة على المسافر.";
    String [] citiesInEng= {"Dubai","Riyadh","Jeddah"};
    int randomCitiesInEngIndex=random.nextInt(citiesInEng.length);
    String expectedCityInEnglish=citiesInEng[randomCitiesInEngIndex];
    String [] citiesInArabic= {"دبي","رياض","جدة"};
    int randomCitiesInArabicIndex=random.nextInt(citiesInArabic.length);
    String expectedCityInArabic=citiesInArabic[randomCitiesInArabicIndex];
    
    String [] randomRoomReservation={"A","B"};
    int randomRoomReservationIndex=random.nextInt(randomRoomReservation.length);
    String randomRoomReservationOption=randomRoomReservation[randomRoomReservationIndex];
  
}
