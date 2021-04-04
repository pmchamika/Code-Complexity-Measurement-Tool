package IT17018760;


import IT17152938.Service.FileReader;




import java.util.ArrayList;
import java.util.StringTokenizer;

public class Inheritance {

    private FileReader fr;
    String fileLocation;
    String words, words2;


    String[] extendsCheck = { "extends" };
    String[] implementsCheck = { "implements" };
    String[] commaCheck = { "," };

    int complexity = 2;
    int totalcomplexity = 0;
    int x = 0;

    ArrayList<String> inherit= new ArrayList<String>();

    // constructor


    public Inheritance(FileReader fr) {
        this.fr = fr;
        CheckInheritance();
    }

    public ArrayList getFullInheritance(){
        return inherit;
    }

    public void CheckInheritance() {







        // Read all lines and every read increase the line number by 1

        for (String line:fr.getFileFormat()) {





            StringTokenizer stringToken = new StringTokenizer(line);

            while (stringToken.hasMoreTokens()) {

                words = stringToken.nextToken();

                for (int i = 0; i < extendsCheck.length; i++) {
                    if (extendsCheck[i].equals("//")) {
                        break;
                    } else {
                        if (extendsCheck[i].equals(words)) {
                            complexity = complexity + 1;
                        } else if (implementsCheck[i].equals(words)) {

                            String line2 = line;
                            complexity = complexity + 1;
                            StringTokenizer stringToken2 = new StringTokenizer(line2);

                            while (stringToken2.hasMoreTokens()) {
                                words2 = stringToken2.nextToken();

                                for (int x = 0; x < commaCheck.length; x++) {
                                    if (commaCheck[x].equals("{")) {
                                        break;
                                    } else {
                                        if (commaCheck[x].equals(words2)) {
                                            complexity = complexity + 1;
                                            System.out.println(complexity);
                                        }
                                    }

                                }

                            }
                        }
                    }
                }

            }


            inherit.add(String.valueOf(complexity));



            x = x + complexity;

        }

        totalcomplexity = totalcomplexity + x;

    }

}