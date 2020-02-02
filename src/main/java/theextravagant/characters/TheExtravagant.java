package theextravagant.characters;

import basemod.abstracts.CustomPlayer;
import basemod.animations.AbstractAnimation;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.esotericsoftware.spine.AnimationState;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import theextravagant.cards.Fence;
import theextravagant.cards.QuickAttack;
import theextravagant.cards.SlyStrike;
import theextravagant.cards.Triumph;
import theextravagant.relics.TranslucentFeather;
import theextravagant.theextravagant;

import java.util.ArrayList;

import static theextravagant.characters.TheExtravagant.Enums.EV_BLUE;
import static theextravagant.theextravagant.*;

public class TheExtravagant extends CustomPlayer {
    public static final Logger logger = LogManager.getLogger(theextravagant.class.getName());
    public static final int ENERGY_PER_TURN = 3;
    public static final int STARTING_HP = 75;
    public static final int MAX_HP = 75;
    public static final int STARTING_GOLD = 99;
    public static final int CARD_DRAW = 5;
    public static final int ORB_SLOTS = 0;
    public static final String[] orbTextures = {
            "theextravagantResources/images/char/defaultCharacter/orb/layer1.png",
            "theextravagantResources/images/char/defaultCharacter/orb/layer2.png",
            "theextravagantResources/images/char/defaultCharacter/orb/layer3.png",
            "theextravagantResources/images/char/defaultCharacter/orb/layer4.png",
            "theextravagantResources/images/char/defaultCharacter/orb/layer5.png",
            "theextravagantResources/images/char/defaultCharacter/orb/layer6.png",
            "theextravagantResources/images/char/defaultCharacter/orb/layer1d.png",
            "theextravagantResources/images/char/defaultCharacter/orb/layer2d.png",
            "theextravagantResources/images/char/defaultCharacter/orb/layer3d.png",
            "theextravagantResources/images/char/defaultCharacter/orb/layer4d.png",
            "theextravagantResources/images/char/defaultCharacter/orb/layer5d.png"};
    private static final String ID = makeID("EVCharacter");
    private static final CharacterStrings characterStrings = CardCrawlGame.languagePack.getCharacterString(ID);
    private static final String[] NAMES = characterStrings.NAMES;
    private static final String[] TEXT = characterStrings.TEXT;

    public TheExtravagant(String name, PlayerClass setClass) {
        super(name, setClass, orbTextures,
                "theextravagantResources/images/char/defaultCharacter/orb/vfx.png", new AbstractAnimation() {
                    @Override
                    public Type type() {
                        return Type.NONE;
                    }
                });


        initializeClass(null,

                THE_DEFAULT_SHOULDER_1,
                THE_DEFAULT_SHOULDER_2,
                THE_DEFAULT_CORPSE,
                getLoadout(), 20.0F, -10.0F, 220.0F, 290.0F, new EnergyManager(ENERGY_PER_TURN));


        loadAnimation(
                THE_DEFAULT_SKELETON_ATLAS,
                THE_DEFAULT_SKELETON_JSON,
                1.0f);
        AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);

        dialogX = (drawX + 0.0F * Settings.scale);
        dialogY = (drawY + 220.0F * Settings.scale);
    }

    @Override
    public CharSelectInfo getLoadout() {
        return new CharSelectInfo(NAMES[0], TEXT[0],
                STARTING_HP, MAX_HP, ORB_SLOTS, STARTING_GOLD, CARD_DRAW, this, getStartingRelics(),
                getStartingDeck(), false);
    }

    @Override
    public ArrayList<String> getStartingDeck() {
        ArrayList<String> retVal = new ArrayList<>();

        logger.info("Begin loading starter Deck Strings");

        for (int i = 0; i < 4; i++) {
            retVal.add(SlyStrike.ID);
        }
        for (int i = 0; i < 4; i++) {
            retVal.add(Fence.ID);
        }
        retVal.add(QuickAttack.ID);
        retVal.add(Triumph.ID);
        return retVal;
    }

    public ArrayList<String> getStartingRelics() {
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add(TranslucentFeather.ID);
        UnlockTracker.markRelicAsSeen(TranslucentFeather.ID);
        return retVal;
    }

    @Override
    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.sound.playA("ATTACK_DAGGER_1", 1.25f);
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.LOW, ScreenShake.ShakeDur.SHORT,
                false);
    }

    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return "ATTACK_DAGGER_1";
    }

    @Override
    public int getAscensionMaxHPLoss() {
        return 0;
    }

    @Override
    public AbstractCard.CardColor getCardColor() {
        return EV_BLUE;
    }

    @Override
    public Color getCardTrailColor() {
        return theextravagant.EVBLUE;
    }

    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontRed;
    }

    @Override
    public String getLocalizedCharacterName() {
        return NAMES[0];
    }

    @Override
    public AbstractCard getStartCardForEvent() {
        return new SlyStrike();
    }

    @Override
    public String getTitle(AbstractPlayer.PlayerClass playerClass) {
        return NAMES[1];
    }

    @Override
    public AbstractPlayer newInstance() {
        return new TheExtravagant(name, chosenClass);
    }

    @Override
    public Color getCardRenderColor() {
        return theextravagant.EVBLUE;
    }

    @Override
    public Color getSlashAttackColor() {
        return theextravagant.EVBLUE;
    }

    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[]{
                AbstractGameAction.AttackEffect.SMASH,
                AbstractGameAction.AttackEffect.BLUNT_HEAVY,
                AbstractGameAction.AttackEffect.BLUNT_HEAVY};
    }

    @Override
    public String getSpireHeartText() {
        return TEXT[1];
    }

    @Override
    public String getVampireText() {
        return TEXT[2];
    }

    public String getPortraitImageName() {
        return "name";
    }

    public static class Enums {
        @SpireEnum
        public static AbstractPlayer.PlayerClass THE_EXTRAVAGANT;
        @SpireEnum(name = "EV_BLUE_COLOR")
        public static AbstractCard.CardColor EV_BLUE;
        @SpireEnum(name = "EV_BLUE_COLOR")
        @SuppressWarnings("unused")
        public static CardLibrary.LibraryType LIBRARY_COLOR;
    }
}
