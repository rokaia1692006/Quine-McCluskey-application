import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class ToBin{
    public static List<BinContainer> toBinfn(String[] var,int n){

        List<BinContainer> grps = new ArrayList<>();
        if(var.length < 1){
            System.out.println("NO INPUT");
            return null;
        }

        int max = maxINT(n);

        for(String val : var ){
            int value = Integer.parseInt(val);
            if(value>max){
                System.out.println("Invalid input");
                return null;
            }
            String bin = Integer.toBinaryString(value);
            while(bin.length()<n){
                bin = "0" + bin;
            }
            BinContainer bins = BinContainer.initialize(bin,value);
            grps.add(bins);
        }

        return grps;
    }

    public static int maxINT(int n){
        int max = 0;

        for(int i = 0 ; i < n ; i ++ ){
            max = max + (int)Math.pow(2,i);
        }
        return max;
    }
}

class BinContainer{
    String Bin;
    int num ;

    public static BinContainer initialize(String str , int Value){
        BinContainer nw = new BinContainer();
        nw.Bin = str;
        nw.num = Value;
        return nw;
    }
}

class BingrpsContainer{
    String bin;
    List <Integer> nums;
    boolean isnew = false;

    public static BingrpsContainer initialize(String str , List<Integer> Value){
        BingrpsContainer nw = new BingrpsContainer();
        nw.bin = str;
        nw.nums = Value;
        return nw;
    }
}


class code{
    public  JTable tableprint;
    public  DefaultTableModel tm;
    public  JTextArea Vars;
    public  JButton btn;
    public  JTextField numVar;
    public  JLabel Sumout;
    public  JLabel printagain;
    public JTextArea Dontcare;
    public  class resGui{
        int n;
        String var[];
    }
    class GUI{


        public  void gui(){
            JFrame frame = new JFrame();
            frame.setTitle("prime implicant");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
            panel.setBorder(BorderFactory.createEmptyBorder(20,10,20,10));
            panel.setBackground(Color.white);
            JLabel title = new JLabel("Prime Implecants and Essential Prime Implecants");
            title.setFont(new Font("Times New Roman",Font.BOLD,30));
            title.setAlignmentX(Component.CENTER_ALIGNMENT);
            title.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
            panel.add(title);
            JPanel numPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel numVarLabel = new JLabel("Enter number of variables");
            numVarLabel.setBorder(BorderFactory.createEmptyBorder(30,0,30,30));
            numPanel.add(numVarLabel);
            numVar = new JTextField(20);
            numVar.setBorder(BorderFactory.createLineBorder(Color.BLACK,2,true));
            panel.add(numPanel);
            panel.add(numVar);
            JPanel Varspanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel VarsLabel = new JLabel("Enter Variables(space seperated)");
            VarsLabel.setBorder(BorderFactory.createEmptyBorder(30,0,30,30));
            Varspanel.add(VarsLabel);
            Vars = new JTextArea();
            Vars.setBorder(BorderFactory.createLineBorder(Color.BLACK,2,true));
            panel.add(Varspanel);
            panel.add(Vars);
            panel.add(Box.createVerticalGlue());
            JPanel DontPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel dontlabel = new JLabel("Dont Care Value (space seperated)");
            dontlabel.setBorder(BorderFactory.createEmptyBorder(10,0,10,10));
            DontPanel.add(dontlabel);
            Dontcare = new JTextArea();
            Dontcare.setBorder(BorderFactory.createLineBorder(Color.BLACK,2,true));
            panel.add(DontPanel);
            panel.add(Dontcare);
            JPanel btnPanel = new JPanel();
            btn = new JButton("Calculate");
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
            btn.setFont(new Font("Times New Roman",Font.BOLD,20));
            btn.setBackground(Color.ORANGE);
            btnPanel.add(btn);
            btnPanel.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
            JPanel print = new JPanel(new  FlowLayout(FlowLayout.CENTER));
            print.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
            print.setBackground(Color.WHITE);
            printagain =new JLabel("");
            printagain.setFont(new Font("Serif",Font.BOLD,20));
            printagain.setAlignmentX(Component.CENTER_ALIGNMENT);
            printagain.setBackground(Color.RED);
            print.add(printagain);
            panel.add(print);
            Sumout = new JLabel("");
            Sumout.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));

