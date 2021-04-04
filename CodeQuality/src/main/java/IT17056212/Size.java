package IT17056212;

import com.sun.deploy.util.StringUtils;

import java.io.*;
import java.sql.SQLOutput;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import IT17152938.Service.FileReader;

public class Size {



    private FileReader fr;
    ArrayList<String> aopratars = new ArrayList<String>();
    ArrayList<String> lopratars = new ArrayList<String>();
    ArrayList<String> ropratars = new ArrayList<String>();
    ArrayList<String> bwopratars = new ArrayList<String>();
    ArrayList<String> mopratars = new ArrayList<String>();
    ArrayList<String> manip = new ArrayList<String>();
    ArrayList<String> assignopratars = new ArrayList<String>();
    ArrayList<String> keyward = new ArrayList<String>();
    ArrayList<String> refDefkeyward = new ArrayList<String>();
    ArrayList<String> numeric = new ArrayList<String>();
    ArrayList<String> text = new ArrayList<String>();
    ArrayList<Integer> countNumeric = new ArrayList<Integer>();
    ArrayList<Integer> countkeyward = new ArrayList<Integer>();
    ArrayList<Integer> countrefDefkeyward = new ArrayList<Integer>();
    ArrayList<Integer> countManip = new ArrayList<Integer>();
    ArrayList<Integer> countaopratars = new ArrayList<Integer>();
    ArrayList<Integer> countlopratars = new ArrayList<Integer>();
    ArrayList<Integer> countRopratars = new ArrayList<Integer>();
    ArrayList<Integer> countbwopratars = new ArrayList<Integer>();
    ArrayList<Integer> countMopratars = new ArrayList<Integer>();
    ArrayList<Integer> countText = new ArrayList<Integer>();
    ArrayList<Integer> countAssignopratars = new ArrayList<Integer>();
    ArrayList<String> classes = new ArrayList<String>();
    ArrayList<Integer> classesCount = new ArrayList<Integer>();

    ArrayList<String> meth = new ArrayList<String>();
    ArrayList<Integer> methcount = new ArrayList<Integer>();
    ArrayList<String> va = new ArrayList<String>();
    ArrayList<String> va2 = new ArrayList<String>();
    ArrayList<String> vari = new ArrayList<String>();
    ArrayList<Integer> varicount = new ArrayList<Integer>();

    ArrayList<String> all = new ArrayList<String>();
    ArrayList<Integer> allcount = new ArrayList<Integer>();


    public Size(FileReader f) {

        setFr(f);
        method();
        variabal();
        createvar2();
        countvari();
        for (String ln : f.getFileFormat()) {
            int i = line(ln);
            //System.out.println(i);
        }
        getfullList();
    }

    public ArrayList<Integer> getLineAssignmentCount() {
        return countAssignopratars;
    }
    public ArrayList<Integer> getLineNoCount() {
        return countNumeric;
    }
    public ArrayList<Integer> getTextCount() {
        return countText;
    }
    public void setFr(FileReader fr) {
        this.fr = fr;
    }

    public static int count(String string, String substr) {
        int i;
        int last = 0;
        int count = 0;

        do {
            i = string.indexOf(substr, last);
            if (i != -1) count++;
            last = i + substr.length();
        } while (i != -1);
        return count;
    }

    public int refDefAndKeywards(String line) {

        int i = 0;
        int j = i;
        String s = " ";
        String[] ops = {"* ", " &", " new", " delete", " throw ", " throws "};
        for (String op : ops) {

            i = i + count(line, op);
            if (j != i) {
                s = s.concat(",").concat(op);
            }
            j = i;

        }
        s = s.substring(1);
        if (s.length() != 0) {
            s = s.substring(1);
        }
        i = i * 2;
        /*if(i!=0){
            i=i+2;//adding constant +2
        }*/
        //System.out.println(s);
        this.refDefkeyward.add(s);
        this.countrefDefkeyward.add(i);
        return i;

    }

    public int arithmaticOperators(String line) {

        int i = 0;
        int j = i;
        String s = " ";
        String[] ops = {" + ", " - ", " * ", " / ", " % ", "++", "--"};
        for (String op : ops) {

            i = i + count(line, op);
            if (j != i) {
                s = s.concat(",").concat(op);
            }
            j = i;

        }
        s = s.substring(1);
        if (s.length() != 0) {
            s = s.substring(1);
        }
//        System.out.println(line+"------------------------>"+s);
        this.aopratars.add(s);
        this.countaopratars.add(i);
        return i;

    }

