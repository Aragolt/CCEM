package CCEMRelics.rewards;

import CCEMRelics.patches.RerollRewardTypePatch;
import CCEMRelics.relics.RunicDSix;
import basemod.ReflectionHacks;
import basemod.abstracts.CustomReward;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.ModHelper;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rewards.RewardItem;
import theextravagant.util.TextureLoader;

import java.util.ArrayList;
import java.util.Iterator;

import static CCEMRelics.CCEMRelics.makeRewardPath;
import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.*;

public class RerollRewards extends CustomReward {
    private static final Texture ICON = TextureLoader.getTexture(makeRewardPath("Reroll.png"));
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString("Reroll");
    public int amount;

    public RerollRewards(int amount) {
        super(ICON, UI_STRINGS.TEXT[0] + amount + UI_STRINGS.TEXT[1], RerollRewardTypePatch.CCEM_REROLLREWARD);
        this.amount = amount;
    }

    public static ArrayList<AbstractCard> getRewardCards() {
        ArrayList<AbstractCard> retVal = new ArrayList();
        int numCards = 3;

        AbstractRelic r;
        for (Iterator var2 = AbstractDungeon.player.relics.iterator(); var2.hasNext(); numCards = r.changeNumberOfCardsInReward(numCards)) {
            r = (AbstractRelic) var2.next();
        }

        if (ModHelper.isModEnabled("Binary")) {
            --numCards;
        }

        AbstractCard c;
        for (int i = 0; i < numCards; ++i) {
            AbstractCard.CardRarity rarity = rollRarity();
            c = null;
            switch (rarity) {
                case COMMON:
                    cardBlizzRandomizer -= cardBlizzGrowth;
                    if (cardBlizzRandomizer <= cardBlizzMaxOffset) {
                        cardBlizzRandomizer = cardBlizzMaxOffset;
                    }
                case UNCOMMON:
                    break;
                case RARE:
                    cardBlizzRandomizer = cardBlizzStartOffset;
                    break;
            }

            boolean containsDupe = true;

            while (true) {
                while (containsDupe) {
                    containsDupe = false;
                    if (AbstractDungeon.player.hasRelic("PrismaticShard")) {
                        c = CardLibrary.getAnyColorCard(rarity);
                    } else {
                        c = getCard(rarity);
                    }

                    Iterator var6 = retVal.iterator();

                    while (var6.hasNext()) {
                        AbstractCard card = (AbstractCard) var6.next();
                        if (card.cardID.equals(card.cardID)) {
                            containsDupe = true;
                            break;
                        }
                    }
                }

                if (c != null) {
                    retVal.add(c);
                }
                break;
            }
        }

        ArrayList<AbstractCard> retVal2 = new ArrayList();
        Iterator var11 = retVal.iterator();

        while (var11.hasNext()) {
            c = (AbstractCard) var11.next();
            retVal2.add(c.makeCopy());
        }

        var11 = retVal2.iterator();

        while (true) {
            while (var11.hasNext()) {
                c = (AbstractCard) var11.next();
                if (c.rarity != AbstractCard.CardRarity.RARE && cardRng.randomBoolean((float) ReflectionHacks.getPrivateStatic(float.class, "cardUpgradedChance")) && c.canUpgrade()) {
                    c.upgrade();
                } else if (c.type == AbstractCard.CardType.ATTACK && AbstractDungeon.player.hasRelic("Molten Egg 2")) {
                    c.upgrade();
                } else if (c.type == AbstractCard.CardType.SKILL && AbstractDungeon.player.hasRelic("Toxic Egg 2")) {
                    c.upgrade();
                } else if (c.type == AbstractCard.CardType.POWER && AbstractDungeon.player.hasRelic("Frozen Egg 2")) {
                    c.upgrade();
                }
            }

            return retVal2;
        }
    }

    @Override
    public boolean claimReward() {
        ArrayList<RewardItem> CardRewardlist = new ArrayList();
        for (RewardItem Item : combatRewardScreen.rewards) {
            if (Item.cards != null && !Item.cards.isEmpty()) {
                Item.cards = AbstractDungeon.getRewardCards();
                Item.update();
            }
            if (Item.type == RewardType.RELIC) {
                AbstractRelic.RelicTier tier = returnRandomRelicTier();
                Item.relic = AbstractDungeon.returnRandomRelic(tier);
                Item.text = Item.relic.name;
                Item.update();
            }
            if (Item.type == RewardType.POTION) {
                Item.potion = AbstractDungeon.returnRandomPotion();
                Item.text = Item.potion.name;
                Item.update();
            }
            Item.update();
        }
        this.amount -= 1;
        if (AbstractDungeon.player.hasRelic(RunicDSix.ID)) {
            AbstractDungeon.player.getRelic(RunicDSix.ID).counter -= 1;
        }
        if (amount <= 0) {
            return true;
        }
        this.text = UI_STRINGS.TEXT[0] + amount + UI_STRINGS.TEXT[1];

        return false;
    }

    private AbstractRelic.RelicTier returnRandomRelicTier() {
        int roll = AbstractDungeon.relicRng.random(0, 99);
        if (ModHelper.isModEnabled("Elite Swarm")) {
            roll += 10;
        }

        if (roll < 50) {
            return AbstractRelic.RelicTier.COMMON;
        } else {
            return roll > 82 ? AbstractRelic.RelicTier.RARE : AbstractRelic.RelicTier.UNCOMMON;
        }
    }
}
