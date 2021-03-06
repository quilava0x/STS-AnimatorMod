package eatyourbeets.cards.animator;

import com.evacipated.cardcrawl.mod.stslib.actions.common.FetchAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import eatyourbeets.GameActionsHelper;
import eatyourbeets.cards.AnimatorCard;
import eatyourbeets.cards.Synergies;

public class HououinKyouma extends AnimatorCard
{
    public static final String ID = CreateFullID(HououinKyouma.class.getSimpleName());

    public HououinKyouma()
    {
        super(ID, 1, CardType.SKILL, CardColor.COLORLESS, CardRarity.RARE, CardTarget.SELF);

        Initialize(0, 0, 1);

        this.purgeOnUse = true;

        AddExtendedDescription();

        SetSynergy(Synergies.SteinsGate);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisCombat)
        {
            if (!c.cardID.equals(this.cardID))
            {
                boolean canAdd = true;
                for (AbstractCard c2 : group.group)
                {
                    if (c.cardID.equals(c2.cardID) && c.timesUpgraded == c2.timesUpgraded)
                    {
                        canAdd = false;
                        break;
                    }
                }

                if (canAdd)
                {
                    group.addToTop(c.makeStatEquivalentCopy());
                }
            }
        }

        if (group.size() > 0)
        {
            GameActionsHelper.AddToBottom(new FetchAction(group, this.magicNumber));
        }
    }

    @Override
    public void upgrade()
    {
        if (TryUpgrade())
        {
            upgradeMagicNumber(1);
        }
    }
}