package theovergrown;

import basemod.BaseMod;
import basemod.ModLabeledToggleButton;
import basemod.ModPanel;
import basemod.abstracts.CustomSavableRaw;
import basemod.helpers.RelicType;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.Keyword;
import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.TheCity;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.relics.PrismaticShard;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import theextravagant.cards.*;
import theextravagant.characters.TheExtravagant;
import theextravagant.events.IdentityCrisisEvent;
import theextravagant.potions.PlaceholderPotion;
import theextravagant.relics.DefaultClickableRelic;
import theextravagant.relics.PlaceholderRelic;
import theextravagant.relics.PlaceholderRelic2;
import theextravagant.ui.SecondEnergyOrb;
import theextravagant.util.IDCheckDontTouchPls;
import theextravagant.util.TextureLoader;
import theextravagant.variables.DefaultCustomVariable;
import theextravagant.variables.DefaultSecondMagicNumber;
import theextravagant.variables.ReducedBlockVariable;
import theovergrown.characters.TheOvergrown;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

@SpireInitializer
public class theovergrown implements EditCardsSubscriber, EditRelicsSubscriber, EditStringsSubscriber, EditKeywordsSubscriber, EditCharactersSubscriber, PostInitializeSubscriber
{

    public static final Logger logger = LogManager.getLogger(theovergrown.class.getName());
    private static String modID;

    public static Properties theDefaultDefaultSettings = new Properties();
    public static final String ENABLE_PLACEHOLDER_SETTINGS = "enablePlaceholder";
    public static boolean enablePlaceholder = true;

    private static final String MODNAME = "TheOvergrown";
    private static final String AUTHOR = "EnderGrimm and Aragolt";
    private static final String DESCRIPTION = "[temp]";

    public static final Color EVBLUE = Color.BLUE;

    public static final Color PLACEHOLDER_POTION_LIQUID = CardHelper.getColor(209.0f, 53.0f, 18.0f);
    public static final Color PLACEHOLDER_POTION_HYBRID = CardHelper.getColor(255.0f, 230.0f, 230.0f);
    public static final Color PLACEHOLDER_POTION_SPOTS = CardHelper.getColor(100.0f, 25.0f, 10.0f);

    /**private static final String ATTACK_EV_BLUE = "theextravagantResources/images/512/bg_attack_default_gray.png";
    private static final String SKILL_EV_BLUE = "theextravagantResources/images/512/bg_skill_default_gray.png";
    private static final String POWER_EV_BLUE = "theextravagantResources/images/512/bg_power_default_gray.png";

    private static final String ENERGY_ORB_EV_BLUE = "theextravagantResources/images/512/card_default_gray_orb.png";
    private static final String CARD_ENERGY_ORB = "theextravagantResources/images/512/card_small_orb.png";

    private static final String ATTACK_EV_BLUE_PORTRAIT = "theextravagantResources/images/1024/bg_attack_default_gray.png";
    private static final String SKILL_EV_BLUE_PORTRAIT = "theextravagantResources/images/1024/bg_skill_default_gray.png";
    private static final String POWER_EV_BLUE_PORTRAIT = "theextravagantResources/images/1024/bg_power_default_gray.png";
    private static final String ENERGY_ORB_EV_BLUE_PORTRAIT = "theextravagantResources/images/1024/card_default_gray_orb.png";**/

    private static final String THE_OVERGROWN_BUTTON = "theextravagantResources/images/charSelect/DefaultCharacterButton.png";
    private static final String THE_DEFAULT_PORTRAIT = "theextravagantResources/images/charSelect/DefaultCharacterPortraitBG.png";
    public static final String THE_DEFAULT_SHOULDER_1 = "theextravagantResources/images/char/defaultCharacter/shoulder.png";
    public static final String THE_DEFAULT_SHOULDER_2 = "theextravagantResources/images/char/defaultCharacter/shoulder2.png";
    public static final String THE_DEFAULT_CORPSE = "theextravagantResources/images/char/defaultCharacter/corpse.png";

    public static final String BADGE_IMAGE = "theovergrownResources/img/Badge.png";

    public static final String THE_DEFAULT_SKELETON_ATLAS = "theextravagantResources/images/char/defaultCharacter/skeleton.atlas";
    public static final String THE_DEFAULT_SKELETON_JSON = "theextravagantResources/images/char/defaultCharacter/skeleton.json";
    //public static Texture SecondEnergyOrbCard;
    //public static Texture LargeSecondEnergyOrbCard;
    public static final TextureAtlas UIAtlas = new TextureAtlas();
    //public static SecondEnergyOrb SecondEnergyOrb;

    public static String makeCardPath(String resourcePath) {
        return getModID() + "Resources/img/cards/" + resourcePath;
    }

