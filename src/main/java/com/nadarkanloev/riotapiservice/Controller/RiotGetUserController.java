package com.nadarkanloev.riotapiservice.Controller;

import com.nadarkanloev.riotapiservice.DTO.RiotUserDTO;
import com.nadarkanloev.riotapiservice.Model.RiotUser;
import com.nadarkanloev.riotapiservice.Service.RiotGetUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.core.publisher.Mono;

@Controller
@Tag(name = "Получение пользователя по его имени")
@RequiredArgsConstructor
public class RiotGetUserController {

    private final  RiotGetUserService riotGetUserService;

    @Operation(summary = "Запрос на сервер Riot")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешный запрос", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "{\"row_id\": 1, \"accountId\": \"x-pd89cNpqSzb9ecCmhzy_quubHQBtHtBiBgwl_tT6YYiCP1R7u1YAaB\", \"id\": \"_6UU_nshKQQaLlzpb10mK2sQhs0pYxvZEfFLG0-VguXClYKDrnrHiEU4bg\", \"name\": \"VeventumT\", \"profileIconId\": 4547, \"puuid\": \"4bogeYe_o-X0iswKDig1u8WG8NFiWqFA1waNgavxpnW_4AjW55gMfzDdD2DhY9osDZawiNmW5wrbIg\", \"revisionDate\": \"1708281824000\", \"summonerLevel\": 381}")))
    })
    @GetMapping("/getUser")
    public Mono<ResponseEntity> GetAndSaveUser(@RequestBody RiotUserDTO riotUserDTO){
        String summonerName = riotUserDTO.getSummonerName();
        String summonerRegion = riotUserDTO.getRegion();

        return riotGetUserService.fetchUserDataFromSummonerName(summonerName, summonerRegion)
                .flatMap(user -> {
                    RiotUser savedUser = riotGetUserService.saveUser(user);
                    return Mono.just(ResponseEntity.status(HttpStatusCode.valueOf(200)).body(savedUser));
                })
                .cast(ResponseEntity.class)
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("-"));
    }
}
