/*package aramod.relics;
import aramod.actions.DamageRandomEnemyFasterAction;
import aramod.powers.LoseFocusPower;
import aramod.powers.LoseThornsPower;
import aramod.utils.TextureLoader;
import basemod.abstracts.CustomRelic;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ThornsPower;

public class ReadiedClaws extends CustomRelic {
    public static final String ID = "AraMod:ReadiedClaws";

    public ReadiedClaws(){
        super(ID, TextureLoader.getTexture("aramod/img/relics/readiedclaws.png"), TextureLoader.getTexture("aramod/img/relics/readiedclaws-outline.png"), RelicTier.RARE, LandingSound.CLINK);
    }

    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        if (c.type == AbstractCard.CardType.ATTACK){
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new ThornsPower(AbstractDungeon.player, 2), 2));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new LoseThornsPower(AbstractDungeon.player, 2), 2));
        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}*/