import java.util.*;
import java.io.*;

public class AdventOfCode {
    public static ArrayList<String> Inputs;

    public static void main(String[] args) throws IOException {
        Inputs = new ArrayList<String>();
//		Day6 d = new Day6();
//		d.doDay(Part.TWO);
        new Day10().doDay(Part.TWO);
    }

    public static class Day10 implements DayInterface {
        @Override
        public String fileNum() {
            return "10";
        }

        boolean started = true;
        boolean pstarted = true;
        long sum = 0;

        @Override
        public void part1(ArrayList<String> in) {
            ArrayList<Integer> ratings = new ArrayList<>();
            ratings.add(0);
            for (String line : in) {
                ratings.add(Integer.parseInt(line));
            }
            Collections.sort(ratings);
            System.out.println(ratings);
            int oneSum = 0;
            int threeSum = 0;
            System.out.print(" ");
            for (int i = 0; i < ratings.size()-1; i++) {
                int diff = ratings.get(i+1)-ratings.get(i);
                System.out.print(diff+", ");
                switch (diff) {
                    case 1:
                        oneSum++;
                        break;
                    case 3:
                        threeSum++;
                        break;
                    default:
                        break;
                }
            }
            threeSum++;
            System.out.println();
            System.out.println(oneSum);
            System.out.println(threeSum);
            System.out.println(oneSum*threeSum);
        }

        @Override
        public void part2(ArrayList<String> in) {
            ArrayList<Integer> ratings = new ArrayList<>();
            ratings.add(0);
            HashMap<Integer, ArrayList<Integer>> ratingPoses = new HashMap<>();
            for (String line : in) {
                System.out.println(line+": "+Integer.parseInt(line));
                ratings.add(Integer.parseInt(line));
            }
            Collections.sort(ratings);
            int fin = ratings.get(ratings.size()-1);
            System.out.println(fin);
            ratings.add(fin+3);
            System.out.println(ratings);
            for (int r : ratings) {
                ArrayList<Integer> ratingPos = new ArrayList<>();
                for (int i = 1; i <=3; i++) {
                    if (ratings.contains(r+i)) {
                        ratingPos.add(r+i);
                    }
                }
                ratingPoses.put(r, ratingPos);
            }

            System.out.println(ratingPoses);
            ArrayList<Integer> superRatingPoses = new ArrayList<>();

            for (int i = 1; i <ratings.size()-1; i++) {
                if (ratings.get(i+1)-ratings.get(i-1)<=3) {
                    superRatingPoses.add(ratings.get(i));
                }
            }
            System.out.println(superRatingPoses);

            System.out.println(superRatingPoses.size());
            int sum = 0;
            for (long i = 0; i < Math.pow(2, superRatingPoses.size()); i++) {
                if (i==i) {
                    continue;
                }
                char[] code = Long.toBinaryString(i).toCharArray();
                Set<Integer> posRem = new TreeSet<>();
                for (int j = code.length-1; j >-1 ; j--) {
                    if (code[j]=='1') {
                        posRem.add(superRatingPoses.get(code.length-1-j));
                    }
//                    switch (code[j]) {
//                        case '1':
//                            posRem.add(superRatingPoses.get(code.length-1-j));
//                    }
                }
                ArrayList<Integer> clone = new ArrayList<>(ratings);
                for (Integer n : posRem) {
                    clone.remove(Integer.valueOf(n));
                }
                boolean isValid = checkRemovable(clone);
                if (isValid) {
                    sum++;
                }
//                System.out.println(clone+": "+posRem+" ("+isValid+")");
            }
            System.out.println(superRatingPoses.size());
            System.out.println(sum);
            System.out.println("done");
//            recursiveElim(ratings);

//            System.out.println(sum);
//            System.out.println(orders);
//            System.out.println(orders.size());

        }

//        private void recursiveElim(ArrayList<Integer> ratings) \
//            for (int i = 1; i <ratings.size()-1; i++) {
//                if (!checkRemovable(ratings)) {
//                    return;
//                }
//                if (ratings.get(i+1)-ratings.get(i-1)<=3) {
//                    Set<Integer> one = new TreeSet<>();
//                    one.add(ratings.get(i));
//                    orders.add(one);
//                    order.add(ratings.get(i));
//                    ArrayList<Integer> removed = new ArrayList<>(ratings);
//                    removed.remove(i);
//                    recursiveElim(removed);
//                    System.out.println(orders.size());
//                }
//            }
//            if (orders.add(order)) {
//                sum++;
//            }
//            order = new TreeSet<>();
//        }
        private boolean checkRemovable(ArrayList<Integer> ratings) {
            for (int i = 1; i < ratings.size(); i++) {
                int after = ratings.get(i);
                int before = ratings.get(i-1);
                int diff = after-before;
                if (!(diff<=3)) {
                    return false;
                }
            }
            return true;
        }
        