    public int relationOperators(String line) {
        int i = 0;
        int j = i;
        String s = " ";
        String[] ops = {" == ", " != ", " > ", " < ", " >= ", " <= "};
        for (String op : ops) {

            i = i + count(line, op);
            if (j != i) {
                s = s.concat(",").concat(op);
            }
            j = i;

        }

        s = s.substring(1);
        if (s.length() != 0) {
            s = s.substring(1);
        }
        // System.out.println(s);
        this.ropratars.add(s);
        this.countRopratars.add(i);
        return i;
    }

    public int logicalOperators(String line) {
        int i = 0;
        int j = i;
        String s = " ";
        String[] ops = {" && ", " || ", " ! "};
        for (String op : ops) {

            i = i + count(line, op);
            if (j != i) {
                s = s.concat(",").concat(op);
            }
            j = i;

        }
        s = s.substring(1);
        if (s.length() != 0) {
            s = s.substring(1);
        }
//        System.out.println(s);
//        System.out.println(line+"------------------------>"+s);
        this.lopratars.add(s);
        this.countlopratars.add(i);
        return i;
    }

    public int bitwiseOperators(String line) {
        int i = 0;
        int j = i;
        String s = " ";
        String[] ops = {" | ", " ^ ", " ~ ", " << ", " >> ", " >>> ", " <<< "};
        for (String op : ops) {

            i = i + count(line, op);
            if (j != i) {
                s = s.concat(",").concat(op);
            }
            j = i;


        }

        s = s.substring(1);
        if (s.length() != 0) {
            s = s.substring(1);
        }
        //System.out.println(s);
        this.bwopratars.add(s);
        this.countbwopratars.add(i);
        return i;
    }

    public int miscellaneousOperators(String line) {
        int i = 0;
        int j = i;
        String s = " ";
        String[] ops = {", ", " _> ", ".", " :: "};
        for (String op : ops) {

            i = i + count(line, op);
            if (j != i) {
                s = s.concat(",").concat(op);
            }
            j = i;

        }

        s = s.substring(1);
        if (s.length() != 0) {
            s = s.substring(1);
        }
        //System.out.println(s);
        this.mopratars.add(s);
        this.countMopratars.add(i);
        return i;
    }

    public int assignmentOperators(String line) {
        int i = 0;
        int j = i;
        String s = " ";
        String[] ops = {" += ", " _= ", " *=", " /= ", " = ", " >>>= ", " |= ", " &= ", " %= ", " <<= ", " >>= ", " ^= "};
        for (String op : ops) {

            i = i + count(line, op);
            if (j != i) {
                s = s.concat(",").concat(op);
            }
            j = i;

        }

        s = s.substring(1);
        if (s.length() != 0) {
            s = s.substring(1);
        }
        //System.out.println(s);
        this.assignopratars.add(s);
        this.countAssignopratars.add(i);
        return i;
    }

  /*  public String findDuplicates(String s){

        String t="";

        if(s.length()!=0) {
            s = s.replaceAll("\\s", "");
            String[] arrayS = s.split(",");
            arrayS = new HashSet<String>(Arrays.asList(arrayS)).toArray(new String[0]);
            System.out.println(s);
            t = Arrays.toString(arrayS);
            t = t.replaceAll("\\[|\\]", "");
            t= t.replaceAll("\\s", "");
            System.out.println(t);

        }

       return t;
    }*/

    public int keyWards(String line) {
        int i = 0;
        int j = i;
        String t = "";
        String s = " ";
        String[] ops = {" void ", "double", "int", "float", "String", "long", "printf ", "cout ", "cin ", "if ", "for ", "while ", "switch ", "case ", "private ", "protected ", " interface ", " implements ", "synchronized ", " final ", "package ", "continue ", "default ", " extends ","string"};
        for (String op : ops) {

            i = i + count(line, op);
            if (j != i) {
                s = s.concat(",").concat(op);
            }
/*
            t=findDuplicates(s);
            if (t != s) {
                i = i - 1;
            }*/
            j = i;

        }

        s = s.substring(1);
        if (s.length() != 0) {
            s = s.substring(1);
        }

        /*s = s.replaceAll("\\s","");
        String[] arrayS = s.split(",");
        arrayS = new HashSet<String>(Arrays.asList(arrayS)).toArray(new String[0]);

        s=Arrays.toString(arrayS);
        s = s.replaceAll("\\[|\\]", "");*/
        //System.out.println(s);
        this.keyward.add(s);
        this.countkeyward.add(i);
        return i;
    }

