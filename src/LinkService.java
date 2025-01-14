import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class LinkService {
    private Map<String, Link> links = new HashMap<>();

    public Link createLink(String originalUrl, String creatorUUID, LocalDateTime expirationTime, int maxClicks) {
        Link newLink = new Link(originalUrl, creatorUUID, expirationTime, maxClicks);
        links.put(newLink.getShortUrl(), newLink);
        return newLink;
    }

    public Link getLink(String shortUrl) {
        return links.get(shortUrl);
    }

    public void deleteLink(String shortUrl, String creatorUUID) {
        Link link = links.get(shortUrl);
        if (link != null && link.getCreatorUUID().equals(creatorUUID)) {
            links.remove(shortUrl);
            System.out.println("Ссылка удалена: " + shortUrl);
        } else {
            System.out.println("У вас нет прав на удаление этой ссылки.");
        }
    }

    public void updateClickLimit(String shortUrl, String creatorUUID, int newLimit) {
        Link link = links.get(shortUrl);
        if (link != null && link.getCreatorUUID().equals(creatorUUID)) {
            link.changeClickLimit(newLimit);
            System.out.println("Лимит переходов обновлен для ссылки: " + shortUrl);
        } else {
            System.out.println("У вас нет прав на изменение этой ссылки.");
        }
    }

    public void checkLinkAvailability(String shortUrl) {
        Link link = links.get(shortUrl);
        if (link != null) {
            if (link.isUnavailable()) {
                System.out.println("Ссылка " + shortUrl + " недоступна.");
            } else {
                System.out.println("Ссылка " + shortUrl + " доступна.");
            }
        } else {
            System.out.println("Ссылка не найдена.");
        }
    }
}
