package ACCEMCore;

import basemod.BaseMod;
import basemod.ModLabeledToggleButton;
import basemod.ModPanel;
import basemod.interfaces.EditStringsSubscriber;
import basemod.interfaces.PostInitializeSubscriber;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.UIStrings;

import java.util.Properties;

@SpireInitializer
public class ACCEMCore implements PostInitializeSubscriber, EditStringsSubscriber {
    public static final String ENABLE_EXTRAVAGANT = "enableEnableExtravagant";
    private static final String AUTHOR = "Lobbienjonsji";
    private static final String DESCRIPTION = "Im not your ordinary byrd. I am a peacock.";
    private static final String CARD_ENERGY_ORB = "theextravagantResources/images/512/card_small_orb.png";
    public static boolean enablePlaceholder = true;
    public static Properties CCEMDefaultSettings = new Properties();

    public ACCEMCore() {
        BaseMod.subscribe(this);
        CCEMDefaultSettings.setProperty(ENABLE_EXTRAVAGANT, "TRUE");
        try {
            SpireConfig config = new SpireConfig("CCEM", "CCEM", CCEMDefaultSettings);

            config.load();
            enablePlaceholder = config.getBool(ENABLE_EXTRAVAGANT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unused")
    public static void initialize() {
        ACCEMCore ACCEMCore = new ACCEMCore();
    }

    public static String getModID() {
        return "ACCEMCore";
    }

    @Override
    public void receivePostInitialize() {
        UIStrings EnableCharacters = CardCrawlGame.languagePack.getUIString("EnableCharacters");
        Texture Modtexture = new Texture(Gdx.files.internal(CARD_ENERGY_ORB));
        ModPanel settingsPanel = new ModPanel();
        ModLabeledToggleButton enableNormalsButton = new ModLabeledToggleButton(EnableCharacters.TEXT[0],
                350.0f, 700.0f, Settings.CREAM_COLOR, FontHelper.charDescFont,
                enablePlaceholder,
                settingsPanel,
                (label) -> {
                },
                (button) -> {
                    enablePlaceholder = button.enabled;
                    try {

                        SpireConfig config = new SpireConfig("CCEM", "CCEM", CCEMDefaultSettings);
                        config.setBool(ENABLE_EXTRAVAGANT, enablePlaceholder);
                        config.save();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
        settingsPanel.addUIElement(enableNormalsButton);
        BaseMod.registerModBadge(Modtexture, "CCEM", AUTHOR, DESCRIPTION, settingsPanel);
    }


    @Override
    public void receiveEditStrings() {
        BaseMod.loadCustomStringsFile(com.megacrit.cardcrawl.localization.UIStrings.class,
                getModID() + "Resources/localization/eng/ACCEMCore-UI-Strings.json");
    }
}
