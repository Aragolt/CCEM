package CCEMRelics.relics;

import CCEMRelics.CCEMRelics;
import CCEMRelics.actions.SortDrawPileAction;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theextravagant.util.TextureLoader;

import static CCEMRelics.CCEMRelics.makeRelicOutlinePath;
import static CCEMRelics.CCEMRelics.makeRelicPath;

public class HistoryBook extends CustomRelic {
    public static final String ID = CCEMRelics.makeID("HistoryBook");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("HistoryBook.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("HistoryBook.png"));

    public HistoryBook() {
        super(ID, IMG, OUTLINE, RelicTier.SHOP, LandingSound.FLAT);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }


    @Override
    public void atBattleStartPreDraw() {
        AbstractDungeon.actionManager.addToBottom(new SortDrawPileAction());
    }

    @Override
    public void onShuffle() {
        AbstractDungeon.actionManager.addToTop(new SortDrawPileAction());
    }

}
