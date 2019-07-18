package aramod.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.actions.defect.EvokeAllOrbsAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.Dark;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
import com.megacrit.cardcrawl.orbs.Lightning;

import java.util.ArrayList;

public class OrbVolley extends CustomCard {
    public static final String ID = "AraMod:OrbVolley";
    public static final String IMG = "aramod/img/cards/betaAttack.png";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    public static final int COST = 2;

    public OrbVolley(){
        super(ID, NAME, IMG, COST, DESCRIPTION, CardType.SKILL, CardColor.BLUE, CardRarity.UNCOMMON, CardTarget.NONE);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        ArrayList<AbstractOrb> orbs = new ArrayList<AbstractOrb>();
        for (int x = 0; x < AbstractDungeon.player.orbs.size(); x++){
            if (AbstractDungeon.player.orbs.get(x).getClass() != EmptyOrbSlot.class) {
                orbs.add(AbstractDungeon.player.orbs.get(x));
            }
        }
        for (int x = 0; x < 3; x++){
            AbstractDungeon.actionManager.addToBottom(new ChannelAction(new Lightning()));
            orbs.add(new Lightning());
            if (orbs.size() > AbstractDungeon.player.maxOrbs){
                orbs.remove(0);
            }
        }
        AbstractDungeon.actionManager.addToBottom(new EvokeAllOrbsAction());
        if (upgraded) {
            for (int x = 0; x < orbs.size(); x++) {
                if (orbs.get(x).ID.equals("Dark")){
                    AbstractDungeon.actionManager.addToBottom(new ChannelAction(new Dark()));
                }
                else {
                    AbstractDungeon.actionManager.addToBottom(new ChannelAction(orbs.get(x)));
                }
            }
        }
    }

    @Override
    public void upgrade() {
        if (!upgraded){
            upgradeName();
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy(){
        return new OrbVolley();
    }
}
