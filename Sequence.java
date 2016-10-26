package Assignment_1;

/**
 * Created by Sebastian on 23.10.2016.
 * Saves sequence and computes some information about the sequence
 */

class Sequence {

    private String name;
    private String sequence;
    private int length;
    private int lengthWithoutHyphen;

    Sequence(String name, String sequence) {
        this.name = name;
        this.sequence = sequence;
        this.length = sequence.length();
        this.lengthWithoutHyphen = this.length;
        for (int i = 0; i < this.length; i++) {
            switch (sequence.charAt(i)) {
                case 'A':
                    Nucleotide.A++;
                    break;
                case 'U':
                    Nucleotide.U++;
                    break;
                case 'C':
                    Nucleotide.C++;
                    break;
                case 'G':
                    Nucleotide.G++;
                    break;
                case '-':
                    Nucleotide.Hyphen++;
                    this.lengthWithoutHyphen--;
                    break;
                default:
                    System.err.println("Error while reading sequence, unexpected character");
            }
        }
    }

    public String getName() {
        return name;
    }

    public String getSequence() {
        return sequence;
    }

    public int getLength() {
        return length;
    }

    public int getLengthWithoutHyphen() {
        return lengthWithoutHyphen;
    }
}
