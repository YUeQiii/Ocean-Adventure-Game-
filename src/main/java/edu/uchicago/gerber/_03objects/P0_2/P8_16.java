package edu.uchicago.gerber._03objects.P0_2;

import java.util.ArrayList;
import java.util.List;

public class P8_16 {
    public static void main (String args[]){
        Message message1 = new Message("Harry Morgan","Rudolf Reindeer");
        message1.append("Hope this email finds you well.");
        message1.append("How's everything going?");
        Message message2 = new Message("Rudolf Reindeer","Harry Morgan");
        message2.append("Everything goes well");
        message2.append("How about you?");
        Mailbox mailbox = new Mailbox();
        mailbox.addMessage(message1);
        mailbox.addMessage(message2);
        System.out.println(mailbox.getMessage(0).toString());
        mailbox.removeMessage(0);
        System.out.println(mailbox.getMessage(0).toString());

    }

}

class Message {
    private final String recipient;
    private final String sender;
    private StringBuilder messageBody;

    public Message(String sender, String recipient){
        this.sender = sender;
        this.recipient = recipient;
    }
    public void append(String text){
        messageBody.append(text).append("\n");
    }

    public String toString(){
        return "From: " + sender + "\nTO: "+recipient+"\n"+messageBody.toString();
    }
}
class Mailbox{
    List<Message> messages = new ArrayList<Message>();

    public void addMessage(Message m){
        messages.add(m);
    }

    public Message getMessage(int i){
        return messages.get(i);
    }

    public void removeMessage(int i){
        messages.remove(i);
    }

}
