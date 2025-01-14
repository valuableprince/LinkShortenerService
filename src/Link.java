import java.util.UUID;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Link {
    private String originalUrl;
    private String shortUrl;
    private String creatorUUID;
    private LocalDateTime expirationTime;
    private int clickCount;
    private int maxClicks;

    public Link(String originalUrl, String creatorUUID, LocalDateTime expirationTime, int maxClicks) {
        this.originalUrl = originalUrl;
        this.shortUrl = "clck.ru/" + UUID.randomUUID().toString().substring(0, 6);
        this.creatorUUID = creatorUUID;
        this.expirationTime = expirationTime;
        this.maxClicks = maxClicks;
        this.clickCount = 0;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public String getCreatorUUID() {
        return creatorUUID;
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expirationTime);
    }

    public boolean isUnavailable() {
        return clickCount >= maxClicks || isExpired();
    }


    public void changeClickLimit(int newLimit) {
        this.maxClicks = newLimit;
    }
}
