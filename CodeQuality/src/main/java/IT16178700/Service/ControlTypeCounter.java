package IT16178700.Service;

import IT16178700.Control.ControlType;
import IT17152938.Service.FileReader;

import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ControlTypeCounter {
    ArrayList<String> lineControlCount = new ArrayList<String>();
    FileReader file;
    private final static Logger LOGGER = Logger.getLogger(ControlTypeCounter.class.getName());

    public ControlTypeCounter(FileReader file) {

        this.file = file;
        printTotal();
    }

    public ArrayList<String> getLineControlCount() {
        return lineControlCount;
    }

    public void printTotal() {
        for (String line : this.file.getFileFormat()) {

            int forCount = 0;
            int whileCount = 0;
            int catchCount = 0;
            int andSCount = 0;
            int andMCount = 0;
            int orSCount = 0;
            int orMCount = 0;
            int systemCount = 0;
            int totalLineCount = 0;
            int stringCount = 0;
            int doCount = 0;
            int switchCount = 0;
            int caseCount = 0;
            int ifCount = 0;
            String data1 = line;

            if ("{".equalsIgnoreCase(line)) {
                lineControlCount.add("");
                continue;
            } else if ("}".equalsIgnoreCase(line)) {
                lineControlCount.add("");
                continue;
            }
            LOGGER.info("Logger Name: " + LOGGER.getName());


            Pattern systemPattern = Pattern.compile(ControlType.systemV, Pattern.UNICODE_CASE); // matcher for system
            Matcher systemMatch = systemPattern.matcher(data1);


            Pattern stringPattern = Pattern.compile(ControlType.stringV, Pattern.UNICODE_CASE); // matcher for string
            Matcher stringMatch = stringPattern.matcher(data1);

            Pattern StringPattern = Pattern.compile(ControlType.StringV, Pattern.UNICODE_CASE); // matcher for String
            Matcher StringMatch = StringPattern.matcher(data1);


            // Checking && and & operators
            if (systemMatch.find()) {
                systemCount = 1;
            }

            if (StringMatch.find() || stringMatch.find()) {
                stringCount = 1;
            }

            int lineSize = line.length();
            String[] Operator = new String[lineSize];
            for (int i = 0; i < lineSize; i++) {
                Operator[i] = String.valueOf(line.charAt(i));
            }
            for (int k = 0; k < lineSize; k++) {

                // checking or operator
                if ("|".equalsIgnoreCase(Operator[k]) && Operator[k + 1].equalsIgnoreCase("|")) {
                    if ((systemCount == 1 && k == 0) || (stringCount == 1 && k == 0)) {
                        orMCount = 0;
                    } else if (Operator[k - 1].equalsIgnoreCase("\"")) {
                        // nothing to do
                    } else {
                        orMCount++;
                    }
                } else if ("|".equalsIgnoreCase(Operator[k])) {
                    if ("|".equalsIgnoreCase(Operator[k - 1])) {
                        // nothing to do
                    } else {
                        if ((systemCount == 1 && k == 0) || (stringCount == 1 && k == 0)) {
                            orSCount = 0;
                        } else if (Operator[k + 1].equalsIgnoreCase("\"")) {
                            orSCount = 0;
                        } else {
                            orSCount++;
                        }
                    }
                }

                //checking and operator
                if ("&".equalsIgnoreCase(Operator[k]) && Operator[k + 1].equalsIgnoreCase("&")) {
                    if ((systemCount == 1 && k == 0) || (stringCount == 1 && k == 0)) {
                        andMCount = 0;
                    } else if (Operator[k - 1].equalsIgnoreCase("\"")) {
                        // nothing to do
                    } else {
                        andMCount++;
                    }
                } else if ("&".equalsIgnoreCase(Operator[k])) {
                    if ("&".equalsIgnoreCase(Operator[k - 1])) {
                        // nothing to do
                    } else {
                        if ((systemCount == 1 && k == 0) || (stringCount == 1 && k == 0)) {
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

                // checking if condition
                if ("i".equals(Operator[k]) && Operator[k + 1].equals("f")) {
                    if ((systemCount == 1 && k != 0) || (stringCount == 1 && k != 0)) {
                        ifCount = 0;
                    } else if (Operator[k + 2].equalsIgnoreCase("\"")) {
                        // nothing to do
                    } else if (Operator[k + 2].equals(" ") || Operator[k + 2].equals("(")) {
                        ifCount++;
                    }
                }

                // checking catch keyword
                if (Operator[k].equals("c") && Operator[k + 1].equals("a") && Operator[k + 2].equals("t") && Operator[k + 3].equals("c") && Operator[k + 4].equals("h")) {
                    if ((systemCount == 1 && k != 0) || (stringCount == 1 && k != 0)) {
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

                // checking while keyword
                if (Operator[k].equals("w") && Operator[k + 1].equals("h") && Operator[k + 2].equals("i") && Operator[k + 3].equals("l") && Operator[k + 4].equals("e")) {
                    if ((systemCount == 1 && k != 0) || (stringCount == 1 && k != 0)) {
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

                // checking for keyword
                if (Operator[k].equals("f") && Operator[k + 1].equals("o") && Operator[k + 2].equals("r")) {
                    if ((systemCount == 1 && k != 0) || (stringCount == 1 && k != 0)) {
                        forCount = 0;
                    } else if (Operator[k + 3].equalsIgnoreCase("\"")) {
                        // nothing to do
                    } else if (Operator[k + 3].equalsIgnoreCase(" ") || Operator[k + 3].equalsIgnoreCase("(")) {
                        forCount += 2;
                    }
                }

                // checking do keyword
                if (Operator[k].equals("d") && Operator[k + 1].equals("o")) {
                    if (line.length() > 2) {
                        if ((systemCount == 1 && k != 0) || (stringCount == 1 && k != 0)) {
                            doCount = 0;
                        } else if (Operator[k + 2].equalsIgnoreCase("\"")) {
                            // nothing to do
                        } else if (Operator[k + 2].equalsIgnoreCase(" ") || Operator[k + 2].equalsIgnoreCase("{")) {
                            doCount += 2;
                        }
                        LOGGER.warning("Can cause ArrayIndexOutOfBoundsException");
                    } else {
                        doCount += 2;
                    }

                }

                // check switch nesting type
                if (Operator[k].equals("s") && Operator[k + 1].equals("w") && Operator[k + 2].equals("i") && Operator[k + 3].equals("t") && Operator[k + 4].equals("c") && Operator[k + 5].equals("h")) {
                    if (line.length() > 6) {
                        if ((systemCount == 1 && k != 0) || (stringCount == 1 && k != 0)) {
                            switchCount = 0;
                        } else if (Operator[k + 6].equalsIgnoreCase("\"")) {
                            // nothing to do
                        } else if (Operator[k + 6].equalsIgnoreCase(" ") || Operator[k + 6].equalsIgnoreCase("(")) {
                            switchCount = 1;
                        }
                        LOGGER.warning("Can cause ArrayIndexOutOfBoundsException");
                    } else {
                        switchCount = 1;
                    }

                }

                // check case nesting type
                if (Operator[k].equals("c") && Operator[k + 1].equals("a") && Operator[k + 2].equals("s") && Operator[k + 3].equals("e")) {
                    if (line.length() > 4 && switchCount == 1) {
                        if ((systemCount == 1 && k != 0) || (stringCount == 1 && k != 0)) {
                            caseCount = 0;
                        } else if (Operator[k + 4].equalsIgnoreCase("\"")) {
                            // nothing to do
                        } else if (Operator[k + 4].equalsIgnoreCase(" ") || Operator[lineSize].equalsIgnoreCase(":")) {
                            caseCount += 2;
                        }
                        LOGGER.warning("Can cause ArrayIndexOutOfBoundsException");
                    } else {
                        caseCount += 2;
                    }

                }
            }

            totalLineCount = andMCount + andSCount + orSCount + orMCount + catchCount + forCount + whileCount + doCount + switchCount + caseCount + ifCount;

            // calculate total of type of control size

            lineControlCount.add(String.valueOf(totalLineCount));


        }


    }

}