        private void recursiveFind(HashMap<Integer, ArrayList<Integer>> ratingPoses, int n) {
            System.out.println("-------------------------");
            pstarted = true;
            for (int r:
                 ratingPoses.get(n)) {
                started = true;
                System.out.println(r);
                recursiveFind(ratingPoses, r);
                if (pstarted==false&&started==true) {
                    System.out.println("^^^^^^^^^^^^^^^");
                    sum++;
                }
                started = false;
            }
            pstarted = false;
            System.out.println("================");
        }

        //private void recursiveFind2(HashMap<Integer, ArrayList<Integer>> ratingPoses, int n) {
//
//            System.out.println("-------------------------");
//            order.add(n);
//            for (int r: ratingPoses.get(n)) {
//                System.out.println(r);
//                recursiveFind2(ratingPoses, r);
//                if (ratingPoses.get(r).size()==0) {
//                    orders.add(order);
//                    order= new TreeSet<>();
//                    sum++;
//                }
//            }
//            System.out.println("================"+n);
//        }
//
//        


    }

    public static class Day9 implements DayInterface {
        @Override
        public String fileNum() {
            return "9";
        }

        @Override
        public void part1(ArrayList<String> in) {
            int preamble = 25;
            ArrayList<Long> ints = new ArrayList<>();
            for (String line : in) {
                ints.add(Long.parseLong(line));
            }

            long badNum = 0;
            for (int i = 0; i < ints.size()/*-preamble*/; i++) {
                ArrayList<Long> preInts = new ArrayList<>(ints.subList(0 + i, preamble + i));
                System.out.println(ints);
                System.out.println(preInts);

                ArrayList<Long> coms = new ArrayList<>(getSumCom(preInts));
                System.out.println(coms);
                System.out.println(coms.size());

                System.out.println(ints.get(i + preamble));
                if (!coms.contains(ints.get(i + preamble))) {
                    badNum = ints.get(i + preamble);
                    break;
                }
            }
            System.out.println(badNum);
            System.out.println((preamble * (preamble - 1) / 2));
        }

        private boolean checkValidNextNum(ArrayList<Long> preInts, long next) {
            return !getSumCom(preInts).contains(next);
        }

        private ArrayList<Long> getSumCom(ArrayList<Long> ints) {
            ArrayList<Long> outs = new ArrayList<>();
            for (int i = 0; i < ints.size() - 1; i++) {
                for (int j = i; j < ints.size(); j++) {
                    if (i != j) {
                        outs.add(ints.get(i) + ints.get(j));
//                        System.out.println(String.format("%s+%s=%s", ints.get(i), ints.get(j), ints.get(i)+ints.get(j)));
                    }
                }
//                System.out.println("======================");
            }
            return outs;
        }

        @Override
        public void part2(ArrayList<String> in) {
            int preamble = 25;
            ArrayList<Long> ints = new ArrayList<>();
            for (String line : in) {
                ints.add(Long.parseLong(line));
            }

            long badNum = 0;
            int badIndex = 0;
            for (int i = 0; i < ints.size()/*-preamble*/; i++) {
                ArrayList<Long> preInts = new ArrayList<>(ints.subList(i, preamble + i));
                System.out.println(ints);
                System.out.println(preInts);

                ArrayList<Long> coms = new ArrayList<>(getSumCom(preInts));
                System.out.println(coms);
                System.out.println(coms.size());

                System.out.println(ints.get(i + preamble));
                if (!coms.contains(ints.get(i + preamble))) {
                    badNum = ints.get(i + preamble);
                    badIndex = i + preamble;
                    break;
                }
            }
            System.out.println(badNum);
            System.out.println(badIndex);

            System.out.println(getSumSublist(ints, badNum));

//            System.out.println((preamble * (preamble - 1) / 2));
        }

