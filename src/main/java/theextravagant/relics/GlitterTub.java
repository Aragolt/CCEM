package theextravagant.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theextravagant.cards.Spark;
import theextravagant.util.TextureLoader;

import static theextravagant.theextravagant.*;

public class GlitterTub extends CustomRelic {

    public static final String ID = makeID("GlitterTub");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("GlitterTub.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("GlitterTub.png"));

    public GlitterTub() {
        super(ID, IMG, OUTLINE, RelicTier.RARE, LandingSound.CLINK);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void onVictory() {
        int a = 0;
        for (int i = 0; i < AbstractDungeon.player.exhaustPile.size(); i++) {
            if (AbstractDungeon.player.exhaustPile.group.get(i) instanceof Spark) {
                a++;
            }
        }
        if (a >= 5) {
            AbstractDungeon.actionManager.addToBottom(new HealAction(AbstractDungeon.player, AbstractDungeon.player, 8));
        }
    }
}
