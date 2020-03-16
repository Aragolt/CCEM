package CCEMRelics.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;

@SpirePatch(
        cls = "com.megacrit.cardcrawl.cards.AbstractCard",
        method = SpirePatch.CLASS
)
public class ChainField {
    public static SpireField<Boolean> chain = new SpireField<>(() -> false);

    @SpirePatch(
            clz = AbstractCard.class,
            method = "makeStatEquivalentCopy"
    )
    public static class MakeStatEquivalentCopy {
        public static AbstractCard Postfix(AbstractCard __result, AbstractCard __instance) {
            chain.set(__result, chain.get(__instance));
            return __result;
        }
    }

    @SpirePatch(clz = AbstractCard.class, method = "makeSameInstanceOf")
    public static class CopyPatch2 {
        @SpirePostfixPatch
        public static AbstractCard RenderhelperPatch1(AbstractCard card, AbstractCard __instance) {
            chain.set(card, false);
            return card;
        }
    }
}