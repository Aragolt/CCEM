package CCEMRelics.campfireoptions;

import CCEMRelics.VFX.PruneEffect;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption;
import theextravagant.util.TextureLoader;

import static CCEMRelics.CCEMRelics.makeCampfirePath;

public class Prune extends AbstractCampfireOption {
    public static final String[] TEXT;
    private static final UIStrings uiStrings;

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString("Prune Option");
        TEXT = uiStrings.TEXT;
    }

    public Prune(boolean active) {
        this.label = TEXT[0];
        this.usable = active;
        this.description = active ? TEXT[1] : TEXT[2];
        this.img = TextureLoader.getTexture(makeCampfirePath("Prune.png"));
    }

    public void useOption() {
        if (this.usable) {
            AbstractDungeon.effectList.add(new PruneEffect());
        }

    }
}
