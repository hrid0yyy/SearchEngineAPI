package sample;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.HttpCookie;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        Message message = new Message("user", input);

        // Creating a list of messages (assuming you might have more than one message in the future)
        List<Message> messageList = List.of(message);

        // Creating a MessageList object
        MessageList messageListObject = new MessageList(messageList);

        // Serializing to JSON using Gson
        Gson gson = new Gson();
        String json = gson.toJson(messageListObject);


        HttpRequest posRequest = HttpRequest.newBuilder()
                .uri(new URI("https://open-ai21.p.rapidapi.com/conversationgpt35"))
                .header("X-RapidAPI-Key", "YOUR_RAPID_API_TOKEN")
                .header("X-RapidAPI-Host", "open-ai21.p.rapidapi.com")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> postRespone = httpClient.send(posRequest, HttpResponse.BodyHandlers.ofString());
        messageListObject = gson.fromJson(postRespone.body(), MessageList.class);
        System.out.println(messageListObject.getResult());

    }
}
