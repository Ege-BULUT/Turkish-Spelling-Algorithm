import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/*
Author: Ege BULUT
Project Subject : Turkish spelling algorithm.

TODO : Fix some special words spellings, add some special rules.
*/

public class Main {
    static ArrayList<noktalama> noktalamalar = new ArrayList<>();
    private static String[] nokt = {",", ".", "\"", "\'", "?", "!", "(", ")",};
    private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        test();
    }

    private static void test() {
        String cumle = scan.nextLine();
        String[] tempKelime = cumle.split(" ");
        for (int i = 0; i < tempKelime.length; i++) {
            for (int n = 0; n < nokt.length; n++) {
                if (tempKelime[i].contains(nokt[n])) {
                    int t_index;
                    if ((tempKelime[i].charAt(0) + "").equals(nokt[n])) {
                        t_index = i - 1;
                    } else {
                        t_index = i;
                    }
                    noktalamalar.add(new noktalama(t_index, nokt[n]));
                }
            }
        }

        String[] kelimeler = cumle.replaceAll("[^a-zA-ZıİöÖüÜğĞçÇşŞ ]", "").split("\\s+");

        for (int i = 0; i < kelimeler.length; i++) {
            Kelime temp = new Kelime(kelimeler[i]);
            for (int h = 0; h < temp.Heceler.size(); h++) {
                System.out.print(temp.Heceler.get(h));
                if (h != temp.Heceler.size() - 1) {
                    System.out.print("-");
                } else {
                    System.out.print(" ");
                }
            }
            for (int n = 0; n < noktalamalar.size(); n++) {
                if (noktalamalar.get(n).sıra == i) {
                    System.out.print(" " + noktalamalar.get(n).karakter);
                }
            }
            System.out.print(" ");
        }
            noktalamalar.clear();
            System.out.println("\n");
            main(null);
    }

    void playSound(String soundFile) {
        File f = new File("./" + soundFile);
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(f.toURI().toURL());
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }

}