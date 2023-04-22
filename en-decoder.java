import java.util.Random;

public class Encoder {
    public static final String characterList = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789()*+,-./";
    public static String encode(String plainText){
        // if no first character provided, generate a random one
        Random random = new Random();
        char offsetChar = characterList.charAt(random.nextInt(44));
        return encode(plainText, offsetChar);
    }
    public static String encode(String plainText, char offsetChar){
        // overload for specific first character
        String encodedString = String.valueOf(Character.toUpperCase(offsetChar)); //add first character to output
        int offset = characterList.indexOf(Character.toUpperCase(offsetChar)); //calculate offset from first character
        plainText = plainText.toUpperCase(); //set everything to uppercase
        for (int i=0; i<plainText.length();i++){ //iterate over  plaintext
            char currentChar = plainText.charAt(i); 
            if (characterList.indexOf(currentChar) == -1){encodedString+=plainText.charAt(i);} ////spaces return as -1, skip spaces
            else {
                int preoffset = characterList.indexOf(currentChar);
                int postoffset = (preoffset-offset)%44;
                if (postoffset<0){postoffset+=44;} // java modulus uses remainder instead?
                char newChar = characterList.charAt(postoffset);
                encodedString += newChar;
            }
        }
        return encodedString;
    }
    
    public static String decode(String encodedText){
		//same thing but +offset instead of -offset
        String decodedString = "";
        encodedText = encodedText.toUpperCase();
        int offset = characterList.indexOf(encodedText.charAt(0));
        for (int j=1;j<encodedText.length();j++){
            char currentChar = encodedText.charAt(j);
            if (characterList.indexOf(currentChar) == -1){decodedString+=encodedText.charAt(j);}
            else {
                int preoffset = characterList.indexOf(currentChar);
                int postoffset = (preoffset+offset)%44;
               // if (postoffset<0){postoffset+=44;} // java modulus uses remainder instead?
                char newChar = characterList.charAt(postoffset);
                decodedString += newChar;
            }
        }
        return decodedString;
    }
    
    public static void main(String args[]) {
        if (args.length == 0){
            System.out.println("Running with input: 'hello world!!{}[]' and random offset, specify java en-decoder.java [(E)ncrypt/(D)ecrypt] [input String] [Offset] for specific");
            String inputString = "hello world!!{}[]";
            String encodedString = encode(inputString);
            System.out.println("Encoded String is: " + encodedString);
            String decodedString = decode(encodedString);
            System.out.println("Decoded String is: " + decodedString);
            return;
        }
        else if (args[0].toUpperCase().equals("E") || args[0].toUpperCase().equals("ENCRYPT")){
            if (args.length != 3){
                System.out.println("specify java en-decoder.java [(E)ncrypt] [input String] [Offset char]");
                return;
            }
            if (args[2].length() != 1){
                System.out.println("offset Char must be single character");
                return;
            }
            else {
                String inputString = args[1];
                char offsetChar = args[2].charAt(0);
                String encodedString = encode(inputString, offsetChar);
                System.out.println("Encoded String is: " + encodedString);
                return;
            }
        }
        else if (args[0].toUpperCase().equals("D") || args[0].toUpperCase().equals("DECRYPT")){
            if (args.length != 2){
                System.out.println("specify java en-decoder.java [(D)ecrypt] [input String]");
                return;
            }
            if (args[1].length() == 0){
                System.out.println("specify java en-decoder.java [(D)ecrypt] [input String]");
                return;
            }
            else {
                String encodedString = args[1];
                String decodedString = decode(encodedString);
                System.out.println("Decoded String is: " + decodedString);
                return;
            }
        } 
    }
}