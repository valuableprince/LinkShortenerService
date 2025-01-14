import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class LinkShortener {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LinkService linkService = new LinkService();
        String creatorUUID = "user-uuid-123";  // Пример UUID пользователя

        int defaultMaxClicks = Utils.getIntProperty("maxClicks");
        int defaultExpiryDays = Utils.getIntProperty("defaultExpiryDays");

        while (true) {
            System.out.println("1. Создать короткую ссылку");
            System.out.println("2. Проверить доступность ссылки");
            System.out.println("3. Обновить лимит переходов");
            System.out.println("4. Удалить ссылку");
            System.out.println("5. Выйти");
            int choice = Integer.parseInt(scanner.nextLine());

            if (choice == 1) {
                System.out.println("Введите исходный URL: ");
                String originalUrl = scanner.nextLine();
                System.out.println("Введите срок действия ссылки в днях (по умолчанию " + defaultExpiryDays + "): ");
                int days = Integer.parseInt(scanner.nextLine());
                days = days > 0 ? days : defaultExpiryDays;

                System.out.println("Введите максимальное количество переходов (по умолчанию " + defaultMaxClicks + "): ");
                int maxClicks = Integer.parseInt(scanner.nextLine());
                maxClicks = maxClicks > 0 ? maxClicks : defaultMaxClicks;

                LocalDateTime expirationTime = LocalDateTime.now().plus(days, ChronoUnit.DAYS);
                Link newLink = linkService.createLink(originalUrl, creatorUUID, expirationTime, maxClicks);
                System.out.println("Короткая ссылка создана: " + newLink.getShortUrl());
            } else if (choice == 2) {
                System.out.println("Введите короткую ссылку для проверки: ");
                String shortUrl = scanner.nextLine();
                linkService.checkLinkAvailability(shortUrl);
            } else if (choice == 3) {
                System.out.println("Введите короткую ссылку для обновления: ");
                String shortUrl = scanner.nextLine();
                System.out.println("Введите новый лимит переходов: ");
                int newLimit = Integer.parseInt(scanner.nextLine());
                linkService.updateClickLimit(shortUrl, creatorUUID, newLimit);
            } else if (choice == 4) {
                System.out.println("Введите короткую ссылку для удаления: ");
                String shortUrl = scanner.nextLine();
                linkService.deleteLink(shortUrl, creatorUUID);
            } else if (choice == 5) {
                break;
            }
        }
        scanner.close();
    }
}