    public int manipulators(String line) {
        int i = 0;
        int j = i;
        String s = " ";
        String[] ops = {" endl ", " setw ", " setprecision ", " setf "};
        for (String op : ops) {

            i = i + count(line, op);
            if (j != i) {
                s = s.concat(",").concat(op);
            }
            j = i;

        }

        s = s.substring(1);
        if (s.length() != 0) {
            s = s.substring(1);
        }
        //System.out.println(s);
        this.manip.add(s);
        this.countManip.add(i);
        return i;
    }

    public int textInsideDQ(String line) {
        int i = 0;
        String q = "\"";
        String text = null;
        String space = " ";
        String s = " ";
        Pattern p = Pattern.compile("\"([^\"]*)\"");
        Matcher m = p.matcher(line);
        while (m.find()) {
            //System.out.println(m.group(1));
            text = q + m.group(1) + q + space;
            s = s.concat(",").concat(text);
            i++;
        }

        s = s.substring(1);
        if (s.length() != 0) {
            s = s.substring(1);
        }
        //System.out.println(s);
        this.text.add(s);
        this.countText.add(i);
        return i;
    }

    public int searchClasses(String line){
        int i=0;


        String s = " ";
        String fullText=line;
        String [] strWords = fullText.split("\\s");
        for(String strWord:strWords){
            if(strWord.contains("Exception")){
                String [] word = strWord.split("\\(");
                s = s.concat(",").concat(word[1]);
                i++;

            }
        }
        s = s.substring(1);
        if (s.length() != 0) {
            s = s.substring(1);
        }
        //System.out.println(s);
        this.classes.add(s);
        this.classesCount.add(i);

        return i;


    }

    public int num(String line) {
        int i = 0;

        String s = " ";
        ArrayList<String> numsmatch = new ArrayList<>();

        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(line);
        while (m.find()) {


            if (!numsmatch.contains(m.group())) {
                numsmatch.add(m.group());
                s = s.concat(",").concat(m.group()) + " ";
            }

            i++;
        }

        s = s.substring(1);
        if (s.length() != 0) {
            s = s.substring(1);
        }
        //System.out.println(s);
        this.numeric.add(s);
        this.countNumeric.add(i);

        return i;
    }

    public int methodVariables(String line) {


        String[] s = line.split(" ");

        String vm, vm1, vm2;
        String full = "";
        int count = 0;
        String[] ops = {"int", "double", "long", "String", "void"};

        for (int i = 0; i < s.length; i++) {

            for (String var : ops) {

                if (s[i].equals(var)) {
                    // index = i;

                    vm = s[i + 1];
                    if (vm.indexOf("(") != -1) {
                        String[] s1 = vm.split("\\(");

                        vm1 = s1[0];
                        if (vm1.indexOf("::") != -1) {//c++ function
                            String[] s2 = vm1.split("::");
                            vm1 = s2[1];

                        }

                        vm2 = s[i + 2];
                        if ((vm2.indexOf(";") != -1) || (vm2.indexOf(",") != -1)) {

                            vm2 = vm2.replace(";", " ");
                            vm2 = vm2.replace(",", "");
                            full = full.concat(vm2).concat(" ");
                            count = count + 1;
                        }


                        full = full.concat(vm1).concat(" ");


                        full = full.replace(",", " ");

                        count = count + 1;


                    } else {
                        if ((vm.indexOf(",") != -1) && (vm.indexOf(";") != -1)) {
                            String[] novar = vm.split(",");
                            int noOfVaribles = novar.length;
                            vm = vm.replace(";", "");
                            full = full.concat(vm);
                            count = noOfVaribles;
                        } else {
                            vm = vm.replace(";", "");
                            full = full.concat(vm);
                            count = count + 1;
                        }

                    }


                    break;
                }
            }
        }


        full = full.replace(")", "");
        full = full.replace(" ", ",");
        if (full != "") {
            char lastcharacter = full.charAt(full.length() - 1);
            if (lastcharacter == ',') {

                full = full.substring(0, full.length() - 1);
            }
        }
        System.out.println(full);
        System.out.println(count);


        return count;
    }