        private long getSumSublist(ArrayList<Long> ints, long target) {
            for (int i = 0; i < ints.size(); i++) {
                for (int j = i + 1; j < ints.size(); j++) {
                    if (i != j) {
                        ArrayList<Long> con = new ArrayList<>(ints.subList(i, j + 1));
//                        System.out.println(con);

                        int sum = 0;
                        for (long num : con) {
                            sum += num;
                        }
                        if (sum == target) {
//                            System.out.println(con);
                            System.out.println(sum);
                            Collections.sort(con);
                            System.out.println(con);
                            return con.get(0) + con.get(con.size() - 1);
                        }
                    }
                }
            }
            return 0;
        }
    }

    public static class Day8 implements DayInterface {
        public String fileNum() {
            return "8";
        }

        public void part1(ArrayList<String> ins) {
            ArrayList<String> ops = new ArrayList<>();
            ArrayList<Integer> args = new ArrayList<>();

            for (String line : ins) {
                String[] splittedLine = line.split(" ");
                ops.add(splittedLine[0]);
                args.add(Integer.parseInt(splittedLine[1]));
            }
            System.out.println(ops);
            System.out.println(args);

            int insIndex = 0;
            ArrayList<Integer> doneIndeces = new ArrayList<>();
            int accumulator = 0;
            while (!doneIndeces.contains(insIndex)) {
                switch (ops.get(insIndex)) {
                    case "nop":
                        System.out.println(insIndex);
                        doneIndeces.add(insIndex);
                        insIndex++;
                        break;
                    case "acc":
                        System.out.println(insIndex);
                        accumulator += args.get(insIndex);
                        doneIndeces.add(insIndex);
                        insIndex++;
                        break;
                    case "jmp":
                        System.out.println(insIndex);
                        doneIndeces.add(insIndex);
                        insIndex += args.get(insIndex);
                        break;
                }
            }
            System.out.println(doneIndeces);
            System.out.println(insIndex);
            System.out.println(accumulator);
        }

        public void part2(ArrayList<String> ins) {
            ArrayList<String> ops = new ArrayList<>();
            ArrayList<Integer> args = new ArrayList<>();

            for (String line : ins) {
                String[] splittedLine = line.split(" ");
                ops.add(splittedLine[0]);
                args.add(Integer.parseInt(splittedLine[1]));
            }
            System.out.println(ops);
            System.out.println(args);

            ArrayList<String> goodOps = null;
            for (int i = 0; i < ins.size(); i++) {
                if (ops.get(i) == "acc") {
                    continue;
                }
                ArrayList<String> changedOps = new ArrayList<>(ops);
                changedOps.set(i, changeOp(ops.get(i)));
                System.out.println(changedOps + ": " + Arrays.toString(checkLoops(changedOps, args)));
                if (checkLoops(changedOps, args) != null) {
                    goodOps = new ArrayList<>(changedOps);
                    break;
                }
            }
            System.out.println("======================================================================================");
            System.out.println(goodOps);
            System.out.println(Arrays.toString(checkLoops(goodOps, args)));
            System.out.println(ins.size());
        }

        private String changeOp(String op) {
            String returned;
            switch (op) {
                case "jmp":
                    returned = "nop";
                    break;
                case "nop":
                    returned = "jmp";
                    break;
                default:
                    returned = "acc";
            }
            return returned;
        }

        private int[] checkLoops(ArrayList<String> ops, ArrayList<Integer> args) {

            int insIndex = 0;
            ArrayList<Integer> doneIndeces = new ArrayList<>();
            int accumulator = 0;
            while (!doneIndeces.contains(insIndex)) {
                if (insIndex == ops.size()) {
                    return new int[]{insIndex, accumulator};
                }
                switch (ops.get(insIndex)) {
                    case "nop":
                        doneIndeces.add(insIndex);
                        insIndex++;
                        break;
                    case "acc":
                        accumulator += args.get(insIndex);
                        doneIndeces.add(insIndex);
                        insIndex++;
                        break;
                    case "jmp":
                        doneIndeces.add(insIndex);
                        insIndex += args.get(insIndex);
                        break;
                }
            }
            return null;
        }
    }

    public static class Day7 implements DayInterface {
        public String fileNum() {
            return "7";
        }

