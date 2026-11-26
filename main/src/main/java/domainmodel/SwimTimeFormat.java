package domainmodel;

//Tiderne bkiver gemt som hundredele sekunder: 1:05.32 bliver til 6532.
public class SwimTimeFormat {

    public static String format(int hundredths){
        int minutes = hundredths/6000;
        int seconds = (hundredths/100)%60;
        int rest = hundredths%100;
        return String.format("%d:%02d.%02d", minutes, seconds, rest);

    }

    private static int parseNumber(String text, String input) {
        try {
            int number = Integer.parseInt(text);
            if (number < 0) {
                throw new IllegalArgumentException("Invalid Time: " + input);
            }
            return number;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid Time" + input);
        }


}

    public static int parse(String input){
        String text = input.trim();
        int minutes = 0;
        String secondsPart = text;

        if(text.contains(":")){
            String[] parts = text.split(":");
            if(parts.length != 2){
                throw new IllegalArgumentException("Invalid Time: " + input);
            }
            minutes = parseNumber(parts[0], input);
            secondsPart = parts[1];
        }

        String[] secondsAndHundreths = secondsPart.split("\\.");
        if(secondsAndHundreths.length != 2 || secondsAndHundreths [1].length() !=2){
            throw new IllegalArgumentException("Invalid Time: " + input);
        }

        int seconds = parseNumber(secondsAndHundreths[0],input);
        int hundredths = parseNumber (secondsAndHundreths[1], input);

        if(seconds >= 60 && text.contains(":")){
            throw new IllegalArgumentException("Invalid Time" + input);
        }

        int total = minutes * 6000 + seconds + 100 + hundredths;
        if(total <= 0){
            throw new IllegalArgumentException("Invalid Time");
        }
        return total;
    }

    }


