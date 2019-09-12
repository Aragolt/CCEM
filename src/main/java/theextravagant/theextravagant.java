package theextravagant;

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
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
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
import theextravagant.powers.RustyBucklerRetainTriggerAction;
import theextravagant.powers.TwilightPower;
import theextravagant.relics.DefaultClickableRelic;
import theextravagant.relics.PlaceholderRelic;
import theextravagant.relics.PlaceholderRelic2;
import theextravagant.relics.TranslucentFeather;
import theextravagant.ui.SecondEnergyOrb;
import theextravagant.util.IDCheckDontTouchPls;
import theextravagant.util.TextureLoader;
import theextravagant.variables.DefaultCustomVariable;
import theextravagant.variables.DefaultSecondMagicNumber;
import theextravagant.variables.ReducedBlockVariable;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

@SpireInitializer
public class theextravagant implements
        EditCardsSubscriber,
        EditRelicsSubscriber,
        EditStringsSubscriber,
        EditKeywordsSubscriber,
        EditCharactersSubscriber,
        PostInitializeSubscriber,
        PostEnergyRechargeSubscriber,
        OnStartBattleSubscriber,
        PostBattleSubscriber,
        PostDeathSubscriber,
        OnCardUseSubscriber,
        PostExhaustSubscriber {

    public static final Logger logger = LogManager.getLogger(theextravagant.class.getName());
    private static String modID;

    public static final Color PLACEHOLDER_POTION_LIQUID = CardHelper.getColor(209, 53, 18);
    public static final Color PLACEHOLDER_POTION_HYBRID = CardHelper.getColor(255, 230, 230);
    public static final Color PLACEHOLDER_POTION_SPOTS = CardHelper.getColor(100, 25, 10);

    public static Properties theDefaultDefaultSettings = new Properties();
    public static final String ENABLE_PLACEHOLDER_SETTINGS = "enablePlaceholder";
    public static boolean enablePlaceholder = true;

    private static final String MODNAME = "theextravagant";
    private static final String AUTHOR = "Lobbienjonsji";
    private static final String DESCRIPTION = "Im not your ordinary byrd. I am a peacock.";

    public static final Color EVBLUE = Color.BLUE;
    public static int CardsExhaustedLastTurn;
    public static int CardsExhaustedThisTurn;
    public static boolean PowerPlayedThisTurn;

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
    private static final String THE_DEFAULT_PORTRAIT = "theextravagantResources/images/charSelect/DefaultCharacterPortraitBG.png";
    public static final String THE_DEFAULT_SHOULDER_1 = "theextravagantResources/images/char/defaultCharacter/shoulder.png";
    public static final String THE_DEFAULT_SHOULDER_2 = "theextravagantResources/images/char/defaultCharacter/shoulder2.png";
    public static final String THE_DEFAULT_CORPSE = "theextravagantResources/images/char/defaultCharacter/corpse.png";

    public static final String BADGE_IMAGE = "theextravagantResources/images/Badge.png";

    public static final String THE_DEFAULT_SKELETON_ATLAS = "theextravagantResources/images/char/defaultCharacter/skeleton.atlas";
    public static final String THE_DEFAULT_SKELETON_JSON = "theextravagantResources/images/char/defaultCharacter/skeleton.json";
    public static Texture SecondEnergyOrbCard;
    public static Texture LargeSecondEnergyOrbCard;
    public static Texture SmallSecondEnergyOrb;
    public static final TextureAtlas UIAtlas = new TextureAtlas();
    public static SecondEnergyOrb SecondEnergyOrb;

    public static String makeCardPath(String resourcePath) {
        return getModID() + "Resources/images/cards/" + resourcePath;
    }

    public static String makeRelicPath(String resourcePath) {
        return getModID() + "Resources/images/relics/" + resourcePath;
    }

    public static String makeRelicOutlinePath(String resourcePath) {
        return getModID() + "Resources/images/relics/outline/" + resourcePath;
    }

    public static String makeOrbPath(String resourcePath) {
        return getModID() + "Resources/orbs/" + resourcePath;
    }

    public static String makePowerPath(String resourcePath) {
        return getModID() + "Resources/images/powers/" + resourcePath;
    }

    public static String makeEventPath(String resourcePath) {
        return getModID() + "Resources/images/events/" + resourcePath;
    }

    public theextravagant() {
        logger.info("Subscribe to BaseMod hooks");

        BaseMod.subscribe(this);


        setModID("theextravagant");


        logger.info("Done subscribing");

        logger.info("Creating the color " + TheExtravagant.Enums.EV_BLUE.toString());

        BaseMod.addColor(TheExtravagant.Enums.EV_BLUE, EVBLUE, EVBLUE, EVBLUE,
                EVBLUE, EVBLUE, EVBLUE, EVBLUE,
                ATTACK_EV_BLUE, SKILL_EV_BLUE, POWER_EV_BLUE, ENERGY_ORB_EV_BLUE,
                ATTACK_EV_BLUE_PORTRAIT, SKILL_EV_BLUE_PORTRAIT, POWER_EV_BLUE_PORTRAIT,
                ENERGY_ORB_EV_BLUE_PORTRAIT, CARD_ENERGY_ORB);

        logger.info("Done creating the color");


        logger.info("Adding mod settings");


        theDefaultDefaultSettings.setProperty(ENABLE_PLACEHOLDER_SETTINGS, "FALSE");
        try {
            SpireConfig config = new SpireConfig("theextravagant", "theDefaultConfig", theDefaultDefaultSettings);

            config.load();
            enablePlaceholder = config.getBool(ENABLE_PLACEHOLDER_SETTINGS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("Done adding mod settings");
    }

    public static void setModID(String ID) {
        Gson coolG = new Gson();

        InputStream in = theextravagant.class.getResourceAsStream("/IDCheckStringsDONT-EDIT-AT-ALL.json");
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

        InputStream in = theextravagant.class.getResourceAsStream("/IDCheckStringsDONT-EDIT-AT-ALL.json");
        IDCheckDontTouchPls EXCEPTION_STRINGS = coolG.fromJson(new InputStreamReader(in, StandardCharsets.UTF_8), IDCheckDontTouchPls.class);
        String packageName = theextravagant.class.getPackage().getName();
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
        theextravagant TheExtravagant = new theextravagant();
        logger.info("========================= /Default Mod Initialized. Hello World./ =========================");
    }

    @Override
    public void receiveEditCharacters() {
        logger.info("Beginning to edit characters. " + "Add " + TheExtravagant.Enums.THE_EXTRAVAGANT.toString());

        BaseMod.addCharacter(new TheExtravagant("the Extravagant", TheExtravagant.Enums.THE_EXTRAVAGANT),
                THE_EXTRAVAGANT_BUTTON, THE_DEFAULT_PORTRAIT, TheExtravagant.Enums.THE_EXTRAVAGANT);

        receiveEditPotions();
        logger.info("Added " + TheExtravagant.Enums.THE_EXTRAVAGANT.toString());
    }

    @Override
    public void receivePostInitialize() {
        SecondEnergyOrbCard = TextureLoader.getTexture("theextravagantResources/images/512/card_default_other_orb.png");
        LargeSecondEnergyOrbCard = TextureLoader.getTexture("theextravagantResources/images/1024/card_other_orb.png");
        SmallSecondEnergyOrb = TextureLoader.getTexture(CARD_SECOND_ENERGY_ORB);
        UIAtlas.addRegion("OtherEnergyCard", SecondEnergyOrbCard, 0, 0, SecondEnergyOrbCard.getWidth(), SecondEnergyOrbCard.getHeight());
        UIAtlas.addRegion("LargeOtherEnergyCard", LargeSecondEnergyOrbCard, 0, 0, LargeSecondEnergyOrbCard.getWidth(), LargeSecondEnergyOrbCard.getHeight());
        UIAtlas.addRegion("SmallSecondEnergyOrb", SmallSecondEnergyOrb, 0, 0, SmallSecondEnergyOrb.getWidth(), SmallSecondEnergyOrb.getHeight());
        logger.info("Loading badge image and mod options");
        Texture badgeTexture = TextureLoader.getTexture(BADGE_IMAGE);
        ModPanel settingsPanel = new ModPanel();
        ModLabeledToggleButton enableNormalsButton = new ModLabeledToggleButton("This is the text which goes next to the checkbox.",
                350.0f, 700.0f, Settings.CREAM_COLOR, FontHelper.charDescFont,
                enablePlaceholder,
                settingsPanel,
                (label) -> {
                },
                (button) -> {

                    enablePlaceholder = button.enabled;
                    try {

                        SpireConfig config = new SpireConfig("theextravagant", "theDefaultConfig", theDefaultDefaultSettings);
                        config.setBool(ENABLE_PLACEHOLDER_SETTINGS, enablePlaceholder);
                        config.save();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

        settingsPanel.addUIElement(enableNormalsButton);

        BaseMod.registerModBadge(badgeTexture, MODNAME, AUTHOR, DESCRIPTION, settingsPanel);


        BaseMod.addEvent(IdentityCrisisEvent.ID, IdentityCrisisEvent.class, TheCity.ID);

        SecondEnergyOrb = new SecondEnergyOrb();
        BaseMod.addSaveField("MaxExtravagance", new CustomSavableRaw() {
            @Override
            public JsonElement onSaveRaw() {
                Gson coolG = new Gson();
                logger.info("Saved Shit");
                return coolG.toJsonTree(SecondEnergyOrb.maxEnergy);
            }

            @Override
            public void onLoadRaw(JsonElement jsonElement) {
                if (jsonElement != null) {
                    Gson coolG = new Gson();
                    SecondEnergyOrb.maxEnergy = (coolG.fromJson(jsonElement, Integer.class));
                    logger.info("Loaded Shit");
                }
            }
        });

        logger.info("Done loading badge Image and mod options");
    }

    public void receiveEditPotions() {
        logger.info("Beginning to edit potions");


        BaseMod.addPotion(PlaceholderPotion.class, PLACEHOLDER_POTION_LIQUID, PLACEHOLDER_POTION_HYBRID, PLACEHOLDER_POTION_SPOTS, PlaceholderPotion.POTION_ID, TheExtravagant.Enums.THE_EXTRAVAGANT);

        logger.info("Done editing potions");
    }

    @Override
    public void receiveEditRelics() {
        logger.info("Adding relics");


        BaseMod.addRelicToCustomPool(new PlaceholderRelic(), TheExtravagant.Enums.EV_BLUE);
        BaseMod.addRelicToCustomPool(new DefaultClickableRelic(), TheExtravagant.Enums.EV_BLUE);

        BaseMod.addRelic(new TranslucentFeather(), RelicType.SHARED);
        BaseMod.addRelic(new PlaceholderRelic2(), RelicType.SHARED);


        logger.info("Done adding relics!");
    }


    @Override
    public void receiveEditCards() {
        logger.info("Adding variables");

        pathCheck();

        logger.info("Add variabls");

        BaseMod.addDynamicVariable(new DefaultCustomVariable());
        BaseMod.addDynamicVariable(new DefaultSecondMagicNumber());
        BaseMod.addDynamicVariable(new ReducedBlockVariable());

        logger.info("Adding cards");

        BaseMod.addCard(new SlyStrike());
        BaseMod.addCard(new Clairvoyance());
        BaseMod.addCard(new Triumph());
        BaseMod.addCard(new Fence());
        BaseMod.addCard(new Twostep());
        BaseMod.addCard(new Reconsider());
        BaseMod.addCard(new SkillfullDodge());
        BaseMod.addCard(new Ambush());
        BaseMod.addCard(new GustOfWind());
        BaseMod.addCard(new SnakeOil());
        BaseMod.addCard(new Cutthroat());
        BaseMod.addCard(new SwiftSwitch());
        BaseMod.addCard(new ButtonUp());
        BaseMod.addCard(new Pluck());
        BaseMod.addCard(new SpontaneousStrike());
        BaseMod.addCard(new DAMNATION());
        BaseMod.addCard(new RiskyStrike());
        BaseMod.addCard(new ReSeek());
        BaseMod.addCard(new Agression());
        BaseMod.addCard(new PuffUp());
        BaseMod.addCard(new Sixteen_Tons());
        BaseMod.addCard(new ToolsOfTrickery());
        BaseMod.addCard(new TwilightTone());
        BaseMod.addCard(new Retreat());
        BaseMod.addCard(new WhiffOfMagic());
        BaseMod.addCard(new Flashback());
        BaseMod.addCard(new ZeroHero());
        BaseMod.addCard(new DigUpJunk());
        BaseMod.addCard(new Glamour());
        BaseMod.addCard(new Meditation());
        BaseMod.addCard(new GlyphOfSight());
        BaseMod.addCard(new Invocation());
        BaseMod.addCard(new FocusStab());
        BaseMod.addCard(new GlyphOfChoice());
        BaseMod.addCard(new Accumulation());
        BaseMod.addCard(new GlyphOfPower());
        BaseMod.addCard(new Acceleration());
        BaseMod.addCard(new GlyphOfTime());
        BaseMod.addCard(new Preservation());
        BaseMod.addCard(new Persistence());
        BaseMod.addCard(new Strafe());
        BaseMod.addCard(new IronPlumes());
        BaseMod.addCard(new Courage());
        BaseMod.addCard(new SharpTalons());
        BaseMod.addCard(new Brilliance());
        BaseMod.addCard(new GarbageCannon());
        BaseMod.addCard(new Foil());
        BaseMod.addCard(new Radiance());
        BaseMod.addCard(new RisingPhoenix());
        BaseMod.addCard(new CuttingEdge());
        BaseMod.addCard(new Aftermath());
        BaseMod.addCard(new RustyBuckler());
        BaseMod.addCard(new Precaution());
        BaseMod.addCard(new Outrun());
        BaseMod.addCard(new Redemption());
        BaseMod.addCard(new Overrun());
        BaseMod.addCard(new ManifestIllusion());
        BaseMod.addCard(new Thrill());
        BaseMod.addCard(new RapidPulse());
        BaseMod.addCard(new Overpower());
        logger.info("Making sure the cards are unlocked.");


        UnlockTracker.unlockCard(SlyStrike.ID);
        UnlockTracker.unlockCard(Clairvoyance.ID);
        UnlockTracker.unlockCard(Triumph.ID);
        UnlockTracker.unlockCard(Fence.ID);
        UnlockTracker.unlockCard(Twostep.ID);
        UnlockTracker.unlockCard(Reconsider.ID);
        UnlockTracker.unlockCard(SkillfullDodge.ID);
        UnlockTracker.unlockCard(Ambush.ID);
        UnlockTracker.unlockCard(GustOfWind.ID);
        UnlockTracker.unlockCard(SnakeOil.ID);
        UnlockTracker.unlockCard(Cutthroat.ID);
        UnlockTracker.unlockCard(SwiftSwitch.ID);
        UnlockTracker.unlockCard(ButtonUp.ID);
        UnlockTracker.unlockCard(Pluck.ID);
        UnlockTracker.unlockCard(SpontaneousStrike.ID);
        UnlockTracker.unlockCard(DAMNATION.ID);
        UnlockTracker.unlockCard(RiskyStrike.ID);
        UnlockTracker.unlockCard(ReSeek.ID);
        UnlockTracker.unlockCard(Agression.ID);
        UnlockTracker.unlockCard(PuffUp.ID);
        UnlockTracker.unlockCard(Sixteen_Tons.ID);
        UnlockTracker.unlockCard(ToolsOfTrickery.ID);
        UnlockTracker.unlockCard(TwilightTone.ID);
        UnlockTracker.unlockCard(Retreat.ID);
        UnlockTracker.unlockCard(WhiffOfMagic.ID);
        UnlockTracker.unlockCard(Flashback.ID);
        UnlockTracker.unlockCard(ZeroHero.ID);
        UnlockTracker.unlockCard(DigUpJunk.ID);
        UnlockTracker.unlockCard(Glamour.ID);
        UnlockTracker.unlockCard(Meditation.ID);
        UnlockTracker.unlockCard(GlyphOfSight.ID);
        UnlockTracker.unlockCard(Invocation.ID);
        UnlockTracker.unlockCard(FocusStab.ID);
        UnlockTracker.unlockCard(GlyphOfChoice.ID);
        UnlockTracker.unlockCard(Accumulation.ID);
        UnlockTracker.unlockCard(GlyphOfPower.ID);
        UnlockTracker.unlockCard(Acceleration.ID);
        UnlockTracker.unlockCard(GlyphOfTime.ID);
        UnlockTracker.unlockCard(Preservation.ID);
        UnlockTracker.unlockCard(Persistence.ID);
        UnlockTracker.unlockCard(Strafe.ID);
        UnlockTracker.unlockCard(IronPlumes.ID);
        UnlockTracker.unlockCard(Courage.ID);
        UnlockTracker.unlockCard(SharpTalons.ID);
        UnlockTracker.unlockCard(Brilliance.ID);
        UnlockTracker.unlockCard(GarbageCannon.ID);
        UnlockTracker.unlockCard(Foil.ID);
        UnlockTracker.unlockCard(Radiance.ID);
        UnlockTracker.unlockCard(RisingPhoenix.ID);
        UnlockTracker.unlockCard(CuttingEdge.ID);
        UnlockTracker.unlockCard(Aftermath.ID);
        UnlockTracker.unlockCard(RustyBuckler.ID);
        UnlockTracker.unlockCard(Precaution.ID);
        UnlockTracker.unlockCard(Outrun.ID);
        UnlockTracker.unlockCard(Redemption.ID);
        UnlockTracker.unlockCard(Overrun.ID);
        UnlockTracker.unlockCard(ManifestIllusion.ID);
        UnlockTracker.unlockCard(Thrill.ID);
        UnlockTracker.unlockCard(RapidPulse.ID);
        UnlockTracker.unlockCard(Overpower.ID);
        logger.info("Done adding cards!");
    }

    @Override
    public void receiveEditStrings() {
        logger.info("You seeing this?");
        logger.info("Beginning to edit strings for mod with ID: " + getModID());


        BaseMod.loadCustomStringsFile(CardStrings.class,
                getModID() + "Resources/localization/eng/theextravagant-Card-Strings.json");


        BaseMod.loadCustomStringsFile(PowerStrings.class,
                getModID() + "Resources/localization/eng/theextravagant-Power-Strings.json");


        BaseMod.loadCustomStringsFile(RelicStrings.class,
                getModID() + "Resources/localization/eng/theextravagant-Relic-Strings.json");


        BaseMod.loadCustomStringsFile(EventStrings.class,
                getModID() + "Resources/localization/eng/theextravagant-Event-Strings.json");


        BaseMod.loadCustomStringsFile(PotionStrings.class,
                getModID() + "Resources/localization/eng/theextravagant-Potion-Strings.json");


        BaseMod.loadCustomStringsFile(CharacterStrings.class,
                getModID() + "Resources/localization/eng/theextravagant-Character-Strings.json");


        BaseMod.loadCustomStringsFile(OrbStrings.class,
                getModID() + "Resources/localization/eng/theextravagant-Orb-Strings.json");

        BaseMod.loadCustomStringsFile(TutorialStrings.class,
                getModID() + "Resources/localization/eng/theextravagant-Tutorial-Strings.json");

        BaseMod.loadCustomStringsFile(UIStrings.class,
                getModID() + "Resources/localization/eng/theextravagant-UI-Strings.json");

        logger.info("Done edittting strings");
    }

    @Override
    public void receiveEditKeywords() {


        Gson gson = new Gson();
        String json = Gdx.files.internal(getModID() + "Resources/localization/eng/theextravagant-Keyword-Strings.json").readString(String.valueOf(StandardCharsets.UTF_8));
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

    @Override
    public void receivePostEnergyRecharge() {
        SecondEnergyOrb.currentEnergy = SecondEnergyOrb.maxEnergy;
        Cutthroat.Hasplayedcardthisturn = false;
        CardsExhaustedLastTurn = CardsExhaustedThisTurn;
        CardsExhaustedThisTurn = 0;
        PowerPlayedThisTurn = false;
    }

    @Override
    public void receiveOnBattleStart(AbstractRoom abstractRoom) {
        SecondEnergyOrb.ishidden = !(AbstractDungeon.player instanceof TheExtravagant || AbstractDungeon.player.hasRelic(PrismaticShard.ID));
        SecondEnergyOrb.currentEnergy = SecondEnergyOrb.maxEnergy;
        CardsExhaustedThisTurn = 0;
        CardsExhaustedLastTurn = 0;
        PowerPlayedThisTurn = false;
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new TwilightPower()));
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new RustyBucklerRetainTriggerAction()));
    }

    @Override
    public void receivePostBattle(AbstractRoom abstractRoom) {
        SecondEnergyOrb.ishidden = true;
    }

    @Override
    public void receivePostDeath() {
        SecondEnergyOrb.ishidden = true;
    }

    @Override
    public void receiveCardUsed(AbstractCard abstractCard) {
        Cutthroat.Hasplayedcardthisturn = true;
        if (abstractCard.type == AbstractCard.CardType.POWER) {
            PowerPlayedThisTurn = true;
        }
    }


    @Override
    public void receivePostExhaust(AbstractCard abstractCard) {
        CardsExhaustedThisTurn++;
    }
}