    public int method(String line) {

        int li = line.indexOf('(');
        if (li != -1) {
            String[] r = line.split("\\(");

            for (int w = 0; w < r.length; w++) {
                String[] p = r[w].split(" ");
//                int j;
//                for(int u=p.length-1;u>=0;u--){
//                    if()
//                }

                if (Character.isLetter(p[p.length - 1].charAt(p[p.length - 1].length() - 1))) {
                    r[w] = p[p.length - 1];
                } else {
                    r[w] = " ";
                }

            }

            for (String b : r) {
                if (!(" ".equalsIgnoreCase(b))) {
                    System.out.print(b + ",");
                }

            }
            System.out.println();
        }
//        String meth1;
//        String methodName;
//        int i=0;
//        if (line.indexOf("(") != -1) {
//            String[] meth=line.split("\\(");
//            meth1= meth[0];
//            String[] meth2=meth1.split(" ");
//            methodName=meth2[meth2.length-1];
//            methodName=methodName.replaceAll(" ","");
//            if(!("for".equalsIgnoreCase(methodName))&& !("if".equalsIgnoreCase(methodName))&& !("while".equalsIgnoreCase(methodName))&& !("switch".equalsIgnoreCase(methodName))) {
//                System.out.println(methodName);
//                i++;
//            }
//
//        }
//
        return 0;
    }

    public int line(String line) {

        int i = 0;
        String l;
        l = line.replaceAll("\".*?\"", "");
        i = i + this.refDefAndKeywards(l);//if these exists add 2 to Cs

        i = i + this.arithmaticOperators(l);//if below these exists add 1 to Cs
        i = i + this.relationOperators(l);
        i = i + this.logicalOperators(l);
        i = i + this.bitwiseOperators(l);
        i = i + this.miscellaneousOperators(l);
        i = i + this.assignmentOperators(l);
        i = i + this.keyWards(l);
        i = i + this.num(l);
        i = i + this.manipulators(l);
        i = i + this.searchClasses(l);
        i = i + this.textInsideDQ(line);
        // i=i+methodVariables(line);
        // i=i+method(line);
        return i;

    }

    public void getfullList() {

        ArrayList<String> temp = new ArrayList<>();


        for (int i = 0; i < this.lopratars.size(); i++) {
            String a = "";

            if (!this.aopratars.get(i).equalsIgnoreCase("")) {
                a += "," + this.aopratars.get(i) + " ";
            }

            if (!this.lopratars.get(i).equalsIgnoreCase("")) {
                a += "," + this.lopratars.get(i) + " ";
            }
            if (!this.keyward.get(i).equalsIgnoreCase("")) {
                a += "," + this.keyward.get(i) + " ";
            }
            if (!this.ropratars.get(i).equalsIgnoreCase("")) {
                a += "," + this.ropratars.get(i) + " ";
            }
            if (!this.refDefkeyward.get(i).equalsIgnoreCase("")) {
                a += "," + this.refDefkeyward.get(i) + " ";
            }
            if (!this.bwopratars.get(i).equalsIgnoreCase("")) {
                a += "," + this.bwopratars.get(i) + " ";
            }
            if (!this.mopratars.get(i).equalsIgnoreCase("")) {
                a += "," + this.mopratars.get(i) + " ";
            }
            if (!this.assignopratars.get(i).equalsIgnoreCase("")) {
                a += "," + this.assignopratars.get(i) + " ";
            }
            if (!this.manip.get(i).equalsIgnoreCase("")) {
                a += "," + this.manip.get(i) + " ";
            }
            if (!this.numeric.get(i).equalsIgnoreCase("")) {
                a += "," + this.numeric.get(i) + " ";
            }
            if (!this.text.get(i).equalsIgnoreCase("")) {
                a += "," + this.text.get(i) + " ";
            }

            if (!this.vari.get(i).equalsIgnoreCase("")) {
                a += "," + this.vari.get(i) + " ";
            }

            if (!this.meth.get(i).equalsIgnoreCase("")) {
                a += "," + this.meth.get(i) + " ";
            }
            if (!this.classes.get(i).equalsIgnoreCase("")) {
                a += "," + this.classes.get(i) + " ";
            }


            int b = this.countaopratars.get(i) + this.countlopratars.get(i) + this.countkeyward.get(i) + this.countRopratars.get(i) + this.countrefDefkeyward.get(i) + this.countbwopratars.get(i) + this.countMopratars.get(i) + this.countAssignopratars.get(i) + this.countManip.get(i) + this.countNumeric.get(i) + this.countText.get(i) + this.methcount.get(i) + this.varicount.get(i)+ this.classesCount.get(i);

            int c = this.countaopratars.get(i) + this.countlopratars.get(i) + this.countkeyward.get(i) + this.countRopratars.get(i) + this.countbwopratars.get(i) + this.countMopratars.get(i) + this.countAssignopratars.get(i) + this.countManip.get(i) + this.countNumeric.get(i) + this.countText.get(i);

           /* if(c!=0){
               b=b+1;//adding constant +1
           }*/

            if (a.length() != 0) {
                a = a.substring(1);
            }
            this.allcount.add(b);
            temp.add(a);


        }


        this.all = temp;

    }

