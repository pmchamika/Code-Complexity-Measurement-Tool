package IT17152938.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class FileReader {

    private String filepath;
    private ArrayList<String> file = new ArrayList<String>();
    private ArrayList<String> fileNoSpase = new ArrayList<String>();
    private ArrayList<String> fileFormat = new ArrayList<String>();
    private ArrayList<String> fileFormat2 = new ArrayList<String>();


    public FileReader(String filepath) {
        this.filepath = filepath;
        this.readfile();
        this.formatfile();
        this.createprint();
    }

/*    public int getLinecount() {
        return linecount;
    }

    public void setLinecount(int linecount) {
        this.linecount = linecount;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }*/

//    public ArrayList<String> getFile() {
//        return file;
//    }

//    public void setFile(ArrayList<String> file) {
//        this.file = file;
//    }

//    public ArrayList<String> getFileNoSpase() {
//        return fileNoSpase;
//    }

//    public void setFileNoSpase(ArrayList<String> fileNoSpase) {
//        this.fileNoSpase = fileNoSpase;
//    }

    public ArrayList<String> getFileFormat() {
        return fileFormat;
    }

//    public void setFileFormat(ArrayList<String> fileFormat) {
//        this.fileFormat = fileFormat;
//    }

    public ArrayList<String> getFileFormat2() {
        return fileFormat2;
    }

//    public void setFileFormat2(ArrayList<String> fileFormat2) {
//        this.fileFormat2 = fileFormat2;
//    }


    public void createprint() {
        int tabcouint = 0;
        String tab = "";
        for (String line : this.fileFormat) {

            if ("{".equalsIgnoreCase(line.substring(0, 1))) {
                this.fileFormat2.add(tab + line);
                tabcouint++;
                tab = "";
                for (int i = 0; i < tabcouint; i++) {
                    tab += "&nbsp&nbsp&nbsp&nbsp&nbsp";
                }
            } else if ("}".equalsIgnoreCase(line.substring(0, 1))) {
                tabcouint--;
                tab = "";
                for (int i = 0; i < tabcouint; i++) {
                    tab += "&nbsp&nbsp&nbsp&nbsp&nbsp";
                }
                this.fileFormat2.add(tab + line);
            } else {
                this.fileFormat2.add(tab + line);
            }

        }
    }

    public void readfile() {
        String full = "";

        try {
            Scanner s = new Scanner(new File(this.filepath));
            boolean malti = false;
            while (s.hasNext()) {

                String readLine = s.nextLine();
                this.file.add(readLine);
                //format file
                readLine = readLine.replaceAll("\t", "");

//                readLine=readLine.replaceAll(" ","");
                int commentstart = readLine.indexOf('/');


                if (commentstart != -1) {
//                    System.out.println(readLine);
//                    System.out.println(commentstart);
                    if (readLine.length() > commentstart + 1) {
                        if ('/' == readLine.charAt(commentstart + 1)) {
                            readLine = readLine.substring(0, commentstart);
                        } else if ('*' == readLine.charAt(commentstart + 1)) {
                            readLine = "";
                            malti = true;
                        } else if (commentstart - 1 >= 0) {
                            if ('*' == readLine.charAt(commentstart - 1)) {
                                readLine = readLine = readLine.substring(commentstart + 1, readLine.length());
                                ;
                                malti = false;
                            }
                        }
                    } else if (commentstart - 1 >= 0) {
                        if ('*' == readLine.charAt(commentstart - 1)) {
                            readLine = readLine = readLine.substring(commentstart + 1, readLine.length());

                            malti = false;
                        }
                    }

                }
                if (malti) {
                    readLine = "";
                }

//                System.out.println(readLine);
                this.fileNoSpase.add(readLine);

                full += readLine;

            }

            this.fileNoSpase.removeAll(Arrays.asList("", null));

            s.close();


        } catch (Exception e) {

            e.printStackTrace();

        }


    }

    public ArrayList<String> format(ArrayList<String> list, char sep) {
        ArrayList<String> fileFormattemp = new ArrayList<String>();

        for (String line : list) {

            int brstrt = line.indexOf(sep);
//            System.out.println(brstrt);
//            int br=0;
            while (brstrt != -1) {
                fileFormattemp.add(line.substring(0, brstrt));
                fileFormattemp.add(String.valueOf(sep));
                if (brstrt != line.length()) {
                    line = line.substring(brstrt + 1, line.length());
                }
                brstrt = line.indexOf(sep);
//

            }
            fileFormattemp.add(line);


        }
        return fileFormattemp;
    }

    public void formatfile() {
        ArrayList<String> fileFormattemp = new ArrayList<String>();
        ArrayList<String> fileFormattemp2 = new ArrayList<String>();
        fileFormattemp = format(format(this.fileNoSpase, '{'), '}');

        for (String line : fileFormattemp) {

            int brstrt = line.indexOf(';');
//            System.out.println(brstrt);
//            int br=0;
            while (brstrt != -1) {
                if (line.length() > 2) {
                    String p=line.replaceAll(" ","");
                    if(p.length()>2){
                        if (p.substring(0, 3).equalsIgnoreCase("for")) {
//                        this.fileFormat.add(line);
                            break;
                        }
                    }
                    if (line.substring(0, 3).equalsIgnoreCase("for")) {
//                        this.fileFormat.add(line);
                        break;
                    }
                }
                fileFormattemp2.add(line.substring(0, brstrt + 1));

                if (brstrt != line.length()) {
                    line = line.substring(brstrt + 1, line.length());
                }
                brstrt = line.indexOf(';');
//

            }
            fileFormattemp2.add(line);


        }

        fileFormattemp2.removeAll(Arrays.asList("", null, " "));

        for (String chline : fileFormattemp2) {

            int i = 0;

            if(chline.length()>0){
//                System.out.println(chline);
                while (chline.charAt(i) == ' ') {
                    if(chline.length()==i+1){
                        break;
                    }
//                    System.out.println(chline);
//                    System.out.println(i);
                    i++;
                }
//                System.out.println("out");
            }




            this.fileFormat.add(chline.substring(i, chline.length()));

        }
        this.fileFormat.removeAll(Arrays.asList("", null, " "));

    }

    public void printfile() {
       /* for (String line:this.file) {

            System.out.println(line);

        }*/
        System.out.println("\n\n");
        for (String line : this.fileFormat) {

            System.out.println(line);

        }
    }

}
