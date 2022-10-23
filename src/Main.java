import java.io.BufferedReader;
import java.io.FileReader;

public class Main {
    private static Bearbeitung1 bearbeitung = new Bearbeitung1();
    private static AuftragsQueue queue = new AuftragsQueue();

    public static void main(String[] args) {
        String fileName = "C:\\fahrradwerkstatt1.txt";
        readFile(fileName);
        startVerfahren1();
        System.out.println();
        startVerfahren2();
    }
    public static void readFile(String fileName){
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            String read;
            while((read = reader.readLine()) != null){
                String [] array = read.split(" ");
               queue.getBearbeitung().getList().add(new Auftrag(Integer.parseInt(array[0]), Integer.parseInt(array[1]), false));
               bearbeitung.getList().add(new Auftrag(Integer.parseInt(array[0]), Integer.parseInt(array[1]), false));
            }
        } catch (Exception e){
            System.out.println("File does not exist");
        }
    }

    public static void startVerfahren1(){
        for (Auftrag auftrag: bearbeitung.getList()){
            bearbeitung.startWork(auftrag);
        }
        System.out.println("Verfahren 1:");
        System.out.println("Durchsnittliche Wartezeit ist " + bearbeitung.getDurschnittlicheWartezeit() + " Minuten");
        System.out.println("Maximale Wartezeit ist " + bearbeitung.getMaximaleWartezeit() + " Minuten");
    }

    public static void startVerfahren2(){
        while (queue.getBearbeitung().getListIndex() != -1) {
            queue.startQueue();
        }
        System.out.println("Verfahren 2:");
        System.out.println("Durchsnittliche Wartezeit ist " + queue.getBearbeitung().getDurschnittlicheWartezeit() + " Minuten");
        System.out.println("Maximale Wartezeit ist " + queue.getBearbeitung().getMaximaleWartezeit() + " Minuten");
    }
}