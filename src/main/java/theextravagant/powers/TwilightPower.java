package theextravagant.powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.InvisiblePower;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theextravagant.cards.TwilightTone;
import theextravagant.util.TextureLoader;

import static theextravagant.theextravagant.makeID;

public class TwilightPower extends AbstractPower implements InvisiblePower {
    public static final String POWER_ID = makeID("TwilightPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final Texture tex84 = TextureLoader.getTexture("theextravagantResources/images/powers/placeholder_power84.png");
    private static final Texture tex32 = TextureLoader.getTexture("theextravagantResources/images/powers/placeholder_power32.png");
    private boolean hasPlayedSkill = false;

    public TwilightPower() {
        name = NAME;
        ID = POWER_ID;
        this.owner = AbstractDungeon.player;
        type = PowerType.BUFF;
        isTurnBased = true;
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);
        updateDescription();
    }

    @Override
    public float atDamageFinalGive(float damage, DamageInfo.DamageType type) {
        int multiplier = 1;
        for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
            if (type == DamageInfo.DamageType.NORMAL && c instanceof TwilightTone) {
                multiplier = 2;
                break;
            }
        }
        return  damage * multiplier;
    }

    @Override
    public void updateDescription() {
        description = "[Intro: TryHardNinja]\n" +
                "Creeper\n" +
                "Aw man\n" +
                "\n" +
                "[Verse 1: TryHardNinja]\n" +
                "So we back in the mine\n" +
                "Got our pickaxe swinging from side to side\n" +
                "Side-side to side\n" +
                "This task, a grueling one\n" +
                "Hope to find some diamonds tonight, night, night\n" +
                "Diamonds tonight\n" +
                "\n" +
                "[Pre-Chorus: TryHardNinja]\n" +
                "Heads up\n" +
                "You hear a sound, turn around and look up\n" +
                "Total shock fills your body\n" +
                "Oh, no, it's you again\n" +
                "I can never forget those eyes, eyes, eyes\n" +
                "Eyes-eye-eyes\n" +
                "\n" +
                "[Chorus: TryHardNinja]\n" +
                "'Cause, baby, tonight\n" +
                "The creeper's tryna steal all our stuff again\n" +
                "'Cause, baby, tonight\n" +
                "You grab your pick, shovel, and bolt again (Bolt again-gain)\n" +
                "And run, run until it's done, done\n" +
                "Until the sun comes up in the morn'\n" +
                "'Cause, baby, tonight\n" +
                "The creeper's tryna steal all our stuff again (Stuff again-gain)";
    }
}
