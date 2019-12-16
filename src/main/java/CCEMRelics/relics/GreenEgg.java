package CCEMRelics.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theextravagant.util.TextureLoader;

import static CCEMRelics.CCEMRelics.*;

public class GreenEgg extends CustomRelic {
    public static final String ID = makeID("GreenEgg");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("GreenEgg.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("GreenEgg.png"));

    public GreenEgg() {
        super(ID, IMG, OUTLINE, RelicTier.BOSS, LandingSound.FLAT);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void onEquip() {
        ++AbstractDungeon.player.energy.energyMaster;
    }

    @Override
    public void onUnequip() {
        --AbstractDungeon.player.energy.energyMaster;
    }


    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.upgraded) {
            action.exhaustCard = true;
        }
    }
}
