/*package aramod;

import aramod.cards.OrbVolley;
import aramod.cards.PocketKnife;
import aramod.events.JustAWallEvent;
import aramod.relics.*;
import basemod.BaseMod;
import basemod.helpers.RelicType;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.evacipated.cardcrawl.mod.stslib.Keyword;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.megacrit.cardcrawl.dungeons.TheBeyond;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@SpireInitializer
public class AraMod implements EditRelicsSubscriber, EditStringsSubscriber, EditKeywordsSubscriber, EditCardsSubscriber, PostInitializeSubscriber {
    public static final Logger logger = LogManager.getLogger(AraMod.class.getName());
    public static final String VERSION = "1.0.0";

    public AraMod(){
        BaseMod.subscribe(this);
    }

    public static void initialize(){
        logger.info("VERSION: " + VERSION);
        //new AraMod();
    }

    @Override
    public void receiveEditRelics(){
        initializeRelics();
    }

    public static void initializeRelics(){
        BaseMod.addRelic(new Jellybeans(), RelicType.SHARED);
        BaseMod.addRelic(new NuclearReactor(), RelicType.SHARED);
        BaseMod.addRelic(new PocketApothecary(), RelicType.SHARED);
        BaseMod.addRelic(new RabbytsFoot(), RelicType.SHARED);
        BaseMod.addRelic(new FuseBox(), RelicType.BLUE);
        BaseMod.addRelic(new MidasKnob(), RelicType.SHARED);
        BaseMod.addRelic(new MidasCore(), RelicType.BLUE);
        BaseMod.addRelic(new BeltOfGluttony(), RelicType.SHARED);
        BaseMod.addRelic(new LostWallet(), RelicType.SHARED);
        BaseMod.addRelic(new BlanksHoodie(), RelicType.SHARED);
        BaseMod.addRelic(new BrokenPillar(), RelicType.SHARED);
        BaseMod.addRelic(new Lemon(), RelicType.SHARED);
        BaseMod.addRelic(new SmashedLemon(), RelicType.SHARED);
        BaseMod.addRelic(new ChunkOfWall(), RelicType.SHARED);
        BaseMod.addRelic(new ButchersCleaver(), RelicType.SHARED);
        BaseMod.addRelic(new ReadiedClaws(), RelicType.SHARED);
        BaseMod.addRelic(new AmuletOfProtection(), RelicType.SHARED);
        BaseMod.addRelic(new ShatteredShield(), RelicType.SHARED);
        BaseMod.addRelic(new DefectiveTurbocharger(), RelicType.SHARED);
        BaseMod.addRelic(new StatueOfPride(), RelicType.SHARED);
        BaseMod.addRelic(new IronRibcage(), RelicType.SHARED);
    }

    @Override
    public void receiveEditStrings(){
        String relicStrings = Gdx.files.internal("aramod/local/eng/relics.json").readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(RelicStrings.class, relicStrings);

        String powerStrings = Gdx.files.internal("aramod/local/eng/powers.json").readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(PowerStrings.class, powerStrings);

        String cardStrings = Gdx.files.internal("aramod/local/eng/cards.json").readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(CardStrings.class, cardStrings);

        String eventStrings = Gdx.files.internal("aramod/local/eng/events.json").readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(EventStrings.class, eventStrings);
    }

    @Override
    public void receiveEditKeywords(){
        Gson gson = new Gson();
        String json = Gdx.files.internal("aramod/local/eng/keywords.json").readString(String.valueOf(StandardCharsets.UTF_8));
        Type typeToken = new TypeToken<Map<String, Keyword>>() {}.getType();

        Map<String, Keyword> keywords = gson.fromJson(json, typeToken);

        keywords.forEach((k,v)->{
            // Keyword word = (Keyword)v;
            logger.info("Adding Keyword - " + v.NAMES[0]);
            BaseMod.addKeyword("aramod:", v.PROPER_NAME, v.NAMES, v.DESCRIPTION);
        });
    }

    @Override
    public void receiveEditCards() {
        BaseMod.addCard(new OrbVolley());
        BaseMod.addCard(new PocketKnife());
        //BaseMod.addCard(new Stuck());
    }

    @Override
    public void receivePostInitialize(){
        BaseMod.addEvent(JustAWallEvent.ID, JustAWallEvent.class, TheBeyond.ID);
    }
}*/