package domainmodel;

//Tiderne bkiver gemt som hundredele sekunder: 1:05.32 bliver til 6532.
public class SwimTimeFormat {

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

}
