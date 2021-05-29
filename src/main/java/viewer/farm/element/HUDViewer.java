package viewer.farm.element;

import gui.Color;
import gui.GUI;
import gui.drawer.shape.HorizontalLineDrawer;
import gui.drawer.string.StringDrawer;
import model.Position;
import model.InGameTime;
import model.farm.Currency;
import model.farm.Wallet;
import model.farm.data.Weather;


public class HUDViewer {
    public static final Color HUD_BACKGROUND_COLOR = new Color("#1f1f1f");
    public static final Color CURRENCY_COLOR = new Color("#ffd700");
    private final int width;
    private final int y;

    public HUDViewer(int y, int width) {
        this.width = width;
        this.y = y;
    }

    public void draw(InGameTime time, Weather weather, Wallet wallet, GUI gui) {
        drawBackground(gui);
        drawText(time, weather, wallet, gui);
    }

    private void drawBackground(GUI gui) {
        HorizontalLineDrawer backgroundDrawer = new HorizontalLineDrawer(gui, HUD_BACKGROUND_COLOR);
        backgroundDrawer.draw(new Position(0, this.y), this.width);
    }

    private void drawText(InGameTime time, Weather weather, Wallet wallet, GUI gui) {
        StringDrawer infoDrawer = new StringDrawer(gui, HUD_BACKGROUND_COLOR, Color.WHITE);
        infoDrawer.draw(new Position(0, this.y), time.getDayTimeString());

        infoDrawer.draw(new Position(18, this.y), weather.getName());
        
        StringDrawer currencyDrawer = new StringDrawer(gui, HUD_BACKGROUND_COLOR, CURRENCY_COLOR);
        Position currencyPosition = new Position(this.width - Currency.MAX_STRING_LENGTH, this.y);
        currencyDrawer.draw(currencyPosition, wallet.getCurrency().toStringPadded());
    }
}
