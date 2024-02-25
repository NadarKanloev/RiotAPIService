package com.nadarkanloev.riotapiservice.Service;

import com.nadarkanloev.riotapiservice.Model.RiotUser;
import com.nadarkanloev.riotapiservice.Repository.RiotUserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class RiotGetUserService {
    private final RiotUserRepository riotUserRepository;
    private final String API_KEY = "RGAPI-50553020-8b9b-40b4-a36e-300ce97d0c09";

    private  WebClient webClient = WebClient.create();

    public Mono<RiotUser> fetchUserDataFromSummonerName(String summonerName, String region) {
        String request = String.format("https://%s.api.riotgames.com/lol/summoner/v4/summoners/by-name/%s?api_key=%s", region, summonerName, API_KEY);
        log.info(request);
        return webClient.get()
                .uri(request)
                .retrieve()
                .bodyToMono(RiotUser.class);
    }
    @Transactional
    public RiotUser saveUser(RiotUser riotUser){
        try {
            riotUserRepository.save(riotUser);
            return riotUser;
        }catch (Exception e){
            throw new RuntimeException("Failed To Save User" + e.getMessage());
        }
    }
}
