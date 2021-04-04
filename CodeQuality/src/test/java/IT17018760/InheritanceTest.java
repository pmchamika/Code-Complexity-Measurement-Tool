package IT17018760;

import IT16178700.Control.ControlType;
import org.junit.Before;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class InheritanceTest  {
    String line = "extends class FindTotal{";
    String line1 = "else if ((number == 0) && (number == 1) || (number2 == 1 | number3 == 0))";
    int lineSize = line.length();
    int lineSize1 = line1.length();
    int extendsCount = 0;
    int andMCount = 0;
    int systemCount = 0;
    int stringCount = 0;

    Pattern systemPattern = Pattern.compile(ControlType.systemV, Pattern.UNICODE_CASE); // matcher for system
    Matcher systemMatch = systemPattern.matcher(line);

    Pattern stringPattern = Pattern.compile(ControlType.stringV, Pattern.UNICODE_CASE); // matcher for string
    Matcher stringMatch = stringPattern.matcher(line);

    Pattern StringPattern = Pattern.compile(ControlType.StringV, Pattern.UNICODE_CASE); // matcher for String
    Matcher StringMatch = StringPattern.matcher(line);
    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void TestImplements() {

        if (systemMatch.find()) {
            systemCount = 1;
        }

        if (StringMatch.find() || stringMatch.find()) {
            stringCount = 1;
        }

        String[] Operator = new String[lineSize1];
        for (int i = 0; i < lineSize1; i++) {
            Operator[i] = String.valueOf(line1.charAt(i));
        }

        for (int k = 0; k < lineSize1; k++) {
            // checking if condition
            if (Operator[k].equals("i") && Operator[k + 1].equals("f")) {
                if (systemCount == 1 || (stringCount == 1 && k != 0)) {
                    andMCount = 0;
                } else if (Operator[k + 2].equalsIgnoreCase("\"")) {
                    // nothing to do
                } else if (Operator[k + 2].equals(" ") || Operator[k + 2].equals("(")) {
                    andMCount++;
                }
            }
        }
        assertEquals(1, andMCount);
    }

    @Test
    public void TestExtends() {

        // Checking extends
        if (systemMatch.find()) {
            systemCount = 1;
        }

        if (StringMatch.find() || stringMatch.find()) {
            stringCount = 1;
        }

        String[] Operator = new String[lineSize];
        for (int i = 0; i < lineSize; i++) {
            Operator[i] = String.valueOf(line.charAt(i));
        }

        for (int k = 0; k < lineSize; k++) {
            // checking if condition
            if (Operator[k].equals("e") && Operator[k + 1].equals("x") && Operator[k + 2].equals("t") && Operator[k + 3].equals("e") && Operator[k + 4].equals("n") && Operator[k + 5].equals("d") && Operator[k + 6].equals("s")){
                if (systemCount == 1 || (stringCount == 1 && k != 0)) {
                    extendsCount = 0;
                } else if (Operator[k + 7].equalsIgnoreCase("\"")) {
                    // nothing to do
                } else if (Operator[k + 7].equals(" ")) {
                    extendsCount++;
                }
            }
        }
        assertEquals(1, extendsCount);
    }
}