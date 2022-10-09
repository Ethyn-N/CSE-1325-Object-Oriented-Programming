package product;
class MixIn {
    public MixIn(MixInFlavor flavor, MixInAmount amount) {
        this.flavor = flavor;
        this.amount = amount;
    }

    @Override
    public String toString() {
        if (amount != MixInAmount.Normal)
            return flavor + " (" + amount + ")";
        else
            return flavor.toString();
    }

    private MixInFlavor flavor;
    private MixInAmount amount;
}