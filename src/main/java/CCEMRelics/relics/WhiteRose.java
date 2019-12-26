package CCEMRelics.relics;

import CCEMRelics.campfireoptions.Prune;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.ThornsPower;
import com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption;
import theextravagant.util.TextureLoader;

import java.util.ArrayList;

import static CCEMRelics.CCEMRelics.*;

public class WhiteRose extends CustomRelic {
    public static final String ID = makeID("WhiteRose");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("WhiteRose.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("WhiteRose.png"));

    public WhiteRose() {
        super(ID, IMG, OUTLINE, RelicTier.UNCOMMON, LandingSound.FLAT);
        this.counter = 0;
    }

    public void atBattleStart() {
        if (this.counter != 0) {
            this.flash();
            this.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new ThornsPower(AbstractDungeon.player, this.counter), this.counter));
            this.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        }

    }

    public boolean canSpawn() {
        return AbstractDungeon.floorNum <= 48 || Settings.isEndless;
    }

    public void addCampfireOption(ArrayList<AbstractCampfireOption> options) {
        options.add(new Prune(this.counter < 6));
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
