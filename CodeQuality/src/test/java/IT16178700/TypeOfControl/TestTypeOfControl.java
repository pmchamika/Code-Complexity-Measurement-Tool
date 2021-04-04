package IT16178700.TypeOfControl;

import IT16178700.Control.ControlType;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;

public class TestTypeOfControl {
    String line = "else if ((number == 0) && (number == 1) || (number2 == 1 | number3 == 0))";
    String line2 = "catch(FileNotFoundException e) {";
    String line3 = "while (brstrt != -1) {";
    int systemCount = 0;
    int lineSize = line.length();
    int lineSize2 = line2.length();
    int lineSize3 = line3.length();
    int stringCount = 0;
    int andMCount = 0;
    int andSCount = 0;
    int orSCount = 0;
    int orMCount = 0;
    int totalCount = 0;
    int catchCount = 0;
    int whileCount = 0;

    Pattern systemPattern = Pattern.compile(ControlType.systemV, Pattern.UNICODE_CASE); // matcher for system
    Matcher systemMatch = systemPattern.matcher(line);

    Pattern stringPattern = Pattern.compile(ControlType.stringV, Pattern.UNICODE_CASE); // matcher for string
    Matcher stringMatch = stringPattern.matcher(line);

    Pattern StringPattern = Pattern.compile(ControlType.StringV, Pattern.UNICODE_CASE); // matcher for String
    Matcher StringMatch = StringPattern.matcher(line);

    @Test
    public void TestIfCondition() {

        // Checking && and & operators
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
    public void TestAndOperator() {

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
            //checking and operator
            if (Operator[k].equalsIgnoreCase("&") && Operator[k + 1].equalsIgnoreCase("&")) {
                if (systemCount == 1 || stringCount == 1) {
                    andMCount = 0;
                } else if (Operator[k - 1].equalsIgnoreCase("\"")) {
                    // nothing to do
                } else {
                    andMCount++;
                }
            } else if (Operator[k].equalsIgnoreCase("&")) {
                if (Operator[k - 1].equalsIgnoreCase("&")) {
                    // nothing to do
                } else {
                    if (systemCount == 1 || stringCount == 1) {
                        andSCount = 0;
                    } else if (Operator[k + 1].equalsIgnoreCase("\"")) {
                        andSCount = 0;
                    } else if (Operator[k + 1].equalsIgnoreCase("n") && Operator[k + 2].equalsIgnoreCase("b")
                            && Operator[k + 3].equalsIgnoreCase("s") && Operator[k + 4].equalsIgnoreCase("p")) {
                        // nothing to do
                    } else {
                        andSCount++;
                    }
                }
            }
        }
        totalCount = andSCount + andMCount;
        assertEquals(1, totalCount);
    }

    @Test
    public void TestOrOperator() {

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


            // checking or operator
            if (Operator[k].equalsIgnoreCase("|") && Operator[k + 1].equalsIgnoreCase("|")) {
                if (systemCount == 1 || stringCount == 1) {
                    orMCount = 0;
                } else if (Operator[k - 1].equalsIgnoreCase("\"")) {
                    // nothing to do
                } else {
                    orMCount++;
                }
            } else if (Operator[k].equalsIgnoreCase("|")) {
                if (Operator[k - 1].equalsIgnoreCase("|")) {
                    // nothing to do
                } else {
                    if (systemCount == 1 || stringCount == 1) {
                        orSCount = 0;
                    } else if (Operator[k + 1].equalsIgnoreCase("\"")) {
                        orSCount = 0;
                    } else {
                        orSCount++;
                    }
                }
            }
        }
        totalCount = orMCount + orSCount;
        assertEquals(2, totalCount);
    }

    @Test
    public void TestSwitchCase() {
        if (systemMatch.find()) {
            systemCount = 1;
        }

        if (StringMatch.find() || stringMatch.find()) {
            stringCount = 1;
        }
        String[] Operator = new String[lineSize2];
        for (int i = 0; i < lineSize2; i++) {
            Operator[i] = String.valueOf(line2.charAt(i));
        }

        for (int k = 0; k < lineSize2; k++) {

            // checking catch keyword
            if (Operator[k].equals("c") && Operator[k + 1].equals("a") && Operator[k + 2].equals("t") && Operator[k + 3].equals("c") && Operator[k + 4].equals("h")) {
                if (systemCount == 1 || (stringCount == 1 && k != 0)) {
                    catchCount = 0;
                } else if (Operator[k + 5].equalsIgnoreCase("\"")) {
                    // nothing to do
                } else {
                    boolean b = Operator[k + 5].equals(" ") || Operator[k + 5].equals("(");
                    if (b) {
                        catchCount++;
                    }
                }
            }
            assertEquals(1, catchCount);
        }
    }

    @Test
    public void TestWhile(){
        if (systemMatch.find()) {
            systemCount = 1;
        }

        if (StringMatch.find() || stringMatch.find()) {
            stringCount = 1;
        }
        String[] Operator = new String[lineSize3];
        for (int i = 0; i < lineSize3; i++) {
            Operator[i] = String.valueOf(line2.charAt(i));
        }

        for (int k = 0; k < lineSize3; k++) {

            // checking while keyword
            if (Operator[k].equals("w") && Operator[k + 1].equals("h") && Operator[k + 2].equals("i") && Operator[k + 3].equals("l") && Operator[k + 4].equals("e")) {
                if (systemCount == 1 || (stringCount == 1 && k != 0)) {
                    whileCount = 0;
                } else if (Operator[k + 5].equalsIgnoreCase("\"")) {
                    // nothing to do
                } else {
                    boolean b = Operator[k + 5].equals(" ") || Operator[k + 5].equals("(");
                    if (b) {
                        whileCount += 2;
                    }
                }
            }
        }
    }
}
