package CCEMRelics.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theextravagant.powers.BetterVigorPower;
import theextravagant.util.TextureLoader;

import static CCEMRelics.CCEMRelics.*;

public class GrindingGear extends CustomRelic {
    public static final String ID = makeID("GrindingGear");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("GrindingGear.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("GrindingGear.png"));

    public GrindingGear() {
        super(ID, IMG, OUTLINE, RelicTier.COMMON, LandingSound.FLAT);
        counter = 0;
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        this.counter += 1;
        if (this.counter % 5 == 0) {
            this.counter = 0;
            flash();
            this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new BetterVigorPower(AbstractDungeon.player, 2), 2));
        }
    }
}
