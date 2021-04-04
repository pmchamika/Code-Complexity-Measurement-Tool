package IT17152938.Contoller;

import IT17152938.Service.FileReader;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class QualityControllerTest {
    private String filepath;
    private ArrayList<String> nestig=new ArrayList<>();
    private ArrayList<Integer> size=new ArrayList<>();
    private ArrayList<String> inhe=new ArrayList<>();
    private ArrayList<String> ctc=new ArrayList<>();


    @Before
    public void setUp() throws Exception {
        filepath="spmtest.java";
        nestig.add("");
        nestig.add("");
        nestig.add("0");
        nestig.add("");
        nestig.add("0");
        nestig.add("1");
        nestig.add("");
        nestig.add("1");
        nestig.add("");
        nestig.add("");
        nestig.add("");

        size.add(0);
        size.add(0);
        size.add(4);
        size.add(0);
        size.add(1);
        size.add(2);
        size.add(0);
        size.add(6);
        size.add(0);
        size.add(0);
        size.add(0);

        inhe.add("2");
        inhe.add("");
        inhe.add("2");
        inhe.add("");
        inhe.add("2");
        inhe.add("2");
        inhe.add("");
        inhe.add("2");
        inhe.add("");
        inhe.add("");
        inhe.add("");

        ctc.add("0");
        ctc.add("");
        ctc.add("0");
        ctc.add("");
        ctc.add("0");
        ctc.add("1");
        ctc.add("");
        ctc.add("0");
        ctc.add("");
        ctc.add("");
        ctc.add("");

    }

    @Test
    public void getNextingcount() {

        ArrayList<String> nestingGet=new  QualityController().getNextingcount(new FileReader(this.filepath));

        for(int i=0;i<nestingGet.size();i++){
            assertEquals("not same cnc line "+i,nestingGet.get(i),nestig.get(i));
        }

    }

    @Test
    public void getAllcountSize() {
        ArrayList<Integer> sizecount=new  QualityController().getAllcountSize(new FileReader(this.filepath));

        for(int i=0;i<sizecount.size();i++){
            assertEquals("not same cs line "+i,sizecount.get(i),size.get(i));
        }
    }

    @Test
    public void getAllInheritanse() {
        ArrayList<String> infull=new  QualityController().getAllInheritanse(new FileReader(this.filepath));

        for(int i=0;i<infull.size();i++){
            assertEquals("not same ci line "+i,infull.get(i),inhe.get(i));
        }
    }

    @Test
    public void getAllCtc() {
        ArrayList<String> infull=new  QualityController().getAllCtc(new FileReader(this.filepath));

        for(int i=0;i<infull.size();i++){
            assertEquals("not same ctc line "+i,infull.get(i),ctc.get(i));
        }
    }
}