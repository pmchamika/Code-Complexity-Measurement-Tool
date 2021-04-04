package IT17056212;

import IT17152938.Service.FileReader;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class SizeTest {
    private String filepath;
    private ArrayList<Integer> assignmentOp=new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        filepath="01.txt";
        assignmentOp.add(0);
        assignmentOp.add(0);
        assignmentOp.add(0);
        assignmentOp.add(0);
        assignmentOp.add(0);
        assignmentOp.add(0);
        assignmentOp.add(1);
        assignmentOp.add(0);
        assignmentOp.add(1);
        assignmentOp.add(1);
        assignmentOp.add(0);
        assignmentOp.add(0);
        assignmentOp.add(0);
    }
    @Test
    public void assignmentOperators() {
        ArrayList<Integer> assiGet=new  Size(new  FileReader(this.filepath)).getLineAssignmentCount();

        for(int i=0;i<assiGet.size();i++){
            assertEquals("not same line "+i,assignmentOp.get(i),assiGet.get(i));
        }
    }
}