package Assignment_1;

import java.io.IOException;

/**
 * Created by Sebastian on 23.10.2016.
 * starts FastaTool
 */

public class CommandLine {
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("You should specify a FastA file as input!");
            System.exit(1);
        } else {
            FastaTool multiFasta = null;
            try {
                multiFasta = new FastaTool(args[0]);
            } catch (IOException e) {
                System.err.println("Error in reading file");
            }
            multiFasta.print();
        }

    }
}