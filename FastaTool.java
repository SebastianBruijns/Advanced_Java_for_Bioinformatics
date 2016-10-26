package Assignment_1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Sebastian on 23.10.2016.
 * processes input file
 */

class FastaTool {

    private BufferedReader bfr;
    private ArrayList<Sequence> data = new ArrayList<Sequence>();
    private int size;
    private int avgLength;
    private int maxLength;
    private int minLength;
    private int avgLengthWithout;
    private int maxLengthWithout;
    private int minLengthWithout;


    FastaTool(String inputFile) throws IOException {

        File file = new File(inputFile);
        FileReader fr = new FileReader(file);
        this.bfr = new BufferedReader(fr);

        processFiles();
    }

    private void processFiles() throws IOException {
        String tmp;
        String tmpID = "";
        String seq = "";
        boolean first = true;
        while ((tmp = bfr.readLine()) != null) {
            if (tmp.startsWith(">")) {
                if (! first) {
                    data.add(new Sequence(tmpID.substring(1), seq));
                    seq = "";
                }
                tmpID = tmp;
                first = false;
            } else {
                seq = seq.concat(tmp);
            }
        }
        data.add(new Sequence(tmpID.substring(1), seq));
        this.size = data.size();

        this.avgLength = 0;
        this.avgLengthWithout = 0;
        this.maxLength = 0;
        this.maxLengthWithout = 0;
        this.minLength = data.get(0).getLength();
        this.minLengthWithout = data.get(0).getLengthWithoutHyphen();
        for (Sequence s : data) {
            avgLength += s.getLength();
            avgLengthWithout += s.getLengthWithoutHyphen();
            maxLength = Math.max(maxLength, s.getLength());
            maxLengthWithout = Math.max(maxLengthWithout, s.getLengthWithoutHyphen());
            minLength = Math.min(minLength, s.getLength());
            minLengthWithout = Math.min(minLengthWithout, s.getLengthWithoutHyphen());
        }
        avgLength /= size;
        avgLengthWithout /= size;
    }

    void print() {
        String format = "%-28s%s%n";
        boolean finished = false;
        int counter = 1;
        String s;
        while(! finished) {
            s = "";
            for(int i=0; i < (Math.min(maxLength, counter*60) - (counter-1)*60+1 - ((int)Math.log10((counter-1)*60+1)+1) - ((int)Math.log10(Math.min(maxLength, counter*60))+1)); i++) {
                s += " ";
            }
            System.out.printf(format, "", (counter-1)*60+1 + s + Math.min(maxLength, counter*60));
            //System.out.printf("%5d" ,Math.min(maxLength, counter*60));
            for(Sequence seq : data) {
                System.out.printf(format, seq.getName(), seq.getSequence().substring((counter-1)*60, Math.min(seq.getLength(), counter*60+1)));
            }
            System.out.println();
            if(counter * 60 > maxLength) {
                finished = true;
            }
            counter++;
        }
        System.out.println("Number of sequences: " + size);
        System.out.println("Shortest length: " + minLength + " (excluding \'-\'s: " + minLengthWithout + ")");
        System.out.println("Average length:  " + avgLength + " (excluding \'-\'s: " + avgLengthWithout + ")");
        System.out.println("Longest length:  " + maxLength + " (excluding \'-\'s: " + maxLengthWithout + ")");
        System.out.println("Counts: A: " + Nucleotide.A + ", Ck: " + Nucleotide.C + ", G: " + Nucleotide.G + ", U: " + Nucleotide.U + ", -: " + Nucleotide.Hyphen);
    }
}