    public ArrayList<String> getAll() {
        return all;
    }

    public void setAll(ArrayList<String> all) {
        this.all = all;
    }

    public ArrayList<Integer> getAllcount() {
        return allcount;
    }

    public void setAllcount(ArrayList<Integer> allcount) {
        this.allcount = allcount;
    }

    public void createvar2() {
        for (String s : va) {
            va2.add(" " + s + ";");
            va2.add(" " + s + " ");
            va2.add(" " + s + ",");
            va2.add(" " + s + ".");
            va2.add(" " + s + ")");


            va2.add("(" + s + ";");
            va2.add("(" + s + " ");
            va2.add("(" + s + ",");
            va2.add("(" + s + ".");

            va2.add("(" + s + ")");

            va2.add(s + "++;");
            va2.add(s + "--;");
            va2.add("++" + s + ";");
            va2.add("--" + s + ";");

        }
    }

    public void countvari() {
        for (String line : fr.getFileFormat()) {
            String var1 = "";
            int varcount = 0;
            for (String a : va2) {
                int intIndex = line.indexOf(a);
                if (intIndex != -1) {
                    int intIndex2 = line.indexOf("++");
                    int intIndex3 = line.indexOf("--");
                    if (intIndex2 != -1) {
                        if (a.charAt(a.length() - 2) == '+') {
                            var1 += a.substring(0, a.length() - 3) + ",";

                        } else {
                            var1 += a.substring(2, a.length() - 1) + ",";
                        }
                    } else if (intIndex3 != -1) {
                        if (a.charAt(a.length() - 2) == '-') {
                            var1 += a.substring(0, a.length() - 3) + ",";

                        } else {
                            var1 += a.substring(2, a.length() - 1) + ",";
                        }
                    } else {
//                    String y=line.substring(intIndex,)
                        var1 += a.substring(1, a.length() - 1) + ",";
                    }
                    varcount++;
                }
            }

            varicount.add(varcount);

            if (var1.length() > 0) {
                vari.add(var1.substring(0, var1.length() - 1));
            } else {
                vari.add(var1);
            }
        }
    }


    public void variabal() {

        for (String line : fr.getFileFormat()) {
            String variabal = "";
            int variabalcount = 0;
            boolean ch = false;
//            if (line.length() > 5) {
//                if (line.substring(0, 5).equalsIgnoreCase("catch")) {
//                    ch = true;
//
//                    String[] a = line.split(" ");
//                    if (a[a.length - 1].equalsIgnoreCase(")")) {
//                        boolean ch2 = false;
//                        String v = a[a.length - 2];
//                        for (String po : va) {
//                            if (po.equalsIgnoreCase(v)) {
//                                ch2 = true;
//                                break;
//                            }
//                        }
//                        if (!ch2) {
//                            va.add(v);
//                        }
//
//                    } else {
//                        boolean ch2 = false;
//                        String v = a[a.length - 1].replaceAll("\\)", "");
//                        for (String po : va) {
//                            if (po.equalsIgnoreCase(v)) {
//                                ch2 = true;
//                                break;
//                            }
//                        }
//                        if (!ch2) {
//                            va.add(v);
//                        }
//
//                    }
//                }
//            }

            if (!ch) {

                int p = line.indexOf(" = ");

                if (p != -1) {
//                    line
//                    System.out.println(line);
                    String[] a = line.split(" = ");
                    for (int i = 0; i < a.length - 1; i++) {
                        String vv = a[i].replaceAll("\\(", "");
                        String v;
                        String b[] = vv.split(" ");
                        if (b.length > 0) {
                            v = b[b.length - 1];

                        } else {
                            v = b[0];
                        }

                        boolean ch2 = false;

                        for (String po : va) {
                            if (po.equalsIgnoreCase(v)) {
                                ch2 = true;
                                break;
                            }
                        }
                        if (!ch2) {
                            Boolean checkInt=checkWhetherNo(v);
                            if(checkInt==false) {
                                this.va.add(v);
                            }
                        }

                    }
                    int i;

//                    System.out.println(p);

                }
            }


        }


    }

