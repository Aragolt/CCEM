package theextravagant.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.localization.TutorialStrings;
import theextravagant.util.TextureLoader;

public class SecondEnergyOrb {
    private static final TutorialStrings tutorialStrings = CardCrawlGame.languagePack.getTutorialString("Extravagance");
    public static final String[] MSG = tutorialStrings.TEXT;
    public static final String[] LABEL = tutorialStrings.LABEL;
    public static Texture SecondEnergyOrbbg = TextureLoader.getTexture("theextravagantResources/images/char/defaultCharacter/otherorb/otherorbbg.png");
    public static Texture SecondEnergyOrbvfx0 = TextureLoader.getTexture("theextravagantResources/images/char/defaultCharacter/otherorb/otherorblayer0.png");
    public static Texture SecondEnergyOrbvfx1 = TextureLoader.getTexture("theextravagantResources/images/char/defaultCharacter/otherorb/otherorblayer1.png");
    public static Texture SecondEnergyOrbfg = TextureLoader.getTexture("theextravagantResources/images/char/defaultCharacter/otherorb/otherorbfg.png");
    public static Hitbox hb = new Hitbox(128, 248, 1.15F * 128.0F * Settings.scale, 1.15F * 128.0F * Settings.scale);
    private static float time = 0.0F;
    public int currentEnergy = 0;
    public int maxEnergy;
    public boolean ishidden = true;

    public SecondEnergyOrb() {
        maxEnergy = 3;
    }

    public void render(SpriteBatch sb) {
        /* Rest Well My sweet prince
        if (!ishidden) {
            sb.setColor(Color.WHITE);
            sb.draw(SecondEnergyOrbbg, 128 * Settings.scale, 248 * Settings.scale, 1.15F * 128.0F * Settings.scale, 1.15F * 128.0F * Settings.scale);
            sb.draw(SecondEnergyOrbvfx1, 128.0F * Settings.scale, 248.0F * Settings.scale, 128.0F * 1.15F * Settings.scale / 2.0F, 128.0F * 1.15F * Settings.scale / 2.0F, 128.0F * 1.15F * Settings.scale, 128.0F * 1.15F * Settings.scale, 1.2F, 1.2F, (time * -7) % 360F, 0, 0, 128, 128, false, false);
            sb.draw(SecondEnergyOrbvfx0, 128.0F * Settings.scale, 248.0F * Settings.scale, 128.0F * 1.15F * Settings.scale / 2.0F, 128.0F * 1.15F * Settings.scale / 2.0F, 128.0F * 1.15F * Settings.scale, 128.0F * 1.15F * Settings.scale, 1, 1, (time * 6) % 360F, 0, 0, 128, 128, false, false);
            sb.draw(SecondEnergyOrbfg, 128 * Settings.scale, 248 * Settings.scale, 1.15F * 128.0F * Settings.scale, 1.15F * 128.0F * Settings.scale);
            FontHelper.renderFontCentered(sb, FontHelper.energyNumFontGreen, currentEnergy + "/" + maxEnergy, (float) (128 + 128 * 1.15 * 0.5) * Settings.scale, (float) (248 + 128 * 1.15 * 0.5) * Settings.scale, Color.WHITE.cpy());
        }
        */
    }

    public void tick() {
        /*
        time += Gdx.graphics.getRawDeltaTime();
        */
    }


    public void updatePositions() {
        /*
        hb.update();
        if ((hb.hovered) && (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) && (!AbstractDungeon.isScreenUp)) {
            TipHelper.renderGenericTip(128, 148, LABEL[0], MSG[0]);
        }
        */
    }

    public void addEnergy(int amount) {
        /*
        currentEnergy += amount;
        */
    }
}