    public static String makeRelicPath(String resourcePath) {
        return getModID() + "Resources/img/relics/" + resourcePath;
    }

    public static String makeRelicOutlinePath(String resourcePath) {
        return getModID() + "Resources/img/relics/outline/" + resourcePath;
    }

    public static String makeOrbPath(String resourcePath) {
        return getModID() + "Resources/img/orbs/" + resourcePath;
    }

    public static String makePowerPath(String resourcePath) {
        return getModID() + "Resources/img/powers/" + resourcePath;
    }

    public static String makeEventPath(String resourcePath) {
        return getModID() + "Resources/img/events/" + resourcePath;
    }

    public theovergrown() {
        logger.info("Subscribe to BaseMod hooks");

        BaseMod.subscribe(this);


        setModID("theovergrown");


        logger.info("Done subscribing");

        /**logger.info("Creating the color " + TheExtravagant.Enums.EV_BLUE.toString());

        BaseMod.addColor(TheExtravagant.Enums.EV_BLUE, EVBLUE, EVBLUE, EVBLUE,
                EVBLUE, EVBLUE, EVBLUE, EVBLUE,
                ATTACK_EV_BLUE, SKILL_EV_BLUE, POWER_EV_BLUE, ENERGY_ORB_EV_BLUE,
                ATTACK_EV_BLUE_PORTRAIT, SKILL_EV_BLUE_PORTRAIT, POWER_EV_BLUE_PORTRAIT,
                ENERGY_ORB_EV_BLUE_PORTRAIT, CARD_ENERGY_ORB);

        logger.info("Done creating the color");**/


        logger.info("Adding mod settings");


        theDefaultDefaultSettings.setProperty(ENABLE_PLACEHOLDER_SETTINGS, "FALSE");
        try {
            SpireConfig config = new SpireConfig("TheOvergrown", "theDefaultConfig", theDefaultDefaultSettings);

            config.load();
            enablePlaceholder = config.getBool(ENABLE_PLACEHOLDER_SETTINGS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("Done adding mod settings");
    }

    public static void setModID(String ID) {
        Gson coolG = new Gson();

        InputStream in = theovergrown.class.getResourceAsStream("/IDCheckStringsDONT-EDIT-AT-ALL.json");
        IDCheckDontTouchPls EXCEPTION_STRINGS = coolG.fromJson(new InputStreamReader(in, StandardCharsets.UTF_8), IDCheckDontTouchPls.class);
        logger.info("You are attempting to set your mod ID as: " + ID);
        if (ID.equals(EXCEPTION_STRINGS.DEFAULTID)) {
            throw new RuntimeException(EXCEPTION_STRINGS.EXCEPTION);
        } else if (ID.equals(EXCEPTION_STRINGS.DEVID)) {
            modID = EXCEPTION_STRINGS.DEFAULTID;
        } else {
            modID = ID;
        }
        logger.info("Success! ID is " + modID);
    }

    public static String getModID() {
        return modID;
    }

    private static void pathCheck() {
        Gson coolG = new Gson();

        InputStream in = theovergrown.class.getResourceAsStream("/IDCheckStringsDONT-EDIT-AT-ALL.json");
        IDCheckDontTouchPls EXCEPTION_STRINGS = coolG.fromJson(new InputStreamReader(in, StandardCharsets.UTF_8), IDCheckDontTouchPls.class);
        String packageName = theovergrown.class.getPackage().getName();
        FileHandle resourcePathExists = Gdx.files.internal(getModID() + "Resources");
        if (!modID.equals(EXCEPTION_STRINGS.DEVID)) {
            if (!packageName.equals(getModID())) {
                throw new RuntimeException(EXCEPTION_STRINGS.PACKAGE_EXCEPTION + getModID());
            }
            if (!resourcePathExists.exists()) {
                throw new RuntimeException(EXCEPTION_STRINGS.RESOURCE_FOLDER_EXCEPTION + getModID() + "Resources");
            }
        }
    }

    @SuppressWarnings("unused")
    public static void initialize() {
        logger.info("========================= Initializing Default Mod. Hi. =========================");
        theovergrown TheOvergrown = new theovergrown();
        logger.info("========================= /Default Mod Initialized. Hello World./ =========================");
    }

    @Override
    public void receiveEditCharacters() {
        logger.info("Beginning to edit characters. " + "Add " + TheOvergrown.Enums.THE_OVERGROWN.toString());

        BaseMod.addCharacter(new TheOvergrown("the Overgrown", TheOvergrown.Enums.THE_OVERGROWN),
                THE_OVERGROWN_BUTTON, THE_DEFAULT_PORTRAIT, TheOvergrown.Enums.THE_OVERGROWN);

        receiveEditPotions();
        logger.info("Added " + TheOvergrown.Enums.THE_OVERGROWN.toString());
    }

    @Override
    public void receivePostInitialize() {
        Texture badgeTexture = TextureLoader.getTexture(BADGE_IMAGE);
        ModPanel settingsPanel = new ModPanel();
        ModLabeledToggleButton enableNormalsButton = new ModLabeledToggleButton("This is also the text which goes next to the checkbox.",
                350.0f, 700.0f, Settings.CREAM_COLOR, FontHelper.charDescFont,
                enablePlaceholder,
                settingsPanel,
                (label) -> {
                },
                (button) -> {

                    enablePlaceholder = button.enabled;
                    try {

                        SpireConfig config = new SpireConfig("theovergrown", "theDefaultConfig", theDefaultDefaultSettings);
                        config.setBool(ENABLE_PLACEHOLDER_SETTINGS, enablePlaceholder);
                        config.save();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

        settingsPanel.addUIElement(enableNormalsButton);

        BaseMod.registerModBadge(badgeTexture, MODNAME, AUTHOR, DESCRIPTION, settingsPanel);

        //register custom events?

        logger.info("Done loading badge Image and mod options");
    }

    public void receiveEditPotions() {
        logger.info("Beginning to edit potions");


        //BaseMod.addPotion(PlaceholderPotion.class, PLACEHOLDER_POTION_LIQUID, PLACEHOLDER_POTION_HYBRID, PLACEHOLDER_POTION_SPOTS, PlaceholderPotion.POTION_ID, TheExtravagant.Enums.THE_EXTRAVAGANT);

        logger.info("Done editing potions");
    }

    @Override
    public void receiveEditRelics() {
        logger.info("Adding relics");

        //BaseMod.addRelicToCustomPool(new PlaceholderRelic(), TheExtravagant.Enums.EV_BLUE);

        logger.info("Done adding relics!");
    }

    @Override
    public void receiveEditCards() {
        logger.info("Adding variables");

        pathCheck();

        logger.info("Add variabls");

        //BaseMod.addDynamicVariable(new DefaultCustomVariable());

        logger.info("Adding cards");


        //BaseMod.addCard(new OrbSkill());

        logger.info("Making sure the cards are unlocked.");


        //UnlockTracker.unlockCard(OrbSkill.ID);

        logger.info("Done adding cards!");
    }

    @Override
    public void receiveEditStrings() {
        logger.info("You seeing this?");
        logger.info("Beginning to edit strings for mod with ID: " + getModID());


        BaseMod.loadCustomStringsFile(CardStrings.class,
                getModID() + "Resources/local/eng/theovergrown-Card-Strings.json");


        BaseMod.loadCustomStringsFile(PowerStrings.class,
                getModID() + "Resources/local/eng/theovergrown-Power-Strings.json");


        BaseMod.loadCustomStringsFile(RelicStrings.class,
                getModID() + "Resources/local/eng/theovergrown-Relic-Strings.json");


        BaseMod.loadCustomStringsFile(EventStrings.class,
                getModID() + "Resources/local/eng/theovergrown-Event-Strings.json");


        BaseMod.loadCustomStringsFile(PotionStrings.class,
                getModID() + "Resources/local/eng/theovergrown-Potion-Strings.json");


        BaseMod.loadCustomStringsFile(CharacterStrings.class,
                getModID() + "Resources/local/eng/theovergrown-Character-Strings.json");


        BaseMod.loadCustomStringsFile(OrbStrings.class,
                getModID() + "Resources/local/eng/theovergrown-Orb-Strings.json");

        BaseMod.loadCustomStringsFile(TutorialStrings.class,
                getModID() + "Resources/local/eng/theovergrown-Tutorial-Strings.json");

        BaseMod.loadCustomStringsFile(UIStrings.class,
                getModID() + "Resources/local/eng/theovergrown-UI-Strings.json");

        logger.info("Done editing strings");
    }

    @Override
    public void receiveEditKeywords() {


        Gson gson = new Gson();
        String json = Gdx.files.internal(getModID() + "Resources/local/eng/theovergrown-Keyword-Strings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        com.evacipated.cardcrawl.mod.stslib.Keyword[] keywords = gson.fromJson(json, com.evacipated.cardcrawl.mod.stslib.Keyword[].class);

        if (keywords != null) {
            for (Keyword keyword : keywords) {
                BaseMod.addKeyword(getModID().toLowerCase(), keyword.PROPER_NAME, keyword.NAMES, keyword.DESCRIPTION);
            }
        }
    }

    public static String makeID(String idText) {
        return getModID() + ":" + idText;
    }
}
