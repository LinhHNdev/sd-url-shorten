package se.linhhndev.sd;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class URLInfoService {

    private final URLInfoRepository urlInfoRepository;

    public String shorten(ShortenRequest shortenRequest) {
        String shortCode = generateShortCode();
        URLInfo urlInfo = new URLInfo();
        urlInfo.setShortLink(shortCode);
        urlInfo.setRawLink(shortenRequest.getRawLink());

        urlInfoRepository.save(urlInfo);

        return shortCode;
    }

    public String redirect(String shortLink) {
        return urlInfoRepository.findById(shortLink)
                .map(URLInfo::getRawLink)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Short link not found"));
    }

    private String generateShortCode() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder(6);

        for (int i = 0; i < 6; i++) {
            sb.append(characters.charAt(random.nextInt(characters.length())));
        }

        return sb.toString();
    }
}
