package eatyourbeets.misc.NanamiEffects;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.GameActionsHelper;
import eatyourbeets.cards.animator.Nanami;

public class NanamiEffect_Attack_Defend extends NanamiEffect
{
    public static void Execute(AbstractPlayer p, AbstractMonster m, Nanami nanami)
    {
        GameActionsHelper.GainBlock(p, GetBlock(nanami));
        GameActionsHelper.DamageTarget(p, m, GetDamage(nanami), nanami.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE);
    }

    public static void UpdateDescription(Nanami nanami)
    {
        nanami.rawDescription = Desc(DAMAGE, GetDamage(nanami), true) + Desc(BLOCK, GetBlock(nanami));
    }

    private static int GetBlock(Nanami nanami)
    {
        int diff = (nanami.block - nanami.baseBlock);

        return ((nanami.energyOnUse + 1) * nanami.baseBlock) + diff;
    }

    private static int GetDamage(Nanami nanami)
    {
        int diff = (nanami.damage - nanami.baseDamage);

        return ((nanami.energyOnUse + 1) * nanami.baseDamage) + diff;
    }
}