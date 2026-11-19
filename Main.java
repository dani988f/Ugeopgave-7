package supermarked;

import supermarked.data.Vare;
import supermarked.data.VareLoader;
import supermarked.kasse.Kasseapparat;
import supermarked.robot.Robot;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

public class Main {

    public static void main(String[] args) throws IOException {


        Map<String, Vare> alleVarer =
                VareLoader.indlaesVarer("data/varer.csv");

        Map<String, Vare> tilbudsVarer =
                VareLoader.indlaesVarer("data/tilbud.csv");

        Robot robot = new Robot();

        Collection<Vare> kurv =
                robot.fyldIKurv(alleVarer.values());

        Set<String> forskelligeVarer = new HashSet<>();

        for (Vare vare : kurv) {
            forskelligeVarer.add(vare.getEan());
        }

        System.out.println(
                "Forskellige varer: " + forskelligeVarer.size()
        );

        Kasseapparat kasse =
                new Kasseapparat(alleVarer, tilbudsVarer);

        kasse.udskrivBon(kurv);
    }
}