        public void part1(ArrayList<String> in) {
            HashMap<String, HashMap<String, Integer>> bagColorRules = new HashMap<>();
            for (String line : in) {
                line = line.replace('.', ' ');
                System.out.println(line);
                String[] splittedLine = line.split(" ");
                System.out.println(Arrays.toString(splittedLine));

                String bagColor = splittedLine[0] + " " + splittedLine[1];
                System.out.println(bagColor);

                boolean containsOther = splittedLine.length > 7;

                HashMap<String, Integer> bagsContained;
                if (containsOther) {
                    bagsContained = new HashMap<>();
                    int bagCount = (splittedLine.length - 4) / 4;
                    System.out.println(bagCount);
                    for (int i = 0; i < bagCount; i++) {
                        int index = (i + 1) * 4;
                        String subBagColor = splittedLine[index + 1] + " " + splittedLine[index + 2];
                        int subBagCount = Integer.parseInt(splittedLine[index]);
                        System.out.println(splittedLine[index] + " " + subBagColor);
                        bagsContained.put(subBagColor, subBagCount);
                    }
                } else {
                    bagsContained = null;
                }

                bagColorRules.put(bagColor, bagsContained);
                System.out.println(bagsContained);
                System.out.println("============================================================================");
            }
            System.out.println(bagColorRules);
            int sum = 0;
            for (String keyColor : bagColorRules.keySet()) {
                boolean hasBag = recursiveCheckHasBag(bagColorRules, keyColor);
                if (hasBag) {
                    sum++;
                }
                String mark = hasBag ? "<<<<<<<<" + String.valueOf(sum) : "x";
                System.out.println(keyColor + ": " + mark);
            }
            System.out.println(sum);

            System.out.println(bagColorRules.get("shiny gold"));
        }

        private boolean recursiveCheckHasBag(HashMap<String, HashMap<String, Integer>> bagColorRules, String bagColor) {
            if (bagColor != "shiny gold") {
                HashMap<String, Integer> contained = bagColorRules.get(bagColor);
                if (contained != null) {
                    if (contained.containsKey("shiny gold")) {
                        return true;
                    } else {
                        for (String keyColor : contained.keySet()) {
                            if (recursiveCheckHasBag(bagColorRules, keyColor)) {
                                return true;
                            }
                        }
                    }
                }
                return false;
            }
            return false;
        }

        public void part2(ArrayList<String> in) {
            HashMap<String, HashMap<String, Integer>> bagColorRules = new HashMap<>();
            for (String line : in) {
                line = line.replace('.', ' ');
                System.out.println(line);
                String[] splittedLine = line.split(" ");
                System.out.println(Arrays.toString(splittedLine));

                String bagColor = splittedLine[0] + " " + splittedLine[1];
                System.out.println(bagColor);

                boolean containsOther = splittedLine.length > 7;

                HashMap<String, Integer> bagsContained;
                if (containsOther) {
                    bagsContained = new HashMap<>();
                    int bagCount = (splittedLine.length - 4) / 4;
                    System.out.println(bagCount);
                    for (int i = 0; i < bagCount; i++) {
                        int index = (i + 1) * 4;
                        String subBagColor = splittedLine[index + 1] + " " + splittedLine[index + 2];
                        int subBagCount = Integer.parseInt(splittedLine[index]);
                        System.out.println(splittedLine[index] + " " + subBagColor);
                        bagsContained.put(subBagColor, subBagCount);
                    }
                } else {
                    bagsContained = null;
                }

                bagColorRules.put(bagColor, bagsContained);
                System.out.println(bagsContained);
                System.out.println("============================================================================");
            }
            System.out.println(bagColorRules);
            System.out.println(bagColorRules.get("shiny gold"));
            System.out.println(recursiveGetTotal(bagColorRules, "shiny gold"));
        }

        private int recursiveGetTotal(HashMap<String, HashMap<String, Integer>> bagColorRules, String bagColor) {
            int sum = 0;

            if (bagColorRules.get(bagColor) != null) {
                System.out.println(bagColorRules.get(bagColor).keySet());
                Set<String> bagsContained = bagColorRules.get(bagColor).keySet();
                for (String colorKey : bagsContained) {
                    int bagCount = bagColorRules.get(bagColor).get(colorKey);
                    System.out.println(bagCount);
                    System.out.println("=====================");
                    sum += bagCount + bagCount * recursiveGetTotal(bagColorRules, colorKey);

                    System.out.println("===========================");
                }
            }
            //System.out.println(sum);
            return sum;
        }
    }

    public static class Day6 extends Day {
        public String fileNum() {
            return "6";
        }

