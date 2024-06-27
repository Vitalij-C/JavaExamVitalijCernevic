package lt.java.exam.controller;

import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import lt.java.exam.model.Ad;
import lt.java.exam.persistance.AdRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/v1/ad")
@RequiredArgsConstructor
public class AdController {
    public final AdRepository adRepository;

    @PostMapping
    public Ad createAd(@RequestBody Ad ad) {
        return adRepository.save(ad);
    }

    @GetMapping("/{adUuid}")
    public Ad getAd(@PathVariable("adUuid") UUID adUuid) {
        return adRepository.findOneById(adUuid);
    }

    @GetMapping("/all")
    public List<Ad> getAd() {
        return adRepository.findAll();
    }

    @PutMapping("/{adUuid}")
    public Ad updateAd(@RequestBody Ad ad) {
        return adRepository.save(ad);
    }

    @DeleteMapping("/{adUuid}")
    public void deleteAd(@PathParam("adUuid") UUID adUuid) {
        adRepository.deleteById(adUuid);
    }
}