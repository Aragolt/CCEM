package CCEMRelics;

import CCEMRelics.patches.RerollRewardTypePatch;
import CCEMRelics.relics.*;
import CCEMRelics.rewards.RerollRewards;
import basemod.BaseMod;
import basemod.ModPanel;
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
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.rewards.RewardSave;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import theextravagant.ui.SecondEnergyOrb;
import theextravagant.util.IDCheckDontTouchPls;
import theextravagant.util.TextureLoader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

@SpireInitializer
public class CCEMRelics implements
        EditCardsSubscriber,
        EditRelicsSubscriber,
        EditStringsSubscriber,
        EditKeywordsSubscriber,
        PostInitializeSubscriber {

    public static final Logger logger = LogManager.getLogger(CCEMRelics.class.getName());
    public static final String ENABLE_PLACEHOLDER_SETTINGS = "enablePlaceholder";
    public static final Color EVBLUE = Color.BLUE;
    public static final String THE_DEFAULT_SHOULDER_1 = "theextravagantResources/images/char/defaultCharacter/shoulder.png";
    public static final String THE_DEFAULT_SHOULDER_2 = "theextravagantResources/images/char/defaultCharacter/shoulder2.png";
    public static final String THE_DEFAULT_CORPSE = "theextravagantResources/images/char/defaultCharacter/corpse.png";
    public static final String BADGE_IMAGE = "theextravagantResources/images/Badge.png";
    public static final String THE_DEFAULT_SKELETON_ATLAS = "theextravagantResources/images/char/defaultCharacter/skeleton.atlas";
    public static final String THE_DEFAULT_SKELETON_JSON = "theextravagantResources/images/char/defaultCharacter/skeleton.json";
    public static final TextureAtlas UIAtlas = new TextureAtlas();
    private static final String MODNAME = "theextravagant";
    private static final String AUTHOR = "Lobbienjonsji";
    private static final String DESCRIPTION = "Im not your ordinary byrd. I am a peacock.";
    private static final String ATTACK_EV_BLUE = "theextravagantResources/images/512/bg_attack_default_gray.png";
    private static final String SKILL_EV_BLUE = "theextravagantResources/images/512/bg_skill_default_gray.png";
    private static final String POWER_EV_BLUE = "theextravagantResources/images/512/bg_power_default_gray.png";
    private static final String ENERGY_ORB_EV_BLUE = "theextravagantResources/images/512/card_default_gray_orb.png";
    private static final String CARD_ENERGY_ORB = "theextravagantResources/images/512/card_small_orb.png";
    private static final String CARD_SECOND_ENERGY_ORB = "theextravagantResources/images/512/card_small_second_orb.png";
    private static final String ATTACK_EV_BLUE_PORTRAIT = "theextravagantResources/images/1024/bg_attack_default_gray.png";
    private static final String SKILL_EV_BLUE_PORTRAIT = "theextravagantResources/images/1024/bg_skill_default_gray.png";
    private static final String POWER_EV_BLUE_PORTRAIT = "theextravagantResources/images/1024/bg_power_default_gray.png";
    private static final String ENERGY_ORB_EV_BLUE_PORTRAIT = "theextravagantResources/images/1024/card_default_gray_orb.png";
    private static final String THE_EXTRAVAGANT_BUTTON = "theextravagantResources/images/charSelect/DefaultCharacterButton.png";
    private static final String THE_DEFAULT_PORTRAIT = "theextravagantResources/images/charSelect/DefaultCharacterPortraitBGnew.png";
    public static Properties theDefaultDefaultSettings = new Properties();
    public static boolean enablePlaceholder = true;
    public static int CardsExhaustedLastTurn;
    public static int CardsExhaustedThisTurn;
    public static boolean PowerPlayedThisTurn;
    public static Texture SecondEnergyOrbCard;
    public static Texture LargeSecondEnergyOrbCard;
    public static Texture SmallSecondEnergyOrb;
    public static Texture Enchantmentglittera;
    public static Texture Enchantmentglitterb;
    public static Texture Enchantmentglitterc;
    public static SecondEnergyOrb SecondEnergyOrb;
    private static String modID;

    public CCEMRelics() {
        logger.info("Subscribe to BaseMod hooks");
        BaseMod.subscribe(this);
        setModID("CCEMRelics");
        logger.info("Adding mod settings");
        theDefaultDefaultSettings.setProperty(ENABLE_PLACEHOLDER_SETTINGS, "FALSE");
        try {
            SpireConfig config = new SpireConfig("CCEMRelics", "CCEMRelics", theDefaultDefaultSettings);

            config.load();
            enablePlaceholder = config.getBool(ENABLE_PLACEHOLDER_SETTINGS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("Done adding mod settings");
    }

    public static String makeCardPath(String resourcePath) {
        return getModID() + "Resources/images/cards/" + resourcePath;
    }

    public static String makeRelicPath(String resourcePath) {
        return getModID() + "Resources/images/relics/" + resourcePath;
    }

    public static String makeRelicOutlinePath(String resourcePath) {
        return getModID() + "Resources/images/relics/outline/" + resourcePath;
    }

    public static String makeRewardPath(String resourcePath) {
        return getModID() + "Resources/images/reward/" + resourcePath;
    }

    public static String makePowerPath(String resourcePath) {
        return getModID() + "Resources/images/powers/" + resourcePath;
    }

    public static String makeEventPath(String resourcePath) {
        return getModID() + "Resources/images/events/" + resourcePath;
    }

    public static String getModID() {
        return modID;
    }

    public static void setModID(String ID) {
        Gson coolG = new Gson();

        InputStream in = CCEMRelics.class.getResourceAsStream("/IDCheckStringsDONT-EDIT-AT-ALL.json");
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

    private static void pathCheck() {
        Gson coolG = new Gson();

        InputStream in = CCEMRelics.class.getResourceAsStream("/IDCheckStringsDONT-EDIT-AT-ALL.json");
        IDCheckDontTouchPls EXCEPTION_STRINGS = coolG.fromJson(new InputStreamReader(in, StandardCharsets.UTF_8), IDCheckDontTouchPls.class);
        String packageName = CCEMRelics.class.getPackage().getName();
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
        CCEMRelics CCEMRelics = new CCEMRelics();
        logger.info("========================= /Default Mod Initialized. Hello World./ =========================");
    }

    public static String makeID(String idText) {
        return getModID() + ":" + idText;
    }

    @Override
    public void receivePostInitialize() {
        logger.info("Loading badge image and mod options");
        Texture badgeTexture = TextureLoader.getTexture(BADGE_IMAGE);
        ModPanel settingsPanel = new ModPanel();
        BaseMod.registerModBadge(badgeTexture, MODNAME, AUTHOR, DESCRIPTION, settingsPanel);
        logger.info("Done loading badge Image and mod options");
        BaseMod.registerCustomReward(
                RerollRewardTypePatch.CCEM_REROLLREWARD,
                (rewardSave) -> { // this handles what to do when this quest type is loaded.
                    return new RerollRewards(rewardSave.amount);
                },
                (customReward) -> { // this handles what to do when this quest type is saved.
                    return new RewardSave(customReward.type.toString(), null, ((RerollRewards) customReward).amount, 0);
                });
    }


    @Override
    public void receiveEditRelics() {
        logger.info("Adding relics");
        BaseMod.addRelic(new Barrel(), RelicType.SHARED);
        BaseMod.addRelic(new GrapeVine(), RelicType.SHARED);
        BaseMod.addRelic(new Coconut(), RelicType.SHARED);
        BaseMod.addRelic(new HistoryBook(), RelicType.SHARED);
        BaseMod.addRelic(new WarHorn(), RelicType.SHARED);
        BaseMod.addRelic(new BiPhaseBiPlane(), RelicType.SHARED);
        RelicLibrary.addBlue(new Icicle());
        RelicLibrary.addRed(new StainlessSteel());
        RelicLibrary.addGreen(new PaperClip());
        RelicLibrary.addPurple(new Moonstone());
        BaseMod.addRelic(new GreenEgg(), RelicType.SHARED);
        BaseMod.addRelic(new FryingPan(), RelicType.SHARED);
        BaseMod.addRelic(new JestersCrown(), RelicType.SHARED);
        BaseMod.addRelic(new GrindingGear(), RelicType.SHARED);
        BaseMod.addRelic(new RunicDSix(), RelicType.SHARED);
        BaseMod.addRelic(new PorcelainBowl(), RelicType.SHARED);
        logger.info("Done adding relics!");
    }

    @Override
    public void receiveEditCards() {
        logger.info("Done adding cards!");
    }

    @Override
    public void receiveEditStrings() {
        logger.info("You seeing this?");
        logger.info("Beginning to edit strings for mod with ID: " + getModID());


        BaseMod.loadCustomStringsFile(CardStrings.class,
                getModID() + "Resources/localization/eng/CCEMRelics-Card-Strings.json");


        BaseMod.loadCustomStringsFile(PowerStrings.class,
                getModID() + "Resources/localization/eng/CCEMRelics-Power-Strings.json");


        BaseMod.loadCustomStringsFile(RelicStrings.class,
                getModID() + "Resources/localization/eng/CCEMRelics-Relic-Strings.json");


        BaseMod.loadCustomStringsFile(UIStrings.class,
                getModID() + "Resources/localization/eng/CCEMRelics-UI-Strings.json");

        logger.info("Done edittting strings");
    }

    @Override
    public void receiveEditKeywords() {


        Gson gson = new Gson();
        String json = Gdx.files.internal(getModID() + "Resources/localization/eng/CCEMRelics-Keyword-Strings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        com.evacipated.cardcrawl.mod.stslib.Keyword[] keywords = gson.fromJson(json, com.evacipated.cardcrawl.mod.stslib.Keyword[].class);

        if (keywords != null) {
            for (Keyword keyword : keywords) {
                BaseMod.addKeyword(getModID().toLowerCase(), keyword.PROPER_NAME, keyword.NAMES, keyword.DESCRIPTION);
            }
        }
    }

}
