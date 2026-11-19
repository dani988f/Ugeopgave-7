package supermarked.kasse;

import supermarked.data.Vare;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

public class Kasseapparat {

    private Map<String, Vare> alleVarer;
    private Map<String, Vare> tilbudsVarer;

    public Kasseapparat(Map<String, Vare> alleVarer,
                        Map<String, Vare> tilbudsVarer) {

        this.alleVarer = alleVarer;
        this.tilbudsVarer = tilbudsVarer;
    }

    public void udskrivBon(Collection<Vare> kurv) {

        Map<String, Integer> antalVarer = new LinkedHashMap<>();


        for (Vare vare : kurv) {

            String ean = vare.getEan();

            antalVarer.put(ean,
                    antalVarer.getOrDefault(ean, 0) + 1);
        }

        double total = 0;

        System.out.println("------ KASSEBON ------");


        for (String ean : antalVarer.keySet()) {

            int antal = antalVarer.get(ean);

            Vare vare = alleVarer.get(ean);
            Vare tilbud = tilbudsVarer.get(ean);

            double normalPris = vare.getPris() * antal;

            System.out.println(vare.getNavn());

            System.out.println(
                    antal + " x " +
                            format(vare.getPris()) +
                            " kr. = " +
                            format(normalPris) + " kr."
            );

            double rabat = 0;


            if (tilbud != null &&
                    tilbud.getPris() < vare.getPris()) {

                rabat = (vare.getPris() - tilbud.getPris()) * antal;

                System.out.println(
                        "Tilbud: -" + format(rabat) + " kr."
                );
            }

            total += normalPris - rabat;
        }


        double moms = total * 0.20;

        System.out.println("----------------------");

        System.out.println("I alt: " + format(total) + " kr.");

        System.out.println("Heraf moms: " + format(moms) + " kr.");
    }

    private String format(double belob) {
        return String.format(
                Locale.forLanguageTag("da-DK"), "%.2f", belob);
    }
}