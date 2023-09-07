import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 A class that stores e-mail messages.
 */
class Mailbox {
    private String user;
    private ArrayList<Message> messages;
    private  ArrayList<Message> sentMessages;

    public String getUser() {
        return user;
    }
    /**
     Initializes an empty mailbox.
     */
    public Mailbox(String user)
    {
        this.user = user;
        messages = new ArrayList<Message>();
        sentMessages = new ArrayList<Message>();
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public ArrayList<Message> getSentMessages() {
        return sentMessages;
    }
    /**
     Adds a new message.
     @param m the message
     */
    public void addMessage(Message m) {
        messages = new ArrayList<Message>();
        messages.add(m);
    }

    public void addSentMessage(Message m)
    {
        sentMessages.add(m);
    }
    /**
     Gets the ith message.
     @param i the message number to get
     @return the ith message
     */
    public Message getMessage(int i) {
        return messages.get(i);
    }

    public Message getSentMessage(int i) {
        return sentMessages.get(i);
    }
    /**
     Removes the ith message.
     @param i the message number to remove
     */
    public void removeMessage(int i) {
        sentMessages.remove(i);
    }

    public void removeSentMessage(int i) {
        messages.remove(i);
    }
}
/**
 A class that models an e-mail message.
 */
class Message {
    private String recipient;
    private String sender;
    private String subject;
    private String messageText;
    private Date date;
    private String str;
    /**
     Takes the sender and recipient
     @param recipient the recipient
     @param sender the sender
     */
    public Message(String recipient, String sender, String subject) {
        this.recipient = recipient;
        this.sender = sender;
        this.subject = subject;
        messageText = "";
        this.date = Calendar.getInstance().getTime();
    }
    /**
     Appends a line of text to the message body.
     @param line the line to append
     */
    public void append(String line) {
        messageText += line + "\n";
    }
    /**
     Makes the message into one long string.
     */
    public String toString() {
        str = "From:"+sender+"\nTo:"+recipient+"\nSubject:"+subject +"\nMessageText:"+messageText;
        System.out.println();
        return str;
    }

    public String getMessageHeader() {
        return str;
    }
    /**
     Prints the message text.
     */
    public void print()
    {
        System.out.print(this.toString());
    }
    // Method to initialize the message
    public void send(String sender, String recipient, String subject, Date date) {
        this.sender = sender;
        this.recipient = recipient;
        this.subject = subject;
        this.date = Calendar.getInstance().getTime();
    }
    // Getter and Setter Method for Message class
    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void send(Mailbox sender, Mailbox recipient, Message message) {
        sender.addMessage(message);


        recipient.addSentMessage(message);
    }
}
class MailboxList {

    private MailboxList() {}

    private  static ArrayList<Mailbox> mailboxList = new ArrayList<>();
    public static Mailbox GetUserMailbox(String user) {
        for (Mailbox mailbox: mailboxList) {
            if (mailbox.getUser().equals(user))
                return mailbox;
        }
        // user doesn't exist so create this user
        Mailbox mailbox = new Mailbox(user);
        mailboxList.add(mailbox);
        return mailbox;
    }
}
public class EmailServer {
    //  This class is a sample test driver.  You should change the messages and users to make it your own.
    public static void main(String[] args)
    {
        //MailboxList mailboxOwners = new MailboxList();
        Mailbox sender = MailboxList.GetUserMailbox("Andy");
        Mailbox recipient = MailboxList.GetUserMailbox("Randy");
        Message email = new Message("Randy", "Andy","First Joke");
        email.append("What do you call a fish with no eyes?");
        email.append("A fsh !");

        email.print();
        email.send(sender, recipient, email);

        email = new Message("Randy", "Andy",  "Second Joke");
        email.append("Why do bees have sticky hair?");
        email.append("Because they use honey combs.");
        email.print();
        email.send(sender, recipient, email);

        sender = MailboxList.GetUserMailbox("Andy");
        recipient = MailboxList.GetUserMailbox("Randy");
        Message emailResponse = new Message("Randy", "Andy", "New Joke");
        emailResponse.append("What do you call a pig that does karate?");
        emailResponse.append("A pork chop");
        emailResponse.print();
        emailResponse.send(sender, recipient, emailResponse);
        System.out.println("Inbox:");
        for (Message msg: sender.getMessages()) {
            System.out.println(msg.getMessageHeader());
        }
        System.out.println("Sent:");
        for (Message msg: sender.getSentMessages()) {
            System.out.println(msg.getMessageHeader());
        }
        System.out.println("Inbox:");
        for (Message msg: recipient.getMessages()) {
            System.out.println(msg.getMessageHeader());
        }
        System.out.println("Sent:");
        for (Message msg: recipient.getSentMessages()) {
            System.out.println(msg.getMessageHeader());
        }

    }
}