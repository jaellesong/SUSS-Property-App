package com.example.susspropertyapp;

import android.app.ActivityOptions;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.susspropertyapp.login.ui.MainLoginActivity;
import com.example.susspropertyapp.model.Agent;
import com.example.susspropertyapp.model.AgentDataManager;
import com.example.susspropertyapp.model.ChatRoom;
import com.example.susspropertyapp.model.ChatRoomDataManager;
import com.example.susspropertyapp.model.Listing;
import com.example.susspropertyapp.model.ListingDataManager;
import com.example.susspropertyapp.model.MyMsg;
import com.example.susspropertyapp.model.MyMsgDataManager;
import com.example.susspropertyapp.model.UserDataManager;

public class SplashActivity extends AppCompatActivity {
    private UserDataManager loginDM;
    private ListingDataManager listingDM;
    private AgentDataManager agentDM;
    private ChatRoomDataManager chatDM;
    private MyMsgDataManager msgDM;

    private final int SPLASH_DISPLAY_LENGTH = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setTheme(R.style.AppTheme);
        if (getSupportActionBar() != null)  getSupportActionBar().hide();
        initLoginData();
        initListingData();
        initAgentData();
        initChatMsgData();

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(SplashActivity.this, MainLoginActivity.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SplashActivity.this);
                SplashActivity.this.startActivity(mainIntent, options.toBundle());
                SplashActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);


    }
    public void initLoginData(){
        loginDM = new UserDataManager(this);
        Cursor c = loginDM.searchUser("z@z.com","zzz");
        if (c == null || c.getCount() == 0) {
            loginDM.insert("R059432Z","z@z.com","zzz","Song Jia Lin","true");
        }
        c = loginDM.searchUser("y@y.com","yyy");
            if (c == null || c.getCount() == 0) {
            loginDM.insert("L3002382K","y@y.com","yyy","Jerry Zhang","true");
        }
        c = loginDM.searchUser("x@x.com","xxx");
        if (c == null || c.getCount() == 0) {
            loginDM.insert("Q1810270","x@x.com","xxx","Sally Tan Mei Mei","false");
        }
        c = loginDM.searchUser("w@xw.com","www");
        if (c == null || c.getCount() == 0) {
            loginDM.insert("A1234567","w@w.com","www","Jeff Lee","false");
        }
    }
    public void initListingData(){

        listingDM = new ListingDataManager(this);
        Listing listing1 = new Listing();
        listing1.setTitle("Singapore University of Social Sciences");
        listing1.setAddress("446 Clementi Rd, Singapore 599491");
        listing1.setLatitude("1.3291");
        listing1.setLongitude("1103.7762");
        listing1.setDescription("The Singapore University of Social Sciences (SUSS) is the sixth autonomous university in Singapore.[4][5] Established in 2017, SUSS focuses on applied degree programmes primarily in the social sciences.[6] In 2017, SUSS received its inaugural class of 2,137 graduates");
        listing1.setType("Landed");
        listing1.setBathrooms("10");
        listing1.setBedrooms("0");
        listing1.setPrice("10000000");
        listing1.setArea("3040");
        listing1.setYear("2005");
        listing1.setTenure("Freehold");
        listing1.setStatus("Resale");
        listing1.setKeywords("School");
        listing1.setAgentId("R059432Z");


        Listing listing2 = new Listing();
        listing2.setTitle("Highgate Condo");
        listing2.setAddress("66 Toh Tuck Road, Singapore 596723");
        listing2.setLatitude("1.3418");
        listing2.setLongitude("103.7663");
        listing2.setDescription("Built in 1995 by Lum Chang Properties Pte Ltd, Highgate is located in"+
                "District 21 and has a total of 216 units.\n" +
                "\n" +
                "This Condominium/Apartment is accessible through the nearest train stations such as "+
                "Beauty World MRT (DT5), and Bukit Batok (NS2). The nearest primary schools are " +
                "Bukit Timah Primary School, Keming Primary School, and Pei Hwa Presbyterian Primary School.\n" +
                "\n" +
                "This listing is close to amenities like Giant(Bukit Batok East Avenue 3)," +
                " NTUC Fairprice (Bukit Batok East), and Giant(Beauty World Centre). " +
                "The closest shopping malls are Bukit Timah Shopping Centre, Beauty World Plaza, "+
                "and Bukit Timah Plaza.");
        listing2.setType("HDB");
        listing2.setBathrooms("2");
        listing2.setBedrooms("1");
        listing2.setPrice("1800000");
        listing2.setArea("1324");
        listing2.setYear("1995");
        listing2.setTenure("Freehold");
        listing2.setStatus("Resale");
        listing2.setKeywords("Furnished");
        listing2.setAgentId("R059432Z");


        Listing listing3 = new Listing();
        listing3.setTitle("Suites De Laurel");
        listing3.setAddress("3 Jalan Anak Bukit, Singapore 588998");
        listing3.setLatitude("1.341580");
        listing3.setLongitude("103.777860");
        listing3.setDescription("Cosy and Beautifully maintained home for sale.\n" +
                "This has been a beautiful 1+study where this wonderful couple called home and it"+
                " was here that a small family was created and now with this new little bundle of joy,"+
                " they are looking for the next wonderful family to fill this space with"+
                " much unwillingness as its such a serene and tranquil home where little baby "+
                "can sleep peacefully every nap time. An extra room is needed for the extra help so "+
                "this home is now available for sale at fair market value. "+
                "It is 1km to Methodist Girls Primary & Pei Hwa Presbyterian Primary School, "+
                "both top schools in Singapore. Not to mention a short walk to King Albert Park mall"+
                " and MRT or across to Beauty World MRT station. "+
                "You will appreciate when you see th unit and development for yourself.");
        listing3.setType("Condo");
        listing3.setBathrooms("1");
        listing3.setBedrooms("1");
        listing3.setPrice("1000000");
        listing3.setArea("624");
        listing3.setYear("1974");
        listing3.setTenure("99-103 years");
        listing3.setStatus("Resale");
        listing3.setKeywords("Greenery View");
        listing3.setAgentId("L3002382K");


        Listing listing4 = new Listing();
        listing4.setTitle("Pine Groove");
        listing4.setAddress("Pine Groove, Singapore 590001");
        listing4.setLatitude("1.318360");
        listing4.setLongitude("103.775210");
        listing4.setDescription("Spacious 3 Bedders facing pool and greenery. Within about 10mins walk to Dover MRT station.\n" +
                "\n" +
                "Split levels in this spacious unit allows segregation of spaces such that each family member can have privacy to do their own things. High floor unit with beautiful sprawling grounds and surrounded by greenery!\n" +
                "\n" +
                "Direct access to popular Ulu Pandan Park Connector for some outdoor activities; or enjoy the pool and clubhouse facilities that also come with a minimart selling drinks and food stuff!\n" +
                "\n" +
                "Near to United World College International, NUS High, ACS Independent, Fairfield Methodist Sch, Pei Tong PS, and Henry Park PS.");
        listing4.setDescription("41 Sims Drive is a 99-year Leasehold listing, located in Geylang.");
        listing4.setType("HDB");
        listing4.setBathrooms("3");
        listing4.setBedrooms("4");
        listing4.setPrice("1590000");
        listing4.setArea("1679");
        listing4.setYear("2015");
        listing4.setTenure("99-103 years");
        listing4.setStatus("Resale");
        listing4.setKeywords("Gym");
        listing4.setAgentId("L3002382K");

        Listing listing5 = new Listing();
        listing5.setTitle("Pandan Valley");
        listing5.setAddress("2 Pandan Valley, Singapore 597626");
        listing5.setLatitude("1.3204");
        listing5.setLongitude("103.7789");
        listing5.setDescription("There is a number of public transportation close to Pandan Valley that residents can make use of. The closest MRT station is Dover MRT. The closest bus stops are located at Pandan Valley Condominium, Avian Condominium, Royal Mas Singapore Pte Ltd, Montiew and Henry Park. For those with vehicles, the shopping district located at Orchard Road can be easily accessed via Holland Road in 10 to 15 minutes.");
        listing5.setType("Condo");
        listing5.setBathrooms("4");
        listing5.setBedrooms("6");
        listing5.setPrice("3500000");
        listing5.setArea("2884");
        listing5.setYear("1984");
        listing5.setTenure("Freehold");
        listing5.setStatus("Resale");
        listing5.setKeywords("Maid Room");
        listing5.setAgentId("R053248J");


        Listing listing6 = new Listing();
        listing6.setTitle("Toh Tuck Crescent");
        listing6.setAddress("51 Toh Tuck Pl, Singapore 596826");
        listing6.setLatitude("1.3389");
        listing6.setLongitude("103.7660");
        listing6.setDescription("Beautiful 2 storey corner terrace house for sale!\n" +
                "\n" +
                "- 3bedrooms and 1 maid rooms\n" +
                "- Spacious dining and living hall\n" +
                "- Easily accessible to PIE and AYE\n" +
                "- 100% move in condition\n" +
                "- 1st level - kitchen, maids room, yard area and backyard\n" +
                "- 2nd Level - spacious master en-suite with a walk in wardrobe, 2 good size bedrooms overlooking greenery, Common toilet and family hall.\n" +
                "- TOP date 1992");
        listing6.setType("Landed");
        listing6.setBathrooms("3");
        listing6.setBedrooms("4");
        listing6.setPrice("1800000");
        listing6.setArea("1720");
        listing6.setYear("1992");
        listing6.setTenure("99-103 years");
        listing6.setStatus("Resale");
        listing6.setKeywords("Corner Unit");
        listing6.setAgentId("R053248J");


        Listing listing7 = new Listing();
        listing7.setTitle("Vardale");
        listing7.setAddress("10 De Souza Ave, Singapore 599449");
        listing7.setLatitude("1.3454");
        listing7.setLongitude("103.7692");
        listing7.setDescription("Verdale is an upcoming condo development situated at the centre of Bukit Timah just next to Jalan Jurong Kechil, District 21 of Singapore. The Jalan Jurong Kechil site received a total of 3 bids. China Overseas Holding and CSC Land Group topped the bid after submitting with their $215 million offer. The bid price for Verdale reflects a land rate of $1,002 psf per plot ratio. With respect to the land rate, Verdale is expected to have a breakeven ranging between $1,640 and $1,700 psf.");
        listing7.setType("Condo");
        listing7.setBathrooms("2");
        listing7.setBedrooms("2");
        listing7.setPrice("1280000");
        listing7.setArea("742");
        listing7.setYear("2020");
        listing7.setTenure("99-103 years");
        listing7.setStatus("New");
        listing7.setKeywords("Unfurnished");
        listing7.setAgentId("R046182F");

        Listing listing8 = new Listing();
        listing8.setTitle("Cavendish Park");
        listing8.setAddress("20 Pine Grove, Singapore 597595");
        listing8.setLatitude("1.3168");
        listing8.setLongitude("103.7754");
        listing8.setDescription("Bye to cramp up rooms. Get yourself a Sizeable 3 bedder premium layout near premium school.\n" +
                "\n" +
                "Great For Work From Home and family!\n" +
                "Good Sized, Good Priced, Spacious and efficiently layout 3-bedder plus Utility room!\n" +
                "Be free to enjoy this private condominium apartment, with full condo facilities, while WORKING FROM HOME!!");
        listing8.setType("Condo");
        listing8.setBathrooms("3");
        listing8.setBedrooms("3");
        listing8.setPrice("1600000");
        listing8.setArea("1292");
        listing8.setYear("1960");
        listing8.setTenure("Freehold");
        listing8.setStatus("Resale");
        listing8.setKeywords("Pool");
        listing8.setAgentId("R046182F");

        Listing listing9 = new Listing();
        listing9.setTitle("Jardin");
        listing9.setAddress("966 Dunearn Rd, Singapore 589488");
        listing9.setLatitude("1.3377");
        listing9.setLongitude("103.7801");
        listing9.setDescription("Jardin is a freehold development located at 966 Dunearn Road, Singapore 589488, in District 21, minutes ride to Dover MRT Station. Completed in 2013, it comprises of 10 storeys and 140 units. Jardin is close to Bukit Timah Nature Reserve and Green Fairways Golf Course & Driving Range. Condo Facilities at Jardin Facilities at Jardin include basement car park, swimming pool, gymnasium room, and 24 hours security. Condo Amenities near Jardin Numerous bus services are available close to Jardin. Several local ad international schools, such as Ngee Ann Polytechnic and Swiss School, are located just a short bus ride away. Numerous restaurants are sprawled across the area and nearby shopping centres, such as Bukit Timah Plaza and Beauty World Centre, hosts a range of amenities including supermarkets and more eating establishments. For vehicle owners, it takes 15 - 20 minutes to get to the business hub and the Orchard Road shopping district, via Ayer Rajah Expressway and Dunearn Road respectively.");
        listing9.setType("Condo");
        listing9.setBathrooms("3");
        listing9.setBedrooms("4");
        listing9.setPrice("3350000");
        listing9.setArea("1808");
        listing9.setYear("1974");
        listing9.setTenure("Freehold");
        listing9.setStatus("Resale");
        listing9.setKeywords("Not Furnished");
        listing9.setAgentId("R041159D");

        Listing listing10 = new Listing();
        listing10.setTitle("15 Toh Yi Drive");
        listing10.setAddress("15 Toh Yi Dr, Singapore 590015");
        listing10.setLatitude("1.3390");
        listing10.setLongitude("103.7720");
        listing10.setDescription("This executive maisonette has been beautifully redesigned giving a cozy and landed house feel. The unit boasts of its open living space on the first floor, spacious living and dining areas, and newly-renovated master bedroom and bathroom. It also offers more privacy to the owner as all bedrooms are on the second floor.\n" +
                "\n" +
                "Situated in a very convenient location as a lot of eateries, cafes, groceries, shops are in close proximity to the unit, plus Beauty World MRT is just 6 minutes walk.");
        listing10.setType("HDB");
        listing10.setBathrooms("3");
        listing10.setBedrooms("3");
        listing10.setPrice("980000");
        listing10.setArea("1572");
        listing10.setYear("1989");
        listing10.setTenure("99-103 years");
        listing10.setStatus("Resale");
        listing10.setKeywords("Not Furnished");
        listing10.setAgentId("R041159D");

        Cursor testC = null;
        testC = listingDM.searchRecord(listing1.getTitle());
        if (testC == null || testC.getCount() == 0) listingDM.insert(listing1);
        testC = listingDM.searchRecord(listing2.getTitle());
        if (testC == null || testC.getCount() == 0) listingDM.insert(listing2);
        testC = listingDM.searchRecord(listing3.getTitle());
        if (testC == null || testC.getCount() == 0) listingDM.insert(listing3);
        testC = listingDM.searchRecord(listing4.getTitle());
        if (testC == null || testC.getCount() == 0) listingDM.insert(listing4);
        testC = listingDM.searchRecord(listing5.getTitle());
        if (testC == null || testC.getCount() == 0) listingDM.insert(listing5);
        testC = listingDM.searchRecord(listing6.getTitle());
        if (testC == null || testC.getCount() == 0) listingDM.insert(listing6);
        testC = listingDM.searchRecord(listing7.getTitle());
        if (testC == null || testC.getCount() == 0) listingDM.insert(listing7);
        testC = listingDM.searchRecord(listing8.getTitle());
        if (testC == null || testC.getCount() == 0) listingDM.insert(listing8);
        testC = listingDM.searchRecord(listing9.getTitle());
        if (testC == null || testC.getCount() == 0) listingDM.insert(listing9);
        testC = listingDM.searchRecord(listing10.getTitle());
        if (testC == null || testC.getCount() == 0) listingDM.insert(listing10);


    }
    public void initAgentData() {
        agentDM = new AgentDataManager(this);
        Agent agent1 = new Agent();
        agent1.setName("Song Jia Lin");
        agent1.setId("R059432Z");
        agent1.setRating("3.9");
        agent1.setBio("ERA Group Top Lister | Top Achiever | Director\n" +
                "ERA REALTY NETWORK PTE LTD");

        Agent agent2 = new Agent();
        agent2.setName("Jerry Zhang");
        agent2.setId("L3002382K");
        agent2.setRating("4.2");
        agent2.setBio("I have satisfied many of my clients during successful transactions. "+
                "And I will continue to fulfill your needs: to get your"+
                " best possible price in the shortest possible time. "+
                "If you are looking for selling, buying or leasing property, " +
                "Please feel free to contact me at 9859 3403.");

        Agent agent3 = new Agent();
        agent3.setName("ZHANG QING (STEVEN)");
        agent3.setId("R053248J");
        agent3.setRating("3.2");
        agent3.setBio("PROPNEX REALTY PTE LTD");

        Agent agent4 = new Agent();
        agent4.setName("YONG TECK YI");
        agent4.setId("R046182F");
        agent4.setRating("2.4");
        agent4.setBio("MLI PROPERTY COLLECTION PTE LTD");

        Agent agent5 = new Agent();
        agent5.setName("WU JIANQI");
        agent5.setId("R041159D");
        agent5.setRating("5.0");
        agent5.setBio("ORANGETEE & TIE PTE LTD");

        Cursor testC = null;
        testC = agentDM.searchRecord(agent1.getId());
        if (testC == null || testC.getCount() == 0) agentDM.insert(agent1);
        testC = agentDM.searchRecord(agent2.getId());
        if (testC == null || testC.getCount() == 0) agentDM.insert(agent2);
        testC = agentDM.searchRecord(agent3.getId());
        if (testC == null || testC.getCount() == 0) agentDM.insert(agent3);
        testC = agentDM.searchRecord(agent4.getId());
        if (testC == null || testC.getCount() == 0) agentDM.insert(agent4);
        testC = agentDM.searchRecord(agent5.getId());
        if (testC == null || testC.getCount() == 0) agentDM.insert(agent5);

    }
    public void initChatMsgData() {
        chatDM = new ChatRoomDataManager(this);
        msgDM = new MyMsgDataManager(this);
        Cursor testC = null;

        ChatRoom cr1 = new ChatRoom();
        cr1.setChatRoom("R059432Z","A1234567","Singapore University of Social Sciences");
        testC = chatDM.getChat(cr1.getRoomId());
        if (testC == null || testC.getCount() == 0) chatDM.insert(cr1);


        MyMsg m1 = new MyMsg();
        m1.setMsg(cr1.getRoomId(),cr1.getRoomId()+"_0","A1234567","27-10-2020 10:00:00","Hi");
        testC = msgDM.getMsg(m1.getMsgId());
        if (testC == null || testC.getCount() == 0) msgDM.insert(m1);

        MyMsg m2 = new MyMsg();
        m2.setMsg(cr1.getRoomId(),cr1.getRoomId()+"_1","R059432Z","27-10-2020 10:01:00","Hajimemashite");
        testC = msgDM.getMsg(m2.getMsgId());
        if (testC == null || testC.getCount() == 0) msgDM.insert(m2);

        MyMsg m3 = new MyMsg();
        m3.setMsg(cr1.getRoomId(),cr1.getRoomId()+"_2","A1234567","27-10-2020 10:01:00","I am interested");
        testC = msgDM.getMsg(m3.getMsgId());
        if (testC == null || testC.getCount() == 0) msgDM.insert(m3);

        ChatRoom cr2 = new ChatRoom();
        cr2.setChatRoom("R059432Z","L3002382K","default");
        cr2.setIsSeller("true");
        testC = chatDM.getChat(cr2.getRoomId());
        if (testC == null || testC.getCount() == 0) chatDM.insert(cr2);

        MyMsg m4 = new MyMsg();
        m4.setMsg(cr2.getRoomId(), cr2.getRoomId()+"_0", "R059432Z","27-10-2020 10:00:00","Aloha");
        testC = msgDM.getMsg(m4.getMsgId());
        if (testC == null || testC.getCount() == 0) msgDM.insert(m4);

        MyMsg m5 = new MyMsg();
        m5.setMsg(cr2.getRoomId(),cr2.getRoomId()+"_1", "L3002382K","27-10-2020 10:01:00","Nihao");
        testC = msgDM.getMsg(m5.getMsgId());
        if (testC == null || testC.getCount() == 0) msgDM.insert(m5);

        MyMsg m6 = new MyMsg();
        m6.setMsg(cr2.getRoomId(),cr2.getRoomId()+"_2", "R059432Z","27-10-2020 10:01:00","I am very interested");
        testC = msgDM.getMsg(m6.getMsgId());
        if (testC == null || testC.getCount() == 0) msgDM.insert(m6);

    }


}

