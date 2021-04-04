package IT17152938.Contoller;

import IT16178700.Service.ControlTypeCounter;
import IT17018760.Inheritance;
import IT17056212.Size;
import IT17152938.Service.FileReader;
import IT17152938.Service.NestingCounter;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;

public class QualityController {
    public JsonArray getQuality(String file){
        FileReader fileReader=new FileReader(file);
        ArrayList<String> nestig=getNextingcount(fileReader);
        ArrayList<String>inhe=getAllInheritanse(fileReader);
        ArrayList<Integer>sizecount=getAllcountSize(fileReader);
        ArrayList<String>sizeAll=getAllSize(fileReader);
        ArrayList<String>ctcAll=getAllCtc(fileReader);


        JsonObject res2 = new JsonObject();
        JsonArray data = new JsonArray();
        boolean re=true;
        for(int i=0;i<fileReader.getFileFormat2().size();i++){
            if(!("".equalsIgnoreCase(nestig.get(i))||"0".equalsIgnoreCase(nestig.get(i)))){
                re =false;
                break;
            }
        }


        for(int i=0;i<fileReader.getFileFormat2().size();i++){


            // Create new JSON Object
            JsonObject res = new JsonObject();
            res.addProperty("code", fileReader.getFileFormat2().get(i));
            res.addProperty("nextcount", nestig.get(i));
            res.addProperty("inheritcount", inhe.get(i));
            res.addProperty("sizecount", sizecount.get(i).toString());
            res.addProperty("sizeall", sizeAll.get(i).toString());
            res.addProperty("ctc", ctcAll.get(i).toString());
            res.addProperty("re", re);
            System.out.println();
            data.add(res);

        }

        System.out.println(data.toString());

        res2.addProperty("data", data.toString());



        return data;
    }


    public ArrayList<String> getNextingcount(FileReader fileReader){
        return new NestingCounter(fileReader).getLineNestingCount();
    }

    public ArrayList<Integer> getAllcountSize(FileReader fileReader){
        return new Size(fileReader).getAllcount();
    }

    public ArrayList<String> getAllSize(FileReader fileReader){
        return new Size(fileReader).getAll();
    }

    public ArrayList<String> getAllInheritanse(FileReader fileReader){
        return new Inheritance(fileReader).getFullInheritance();
    }

    public ArrayList<String> getAllCtc(FileReader fileReader){
        return new ControlTypeCounter(fileReader).getLineControlCount();
    }

}
