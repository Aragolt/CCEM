package CCEMRelics.relics;

import CCEMRelics.CCEMRelics;
import CCEMRelics.patches.SpectralField;
import basemod.BaseMod;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.random.Random;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;
import theextravagant.util.TextureLoader;

import java.util.ArrayList;
import java.util.Iterator;

import static CCEMRelics.CCEMRelics.makeRelicOutlinePath;
import static CCEMRelics.CCEMRelics.makeRelicPath;

public class BiPhaseBiPlane extends CustomRelic {

    // ID, images, text.
    public static final String ID = CCEMRelics.makeID("BiPhaseBiplane");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("BiPhaseBiplane.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("BiPhaseBiplane.png"));
    private static final CardStrings cardStringsPurge = CardCrawlGame.languagePack.getCardStrings("purgecard");
    public static final String DESCRIPTIONP = cardStringsPurge.DESCRIPTION;
    private static final CardStrings cardStringsSpectral = CardCrawlGame.languagePack.getCardStrings("spectralcard");
    public static final String DESCRIPTIONSP = cardStringsSpectral.DESCRIPTION;


    public BiPhaseBiPlane() {
        super(ID, IMG, OUTLINE, RelicTier.SHOP, LandingSound.CLINK);
    }

    private static AbstractCard BiPlaneAction(Random rng) {
        ArrayList<AbstractCard> list = new ArrayList();
        Iterator exhaustPileIterator = AbstractDungeon.player.exhaustPile.group.iterator();

        while (exhaustPileIterator.hasNext()) {
            AbstractCard c = (AbstractCard) exhaustPileIterator.next();
            if (c.type != AbstractCard.CardType.CURSE && c.type != AbstractCard.CardType.STATUS) {
                list.add(c);
            }
        }

        if (list.size() <= 0) {
            return null;
        } else {
            return list.get(rng.random(list.size() - 1));
        }
    }

    @Override
    public void atTurnStart() {
        this.flash();
        this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        AbstractCard c = BiPlaneAction(AbstractDungeon.cardRng);
        if (c != null) {
            AbstractCard remove = c;
            c = c.makeSameInstanceOf();
            if (!c.purgeOnUse) {
                c.purgeOnUse = true;
                c.rawDescription += DESCRIPTIONP;
            }
            if (!SpectralField.spectral.get(c)) {
                SpectralField.spectral.set(c, true);
                c.rawDescription += DESCRIPTIONSP;
            }
            c.initializeDescription();
            if (AbstractDungeon.player.hand.size() < BaseMod.MAX_HAND_SIZE) {
                AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(c));
            } else {
                AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(c));
            }
            AbstractDungeon.player.exhaustPile.removeCard(remove);
            this.flash();
        }
    }

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];

    }
}
