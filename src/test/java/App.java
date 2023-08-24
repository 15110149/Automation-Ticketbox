import com.microsoft.playwright.*;

public class App {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            // Launch chromium, firefox or webkit.
            Browser browser = playwright.chromium().launch();
            Page page = browser.newPage();
            // ...
        }
    }
}


