package io.omni.example.screens.app;

import io.omni.example.screens.app.accounts.AccountsScreen;
import io.omni.example.screens.app.siderbar.HomeScreen;
import io.qameta.allure.Step;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractMainScreen extends AbstractAppUI {

    @Step("Navigate to 'Home' screen.")
    public HomeScreen navigateToHomeScreen() {
        swipeToMenuItem(Sidebar.HOME);
        return new HomeScreen();
    }

    @Step("Navigate to 'Accounts' screen.")
    public AccountsScreen navigateToAccountsScreen() {
        swipeToMenuItem(Sidebar.ACCOUNTS);
        return new AccountsScreen();
    }

    @Step("Check is navigation menu item shown.")
    public boolean isNavigationMenuItemShown(Sidebar navItem) {
        swipeToMenuItem(navItem);
        return mobileElementUtils().isElementShown(By.xpath(String.format(VIEW_EQUALS_LOCATOR, navItem.getName())));
    }

    @Step("Swipe to menu item {elem}")
    public void swipeToMenuItem(Sidebar elem) {
        int counter = 0;
        while (!mobileElementUtils().isElementShown(By.xpath(String.format(VIEW_EQUALS_LOCATOR, elem.getName()))) && counter < 10) {
            String elemsLocator = "(//android.widget.ScrollView)[1]/android.view.View";
            List<WebElement> elems = driver.findElements(By.xpath(elemsLocator));
            String start = getValueFromElement(elems.get(0));
            int startIndex = Sidebar.valueOf(start.toUpperCase().replaceAll(StringUtils.SPACE, "_")).ordinal();
            int neededIndex = Sidebar.valueOf(elem.getName().toUpperCase().replaceAll(StringUtils.SPACE, "_")).ordinal();
            Point point1 = mobileElementUtils().getCenterOfElement(elems.get(0));
            Point point2 = mobileElementUtils().getCenterOfElement(elems.get(elems.size() - 1));
            if (startIndex < neededIndex) {
                mobileElementUtils().swipe(point2, point1);
            } else {
                mobileElementUtils().swipe(point1, point2);
            }
            counter++;
        }
        click(By.xpath(String.format(VIEW_EQUALS_LOCATOR, elem.getName())));
    }

    public enum Sidebar {
        HOME("Home"),
        ACCOUNTS("Accounts");

        private final String name;

        Sidebar(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        public static Sidebar getByName(String name) {
            return Arrays.stream(Sidebar.values())
                    .filter(v -> v.getName().equals(name))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException(String.format("There is no value with %s name", name)));
        }
    }
}