        public void part1(ArrayList<String> in) {
            ArrayList<Hashtable<Character, Integer>> groups = new ArrayList<Hashtable<Character, Integer>>();
            Hashtable<Character, Integer> groupAns = new Hashtable<Character, Integer>();
            for (String line : in) {
                if (line.length() > 0) {
                    String[] lineInfos = line.split("");

                    for (String info : lineInfos) {
                        groupAns.put(info.toCharArray()[0], 1);
                    }
                } else {
                    groups.add(groupAns);
                    groupAns = new Hashtable<Character, Integer>();
                }
            }
            groups.add(groupAns);

            int sum = 0;
            for (Hashtable<Character, Integer> ans : groups) {
                sum += ans.size();
            }

            System.out.println(sum);
        }

        public void part2(ArrayList<String> in) {
            ArrayList<ArrayList<Set<Character>>> groups = new ArrayList<ArrayList<Set<Character>>>();
            ArrayList<Set<Character>> group = new ArrayList<>();
            for (String line : in) {
                if (line.length() > 0) {
                    char[] lineChars = line.toCharArray();
                    Character[] lineCharsObj = new Character[lineChars.length];
                    for (int i = 0; i < lineChars.length; i++) {
                        lineCharsObj[i] = Character.valueOf(lineChars[i]);
                    }
                    Set<Character> charSet = new HashSet<>(Arrays.asList(lineCharsObj));
                    group.add(charSet);
                } else {
                    groups.add(group);
                    group = new ArrayList<Set<Character>>();
                }
            }
            groups.add(group);

            for (ArrayList<Set<Character>> gr : groups) {
                for (Set<Character> set : gr) {
                    for (Set<Character> set1 : gr) {
                        set.retainAll(set1);
                        // set1.retainAll(set);
                    }
                }
            }

            Set<Character> a = groups.get(2).get(0);
            Set<Character> b = groups.get(2).get(1);
            a.retainAll(b);
            System.out.println(in);

            int sum = 0;
            for (ArrayList<Set<Character>> gr : groups) {
                System.out.println(gr.get(0).size());
                sum += gr.get(0).size();
            }
            System.out.println(sum);
        }
    }

    public static class Day5 extends Day {
        public String fileNum() {
            return "5";
        }

        public void part1(ArrayList<String> in) {
            int highest = 0;

            ArrayList<Integer> ids = new ArrayList<Integer>();

            for (String line : in) {
                char[] rowCode = line.substring(0, 7).toCharArray();
                char[] columnCode = line.substring(7).toCharArray();

                int row = binaryStr(rowCode, 'B');
                int column = binaryStr(columnCode, 'R');

                int id = row * 8 + column;
                ids.add(id);

                if (id > highest) {
                    highest = id;
                }
                System.out.println(rowCode);
                System.out.println(row);
                System.out.println(column);
                System.out.println(id);
                System.out.println("--------");
            }

            Collections.sort(ids);
            System.out.println(ids);

            int myIdIndex = 0;
            for (int i = 0; i < ids.size() - 1; i++) {
                if (ids.get(i) != ids.get(i + 1) - 1) {
                    System.out.println(i);
                    myIdIndex = i;
                    break;
                }
            }

            System.out.println(ids.size());
            System.out.println(highest);
            System.out.println(ids.get(myIdIndex) + 1);
        }

        private int binaryStr(char[] bin, char one) {
            int j = 0;
            int sum = 0;
            for (int i = bin.length; i > 0; i--) {
                int index = i - 1;
                int num = bin[index] == one ? 1 : 0;
                sum += num * Math.pow(2, j);
                j++;
            }
            return sum;
        }

        public void part2(ArrayList<String> in) {
            part1(in);
        }
    }

    public static class Day4 extends Day {
        public static String[] required = {"byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"};

        public String fileNum() {
            return "4";
        }

        public void part1(ArrayList<String> in) {
            ArrayList<Hashtable<String, String>> allPassportInfos = new ArrayList<Hashtable<String, String>>();
            Hashtable<String, String> passportInfos = new Hashtable<String, String>();
            for (String line : in) {
                if (line.length() > 0) {
                    String[] lineInfos = line.split(" ");
                    for (String info : lineInfos) {
                        String[] splittedInfo = info.split(":");
                        passportInfos.put(splittedInfo[0], splittedInfo[1]);
                    }
                } else {
                    allPassportInfos.add(passportInfos);
                    passportInfos = new Hashtable<String, String>();
                }
            }
            allPassportInfos.add(passportInfos);

            int sum = 0;
            for (Hashtable<String, String> pInfo : allPassportInfos) {
                boolean hasAll = true;
                for (String req : required) {
                    hasAll = hasAll && pInfo.containsKey(req);
                }

                if (hasAll) {
                    sum++;
                }
            }
            System.out.println(sum);
        }

