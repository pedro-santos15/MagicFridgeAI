package com.pedrosantos15.MagicFridgeAI.service;

import com.pedrosantos15.MagicFridgeAI.model.FoodItem;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GeminiService {

    private final WebClient webClient;

    @Value("${gemini.api-key}")
    private String apiKey;

    public GeminiService(WebClient webClient) {
        this.webClient = webClient;
    }

      /*

   curl "https://generativelanguage.googleapis.com/v1beta/models/gemini-3-flash-preview:generateContent" \
  -H "x-goog-api-key: $GEMINI_API_KEY" \
  -H 'Content-Type: application/json' \
  -X POST \
  -d '{
    "contents": [
      {
        "parts": [
          {
            "text": "Explain how AI works in a few words"
          }
        ]
      }
    ]
  }'

     */

    public Mono<String> generateRecipe(List<FoodItem> foodItems){

        String alimentos = foodItems.stream()
                .map(item -> String.format("%s (%s) - Quantidade: %d, Validade: %s",
                        item.getName(), item.getCategory(), item.getQuantity(), item.getExpirationDate()))
                .collect(Collectors.joining("\n"));

        String prompt = "Sugira uma receita simples com os seguintes ingredientes: \n" + alimentos;

        Map<String, Object> body = Map.of(
                "contents", List.of(
                        Map.of(
                                "parts", List.of(
                                        Map.of("text", "Você é um assistente que cria receitas. " + "\n\n" + prompt)
                                )
                        )
                )
        );

        return webClient.post()
                .header("x-goog-api-key", apiKey)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .bodyValue(body)
                .retrieve()
                .bodyToMono(Map.class)
                .map(response -> {
                    var candidates = (List<Map<String, Object>>) response.get("candidates");

                    if (candidates != null && !candidates.isEmpty()) {
                        Map<String, Object> content = (Map<String, Object>) candidates.get(0).get("content");
                        List<Map<String, Object>> parts = (List<Map<String, Object>>) content.get("parts");

                        if (parts != null && !parts.isEmpty()) {
                            return parts.get(0).get("text").toString();
                        }
                    }

                    return "Nenhuma receita foi gerada.";
                });


    }
}
