package IT17056212;

import IT17152938.Service.FileReader;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class SizeTest3 {
    private String filepath;
    private ArrayList<Integer> text=new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        filepath="01.txt";
        text.add(0);
        text.add(0);
        text.add(0);
        text.add(0);
        text.add(0);
        text.add(0);
        text.add(0);
        text.add(1);
        text.add(0);
        text.add(0);
        text.add(1);
        text.add(0);
        text.add(0);
    }

    @Test
    public void num() {
        ArrayList<Integer> textGet=new  Size(new FileReader(this.filepath)).getTextCount();

        for(int i=0;i<textGet.size();i++){
            assertEquals("not same line "+i,text.get(i),textGet.get(i));
        }
    }

}