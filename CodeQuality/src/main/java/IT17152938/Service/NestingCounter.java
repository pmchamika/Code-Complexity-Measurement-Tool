package IT17152938.Service;

import java.util.ArrayList;


public class NestingCounter {
    private ArrayList<String> lineNestingCount = new ArrayList<String>();
    private FileReader file;

    public NestingCounter(FileReader file) {
        this.file = file;
        countnesting();
    }

    public ArrayList<String> getLineNestingCount() {
        return lineNestingCount;
    }



    private void countnesting() {

        int neting = 0;
        ArrayList<Boolean> loop = new ArrayList<Boolean>();//for store  open bracket countable or not

        int k = 0;//for check execute line number
        for (String line : this.file.getFileFormat()) {//execute line by line
            k++;//incises execute line number
            int lineLen = line.length();//get char count in execute line
//            System.out.println("sachin");
            if (lineLen == 1) {


                if ("{".equalsIgnoreCase(line)) {//if this line is open bracket then check before line is countable or not

                    String line2 = this.file.getFileFormat().get(k - 2);//get before line
                    int lineLen2 = line2.length();//get before line length

                    //-----------------------------------------------------------------------------------------------------------
                    /*
                    before line is
                        *else if
                        * while
                        * catch
                        * else
                        * for
                        * do
                        * if
                    then mark that line as a countable line

                     */
                    if (lineLen2 >= 7) {
//
                        if (line2.substring(0, 7).equalsIgnoreCase("else if")) {

                            loop.add(true);
                            lineNestingCount.add("");
                            continue;
                        }

                    }

                    if (lineLen2 >= 5) {
//
                        if (line2.substring(0, 5).equalsIgnoreCase("while") || line2.substring(0, 5).equalsIgnoreCase("catch")) {
                            loop.add(true);
                            lineNestingCount.add("");
                            continue;
                        }

                    }

                    if (lineLen2 >= 4) {
//
                        if (line2.substring(0, 4).equalsIgnoreCase("else")) {

                            loop.add(true);
                            lineNestingCount.add("");
                            continue;
                        }


                    }


                    if (lineLen2 >= 3) {
//
                        if (line2.substring(0, 3).equalsIgnoreCase("for")) {

                            loop.add(true);
                            lineNestingCount.add("");
                            continue;
                        }

                    }

                    if (lineLen2 >= 2) {
//
                        if (line2.substring(0, 2).equalsIgnoreCase("if") || line2.substring(0, 2).equalsIgnoreCase("do")) {

                            loop.add(true);
                            lineNestingCount.add("");
                            continue;
                        }


                    }

                    //-------------------------------------------------------------------------------------------------------------

                    loop.add(false);//another lines mark as a non countable lines
                    lineNestingCount.add("");
                    continue;


                } else if ("}".equalsIgnoreCase(line)) {
//                    bropen--;
                    //System.out.println(k+"----------"+loop.get(loop.size()-1));
                    if (loop.get(loop.size() - 1)) {
                        if (k != this.file.getFileFormat().size()) {
                            String next = this.file.getFileFormat().get(k);
                            next = next.replaceAll(" ", "");
                            //System.out.println(next);
                            if (next.length() >= 4) {
                                if (!"else".equalsIgnoreCase(next.substring(0, 4))) {

                                    if (neting != 0) {
                                        neting--;
                                    }

                                }

                            } else {
                                if (neting != 0) {
                                    neting--;
                                }
                            }


                        } else {
                            neting--;
                        }
                    }

                    loop.remove(loop.size() - 1);


                    lineNestingCount.add("");
                    continue;

                }

            }
            if (lineLen >= 5) {
//                System.out.println(line.substring(0,7));
                if (line.substring(0, 5).equalsIgnoreCase("while") || line.substring(0, 5).equalsIgnoreCase("catch")) {
                    neting++;
                    lineNestingCount.add(String.valueOf(neting));
                    continue;
                }

            }

            if (lineLen >= 4) {
//                System.out.println(line.substring(0,7));
                if (line.substring(0, 4).equalsIgnoreCase("else")) {

                    lineNestingCount.add("");
                    continue;
                }


            }


            if (lineLen >= 3) {
//                System.out.println(line.substring(0,7));
                if (line.substring(0, 3).equalsIgnoreCase("for")) {
                    neting++;
                    lineNestingCount.add(String.valueOf(neting));
                    continue;
                }

            }

            if (lineLen >= 2) {
//                System.out.println(line.substring(0,7));
                if (line.substring(0, 2).equalsIgnoreCase("if") || line.substring(0, 2).equalsIgnoreCase("do")) {
                    neting++;
                    lineNestingCount.add(String.valueOf(neting));
                    continue;
                }


            }

            if (loop.isEmpty()) {
                lineNestingCount.add("");
            } else {
                lineNestingCount.add(String.valueOf(neting));
            }


//            System.out.println(line.length());

        }


    }

    public void printnesting() {
        int size = this.lineNestingCount.size();
        for (int i = 0; i < size; i++) {
            System.out.println(this.file.getFileFormat2().get(i) + "\t\t\t" + this.lineNestingCount.get(i));
        }
    }
}
