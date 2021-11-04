import java.util.ArrayList;
import java.util.Arrays;

public class Kelime {

    public int UZUNLUK;
    public String KELIME = "";
    public ArrayList<String> Heceler = new ArrayList<>();

    private ArrayList<String> ünlü = new ArrayList<>(
            Arrays.asList(  "a", "e", "ı", "i", "o", "ö", "u", "ü",
                            "A", "E", "I", "İ", "O", "Ö", "U", "Ü"));

    private ArrayList<String> ünsüz = new ArrayList<>(
            Arrays.asList(  "b", "c", "ç", "d", "f", "g", "ğ", "h", "j", "k",
                            "l", "m", "n", "p", "r", "ş", "s", "t", "v", "y", "z",
                            "B", "C", "Ç", "D", "F", "G", "Ğ", "H", "J", "K", "L",
                            "M", "N", "P", "R", "Ş", "S", "T", "V", "Y", "Z"));

    public Kelime(String kelime){
        this.KELIME = kelime;
        this.UZUNLUK = kelime.length();
        this.Heceler = Hecele(kelime);
    }

    private ArrayList<String> Hecele(String kelime){
        String[] harf = kelime.split("");

        for(int i = 0; i<harf.length; i++) {

            String tempHece = "";
            tempHece += harf[i];

            if (Ünlü(harf[i])) {
                if (i<harf.length-1 && !Ünlü(harf[i+1])) {
                    if (i<harf.length-2 && !Ünlü(harf[i+2])) {
                        tempHece += harf[i+1];
                        i++;
                        if (i<harf.length-2 && !Ünlü(harf[i+2])) {
                            tempHece += harf[i+1];
                            i++;
                        }else if (i==harf.length-2){
                            tempHece += harf[i+1];
                            i++;
                        }
                    }
                }
                if (i==harf.length-2){
                    tempHece += harf[i+1];
                    i++;
                }
            }

            else {
                if (i < harf.length - 1 && Ünlü(harf[i + 1])) {
                    tempHece += harf[i + 1];
                    i++;
                    if (i < harf.length - 1 && !Ünlü(harf[i + 1])) {
                        if (i < harf.length - 2 && !Ünlü(harf[i + 2])) {
                            tempHece += harf[i + 1];
                            i++;
                            if (i < harf.length - 2 && !Ünlü(harf[i + 2])) {
                                tempHece += harf[i + 1];
                                i++;
                            }
                        }
                        if (i == harf.length - 2) {
                            tempHece += harf[i + 1];
                            i++;
                        }
                    }
                } else if (i < harf.length - 1 && !Ünlü(harf[i + 1])) {
                    if (i < harf.length - 2 && Ünlü(harf[i + 2])) {
                        tempHece += harf[i+1] +""+harf[i+2];
                        i += 2;
                        if (i < harf.length - 1 && !Ünlü(harf[i + 1])) {
                            tempHece += harf[i+1];
                            i++;
                        }
                    }
                }
            }
            Heceler.add(tempHece);
            tempHece = "";
        }
        return Heceler;
    }

    private boolean Ünlü(String harf){ return ünlü.contains(harf); }
}