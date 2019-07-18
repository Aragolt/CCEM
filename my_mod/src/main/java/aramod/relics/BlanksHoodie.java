package aramod.relics;
import aramod.utils.TextureLoader;
import basemod.abstracts.CustomRelic;

import com.evacipated.cardcrawl.mod.stslib.relics.OnAfterUseCardRelic;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class BlanksHoodie extends CustomRelic implements OnAfterUseCardRelic {
    public static final String ID = "AraMod:BlanksHoodie";

    public BlanksHoodie(){
        super(ID, TextureLoader.getTexture("aramod/img/relics/blankshoodie.png"), TextureLoader.getTexture("aramod/img/relics/blankshoodie-outline.png"), RelicTier.RARE, LandingSound.MAGICAL);
    }

    @Override
    public void onAfterUseCard(AbstractCard abstractCard, UseCardAction useCardAction) {
        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(null, DamageInfo.createDamageMatrix(2, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}