        public void part2(ArrayList<String> in) {
            ArrayList<Hashtable<String, String>> allPassportInfos = new ArrayList<Hashtable<String, String>>();
            Hashtable<String, String> passportInfos = new Hashtable<String, String>();
            for (String line : in) {
                if (line.length() > 0) {
                    String[] lineInfos = line.split(" ");
                    for (String info : lineInfos) {
                        String[] splittedInfo = info.split(":");
                        passportInfos.put(splittedInfo[0], splittedInfo[1]);
                    }
                } else {
                    allPassportInfos.add(passportInfos);
                    passportInfos = new Hashtable<String, String>();
                }
            }
            allPassportInfos.add(passportInfos);

            System.out.println(allPassportInfos);

            String reqStr = "0123456789abcdef";

            String[] validColorsArr = {"amb", "blu", "brn", "gry", "grn", "hzl", "oth"};
            List<String> validColors = new ArrayList<String>();
            Collections.addAll(validColors, validColorsArr);

            int sum = 0;
            for (Hashtable<String, String> pInfo : allPassportInfos) {
                boolean hasAll = true;

                for (String req : required) {
                    boolean hasKey = pInfo.containsKey(req);
                    boolean isValid = false;

                    if (hasKey) {
                        String val = pInfo.get(req);
                        switch (req) {
                            case "byr":
                                isValid = strCheckBetween(val, 4, 1920, 2002);
                                break;
                            case "iyr":
                                isValid = strCheckBetween(val, 4, 2010, 2020);
                                break;
                            case "eyr":
                                isValid = strCheckBetween(val, 4, 2020, 2030);
                                break;
                            case "hgt":
                                if (checkBetween(val.length(), 4, 5)) {
                                    String end = val.substring(val.length() - 2);
                                    if (end.equals("in") || end.equals("cm")) {
                                        String start = val.substring(0, val.length() - 2);
                                        switch (end) {
                                            case "cm":
                                                isValid = strCheckBetween(start, 3, 150, 193);
                                                break;
                                            case "in":
                                                isValid = strCheckBetween(start, 2, 59, 76);
                                                break;
                                            default:
                                                break;
                                        }
                                    }
                                }
                                break;
                            case "hcl":
                                if (val.length() == 7) {
                                    if (val.charAt(0) == '#') {
                                        char[] end = val.substring(1).toCharArray();
                                        for (char c : end) {
                                            if (reqStr.contains(String.valueOf(c))) {
                                                isValid = true;
                                            } else {
                                                isValid = false;
                                                break;
                                            }
                                        }
                                    }
                                }
                                break;
                            case "ecl":
                                if (val.length() == 3) {
                                    if (validColors.contains(val)) {
                                        isValid = true;
                                    }
                                }
                                break;
                            case "pid":
                                if (val.length() == 9) {
                                    isValid = true;
                                }
                                break;
                            default:
                                break;
                        }
                        System.out.println(req + "=" + val + ": " + String.valueOf(isValid));
                    } else {
                        hasAll = false;
                        // break;
                    }
                    if (!isValid) {
                        hasAll = false;
                        // break;
                    }
                }
                System.out.println("====123456789=====================");
                if (hasAll) {
                    sum++;
                } else {
                    System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
                }
            }
            System.out.println(sum);
        }

        private static boolean checkBetween(int x, int min, int max) {
            return x >= min && x <= max;
        }

        private static boolean strCheckBetween(String val, int len, int min, int max) {
            if (val.length() == len) {
                int intVal;
                try {
                    intVal = Integer.parseInt(val);
                } catch (Exception e) {
                    return false;
                }
                if (checkBetween(intVal, min, max)) {
                    return true;
                }
                return false;
            }
            return false;
        }
    }

    public static class Day3 extends Day {

        public String fileNum() {
            return "3";
        }