            panel.add(btnPanel);
            panel.add(Box.createVerticalGlue());
            Sumout.setFont(new Font("Serif",Font.BOLD,20));
            Sumout.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(Sumout);
            panel.add(Box.createVerticalGlue());



            String [] colTitle = {"Variables" , "Binary final Representation"};
            tm = new DefaultTableModel(colTitle,0);

            tableprint = new JTable(tm);
            tableprint.setFont(new Font("Serif",Font.BOLD,20));
            tableprint.setRowHeight(30);
            tableprint.setBackground(Color.PINK);
            panel.add(tableprint);
            JScrollPane scroll = new JScrollPane(panel);
            scroll.getVerticalScrollBar().setUnitIncrement(16);
            frame.setContentPane(scroll);
            btn.addActionListener(code.this:: Start);

            frame.setVisible(true);



        }

    }
    public  void Start(ActionEvent a){
        String vars = Vars.getText().trim();

        String dontCare = Dontcare.getText().trim();
        int n = 0;
        n = Integer.parseInt(numVar.getText().trim());
        String[] all ;
        if (vars == null || vars.trim().isEmpty() || n <= 0) {
            System.out.println("No input provided.");
            JOptionPane.showMessageDialog(null,"No input provided.");

        }
        String[] var = vars.split(" ");
        String [] DCV = new String[0];
        if(dontCare.equals("") || dontCare == null){
            all = var;
            DCV = null;

        }
        else{
            DCV = dontCare.split(" ");
            all = Stream.concat(Arrays.stream(DCV),Arrays.stream(var)).toArray(String[]::new);
        }


        List <String> sortedAll = Arrays.asList(all);
        Collections.sort(sortedAll, Comparator.comparingInt(Integer::parseInt));
        all = sortedAll.toArray(new String[0]);
        printagain.setText(Arrays.toString(all));
        functions(all , n ,DCV);
        numVar.setText("");
        Dontcare.setText("");
        Vars.setText("");


    }
    public static void main(String []args){

        new code().new GUI().gui();
    }
    public List<String> functions(String[] var, int n,String[] DCV){

        List<BinContainer> grps = ToBin.toBinfn(var,n);
        if(grps == null){
            return null;
        }
        List<BingrpsContainer> pi = PI(grps);
        if(pi.isEmpty()){
            System.out.println("No Prime Implicants found");
            return null;
        }
        System.out.println("Prime Implicants:");
        tm.setRowCount(0);
        for (BingrpsContainer prime : pi) {
            String bin = prime.bin;
            String nums = prime.nums.toString();
            tm.addRow(new Object[] {nums , bin});

        }
        List<BingrpsContainer> ess = EssPI(pi , DCV);
        System.out.println("Essential Prime Implicants:");
        if (ess.isEmpty()) {
            System.out.println("No Essential Prime Implicants found");
        } else {
            for (BingrpsContainer container : ess) {
                System.out.print("Binary = " + container.bin + ", ");
                System.out.print("Numbers = " + container.nums);
                System.out.println();
            }
        }
        char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        System.out.println("essential letters:");
        StringBuilder sumletters = new StringBuilder();
        for(BingrpsContainer bin : ess){

            for(int i = 0 ; i < bin.bin.length();i++){
                if(bin.bin.charAt(i) == '1'){
                    sumletters.append(alphabet[i]);
                }
                else if(bin.bin.charAt(i) == '0'){
                    sumletters.append(alphabet[i]);
                    sumletters.append("'");
                }
            }
            sumletters.append(" + ");
        }
        sumletters.setLength(sumletters.length()-2);
        System.out.println(sumletters.toString());
        Sumout.setText(sumletters.toString());
        List<String> result = new ArrayList<>();
        result.add(sumletters.toString());
        return result;
    }

    public static List <BingrpsContainer> PI (List<BinContainer> binRep){
        if(binRep.isEmpty()){ return Collections.emptyList(); }

        LinkedHashMap <Integer , List<BinContainer>> one = new LinkedHashMap<>();
        for(BinContainer bins : binRep){
            int count = 0;
            for(int i = 0 ; i < bins.Bin.length() ; i ++){
                if(bins.Bin.charAt(i) == '1'){
                    count++;
                }
            }
            if(one.containsKey(count)){
                one.get(count).add(bins);

            }
            else{
                List<BinContainer> newlst = new ArrayList<>();
                newlst.add(bins);
                one.put(count, newlst);
            }
        }

        LinkedHashMap <Integer, List<BingrpsContainer>> MatchPair = new LinkedHashMap<>();
        for(Map.Entry<Integer, List<BinContainer>> entry : one.entrySet()){
            int count = entry.getKey();
            List<BinContainer> bins = entry.getValue();
            List <BinContainer> nxt = one.get(count+1);
            if(nxt == null){
                continue;
            }
            Set <String> repeats = new HashSet<>();
            for(BinContainer bin : bins){

                for(BinContainer next : nxt){
                    List<Integer> nums = new ArrayList<>();
                    nums.add(bin.num);
                    nums.add(next.num);
                    String numb = nums.toString();
                    if(diffrence(bin.Bin,next.Bin) != 1){
                        continue;
                    }
                    String Grp = combine(bin.Bin,next.Bin);
                    if(!repeats.contains(numb)){
                        BingrpsContainer nw = BingrpsContainer.initialize(Grp, nums);
                        MatchPair.computeIfAbsent(count, k -> new ArrayList<>()).add(nw);
                        repeats.add(numb);

                    }
                }

            }
        }




        List <BingrpsContainer> pi = new ArrayList<>();
        boolean combine = false;
        do {
            combine = false;
            LinkedHashMap <Integer, List<BingrpsContainer>> NewMatchPair = new LinkedHashMap<>();
            Set<String> added = new HashSet<>();

            for(Map.Entry<Integer, List<BingrpsContainer>> entry : MatchPair.entrySet()){
                int count = entry.getKey();
                List<BingrpsContainer> grps = entry.getValue();
                for(BingrpsContainer grp : grps){
                    boolean grpCombined = false;
                    String binaryRep = grp.bin;
                    List<BingrpsContainer> next = MatchPair.get(count+1);
                    if(next != null){
                        for(BingrpsContainer nextgrp : next){
                            if (nextgrp.isnew) continue;
                            String binRepNext = nextgrp.bin;
                            if(Cancombine(binaryRep, binRepNext)){
                                combine = true;
                                grpCombined = true;
                                grp.isnew = true;
                                nextgrp.isnew = true;
                                String newRep = combine(binaryRep, binRepNext);

                                List<Integer> combineNums = combineLists(grp.nums, nextgrp.nums);
                                Collections.sort(combineNums);
                                String numbersorder = combineNums.toString();
                                if (!added.contains(numbersorder)){
                                    added.add(numbersorder);
                                    BingrpsContainer g = BingrpsContainer.initialize(newRep, combineNums);
                                    if(NewMatchPair.containsKey(count)){
                                        NewMatchPair.get(count).add(g);
                                    }
                                    else{
                                        List <BingrpsContainer> newlst = new ArrayList<>();
                                        newlst.add(g);
                                        NewMatchPair.put(count , newlst);
                                    }
                                }
                            }
                        }
                    }
                    if (!grpCombined) {
                        if(NewMatchPair.containsKey(count)){
                            NewMatchPair.get(count).add(grp);
                        }
                        else{
                            List <BingrpsContainer> newlst = new ArrayList<>();
                            newlst.add(grp);
                            NewMatchPair.put(count , newlst);
                        }
                    }
                }
            }
            MatchPair = NewMatchPair;
        } while(combine==true);

        Set <Integer> uniqueNums = new LinkedHashSet<>();
        for(Map.Entry<Integer, List<BingrpsContainer>> entry : MatchPair.entrySet()){
            List<BingrpsContainer> grps = entry.getValue();
            for(BingrpsContainer grp : grps){
                if(!grp.isnew){
                    pi.add(grp);
                    uniqueNums.addAll(grp.nums);
                }
            }
        }
        for(BinContainer bin : binRep){
            if(!uniqueNums.contains(bin.num)){
                BingrpsContainer nw = BingrpsContainer.initialize(bin.Bin, Collections.singletonList(bin.num));
                pi.add(nw);
            }
        }



        return pi;
    }

    public static List<Integer> combineLists(List<Integer> one , List<Integer> Two){
        Set<Integer> combineSet = new LinkedHashSet<>(one);
        combineSet.addAll(Two);
        one = null;
        Two = null;
        return new ArrayList<>(combineSet);
    }
    public static List<BingrpsContainer> EssPI(List<BingrpsContainer> pi , String [] DCV){
        if(pi.isEmpty()){
            return Collections.emptyList();
        }
        List <Integer> DC = new ArrayList<>();
if(DCV != null && DCV.length != 0 ){
    DC = Arrays.stream(DCV).map(Integer::parseInt).collect(Collectors.toList());
}


        List<BingrpsContainer> Rdc = new ArrayList<>();
        for(BingrpsContainer bin : pi){
            List<Integer> Ndc = new ArrayList<>();
            for(Integer num : bin.nums){
                if(!DC.contains(num)){
                    Ndc.add(num);
                }
            }
            if(!Ndc.isEmpty()){
                BingrpsContainer binNdc =new BingrpsContainer();
                binNdc.bin = bin.bin;
                binNdc.nums = Ndc;
                Rdc.add(binNdc);
            }
        }

        List <BingrpsContainer> essential = new ArrayList<>();
        HashMap<Integer, Integer> count = new HashMap<>();
        HashMap <Integer, List<BingrpsContainer>> valuesbins = new HashMap<>();
        for(BingrpsContainer bin : Rdc){
            for(Integer num : bin.nums){
                if(!count.containsKey(num)){
                    count.put(num, 1);
                }
                else{
                    count.put(num,count.get(num)+1);
                }
                if(!valuesbins.containsKey(num)){
                    valuesbins.put(num,new ArrayList<>());

                }
                valuesbins.get(num).add(bin);
            }

        }
        Set <Integer> used = new LinkedHashSet<>();
        for(Map.Entry<Integer,Integer> c: count.entrySet()){
            if(c.getValue()==1){
                essential.add(valuesbins.get(c.getKey()).get(0));
                used.addAll(valuesbins.get(c.getKey()).get(0).nums);
            }
        }
        for(BingrpsContainer bin : Rdc){
            if(Collections.disjoint(used, bin.nums) && !essential.contains(bin)){
                long ccover = bin.nums.stream().filter(num->!used.contains(num)).count();
                if(ccover>0){
                    essential.add(bin);
                    used.addAll(bin.nums);
                }
            }
        }

        Set <BingrpsContainer> essentialSet = new LinkedHashSet<>(essential);
        essential.clear();
        essential.addAll(essentialSet);
        count = null;
        valuesbins = null;

        return essential;

    }





    public static Boolean Cancombine(String one , String two){
        int j = 0;
        for(int i = 0 ; i < one.length() ; i ++){
            if(one.charAt(i)!=two.charAt(i)){
                if(one.charAt(i) == '_' || two.charAt(i) == '_'){
                    return false;
                }
                j++;
                if(j > 1){
                    return false;
                }
            }}
        if(j == 1){
            return true;
        }
        return false;


    }

    public static String combine(String one, String Two){
        StringBuilder removeDiff = new StringBuilder();

        for(int i = 0 ; i < one.length(); i++){
            if(one.charAt(i)!=Two.charAt(i)){


                removeDiff.append('_');
            }
            else{
                removeDiff.append(one.charAt(i));
            }
        }
        one = null;
        Two = null;
        return removeDiff.toString();
    }

    public static int diffrence(String bin1, String bin2){
        int count = 0;
        for (int i = 0 ; i < bin1.length(); i++){
            if(bin1.charAt(i) != bin2.charAt(i)){
                count++;
            }
        }
        return count;
    }
}