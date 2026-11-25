package domainmodel;

//Tiderne bkiver gemt som hundredele sekunder: 1:05.32 bliver til 6532.
public class SwimTimeFormat {

    public static String format(int hundredths){
        int minutes = hundredths/6000;
        int seconds = hundredths/100)%60;
        int rest = hundredths%100;
        return String.format("%d:%02d.%02d", minutes, seconds, rest);

    }

    private static int parseNumber()


    public static int parse(String input){
        String text = input.trim();
        int minutes = 0;
        String secondsPart = text;

        if(text.contains(":")){
            String[] parts = text.split(":");
            if(parts.length != 2){
                throw new IllegalArgumentException("Invalid Time: " + input);
            }


    }

}
