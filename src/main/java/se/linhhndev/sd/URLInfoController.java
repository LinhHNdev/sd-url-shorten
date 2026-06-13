package se.linhhndev.sd;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class URLInfoController {

    private final URLInfoService urlInfoService;

    @PostMapping("/shorten")
    public ResponseEntity<String> shorten(@RequestBody ShortenRequest shortenRequest) {
        return new ResponseEntity<>(urlInfoService.shorten(shortenRequest), HttpStatus.OK) ;
    }

    @GetMapping("/{shortLink}")
    public ResponseEntity<Void> redirect(@PathVariable String shortLink) {
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(urlInfoService.redirect(shortLink)))
                .build();
    }
}
