package me.invis.hubcore.util.centeredmessage;

/**
 * this code ain't mine, don't remember from where tho lmao
 */

public class CenteredMessage {

    public String output;

    public CenteredMessage(String input) {
        this.output = centeredMessage(input);
    }

    private final static int CENTER_PX = 154;

    private String centeredMessage(String message){

        if(message == null || message.equals("")) return message;

        int messagePxSize = 0;
        boolean previousCode = false;
        boolean isBold = false;

        for(char c : message.toCharArray()) {
            if(c == '§') previousCode = true;
            else if(previousCode) {
                previousCode = false;
                isBold = c == 'l' || c == 'L';
            }
            else {
                DefaultFontInfo dFI = DefaultFontInfo.getDefaultFontInfo(c);
                messagePxSize += isBold ? dFI.getBoldLength() : dFI.getLength();
                messagePxSize++;
            }
        }

        int halvedMessageSize = messagePxSize / 2;
        int toCompensate = CENTER_PX - halvedMessageSize;
        int spaceLength = DefaultFontInfo.SPACE.getLength() + 1;
        int compensated = 0;
        StringBuilder sb = new StringBuilder();
        while(compensated < toCompensate){
            sb.append(" ");
            compensated += spaceLength;
        }
        return sb + message + sb;
    }

}
