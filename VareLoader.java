package supermarked.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class VareLoader {

    public static Map<String, Vare> indlaesVarer(String filnavn)
            throws IOException {

        Map<String, Vare> varer = new HashMap<>();

        BufferedReader reader =
                new BufferedReader(new FileReader(filnavn));

        reader.readLine(); // Spring overskriften over

        String linje;

        while ((linje = reader.readLine()) != null) {

            String[] felter = linje.split(";");

            String ean = felter[0];
            String navn = felter[1];
            double pris = Double.parseDouble(felter[3]);

            Vare vare = new Vare(ean, navn, pris);

            varer.put(ean, vare);
        }

        reader.close();

        return varer;
    }
}