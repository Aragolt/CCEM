package CCEMRelics.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theextravagant.util.TextureLoader;

import java.util.Iterator;

import static CCEMRelics.CCEMRelics.*;


public class WarHorn extends CustomRelic {
    public static final String ID = makeID("WarHorn");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("WarHorn.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("WarHorn.png"));

    public WarHorn() {
        super(ID, IMG, OUTLINE, RelicTier.RARE, LandingSound.MAGICAL);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }


    public void atTurnStart() {
        if (!grayscale) {
            this.counter = 0;
        }
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == AbstractCard.CardType.POWER && !this.grayscale) {
            this.counter += 1;
            if (this.counter % 3 == 0) {
                this.counter = -1;
                grayscale = true;
                flash();
                Iterator var3 = AbstractDungeon.getMonsters().monsters.iterator();
                while (var3.hasNext()) {
                    AbstractMonster monster = (AbstractMonster) var3.next();
                    if (!monster.isDead && !monster.isDying) {
                        this.addToBot(new StunMonsterAction(monster, AbstractDungeon.player));
                    }
                }
            }
        }
    }

    public void onVictory() {
        this.counter = -1;
        this.grayscale = false;
    }
}