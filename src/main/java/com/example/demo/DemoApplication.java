package com.example.demo;

import com.example.demo.config.WebsocketClientEndpoint;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.URI;
import java.net.URISyntaxException;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) throws URISyntaxException {
		final WebsocketClientEndpoint clientEndPoint = new WebsocketClientEndpoint(new URI("ws://localhost:19130/websocket"));

		// add listener
		clientEndPoint.addMessageHandler(new WebsocketClientEndpoint.MessageHandler() {
			public void handleMessage(String message) {
				System.out.println(message);
			}
		});

		// send message to websocket
		//clientEndPoint.sendMessage("{'event':'addChannel','channel':'ok_btccny_ticker'}");

		JSONObject req = new JSONObject();
		req.put("id", "1");
		req.put("method", "subscribeAddresses");
		JSONArray addresses = new JSONArray();
		JSONObject params = new JSONObject();
		addresses.put("mfoVKjK47MdGoh1JAuk5psqsEWcns811yB");
		params.put("addresses", addresses);
		req.put("params", params);
		clientEndPoint.sendMessage(req.toString());
		SpringApplication.run(DemoApplication.class, args);
	}

}