    public Boolean checkWhetherNo(String no){

        try
        {
            Integer.parseInt(no);
            return true;
        } catch (NumberFormatException ex)
        {
            return false;
        }
    }

    public void addtova(ArrayList<String> arr) {
        for (String line : arr) {
            if (line.length() >= 3) {
                if (line.substring(0, 3).equalsIgnoreCase("for")) {
                    break;
                }
            }
            int k = line.indexOf(")");
            if (k != -1) {
                line = line.substring(0, k);
                line = line.replaceAll(",", "");
                String ar[] = line.split(" ");
                ArrayList<String> rr = new ArrayList<>();
                boolean ch = false;
                for (String g : ar) {
                    rr.add(g);
                }
                rr.removeAll(Arrays.asList("", null, " "));
                for (String g : rr) {
                    g = g.replaceAll(" ", "");
                    if (!Character.isLetterOrDigit(g.charAt(g.length() - 1))) {
                        ch = true;
                        break;
                    }

                }
                if (!ch) {
                    String h[] = line.split(" ");
                    if (h.length % 2 == 0) {
                        for (int o = 1; o < h.length; o = o + 2) {
                            boolean ch2 = false;

                            for (String po : va) {
                                if (po.equalsIgnoreCase(h[o])) {
                                    ch2 = true;
                                    break;
                                }
                            }
                            if (!ch2) {
                                Boolean checkInt=checkWhetherNo(h[o]);
                                if(checkInt==false) {
                                    va.add(h[o]);
                                }
                            }
                        }
                    }
                }

            }

        }

    }

    public void method() {


        for (String line : fr.getFileFormat()) {

            int a = line.indexOf('(');

            ArrayList<String> r = new ArrayList<String>();

            int count = 0;
            String linemeth = "";


            if (a != -1) {

                String[] me = line.split("\\(");
                for (String w : me) {
                    r.add(w);
                }
                r.removeAll(Arrays.asList("", null, " "));

                addtova(r);

                for (int i = 0; i < r.size(); i = i + 1) {


                    String[] b = r.get(i).split(" ");
                    ArrayList<String> rr = new ArrayList<String>();
                    for (String w : b) {
                        rr.add(w);
                    }
                    rr.removeAll(Arrays.asList("", null, " "));
                    String e = rr.get(rr.size() - 1);
                    e = e.replaceAll(" ", "");

                    if (Character.isLetter(e.charAt(e.length() - 1))) {
                        if (!("if".equalsIgnoreCase(e) || "for".equalsIgnoreCase(e) || "switch".equalsIgnoreCase(e) || "while".equalsIgnoreCase(e))) {
                            int k = e.indexOf('.');
//                                System.out.println(k+" "+e);
                            if (k != -1) {
                                String[] q = e.split("\\.");
                                //int o=q.length;
                                for (String c : q) {
                                    linemeth += c + ",";
                                    /*if(c.equalsIgnoreCase("this")){
                                        o--;
                                    }else{
                                        linemeth += c + ",";
                                    }*/
                                }
                                count = count + q.length;
                            } else {
                                linemeth += e + ",";
                                count++;
                            }

                        }

                    }

                }
                if (count != 0) {
                    linemeth = linemeth.substring(0, linemeth.length() - 1);
                }


            }

            this.meth.add(linemeth);

            this.methcount.add(count);


        }
    }


}
