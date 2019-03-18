package eatyourbeets.misc.NanamiEffects;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.GameActionsHelper;
import eatyourbeets.cards.animator.Nanami;

public class NanamiEffect_Defend extends NanamiEffect
{
    public static void Execute(AbstractPlayer p, AbstractMonster m, Nanami nanami)
    {
        GameActionsHelper.DamageTarget(p, m, GetDamage(nanami), nanami.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE);
    }

    public static void UpdateDescription(Nanami nanami)
    {
        nanami.rawDescription = Desc(DAMAGE, GetDamage(nanami));
    }

    private static int GetDamage(Nanami nanami)
    {
        return (nanami.energyOnUse + 1) * nanami.damage;
    }
}