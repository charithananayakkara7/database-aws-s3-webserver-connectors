package resources;



import pojo.Person;
import pojo.CreateBook;
import pojo.Location;

import java.util.ArrayList;
import java.util.List;

public class requestpayloads {

    public Person create(String name, String language , String address){

        Person p =new Person();
        p.setAccuracy(50);
        p.setAddress(address);
        p.setLanguage(language);
        p.setPhone_number("(+91) 983 893 3937");
        p.setWebsite("https://slslanka.com");
        p.setName(name);
        List<String> myList =new ArrayList<String>();
        myList.add("shoe park");
        myList.add("shop");
        p.setTypes(myList);
        Location l =new Location();
        l.setLat(-38.383494);
        l.setLng(33.427362);
        //p.setLocation();
        p.setLocation(l);
        System.out.println(p.getLocation().getLat());
        return p;
    }
 public String  delete_car(String place_id){
     System.out.println("req_payload>>>>>>>>>>>>>>>>>"+place_id);
     return "{\r\n    \"place_id\":\""+place_id+"\"\r\n}";

 }


 public CreateBook  create_car(String name){

CreateBook CB =new CreateBook();
CB.setId("55555555");
CB.setModelType("Car");
CB.setName(name);
CB.setEdition(1);
CB.setState("Created");

return CB;
 }

}
