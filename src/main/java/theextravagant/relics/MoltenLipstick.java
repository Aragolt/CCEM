package theextravagant.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.FireBreathingPower;
import theextravagant.util.TextureLoader;

import static theextravagant.theextravagant.*;

public class MoltenLipstick extends CustomRelic {

    public static final String ID = makeID("MoltenLipstick");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("MoltenLipstick.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("MoltenLipstick.png"));

    public MoltenLipstick() {
        super(ID, IMG, OUTLINE, RelicTier.SHOP, LandingSound.MAGICAL);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void atBattleStart() {
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(new Burn(), 2, true, true));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new FireBreathingPower(AbstractDungeon.player, 5)));
    }
}
