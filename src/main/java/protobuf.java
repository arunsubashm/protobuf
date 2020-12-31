
import example.simple.Simple;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class protobuf {

    public static void main (String [] args) {
        System.out.println("Hello World");

        Simple.Person.Builder builder = Simple.Person.newBuilder();

        builder.setAge(35)
                .setFirstName("Arun");

        builder.addSamplelist(1);
        builder.addSamplelist(2);
        builder.addSamplelist(3);
        builder.addAllSamplelist(Arrays.asList(4, 5, 6));

        System.out.println(builder.toString());

        Simple.Person message = builder.build();

        // write the protocol buffers binary to a file
        try {
            FileOutputStream outputStream = new FileOutputStream("simple_message.bin");
            message.writeTo(outputStream);
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // send as byte array
        // byte[] bytes = message.toByteArray();

        try {
            System.out.println("Reading from file... ");
            FileInputStream fileInputStream = new FileInputStream("simple_message.bin");
            Simple.Person messageFromFile = Simple.Person.parseFrom(fileInputStream);
            System.out.println(messageFromFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