        public void part1(ArrayList<String> in) {
            String[] inputs = new String[in.size()];
            inputs = in.toArray(inputs);
            int x = 0;
            int sum = 0;
            for (int y = 0; y < inputs.length; y++) {
                char[] rep;
                int index = x % inputs[y].length();
                if (inputs[y].charAt(index) == '#') {
                    rep = inputs[y].toCharArray();
                    rep[index] = 'X';
                    sum++;
                } else {
                    rep = inputs[y].toCharArray();
                    rep[index] = 'O';
                }
                System.out.println(rep);
                x += 3;
            }
            System.out.println(sum);
        }

        public void part2(ArrayList<String> in) {
            String[] inputs = new String[in.size()];
            inputs = in.toArray(inputs);
            int[][] slopes = {{1, 1}, {3, 1}, {5, 1}, {7, 1}, {1, 2}};
            long product = 1;
            ArrayList<Integer> sums = new ArrayList<Integer>();
            for (int[] slope : slopes) {
                int x = 0;
                int sum = 0;
                for (int y = 0; y < inputs.length; y += slope[1]) {
                    int index = x % inputs[y].length();
                    char[] rep;
                    if (inputs[y].charAt(index) == '#') {
                        rep = inputs[y].toCharArray();
                        rep[index] = 'X';
                        System.out.println(rep);
                        sum++;
                    } else {
                        rep = inputs[y].toCharArray();
                        rep[index] = 'O';
                        System.out.println(rep);
                    }
                    x += slope[0];
                }
                sums.add(sum);
                System.out.println(sum);
                product *= sum;
                System.out.println("=======================================================================");

            }
            sums.forEach((n) -> System.out.println(n));
            System.out.println(product);
        }
    }

    public static class Day2 extends Day {
        public String fileNum() {
            return "2";
        }

        public void part1(ArrayList<String> in) {
            int superSum = 0;
            for (String n : Inputs) {
                String[] splitted = n.split(" ");
                String[] stringBounds = splitted[0].split("-");
                Integer[] bounds = {Integer.parseInt(stringBounds[0]), Integer.parseInt(stringBounds[1])};
                char target = splitted[1].charAt(0);
                String password = splitted[2];
                int sum = 0;
                for (char c : password.toCharArray()) {
                    if (c == target) {
                        sum++;
                    }
                }
                // System.out.println(sum>=bounds[0]&&sum<=bounds[1]);
                if (sum >= bounds[0] && sum <= bounds[1]) {

                    superSum++;
                }
            }
            System.out.println(superSum);
        }

        public void part2(ArrayList<String> in) {
            int superSum = 0;
            for (String n : Inputs) {
                String[] splitted = n.split(" ");
                String[] stringBounds = splitted[0].split("-");
                Integer[] pos = {Integer.parseInt(stringBounds[0]), Integer.parseInt(stringBounds[1])};
                char target = splitted[1].charAt(0);
                char[] password = splitted[2].toCharArray();
                if (password[pos[0] - 1] == target ^ password[pos[1] - 1] == target) {
                    superSum++;
                }
            }
            System.out.println(superSum);
        }
    }

    public static class Day1 extends Day {
        public String fileNum() {
            return "1";
        }

        public void part1(ArrayList<String> in) {
            ArrayList<Integer> inInt = new ArrayList<Integer>();
            in.forEach((n) -> inInt.add(Integer.parseInt(n)));
            for (Integer n : inInt) {
                for (Integer m : inInt) {
                    if (n + m == 2020) {
                        System.out.println(n);
                        System.out.println(m);
                        System.out.println(n * m);
                        return;
                    }
                }
            }
        }

        public void part2(ArrayList<String> in) {
            ArrayList<Integer> inInt = new ArrayList<Integer>();
            in.forEach((n) -> inInt.add(Integer.parseInt(n)));
            for (Integer a : inInt) {
                for (Integer b : inInt) {
                    for (Integer c : inInt) {
                        if (a + b + c == 2020) {
                            System.out.println(a);
                            System.out.println(b);
                            System.out.println(c);
                            System.out.println(a * b * c);
                            return;
                        }
                    }
                }
            }
        }
    }

    public static String inputPath(String a) {
        return "D:\\Fabian\\Advent_of_Code\\" + a + ".txt";
    }

    public static void readInput(String n) {
        FileReader fr;
        try {
            fr = new FileReader(inputPath(n));
            BufferedReader reader = new BufferedReader(fr);

            String line = null;
            Inputs.clear();
            try {
                while ((line = reader.readLine()) != null) {
                    Inputs.add(line);
                }
                fr.close();
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
